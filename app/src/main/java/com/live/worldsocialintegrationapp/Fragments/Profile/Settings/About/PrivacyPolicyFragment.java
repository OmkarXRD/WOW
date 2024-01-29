package com.live.worldsocialintegrationapp.Fragments.Profile.Settings.About;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.live.worldsocialintegrationapp.R;


public class PrivacyPolicyFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_privacy_policy, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        startActivity( new Intent(Intent.ACTION_VIEW, Uri.parse("https://omninos.life/Social_Integration/privecy.html")));

//                Navigation.findNavController(binding.getRoot()).navigate(R.id.termsConditionsTV2);


//        view.findViewById(R.id.privacyBackImg).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getActivity().onBackPressed();
//            }
//        });
//
//        // Find the WebView by its unique ID
//        WebView webView = view.findViewById(R.id.web);
//
//        // loading http://www.google.com url in the WebView.
//        webView.loadUrl("https://omninos.life/Social_Integration/privecy.html");
//
//        // this will enable the javascript.
//        webView.getSettings().setJavaScriptEnabled(true);
//
//        // WebViewClient allows you to handle
//        // onPageFinished and override Url loading.
//        webView.setWebViewClient(new WebViewClient());
    }
}