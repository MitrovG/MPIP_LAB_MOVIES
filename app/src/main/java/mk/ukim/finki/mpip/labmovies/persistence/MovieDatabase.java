package mk.ukim.finki.mpip.labmovies.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import mk.ukim.finki.mpip.labmovies.constants.Constants;
import mk.ukim.finki.mpip.labmovies.models.Movie;
import mk.ukim.finki.mpip.labmovies.persistence.dao.MovieDao;

@Database(entities = Movie.class, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();

    private static MovieDatabase instance;

    public static synchronized MovieDatabase getInstance(Context context) {
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), MovieDatabase.class,
                    Constants.DB_NAME).build();
        }
        return instance;
    }
}
