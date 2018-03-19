package com.android.movieapp.module.movie.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.movieapp.R;
import com.android.movieapp.injection.Injector;
import com.android.movieapp.module.base.BaseFragment;
import com.android.movieapp.module.common.util.Bakery;
import com.android.movieapp.module.common.util.ConnectivityUtil;
import com.android.movieapp.data.model.Movie;
import com.android.movieapp.module.movie.model.MovieResponse;
import com.android.movieapp.module.movie.presenter.MoviePresenter;
import com.android.movieapp.module.movie.presenter.MovieViewInteractor;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_SETTLING;
import static android.widget.AbsListView.OnScrollListener.SCROLL_STATE_IDLE;

/**
 * Created by aaditya on 3/15/18.
 */

/**
 * Popular fragment to load data into
 */
public class PopularFragment extends BaseFragment implements MovieViewInteractor, MovieAdapter.ItemClickListener {

    @Inject
    MoviePresenter moviePresenter;
    @Inject
    Gson gson;
    @Inject
    ConnectivityUtil connectivityUtil;
    @Inject
    Bakery bakery;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.progress)
    ProgressBar progressBar;

    private MovieAdapter movieAdapter;
    private List<Movie> movieList;
    private MovieResponse movieResponse;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_view_layout, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView.setHasFixedSize(true);
        Injector.component().inject(this);
        moviePresenter.attachViewInteractor(this);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        // Load movie data from server.
        if (movieList == null) {
            movieList = new ArrayList<>();
            // Check for internet connection before making api call.
            if (connectivityUtil.isConnected())
                moviePresenter.getPopularMovies(1);
            else {
                bakery.snackLong(getView(),"Check Internet Connection");
            }
        }

        movieAdapter = new MovieAdapter(getContext(), movieList, this);
        recyclerView.setAdapter(movieAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                // Show or hide tab layout on scroll.
                switch (newState) {
                    case SCROLL_STATE_IDLE:
                        ((MovieActivity) getActivity()).toggleTabView(View.VISIBLE);
                        break;
                    case SCROLL_STATE_DRAGGING:
                    case SCROLL_STATE_SETTLING:
                        ((MovieActivity) getActivity()).toggleTabView(View.GONE);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // Pagination implementation on scrolling to end of list
                if (!(recyclerView.canScrollVertically(1))) {
                    if (movieResponse.getPage() < movieResponse.getTotal_pages()) {
                        if (!connectivityUtil.isConnected()) {
                            bakery.snackLong(getView(), "Check Internet Connection");
                            return;
                        }

                        moviePresenter.getPopularMovies(movieResponse.getPage() + 1);
                    }
                }
            }
        });
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onResult(MovieResponse response) {
        // Clears list add updated data
        this.movieResponse = response;
        Set<Movie> movieSet = new HashSet<>();
        movieSet.addAll(this.movieList);
        movieSet.addAll(response.getMovies());
        this.movieList.clear();
        this.movieList.addAll(movieSet);
        movieAdapter.notifyDataSetChanged();

    }

    // Loads detail activity with movie data
    @Override
    public void onMovieClicked(int position) {
        Bundle movie = new Bundle();
        movie.putSerializable("movie", movieList.get(position));
        startActivity(DetailActivity.class, movie);
    }
}
