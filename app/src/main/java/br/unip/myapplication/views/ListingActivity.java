package br.unip.myapplication.views;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import br.unip.myapplication.R;
import br.unip.myapplication.models.Location;
import br.unip.myapplication.utils.AppUtil;
import br.unip.myapplication.views.adapters.ViewPagerAdapter;
import br.unip.myapplication.views.fragments.BusFragment;
import br.unip.myapplication.views.fragments.SubwayFragment;

public class ListingActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ViewPagerAdapter mAdapter;
    private FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);

        bindElements();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initViewPagerAndTabs();
    }

    private void initViewPagerAndTabs() {
        mViewPager = AppUtil.get(findViewById(R.id.viewpager));
        setupViewPager(mViewPager);

        mTabLayout = AppUtil.get(findViewById(R.id.tabs));
        mTabLayout.setupWithViewPager(mViewPager);
        setupTabIcons();
    }

    private void setupTabIcons() {
        final int[] tabIcons = {
                R.drawable.ic_tab_bus,
                R.drawable.ic_tab_subway
        };

        mTabLayout.getTabAt(0).setIcon(tabIcons[0]);
        mTabLayout.getTabAt(1).setIcon(tabIcons[1]);
    }

    private void setupViewPager(ViewPager viewPager) {
        Location location = new Location();

        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mAdapter.addFragment(new BusFragment(), getString(R.string.str_bus));
        mAdapter.addFragment(new SubwayFragment(), getString(R.string.str_subway));
        viewPager.setAdapter(mAdapter);
    }

    private void bindElements() {
        mToolbar = AppUtil.get(findViewById(R.id.toolbar));
        setSupportActionBar(mToolbar);

        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ListingActivity.this, R.string.msg_example, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
