package com.foodbank.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import androidx.appcompat.app.AppCompatActivity;

import com.foodbank.app.Receiver.ReceiverActivity;
import com.foodbank.app.ui.auth.LoginActivity;
import com.foodbank.app.ui.auth.RegisterActivity;
import com.foodbank.app.utils.PreferenceManager;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        removeActionBar();
        setContentView(R.layout.activity_splash_screen);
        animateLogo();
        new Handler(Looper.getMainLooper()).postDelayed(this::handleNavigation, 3000);
    }

    void  removeActionBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void animateLogo() {

        AlphaAnimation anim = new AlphaAnimation(1.0f, 0.3f);
        anim.setRepeatCount(Animation.INFINITE);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setDuration(1000);
        findViewById(R.id.rlContainer).setAnimation(anim); // to start animation
    }

    private void handleNavigation() {

        PreferenceManager preferenceManager = PreferenceManager.Companion.getInstance(this);


        if(Boolean.TRUE.equals(preferenceManager.isLoggedIn())) {


            if(PreferenceManager.Companion.getInstance().isDonner()) {

                startActivity(new Intent(SplashScreen.this, ReceiverActivity.class));
            }else {
                 startActivity(new Intent(SplashScreen.this, ReceiverActivity.class));
            }

        }else {
            startActivity(new Intent(SplashScreen.this, LoginActivity.class));
        }


        finish();
    }
}