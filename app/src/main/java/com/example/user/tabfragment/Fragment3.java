package com.example.user.tabfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment3 extends Fragment {

    /*
    EditText edittext;
    String input;
    ArrayList<Integer> answer = new ArrayList<Integer>();
    int leng;
    int strike =0;
    int ball =0;


    public Fragment3() {
        // Required empty public constructor
        makeAnswer();
        String ans = "";
        for(int i=0; i<answer.size();i++){
            ans+=answer.get(i);
        }
        Log.i("ANSWER is",ans);
        leng = answer.size();
    }

*/
   private Button gamestart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragment3, container, false);

        gamestart = (Button) view.findViewById(R.id.gamestart);


        gamestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity().getApplicationContext(),numberbaseball.class);
                startActivity(intent);


            }
        });


        /*
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.activity_base_num, container, false);
        edittext = (EditText) view.findViewById(R.id.editText2);
        Button button = (Button) view.findViewById(R.id.bu1);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                input = edittext.getText().toString();
                Log.i("Trial",input);
                int res = trialResult();
                int str = res/10;
                int bal = res%10;
                //result of trial
                if(str==4){
                    //TODO: SUCCESS Game종료
                    Log.i("basenum","SUCCESS");
                } else if(str+bal==0){
                    Log.i("basenum","OUT");
                }
                //TODO: else->strike값과 ball값 띄우기
                Log.i("basenum","strike: "+str+" ball: "+bal);
            }
        });

*/
        return view;


    }

    /*
    public void makeAnswer() {
        Set<Integer> set = new HashSet<Integer>();
        Random random = new Random();
        int Currentsetsize = 0;
        while (set.size() <= 3) {
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
    */
}