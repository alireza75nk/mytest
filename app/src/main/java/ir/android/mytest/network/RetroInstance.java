package ir.android.mytest.network;

import ir.android.mytest.model.RetroUser;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RetroInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://api.alirezanamazi.ir";


    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public interface UserService {
        @GET("/users/login/email")
        Call<String> setCode(@Query("email") String email);

        @GET("/users/login/{email}")
        Call<String> checkCode(@Path("email") String email, @Query("code") String code);

        @GET("/users/login/{email}/{token}")
        Call<String> setName(@Path("email") String email, @Query("token") String token,@Query("name") String name);

        @GET("/users/{email}/{token}")
        Call<RetroUser> getInfo(@Path("email") String email, @Path("token") String token);

    }
}
