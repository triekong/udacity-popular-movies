package com.snoop.sliding_real;

import android.support.v4.view.PagerAdapter;

import com.snoop.sliding_real.adapters.DetailMoviesPagerAdapter;
import com.snoop.sliding_real.ui.NavDrawer;

public class MovieDetails extends NavDrawer {


    @Override
    public PagerAdapter getPagerAdapter() {

        return new DetailMoviesPagerAdapter(getSupportFragmentManager());
    }

   /* Bundle extras = getIntent().getBundleExtra("EXTRAS");
    int num = extras.getInt("MovieID");
    String val = Integer.toString(num);
    ((TextView) findViewById(R.id.tv_detail)).setText(val);*/




}
