package com.live.worldsocialintegrationapp.utils;

import static android.content.Context.AUDIO_SERVICE;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.firebase.database.annotations.NotNull;
import com.live.worldsocialintegrationapp.R;
import com.opensource.svgaplayer.SVGACallback;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGADynamicEntity;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class CommonUtils {

    private Activity activity = new Activity();

        public static boolean isNetworkConnected(Context context) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            return cm.getActiveNetworkInfo() != null;
        }


        private static AlertDialog alertDialog;

        public static void openDialog(Activity activity) {
            final AlertDialog.Builder ab = new AlertDialog.Builder(activity);

            View sView = activity.getLayoutInflater().inflate(R.layout.dialog_progress, null);
            ab.setView(sView);
            alertDialog = ab.create();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            alertDialog.show();
            alertDialog.setCanceledOnTouchOutside(false);
        }

        public static void dismissDialog() {
            alertDialog.dismiss();
        }


        public static boolean isValidEmail(String email) {
            String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
            boolean flag = false;

            if (email.matches(emailPattern)) {
                flag = true;
            }
            return flag;

        }

        public static boolean isValidMobile(String phone) {
            return android.util.Patterns.PHONE.matcher(phone).matches();
        }

        private static ProgressDialog progressDialog;

        public static void showProgress(Activity activity, String message) {
            progressDialog = new ProgressDialog(activity);
            progressDialog.setMessage(message);
            progressDialog.setCanceledOnTouchOutside(true);
            progressDialog.show();

        }

        public static void dismissProgress() {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }else {
            }
        }

    public static void disableBottomNavigation(Activity activity) {

        View view1  = activity.findViewById( R.id.bottom_lay);
        view1.setVisibility( View.GONE );
    }
    public static RequestBody getRequestBodyText(String data) {

        return RequestBody.create(MediaType.parse("text/plain"), data);

    }
    public static MultipartBody.Part  getFileData(String path, String parameter) {

        if (path != null) {

            File file = new File(path);

            final RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

            return MultipartBody.Part.createFormData(parameter, file.getName(), requestFile);

        } else {

            final RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), "");

            return MultipartBody.Part.createFormData(parameter, "", requestFile);

        }
    }
    public static MultipartBody.Part[]  getFileData2(String path, String parameter) {

        if (path != null) {

            File file = new File(path);

            final RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

            return new MultipartBody.Part[]{MultipartBody.Part.createFormData(parameter, file.getName(), requestFile)};

        } else {

            final RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), "");

            return new MultipartBody.Part[]{MultipartBody.Part.createFormData(parameter, "", requestFile)};

        }
    }
    public static void visibileBottomNavigation(Activity activity) {

        View view1  = activity.findViewById( R.id.bottom_lay );

        view1.setVisibility( View.VISIBLE );
    }

    //this method duration of song covert into mintues
    public static String formatDuration(long duration) {
        long minutes = TimeUnit.MINUTES.convert(duration, TimeUnit.MILLISECONDS);
        long seconds = TimeUnit.SECONDS.convert(duration, TimeUnit.MILLISECONDS)
                - minutes * TimeUnit.SECONDS.convert(1, TimeUnit.MINUTES);
        return String.format("%02d:%02d", minutes, seconds);
    }

    public static int getAge(int year, int month, int dayOfMonth) {
            int age=0;
            try {
                age =Period.between(
                        LocalDate.of(year, month, dayOfMonth),
                        LocalDate.now()
                ).getYears();
            }catch (Exception e){

            }

            return age;
    }


    public static String ageCalcualte(String age){
        int age1=0;
        String year="",month="",date="";

        for(int i=0;i<age.length();i++){
            if(i==0 || i==1){
                year += age.charAt(i);
            }else if(i==3 || i==4){
                month += age.charAt(i);
            }else if(i==6 || i==7){
                date += age.charAt(i);
            }
        }

        if(!month.equalsIgnoreCase("00") && !date.equalsIgnoreCase("00")){
            if(year.length()==2){
                if(Integer.parseInt(year)<=22  && Integer.parseInt(year)>=0){
                    age1 = getAge(Integer.parseInt("20"+year),Integer.parseInt(month),Integer.parseInt(date));
                }else{
                    age1 = getAge(Integer.parseInt("19"+year),Integer.parseInt(month),Integer.parseInt(date));
                }

            } else if(year.length()==4){
                age1 = getAge(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(date));
            }
        }


        return String.valueOf(age1);
    }


    public static void  setAnimation(Context context, String image, SVGAImageView imageView) {

        SVGAParser parser = new SVGAParser(context);

        try {
            parser.decodeFromURL(new URL(image), new SVGAParser.ParseCompletion() {

                @Override
                public void onComplete(@com.google.firebase.database.annotations.NotNull SVGAVideoEntity videoItem) {

                    SVGADrawable drawable = new SVGADrawable(videoItem);
                    imageView.setImageDrawable(drawable);
                    imageView.startAnimation();
                }

                @Override
                public void onError() {

                }
            }, new SVGAParser.PlayCallback() {

                @Override
                public void onPlay(@NonNull List<? extends File> list) {

                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    public static void setAnimationTwo(Context context, String image, SVGAImageView imageView) {

        SVGAParser    parser = new SVGAParser(context);

        try {
            parser.decodeFromURL(new URL(image), new SVGAParser.ParseCompletion() {

                @Override
                public void onComplete(@NotNull SVGAVideoEntity videoItem) {

//                    videoItem.getVideoSize();
//                    imageView.setVideoItem(videoItem);
//                    imageView.startAnimation();

                    SVGADynamicEntity dynamicEntity = new SVGADynamicEntity();
                    dynamicEntity.setDynamicImage(image, "91"); // Here is the KEY implementation.
//                   */
                    SVGADrawable drawable = new SVGADrawable(videoItem);
                    imageView.setImageDrawable(drawable);
                    imageView.startAnimation();

                    imageView.setCallback(new SVGACallback() {
                        @Override
                        public void onPause() {

                        }

                        @Override
                        public void onFinished() {

                        }

                        @Override
                        public void onRepeat() {

                            //SVGASoundManager.INSTANCE.release();

                            videoItem.setMovieItem(null);
                            videoItem.clear();


                            drawable.clear();
                            imageView.clear();
                            imageView.setClearsAfterDetached(true);

                            imageView.setVideoItem(null);
                            imageView.setImageDrawable(null);





                        }

                        @Override
                        public void onStep(int i, double v) {

                        }
                    });

                }

                @Override
                public void onError() {

                }
            },null);
        } catch (Exception e) {
            //Toast.makeText(context, "aa" + e.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }


}
