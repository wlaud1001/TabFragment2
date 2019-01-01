package com.example.user.tabfragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class numberbaseball extends AppCompatActivity {
    EditText edittext;
    String input;
    ArrayList<Integer> answer = new ArrayList<Integer>();
    int len; //intent로 사용자가 입력한 숫자의 길이
    String userAnswer;
    ArrayList<HashMap<String, String>> infoList;
    int mode;
    ArrayList<HashMap<String, String>> infoList1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_num);

        len = getIntent().getIntExtra("num#",3); //checkbox에서 입력한 숫자길이
        if(getIntent().getIntExtra("user",0)==1){
            mode=2;
            userAnswer = getIntent().getStringExtra("userAnswer");
        }else mode=1;

        infoList = new ArrayList<>();
        infoList1 = new ArrayList<>();
    }


    @Override
    protected void onStart() {
        answer.clear();
        //정답 생성
        makeAnswer();
        String ans = "";
        for (int i = 0; i < answer.size(); i++) {
            ans += answer.get(i);
        }
        Log.i("ANSWER is", ans);
        //leng = answer.size();
        Log.i("leng", Integer.toString(len));
        super.onStart();
    }
    public boolean isOverlap(String test){
        Set<Character> testSet = new HashSet<Character>();
        for(int i=0; i<test.length();i++){
            testSet.add(test.charAt(i));
        }
        return testSet.size()!=len;
    }
    @Override
    protected void onResume() {
        //게임 진행
        edittext = (EditText) findViewById(R.id.editText2);
        Button button = (Button) findViewById(R.id.bu1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input = edittext.getText().toString();
                if(input.length() != len)
                    Toast.makeText(getApplicationContext(), "Wrong length!! Please enter "+len+" digits.", Toast.LENGTH_LONG).show();
                else if(isOverlap(input)){
                    Toast.makeText(getApplicationContext(), "Digits must not be duplicated.", Toast.LENGTH_LONG).show();
                }
                else {
                    HashMap<String, String> info = new HashMap<>();

                    Log.i("Trial", input);

                    int res = trialResult();
                    int str = res / 10;
                    int bal = res % 10;


                    info.put("input", input);
                    info.put("ball", Integer.toString(bal));
                    info.put("strike", Integer.toString(str));

                    if (str + bal == 0) {
                        info.put("strike","ㅡ");
                        info.put("ball", "ㅡ");
                        info.put("out", "O");

                    } else {
                        info.put("out", "X");
                    }


                    infoList.add(info);


                    ListView pastlist = findViewById(R.id.pastinfo);

                    infoList1.clear();
                    for(int i=infoList.size()-1; i>=0; i--)
                    {
                        infoList1.add(infoList.get(i));
                    }

                    ListAdapter adapter = new ExtendedSimpleAdapter(getApplicationContext(),infoList1 ,
                            R.layout.infolist, new String[]{"input", "strike", "ball", "out"},
                            new int[]{R.id.tnumber, R.id.tstrike, R.id.tball, R.id.tout});
                    pastlist.setAdapter(adapter);

                    /*result of trial*/
                    if (str == len) {
                        /** SUCCESS - Game종료*/
                        Log.i("basenum", "SUCCESS");
                        showalert(input);
                    } else if (str + bal == 0) {
                        Log.i("basenum", "OUT");
                        Toast.makeText(getApplicationContext(), "OUT", Toast.LENGTH_LONG).show();
                    } else {
                        /** NORMAL - strike값과 ball값 띄우기*/
                        Log.i("basenum", "strike: " + str + " ball: " + bal);
                        Toast.makeText(getApplicationContext(), "strike: " + str + " ball: " + bal, Toast.LENGTH_LONG).show();
                    }
                    edittext.getText().clear();
                }
            }
        });
        /**NEW GAME BUTTON Click*/
        Button restart = findViewById(R.id.restart);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infoList.clear();
                onBackPressed();

            }
        });
        super.onResume();
    }

    /* 임의로 4개의 숫자배열 생성 */
    public void makeAnswer() {
        if(mode==1){
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
            return;
        }else{
            for(int i =0; i<len; i++){
                answer.add(userAnswer.charAt(i)-48);
            }
        }

    }

    public int trialResult() {
        int strike = 0;
        int ball = 0;
        for (int i = 0; i < len; i++) {
            int current = input.charAt(i);
            if (current - 48 == answer.get(i)) {
                strike++;
            } else if (answer.contains(current - 48)) {
                ball++;
            }
        }
        return strike * 10 + ball;
    }


    /**SUCCESS*/
    public void showalert(String string) {
        AlertDialog.Builder alert_confirm = new AlertDialog.Builder(this);
        // 메세지
        alert_confirm.setMessage("4 STRIKES\n" + "정답: " + string);
        // 확인 버튼 리스너
        alert_confirm.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                /**restart*/
                infoList.clear();
                onBackPressed();

            }
        });
        // 다이얼로그 생성
        AlertDialog alert = alert_confirm.create();
        // 아이콘
        //alert.setIcon(R.drawable.icon);
        // 다이얼로그 타이틀
        alert.setTitle("정답입니다.");
        // 다이얼로그 보기
        alert.show();
    }
}







