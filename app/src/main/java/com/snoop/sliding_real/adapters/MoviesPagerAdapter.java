package com.snoop.sliding_real.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.snoop.sliding_real.fragments.NowPlayingFragment;
import com.snoop.sliding_real.fragments.PopularFragment;
import com.snoop.sliding_real.fragments.TopRatedFragment;
import com.snoop.sliding_real.fragments.UpcomingFragment;

import static com.snoop.sliding_real.extras.Constants.MOVIES_NOW_PLAYING;
import static com.snoop.sliding_real.extras.Constants.MOVIES_POPULAR;
import static com.snoop.sliding_real.extras.Constants.MOVIES_TOP_RATED;
import static com.snoop.sliding_real.extras.Constants.MOVIES_UPCOMING;
/**
 * Created by galaxywizkid on 6/23/16.
 */

public class MoviesPagerAdapter extends FragmentStatePagerAdapter {

    public MoviesPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case MOVIES_UPCOMING:
                return new UpcomingFragment();
            case MOVIES_NOW_PLAYING:
                return new NowPlayingFragment();
            case MOVIES_POPULAR:
                return new PopularFragment();
            case MOVIES_TOP_RATED:
                return new TopRatedFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "UPCOMING";
            case 1:
                return "NOW PLAYING";
            case 2:
                return "POPULAR";
            case 3:
                return "TOP RATED";
            default:
                return "None";
        }
    }
}