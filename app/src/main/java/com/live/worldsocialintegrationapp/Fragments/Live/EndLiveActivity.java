package com.live.worldsocialintegrationapp.Fragments.Live;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.Activites.HomeActivity;
import com.live.worldsocialintegrationapp.databinding.ActivityEndLiveBinding;
import com.live.worldsocialintegrationapp.utils.App;

public class EndLiveActivity extends AppCompatActivity {

    ActivityEndLiveBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEndLiveBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        Glide.with(this).load(App.getSharedpref().getString("image")).into(binding.img);
        Intent intent = getIntent();
        String viwerCount = intent.getStringExtra("liveUserCount");

        binding.viwerCount.setText(viwerCount);

        binding.back.setOnClickListener(view -> {

            startActivity(new Intent(EndLiveActivity.this, HomeActivity.class));
            finish();

        });

    }
}