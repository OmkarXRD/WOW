package com.live.worldsocialintegrationapp.Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.phonepe.intent.sdk.api.B2BPGRequest;
import com.phonepe.intent.sdk.api.B2BPGRequestBuilder;
import com.phonepe.intent.sdk.api.PhonePe;
import com.phonepe.intent.sdk.api.PhonePeInitException;
import com.phonepe.intent.sdk.api.UPIApplicationInfo;
import com.phonepe.intent.sdk.api.models.PhonePeEnvironment;

import org.json.JSONException;
import org.json.JSONObject;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class PhonePeActivity extends AppCompatActivity {

    private String amountId, amount,amountCoin,userMobileNumber,payloadBase64,checksum;
    private AppCompatButton payBTN;
    String merchantId = "WOWSONLINE";
    private int getAmount = 0;
    String apiKey  = "c60f21de872149f797e2d59fafb3790e";
    UUID merchantTransactionId = UUID.randomUUID();
    String apiEndPoint = "/pg/v1/pay";
    private static int B2B_PG_REQUEST_CODE = 1;
    B2BPGRequest b2BPGRequestBuilder;
//    private List<UPIApplicationInfo> upiApps;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pohon_pe);
        payBTN = findViewById(R.id.payBTN);

        getData();
        phonePe();

    }

    private void phonePe() {
        try {
            PhonePe.init(this, PhonePeEnvironment.UAT_SIMULATOR, merchantId, String.valueOf(merchantTransactionId));
            String string_signature = PhonePe.getPackageSignature();
            Log.d("getData", "string_signature: "+string_signature);
            List<UPIApplicationInfo> upiApps = PhonePe.getUpiApps();
        } catch (PhonePeInitException exception) {
            exception.printStackTrace();
        }

        try {
            setupPaymentData();
            setupB2BPGRequestBuilder();
        } catch (Exception e) {
            Log.d("getData", "Error in starting PhonePe Checkout: " + e.getMessage());
        }

        payBTN.setOnClickListener(v -> {
            try {
                startPhonePeCheckout();
            } catch (PhonePeInitException e) {
                // Handle exception
            }
        });
    }

    private void setupPaymentData() throws JSONException {
        Log.d("getData", "merchantTransactionId: " + apiKey);
        Log.d("getData", "merchantId: " + merchantId);
        Log.d("getData", "amount: " + getAmount * 100);
        Log.d("getData", "userMobileNumber: " + userMobileNumber);
        Log.d("getData", "merchantTransactionId: " + merchantTransactionId);

        JSONObject data = new JSONObject();
        data.put("merchantTransactionId", merchantTransactionId);
        data.put("merchantId", merchantId);
        data.put("appId", apiKey);
        data.put("amount", getAmount * 100);
        data.put("userMobileNumber", userMobileNumber);
        data.put("CallbackUrl", "https://xrdsimulators.tech/wow_project/index.php/");

        JSONObject paymentInstrument = new JSONObject();
        paymentInstrument.put("type", "PAY_PAGE");
        paymentInstrument.put("target", "com.phonepe.simulator");

        data.put("paymentInstrument", paymentInstrument);

        JSONObject deviceContext = new JSONObject();
        deviceContext.put("deviceOS", "ANDROID");

        data.put("deviceContext", deviceContext);

        payloadBase64 = android.util.Base64.encodeToString(
                data.toString().getBytes(Charset.defaultCharset()), android.util.Base64.NO_WRAP
        );

        checksum = sha256(payloadBase64 + apiEndPoint + apiKey) + "###1";

        Log.d("getData", "payloadBase64: " + payloadBase64);
        Log.d("getData", "checksum: " + checksum);



    }

    private void setupB2BPGRequestBuilder() {
        b2BPGRequestBuilder = new B2BPGRequestBuilder()
                .setData(payloadBase64)
                .setUrl(apiEndPoint)
                .setChecksum(checksum)
                .callbackUrl("https://xrdsimulators.tech/wow_project/index.php/")
                .build();
    }

    private void startPhonePeCheckout() throws PhonePeInitException {
        Log.d("getData", "b2BPGRequestBuilder: " + b2BPGRequestBuilder);
        Log.d("getData", "B2B_PG_REQUEST_CODE: " + B2B_PG_REQUEST_CODE);

        startActivityForResult(PhonePe.getImplicitIntent(this, b2BPGRequestBuilder, "com.phonepe.simulator"), B2B_PG_REQUEST_CODE);
    }

    public static String sha256(String input) {
        byte[] bytes = input.getBytes(StandardCharsets.UTF_8);

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(bytes);

            StringBuilder result = new StringBuilder();
            for (byte b : digest) {
                result.append(String.format("%02x", b));
            }

            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // Handle the exception as needed
            return "";
        }
    }
    private void getData() {

        amountId = getIntent().getStringExtra("amountId");
        amount = getIntent().getStringExtra("amount");
        amountCoin = getIntent().getStringExtra("amountCoin");
        userMobileNumber = getIntent().getStringExtra("phoneNumber");


        getAmount =  Integer.parseInt(amount);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("getData", "requestCode: " + requestCode);
        if (requestCode == B2B_PG_REQUEST_CODE) {
            // This is a UI callback and indicates only the completion of the UI flow.
            StringBuilder result = new StringBuilder(); // Initialize the StringBuilder
            Log.d("getData", "resultCode: " + resultCode);

            if (resultCode != Activity.RESULT_CANCELED) {
                Log.i("getData", "Result OK");
                Log.i("getData", "Data UserInfo: " + data.getData().getUserInfo());
                hitapi(data);
            } else {
                if (data != null && data.getExtras() != null) {
                    String errorCode = data.getExtras().getString("key_error_code");
                    String errorResult = data.getExtras().getString("key_error_result");

                    Log.i("getData", "Error Code: " + errorCode.toString());
                    Log.i("getData", "Error Result: " + errorResult);

                    // Handle the error appropriately based on the error code and message.
                }
            }
        }
    }

    private void hitapi(Intent data) {

//        new Mvvm().saveTransaction(AppConstants.USER_ID,merchantId, String.valueOf(merchantTransactionId),amount,d,"","").observe(PhonePeActivity.this, response ->{
//            if (response !=null){
//                if (response.success){
//                    Toast.makeText(this, "1 :-"+response.getMessage(), Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(this, "0 :-"+response.getMessage(), Toast.LENGTH_SHORT).show();
//
//                }
//            }else {
//                Toast.makeText(this, "Technical issue...", Toast.LENGTH_SHORT).show();
//
//            }
//        });
    }


    private void checkStatus() {
        String xVerify = sha256("hermes/pg/v1/pay" + merchantId + "/" + merchantId + apiKey) + "###1";
        Log.d("getData", "onCreate  xverify : $xVerify");


    }

}




//        try {
//            PhonePe.fetchPhonePeUserDetails(apiUrl, checksum, new DataListener<UserDetails>() {
//                @Override
//                public void onSuccess(UserDetails userDetails) {
//                    /* Process the info */
//                }
//
//                @Override
//                public void onFailure(ErrorInfo error) {
//
//                }
//            });
//        } catch(PhonePeInitException e){
//        }
//        new Mvvm().saveTransaction(AppConstants.USER_ID,merchantId,"",amount,"","","").observe(PhonePeActivity.this,response ->{
//            if (response !=null){
//                if (response.success){
//                    Toast.makeText(this, "1 :-"+response.getMessage(), Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(this, "0 :-"+response.getMessage(), Toast.LENGTH_SHORT).show();
//
//                }
//            }else {
//                Toast.makeText(this, "Technical issue...", Toast.LENGTH_SHORT).show();
//
//            }
//        });

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == B2B_PG_REQUEST_CODE) {
//            //This is UI callback and indicates only about completion of UI flow.
//            StringBuilder result = new StringBuilder();
//            if (resultCode != Activity.RESULT_CANCELED) {
//                Log.i("getData", "Result Cancelled : " + resultCode);
//                Log.i("getData", "Data : " + data.getData().getUserInfo());
//
//            }else {
//                Toast.makeText(this, "szdfxgfchgvjhbjn", Toast.LENGTH_SHORT).show();
//                if (data != null && data.getExtras() != null && data.getExtras().keySet().size() > 0)
//                    for (String key : data.getExtras().keySet())
//                        result.append(key).append(" = ").append(data.getExtras().get(key)).append("\n");
//                Log.i("getData", "result: " + result);
//            }
//        }
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1) {
//            Log.d("getData", "data: " + data);
//            Log.d("getData", "data-getData: " + data.getData());
//
//            checkStatus();
//
//        }
//    }