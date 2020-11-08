package ir.android.mytest.activity.LoginSplash;

import android.animation.Animator;
import android.app.Activity;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.textfield.TextInputEditText;

import ir.android.mytest.R;

public class UiManager {
    Activity activity;
    Listener listener;

    // interface for listener login onClick and Splash onInVisible
    public interface Listener{
        void onFinishSplash();
        void onClickLogin(String value);
    }

    public UiManager(Activity activity,Listener listener) {
        this.activity = activity;
        this.listener=listener;
    }

    public void setupContent(){
        final LottieAnimationView f=activity.findViewById(R.id.frontLottie);
        f.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                f.pauseAnimation();
                showSplash();
            }
        });

        Button login =activity.findViewById(R.id.button_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextInputEditText text=activity.findViewById(R.id.email);
                listener.onClickLogin(text.getText().toString());
            }
        });

    }

    private void showSplash(){
        final LinearLayout loadLayout=activity.findViewById(R.id.loading_line);
        loadLayout.setVisibility(View.VISIBLE);
        new CountDownTimer(5000,1000){
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                loadLayout.setVisibility(View.GONE);
                listener.onFinishSplash();
            }
        }.start();
    }

    //show login linear layout
    public void showLogin(){
        LinearLayout loginLayout=activity.findViewById(R.id.login_line);
        loginLayout.setVisibility(View.VISIBLE);
    }

    //change login input text hint and clear text
    public void setHint(String hint){
        TextInputEditText text=activity.findViewById(R.id.email);
        text.setHint(hint);
        text.setText("");
    }
    public void setHint(int hint){
        TextInputEditText text=activity.findViewById(R.id.email);
        text.setHint(hint);
        text.setText("");
    }

    //show/hide circle loading and  hide/show ok button
    public void loading(boolean show){
        Button button =activity.findViewById(R.id.button_login);
        ProgressBar progressBar =activity.findViewById(R.id.progressBar);

        button.setVisibility(show?View.GONE:View.VISIBLE);
        progressBar.setVisibility(show?View.VISIBLE:View.GONE);
    }

}
