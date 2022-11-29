package com.pangtourPangasinan.pangtour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.pangtourPangasinan.pangtour.R;

public class Full_View extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_view);

        ImageView imageView= findViewById(R.id.img_full);

        Intent intent = getIntent();
        String otherImage_1 = intent.getStringExtra("otherImage_1");

        Glide.with(this).load(otherImage_1).into(imageView);

    }
}