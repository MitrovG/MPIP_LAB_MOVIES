package mk.ukim.finki.mpip.labmovies;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import mk.ukim.finki.mpip.labmovies.adapters.MovieAdapter;
import mk.ukim.finki.mpip.labmovies.constants.Constants;
import mk.ukim.finki.mpip.labmovies.models.Movie;
import mk.ukim.finki.mpip.labmovies.viewmodels.MovieViewModel;

public class MovieDetailsActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView tvPlot;
    private MovieViewModel movieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        initUI();
        handleIntent();
    }

    public void initUI() {
        imageView = findViewById(R.id.imgMovieDetail);
        tvPlot = findViewById(R.id.tvMoviePlotDetail);
    }

    public void handleIntent() {

        Intent intent = getIntent();
        if (intent != null) {
            String imdbId = intent.getStringExtra(Constants.EXTRA_IMDB_ID);
            initViewModel(imdbId);
        }

    }
    public void initViewModel(String imdbId) {
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.loadMovie(imdbId);
        movieViewModel.getMovieById(imdbId).observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(@Nullable Movie movie) {
                bindViews(movie);
            }
        });
    }

    public void bindViews(Movie movie) {
        tvPlot.setText(movie.getPlot());
        RequestOptions options = new RequestOptions();
        options.transform(new RoundedCorners(50));
        GlideApp.with(this)
                .load(movie.getPhotoUrl())
                .apply(options)
                .placeholder(getPlaceholder())
                .error(R.drawable.ic_error_black_24dp)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }

    private CircularProgressDrawable getPlaceholder() {
        CircularProgressDrawable cpd = new CircularProgressDrawable(this);
        cpd.setCenterRadius(30f);
        cpd.setStrokeWidth(5f);
        cpd.start();
        return cpd;
    }
}
