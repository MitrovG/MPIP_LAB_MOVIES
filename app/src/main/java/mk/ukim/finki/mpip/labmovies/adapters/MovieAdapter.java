package mk.ukim.finki.mpip.labmovies.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mk.ukim.finki.mpip.labmovies.R;
import mk.ukim.finki.mpip.labmovies.adapters.holders.MovieHolder;

public class MovieAdapter extends RecyclerView.Adapter<MovieHolder> {


    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_view, viewGroup, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder movieHolder, int i) {
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
