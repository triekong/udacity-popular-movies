package com.snoop.sliding_real.fragments;


import static com.snoop.sliding_real.extras.MyApplication.API_KEY_THE_MOVIE_DATABASE;
import static com.snoop.sliding_real.extras.UrlEndpoints.URL_CHAR_QUESTION;
import static com.snoop.sliding_real.extras.UrlEndpoints.URL_PARAM_API_KEY;
import static com.snoop.sliding_real.extras.UrlEndpoints.URL_TMDB_POPULAR;

public class PopularFragment extends UpcomingFragment {


    public PopularFragment() {
        // Required empty public constructor
    }

    @Override
    public String getRequestURL() {
        return URL_TMDB_POPULAR +
                URL_CHAR_QUESTION +
                URL_PARAM_API_KEY + API_KEY_THE_MOVIE_DATABASE;
    }
}