package ir.android.mytest.network;

import ir.android.mytest.model.RetroUser;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RetroInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://alirezanamazi.ir/api/";


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
        @GET("user/login/email")
        Call<ResponseBody> setCode(@Query("email") String email);

        @GET("user/login/{email}/")
        Call<ResponseBody> checkCode(@Path("email") String email, @Query("code") String code);

        @GET("user/{email}/{token}/")
        Call<ResponseBody> setName(@Path("email") String email, @Query("token") String token,@Query("name") String name);

        @GET("user/{email}/{token}/")
        Call<RetroUser> getInfo(@Path("email") String email, @Path("token") String token);

    }
}
