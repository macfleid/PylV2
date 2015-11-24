package com.mcfly.pyl.playlists.menu.adapter;


import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.mcfly.pyl.R;
import com.mcfly.pyl.playlists.fragments.PlaylistListFragment;

/**
 * *************************************************
 * Kayentis© Pyl
 * *************************************************
 * Last change : [20/11/2015]
 * Author :  [mcfly]
 */
public class PlaylistsActivityMenuAdapter extends FragmentStatePagerAdapter {

    private final static String TAG = PlaylistsActivityMenuAdapter.class.getName();

    private String[] menuList;

    public PlaylistsActivityMenuAdapter(FragmentManager fm, String[] menuList) {
        super(fm);
        this.menuList = menuList;
    }

    @Override
    public Fragment getItem(int position) {
        PlaylistListFragment fragment = new PlaylistListFragment();
        Bundle bundle = new Bundle();
        switch (position){
            case 0:
                break;
            case 1:
                bundle.putBoolean(PlaylistListFragment.ADD_CREATE_ACTION,true);
                break;
            case 2:
                break;
        }
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return menuList.length;
    }
}
