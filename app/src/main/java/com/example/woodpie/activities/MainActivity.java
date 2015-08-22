package com.example.woodpie.activities;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.woodpie.R;
import com.example.woodpie.adapters.TabsAdapter;

public class MainActivity extends ActionBarActivity implements android.support.v7.app.ActionBar.TabListener {

    private ViewPager tabsViewPager;
    private ActionBar mActionBar;
    private TabsAdapter mTabsAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabsViewPager = (ViewPager) findViewById(R.id.tabsPager);
        mTabsAdapter = new TabsAdapter(getSupportFragmentManager());
        tabsViewPager.setAdapter(mTabsAdapter);

        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        Tab feedTab = getSupportActionBar().newTab().setText("Feed").setTabListener(this).setCustomView(R.layout.feed_view);
        Tab libraryTab = getSupportActionBar().newTab().setText("Library").setTabListener(this).setCustomView(R.layout.library_view);
        Tab tradeTab = getSupportActionBar().newTab().setText("Trade").setTabListener(this).setCustomView(R.layout.trade_view);

        getSupportActionBar().addTab(feedTab);
        getSupportActionBar().addTab(libraryTab);
        getSupportActionBar().addTab(tradeTab);

        tabsViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int i, float v, int i1)
            {

            }

            @Override
            public void onPageSelected(int position)
            {
                getSupportActionBar().setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int i)
            {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(Tab selectedTab, FragmentTransaction fragmentTransaction)
    {
        tabsViewPager.setCurrentItem(selectedTab.getPosition());
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction fragmentTransaction)
    {

    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction fragmentTransaction)
    {

    }
}
