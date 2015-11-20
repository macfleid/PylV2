package com.mcfly.pyl.sqlite.dal;
 
import java.io.Serializable;
import java.util.Date;
/*
* AUTO GENERATED FILE 
* creation date : 2015-11-20 13:58 
*/
public class Contact_has_Playlist implements Serializable { 

    public static String TABLE_NAME = "Contact_has_Playlist"; 
    public final static String COLUMN_CONTACT__ID="Contact__id"; 
    public final static String COLUMN_PLAYLIST__ID="Playlist__id"; 
    public final static String COLUMN_SHAREDATE="shareDate"; 

    private int Contact__id;
    private int Playlist__id;
    private Date shareDate;

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
    public Date getshareDate() { 
        return shareDate;
    }

    public void setshareDate(Date obj) {
        this.shareDate = obj;
    }
}
