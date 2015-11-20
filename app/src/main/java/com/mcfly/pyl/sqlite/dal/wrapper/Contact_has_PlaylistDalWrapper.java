package com.mcfly.pyl.sqlite.dal.wrapper;

import android.content.ContentValues;
import java.io.Serializable;
import com.mcfly.pyl.sqlite.utils.DateGetter;
import com.mcfly.pyl.sqlite.dal.Contact_has_Playlist;
import com.mcfly.pyl.sqlite.cursor.Contact_has_PlaylistCursor;
import java.util.Date;

/*
* AUTO GENERATED FILE 
* creation date : 2015-11-20 13:58 
*/
public class Contact_has_PlaylistDalWrapper {

    public static Contact_has_Playlist getObjectFromDB(Contact_has_PlaylistCursor cursor, int start) { 
        Contact_has_Playlist object_ = new Contact_has_Playlist();
        object_.setContact__id(cursor.getInt(0+start));
        object_.setPlaylist__id(cursor.getInt(1+start));
        Date date = DateGetter.getInstance().getDateFromString(cursor.getString(2+start));
        object_.setshareDate(date);
        return object_;
    }

    public static int getNbColumns() { 
        return 3;
    }

    public static ContentValues getContentValueFromObject(Serializable object) { 
        Contact_has_Playlist object_ = (Contact_has_Playlist) object;
        ContentValues values = new ContentValues();
        values.put(Contact_has_Playlist.COLUMN_CONTACT__ID,object_.getContact__id());
        values.put(Contact_has_Playlist.COLUMN_PLAYLIST__ID,object_.getPlaylist__id());
        String dateString = DateGetter.getInstance().getStringFromDate(object_.getshareDate());
        values.put(Contact_has_Playlist.COLUMN_SHAREDATE,dateString);
        return values;
    }

}