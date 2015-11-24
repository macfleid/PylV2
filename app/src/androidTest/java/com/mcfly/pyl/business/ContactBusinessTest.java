package com.mcfly.pyl.business;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import com.mcfly.pyl.business.exceptions.ContactBusinessException;
import com.mcfly.pyl.business.impl.ContactBusinessImpl;
import com.mcfly.pyl.playlists.SharedPlaylistActivity;
import com.mcfly.pyl.sqlite.DbManager;
import com.mcfly.pyl.sqlite.dal.Contact;

/**
 * *************************************************
 * Mcfly© Pyl
 * *************************************************
 * Last change : [24/11/2015]
 * Author :  [mcfly]
 */
public class ContactBusinessTest extends ActivityInstrumentationTestCase2<SharedPlaylistActivity> {

    private final static String TAG = ContactBusinessTest.class.getName();

    private Context context;
    private SQLiteDatabase database;

    public ContactBusinessTest() {
        super(SharedPlaylistActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        Log.d(TAG, "... call setUp()");
        context = getInstrumentation().getContext();
        super.setUp();
        //-- 1 create savePoint
        try {
            database = DbManager.getInstance(getActivity()).getWritableDatabase();
        } catch(SQLiteException e) {
            Log.e(TAG, "Error while getting writable database",e);
        }
        database.execSQL("savepoint testpoint");
    }

    @Override
    protected void tearDown() throws Exception {
        Log.d(TAG, "... call tearDown()");
        super.tearDown();
        try {
            database = DbManager.getInstance(getActivity()).getWritableDatabase();
        } catch(SQLiteException e) {
            Log.e(TAG, "Error while getting writable database",e);
        }
        //-- restore savePoint
        database.execSQL(";rollback to testpoint");
    }


    /////////////////////////////////////////////////////////////////////////

    /**
     * Get the me contact from systeme DB
     * save a link to it in app db or get it from it
     * @throws ContactBusinessException
     */
    public void testGetMeContact() throws ContactBusinessException {
        ContactBusinessImpl business = new ContactBusinessImpl();
        Contact result = business.getMeContact(getActivity());
        assertNotNull(result);
        assertNotNull(result.getname());
    }
}
