package com.live.worldsocialintegrationapp.Activites;

import static com.live.worldsocialintegrationapp.Adapters.MultiLiveVideoAdapter.liveType;
import static com.live.worldsocialintegrationapp.Fragments.LiveFreeThemeFragment.liveHostBackImg;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.live.worldsocialintegrationapp.ModelClasses.GeneratedIdClass;
import com.live.worldsocialintegrationapp.ModelClasses.SendOtpRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.agora.openvcall.ui.CallActivity;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstant;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.live.worldsocialintegrationapp.utils.CommonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SplashActivity extends AppCompatActivity {

    private static final int PERMISSION_ID = 44;
    public static double latitude, longitude;
    public FusedLocationProviderClient locationProviderClient;
    Animation animation;
    ImageView  image;
    String json_str = "{\"AD\":\"Europe\",\"AE\":\"Asia\",\"AF\":\"Asia\",\"AG\":\"North America\",\"AI\":\"North America\",\"AL\":\"Europe\",\"AM\":\"Asia\",\"AN\":\"North America\",\"AO\":\"Africa\",\"AQ\":\"Antarctica\",\"AR\":\"South America\",\"AS\":\"Australia\",\"AT\":\"Europe\",\"AU\":\"Australia\",\"AW\":\"North America\",\"AZ\":\"Asia\",\"BA\":\"Europe\",\"BB\":\"North America\",\"BD\":\"Asia\",\"BE\":\"Europe\",\"BF\":\"Africa\",\"BG\":\"Europe\",\"BH\":\"Asia\",\"BI\":\"Africa\",\"BJ\":\"Africa\",\"BM\":\"North America\",\"BN\":\"Asia\",\"BO\":\"South America\",\"BR\":\"South America\",\"BS\":\"North America\",\"BT\":\"Asia\",\"BW\":\"Africa\",\"BY\":\"Europe\",\"BZ\":\"North America\",\"CA\":\"North America\",\"CC\":\"Asia\",\"CD\":\"Africa\",\"CF\":\"Africa\",\"CG\":\"Africa\",\"CH\":\"Europe\",\"CI\":\"Africa\",\"CK\":\"Australia\",\"CL\":\"South America\",\"CM\":\"Africa\",\"CN\":\"Asia\",\"CO\":\"South America\",\"CR\":\"North America\",\"CU\":\"North America\",\"CV\":\"Africa\",\"CX\":\"Asia\",\"CY\":\"Asia\",\"CZ\":\"Europe\",\"DE\":\"Europe\",\"DJ\":\"Africa\",\"DK\":\"Europe\",\"DM\":\"North America\",\"DO\":\"North America\",\"DZ\":\"Africa\",\"EC\":\"South America\",\"EE\":\"Europe\",\"EG\":\"Africa\",\"EH\":\"Africa\",\"ER\":\"Africa\",\"ES\":\"Europe\",\"ET\":\"Africa\",\"FI\":\"Europe\",\"FJ\":\"Australia\",\"FK\":\"South America\",\"FM\":\"Australia\",\"FO\":\"Europe\",\"FR\":\"Europe\",\"GA\":\"Africa\",\"GB\":\"Europe\",\"GD\":\"North America\",\"GE\":\"Asia\",\"GF\":\"South America\",\"GG\":\"Europe\",\"GH\":\"Africa\",\"GI\":\"Europe\",\"GL\":\"North America\",\"GM\":\"Africa\",\"GN\":\"Africa\",\"GP\":\"North America\",\"GQ\":\"Africa\",\"GR\":\"Europe\",\"GS\":\"Antarctica\",\"GT\":\"North America\",\"GU\":\"Australia\",\"GW\":\"Africa\",\"GY\":\"South America\",\"HK\":\"Asia\",\"HN\":\"North America\",\"HR\":\"Europe\",\"HT\":\"North America\",\"HU\":\"Europe\",\"ID\":\"Asia\",\"IE\":\"Europe\",\"IL\":\"Asia\",\"IM\":\"Europe\",\"IN\":\"Asia\",\"IO\":\"Asia\",\"IQ\":\"Asia\",\"IR\":\"Asia\",\"IS\":\"Europe\",\"IT\":\"Europe\",\"JE\":\"Europe\",\"JM\":\"North America\",\"JO\":\"Asia\",\"JP\":\"Asia\",\"KE\":\"Africa\",\"KG\":\"Asia\",\"KH\":\"Asia\",\"KI\":\"Australia\",\"KM\":\"Africa\",\"KN\":\"North America\",\"KP\":\"Asia\",\"KR\":\"Asia\",\"KW\":\"Asia\",\"KY\":\"North America\",\"KZ\":\"Asia\",\"LA\":\"Asia\",\"LB\":\"Asia\",\"LC\":\"North America\",\"LI\":\"Europe\",\"LK\":\"Asia\",\"LR\":\"Africa\",\"LS\":\"Africa\",\"LT\":\"Europe\",\"LU\":\"Europe\",\"LV\":\"Europe\",\"LY\":\"Africa\",\"MA\":\"Africa\",\"MC\":\"Europe\",\"MD\":\"Europe\",\"ME\":\"Europe\",\"MG\":\"Africa\",\"MH\":\"Australia\",\"MK\":\"Europe\",\"ML\":\"Africa\",\"MM\":\"Asia\",\"MN\":\"Asia\",\"MO\":\"Asia\",\"MP\":\"Australia\",\"MQ\":\"North America\",\"MR\":\"Africa\",\"MS\":\"North America\",\"MT\":\"Europe\",\"MU\":\"Africa\",\"MV\":\"Asia\",\"MW\":\"Africa\",\"MX\":\"North America\",\"MY\":\"Asia\",\"MZ\":\"Africa\",\"NA\":\"Africa\",\"NC\":\"Australia\",\"NE\":\"Africa\",\"NF\":\"Australia\",\"NG\":\"Africa\",\"NI\":\"North America\",\"NL\":\"Europe\",\"NO\":\"Europe\",\"NP\":\"Asia\",\"NR\":\"Australia\",\"NU\":\"Australia\",\"NZ\":\"Australia\",\"OM\":\"Asia\",\"PA\":\"North America\",\"PE\":\"South America\",\"PF\":\"Australia\",\"PG\":\"Australia\",\"PH\":\"Asia\",\"PK\":\"Asia\",\"PL\":\"Europe\",\"PM\":\"North America\",\"PN\":\"Australia\",\"PR\":\"North America\",\"PS\":\"Asia\",\"PT\":\"Europe\",\"PW\":\"Australia\",\"PY\":\"South America\",\"QA\":\"Asia\",\"RE\":\"Africa\",\"RO\":\"Europe\",\"RS\":\"Europe\",\"RU\":\"Europe\",\"RW\":\"Africa\",\"SA\":\"Asia\",\"SB\":\"Australia\",\"SC\":\"Africa\",\"SD\":\"Africa\",\"SE\":\"Europe\",\"SG\":\"Asia\",\"SH\":\"Africa\",\"SI\":\"Europe\",\"SJ\":\"Europe\",\"SK\":\"Europe\",\"SL\":\"Africa\",\"SM\":\"Europe\",\"SN\":\"Africa\",\"SO\":\"Africa\",\"SR\":\"South America\",\"ST\":\"Africa\",\"SV\":\"North America\",\"SY\":\"Asia\",\"SZ\":\"Africa\",\"TC\":\"North America\",\"TD\":\"Africa\",\"TF\":\"Antarctica\",\"TG\":\"Africa\",\"TH\":\"Asia\",\"TJ\":\"Asia\",\"TK\":\"Australia\",\"TM\":\"Asia\",\"TN\":\"Africa\",\"TO\":\"Australia\",\"TR\":\"Asia\",\"TT\":\"North America\",\"TV\":\"Australia\",\"TW\":\"Asia\",\"TZ\":\"Africa\",\"UA\":\"Europe\",\"UG\":\"Africa\",\"US\":\"North America\",\"UY\":\"South America\",\"UZ\":\"Asia\",\"VC\":\"North America\",\"VE\":\"South America\",\"VG\":\"North America\",\"VI\":\"North America\",\"VN\":\"Asia\",\"VU\":\"Australia\",\"WF\":\"Australia\",\"WS\":\"Australia\",\"YE\":\"Asia\",\"YT\":\"Africa\",\"ZA\":\"Africa\",\"ZM\":\"Africa\",\"ZW\":\"Africa\"}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        image = findViewById(R.id.wows);
        animation = AnimationUtils.loadAnimation(this,R.anim.middle_animation_code);
        image.setAnimation(animation);
        locationProviderClient = LocationServices.getFusedLocationProviderClient(SplashActivity.this);
      //  App.getSharedpref().getString("ContinentName");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (App.getSharedpref().getString(AppConstant.SESSION).equalsIgnoreCase("1")){
                    banUserStatusCheck();
                } else {
                    Intent intent=new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        }, 2000);
    }

    public void onResume() {
        super.onResume();
        getLastLocation();
    }

    private void banUserStatusCheck() {
        Log.i("SPlacsh","zzzzzzzzzzzzzzzzzz "+AppConstants.USER_ID);
        new Mvvm().getGeneratedIdClassLiveData(SplashActivity.this,AppConstants.USER_ID).observe(SplashActivity.this, new Observer<GeneratedIdClass>() {
            @Override
            public void onChanged(GeneratedIdClass generatedIdClass) {
                if (generatedIdClass !=null){
                    if (generatedIdClass.getSuccess().equalsIgnoreCase("1")){
                        if (generatedIdClass.getStatus().equalsIgnoreCase("1")){
                            Intent intent=new Intent(SplashActivity.this, MainActivity.class);
                            App.getSharedpref().saveString("bannedId",generatedIdClass.getMessage());
                            startActivity(intent);
                            finish();
                        } else if (generatedIdClass.getStatus().equalsIgnoreCase("2")) {
                            finishAffinity();
                        } else if (generatedIdClass.getStatus().equalsIgnoreCase("3")) {
                            finishAffinity();
                        }
                    }else {
                        Intent intent=new Intent(SplashActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else {
                    Log.i("SPlacsh","zzzzzzzzzzzzzzzzzz");
                    Toast.makeText(SplashActivity.this, "Technical issue...", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(
                SplashActivity.this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_ID
        );
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) SplashActivity.this.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }
    private void getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                if (ActivityCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                locationProviderClient.getLastLocation().addOnCompleteListener(
                        task -> {
                            Location location = task.getResult();
                            if (location == null) {
                                requestNewLocationData();
                            } else {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                                App.getSharedpref().saveString("latitude", String.valueOf(latitude));
                                App.getSharedpref().saveString("longitude", String.valueOf(longitude));
                                getCompleteAddressString(latitude, longitude);
                            }
                        }
                );
            } else {
                Toast.makeText(SplashActivity.this, "Turn on location", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            requestPermissions();
        }
    }
    @SuppressLint("MissingPermission")
    private void requestNewLocationData() {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(0);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        locationProviderClient = LocationServices.getFusedLocationProviderClient(SplashActivity.this);
        locationProviderClient.requestLocationUpdates(
                mLocationRequest, mLocationCallback,
                Looper.myLooper()
        );
    }

    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            latitude = mLastLocation.getLatitude();
            longitude = mLastLocation.getLongitude();
            Geocoder geocoder;
            List<Address> addresses = null;
            geocoder = new Geocoder(SplashActivity.this, Locale.getDefault());
            try {
                addresses = geocoder.getFromLocation(latitude, longitude, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!addresses.isEmpty()) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");
                for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append(" ");
                }
            }
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        }
    }

    private void getCompleteAddressString(double LATITUDE, double LONGITUDE) {

        Geocoder geocoder;
        List<Address> addresses = null;
        geocoder = new Geocoder(SplashActivity.this, Locale.getDefault());
        try
        {
            addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (addresses != null)
        {
            Address returnedAddress = addresses.get(0);
            StringBuilder strReturnedAddress = new StringBuilder("");
            for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++)
            {
                strReturnedAddress.append(returnedAddress.getAddressLine(i)).append(" ");
            }

        } else
        {
        }
        try {
            JSONObject jsonObject = new JSONObject(json_str);
            Geocoder geocoderr = new Geocoder(this, Locale.ENGLISH);

            List<Address> addressess = geocoderr.getFromLocation(latitude, longitude, 1);

            if (addressess.size() > 0) {

                Address fetchedAddress = addresses.get(0);

                // getCountryCode from Address
                String countryCode = fetchedAddress.getCountryCode();
                String countryName = fetchedAddress.getCountryName();

                // get continentName here
                String continentName = jsonObject.getString(countryCode);
                App.getSharedpref().saveString("continentName",continentName);
                App.getSharedpref().saveString("countryName",countryName);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}