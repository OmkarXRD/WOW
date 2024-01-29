package com.live.worldsocialintegrationapp.Fragments.Profile.Settings.About;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.live.worldsocialintegrationapp.R;


public class TermsConditionsTV extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_terms_conditions_t_v, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        startActivity( new Intent(Intent.ACTION_VIEW, Uri.parse("https://omninos.life/Social_Integration/termandcondition.html")));

//        view.findViewById(R.id.temsBackImg).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getActivity().onBackPressed();
//            }
//        });
//
//        // Find the WebView by its unique ID
//        WebView webView = view.findViewById(R.id.termsWebView);
//
//        // loading http://www.google.com url in the WebView.
//        webView.loadUrl("https://omninos.life/Social_Integration/termandcondition.html");
//
//        // this will enable the javascript.
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.getSettings().setSafeBrowsingEnabled(true);
//        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
//
//        // WebViewClient allows you to handle
//        // onPageFinished and override Url loading.
//        webView.setWebViewClient(new WebViewClient());



    }

}