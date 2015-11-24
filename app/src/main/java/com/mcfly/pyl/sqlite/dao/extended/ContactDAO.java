package com.mcfly.pyl.sqlite.dao.extended;

import android.content.Context;

import com.mcfly.pyl.sqlite.cursor.ContactCursor;
import com.mcfly.pyl.sqlite.dal.wrapper.ContactDalWrapper;

import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.mcfly.pyl.sqlite.contentprovider.ContactContentProvider;
import com.mcfly.pyl.sqlite.dao.BaseDAO;
import com.mcfly.pyl.sqlite.dal.Contact;
import com.mcfly.pyl.sqlite.dao.extended.interfaces.IContact;

public class ContactDAO extends BaseDAO implements IContact {

    private final static String TAG = ContactDAO.class.getName();

    public ContactDAO(Context c) {
        super(c, ContactContentProvider.CONTENT_URI);
    }

    @Override
    public int save(Contact element) {
       int result = add(ContactDalWrapper.getContentValueFromObject(element));
       return result;
    }

    public Contact getContact(String name) {
        Cursor cursor = context.getContentResolver().query(contentProviderUri,
                null,
                String.format("%s='%s'", Contact.COLUMN_NAME, name),
                null,
                null);
        Contact contact = null;
        if(cursor!=null && cursor.moveToFirst()) {
            contact = ContactDalWrapper.getObjectFromDB(new ContactCursor(cursor), 0);
            cursor.close();
        }
        return contact;
    }

    @Override
    public Contact getContact(int id) {
        return null; //TODO
    }
}
