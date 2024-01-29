package com.live.worldsocialintegrationapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.live.worldsocialintegrationapp.databinding.FragmentEditProfileBinding;
import com.live.worldsocialintegrationapp.utils.App;
import com.rilixtech.widget.countrycodepicker.Country;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class Fragment_Edit_profile extends Fragment {
    FragmentEditProfileBinding binding;
    private TextView hospital_name_id_hv;
    private CircleImageView img_hospital_id;
    private MultipartBody.Part multipart_HospitalPhoto;
    private String userName, stringPhotoPath, id,gender="";
    private RequestBody rb_About, rb_userId, rb_Photos, req_gender, req_dob, req_country;
    final Calendar myCalendar= Calendar.getInstance();
    String countryName="";




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEditProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findIds(view);
        clicks(view);
        dataPicker();
        setData();
        countryCode();
    }

    private void findIds(View view) {

        hospital_name_id_hv=view.findViewById(R.id.hospital_name_id_hv);
        img_hospital_id=view.findViewById(R.id.img_hospital_id);

    }

    private void setData() {

        if(App.getSharedpref().getString("name").length()==0){
            hospital_name_id_hv.setText("WorldSocialIntegration");
        } else{
            hospital_name_id_hv.setText(App.getSharedpref().getString("name"));
        }
        Glide.with(requireContext()).load(App.getSharedpref().getString("image")).error(R.drawable.demo_user_profile_img).into(img_hospital_id);
        binding.edtGender.setText(App.getSharedpref().getString("gender"));
        binding.edtDOB.setText(App.getSharedpref().getString("dob"));
        binding.edtPhoneHv.setText(App.getSharedpref().getString("name"));
        binding.editCountryTV.setText(App.getSharedpref().getString("country"));
    }

    private void clicks(View view) {

        binding.icPlusId.setOnClickListener(view1 -> {
            ImagePicker.Companion.with(this).galleryOnly().cropSquare().compress(1024).start();
        });

        binding.editProfileBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().onBackPressed();
            }
        });



        binding.btnSaveProfile.setOnClickListener(view1 -> {
            stringToRequestBody();

//            new Mvvm().updateVendorHVRootModelLiveData(requireActivity(), rb_userId, rb_About, imageListMutiPart, req_gender, req_dob, req_country)
//            .observe(requireActivity(), new Observer<UpdateUserProfileRoot>() {
//                        @Override
//                        public void onChanged(UpdateUserProfileRoot updateUserProfileRoot) {
//                            if (updateUserProfileRoot.getSuccess().equalsIgnoreCase("1")) {
//
//                                App.getSharedpref().saveModel("login", updateUserProfileRoot.getDetails());
//                                App.getSharedpref().saveString("name", updateUserProfileRoot.getDetails().getName());
//                                App.getSharedpref().saveString("image", updateUserProfileRoot.getDetails().getImage());
//                                App.getSharedpref().saveString("gender", updateUserProfileRoot.getDetails().getGender());
//                                App.getSharedpref().saveString("dob", updateUserProfileRoot.getDetails().getDob());
//                                App.getSharedpref().saveString("country", updateUserProfileRoot.getDetails().getCountry());
//                                setData();
//
//                                Toast.makeText(requireContext(), "" + updateUserProfileRoot.getMessage(), Toast.LENGTH_SHORT).show();
//                            } else {
//                                Toast.makeText(requireContext(), "" + updateUserProfileRoot.getMessage(), Toast.LENGTH_SHORT).show();
//
//                            }
//                        }
//                    });
        });

        binding.edtGender.setOnClickListener(view1 -> {
            DialogBox();
        });
    }

    private void stringToRequestBody() {
        userName = binding.edtPhoneHv.getText().toString();
        id = App.getSharedpref().getString("userId");

        rb_About = RequestBody.create(MediaType.parse("text/plain"), userName);
        rb_userId = RequestBody.create(MediaType.parse("text/plain"), id);

        req_gender = RequestBody.create(MediaType.parse("text/plain"),gender);
        req_dob = RequestBody.create(MediaType.parse("text/plain"), binding.edtDOB.getText().toString().trim());
//        req_country = RequestBody.create(MediaType.parse("text/plain"), binding.edtCountry.getText().toString().trim());
        req_country = RequestBody.create(MediaType.parse("text/plain"),"");


        if (stringPhotoPath != null) {
//            File file = new File(stringPhotoPath);
//            rb_Photos = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//            multipart_HospitalPhoto = MultipartBody.Part.createFormData("image", file.getName(), rb_Photos);
//

//            imageListMutiPart = new MultipartBody.Part[imagesList.size()];
//
//            for (int i = 0; i < imagesList.size(); i++) {
//
//                File file5 = new File(imagesList.get(i));
//
//                RequestBody qualification = RequestBody.create(MediaType.parse("multipart/form-data"), file5); /*-------> create a request body for each image file */
//
//                imageListMutiPart[i] = MultipartBody.Part.createFormData("qualification[]", file.getName(), qualification);  /*----> pass this in multipart array*/

//            }
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {

            assert data != null;
            Uri imageUriPhotos = data.getData();
            stringPhotoPath = imageUriPhotos.getPath();
            //Toast.makeText(getContext(), stringPhotoPath, Toast.LENGTH_SHORT).show();
            binding.imgHospitalId.setImageURI(imageUriPhotos);

        } else if (resultCode == ImagePicker.RESULT_ERROR) {

            //Toast.makeText(requireContext(), ImagePicker.RESULT_ERROR, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(requireContext(), "Image Uploading Cancelled", Toast.LENGTH_SHORT).show();

        }
    }

    private void DialogBox() {
        Dialog dialog_share = new Dialog(getContext());
        dialog_share.setContentView(R.layout.select_gender_dialog_layout);
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog_share.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog_share.setCanceledOnTouchOutside(false);
        Window window = dialog_share.getWindow();
        window.setGravity(Gravity.CENTER);
        dialog_share.show();

        RadioGroup groupRadio = dialog_share.findViewById(R.id.groupRadio);

        AppCompatButton CancleBtn = dialog_share.findViewById(R.id.Cancel);
        CancleBtn.setOnClickListener(view -> {
            dialog_share.dismiss();
        });

        AppCompatButton saveBtn = dialog_share.findViewById(R.id.save);
        saveBtn.setOnClickListener(view -> {

            int selectedId = groupRadio.getCheckedRadioButtonId();
            RadioButton genderradioButton = (RadioButton) dialog_share.findViewById(selectedId);

            if(selectedId==-1){
                Toast.makeText(requireContext(),"Nothing selected", Toast.LENGTH_SHORT).show();
            }
            else{
                //Toast.makeText(requireContext(),genderradioButton.getText().toString(), Toast.LENGTH_SHORT).show();
                gender = genderradioButton.getText().toString();
                binding.edtGender.setText(gender);

                dialog_share.dismiss();
            }

        });
    }


    private void dataPicker() {

        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };

        binding.edtDOB.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            new DatePickerDialog(requireContext(),date,myCalendar.get(Calendar.YEAR),
                    myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        }
    });
}

    private void updateLabel(){
        String myFormat="dd/MM/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        binding.edtDOB.setText(dateFormat.format(myCalendar.getTime()));
    }

    private void countryCode() {
        binding.ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected(Country selectedCountry) {
//                Toast.makeText(getContext(), "phone code"+selectedCountry.getName(), Toast.LENGTH_SHORT).show();
                countryName=selectedCountry.getName();
                binding.editCountryTV.setText(selectedCountry.getName());
                App.getSharedpref().saveString("country",selectedCountry.getName());
            }
        });

    }

}