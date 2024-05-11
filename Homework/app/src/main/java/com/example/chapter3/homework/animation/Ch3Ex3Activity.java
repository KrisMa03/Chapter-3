package com.example.chapter3.homework.animation;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.airbnb.lottie.LottieAnimationView;
import com.example.chapter3.homework.R;
import com.example.chapter3.homework.fragment.FriendsListFragment;
import com.example.chapter3.homework.fragment.HelloFragment;
import com.google.android.material.tabs.TabLayout;

public class Ch3Ex3Activity extends AppCompatActivity {
    ViewPager pager;
    private static final int PAGE_COUNT = 2;
    private int targetPage = -1;
    private boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch3ex3);
        pager = findViewById(R.id.view_pager);
        pager.setOffscreenPageLimit(1);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                switch (i) {
                    case 0:
                        return new FriendsListFragment();
                    case 1:
                        return new HelloFragment();
                    default:
                        return new HelloFragment();
                }
            }

            @Override
            public int getCount() {
                return PAGE_COUNT;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "好友列表";
                    case 1:
                        return "我的好友";
                    default:
                        return null;
                }
            }
        });
        tabLayout.setupWithViewPager(pager);

    }

}