package com.live.worldsocialintegrationapp.RazorPay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.live.worldsocialintegrationapp.Activites.HomeActivity;
import com.live.worldsocialintegrationapp.Fragments.Profile.ProfileMainFragment;
import com.live.worldsocialintegrationapp.Fragments.Profile.RechargePackage.RechargeCointsFragment;
import com.live.worldsocialintegrationapp.ModelClasses.Wallet.AddWalletMoneyRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.razorpay.Checkout;
import com.razorpay.ExternalWalletListener;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONException;
import org.json.JSONObject;


public class PaymentActivity extends AppCompatActivity implements PaymentResultWithDataListener {

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


    }


    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {

        new Mvvm().addMoneyToWallet(PaymentActivity.this,AppConstants.USER_ID,itemId,orderId,paymentData.getPaymentId(),paymentData.getSignature()).observe(PaymentActivity.this,
                new Observer<AddWalletMoneyRoot>() {
            @Override
            public void onChanged(AddWalletMoneyRoot addWalletMoneyRoot) {

                if(addWalletMoneyRoot.getSuccess().equalsIgnoreCase("1")){

                    Toast.makeText(PaymentActivity.this, "Successfully payment " + s, Toast.LENGTH_SHORT).show();

                    onBackPressed();

                }else{
                    Toast.makeText(PaymentActivity.this, "not done payment " + s, Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
            }
        });

    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {

        Toast.makeText(this, "Error " + s, Toast.LENGTH_SHORT).show();
        onBackPressed();
    }


    public void startPayment() {
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        final Activity activity = this;

        final Checkout co = new Checkout();

        co.setKeyID(key);

        EditText etCustomOptions = new EditText(this);

        if (!TextUtils.isEmpty(etCustomOptions.getText().toString())) {

            try {
                JSONObject options = new JSONObject(etCustomOptions.getText().toString());
                co.open(activity, options);
            } catch (JSONException e) {
                Log.i("Razorpay","zzzzzzzzzzzzzzz 111");
                Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

        } else {
            try {
                JSONObject options = new JSONObject();
                options.put("name", App.getSharedpref().getString("name"));
                options.put("description", "Demoing Charges");
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
                   preFill.put("email", "test@razorpay.com");
               }else{
                   preFill.put("email", email);
               }
                if(phone.isEmpty()){
                    preFill.put("contact", "9999999999");
                }else{
                    preFill.put("contact", phone);
                }

                options.put("prefill", preFill);

                co.open(activity, options);
            } catch (Exception e) {
                Log.i("Razorpay","zzzzzzzzzzzzzzz 22222");
                Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }


}