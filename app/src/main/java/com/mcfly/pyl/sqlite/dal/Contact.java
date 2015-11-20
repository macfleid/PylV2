package com.mcfly.pyl.sqlite.dal;
 
import java.io.Serializable;
import java.util.Date;
/*
* AUTO GENERATED FILE 
* creation date : 2015-11-20 13:58 
*/
public class Contact implements Serializable { 

    public static String TABLE_NAME = "Contact"; 
    public final static String COLUMN__ID="_id"; 
    public final static String COLUMN_URI="uri"; 
    public final static String COLUMN_NAME="name"; 

    private int _id;
    private String uri;
    private String name;

    public int get_id() { 
        return _id;
    }

    public void set_id(int obj) {
        this._id = obj;
    }
    public String geturi() { 
        return uri;
    }

    public void seturi(String obj) {
        this.uri = obj;
    }
    public String getname() { 
        return name;
    }

    public void setname(String obj) {
        this.name = obj;
    }
}
