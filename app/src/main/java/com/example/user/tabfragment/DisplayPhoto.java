package com.example.user.tabfragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayPhoto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_photo);

        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        Intent intent = getIntent();
        int position = intent.getIntExtra("key",0);
        int id = ImageAdapter.mThumbIds[position]; //click한 image의 id
        ImageView imgview = findViewById(R.id.imageView3);
        //TextView photonum = findViewById(R.id);
        setTitle("Photo "+position);
        imgview.setImageDrawable(getResources().getDrawable(id));



    }

}
