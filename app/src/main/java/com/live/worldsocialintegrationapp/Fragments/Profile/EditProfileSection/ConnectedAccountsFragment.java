package com.live.worldsocialintegrationapp.Fragments.Profile.EditProfileSection;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;
import com.live.worldsocialintegrationapp.Activites.HomeActivity;
import com.live.worldsocialintegrationapp.Activites.IdBannedActivity;
import com.live.worldsocialintegrationapp.Activites.SplashActivity;
import com.live.worldsocialintegrationapp.Fragments.SignUp.LoginFragment;
import com.live.worldsocialintegrationapp.ModelClasses.SendOtpRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.databinding.FragmentConnectedAccountsBinding;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstant;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.live.worldsocialintegrationapp.utils.CommonUtils;

import org.json.JSONException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


public class ConnectedAccountsFragment extends Fragment {

    public FusedLocationProviderClient locationProviderClient;
    FragmentConnectedAccountsBinding binding;

    FirebaseAuth firebaseAuth;
    GoogleSignInClient mGoogleSignInClient;
    private String phone, email="", facebook,name,deviceId, username;
    private static final int PERMISSION_ID = 44;
    String RegId = "",countryNew,country;
    private LoginManager loginManager;
    private static final int REQ_ONE_TAP = 2;
    private CallbackManager callbackManager;
    public static double latitude, longitude;

    @SuppressLint("HardwareIds")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentConnectedAccountsBinding.inflate(inflater, container, false);
        FacebookSdk.sdkInitialize(requireActivity());
        locationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity());
        callbackManager = CallbackManager.Factory.create();
        loginManager = LoginManager.getInstance();
        deviceId = Settings.Secure.getString(requireActivity().getContentResolver(), Settings.Secure.ANDROID_ID);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        firebaseAuth = FirebaseAuth.getInstance();
        if (CommonUtils.isNetworkConnected(requireContext())) {
            //checks if network is connected
        } else {
            Toast.makeText(requireContext(), "Connect to Network", Toast.LENGTH_SHORT).show();
        }
        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso);
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.w("OP", "Fetching FCM registration token failed", task.getException());
                        return;
                    }
                    RegId = task.getResult();
                    Log.d("OTP", "REGID " + RegId);
                });
        onClick();
//
//        // This callback is only called when MyFragment is at least started
//        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
//            @Override
//            public void handleOnBackPressed() {
//            }
//
//        };
//        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
        return binding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        phone = App.getSharedpref().getString("phone");
        email = App.getSharedpref().getString("email");
        name = App.getSharedpref().getString("name");
        username = App.getSharedpref().getString("username");

        facebook = App.getSharedpref().getString("facebook");
        if (phone.isEmpty()) {
            binding.connectedPhoneTv.setText("Add");
            //binding.phoneArrow.setVisibility(View.GONE);
        }else{
            binding.connectedPhoneTv.setText(phone);
            //binding.phoneArrow.setVisibility(View.VISIBLE);
        }
        if (facebook.isEmpty()) {
            binding.connectFacebookTv.setText("Add");
            //binding.facebookArrow.setVisibility(View.GONE);
        }else{
            binding.connectFacebookTv.setText(facebook);
            //binding.facebookArrow.setVisibility(View.VISIBLE);
        }
        if (email.isEmpty()) {
            binding.connectGoogle.setText("Add");
            //binding.googleArrow.setVisibility(View.GONE);
        }else{
            binding.connectGoogle.setText(email);
            //binding.googleArrow.setVisibility(View.VISIBLE);
        }
    }

    private void loginWithGoogle(String countryName, double latitude, double longitude) {


        if (countryName !=null){
            countryNew = countryName;
        }else {
            countryNew ="";
        }

        binding.connectGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.connectGoogle.getText() == "Add"){
                    Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                    startActivityForResult(signInIntent, REQ_ONE_TAP);
                }

                else{
                    Toast.makeText(requireActivity(), "Account all-ready Added", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void onClick() {

        binding.phoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phone.isEmpty()){
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_connectedAccountsFragment_to_addPhoneNumber);
                    //Toast.makeText(requireContext(), "You don't have any number linked", Toast.LENGTH_SHORT).show();
                }else{
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_connectedAccountsFragment_to_phoneVerificationFragment);
                }
            }
        });

        binding.backEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        binding.deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAccountDialogBox();
            }
        });

        binding.connectFacebookTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.connectFacebookTv.getText() == "Add"){
                    FacebookLogin();
                }
                else{
                    Toast.makeText(requireActivity(), "Account all-ready Added", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void deleteAccountDialogBox() {

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

        builder.setMessage("Are you sure you want to delete your account ?");
        builder.setTitle("Delete Account");

        builder.setCancelable(false);

        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            deleteAccountApi();
            dialog.dismiss();

        });

        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void deleteAccountApi() {
        String id = App.getSharedpref().getString("userId");

        if (id.length() == 0) {
            Toast.makeText(requireContext(), "user id null", Toast.LENGTH_SHORT).show();
        } else {

            new Mvvm().removeUserAccount(requireActivity(), id).observe(requireActivity(), new Observer<SendOtpRoot>() {
                @Override
                public void onChanged(SendOtpRoot sendOtpRoot) {

                    if (sendOtpRoot.getSuccess().equalsIgnoreCase("1")) {
                        //                    Toast.makeText(requireContext(), "1 "+sendOtpRoot.getMessage(), Toast.LENGTH_SHORT).show();

                        //////////////////////////////////////////////////////
//                        App.getSharedpref().clearPreferences();
//                        AppConstants.USER_ID = "";
//                        startActivity(new Intent(requireContext(), SplashActivity.class));
                        //////////////////////////////////////////////////////
                        App.getSharedpref().clearPreferences();
                        AppConstants.USER_ID = "";
                        Intent intent = new Intent(requireContext(), SplashActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        requireActivity().finish();
                    } else {
//                    Toast.makeText(requireContext(), "0 "+sendOtpRoot.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    private void FacebookLogin() {

        loginManager.logInWithReadPermissions(ConnectedAccountsFragment.this,
                Arrays.asList("email", "public_profile"));
        loginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), (object, response) -> {
                    if (object != null) {
                        try {

                            String personName = object.getString("name");
                            //String personEmail = object.getString("email");
                            String facebookId = object.getString("id");
                            //add facebook username in sharedPref
                            App.getSharedpref().saveString("facebook",personName);

                            //Toast.makeText(requireActivity(), "LogIn Success", Toast.LENGTH_SHORT).show();

                            //added static email for now as we need to get mail from facebook
                            socialLoginApi(countryNew,"","",email,"true",username,facebookId,"",personName);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        Log.i("Facebookzzzzzzzzz","Logged in successfully in else");
                    }
                });
                request.executeAsync();

            }

            @Override
            public void onCancel() {
                Log.i("Facebookzzzzzzzzz","on cancel");
            }

            @Override
            public void onError(FacebookException error) {
                // here write code when get error
                Log.v("Facebookzzzzzzzzz", "----onError: " + error.getMessage());
            }
        });
    }

    private void socialLoginApi(String continent, String gmailId, String name, String email, String isAddingAccount, String userName, String facebookId, String snapChatId, String facebookUserName) {

        Log.d("socialLoginApi", "socialLoginApi: ");
        Log.d("socialLoginApi", "socialLoginApi: "+gmailId);
        Log.d("socialLoginApi", "socialLoginApi: "+RegId);
        Log.d("socialLoginApi", "socialLoginApi: "+deviceId);
        Log.d("socialLoginApi", "socialLoginApi: "+ "Device type");
        Log.d("socialLoginApi", "socialLoginApi: "+ "Phone number");
        Log.d("socialLoginApi", "socialLoginApi: "+name);
        Log.d("socialLoginApi", "socialLoginApi: "+email);
        Log.d("socialLoginApi", "socialLoginApi: "+continent);
        Log.d("socialLoginApi", "socialLoginApi: "+countryNew);
        //Log.d("socialLoginApi", "socialLoginApi: "+s);
        String continentName = App.getSharedpref().getString("continentName");



        new Mvvm().socialLogin(requireActivity(),
                        CommonUtils.getRequestBodyText(gmailId),
                        CommonUtils.getRequestBodyText(RegId),
                        CommonUtils.getRequestBodyText(deviceId),
                        CommonUtils.getRequestBodyText("android"),
                        CommonUtils.getRequestBodyText(""),
                        CommonUtils.getRequestBodyText(name),
                        CommonUtils.getRequestBodyText(email),
                        CommonUtils.getRequestBodyText(continentName),
                        // CommonUtils.getFileData(s,"image"),
                        CommonUtils.getRequestBodyText(countryNew),
                        CommonUtils.getRequestBodyText(isAddingAccount),
                        CommonUtils.getRequestBodyText(userName),
                        CommonUtils.getRequestBodyText(facebookId),
                        CommonUtils.getRequestBodyText(snapChatId),
                        CommonUtils.getRequestBodyText(facebookUserName))
                .observe(requireActivity(), socialLoginRoot -> {
//                    if (socialLoginRoot != null) {
                    if (socialLoginRoot != null && socialLoginRoot.getStatus() == 1) {

                        //App.getSharedpref().saveString(AppConstant.SESSION, "1");
                        //App.getSharedpref().saveString("id bannedStatus",String.valueOf(socialLoginRoot.getDetails().idBannedStatus));
                        //App.getSharedpref().saveString("username", socialLoginRoot.getDetails().getUsername());
                        //App.getSharedpref().saveString("image", socialLoginRoot.getDetails().getImage());
                        //App.getSharedpref().saveString("name", socialLoginRoot.getDetails().getName());
                        //App.getSharedpref().saveString("phone", socialLoginRoot.getDetails().getPhone());
                        //App.getSharedpref().saveString("userId", socialLoginRoot.getDetails().getId());
                        //App.getSharedpref().saveString("country", socialLoginRoot.getDetails().getCountry());
                        //App.getSharedpref().saveString("dob", socialLoginRoot.getDetails().getDob());
                        App.getSharedpref().saveString("email", socialLoginRoot.getDetails().getEmail());
                        //App.getSharedpref().saveModel("RegisterRoot", socialLoginRoot.getDetails());
                        AppConstants.USER_ID = socialLoginRoot.getDetails().getId();

                        if (String.valueOf(socialLoginRoot.getDetails().idBannedStatus).equalsIgnoreCase("false")){
                            Toast.makeText(requireContext(), "Account added successfully!!", Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(requireActivity(), HomeActivity.class));
                        }else {
                            startActivity(new Intent(requireContext(), IdBannedActivity.class));
                        }
                    }
                    else {
                        if (getContext() != null) {
                            Toast.makeText(requireContext(), "Technical issue ", Toast.LENGTH_SHORT).show();
                        }
                    }

                });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                getLastLocation();
            }
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_ONE_TAP) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
            callbackManager.onActivityResult(requestCode, resultCode, data);
        } else {

            super.onActivityResult(requestCode, resultCode, data);
        }
        callbackManager.onActivityResult(requestCode, resultCode, data);


    }
    @Override
    public void onResume() {
        super.onResume();
        getLastLocation();
    }

    private boolean checkPermissions() {
        return ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }


    private void requestPermissions() {
        ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_ID
        );
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }
    private void getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                if (ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireActivity(),
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                if (locationProviderClient != null){
                    locationProviderClient.getLastLocation().addOnCompleteListener(task -> {
                        Location location = task.getResult();
                        if (location == null) {
                            requestNewLocationData();
                            Log.i("Googleeee","in if of lcation provider client");
                        } else {

                            latitude = location.getLatitude();
                            longitude = location.getLongitude();

                            getCompleteAddressString();

                        }
                    });
                }else {
                    //Toast.makeText(requireContext(), "locationProviderClient null ", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(requireActivity(), "Turn on location", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            requestPermissions();
        }
    }

    private void getCompleteAddressString() {

        Geocoder geocoder;
        List<Address> addresses = null;
        geocoder = new Geocoder(requireContext(), Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (addresses != null) {
            Address returnedAddress = addresses.get(0);
            StringBuilder strReturnedAddress = new StringBuilder();

            for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
                strReturnedAddress.append(returnedAddress.getAddressLine(i)).append(" ");
            }
            country= addresses.get(0).getCountryName();
            App.getSharedpref().saveString("countryName",country);
            Log.i("socialLoginApi","Country " + country);
            loginWithGoogle(country, latitude, longitude);
//            App.getSharedpref().saveString(AppConstants.USER_CURRENT_ADDRESS, addresses.get(0).getLocality());

        } else {
            Toast.makeText(requireContext(), "COUNTRY MISSING", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("MissingPermission")
    private void requestNewLocationData() {

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(0);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        locationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity());
        locationProviderClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper()

        );
    }

    private LocationCallback mLocationCallback = new LocationCallback()
    {
        @Override
        public void onLocationResult(LocationResult locationResult) {


            Location mLastLocation = locationResult.getLastLocation();
            latitude = mLastLocation.getLatitude();
            longitude = mLastLocation.getLongitude();

            Geocoder geocoder;
            List<Address> addresses = null;
            geocoder = new Geocoder(requireActivity(), Locale.getDefault());
            try {
                addresses = geocoder.getFromLocation(latitude, longitude, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert addresses != null;
            if (!addresses.isEmpty()) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder();

                for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append(" ");
                }
                country= addresses.get(0).getCountryName();

                //App.getSharedpref().saveString("countryName",country);

            }
        }
    };
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount acct = completedTask.getResult(ApiException.class);
            if (acct != null) {

                String personName = acct.getDisplayName();
                String personGivenName = acct.getGivenName();
                String personFamilyName = acct.getFamilyName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();
                //Uri personPhoto = acct.getPhotoUrl();
                Toast.makeText(requireActivity(), "Account added successfully!!", Toast.LENGTH_SHORT).show();
                Log.i("socialLoginApi"," above social login api call ");
                Log.i("socialLoginApi",countryNew);
                Log.i("socialLoginApi",personId);
                Log.i("socialLoginApi",personName);
                Log.i("socialLoginApi",personEmail);
                //Log.i("socialLoginApi",personPhoto.toString());
                socialLoginApi(countryNew,personId,"",personEmail,"true",username,"","","");

            }
            // Signed in successfully, show authenticated UI.
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
        }


    }

}