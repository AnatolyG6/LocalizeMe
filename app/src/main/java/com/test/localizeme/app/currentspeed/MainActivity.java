package com.test.localizeme.app.currentspeed;

import android.Manifest;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.test.localizeme.R;
import com.test.localizeme.databinding.ActivityMainBinding;
import com.test.localizeme.di.DaggerApplicationComponent;
import com.test.localizeme.di.MainActivityModule;
import java.util.List;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    @Inject CurrentSpeedViewModel viewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerApplicationComponent.builder()
                .mainActivityModule(new MainActivityModule(this))
                .build().inject(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initScreen();
        setupDataObserver();
        checkLocationPermission(this);
    }


    private void initScreen() {
        binding.mainCurrentSpeed.setText(getString(R.string.speed_value, "0"));
    }

    private void setupDataObserver() {
        viewModel.mainActivityContent.observe(
                this,
                mainContent -> {
                    if(mainContent != null)
                    binding.mainCurrentSpeed.setText(
                            Integer.toString(mainContent.getUserCurrentSpeed())
                    );
                }
        );
    }

    private void checkLocationPermission(MainActivity mainActivity) {
        TedPermission
                .with(this)
                .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION)
                .setPermissionListener(
                        new PermissionListener() {
                            @Override
                            public void onPermissionGranted() {
                                viewModel.initView(mainActivity);
                            }

                            @Override
                            public void onPermissionDenied(List<String> deniedPermissions) {
                                Toast.makeText(
                                        mainActivity,
                                        R.string.permission_denied_message,
                                        Toast.LENGTH_SHORT
                                ).show();
                                checkLocationPermission(mainActivity);
                            }
                        }
                )
                .check();
    }
}