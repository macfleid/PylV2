package com.mcfly.pyl.business;

import com.mcfly.pyl.business.exceptions.PlaylistSavingException;
import com.mcfly.pyl.sqlite.dal.Contact;

/**
 * *************************************************
 * Kayentis© Pyl
 * *************************************************
 * Last change : [20/11/2015]
 * Author :  [mcfly]
 */
public interface PlaylistBusiness {

    /**
     * save a new playlist
     * @param title
     * @param from if mine from is null
     * @throws PlaylistSavingException
     */
    public void saveNewPlaylist(String title, Contact from) throws PlaylistSavingException;
}
