package com.live.worldsocialintegrationapp.Fragments.ChatsFragments;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.app.Activity.RESULT_OK;

import static androidx.core.content.ContextCompat.checkSelfPermission;

import static com.facebook.FacebookSdk.getApplicationContext;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.os.CountDownTimer;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.live.worldsocialintegrationapp.Activites.HomeActivity;
import com.live.worldsocialintegrationapp.Adapters.MessagesRVAdapter;
import com.live.worldsocialintegrationapp.ModelClasses.ChatInformation;
import com.live.worldsocialintegrationapp.ModelClasses.ChatModel;
import com.live.worldsocialintegrationapp.ModelClasses.ChatNotificationRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GetChatFilesRoot;
import com.live.worldsocialintegrationapp.ModelClasses.Image;
import com.live.worldsocialintegrationapp.ModelClasses.RequstChat;
import com.live.worldsocialintegrationapp.ModelClasses.SendFileInChatRoot;
import com.live.worldsocialintegrationapp.ModelClasses.SingleLiveUserRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.agora.openvcall.ui.CallActivity;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.live.worldsocialintegrationapp.utils.AudioRecorder;
import com.live.worldsocialintegrationapp.utils.CommonUtils;
import com.live.worldsocialintegrationapp.utils.RealPathUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

@SuppressWarnings("ALL")
public class MessagesFragment extends Fragment implements MessagesRVAdapter.Callback {
    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference onlineUsers = firebaseDatabase.getReference().child("onlineUsers");
    private final DatabaseReference dynamicLinkKeys = firebaseDatabase.getReference().child("dynamicLinkKeys");
    private DatabaseReference friendReqRef;
    private FirebaseDatabase database;
    private RecyclerView messageRV;
    private List<ChatModel> list = new ArrayList<>();
    private List<GetChatFilesRoot.Detail> fileList = new ArrayList<>();
    private String otherUserId, userId, otherUserImg, otherUserName, ImagePath, videoOrImgStatus = "3", sendVideoId, sendFilePosition, sendFileUrl;
    private EditText chatMsgEdtx;
    private RelativeLayout msgSendRL;
    private MessagesRVAdapter messagesRVAdapter;
    private CircularImageView msgCircleImg;
    private TextView messageUserNameTV, onliveTv;
    private RequstChat requstChat;
    private int backPressed = 0;
    private Uri imageUri, uploadImageUri;
    private File file;
    private ImageView galleryImg, selectedImage, messageBackImg, chatCameraImg, voiceRecoderImg, stopRecodingImg;
    private int SizeMB = 0, reqCameraOrGallery = 0;  //permission 0 means nothing 1->Gallery 2->camera
    private final int CAMERA_REQUEST = 100;
    boolean isFriendOrNOt = false, isOnline = false;
    MultipartBody.Part uploadImgORvideo;
    String unFriendId = "0";
    Bitmap bmp;
    int ImageCode = 102;
    DatabaseReference chatRef, databaseReference, myRef;
    private List<ChatInformation> chatInformationArrayList = new ArrayList<>();
    FirebaseStorage storage;
    StorageReference storageReference;
    AudioRecorder audioRecorder = new AudioRecorder("Service/Record");
    LottieAnimationView voiceRecoderLottie;
    private String itemId, sendImage;
    private Mvvm mvvm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mvvm = new ViewModelProvider(this).get(Mvvm.class);
        return inflater.inflate(R.layout.fragment_messages, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        boolean shouldChangeStatusBarTintToDark = true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = requireActivity().getWindow().getDecorView();
            if (shouldChangeStatusBarTintToDark) {
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                // We want to change tint color to white again.
                // You can also record the flags in advance so that you can turn UI back completely if
                // you have set other flags before, such as translucent or full screen.
                decor.setSystemUiVisibility(0);
            }
        }
//        CommonUtils.disableBottomNavigation(requireActivity());
        if (getArguments() != null && getArguments().containsKey("backPressed")) {
            getArguments().getString("data_key");
            backPressed = getArguments().getInt("backPressed");
            CommonUtils.disableBottomNavigation(requireActivity());


        } else if (getArguments() != null && getArguments().containsKey("notification")) {
//            CommonUtils.disableBottomNavigation(requireActivity());
//            View view1  = getActivity().findViewById( R.id.bottom_lay);
//            view1.setVisibility( View.GONE );
        }

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        chatRef = FirebaseDatabase.getInstance().getReference("ChatMessages");
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("ChatRequest");
        findIds(view);

        messageBackImg.setVisibility(View.GONE);

        userId = App.getSharedpref().getString("userId");
        MessagesRVAdapter.userId = userId;

        if (getArguments() != null) {
            otherUserId = getArguments().getString("otherUserId");
            otherUserImg = getArguments().getString("otherUserImg");
            otherUserName = getArguments().getString("otherUserName");
            App.getSharedpref().saveString("otherUserImg", otherUserImg);
//            requstChat = (RequstChat) getArguments().getSerializable("requstChat");
//            Toast.makeText(requireContext(), "OtherUserId " + otherUserId, Toast.LENGTH_SHORT).show();
        } else {
//            Toast.makeText(requireContext(), "arguments is null", Toast.LENGTH_SHORT).show();
        }

        clicks(view);
        onBackPressed(view);
//        getReceiveFiles();

        messagesRVAdapter = new MessagesRVAdapter(list, requireContext(), MessagesFragment.this);
        messageRV.setAdapter(messagesRVAdapter);
        receiveData();


        if (otherUserId.isEmpty()) {
            msgCircleImg.setImageResource(R.drawable.demo_user_profile_img);
        } else {
            Glide.with(msgCircleImg.getContext()).load(otherUserImg).error(R.drawable.demo_user_profile_img).into(msgCircleImg);
        }
        messageUserNameTV.setText(otherUserName);

        showliveUser();
        isFriendOrNot();
    }

    private void showliveUser() {
        onlineUsers.child(otherUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    onliveTv.setText("online");
                    isOnline = true;
                } else {
                    onliveTv.setText("offline");
                    isOnline = false;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void findIds(View view) {
        messageRV = view.findViewById(R.id.messageRV);
        msgSendRL = view.findViewById(R.id.msgSendRL);
        chatMsgEdtx = view.findViewById(R.id.chatMsgEdtx);
        msgCircleImg = view.findViewById(R.id.msgCircleImg);
        messageUserNameTV = view.findViewById(R.id.messageUserNameTV);
        onliveTv = view.findViewById(R.id.onliveTv);
        galleryImg = view.findViewById(R.id.galleryImg);
        selectedImage = view.findViewById(R.id.selectedImage);
        messageBackImg = view.findViewById(R.id.messageBackImg);
        chatCameraImg = view.findViewById(R.id.chatCameraImg);
        voiceRecoderImg = view.findViewById(R.id.voiceRecoderImg);
        stopRecodingImg = view.findViewById(R.id.stopRecodingImg);
        voiceRecoderLottie = view.findViewById(R.id.voiceRecoderLottie);
    }

    private Timer myTimer;

    @SuppressLint("ClickableViewAccessibility")
    private void clicks(View view) {

        view.findViewById(R.id.msgBackImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (backPressed == 0) {
                    getActivity().onBackPressed();
                } else {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.callActivityFrameLayout, new ChatFragment()).addToBackStack(null).commit();
                }
            }
        });

        msgSendRL.setOnClickListener(v -> {
            if (videoOrImgStatus.equalsIgnoreCase("3")) {
                SendMessage();
            } else {
                if (file != null) {
                    if (SizeMB >= 30) {
                        Toast.makeText(requireContext(), "File Size not bigger than " + 30, Toast.LENGTH_SHORT).show();
                    } else if (SizeMB <= 30) {
//                        uploadBackImg(ImagePath);
                        SendFile(ImagePath);
                    }
                }
            }

            messageBackImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedImage.setVisibility(View.GONE);
                    messageBackImg.setVisibility(View.GONE);
                    ImagePath = null;
                }
            });
        });

        galleryImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reqCameraOrGallery = 1;
                requestPermissions();
            }
        });

        msgCircleImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("otherUserId", otherUserId);
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.otherUser, bundle);
            }
        });
        chatCameraImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reqCameraOrGallery = 2;
                requestPermissions();

            }
        });

        voiceRecoderImg.setVisibility(View.VISIBLE);
        voiceRecoderLottie.setVisibility(View.GONE);

        voiceRecoderImg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // start tiemr
                    voiceRecoderLottie.setVisibility(View.VISIBLE);
                    voiceRecoderLottie.playAnimation();

                    galleryImg.setVisibility(View.GONE);
                    chatMsgEdtx.setVisibility(View.GONE);
                    msgSendRL.setVisibility(View.GONE);
                    startRecording();


                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // stop timer.
                    voiceRecoderLottie.setVisibility(View.INVISIBLE);
                    voiceRecoderLottie.cancelAnimation();

                    galleryImg.setVisibility(View.VISIBLE);
                    chatMsgEdtx.setVisibility(View.VISIBLE);
                    msgSendRL.setVisibility(View.VISIBLE);
                    pauseRecording();

                }
                return true;
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // this method is called when user will
        // grant the permission for audio recording.
        switch (requestCode) {
            case REQUEST_AUDIO_PERMISSION_CODE:
                if (grantResults.length > 0) {
                    boolean permissionToRecord = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean permissionToStore = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (permissionToRecord && permissionToStore) {
                        Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }


    private void onBackPressed(View view) {

        if (backPressed == 0) {
            view.setFocusableInTouchMode(true);
            view.requestFocus();

            view.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View view, int i, KeyEvent keyEvent) {
                    if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                        if (i == KeyEvent.KEYCODE_BACK) {
                            Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.chatFragment);
                            return true;
                        }
                    }
                    return false;
                }
            });
        } else {
            view.setFocusableInTouchMode(true);
            view.requestFocus();
            view.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View view, int i, KeyEvent keyEvent) {
                    if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                        if (i == KeyEvent.KEYCODE_BACK) {
                            ChatFragment chatFragment = new ChatFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt("backPressed", 1);
                            chatFragment.setArguments(bundle);
//                ChatFragment.backPressed = 1;
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.callActivityFrameLayout, chatFragment).addToBackStack(null).commit();
//                            getActivity().findViewById(R.id.callAcitivtyMainRL).setVisibility(View.VISIBLE);
//                            getActivity().findViewById(R.id.callActivityFrameLayout).setVisibility(View.GONE);
                            return true;
                        }
                    }
                    return false;
                }
            });
        }
    }


    private void receiveData() {

        chatRef.child("Messages").child(userId).child(otherUserId)
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        if (snapshot.exists()) {

                            ChatModel messages = snapshot.getValue(ChatModel.class);
                            list.add(messages);
//                            lastMessage(messages.getMessage());
//                            if(!messages.getMsgType().equalsIgnoreCase("3")){
//                                getReceiveFiles();
//                            }
                            messagesRVAdapter.notifyDataSetChanged();
                            messageRV.smoothScrollToPosition(messageRV.getAdapter().getItemCount());
                        }
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    private void SendMessage() {

        if (isFriendOrNOt) {
            if (chatMsgEdtx.getText().toString().trim().length() == 0 && videoOrImgStatus.equalsIgnoreCase("3")) {
                Toast.makeText(requireContext(), "Enter the message", Toast.LENGTH_SHORT).show();
            } else {

                String msgSendRef = "Messages/" + userId + "/" + otherUserId;
                String msgRecRef = "Messages/" + otherUserId + "/" + userId;

                DatabaseReference userMsgKeyRef = chatRef.child("Messages").child(userId).child(otherUserId).push();

                String msgPushId = userMsgKeyRef.getKey();  // msg key for msg key

                ChatModel messages = new ChatModel();
                messages.setMsgType(videoOrImgStatus); //1 image and 2 video and 3 message

                if (videoOrImgStatus.equalsIgnoreCase("1")) {
                    messages.setMessageUrl(uploadImageUri + ".jpg");
                    videoOrImgStatus = "3";
                    messages.setMessage(sendFileUrl);

                } else if (videoOrImgStatus.equalsIgnoreCase("2")) {

//                messages.setMessageUrl(uploadImageUri + ".mp4");

                    videoOrImgStatus = "3";
                    messages.setMessage(sendFileUrl);

                } else if (videoOrImgStatus.equalsIgnoreCase("3")) {
                    messages.setMessage(chatMsgEdtx.getText().toString());

                } else if (videoOrImgStatus.equalsIgnoreCase("4")) {
                    videoOrImgStatus = "3";
                    messages.setMessage(sendFileUrl);
                    //Toast.makeText(requireContext(), "fileUrl sent", Toast.LENGTH_SHORT).show();
                }

                messages.setFrom(userId);
                messages.setTo(otherUserId);
                messages.setMsgId(msgPushId);
                messages.setTime(getCurrentTime());
                messages.setDate(getDate());

                Map messageBodyDetails = new HashMap();
                messageBodyDetails.put(msgSendRef + "/" + msgPushId, messages);

                messageBodyDetails.put(msgRecRef + "/" + msgPushId, messages);

                chatRef.updateChildren(messageBodyDetails).addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {

                        if (task.isSuccessful()) {
                            if (!isOnline) {
                                sendMessageNotificationApi();
                            }
//                            lastMessage(chatMsgEdtx.getText().toString());
                        } else {
                        }
                        chatMsgEdtx.setText("");
                    }
                });
            }
        } else {
            if (!unFriendId.equalsIgnoreCase(AppConstants.USER_ID)) {
                Toast.makeText(requireContext(), "You can't send the message because you are unfriend by user", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "First make friend", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void sendMessageNotificationApi() {

        mvvm.sendChatNotification(requireActivity(), AppConstants.USER_ID, otherUserId).observe(requireActivity(), new Observer<ChatNotificationRoot>() {
            @Override
            public void onChanged(ChatNotificationRoot chatNotificationRoot) {
                if (chatNotificationRoot != null) {
                    if (chatNotificationRoot.getSuccess().equalsIgnoreCase("1")) {
                        if (getContext() != null) {
                            Toast.makeText(requireContext(), "" + chatNotificationRoot.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });


    }

    private void lastMessage(String message) {

        myRef.child(requstChat.getFrom()).child(AppConstants.USER_ID).child("lastMessage").setValue(message);
        myRef.child(AppConstants.USER_ID).child(requstChat.getFrom()).child("lastMessage").setValue(message);
    }

    private void uploadBackImg(String imagePath) {

        if (imagePath != null) {

            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog
                    = new ProgressDialog(requireActivity());
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
//            StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());
            StorageReference ref = storageReference.child("chatStorage/").child(AppConstants.USER_ID).child(UUID.randomUUID().toString());

            UploadTask uploadTask2 = null;
            if (videoOrImgStatus.equalsIgnoreCase("1")) {
                try {
                    bmp = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
                } catch (Exception e) {
                }
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 25, baos);
                byte[] data = baos.toByteArray();
                //uploading the image
                uploadTask2 = ref.putBytes(data);

            } else if (videoOrImgStatus.equalsIgnoreCase("2")) {
                uploadTask2 = ref.putFile(imageUri);
            }


//             adding listeners on upload
//             or failure of image
            uploadTask2.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    if (getContext() != null) {
                        Toast.makeText(requireContext(), "Image Uploaded!!", Toast.LENGTH_SHORT).show();
                    }
                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            uploadImageUri = uri;
                            // Image uploaded successfully
                            // Dismiss dialog
                            progressDialog.dismiss();
                            Toast
                                    .makeText(requireContext(),
                                            "Image Uploaded!!",
                                            Toast.LENGTH_SHORT)
                                    .show();
                            SendMessage();
                        }
                    });

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(requireContext(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    double progress
                            = (100.0
                            * snapshot.getBytesTransferred()
                            / snapshot.getTotalByteCount());
                    progressDialog.setMessage(
                            "Uploaded "
                                    + (int) progress + "%");


                }
            });

        }
    }

    private void ImagePick() {
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setType("video/* image/*");   //this is for video and image both
//        intent.setType("image/*");   //this is only for image
//        startActivityForResult(intent, ImageCode);


        ImagePicker.Companion.with(MessagesFragment.this).crop().compress(512).maxResultSize(1080, 1080).start();


    }

    @SuppressLint("Range")
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && data != null) {
            assert data != null;
            reqCameraOrGallery = 0;
            Uri image = data.getData();
            this.imageUri = image;

            imageUri = data.getData();

            ImagePath = imageUri.getPath();
            videoOrImgStatus = "1";

            selectedImage.setVisibility(View.VISIBLE);
            messageBackImg.setVisibility(View.VISIBLE);
            selectedImage.setImageURI(imageUri);

            file = new File(ImagePath);
            getFileSize(file);

        } else {
            Toast.makeText(getContext(), "Can't get image", Toast.LENGTH_SHORT).show();
        }

//        if (requestCode == ImageCode && resultCode == RESULT_OK && data != null) {
//            assert data != null;
//            reqCameraOrGallery = 0;
//            Uri image = data.getData();
//            this.imageUri = image;

//            this.ImagePath = RealPathUtil.getRealPath(requireContext(), image);
//           uploadBackImg(ImagePath);

//            ContentResolver contentResolver = requireContext().getContentResolver();
//            Cursor cursor = contentResolver.query(image, null, null, null, null);

//            if (image.toString().contains("image")) {
//
//                selectedImage.setVisibility(View.VISIBLE);
//                selectedImage.setImageURI(image);
//                messageBackImg.setVisibility(View.VISIBLE);
//
//                //handle image
//                if (cursor != null) {
//                    while (cursor.moveToNext()) {
//                        this.file = new File(cursor.getString(cursor.getColumnIndex("_data")));
//
//                        this.ImagePath = file.getPath().toString();
//                        videoOrImgStatus = "1";
//                        getFileSize(file);
//                    }
//                }
//                cursor.close();
//
//            } else if (image.toString().contains("video")) {
//                handle video
//                if (cursor != null) {
//                    while (cursor.moveToNext()) {
//                        this.file = new File(cursor.getString(cursor.getColumnIndex("_data")));
//                        this.ImagePath = file.getPath().toString();
//                        videoOrImgStatus = "2";
//
//                        getFileSize(file);
//
//                    }
//                    cursor.close();
//                }
//            } else {
//            }

//        } else if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK && data.getData() != null) {
//            reqCameraOrGallery = 0;
//            assert data != null;
//            Uri image = data.getData();
//            this.imageUri = image;
//
//            Toast.makeText(requireContext(), "realpath "+ image.toString(), Toast.LENGTH_SHORT).show();
//
//            selectedImage.setVisibility(View.VISIBLE);
//            selectedImage.setImageURI(image);
//            messageBackImg.setVisibility(View.VISIBLE);

//            this.ImagePath = getRealPathFromURI(image);
//
//            ContentResolver contentResolver = requireContext().getContentResolver();
//            Cursor cursor = contentResolver.query(image, null, null, null, null);
//
////            handle image
//            if (cursor != null) {
//                while (cursor.moveToNext()) {
//                    this.file = new File(cursor.getString(cursor.getColumnIndex("_data")));
//
//                    this.ImagePath = file.getPath().toString();
//                    videoOrImgStatus = "1";
//                    Toast.makeText(requireContext(), "realpath "+ ImagePath, Toast.LENGTH_SHORT).show();
//                    getFileSize(file);
//                }
//            }
//            cursor.close();
//        }


    }


    private String getFileSize(File file) {

        String size = "0";
        long length = file.length();
        length = length / 1024;  //size in kb

        SizeMB = (int) length / 1024;

        if (length >= 1024) {
            float floatLength = length / 1024f;
            size = floatLength + " MB";
        } else {
            size = length + " KB";
        }
        return size;

    }

    @Override
    public void openImage(String url, String videoOrImgStatus) {

        Dialog dialog_share = new Dialog(requireContext());
        dialog_share.setContentView(R.layout.chat_image_or_video_open);
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog_share.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_share.setCanceledOnTouchOutside(true);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog_share.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog_share.getWindow().setAttributes(layoutParams);
        Window window = dialog_share.getWindow();
        window.setGravity(Gravity.CENTER);
        dialog_share.show();

        ImageView chatImage = dialog_share.findViewById(R.id.chatImage);
        ImageView backImage = dialog_share.findViewById(R.id.backImage);
        VideoView videoView = (VideoView) dialog_share.findViewById(R.id.videoView2);


        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_share.dismiss();
            }
        });

        if (videoOrImgStatus.equalsIgnoreCase("1")) {
            chatImage.setVisibility(View.VISIBLE);
            videoView.setVisibility(View.GONE);
            Glide.with(chatImage.getContext()).load(url).into(chatImage);
        } else if (videoOrImgStatus.equalsIgnoreCase("2")) {
            videoView.setVisibility(View.VISIBLE);
            chatImage.setVisibility(View.GONE);

            //Creating MediaController
            MediaController mediaController = new MediaController(dialog_share.getContext());
            mediaController.setAnchorView(videoView);

            //specify the location of media file
            Uri uri = Uri.parse(url);

            //Setting MediaController and URI, then starting the videoView
            videoView.setMediaController(mediaController);
            videoView.setVideoURI(uri);
            videoView.requestFocus();
            videoView.start();
        }
    }

    @Override
    public void deleteMessage(String msgKey) {

        AlertDialog.Builder alert = new AlertDialog.Builder(requireContext());

        alert.setTitle("Delete Comment");
        alert.setMessage("Are you sure you want to delete?");
        alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                chatRef.child("Messages").child(userId).child(otherUserId).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            chatRef.child("Messages").child(userId).child(otherUserId).child(msgKey).removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        alert.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // close dialog
                dialog.cancel();
            }
        });
        alert.show();
    }

    @Override
    public void openLink(String userId, String liveKey) {

        if (userId != null && liveKey != null) {
            openLive(userId);

//            dynamicLinkKeys.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    if (snapshot.exists()){
//                        String key=snapshot.getValue().toString();
//
//                        if (key.equalsIgnoreCase(liveKey) && key != null){
//                            openLive(userId);
//                        }
//                    }else{
//                        Toast.makeText(requireContext(), "not exits", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });

        }


    }

    @Override
    public void claimFrame(ChatModel model,TextView textView) {

        if (model.getCarId() !=null && !model.getCarId().isEmpty()){
            itemId = model.getCarId();
            sendImage = "";
        }else if (model.getFrameId() !=null && !model.getFrameId().isEmpty()){
            itemId = model.getFrameId();
            sendImage =  "";
        } else if (model.getThemeId()!=null && !model.getThemeId().isEmpty()) {
            itemId=model.getThemeId();
            sendImage =  "";
        } else  if (model.getGalleryId()!=null && !model.getGalleryId().isEmpty()){
            itemId=model.getGalleryId();
            sendImage =  model.getGalleryTheme();
        }
        mvvm.claim_garage(AppConstants.USER_ID,itemId,String.valueOf(model.getType2()),sendImage).observe(requireActivity(),response ->{
            if (response !=null){
                if (response.arjun){
                    //Toast.makeText(requireContext(), ""+response.getMessage(), Toast.LENGTH_SHORT).show();
                    model.setClaim(true);
                    textView.setText("Claimed");
                }else {
                    model.setClaim(false);
                    //Toast.makeText(requireContext(), ""+response.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(requireContext(), "Technical issue...", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void SendFile(String ImagePath) {

        if (videoOrImgStatus.equalsIgnoreCase("4")) {
            videoOrImgStatus = "3";
        }

        //file type     1->image   2->video    3->audio
        RequestBody userId_req = RequestBody.create(MediaType.parse("text/plain"), AppConstants.USER_ID);
        RequestBody otherUser_req = RequestBody.create(MediaType.parse("text/plain"), otherUserId);
        RequestBody fileType_req = RequestBody.create(MediaType.parse("text/plain"), videoOrImgStatus);


        if (ImagePath.length() != 0) {
            File file1 = new File(ImagePath);
            RequestBody requestBody = RequestBody.create(MediaType.parse("image"), file1);
            uploadImgORvideo = MultipartBody.Part.createFormData("file", file1.getName(), requestBody);
        } else {
            File file1 = new File("");
            RequestBody requestBody = RequestBody.create(MediaType.parse("image"), "");
            uploadImgORvideo = MultipartBody.Part.createFormData("file", file1.getName(), requestBody);
        }


        //file type     1->image   2->video    3->audio
        mvvm.sendFileInChat(requireActivity(), userId_req, otherUser_req, fileType_req, uploadImgORvideo).observe(requireActivity(), new Observer<SendFileInChatRoot>() {
            @Override
            public void onChanged(SendFileInChatRoot sendFileInChatRoot) {
                if (sendFileInChatRoot != null) {
                    if (sendFileInChatRoot.getSuccess().equalsIgnoreCase("1")) {

                        //Toast.makeText(requireContext(), "" + sendFileInChatRoot.getMessage(), Toast.LENGTH_SHORT).show();
                        if (sendFileInChatRoot.getDetails().getFileType().equalsIgnoreCase("3")) {
                            videoOrImgStatus = "4";

                            MediaPlayer mPlayer = new MediaPlayer();
                            try {
                                // below method is used to set the
                                // data source which will be our file name
                                mPlayer.setDataSource(sendFileInChatRoot.getDetails().getFile());

                                // below method will prepare our media player
                                mPlayer.prepare();

                                // below method will start our media player.
                                mPlayer.start();
                            } catch (IOException e) {
                                Log.e("TAG", "prepare() failed");
                            }
                        }
                        sendVideoId = sendFileInChatRoot.getDetails().getId();
                        sendFilePosition = sendFileInChatRoot.getDetails().getPosition();
                        sendFileUrl = sendFileInChatRoot.getDetails().getFile();

                        selectedImage.setVisibility(View.GONE);
                        messageBackImg.setVisibility(View.GONE);

                        SendMessage();
                    } else {
                        //Toast.makeText(requireContext(), "" + sendFileInChatRoot.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                } else {
                }
            }
        });
    }


    private void getReceiveFiles() {

        mvvm.getChatFiles(requireActivity(), otherUserId).observe(requireActivity(), new Observer<GetChatFilesRoot>() {
            @Override
            public void onChanged(GetChatFilesRoot getChatFilesRoot) {
                if (getChatFilesRoot != null) {
                    if (getChatFilesRoot.getSuccess().equalsIgnoreCase("1")) {
                        fileList = getChatFilesRoot.getDetails();
                    }
                }
            }
        });
    }


    private void isFriendOrNot() {
        friendReqRef = database.getReference("ChatRequest");

        if (!otherUserId.equalsIgnoreCase(AppConstants.USER_ID)) {
            friendReqRef.child(otherUserId).child(AppConstants.USER_ID).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {

                        if (snapshot.hasChild("toFriendStatus")) {

                            if (snapshot.hasChild("byUnfriend")) {
                                unFriendId = snapshot.child("byUnfriend").getValue().toString();
                            }

                            String isFriend = snapshot.child("toFriendStatus").getValue().toString(); // 0 means no friend // 1 means friend hai

                            if (isFriend.equalsIgnoreCase("1")) {
                                isFriendOrNOt = true;
                            } else {
                                isFriendOrNOt = false;
                            }

                            } else {
                            friendReqRef.child(otherUserId).child(AppConstants.USER_ID).child("toFriendStatus").setValue("0");
                            friendReqRef.child(otherUserId).child(AppConstants.USER_ID).child("byUnfriend").setValue("0");
                            friendReqRef.child(AppConstants.USER_ID).child(otherUserId).child("toFriendStatus").setValue("0");
                            friendReqRef.child(AppConstants.USER_ID).child(otherUserId).child("byUnfriend").setValue("0");
                        }
                    } else {
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
    }


    private void requestPermissions() {
        // below line is use to request permission in the current activity.
        // this method is use to handle error in runtime permissions
        Dexter.withActivity(requireActivity())
                // below line is use to request the number of permissions which are required in our app.
                .withPermissions(Manifest.permission.CAMERA,
                        // below is the list of permissions
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.RECORD_AUDIO)
                // after adding permissions we are calling an with listener method.
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                        // this method is called when all permissions are granted
                        if (multiplePermissionsReport.areAllPermissionsGranted()) {
                            // do you work now

//                            if (reqCameraOrGallery == 1) {
                            ImagePick();
//                            } else {
//                                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                                startActivityForResult(cameraIntent, CAMERA_REQUEST);
//                            }
                        }
                        // check for permanent denial of any permission
                        if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied()) {
                            // permission is denied permanently, we will show user a dialog message.
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                        // this method is called when user grants some permission and denies some of them.
                        permissionToken.continuePermissionRequest();
                    }
                }).withErrorListener(error -> {
                    // we are displaying a toast message for error message.
                    Toast.makeText(getApplicationContext(), "Error occurred! ", Toast.LENGTH_SHORT).show();
                })
                // below line is use to run the permissions on same thread and to check the permissions
                .onSameThread().check();
    }

    // below is the shoe setting dialog method which is use to display a dialogue message.
    private void showSettingsDialog() {
        // we are displaying an alert dialog for permissions
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

        // below line is the title for our alert dialog.
        builder.setTitle("Need Permissions");

        // below line is our message for our dialog
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", (dialog, which) -> {
            // this method is called on click on positive button and on clicking shit button
            // we are redirecting our user from our app to the settings page of our app.
            dialog.cancel();
            // below is the intent from which we are redirecting our user.
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
            intent.setData(uri);
            startActivityForResult(intent, 101);
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> {
            // this method is called when user click on negative button.
            dialog.cancel();
        });
        // below line is used to display our dialog
        builder.show();

        builder.show().getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(requireContext(), R.color.green));
        builder.show().getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(requireContext(), R.color.green));
    }


    // creating a variable for media recorder object class.
    private MediaRecorder mRecorder;

    // constant for storing audio permission
    public static final int REQUEST_AUDIO_PERMISSION_CODE = 1;

    // string variable is created for storing a file name
    private static String mFileName = null;

    private void startRecording() {
        if (CheckPermissions()) {
//            mFileName = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();
            mFileName = getActivity().getExternalCacheDir().getAbsolutePath();
            mFileName += "/AudioRecording.3gp";

            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);

            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);

            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

            mRecorder.setOutputFile(mFileName);
            try {

                mRecorder.prepare();
            } catch (IOException e) {
                Log.e("TAG", "prepare() failed");
            }

            mRecorder.start();
        } else {
            //Toast.makeText(requireContext(), "Unchecked permission checked", Toast.LENGTH_SHORT).show();

            RequestPermissions();
        }
    }


    public boolean CheckPermissions() {
        // this method is used to check permission
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
    }

    private void RequestPermissions() {
        ActivityCompat.requestPermissions(requireActivity(), new String[]{RECORD_AUDIO, WRITE_EXTERNAL_STORAGE}, REQUEST_AUDIO_PERMISSION_CODE);
    }


    public void pauseRecording() {

        if (mRecorder != null) {
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;
            videoOrImgStatus = "4";

            SendFile(mFileName);
        }
    }

    private void openLive(String userId) {


        mvvm.getSingleLiveUser(requireActivity(), AppConstants.USER_ID, userId).observe(requireActivity(), new Observer<SingleLiveUserRoot>() {
            @Override
            public void onChanged(SingleLiveUserRoot singleLiveUserRoot) {
                if (singleLiveUserRoot != null) {
                    if (singleLiveUserRoot.getLiveStatus()) {
                        App.getSingletone().setLiveType("");
                        Intent intent = new Intent(requireActivity(), CallActivity.class);
                        intent.putExtra("channelName", singleLiveUserRoot.getDetails().getChannelName());
                        intent.putExtra("userId", singleLiveUserRoot.getDetails().getUserId());
                        intent.putExtra("liveHostIds", singleLiveUserRoot.getDetails().getUserId());
                        intent.putExtra("liveType", "multiLive");
                        intent.putExtra("liveStatus", "host");
                        intent.putExtra("token", singleLiveUserRoot.getDetails().getToken());
                        intent.putExtra("name", singleLiveUserRoot.getDetails().getName());
                        intent.putExtra("liveHostId", singleLiveUserRoot.getDetails().getUserId());
                        if (singleLiveUserRoot.getDetails().getImageTitle() != null && !singleLiveUserRoot.getDetails().getImageTitle().isEmpty()) {
                            intent.putExtra("broadTitle", singleLiveUserRoot.getDetails().getImageTitle());
                        } else {
                            intent.putExtra("broadTitle", singleLiveUserRoot.getDetails().getName());
                        }
                        intent.putExtra("liveImage", singleLiveUserRoot.getDetails().getLiveimage());
                        intent.putExtra("image", singleLiveUserRoot.getDetails().getUserProfileImage());
                        intent.putExtra("status", "1");
                        intent.putExtra("dob", CommonUtils.ageCalcualte(singleLiveUserRoot.getDetails().getDob()));
                        intent.putExtra("gender", singleLiveUserRoot.getDetails().getGender());
//                                       intent.putExtra("userDob", CommonUtils.ageCalcualte(singleLiveUserRoot.getDetails().getUserDob()));
//                                       intent.putExtra("UserGender", singleLiveUserRoot.getDetails().getUserGender());
                        startActivity(intent);
                    } else {
                        if (requireActivity() != null) {
                            Toast.makeText(requireContext(), "Live Expired", Toast.LENGTH_SHORT).show();
                        }

                    }
                } else {

                }
            }
        });
    }

    private  String getCurrentTime() {
        //date output format
        DateFormat dateFormat = new SimpleDateFormat("HH:mm aa");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }
    private  String getDate(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        return  dateFormat.format(c.getTime());
    }


}