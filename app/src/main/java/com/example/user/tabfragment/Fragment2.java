package com.example.user.tabfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment {


    public Fragment2() {
        // Required empty public constructor
    }

    @Override //fragment가 생성될때 호출되는 부분
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override //onCreate 후에 화면을 구성할 때 호출되는 부분
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment2, container, false);
        GridView gridview = (GridView) view.findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(getActivity()));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Toast.makeText(getActivity(), "이미지 클릭" + position,
                        Toast.LENGTH_SHORT).show();
                /*Intent 생성자는 두개의 변수를 취합니다*
                 *첫번째 매개변수 Context(Activity 클래스가 Context의 서브클래스이기 때문에 this가 사용됨)
                 *시스템이 Intent를 전달할 앱 구성요소의 Class(이 경우, 시작할 activity)
                 */
                Intent intent = new Intent(getActivity(), DisplayPhoto.class);
                //첫번째 인자는 나중에 data를 꺼내기 위한 key 두번째 인자는 전달할 data입니다.
                intent.putExtra("key", position);

                //startActivity() 메서드는 Intent로 지정한 DisplayPhoto의 인스턴스를 시작합니다
                startActivity(intent);
            }
        });

        return view;
    }
    }
