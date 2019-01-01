package com.example.user.tabfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment3 extends Fragment {

   private Button gamestart;
   ArrayList<CheckBox> mCheckBoxes = new ArrayList<CheckBox>();
   ArrayList<CheckBox> gamemode = new ArrayList<CheckBox>();
   int selected_position;
   CheckBox checkBox1;
   CheckBox checkBox2;
   CheckBox checkBox3;
   CheckBox single;
   CheckBox multi;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment3, container, false);
        gamestart = (Button) view.findViewById(R.id.gamestart);

        checkBox1 = view.findViewById(R.id.checkBox1);
        checkBox2 = view.findViewById(R.id.checkBox2);
        checkBox3 = view.findViewById(R.id.checkBox3);
        single = view.findViewById(R.id.single);
        multi = view.findViewById(R.id.multi);
        mCheckBoxes.add(checkBox1);
        mCheckBoxes.add(checkBox2);
        mCheckBoxes.add(checkBox3);
        gamemode.add(single);
        gamemode.add(multi);
        noOverlap(mCheckBoxes);
        noOverlap(gamemode);


        gamestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected_position = whichBox();
                Log.i("checkbox1",""+checkBox1.isChecked());
                Log.i("checkbox2",""+checkBox2.isChecked());
                Log.i("checkbox3",""+checkBox3.isChecked());
                Log.i("selected_position",""+selected_position);
                if(single.isChecked()){
                    Intent intent1 = new Intent(getActivity().getApplicationContext(),numberbaseball.class);
                    intent1.putExtra("num#",selected_position);
                    if(selected_position>0)
                        startActivity(intent1);
                    else
                        Toast.makeText(getActivity(),"Cannot start TT", Toast.LENGTH_LONG).show();
                }else{
                    Intent intent = new Intent(getActivity().getApplicationContext(),DoubleMode.class);
                    intent.putExtra("first",true);
                    startActivity(intent);
                }
            }
        });
        return view;
    }

    /*checkBox 선택 중복되지 않게*/
    public void noOverlap(final ArrayList<CheckBox> boxes){
        for(CheckBox box : boxes){
            box.setOnClickListener(new View.OnClickListener(){
                @Override public void onClick (View v) {
                    if (((CheckBox) v).isChecked()) {
                        for (int i = 0; i < boxes.size(); i++) {
                            if (boxes.get(i) == v)
                                selected_position = i;
                            else boxes.get(i).setChecked(false); } }
                    else { selected_position=-1; } } });
        }
    }

    /* 어떤 checkbox 선택했는지 보고 숫자길이 return */
    private int whichBox(){
        if(checkBox1.isChecked()){
            return 3;
        }else if(checkBox2.isChecked()){
            return 4;
        }else if(checkBox3.isChecked()){
            return 5;
        }else {
            Toast.makeText(getActivity(),"SELECT NUMBER LENGTH", Toast.LENGTH_LONG).show();
            return 0;
        }
    }
}