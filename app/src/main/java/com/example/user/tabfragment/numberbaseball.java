package com.example.user.tabfragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class numberbaseball extends AppCompatActivity {


    EditText edittext;
    String input;
    ArrayList<Integer> answer = new ArrayList<Integer>();
    int leng;
    int strike =0;
    int ball =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_num);


    }


    @Override
    protected void onStart() {

        int len;

        len =4;


        answer.clear();
        //정답 생성
        makeAnswer(len);
        String ans = "";
        for(int i=0; i<answer.size();i++){
            ans+=answer.get(i);
        }
        Log.i("ANSWER is",ans);

        leng = answer.size();

        Log.i("leng",Integer.toString(leng));

        super.onStart();

    }

    @Override
    protected void onResume() {


        //게임 진행
        edittext = (EditText) findViewById(R.id.editText2);
        Button button = (Button) findViewById(R.id.bu1);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                input = edittext.getText().toString();


                Log.i("Trial",input);
                int res = trialResult();
                int str = res/10;
                int bal = res%10;
                /*result of trial*/
                if(str==4){
                    /*TODO: SUCCESS Game종료*/
                    Log.i("basenum","SUCCESS");
                    showalert(input);

                } else if(str+bal==0){
                    Log.i("basenum","OUT");
                }
                /*TODO: else->strike값과 ball값 띄우기*/
                Log.i("basenum","strike: "+str+" ball: "+bal);

                edittext.getText().clear();


            }
        });



        Button restart = findViewById(R.id.restart);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onStart();
                onResume();

            }
        });



        super.onResume();
    }


    public void makeAnswer(int len) {
        Set<Integer> set = new HashSet<Integer>();
        set.clear();
        Random random = new Random();
        int Currentsetsize = 0;
        while (set.size() < len) {
            int c = random.nextInt(10);
            set.add(c);
            if (Currentsetsize != set.size()) {
                answer.add(c);
                Currentsetsize++;
            }
        }
    }

    public int trialResult() {
        int strike =0;
        int ball =0;
        for(int i=0; i< leng; i++){
            int current = input.charAt(i);
            if(current-48==answer.get(i)){
                strike++;
            }else if(answer.contains(current-48)){
                ball++;
            }
        }
        return strike*10+ball;
    }

    public void showalert(String string) {

        AlertDialog.Builder alert_confirm = new AlertDialog.Builder(this);
        // 메세지
        alert_confirm.setMessage("4 STRIKES\n" + "정답: " + string);
        // 확인 버튼 리스너
        alert_confirm.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

               onStart();
               onResume();

            }
        });
        // 다이얼로그 생성
        AlertDialog alert = alert_confirm.create();

        // 아이콘
        //alert.setIcon(R.drawable.ic_launcher);
        // 다이얼로그 타이틀
        alert.setTitle("정답입니다.");
        // 다이얼로그 보기
        alert.show();

    }


}
