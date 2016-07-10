package com.snoop.sliding_real.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.snoop.sliding_real.fragments.FragmentA;
import com.snoop.sliding_real.fragments.OverviewFragment;

import static com.snoop.sliding_real.extras.Constants.MAX_NUM_PAGES;
import static com.snoop.sliding_real.extras.Constants.MOVIES_CAST;
import static com.snoop.sliding_real.extras.Constants.MOVIES_INFO;
import static com.snoop.sliding_real.extras.Constants.MOVIES_OVERVIEW;

/**
 * Created by galaxywizkid on 7/8/16.
 */
public class DetailMoviesPagerAdapter extends FragmentStatePagerAdapter{

    public DetailMoviesPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case MOVIES_INFO:
                return new FragmentA();
            case MOVIES_CAST:
                return new FragmentA();
            case MOVIES_OVERVIEW:
                return new OverviewFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return MAX_NUM_PAGES;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "INFO";
            case 1:
                return "CAST";
            case 2:
                return "OVERVIEW";
            default:
                return "None";
        }
    }
}












