package com.mcfly.pyl.sqlite;

/**
 * Created by mcfly on 12/02/14.
 */
 import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
 import java.util.ArrayList;

import android.content.Context;
 import android.database.Cursor;
 import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.provider.MediaStore.Files;
import android.util.Log;

public class DbManager extends SQLiteOpenHelper
{
	private final static String TAG = DbManager.class.getName();
	
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "pyl.db";

    public static final String DATABASE_UPDATE_FILES_FOLDER = "sql/updates/";
    public static final String DATABASE_DATABASE_CREATE_FILENAME = "sql/create.sql";
    public static final String DATABASE_DATABASE_CREATE_BASETEST = "sql/baseTest.sql";

    public static final String DATABASE_DATABASE_VIEWS = "sql/views.sql";
    public static final String DATABASE_DATABASE_TRIGGERS = "sql/triggers.sql";

    private static Context context;
    private static DbManager instance;

    public static synchronized DbManager getInstance(Context c) {
        if(instance==null) {
            context = c;
            instance= new DbManager();
        }
        return instance;
    }

    private DbManager() {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DbManager", "...call onCreate");
        createFromSQL(db);
        createViews(db);
        createTriggers(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        Log.d("DbManager", "...call onOpen");
        Log.d("DbManager", "   ... path:"+db.getPath());
        Log.d("DbManager", "   ... opened:" + db.isOpen());
        super.onOpen(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        Log.d("DbManager", "   ... onConfigure:" + db.isOpen());
        super.onConfigure(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "...call onUpgrade");
        Log.d(TAG, "[from:"+oldVersion+"]"+"[to:"+newVersion+"]");
//        if(oldVersion == 2) {
//            applyPatch(db,"1_2.sql");
//        }
//        deleteAllViews(db);
//        deleteAllTriggers(db);
//        createViews(db);
//        createTriggers(db);
    }
    
    //----------------------------
    
    
    private boolean isDataBaseFilled() {
    	// TODO
    	return true;
    }
    
    
    @Override
	public SQLiteDatabase getReadableDatabase() {
		return super.getReadableDatabase();
	}

	@Override
	public SQLiteDatabase getWritableDatabase() {
		return super.getWritableDatabase();
	}

    
    public byte[] copyDataBase_(SQLiteDatabase db) {
    	InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(db.getPath());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int read = 0;
        try {
        	byte[] buffer = new byte[4096];
        	while ( (read = inputStream.read(buffer)) > 0 ) {
        		baos.write(buffer, 0, read);
        	}
        	baos.flush();
		} catch (IOException e1) {
			 Log.e(TAG, "error while copyDataBase_",e1);
		} finally {
			try {
				inputStream.close();
				baos.close();
			} catch (IOException e) {
                Log.e(TAG, "error while closing writer",e);
			}
		}
        return baos.toByteArray();
    }
    
    
    public void copyDbToDownload(SQLiteDatabase db) {
    	File downLoadDir = Environment.getExternalStorageDirectory();
    	File file = new File(downLoadDir.getPath()+"/"+"test.db");
    	InputStream inputStream = null;
    	try {
    		inputStream = new FileInputStream(db.getPath());
    		boolean createdFile = file.createNewFile();
    		FileOutputStream os = null;
    		if(!createdFile)
        		os = context.openFileOutput("test.db",Context.MODE_PRIVATE);    
        	else
        		 os = new FileOutputStream(file);
    		int read = 0;
    		byte[] buffer = new byte[4096];
    		while ( (read = inputStream.read(buffer)) > 0 ) {
    			os.write(buffer, 0, read);
    		}
    		Log.d(TAG, "...end of reading file");
    		os.flush();
    		os.close();
    	} catch (Exception e1) {
    		e1.printStackTrace();
    	}
    	finally {
    		try {
    			inputStream.close();
    		} catch (IOException e) {
    			Log.e(TAG, "error while closing writer",e);
    		}
    	}
    }



    public static void copyFile(FileInputStream fromFile, FileOutputStream toFile) throws IOException {
        FileChannel fromChannel = null;
        FileChannel toChannel = null;
        try {
            fromChannel = fromFile.getChannel();
            toChannel = toFile.getChannel();
            fromChannel.transferTo(0, fromChannel.size(), toChannel);
        } finally {
            try {
                if (fromChannel != null) {
                    fromChannel.close();
                }
            } finally {
                if (toChannel != null) {
                    toChannel.close();
                }
            }
        }
    }

    /**
     * remove data from all the table
     * CAUTION : please take care that all forms have been sent before, else you'll loose
     * some FormData and won't be able to recover them
     */
    public void deleteDatabase() {
        SQLiteDatabase myDb = getWritableDatabase();
        String mySql = " SELECT name FROM sqlite_master "
                + " WHERE type='table'  ";
        Cursor cursor = myDb.rawQuery(mySql, null);
        ArrayList<String> tableList = new ArrayList<String>();
        if(!cursor.moveToFirst()) {
            return;
        }
        do {
            tableList.add(cursor.getString(0));
        } while (cursor.moveToNext());
        cursor.close();
        myDb.setForeignKeyConstraintsEnabled(false);
        for(String table : tableList) {
            myDb.delete(table,null,null);
        }
        myDb.setForeignKeyConstraintsEnabled(true);
    }


    private void createFromSQL(SQLiteDatabase db) {
        InputStream inputStream = null;
        try {
            inputStream = context.getAssets().open(DATABASE_DATABASE_CREATE_FILENAME);
        } catch (IOException e1) {
        	Log.e(TAG, "No such file", e1);
        }
        BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder total = new StringBuilder();
        String line;
        try {
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("DbManager", "...creating db :");
        Log.d("DbManager", db.getPath());
        String[] statements = getStatementsFromFile(total.toString());
        Log.d("DbManager", statements.length + " statements will be executed");
        for(String statement : statements) {
            db.execSQL(statement);
        }
    }
    
    
    private String readFile(String path) {
    	InputStream is = null;
    	try {
    		is = context.getAssets().open(DATABASE_DATABASE_CREATE_BASETEST);
    	} catch (IOException e ){
    		Log.e(TAG, "No such file", e);
    	}
    	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
    	StringBuilder stringBuilder = new StringBuilder();
    	String line;
    	try {
    		while ((line = bufferedReader.readLine()) != null) {
    			stringBuilder.append(line);
            }
    	} catch (IOException e ) {
    		Log.e(TAG, "Error while reading file " + path, e);
    	}
    	return stringBuilder.toString();
    }
    
    

    private void createViews(SQLiteDatabase db) {
        InputStream inputStream = null;
        try {
            inputStream = context.getAssets().open(DATABASE_DATABASE_VIEWS);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder total = new StringBuilder();
        String line;
        try {
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "...creating views to db :");
        Log.d(TAG, db.getPath());
        String[] statements = getStatementsFromFile(total.toString());
        Log.d(TAG, statements.length+" statements will be executed");
        for(String statement : statements) {
        	Log.d(TAG, "###"+statement);
            db.execSQL(statement);
        }
    }


    private void createTriggers(SQLiteDatabase db) {
        InputStream inputStream = null;
        try {
            inputStream = context.getAssets().open(DATABASE_DATABASE_TRIGGERS);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder total = new StringBuilder();
        String line;
        try {
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "...creating triggers to db :");
        Log.d(TAG, db.getPath());
        String[] statements = getTriggerStatementsFromFile(total.toString());
        Log.d(TAG, statements.length+" statements will be executed");
        for(String statement : statements) {
            try {
                db.execSQL(statement+" END;");
            } catch (SQLException e) {
                Log.e(TAG, "..trigger creation exception",e);
            }

        }
    }

    private void applyPatch(SQLiteDatabase db, String updateFile) {
        InputStream inputStream = null;
        try {
            inputStream = context.getAssets().open(DATABASE_UPDATE_FILES_FOLDER+updateFile);
        } catch (IOException e1) {
            Log.e(TAG, "No such file", e1);
        }
        BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder total = new StringBuilder();
        String line;
        try {
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("DbManager", "...creating db :");
        Log.d("DbManager", db.getPath());
        String[] statements = getStatementsFromFile(total.toString());
        Log.d("DbManager", statements.length+" statements will be executed");
        for(String statement : statements) {
            db.execSQL(statement);
        }
    }


    private void deleteAllViews(SQLiteDatabase myDb) {
        String mySql = " SELECT name FROM sqlite_master "
                + " WHERE type in ('view')  ";
        Cursor cursor = myDb.rawQuery(mySql, null);
        ArrayList<String> tableList = new ArrayList<String>();
        if(!cursor.moveToFirst()) {
            return;
        }
        do {
            tableList.add(cursor.getString(0));
        } while (cursor.moveToNext());
        cursor.close();
        myDb.setForeignKeyConstraintsEnabled(false);
        for(String table : tableList) {
            myDb.execSQL(String.format("DROP VIEW %s;", table));
        }
    }

    private void deleteAllTriggers(SQLiteDatabase myDb) {
        String mySql = " SELECT name FROM sqlite_master "
                + " WHERE type in ('trigger')  ";
        Cursor cursor = myDb.rawQuery(mySql, null);
        ArrayList<String> tableList = new ArrayList<String>();
        if(!cursor.moveToFirst()) {
            return;
        }
        do {
            tableList.add(cursor.getString(0));
        } while (cursor.moveToNext());
        cursor.close();
        myDb.setForeignKeyConstraintsEnabled(false);
        for(String table : tableList) {
            myDb.execSQL(String.format("DROP TRIGGER %s;", table));
        }
    }

    private String[] getStatementsFromFile(String statements)
    {
        String[] result = statements.split(";");
        return result;
    }

    private String[] getTriggerStatementsFromFile(String statements)
    {
        String[] result = statements.split("END;");
        return result;
    }


}
