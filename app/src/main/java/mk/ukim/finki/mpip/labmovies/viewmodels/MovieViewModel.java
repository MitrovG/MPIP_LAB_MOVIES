package mk.ukim.finki.mpip.labmovies.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import mk.ukim.finki.mpip.labmovies.models.Movie;
import mk.ukim.finki.mpip.labmovies.repository.MovieRepository;

public class MovieViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;
    private LiveData<List<Movie>> movies;
    private static final String TAG = "MovieViewModel";

    public MovieViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
        movies = movieRepository.getMovies();
    }

    public void loadMovie(String id) {
        movieRepository.loadMovie(id);
    }

    public void insertAll(List<Movie> movies) {
        movieRepository.insertAll(movies);
    }

    public void insert(Movie movie) {
        movieRepository.update(movie);
    }

    public void deleteAll() {
        movieRepository.deleteAll();
    }

    public void syncData(String title) {
        Log.i(TAG, "syncData: inside syncData");
        movieRepository.loadMoviesFromApi(title);
    }

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }

    public LiveData<Movie> getMovieById(String id) {
        return movieRepository.getMovieById(id);
    }
}
