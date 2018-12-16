package mk.ukim.finki.mpip.labmovies.asynctasks;

import android.os.AsyncTask;

import mk.ukim.finki.mpip.labmovies.models.Movie;
import mk.ukim.finki.mpip.labmovies.persistence.dao.MovieDao;

public class UpdateMovieAsyncTask extends AsyncTask<Movie, Void, Void> {

    private MovieDao movieDao;

    public UpdateMovieAsyncTask(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    protected Void doInBackground(Movie... movies) {
        movieDao.update(movies[0]);
        return null;
    }
}
