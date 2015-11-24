package com.mcfly.pyl.business;

import android.content.Context;

import com.mcfly.pyl.sqlite.dal.AndroidContactModel;

/**
 * *************************************************
 * Kayentis© Pyl
 * *************************************************
 * Last change : [23/11/2015]
 * Author :  [mcfly]
 */
public interface ContactBusiness {

    /**
     * retrieve the me contact from the android contact list
     * @param context
     * @return
     */
    public AndroidContactModel getMeContact(Context context);

}
