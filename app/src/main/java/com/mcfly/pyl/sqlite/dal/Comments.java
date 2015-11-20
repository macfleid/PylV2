package com.mcfly.pyl.sqlite.dal;
 
import java.io.Serializable;
import java.util.Date;
/*
* AUTO GENERATED FILE 
* creation date : 2015-11-20 13:58 
*/
public class Comments implements Serializable { 

    public static String TABLE_NAME = "Comments"; 
    public final static String COLUMN__ID="_id"; 
    public final static String COLUMN_TEXT="text"; 
    public final static String COLUMN_CONTACT__ID="Contact__id"; 
    public final static String COLUMN_PLAYLIST__ID="Playlist__id"; 
    public final static String COLUMN_SONG__ID="Song__id"; 

    private int _id;
    private String text;
    private int Contact__id;
    private int Playlist__id;
    private int Song__id;

    public int get_id() { 
        return _id;
    }

    public void set_id(int obj) {
        this._id = obj;
    }
    public String gettext() { 
        return text;
    }

    public void settext(String obj) {
        this.text = obj;
    }
    public int getContact__id() { 
        return Contact__id;
    }

    public void setContact__id(int obj) {
        this.Contact__id = obj;
    }
    public int getPlaylist__id() { 
        return Playlist__id;
    }

    public void setPlaylist__id(int obj) {
        this.Playlist__id = obj;
    }
    public int getSong__id() { 
        return Song__id;
    }

    public void setSong__id(int obj) {
        this.Song__id = obj;
    }
}
