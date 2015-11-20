package com.mcfly.pyl.playlists;

import android.app.ActionBar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mcfly.pyl.R;
import com.mcfly.pyl.playlists.menu.adapter.PlaylistsActivityMenuAdapter;

/**
 *
 */
public class SharedPlaylistActivity extends AppCompatActivity {

    private PlaylistsActivityMenuAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_playlist);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    ////////////////////////////////////////////////////////////////////



    ////////////////////////////////////////////////////////////////////

    private void setupTabs(TabLayout tabLayout) {

    }


}