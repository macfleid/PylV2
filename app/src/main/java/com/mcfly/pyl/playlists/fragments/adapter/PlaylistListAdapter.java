package com.mcfly.pyl.playlists.fragments.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.mcfly.pyl.R;
import com.mcfly.pyl.sqlite.dal.Contact;
import com.mcfly.pyl.sqlite.dal.Playlist;
import com.mcfly.pyl.sqlite.utils.DateUtils;
import com.mcfly.pyl.sqlite.views.wrapper.Playlist_listVIEWDalWrapper;

/**
 * *************************************************
 * Mcfly© Pyl
 * *************************************************
 * Last change : [23/11/2015]
 * Author :  [mcfly]
 */
public class PlaylistListAdapter extends CursorAdapter {

    private LayoutInflater inflater;

    public PlaylistListAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public PlaylistListAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return inflater.inflate(R.layout.fragment_playlist_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        TextView titleTextView = (TextView) view.findViewById(R.id.titleTextview);
        TextView secondLineTextView = (TextView) view.findViewById(R.id.sharedTextView);
        TextView nbSongsTextView = (TextView) view.findViewById(R.id.nbSongsTextView);

        Playlist playlist = Playlist_listVIEWDalWrapper.getPlaylist(cursor);
        Contact contact = Playlist_listVIEWDalWrapper.getContact(cursor);
        int nbSongs = Playlist_listVIEWDalWrapper.getNbSong(cursor);

        titleTextView.setText(playlist.gettitle());
        String secondLinePattern = context.getResources().getString(R.string.playlist_list_item_secondline_pattern);
        secondLineTextView.setText(String.format(secondLinePattern, contact.getname(), DateUtils.getDateAsLocaleString(context, playlist.getdate())));
        nbSongsTextView.setText(nbSongs);
    }


}
