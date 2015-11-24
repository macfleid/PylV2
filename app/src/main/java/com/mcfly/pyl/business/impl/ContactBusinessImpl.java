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
import com.mcfly.pyl.business.exceptions.ContactBusinessException;
import com.mcfly.pyl.business.listener.ContactBusinessListener;
import com.mcfly.pyl.sqlite.dal.AndroidContactModel;
import com.mcfly.pyl.sqlite.dal.Contact;
import com.mcfly.pyl.sqlite.dao.extended.ContactDAO;

import java.util.ArrayList;

/**
 * *************************************************
 * Mcfly© Pyl
 * *************************************************
 * Last change : [23/11/2015]
 * Author :  [mcfly]
 */
public class ContactBusinessImpl implements ContactBusiness {

    private final static String TAG = ContactBusiness.class.getName();

    @Override
    public AndroidContactModel getAndroidMeContact(Context context) throws ContactBusinessException {
        String[] projection =
                {
                        ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY,
                        ContactsContract.RawContacts.CONTACT_ID
                };
        Cursor cursor = context.getContentResolver().query(
                ContactsContract.Profile.CONTENT_RAW_CONTACTS_URI,
                projection, null, null, null);
        if(cursor==null || !cursor.moveToFirst() || cursor.getColumnCount()==0) {
            Log.w(TAG, "no contact found");
            return null;
        }
        AndroidContactModel model = new AndroidContactModel();
        model.setAndroidContactId(cursor.getInt(1));
        model.setName(cursor.getString(0));
        cursor.close();
        return model;
    }

    @Override
    public Contact getMeContact(Context context) throws ContactBusinessException {
        AndroidContactModel androidMeContact = getAndroidMeContact(context);
        if(androidMeContact == null) {
            throw new ContactBusinessException("No me contact found");
        }
        ContactDAO contactDAO = new ContactDAO(context);
        //FIXME use : public Contact getContact(int id) instead
        Contact contact = contactDAO.getContact(androidMeContact.getName());
        if(contact == null) {
            contact = Transform(androidMeContact);
            contact.set_id(contactDAO.save(contact));
            if(contact.get_id()==-1){
                throw new ContactBusinessException("unable to save me contact");
            }
        }
        return contact;
    }


    //////////////////////////////////////////////////////

    private Contact Transform(AndroidContactModel model) {
        Contact contact = new Contact();
        contact.setname(model.getName());
        //-- put the correct URI to search the contact here
        contact.seturi(String.valueOf(model.getAndroidContactId()));
        return contact;
    }






    //////////////////////////////////////////////////////////
    //OLD
//    @Override
//    public AndroidContactModel getAndroidMeContact(Context context) {
//        String[] projection =
//                {
//                        ContactsContract.Contacts._ID,
//                        ContactsContract.Contacts.LOOKUP_KEY,
//                        Build.VERSION.SDK_INT
//                                >= Build.VERSION_CODES.HONEYCOMB ?
//                                ContactsContract.Contacts.DISPLAY_NAME_PRIMARY :
//                                ContactsContract.Contacts.DISPLAY_NAME
//                };
//        Cursor cursor = context.getContentResolver().query(
//                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI, ContactsContract.Contacts.Data.CONTENT_DIRECTORY),
//                projection, null, null, null);
//        if(cursor==null || !cursor.moveToFirst() || cursor.getColumnCount()==0) {
//            Log.w(TAG, "no contact found");
//            return null;
//        }
//        AndroidContactModel model = new AndroidContactModel();
//        model.setId(cursor.getInt(0));
//        model.setLookupKey(cursor.getString(1));
//        model.setName(cursor.getString(2));
//        cursor.close();
//        return model;
//    }

//    public void list(Context context){
//        String[] projection = null;
//        Uri uri = Uri.withAppendedPath(ContactsContract.Profile.CONTENT_RAW_CONTACTS_URI, ContactsContract.Contacts.Data.CONTENT_DIRECTORY);
//        Cursor cursor = context.getContentResolver().query(ContactsContract.Profile.CONTENT_RAW_CONTACTS_URI, projection, null, null, null);
//        if(cursor==null || !cursor.moveToFirst() || cursor.getColumnCount()==0) {
//            Log.w(TAG, "no contact found");
//            return;
//        }
//        int _nameIndex = cursor.getColumnIndex(ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY);
//        int _lookupKey = cursor.getColumnIndex(ContactsContract.RawContacts.CONTACT_ID);
//
//        Log.w(TAG, "-nb contact found : " + cursor.getCount());
//        Log.w(TAG, "-nb column found : " + cursor.getColumnCount());
//        for(int i=0;i<cursor.getColumnCount();i++) {
//            Log.d(TAG, "    #"+cursor.getColumnName(i));
//            if( cursor.getType(i) == Cursor.FIELD_TYPE_STRING) {
//                Log.d(TAG, "-> : "+cursor.getString(i));
//            }
//        }
//        Log.d(TAG, "-name : " + cursor.getString(_nameIndex));
////        Log.w(TAG, "-lookupKey : "+cursor.getString(_lookupKey));
//        cursor.close();
//    }

//    public void getContactList(Context context){
//        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
//        String[] columns    = new String[] {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
//                ContactsContract.CommonDataKinds.Phone.NUMBER};
//
//        Cursor people = context.getContentResolver().query(uri, null, null, null, null);
//
//        int _nameIndex = people.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
//        int _numberIndex = people.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
//
//        if(people.moveToFirst()){
//            return;
//        }
//        do {
//            String name   = people.getString(_nameIndex);
//            String number = people.getString(_numberIndex);
//            Log.d(TAG, "name:" + name + " number: "+number);
//        } while (people.moveToNext());
//    }


}
