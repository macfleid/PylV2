package com.mcfly.pyl.playlists;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.mcfly.pyl.R;
import com.mcfly.pyl.business.PlaylistBusiness;
import com.mcfly.pyl.business.exceptions.PlaylistSavingException;
import com.mcfly.pyl.business.impl.PlaylistBusinessImpl;

/**
 * *************************************************
 * Kayentis© Pyl
 * *************************************************
 * Last change : [23/11/2015]
 * Author :  [mcfly]
 */
public class CreatePlaylistActivity extends AppCompatActivity {

    private final static String TAG = CreatePlaylistActivity.class.getName();

    private PlaylistBusiness playlistBusiness;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_playlist);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setOnMenuItemClickActions(toolbar);

        playlistBusiness = new PlaylistBusinessImpl();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.create_playlist, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void setOnMenuItemClickActions(Toolbar toolbar) {
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_save) {
                    savePlaylistAction();
                }
                return false;
            }
        });
    }

    private void savePlaylistAction() {
        try {
            playlistBusiness.saveNewPlaylist(getApplicationContext(),"Title",null);
        } catch (PlaylistSavingException e) {
            Log.e(TAG, "Error while saving new playlist", e);
        }
    }
}
