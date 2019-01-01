package com.example.user.tabfragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

public class player1 extends AppCompatActivity {
    EditText edittext;
    String input;
    ArrayList<Integer> answer;
    int len; //intent로 사용자가 입력한 숫자의 길이
    boolean first;
    ArrayList<HashMap<String, String>> infoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_num);
        len = getIntent().getIntExtra("num#",3); //checkbox에서 입력한 숫자길이
        first = getIntent().getBooleanExtra("first",false);
        if(first){
            infoList = new ArrayList<>();
            answer = new ArrayList<Integer>();
            answer.clear();
            //정답 생성
            makeAnswer(len);
            String ans = "";
            for (int i = 0; i < answer.size(); i++) {
                ans += answer.get(i);
            }
            Log.i("ANSWER is", ans);
            //leng = answer.size();
            Log.i("leng", Integer.toString(len));
            super.onStart();
        }

    }

/*
    @Override
    protected void onStart() {
        answer.clear();
        //정답 생성
        makeAnswer(len);
        String ans = "";
        for (int i = 0; i < answer.size(); i++) {
            ans += answer.get(i);
        }
        Log.i("ANSWER is", ans);
        //leng = answer.size();
        Log.i("leng", Integer.toString(len));
        super.onStart();
    }
*/


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
                    Toast.makeText(getApplicationContext(), "wrong input", Toast.LENGTH_LONG).show();
                else {
                    HashMap<String, String> info = new HashMap<>();

                    Log.i("Trial", input);

                    int res = trialResult();
                    int str = res / 10;
                    int bal = res % 10;


                    info.put("input", input);
                    if(str==0){
                        info.put("strike","ㅡ");
                    }else
                        info.put("strike", Integer.toString(str));

                    if(bal==0){
                        info.put("ball", "ㅡ");
                    }else
                        info.put("ball", Integer.toString(bal));

                    if (str + bal == 0) {
                        info.put("out", "O");
                    } else {
                        info.put("out", "X");
                    }


                    infoList.add(info);


                    ListView pastlist = findViewById(R.id.pastinfo);

                    Collections.reverse(infoList);
                    ListAdapter adapter = new ExtendedSimpleAdapter(getApplicationContext(),infoList ,
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
                    if(first){
                        Intent intent = new Intent(getApplicationContext(),player2.class);
                        intent.putExtra("first",true);
                        startActivity(intent);
                        first = false;
                    }
                    else{
                        Intent intent = new Intent(getApplicationContext(),player2.class);
                        startActivity(intent);
                    }
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
                /*
                ListView pastlist = findViewById(R.id.pastinfo);
                ListAdapter adapter = new ExtendedSimpleAdapter(getApplicationContext(), infoList,
                        R.layout.infolist, new String[]{"input", "strike", "ball", "out"},
                        new int[]{R.id.tnumber, R.id.tstrike, R.id.tball, R.id.tout});
                pastlist.setAdapter(adapter);
                onStart();
                onResume();*/
            }
        });
        super.onResume();
    }

    /* 임의로 4개의 숫자배열 생성 */
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







