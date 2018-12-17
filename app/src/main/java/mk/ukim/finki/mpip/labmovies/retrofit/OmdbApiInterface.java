package mk.ukim.finki.mpip.labmovies.retrofit;

import java.util.List;

import mk.ukim.finki.mpip.labmovies.models.Movie;
import mk.ukim.finki.mpip.labmovies.models.MovieList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OmdbApiInterface {

    @GET(".")
    Call<MovieList> getMovieResults(@Query("apikey") String apikey,
                                    @Query("s") String movieTitle);

    @GET(".")
    Call<Movie> getMovieDetails(@Query("apikey") String apikey,
                                @Query("i") String id);

}
