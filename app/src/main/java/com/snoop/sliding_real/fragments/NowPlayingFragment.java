package com.snoop.sliding_real.fragments;


import android.support.v4.app.Fragment;

import static com.snoop.sliding_real.extras.MyApplication.API_KEY_THE_MOVIE_DATABASE;
import static com.snoop.sliding_real.extras.UrlEndpoints.URL_CHAR_QUESTION;
import static com.snoop.sliding_real.extras.UrlEndpoints.URL_PARAM_API_KEY;
import static com.snoop.sliding_real.extras.UrlEndpoints.URL_TMDB_NOWPLAYING;

/**
 * A simple {@link Fragment} subclass.
 */
public class NowPlayingFragment extends UpcomingFragment {


    public NowPlayingFragment() {
        // Required empty public constructor
    }

    @Override
    public String getRequestURL() {
        return URL_TMDB_NOWPLAYING +
                URL_CHAR_QUESTION +
                URL_PARAM_API_KEY + API_KEY_THE_MOVIE_DATABASE;
    }


}
