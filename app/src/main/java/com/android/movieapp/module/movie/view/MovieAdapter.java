package com.android.movieapp.module.movie.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.movieapp.Config;
import com.android.movieapp.R;
import com.android.movieapp.module.movie.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by aaditya on 3/15/18.
 */

class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Context context;
    private List<Movie> movieList;
    private ItemClickListener itemClickListener;

    public MovieAdapter(Context context, List<Movie> movieList, ItemClickListener itemClickListener) {
        this.movieList = movieList;
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindViews(movieList.get(position));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.poster_image)
        ImageView posterImage;
        @BindView(R.id.movie_genres)
        TextView movieGenres;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bindViews(Movie movie) {
            title.setText(movie.getTitle().trim());
            Picasso.get()
                    .load(Config.POSTER_BASE_PATH + movie.getPoster())
                    .placeholder(R.drawable.poster_loading_placeholder)
                    .fit()
                    .centerCrop()
                    .error(R.drawable.poster_failed_placeholder)
                    .into(posterImage);
            setGenres(movie.getGenres());
            movieGenres.setText(movie.getGenres().toString().replaceAll("[\\[.\\].\\s+]", ""));
        }

        private void setGenres(List<String> genres) {

        }

        @OnClick(R.id.container)
        public void onItemClick(View view) {
            itemClickListener.onMovieClicked(getAdapterPosition());
        }
    }

    public interface ItemClickListener {

        void onMovieClicked(int position);

    }
}
