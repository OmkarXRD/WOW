package com.live.worldsocialintegrationapp.RazorPay;

import android.app.Activity;
import android.os.Bundle;



import com.razorpay.Checkout;

public class SomeEarlierMerchantActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String samount = "100";

        // rounding off the amount.
        int amount = Math.round(Float.parseFloat(samount) * 100);

        Checkout checkout = new Checkout();

        checkout.setKeyID("<YOUR_KEY_ID>");
        Checkout.preload(getApplicationContext());





    }
}
