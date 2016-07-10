package com.snoop.sliding_real.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.snoop.sliding_real.R;
import com.snoop.sliding_real.extras.Constants;
import com.snoop.sliding_real.network.VolleySingleton;
import com.snoop.sliding_real.pojo.MovieItem;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;


public class RecAdapter extends RecyclerView.Adapter<RecAdapter.RecHolder> {

    private LayoutInflater inflater;
    private ArrayList<MovieItem> listMoviesItem = new ArrayList<>();
    private DateFormat dateFormat = new SimpleDateFormat("yyyy", Locale.US);
    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;

    private ItemClickCallback itemClickCallback;
    //The interface will allow us to communicate with the Activity, without the DerpAdapter class
    //Having to hold the Activity in memory

    public interface ItemClickCallback {
        void onItemClick(int p); // Called whenever user clicks any item in the list
    }

    // This method is used to set an instance of the interface
    public void setItemClickCallback(final ItemClickCallback itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
    }


    public RecAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        volleySingleton = volleySingleton.getInstance();
        imageLoader = volleySingleton.getImageLoader();
    }

    @Override
    public RecHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_row, parent, false);
        return new RecHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecHolder holder, int position) {
        MovieItem currentMovie = listMoviesItem.get(position);

        // Set values to be displayed at specified positions with data from ArrayList of movies
        holder.movieTitle.setText(currentMovie.getTitle());

        if (currentMovie.getDate() != null) {
            String formattedDate = "(" + dateFormat.format(currentMovie.getDate()) + ")";
            holder.releaseDate.setText(formattedDate);
        } else {
            holder.releaseDate.setText(Constants.NA);
        }

        String urlThumbnail = currentMovie.getPosterURL();
        if (!urlThumbnail.equals(Constants.NA)) {
            loadImages(urlThumbnail, holder);
        }

    }

    // Load Images based on the url gotten from ArrayList of movie Objects
    public void loadImages(String urlThumbnail, final RecHolder holder) {
        imageLoader.get(urlThumbnail, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                holder.posterIcon.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    public void setMovieList(ArrayList<MovieItem> listMoviesItem) {
        this.listMoviesItem = listMoviesItem;
        notifyItemRangeChanged(0, listMoviesItem.size());
    }

    @Override
    public int getItemCount() {

        return listMoviesItem.size();
    }


    class RecHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView movieTitle;
        private TextView releaseDate;
        private ImageView posterIcon;
        private View container;


        public RecHolder(View itemView) {
            super(itemView);

            movieTitle = (TextView) itemView.findViewById(R.id.tv_movie_title);
            releaseDate = (TextView) itemView.findViewById(R.id.tv_release_date);
            posterIcon = (ImageView) itemView.findViewById(R.id.im_movie_poster_icon);
            container = itemView.findViewById(R.id.cont_custom_row);
            container.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.cont_custom_row){
                itemClickCallback.onItemClick(getAdapterPosition());
            }
        }
    }

}
