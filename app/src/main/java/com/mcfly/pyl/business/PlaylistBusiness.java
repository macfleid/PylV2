package com.mcfly.pyl.business;

import com.mcfly.pyl.business.exceptions.PlaylistSavingException;
import com.mcfly.pyl.business.exceptions.SharePlaylistException;
import com.mcfly.pyl.sqlite.dal.Contact;
import com.mcfly.pyl.sqlite.dal.Playlist;
import com.mcfly.pyl.sqlite.dal.Song;

/**
 * *************************************************
 * Mcfly© Pyl
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

    /**
     * you cannot share a playlist you do not own (Contact_id is null when you own it)
     * @param playlist
     * @param contacts
     * @throws SharePlaylistException
     */
    public void shareMyPLaylist(Playlist playlist, Contact[] contacts) throws SharePlaylistException;

    /**
     * throws on :
     *  - empty playlist
     *  - no link
     *  - you do not own playlist
     * @param playlist
     * @param link
     * @param artiste
     * @param title
     * @throws PlaylistSavingException
     */
    public void saveSong(Playlist playlist, String link, String artiste, String title) throws PlaylistSavingException;

    /**
     * - remove a song from a playlist you do not own throws error
     * - remove a song that is not linked to the playlist throws error
     * @param playlist
     * @param song
     * @throws PlaylistSavingException
     */
    public void removeSong(Playlist playlist, Song song) throws PlaylistSavingException;
}
