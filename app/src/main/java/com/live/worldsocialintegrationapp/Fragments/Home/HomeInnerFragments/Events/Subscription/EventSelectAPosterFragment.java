package com.live.worldsocialintegrationapp.Fragments.Home.HomeInnerFragments.Events.Subscription;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.ModelClasses.Events.CreateEventRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.databinding.FragmentEventSelectAPosterBinding;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.live.worldsocialintegrationapp.utils.CommonUtils;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class EventSelectAPosterFragment extends Fragment {

    FragmentEventSelectAPosterBinding binding;
    private String time, topic, des, type;
    RequestBody reqType;

    private int ImageCode = 101;
    private File file;
    private String ImagePath;
    private MultipartBody.Part familyImg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_event_select_a_poster, container, false);
        binding = FragmentEventSelectAPosterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CommonUtils.disableBottomNavigation(requireActivity());
        setonClicks();
        getDataFromBundle(view);
        setDataOfUser();
    }

    private void setDataOfUser() {
        binding.eventSelectPostNameTv.setText(App.getSharedpref().getString("name"));
        Glide.with(binding.eventSelectPostUserImg.getContext()).load(App.getSharedpref().getString("image")).error(R.drawable.demo_user_profile_img).into(binding.eventSelectPostUserImg);
        binding.eventSelectPostTopicTv.setText(topic);
        binding.eventSelectPostStartTimeTv.setText(time);
        binding.eventSelectPostDesTv.setText(des);
    }

    private void getDataFromBundle(View view) {

        if (getArguments() != null) {
            time = getArguments().getString("eventStartTimeTv");
            des = getArguments().getString("eventsRulesEdtex");
            topic = getArguments().getString("eventTopicEdtx");
            type = getArguments().getString("type");
            Toast.makeText(requireContext(), "time "+time, Toast.LENGTH_SHORT).show();
            Toast.makeText(requireContext(), "des "+des, Toast.LENGTH_SHORT).show();
            Toast.makeText(requireContext(), "topic "+topic, Toast.LENGTH_SHORT).show();
            Toast.makeText(requireContext(), "type "+type, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(requireContext(), "Argmts null", Toast.LENGTH_SHORT).show();
        }
    }


    private void setonClicks() {
        binding.selectPosterBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
//                requireActivity().getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.frame_holder, new FragmentUserProfile()).addToBackStack(null).commit();
            }
        });

        binding.ImgeSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImg();
            }
        });
        binding.postEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createEventApi();
            }
        });
    }

    private void createEventApi() {

//        Log.d("EVENTSELECTPOST","type : "+topic);
//        Log.d("EVENTSELECTPOST","type : "+des);
//        Log.d("EVENTSELECTPOST","type : "+AppConstants.USER_ID);
//        Log.d("EVENTSELECTPOST","type : "+time);
//        Log.d("EVENTSELECTPOST","type : "+type);


        //dateTimeFormat y-m-d  h:m:s
        RequestBody req_Topic = RequestBody.create(MediaType.parse("text/plain"), topic);
        RequestBody req_Des = RequestBody.create(MediaType.parse("text/plain"), des);
        RequestBody userId = RequestBody.create(MediaType.parse("text/plain"), AppConstants.USER_ID);
        RequestBody reqTime = RequestBody.create(MediaType.parse("text/plain"), time);
        if(type != null){
             reqType = RequestBody.create(MediaType.parse("text/plain"), type);
        }else{
            reqType = RequestBody.create(MediaType.parse("text/plain"), "");
        }

        if (ImagePath != null) {
            File file1 = new File(ImagePath);
            Toast.makeText(requireContext(), "image Path : " + ImagePath, Toast.LENGTH_SHORT).show();
            RequestBody requestBody = RequestBody.create(MediaType.parse("image"), file1);
            familyImg = MultipartBody.Part.createFormData("event_image", file1.getName(), requestBody);
        } else {
            Toast.makeText(requireContext(), "image Null" + ImagePath, Toast.LENGTH_SHORT).show();
            File file1 = new File("");
            RequestBody requestBody = RequestBody.create(MediaType.parse("image"), "");
            familyImg = MultipartBody.Part.createFormData("event_image", file1.getName(), requestBody);
        }

//        Log.d("EVENTSELECTPOST","type : "+familyImg);

        new Mvvm().createEvent(requireActivity(), req_Topic, userId, req_Des, reqTime, reqType, familyImg).observe(requireActivity(), new Observer<CreateEventRoot>() {
            @Override
            public void onChanged(CreateEventRoot createEventRoot) {

                if(createEventRoot != null) {
                    if (createEventRoot.getStatus() == 1 ) {
                        postEventDialogBox();
                        Toast.makeText(requireContext(), "1 "+createEventRoot.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(requireContext(), "0 "+createEventRoot.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
                }

            }


        });
    }

    private void pickImg() {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, ImageCode);
    }

    @SuppressLint("Range")
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ImageCode && resultCode == RESULT_OK) {
            assert data != null;
            Uri image = data.getData();

            ContentResolver contentResolver = requireContext().getContentResolver();
            Cursor cursor = contentResolver.query(image, null, null, null, null);

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    this.file = new File(cursor.getString(cursor.getColumnIndex("_data")));

                    binding.pickIV.setImageURI(image);
                    binding.posterBgImg.setImageURI(image);
                    this.ImagePath = file.getPath().toString();
                    Toast.makeText(requireContext(), "UserImagePath : " + ImagePath, Toast.LENGTH_SHORT).show();
                }
                cursor.close();
            }
        }
    }

    private void postEventDialogBox() {
        Dialog dialog_share = new Dialog(requireContext());
        dialog_share.setContentView(R.layout.item_dialog);
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        // dialog_share.getWindow().getAttributes().windowAnimations=R.style.DialogAnimation;
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog_share.setCanceledOnTouchOutside(true);

        Window window = dialog_share.getWindow();
        window.setGravity(Gravity.CENTER);
        dialog_share.show();

        AppCompatButton button = dialog_share.findViewById(R.id.confirm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_share.dismiss();
//                        requireActivity().getSupportFragmentManager()
//                                .beginTransaction().replace(R.id.frame_holder, new FragmentDetailsEvent()).addToBackStack(null).commit();
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.eventsFragment);
            }
        });
    }

}