package com.mcfly.pyl.sqlite.dal.wrapper;

import android.content.ContentValues;
import java.io.Serializable;
import com.mcfly.pyl.sqlite.utils.DateGetter;
import com.mcfly.pyl.sqlite.dal.Contact;
import com.mcfly.pyl.sqlite.cursor.ContactCursor;
import java.util.Date;

/*
* AUTO GENERATED FILE 
* creation date : 2015-11-20 13:58 
*/
public class ContactDalWrapper {

    public static Contact getObjectFromDB(ContactCursor cursor, int start) { 
        Contact object_ = new Contact();
        if(!cursor.isNull(0+start)) {
            object_.set_id(cursor.getInt(0+start));
        }
        object_.seturi(cursor.getString(1+start));
        object_.setname(cursor.getString(2+start));
        return object_;
    }

    public static int getNbColumns() { 
        return 3;
    }

    public static ContentValues getContentValueFromObject(Serializable object) { 
        Contact object_ = (Contact) object;
        ContentValues values = new ContentValues();
        values.put(Contact.COLUMN_URI,object_.geturi());
        values.put(Contact.COLUMN_NAME,object_.getname());
        return values;
    }

}