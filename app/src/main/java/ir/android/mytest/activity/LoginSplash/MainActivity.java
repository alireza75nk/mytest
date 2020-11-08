package ir.android.mytest.activity.LoginSplash;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import ir.android.mytest.R;
import ir.android.mytest.model.RetroUser;
import ir.android.mytest.util.AndroidAPI;
import ir.android.mytest.network.RetroInstance;
import ir.android.mytest.util.Setting;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Setting setting;
    UiManager uiManager;
    UiManager.Listener uiListener;

    int step=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setup();



    }

    private void setup(){
        uiListener=new UiManager.Listener() {
            @Override
            public void onFinishSplash() { checkLogin();
            }

            @Override
            public void onClickLogin(String value) {
                checkStep(value);
            }
        };

        uiManager=new UiManager(this,uiListener);

        setting=new Setting(this);

        uiManager.setupContent();
    }

    private void checkLogin(){
        if(AndroidAPI.isInternetAvailable(this)) {
            String isLogin = setting.get("checkLogin","0");
            if (isLogin.equals("1")) {
                // start activity
                startActivity();
            } else {
                //show login Content
                uiManager.showLogin();
            }

        }else{
            new AlertDialog.Builder(this).
                    setMessage("No Internet Connection!")
                    .setPositiveButton("Connected", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            checkLogin();
                        }
                    }).setCancelable(false).show();
        }
    }

    private void checkStep(final String value){
        // 0= email entered  , 1= email validate code entered , 1= set name
        uiManager.loading(true);
        RetroInstance.UserService service=RetroInstance.getRetrofitInstance().create(RetroInstance.UserService.class);
        switch (step){
            case 0:
                Call<String> call=service.setCode(value);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        step=1;
                        setting.put("email",value);
                        uiManager.setHint("check your email and enter code");
                        uiManager.loading(false);
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        uiManager.setHint(t.getMessage());
                        uiManager.loading(false);
                    }
                });
                break;


            case 1:
                Call<String> call1=service.checkCode(setting.get("email",""),value);
                call1.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        step=2;
                        setting.put("token",response.body());
                        uiManager.setHint("enter your name");
                        uiManager.loading(false);
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        uiManager.setHint("try again");
                        uiManager.loading(false);
                    }
                });
                break;


            case 2:
                Call<String> call2=service.setName(
                        setting.get("email",""),
                        setting.get("token",""),
                        value);

                call2.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        //start activity
                        setting.put("name",value);
                        startActivity();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        uiManager.setHint("try again");
                        uiManager.loading(false);
                    }
                });
                break;

        }
    }

    private void startActivity(){
        RetroInstance.UserService service=RetroInstance.getRetrofitInstance().create(RetroInstance.UserService.class);
        Call<RetroUser> call=service.getInfo(
                setting.get("email",""),
                setting.get("token",""));
        call.enqueue(new Callback<RetroUser>() {
            @Override
            public void onResponse(Call<RetroUser> call, Response<RetroUser> response) {
                setting.put("checkLogin","1");
                Intent intent=new Intent();
                intent.putExtra("name",response.body().getName());
            }

            @Override
            public void onFailure(Call<RetroUser> call, Throwable t) {

            }
        });
    }

}