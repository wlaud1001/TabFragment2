package com.example.user.tabfragment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class userInput extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_input);
        final int numnum = getIntent().getIntExtra("num#", 0);

        final EditText edittext = (EditText) findViewById(R.id.editText3);
        Button button = (Button) findViewById(R.id.bu2);
        Button button3 = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userAnswer = edittext.getText().toString();
                switch(isValid(userAnswer,numnum)){
                    case -1: //숫자를 입력해주세요
                        Toast.makeText(getApplicationContext(), "You can only enter numbers.", Toast.LENGTH_LONG).show();
                        return;
                    case 0:
                        Toast.makeText(getApplicationContext(), "Digits must not be duplicated.", Toast.LENGTH_LONG).show();
                        return;
                    case 1:
                        if (userAnswer.length() == numnum) {
                            Intent intent = new Intent(getApplicationContext(), numberbaseball.class);
                            intent.putExtra("userAnswer", userAnswer);
                            intent.putExtra("user", 1);
                            intent.putExtra("num#", numnum);
                            startActivity(intent);
                        } else
                            Toast.makeText(getApplicationContext(), "Wrong length!! Please enter " + numnum + " digits.", Toast.LENGTH_LONG).show();
                    default:

                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
     public int isValid(String str, int leng){
         Set<Integer> set = new HashSet<Integer>();
         set.clear();
         int current;
        for(int i=0; i<str.length(); i++){
            current=str.charAt(i)-48;
            if(current>=0&&current<10){
                set.add(current);
            }else return -1; //숫자를 입력하시오
        }
        if(set.size()==leng) return 1; //valid!
        return 0;//중복된 숫자
     }
}
