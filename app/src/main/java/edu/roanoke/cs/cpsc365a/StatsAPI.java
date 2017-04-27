package edu.roanoke.cs.cpsc365a;

/**
 * Created by connorricks on 4/26/17.
 */

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StatsAPI {

    public static final String BASE_URL = "http://cs.roanoke.edu/~jastoro/CPSC365A-WebApp/public/api/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
