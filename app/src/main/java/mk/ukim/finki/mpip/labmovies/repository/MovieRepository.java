package mk.ukim.finki.mpip.labmovies.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import mk.ukim.finki.mpip.labmovies.asynctasks.DeleteAllMoviesAsyncTask;
import mk.ukim.finki.mpip.labmovies.asynctasks.InsertMovieAsyncTask;
import mk.ukim.finki.mpip.labmovies.asynctasks.UpdateMovieAsyncTask;
import mk.ukim.finki.mpip.labmovies.clients.OmdbApiClient;
import mk.ukim.finki.mpip.labmovies.constants.Constants;
import mk.ukim.finki.mpip.labmovies.models.Movie;
import mk.ukim.finki.mpip.labmovies.models.MovieList;
import mk.ukim.finki.mpip.labmovies.persistence.MovieDatabase;
import mk.ukim.finki.mpip.labmovies.persistence.dao.MovieDao;
import mk.ukim.finki.mpip.labmovies.retrofit.OmdbApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private LiveData<List<Movie>> movies;
    private OmdbApiInterface omdbApiService;
    private MovieDao movieDao;
    private Context context;
    private static final String TAG = "MovieRepository";

    public MovieRepository(Application application) {
        omdbApiService = OmdbApiClient.getRetrofit().create(OmdbApiInterface.class);
        movieDao = MovieDatabase.getInstance(application).movieDao();
        movies = movieDao.getMovies();
        context = application.getApplicationContext();
    }

    public void insert(Movie movie) {
        new InsertMovieAsyncTask(movieDao).execute(movie);
    }

    public void update(Movie movie) {
        new UpdateMovieAsyncTask(movieDao).execute(movie);
    }

    public void deleteAll() {
        new DeleteAllMoviesAsyncTask(movieDao).execute();
    }

    public void insertAll(List<Movie> movies) {
        for (Movie movie : movies) {
            insert(movie);
        }
    }

    public void loadMoviesFromApi(String title) {
        Log.i(TAG, "loadMoviesFromApi: in the beggining of the method");
        Call<MovieList> call = omdbApiService.getMovieResults(Constants.OMDB_API_KEY, title);
        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {

                Log.i(TAG, "onResponse: response not successful");
                if (response.isSuccessful()) {

                    Log.i(TAG, "onResponse: in the beggining in onResponse");
                    List<Movie> movies = response.body().getMovies();
                    deleteAll();
                    insertAll(movies);
                    for (Movie movie : movies) {
                        loadMovie(movie.getImdbId());
                    }
                    Log.i(TAG, "onResponse: in the end in on Response");
                }
            }
            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                Toast.makeText(context, "Failure:(", Toast.LENGTH_LONG).show();
                Log.i(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public LiveData<Movie> getMovieById(String id) {
        return movieDao.getMovieById(id);
    }

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }

    public void loadMovie(String id) {
        Call<Movie> call = omdbApiService.getMovieDetails(Constants.OMDB_API_KEY, id);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {

                if (response.isSuccessful()) {
                    Movie movie = response.body();
                    update(movie);
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Toast.makeText(context, "Failure:(", Toast.LENGTH_LONG).show();
                Log.i(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
