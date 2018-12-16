package mk.ukim.finki.mpip.labmovies.persistence.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import mk.ukim.finki.mpip.labmovies.models.Movie;

@Dao
public interface MovieDao {

    @Insert
    void insert(Movie movie);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Movie movie);

    @Query("DELETE FROM movie")
    void deleteAll ();

    @Query("SELECT * FROM movie")
    LiveData<List<Movie>> getMovies();

}
