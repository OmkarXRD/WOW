package com.live.worldsocialintegrationapp.games;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.live.worldsocialintegrationapp.R;
public class WinnerScreen extends AppCompatActivity {


    @Override
    public void onBackPressed() {
    }


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.spin_activity_winner_screen);

    }
}
