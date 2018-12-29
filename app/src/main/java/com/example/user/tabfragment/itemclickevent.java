package com.example.user.tabfragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class itemclickevent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemclickevent);


        // 이전 액티비티로부터 넘어온 데이터를 꺼낸다.
        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");
        String mobile = getIntent().getStringExtra("mobile");

        TextView tname = (TextView)findViewById(R.id.tname);
        TextView temail = (TextView)findViewById(R.id.temail);
        TextView tmobile = (TextView)findViewById(R.id.tmobile);

        tname.setText(name);
        temail.setText(email);
        tmobile.setText(mobile);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {


                onBackPressed();


                /*
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                */

                /*
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragment1 fragment1 = new Fragment1();
                transaction.replace(R.id.fragment_container, fragment1);
                transaction.commit();

                */

            }
        });


    }

}
