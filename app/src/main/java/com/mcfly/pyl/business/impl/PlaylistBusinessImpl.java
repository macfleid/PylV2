package com.mcfly.pyl.business.impl;

import android.content.Context;
import android.util.Log;

import com.mcfly.pyl.business.ContactBusiness;
import com.mcfly.pyl.business.PlaylistBusiness;
import com.mcfly.pyl.business.exceptions.ContactBusinessException;
import com.mcfly.pyl.business.exceptions.PlaylistSavingException;
import com.mcfly.pyl.business.exceptions.SharePlaylistException;
import com.mcfly.pyl.sqlite.dal.AndroidContactModel;
import com.mcfly.pyl.sqlite.dal.Contact;
import com.mcfly.pyl.sqlite.dal.Playlist;
import com.mcfly.pyl.sqlite.dal.Song;
import com.mcfly.pyl.sqlite.dao.extended.ContactDAO;
import com.mcfly.pyl.sqlite.dao.extended.PlaylistDAO;
import com.mcfly.pyl.sqlite.utils.DateUtils;

/**
 * *************************************************
 * Mcfly© Pyl
 * *************************************************
 * Last change : [23/11/2015]
 * Author :  [mcfly]
 */
public class PlaylistBusinessImpl implements PlaylistBusiness {

    private final static String TAG = PlaylistBusiness.class.getName();

    @Override
    public void saveNewPlaylist(Context context, String title, Contact from) throws PlaylistSavingException, ContactBusinessException {
        PlaylistDAO playlistDAO = new PlaylistDAO(context);
        ContactDAO contactDAO = new ContactDAO(context);
        if(from == null) {
            //contact is me then
            ContactBusiness contactBusiness = new ContactBusinessImpl();
            from = contactBusiness.getMeContact(context);
            Log.d(TAG, "Me ->"+from);

        }
        Playlist playlist = new Playlist(title, 0, DateUtils.getUTCDate(), (from!=null ? from.get_id() : 0));
        playlistDAO.save(playlist);
    }

    @Override
    public void shareMyPLaylist(Context context, Playlist playlist, Contact[] contacts) throws SharePlaylistException {

    }

    @Override
    public void saveSong(Context context, Playlist playlist, String link, String artiste, String title) throws PlaylistSavingException {

    }

    @Override
    public void removeSong(Context context, Playlist playlist, Song song) throws PlaylistSavingException {

    }
}
