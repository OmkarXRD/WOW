package com.live.worldsocialintegrationapp.utils;

import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.live.worldsocialintegrationapp.ModelClasses.SendOtpRoot;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;

import java.io.PrintWriter;
import java.io.StringWriter;

public class CrashReportHandler {
//        extends AppCompatActivity implements Thread.UncaughtExceptionHandler {
//    private Context m_context;
//    private String liveId="";
//
//    public static void attach(Context context,String liveId) {
//        Thread.setDefaultUncaughtExceptionHandler(
//                new CrashReportHandler(context)
//        );
//
//    }
//
//    ///////////////////////////////////////////// implementation
//
//    private CrashReportHandler(Context context) {
//        m_context = context;
//    }
//
//    public void uncaughtException(Thread thread, Throwable exception) {
//
//        new Mvvm().endLiveCall(CrashReportHandler.this,App.getSharedpref().getString("liveId")).observe(this, new Observer<SendOtpRoot>() {
//            @Override
//            public void onChanged(SendOtpRoot sendOtpRoot) {
//                if(sendOtpRoot != null){
////                    Toast.makeText(m_context, "closed", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
////        StringWriter stackTrace = new StringWriter();
////        exception.printStackTrace(new PrintWriter(stackTrace));
//
//        //You will get call back here when app crashes.
//
////        // from RuntimeInit.crash()
////        Process.killProcess(Process.myPid());
////        System.exit(10);
//    }
//


}
