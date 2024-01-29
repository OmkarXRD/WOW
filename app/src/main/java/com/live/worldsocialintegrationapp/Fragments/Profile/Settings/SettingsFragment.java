package com.live.worldsocialintegrationapp.Fragments.Profile.Settings;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.live.worldsocialintegrationapp.Activites.HomeActivity;
import com.live.worldsocialintegrationapp.Activites.SplashActivity;
import com.live.worldsocialintegrationapp.ModelClasses.SendOtpRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.databinding.FragmentSettingsBinding;
import com.live.worldsocialintegrationapp.room.AppDatabase;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstants;

import java.util.Arrays;


public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;
    private ProgressDialog progressBar;
    private int progressBarStatus = 0;
    private Handler progressBarHandler = new Handler();
    private long fileSize = 0;
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInOptions gso;
    private AppDatabase database;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onClick();
        database= AppDatabase.getInstance(requireContext());

        setStatusBarGradiant(requireActivity());
// Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
         gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

// Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso);

    }

    private void onClick() {

        binding.hostApplyTv.setOnClickListener(view -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.applyForHostFragment);
        });
        binding.settingConnectAccount.setOnClickListener(view -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_settingsFragment_to_connectedAccountsFragment);
        });

        binding.settingBackImg.setOnClickListener(view -> {
            getActivity().onBackPressed();
        });

        binding.settingsLogoutTV.setOnClickListener(view -> {
            logoutDialoxbox(view);
        });

        binding.privacyTv.setOnClickListener(view -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.privacyFragment);
        });

        binding.accountAndSecurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_settingsFragment_to_connectedAccountsFragment);
            }
        });

        binding.accountAndSecurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.accountAndSecurityFragment);
            }
        });
        binding.aboutUsTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.aboutFragment);
            }
        });

        binding.settingsBlockedListTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.aboutFragment);
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.fragment_BlockedUser);
            }
        });
        binding.cleanCacheTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cleanCache();
            }
        });


    }


    private void logoutApi(Dialog dialog) {

        dialog.dismiss();
        if(database.userDao().questionTableExits(1)){
            database.userDao().chatTableDrop();
            database.userDao().deleteQuestionTable();
        }else{
            database.userDao().chatTableDrop();
            database.userDao().deleteQuestionTable();
        }
        String id = App.getSharedpref().getString("userId");

        if (id.length() == 0) {
            Toast.makeText(requireContext(), "user id null", Toast.LENGTH_SHORT).show();
        } else {
            new Mvvm().logoutUser(requireActivity(), id).observe(requireActivity(), new Observer<SendOtpRoot>() {
                @Override
                public void onChanged(SendOtpRoot sendOtpRoot) {

                    if (sendOtpRoot.getSuccess().equalsIgnoreCase("1")) {

                        App.getSharedpref().clearPreferences();

                        if(mGoogleSignInClient != null){
                            mGoogleSignInClient.signOut();
                        }
                        disconnectFromFacebook();

                        AppConstants.USER_ID = "";
                        startActivity(new Intent(requireContext(), SplashActivity.class));
                        getActivity().finish();
                    } else {
                    }
                }

            });
        }

    }

    private void cleanCache() {

        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.clean_storage_box);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        dialog.show();

        dialog.findViewById(R.id.cancelBtn).setOnClickListener(view2 -> {
            dialog.dismiss();
        });
        dialog.findViewById(R.id.continueBtn).setOnClickListener(view2 -> {
            dialog.dismiss();

            progressBar = new ProgressDialog(getContext());
            progressBar.setCancelable(true);
            progressBar.setMessage("Clean....");
            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressBar.setProgress(0);
            progressBar.setMax(100);
            progressBar.show();
            //reset progress bar and filesize status
            progressBarStatus = 0;
            fileSize = 0;

            new Thread(new Runnable() {
                public void run() {
                    while (progressBarStatus < 100) {
                        // performing operation
                        progressBarStatus = doOperation();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        // Updating the progress bar
                        progressBarHandler.post(new Runnable() {
                            public void run() {
                                progressBar.setProgress(progressBarStatus);
                            }
                        });
                    }
                    // performing operation if file is downloaded,
                    if (progressBarStatus >= 100) {
                        // sleeping for 1 second after operation completed
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        // close the progress bar dialog
                        progressBar.dismiss();
                    }
                }
            }).start();


        });
    }
    private int doOperation() {

        while (fileSize <= 10000) {
            fileSize++;
            if (fileSize == 1000) {
                return 10;
            } else if (fileSize == 2000) {
                return 20;
            } else if (fileSize == 3000) {
                return 30;
            } else if (fileSize == 4000) {
                return 40; // you can add more else if
            }
        }

        return 100;
    }

    private void logoutDialoxbox(View view) {

        final Dialog dialog = new Dialog(requireContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.log_out_dialog);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
        dialog.findViewById(R.id.yesText).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               logoutApi(dialog);
            }
        });
        dialog.findViewById(R.id.noText).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }


    public void disconnectFromFacebook() {

        if (AccessToken.getCurrentAccessToken() == null) {
            return; // already logged out
        }

        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest
                .Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {
                LoginManager.getInstance().logOut();
            }
        }).executeAsync();
    }

    @Override
    public void onResume() {

        super.onResume();

        requireActivity().findViewById(R.id.bottom_lay).setVisibility(View.GONE);
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setStatusBarGradiant(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            Drawable background = activity.getResources().getDrawable(R.drawable.wallet_background);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setNavigationBarColor(activity.getResources().getColor(android.R.color.darker_gray));
            window.setBackgroundDrawable(background);
        }
    }

}