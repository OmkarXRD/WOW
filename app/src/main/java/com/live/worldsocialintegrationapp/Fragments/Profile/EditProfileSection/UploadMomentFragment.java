package com.live.worldsocialintegrationapp.Fragments.Profile.EditProfileSection;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.live.worldsocialintegrationapp.ModelClasses.UploadPost.UploadPostRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.agora.openvcall.ui.CallActivity;
import com.live.worldsocialintegrationapp.utils.App;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class UploadMomentFragment extends Fragment {

    private String ImagePath, videoOrImgStatus;
    ProgressDialog progress;
    MultipartBody.Part uploadImgORvideo;
    private File file;
    int ImageCode = 1;
    private ImageView momentSelectIV, momentPlusImg, momentBackImg;
    private CardView selectImgCV;
    private AppCompatButton uploadMomentBtn, cancelUploadMomentBtn;
    private EditText momentDesEdtx;
    private TextView uploadMomentPreviewTv;
    private static final int REQUEST_CODE_OPEN_GALLERY = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upload_moment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findIds(view);
        clicks(view);
    }

    private void clicks(View view) {

        selectImgCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePick();
            }
        });

        uploadMomentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ImagePath ==null) {
                    Toast.makeText(requireContext(), "Plase select Image", Toast.LENGTH_SHORT).show();
                } else {
                    UploadNewsFeedApi();

                }
            }
        });

        uploadMomentPreviewTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                 if(videoOrImgStatus.equalsIgnoreCase("0")){
//                     //image
//
//                 }else{
//                     //video
//                 }

                previewBox();

            }
        });


    }

    private void findIds(View view) {

        momentSelectIV = view.findViewById(R.id.momentSelectIV);
        selectImgCV = view.findViewById(R.id.selectImgCV);
        momentPlusImg = view.findViewById(R.id.momentPlusImg);
        cancelUploadMomentBtn = view.findViewById(R.id.cancelUploadMomentBtn);
        uploadMomentBtn = view.findViewById(R.id.uploadMomentBtn);
        momentDesEdtx = view.findViewById(R.id.momentDesEdtx);
        uploadMomentPreviewTv = view.findViewById(R.id.uploadMomentPreviewTv);
    }


    private void UploadNewsFeedApi() {

        RequestBody description = RequestBody.create(MediaType.parse("text/plain"), momentDesEdtx.getText().toString());
        RequestBody user_id = RequestBody.create(MediaType.parse("text/plain"), App.getSharedpref().getString("userId"));
        RequestBody status = RequestBody.create(MediaType.parse("text/plain"), videoOrImgStatus); // status 0 for image and 1 for videos

        if (ImagePath.length() != 0) {
            File file1 = new File(ImagePath);
            RequestBody requestBody = RequestBody.create(MediaType.parse("image"), file1);
            uploadImgORvideo = MultipartBody.Part.createFormData("image", file1.getName(), requestBody);
        } else {
            File file1 = new File("");
            RequestBody requestBody = RequestBody.create(MediaType.parse("image"), "");
            uploadImgORvideo = MultipartBody.Part.createFormData("image", file1.getName(), requestBody);
        }


        new Mvvm().uploadPost(requireActivity(), user_id, description, status, uploadImgORvideo).observe(requireActivity(), new Observer<UploadPostRoot>() {
            @Override
            public void onChanged(UploadPostRoot uploadPostRoot) {
                if(uploadPostRoot != null){
                    if (uploadPostRoot.getSuccess().equalsIgnoreCase("1")) {
                        loadingBar();
                        Navigation.findNavController(requireActivity().findViewById(R.id.nav_home))
                                .navigate(R.id.action_uploadMomentFragment_to_editProfileMomentsFragment2);
                    } else {
                    }
                }else{
                    if(getContext() != null){
                        Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }

    private void ImagePick() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("video/* image/*");
        //noinspection deprecation
        startActivityForResult(intent, ImageCode);


//        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
//        intent.addCategory(Intent.CATEGORY_OPENABLE);
//       /// intent.setType("*/*"); // Set the MIME type to display both photos and videos
//
//        String[] mimeTypes = {"image/*", "video/*"};
//        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
//
//        startActivityForResult(intent, REQUEST_CODE_OPEN_GALLERY);

    }


    public void loadingBar() {
        progress = new ProgressDialog(requireContext());
        progress.setMessage("Uploading...");
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setIndeterminate(true);
        progress.setProgress(0);
        progress.show();

        final int totalProgressTime = 100;
        final Thread t = new Thread() {
            @Override
            public void run() {
                int jumpTime = 0;

                while (jumpTime < totalProgressTime) {
                    try {
                        sleep(200);
                        jumpTime += 5;
                        progress.setProgress(jumpTime);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                progress.dismiss();

            }
        };
        t.start();
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

            if (image.toString().contains("image")) {
                //handle image
//                Toast.makeText(requireContext(), "imageUri " + image.toString(), Toast.LENGTH_SHORT).show();
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        this.file = new File(cursor.getString(cursor.getColumnIndex("_data")));
                        momentPlusImg.setVisibility(View.GONE);
                        momentSelectIV.setVisibility(View.VISIBLE);
                        momentSelectIV.setImageURI(image);
                        this.ImagePath = file.getPath().toString();
//                      Toast.makeText(requireContext(), "ImagePath : " + ImagePath, Toast.LENGTH_SHORT).show();
                        videoOrImgStatus = "0";
                        uploadMomentPreviewTv.setVisibility(View.VISIBLE);
                    }
                }
                cursor.close();

            } else if (image.toString().contains("video")) {
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        this.file = new File(cursor.getString(cursor.getColumnIndex("_data")));
                        momentPlusImg.setVisibility(View.GONE);
                        momentSelectIV.setVisibility(View.VISIBLE);

                        this.ImagePath = file.getPath().toString();
                        momentSelectIV.setImageBitmap(createVideoThumbNail(ImagePath));
                        videoOrImgStatus = "1";
                        uploadMomentPreviewTv.setVisibility(View.VISIBLE);
                    }
                    cursor.close();
                }
            } else {
            }
        }

    }

    //video thumbnail
    public Bitmap createVideoThumbNail(String path) {
        return ThumbnailUtils.createVideoThumbnail(path, MediaStore.Video.Thumbnails.MICRO_KIND);
    }

    private void previewBox(){

        Dialog dialog_share = new Dialog(requireContext());
        dialog_share.setContentView(R.layout.upload_video_preview_dialogbox);
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog_share.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog_share.setCanceledOnTouchOutside(true);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog_share.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog_share.getWindow().setAttributes(layoutParams);
        Window window = dialog_share.getWindow();
        window.setGravity(Gravity.CENTER);
        dialog_share.show();

        VideoView videoView =(VideoView)dialog_share.findViewById(R.id.videoView1);
        ImageView uploadVideoImg =(ImageView) dialog_share.findViewById(R.id.uploadVideoImg);

        if(videoOrImgStatus.equalsIgnoreCase("0")){
            uploadVideoImg.setVisibility(View.VISIBLE);
            videoView.setVisibility(View.GONE);
            uploadVideoImg.setImageURI(Uri.parse(ImagePath));
        }else{
            videoView.setVisibility(View.VISIBLE);
            uploadVideoImg.setVisibility(View.GONE);

            //Creating MediaController
            MediaController mediaController= new MediaController(dialog_share.getContext());
            mediaController.setAnchorView(videoView);

            //specify the location of media file
            Uri uri=Uri.parse(ImagePath);

            //Setting MediaController and URI, then starting the videoView
            videoView.setMediaController(mediaController);
            videoView.setVideoURI(uri);
            videoView.requestFocus();
            videoView.start();
        }



    }
}