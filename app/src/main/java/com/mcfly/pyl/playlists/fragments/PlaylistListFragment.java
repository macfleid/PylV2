package com.mcfly.pyl.playlists.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mcfly.pyl.R;

/**
 * *************************************************
 * Kayentis© Pyl
 * *************************************************
 * Last change : [20/11/2015]
 * Author :  [mcfly]
 */
public class PlaylistListFragment extends Fragment {

    private final static String TAG = PlaylistListFragment.class.getName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playlist_list, container, false);

        return view;
    }

    /*****************************************************************************
     *  VIEWMODEL
     *****************************************************************************/
    public class viewModel {
        public String title;
        public String creationDate;
        public int rating;
        public String sharedWith;
    }
}
