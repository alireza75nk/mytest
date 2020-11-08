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
        @GET("users/login")
        Call<RetroUser> login(@Query("email") String email);
    }
}
