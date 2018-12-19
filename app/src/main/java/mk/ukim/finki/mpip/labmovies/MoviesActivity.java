package mk.ukim.finki.mpip.labmovies;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import mk.ukim.finki.mpip.labmovies.adapters.MovieAdapter;
import mk.ukim.finki.mpip.labmovies.models.Movie;
import mk.ukim.finki.mpip.labmovies.viewmodels.MovieViewModel;

public class MoviesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MovieAdapter movieAdapter;
    private MovieViewModel movieViewModel;
    private static final String TAG = "MoviesActivity";
    private Toolbar toolbar;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        initActionBar();
        initList();
        Log.i(TAG, "onCreate: after initList()");
        initViewModel();
        Log.i(TAG, "onCreate: after initViewModel()");
    }

    public void initActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbarMovieActivity);
        setSupportActionBar(toolbar);
    }

    public void initList() {

        recyclerView = findViewById(R.id.recyclerViewMovie);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        movieAdapter = new MovieAdapter(this);
        recyclerView.setAdapter(movieAdapter);
    }

    public void initViewModel() {
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                if(movies == null || movies.size() == 0) {
//                    movieViewModel.syncData("Matrix");
                }
                else {
                    movieAdapter.updateData(movies);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        searchView = (SearchView) menu.findItem(R.id.menuItemSearch).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                movieViewModel.syncData(s.trim());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
