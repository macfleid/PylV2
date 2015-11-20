package com.mcfly.pyl.sqlite.dao.extended;

import android.content.Context;
import com.mcfly.pyl.sqlite.dal.wrapper.Contact_has_PlaylistDalWrapper;

import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.mcfly.pyl.sqlite.contentprovider.Contact_has_PlaylistContentProvider;
import com.mcfly.pyl.sqlite.dao.BaseDAO;
import com.mcfly.pyl.sqlite.dal.Contact_has_Playlist;
import com.mcfly.pyl.sqlite.dao.extended.interfaces.IContact_has_Playlist;

public class Contact_has_PlaylistDAO extends BaseDAO implements IContact_has_Playlist {

    private final static String TAG = Contact_has_PlaylistDAO.class.getName();

    public Contact_has_PlaylistDAO(Context c) {
        super(c, Contact_has_PlaylistContentProvider.CONTENT_URI);
    }

    @Override
    public int save(Contact_has_Playlist element) {
       int result = add(Contact_has_PlaylistDalWrapper.getContentValueFromObject(element));
       return result;
    }
}
