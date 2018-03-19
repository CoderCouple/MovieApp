package com.android.movieapp.module.movie.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.movieapp.R;
import com.android.movieapp.injection.Injector;
import com.android.movieapp.module.base.BaseFragment;
import com.android.movieapp.module.movie.model.Movie;
import com.android.movieapp.module.movie.model.MovieResponse;
import com.android.movieapp.module.movie.presenter.MoviePresenter;
import com.android.movieapp.module.movie.presenter.MovieViewInteractor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by aaditya on 3/15/18.
 */

public class UpComingFragment extends BaseFragment implements MovieViewInteractor, MovieAdapter.ItemClickListener {


    @Inject
    MoviePresenter moviePresenter;

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

        if (savedInstanceState != null) {
            //probably orientation change
            movieList = (List<Movie>) savedInstanceState.getSerializable("upComingList");
        }

        if (movieList == null) {
            movieList = new ArrayList<>();
            moviePresenter.getUpComingMovies(1);
        }

        movieAdapter = new MovieAdapter(getContext(), movieList, this);
        recyclerView.setAdapter(movieAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!(recyclerView.canScrollVertically(1))) {
                    if (movieResponse.getPage() < movieResponse.getTotal_pages())
                        moviePresenter.getPopularMovies(movieResponse.getPage() + 1);//TODO :change it
                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("upComingList", (Serializable) movieList);
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
        this.movieResponse = response;
        Set<Movie> movieSet = new HashSet<>();
        movieSet.addAll(this.movieList);
        movieSet.addAll(response.getMovies());
        this.movieList.clear();
        this.movieList.addAll(movieSet);
        movieAdapter.notifyDataSetChanged();

    }

    @Override
    public void onMovieClicked(int position) {
        Bundle movie = new Bundle();
        movie.putSerializable("movie", movieList.get(position));
        startActivity(DetailActivity.class,movie);
    }
}
