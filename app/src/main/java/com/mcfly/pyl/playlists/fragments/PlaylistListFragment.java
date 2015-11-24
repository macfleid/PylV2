package com.mcfly.pyl.playlists.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.mcfly.pyl.R;
import com.mcfly.pyl.playlists.CreatePlaylistActivity;

import org.w3c.dom.Text;

import java.util.List;

/**
 * *************************************************
 * Kayentis© Pyl
 * *************************************************
 * Last change : [20/11/2015]
 * Author :  [mcfly]
 */
public class PlaylistListFragment extends Fragment {

    private final static String TAG = PlaylistListFragment.class.getName();

    public final static String ADD_CREATE_ACTION="ADD_CREATE_ACTION";

    private List<ViewModel> itemList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playlist_list, container, false);
        FloatingActionButton addButton = (FloatingActionButton) view.findViewById(R.id.addButton);
        TextView textView = (TextView) view.findViewById(R.id.no_playlist_label);
        GridView gridView = (GridView) view.findViewById(R.id.gridView);

        if(getArguments()!=null && getArguments().getBoolean(ADD_CREATE_ACTION, false)){
            addButton.setVisibility(View.VISIBLE);
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createPlaylistAction();
                }
            });
        }

        if(itemList==null || itemList.size()==0) {
            textView.setVisibility(View.VISIBLE);
            gridView.setVisibility(View.GONE);
        } else {
            textView.setVisibility(View.GONE);
        }
        return view;
    }

    private void createPlaylistAction() {
        Intent intent = new Intent(getActivity(), CreatePlaylistActivity.class);
        startActivity(intent);
    }

    /*****************************************************************************
     *  VIEWMODEL
     *****************************************************************************/
    public class ViewModel {
        public String title;
        public String creationDate;
        public int rating;
        public String sharedWith;
    }
}
