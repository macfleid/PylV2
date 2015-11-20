package com.mcfly.pyl.sqlite.dao.extended;

import android.content.Context;
import com.mcfly.pyl.sqlite.dal.wrapper.CommentsDalWrapper;

import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.mcfly.pyl.sqlite.contentprovider.CommentsContentProvider;
import com.mcfly.pyl.sqlite.dao.BaseDAO;
import com.mcfly.pyl.sqlite.dal.Comments;
import com.mcfly.pyl.sqlite.dao.extended.interfaces.IComments;

public class CommentsDAO extends BaseDAO implements IComments {

    private final static String TAG = CommentsDAO.class.getName();

    public CommentsDAO(Context c) {
        super(c, CommentsContentProvider.CONTENT_URI);
    }

    @Override
    public int save(Comments element) {
       int result = add(CommentsDalWrapper.getContentValueFromObject(element));
       return result;
    }
}
