package com.live.worldsocialintegrationapp.Fragments.SignUp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;

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

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;
import com.live.worldsocialintegrationapp.Activites.HomeActivity;
import com.live.worldsocialintegrationapp.Activites.IdBannedActivity;
import com.live.worldsocialintegrationapp.ModelClasses.SocialLoginRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.databinding.FragmentLoginBinding;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstant;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.live.worldsocialintegrationapp.utils.CommonUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class LoginFragment extends Fragment {

    FragmentLoginBinding binding;
    //google
    public FusedLocationProviderClient locationProviderClient;
    GoogleSignInClient mGoogleSignInClient;
    FirebaseAuth firebaseAuth;
    BeginSignInRequest signInRequest;
    private static final int REQ_ONE_TAP = 2;
    String RegId = "";
    String countryNew,countryName;
    String socialId, name, email, country;
    private String ImagePath,deviceId;
    private MultipartBody.Part imagePart;
    private RequestBody country_req;
    private static final int PERMISSION_ID = 44;

    //location
    private static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    public static double latitude, longitude;
    //facebook
    private CallbackManager callbackManager;

    private LoginManager loginManager;

    //location
    List<Address> addresses;
    Geocoder geocoder;
    private double lat;
    private double longi;

    private FirebaseAuth mAuth;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false);

        //Toast.makeText(requireContext(), "inflater", Toast.LENGTH_SHORT).show();
        locationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity());
        FacebookSdk.sdkInitialize(requireActivity());
        callbackManager = CallbackManager.Factory.create();
        loginManager = LoginManager.getInstance();
        FirebaseApp.initializeApp(requireActivity());

        deviceId = Settings.Secure.getString(requireActivity().getContentResolver(), Settings.Secure.ANDROID_ID);

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.w("OP", "Fetching FCM registration token failed", task.getException());
                        return;
                    }
                    RegId = task.getResult();
                    Log.d("OTP", "REGID " + RegId);
                });
        if (CommonUtils.isNetworkConnected(requireContext())) {
        } else {
            Toast.makeText(requireContext(), "Connect to Network", Toast.LENGTH_SHORT).show();
        }
        onClick();
        snapChatLogin();
        signInRequest = BeginSignInRequest.builder()
                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        .setServerClientId(getString(R.string.default_web_client_id))
                        .setFilterByAuthorizedAccounts(true)
                        .build())
                .build();

        firebaseAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso);




        return binding.getRoot();
    }

    private void loginWithGoogle(String countryName, double latitude, double longitude) {


        if (countryName !=null){
            countryNew = countryName;
        }else {
            countryNew ="";
        }

        binding.loginWithGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.switchButton.isChecked()){
                    Log.i("Googlee"," in Onactive 1");
                    Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                    startActivityForResult(signInIntent, REQ_ONE_TAP);




                }else {
                    Toast.makeText(requireContext(), "Please accept the terms and conditions", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


//    private void getLatLong() {
//        String json_str = "{\"AD\":\"Europe\",\"AE\":\"Asia\",\"AF\":\"Asia\",\"AG\":\"North America\",\"AI\":\"North America\",\"AL\":\"Europe\",\"AM\":\"Asia\",\"AN\":\"North America\",\"AO\":\"Africa\",\"AQ\":\"Antarctica\",\"AR\":\"South America\",\"AS\":\"Australia\",\"AT\":\"Europe\",\"AU\":\"Australia\",\"AW\":\"North America\",\"AZ\":\"Asia\",\"BA\":\"Europe\",\"BB\":\"North America\",\"BD\":\"Asia\",\"BE\":\"Europe\",\"BF\":\"Africa\",\"BG\":\"Europe\",\"BH\":\"Asia\",\"BI\":\"Africa\",\"BJ\":\"Africa\",\"BM\":\"North America\",\"BN\":\"Asia\",\"BO\":\"South America\",\"BR\":\"South America\",\"BS\":\"North America\",\"BT\":\"Asia\",\"BW\":\"Africa\",\"BY\":\"Europe\",\"BZ\":\"North America\",\"CA\":\"North America\",\"CC\":\"Asia\",\"CD\":\"Africa\",\"CF\":\"Africa\",\"CG\":\"Africa\",\"CH\":\"Europe\",\"CI\":\"Africa\",\"CK\":\"Australia\",\"CL\":\"South America\",\"CM\":\"Africa\",\"CN\":\"Asia\",\"CO\":\"South America\",\"CR\":\"North America\",\"CU\":\"North America\",\"CV\":\"Africa\",\"CX\":\"Asia\",\"CY\":\"Asia\",\"CZ\":\"Europe\",\"DE\":\"Europe\",\"DJ\":\"Africa\",\"DK\":\"Europe\",\"DM\":\"North America\",\"DO\":\"North America\",\"DZ\":\"Africa\",\"EC\":\"South America\",\"EE\":\"Europe\",\"EG\":\"Africa\",\"EH\":\"Africa\",\"ER\":\"Africa\",\"ES\":\"Europe\",\"ET\":\"Africa\",\"FI\":\"Europe\",\"FJ\":\"Australia\",\"FK\":\"South America\",\"FM\":\"Australia\",\"FO\":\"Europe\",\"FR\":\"Europe\",\"GA\":\"Africa\",\"GB\":\"Europe\",\"GD\":\"North America\",\"GE\":\"Asia\",\"GF\":\"South America\",\"GG\":\"Europe\",\"GH\":\"Africa\",\"GI\":\"Europe\",\"GL\":\"North America\",\"GM\":\"Africa\",\"GN\":\"Africa\",\"GP\":\"North America\",\"GQ\":\"Africa\",\"GR\":\"Europe\",\"GS\":\"Antarctica\",\"GT\":\"North America\",\"GU\":\"Australia\",\"GW\":\"Africa\",\"GY\":\"South America\",\"HK\":\"Asia\",\"HN\":\"North America\",\"HR\":\"Europe\",\"HT\":\"North America\",\"HU\":\"Europe\",\"ID\":\"Asia\",\"IE\":\"Europe\",\"IL\":\"Asia\",\"IM\":\"Europe\",\"IN\":\"Asia\",\"IO\":\"Asia\",\"IQ\":\"Asia\",\"IR\":\"Asia\",\"IS\":\"Europe\",\"IT\":\"Europe\",\"JE\":\"Europe\",\"JM\":\"North America\",\"JO\":\"Asia\",\"JP\":\"Asia\",\"KE\":\"Africa\",\"KG\":\"Asia\",\"KH\":\"Asia\",\"KI\":\"Australia\",\"KM\":\"Africa\",\"KN\":\"North America\",\"KP\":\"Asia\",\"KR\":\"Asia\",\"KW\":\"Asia\",\"KY\":\"North America\",\"KZ\":\"Asia\",\"LA\":\"Asia\",\"LB\":\"Asia\",\"LC\":\"North America\",\"LI\":\"Europe\",\"LK\":\"Asia\",\"LR\":\"Africa\",\"LS\":\"Africa\",\"LT\":\"Europe\",\"LU\":\"Europe\",\"LV\":\"Europe\",\"LY\":\"Africa\",\"MA\":\"Africa\",\"MC\":\"Europe\",\"MD\":\"Europe\",\"ME\":\"Europe\",\"MG\":\"Africa\",\"MH\":\"Australia\",\"MK\":\"Europe\",\"ML\":\"Africa\",\"MM\":\"Asia\",\"MN\":\"Asia\",\"MO\":\"Asia\",\"MP\":\"Australia\",\"MQ\":\"North America\",\"MR\":\"Africa\",\"MS\":\"North America\",\"MT\":\"Europe\",\"MU\":\"Africa\",\"MV\":\"Asia\",\"MW\":\"Africa\",\"MX\":\"North America\",\"MY\":\"Asia\",\"MZ\":\"Africa\",\"NA\":\"Africa\",\"NC\":\"Australia\",\"NE\":\"Africa\",\"NF\":\"Australia\",\"NG\":\"Africa\",\"NI\":\"North America\",\"NL\":\"Europe\",\"NO\":\"Europe\",\"NP\":\"Asia\",\"NR\":\"Australia\",\"NU\":\"Australia\",\"NZ\":\"Australia\",\"OM\":\"Asia\",\"PA\":\"North America\",\"PE\":\"South America\",\"PF\":\"Australia\",\"PG\":\"Australia\",\"PH\":\"Asia\",\"PK\":\"Asia\",\"PL\":\"Europe\",\"PM\":\"North America\",\"PN\":\"Australia\",\"PR\":\"North America\",\"PS\":\"Asia\",\"PT\":\"Europe\",\"PW\":\"Australia\",\"PY\":\"South America\",\"QA\":\"Asia\",\"RE\":\"Africa\",\"RO\":\"Europe\",\"RS\":\"Europe\",\"RU\":\"Europe\",\"RW\":\"Africa\",\"SA\":\"Asia\",\"SB\":\"Australia\",\"SC\":\"Africa\",\"SD\":\"Africa\",\"SE\":\"Europe\",\"SG\":\"Asia\",\"SH\":\"Africa\",\"SI\":\"Europe\",\"SJ\":\"Europe\",\"SK\":\"Europe\",\"SL\":\"Africa\",\"SM\":\"Europe\",\"SN\":\"Africa\",\"SO\":\"Africa\",\"SR\":\"South America\",\"ST\":\"Africa\",\"SV\":\"North America\",\"SY\":\"Asia\",\"SZ\":\"Africa\",\"TC\":\"North America\",\"TD\":\"Africa\",\"TF\":\"Antarctica\",\"TG\":\"Africa\",\"TH\":\"Asia\",\"TJ\":\"Asia\",\"TK\":\"Australia\",\"TM\":\"Asia\",\"TN\":\"Africa\",\"TO\":\"Australia\",\"TR\":\"Asia\",\"TT\":\"North America\",\"TV\":\"Australia\",\"TW\":\"Asia\",\"TZ\":\"Africa\",\"UA\":\"Europe\",\"UG\":\"Africa\",\"US\":\"North America\",\"UY\":\"South America\",\"UZ\":\"Asia\",\"VC\":\"North America\",\"VE\":\"South America\",\"VG\":\"North America\",\"VI\":\"North America\",\"VN\":\"Asia\",\"VU\":\"Australia\",\"WF\":\"Australia\",\"WS\":\"Australia\",\"YE\":\"Asia\",\"YT\":\"Africa\",\"ZA\":\"Africa\",\"ZM\":\"Africa\",\"ZW\":\"Africa\"}";
//        try {
//            JSONObject jsonObject = new JSONObject(json_str);
//            Geocoder geocoderr = new Geocoder(requireContext(), Locale.ENGLISH);
//
//            List<Address> addressess = geocoderr.getFromLocation( lat, longi, 1);
//
//            if (addressess.size() > 0) {
//
//                Address fetchedAddress = addresses.get(0);
//
//                // getCountryCode from Address
//                String countryCode = fetchedAddress.getCountryCode();
//                String countryName = fetchedAddress.getCountryName();
//
//                // get continentName here
//                String continentName = jsonObject.getString(countryCode);
//
////                App.getSharedpref().saveString("ContinentName",continentName);
////                App.getSharedpref().saveString("CountryName",countryName);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

    private void snapChatLogin() {

        binding.snapChatLoginImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(requireContext(), "Coming soon", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        checkUserStatus();
    }

    private void onClick() {

        int flagOffset = 0x1F1E6;
        int asciiOffset = 0x41;

        String country = "IN";

        int firstChar = Character.codePointAt(country, 0) - asciiOffset + flagOffset;
        int secondChar = Character.codePointAt(country, 1) - asciiOffset + flagOffset;

        String flag = new String(Character.toChars(firstChar))
                + new String(Character.toChars(secondChar));

        //binding.txtEmoji.setText(flag);

        binding.loginPhonell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.switchButton.isChecked()){
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_loginFragment_to_enterPhoneFragment, getArguments());
                }else {
                    Toast.makeText(requireContext(), "Please accept the terms and conditions", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.loginPrivacyTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//             Navigation.findNavController(binding.getRoot()).navigate(R.id.privacyPolicyFragment2);
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://omninos.life/Social_Integration/privecy.html")));
            }
        });
        binding.loginTermsTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//             Navigation.findNavController(binding.getRoot()).navigate(R.id.termsConditionsTV2);
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://omninos.life/Social_Integration/termandcondition.html")));
            }
        });

        binding.loginServiceTv.setOnClickListener(view -> {
//             Navigation.findNavController(binding.getRoot()).navigate(R.id.termsConditionsTV2);
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://omninos.life/Social_Integration/termandcondition.html")));
        });



        binding.llLoginWithFacebook.setOnClickListener(view -> {
            if (binding.switchButton.isChecked()){

               FacebookLogin();
            }else {
                Toast.makeText(requireContext(), "Please accept the terms and conditions", Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void checkUserStatus() {
//        if (AppConstants.USER_ID.length() != 0) {
//
//            startActivity(new Intent(requireActivity(), HomeActivity.class));
//        } else {
//            if (getContext() != null) {
////                Toast.makeText(requireContext(), "Preferences null", Toast.LENGTH_SHORT).show();
//            }
//        }
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

//        if (requestCode == REQ_ONE_TAP) {
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            try {
//                Log.d("TAG", "TASK started");
//                handleSignInResult(task);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else {
//            // Pass the activity result back to the Facebook SDK
//            callbackManager.onActivityResult(requestCode, resultCode, data);
//        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {



        try {
            GoogleSignInAccount acct = completedTask.getResult(ApiException.class);
            if (acct != null) {

                String personName = acct.getDisplayName();
                String personGivenName = acct.getGivenName();
                String personFamilyName = acct.getFamilyName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();
                Uri personPhoto = acct.getPhotoUrl();
                Toast.makeText(requireActivity(), "LogIn Success", Toast.LENGTH_SHORT).show();
                socialLoginApi(countryNew,personId,personName,personEmail,personPhoto.toString());
            }
            // Signed in successfully, show authenticated UI.
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
        }

//        try {
//            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
//            socialId = account.getId().toString();
//            name = account.getDisplayName().toString();
//            email = account.getEmail().toString();
//            App.getSharedpref().saveString("gamil", email);
//            ImagePath = account.getPhotoUrl().getEncodedPath().toString();
//            Log.w("TAG", "signInResult:failed code=" + account.getDisplayName());
////            continentName = App.getSharedpref().getString("continentName");
////            countryName = App.getSharedpref().getString("countryName");
//
//            socialLoginApi(countryNew,socialId,name,email,imagePart.toString());
//
////            if (continentName.isEmpty()){
////                Toast.makeText(requireContext(), "Please turn on Location your continent is null", Toast.LENGTH_SHORT).show();
////            }else if (countryName.isEmpty()){
////                Toast.makeText(requireContext(), "Please turn on Location your countryName is null", Toast.LENGTH_SHORT).show();
////            }else {
////                try {
//////                    Toast.makeText(requireContext(), "idbannedStatus :"+App.getSharedpref().getString("idbannedStatus"), Toast.LENGTH_SHORT).show();
////                    if (App.getSharedpref().getString("idbannedStatus").equalsIgnoreCase("true") ){
////                        startActivity(new Intent(requireContext(), IdBannedActivity.class));
////                    }else {
////                        socialLoginApi(continentName,countryName);
////                    }
////                } catch (Exception e){
////                }
////            }
//        } catch (ApiException e) {
//            Log.w("TAG", "signInResult:failed code=" + e.getStatusCode());
//            Log.w("TAG", "signInResult:failed code=" + e.getMessage());
//        }
    }

    private void FacebookLogin() {

        loginManager.logInWithReadPermissions(LoginFragment.this,
                Arrays.asList("email", "public_profile"));
        loginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
//                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), (object, response) -> {
//                    if (object != null) {
//
//                        try {
//                            name = object.getString("name");
//                            email = object.getString("email");
//                            socialId = object.getString("id");
////                             ImagePath = object.getString("picture");
//
//                            App.getSharedpref().saveString("facebook", name);
//
////                                countryNew = App.getSharedpref().getString("continentName");
////                                countryName = App.getSharedpref().getString("countryName");
//                            if (countryNew.isEmpty()){
//                                Toast.makeText(requireContext(), "Please turn on Location your continent is null", Toast.LENGTH_SHORT).show();
//                            }else if (countryName.isEmpty()){
//                                Toast.makeText(requireContext(), "Please turn on Location your countryName is null", Toast.LENGTH_SHORT).show();
//                            }else {
//                                socialLoginApi(countryNew,countryName);
//                            }
//
//                            // do action after Facebook login success
//                            // or call your API
//                        } catch (JSONException | NullPointerException e) {
//                            e.printStackTrace();
//                        }
//
//                });
//                Bundle parameters = new Bundle();
//                parameters.putString(
//                        "fields",
//                        "id, name, email, gender, birthday");
//                request.setParameters(parameters);
//                request.executeAsync();
//                Log.d("TAG", "facebook:onSuccess:" + loginResult);
//                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.v("LoginScreen", "---onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                // here write code when get error
                Log.v("LoginScreen", "----onError: " + error.getMessage());
            }
        });
    }

    private void handleFacebookAccessToken(AccessToken accessToken) {

        Log.d("accessToken", "handleFacebookAccessToken:" + accessToken);

        AuthCredential credential = FacebookAuthProvider.getCredential(String.valueOf(accessToken));
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener((Executor) this, (OnCompleteListener<AuthResult>) new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
//                            socialLoginApi(countryNew);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            Toast.makeText(requireContext(), "Authentication failed", Toast.LENGTH_SHORT).show();
//                            socialLoginApi("");
                        }
                    }
                });
    }

    private void socialLoginApi(String continent, String socialId, String name, String email, String s) {

        Log.d("socialLoginApi", "socialLoginApi: "+socialId);
        Log.d("socialLoginApi", "socialLoginApi: "+RegId);
        Log.d("socialLoginApi", "socialLoginApi: "+name);
        Log.d("socialLoginApi", "socialLoginApi: "+email);
        Log.d("socialLoginApi", "socialLoginApi: "+continent);
        Log.d("socialLoginApi", "socialLoginApi: "+countryNew);
        Log.d("socialLoginApi", "socialLoginApi: "+s);
        Log.d("socialLoginApi", "socialLoginApi: "+deviceId);

        new Mvvm().socialLogin(requireActivity(),
                        CommonUtils.getRequestBodyText(socialId),
                        CommonUtils.getRequestBodyText(RegId),
                        CommonUtils.getRequestBodyText(deviceId),
                        CommonUtils.getRequestBodyText("android"),
                        CommonUtils.getRequestBodyText(""),
                        CommonUtils.getRequestBodyText(name),
                        CommonUtils.getRequestBodyText(email),
                        CommonUtils.getRequestBodyText(""),
                        CommonUtils.getFileData(s,"image"),
                        CommonUtils.getRequestBodyText(countryNew))
                .observe(requireActivity(), socialLoginRoot -> {
//                    if (socialLoginRoot != null) {
                        if (socialLoginRoot != null && socialLoginRoot.getStatus() == 1) {
                            Log.i("socialLoginApi","inside if");
                            //Toast.makeText(requireContext(), "1"+socialLoginRoot.getMessage(), Toast.LENGTH_SHORT).show();
                            App.getSharedpref().saveString(AppConstant.SESSION, "1");
                            App.getSharedpref().saveString("idbannedStatus",String.valueOf(socialLoginRoot.getDetails().idBannedStatus));
                            App.getSharedpref().saveString("username", socialLoginRoot.getDetails().getUsername());
                            App.getSharedpref().saveString("image", socialLoginRoot.getDetails().getImage());
                            App.getSharedpref().saveString("name", socialLoginRoot.getDetails().getName());
                            App.getSharedpref().saveString("phone", socialLoginRoot.getDetails().getPhone());
                            App.getSharedpref().saveString("userId", socialLoginRoot.getDetails().getId());
                            App.getSharedpref().saveString("country", socialLoginRoot.getDetails().getCountry());
                            App.getSharedpref().saveString("dob", socialLoginRoot.getDetails().getDob());

                            App.getSharedpref().saveModel("RegisterRoot", socialLoginRoot.getDetails());
                            AppConstants.USER_ID = socialLoginRoot.getDetails().getId();

                            if (String.valueOf(socialLoginRoot.getDetails().idBannedStatus).equalsIgnoreCase("false")){
                                startActivity(new Intent(requireActivity(), HomeActivity.class));
                            }else {
                                startActivity(new Intent(requireContext(), IdBannedActivity.class));
                            }
                        } else {
                            if (getContext() != null) {
                                Toast.makeText(requireContext(), "Technical issue ", Toast.LENGTH_SHORT).show();
                            }
                        }
//                    } else {
//                        if (getContext() != null) {
//                            Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
//                        }
//                    }
                });

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

    private LocationCallback mLocationCallback = new LocationCallback() {
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

                App.getSharedpref().saveString("countryName",country);

            }
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.i("Googleeee","in on request persmission method");
                getLastLocation();
            }
        }
    }

    private void getCompleteAddressString() {
        Log.i("Googleeee","inside getCompleteAddressString");
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
            Log.i("Googleeee","above function call");
            loginWithGoogle(country, latitude, longitude);
//            App.getSharedpref().saveString(AppConstants.USER_CURRENT_ADDRESS, addresses.get(0).getLocality());

        } else {
            Toast.makeText(requireContext(), "COUNTRY MISSING", Toast.LENGTH_SHORT).show();
        }
    }

}