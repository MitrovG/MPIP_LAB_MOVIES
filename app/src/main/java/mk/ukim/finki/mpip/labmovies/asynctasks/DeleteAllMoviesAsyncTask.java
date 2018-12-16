package mk.ukim.finki.mpip.labmovies.asynctasks;

import android.os.AsyncTask;

import mk.ukim.finki.mpip.labmovies.persistence.dao.MovieDao;

public class DeleteAllMoviesAsyncTask extends AsyncTask<Void, Void, Void> {

    private MovieDao movieDao;

    public DeleteAllMoviesAsyncTask(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        movieDao.deleteAll();
        return null;
    }
}
