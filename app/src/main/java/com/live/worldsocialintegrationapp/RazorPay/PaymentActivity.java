package com.live.worldsocialintegrationapp.RazorPay;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.live.worldsocialintegrationapp.Activites.HomeActivity;
import com.live.worldsocialintegrationapp.Fragments.Profile.ProfileMainFragment;
import com.live.worldsocialintegrationapp.Fragments.Profile.RechargePackage.RechargeCointsFragment;
import com.live.worldsocialintegrationapp.ModelClasses.Wallet.AddWalletMoneyRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.databinding.FragmentProfileMainBinding;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.razorpay.Checkout;
import com.razorpay.ExternalWalletListener;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultListener;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONException;
import org.json.JSONObject;


public class PaymentActivity extends AppCompatActivity implements PaymentResultListener {

    private static final String TAG = "check";
    private AlertDialog.Builder alertDialogBuilder;
    String orderId = "", key = "", price = "",itemId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Checkout.preload(getApplicationContext());

        if (getIntent() != null) {

            orderId = getIntent().getStringExtra("orderId");
            key = getIntent().getStringExtra("key");
            price = getIntent().getStringExtra("price");
            itemId=getIntent().getStringExtra("itemId");

        }
        startPayment();
    }


    public synchronized void makePayment() {

        // secret key -->  GnsTOAh5On4a1JTm8Kb40r4Y

        //    checkout.setKeyID("rzp_live_mltyt2YlNJ6Olg");  //live key
//        checkout.setKeyID("rzp_test_usEmd5LTJQKCTA");  //test key

        //order_Nb9aC2ilbW0OSN
        //order_Nb9aVE2RaQdV1z
    }


//    @Override
//    public void onPaymentSuccess(String s, PaymentData paymentData) {
//        Log.i("Razorpayzzzzzzzz","zzzz "+paymentData.getSignature());
//        Log.i("Razorpayzzzzzzzz","zzzz "+paymentData.getPaymentId());
//        Log.i("Razorpayzzzzzzzz","zzzz "+orderId);
//        Log.i("Razorpayzzzzzzzz","zzzz "+itemId);
//        Log.i("Razorpayzzzzzzzz","zzzz "+AppConstants.USER_ID);
//        new Mvvm().addMoneyToWallet(PaymentActivity.this,AppConstants.USER_ID,itemId,orderId,paymentData.getPaymentId(),paymentData.getSignature()).observe(PaymentActivity.this,
//                new Observer<AddWalletMoneyRoot>() {
//            @Override
//            public void onChanged(AddWalletMoneyRoot addWalletMoneyRoot) {
//
//                if(addWalletMoneyRoot.getSuccess().equalsIgnoreCase("1")){
//
//                    //Toast.makeText(PaymentActivity.this, "Payment Successful" + s, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(PaymentActivity.this, "Payment Successful", Toast.LENGTH_SHORT).show();
//
//                    onBackPressed();
//
//                }else{
//                    Toast.makeText(PaymentActivity.this, "Payment Failed" + s, Toast.LENGTH_SHORT).show();
//                    onBackPressed();
//                }
//            }
//        });
//
//    }

//    @Override
//    public void onPaymentError(int i, String s, PaymentData paymentData) {
//        Log.i("Razorpayzzzzzzzz","zzzzzzzzzzzzzzz zzzzz on payment error " + s );
//        Toast.makeText(this, "Error " + s, Toast.LENGTH_SHORT).show();
//        onBackPressed();
//    }


    public void startPayment() {
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */


        final Checkout co = new Checkout();

        co.setKeyID(key);
        final Activity activity = this;

        EditText etCustomOptions = new EditText(this);

        if (!TextUtils.isEmpty(etCustomOptions.getText().toString())) {
            try {
                JSONObject options = new JSONObject(etCustomOptions.getText().toString());
                co.open(activity, options);
            } catch (JSONException e) {
                Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

        } else {
            try {

                JSONObject options = new JSONObject();
                options.put("name", App.getSharedpref().getString("name"));
                options.put("description", "Demo Charges");
                //options.put("order_id", orderId);
                options.put("send_sms_hash", true);
                options.put("allow_rotation", true);
                //You can omit the image option to fetch the image from dashboard
                options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
                options.put("currency", "INR");

                options.put("amount", Integer.parseInt(price)*100);

                JSONObject preFill = new JSONObject();

               String email= App.getSharedpref().getString("email").toString();
               String phone= App.getSharedpref().getString("phone").toString();

               if(email.isEmpty()){
                   preFill.put("email", "");
               }else{
                   preFill.put("email", email);
               }
                if(phone.isEmpty()){
                    preFill.put("contact", "");
                }else{
                    preFill.put("contact", phone);
                }

                options.put("prefill", preFill);

                co.open(activity, options);
            } catch (Exception e) {
                Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onPaymentSuccess(String s) {
        //Log.i("Razorpayzzzzzzzz","zzzz "+paymentData.getSignature());
        //Log.i("Razorpayzzzzzzzz","zzzz "+paymentData.getPaymentId());
        Log.i("Razorpayzzzzzzzz","zzzz "+orderId);
        Log.i("Razorpayzzzzzzzz","zzzz "+itemId);
        Log.i("Razorpayzzzzzzzz","zzzz "+AppConstants.USER_ID);


        //need to remove the razorpay_payment_id and razorpay_signature sending static at the moment
        new Mvvm().addMoneyToWallet(PaymentActivity.this,AppConstants.USER_ID,itemId,orderId,"123123","asdad123123").observe(PaymentActivity.this,
                new Observer<AddWalletMoneyRoot>() {
                    @Override
                    public void onChanged(AddWalletMoneyRoot addWalletMoneyRoot) {

                        if(addWalletMoneyRoot.getSuccess().equalsIgnoreCase("1")){
                            //Toast.makeText(PaymentActivity.this, "Payment Successful" + s, Toast.LENGTH_SHORT).show();
                            Toast.makeText(PaymentActivity.this, "Payment Successful", Toast.LENGTH_SHORT).show();
                            onBackPressed();

                        }else{
                            Toast.makeText(PaymentActivity.this, "Payment Failed" + s, Toast.LENGTH_SHORT).show();
                            onBackPressed();
                        }
                    }
                });
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Error " + s, Toast.LENGTH_SHORT).show();
        onBackPressed();
    }
}