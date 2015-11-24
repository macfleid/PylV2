package com.mcfly.pyl.business;

import android.content.Context;

import com.mcfly.pyl.business.exceptions.ContactBusinessException;
import com.mcfly.pyl.sqlite.dal.AndroidContactModel;
import com.mcfly.pyl.sqlite.dal.Contact;

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
    public AndroidContactModel getAndroidMeContact(Context context) throws ContactBusinessException;


    /**
     * get the contact from the systeme db
     * if found :
     *  - search in app db
     *  -> if found return;
     *  -> else create in app db and returns it
     * @param context
     * @return
     */
    public Contact getMeContact(Context context)throws ContactBusinessException;

}
