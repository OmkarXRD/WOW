package com.live.worldsocialintegrationapp.Activites;

import static androidx.fragment.app.FragmentManager.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.live.worldsocialintegrationapp.Fragments.ChatsFragments.ChatFragment;
import com.live.worldsocialintegrationapp.Fragments.Home.HomeFragment;
import com.live.worldsocialintegrationapp.Fragments.Profile.EditProfileSection.EditProfileScreen;
import com.live.worldsocialintegrationapp.ModelClasses.AllPopularUsers.GetAllPopularRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GeneratedIdClass;
import com.live.worldsocialintegrationapp.ModelClasses.SendOtpRoot;
import com.live.worldsocialintegrationapp.ModelClasses.SingleLiveUserRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.agora.openvcall.ui.CallActivity;
import com.live.worldsocialintegrationapp.databinding.ActivityHomeBinding;
import com.live.worldsocialintegrationapp.room.AppDatabase;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.live.worldsocialintegrationapp.utils.CommonUtils;

import java.util.List;
public class HomeActivity extends AppCompatActivity {
    public static final String data_key = "data_key";  //this for notification
    public static ActivityHomeBinding binding;
    NavController navController;
    private static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    public static String latitude, longitude;
    String userId, userName, userImage;
    Dialog dia;
//  public static int screenStatus = 0;

    private Mvvm homeActivityViewModel;

    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInOptions gso;
    private AppDatabase database;

    //public String prefix = "https://worldsocialintegrationapp.page.link/4Yif";
    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference ChatRequestCountRef = firebaseDatabase.getReference().child("ChatRequestCount");
    private final DatabaseReference liveUsersRef = firebaseDatabase.getReference().child("liveUsersRef");
    private final DatabaseReference cleanUserCommentsRef = firebaseDatabase.getReference().child("cleanUserCommentsRef");
    private final DatabaseReference lockRef = firebaseDatabase.getReference().child("lockRef");
    private final DatabaseReference banChatRef = firebaseDatabase.getReference().child("banChatRef");
    private final DatabaseReference muteMicRef = firebaseDatabase.getReference().child("muteMicRef");
    private final DatabaseReference LuckBagRef = firebaseDatabase.getReference().child("LuckBagRef");
    private final DatabaseReference luckyDivideUsersRef = firebaseDatabase.getReference().child("luckyDivideUsersRef");
    private final DatabaseReference lockSeat = firebaseDatabase.getReference().child("lockSeat");
    private final DatabaseReference emojiRef = firebaseDatabase.getReference().child("emojiRef");
    private final DatabaseReference userLiveAnnouncement = firebaseDatabase.getReference().child("userLiveAnnouncement");
    private final DatabaseReference ref = firebaseDatabase.getReference().child("userInfo");
    private final DatabaseReference onlineUsers = firebaseDatabase.getReference().child("onlineUsers");

    private final DatabaseReference dynamicLinkKeys = firebaseDatabase.getReference().child("dynamicLinkKeys");
    private AppDatabase roomDatabase;
    boolean shouldChangeStatusBarTintToDark = false;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,
            Manifest.permission.BLUETOOTH_SCAN,
            Manifest.permission.BLUETOOTH_CONNECT,
            Manifest.permission.BLUETOOTH_PRIVILEGED
    };
    private NavController.OnDestinationChangedListener navControllerListener;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("AppFlow", "On Create of Home Activity");
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        homeActivityViewModel = new ViewModelProvider(this).get(Mvvm.class);

// Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(HomeActivity.this, gso);
        banUserStatusCheck();

        getWindow().getDecorView().setSystemUiVisibility(getWindow().getDecorView().getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        roomDatabase = AppDatabase.getInstance(HomeActivity.this);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        checkPermissions();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            if (shouldChangeStatusBarTintToDark) {
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                // We want to change tint color to white again.
                // You can also record the flags in advance so that you can turn UI back completely if
                // you have set other flags before, such as translucent or full screen.
                decor.setSystemUiVisibility(0);
            }
        }

        setContentView(binding.getRoot());
        App.getSharedpref().saveString("exit", "1");

        FirebaseCrashlytics.getInstance().setUserId(AppConstants.USER_ID); //firebase crash
        onlineUsers.child(AppConstants.USER_ID).setValue(true);

        FirebaseDynamicLinks.getInstance()
                .getDynamicLink(getIntent())
                .addOnSuccessListener(this, new OnSuccessListener<PendingDynamicLinkData>() {
                    @Override
                    public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
                        // Get deep link from result (may be null if no link is found)
                        Uri deepLink = null;
                        if (pendingDynamicLinkData != null) {
                            deepLink = pendingDynamicLinkData.getLink();
//                            Toast.makeText(HomeActivity.this, "receiveLinkE " + deepLink.getQueryParameter("userId"), Toast.LENGTH_SHORT).show();
//                            Toast.makeText(HomeActivity.this, "key " + deepLink.getQueryParameter("key"), Toast.LENGTH_SHORT).show();
                            openLive(deepLink.getQueryParameter("userId"),"");
//                           String liveKey = deepLink.getQueryParameter("key");
//                            String userId1 = deepLink.getQueryParameter("userId");
//
//                            if (userId1 != null && liveKey != null) {
//
//                                dynamicLinkKeys.child(userId1).addListenerForSingleValueEvent(new ValueEventListener() {
//                                    @Override
//                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                        if (snapshot.exists()) {
//                                            String key = snapshot.getValue().toString();
//
//                                            if ( key != null && key.equalsIgnoreCase(liveKey)) {
//                                                openLive(userId1,liveKey);
//                                            }
//                                        } else {
//                                            Toast.makeText(HomeActivity.this, "not exits", Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//
//                                    @Override
//                                    public void onCancelled(@NonNull DatabaseError error) {
//
//                                    }
//                                });
//
//                            }
                        }
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("HOMEACTIVITY", "getDynamicLink:onFailure", e);
                    }
                });

        removeLiveUser();

        //for backPress check if we back it direct open homefragment
        //for backPressed 0

//        ChatFragment.backPressed = 0;

//        userImage = App.getSharedpref().getString("image");
        userName = App.getSharedpref().getString("name");
//        userId = App.getSharedpref().getString("userId");
        navController = Navigation.findNavController(this, R.id.nav_home);

        binding.bottomLay.homeFragment.setImageResource(R.drawable.icon_home_ftr_activesvg);
        binding.bottomLay.secondMain.setImageResource(R.drawable.icon_explore_ftsvgr);
        binding.bottomLay.bottomMessage.setImageResource(R.drawable.chattt);
        binding.bottomLay.bottomProfile.setImageResource(R.drawable.icon_profile_ftr);
        setBottomTabs();
      //  getCurrentLatLng();

        if (userName.isEmpty()) {
            setUserProfile();
        } else {
        }
        chatRequestCount();

        //Implemented by #007
        //Checking if user is navigating back to homeFragment from chatScreen or any other screen
        NavController navController = Navigation.findNavController(HomeActivity.this, R.id.nav_home);
        navControllerListener = (controller, destination, arguments) -> {
            if (destination.getId() == R.id.homeFragment) {
                updateBottomBar();
            }
        };
        navController.addOnDestinationChangedListener(navControllerListener);


    }

    //method to update the bottom bar by #007
    private void updateBottomBar() {
        binding.bottomLay.homeFragment.setImageResource(R.drawable.icon_home_ftr_activesvg);
        binding.bottomLay.secondMain.setImageResource(R.drawable.icon_explore_ftsvgr);
        binding.bottomLay.bottomMessage.setImageResource(R.drawable.chattt);
        binding.bottomLay.bottomProfile.setImageResource(R.drawable.icon_profile_ftr);

    }

    private void banUserStatusCheck() {
        String id = App.getSharedpref().getString("userId");
        homeActivityViewModel.getGeneratedIdClassLiveData(HomeActivity.this,AppConstants.USER_ID).observe(HomeActivity.this, new Observer<GeneratedIdClass>() {
            @Override
            public void onChanged(GeneratedIdClass generatedIdClass) {
                if (generatedIdClass.getSuccess().equalsIgnoreCase("1")){
                    if (generatedIdClass.getStatus().equalsIgnoreCase("1")){
                     //   Toast.makeText(HomeActivity.this, ""+generatedIdClass.getMessage(), Toast.LENGTH_SHORT).show();
                        if (id.length() == 0) {
                         //   Toast.makeText(HomeActivity.this, "user id null", Toast.LENGTH_SHORT).show();
                        } else {
                            homeActivityViewModel.logoutUser(HomeActivity.this, id).observe(HomeActivity.this, new Observer<SendOtpRoot>() {
                                @Override
                                public void onChanged(SendOtpRoot sendOtpRoot) {

                                    if (sendOtpRoot.getSuccess().equalsIgnoreCase("1")) {

                                        App.getSharedpref().clearPreferences();

                                        if(mGoogleSignInClient != null){
                                            mGoogleSignInClient.signOut();
                                        }
                                        disconnectFromFacebook();

                                        AppConstants.USER_ID = "";
                                        startActivity(new Intent(HomeActivity.this, SplashActivity.class));
                                        finish();
                                    } else {
                                    }
                                }

                            });
                        }
                        finish();
                    } else if (generatedIdClass.getStatus().equalsIgnoreCase("2")) {
                        finishAffinity();
                    } else if (generatedIdClass.getStatus().equalsIgnoreCase("3")) {
                        finishAffinity();
                    }
                }else {
                    //Toast.makeText(HomeActivity.this, ""+generatedIdClass.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void disconnectFromFacebook() {

        if (AccessToken.getCurrentAccessToken() == null) {
            return; // already logged out
        }

        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest
                .Callback() {
            @Override
            public void onCompleted(@NonNull GraphResponse graphResponse) {
                LoginManager.getInstance().logOut();
            }
        }).executeAsync();
    }


    private void checkPermissions(){
        int permission1 = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permission2 = ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN);
        if (permission1 != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    this,
                    PERMISSIONS_STORAGE,
                    1
            );
        }
//        else if (permission2 != PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(
//                    this,
//                    PERMISSIONS_LOCATION,
//                    1
//            );
//        }
    }
    @SuppressLint("ObsoleteSdkInt")
    private void setBottomTabs() {


        binding.bottomLay.homeFragmentLlayout.setOnClickListener(view -> {
            boolean shouldChangeStatusBarTintToDark = false;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                View decor = getWindow().getDecorView();
                if (shouldChangeStatusBarTintToDark) {
                    decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                } else {
                    decor.setSystemUiVisibility(0);
                }
            }

            Navigation.findNavController(this, R.id.nav_home).navigate(R.id.homeFragment);
            binding.bottomLay.homeFragment.setImageResource(R.drawable.icon_home_ftr_activesvg);
            binding.bottomLay.secondMain.setImageResource(R.drawable.icon_explore_ftsvgr);
            binding.bottomLay.bottomMessage.setImageResource(R.drawable.chattt);
            binding.bottomLay.bottomProfile.setImageResource(R.drawable.icon_profile_ftr);

        });

        binding.bottomLay.secondMainLlayout.setOnClickListener(view2 -> {

            boolean shouldChangeStatusBarTintToDark = false;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                View decor = getWindow().getDecorView();
                if (shouldChangeStatusBarTintToDark) {
                    decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                } else {
                    decor.setSystemUiVisibility(0);
                }
            }

            Navigation.findNavController(this, R.id.nav_home).navigate(R.id.secondMainFragment);
            binding.bottomLay.homeFragment.setImageResource(R.drawable.icon_home_ftr);
            binding.bottomLay.secondMain.setImageResource(R.drawable.icon_explore_ftr);
            binding.bottomLay.bottomMessage.setImageResource(R.drawable.chattt);
            binding.bottomLay.bottomProfile.setImageResource(R.drawable.icon_profile_ftr);
        });

        binding.bottomLay.bottomProfileLLayout.setOnClickListener(view -> {

            boolean shouldChangeStatusBarTintToDark = false;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                View decor = getWindow().getDecorView();
                if (shouldChangeStatusBarTintToDark) {
                    decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                } else {
                    decor.setSystemUiVisibility(0);
                }
            }
            Navigation.findNavController(this, R.id.nav_home).navigate(R.id.profileMainFragment);
            binding.bottomLay.homeFragment.setImageResource(R.drawable.icon_home_ftr);
            binding.bottomLay.secondMain.setImageResource(R.drawable.icon_explore_ftsvgr);
            binding.bottomLay.bottomMessage.setImageResource(R.drawable.chattt);
            binding.bottomLay.bottomProfile.setImageResource(R.drawable.icon_profile_ftr_active);
        });

        binding.bottomLay.bottomMultiLive.setOnClickListener(view -> {

            boolean shouldChangeStatusBarTintToDark = false;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                View decor = getWindow().getDecorView();
                if (shouldChangeStatusBarTintToDark) {
                    decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                } else {
                    decor.setSystemUiVisibility(0);
                }
            }

//            hitGenerateTokenApi();
            binding.bottomLay.homeFragment.setImageResource(R.drawable.icon_home_ftr);
            binding.bottomLay.secondMain.setImageResource(R.drawable.icon_explore_ftsvgr);
            binding.bottomLay.bottomMessage.setImageResource(R.drawable.chattt);
            binding.bottomLay.bottomProfile.setImageResource(R.drawable.icon_profile_ftr);
        });

        binding.bottomLay.bottomMessageLlayout.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putInt("backPressed", 0);
            Navigation.findNavController(this, R.id.nav_home).navigate(R.id.chatFragment, bundle);
//            screenStatus = 0;

            binding.bottomLay.homeFragment.setImageResource(R.drawable.icon_home_ftr);
            binding.bottomLay.secondMain.setImageResource(R.drawable.icon_explore_ftsvgr);
            binding.bottomLay.bottomMessage.setImageResource(R.drawable.bubble_message_color_full_img);
            binding.bottomLay.bottomProfile.setImageResource(R.drawable.icon_profile_ftr);
            boolean shouldChangeStatusBarTintToDark = true;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                View decor = getWindow().getDecorView();
                if (shouldChangeStatusBarTintToDark) {
                    decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                } else {
                    decor.setSystemUiVisibility(0);
                }
            }

        });

    }


    private void getCurrentLatLng() {
        locationManager = (LocationManager) HomeActivity.this.getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            OnGPS();
        } else {
          //  getLocation();
        }
    }

    private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                HomeActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(HomeActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if (locationGPS != null) {
                double lat = locationGPS.getLatitude();
                double longi = locationGPS.getLongitude();
                latitude = String.valueOf(lat);
                longitude = String.valueOf(longi);
                App.getSharedpref().saveString("latitude", latitude);
                App.getSharedpref().saveString("longitude", longitude);
            } else {
                //Toast.makeText(HomeActivity.this, "Unable to find location.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setUserProfile() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setMessage("Please Edit Your Profile First!");
        builder.setTitle("Profile");
        builder.setCancelable(false);

        builder.setPositiveButton("Next", (DialogInterface.OnClickListener) (dialog, which) -> {

            Bundle bundle = new Bundle();
            bundle.putInt("setFirstTimeProfile", 1);
            Navigation.findNavController(findViewById(R.id.nav_home)).navigate(R.id.editProfileScreen, bundle);
        });

//        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> { dialog.cancel(); });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        Button buttonPositive = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        buttonPositive.setTextColor(ContextCompat.getColor(this, R.color.green));
    }

    @Override
    protected void onDestroy() {
//        Toast.makeText(this, "destroyhomeactivity", Toast.LENGTH_SHORT).show();
//        removeLiveUser();


        super.onDestroy();
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.nav_home);
        if (fragment instanceof NavHostFragment) {
            NavController navController = ((NavHostFragment) fragment).getNavController();
            navController.removeOnDestinationChangedListener(navControllerListener);
        }
        onlineUsers.child(AppConstants.USER_ID).removeValue();
        finishAffinity();
        binding = null;


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //      //  finishAffinity();
//
////        if (screenStatus == 1) {
////
////        } else {
////            Navigation.findNavController(this, R.id.nav_home).navigate(R.id.homeFragment);
////
////            binding.bottomLay.homeFragment.setImageResource(R.drawable.icon_home_ftr_activesvg);
////            binding.bottomLay.secondMain.setImageResource(R.drawable.icon_explore_ftsvgr);
////            binding.bottomLay.bottomMessage.setImageResource(R.drawable.message_bubble_chat_icon_154003_1);
////            binding.bottomLay.bottomProfile.setImageResource(R.drawable.icon_profile_ftr);
////        }
    }




    private void chatRequestCount() {
        ChatRequestCountRef.child(AppConstants.USER_ID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String requestCount = snapshot.child("count").getValue().toString();
                    String checkRequest = snapshot.child("checkRequest").getValue().toString();
                    binding.bottomLay.msgCardView.setVisibility(View.VISIBLE);

                    if (Integer.parseInt(requestCount) >= 99) {
                        binding.bottomLay.chatReqMsgCountTv.setText("99+");
                    } else if (Integer.parseInt(requestCount) <= 0) {
                        binding.bottomLay.msgCardView.setVisibility(View.GONE);
                    } else {
                        binding.bottomLay.chatReqMsgCountTv.setText(requestCount);
                    }
                } else {
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void removeLiveUser() {

        liveUsersRef.child(AppConstants.USER_ID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    if (snapshot.hasChild("liveId")) {
                        String liveId = snapshot.child("liveId").getValue().toString();
                        String hostId = snapshot.child("hostId").getValue().toString();
                        String liveType = snapshot.child("liveType").getValue().toString();
                        endLiveApi(liveId, hostId, liveType);
                    }
                } else {
                    String liveId = App.getSharedpref().getString("liveId");
                    String liveType = App.getSharedpref().getString("liveType");
                    String hostId = App.getSharedpref().getString("hostId");

                    if (!liveId.equalsIgnoreCase("") && !liveType.equalsIgnoreCase("") && !hostId.equalsIgnoreCase("")) {
                        endLiveApi(liveId, hostId, liveType);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void endLiveApi(String liveId, String hostId, String liveType) {
        homeActivityViewModel.endLiveCall(HomeActivity.this, liveId).observe(HomeActivity.this, new Observer<SendOtpRoot>() {
            @Override
            public void onChanged(SendOtpRoot sendOtpRoot) {
                if (sendOtpRoot != null) {
                    if (sendOtpRoot.getStatus().equalsIgnoreCase("1")) {
                        try {
                            ref.child(userId).child(liveType).removeValue();
                            liveUsersRef.child(AppConstants.USER_ID).removeValue();


                            muteMicRef.child(hostId).removeValue();
                            userLiveAnnouncement.child(hostId).removeValue();
                            cleanUserCommentsRef.child(hostId).removeValue();
                            LuckBagRef.child(hostId).removeValue();
                            emojiRef.child(hostId).removeValue();
                            luckyDivideUsersRef.child(hostId).removeValue();
                            lockSeat.child(hostId).removeValue();
                            ref.child(userId).child(liveType).removeValue();


                        } catch (Exception e) {
                        }
                        App.getSharedpref().saveString("liveId", "");
                        App.getSharedpref().saveString("liveType", "");
                        App.getSharedpref().saveString("hostId", "");
                    }
                } else {
                    //Toast.makeText(HomeActivity.this, "Technical issue", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        String a = App.getSharedpref().getString("userCheck");

        String exit = App.getSharedpref().getString("exit");

        String liveShareFriends = App.getSharedpref().getString("liveShareFriends");
        String liveShareLink = App.getSharedpref().getString("liveShareLink");
        String dynmicKey = App.getSharedpref().getString("dynmicKey");

        String liveFamily = App.getSharedpref().getString("liveFamily");

        if (liveFamily.isEmpty()) {
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("familyId", App.getSharedpref().getString("liveFamily"));
            Navigation.findNavController(findViewById(R.id.nav_home)).navigate(R.id.familyBatchFragment, bundle);
        }
        if (liveShareFriends.isEmpty()) {
        } else {
            Bundle bundle = new Bundle();
            bundle.putInt("liveShareCheck", 1);
            bundle.putString("liveShareLink", liveShareLink);
            bundle.putString("dynmicKey", dynmicKey);
            Navigation.findNavController(findViewById(R.id.nav_home)).navigate(R.id.friendsFragment, bundle);
        }

        if (exit.equalsIgnoreCase("0")) {
            //Toast.makeText(this, "exit", Toast.LENGTH_SHORT).show();
        } else {
            //Toast.makeText(this, "not exit", Toast.LENGTH_SHORT).show();
        }

        if (a.isEmpty()) {
        } else if (a.equalsIgnoreCase("noCoins")) {
            Navigation.findNavController(findViewById(R.id.nav_home)).navigate(R.id.rechargeCointsFragment);
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("otherUserId", a);
            Navigation.findNavController(findViewById(R.id.nav_home)).navigate(R.id.otherUser, bundle);
        }
    }

    private void openLive(String userId, String key) {

        homeActivityViewModel.getSingleLiveUser(HomeActivity.this, AppConstants.USER_ID, userId).observe(HomeActivity.this, new Observer<SingleLiveUserRoot>() {
            @Override
            public void onChanged(SingleLiveUserRoot singleLiveUserRoot) {

                if (singleLiveUserRoot != null) {
                    if (singleLiveUserRoot.getLiveStatus()) {

                        App.getSingletone().setLiveType("");
                        Intent intent = new Intent(HomeActivity.this, CallActivity.class);
                        intent.putExtra("channelName", singleLiveUserRoot.getDetails().getChannelName());
                        intent.putExtra("userId", singleLiveUserRoot.getDetails().getUserId());

                        intent.putExtra("liveHostIds", singleLiveUserRoot.getDetails().getUserId());
                        intent.putExtra("liveType", "multiLive");
                        intent.putExtra("liveStatus", "host");
                        intent.putExtra("token", singleLiveUserRoot.getDetails().getToken());
                        intent.putExtra("name", singleLiveUserRoot.getDetails().getName());
                        intent.putExtra("liveHostId", singleLiveUserRoot.getDetails().getUserId());

                        if (singleLiveUserRoot.getDetails().getImageTitle() != null && !singleLiveUserRoot.getDetails().getImageTitle().isEmpty()) {
                            intent.putExtra("broadTitle", singleLiveUserRoot.getDetails().getImageTitle());
                        } else {
                            intent.putExtra("broadTitle", singleLiveUserRoot.getDetails().getName());
                        }

                        intent.putExtra("liveImage", singleLiveUserRoot.getDetails().getLiveimage());
                        intent.putExtra("image", singleLiveUserRoot.getDetails().getUserProfileImage());

                        intent.putExtra("status", "1");

                        intent.putExtra("dob", CommonUtils.ageCalcualte(singleLiveUserRoot.getDetails().getDob()));
                        intent.putExtra("gender", singleLiveUserRoot.getDetails().getGender());

//                                       intent.putExtra("userDob", CommonUtils.ageCalcualte(singleLiveUserRoot.getDetails().getUserDob()));
//                                       intent.putExtra("UserGender", singleLiveUserRoot.getDetails().getUserGender());
                        startActivity(intent);
                    } else {
                        //Toast.makeText(HomeActivity.this, "Live Expired", Toast.LENGTH_SHORT).show();
                    }
                } else {

                }
            }
        });
    }

}