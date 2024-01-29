package com.live.worldsocialintegrationapp.Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.live.worldsocialintegrationapp.ModelClasses.GeneratedIdClass;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.live.worldsocialintegrationapp.utils.CommonUtils;

public class IdBannedActivity extends AppCompatActivity {
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_banned);
        text=findViewById(R.id.text);
        //Toast.makeText(this, "UserIDD: " + App.getSharedpref().getString("userId"), Toast.LENGTH_SHORT).show();
        try {
            new Mvvm().getGeneratedIdClassLiveData(IdBannedActivity.this, App.getSharedpref().getString("userId")).observe(IdBannedActivity.this, new Observer<GeneratedIdClass>() {
                @Override
                public void onChanged(GeneratedIdClass generatedIdClass) {
                    if (generatedIdClass.getSuccess().equalsIgnoreCase("1")){
                        if (generatedIdClass.getStatus().equalsIgnoreCase("1")){
                            //Toast.makeText(IdBannedActivity.this, ""+generatedIdClass.getMessage(), Toast.LENGTH_SHORT).show();
                            String msg =  generatedIdClass.getMessage();
                            text.setText(msg);
                        } else if (generatedIdClass.getStatus().equalsIgnoreCase("2")) {
                            //Toast.makeText(IdBannedActivity.this, ""+generatedIdClass.getMessage(), Toast.LENGTH_SHORT).show();
                            finishAffinity();
                        } else if (generatedIdClass.getStatus().equalsIgnoreCase("3")) {
                            //Toast.makeText(IdBannedActivity.this, ""+generatedIdClass.getMessage(), Toast.LENGTH_SHORT).show();
                            finishAffinity();
                        }
                    }else {
                        //Toast.makeText(IdBannedActivity.this, "22", Toast.LENGTH_SHORT).show();
                        //Toast.makeText(IdBannedActivity.this, ""+generatedIdClass.getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            });
        }catch(Exception e){

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}