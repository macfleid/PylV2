package com.mcfly.pyl.sqlite.dal;

/**
 * *************************************************
 * Kayentis© Pyl
 * *************************************************
 * Last change : [23/11/2015]
 * Author :  [mcfly]
 */
public class AndroidContactModel {

    private int id;
    private String name;
    private String lookupKey;

    public AndroidContactModel() {

    }

    public AndroidContactModel(int id, String name, String lookupKey) {
        this.id = id;
        this.lookupKey = lookupKey;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLookupKey() {
        return lookupKey;
    }

    public void setLookupKey(String lookupKey) {
        this.lookupKey = lookupKey;
    }

    @Override
    public String toString() {
        return "AndroidContactModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lookupKey='" + lookupKey + '\'' +
                '}';
    }
}
