package com.live.worldsocialintegrationapp.Fragments.Profile.Family;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.live.worldsocialintegrationapp.ModelClasses.EDitFamilyModelClass;
import com.live.worldsocialintegrationapp.ModelClasses.Family.CreateFamilyRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.live.worldsocialintegrationapp.utils.CommonUtils;
import com.tapadoo.alerter.Alerter;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class CreateFamilyFragment extends Fragment {
    private CircleImageView createFamilyCirImg;
    private int ImageCode= 101;
    private File file;
    private String ImagePath,status;
    private MultipartBody.Part familyImg;
    private EditText crateFmilyNameEdtx,fmailyDesciptionEdtx;
    private TextView createTV;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_family, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CommonUtils.disableBottomNavigation(requireActivity());
        findIds(view);
        clicks(view);

        Bundle bundle = getArguments();
        try {
            status = bundle.getString("status");
        }catch (Exception e){

        }

        if (status.equalsIgnoreCase("1")){
            createTV.setText("Edit Family Profile");
        }else {
            createTV.setText("Create Family");
        }
    }

    private void editFamilyApihit() {

        String familyName= crateFmilyNameEdtx.getText().toString();
        String familyDes=fmailyDesciptionEdtx.getText().toString();


        new Mvvm().editFamilyApi(requireActivity(),CommonUtils.getRequestBodyText(App.getSharedpref().getString("leaderId")),CommonUtils.getRequestBodyText(App.getSharedpref().getString("id")),CommonUtils.getRequestBodyText(familyName),CommonUtils.getRequestBodyText(familyDes),CommonUtils.getFileData(ImagePath,"image")).observe(requireActivity(), new Observer<EDitFamilyModelClass>() {
            @Override
            public void onChanged(EDitFamilyModelClass eDitFamilyModelClass) {
                if (eDitFamilyModelClass.getSuccess().equalsIgnoreCase("1")){
                    if (eDitFamilyModelClass.getDetails()!=null){

                        Alerter.create(requireActivity()).setTitle("Family Updated").setText("Your family details change").setBackgroundResource(R.drawable.wallet_background).show();
                    }else {
                        //Toast.makeText(requireContext(), "null", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(requireContext(), "Technical issues accquired", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void findIds(View view) {
        createFamilyCirImg=view.findViewById(R.id.createFamilyCirImg);
        crateFmilyNameEdtx=view.findViewById(R.id.crateFmilyNameEdtx);
        fmailyDesciptionEdtx=view.findViewById(R.id.fmailyDesciptionEdtx);
        createTV=view.findViewById(R.id.createTV);
    }

    private void clicks(View view) {

        view.findViewById(R.id.crateFamilyBackImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        view.findViewById(R.id.createFamilySubmitBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (crateFmilyNameEdtx.getText().toString()==null){
                    Alerter.create(requireActivity())
                            .setTitle("Family Name")
                            .setText("Please enter the Family name")
                            .setBackgroundResource(R.drawable.wallet_background)
                            .show();
                }else if (fmailyDesciptionEdtx.getText().toString()==null){
                    Alerter.create(requireActivity())
                            .setTitle("Description")
                            .setText("Please enter the Description")
                            .setBackgroundResource(R.drawable.wallet_background)
                            .show();

                }else{
                    try {
                        if (status.equalsIgnoreCase("1")){

                            editFamilyApihit();
                        }else {
                            createFamilyApi();
                        }
                    }catch (Exception e){

                    }
                }
            }
        });

        createFamilyCirImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImg();
            }
        });
    }

    private void createFamilyApi() {
        RequestBody familyName= RequestBody.create(MediaType.parse("text/plain"),crateFmilyNameEdtx.getText().toString());
        RequestBody familyDes=RequestBody.create(MediaType.parse("text/plain"),fmailyDesciptionEdtx.getText().toString());
        RequestBody userId=RequestBody.create(MediaType.parse("text/plain"), AppConstants.USER_ID);
       // Toast.makeText(requireContext(), "FamilyImagePath : "+ImagePath, Toast.LENGTH_SHORT).show();
       // Toast.makeText(requireContext(), "uiuiu"+userId, Toast.LENGTH_SHORT).show();
        if(ImagePath != null){
            File file1=new File(ImagePath);
//            Toast.makeText(requireContext(), "image Path : "+ImagePath, Toast.LENGTH_SHORT).show();
            RequestBody requestBody = RequestBody.create(MediaType.parse("image"), file1);
            familyImg = MultipartBody.Part.createFormData("image", file1.getName(), requestBody);
        }
        else{
//            Toast.makeText(requireContext(), "image Null"+ImagePath, Toast.LENGTH_SHORT).show();
            File file1=new File("");
            RequestBody requestBody = RequestBody.create(MediaType.parse("image"), "");
            familyImg = MultipartBody.Part.createFormData("image", file1.getName(), requestBody);
        }

        new Mvvm().createFamily(requireActivity(),userId,familyName,familyDes,familyImg).observe(requireActivity(), new Observer<CreateFamilyRoot>() {
            @Override
            public void onChanged(CreateFamilyRoot createFamilyRoot) {
                if (createFamilyRoot != null) {
                    if (createFamilyRoot.getStatus() == 1) {
                        Toast.makeText(requireContext(), ""+familyName, Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.fragment_Family);
                        App.getSharedpref().saveString("familycreate",createFamilyRoot.getDetails().getFamilyName());
                        Alerter.create(requireActivity())
                                .setTitle("Request send to Admin")
                                .setText("waiting for request accepting")
                                .setBackgroundResource(R.drawable.wallet_background)
                                .show();
                    } else {
                        Alerter.create(requireActivity())
                                .setTitle("Alert")
                                .setText(createFamilyRoot.getMessage())
                                .setBackgroundResource(R.drawable.wallet_background)
                                .show();
                    }
                }else {
                    Toast.makeText(requireContext(), "Technical issue accquired", Toast.LENGTH_SHORT).show();
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
                    createFamilyCirImg.setImageURI(image);
                    this.ImagePath = file.getPath().toString();
//                    Toast.makeText(requireContext(), "UserImagePath : " + ImagePath, Toast.LENGTH_SHORT).show();
                }
                cursor.close();
            }
        }

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setStatusBarGradiant(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            Drawable background = activity.getResources().getDrawable(R.drawable.wallet_background);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setNavigationBarColor(activity.getResources().getColor(android.R.color.darker_gray));
            window.setBackgroundDrawable(background);
        }
    }
}