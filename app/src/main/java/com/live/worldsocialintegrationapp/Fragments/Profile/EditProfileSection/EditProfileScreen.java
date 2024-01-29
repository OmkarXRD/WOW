package com.live.worldsocialintegrationapp.Fragments.Profile.EditProfileSection;

import static android.app.Activity.RESULT_OK;
import static com.facebook.FacebookSdk.getApplicationContext;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;

import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.live.worldsocialintegrationapp.Activites.HomeActivity;

import com.live.worldsocialintegrationapp.Adapters.EditProfileImagesRVAdapter;
import com.live.worldsocialintegrationapp.ModelClasses.GetUserImagesRoot;

import com.live.worldsocialintegrationapp.ModelClasses.UpdateUserProfileRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.App;

import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.live.worldsocialintegrationapp.utils.CommonUtils;
import com.live.worldsocialintegrationapp.utils.RealPathUtil;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

import com.tapadoo.alerter.Alerter;
import com.vanniktech.emoji.EmojiPopup;
import com.vanniktech.emoji.EmojiTextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class EditProfileScreen extends Fragment implements EditProfileImagesRVAdapter.Callback {

    private static final int REQUEST_READ_EXTERNAL_STORAGE = 1;
    private static final int REQUEST_CODE_PERMISSION = 100;
    private static final int REQUEST_PERMISSIONS_CODE = 10001;
    private TextView editProfileDobTV, editProfileGenderTV, editProfileCountryTv, editProfileSaveTV;
    private ImageView editProfileScreenUserImg1, addEmojiImg;
    private EditText editProfileUserName;

    static int CODE_READ_EXTERNAL = 1378;
    static int PICK_IMAGE_PHOTO = 1;
    private int flag = 0;

    private String userName, stringPhotoPath, id, gender = "", dateReq = "", checkUnderAge = "";
    RequestBody rb_About, rb_userId, req_gender, req_dob, req_country, req_bio;
    private UpdateUserProfileRoot.Details details;
    final Calendar myCalendar = Calendar.getInstance();
    CountryCodePicker ccp;
    //    public static int ImageCode = 88, setFirstTimeProfile = 0;
    private int ImageCode = 88, setFirstTimeProfile = 0;
    private RecyclerView imageRV;
    private CardView editProfileCardViewImg;
    private EditText bioEditText;

    private List<GetUserImagesRoot.Detail> imagesList = new ArrayList<>();
    MultipartBody.Part[] imageListMutiPart;
    EditProfileImagesRVAdapter editProfileImagesRVAdapter;
    ArrayList<String> uploadImagesList = new ArrayList<>();
    LinearLayout llTextViews;
    // private ActivityResultLauncher<String> galleryLauncher;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_profile_screen, container, false);

        // Initialize the launcher

        checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE, CODE_READ_EXTERNAL);
        if (hasReadExternalStoragePermission()) {
            // Permission already granted, proceed with your logic to access the image
            // For example, call the method to upload the image
            // uploadImage("/storage/emulated/0/DCIM/Camera/IMG_20230725_110038011.jpg");
        } else {
            // Request the permission
            requestReadExternalStoragePermission();
        }
        if (getArguments() != null && getArguments().containsKey("setFirstTimeProfile")) {
            setFirstTimeProfile = getArguments().getInt("setFirstTimeProfile");
        }

//        galleryLauncher = registerForActivityResult(
//                new ActivityResultContracts.GetContent(),
//                this::handleImageSelection
//        );

        findIds(view);

        FrameLayout firstRoot = new FrameLayout(requireContext());
        View child = getLayoutInflater().inflate(R.layout.layout_target, firstRoot);
        editProfileCardViewImg.setVisibility(View.VISIBLE);
//        Target target = new Target.Builder()
//                .setAnchor(100f,100f)
////                .setAnchor(view.findViewById(R.id.editProfileCardViewImg))
//                .setShape(new Circle(100f))
//                .setEffect(new RippleEffect(100f, 200f, argb(30, 124, 255, 90)))
//                .setOverlay(child)
//
//                .setOnTargetListener(new OnTargetListener() {
//                    @Override
//                    public void onStarted() {
//                        Toast.makeText(requireContext(), "Started", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onEnded() {
//                        Toast.makeText(requireContext(), "Ended", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .build();

        // create spotlight

//        Spotlight spotlight =new Spotlight.Builder(requireActivity())
//                .setContainer(view.findViewById(R.id.container1))
//                .setTargets(target)
//                .setBackgroundColorRes(R.color.spotlightBackground)
//                .setDuration(1000L)
//                .setAnimation(new DecelerateInterpolator(2f))
//                .setOnSpotlightListener(new OnSpotlightListener() {
//                    @Override
//                    public void onStarted() {
//                        Toast.makeText(requireContext(), "Spot light started", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onEnded() {
//                        Toast.makeText(requireContext(), "Spot light Ended", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .build();
//
//        spotlight.start();

//        child.findViewById(R.id.close_spotlight).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                spotlight.finish();
//            }
//        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setData();
//        userImageApi();
        clicks(view);
        dataPicker();
        countryCode();
        backPressed(view);

        llTextViews = view.findViewById(R.id.llTextViews);

        // Request permission when needed

        final EmojiPopup popup = EmojiPopup.Builder
                .fromRootView(view.findViewById(R.id.rootView)).build(editProfileUserName);

        addEmojiImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup.toggle();
            }
        });

    }

    private void checkPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(getActivity(), permission) == -1) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{permission}, requestCode);
            return;
        }
        Toast.makeText(getActivity(), "Permission already granted", Toast.LENGTH_SHORT).show();
        flag = 1;
    }

    private boolean hasReadExternalStoragePermission() {
        return ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;
    }

    private void requestReadExternalStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_READ_EXTERNAL_STORAGE
            );
        }
    }

    private void findIds(View view) {
        editProfileDobTV = view.findViewById(R.id.editProfileDobTV);
        editProfileGenderTV = view.findViewById(R.id.editProfileGenderTV);
        editProfileCountryTv = view.findViewById(R.id.editProfileCountryTv);
        editProfileSaveTV = view.findViewById(R.id.editProfileSaveTV);
        editProfileUserName = view.findViewById(R.id.editProfileUserName);
        imageRV = view.findViewById(R.id.imageRV);
        editProfileScreenUserImg1 = view.findViewById(R.id.editProfileScreenUserImg1);
        editProfileCardViewImg = view.findViewById(R.id.editProfileCardViewImg);
        bioEditText = view.findViewById(R.id.bioEditText);
        addEmojiImg = view.findViewById(R.id.addEmojiImg);
    }


    private void clicks(View view) {

        view.findViewById(R.id.editProfilebackImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        editProfileGenderTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogBox();
            }
        });

            editProfileSaveTV.setOnClickListener(view1 -> {
                //0 means already edited
                if (setFirstTimeProfile == 0) {
                    editProfileApi();//
                } else {
                    if (stringPhotoPath == null) {
                        Alerter.create(requireActivity()).setTitle("Alert").setText("Please Select image").setBackgroundResource(R.drawable.wallet_background).show();
                    } else if (editProfileUserName.getText().toString().trim().isEmpty()) {
                        Alerter.create(requireActivity()).setTitle("Alert").setText("Please enter your name").setBackgroundResource(R.drawable.wallet_background).show();
                    } else if (editProfileDobTV.getText().toString().equalsIgnoreCase("dd/mm/yy")) {
                        Alerter.create(requireActivity()).setTitle("Alert").setText("Please enter the DOB").setBackgroundResource(R.drawable.wallet_background).show();
                    } else if (editProfileGenderTV.getText().toString().trim().isEmpty()) {
                        Alerter.create(requireActivity()).setTitle("Alert").setText("Please select the Gender").setBackgroundResource(R.drawable.wallet_background).show();
                    } else if (bioEditText.getText().toString().trim().isEmpty()) {
                        Alerter.create(requireActivity()).setTitle("Alert").setText("Please enter the Bio").setBackgroundResource(R.drawable.wallet_background).show();
                    } else {
                        if (Integer.parseInt(CommonUtils.ageCalcualte(checkUnderAge)) < 18) {
                            Alerter.create(requireActivity()).setTitle("Alert").setText("You are under 18 teen").setBackgroundResource(R.drawable.wallet_background).show();
                            editProfileDobTV.setError("You are under 18 teen");
                        } else {
                            editProfileApi();
                        }
                    }
                }
            });



        editProfileCardViewImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePick();
            }
        });
    }

    private void editProfileApi() {
        stringToRequestBody();
        Log.d("editProfileApi", "id: "+AppConstants.USER_ID);
        Log.d("editProfileApi", "userName: "+userName);
        Log.d("editProfileApi", "stringPhotoPath: "+stringPhotoPath);
        Log.d("editProfileApi", "editProfileApi: "+gender);
        Log.d("editProfileApi", "gender: "+dateReq);
        Log.d("editProfileApi", "bioEditText: "+bioEditText.getText().toString());
        new Mvvm().updateVendorHVRootModelLiveData(requireActivity(),
                        CommonUtils.getRequestBodyText(AppConstants.USER_ID),
                        CommonUtils.getRequestBodyText(userName),
                        CommonUtils.getFileData(stringPhotoPath,"image"),
                        CommonUtils.getRequestBodyText(gender),
                        CommonUtils.getRequestBodyText(dateReq),
                        CommonUtils.getRequestBodyText("india"),
                        CommonUtils.getRequestBodyText(bioEditText.getText().toString())).observe(requireActivity(), updateUserProfileRoot -> {
                    if (updateUserProfileRoot != null) {
                        if (updateUserProfileRoot.getSuccess().equalsIgnoreCase("1")) {
                            //Toast.makeText(requireContext(), " 1"+ updateUserProfileRoot.getMessage(), Toast.LENGTH_SHORT).show();
                            App.getSharedpref().saveModel("login", updateUserProfileRoot.getDetails());
                            App.getSharedpref().saveString("gender", updateUserProfileRoot.getDetails().getGender());
                            App.getSharedpref().saveString("dob", updateUserProfileRoot.getDetails().getDob());
                            App.getSharedpref().saveString("country", updateUserProfileRoot.getDetails().getCountry());
                            App.getSharedpref().saveString("bio", updateUserProfileRoot.getDetails().getBio());
                            App.getSharedpref().saveString("image",updateUserProfileRoot.getDetails().getImage());
                            getActivity().onBackPressed();
                            setData();
//                            userImageApi();
                            llTextViews.addView(getEmojiTextView());
                            ageCalcualte(updateUserProfileRoot.getDetails().getDob());
                            setFirstTimeProfile = 0;
                        } else {
                            Toast.makeText(requireContext(), "NULL "+ updateUserProfileRoot.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        if (getContext() != null) {
                            Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void userImageApi() {
        editProfileCardViewImg.setVisibility(View.VISIBLE);
        this.id = App.getSharedpref().getString("userId");
        new Mvvm().getUserImages(requireActivity(), id).observe(requireActivity(), new Observer<GetUserImagesRoot>() {
            @Override
            public void onChanged(GetUserImagesRoot getUserImagesRoot) {
                if (getUserImagesRoot != null) {
                    if (getUserImagesRoot.getStatus() == 1) {
                        imagesList = getUserImagesRoot.getDetails();
                        if (imagesList.isEmpty()) {
                            editProfileCardViewImg.setVisibility(View.VISIBLE);
                            imageRV.setVisibility(View.GONE);
                        } else {
                            editProfileCardViewImg.setVisibility(View.GONE);
                            imageRV.setVisibility(View.VISIBLE);
                            App.getSharedpref().saveString("image", imagesList.get(0).getImage());
                            if (getContext() != null) {
                                editProfileImagesRVAdapter = new EditProfileImagesRVAdapter(imagesList, requireContext(), EditProfileScreen.this);
                            }
                            imageRV.setAdapter(editProfileImagesRVAdapter);
//                        if (setFirstTimeProfile==0){
//                            editProfileImagesRVAdapter.notifyDataSetChanged();
//                        }
                        }
                    } else {
                    }
                }
            }
        });

    }

    private void pickImg() {
        openCameraOrGallery();
    }

    private void setData() {
        editProfileCountryTv.setText(App.getSharedpref().getString("countryName"));
        if (setFirstTimeProfile == 0) {

            if (App.getSharedpref().getString("name").length() == 0) {
                editProfileUserName.setText("Username");
            } else {
                editProfileUserName.setText(App.getSharedpref().getString("name"));
            }
            editProfileGenderTV.setText(App.getSharedpref().getString("gender"));
            editProfileDobTV.setText(getDateFormat(App.getSharedpref().getString("dob")));
            bioEditText.setText(App.getSharedpref().getString("bio"));
        } else {
            editProfileDobTV.setText("dd/mm/yy");
        }

    }

    private void dataPicker() {
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, day);
                updateLabel();
            }
        };

        editProfileDobTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), date, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));

                //following line to restrict future date selection
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();

                Button buttonPositive = datePickerDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                buttonPositive.setTextColor(ContextCompat.getColor(requireContext(), R.color.green));
                Button nagitiveButton = datePickerDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                nagitiveButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.green));
            }
        });
    }

    private void updateLabel() {
        //this code for send to api
        String myFormat = "yy/MM/dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        dateReq = dateFormat.format(myCalendar.getTime());
        checkUnderAge = dateFormat.format(myCalendar.getTime());


        //this code for show the format of the date according to client
        String setFormatToEditTextView = "dd/MM/yy";
        SimpleDateFormat setfirEduit = new SimpleDateFormat(setFormatToEditTextView, Locale.US);
        editProfileDobTV.setText(setfirEduit.format(myCalendar.getTime()));

    }

    private void countryCode() {
//        ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
//            @Override
//            public void onCountrySelected(Country selectedCountry) {
//                Toast.makeText(getContext(), "phone code"+selectedCountry.getName(), Toast.LENGTH_SHORT).show();
//                countryName=selectedCountry.getName();
//               editProfileCountryTv.setText(selectedCountry.getName());
//                App.getSharedpref().saveString("country",selectedCountry.getName());
//            }
//        });

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

            if (selectedId == -1) {
                Toast.makeText(requireContext(), "Nothing selected", Toast.LENGTH_SHORT).show();
            } else {
                gender = genderradioButton.getText().toString();
                editProfileGenderTV.setText(gender);

                dialog_share.dismiss();
            }

        });
    }

    private void stringToRequestBody() {
        userName = editProfileUserName.getText().toString();
    }

    private void openCameraOrGallery() {
        Dialog dialog_share = new Dialog(getContext());
        dialog_share.setContentView(R.layout.open_gallery_camera_dialog_box);
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog_share.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog_share.setCanceledOnTouchOutside(false);
        Window window = dialog_share.getWindow();
        window.setGravity(Gravity.CENTER);
        dialog_share.show();

        LinearLayout cameraLinearLyaout = dialog_share.findViewById(R.id.cameraLinearLyaout);
        cameraLinearLyaout.setOnClickListener(view -> {
            cameraPick();
            dialog_share.dismiss();
        });

        LinearLayout galleryLinearLayout = dialog_share.findViewById(R.id.galleryLinearLayout);
        galleryLinearLayout.setOnClickListener(view -> {

            imagePick();
            dialog_share.dismiss();

        });
    }

    private void imagePick() {
        ImagePicker.Companion.with(this).galleryOnly().crop().start();
    }

    private void cameraPick() {
        ImagePicker.Companion.with(this).cameraOnly().crop().start();
    }

    private void ImagePick() {
        imagePick();
    }

    @Override
    public void callback(String value) {
        if (value.equalsIgnoreCase("1")) {
            int currentApiVersion = Build.VERSION.SDK_INT;
            if (currentApiVersion >= Build.VERSION_CODES.TIRAMISU) {
                String[] permissions = {
                        Manifest.permission.READ_MEDIA_IMAGES,
                };

                ActivityCompat.requestPermissions(requireActivity(), permissions, REQUEST_PERMISSIONS_CODE);
            } else {
                ImagePick();
            }
        } else {
        }
    }

    private EmojiTextView getEmojiTextView() {

        EmojiTextView tvEmoji = (EmojiTextView) LayoutInflater
                .from(getApplicationContext())
                .inflate(R.layout.emoji_layout_file, llTextViews, false);
        tvEmoji.setText(editProfileUserName.getText().toString());
        return tvEmoji;
    }

    private void ageCalcualte(String age) {
        int age1 = 0;
        String year = "", month = "", date = "";

        for (int i = 0; i < age.length(); i++) {
            if (i == 0 || i == 1) {
                year += age.charAt(i);
            } else if (i == 3 || i == 4) {
                month += age.charAt(i);
            } else if (i == 6 || i == 7) {
                date += age.charAt(i);
            }
        }

        if (year.length() == 2) {
            if (Integer.parseInt(year) <= 22 && Integer.parseInt(year) >= 0) {
                age1 = CommonUtils.getAge(Integer.parseInt("20" + year), Integer.parseInt(month), Integer.parseInt(date));
            } else {
                age1 = CommonUtils.getAge(Integer.parseInt("19" + year), Integer.parseInt(month), Integer.parseInt(date));
            }

        } else if (year.length() == 4) {
            age1 = CommonUtils.getAge(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(date));
        }
        App.getSharedpref().saveString("age", String.valueOf(age1));
    }


    public String getDateFormat(String dataofbirth) {
        String correctFormat = "";
        String year = "", month = "", date = "";
        for (int i = 0; i < dataofbirth.length(); i++) {
            if (i == 0 || i == 1) {
                year += dataofbirth.charAt(i);
            } else if (i == 3 || i == 4) {
                month += dataofbirth.charAt(i);
            } else if (i == 6 || i == 7) {
                date += dataofbirth.charAt(i);
            }
        }
        correctFormat = String.valueOf(date + "/" + month + "/" + year);

        return correctFormat;
    }


    private void backPressed(View sView) {
        if (setFirstTimeProfile == 1) {

            sView.setFocusableInTouchMode(true);
            sView.requestFocus();
            sView.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View view, int i, KeyEvent keyEvent) {
                    if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                        if (i == KeyEvent.KEYCODE_BACK) {

                            final Dialog dialog = new Dialog(requireActivity());
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setCancelable(true);
                            dialog.setContentView(R.layout.exit_app_dialog);
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                            TextView yesBtn = dialog.findViewById(R.id.yesText);
                            TextView noBtn = dialog.findViewById(R.id.noText);

                            yesBtn.setOnClickListener(view1 -> {
                                requireActivity().finishAffinity();
                            });
                            noBtn.setOnClickListener(view1 -> {
                                dialog.dismiss();
                            });
                            dialog.show();
                            return true;
                        }
                    }
                    return false;
                }
            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == ImagePicker.REQUEST_CODE) {
            assert data != null;
            Uri imageUriPhotos = data.getData();
            assert imageUriPhotos != null;
            stringPhotoPath =  imageUriPhotos.getPath();
            editProfileScreenUserImg1.setImageURI(imageUriPhotos);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        HomeActivity.binding.bottomLay.getRoot().setVisibility(View.GONE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_PERMISSIONS_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                ImagePick();
            } else {
                Toast.makeText(requireContext(), "REQUEST REJECTED", Toast.LENGTH_SHORT).show();
            }
        }
    }
}