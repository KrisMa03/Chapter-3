package com.example.chapter3.homework.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
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
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.friends_list, container, false);

        // Setup Lottie Animation View
        lottieAnimationView = rootView.findViewById(R.id.animation_view);
        lottieAnimationView.setVisibility(View.VISIBLE);
        fadeInAnimation(lottieAnimationView);  // 应用淡入效果
        lottieAnimationView.playAnimation();

        // Setup ListView
        listView = rootView.findViewById(R.id.lvItems);
        listView.setVisibility(View.INVISIBLE); // Initially set to invisible

        // After 5 seconds, apply fade out effect to lottieAnimationView and fade in effect to listView
        handler.postDelayed(() -> {
            fadeOutAnimation(lottieAnimationView); // 应用淡出效果
            listView.setVisibility(View.VISIBLE); // Set listView to visible before starting the fade in animation
            fadeInAnimation(listView); // 应用淡入效果
            setupFriendList(); // Fill the list after the animation starts
        }, 4000);

        return rootView;
    }

    private void setupFriendList() {
        String[] friends = {"Friend 1", "Friend 2", "Friend 3", "Friend 4", "Friend 5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, friends);
        listView.setAdapter(adapter);
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
        view.startAnimation(animation);
    }

}