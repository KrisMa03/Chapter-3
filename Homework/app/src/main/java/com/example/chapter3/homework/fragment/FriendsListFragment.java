package com.example.chapter3.homework.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.example.chapter3.homework.R;

public class FriendsListFragment extends Fragment {

    private Handler handler = new Handler();
    private View rootView;
    private LottieAnimationView lottieAnimationView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.friends_list, container, false);

        // Setup Lottie Animation View
        lottieAnimationView = rootView.findViewById(R.id.animation_view);
        lottieAnimationView.setVisibility(View.VISIBLE);
        fadeInAnimation(lottieAnimationView);  // 应用淡入效果
        lottieAnimationView.playAnimation();

        // After 5 seconds, apply fade out effect and then show the friend list
        handler.postDelayed(() -> {
            fadeOutAnimation(lottieAnimationView); // 应用淡出效果
        }, 5000);

        return rootView;
    }



    private void setupFriendList() {
        ListView listView = rootView.findViewById(R.id.lvItems);
        String[] friends = {"Friend 1", "Friend 2", "Friend 3", "Friend 4", "Friend 5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, friends);
        listView.setAdapter(adapter);
        listView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacksAndMessages(null); // Clean up handler
    }
    private void fadeInAnimation(View view) {
        AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(1000); // 动画时长 1000 毫秒（1秒）
        animation.setFillAfter(true); // 动画结束后保留结束状态
        view.startAnimation(animation);
    }

    private void fadeOutAnimation(View view) {
        AlphaAnimation animation = new AlphaAnimation(1.0f, 0.0f);
        animation.setDuration(1000); // 动画时长 1000 毫秒（1秒）
        animation.setFillAfter(true); // 动画结束后保留结束状态
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE); // 动画结束后设置视图为GONE
                setupFriendList(); // 开始设置好友列表
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        view.startAnimation(animation);
    }

}
