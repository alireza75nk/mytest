package ir.android.mytest.activity.LoginSplash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ir.android.mytest.R;
import ir.android.mytest.model.RetroUser;
import ir.android.mytest.network.RetroInstance;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        RetroInstance.UserService service=RetroInstance.getRetrofitInstance().create(RetroInstance.UserService.class);
        //Call<RetroUser> call=service.login();

    }
}