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
    private int androidContactId;

    public AndroidContactModel() {

    }

    public AndroidContactModel(int id, String name, int androidContactId) {
        this.id = id;
        this.androidContactId = androidContactId;
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

    public int getAndroidContactId() {
        return androidContactId;
    }

    public void setAndroidContactId(int androidContactId) {
        this.androidContactId = androidContactId;
    }

    @Override
    public String toString() {
        return "AndroidContactModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", androidContactId=" + androidContactId +
                '}';
    }
}
