package com.smparkworld.androidpractice18;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView ivMainImage = findViewById(R.id.ivMainImage);
        final Animation imageAnim = AnimationUtils.loadAnimation(this, R.anim.image_anim);

        findViewById(R.id.btnImageAnim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivMainImage.startAnimation(imageAnim);
            }
        });

        findViewById(R.id.btnIntentAnim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(i);

                overridePendingTransition(R.anim.intent_anim_in, R.anim.intent_anim_out);
            }
        });

    }
}
