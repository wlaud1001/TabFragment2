package com.example.user.tabfragment;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class DoubleMode extends AppCompatActivity {
    EditText edittext;
    String input;
    ArrayList<Integer> answer1 = new ArrayList<Integer>();
    ArrayList<Integer> answer2 = new ArrayList<Integer>();
    ArrayList<HashMap<String, String>> infoList1;
    ArrayList<HashMap<String, String>> infoList2;

    int len; //intent로 사용자가 입력한 숫자의 길이
    int turn=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_mode);
        len = getIntent().getIntExtra("num#",3); //checkbox에서 입력한 숫자길이
        infoList1 = new ArrayList<>();
        infoList1 = new ArrayList<>();
    }
    @Override
    protected void onStart() {
        answer1.clear();
        answer2.clear();
        //정답 생성
        makeAnswer(answer1);
        makeAnswer(answer2);
        String ans1 = "";
        String ans2 = "";
        for (int i = 0; i < len; i++) {
            ans1 += answer1.get(i);
            ans2 += answer2.get(i);
        }
        Log.i("ANSWER1 is", ans1);
        Log.i("ANSWER2 is", ans2);
        Log.i("leng", Integer.toString(len));
        super.onStart();
    }
    /* 임의로 4개의 숫자배열 생성 */
    public ArrayList<Integer> makeAnswer(ArrayList<Integer> answer) {
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
        return answer;
    }
    public int trialResult(ArrayList<Integer> answer) {
        int strike = 0;
        int ball = 0;
        int current;
        for (int i = 0; i < len; i++) {
            current = input.charAt(i);
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
                infoList1.clear();
                infoList2.clear();
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

    @Override
    protected void onResume() {
        //게임 진행
        edittext = (EditText) findViewById(R.id.editText2) ;
        Button button = (Button) findViewById(R.id.bu1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(turn==0){
                    Game(answer1,infoList1,edittext);
                }else
                    Game(answer2,infoList2,edittext);
                }
            });
            /**NEW GAME BUTTON Click*/
            Button restart = findViewById(R.id.restart);
            restart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    infoList1.clear();
                    infoList2.clear();
                    onBackPressed();
                }
            });
            if(turn==0)
                turn=1;
            else turn=0;
            super.onResume();

    }
    public void Game(ArrayList<Integer> answer,ArrayList<HashMap<String, String>> infoList, EditText edittext1){
        input = edittext1.getText().toString();
        if(input.length() != len)
            Toast.makeText(getApplicationContext(), "wrong input", Toast.LENGTH_LONG).show();
        else {
            HashMap<String, String> info = new HashMap<>();

            Log.i("Trial", input);

            int res = trialResult(answer);
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
        }
    }
}