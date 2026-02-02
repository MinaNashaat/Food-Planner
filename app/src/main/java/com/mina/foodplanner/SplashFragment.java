package com.mina.foodplanner;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashFragment extends Fragment {

    TextView title, subtitle, caption;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title = view.findViewById(R.id.title);
        subtitle = view.findViewById(R.id.subtitle);
        caption = view.findViewById(R.id.caption);
        Animation anim = AnimationUtils.loadAnimation(view.getContext(),R.anim.fade_in);
        title.setAnimation(anim);
        subtitle.setAnimation(anim);
        caption.setAnimation(anim);



        new Handler(Looper.getMainLooper()).postDelayed(() -> {

            NavController navController = NavHostFragment.findNavController(SplashFragment.this);

            NavOptions navOptions = new NavOptions.Builder()
                    .setPopUpTo(R.id.splashFragment, true)
                    .build();

            navController.navigate(
                    R.id.action_splashFragment_to_loginFragment,
                    null,
                    navOptions
            );

        }, 4000);


    }
}