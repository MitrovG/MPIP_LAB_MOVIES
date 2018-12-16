package mk.ukim.finki.mpip.labmovies;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        initList();
        Log.i(TAG, "onCreate: after initList()");
        initViewModel();
        Log.i(TAG, "onCreate: after initViewModel()");
    }

    public void initList() {

        recyclerView = findViewById(R.id.recyclerViewMovie);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        movieAdapter = new MovieAdapter();
        recyclerView.setAdapter(movieAdapter);
    }

    public void initViewModel() {
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                if(movies == null || movies.size() == 0) {
                    movieViewModel.syncData("Matrix");
                }
                movieAdapter.updateData(movies);
            }
        });
    }

    public void callSyncing(View view) {
        Random random = new Random();
        int randNum = random.nextInt(3);
        String[] array = new String[3];
        array[0] = "Terminator";
        array[1] = "Matrix";
        array[2] = "Pocahontas";
        movieViewModel.syncData(array[0]);
    }
}
