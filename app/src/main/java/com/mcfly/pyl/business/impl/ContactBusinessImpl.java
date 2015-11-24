package com.mcfly.pyl.business.impl;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import com.mcfly.pyl.business.ContactBusiness;
import com.mcfly.pyl.business.listener.ContactBusinessListener;
import com.mcfly.pyl.sqlite.dal.AndroidContactModel;

import java.util.ArrayList;

/**
 * *************************************************
 * Kayentis© Pyl
 * *************************************************
 * Last change : [23/11/2015]
 * Author :  [mcfly]
 */
public class ContactBusinessImpl implements ContactBusiness {

    private final static String TAG = ContactBusiness.class.getName();

    @Override
    public AndroidContactModel getMeContact(Context context) {
        String[] projection =
                {
                        ContactsContract.Contacts._ID,
                        ContactsContract.Contacts.LOOKUP_KEY,
                        Build.VERSION.SDK_INT
                                >= Build.VERSION_CODES.HONEYCOMB ?
                                ContactsContract.Contacts.DISPLAY_NAME_PRIMARY :
                                ContactsContract.Contacts.DISPLAY_NAME
                };
        Cursor cursor = context.getContentResolver().query(
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI, ContactsContract.Contacts.Data.CONTENT_DIRECTORY),
                projection, null, null, null);
        if(cursor==null || !cursor.moveToFirst() || cursor.getColumnCount()==0) {
            Log.w(TAG, "no contact found");
            return null;
        }
        AndroidContactModel model = new AndroidContactModel();
        model.setId(cursor.getInt(0));
        model.setLookupKey(cursor.getString(1));
        model.setName(cursor.getString(2));
        cursor.close();
        return model;
    }


    public void getContactList(Context context){
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] columns    = new String[] {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER};

        Cursor people = context.getContentResolver().query(uri, null, null, null, null);

        int _nameIndex = people.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        int _numberIndex = people.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

        people.moveToFirst();
        do {
            String name   = people.getString(_nameIndex);
            String number = people.getString(_numberIndex);
            Log.d(TAG, "name:" + name + " number: "+number);
        } while (people.moveToNext());
    }


}
