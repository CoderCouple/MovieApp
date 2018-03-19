package com.android.movieapp.module.movie.view;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.movieapp.Config;
import com.android.movieapp.R;
import com.android.movieapp.module.base.BaseActivity;
import com.android.movieapp.data.model.Movie;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Activity to show detail view
 */
public class DetailActivity extends BaseActivity {

    @BindView(R.id.back_drop_image)
    ImageView backDropImage;
    @BindView(R.id.movie_poster)
    ImageView moviePoster;
    @BindView(R.id.rating_text)
    TextView ratingText;
    @BindView(R.id.vote_count)
    TextView voteCount;
    @BindView(R.id.release_date)
    TextView releaseDate;
    @BindView(R.id.original_language)
    TextView originalLanguage;
    @BindView(R.id.overview)
    ExpandableTextView overview;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    android.support.v7.widget.Toolbar toolbar;

    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        movie = (Movie) getIntent().getExtras().getSerializable("movie");
        setSupportActionBar(toolbar);

        loadToolbar();
        loadDetails(movie);
    }

    /**
     * Loads toolbar
     */
    private void loadToolbar() {
        // Changes color of back arrow icon
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Load details in view from movie data.
     * @param movie
     */
    private void loadDetails(Movie movie) {
        //Set Images using picasso library.
        Picasso.get()
                .load(Config.BACKDROP_BASE_PATH + movie.getBackDropImage())
                .placeholder(R.drawable.backdrop_loading_placeholder)
                .error(R.drawable.backdrop_failed_placeholder).into(backDropImage);
        Picasso.get().
                load(Config.POSTER_BASE_PATH + movie.getPoster())
                .placeholder(R.drawable.poster_loading_placeholder)
                .error(R.drawable.poster_failed_placeholder)
                .into(moviePoster);

        title.setText(movie.getTitle());
        ratingText.setText(String.valueOf(movie.getPopularity()));
        overview.setText(movie.getOverview());
        originalLanguage.setText(movie.getLanguage());
        voteCount.setText(String.valueOf(movie.getVote_count()));
        releaseDate.setText(movie.getReleaseDate());
    }

}
