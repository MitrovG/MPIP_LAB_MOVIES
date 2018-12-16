package mk.ukim.finki.mpip.labmovies.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import mk.ukim.finki.mpip.labmovies.R;
import mk.ukim.finki.mpip.labmovies.adapters.holders.MovieHolder;
import mk.ukim.finki.mpip.labmovies.models.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieHolder> {

    private List<Movie> movieList;

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_view, viewGroup, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder movieHolder, int i) {
        Movie movie = movieList.get(i);
        movieHolder.bind(movie);
    }

    @Override
    public int getItemCount() {
        if (movieList != null ) {
            return movieList.size();
        }
        return 0;
    }

    public void updateData(List<Movie> movies) {
        movieList = movies;
        notifyDataSetChanged();
    }
}
