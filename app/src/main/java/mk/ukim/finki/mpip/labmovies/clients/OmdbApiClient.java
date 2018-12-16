package mk.ukim.finki.mpip.labmovies.clients;

import mk.ukim.finki.mpip.labmovies.constants.Constants;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OmdbApiClient {

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(Constants.OMDB_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit = builder.build();
    private static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);
    private static OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();


    public static Retrofit getRetrofit() {

        if (!clientBuilder.interceptors().contains(loggingInterceptor)) {
            clientBuilder.addInterceptor(loggingInterceptor);
            builder.client(clientBuilder.build());
            retrofit = builder.build();
        }
        return retrofit;
    }
}
