package com.mcfly.pyl.playlists.menu.adapter;


import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.mcfly.pyl.playlists.fragments.PlaylistListFragment;

/**
 * *************************************************
 * Kayentis© Pyl
 * *************************************************
 * Last change : [20/11/2015]
 * Author :  [mcfly]
 */
public class PlaylistsActivityMenuAdapter extends FragmentStatePagerAdapter {

    public PlaylistsActivityMenuAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new PlaylistListFragment();
    }

    @Override
    public int getCount() {
        return 1;
    }
}
