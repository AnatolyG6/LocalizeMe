package com.test.localizeme.app.averagespeed;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.test.localizeme.R;
import com.test.localizeme.databinding.ActivityAverageSpeedBinding;


import javax.inject.Inject;

public class AverageSpeedActivity extends AppCompatActivity {
    public static final String AVERAGE_SPEED_KEY = "average_speed_key";
    public static final String BIRD_FLY_SPEED_KEY = "bird_fly_speed_activity";
    public static final int AVERAGE_SPEED_ACTIVITY_KEY = 12314;

    @Inject
    AverageSpeedViewModel viewModel;
    private ActivityAverageSpeedBinding binding;

    public static Intent createIntent(int averageSpeed, int birdFlySpeed, Context context) {
        Intent intent = new Intent(context, AverageSpeedActivity.class);
        intent.putExtra(AVERAGE_SPEED_KEY, averageSpeed);
        intent.putExtra(BIRD_FLY_SPEED_KEY, birdFlySpeed);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAverageSpeedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
        bindEvent();
    }

    private void initView() {
        Intent intent = getIntent();
        binding.averageSpeedValue.setText(
                getString(
                        R.string.speed_value,
                        Integer.toString(intent.getIntExtra(AVERAGE_SPEED_KEY, 0))
                )
        );

        binding.birdFlySpeedValue.setText(
                getString(
                        R.string.speed_value,
                        Integer.toString(intent.getIntExtra(BIRD_FLY_SPEED_KEY, 0))
                )
        );
    }

    private void bindEvent() {
        binding.averageSpeedToolbar.setNavigationOnClickListener( useless -> finish());
    }
}
