package com.live.worldsocialintegrationapp;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.live.worldsocialintegrationapp.Retrofit.AgencyRoot;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.live.worldsocialintegrationapp.utils.CommonUtils;
import com.live.worldsocialintegrationapp.utils.RealPathUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;


public class ApplyForAnchorFragment extends Fragment {

    private Spinner spinner_country_select,get_agency_id;
    private EditText get_name, get_address, get_email_address;
    private String name, number, address, email_address, national_id, agency_id, country, paymentTypeName, paymentMethodName;
    private Button apply_for_host_button;
    RadioButton paymentMethodButton, paymentTypeButton;
    private ImageView imageNationaliId;
    private LinearLayout clickImage;
    private RadioGroup paymentMethod, paymentType;
    ArrayList<String> countryList = new ArrayList<>();
    ArrayList<String> agencyList = new ArrayList<>();
    private String stringPhotoPath = "";
    CompoundButton.OnCheckedChangeListener listener;
    private TextView wowsid,countrytext;
    private EditText editAgencyId;

    TextView date;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_apply_for_anchor, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findId(view);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        date.setText(dtf.format(now));

        country =countrytext.getText().toString();
        agency_id = editAgencyId.getText().toString();
   //     getCountry();
      //  getAgencyCode();
        OnClick(view);
        wowsid.setText( App.getSharedpref().getString("username"));
        if (stringPhotoPath != null) {
            clickImage.setVisibility(View.GONE);
        } else {
            clickImage.setVisibility(View.VISIBLE);
        }
        }

    private void getAgencyCode() {
        new Mvvm().getAgencyCode().observe(requireActivity(), new Observer<AgencyRoot>() {

            @Override
            public void onChanged(AgencyRoot agencyRoot) {
                if (agencyRoot !=null){
                    if (agencyRoot.getStatus()==1){
                        //Toast.makeText(requireContext(), ""+agencyRoot.getMessage(), Toast.LENGTH_SHORT).show();
                        setAdapter2(agencyRoot.getDetails());
                    }else {
                        //Toast.makeText(requireContext(), ""+agencyRoot.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    //Toast.makeText(requireContext(),"Technical issue...",Toast.LENGTH_SHORT).show();
                }
            }

            private void setAdapter2(List<AgencyRoot.Detail> details) {
                for (int i = 0; i < details.size(); i++) {

                    agencyList.add(details.get(i).getAgencyCode());

                }
                ArrayAdapter arrayAdapter = new ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, agencyList);
                get_agency_id.setAdapter(arrayAdapter);
                get_agency_id.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        get_agency_id.setSelection(i);
                        agency_id = get_agency_id.getSelectedItem().toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
        });

    }

    private void OnClick(View viewnew) {

        viewnew.findViewById(R.id.imageBack_applyAgency).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });
        viewnew.findViewById(R.id.imageBack_applyAgency).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });

        imageNationaliId.setOnClickListener(view1 -> {

            int permission = ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE);
            if (permission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);
            } else {
                imageNationaliId.setClickable(false);
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(i, 2);
//                    Intent intent = new Intent(Intent.ACTION_PICK);
//                    intent.setType("image/*");
//                    startActivityForResult(intent, 2);
            }
        });
        apply_for_host_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int paymentMethodId = paymentMethod.getCheckedRadioButtonId();
                int paymentTypeId = paymentType.getCheckedRadioButtonId();
//sumit
//                paymentMethodButton = viewnew.findViewById(paymentMethodId);
//                paymentTypeButton = viewnew.findViewById(paymentTypeId);
//
//                paymentTypeName = paymentTypeButton.getText().toString();
//                paymentMethodName = paymentMethodButton.getText().toString();

                name = get_name.getText().toString();
              //  address = get_address.getText().toString();
         //       email_address = get_email_address.getText().toString();
                agency_id = editAgencyId.getText().toString();

                if (name.length() == 0) {
                    get_name.setError("Add Real Name");
                    get_name.requestFocus();
                } else if (country.length() == 0) {

                    Toast.makeText(requireActivity(), "Select Country", Toast.LENGTH_SHORT).show();
//                } else if (email_address.length() == 0 || !Patterns.EMAIL_ADDRESS.matcher(get_email_address.getText().toString()).matches()) {
//                    get_email_address.setError("Add  Valid Email Address");
//                    get_email_address.requestFocus();
                }
//                } else if (stringPhotoPath == "") {
//                    Toast.makeText(requireActivity(), "Upload National Id", Toast.LENGTH_SHORT).show();
//
//                }
                else if (agency_id.length() == 0) {
                    Toast.makeText(requireActivity(), "Enter agency id", Toast.LENGTH_SHORT).show();
                }
                else {
                    hitApi(name,number,national_id,agency_id);

                }
            }

            private void hitApi(String namee,String numberr,String national_Id,String agencyId) {

                HashMap<String, RequestBody> data = new HashMap<>();
               // Toast.makeText(requireContext(), "ftyfty"+namee, Toast.LENGTH_SHORT).show();
                data.put("userId", CommonUtils.getRequestBodyText(AppConstants.USER_ID));
               // data.put("phone", CommonUtils.getRequestBodyText(numberr));
                data.put("agencyId", CommonUtils.getRequestBodyText(agencyId));
              //  data.put("national_no", CommonUtils.getRequestBodyText(national_Id));
                data.put("name", CommonUtils.getRequestBodyText(namee));
                data.put("country", CommonUtils.getRequestBodyText(country));
                data.put("nationalId", CommonUtils.getRequestBodyText(stringPhotoPath));
                //data.put("nationalId", CommonUtils.getRequestBodyText(stringPhotoPath));
               // data.put("address", CommonUtils.getRequestBodyText(address));
             // data.put("paymentType", CommonUtils.getRequestBodyText(paymentTypeName));
             //   data.put("paymentMethod", CommonUtils.getRequestBodyText(paymentMethodName));
                new Mvvm().getApplyForHost(requireActivity(), data).observe(requireActivity(), new Observer<Map>() {
                    @Override
                    public void onChanged(Map uploadClass) {

                        if (uploadClass.get("status").toString().equalsIgnoreCase("2")) {
//                            GetUserDetailRoot getUserDetailRoot = App.getSharedpref().getModel(AppConstant.USER_INFO, GetUserDetailRoot.class);
//                            getUserDetailRoot.getDetails().setHost_status(uploadClass.get("status").toString());
 //                           App.getSharedpref().saveModel(AppConstant.USER_INFO, getUserDetailRoot);
                            //Creating the LayoutInflater instance
//                            LayoutInflater li = getLayoutInflater();
//                            //Getting the View object as defined in the customtoast.xml file
//                            View layout = li.inflate(R.layout.congradulationdesign,(ViewGroup) getView().findViewById(R.id.custom_toast_layout));
//                            Toast toast = new Toast(getApplicationContext());
//                            toast.setDuration(Toast.LENGTH_SHORT);
//                            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
//                            toast.setView(layout);//setting the view of custom toast layout
//                            toast.show();

                            final Dialog dialog = new Dialog(requireContext());
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setContentView(R.layout.congradulationdesign);
                            dialog.setCanceledOnTouchOutside(true);
                            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            dialog.getWindow().setGravity(Gravity.CENTER);
                            dialog.show();


                            requireActivity().onBackPressed();

                        } else {
                            //Toast.makeText(requireContext(), "" + uploadClass.get("message"), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
//sumit
//    private void getCountry() {
//        new Mvvm().getLiveDataCountryDetails(requireActivity()).observe(requireActivity(), new Observer<CountryRoot>() {
//            @Override
//            public void onChanged(CountryRoot countryClass) {
//                if (countryClass.getSuccess().equalsIgnoreCase("1")) {
//                    setAdapter(countryClass.getDetails());
//                } else {
//                    Toast.makeText(requireContext(), "" + countryClass.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            private void setAdapter(List<CountryRootDetails> details) {
//                for (int i = 0; i < details.size(); i++) {
//
//                    countryList.add(details.get(i).getName());
//
//                }
//                ArrayAdapter arrayAdapter = new ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, countryList);
//                spinner_country_select.setAdapter(arrayAdapter);
//                spinner_country_select.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                        spinner_country_select.setSelection(i);
//                        country = spinner_country_select.getSelectedItem().toString();
//                    }
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> adapterView) {
//
//                    }
//                });
//            }
//        });
//    }
    private void findId(View view) {
       // spinner_country_select = view.findViewById(R.id.spinner_country_select);
        editAgencyId = view.findViewById(R.id.editAgencyid);
        countrytext=view.findViewById(R.id.countrytext);
        wowsid = view.findViewById(R.id.wowsid);
        imageNationaliId = view.findViewById(R.id.imageNationaliId);
        imageNationaliId.setClickable(true);
        clickImage = view.findViewById(R.id.clickImage);
        paymentMethod = view.findViewById(R.id.radioGrp2);
        paymentType = view.findViewById(R.id.radioGrp);
        get_name = view.findViewById(R.id.get_name);
        date = view.findViewById(R.id.date);
        //  get_address = view.findViewById(R.id.get_address);
      //  get_email_address = view.findViewById(R.id.get_email_address);
    //    get_agency_id = view.findViewById(R.id.get_agency_id);
        apply_for_host_button = view.findViewById(R.id.apply_for_host_button);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == Activity.RESULT_OK) {

            assert data != null;
            Uri imageUriPhotos = data.getData();
            imageNationaliId.setClickable(true);
            stringPhotoPath = RealPathUtil.getRealPath(requireActivity(), imageUriPhotos);

            clickImage.setVisibility(View.GONE);
            imageNationaliId.setImageURI(imageUriPhotos);

        } else {
            Toast.makeText(requireContext(), "Image Uploading Cancelled", Toast.LENGTH_SHORT).show();
            imageNationaliId.setClickable(true);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().findViewById(R.id.bottom_lay).setVisibility(View.GONE);
        if (stringPhotoPath != "") {
            clickImage.setVisibility(View.GONE);
        } else {
            clickImage.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        requireActivity().findViewById(R.id.bottom_lay).setVisibility(View.GONE);
    }
}