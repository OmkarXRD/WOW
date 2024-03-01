package com.live.worldsocialintegrationapp.games;

import android.os.Bundle;

import androidx.annotation.Keep;
import androidx.appcompat.app.AppCompatActivity;
import com.live.worldsocialintegrationapp.R;
@Keep
public class LosserScreen extends AppCompatActivity {

    @Override
    public void onBackPressed() {
    }


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.spin_activity_losser_screen);

    }
}
