package com.live.worldsocialintegrationapp.Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.View;

import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.databinding.ActivityNotificationBinding;

public class NotificationActivity extends AppCompatActivity {

    public static ActivityNotificationBinding binding;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.white));
        getWindow().getDecorView().setSystemUiVisibility(getWindow().getDecorView().getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        binding = ActivityNotificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        navController = Navigation.findNavController(this, R.id.nav_notification);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}