package com.mcfly.pyl.playlists;

import android.app.ActionBar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.mcfly.pyl.R;
import com.mcfly.pyl.playlists.menu.adapter.PlaylistsActivityMenuAdapter;

/**
 *
 */
public class SharedPlaylistActivity extends AppCompatActivity {

    private final static String TAG = SharedPlaylistActivity.class.getName();

    private TabLayout tabLayout;
    private PlaylistsActivityMenuAdapter viewPagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_playlist);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        final String[] menuList = getResources().getStringArray(R.array.shared_playlist_menus);
        viewPagerAdapter = new PlaylistsActivityMenuAdapter(getFragmentManager(), menuList);
        viewPager.setAdapter(viewPagerAdapter);
        setupTabs(menuList);

        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    ////////////////////////////////////////////////////////////////////

    private void setupTabs(final String[] menuList) {
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
                int i = 0;
                for (String menuElement : menuList) {
                    TabLayout.Tab tab = tabLayout.getTabAt(i);
                    tab.setText(menuElement);
                    i++;
                }
            }
        });
    }
}
