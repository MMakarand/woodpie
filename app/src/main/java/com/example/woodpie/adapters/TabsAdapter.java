package com.example.woodpie.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.woodpie.fragments.FeedFragment;
import com.example.woodpie.fragments.LibraryFragment;
import com.example.woodpie.fragments.TradeFragment;

/**
 * Created by asmita on 17/8/15.
 */
public class TabsAdapter extends FragmentPagerAdapter
{

    private int TOTAL_TABS = 3;

    public TabsAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int index)
    {
        switch (index)
        {
            case 0:
                return new FeedFragment();
            case 1:
                return new LibraryFragment();
            case 2:
                return new TradeFragment();
        }
        return null;
    }

    @Override
    public int getCount()
    {
        return TOTAL_TABS;
    }
}
