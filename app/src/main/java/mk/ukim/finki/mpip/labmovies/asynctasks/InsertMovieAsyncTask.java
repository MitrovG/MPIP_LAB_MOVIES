package mk.ukim.finki.mpip.labmovies.asynctasks;

import android.os.AsyncTask;

import mk.ukim.finki.mpip.labmovies.models.Movie;
import mk.ukim.finki.mpip.labmovies.persistence.dao.MovieDao;

public class InsertMovieAsyncTask extends AsyncTask<Movie, Void, Void> {

    private MovieDao movieDao;

    public InsertMovieAsyncTask(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    protected Void doInBackground(Movie... movies) {
        movieDao.insert(movies[0]);
        return null;
    }
}
