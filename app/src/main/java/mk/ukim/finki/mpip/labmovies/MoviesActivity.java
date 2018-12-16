package mk.ukim.finki.mpip.labmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import mk.ukim.finki.mpip.labmovies.adapters.MovieAdapter;

public class MoviesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        initList();
    }

    public void initList() {

        recyclerView = findViewById(R.id.recyclerViewMovie);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        movieAdapter = new MovieAdapter();
        recyclerView.setAdapter(movieAdapter);

    }
}
