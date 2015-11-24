package com.mcfly.pyl.sqlite.contentprovider;
 
 
import com.mcfly.pyl.sqlite.DbManager;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import com.mcfly.pyl.sqlite.dal.Song;
import android.content.UriMatcher;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class SongContentProvider extends ContentProvider {
    private DbManager dbManager;
    private static final String BASE_PATH = "Song";

    public static final String AUTHORITY = "com.mcfly.pyl.sqlite.contentprovider.SongContentProvider";
    public static final Uri CONTENT_URI = Uri.parse("content://" +AUTHORITY+ "/" + BASE_PATH);
    public static final String TYPE = "Song";

    private static final int DEFAULT_CODE = 1;
    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        static {
            sURIMatcher.addURI(AUTHORITY, BASE_PATH, DEFAULT_CODE);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase database = dbManager.getWritableDatabase();
        int nb = database.delete(Song.TABLE_NAME, selection, null);
        if(nb>0) {
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return nb;
    }
 
    @Override
    public String getType(Uri uri) {
        return SongContentProvider.TYPE;
    }
 
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase database = dbManager.getWritableDatabase();
        long id = database.insert(Song.TABLE_NAME, null, values);
        if(id>0) {
            getContext().getContentResolver().notifyChange(uri,null);
            return Uri.parse(String.valueOf(id));
        }
        return null;
    }
 
    @Override
    public boolean onCreate() {
        dbManager =  DbManager.getInstance(getContext());
        return false;
    }
 
    @Override 
    public Cursor query(Uri uri, String[] projection, String selection,String[] selectionArgs, String sortOrder) { 
        SQLiteDatabase database =  dbManager.getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        String groupBy = "";

        queryBuilder.setTables(Song.TABLE_NAME);
        int uriType = sURIMatcher.match(uri);
        Cursor cursor = queryBuilder.query(database,null,selection,null,groupBy,null,null);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }
 
    @Override
    public int update(Uri uri, ContentValues values, String selection,String[] selectionArgs) {
        SQLiteDatabase database = dbManager.getWritableDatabase();
        int nbColumn = database.update(Song.TABLE_NAME, values, selection, selectionArgs);
        if(nbColumn > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return nbColumn;
    }
}