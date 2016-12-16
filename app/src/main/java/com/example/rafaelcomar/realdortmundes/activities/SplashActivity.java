package com.example.rafaelcomar.realdortmundes.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

import com.example.rafaelcomar.realdortmundes.R;

public class SplashActivity extends AppCompatActivity {

    // Timer da splash screen
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImageView img = (ImageView) findViewById(R.id.imgLogo);
        img.setImageResource(R.drawable.escudo);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // Esse método será executado sempre que o timer acabar
                // E inicia a activity principal
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);

                // Fecha esta activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
