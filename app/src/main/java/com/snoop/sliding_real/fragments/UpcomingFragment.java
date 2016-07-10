package com.snoop.sliding_real.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.snoop.sliding_real.MovieDetails;
import com.snoop.sliding_real.R;
import com.snoop.sliding_real.adapters.RecAdapter;
import com.snoop.sliding_real.network.VolleySingleton;
import com.snoop.sliding_real.pojo.MovieItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static com.snoop.sliding_real.extras.Constants.NA;
import static com.snoop.sliding_real.extras.Keys.EndpointUpcoming.KEY_ID;
import static com.snoop.sliding_real.extras.Keys.EndpointUpcoming.KEY_POSTER;
import static com.snoop.sliding_real.extras.Keys.EndpointUpcoming.KEY_RELEASE_DATES;
import static com.snoop.sliding_real.extras.Keys.EndpointUpcoming.KEY_RESULTS;
import static com.snoop.sliding_real.extras.Keys.EndpointUpcoming.KEY_TITLE;
import static com.snoop.sliding_real.extras.MyApplication.API_KEY_THE_MOVIE_DATABASE;
import static com.snoop.sliding_real.extras.UrlEndpoints.URL_CHAR_QUESTION;
import static com.snoop.sliding_real.extras.UrlEndpoints.URL_PARAM_API_KEY;
import static com.snoop.sliding_real.extras.UrlEndpoints.URL_POSTER_PREFIX;
import static com.snoop.sliding_real.extras.UrlEndpoints.URL_TMDB_UPCOMING;

public class UpcomingFragment extends Fragment implements RecAdapter.ItemClickCallback {

    private RecyclerView recyclerView;
    private RecAdapter recAdapter;
    private ArrayList<MovieItem> movieList;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy", Locale.US);

    private RequestQueue requestQueue;


    public UpcomingFragment() {
        // Required empty public constructor
    }

    public String getRequestURL() {
        return URL_TMDB_UPCOMING +
                URL_CHAR_QUESTION +
                URL_PARAM_API_KEY + API_KEY_THE_MOVIE_DATABASE;
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestQueue = VolleySingleton.getInstance().getmRequestQueue();
        sendJsonRequest();

    }


    public void sendJsonRequest() {

        JsonObjectRequest upcomingJsonObj = new JsonObjectRequest(Request.Method.GET, getRequestURL(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                movieList = parseJSONResponse(response);
                recAdapter.setMovieList(movieList);
                Log.d("Vivz", "onResponse was called ");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(upcomingJsonObj);
    }

    public ArrayList<MovieItem> parseJSONResponse(JSONObject response) {

        ArrayList<MovieItem> movieList = new ArrayList<>();

        if (response != null && response.length() != 0) {
            Log.d("Vivz", "parseJSONResponse was called ");

            int id = -1;
            String title = NA;
            String releaseDate = NA;
            String posterURL = NA;

            try {
                JSONArray arrayMovies = response.getJSONArray(KEY_RESULTS);

                for (int i = 0; i < arrayMovies.length(); i++) {
                    JSONObject currentMovie = arrayMovies.getJSONObject(i);

                    id = currentMovie.getInt(KEY_ID);

                    // Check to ensure all key value pairs are present before setting their appropriate values
                    if (currentMovie.has(KEY_TITLE) && !currentMovie.isNull(KEY_TITLE)) {
                        title = currentMovie.getString(KEY_TITLE);
                    }
                    if (currentMovie.has(KEY_RELEASE_DATES) && !currentMovie.isNull(KEY_RELEASE_DATES)) {
                        releaseDate = currentMovie.getString(KEY_RELEASE_DATES);
                    }

                    if (currentMovie.has(KEY_POSTER) && !currentMovie.isNull(KEY_POSTER)) {
                        posterURL = URL_POSTER_PREFIX + currentMovie.getString(KEY_POSTER);
                    }

                    MovieItem movieItem = new MovieItem();
                    movieItem.setId(id);
                    movieItem.setTitle(title);
                    movieItem.setPosterURL(posterURL);

                    Date date = null;
                    try {
                        date = dateFormat.parse(releaseDate);
                    } catch (ParseException e) {
                        //TODO: Handle this exception

                    }

                    movieItem.setDate(date);

                    if (id != -1 || !title.equals(NA)) {
                        movieList.add(movieItem);
                    }

                }

            } catch (JSONException e) {
                //TODO: Handle this exception maybe with a toast

            }
        }
        return movieList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movies, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rec_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recAdapter = new RecAdapter(getActivity());
        recyclerView.setAdapter(recAdapter);

        recAdapter.setItemClickCallback(this);
        return view;
    }


    @Override
    public void onItemClick(int p) {

        MovieItem movieItem = movieList.get(p);
        Bundle extras = new Bundle();
        extras.putInt("MovieID", movieItem.getId());
        Intent intent = new Intent(getActivity(), MovieDetails.class);
        intent.putExtra("EXTRAS", extras);
        startActivity(intent);

    }
}
