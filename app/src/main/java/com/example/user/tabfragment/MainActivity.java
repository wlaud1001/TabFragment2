package com.example.user.tabfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


public class MainActivity extends AppCompatActivity implements ActionBar.TabListener {

    AppSectionsPagerAdapter mAppSectionsPagerAdapter;

    //ViewPager에는 한번에 하나의 섹션만 보여진다.
    static ViewPager mViewPager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //어댑터를 생성한다. 섹션마다 프래그먼트를 생성하여 리턴해준다.
        mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());

        //액션바를 설정한다.
        final ActionBar actionBar = getSupportActionBar();

        //액션바 코너에 있는 Home버튼을 비활성화 한다.
        actionBar.setHomeButtonEnabled(true);

        //탭을 액션바에 보여줄 것이라고 지정한다.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        //ViewPager를 정의하고
        mViewPager = (ViewPager) findViewById(R.id.pager);
        //ViewPager에 어댑터를 연결한다.
        mViewPager.setAdapter(mAppSectionsPagerAdapter);

        //사용자가 섹션사이를 스와이프할때 발생하는 이벤트에 대한 리스너를 설정한다.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override //스와이프로 페이지 이동시 호출됨
            public void onPageSelected(int position) {
                //액션바의 탭위치를 페이지 위치에 맞춘다.
                actionBar.setSelectedNavigationItem(position);
            }
        });

        //각각의 섹션을 위한 탭을 액션바에 추가한다.
        for (int i = 0; i <mAppSectionsPagerAdapter.getCount(); i++) {
            Log.i("main","adding..."+i);
            actionBar.addTab(
                    actionBar.newTab()
                            //어댑터에서 정의한 페이지 제목을 탭에 보이는 문자열로 사용한다.
                            .setText(mAppSectionsPagerAdapter.getPageTitle(i))
                            //TabListener 인터페이스를 구현할 액티비티 오브젝트도 지정한다.
                            .setTabListener(this));
        }
    }


    @Override //액션바의 탭 선택시 호출됨
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        //액션바에서 선택된 탭에 대응되는 페이지를 뷰페이지에서 현재 보여지는 페이지로 변경한다.
        mViewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    //세션에 대응되는 프래그먼트를 리턴한다
    //FragmentPagerAdapter는 메모리에 프래그먼트를 로드한 상태로 유지하지만(3개 프래그먼트 유지하는게 적당함)
    //FragmentStatePagerAdapter는 화면에 보이지 않는 프래그먼트는 메모리에서 제거한다.
    public class AppSectionsPagerAdapter extends FragmentPagerAdapter {
        private FragmentManager fm;
        public AppSectionsPagerAdapter(FragmentManager fm) {
            super(fm);
            this.fm = fm;
        }

        @Override
        public Fragment getItem(int pos) {
            //태그로 프래그먼트를 찾는다.
            Fragment fragment = fm.findFragmentByTag("android:switcher:" + mViewPager.getId() + ":" + getItemId(pos));

            //프래그먼트가 이미 생성되어 있는 경우에는 리턴
            if (fragment != null) {
                return fragment;
            }

            Log.i("in main","getitem");
            //프래그먼트의 인스턴스를 생성한다.
            switch(pos) {
                case 0: return new Fragment1();
                case 1: return new Fragment2();
                case 2: return new Fragment3();
                default : return null;
            }

        }

        //프래그먼트를 최대 3개를 생성할 것임
        @Override
        public int getCount() {
            return 3;
        }

        //탭의 제목으로 사용되는 문자열 생성
        @Override
        public CharSequence getPageTitle(int position) {
            switch(position) {
                case 0: return "Contacts";
                case 1: return "Gallery";
                case 2: return "Game";
                default: return "Something Wrong";
            }
        }
    }


}