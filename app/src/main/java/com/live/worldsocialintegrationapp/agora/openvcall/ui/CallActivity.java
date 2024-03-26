package com.live.worldsocialintegrationapp.agora.openvcall.ui;

import static io.agora.rtc.Constants.AUDIO_PROFILE_MUSIC_HIGH_QUALITY_STEREO;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PictureInPictureParams;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.provider.Settings;

import android.util.Log;
import android.util.Rational;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.live.worldsocialintegrationapp.Activites.HomeActivity;

import com.live.worldsocialintegrationapp.Adapters.AdminRVAdapter;
import com.live.worldsocialintegrationapp.Adapters.CoverInfoRVAdapter;
import com.live.worldsocialintegrationapp.Adapters.EmojiGiftAdapter;
import com.live.worldsocialintegrationapp.Adapters.EmojiRVAdpter;
import com.live.worldsocialintegrationapp.Adapters.EventGiftRVAdapter;
import com.live.worldsocialintegrationapp.Adapters.InviteAudienceRVAdapter;
import com.live.worldsocialintegrationapp.Adapters.LocalAddedAdapter;
import com.live.worldsocialintegrationapp.Adapters.MultiLiveVideoAdapter;
import com.live.worldsocialintegrationapp.Adapters.MusicRVAdapter;
import com.live.worldsocialintegrationapp.Adapters.PrimeGiftAdapter;
import com.live.worldsocialintegrationapp.Adapters.ScoreBoardHistroyRVAdapter;
import com.live.worldsocialintegrationapp.Adapters.SendGiftUserRVAdapter;
import com.live.worldsocialintegrationapp.Adapters.SoundGiftRVAdapter;
import com.live.worldsocialintegrationapp.Adapters.TrickGiftRVAdapter;
import com.live.worldsocialintegrationapp.Fragments.ChatsFragments.ChatFragment;
import com.live.worldsocialintegrationapp.Fragments.LiveDiamondDialogFragment;
import com.live.worldsocialintegrationapp.Fragments.LiveFreeThemeFragment;
import com.live.worldsocialintegrationapp.Fragments.LivePurchasedThemeFragment;
import com.live.worldsocialintegrationapp.Fragments.LiveThemeFragment;
import com.live.worldsocialintegrationapp.Fragments.LuckyBagRulesFragment;
import com.live.worldsocialintegrationapp.Fragments.Profile.OtherUserTabs.FamilyBatchFragment;
import com.live.worldsocialintegrationapp.ModelClasses.AdminFirebaseRoot;
import com.live.worldsocialintegrationapp.ModelClasses.Family.GetInvitationsRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GetAppliedFrameRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GetAppliedGalleryModel;
import com.live.worldsocialintegrationapp.ModelClasses.GetEmoji.GetEmojiRoot;

import com.live.worldsocialintegrationapp.ModelClasses.GetLiveGiftHistoryRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GetLiveTotalDiamondRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GetUserCoinModel;
import com.live.worldsocialintegrationapp.ModelClasses.GetUserDetail.GetUserDetailRoot;
import com.live.worldsocialintegrationapp.ModelClasses.KickOutLiveRoot;
import com.live.worldsocialintegrationapp.ModelClasses.LiveUserHideUnhideRoot;
import com.live.worldsocialintegrationapp.ModelClasses.LockBroadCast.RootLockBroadCast;
import com.live.worldsocialintegrationapp.ModelClasses.LuckBag.CreateSuperLuckyBagRoot;
import com.live.worldsocialintegrationapp.ModelClasses.LuckBag.DeductCoinsRoot;
import com.live.worldsocialintegrationapp.ModelClasses.LuckBag.HitLuckyBagCoinsRoot;
import com.live.worldsocialintegrationapp.ModelClasses.LuckBag.HitSuperLuckyBagRoot;
import com.live.worldsocialintegrationapp.ModelClasses.LuckBag.LuckBagRoot;
import com.live.worldsocialintegrationapp.ModelClasses.LuckBag.SuperLuckyBagDetails;
import com.live.worldsocialintegrationapp.ModelClasses.Music;
import com.live.worldsocialintegrationapp.ModelClasses.SendOtpRoot;
import com.live.worldsocialintegrationapp.ModelClasses.SetLiveCoverImgRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.agora.firebase.AllPendingRequestAdapter;
import com.live.worldsocialintegrationapp.agora.firebase.ChatMessageModel;
import com.live.worldsocialintegrationapp.agora.firebase.CommentAdapter;
import com.live.worldsocialintegrationapp.agora.firebase.GiftModel;
import com.live.worldsocialintegrationapp.agora.firebase.GiftsListModel;
import com.live.worldsocialintegrationapp.agora.firebase.GoLiveModelClass;
import com.live.worldsocialintegrationapp.agora.firebase.HeartModel;
import com.live.worldsocialintegrationapp.agora.firebase.RequestMultiLiveAdapter;
import com.live.worldsocialintegrationapp.agora.firebase.UserJoinedAdapter;
import com.live.worldsocialintegrationapp.agora.firebase.ViewerAdapter;
import com.live.worldsocialintegrationapp.agora.heartview.HeartDrawable;
import com.live.worldsocialintegrationapp.agora.openvcall.model.AGEventHandler;
import com.live.worldsocialintegrationapp.agora.openvcall.model.ConstantApp;
import com.live.worldsocialintegrationapp.agora.openvcall.model.DuringCallEventHandler;
import com.live.worldsocialintegrationapp.agora.openvcall.model.EmojiGiftModel;
import com.live.worldsocialintegrationapp.agora.openvcall.model.GiftSendModel;
import com.live.worldsocialintegrationapp.agora.openvcall.model.GiftType.EventGift;
import com.live.worldsocialintegrationapp.agora.openvcall.model.GiftType.Privilege;
import com.live.worldsocialintegrationapp.agora.openvcall.model.GiftType.SoundGift;
import com.live.worldsocialintegrationapp.agora.openvcall.model.GiftType.Trick;
import com.live.worldsocialintegrationapp.agora.openvcall.model.PrimeGiftModel;
import com.live.worldsocialintegrationapp.agora.openvcall.model.SendEmojiGiftModel;
import com.live.worldsocialintegrationapp.agora.openvcall.model.SharedViewModel;
import com.live.worldsocialintegrationapp.agora.openvcall.ui.layout.GridVideoViewContainer;
import com.live.worldsocialintegrationapp.agora.openvcall.ui.layout.SmallVideoViewAdapter;
import com.live.worldsocialintegrationapp.agora.openvcall.ui.layout.SmallVideoViewDecoration;
import com.live.worldsocialintegrationapp.agora.propeller.Constant;
import com.live.worldsocialintegrationapp.agora.propeller.UserStatusData;
import com.live.worldsocialintegrationapp.agora.propeller.VideoInfoData;
import com.live.worldsocialintegrationapp.agora.propeller.ui.RecyclerItemClickListener;
import com.live.worldsocialintegrationapp.agora.propeller.ui.RtlLinearLayoutManager;
import com.live.worldsocialintegrationapp.databinding.ActivityCallBinding;
import com.live.worldsocialintegrationapp.databinding.ActivityCallBinding;
import com.live.worldsocialintegrationapp.databinding.DialogGiftBinding;
import com.live.worldsocialintegrationapp.databinding.DialogGiftEmojiBinding;
import com.live.worldsocialintegrationapp.databinding.DialogKikoutJoinedUserBinding;
import com.live.worldsocialintegrationapp.databinding.DialogLiveUserJoinedBinding;
import com.live.worldsocialintegrationapp.databinding.DialogRequestMultiLiveBinding;
import com.live.worldsocialintegrationapp.databinding.DialogSendMessageBinding;

import com.live.worldsocialintegrationapp.databinding.MultiliveListDialogBinding;
import com.live.worldsocialintegrationapp.databinding.ProfileBottomSheetBinding;
import com.live.worldsocialintegrationapp.room.AppDatabase;
import com.live.worldsocialintegrationapp.room.MusicTable;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.live.worldsocialintegrationapp.utils.BTReceiver;
import com.live.worldsocialintegrationapp.utils.CommonUtils;
import com.live.worldsocialintegrationapp.utils.RealPathUtil;
import com.opensource.svgaplayer.SVGACallback;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGADynamicEntity;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import org.jetbrains.annotations.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import in.aabhasjindal.otptextview.OtpTextView;
import io.agora.rtc.Constants;
import io.agora.rtc.IAudioEffectManager;
import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;
import io.agora.rtc.video.VideoCanvas;
import io.agora.rtc.video.VideoEncoderConfiguration;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class CallActivity extends BaseActivity implements DuringCallEventHandler {

    public static final String data_key = "data_key";
    float valueTime = 0;
    long resumeTime = 0;
    int resumeCheck = 0;
    private CountDownTimer musicCountDownTimer;
    int outSideBoxMusicCheck = 0;
    int peerVolume;
    int peerVol, poos;
    int musicOnOffStatus = 0;
    private String musictime,familyname = "";
    private String profileFrame,entryFrameEffect;
    private BTReceiver mHeadsetBroadcastReceiver;
    int SizeMB = 0;
    int jjj = 2;
    public static final int LAYOUT_TYPE_DEFAULT = 0;
    private Chronometer chronometer;
    private ArrayList<Music> audioList = new ArrayList<>();
    public static final int LAYOUT_TYPE_SMALL = 1;
    private static final int DRAW_OVER_OTHER_APP_PERMISSION = 123;
    private final static Logger log = LoggerFactory.getLogger(CallActivity.class);
    public static String liveType = "";
    public static String liveHostid = "";
    private final HashMap<Integer, SurfaceView> mUidsList = new HashMap<>(); // uid = 0 || uid == EngineConfig.mUid
    private final Handler mUIHandler = new Handler();
    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference ref = firebaseDatabase.getReference().child("userInfo");
    private final DatabaseReference liveDiamonds = firebaseDatabase.getReference().child("liveHostBackImg");
    private final DatabaseReference coverInfo = firebaseDatabase.getReference();
    private final DatabaseReference userLiveAnnouncement = firebaseDatabase.getReference().child("userLiveAnnouncement");
    private final DatabaseReference userLiveBackImgRef = firebaseDatabase.getReference().child("userLiveBackImgRef");
    private final DatabaseReference adminLiveRef = firebaseDatabase.getReference().child("adminLiveRef");
    private final DatabaseReference visualEffectsRef = firebaseDatabase.getReference().child("visualEffectsRef");
    private final DatabaseReference emojiRef = firebaseDatabase.getReference().child("emojiRef");
    private final DatabaseReference cleanUserCommentsRef = firebaseDatabase.getReference().child("cleanUserCommentsRef");
    private final DatabaseReference lockRef = firebaseDatabase.getReference().child("lockRef");
    private final DatabaseReference banChatRef = firebaseDatabase.getReference().child("banChatRef");
    private final DatabaseReference muteMicRef = firebaseDatabase.getReference().child("muteMicRef");
    private final DatabaseReference LuckBagRef = firebaseDatabase.getReference().child("LuckBagRef");
    private final DatabaseReference superLuckybagRef = firebaseDatabase.getReference().child("superLuckybagRef");
    private final DatabaseReference luckyDivideUsersRef = firebaseDatabase.getReference().child("luckyDivideUsersRef");
    private final DatabaseReference luckyBagDivideShareAnimation = firebaseDatabase.getReference().child("luckyBagDivideShareAnimation");
    private final DatabaseReference lockSeat = firebaseDatabase.getReference().child("lockSeat");
    private final DatabaseReference nameAniamtion = firebaseDatabase.getReference().child("nameAniamtion");
    private final DatabaseReference liveUsersRef = firebaseDatabase.getReference().child("liveUsersRef");
    private final DatabaseReference inviteAudienceRef = firebaseDatabase.getReference().child("inviteAudienceRef");
    private final DatabaseReference liveUserPeerId = firebaseDatabase.getReference().child("liveUserPeerId");
    private final DatabaseReference familyRef = firebaseDatabase.getReference().child("familyRef");
    private final DatabaseReference dynamicLinkKeys = firebaseDatabase.getReference().child("dynamicLinkKeys");
    private final List<ChatMessageModel> chatMessages = new ArrayList<>();
    private List<ChatMessageModel> chatMessages1 = new ArrayList<>();
    private final List<GoLiveModelClass> viewerList = new ArrayList<>();
    private final List<GoLiveModelClass> banList = new ArrayList<>();
    public int mLayoutType = LAYOUT_TYPE_DEFAULT;
    private List<String> list = new ArrayList<>();
    private String emojiImage;
    int[] drawableIds = {R.drawable.ic_red_heart, R.drawable.ic_blue_heart, R.drawable.ic_peach_heart, R.drawable.ic_white_heart,
            R.drawable.ic_pink_heart, R.drawable.ic_green_heart, R.drawable.ic_yello_heart, R.drawable.ic_black_heart,};
    AlertDialog alertDialog;
    BottomSheetDialog requestListMultiBottomSheet;
    CountDownTimer countDownTimer;
    int emptyPosition;
    BottomSheetDialog sendMessageBottomSheet;
    boolean isJoined = false;
    UserJoinedAdapter userJoinedAdapter;
    private MusicRVAdapter musicRVAdapter;
    private GridVideoViewContainer mGridVideoViewContainer;
    private RelativeLayout mSmallVideoViewDock;
    private final boolean mVideoMuted = false;
    private final boolean mAudioMuted = false;
    private volatile boolean mMixingAudio = false;
    private volatile int mAudioRouting = Constants.AUDIO_ROUTE_DEFAULT;
    private volatile boolean mFullScreen = false;
    private boolean mIsLandscape = false;
    private RecyclerView musicRv;
    private SmallVideoViewAdapter mSmallVideoViewAdapter;
    private String getChannelName = "";
    String meriID;
    private String getAccessToken = "";
    private String liveStatus = "";
    private String name = "", image = "", count = "", broadTitle = "", dob = "", gender = "", userDob = "", liveImage = "",
            userGender = "", hostUsername = "", username = "",statuslive="", familyJoinId;
    ActivityCallBinding binding;
    private String otherUserId = "";
    private final List<GoLiveModelClass> requestMultiLiveList = new ArrayList<>();
    private final List<String> muteUsers = new ArrayList<>();
    private CommentAdapter commentAdapter;
    private ViewerAdapter viewerAdapter;
    private final List<Drawable> drawablesList = new ArrayList<>();
    private RequestMultiLiveAdapter requestMultiLiveAdapter;
    private boolean isLiveConnected = false;
    //private CreateLiveHistoryModel createLiveHistory;
    private String liveId = "";
    private Long currentTimeStamp;
    private List<PrimeGiftModel.Details> arrayList = new ArrayList<>();
    private List<EmojiGiftModel.Detail> arrayListEmoji = new ArrayList<>();
    private final List<GoLiveModelClass> liveJoinedUserList = new ArrayList<>();
    private final List<GoLiveModelClass> liveJoinedHostUserList = new ArrayList<>();
    private MultiLiveVideoAdapter multiLiveVideoAdapter;
    private boolean isMute = false;
    private AllPendingRequestAdapter allPendingRequestAdapter;
    String userId, userName, userImage, endLive, status = "", liveHostBackImg = "", stringCoverPhotoPath = "", coverInfotopicString = "", profileAge, profileGender,hostStatusUser="";
    private boolean speakerImgCheck = true;
    ImageView coverInfoUserImg;
    int coverPhotoRequstCode = 95;
    private MultipartBody.Part reqCoverLiveImg;
    ScoreBoardHistroyRVAdapter scoreBoardHistroyRVAdapter;
    List<GetLiveGiftHistoryRoot.Detail> liveGiftHistoryList;
    CoverInfoRVAdapter coverInfoRVAdapter;
    ArrayList<String> coverInfoTextList = new ArrayList<>();
    private RequestBody req_LiveTopic, req_UserId, req_LiveId, coverImgTitle_req;
    int adminStatus = 0; //0 means not admin
    List<AdminFirebaseRoot> adminList;
    AdminRVAdapter adminRVAdapter;
    String liveUsersCount = "1";
    AppCompatTextView familyNameTv;
    SVGAImageView DpFrame;
    String frame;
    private SVGAParser parser;
    int j = 2, liveChatBanned = 0, muteMicStatus = 0;
    String giftEffect = "0", vehicleEffect = "0", giftSoundEffect = "0", rewardEffect = "0"; //Visual Effects Strings
    public static int backDialogShow = 0, emojiCheck = 0, cleanChatCheck = 0;
    IAudioEffectManager audioEffectManager;
    public static String audioPath, audioPath1, emojiSenderIdCheck;
    public MusicTable musicDetails;
    public static int check = 0;
    LuckBagRoot luckBagRootDetail;
    String LuckBagCoinId = "";
    String LuckBagQuantityId = "", luckBagCoinDeductedUserId, luckBagDeductedId, deductType, luckBagType = "1";
    String adminId = "", adminIdThroughCallback = "";
    LiveThemeFragment themesFragment;
    int cnt = 0;
    int possss = 0;
    int musicpos;
    //gifts
    List<Privilege> privilegesList = new ArrayList<>();
    List<EventGift> eventGiftList = new ArrayList<>();
    List<SoundGift> soundGiftList = new ArrayList<>();
    List<Trick> trickList = new ArrayList<>();
    ArrayList<GoLiveModelClass> inviteAudienceList = new ArrayList<>();
    List<MusicTable> musicList = new ArrayList<>();
    protected static final String PREFS_FILE = "device_id.xml";
    protected static final String PREFS_DEVICE_ID = "device_id";
    protected static UUID uuid;
    AppDatabase appDatabase;
    int musicStatus = 0;
    int MusicId = 0;
    AudioManager audioManager = null;
    AppCompatSeekBar seekbar1;
    int REQUEST_ENABLE_BT = 2;
    private boolean muteMySelf = false;
    private String mutevalueOfUSer = "1";
    String coverImageTitle, coverImage;
    private int pos;
    private Mvvm callViewModel;
    String galleryAppliedImageTheme;
    SharedViewModel sharedViewModel;

    public static String getCurrentTime() {
        //date output format
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }
    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCallBinding.inflate(LayoutInflater.from(CallActivity.this));
        setContentView(binding.getRoot());
        profileFrame = App.getSharedpref().getString("appliedFrame");
        entryFrameEffect = App.getSharedpref().getString("entryFrame");
        otherUserId = getIntent().getStringExtra("userId");
        callViewModel = new ViewModelProvider(this).get(Mvvm.class);
        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);

//        liveDiamonds.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    binding.liveDiamondsTv.setText(snapshot.child("liveDiamonds").getValue().toString());
//                } else {}
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


        sharedViewModel.getBackgroundImageUrl().observe(this, s -> {
            if (!s.isEmpty()){
                Glide.with(CallActivity.this).load(s).placeholder(R.drawable.themeblack).into(binding.imgBackground);
                new Handler().postDelayed(() -> Glide.with(CallActivity.this).load( App.getSharedpref().getString("theme")).placeholder(R.drawable.themeblack).into(binding.imgBackground),6000);
            }
        });
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        getChannelName = getIntent().getStringExtra("channelName");
        liveType = getIntent().getStringExtra("liveType");
        liveStatus = getIntent().getStringExtra("liveStatus");
        getAccessToken = getIntent().getStringExtra("token");

        name = getIntent().getStringExtra("name");
        image = getIntent().getStringExtra("image");
        count = getIntent().getStringExtra("count");
        this.liveHostBackImg = getIntent().getStringExtra("liveHostIds");
        status = getIntent().getStringExtra("status");
        liveId = getIntent().getStringExtra("liveId");
        App.getSharedpref().saveString("liveIId",liveId);
        broadTitle = getIntent().getStringExtra("broadTitle");
        dob = getIntent().getStringExtra("dob");
        gender = getIntent().getStringExtra("gender");
        userDob = getIntent().getStringExtra("userDob");
        userGender = getIntent().getStringExtra("UserGender");
        liveImage = getIntent().getStringExtra("liveImage");
        hostUsername = getIntent().getStringExtra("hostUsername");
        hostStatusUser = getIntent().getStringExtra("hostStatusUser");
        galleryAppliedImageTheme = getIntent().getStringExtra("galleryImageTheme");
        statuslive = getIntent().getStringExtra("statuslive");

        Log.d("onCreate", "onCreate: ");

        //Get some Functionality
        chronometer = findViewById(R.id.simpleChronometer);
        chronometer.start();
        mHeadsetBroadcastReceiver = new BTReceiver();
        appDatabase = AppDatabase.getInstance(CallActivity.this);
        getCurrentDateAndTime();
//      loadingLottie();
        binding.callActivityFrameLayout.setVisibility(View.GONE);
        firebaseDatabase.goOnline();
        currentTimeStamp = getCurrentTimeStamp();
        Log.d("MULTILIVE", "live1: " + image);
        audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
        audioManager.setSpeakerphoneOn(true);
        getNameAnimationFromFirebase();

        new Mvvm().getAppliedGalleryModelLiveData(this,AppConstants.USER_ID).observe(this, new Observer<GetAppliedGalleryModel>() {
            @Override
            public void onChanged(GetAppliedGalleryModel getAppliedGalleryModel) {
                if (getAppliedGalleryModel != null){
                    if (getAppliedGalleryModel.getStatus()==1){
                        if (!getAppliedGalleryModel.getDetails().getImage().isEmpty()){
                            if (liveHostBackImg != null && !liveHostBackImg.isEmpty()){
                                Glide.with(CallActivity.this).load(getAppliedGalleryModel.getDetails().getImage()).placeholder(R.drawable.themeblack).into(binding.imgBackground);
                            }
                        }else {

                        }
                    }
                }
            }
        });

        getAllTotalDiamonds();

        if (hostStatusUser!=null){
            if (hostStatusUser.equalsIgnoreCase("2")){
                binding.anchorlayout.setVisibility(View.VISIBLE);
            }
        }else {
            binding.anchorlayout.setVisibility(View.GONE);
        }
        if(App.getSharedpref().getString("hostStatus").equalsIgnoreCase("2")){
            if (liveHostBackImg.equalsIgnoreCase(AppConstants.USER_ID)){
                binding.anchorlayout.setVisibility(View.VISIBLE);
            }else {
                binding.anchorlayout.setVisibility(View.GONE);
            }
        } else {}

        if (liveHostBackImg.equalsIgnoreCase(AppConstants.USER_ID)) {
            HashMap<String, String> liveUserHs = new HashMap<>();
            liveUserHs.put("liveId", liveId);
            liveUserHs.put("hostId", liveHostBackImg);
            liveUserHs.put("liveType", liveType);
            liveUsersRef.child(liveHostBackImg).setValue(liveUserHs);
            App.getSharedpref().saveString("liveId", liveId);
            App.getSharedpref().saveString("liveType", liveType);
            App.getSharedpref().saveString("hostId", liveHostBackImg);
        }
        if (otherUserId.equalsIgnoreCase(liveHostBackImg)) {
            GoLiveModelClass goLiveModelClass = new GoLiveModelClass();
            goLiveModelClass.setName(otherUserId);
            goLiveModelClass.setImage(image);
            goLiveModelClass.setUserName(userName);
            goLiveModelClass.setLive(true);
        }
        MultiLiveVideoAdapter.directHostId = getIntent().getStringExtra("liveHostIds");
        InviteAudienceRVAdapter.directHostId = getIntent().getStringExtra("liveHostIds");
        MultiLiveVideoAdapter.liveType = getIntent().getStringExtra("liveType");
        Glide.with(this).load(image).placeholder(R.drawable.ic_baseline_person_24).into(binding.imgHostProfile);

        binding.imgHostProfile.setOnClickListener(view -> {
            GoLiveModelClass goLiveModelClass = new GoLiveModelClass();
            goLiveModelClass.setImage(image);
            goLiveModelClass.setName(name);
            goLiveModelClass.setUserID(liveHostBackImg);
            openOtherUserProfile(goLiveModelClass, adminStatus);
        });

        binding.imgProfileuser.setOnClickListener(view -> {
            GoLiveModelClass goLiveModelClass = new GoLiveModelClass();
            goLiveModelClass.setImage(image);
            goLiveModelClass.setName(name);
            goLiveModelClass.setUserID(liveHostBackImg);
            openOtherUserProfile(goLiveModelClass, adminStatus);
        });

        if (AppConstants.USER_ID.equalsIgnoreCase(liveHostBackImg)) {
//            String familyName = App.getSharedpref().getString("familyName");
            String familyId = App.getSharedpref().getString("familyId");

            if (!familyname.equalsIgnoreCase("") && !familyname.isEmpty()) {
                HashMap<String, String> familyHs = new HashMap<>();
                familyHs.put("familyName", familyname);
                familyHs.put("familyId", familyId);
                familyRef.child(liveHostBackImg).setValue(familyHs);
            }
        }

        familyRef.child(liveHostBackImg).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    binding.liveFamilyLlayout.setVisibility(View.VISIBLE);
                    binding.familyNameTv.setText(familyname);
//                    binding.familyNameTv.setText(snapshot.child("familyName").getValue().toString());
                    binding.familyNameTv.setSelected(true);
                    binding.liveFamilyLlayout.setOnClickListener(view -> {
                        App.getSharedpref().saveString("liveFamily", snapshot.child("familyId").getValue().toString());
                        setPIPScreen();
                    });

                } else {
                    binding.liveFamilyLlayout.setVisibility(View.GONE);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        Glide.with(this).load(image).into(binding.imgHostProfileInfo);
        userImage = App.getSharedpref().getString("image");
        userName = App.getSharedpref().getString("name");
        userId = App.getSharedpref().getString("userId");
        CommentAdapter.liveHostBackImg = liveHostBackImg;

        if (name != null) {
            binding.txtUserHostName.setText(name);
        }

        binding.txtUserHostNameInfo.setText(name);
        liveJoinedHostUserList.add(new GoLiveModelClass());
        liveJoinedHostUserList.add(new GoLiveModelClass());
        liveJoinedHostUserList.add(new GoLiveModelClass());
        liveJoinedHostUserList.add(new GoLiveModelClass());
        liveJoinedHostUserList.add(new GoLiveModelClass());
        liveJoinedHostUserList.add(new GoLiveModelClass());
        liveJoinedHostUserList.add(new GoLiveModelClass());
        liveJoinedHostUserList.add(new GoLiveModelClass());

        setAcceptedGustHostAdapter(liveJoinedHostUserList);
        binding.showLiveUserslLyout.setOnClickListener(view -> inviteAudienceDialogBox(""));

        if (!liveStatus.equalsIgnoreCase("host")) {
            ref.child(userId).child(liveType).removeValue();
            hitCreateLiveHistoryApi(userId, getCurrentTime(), "", liveType);

            binding.callActvityDateTv.setOnClickListener(view -> {
            });

        } else {
            liveId = getIntent().getStringExtra("liveId");
        }
//sumit
      //  superLuckyBagHitApi();

        getBackgroundImageFromFirebase();

        getMultiLiveRequest();
        getBanListFirebase();
        getMuteUsers();
        initHeartDrawables();

        binding.callSpearkImg.setImageResource(R.drawable.volume_up_24);
        rtcEngine().setEnableSpeakerphone(true);
        rtcEngine().setDefaultAudioRoutetoSpeakerphone(true);
        rtcEngine().enableAudio();

        coverInfo.child("CoverInfo").child(otherUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    try {
                        if (otherUserId != AppConstants.USER_ID || otherUserId == AppConstants.USER_ID) {
                            Glide.with(binding.imgProfileuser.getContext()).load(snapshot.child("photopath").getValue().toString()).placeholder(R.drawable.ic_baseline_account_circle_24).into(binding.imgProfileuser);
                            binding.txtUserName.setText(snapshot.child("coverInfoLiveTitle").getValue().toString());
                            coverImage = snapshot.child("photopath").getValue().toString();
                            coverImageTitle = snapshot.child("coverInfoLiveTitle").getValue().toString();
                        } else {
                        }
                    } catch (Exception e) {
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        if (broadTitle != null) {
            binding.txtUserName.setText(broadTitle);
        } else {
            if (name != null) {
                binding.txtUserName.setText(name);
            } else {
                binding.txtUserName.setText("Username");
            }
        }

        binding.txtUserName.setSelected(true);

        binding.showLiveDiamondslLayout.setOnClickListener(view -> diamondsDialogBox());

        animationRecursion(1);

        lockRef.child(liveHostBackImg).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String status = snapshot.child("status").getValue().toString();
                    if (status.equalsIgnoreCase("0")) {
                        binding.roomLockImg.setVisibility(View.GONE);
                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) binding.txtFollowers.getLayoutParams();
                        params.leftMargin = 15;
                    } else {
                        binding.roomLockImg.setVisibility(View.VISIBLE);
                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) binding.txtFollowers.getLayoutParams();
                        params.leftMargin = 5;
                    }
                } else {
                    binding.roomLockImg.setVisibility(View.GONE);
                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) binding.txtFollowers.getLayoutParams();
                    params.leftMargin = 15;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        adminLiveRef.child(liveHostBackImg).child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    adminStatus = 1;
                    binding.menuBoard.setVisibility(View.VISIBLE);
                } else {
                    adminStatus = 0;
                    if (otherUserId.equalsIgnoreCase(userId)) {
                        binding.menuBoard.setVisibility(View.VISIBLE);
                    } else {
                        binding.menuBoard.setVisibility(View.GONE);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        adminLiveRef.child(liveHostBackImg).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        GoLiveModelClass goLiveModelClass = new GoLiveModelClass();
                        goLiveModelClass.setImage(snapshot1.child("adminImg").getValue().toString());
                        goLiveModelClass.setUserID(snapshot1.child("adminId").getValue().toString());
                        goLiveModelClass.setAdminStatus(true);
                        goLiveModelClass.setName(snapshot1.child("adminName").getValue().toString());
                        inviteAudienceList.add(goLiveModelClass);
                    }
                } else {
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        binding.musicPlayCirImg.setOnClickListener(view -> playMusicDialogBox());

        emojiRef.child(liveHostBackImg).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    if (snapshot.hasChild("status") && snapshot.child("status").getValue().toString().equalsIgnoreCase("1")) {
                        emojiRef.child(liveHostBackImg).removeValue();
                    } else {
                        String emojiImg = snapshot.child("emojiImg").getValue().toString();
                        String emojiSenderId = snapshot.child("emojiSenderId").getValue().toString();
                        String emojiSenderName = snapshot.child("emojiSenderName").getValue().toString();
                        String emojiSenderImg = snapshot.child("emojiSenderImg").getValue().toString();
                        String status = snapshot.child("status").getValue().toString();
                        String giftStatus;
                        if (!snapshot.child("giftStatus").getValue().toString().isEmpty()){
                             giftStatus=snapshot.child("giftStatus").getValue().toString();
                        }else {
                            giftStatus="2";
                        }

                        binding.giftSendUserId.setText(emojiSenderId);
                        binding.giftSendUserName.setText(emojiSenderName);
                        Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink_animation);
                        if (emojiSenderId.equalsIgnoreCase(liveHostBackImg) && status.equalsIgnoreCase("0")) {

                            if (giftStatus.equalsIgnoreCase("1")){
//                                binding.globaGiftAnimation.startAnimation(animation2);
//                                binding.globaGiftAnimation.setVisibility(View.VISIBLE);
                                SVGAParser parserr = new SVGAParser(CallActivity.this);
                                try { // new URL needs try catch.
                                    parserr.decodeFromURL(new URL(emojiImg), new SVGAParser.ParseCompletion() {
                                        @Override
                                        public void onComplete(@NotNull SVGAVideoEntity videoItem) {
                                            SVGADynamicEntity dynamicEntity = new SVGADynamicEntity();
                                            dynamicEntity.setDynamicImage(emojiImg, "99"); // Here is the KEY implementation.
                                            SVGADrawable drawable = new SVGADrawable(videoItem, dynamicEntity);
                                            binding.globalGifts.setImageDrawable(drawable);
                                            binding.globalGifts.setVisibility(View.VISIBLE);
                                            binding.globalGifts.setLoops(1);
                                            binding.globalGifts.setCallback(new SVGACallback() {
                                                @Override
                                                public void onPause() {

                                                }

                                                @Override
                                                public void onFinished() {
                                                    binding.globalGifts.stopAnimation();
                                                    binding.globalGifts.setVisibility(View.GONE);
                                                }

                                                @Override
                                                public void onRepeat() {

                                                }

                                                @Override
                                                public void onStep(int i, double v) {

                                                }
                                            });

                                            binding.globalGifts.startAnimation();
//                                            new Handler().postDelayed(new Runnable() {
//                                                @Override
//                                                public void run() {
//                                                    binding.globalGifts.stopAnimation();
//                                                    binding.globalGifts.setVisibility(View.GONE);
//                                                }
//                                            },14000);
                                        }

                                        @Override
                                        public void onError() {

                                        }
                                    }, null);

                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                }

//                                binding.muCarsRVImagee.setCallback(new SVGACallback() {
//                                    @Override
//                                    public void onPause() {
//
//                                    }
//
//                                    @Override
//                                    public void onFinished() {
//
//                                        Toast.makeText(CallActivity.this, "on finsish", Toast.LENGTH_SHORT).show();
//                                        binding.muCarsRVImagee.stopAnimation();
//                                        binding.muCarsRVImagee.setVisibility(View.GONE);
//                                        binding.muCarsRVImagee.setVisibility(View.INVISIBLE);
//
//                                    }
//
//                                    @Override
//                                    public void onRepeat() {
//
//                                    }
//
//                                    @Override
//                                    public void onStep(int i, double v) {
//
//                                    }
//                                });
                            }else {
                                Glide.with(getApplicationContext()).load(emojiImg).into(binding.emojiShowImg);
                                binding.emojiShowImg.setVisibility(View.VISIBLE);
                                new Handler().postDelayed(() -> binding.emojiShowImg.setVisibility(View.GONE), 5000);
                            }
//                            binding.globaGiftAnimation.setVisibility(View.GONE);
//                            animation2.cancel();
                            emojiRef.child(liveHostBackImg).removeValue();
                        } else if (status.equalsIgnoreCase("0")) {
                            MultiLiveVideoAdapter.userId = emojiSenderId;
                            MultiLiveVideoAdapter.emoji = emojiImg;
                            MultiLiveVideoAdapter.hostId = liveHostBackImg;
                            multiLiveVideoAdapter.notifyDataSetChanged();
                        } else {
                            if (giftStatus.equalsIgnoreCase("1")){
//                                binding.globaGiftAnimation.startAnimation(animation2);
//                                binding.globaGiftAnimation.setVisibility(View.VISIBLE);
                                //SVGAParser parserrr = new SVGAParser(CallActivity.this);
                                try { // new URL needs try catch.
                                    parser.decodeFromURL(new URL(emojiImg), new SVGAParser.ParseCompletion() {
                                        @Override
                                        public void onComplete(@NotNull SVGAVideoEntity videoItem) {
                                            SVGADynamicEntity dynamicEntity = new SVGADynamicEntity();
                                            dynamicEntity.setDynamicImage(emojiImg, "99"); // Here is the KEY implementation.
                                            SVGADrawable drawable = new SVGADrawable(videoItem, dynamicEntity);
                                            binding.globalGifts.setImageDrawable(drawable);
                                            binding.globalGifts.setVisibility(View.VISIBLE);
                                            binding.globalGifts.setLoops(1);
                                            binding.globalGifts.setCallback(new SVGACallback() {
                                                @Override
                                                public void onPause() {

                                                }

                                                @Override
                                                public void onFinished() {
                                                    binding.globalGifts.stopAnimation();
                                                    binding.globalGifts.setVisibility(View.GONE);
                                                }

                                                @Override
                                                public void onRepeat() {

                                                }

                                                @Override
                                                public void onStep(int i, double v) {

                                                }
                                            });
                                            binding.globalGifts.startAnimation();

//                                            new Handler().postDelayed(new Runnable() {
//                                                @Override
//                                                public void run() {
//                                                    binding.globalGifts.stopAnimation();
//                                                    binding.globalGifts.setVisibility(View.GONE);
//                                                }
//                                            },14000);
                                        }

                                        @Override
                                        public void onError() {

                                        }
                                    }, null);
                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                }
                            }else {
                                binding.liveShowEmojiImg.setVisibility(View.VISIBLE);
                                Glide.with(getApplicationContext()).load(emojiImg).into(binding.liveShowEmojiImg);
                                new Handler().postDelayed(() -> binding.liveShowEmojiImg.setVisibility(View.GONE), 2000);
                            }

                            emojiRef.child(liveHostBackImg).removeValue();
                        }
                    }
                } else {
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        inviteAudienceRef.child(liveHostBackImg).child(AppConstants.USER_ID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    Dialog dialog_share = new Dialog(CallActivity.this);
                    dialog_share.setContentView(R.layout.invite_audience_in_live_dialogbox);
                    dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                    dialog_share.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                    dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog_share.setCanceledOnTouchOutside(false);
                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                    layoutParams.copyFrom(dialog_share.getWindow().getAttributes());
                    layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                    layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    dialog_share.getWindow().setAttributes(layoutParams);
                    Window window = dialog_share.getWindow();
                    window.setGravity(Gravity.CENTER_HORIZONTAL);
                    dialog_share.show();

                    AppCompatButton inviteAudienceStandupBtn = dialog_share.findViewById(R.id.inviteAudienceStandupBtn);
                    AppCompatButton inviteAudienceSitBtn = dialog_share.findViewById(R.id.inviteAudienceSitBtn);

                    inviteAudienceStandupBtn.setOnClickListener(view -> {
                        inviteAudienceRef.child(liveHostBackImg).child(AppConstants.USER_ID).removeValue();
                        dialog_share.dismiss();
                    });

                    inviteAudienceSitBtn.setOnClickListener(view -> {
                        String seatPosition = snapshot.getValue().toString();
                        if (seatPosition != null) {
                            sendRequestForMultiLive(seatPosition, "");
                            dialog_share.dismiss();
                            inviteAudienceRef.child(liveHostBackImg).child(AppConstants.USER_ID).removeValue();
                        }
                    });
                } else {

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        LuckBagRef.child(liveHostBackImg).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    luckyboxAnimation(1);
                    String luckyBagUserId = snapshot.child("userId").getValue().toString();
                    String quantity = snapshot.child("quantity").getValue().toString();
                    String detectCoinsId = snapshot.child("detectCoinsId").getValue().toString();
                    String status = snapshot.child("status").getValue().toString();
                    String dividePerShare = snapshot.child("dividePerShare").getValue().toString();
                    String luckyBagtype = snapshot.child("luckyBagtype").getValue().toString();

                    if (luckyBagtype.equalsIgnoreCase("luckyBagCoins")) {
                        deductType = "1";
                    } else {
                        deductType = "2";
                    }
                    binding.luckGiftImg.setOnClickListener(view -> {
                        if (!luckyBagUserId.equalsIgnoreCase(AppConstants.USER_ID)) {
                            callViewModel.hitLuckBagCoins(CallActivity.this, AppConstants.USER_ID, detectCoinsId, deductType).observe(CallActivity.this, new Observer<HitLuckyBagCoinsRoot>() {
                                @Override
                                public void onChanged(HitLuckyBagCoinsRoot hitLuckyBagCoinsRoot) {
                                    if (hitLuckyBagCoinsRoot != null) {
                                        if (hitLuckyBagCoinsRoot.getSuccess().equalsIgnoreCase("1")) {
                                            binding.luckGiftImg.setVisibility(View.VISIBLE);
                                            luckyDivideUsersRef.child(liveHostBackImg).child(AppConstants.USER_ID).setValue("1");
                                            HashMap<String, String> divideHs = new HashMap<>();
                                            divideHs.put("name", hitLuckyBagCoinsRoot.getDetails().getName());
                                            divideHs.put("image", hitLuckyBagCoinsRoot.getDetails().getImageDp());
                                            divideHs.put("coins", hitLuckyBagCoinsRoot.getDetails().getPerShareCoins());
                                            divideHs.put("userId", hitLuckyBagCoinsRoot.getDetails().getUserId());
                                            luckyBagDivideShareAnimation.child(liveHostBackImg).child(AppConstants.USER_ID).setValue(divideHs);
                                            binding.luckGiftImg.setVisibility(View.GONE);
                                        } else {
                                            luckyDivideUsersRef.child(liveHostBackImg).child(AppConstants.USER_ID).addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot12) {
                                                    if (snapshot12.exists()) {
//                                                        Toast.makeText(CallActivity.this, "You already got", Toast.LENGTH_SHORT).show();
                                                    } else {
                                                        if (hitLuckyBagCoinsRoot.getStatus() == false) {
                                                            binding.luckGiftImg.setVisibility(View.GONE);
                                                            LuckBagRef.child(liveHostBackImg).removeValue();
                                                        }
                                                    }
                                                }
                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {
                                                }
                                            });
                                        }
                                    } else {
                                        Toast.makeText(CallActivity.this, "Technical issue", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                        }
                    });
                } else {
                    binding.luckGiftImg.setVisibility(View.GONE);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        luckyBagDivideShareAnimation.child(liveHostBackImg).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        String name = snapshot1.child("name").getValue().toString();
                        String image = snapshot1.child("image").getValue().toString();
                        String coins = snapshot1.child("coins").getValue().toString();
                        String userId = snapshot1.child("userId").getValue().toString();
                        binding.luckybagLootedRL.setVisibility(View.VISIBLE);
                        binding.luckyBaglottedTv.setText(name + " got " + coins + " coins");
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                binding.luckybagLootedRL.setVisibility(View.GONE);
                                binding.luckyBaglottedTv.setVisibility(View.GONE);
                                luckyBagDivideShareAnimation.child(liveHostBackImg).child(userId).removeValue();
                            }
                        },4000);
                        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_to_left_animation);
                        binding.luckybagLootedRL.startAnimation(animation);
                        Handler handler = new Handler();
                        handler.postDelayed(
                                new Runnable() {
                                    public void run() {
                                        binding.luckybagLootedRL.setVisibility(View.GONE);
                                        luckyBagDivideShareAnimation.child(liveHostBackImg).child(userId).removeValue();
                                    }
                                }, 4000);
                    }
                } else {
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        if (liveImage != null && !liveImage.isEmpty()) {
            Glide.with(this).load(liveImage).placeholder(R.drawable.ic_baseline_account_circle_24).into(binding.imgProfileuser);
        } else {
            Glide.with(this).load(image).placeholder(R.drawable.ic_baseline_account_circle_24).into(binding.imgProfileuser);
        }
        binding.menuBoard.setOnClickListener(view -> menuDialogBox());
        binding.callPowerButton.setOnClickListener(view -> onBackPressed());

        banChatRef.child(liveHostBackImg).child(AppConstants.USER_ID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String chatBanned = snapshot.getValue().toString();
                    liveChatBanned = Integer.parseInt(chatBanned);
                } else {
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        getAppliedFrameApi(liveHostBackImg, findViewById(R.id.liveProfieFrame));
        binding.callMsgLinearLayout.setOnClickListener(view -> openDialogForSendMessage());
        ref.child(otherUserId).child(liveType).child(otherUserId).child("multiLiveRequest").child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    try {
                        if (snapshot.child("mute").getValue().toString() != null) {
                            if (snapshot.child("mute").getValue().toString().equalsIgnoreCase("0")) {
                                binding.imgMuteMic.setImageResource(R.drawable.ic_baseline_mic_off_24);
                                mutevalueOfUSer = "0";
                                binding.voiceIndicationLottie.setVisibility(View.GONE);
                                binding.voiceIndicationLottie.cancelAnimation();
                                rtcEngine().muteLocalAudioStream(true);
                            } else {
                                mutevalueOfUSer = "1";
                                binding.imgMuteMic.setImageResource(R.drawable.ic_baseline_mic_24);
                                binding.voiceIndicationLottie.setVisibility(View.VISIBLE);
                                binding.voiceIndicationLottie.playAnimation();
                                rtcEngine().muteLocalAudioStream(false);
                            }
                        } else {
                        }
                    } catch (Exception e) {
//                        Toast.makeText(CallActivity.this, "xception" + e, Toast.LENGTH_SHORT).show();
                    }
                } else {
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        binding.callMuteIMg.setOnClickListener(view -> muteMicRef.child(liveHostBackImg).child(AppConstants.USER_ID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String status = "";
                    if (snapshot.getValue().toString() != null) {
                        status = snapshot.getValue().toString();
                    }
                    if (status.equalsIgnoreCase("0")) {
                        binding.imgMuteMic.setImageResource(R.drawable.ic_baseline_mic_off_24);
                    } else {
                        onVoiceMuteClicked(binding.imgMuteMic, "1");
                    }
                } else {
                    onVoiceMuteClicked(binding.imgMuteMic, "");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        }));

        binding.giftBoxImg.setOnClickListener(view -> openGiftDialog());

        binding.callEmojiLinearLayout.setOnClickListener(view -> emojiBottomSheet());

        binding.callSpearkImg.setOnClickListener(view -> switchSpeakerState());
        binding.menuLinearLayout.setOnClickListener(view -> navBottomMenuDialogBox());

        if (liveHostBackImg != null) {
            userLiveAnnouncement.child(liveHostBackImg).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        binding.announcementTv.setVisibility(View.VISIBLE);
                        binding.announcementTv.setText("Room announcement: " + snapshot.getValue().toString());
                        binding.announcementTv.setSelected(true);
                    } else {
                        binding.announcementTv.setVisibility(View.GONE);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }

        binding.mailLiveCirImg.setOnClickListener(view -> {
            findViewById(R.id.callAcitivtyMainRL).setVisibility(View.GONE);
            binding.callActivityFrameLayout.setVisibility(View.VISIBLE);
            ChatFragment chatFragment = new ChatFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("backPressed", 1);
            chatFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.callActivityFrameLayout, chatFragment).addToBackStack(null).commit();
        });

        hitApi(liveHostBackImg);

        binding.liveFamilyLlayout.setOnClickListener(v -> {
            if (familyJoinId !=null){
                getSupportFragmentManager().beginTransaction().replace(R.id.callActivityFrameLayout,new FamilyBatchFragment()).addToBackStack(null).commit();
            }else {
                Toast.makeText(this, "technical issue", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void hitApi(String liveHostBackImg) {

        if (liveHostBackImg !=null){
            meriID = liveHostBackImg;
        }else {
            meriID = AppConstants.USER_ID;
        }

        callViewModel.getUserDetail(CallActivity.this, AppConstants.USER_ID,meriID,meriID).observe(CallActivity.this, response -> {
            if (response !=null){
                if (response.getStatus().equalsIgnoreCase("1")){
                    familyname = response.getDetails().getFamilyJoinName();
                    if (response.getDetails().familyJoinStatus){
                        binding.liveFamilyLlayout.setVisibility(View.VISIBLE);
                        binding.familyNameTv.setText(response.getDetails().getFamilyJoinName());
                        familyJoinId = response.getDetails().getFamilyJoinId();
                    }else {
                        binding.liveFamilyLlayout.setVisibility(View.GONE);
                    }

                }
            }
        });
    }

    boolean isSpeakerOn = true;
    public void switchSpeakerState() {
        if (isSpeakerOn) {
            isSpeakerOn = false;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                setCommunicationDevice(getApplicationContext(), AudioDeviceInfo.TYPE_BUILTIN_EARPIECE);
            } else {
                audioManager.setSpeakerphoneOn(false);
            }
            binding.callSpearkImg.setImageResource(R.drawable.ic_baseline_volume_off_24);
            rtcEngine().muteAllRemoteAudioStreams(true);
            pause_music();
        } else {
            isSpeakerOn = true;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                setCommunicationDevice(getApplicationContext(), AudioDeviceInfo.TYPE_BUILTIN_SPEAKER);
            } else {
                audioManager.setSpeakerphoneOn(true);
                rtcEngine().enableAudio();
            }
            binding.callSpearkImg.setImageResource(R.drawable.volume_up_24);
            rtcEngine().muteAllRemoteAudioStreams(false);
            if (musicOnOffStatus == 1) {
                resume_music();
            } else {
                pause_music();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.S)
    public void setCommunicationDevice(Context context, Integer targetDeviceType) {
        AudioManager audioManager = (AudioManager) context.getSystemService(AUDIO_SERVICE);
        List<AudioDeviceInfo> devices = audioManager.getAvailableCommunicationDevices();
        for (AudioDeviceInfo device : devices) {
            if (device.getType() == targetDeviceType) {
                boolean result = audioManager.setCommunicationDevice(device);
                Log.d("result: ", "reuslt: " + result);
            }
        }
    }

    private void luckyboxAnimation(int i) {
        if (i < 5) {
            binding.luckGiftImg.setVisibility(View.VISIBLE);
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink_animation);
            binding.relativeLayout.startAnimation(animation);
            jjj = i;
            Handler handler = new Handler();
            handler.postDelayed(
                    new Runnable() {
                        public void run() {
                            animationRecursion(jjj++);
                        }
                    }, 5000);

        } else {
            binding.luckGiftImg.setVisibility(View.GONE);
        }
    }
    TextView musicSongName;
    TextView startMusicTimeTv;
    TextView endMusicTimeTv;
    ImageView playMusicDialogImg;
    SeekBar seekbarDialog12;
    private void playMusicDialogBox() {
        outSideBoxMusicCheck = 1;
        musicOnOffStatus = 1;
        Dialog dialog_share = new Dialog(CallActivity.this);
        dialog_share.setContentView(R.layout.music_play_dialog);
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_share.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        ImageView switcch = dialog_share.findViewById(R.id.switcch);
        ImageView musicplaylist = dialog_share.findViewById(R.id.musicplaylist);
        ImageView nextMusic = dialog_share.findViewById(R.id.nextMusic);
        ImageView speaker = dialog_share.findViewById(R.id.speaker);
        ImageView minimize = dialog_share.findViewById(R.id.minimize);
        TextView volumetext = dialog_share.findViewById(R.id.volumetext);
        AppCompatSeekBar volumeSeekbar = dialog_share.findViewById(R.id.seekbarDialogvolume);
        LinearLayout volumelayout = dialog_share.findViewById(R.id.volumelayout);
        dialog_share.setCanceledOnTouchOutside(true);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog_share.getWindow().getAttributes());
        Window window = dialog_share.getWindow();
        window.setGravity(Gravity.BOTTOM);
        dialog_share.show();
        musicSongName = dialog_share.findViewById(R.id.musicSongName);
        startMusicTimeTv = dialog_share.findViewById(R.id.startMusicTimeTv);
        endMusicTimeTv = dialog_share.findViewById(R.id.endMusicTimeTv);
        playMusicDialogImg = dialog_share.findViewById(R.id.playMusicDialogImg);
        seekbarDialog12 = dialog_share.findViewById(R.id.seekbarDialog);
        musicSongName.setText(musicDetails.getTitle());
        endMusicTimeTv.setText(formatDuration(musicDetails.getDuration()));
        musicList = appDatabase.userDao().getAllSongs();

        nextMusic.setOnClickListener(v -> {
            musicOnOffStatus = 1;
            try {
                if (musicList.size() > possss) {
                    possss++;
                    binding.musicPlayCirImg.setVisibility(View.VISIBLE);
                    binding.musicPlayCirImg.playAnimation();
                    audioEffectManager.playEffect(musicList.get(possss).getId(), musicList.get(possss).getPath(), -1, 0.0, 100, 0, true);
                    // Pauses all audio effects.
                    audioEffectManager.pauseAllEffects();
                    rtcEngine().startAudioMixing(musicList.get(possss).getPath(), false, false, -1, 0);
                    musicSongName.setText(musicList.get(possss).getTitle());
                } else {
                    possss = 0;
                }
            } catch (Exception e) {
//                Toast.makeText(CallActivity.this, "" + e, Toast.LENGTH_SHORT).show();
            }
        });

        musictime = startMusicTimeTv.getText().toString();

        Log.d("musictime","music time = "+musictime);

        if (musictime.equalsIgnoreCase("00:00")) {
            possss++;
            binding.musicPlayCirImg.setVisibility(View.VISIBLE);
            binding.musicPlayCirImg.playAnimation();
            audioEffectManager.playEffect(musicList.get(possss).getId(), musicList.get(possss).getPath(), -1, 0.0, 100, 0, true);
            // Pauses all audio effects.
            audioEffectManager.pauseAllEffects();
            rtcEngine().startAudioMixing(musicList.get(possss).getPath(), false, false, -1, 0);
            musicSongName.setText(musicList.get(possss).getTitle());
        } else {
            possss = 0;
        }

        volumeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                volumetext.setText(progress + "" + "/" + "" + "100");
                int vol = progress;
                rtcEngine().adjustAudioMixingVolume(vol);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        musicplaylist.setOnClickListener(v -> addMusicDialogBox());
        playMusicDialogImg.setOnClickListener(view -> {
            if (musicStatus == 0) {
                musicOnOffStatus = 0;
                playMusicFragmentImg.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
                playMusicDialogImg.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
                pause_music();
                binding.musicPlayCirImg.setVisibility(View.VISIBLE);
                binding.musicPlayCirImg.cancelAnimation();
                musicCountDownTimer.cancel();
                resumeCheck = 1;
                musicStatus = 1;
            } else {
                musicOnOffStatus = 1;
                playMusicFragmentImg.setImageResource(R.drawable.ic_baseline_pause_circle_24);
                playMusicDialogImg.setImageResource(R.drawable.ic_baseline_pause_circle_24);
                resume_music();
                binding.musicPlayCirImg.setVisibility(View.VISIBLE);
                binding.musicPlayCirImg.playAnimation();
                musicStatus = 0;
                resumeCheck = 2;
            }
        });
        switcch.setOnClickListener(v -> {
            musicOnOffStatus = 0;
            pause_music();
            binding.musicPlayCirImg.setVisibility(View.GONE);
            binding.musicPlayCirImg.cancelAnimation();
            dialog_share.dismiss();
        });
        minimize.setOnClickListener(v -> {
            binding.musicPlayCirImg.setVisibility(View.VISIBLE);
            binding.musicPlayCirImg.playAnimation();
            dialog_share.dismiss();
        });
        seekbarDialog12.setFocusableInTouchMode(false);
    }

    private void animationRecursion(int i) {
        if (i < 5) {
            callViewModel.getSuperLuckyBagDetails(CallActivity.this, AppConstants.USER_ID).observe(CallActivity.this, new Observer<SuperLuckyBagDetails>() {
                @Override
                public void onChanged(SuperLuckyBagDetails superLuckyBagDetails) {
                    if (superLuckyBagDetails != null) {
                        if (superLuckyBagDetails.getSuccess().equalsIgnoreCase("1")) {
                            Log.d("lucky Id","lucky :"+superLuckyBagDetails.getDetails().getSuperLuckyBagDetailsId());
                          // Toast.makeText(CallActivity.this, "lucky :_"+superLuckyBagDetails.getDetails().getSuperLuckyBagDetailsId(), Toast.LENGTH_SHORT).show();
                            //sumit
                           // animationRecursion2(1, superLuckyBagDetails.getDetails().getSuperLuckyBagDetailsId());
                            HashMap<String, String> superLuckBagHs = new HashMap<>();
                            superLuckBagHs.put("luckybagId", superLuckyBagDetails.getDetails().getSuperLuckyBagDetailsId());
                            superLuckBagHs.put("channelName", superLuckyBagDetails.getDetails().getChannelName());
                            superLuckBagHs.put("userId", superLuckyBagDetails.getDetails().getUserId());
                            superLuckBagHs.put("liveHostIds", superLuckyBagDetails.getDetails().getUserId());
                            superLuckBagHs.put("liveHostId", superLuckyBagDetails.getDetails().getUserId());
                            superLuckBagHs.put("liveType", "multiLive");
                            superLuckBagHs.put("liveStatus", "host");
                            superLuckBagHs.put("token", superLuckyBagDetails.getDetails().getToken());
                            superLuckBagHs.put("status", "1");
                            superLuckBagHs.put("liveId", superLuckyBagDetails.getDetails().getLiveId());

                            //changes
                            superLuckBagHs.put("name", superLuckyBagDetails.getDetails().getHostName());
                            if (superLuckyBagDetails.getDetails().getImageTitle() != null && !superLuckyBagDetails.getDetails().getImageTitle().isEmpty()) {
                                superLuckBagHs.put("broadTitle", superLuckyBagDetails.getDetails().getImageTitle());
                            } else {
                                superLuckBagHs.put("broadTitle", superLuckyBagDetails.getDetails().getHostName());
                            }
//                            if (superLuckyBagDetails.getDetails().getLiveimage().isEmpty()) {
//                                superLuckBagHs.put("image", superLuckyBagDetails.getDetails().getHostImageDp());
//                            } else {
//                                superLuckBagHs.put("image", superLuckyBagDetails.getDetails().getLiveimage());
//                            }
////                            if (!superLuckyBagDetails.getDetails().getHostDob().equalsIgnoreCase("") && !superLuckyBagDetails.getDetails().getHostGender().equalsIgnoreCase("")){
//                                superLuckBagHs.put("dob", CommonUtils.ageCalcualte(superLuckyBagDetails.getDetails().getHostDob()));
//                                superLuckBagHs.put("gender", CommonUtils.ageCalcualte(superLuckyBagDetails.getDetails().getHostGender()));
//                            }else{
//                                superLuckBagHs.put("dob", "");
//                                superLuckBagHs.put("gender","");
//                            }
//                            if (!superLuckyBagDetails.getDetails().getUserDob().equalsIgnoreCase("") && !superLuckyBagDetails.getDetails().getUserGender().equalsIgnoreCase("") ){
//                                superLuckBagHs.put("userDob",  CommonUtils.ageCalcualte(superLuckyBagDetails.getDetails().getUserDob()));
//                                superLuckBagHs.put("UserGender", CommonUtils.ageCalcualte(superLuckyBagDetails.getDetails().getUserGender()));
//                            }else{
//                                superLuckBagHs.put("userDob", "");
//                                superLuckBagHs.put("UserGender", "");
//                            }
                            superLuckybagRef.child("1").setValue(superLuckBagHs);
                        } else {
                            binding.superLuckyBagTvAnim.setVisibility(View.GONE);
                            binding.superLuckyBagRL.setVisibility(View.GONE);
                            superLuckybagRef.child("1").removeValue();
                        }
                    }else {
//                        Toast.makeText(CallActivity.this, "super Lucky bag null", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            j = i;
            Handler handler = new Handler();
            handler.postDelayed(
                    new Runnable() {
                        public void run() {
                            animationRecursion(j++);
                        }
                    }, 60000);
        } else {
            animationRecursion(i);
        }
    }

    private void superLuckyBagHitApi() {
        binding.superLuckyBagTvAnim.setOnClickListener(view ->
                superLuckybagRef.child("1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String luckybagId = snapshot.child("luckybagId").getValue().toString();
                    callViewModel.hitSuperLuckyBag(CallActivity.this, AppConstants.USER_ID, luckybagId).observe(CallActivity.this, new Observer<HitSuperLuckyBagRoot>() {
                        @Override
                        public void onChanged(HitSuperLuckyBagRoot hitSuperLuckyBagRoot) {
                            if (hitSuperLuckyBagRoot != null) {
                                if (hitSuperLuckyBagRoot.getSuccess().equalsIgnoreCase("1")) {
                                    deleterLiveStreamViewers();
                                    optionalDestroy();
                                    doLeaveChannel();
                                    mUidsList.clear();
                                    finish();
                                    Intent intent = new Intent(CallActivity.this, CallActivity.class);
                                    intent.putExtra("channelName", snapshot.child("channelName").getValue().toString());
                                    intent.putExtra("userId", snapshot.child("userId").getValue().toString());
                                    intent.putExtra("liveHostIds", snapshot.child("userId").getValue().toString());
                                    intent.putExtra("liveType", "multiLive");
                                    intent.putExtra("liveStatus", "host");
                                    intent.putExtra("token", snapshot.child("token").getValue().toString());
                                    intent.putExtra("liveHostId", snapshot.child("userId").getValue().toString());
                                    intent.putExtra("status", "1");
                                    intent.putExtra("liveId", snapshot.child("liveId").getValue().toString());
                                    intent.putExtra("image", snapshot.child("image").getValue().toString());
                                    intent.putExtra("broadTitle", snapshot.child("broadTitle").getValue().toString());
                                    intent.putExtra("image", snapshot.child("liveId").getValue().toString());
                                    startActivity(intent);
                                } else {
                                }
                            } else {
                            }
                        }
                    });
                } else {
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        }));
    }

    int k = 0;
    private void animationRecursion2(int i, String luckyBagId) {
        if (i < 6) {
//            Toast.makeText(this, "5 second" + luckyBagId, Toast.LENGTH_SHORT).show();
            binding.superLuckyBagRL.setVisibility(View.VISIBLE);
            binding.superLuckyBagTvAnim.setVisibility(View.VISIBLE);
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink_animation);
            binding.superLuckyBagTvAnim.startAnimation(animation);
            Handler handler = new Handler();
            i = k;
            Log.d("CALLACTIVITIY", "post " + i + " i");
            handler.postDelayed(
                    new Runnable() {
                        public void run() {
                            Log.d("CALLACTIVITIY", "post " + k + " k");
                            animationRecursion2(k++, luckyBagId);
                        }
                    }, 5000);
        } else {
            binding.superLuckyBagRL.setVisibility(View.GONE);
            binding.superLuckyBagTvAnim.setVisibility(View.GONE);
//            Toast.makeText(this, "finish superlucky time", Toast.LENGTH_SHORT).show();
        }
    }

    private void getCurrentDateAndTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat timeFormate = new SimpleDateFormat("hh:mm:ss a");
        String date = dateFormat.format(c.getTime());
        String time = timeFormate.format(c.getTime());
        binding.callActvityDateTv.setText(date);
        Thread myThread = null;
        Runnable runnable = new CountDownRunner();
        myThread = new Thread(runnable);
        myThread.start();
    }

    public void doWork() {
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat timeFormate = new SimpleDateFormat("hh:mm:ss");
                    String curTime = timeFormate.format(c.getTime());
                    binding.callActvityTimeTv.setText(curTime);
                } catch (Exception e) {
                }
                cnt++;
                String time = new Integer(cnt).toString();
                long millis = cnt;
                int seconds = (int) (millis / 60);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                binding.liveRecodingTimeTv.setText(String.format("%d:%02d:%02d", minutes, seconds, millis));
            }
        });
    }

    class CountDownRunner implements Runnable {
        // @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    doWork();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (Exception e) {
                }
            }
        }
    }

    private void emojiBottomSheet() {
        Dialog viewDetails_box = new Dialog(this);
        viewDetails_box.setContentView(R.layout.call_emoji_bottom_sheet_dialog_box);
        viewDetails_box.getWindow().setBackgroundDrawable(new ColorDrawable());
        Window window = viewDetails_box.getWindow();
        viewDetails_box.setCanceledOnTouchOutside(true);
        window.setGravity(Gravity.BOTTOM);
        viewDetails_box.show();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RecyclerView emojiRV = viewDetails_box.findViewById(R.id.emojiRV);
        callViewModel.getEmoji(CallActivity.this).observe(CallActivity.this, new Observer<GetEmojiRoot>() {
            @Override
            public void onChanged(GetEmojiRoot getEmojiRoot) {
                if (getEmojiRoot != null) {
                    if (getEmojiRoot.getSuccess().equalsIgnoreCase("1")) {
                        EmojiRVAdpter emojiRVAdpter = new EmojiRVAdpter(getEmojiRoot.getDetails(), CallActivity.this, new EmojiRVAdpter.Callback() {
                            @Override
                            public void callback(GetEmojiRoot.Detail detail) {
                                HashMap<String, String> sendEmojiHs = new HashMap<>();
                                sendEmojiHs.put("emojiImg", detail.getFrame_img());
                                sendEmojiHs.put("emojiSenderId", AppConstants.USER_ID);
                                sendEmojiHs.put("emojiSenderName", App.getSharedpref().getString("name"));
                                sendEmojiHs.put("emojiSenderImg", App.getSharedpref().getString("image"));
                                sendEmojiHs.put("status", "0");
                                sendEmojiHs.put("giftStatus","2");
                                emojiRef.child(liveHostBackImg).setValue(sendEmojiHs).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        viewDetails_box.dismiss();
                                    }
                                });
                            }
                        });
                        emojiRV.setAdapter(emojiRVAdpter);
                    } else {
                    }
                }
            }
        });
    }

    private void navBottomMenuDialogBox() {
        Dialog viewDetails_box = new Dialog(this);
        viewDetails_box.setContentView(R.layout.call_menu_dialog_box);
        viewDetails_box.getWindow().setBackgroundDrawable(new ColorDrawable());
        Window window = viewDetails_box.getWindow();
        viewDetails_box.setCanceledOnTouchOutside(true);
        window.setGravity(Gravity.BOTTOM);
        viewDetails_box.show();
        ImageView callMenuVisualEffects = viewDetails_box.findViewById(R.id.callMenuVisualEffects);
        ImageView callLuckBagsImg = viewDetails_box.findViewById(R.id.callLuckBagsImg);
        ImageView shareLiveCallImg = viewDetails_box.findViewById(R.id.shareLiveCallImg);
        callMenuVisualEffects.setOnClickListener(view -> {
            callVisualEffectBottomSheet();
            viewDetails_box.dismiss();
        });
        callLuckBagsImg.setOnClickListener(view -> {
            callLuckBagDialogBox();
            viewDetails_box.dismiss();
        });
        shareLiveCallImg.setOnClickListener(view -> {
            viewDetails_box.dismiss();
            Dialog viewDetails_box1 = new Dialog(CallActivity.this);
            viewDetails_box1.setContentView(R.layout.share_live_options);
            viewDetails_box1.getWindow().setBackgroundDrawable(new ColorDrawable());
            Window window1 = viewDetails_box1.getWindow();
            viewDetails_box1.setCanceledOnTouchOutside(true);
            window1.setGravity(Gravity.BOTTOM);
            viewDetails_box1.show();
            window1.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            ConstraintLayout constarintMoreLyout = viewDetails_box1.findViewById(R.id.constarintMoreLyout);
            ConstraintLayout constarintFriendLyout = viewDetails_box1.findViewById(R.id.constarintFriendLyout);
            String dynmicKey = ref.push().getKey().toString();
            DynamicLink dynamicLink = FirebaseDynamicLinks.getInstance().createDynamicLink()
                    // .setLink(dynamicLinkUri)
                    .setLink(Uri.parse("http://www.google.com/?userId=" + liveHostBackImg + "?key=" + dynmicKey))
                    .setDomainUriPrefix("https://worldsocialintegrationapp.page.link")
                    // Open links with this app on Android
                    .setAndroidParameters(new DynamicLink.AndroidParameters.Builder().build())
                    // Open links with com.example.ios on iOS
                    .setIosParameters(new DynamicLink.IosParameters.Builder("com.appinventiv.ios").build())
                    .buildDynamicLink();
            String link = dynamicLink.getUri().toString();
            dynamicLinkKeys.child(liveHostBackImg).setValue(dynmicKey);
            constarintMoreLyout.setOnClickListener(view12 -> {
                viewDetails_box1.dismiss();
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                    String shareMessage = "\nLet me recommend you this application\n\n";
                    shareMessage = shareMessage + link;
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch (Exception e) {

                }
            });
            constarintFriendLyout.setOnClickListener(view1 -> {
                viewDetails_box1.dismiss();
                App.getSharedpref().saveString("liveShareFriends", link);
                App.getSharedpref().saveString("liveShareLink", link);
                App.getSharedpref().saveString("dynmicKey", dynmicKey);
                setPIPScreen();
            });
        });
    }

    private void callLuckBagDialogBox() {
        Dialog viewDetails_box = new Dialog(this);
        viewDetails_box.setContentView(R.layout.lucky_bag_dialog_box);
        viewDetails_box.getWindow().setBackgroundDrawable(new ColorDrawable());
        Window window = viewDetails_box.getWindow();
        viewDetails_box.setCanceledOnTouchOutside(true);
        window.setGravity(Gravity.CENTER);
        viewDetails_box.show();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        AppCompatButton luckBagSubmitBtn = viewDetails_box.findViewById(R.id.luckBagSubmitBtn);
        ImageView luckyBagCancelImg = viewDetails_box.findViewById(R.id.luckyBagCancelImg);
        LinearLayout luckybagQuantityLlayout = viewDetails_box.findViewById(R.id.luckybagQuantityLlayout);
        TextView luckbagQuantityTv = viewDetails_box.findViewById(R.id.luckbagQuantityTv);
        TextView luckBagLearnMoreTv = viewDetails_box.findViewById(R.id.luckBagLearnMoreTv);

        RelativeLayout luckybagRL = viewDetails_box.findViewById(R.id.luckybagRL);
        RelativeLayout superLuckbagRL = viewDetails_box.findViewById(R.id.superLuckbagRL);

        TextView luckyBagTv = viewDetails_box.findViewById(R.id.luckyBagTv);
        TextView superLuckybagTv = viewDetails_box.findViewById(R.id.superLuckybagTv);

        luckBagLearnMoreTv.setOnClickListener(view -> {
            viewDetails_box.dismiss();
            LuckyBagRulesFragment luckyBagRulesFragment = new LuckyBagRulesFragment();
            luckyBagRulesFragment.show(getSupportFragmentManager(), luckyBagRulesFragment.getTag());
        });

        luckyBagDetailApi("1", viewDetails_box, luckBagSubmitBtn, luckyBagCancelImg);

        luckybagRL.setOnClickListener(view -> {
            luckybagRL.setBackground(ContextCompat.getDrawable(CallActivity.this, R.drawable.rect_conrs_lucky_bag_white));
            superLuckbagRL.setBackground(ContextCompat.getDrawable(CallActivity.this, R.drawable.lucky_bag_empty_drawable));

            luckyBagTv.setTextColor(Color.RED);
            superLuckybagTv.setTextColor(Color.WHITE);

            luckBagType = "1";
            luckyBagDetailApi("1", viewDetails_box, luckBagSubmitBtn, luckyBagCancelImg);
//                setCoinsAndQuantity(viewDetails_box, luckBagSubmitBtn, luckyBagCancelImg, luckBagType);
        });
        superLuckbagRL.setOnClickListener(view -> {
            superLuckbagRL.setBackground(ContextCompat.getDrawable(CallActivity.this, R.drawable.rect_conrs_lucky_bag_white));
            luckybagRL.setBackground(ContextCompat.getDrawable(CallActivity.this, R.drawable.lucky_bag_empty_drawable));
            luckyBagTv.setTextColor(Color.WHITE);
            superLuckybagTv.setTextColor(Color.RED);
            luckyBagDetailApi("2", viewDetails_box, luckBagSubmitBtn, luckyBagCancelImg);
//                luckBagType = "2";
//                setCoinsAndQuantity(viewDetails_box, luckBagSubmitBtn, luckyBagCancelImg, luckBagType);
        });
    }

    private void luckyBagDetailApi(String luckBagType, Dialog dialog, AppCompatButton submit, ImageView luckyBagCancelImg) {
        callViewModel.getLuckyBagDetail(CallActivity.this, luckBagType).observe(CallActivity.this, new Observer<LuckBagRoot>() {
            @Override
            public void onChanged(LuckBagRoot luckBagRoot) {
                if (luckBagRoot != null) {
                    if (luckBagRoot.getSuccess().equalsIgnoreCase("1")) {
//                        Toast.makeText(CallActivity.this, "" + luckBagRoot.getMessage(), Toast.LENGTH_SHORT).show();
                        luckBagRootDetail = luckBagRoot;
                        setCoinsAndQuantity(dialog, submit, luckyBagCancelImg, luckBagType);
                        Log.d("lucky","lucky :"+dialog);
                        Log.d("lucky","lucky :"+submit);
                        Log.d("lucky","lucky :"+luckyBagCancelImg);
                        Log.d("lucky","lucky :"+luckBagType);
                    } else {
//                        Toast.makeText(CallActivity.this, "" + luckBagRoot.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void setCoinsAndQuantity(Dialog dialog, AppCompatButton submit, ImageView luckyBagCancelImg, String luckyBagtype) {
        //super lucky bag
        LinearLayout luckBagMainLlayout = dialog.findViewById(R.id.luckBagMainLlayout);
        LinearLayout superLuckBagMainLlayout = dialog.findViewById(R.id.superLuckBagMainLlayout);

        //for super lucky bag
        RelativeLayout superLuckybagfirstCoinsRL = dialog.findViewById(R.id.superLuckybagfirstCoinsRL);
        RelativeLayout superLuckybagSecCoinsRL = dialog.findViewById(R.id.superLuckybagSecCoinsRL);

        TextView superLuckybagfirstCoinsTv = dialog.findViewById(R.id.superLuckybagfirstCoinsTv);
        TextView superLuckybagSecCoinsTv = dialog.findViewById(R.id.superLuckybagSecCoinsTv);

        if (luckyBagtype.equalsIgnoreCase("1")) {
            //lucky bag
            luckBagMainLlayout.setVisibility(View.VISIBLE);
            superLuckBagMainLlayout.setVisibility(View.GONE);

        } else {
            //for superlucky bag
            luckBagMainLlayout.setVisibility(View.GONE);
            superLuckBagMainLlayout.setVisibility(View.VISIBLE);
            superLuckybagfirstCoinsTv.setText(luckBagRootDetail.getDetails().getCoins().get(2).getCoins());
            superLuckybagSecCoinsTv.setText(luckBagRootDetail.getDetails().getCoins().get(3).getCoins());
        }

        //for lucky bag
        RelativeLayout coinsRL290 = dialog.findViewById(R.id.coinsRL290);
        RelativeLayout coinsRL990 = dialog.findViewById(R.id.coinsRL990);
        RelativeLayout coinsRL2990 = dialog.findViewById(R.id.coinsRL2990);
        RelativeLayout coinsRL19990 = dialog.findViewById(R.id.coinsRL19990);

        TextView coinsTv290 = dialog.findViewById(R.id.coinsTv290);
        TextView coinsTv990 = dialog.findViewById(R.id.coinsTv990);
        TextView coinsTv2990 = dialog.findViewById(R.id.coinsTv2990);
        TextView coinsTv19990 = dialog.findViewById(R.id.coinsTv19990);

        RelativeLayout QuantityRL6 = dialog.findViewById(R.id.QuantityRL6);
        RelativeLayout QuantityRL10 = dialog.findViewById(R.id.QuantityRL10);
        RelativeLayout QuantityRL15 = dialog.findViewById(R.id.QuantityRL15);
        RelativeLayout QuantityRL20 = dialog.findViewById(R.id.QuantityRL20);

        TextView QuantityTv6 = dialog.findViewById(R.id.QuantityTv6);
        TextView QuantityTv10 = dialog.findViewById(R.id.QuantityTv10);
        TextView QuantityTv15 = dialog.findViewById(R.id.QuantityTv15);
        TextView QuantityTv20 = dialog.findViewById(R.id.QuantityTv20);

        AppCompatButton luckBagSubmitBtn = dialog.findViewById(R.id.luckBagSubmitBtn);

        QuantityTv6.setText(luckBagRootDetail.getDetails().getQuantity().get(0).getCoinQuantity());
        QuantityTv10.setText(luckBagRootDetail.getDetails().getQuantity().get(1).getCoinQuantity());
        QuantityTv15.setText(luckBagRootDetail.getDetails().getQuantity().get(2).getCoinQuantity());


        coinsTv290.setText(luckBagRootDetail.getDetails().getCoins().get(0).getCoins());
        coinsTv990.setText(luckBagRootDetail.getDetails().getCoins().get(1).getCoins());
        coinsTv2990.setText(luckBagRootDetail.getDetails().getCoins().get(2).getCoins());

        if (luckyBagtype.equalsIgnoreCase("1")) {
            //lucky bag
//            coinsTv19990.setText(luckBagRootDetail.getDetails().getCoins().get(3).getCoins());
//            QuantityTv20.setText(luckBagRootDetail.getDetails().getQuantity().get(3).getCoinQuantity());
            coinsTv19990.setText("3999");
            QuantityTv20.setText("25");
        }

        coinsRL290.setOnClickListener(view -> {
            LuckBagCoinId = luckBagRootDetail.getDetails().getCoins().get(0).getCoins();
            coinsRL290.setBackgroundColor(Color.WHITE);
            coinsRL990.setBackgroundColor(Color.TRANSPARENT);
            coinsRL2990.setBackgroundColor(Color.TRANSPARENT);
            coinsRL19990.setBackgroundColor(Color.TRANSPARENT);
            coinsTv290.setTextColor(Color.RED);
            coinsTv990.setTextColor(Color.WHITE);
            coinsTv2990.setTextColor(Color.WHITE);
            coinsTv19990.setTextColor(Color.WHITE);
        });

        coinsRL990.setOnClickListener(view -> {
            LuckBagCoinId = luckBagRootDetail.getDetails().getCoins().get(1).getCoins();
            coinsRL290.setBackgroundColor(Color.TRANSPARENT);
            coinsRL990.setBackgroundColor(Color.WHITE);
            coinsRL2990.setBackgroundColor(Color.TRANSPARENT);
            coinsRL19990.setBackgroundColor(Color.TRANSPARENT);
            coinsTv290.setTextColor(Color.WHITE);
            coinsTv990.setTextColor(Color.RED);
            coinsTv2990.setTextColor(Color.WHITE);
            coinsTv19990.setTextColor(Color.WHITE);
        });

        coinsRL2990.setOnClickListener(view -> {
            LuckBagCoinId = luckBagRootDetail.getDetails().getCoins().get(2).getCoins();
            coinsRL290.setBackgroundColor(Color.TRANSPARENT);
            coinsRL990.setBackgroundColor(Color.TRANSPARENT);
            coinsRL2990.setBackgroundColor(Color.WHITE);
            coinsRL19990.setBackgroundColor(Color.TRANSPARENT);
            coinsTv290.setTextColor(Color.WHITE);
            coinsTv990.setTextColor(Color.WHITE);
            coinsTv2990.setTextColor(Color.RED);
            coinsTv19990.setTextColor(Color.WHITE);
        });

        coinsRL19990.setOnClickListener(view -> {
            LuckBagCoinId = luckBagRootDetail.getDetails().getCoins().get(3).getCoins();
            coinsRL290.setBackgroundColor(Color.TRANSPARENT);
            coinsRL990.setBackgroundColor(Color.TRANSPARENT);
            coinsRL2990.setBackgroundColor(Color.TRANSPARENT);
            coinsRL19990.setBackgroundColor(Color.WHITE);
            coinsTv290.setTextColor(Color.WHITE);
            coinsTv990.setTextColor(Color.WHITE);
            coinsTv2990.setTextColor(Color.WHITE);
            coinsTv19990.setTextColor(Color.RED);
        });

        QuantityRL6.setOnClickListener(view -> {
            LuckBagQuantityId = luckBagRootDetail.getDetails().getQuantity().get(0).getCoinQuantity();
            QuantityRL6.setBackgroundColor(Color.WHITE);
            QuantityRL10.setBackgroundColor(Color.TRANSPARENT);
            QuantityRL15.setBackgroundColor(Color.TRANSPARENT);
            QuantityRL20.setBackgroundColor(Color.TRANSPARENT);
            QuantityTv6.setTextColor(Color.RED);
            QuantityTv10.setTextColor(Color.WHITE);
            QuantityTv15.setTextColor(Color.WHITE);
            QuantityTv20.setTextColor(Color.WHITE);
        });

        QuantityRL10.setOnClickListener(view -> {
            LuckBagQuantityId = luckBagRootDetail.getDetails().getQuantity().get(1).getCoinQuantity();
            QuantityRL6.setBackgroundColor(Color.TRANSPARENT);
            QuantityRL10.setBackgroundColor(Color.WHITE);
            QuantityRL15.setBackgroundColor(Color.TRANSPARENT);
            QuantityRL20.setBackgroundColor(Color.TRANSPARENT);
            QuantityTv6.setTextColor(Color.WHITE);
            QuantityTv10.setTextColor(Color.RED);
            QuantityTv15.setTextColor(Color.WHITE);
            QuantityTv20.setTextColor(Color.WHITE);
        });

        QuantityRL15.setOnClickListener(view -> {
            LuckBagQuantityId = luckBagRootDetail.getDetails().getQuantity().get(2).getCoinQuantity();
            QuantityRL6.setBackgroundColor(Color.TRANSPARENT);
            QuantityRL10.setBackgroundColor(Color.TRANSPARENT);
            QuantityRL15.setBackgroundColor(Color.WHITE);
            QuantityRL20.setBackgroundColor(Color.TRANSPARENT);
            QuantityTv6.setTextColor(Color.WHITE);
            QuantityTv10.setTextColor(Color.WHITE);
            QuantityTv15.setTextColor(Color.RED);
            QuantityTv20.setTextColor(Color.WHITE);
        });

        QuantityRL20.setOnClickListener(view -> {
            LuckBagQuantityId = luckBagRootDetail.getDetails().getQuantity().get(3).getCoinQuantity();
            QuantityRL6.setBackgroundColor(Color.TRANSPARENT);
            QuantityRL10.setBackgroundColor(Color.TRANSPARENT);
            QuantityRL15.setBackgroundColor(Color.TRANSPARENT);
            QuantityRL20.setBackgroundColor(Color.WHITE);
            QuantityTv6.setTextColor(Color.WHITE);
            QuantityTv10.setTextColor(Color.WHITE);
            QuantityTv15.setTextColor(Color.WHITE);
        });

        superLuckybagfirstCoinsRL.setOnClickListener(view -> {
            LuckBagCoinId = luckBagRootDetail.getDetails().getCoins().get(2).getCoins();
            superLuckybagfirstCoinsRL.setBackgroundColor(Color.WHITE);
            superLuckybagSecCoinsRL.setBackgroundColor(Color.TRANSPARENT);
            superLuckybagfirstCoinsTv.setTextColor(Color.RED);
            superLuckybagSecCoinsTv.setTextColor(Color.WHITE);
        });

        superLuckybagSecCoinsRL.setOnClickListener(view -> {
            LuckBagCoinId = luckBagRootDetail.getDetails().getCoins().get(3).getCoins();
            superLuckybagfirstCoinsRL.setBackgroundColor(Color.TRANSPARENT);
            superLuckybagSecCoinsRL.setBackgroundColor(Color.WHITE);
            superLuckybagfirstCoinsTv.setTextColor(Color.WHITE);
            superLuckybagSecCoinsTv.setTextColor(Color.RED);
        });

        luckBagSubmitBtn.setOnClickListener(view -> {
            if (!luckyBagtype.isEmpty() && luckyBagtype.equalsIgnoreCase("1")) {
                if (!LuckBagCoinId.isEmpty() && !LuckBagQuantityId.isEmpty() && !luckyBagtype.isEmpty()) {
                    callViewModel.deductLuckbagCoins(CallActivity.this, AppConstants.USER_ID, LuckBagCoinId, LuckBagQuantityId, luckyBagtype).observe(CallActivity.this, new Observer<DeductCoinsRoot>() {
                        @Override
                        public void onChanged(DeductCoinsRoot deductCoinsRoot) {
                            if (deductCoinsRoot != null) {
                                if (deductCoinsRoot.getStatus() == 1) {
                                    dialog.dismiss();
//                                    Toast.makeText(CallActivity.this, " " + deductCoinsRoot.getMessage(), Toast.LENGTH_SHORT).show();
                                    luckBagCoinDeductedUserId = deductCoinsRoot.getDetails().getUserId();
                                    luckBagDeductedId = deductCoinsRoot.getDetails().getId();
                                    binding.luckGiftImg.setVisibility(View.VISIBLE);
                                    if ("luckyBagCoins".equalsIgnoreCase(deductCoinsRoot.getDetails().getDeductType())) {
                                        HashMap<String, String> luckyBadHitHs = new HashMap<>();
                                        luckyBadHitHs.put("userId", deductCoinsRoot.getDetails().getUserId());
                                        luckyBadHitHs.put("quantity", deductCoinsRoot.getDetails().getQuantity());
                                        luckyBadHitHs.put("detectCoinsId", deductCoinsRoot.getDetails().getId());
                                        luckyBadHitHs.put("dividePerShare", deductCoinsRoot.getDetails().getPerShare());
                                        luckyBadHitHs.put("luckyBagtype", deductCoinsRoot.getDetails().getDeductType());
                                        luckyBadHitHs.put("name", deductCoinsRoot.getDetails().getName());
                                        luckyBadHitHs.put("image", deductCoinsRoot.getDetails().getImageDp());
                                        luckyBadHitHs.put("status", "1");
                                        LuckBagRef.child(liveHostBackImg).setValue(luckyBadHitHs);
                                        ChatMessageModel chatMessageModel = new ChatMessageModel();
                                        chatMessageModel.setGift("");
                                        chatMessageModel.setImage(deductCoinsRoot.getDetails().getImageDp());
                                        chatMessageModel.setKey(ref.push().getKey());
                                        chatMessageModel.setMessage("Created Lucky Bag");
                                        chatMessageModel.setName(deductCoinsRoot.getDetails().getName());
                                        chatMessageModel.setUserId(deductCoinsRoot.getDetails().getUserId());
                                        chatMessageModel.setAnnouncement("0");
                                        chatMessageModel.setTimeStamp(getCurrentTimeStamp());
                                        sendMessage(chatMessageModel, chatMessageModel.getKey());
                                    } else {
                                    }
                                } else {
//                                    Toast.makeText(CallActivity.this, "" + deductCoinsRoot.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(CallActivity.this, "Technical issue", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            } else {
                //super lucky bag
                if (LuckBagCoinId.equalsIgnoreCase("7777") || LuckBagCoinId.equalsIgnoreCase("19999")) {
                    callViewModel.createSuperLuckyBag(CallActivity.this, AppConstants.USER_ID, LuckBagCoinId, liveId).observe(CallActivity.this, new Observer<CreateSuperLuckyBagRoot>() {
                        @Override
                        public void onChanged(CreateSuperLuckyBagRoot createSuperLuckyBagRoot) {
//                            Toast.makeText(CallActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();
//                            if (createSuperLuckyBagRoot != null) {
//                                if (createSuperLuckyBagRoot.getSuccess().equalsIgnoreCase("1")) {
//                                    Toast.makeText(CallActivity.this, "luckyBagtype="+luckyBagtype, Toast.LENGTH_SHORT).show();
//                                    Toast.makeText(CallActivity.this, "luckyBagtype="+createSuperLuckyBagRoot.getMessage(), Toast.LENGTH_SHORT).show();
//                                    dialog.dismiss();
//                                } else {
//                                    Toast.makeText(CallActivity.this, "" + createSuperLuckyBagRoot.getMessage(), Toast.LENGTH_SHORT).show();
//                                }
//                            } else {
//                                Toast.makeText(CallActivity.this, "Technical issue", Toast.LENGTH_SHORT).show();
//                            }
                        }
                    });
                }
            }
        });
        luckyBagCancelImg.setOnClickListener(view -> dialog.dismiss());
    }


    private void callVisualEffectBottomSheet() {
        Dialog viewDetails_box = new Dialog(this);
        viewDetails_box.setContentView(R.layout.call_visual_effects_dialog_box);
        viewDetails_box.getWindow().setBackgroundDrawable(new ColorDrawable());
        Window window = viewDetails_box.getWindow();
        viewDetails_box.setCanceledOnTouchOutside(true);
        window.setGravity(Gravity.BOTTOM);
        viewDetails_box.show();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Switch giftEffectSwitch = viewDetails_box.findViewById(R.id.giftEffectSwitch);
        Switch vehicleEffectSwitch = viewDetails_box.findViewById(R.id.vehicleEffectSwitch);
        Switch giftSoundEffectSwitch = viewDetails_box.findViewById(R.id.giftSoundEffectSwitch);
        Switch rewardEffectSwitch = viewDetails_box.findViewById(R.id.rewardEffectSwitch);
        visualEffectsRef.child(liveHostBackImg).child(AppConstants.USER_ID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    giftEffect = snapshot.child("giftEffect").getValue().toString();
                    vehicleEffect = snapshot.child("vehicleEffect").getValue().toString();
                    giftSoundEffect = snapshot.child("giftSoundEffect").getValue().toString();
                    rewardEffect = snapshot.child("rewardEffect").getValue().toString();
                    if (giftEffect.equalsIgnoreCase("1")) {
                        giftEffectSwitch.setChecked(false);
                    } else {
                        giftEffectSwitch.setChecked(true);
                    }

                    if (vehicleEffect.equalsIgnoreCase("1")) {
                        vehicleEffectSwitch.setChecked(false);
                    } else {
                        vehicleEffectSwitch.setChecked(true);
                    }
                    if (giftSoundEffect.equalsIgnoreCase("1")) {
                        giftSoundEffectSwitch.setChecked(false);
                    } else {
                        giftSoundEffectSwitch.setChecked(true);
                    }

                    if (rewardEffect.equalsIgnoreCase("1")) {
                        rewardEffectSwitch.setChecked(false);
                    } else {
                        rewardEffectSwitch.setChecked(true);
                    }
                    giftEffectSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            if (b) {
                                visualEffectsRef.child(liveHostBackImg).child(AppConstants.USER_ID).child("giftEffect").setValue("0");
                            } else {
                                visualEffectsRef.child(liveHostBackImg).child(AppConstants.USER_ID).child("giftEffect").setValue("1");
                            }
                        }
                    });
                    vehicleEffectSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            if (b) {
                                visualEffectsRef.child(liveHostBackImg).child(AppConstants.USER_ID).child("vehicleEffect").setValue("0");
                            } else {
                                visualEffectsRef.child(liveHostBackImg).child(AppConstants.USER_ID).child("vehicleEffect").setValue("1");
                            }
                        }
                    });
                    giftSoundEffectSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            if (b) {
                                visualEffectsRef.child(liveHostBackImg).child(AppConstants.USER_ID).child("giftSoundEffect").setValue("0");
                            } else {
                                visualEffectsRef.child(liveHostBackImg).child(AppConstants.USER_ID).child("giftSoundEffect").setValue("1");
                            }
                        }
                    });
                    rewardEffectSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            if (b) {
                                visualEffectsRef.child(liveHostBackImg).child(AppConstants.USER_ID).child("rewardEffect").setValue("0");
                            } else {
                                visualEffectsRef.child(liveHostBackImg).child(AppConstants.USER_ID).child("rewardEffect").setValue("1");
                            }
                        }
                    });
                    HashMap<String, String> visualEffectHs = new HashMap<>();
                    visualEffectHs.put("giftEffect", giftEffect);
                    visualEffectHs.put("vehicleEffect", vehicleEffect);
                    visualEffectHs.put("giftSoundEffect", giftSoundEffect);
                    visualEffectHs.put("rewardEffect", rewardEffect);
                    visualEffectsRef.child(liveHostBackImg).child(AppConstants.USER_ID).setValue(visualEffectHs);
                } else {
                    HashMap<String, String> visualEffectHs = new HashMap<>();
                    visualEffectHs.put("giftEffect", giftEffect);
                    visualEffectHs.put("vehicleEffect", vehicleEffect);
                    visualEffectHs.put("giftSoundEffect", giftSoundEffect);
                    visualEffectHs.put("rewardEffect", rewardEffect);
                    visualEffectsRef.child(liveHostBackImg).child(AppConstants.USER_ID).setValue(visualEffectHs);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    private void menuDialogBox() {
        Dialog dialog_share = new Dialog(CallActivity.this);
        dialog_share.setContentView(R.layout.menu_dialog_box);
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog_share.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_share.setCanceledOnTouchOutside(true);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog_share.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog_share.getWindow().setAttributes(layoutParams);
        Window window = dialog_share.getWindow();
        window.setGravity(Gravity.CENTER_HORIZONTAL);
        dialog_share.show();
        ImageView coverInfoImg = dialog_share.findViewById(R.id.coverInfoImg);
        ImageView bulletinImg = dialog_share.findViewById(R.id.bulletinImg);
        ImageView adminImg = dialog_share.findViewById(R.id.adminImg);
        ImageView lockImg = dialog_share.findViewById(R.id.lockImg);
        ImageView cleanChatImg = dialog_share.findViewById(R.id.cleanChatImg);
        ImageView hiddenImg = dialog_share.findViewById(R.id.hiddenImg);
        ImageView musicImg = dialog_share.findViewById(R.id.musicImg);
        ImageView themeImg = dialog_share.findViewById(R.id.themeImg);
        ImageView scoreBoardImg = dialog_share.findViewById(R.id.scoreBoardImg);
        ImageView roomModeImg = dialog_share.findViewById(R.id.roomModeImg);
        TextView liveLocktv = dialog_share.findViewById(R.id.liveLocktv);

        lockRef.child(liveHostBackImg).addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String status = snapshot.child("status").getValue().toString();
                    if (status.equalsIgnoreCase("0")) {
                        liveLocktv.setText("Lock");
                        binding.roomLockImg.setVisibility(View.GONE);
                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) binding.txtFollowers.getLayoutParams();
                        params.leftMargin = 15;
                        lockImg.setOnClickListener(view -> lock_dialog_box());
                    } else {
                        lockImg.setOnClickListener(view -> apiSetPin("", dialog_share));
                        liveLocktv.setText("Unlock");
                        binding.roomLockImg.setVisibility(View.VISIBLE);
                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) binding.txtFollowers.getLayoutParams();
                        params.leftMargin = 5;
                    }
                } else {
                    lockImg.setOnClickListener(view -> lock_dialog_box());
                    binding.roomLockImg.setVisibility(View.GONE);
                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) binding.txtFollowers.getLayoutParams();
                    params.leftMargin = 15;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        if (adminStatus == 1) {
            dialog_share.findViewById(R.id.menuDialogTopRawLinealyotut).setVisibility(View.GONE);
            dialog_share.findViewById(R.id.menuDialogThemeLineayout).setVisibility(View.GONE);
            dialog_share.findViewById(R.id.menuDialogHiddenLineayout).setVisibility(View.GONE);
            dialog_share.findViewById(R.id.menuDialogScoreBoardLineayout).setVisibility(View.GONE);
            dialog_share.findViewById(R.id.menuDialogRoomModeLineayout).setVisibility(View.GONE);
            LinearLayout menuDialogMusicLineayout = dialog_share.findViewById(R.id.menuDialogMusicLineayout);
            menuDialogMusicLineayout.setVisibility(View.VISIBLE);
        }
        coverInfoImg.setOnClickListener(view -> {
            coverInfoBottomSheet(dialog_share);
            dialog_share.dismiss();
            //cover_info_dialog
        });
        bulletinImg.setOnClickListener(view -> {
            bulletin_dialog_box(dialog_share);
            dialog_share.dismiss();
            //call_bulletin_dialog_box
        });
        adminImg.setOnClickListener(view -> {
            adminDialogBox(dialog_share);
            dialog_share.dismiss();
            //admin_dialog_box
        });
        cleanChatImg.setOnClickListener(view -> {
            clean_chat_dialog_box(dialog_share);
            dialog_share.dismiss();
        });
        hiddenImg.setOnClickListener(view -> {
            hide_room();
            dialog_share.dismiss();
            //hide dialog box
        });
        musicImg.setOnClickListener(view -> {
            dialog_share.dismiss();
            requestPermissions();
        });
        themeImg.setOnClickListener(view -> {
            themeBottomSheet(dialog_share);
            dialog_share.dismiss();
        });
        scoreBoardImg.setOnClickListener(view -> {
            scoreboard_bottom_sheet(dialog_share);
            dialog_share.dismiss();
        });
        roomModeImg.setOnClickListener(view -> {
            room_bottom_sheet_dialog();
            dialog_share.dismiss();
        });
    }
    private void room_bottom_sheet_dialog() {
        Dialog dialog_share = new Dialog(CallActivity.this);
        dialog_share.setContentView(R.layout.yoho_battle_dialog_box);
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog_share.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog_share.setCanceledOnTouchOutside(true);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog_share.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog_share.getWindow().setAttributes(layoutParams);
        Window window = dialog_share.getWindow();
        window.setGravity(Gravity.BOTTOM);
        dialog_share.show();
    }

    private void scoreboard_bottom_sheet(Dialog dialog) {
        Dialog dialog_share = new Dialog(CallActivity.this);
        dialog_share.setContentView(R.layout.scoreboard_dialog_box);
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog_share.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog_share.setCanceledOnTouchOutside(true);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog_share.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog_share.getWindow().setAttributes(layoutParams);
        Window window = dialog_share.getWindow();
        window.setGravity(Gravity.BOTTOM);
        dialog_share.show();

        RecyclerView historyRv = dialog_share.findViewById(R.id.historyRv);
        LinearLayout historyNodataLinearyout = dialog_share.findViewById(R.id.historyNodataLinearyout);
        ImageView historyCloseDialog = dialog_share.findViewById(R.id.historyCloseDialog);

        callViewModel.getLiveGiftHistory(CallActivity.this, liveHostBackImg, liveId).observe(CallActivity.this, new Observer<GetLiveGiftHistoryRoot>() {
            @Override
            public void onChanged(GetLiveGiftHistoryRoot getLiveGiftHistoryRoot) {

                if (getLiveGiftHistoryRoot.getSuccess().equalsIgnoreCase("1")) {
                    liveGiftHistoryList = getLiveGiftHistoryRoot.getDetails();
                    if (liveGiftHistoryList.isEmpty()) {
                        historyNodataLinearyout.setVisibility(View.VISIBLE);
                    } else {
                        historyNodataLinearyout.setVisibility(View.GONE);
                        scoreBoardHistroyRVAdapter = new ScoreBoardHistroyRVAdapter(liveGiftHistoryList);
                        historyRv.setAdapter(scoreBoardHistroyRVAdapter);
                    }
                } else {
                    historyNodataLinearyout.setVisibility(View.VISIBLE);
                }
            }
        });
        historyCloseDialog.setOnClickListener(view -> {
            dialog_share.dismiss();
            dialog.dismiss();
        });
    }

    @SuppressLint("SetTextI18n")
    private void inviteAudienceDialogBox(String seatPosition) {
        Dialog dialog_share = new Dialog(CallActivity.this);
        dialog_share.setContentView(R.layout.invite_audience_dialog_box);
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog_share.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog_share.setCanceledOnTouchOutside(true);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog_share.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog_share.getWindow().setAttributes(layoutParams);
        Window window = dialog_share.getWindow();
        window.setGravity(Gravity.BOTTOM);

        RecyclerView inviteAudienceRv = dialog_share.findViewById(R.id.inviteAudienceRv);
        TextView inviteAudiencePeopleLiveTv = dialog_share.findViewById(R.id.inviteAudiencePeopleLiveTv);
        ImageView inviteCloseDialog = dialog_share.findViewById(R.id.inviteCloseDialog);
        InviteAudienceRVAdapter.memeberPostion = 1;

        for (int i = 0; i < viewerList.size(); i++) {
            if (viewerList.get(i).isAdminStatus()) {
                InviteAudienceRVAdapter.memeberPostion++;
            } else {
            }
        }

        inviteCloseDialog.setOnClickListener(view -> dialog_share.dismiss());
        inviteAudiencePeopleLiveTv.setText(String.valueOf(viewerList.size()) + " people online");
        InviteAudienceRVAdapter inviteAudienceRVAdapter = new InviteAudienceRVAdapter(viewerList, CallActivity.this, new InviteAudienceRVAdapter.Callback() {
            @Override
            public void callback(GoLiveModelClass goLiveModelClass) {
            }
            @Override
            public void onBanned(int position, boolean liveHost, String userId) {
                if (seatPosition.isEmpty()) {
                    if (liveHost) {
                        openOtherUserProfile(viewerList.get(position), 0);
                        dialog_share.dismiss();
                    } else {
                        openOtherUserProfile(viewerList.get(position), 1);
                        dialog_share.dismiss();
                    }
                } else {
                    if (userId.equalsIgnoreCase(AppConstants.USER_ID)) {
                    } else {
                        if (userId.equalsIgnoreCase(liveHostBackImg)) {
                        } else {
                            inviteAudienceRef.child(liveHostBackImg).child(userId).setValue(seatPosition);
                        }
                    }
                }
            }
        });
        inviteAudienceRv.setAdapter(inviteAudienceRVAdapter);
        dialog_share.show();
    }

    private void themeBottomSheet(Dialog dialog) {
        LiveFreeThemeFragment.liveHostBackImg = liveHostBackImg;
        LivePurchasedThemeFragment.liveHostBackImg = liveHostBackImg;
        LiveThemeFragment.liveHostBackImg = liveHostBackImg;
        themesFragment = new LiveThemeFragment();
        themesFragment.show(getSupportFragmentManager(), themesFragment.getTag());
    }

    private void sendGiftToParticularUser(String giftImage, String otherUserId, String userId, String primeAccount, String id, String liveHostid,String giftType) {
        Dialog dialog_share = new Dialog(CallActivity.this);
        dialog_share.setContentView(R.layout.send_gift_to_live_user);
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog_share.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog_share.setCanceledOnTouchOutside(true);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog_share.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog_share.getWindow().setAttributes(layoutParams);
        Window window = dialog_share.getWindow();
        window.setGravity(Gravity.BOTTOM);
        if (!liveJoinedUserList.isEmpty()) {
            dialog_share.show();
        }
        for (int i = 0; i < liveJoinedUserList.size(); i++) {
            Log.d("CALLACTIVITY", "liveJoinedUserList: " + liveJoinedUserList.get(i).getUserID());
        }
        RecyclerView sendGiftUsersRV = (RecyclerView) dialog_share.findViewById(R.id.sendGiftUsersRV);
        SendGiftUserRVAdapter sendGiftUserRVAdapter = new SendGiftUserRVAdapter(liveJoinedUserList, CallActivity.this, new SendGiftUserRVAdapter.Callback() {
            @Override
            public void callback(GoLiveModelClass goLiveModelClass, TextView textView) {
                callViewModel.sendModelLiveData(CallActivity.this, AppConstants.USER_ID, goLiveModelClass.getUserID(), primeAccount, id, liveHostBackImg).observe(CallActivity.this, new Observer<GiftSendModel>() {
                    @Override
                    public void onChanged(GiftSendModel giftSendModel) {
                        if (giftSendModel.getStatus().equalsIgnoreCase("1")) {

                            HashMap<String, String> sendEmojiHs = new HashMap<>();
                            sendEmojiHs.put("emojiImg", giftImage);
                            sendEmojiHs.put("giftStatus",giftType);
                            sendEmojiHs.put("emojiSenderId", goLiveModelClass.getUserID());  //yeha voh sender means jo receive kr raha hai gift
                            sendEmojiHs.put("emojiSenderName", App.getSharedpref().getString("name"));
                            sendEmojiHs.put("emojiSenderImg", App.getSharedpref().getString("image"));
                            sendEmojiHs.put("status", "0");  //for gift

                            getAllTotalDiamonds();

                            emojiRef.child(liveHostBackImg).setValue(sendEmojiHs).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    sendGiftFirebase(giftImage,giftType);
                                    dialog_share.dismiss();
                                }
                            });
                            textView.setText("Sent");

                        } else {
                            notEnoughCoins();
//                            Toast.makeText(CallActivity.this, "0 " + giftSendModel.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        sendGiftUsersRV.setAdapter(sendGiftUserRVAdapter);
    }

    public void notEnoughCoins() {
        AlertDialog.Builder builder = new AlertDialog.Builder(CallActivity.this);
        builder.setMessage("Not enough coins, want to recharge?");
        builder.setTitle("Tips");
        builder.setCancelable(false);
        builder.setPositiveButton("Recharge", (DialogInterface.OnClickListener) (dialog, which) -> {
            App.getSharedpref().saveString("userCheck", "noCoins");
            setPIPScreen();
            dialog.dismiss();
        });
        builder.setNegativeButton("Cancel", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void getBackgroundImageFromFirebase() {
        if (liveHostBackImg != null && !liveHostBackImg.isEmpty()) {
            userLiveBackImgRef.child(liveHostBackImg).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        binding.progressImg.cancelAnimation();
                        binding.progressImg.setVisibility(View.GONE);
                        App.getSharedpref().saveString("theme",snapshot.getValue().toString());
                        Glide.with(getApplicationContext()).load(snapshot.getValue().toString()).error(R.drawable.themeblack).into(binding.imgBackground);
                    } else {
                        binding.imgBackground.setImageResource(R.drawable.themeblack);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        } else {
        }
    }

    private void adminDialogBox(Dialog dialog) {
        Dialog dialog_share = new Dialog(CallActivity.this);
        dialog_share.setContentView(R.layout.admin_rules);
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog_share.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog_share.setCanceledOnTouchOutside(true);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog_share.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog_share.getWindow().setAttributes(layoutParams);
        Window window = dialog_share.getWindow();
        window.setGravity(Gravity.BOTTOM);
        RecyclerView adminRV = (RecyclerView) dialog_share.findViewById(R.id.adminRV);
        LinearLayout adminRvLinearlayout = (LinearLayout) dialog_share.findViewById(R.id.adminRvLinearlayout);
        adminList = new ArrayList<>();
        adminLiveRef.child(liveHostBackImg).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    adminList.clear();
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        AdminFirebaseRoot adminFirebaseRoot = snapshot1.getValue(AdminFirebaseRoot.class);
                        adminList.add(adminFirebaseRoot);
                    }
                    if (adminList.isEmpty()) {
                        adminRvLinearlayout.setVisibility(View.GONE);
                    } else {
                        adminRvLinearlayout.setVisibility(View.VISIBLE);
                    }
                    adminRVAdapter = new AdminRVAdapter(adminList, CallActivity.this, new AdminRVAdapter.Callback() {
                        @Override
                        public void callback(AdminFirebaseRoot adminFirebaseRoot) {
                            if (adminFirebaseRoot != null) {

                                MultiLiveVideoAdapter.adminId = "0";
                                adminRVAdapter.notifyDataSetChanged();
                                dialog_share.dismiss();
                                adminLiveRef.child(liveHostBackImg).child(adminFirebaseRoot.getAdminId()).removeValue();
                                ref.child(otherUserId).child(liveType).child(otherUserId).child("viewer List").child(adminFirebaseRoot.getAdminId()).child("adminStatus").setValue(false);
                            }
                        }
                    });
                    adminRV.setAdapter(adminRVAdapter);
                } else {
                    if (getBaseContext() != null) {
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        dialog_share.show();
    }

    private void hide_room() {
        Dialog dialog_share = new Dialog(CallActivity.this);
        dialog_share.setContentView(R.layout.hidden_dialog_box);
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog_share.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_share.setCanceledOnTouchOutside(true);
        Window window = dialog_share.getWindow();
        window.setGravity(Gravity.CENTER_HORIZONTAL);
        dialog_share.show();
        AppCompatButton hiddenConfirmBtn = dialog_share.findViewById(R.id.hiddenConfirmBtn);
        AppCompatButton hiddenCancelbtn = dialog_share.findViewById(R.id.hiddenCancelbtn);

        hiddenCancelbtn.setOnClickListener(view -> dialog_share.dismiss());
        hiddenConfirmBtn.setOnClickListener(view -> hideLiveUserRoomApi(dialog_share));
    }

    private void hideLiveUserRoomApi(Dialog dialog_share) {
        callViewModel.hideLiveUserRoom(CallActivity.this, liveHostBackImg).observe(CallActivity.this, new Observer<LiveUserHideUnhideRoot>() {
            @Override
            public void onChanged(LiveUserHideUnhideRoot liveUserHideUnhideRoot) {
                if (liveUserHideUnhideRoot != null) {
                    if (liveUserHideUnhideRoot.getSuccess() == 1) {
//                        Toast.makeText(CallActivity.this, "" + liveUserHideUnhideRoot.getMessage(), Toast.LENGTH_SHORT).show();
                        dialog_share.dismiss();
                    } else {
//                        Toast.makeText(CallActivity.this, "" + liveUserHideUnhideRoot.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                }
            }
        });
    }

    private void clean_chat_dialog_box(Dialog dialog) {
        Dialog dialog_share = new Dialog(CallActivity.this);
        dialog_share.setContentView(R.layout.clean_chat_dialog_box);
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog_share.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_share.setCanceledOnTouchOutside(true);
        Window window = dialog_share.getWindow();
        window.setGravity(Gravity.CENTER);
        dialog_share.show();
        AppCompatButton cleanChatConfirmBtn = (AppCompatButton) dialog_share.findViewById(R.id.cleanChatConfirmBtn);
        AppCompatButton cleanChatCancelBtn = (AppCompatButton) dialog_share.findViewById(R.id.cleanChatCancelBtn);
        cleanChatConfirmBtn.setOnClickListener(view -> {
            ref.child(otherUserId).child(liveType).child(otherUserId).child("chat comments").removeValue();
            sendCustomeMessage("Chat cleaned", "");
            getCommentChatMessageFirebase();
            dialog_share.dismiss();
            dialog.dismiss();
        });
        cleanChatCancelBtn.setOnClickListener(view -> {
            dialog_share.dismiss();
            dialog.dismiss();
        });
    }


    private void lock_dialog_box() {
        Dialog dialog_share = new Dialog(CallActivity.this);
        dialog_share.setContentView(R.layout.set_room_password_dialog_box);
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog_share.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_share.setCanceledOnTouchOutside(true);

        Window window = dialog_share.getWindow();
        window.setGravity(Gravity.CENTER);
        OtpTextView liveLock = dialog_share.findViewById(R.id.liveLock_view);
        AppCompatButton confirm_pin = dialog_share.findViewById(R.id.confirm_pin);
        AppCompatButton cancel = dialog_share.findViewById(R.id.cancle_pin);

        confirm_pin.setOnClickListener(view -> {
            if (liveLock.getOTP().length() == 6) {
                apiSetPin(liveLock.getOTP(), dialog_share);
            } else {
//                Toast.makeText(CallActivity.this, "Enter 6 digit password", Toast.LENGTH_SHORT).show();
            }
        });

        cancel.setOnClickListener(view -> dialog_share.dismiss());
        dialog_share.show();
    }

    private void bulletin_dialog_box(Dialog dialog) {
        Dialog dialog_share = new Dialog(CallActivity.this);
        dialog_share.setContentView(R.layout.call_bulletin_dialog_box);
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog_share.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_share.setCanceledOnTouchOutside(true);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog_share.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog_share.getWindow().setAttributes(layoutParams);
        Window window = dialog_share.getWindow();
        window.setGravity(Gravity.CENTER);
        dialog_share.show();
        TextView editAnnoucementEdtx = dialog_share.findViewById(R.id.editAnnoucementEdtx);
        AppCompatButton announcementConfirmbtm = dialog_share.findViewById(R.id.announcementConfirmbtm);
        AppCompatButton announcementCancelbtm = dialog_share.findViewById(R.id.announcementCancelbtm);
        announcementConfirmbtm.setOnClickListener(view -> {
            String announcement = editAnnoucementEdtx.getText().toString();
            if (announcement.isEmpty()) {
                Toast.makeText(CallActivity.this, "Please enter your announcement", Toast.LENGTH_SHORT).show();
            } else {
                if (liveHostBackImg.equalsIgnoreCase(AppConstants.USER_ID)) {
                    userLiveAnnouncement.child(liveHostBackImg).setValue(announcement).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            binding.announcementTv.setVisibility(View.VISIBLE);
                            binding.announcementTv.setText("Room announcement: " + announcement);
                            binding.announcementTv.setSelected(true);
                            dialog.dismiss();
                            dialog_share.dismiss();
                        }
                    });
                }
            }
        });
        announcementCancelbtm.setOnClickListener(view -> dialog_share.dismiss());
    }

    private void coverInfoBottomSheet(Dialog dialog) {
        Dialog dialog_share = new Dialog(CallActivity.this);
        dialog_share.setContentView(R.layout.cover_info_dialog_box);
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog_share.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog_share.setCanceledOnTouchOutside(true);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog_share.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog_share.getWindow().setAttributes(layoutParams);
        Window window = dialog_share.getWindow();
        window.setGravity(Gravity.BOTTOM);
        dialog_share.show();

        ImageView coverInfoSelectImg = (ImageView) dialog_share.findViewById(R.id.coverInfoSelectImg);
        coverInfoUserImg = (ImageView) dialog_share.findViewById(R.id.coverInfoUserImg);
        ImageView coverInfoCloseImg = (ImageView) dialog_share.findViewById(R.id.coverInfoCloseImg);
        AppCompatButton coverInfoConfirmBtn = (AppCompatButton) dialog_share.findViewById(R.id.coverInfoConfirmBtn);
        RecyclerView coverInfoRV = (RecyclerView) dialog_share.findViewById(R.id.coverInfoRV);
        TextView coverInfoLiveTitleTv = (TextView) dialog_share.findViewById(R.id.coverInfoLiveTitleTv);

        Glide.with(coverInfoSelectImg.getContext()).load(App.getSharedpref().getString("liveCoverImg")).placeholder(R.drawable.ic_baseline_account_circle_24).into(coverInfoSelectImg);

        coverInfoTextList.clear();
        coverInfoTextList.add("Any");
        coverInfoTextList.add("Live");
        coverInfoTextList.add("Chat");
        coverInfoTextList.add("Party");
        coverInfoTextList.add("Sing");
        coverInfoTextList.add("Radio");
        coverInfoTextList.add("Game");
        coverInfoTextList.add("Scoor");
        coverInfoTextList.add("Birthday");
        coverInfoTextList.add("Emotions");
        coverInfoTextList.add("Dj");

        coverInfoRVAdapter = new CoverInfoRVAdapter(CallActivity.this, coverInfoTextList);
        coverInfoRV.setAdapter(coverInfoRVAdapter);

        coverInfoCloseImg.setOnClickListener(view -> dialog_share.dismiss());

        coverInfoSelectImg.setOnClickListener(view -> {
            ImagePicker.Companion.with(this).galleryOnly().crop().start();
        });

        coverInfoConfirmBtn.setOnClickListener(view -> {

            if (stringCoverPhotoPath.isEmpty()) {
                Toast.makeText(CallActivity.this, "Please Select image", Toast.LENGTH_SHORT).show();
            } else if (coverInfoLiveTitleTv.getText().toString().trim().isEmpty()) {
                Toast.makeText(CallActivity.this, "Please select title", Toast.LENGTH_SHORT).show();
            } else {

                if (coverInfoRVAdapter.getSelected() != null) {
                    coverInfotopicString = coverInfoRVAdapter.getSelected();
                } else {
                    Toast.makeText(CallActivity.this, "No selection", Toast.LENGTH_SHORT).show();
                }
                req_UserId = RequestBody.create(MediaType.parse("text/plain"), AppConstants.USER_ID);
                req_LiveId = RequestBody.create(MediaType.parse("text/plain"), liveId);
                coverImgTitle_req = RequestBody.create(MediaType.parse("text/plain"), coverInfoLiveTitleTv.getText().toString());

                if (coverInfotopicString != null) {
                    req_LiveTopic = RequestBody.create(MediaType.parse("text/plain"), coverInfotopicString);
                } else {
                    req_LiveTopic = RequestBody.create(MediaType.parse("text/plain"), "");
                }
                if (stringCoverPhotoPath.length() != 0) {
                    File file1 = new File(stringCoverPhotoPath);
                    RequestBody requestBody1 = RequestBody.create(MediaType.parse("image"), file1);
                    reqCoverLiveImg = MultipartBody.Part.createFormData("Liveimage", file1.getName(), requestBody1);
                } else {
                    File file1 = new File("");
                    RequestBody requestBody = RequestBody.create(MediaType.parse("image"), "");
                    reqCoverLiveImg = MultipartBody.Part.createFormData("Liveimage", file1.getName(), requestBody);
                }

                callViewModel.setLiveCoverImg(CallActivity.this, req_UserId, req_LiveId, req_LiveTopic, coverImgTitle_req, reqCoverLiveImg).observe(CallActivity.this, new Observer<SetLiveCoverImgRoot>() {
                    @Override
                    public void onChanged(SetLiveCoverImgRoot setLiveCoverImgRoot) {
                        if (setLiveCoverImgRoot != null) {
                            if (setLiveCoverImgRoot.getSuccess().equalsIgnoreCase("1")) {
                                dialog.dismiss();
                                App.getSharedpref().saveString("liveCoverImg", setLiveCoverImgRoot.getDetails().getLiveimage());
                                App.getSharedpref().saveString("liveTopic", setLiveCoverImgRoot.getDetails().getImageText());
                                App.getSharedpref().saveString("broadTitle", setLiveCoverImgRoot.getDetails().getImageTitle());
                                binding.txtUserName.setText(setLiveCoverImgRoot.getDetails().getImageTitle());
                                Glide.with(binding.imgProfileuser.getContext()).load(setLiveCoverImgRoot.getDetails().getLiveimage()).placeholder(R.drawable.ic_baseline_account_circle_24).into(binding.imgProfileuser);
                                dialog_share.dismiss();
                                dialog.dismiss();
                                HashMap<String, String> hashmapInfo = new HashMap<>();
                                hashmapInfo.put("photopath", App.getSharedpref().getString("liveCoverImg"));
                                hashmapInfo.put("coverInfoLiveTitle", App.getSharedpref().getString("broadTitle"));
                                coverInfo.child("CoverInfo").child(AppConstants.USER_ID).setValue(hashmapInfo);
                            } else {
                            }
                        } else {
                            Toast.makeText(CallActivity.this, "Technical issue", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    private void apiSetPin(String otp, Dialog dialog_share) {
        callViewModel.LockUser(CallActivity.this, AppConstants.USER_ID, liveId, otp).observe(CallActivity.this, new Observer<RootLockBroadCast>() {
            @Override
            public void onChanged(RootLockBroadCast rootNewUser) {

                if (rootNewUser != null) {
                    dialog_share.dismiss();
                    if (rootNewUser.getSuccess().equalsIgnoreCase("1")) {
                        HashMap<String, String> lockLive = new HashMap<>();
                        if (rootNewUser.getDetails().getPassword().isEmpty()) {
                            lockLive.put("status", "0");  //false
                            lockLive.put("password", "");
                            Toast.makeText(CallActivity.this, "Unlocked Successfully", Toast.LENGTH_SHORT).show();
                            binding.roomLockImg.setVisibility(View.GONE);

                            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) binding.txtFollowers.getLayoutParams();
                            params.leftMargin = 15;

                        } else {
                            lockLive.put("status", "1"); //true
                            lockLive.put("password", rootNewUser.getDetails().getPassword());
                            Toast.makeText(CallActivity.this, "Locked successfully", Toast.LENGTH_SHORT).show();
                            binding.roomLockImg.setVisibility(View.VISIBLE);
                            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) binding.txtFollowers.getLayoutParams();
                            params.leftMargin = 5;
                        }

                        lockRef.child(liveHostBackImg).setValue(lockLive);

                    } else {
                    }
                } else {
                }
            }
        });
    }

    private void getGift() {
        ref.child(otherUserId).child(liveType).child(otherUserId).child("gifts").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                if (snapshot.exists()) {
                    GiftModel giftModel = snapshot.getValue(GiftModel.class);
                    showGift(giftModel);
                }
            }
            @Override
            public void onChildChanged(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
            }
            @Override
            public void onChildRemoved(@NonNull @NotNull DataSnapshot snapshot) {
            }
            @Override
            public void onChildMoved(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
            }
        });
    }

    private void showGift(GiftModel giftModel) {
        binding.lottieGift.setVisibility(View.GONE);
        try {
            Glide.with(this).load(giftModel.getGiftPath()).into(binding.lottieGift);
        } catch (Exception e) {
        }
//        binding.lottieGift.setAnimationFromUrl(giftModel.getGiftPath());
        binding.lottieGift.setVisibility(View.VISIBLE);
//        binding.lottieGift.playAnimation();
        if (countDownTimer != null) countDownTimer.cancel();
        countDownTimer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {
                Log.i("onTick: ", (l / 1000) + "");
            }
            @Override
            public void onFinish() {
                binding.lottieGift.setVisibility(View.GONE);
                ref.child(otherUserId).child(liveType).child(otherUserId).child("gifts").removeValue();
            }
        };
        countDownTimer.start();
    }

    private void getMultiLiveRequest() {
        ref.child(otherUserId).child(liveType).child(otherUserId).child("multiLiveRequest").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    requestMultiLiveList.clear();
                    liveJoinedUserList.clear();
                    liveJoinedHostUserList.clear();
                    liveJoinedHostUserList.add(new GoLiveModelClass());
                    liveJoinedHostUserList.add(new GoLiveModelClass());
                    liveJoinedHostUserList.add(new GoLiveModelClass());
                    liveJoinedHostUserList.add(new GoLiveModelClass());
                    liveJoinedHostUserList.add(new GoLiveModelClass());
                    liveJoinedHostUserList.add(new GoLiveModelClass());
                    liveJoinedHostUserList.add(new GoLiveModelClass());
                    liveJoinedHostUserList.add(new GoLiveModelClass());

                    GoLiveModelClass hostUserDetails = new GoLiveModelClass();
                    hostUserDetails.setUserID(otherUserId);
                    hostUserDetails.setUserName(getChannelName);
                    hostUserDetails.setImage(image);
                    hostUserDetails.setName(name);
                    liveJoinedUserList.clear();
                    liveJoinedUserList.add(0, hostUserDetails);

                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        GoLiveModelClass goLiveModelClass = snapshot1.getValue(GoLiveModelClass.class);
                        if (goLiveModelClass.getRequestStatus().equalsIgnoreCase("0")) {
                            if (userId.equalsIgnoreCase(otherUserId)) {
                                boolean isSlotAvailable = false;
                                for (int i = 0; i < liveJoinedHostUserList.size(); i++) {
                                    isSlotAvailable = !liveJoinedHostUserList.get(i).getUserID().equalsIgnoreCase("");
                                }
                                if (!isSlotAvailable) {
                                    openRequestDialogForMultiLive(goLiveModelClass);
                                }
                            }
                            requestMultiLiveList.add(goLiveModelClass);
                        } else if (goLiveModelClass.getRequestStatus().equalsIgnoreCase("1")) {

                            liveJoinedUserList.add(goLiveModelClass);

                            //empty postion method on the seat
                            if (goLiveModelClass.getSeatPosition() != null) {
                                liveJoinedHostUserList.set(Integer.parseInt(goLiveModelClass.getSeatPosition()), goLiveModelClass);
                            } else {
                                liveJoinedHostUserList.set(getListEmptyPosition(), goLiveModelClass);
                            }

                            if (goLiveModelClass.getUserID().equalsIgnoreCase(AppConstants.USER_ID)) {

                                //Mute
                                //for mute host to another users
                                if (goLiveModelClass.getMute().equalsIgnoreCase("0")) {
                                    rtcEngine().muteLocalAudioStream(true);
//                                    muteMicRef.child(liveHostBackImg).child(goLiveModelClass.getUserID()).child("status").setValue("0");
                                    muteMicRef.child(liveHostBackImg).child(goLiveModelClass.getUserID()).setValue("0");
                                    binding.imgMuteMic.setImageResource(R.drawable.ic_baseline_mic_off_24);


                                } else {
                                    rtcEngine().muteLocalAudioStream(false);
//                                  muteMicRef.child(liveHostBackImg).child(goLiveModelClass.getUserID()).child("status").setValue("1");
                                    muteMicRef.child(liveHostBackImg).child(goLiveModelClass.getUserID()).setValue("1");
                                    binding.imgMuteMic.setImageResource(R.drawable.ic_baseline_mic_24);
                                }
                            }
                        }
                    }
                    if (userJoinedAdapter != null) {
                        userJoinedAdapter.notifyDataSetChanged();
                    }
                    if (multiLiveVideoAdapter != null) {
                        multiLiveVideoAdapter.notifyDataSetChanged();
                    }
                    if (!liveStatus.equalsIgnoreCase("host")) {
                        setRequestListAdapter(requestMultiLiveList);
                        requestMultiLiveAdapter.notifyDataSetChanged();
                    } else {
                        if (allPendingRequestAdapter != null) {
                            allPendingRequestAdapter.notifyDataSetChanged();
                        }
                        setAllPendingAdapter(requestMultiLiveList);
                    }
                    setAcceptedGustHostAdapter(liveJoinedHostUserList);
                    if(multiLiveVideoAdapter != null) {
                        multiLiveVideoAdapter.notifyDataSetChanged();
                    }
                    binding.txtTotalRequest.setText("" + requestMultiLiveList.size());
                }else{
                }
            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
            }
        });
    }

    private void setAllPendingAdapter(List<GoLiveModelClass> requestMultiLiveList) {
        allPendingRequestAdapter = new AllPendingRequestAdapter(this, requestMultiLiveList);
    }

    private int getListEmptyPosition() {
        //this empty position live users
        for (int i = 0; i < liveJoinedHostUserList.size(); i++) {
            if (liveJoinedHostUserList.get(i).getUserID().equalsIgnoreCase("")) {
                emptyPosition = i;
                break;
            }
        }
        return emptyPosition;
    }

    private void setAcceptedGustHostAdapter(List<GoLiveModelClass> liveJoinedUserList) {
        multiLiveVideoAdapter = new MultiLiveVideoAdapter(this, liveJoinedUserList, new MultiLiveVideoAdapter.Click() {
            @Override
            public void setOnUserKickListener(GoLiveModelClass goLiveModelClass, String admin) {
                checkAdmin();
                if (userId.equalsIgnoreCase(otherUserId) || admin.equalsIgnoreCase(AppConstants.USER_ID)) {
                    openKickDialog(goLiveModelClass);
                } else if (userId.equalsIgnoreCase(goLiveModelClass.getUserID())) {
                }
            }

            @Override
            public void setOnShowUserProfile(GoLiveModelClass goLiveModelClass, int adminStatus, String adminIdThroughCall) {

                //1 means admin hai
                openOtherUserProfile(goLiveModelClass, adminStatus);
                adminIdThroughCallback = adminIdThroughCall;

            }

            @Override
            public void muteMic(GoLiveModelClass goLiveModelClass, AppCompatImageView imgMic, String status) {

                checkAdmin();

                if (liveHostBackImg.equalsIgnoreCase(AppConstants.USER_ID) || adminId.equalsIgnoreCase(AppConstants.USER_ID)) {
                    Map data = new HashMap<>();
                    data.put("mute", status);
                    ref.child(otherUserId).child(liveType).child(otherUserId).child("multiLiveRequest").child(goLiveModelClass.getUserID()).updateChildren(data);
                } else {
//                    Toast.makeText(CallActivity.this, "You are  muted by host", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void showEmojiBackToActivity(String emoji, String userId, String hostId) {
            }

            @Override
            public void selectSeat(GoLiveModelClass goLiveModelClass, String position) {
                if (goLiveModelClass.getUserID().equalsIgnoreCase(AppConstants.USER_ID)) {
                    //lock seat
//                    sendRequestForMultiLive(position,position);
                } else {
                    sendRequestForMultiLive(position, "");
                }

            }

            @Override
            public void lockSeat(GoLiveModelClass goLiveModelClass, String positon) {
                sendRequestForMultiLive("9", "");
            }

            @Override
            public void inviteForSeat(String position) {

                inviteAudienceDialogBox(position);

            }
        });
        binding.rvMultiLiveVideos.setAdapter(multiLiveVideoAdapter);
    }

    private void openKickDialog(GoLiveModelClass goLiveModelClass) {
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        DialogKikoutJoinedUserBinding dialogKickoutJoinedUserBinding = DialogKikoutJoinedUserBinding.inflate(LayoutInflater.from(this));
        alert.setView(dialogKickoutJoinedUserBinding.getRoot());
        alertDialog = alert.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        try {
            alertDialog.show();
            Glide.with(CallActivity.this).load(goLiveModelClass.getImage()).placeholder(R.drawable.ic_baseline_account_circle_24).into(dialogKickoutJoinedUserBinding.imgProfile);
        } catch (Exception e) {
        }

        dialogKickoutJoinedUserBinding.txtUserName.setText("Do you want to kick out this user: " + goLiveModelClass.getName());
        dialogKickoutJoinedUserBinding.btnAccept.setOnClickListener(view -> {

            callViewModel.kickOutLiveUser(CallActivity.this, goLiveModelClass.getUserID(), AppConstants.USER_ID, liveId).observe(this, new Observer<KickOutLiveRoot>() {
                @Override
                public void onChanged(KickOutLiveRoot kickOutLiveRoot) {
                    if (kickOutLiveRoot != null) {
                        if (kickOutLiveRoot.getSuccess().equalsIgnoreCase("1")) {
                            Toast.makeText(CallActivity.this, " " + kickOutLiveRoot.getMessage(), Toast.LENGTH_SHORT).show();
                            setBannedUser(goLiveModelClass);
                            ref.child(otherUserId).child(liveType).child(otherUserId).child("banUser").child(goLiveModelClass.getUserID()).removeValue();
                        } else {
                            Toast.makeText(CallActivity.this, " " + kickOutLiveRoot.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(CallActivity.this, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            alertDialog.dismiss();
        });

        dialogKickoutJoinedUserBinding.btnRejected.setOnClickListener(view -> {
            alertDialog.dismiss();
        });
        dialogKickoutJoinedUserBinding.imgClose.setOnClickListener(view -> {
            alertDialog.dismiss();
        });
    }

    private void setRequestListAdapter(List<GoLiveModelClass> requestMultiLiveList) {
        try {
            requestMultiLiveAdapter = new RequestMultiLiveAdapter(this, requestMultiLiveList, new RequestMultiLiveAdapter.Click() {
                @Override
                public void setOnRequestAcceptListener(GoLiveModelClass goLiveModelClass) {
                    requestListMultiBottomSheet.dismiss();
                    setMultiLiveRequestAcceptRejecte(goLiveModelClass, "1");
                }

                @Override
                public void setOnRequestRejectedListner(GoLiveModelClass goLiveModelClass) {
                    requestListMultiBottomSheet.dismiss();
                    setMultiLiveRequestAcceptRejecte(goLiveModelClass, "2");

                }

                @Override
                public void setOnRemoveUserListener(GoLiveModelClass goLiveModelClass) {
                    requestListMultiBottomSheet.dismiss();
                    setMultiLiveRequestAcceptRejecte(goLiveModelClass, "3");
                }
            });
        } catch (Exception e) {
        }
    }

    private void openRequestDialogForMultiLive(GoLiveModelClass goLiveModelClass) {
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        DialogRequestMultiLiveBinding requestMultiLiveBinding = DialogRequestMultiLiveBinding.inflate(LayoutInflater.from(this));
        alert.setView(requestMultiLiveBinding.getRoot());
        alertDialog = alert.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        try {
            alertDialog.show();
            Glide.with(CallActivity.this).load(goLiveModelClass.getImage()).placeholder(R.drawable.ic_baseline_account_circle_24).into(requestMultiLiveBinding.imgProfile);
        } catch (Exception e) {

        }
        requestMultiLiveBinding.txtUserName.setText(goLiveModelClass.getName() + " wants to join with you in live session.");
        requestMultiLiveBinding.btnAccept.setOnClickListener(view -> {
            alertDialog.dismiss();
            setMultiLiveRequestAcceptRejecte(goLiveModelClass, "1");
        });
        requestMultiLiveBinding.btnRejected.setOnClickListener(view -> {
            alertDialog.dismiss();
            setMultiLiveRequestAcceptRejecte(goLiveModelClass, "2");
        });
        requestMultiLiveBinding.imgClose.setOnClickListener(view -> {
            alertDialog.dismiss();
        });
    }

    private void setMultiLiveRequestAcceptRejecte(GoLiveModelClass goLiveModelClass, String status) {
        goLiveModelClass.setRequestStatus(status);
        ref.child(otherUserId).child(liveType).child(otherUserId).child("multiLiveRequest").child(goLiveModelClass.getUserID()).setValue(goLiveModelClass);
    }

    private void hitCreateLiveHistoryApi(String userId, String currentTime, String endLive, String liveType) {
        HashMap<String, String> data = new HashMap<>();
        data.put("userId", userId);
        data.put("startLive", currentTime);
        data.put("endLive", endLive);
        data.put("liveType", liveType);
//        new VM().createLiveHistory(this, data).observe(this, new Observer<CreateLiveHistoryModel>() {
//            @Override
//            public void onChanged(CreateLiveHistoryModel createLiveHistoryModel) {
//                if (createLiveHistoryModel.getSuccess().equalsIgnoreCase("1")) {
//                    createLiveHistory = createLiveHistoryModel;
//                }
//                Toast.makeText(CallActivity.this, "" + createLiveHistoryModel.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void initHeartDrawables() {
        for (Integer i : drawableIds) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), i);
            Drawable drawable = new BitmapDrawable(bitmap);
            drawablesList.add(drawable);
        }
    }

    private void getHeart() {
        ref.child(otherUserId).child(liveType).child(otherUserId).child("flyingHeart").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    HeartModel data = snapshot.getValue(HeartModel.class);
                    int position = Integer.parseInt(data.getPosition());
                    binding.heartView.add(new HeartDrawable(drawablesList.get(position)));
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    private void checkAdmin() {
        adminLiveRef.child(liveHostBackImg).child(AppConstants.USER_ID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    adminId = snapshot.child("adminId").getValue().toString();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void getMuteUsers() {
        ref.child(otherUserId).child(liveType).child(otherUserId).child("muteUsers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    muteUsers.clear();
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        GoLiveModelClass goLiveModelClass = snapshot1.getValue(GoLiveModelClass.class);
                        muteUsers.add(goLiveModelClass.getUserID());
                    }
                    if (liveStatus.equalsIgnoreCase("host")) {
                        if (muteUsers.contains(userId)) {
//                            Toast.makeText(CallActivity.this, "You are muted by Host", Toast.LENGTH_SHORT).show();
                        } else {
//                            Toast.makeText(CallActivity.this, "You are unMute by Host", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
            }
        });
    }

    private void getBanListFirebase() {
        ref.child(otherUserId).child(liveType).child(otherUserId).child("banUser").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                banList.clear();
                list.clear();
                if (snapshot.exists()) {
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        GoLiveModelClass goLiveModelClass = snapshot1.getValue(GoLiveModelClass.class);
                        banList.add(goLiveModelClass);
                        list.add(goLiveModelClass.getUserID());
                    }
                    checkUserBanedOrNot(list);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
            }
        });
    }

    private void checkUserBanedOrNot(List<String> banList) {
        for (int i = 0; i < banList.size(); i++) {
            if (banList.contains(userId)) {
                Toast.makeText(this, "your are baned", Toast.LENGTH_SHORT).show();
                finish();
            } else {
//                Toast.makeText(this, "your are not baned", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void exitLiveStream() {
        ref.child(otherUserId).child(liveType).child(otherUserId).child("hostLiveInfo").child("live").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    boolean b = snapshot.getValue(Boolean.class);
                    if (!b) finish();
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    // this function create viewer list
    private void firebaseAddDataUnderHostId() {
        GoLiveModelClass goLiveModelClass = new GoLiveModelClass();
        goLiveModelClass.setUserID(userId);
        String image = userImage;
        goLiveModelClass.setImage(image);
        goLiveModelClass.setName(userName);
        goLiveModelClass.setUserName(userName);
        goLiveModelClass.setLive(true);
        goLiveModelClass.setAge(userDob);
        goLiveModelClass.setGender(userGender);
        goLiveModelClass.setSvga(profileFrame);
        goLiveModelClass.setEntryEffect(entryFrameEffect);
        if (userId.equalsIgnoreCase(liveHostBackImg)) {
            goLiveModelClass.setAge(dob);
            goLiveModelClass.setGender(gender);
            goLiveModelClass.setUserName(hostUsername);
        }

        adminLiveRef.child(liveHostBackImg).child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    goLiveModelClass.setAdminStatus(true);
                    ref.child(otherUserId).child(liveType).child(otherUserId).child("viewer List").child(userId).setValue(goLiveModelClass);
                } else {
                    goLiveModelClass.setAdminStatus(false);
                    ref.child(otherUserId).child(liveType).child(otherUserId).child("viewer List").child(userId).setValue(goLiveModelClass);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void sendMessage(ChatMessageModel chatMessageModel, String key) {
        ref.child(otherUserId).child(liveType).child(otherUserId).child("chat comments").child(key).setValue(chatMessageModel);
    }

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_HEADSET_PLUG);
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED);
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        this.registerReceiver(mHeadsetBroadcastReceiver, filter);
        if (isLiveConnected) {
            rtcEngine().enableAudio();
            rtcEngine().enableVideo();
        }
        Log.i("onPause: ", "go on onResume");
        if (!liveStatus.equalsIgnoreCase("host")) {
            rtcEngine().enableAudio();
            rtcEngine().enableVideo();
        }
    }

    @Override
    protected void initUIandEvent() {
        firebaseAddDataUnderHostId();
        if (liveStatus.equalsIgnoreCase("host")) {
            sendJoinedMessage();
            if (liveType.equalsIgnoreCase("singleLive")) {
                binding.llOption.setVisibility(View.GONE);
                binding.rlGiftHeart.setVisibility(View.VISIBLE);
                binding.rlGift.setVisibility(View.GONE);
            } else {
                binding.llOption.setVisibility(View.GONE);
                binding.rlGiftHeart.setVisibility(View.VISIBLE);
                binding.rlGift.setVisibility(View.GONE);
                getMultiLiveRequestStatus();
            }
        } else {
            binding.rlMultiLiveRequest.setVisibility(View.GONE);
            if (liveType.equalsIgnoreCase("singleLive")) {
                binding.rlMultiLiveRequest.setVisibility(View.GONE);
            } else {
                binding.rlMultiLiveRequest.setVisibility(View.VISIBLE);
            }
            GoLiveModelClass goLiveModelClass = new GoLiveModelClass();
            goLiveModelClass.setUserID(userId);
            goLiveModelClass.setImage(userImage);
            goLiveModelClass.setLiveStatus(liveStatus);
            goLiveModelClass.setName(userName);
            goLiveModelClass.setLiveType(liveType);
            goLiveModelClass.setLive(true);
            ref.child(userId).child(liveType).child(userId).child("hostLiveInfo").setValue(goLiveModelClass);
//          binding.llOption.setVisibility(View.VISIBLE); this is for all requst this will show for host only
            binding.rlGiftHeart.setVisibility(View.GONE);
            sendWelcomeMessageFirebase();
            inviteAudienceList.add(goLiveModelClass);
        }
        if (liveStatus.equalsIgnoreCase("host")) {
            exitLiveStream();
        }
        getCommentChatMessageFirebase();
        getViewerListFirebase();
        getHeart();
        getGift();
        commentAdapter = new CommentAdapter(this, chatMessages, new CommentAdapter.Callback() {
            @Override
            public void openUserProfile(ChatMessageModel chatMessageModel) {
                GoLiveModelClass goLiveModelClass =  new GoLiveModelClass();
                goLiveModelClass.setUserID(chatMessageModel.getUserId());
                goLiveModelClass.setImage(chatMessageModel.getImage());
                goLiveModelClass.setName(chatMessageModel.getName());
                openOtherUserProfile(goLiveModelClass, adminStatus);
            }
        });
        binding.recyclerAllMessage.setAdapter(commentAdapter);
        viewerAdapter = new ViewerAdapter(this, viewerList, liveStatus, new ViewerAdapter.Click() {
            @Override
            public void onBanned(int position, boolean liveHost) {
                if (liveHost) {
                    openOtherUserProfile(viewerList.get(position), 0);
                } else {
                    openOtherUserProfile(viewerList.get(position), 1);
                }
            }
        });
        binding.recyclerViewers.setAdapter(viewerAdapter);
        //Viewer list Gone
        binding.recyclerViewers.setVisibility(View.GONE);
        addEventHandler(this);
        final String encryptionKey = getIntent().getStringExtra(ConstantApp.ACTION_KEY_ENCRYPTION_KEY);
        final String encryptionMode = getIntent().getStringExtra(ConstantApp.ACTION_KEY_ENCRYPTION_MODE);
        doConfigEngine(encryptionKey, encryptionMode);
        mGridVideoViewContainer = findViewById(R.id.grid_video_view_container);

        if (!liveStatus.equalsIgnoreCase("host")) {
            SurfaceView surfaceV = RtcEngine.CreateRendererView(getApplicationContext());
            preview(true, surfaceV, 0);
            surfaceV.setZOrderOnTop(false);
            surfaceV.setZOrderMediaOverlay(false);
            mUidsList.put(0, surfaceV); // get first surface view
            rtcEngine().enableLocalVideo(false);
            rtcEngine().enableLocalAudio(true);
            rtcEngine().enableAudio();

        } else {
            rtcEngine().enableLocalVideo(false);
            rtcEngine().enableLocalAudio(false);
        }
        mGridVideoViewContainer.initViewContainer(this, 0, mUidsList, mIsLandscape, mGridVideoViewContainer, RecyclerView.VERTICAL); // first is now full view
        rtcEngine().setAudioProfile(AUDIO_PROFILE_MUSIC_HIGH_QUALITY_STEREO, Constants.AUDIO_SCENARIO_SHOWROOM);
        joinChannel(getChannelName, config().mUid, getAccessToken);
        optional();
        binding.rlHeart.setOnClickListener(view -> {
            sendFlyingHeartInFirebase();
        });

        binding.edtMessage.setOnClickListener(view -> {
            openDialogForSendMessage();
        });

        binding.rlGift.setOnClickListener(view -> {
            openGiftDialog();
        });

        binding.rlEmojiGift.setOnClickListener(v -> {
            setEmojiGifts();
        });

        binding.rlMultiLiveRequest.setOnClickListener(view -> {
            openRequestMultiLiveDialog();
        });

        binding.rlSendMessage.setOnClickListener(view -> {
            binding.edtMessage.requestFocus();
            openDialogForSendMessage();
        });

        AudioManager audioManager = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        }

        audioManager.setMode(AudioManager.MODE_NORMAL);
        audioManager.setSpeakerphoneOn(false);

    }

    private void getMultiLiveRequestStatus() {
        ref.child(otherUserId).child(liveType).child(otherUserId).child("multiLiveRequest").child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    GoLiveModelClass goLiveModelClass = snapshot.getValue(GoLiveModelClass.class);
                    if (goLiveModelClass.getRequestStatus().equalsIgnoreCase("1")) {
                        isLiveConnected = true;
//                        Toast.makeText(CallActivity.this, "Request Accepted", Toast.LENGTH_SHORT).show();
                        setViewerGoLiveWithHost();
                    } else if (goLiveModelClass.getRequestStatus().equalsIgnoreCase("2")) {
                        isLiveConnected = false;
                        Toast.makeText(CallActivity.this, "Request Rejected", Toast.LENGTH_SHORT).show();
                    } else if (goLiveModelClass.getRequestStatus().equalsIgnoreCase("3")) {
                        isLiveConnected = false;
                        removeByHostLive();
                        Toast.makeText(CallActivity.this, "Remove By Host", Toast.LENGTH_SHORT).show();
                    } else {
                        isLiveConnected = false;
                        Toast.makeText(CallActivity.this, "Waiting for host to accept request", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
            }
        });

    }

    private void openDialogForSendMessage() {
        sendMessageBottomSheet = new BottomSheetDialog(this);
        DialogSendMessageBinding dialogGiftBinding = DialogSendMessageBinding.inflate(LayoutInflater.from(this));
        sendMessageBottomSheet.setContentView(dialogGiftBinding.getRoot());
        sendMessageBottomSheet.show();
        dialogGiftBinding.edtMessage.requestFocus();
        InputMethodManager methodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        assert methodManager != null && dialogGiftBinding.edtMessage != null;
        methodManager.showSoftInput(dialogGiftBinding.edtMessage, InputMethodManager.SHOW_IMPLICIT);

        Objects.requireNonNull(dialogGiftBinding.rlSend).setOnClickListener(view -> {
            if (liveChatBanned == 0) {
                if (dialogGiftBinding.edtMessage.getText().toString().trim().equalsIgnoreCase("")) {
                } else {
                    if (muteUsers.contains(userId)) {
                        Toast.makeText(this, "You can not send a message", Toast.LENGTH_SHORT).show();
                        dialogGiftBinding.edtMessage.setText("");
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                                String mess = "";
                                mess = dialogGiftBinding.edtMessage.getText().toString();
                                dialogGiftBinding.edtMessage.setText("");
                                sendMessageBottomSheet.dismiss();
                                sendCustomeMessage(mess, "");
                            }
                        });
                    }
                }
            } else {
                Toast.makeText(this, "You are Banned", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendGiftFirebase(String giftImage,String giftType) {
        GiftModel giftModel = new GiftModel();
        giftModel.setGiftPath(giftImage);
        giftModel.setUserId(userId);
        giftModel.setUserName(userName);
        sendCustomeMessage("Sends you gift", giftImage);
    }

    private void removeByHostLive() {
        finish();
        deInitUIandEvent();
    }

    private void setViewerGoLiveWithHost() {
        SurfaceView surfaceV = RtcEngine.CreateRendererView(getApplicationContext());
        preview(true, surfaceV, 0);
        surfaceV.setZOrderOnTop(false);
        surfaceV.setZOrderMediaOverlay(false);
        mUidsList.put(0, surfaceV); // get first surface view
        rtcEngine().enableLocalAudio(true);
//      binding.llOption.setVisibility(View.VISIBLE);  //  this willl show only for host its request
        binding.rlMultiLiveRequest.setVisibility(View.GONE);
        binding.rlGift.setVisibility(View.GONE);
        binding.rlEmojiGift.setVisibility(View.GONE);
        isJoined = true;
    }

    private void openRequestMultiLiveDialog() {
        requestListMultiBottomSheet = new BottomSheetDialog(this);
        MultiliveListDialogBinding dialogGiftBinding = MultiliveListDialogBinding.inflate(LayoutInflater.from(this));
        requestListMultiBottomSheet.setContentView(dialogGiftBinding.getRoot());
        dialogGiftBinding.recyclerRequestMultiLive.setAdapter(requestMultiLiveAdapter);
        requestListMultiBottomSheet.show();
    }

    private void sendRequestForMultiLive(String seatPosition, String lockSeat) {
        GoLiveModelClass goLiveModelClass = new GoLiveModelClass();
        goLiveModelClass.setUserID(userId);
        String image = userImage;
        goLiveModelClass.setImage(image);
        goLiveModelClass.setName(userName);
        goLiveModelClass.setUserName(userName);
        goLiveModelClass.setRequestStatus("0");
        goLiveModelClass.setRequestStatus("1");
        goLiveModelClass.setSvga(profileFrame);
        goLiveModelClass.setEntryEffect(entryFrameEffect);
        goLiveModelClass.setSeatPosition(seatPosition);
        goLiveModelClass.setSeatLock(lockSeat);
        // 1 for not mute
        goLiveModelClass.setMute(mutevalueOfUSer);
        ref.child(otherUserId).child(liveType).child(otherUserId).child("multiLiveRequest").child(userId).setValue(goLiveModelClass);
    }

    private void setEmojiGifts() {
        liveHostid = getIntent().getStringExtra("liveHostId");

        final BottomSheetDialog emojiGiftDialog = new BottomSheetDialog(this);
        DialogGiftEmojiBinding dialogGiftEmojiBinding = DialogGiftEmojiBinding.inflate(LayoutInflater.from(this));
        emojiGiftDialog.setContentView(dialogGiftEmojiBinding.getRoot());
        emojiGiftDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        HashMap<String, String> data = new HashMap<>();
        data.put("userId", userId);
        callViewModel.emojiGiftModelLiveData(CallActivity.this).observe(this, new Observer<EmojiGiftModel>() {
            @Override
            public void onChanged(EmojiGiftModel emojiGiftModel) {
                if (emojiGiftModel.getSuccess().equalsIgnoreCase("1")) {
                    arrayListEmoji = emojiGiftModel.getDetails();
                    EmojiGiftAdapter emojiGiftAdapter = new EmojiGiftAdapter(CallActivity.this, arrayListEmoji, new EmojiGiftAdapter.Select() {
                        @Override
                        public void details(EmojiGiftModel.Detail detail) {
                            sendEmojiGift(detail, otherUserId, userId, detail.getPrimeAccount(), detail.getId(), liveHostid);
                            emojiGiftDialog.dismiss();
                        }
                    });
                    dialogGiftEmojiBinding.recyclerEmojiGift.setAdapter(emojiGiftAdapter);
                }
            }
        });
        emojiGiftDialog.show();
    }

    private void sendEmojiGift(EmojiGiftModel.Detail detail, String otherUserId, String userId, String primeAccount, String id, String liveHostid) {
        callViewModel.sendEmojiGiftModelLiveData(CallActivity.this, otherUserId, userId, primeAccount, id, liveHostid).observe(this, new Observer<SendEmojiGiftModel>() {
            @Override
            public void onChanged(SendEmojiGiftModel sendEmojiGiftModel) {
                if (sendEmojiGiftModel.getSuccess().equalsIgnoreCase("1")) {
                    emojiImage = detail.getImage();
                    sendEmojiToHost(detail);
                } else {
                }
            }
        });
    }

    private void sendEmojiToHost(EmojiGiftModel.Detail detail) {
        GiftModel giftModel = new GiftModel();
        giftModel.setGiftPath(detail.getImage());
        giftModel.setUserId(userId);
        giftModel.setUserName(userName);
        String key = ref.push().getKey();
        ref.child(otherUserId).child(liveType).child(otherUserId).child("gifts").child(key).setValue(giftModel);
        sendCustomeMessage("Sends you gift", detail.getImage());
    }

    private void openGiftDialog() {
        liveHostid = getIntent().getStringExtra("liveHostId");
        DialogGiftBinding dialogGiftBinding = DialogGiftBinding.inflate(LayoutInflater.from(this));
        //DIALOG BOX
        Dialog dialog = new Dialog(CallActivity.this);
        dialog.setContentView(dialogGiftBinding.getRoot());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog.setCanceledOnTouchOutside(true);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(layoutParams);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);

        HashMap<String, String> data = new HashMap<>();
        data.put("userId", userId);

        dialogGiftBinding.trickTv.setTextColor(Color.WHITE);
        dialogGiftBinding.privilegeTv.setTextColor(Color.GRAY);
        dialogGiftBinding.soundTv.setTextColor(Color.WHITE);
        dialogGiftBinding.eventTv.setTextColor(Color.WHITE);

        dialogGiftBinding.trickLine.setBackgroundColor(Color.WHITE);
        dialogGiftBinding.privilegelLine.setBackgroundColor(Color.GRAY);
        dialogGiftBinding.soundLine.setBackgroundColor(Color.WHITE);
        dialogGiftBinding.eventLine.setBackgroundColor(Color.WHITE);

        callViewModel.getUserCoinModelLiveData(this,AppConstants.USER_ID).observe(this, new Observer<GetUserCoinModel>() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onChanged(GetUserCoinModel getUserCoinModel) {
                if (getUserCoinModel !=null){
                    if (getUserCoinModel.getSuccess().equalsIgnoreCase("1")){
                        dialogGiftBinding.textCoin.setText(getUserCoinModel.getDetails().getMyCoin());

//                        if (Integer.parseInt(getUserCoinModel.getDetails().getMyCoin()) < 1000) {
//                            dialogGiftBinding.textCoin.setText(getUserCoinModel.getDetails().getMyCoin());
//                        } else if (Integer.parseInt(getUserCoinModel.getDetails().getMyCoin()) < 1000000) {
//                            dialogGiftBinding.textCoin.setText(String.format("%.1fk", Integer.parseInt(getUserCoinModel.getDetails().getMyCoin()) / 1000.0));
//                        } else if (Integer.parseInt(getUserCoinModel.getDetails().getMyCoin()) < 1000000000) {
//                            dialogGiftBinding.textCoin.setText(String.format("%.1fm", Integer.parseInt(getUserCoinModel.getDetails().getMyCoin()) / 1000000.0));
//                        } else {
//                            dialogGiftBinding.textCoin.setText(String.format("%.1ft", Integer.parseInt(getUserCoinModel.getDetails().getMyCoin()) / 1000000000.0));
//                        }
                    }
                }
            }
        });


        callViewModel.giftModelLiveData(CallActivity.this,AppConstants.USER_ID).observe(CallActivity.this, new Observer<PrimeGiftModel>() {
            @Override
            public void onChanged(PrimeGiftModel primeGiftModel) {

                if (primeGiftModel.getSuccess().equalsIgnoreCase("1")) {
                    arrayList.add(primeGiftModel.getDetails());

                    privilegesList = primeGiftModel.getDetails().getPrivilege();
                    eventGiftList = primeGiftModel.getDetails().getEventGifts();
                    soundGiftList = primeGiftModel.getDetails().getSoundGifts();
                    trickList = primeGiftModel.getDetails().getTrick();

                    if (primeGiftModel.getDetails().getPrivilege().isEmpty()) {
                        dialogGiftBinding.noGiftTv.setVisibility(View.VISIBLE);
                        dialogGiftBinding.recyclerGift.setVisibility(View.GONE);
                    } else {
                        dialogGiftBinding.noGiftTv.setVisibility(View.GONE);
                        dialogGiftBinding.recyclerGift.setVisibility(View.VISIBLE);
                    }

                    PrimeGiftAdapter primeGiftAdapter = new PrimeGiftAdapter(CallActivity.this, privilegesList, new PrimeGiftAdapter.Select() {
                        @Override
                        public void details(Privilege detail) {
                            hitSendGift(detail.getImage(), userId, otherUserId, detail.getPrimeAccount(), detail.getId(), liveHostid,"2");
                            dialog.dismiss();
                        }
                    });
                    dialogGiftBinding.recyclerGift.setAdapter(primeGiftAdapter);
                }
            }
        });

        dialogGiftBinding.PrivilegeLlayout.setOnClickListener(view -> {
            dialogGiftBinding.trickTv.setTextColor(Color.WHITE);
            dialogGiftBinding.privilegeTv.setTextColor(Color.GRAY);
            dialogGiftBinding.soundTv.setTextColor(Color.WHITE);
            dialogGiftBinding.eventTv.setTextColor(Color.WHITE);

            dialogGiftBinding.trickLine.setBackgroundColor(Color.WHITE);
            dialogGiftBinding.privilegelLine.setBackgroundColor(Color.GRAY);
            dialogGiftBinding.soundLine.setBackgroundColor(Color.WHITE);
            dialogGiftBinding.eventLine.setBackgroundColor(Color.WHITE);

            if (privilegesList.isEmpty()) {
                dialogGiftBinding.noGiftTv.setVisibility(View.VISIBLE);
                dialogGiftBinding.recyclerGift.setVisibility(View.GONE);
            } else {
                dialogGiftBinding.noGiftTv.setVisibility(View.GONE);
                dialogGiftBinding.recyclerGift.setVisibility(View.VISIBLE);
            }

            PrimeGiftAdapter primeGiftAdapter = new PrimeGiftAdapter(CallActivity.this, privilegesList, new PrimeGiftAdapter.Select() {
                @Override
                public void details(Privilege detail) {
                    hitSendGift(detail.getImage(), userId, otherUserId, detail.getPrimeAccount(), detail.getId(), liveHostid,"2");
                    dialog.dismiss();
                }
            });
            dialogGiftBinding.recyclerGift.setAdapter(primeGiftAdapter);
        });
        dialogGiftBinding.trickLlayout.setOnClickListener(view -> {

            dialogGiftBinding.trickTv.setTextColor(Color.GRAY);
            dialogGiftBinding.privilegeTv.setTextColor(Color.WHITE);
            dialogGiftBinding.soundTv.setTextColor(Color.WHITE);
            dialogGiftBinding.eventTv.setTextColor(Color.WHITE);

            dialogGiftBinding.trickLine.setBackgroundColor(Color.GRAY);
            dialogGiftBinding.privilegelLine.setBackgroundColor(Color.WHITE);
            dialogGiftBinding.soundLine.setBackgroundColor(Color.WHITE);
            dialogGiftBinding.eventLine.setBackgroundColor(Color.WHITE);
           try {
               if (trickList.isEmpty()) {
                   dialogGiftBinding.noGiftTv.setVisibility(View.VISIBLE);
                   dialogGiftBinding.recyclerGift.setVisibility(View.GONE);
               } else {
                   dialogGiftBinding.noGiftTv.setVisibility(View.GONE);
                   dialogGiftBinding.recyclerGift.setVisibility(View.VISIBLE);
               }
           }catch (Exception e){


           }
            TrickGiftRVAdapter trickGiftRVAdapter = new TrickGiftRVAdapter(CallActivity.this, trickList, new TrickGiftRVAdapter.Select() {
                @Override
                public void details(Trick detail) {
                    hitSendGift(detail.getImage(), userId, otherUserId, detail.getPrimeAccount(), detail.getId(), liveHostid,"2");
                    dialog.dismiss();
                }
            });
            dialogGiftBinding.recyclerGift.setAdapter(trickGiftRVAdapter);
        });
        dialogGiftBinding.soundGiftsLlayout.setOnClickListener(view -> {
            dialogGiftBinding.trickTv.setTextColor(Color.WHITE);
            dialogGiftBinding.privilegeTv.setTextColor(Color.WHITE);
            dialogGiftBinding.soundTv.setTextColor(Color.GRAY);
            dialogGiftBinding.eventTv.setTextColor(Color.WHITE);

            dialogGiftBinding.trickLine.setBackgroundColor(Color.WHITE);
            dialogGiftBinding.privilegelLine.setBackgroundColor(Color.WHITE);
            dialogGiftBinding.soundLine.setBackgroundColor(Color.GRAY);
            dialogGiftBinding.eventLine.setBackgroundColor(Color.WHITE);

            if (soundGiftList.isEmpty()) {
                dialogGiftBinding.noGiftTv.setVisibility(View.VISIBLE);
                dialogGiftBinding.recyclerGift.setVisibility(View.GONE);
            } else {
                dialogGiftBinding.noGiftTv.setVisibility(View.GONE);
                dialogGiftBinding.recyclerGift.setVisibility(View.VISIBLE);
            }
            SoundGiftRVAdapter soundGiftRVAdapter = new SoundGiftRVAdapter(CallActivity.this, soundGiftList, new SoundGiftRVAdapter.Select() {
                @Override
                public void details(SoundGift detail) {
                    hitSendGift(detail.getImage(), userId, otherUserId, detail.getPrimeAccount(), detail.getId(), liveHostid,"1");
                    dialog.dismiss();
                }
            });

            dialogGiftBinding.recyclerGift.setAdapter(soundGiftRVAdapter);
        });
        dialogGiftBinding.eventsGiftsLlayout.setOnClickListener(view -> {
            dialogGiftBinding.trickTv.setTextColor(Color.WHITE);
            dialogGiftBinding.privilegeTv.setTextColor(Color.WHITE);
            dialogGiftBinding.soundTv.setTextColor(Color.WHITE);
            dialogGiftBinding.eventTv.setTextColor(Color.GRAY);

            dialogGiftBinding.trickLine.setBackgroundColor(Color.WHITE);
            dialogGiftBinding.privilegelLine.setBackgroundColor(Color.WHITE);
            dialogGiftBinding.soundLine.setBackgroundColor(Color.WHITE);
            dialogGiftBinding.eventLine.setBackgroundColor(Color.GRAY);

            if (eventGiftList.isEmpty()) {
                dialogGiftBinding.noGiftTv.setVisibility(View.VISIBLE);
                dialogGiftBinding.recyclerGift.setVisibility(View.GONE);
            } else {
                dialogGiftBinding.noGiftTv.setVisibility(View.GONE);
                dialogGiftBinding.recyclerGift.setVisibility(View.VISIBLE);
            }
            EventGiftRVAdapter eventGiftRVAdapter = new EventGiftRVAdapter(CallActivity.this, eventGiftList, new EventGiftRVAdapter.Select() {
                @Override
                public void details(EventGift detail) {
                    hitSendGift(detail.getImage(), userId, otherUserId, detail.getPrimeAccount(), detail.getId(), liveHostid,"2");
                    dialog.dismiss();
                }
            });
            dialogGiftBinding.recyclerGift.setAdapter(eventGiftRVAdapter);
        });
        dialog.show();
    }



    private void hitSendGift(String giftImage, String otherUserId, String userId, String primeAccount, String id, String liveHostid,String giftType) {
        sendGiftToParticularUser(giftImage, otherUserId, userId, primeAccount, id, liveHostid,giftType);
    }

    private void openDialogUsersLiveJoined(GiftsListModel.Detail detail) {
        BottomSheetDialog sendGiftUsersDialog = new BottomSheetDialog(this);
        DialogLiveUserJoinedBinding liveUserJoinedBinding = DialogLiveUserJoinedBinding.inflate(LayoutInflater.from(this));
        sendGiftUsersDialog.setContentView(liveUserJoinedBinding.getRoot());
        userJoinedAdapter = new UserJoinedAdapter(this, liveJoinedUserList, new UserJoinedAdapter.Click() {
            @Override
            public void setOnSendGiftListener(GoLiveModelClass goLiveModelClass) {
                sendGiftUsersDialog.dismiss();
//                sendGiftFirebase(detail);
            }
        });

        liveUserJoinedBinding.recyclerRequestMultiLive.setAdapter(userJoinedAdapter);
        sendGiftUsersDialog.show();
    }

    private void sendFlyingHeartInFirebase() {
//for position
        int size = drawableIds.length;
        final int min = 0;
        final int max = size - 1;
        final int random = new Random().nextInt((max - min) + 1) + min;

//for update listner
        final int minR = 0;
        final int maxR = 1000;
        final int randomR = new Random().nextInt((maxR - minR) + 1) + minR;

        HeartModel heartModel = new HeartModel();
        heartModel.setPosition(String.valueOf(random));
        heartModel.setRandom(String.valueOf(randomR));
        ref.child(otherUserId).child(liveType).child(otherUserId).child("flyingHeart").setValue(heartModel);

    }

    GoLiveModelClass goLiveModelClass;

    private void openOtherUserProfile(@NotNull GoLiveModelClass goLiveModelClass, int userOrAdmin) {

        this.goLiveModelClass = goLiveModelClass;
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        ProfileBottomSheetBinding profileBottomSheetBinding = ProfileBottomSheetBinding.inflate(LayoutInflater.from(this));
        bottomSheetDialog.setContentView(profileBottomSheetBinding.getRoot());
        bottomSheetDialog.show();
        profileBottomSheetBinding.sendGiftsBottomSheetLl.setOnClickListener(view -> openGiftDialog());

        profileBottomSheetBinding.inviteUserLineayout.setOnClickListener(view -> inviteAudienceRef.child(liveHostBackImg).child(goLiveModelClass.getUserID()).setValue("0"));

        profileBottomSheetBinding.otherUserDialogOpenProfileRL.setOnClickListener(view -> {
            App.getSharedpref().saveString("userCheck", goLiveModelClass.getUserID());
            setPIPScreen();

        });

        if (goLiveModelClass.getUserID().equalsIgnoreCase(AppConstants.USER_ID)) {
            profileBottomSheetBinding.blockUserImg.setVisibility(View.GONE);
        } else {
            profileBottomSheetBinding.blockUserImg.setVisibility(View.VISIBLE);
        }


        if (userOrAdmin == 0) {

            Toast.makeText(CallActivity.this, "0 admin 1", Toast.LENGTH_SHORT).show();
            if (goLiveModelClass.getUserID().equalsIgnoreCase(AppConstants.USER_ID)) {

                profileBottomSheetBinding.liveProfileAdminLyout.setVisibility(View.GONE);
                profileBottomSheetBinding.profileBottomFollowingRL.setVisibility(View.GONE);
                profileBottomSheetBinding.profileBottomReminderLLayout.setVisibility(View.GONE);

                if (goLiveModelClass.getUserID().equalsIgnoreCase(AppConstants.USER_ID) && liveHostBackImg.equalsIgnoreCase(AppConstants.USER_ID)) {
                    profileBottomSheetBinding.adminIconImg.setVisibility(View.VISIBLE);
                    profileBottomSheetBinding.adminIconImg.setImageResource(R.drawable.host_icon);
                }

//                Toast.makeText(CallActivity.this, "0 admin 2", Toast.LENGTH_SHORT).show();
            } else {
                profileBottomSheetBinding.liveProfileAdminLyout.setVisibility(View.GONE);

//                Toast.makeText(CallActivity.this, "0 admin 3", Toast.LENGTH_SHORT).show();
                if (!goLiveModelClass.getUserID().equalsIgnoreCase(AppConstants.USER_ID) && liveHostBackImg.equalsIgnoreCase(goLiveModelClass.getUserID())) {
                    profileBottomSheetBinding.liveProfileAdminLyout.setVisibility(View.GONE);

//                    Toast.makeText(CallActivity.this, "0 admin 33", Toast.LENGTH_SHORT).show();
                    profileBottomSheetBinding.adminIconImg.setVisibility(View.VISIBLE);
                    profileBottomSheetBinding.adminIconImg.setImageResource(R.drawable.host_icon);

                } else if (liveHostBackImg.equalsIgnoreCase(AppConstants.USER_ID)) {

                    profileBottomSheetBinding.liveProfileAdminLyout.setVisibility(View.VISIBLE);

//                    Toast.makeText(CallActivity.this, "0 admin 333", Toast.LENGTH_SHORT).show();

                    adminLiveRef.child(liveHostBackImg).child(goLiveModelClass.getUserID()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
//                                Toast.makeText(CallActivity.this, "0 admin 4", Toast.LENGTH_SHORT).show();
                                if (liveHostBackImg.equalsIgnoreCase(AppConstants.USER_ID)) {
                                    profileBottomSheetBinding.liveProfileBottomAdminLlyout.setVisibility(View.GONE);
                                    profileBottomSheetBinding.adminStatusTv.setVisibility(View.VISIBLE);

                                    profileBottomSheetBinding.adminIconImg.setVisibility(View.VISIBLE);
                                    profileBottomSheetBinding.adminIconImg.setImageResource(R.drawable.live_admin_icon);
                                } else {
                                    profileBottomSheetBinding.adminStatusTv.setVisibility(View.VISIBLE);

                                    profileBottomSheetBinding.adminIconImg.setVisibility(View.VISIBLE);
                                    profileBottomSheetBinding.adminIconImg.setImageResource(R.drawable.live_admin_icon);
                                }
                            } else {
                                profileBottomSheetBinding.liveProfileAdminLyout.setVisibility(View.VISIBLE);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });

                } else if (!goLiveModelClass.getUserID().equalsIgnoreCase(AppConstants.USER_ID)) {

                    Toast.makeText(CallActivity.this, "0 admin 5", Toast.LENGTH_SHORT).show();
                    profileBottomSheetBinding.liveProfileAdminLyout.setVisibility(View.GONE);

                    adminLiveRef.child(liveHostBackImg).child(goLiveModelClass.getUserID()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                if (goLiveModelClass.getUserID().equalsIgnoreCase(AppConstants.USER_ID)) {
                                    profileBottomSheetBinding.adminStatusTv.setVisibility(View.VISIBLE);
                                    profileBottomSheetBinding.adminIconImg.setVisibility(View.VISIBLE);
                                    profileBottomSheetBinding.adminIconImg.setImageResource(R.drawable.live_admin_icon);
                                } else {
                                    profileBottomSheetBinding.adminStatusTv.setVisibility(View.VISIBLE);
                                    profileBottomSheetBinding.adminIconImg.setVisibility(View.VISIBLE);
                                    profileBottomSheetBinding.adminIconImg.setImageResource(R.drawable.live_admin_icon);
                                }
                            } else {
                                profileBottomSheetBinding.liveProfileAdminLyout.setVisibility(View.GONE);
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });

                } else {
                    profileBottomSheetBinding.liveProfileAdminLyout.setVisibility(View.VISIBLE);
                    Toast.makeText(CallActivity.this, "0 admin 6", Toast.LENGTH_SHORT).show();
                }
            }

        } else {
            if (adminStatus == 1) {
                Toast.makeText(CallActivity.this, "1 admin 7", Toast.LENGTH_SHORT).show();
                profileBottomSheetBinding.adminStatusTv.setVisibility(View.VISIBLE);

                profileBottomSheetBinding.adminIconImg.setVisibility(View.VISIBLE);
                profileBottomSheetBinding.adminIconImg.setImageResource(R.drawable.live_admin_icon);

                profileBottomSheetBinding.liveProfileBottomAdminLlyout.setVisibility(View.GONE);

                if (goLiveModelClass.getUserID().equalsIgnoreCase(AppConstants.USER_ID)) {

                    profileBottomSheetBinding.liveProfileAdminLyout.setVisibility(View.GONE);
                    profileBottomSheetBinding.profileBottomFollowingRL.setVisibility(View.GONE);
                    profileBottomSheetBinding.profileBottomReminderLLayout.setVisibility(View.GONE);
//                    Toast.makeText(CallActivity.this, "admin1 show", Toast.LENGTH_SHORT).show();

                } else {

                    profileBottomSheetBinding.liveProfileAdminLyout.setVisibility(View.VISIBLE);
                    profileBottomSheetBinding.liveProfileBottomAdminLlyout.setVisibility(View.GONE);

                    adminLiveRef.child(liveHostBackImg).child(goLiveModelClass.getUserID()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                if (liveHostBackImg.equalsIgnoreCase(AppConstants.USER_ID)) {
//                                    Toast.makeText(CallActivity.this, "test1", Toast.LENGTH_SHORT).show();
                                    profileBottomSheetBinding.liveProfileBottomAdminLlyout.setVisibility(View.GONE);
                                    profileBottomSheetBinding.adminStatusTv.setVisibility(View.VISIBLE);
                                } else if (goLiveModelClass.getUserID().equalsIgnoreCase(AppConstants.USER_ID)) {

//                                    Toast.makeText(CallActivity.this, "test2", Toast.LENGTH_SHORT).show();
                                    profileBottomSheetBinding.adminStatusTv.setVisibility(View.VISIBLE);
                                    profileBottomSheetBinding.liveProfileAdminLyout.setVisibility(View.GONE);

                                } else if (!goLiveModelClass.getUserID().equalsIgnoreCase(AppConstants.USER_ID)) {
//                                    Toast.makeText(CallActivity.this, "test3", Toast.LENGTH_SHORT).show();
                                    profileBottomSheetBinding.adminStatusTv.setVisibility(View.VISIBLE);
                                    profileBottomSheetBinding.liveProfileAdminLyout.setVisibility(View.GONE);
                                } else {
//                                    Toast.makeText(CallActivity.this, "test4", Toast.LENGTH_SHORT).show();
                                    profileBottomSheetBinding.adminStatusTv.setVisibility(View.VISIBLE);
                                    profileBottomSheetBinding.liveProfileAdminLyout.setVisibility(View.GONE);
                                }
                            } else {
//                                Toast.makeText(CallActivity.this, "test5", Toast.LENGTH_SHORT).show();

                                if (goLiveModelClass.getUserID().equalsIgnoreCase(liveHostBackImg)) {
                                    profileBottomSheetBinding.liveProfileBottomAdminLlyout.setVisibility(View.GONE);
                                    profileBottomSheetBinding.liveProfileAdminLyout.setVisibility(View.GONE);
                                    profileBottomSheetBinding.adminStatusTv.setVisibility(View.GONE);
//                                    Toast.makeText(CallActivity.this, "test8", Toast.LENGTH_SHORT).show();
                                } else {
                                    profileBottomSheetBinding.liveProfileBottomAdminLlyout.setVisibility(View.GONE);
                                    profileBottomSheetBinding.liveProfileAdminLyout.setVisibility(View.VISIBLE);
                                    profileBottomSheetBinding.adminStatusTv.setVisibility(View.GONE);
//                                    Toast.makeText(CallActivity.this, "test9", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
//                    Toast.makeText(CallActivity.this, "admin2 show", Toast.LENGTH_SHORT).show();

                    if (goLiveModelClass.getUserID().equalsIgnoreCase(liveHostBackImg)) {
                        profileBottomSheetBinding.liveProfileAdminLyout.setVisibility(View.GONE);
//                        Toast.makeText(this, "test6", Toast.LENGTH_SHORT).show();
                    } else {
                        profileBottomSheetBinding.liveProfileBottomAdminLlyout.setVisibility(View.GONE);
//                        Toast.makeText(this, "test7", Toast.LENGTH_SHORT).show();
                    }
                }

            } else {
//                Toast.makeText(this, "2 admin", Toast.LENGTH_SHORT).show();
                profileBottomSheetBinding.liveProfileAdminLyout.setVisibility(View.GONE);

                if (goLiveModelClass.getUserID().equalsIgnoreCase(liveHostBackImg)) {
                    profileBottomSheetBinding.profileBottomFollowingRL.setVisibility(View.GONE);
                    profileBottomSheetBinding.profileBottomReminderLLayout.setVisibility(View.GONE);
//                    Toast.makeText(CallActivity.this, "admin3 show", Toast.LENGTH_SHORT).show();
                } else {

                    profileBottomSheetBinding.profileBottomFollowingRL.setVisibility(View.VISIBLE);
                    profileBottomSheetBinding.profileBottomReminderLLayout.setVisibility(View.VISIBLE);

//                    Toast.makeText(CallActivity.this, "admin4 show", Toast.LENGTH_SHORT).show();
                    profileBottomSheetBinding.adminStatusTv.setVisibility(View.VISIBLE);
                }
            }


            // for mute the user mic
//            setMuteUnMuteUser(GoLiveModelClass goLiveModelClass, String userId);
        }

        banChatRef.child(liveHostBackImg).child(goLiveModelClass.getUserID()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    int chatBanStatus = Integer.parseInt(snapshot.getValue().toString());
                    if (chatBanStatus == 0) {
                        profileBottomSheetBinding.banChatTv.setText("Chat");
                    } else {
                        profileBottomSheetBinding.banChatTv.setText("Banned");
                    }
                    profileBottomSheetBinding.liveProfileChatLyout.setOnClickListener(view -> {
                        if (chatBanStatus == 1) {
                            banChatRef.child(liveHostBackImg).child(goLiveModelClass.getUserID()).setValue("0");
                            profileBottomSheetBinding.banChatTv.setText("Chat");
                            Toast.makeText(CallActivity.this, "UnBanned Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            banChatDialogbox(goLiveModelClass, profileBottomSheetBinding.banChatTv);
                        }
                    });

                } else {
                    profileBottomSheetBinding.liveProfileChatLyout.setOnClickListener(view -> banChatDialogbox(goLiveModelClass, profileBottomSheetBinding.banChatTv));
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        profileBottomSheetBinding.blockUserImg.setOnClickListener(view -> blockUserAlertBox(goLiveModelClass));
        profileBottomSheetBinding.txtName.setText(goLiveModelClass.getName());
//        profileBottomSheetBinding.txtUserName.setText(goLiveModelClass.getUserName());
        getUserDetailApi(goLiveModelClass.getUserID(), profileBottomSheetBinding.liveIdProfileFollowTv,
                profileBottomSheetBinding.userIdAndCountry, profileBottomSheetBinding.followUnfollowImg,
                profileBottomSheetBinding.bottomProfileAgeTv, profileBottomSheetBinding.bottomProfileGenderImg,
                profileBottomSheetBinding.anchorimg,profileBottomSheetBinding.vipImage,profileBottomSheetBinding.genderLayout,
                profileBottomSheetBinding.sendingLayout,profileBottomSheetBinding.receivingLayout,profileBottomSheetBinding.sendingLvl,
                profileBottomSheetBinding.receivingLvl,profileBottomSheetBinding.lvlimg,profileBottomSheetBinding.receivingBottomSheetRL);

        getAppliedFrameApi(goLiveModelClass.getUserID(), profileBottomSheetBinding.liveBottomProfieFrame);

        profileBottomSheetBinding.liveProfileKickOuttLyout.setOnClickListener(view -> openKickDialog(goLiveModelClass));

        if (muteUsers.contains(goLiveModelClass.getUserID())) {
            profileBottomSheetBinding.txtMute.setText("UnMute");
        } else {
            profileBottomSheetBinding.txtMute.setText("Mute");
        }
        profileBottomSheetBinding.followUnfollowImg.setOnClickListener(view -> {
            if (goLiveModelClass.getUserID().equalsIgnoreCase(AppConstants.USER_ID)) {
                Toast.makeText(CallActivity.this, "You can't follow yourself", Toast.LENGTH_SHORT).show();
            } else {
                followUnfollowUser(goLiveModelClass.getUserID(), profileBottomSheetBinding.followUnfollowImg, profileBottomSheetBinding.liveIdProfileFollowTv);
            }

        });
        profileBottomSheetBinding.liveProfileBottomAdminLlyout.setOnClickListener(view -> {

            AdminFirebaseRoot adminFirebaseRoot = new AdminFirebaseRoot("1", goLiveModelClass.getName(),
                    goLiveModelClass.getImage(), goLiveModelClass.getUserID());

            if (adminList == null || adminList.size() == 0) {

                adminLiveRef.child(liveHostBackImg).child(goLiveModelClass.getUserID()).setValue(adminFirebaseRoot);
                ref.child(otherUserId).child(liveType).child(otherUserId).child("viewer List").child(goLiveModelClass.getUserID()).child("adminStatus").setValue(true);

                Toast.makeText(CallActivity.this, "Added", Toast.LENGTH_SHORT).show();
            } else {

                for (int i = 0; i < adminList.size(); i++) {
                    if (adminList.get(i).getAdminId().equalsIgnoreCase(goLiveModelClass.getUserID())) {
                        Toast.makeText(CallActivity.this, " already admin ", Toast.LENGTH_SHORT).show();
                    } else {
                        adminLiveRef.child(liveHostBackImg).child(goLiveModelClass.getUserID()).setValue(adminFirebaseRoot);
                        ref.child(otherUserId).child(liveType).child(otherUserId).child("viewer List").child(goLiveModelClass.getUserID()).child("adminStatus").setValue(true);
                        Toast.makeText(CallActivity.this, "Added", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        Glide.with(this).load(goLiveModelClass.getImage()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable @org.jetbrains.annotations.Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                profileBottomSheetBinding.progress.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                profileBottomSheetBinding.progress.setVisibility(View.GONE);
                return false;
            }
        }).into(profileBottomSheetBinding.imgProfile);

        profileBottomSheetBinding.llMute1.setOnClickListener(view -> {
            if (muteUsers.contains(goLiveModelClass.getUserID())) {
                GoLiveModelClass goLiveModelClass1 = new GoLiveModelClass();
                goLiveModelClass1.setUserID("-1");
                setMuteUnMuteUser(goLiveModelClass1, goLiveModelClass.getUserID());
            } else {
                setMuteUnMuteUser(goLiveModelClass, goLiveModelClass.getUserID());
            }
            bottomSheetDialog.dismiss();
        });

        profileBottomSheetBinding.llBan.setOnClickListener(view -> {
            setBannedUser(goLiveModelClass);
            bottomSheetDialog.dismiss();
        });

        profileBottomSheetBinding.llProfile.setOnClickListener(view -> {
            Toast.makeText(this, "coming soon", Toast.LENGTH_SHORT).show();

        });

        profileBottomSheetBinding.llBlock.setOnClickListener(view -> {
            Toast.makeText(this, "coming soon", Toast.LENGTH_SHORT).show();

        });


    }

    private void blockUserAlertBox(@NotNull GoLiveModelClass goLiveModelClass) {

        AlertDialog.Builder builder = new AlertDialog.Builder(CallActivity.this);
        builder.setMessage("Are sure you want to block this user ?");
        builder.setTitle("Alert");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            apiUserBlock(dialog, goLiveModelClass.getUserID());
        });

        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void banChatDialogbox(GoLiveModelClass goLiveModelClass, TextView banChatTv) {
        final Dialog dialog = new Dialog(CallActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.ban_chat_dialog_box);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(layoutParams);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);

        dialog.show();

        TextView banChatUserNameTv = dialog.findViewById(R.id.banChatUserNameTv);
        AppCompatButton banChatCancelBtn = dialog.findViewById(R.id.banChatCancelBtn);
        AppCompatButton banChatConfirmBtn = dialog.findViewById(R.id.banChatConfirmBtn);
        banChatUserNameTv.setText("After the ban, the other person won\\'t be able to send messages. Do you confirm banning " + goLiveModelClass.getName() + " ?");
        banChatConfirmBtn.setOnClickListener(view -> {

            banChatRef.child(liveHostBackImg).child(goLiveModelClass.getUserID()).setValue("1");
            banChatTv.setText("banned");
            dialog.dismiss();
        });
        banChatCancelBtn.setOnClickListener(view -> dialog.dismiss());

    }

    private void apiUserBlock(DialogInterface builder, String blockUserId) {
        callViewModel.blockUser(CallActivity.this, AppConstants.USER_ID, blockUserId).observe(CallActivity.this, new Observer<GetInvitationsRoot>() {
            @Override
            public void onChanged(GetInvitationsRoot getInvitationsRoot) {
                if (getInvitationsRoot != null) {
                    if (getInvitationsRoot.getStatus() == 1) {
                        Toast.makeText(CallActivity.this, "" + getInvitationsRoot.getMessage(), Toast.LENGTH_SHORT).show();
                        builder.cancel();
                    } else {
                        Toast.makeText(CallActivity.this, "" + getInvitationsRoot.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CallActivity.this, "Root is null", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void followUnfollowUser(String ohterUserId, ImageView followingImg, TextView othrUsrFollowingTV) {

        if (!ohterUserId.equalsIgnoreCase(AppConstants.USER_ID)) {
            callViewModel.followUsers(CallActivity.this, AppConstants.USER_ID, ohterUserId,"follow").observe(CallActivity.this, new Observer<SendOtpRoot>() {
                @Override
                public void onChanged(SendOtpRoot sendOtpRoot) {
                    if (sendOtpRoot.getStatus().equalsIgnoreCase("1")) {
                        if (j == 2) {
                            followingImg.setImageResource(R.drawable.ic_minus__2_);
                            followingImg.setImageTintList(ColorStateList.valueOf(Color.BLACK));
                            othrUsrFollowingTV.setText("Following");
                            j = 1;
                        } else {
                            j = 2;
                            followingImg.setImageTintList(ColorStateList.valueOf(Color.BLACK));
                            followingImg.setImageResource(R.drawable.ic_plus__6_);
                            othrUsrFollowingTV.setText("Follow");
                        }
                    } else {
                    }
                }
            });
        } else {
        }

    }

    private void setMuteUnMuteUser(GoLiveModelClass goLiveModelClass, String userId) {
        ref.child(otherUserId).child(liveType).child(otherUserId).child("muteUsers").child(userId).setValue(goLiveModelClass);
    }

    private void setBannedUser(GoLiveModelClass goLiveModelClass) {
        ref.child(otherUserId).child(liveType).child(otherUserId).child("banUser").child(goLiveModelClass.getUserID()).setValue(goLiveModelClass);
    }

    private void getViewerListFirebase() {
        ref.child(otherUserId).child(liveType).child(otherUserId).child("viewer List").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                try {
                    if (snapshot.exists()) {
                        viewerList.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                        try {
                            GoLiveModelClass goLiveModelClass = dataSnapshot.getValue(GoLiveModelClass.class);
                            if (goLiveModelClass.isLive()) {
                                liveUsersCount = String.valueOf(snapshot.getChildrenCount());
                                Toast.makeText(CallActivity.this, "admin or not " + goLiveModelClass.isAdminStatus(), Toast.LENGTH_SHORT).show();
//                            host on first position
                                if (goLiveModelClass.getUserID().equalsIgnoreCase(liveHostBackImg)) {
                                    viewerList.add(0, goLiveModelClass);
                                } else if (goLiveModelClass.isAdminStatus()) {
                                    //add admin on top
                                    if (viewerList.size() > 0) {
                                        viewerList.add(1, goLiveModelClass);
                                    } else {
                                        viewerList.add(goLiveModelClass);
                                    }

                                } else {
                                    // All members
                                    viewerList.add(goLiveModelClass);
                                }
                                //this is for sending gift to itself
                                if (goLiveModelClass.getUserID().equalsIgnoreCase(liveHostBackImg)) {
                                    liveJoinedUserList.clear();
                                    liveJoinedUserList.add(0, goLiveModelClass);
                                }
                                //   binding.txtUserHostName.setText(goLiveModelClass.getName());
                                if (liveHostBackImg.equalsIgnoreCase(AppConstants.USER_ID)) {
                                    binding.hostNameTv.setText(viewerList.get(0).getName());
                                    binding.txtUserHostName.setText(viewerList.get(viewerList.size()-1).getName());
                                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_left);
                                    binding.animationRL.startAnimation(animation);

                                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) binding.rlGustHost.getLayoutParams();
                                    params.topMargin = 40;

                                    final Handler handler = new Handler(Looper.getMainLooper());
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            binding.animationRL.setVisibility(View.GONE);
                                            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) binding.rlGustHost.getLayoutParams();
                                            params.topMargin = 80;
                                        }
                                    }, 5000);
                                    binding.animationRL.setVisibility(View.VISIBLE);
                                } else {

                                    if (Integer.parseInt(liveUsersCount)>0){

                                        binding.hostNameTv.setText(viewerList.get(0).getName());

                                        binding.txtUserHostName.setText(viewerList.get(viewerList.size()-1).getName());

                                        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_left);
                                        binding.animationRL.startAnimation(animation);

                                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) binding.rlGustHost.getLayoutParams();
                                        params.topMargin = 40;

                                        final Handler handler = new Handler(Looper.getMainLooper());
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                binding.animationRL.setVisibility(View.GONE);
                                                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) binding.rlGustHost.getLayoutParams();
                                                params.topMargin = 80;
                                            }
                                        }, 5000);
                                        binding.animationRL.setVisibility(View.VISIBLE);
                                    }
                                }
                            }
                        }
                        viewerAdapter.notifyDataSetChanged();
                        try {
                            binding.recyclerViewers.scrollToPosition(viewerList.size()-1);
                            binding.liveUsersTV.setText(liveUsersCount);
                            if (Integer.parseInt(liveUsersCount)>0){
                                String entryEffect= viewerList.get(viewerList.size()-1).getEntryEffect();
                                SVGAParser parser = new SVGAParser(CallActivity.this);
//                                if (!AppConstants.USER_ID.equalsIgnoreCase(userIdd)){

                                try { // new URL needs try catch.
                                    parser.decodeFromURL(new URL(entryEffect), new SVGAParser.ParseCompletion() {
                                        @Override
                                        public void onComplete(@NotNull SVGAVideoEntity videoItem) {
                                            SVGADynamicEntity dynamicEntity = new SVGADynamicEntity();
                                            dynamicEntity.setDynamicImage(entryEffect, "99"); // Here is the KEY implementation.
                                            SVGADrawable drawable = new SVGADrawable(videoItem, dynamicEntity);
                                            binding.muCarsRVImage.setImageDrawable(drawable);
                                            binding.muCarsRVImage.setVisibility(View.VISIBLE);
                                            binding.muCarsRVImage.setLoops(1);

                                            binding.muCarsRVImage.setCallback(new SVGACallback() {
                                                @Override
                                                public void onPause() {

                                                }

                                                @Override
                                                public void onFinished() {
                                                    binding.muCarsRVImage.stopAnimation();
                                                    binding.muCarsRVImage.setVisibility(View.GONE);
                                                    binding.muCarsRVImage.setVisibility(View.INVISIBLE);
                                                }

                                                @Override
                                                public void onRepeat() {

                                                }

                                                @Override
                                                public void onStep(int i, double v) {

                                                }
                                            });

                                            binding.muCarsRVImage.startAnimation();

//                                            new Handler().postDelayed(new Runnable() {
//                                                @Override
//                                                public void run() {
//                                                    binding.muCarsRVImage.stopAnimation();
//                                                    binding.muCarsRVImage.setVisibility(View.GONE);
//                                                    binding.muCarsRVImage.setVisibility(View.INVISIBLE);
//
//                                                }
//                                            },8000);
                                        }

                                        @Override
                                        public void onError() {
                                            Toast.makeText(CallActivity.this, "error entry", Toast.LENGTH_SHORT).show();
                                        }
                                    }, null);
                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                }
                                //sumit entry
//                                    try {
//                                        parser.decodeFromURL(new URL(entryEffect), new SVGAParser.ParseCompletion() {
//                                            @Override
//                                            public void onComplete(@com.google.firebase.database.annotations.NotNull SVGAVideoEntity videoItem) {
//                                                SVGADrawable drawable = new SVGADrawable(videoItem);
//                                                binding.muCarsRVImage.setImageDrawable(drawable);
//                                                binding.muCarsRVImage.setVisibility(View.VISIBLE);
//                                                binding.muCarsRVImage.startAnimation();
//                                                new Handler().postDelayed(new Runnable() {
//                                                    @Override
//                                                    public void run() {
//                                                        binding.muCarsRVImage.stopAnimation();
//                                                        binding.muCarsRVImage.setVisibility(View.GONE);
//                                                        binding.muCarsRVImage.setVisibility(View.INVISIBLE);
//
//                                                    }
//                                                },8000);
//                                            }
//                                            @Override
//                                            public void onError() {
//                                                Toast.makeText(CallActivity.this, "error entry", Toast.LENGTH_SHORT).show();
//                                            }
//                                        }, new SVGAParser.PlayCallback() {
//                                            @Override
//                                            public void onPlay(@NonNull List<? extends File> list) {
//
//                                            }
//                                        });
//                                    } catch (MalformedURLException e) {
//                                        e.printStackTrace();
//                                    }
//                                }else {
//
//                                }

                            }else {
                            }
                        }catch (Exception e){

                        }
                        try {
                            binding.txtFollowers.setText("ID:" + viewerList.get(0).getUserName());
                        } catch (Exception e) {
                        }
                    }
                    else {
                        binding.liveUsersTV.setText(liveUsersCount);
                    }
                }catch (Exception e){

                }

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    private Long getCurrentTimeStamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp.getTime());
        return timestamp.getTime();
    }

    public static String getCurrentSecond() {
        //date output format
        DateFormat dateFormat = new SimpleDateFormat("ss");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

    public static String getCurrentMint() {
        //date output format
        DateFormat dateFormat = new SimpleDateFormat("mm");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

    private void sendCustomeMessage(String mess, String gift) {
        ChatMessageModel chatMessageModel = new ChatMessageModel();
        chatMessageModel.setGift(gift);
        chatMessageModel.setImage(userImage);
        chatMessageModel.setKey(ref.push().getKey());
        chatMessageModel.setMessage(mess);
        chatMessageModel.setName(userName);
        chatMessageModel.setUserId(userId);
        chatMessageModel.setAnnouncement("0");
        chatMessageModel.setTimeStamp(getCurrentTimeStamp());
        sendMessage(chatMessageModel, chatMessageModel.getKey());
    }

    private void sendJoinedMessage() {
        ChatMessageModel chatMessageModel = new ChatMessageModel();
        chatMessageModel.setGift("");
        chatMessageModel.setImage(userImage);
        chatMessageModel.setKey(ref.push().getKey());
        chatMessageModel.setMessage("joined Stream");
        chatMessageModel.setName(userName);
        chatMessageModel.setUserId(userId);
        chatMessageModel.setTimeStamp(getCurrentTimeStamp());
        sendMessage(chatMessageModel, chatMessageModel.getKey());
    }

    private void getCommentChatMessageFirebase() {

        ref.child(otherUserId).child(liveType).child(otherUserId).child("chat comments").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    chatMessages.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        ChatMessageModel chatMessageModel = dataSnapshot.getValue(ChatMessageModel.class);
                        if (chatMessageModel.getTimeStamp() != null) {
                            if (currentTimeStamp < chatMessageModel.getTimeStamp()) {
                                chatMessages.add(chatMessageModel);
                            }
                        } else {
                            ChatMessageModel chatMessageModels = new ChatMessageModel();
                            chatMessageModels.setGift("");
                            chatMessageModels.setImage(userImage);
                            chatMessageModels.setKey(ref.push().getKey());
                            chatMessageModels.setMessage("Welcome To WOW'S Live Stream");
                            chatMessageModels.setName(userName);
                            chatMessageModels.setUserId(userId);
//                          sendMessage(chatMessageModel, chatMessageModel.getKey());
                            chatMessages.add(chatMessageModels);
                        }
                    }

                    if (chatMessages!=null)
                    {
                        binding.recyclerAllMessage.scrollToPosition(chatMessages.size() - 1);
                    }
                    commentAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
            }
        });
    }

    private void sendWelcomeMessageFirebase() {
        ChatMessageModel chatMessageModel = new ChatMessageModel();
        chatMessageModel.setGift("");
        chatMessageModel.setImage(userImage);
        chatMessageModel.setKey(ref.push().getKey());
        chatMessageModel.setMessage("Welcome To World Of Wonders Socially Live Stream");
        chatMessageModel.setName(userName);
        chatMessageModel.setUserId(userId);

        sendMessage(chatMessageModel, chatMessageModel.getKey());
    }

    private void deleterLiveStreamViewers() {
        ref.child(otherUserId).child(liveType).child(otherUserId).child("viewer List").child(userId).child("live").setValue(false);
        ref.child(otherUserId).child(liveType).child(otherUserId).child("viewer List").child(userId).removeValue();
        sendCustomeMessage("Left stream", "");
    }

    private void setLiveStreamOffline() {
        HashMap<String, Boolean> data = new HashMap<>();
        data.put("live", false);
        ref.child(userId).child(liveType).child(userId).child("hostLiveInfo").setValue(data);
        ref.child(userId).child(liveType).removeValue();

        App.getSharedpref().saveString("liveCoverImg", "");
        App.getSharedpref().saveString("liveTopic", "");

    }

    private void onBigVideoViewClicked(View view, int position) {
        log.debug("onItemClick " + view + " " + position + " " + mLayoutType);
        toggleFullscreen();
    }

    private void onBigVideoViewDoubleClicked(View view, int position) {
        log.debug("onItemDoubleClick " + view + " " + position + " " + mLayoutType);

        if (mUidsList.size() < 2) {
            return;
        }
        UserStatusData user = mGridVideoViewContainer.getItem(position);
        int uid = (user.mUid == 0) ? config().mUid : user.mUid;

        if (mLayoutType == LAYOUT_TYPE_DEFAULT && mUidsList.size() != 1) {
            switchToSmallVideoView(uid);
        } else {
            switchToDefaultVideoView();
        }
    }

    private void onSmallVideoViewDoubleClicked(View view, int position) {
        log.debug("onItemDoubleClick small " + view + " " + position + " " + mLayoutType);
        switchToDefaultVideoView();
    }

    private void showOrHideStatusBar(boolean hide) {
        // May fail on some kinds of devices
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            View decorView = getWindow().getDecorView();
            int uiOptions = decorView.getSystemUiVisibility();

            if (hide) {
                uiOptions |= View.SYSTEM_UI_FLAG_FULLSCREEN;
            } else {
                uiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
            }
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    private void toggleFullscreen() {
        mFullScreen = !mFullScreen;
        showOrHideCtrlViews(mFullScreen);
        mUIHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showOrHideStatusBar(mFullScreen);
            }
        }, 200); // action bar fade duration
    }

    private void showOrHideCtrlViews(boolean hide) {
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            if (hide) {
                ab.hide();
            } else {
                ab.show();
            }
        }
    }

    private void optional() {
        setVolumeControlStream(AudioManager.STREAM_VOICE_CALL);
    }

    private void optionalDestroy() {
    }

    private int getVideoEncResolutionIndex() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int videoEncResolutionIndex = pref.getInt(ConstantApp.PrefManager.PREF_PROPERTY_VIDEO_ENC_RESOLUTION, ConstantApp.DEFAULT_VIDEO_ENC_RESOLUTION_IDX);
        if (videoEncResolutionIndex > ConstantApp.VIDEO_DIMENSIONS.length - 1) {
            videoEncResolutionIndex = ConstantApp.DEFAULT_VIDEO_ENC_RESOLUTION_IDX;
            // save the new value
            SharedPreferences.Editor editor = pref.edit();
            editor.putInt(ConstantApp.PrefManager.PREF_PROPERTY_VIDEO_ENC_RESOLUTION, videoEncResolutionIndex);
            editor.apply();
        }
        return videoEncResolutionIndex;
    }

    private int getVideoEncFpsIndex() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int videoEncFpsIndex = pref.getInt(ConstantApp.PrefManager.PREF_PROPERTY_VIDEO_ENC_FPS, ConstantApp.DEFAULT_VIDEO_ENC_FPS_IDX);
        if (videoEncFpsIndex > ConstantApp.VIDEO_FPS.length - 1) {
            videoEncFpsIndex = ConstantApp.DEFAULT_VIDEO_ENC_FPS_IDX;
            // save the new value
            SharedPreferences.Editor editor = pref.edit();
            editor.putInt(ConstantApp.PrefManager.PREF_PROPERTY_VIDEO_ENC_FPS, videoEncFpsIndex);
            editor.apply();
        }
        return videoEncFpsIndex;
    }

    private void doConfigEngine(String encryptionKey, String encryptionMode) {
        VideoEncoderConfiguration.VideoDimensions videoDimension = ConstantApp.VIDEO_DIMENSIONS[getVideoEncResolutionIndex()];
        VideoEncoderConfiguration.FRAME_RATE videoFps = ConstantApp.VIDEO_FPS[getVideoEncFpsIndex()];
        configEngine(videoDimension, videoFps, encryptionKey, encryptionMode);
    }

    public void onSwitchSpeakerClicked(View view) {
        RtcEngine rtcEngine = rtcEngine();
        /*
          Enables/Disables the audio playback route to the speakerphone.
          This method sets whether the audio is routed to the speakerphone or earpiece.
          After calling this method, the SDK returns the onAudioRouteChanged callback
          to indicate the changes.
         */
        rtcEngine.setEnableSpeakerphone(mAudioRouting != Constants.AUDIO_ROUTE_SPEAKERPHONE);
    }

    @Override
    protected void deInitUIandEvent() {
        if (!liveStatus.equalsIgnoreCase("host")) {
            setLiveStreamOffline();
//          hitEndLiveApi(createLiveHistory.getDetails().getId(), getCurrentTime());
        } else {
            setViewerExitInMultiLive("5");
        }
        if (isLiveConnected) {
            setViewerExitInMultiLive("2");
        }
        deleterLiveStreamViewers();
        optionalDestroy();
        doLeaveChannel();
        removeEventHandler(this);
        mUidsList.clear();

    }

    private void setViewerExitInMultiLive(String status) {
        GoLiveModelClass goLiveModelClass = new GoLiveModelClass();
        goLiveModelClass.setUserID(userId);
        String image = userImage;
        goLiveModelClass.setImage(image);
        goLiveModelClass.setName(userName);
        goLiveModelClass.setUserName(userName);
        goLiveModelClass.setRequestStatus(status);
        setMultiLiveRequestAcceptRejecte(goLiveModelClass, status);
    }

    private void doLeaveChannel() {
        leaveChannel(config().mChannel);
        preview(false, null, 0);
    }

    private void doHideTargetView(int targetUid, boolean hide) {
        HashMap<Integer, Integer> status = new HashMap<>();
        status.put(targetUid, hide ? UserStatusData.VIDEO_MUTED : UserStatusData.DEFAULT_STATUS);
        if (mLayoutType == LAYOUT_TYPE_DEFAULT) {
            mGridVideoViewContainer.notifyUiChanged(mUidsList, targetUid, status, null);
        } else if (mLayoutType == LAYOUT_TYPE_SMALL) {
            UserStatusData bigBgUser = mGridVideoViewContainer.getItem(0);
            if (bigBgUser.mUid == targetUid) { // big background is target view
                mGridVideoViewContainer.notifyUiChanged(mUidsList, targetUid, status, null);
            } else { // find target view in small video view list
                log.warn("SmallVideoViewAdapter call notifyUiChanged " + mUidsList + " " + (bigBgUser.mUid & 0xFFFFFFFFL) + " target: " + (targetUid & 0xFFFFFFFFL) + "==" + targetUid + " " + status);
                mSmallVideoViewAdapter.notifyUiChanged(mUidsList, bigBgUser.mUid, status, null);
            }
        }
    }

    public void onVoiceMuteClicked(View view, String status) {
        RtcEngine rtcEngine = rtcEngine();
        HashMap<String, Object> data = new HashMap<>();
        if (!isMute) {
            isMute = true;
            rtcEngine.muteLocalAudioStream(true);
            data.put("mute", "0");
        } else {
            isMute = false;
            data.put("mute", "1");
            rtcEngine.muteLocalAudioStream(false);
            ref.child(otherUserId).child(liveType).child(otherUserId).child("multiLiveRequest").child(userId).updateChildren(data);
        }

        ImageView iv = (ImageView) view;
        if (isMute) {
            iv.setImageResource(R.drawable.ic_baseline_mic_off_24);
        } else {
            iv.setImageResource(R.drawable.ic_baseline_mic_24);
        }
    }

    public void onMixingAudioClicked(View view) {
        log.info("onMixingAudioClicked " + view + " " + mUidsList.size() + " video_status: " + mVideoMuted + " audio_status: " + mAudioMuted + " mixing_audio: " + mMixingAudio);

        if (mUidsList.size() == 0) {
            return;
        }

        mMixingAudio = !mMixingAudio;

        RtcEngine rtcEngine = rtcEngine();
        if (mMixingAudio) {
            rtcEngine.startAudioMixing(Constant.MIX_FILE_PATH, false, false, -1);
        } else {
            rtcEngine.stopAudioMixing();
        }

        ImageView iv = (ImageView) view;
//        iv.setImageResource(mMixingAudio ? R.drawable.btn_audio_mixing : R.drawable.btn_audio_mixing_off);
    }

    @Override
    public void onUserJoined(int uid) {
        log.debug("onUserJoined " + (uid & 0xFFFFFFFFL));
//ToDo
//        doRenderRemoteUi(uid);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                notifyMessageChanged(new Message(new User(0, null), "user " + (uid & 0xFFFFFFFFL) + " joined"));
            }
        });
    }

    @Override
    public void onFirstRemoteVideoDecoded(int uid, int width, int height, int elapsed) {
        log.debug("onFirstRemoteVideoDecoded " + (uid & 0xFFFFFFFFL) + " " + width + " " + height + " " + elapsed);
    }

    private void doRenderRemoteUi(final int uid) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (isFinishing()) {
                    return;
                }
                if (mUidsList.containsKey(uid)) {
                    return;
                }
                /*
                  Creates the video renderer view.
                  CreateRendererView returns the SurfaceView type. The operation and layout of the
                  view are managed by the app, and the Agora SDK renders the view provided by the
                  app. The video display view must be created using this method instead of
                  directly calling SurfaceView.
                 */
                SurfaceView surfaceV = RtcEngine.CreateRendererView(getApplicationContext());
                mUidsList.put(uid, surfaceV);
                boolean useDefaultLayout = mLayoutType == LAYOUT_TYPE_DEFAULT;
                surfaceV.setZOrderOnTop(true);
                surfaceV.setZOrderMediaOverlay(true);
                /*
                  Initializes the video view of a remote user.
                  This method initializes the video view of a remote stream on the local device. It affects only the video view that the local user sees.
                  Call this method to bind the remote video stream to a video view and to set the rendering and mirror modes of the video view.
                 */
                rtcEngine().setupRemoteVideo(new VideoCanvas(surfaceV, VideoCanvas.RENDER_MODE_HIDDEN, uid));
                if (useDefaultLayout) {
                    log.debug("doRenderRemoteUi LAYOUT_TYPE_DEFAULT " + (uid & 0xFFFFFFFFL));
                    switchToDefaultVideoView();
                } else {
                    int bigBgUid = mSmallVideoViewAdapter == null ? uid : mSmallVideoViewAdapter.getExceptedUid();
                    log.debug("doRenderRemoteUi LAYOUT_TYPE_SMALL " + (uid & 0xFFFFFFFFL) + " " + (bigBgUid & 0xFFFFFFFFL));
                    switchToSmallVideoView(bigBgUid);
                }
//                notifyMessageChanged(new Message(new User(0, null), "video from user " + (uid & 0xFFFFFFFFL) + " decoded"));
            }
        });
    }

    @Override
    public void onJoinChannelSuccess(String channel, final int uid, int elapsed) {
        log.debug("onJoinChannelSuccess " + channel + " " + (uid & 0xFFFFFFFFL) + " " + elapsed);
    }

    @Override
    public void onUserOffline(int uid, int reason) {
        log.debug("onUserOffline " + (uid & 0xFFFFFFFFL) + " " + reason);
        doRemoveRemoteUi(uid);
    }

    @Override
    public void onExtraCallback(final int type, final Object... data) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (isFinishing()) {
                    return;
                }
                doHandleExtraCallback(type, data);
            }
        });
    }

    @Override
    public void onRemoteVideoStateChanged(int uid, int state, int reason, int elapsed) {
        if (state == 0) {
        } else {
            doRenderRemoteUi(uid);
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.i("onRemoteVide", "" + uid + "  " + state + "  ");
            }
        });
    }

    private void doHandleExtraCallback(int type, Object... data) {

        int peerUid;
        boolean muted;
        switch (type) {
            case AGEventHandler.EVENT_TYPE_ON_USER_AUDIO_MUTED:
                peerUid = (Integer) data[0];
                muted = (boolean) data[1];

                if (mLayoutType == LAYOUT_TYPE_DEFAULT) {
                    HashMap<Integer, Integer> status = new HashMap<>();
                    status.put(peerUid, muted ? UserStatusData.AUDIO_MUTED : UserStatusData.DEFAULT_STATUS);
                    mGridVideoViewContainer.notifyUiChanged(mUidsList, config().mUid, status, null);
                }
                break;

            case AGEventHandler.EVENT_TYPE_ON_USER_VIDEO_MUTED:
                peerUid = (Integer) data[0];
                muted = (boolean) data[1];
                doHideTargetView(peerUid, muted);
                break;

            case AGEventHandler.EVENT_TYPE_ON_USER_VIDEO_STATS:
                IRtcEngineEventHandler.RemoteVideoStats stats = (IRtcEngineEventHandler.RemoteVideoStats) data[0];

                if (Constant.SHOW_VIDEO_INFO) {
                    if (mLayoutType == LAYOUT_TYPE_DEFAULT) {
                        mGridVideoViewContainer.addVideoInfo(stats.uid, new VideoInfoData(stats.width, stats.height, stats.delay, stats.rendererOutputFrameRate, stats.receivedBitrate));
                        int uid = config().mUid;
                        int profileIndex = getVideoEncResolutionIndex();
                        String resolution = getResources().getStringArray(R.array.string_array_resolutions)[profileIndex];
                        String fps = getResources().getStringArray(R.array.string_array_frame_rate)[profileIndex];

                        String[] rwh = resolution.split("x");
                        int width = Integer.valueOf(rwh[0]);
                        int height = Integer.valueOf(rwh[1]);

                        mGridVideoViewContainer.addVideoInfo(uid, new VideoInfoData(width > height ? width : height,
                                width > height ? height : width,
                                0, Integer.valueOf(fps), Integer.valueOf(0)));
                    }
                } else {
                    mGridVideoViewContainer.cleanVideoInfo();
                }

                break;

            case AGEventHandler.EVENT_TYPE_ON_SPEAKER_STATS:
                IRtcEngineEventHandler.AudioVolumeInfo[] infos = (IRtcEngineEventHandler.AudioVolumeInfo[]) data[0];

                if (infos.length == 1 && infos[0].uid == 0) { // local guy, ignore it
                    break;
                }

                if (mLayoutType == LAYOUT_TYPE_DEFAULT) {
                    HashMap<Integer, Integer> volume = new HashMap<>();

                    for (IRtcEngineEventHandler.AudioVolumeInfo each : infos) {
                        peerUid = each.uid;
                        int peerVolumee = each.volume;
                        if (peerVolumee >= 1) {
                            peerVol = 1;
                        } else {
                            peerVol = 0;
                        }
                        if (peerVol == 1) {
                            peerVolume = 1;
                        } else {
                            peerVolume = 0;
                        }
                        if (peerUid == 0) {
                            continue;
                        }
                        volume.put(peerUid, peerVolume);

                        HashMap hashMap = new HashMap();
                        hashMap.put("peerId", peerVolume);

                        ref.child(otherUserId).child(liveType).child(otherUserId).child("peerId").setValue(hashMap);

                        ref.child(otherUserId).child(liveType).child(otherUserId).child("peerId").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                         //       if (otherUserId != AppConstants.USER_ID || otherUserId == AppConstants.USER_ID) {

   //                             int value = Integer.parseInt(snapshot.child("peerId").getValue().toString());
//                                    if (value>=1){
//                                        if (otherUserId != AppConstants.USER_ID || otherUserId == AppConstants.USER_ID){
//                                            binding.voiceIndicationLottie.setVisibility(View.VISIBLE);
//                                            binding.voiceIndicationLottie.playAnimation();
//                                        }else {
//                                            binding.voiceIndicationLottie.setVisibility(View.VISIBLE);
//                                            binding.voiceIndicationLottie.playAnimation();
//                                        }  1
//
//                                    }else {
//                                        binding.voiceIndicationLottie.setVisibility(View.GONE);
//                                        binding.voiceIndicationLottie.cancelAnimation();
//                                    }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


                        ref.child(otherUserId).child(liveType).child(otherUserId).child("multiLiveRequest").child(userId).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    ref.child(otherUserId).child(liveType).child(otherUserId).child("multiLiveRequest").child(userId).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
                                        @Override
                                        public void onComplete(@NonNull Task task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(CallActivity.this, "" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(CallActivity.this, "" + task.getException().toString(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                    mGridVideoViewContainer.notifyUiChanged(mUidsList, config().mUid, null, volume);
                    ///here notify the data
                }
                break;
            case AGEventHandler.EVENT_TYPE_ON_APP_ERROR:
                int subType = (int) data[0];
                if (subType == ConstantApp.AppError.NO_CONNECTION_ERROR) {
                    String msg = getString(R.string.msg_connection_error);
//                    notifyMessageChanged(new Message(new User(0, null), msg));
                    showLongToast(msg);
                }
                break;
            case AGEventHandler.EVENT_TYPE_ON_DATA_CHANNEL_MSG:
                peerUid = (Integer) data[0];
                final byte[] content = (byte[]) data[1];
                //  notifyMessageChanged(new Message(new User(peerUid, String.valueOf(peerUid)), new String(content)));
                break;
            case AGEventHandler.EVENT_TYPE_ON_AGORA_MEDIA_ERROR: {
                int error = (int) data[0];
                String description = (String) data[1];
//             notifyMessageChanged(new Message(new User(0, null), error + " " + description));
                break;
            }

            case AGEventHandler.EVENT_TYPE_ON_AUDIO_ROUTE_CHANGED:
                notifyHeadsetPlugged((int) data[0]);
                break;
        }
    }

    private void requestRemoteStreamType(final int currentHostCount) {
        log.debug("requestRemoteStreamType " + currentHostCount);
    }

    private void doRemoveRemoteUi(final int uid) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (isFinishing()) {
                    return;
                }
                Object target = mUidsList.remove(uid);
                if (target == null) {
                    return;
                }
                int bigBgUid = -1;
                if (mSmallVideoViewAdapter != null) {
                    bigBgUid = mSmallVideoViewAdapter.getExceptedUid();
                }

                log.debug("doRemoveRemoteUi " + (uid & 0xFFFFFFFFL) + " " + (bigBgUid & 0xFFFFFFFFL) + " " + mLayoutType);

                if (mLayoutType == LAYOUT_TYPE_DEFAULT || uid == bigBgUid) {
                    switchToDefaultVideoView();
                } else {
                    switchToSmallVideoView(bigBgUid);
                }
//              notifyMessageChanged(new Message(new User(0, null), "user " + (uid & 0xFFFFFFFFL) + " left"));
            }
        });
    }

    private void switchToDefaultVideoView() {
        if (mSmallVideoViewDock != null) {
            mSmallVideoViewDock.setVisibility(View.GONE);
        }
        mGridVideoViewContainer.initViewContainer(this, config().mUid, mUidsList, mIsLandscape, mGridVideoViewContainer, RecyclerView.VERTICAL);
        mLayoutType = LAYOUT_TYPE_DEFAULT;
        boolean setRemoteUserPriorityFlag = false;
        int sizeLimit = mUidsList.size();

        if (sizeLimit > ConstantApp.MAX_PEER_COUNT + 1) {
            sizeLimit = ConstantApp.MAX_PEER_COUNT + 1;
        }

        for (int i = 0; i < sizeLimit; i++) {
            int uid = mGridVideoViewContainer.getItem(i).mUid;
            if (config().mUid != uid) {
                if (!setRemoteUserPriorityFlag) {
                    setRemoteUserPriorityFlag = true;
                    rtcEngine().setRemoteUserPriority(uid, Constants.USER_PRIORITY_HIGH);
                    log.debug("setRemoteUserPriority USER_PRIORITY_HIGH " + mUidsList.size() + " " + (uid & 0xFFFFFFFFL));
                } else {
                    rtcEngine().setRemoteUserPriority(uid, Constants.USER_PRIORITY_NORMAL);
                    log.debug("setRemoteUserPriority USER_PRIORITY_NORANL " + mUidsList.size() + " " + (uid & 0xFFFFFFFFL));
                }
            }
        }
    }

    private void switchToSmallVideoView(int bigBgUid) {
        HashMap<Integer, SurfaceView> slice = new HashMap<>(1);
        slice.put(bigBgUid, mUidsList.get(bigBgUid));
        Iterator<SurfaceView> iterator = mUidsList.values().iterator();
        while (iterator.hasNext()) {
            SurfaceView s = iterator.next();
            s.setZOrderOnTop(true);
            s.setZOrderMediaOverlay(true);
        }
        mUidsList.get(bigBgUid).setZOrderOnTop(false);
        mUidsList.get(bigBgUid).setZOrderMediaOverlay(false);
        mGridVideoViewContainer.initViewContainer(this, bigBgUid, slice, mIsLandscape, mGridVideoViewContainer, RecyclerView.VERTICAL);
        bindToSmallVideoView(bigBgUid);
        mLayoutType = LAYOUT_TYPE_SMALL;
        requestRemoteStreamType(mUidsList.size());
    }

    private void bindToSmallVideoView(int exceptUid) {
        if (mSmallVideoViewDock == null) {
            ViewStub stub = findViewById(R.id.small_video_view_dock);
            mSmallVideoViewDock = (RelativeLayout) stub.inflate();
        }
        boolean twoWayVideoCall = mUidsList.size() == 2;
        RecyclerView recycler = findViewById(R.id.small_video_view_container);
        boolean create = false;
        if (mSmallVideoViewAdapter == null) {
            create = true;
            mSmallVideoViewAdapter = new SmallVideoViewAdapter(this, config().mUid, exceptUid, mUidsList, binding.gridVideoViewContainer);
            mSmallVideoViewAdapter.setHasStableIds(true);
        }
        recycler.setHasFixedSize(true);

        log.debug("bindToSmallVideoView " + twoWayVideoCall + " " + (exceptUid & 0xFFFFFFFFL));

        if (twoWayVideoCall) {
            recycler.setLayoutManager(new RtlLinearLayoutManager(getApplicationContext(), RtlLinearLayoutManager.HORIZONTAL, false));
        } else {
            recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        }
        recycler.addItemDecoration(new SmallVideoViewDecoration());
        recycler.setAdapter(mSmallVideoViewAdapter);
        recycler.addOnItemTouchListener(new RecyclerItemClickListener(getBaseContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
            }

            @Override
            public void onItemLongClick(View view, int position) {
            }

            @Override
            public void onItemDoubleClick(View view, int position) {
                //onSmallVideoViewDoubleClicked(view, position);
            }
        }));

        recycler.setDrawingCacheEnabled(true);
        recycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_AUTO);

        if (!create) {
            mSmallVideoViewAdapter.setLocalUid(config().mUid);
            mSmallVideoViewAdapter.notifyUiChanged(mUidsList, exceptUid, null, null);
        }
        for (Integer tempUid : mUidsList.keySet()) {
            if (config().mUid != tempUid) {
                if (tempUid == exceptUid) {
                    rtcEngine().setRemoteUserPriority(tempUid, Constants.USER_PRIORITY_HIGH);
                    log.debug("setRemoteUserPriority USER_PRIORITY_HIGH " + mUidsList.size() + " " + (tempUid & 0xFFFFFFFFL));
                } else {
                    rtcEngine().setRemoteUserPriority(tempUid, Constants.USER_PRIORITY_NORMAL);
                    log.debug("setRemoteUserPriority USER_PRIORITY_NORANL " + mUidsList.size() + " " + (tempUid & 0xFFFFFFFFL));
                }
            }
        }
        recycler.setVisibility(View.VISIBLE);
        mSmallVideoViewDock.setVisibility(View.VISIBLE);
    }

    public void notifyHeadsetPlugged(final int routing) {
        log.info("notifyHeadsetPlugged " + routing + " " + mVideoMuted);
        mAudioRouting = routing;
        ImageView iv = findViewById(R.id.switch_speaker_id);
//        if (mAudioRouting == Constants.AUDIO_ROUTE_SPEAKERPHONE) {
//            iv.setImageResource(R.drawable.btn_speaker);
//        } else {
//            iv.setImageResource(R.drawable.btn_speaker_off);
//        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mIsLandscape = newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE;
        if (mLayoutType == LAYOUT_TYPE_DEFAULT) {
            switchToDefaultVideoView();
        } else if (mSmallVideoViewAdapter != null) {
            switchToSmallVideoView(mSmallVideoViewAdapter.getExceptedUid());
        }
    }

    @Override
    public void onBackPressed() {
        final Dialog dialog = new Dialog(CallActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.back_layout);
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView minimize = dialog.findViewById(R.id.minimize);
        RelativeLayout milayout = dialog.findViewById(R.id.milayout);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.top_animation_code);
        milayout.setAnimation(animation);

        ImageView exit = dialog.findViewById(R.id.exit);
        RelativeLayout exitLayout = dialog.findViewById(R.id.exitLayout);
        Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bottom_animation_code);
        exitLayout.setAnimation(animation2);
        dialog.show();

        minimize.setOnClickListener(v -> {
            dialog.dismiss();
            setPIPScreen();
        });

        long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
//        player.setPlayWhenReady(false);
//        liveEndedDialog(elapsedMillis);
        int h = (int) (elapsedMillis / 3600000);
        int m = (int) (elapsedMillis - h * 3600000) / 60000;
        int s = (int) (elapsedMillis - h * 3600000 - m  * 60000) / 1000;
        String time = (h < 10 ? "0" + h : h) + ":" + (m < 10 ? "0" + m : m) + ":" + (s < 10 ? "0" + s : s);

        exit.setOnClickListener(v -> {
            if (status.equals("1")) {
                Log.i("LiveCall","in if of exit");
                dialog.dismiss();
                newDialog(this,time);
                endLiveApiHit();
                ref.child(otherUserId).child(liveType).child(otherUserId).child("viewer List").child(userId).removeValue();
               startActivity(new Intent(CallActivity.this, HomeActivity.class));
                finish();
            } else if (status.equals("2")) {
                dialog.dismiss();
                newDialog(this,time);
//             Intent intent = new Intent(CallActivity.this, EndLiveActivity.class);
//             intent.putExtra("liveUserCount", liveUsersCount);
//             startActivity(intent);
                ref.child(otherUserId).child(liveType).child(otherUserId).child("viewer List").child(userId).removeValue();
                Log.i("LiveCall","in else if");
                endLiveApiHit();
                //finish();
            }
        });
    }
    private void endLiveApiHit() {
        callViewModel.endLiveCall(CallActivity.this, liveId).observe(CallActivity.this, new Observer<SendOtpRoot>() {
            @Override
            public void onChanged(SendOtpRoot sendOtpRoot) {
                if (sendOtpRoot != null) {
                    if (sendOtpRoot.getStatus().equalsIgnoreCase("1")) {
                        if (liveHostBackImg.equalsIgnoreCase(AppConstants.USER_ID)) {
                            //remove announcement and
                            muteMicRef.child(liveHostBackImg).removeValue();
                            userLiveAnnouncement.child(liveHostBackImg).removeValue();
                            cleanUserCommentsRef.child(liveHostBackImg).removeValue();
                            LuckBagRef.child(liveHostBackImg).removeValue();
                            emojiRef.child(liveHostBackImg).removeValue();
                            luckyDivideUsersRef.child(liveHostBackImg).removeValue();
                            lockSeat.child(liveHostBackImg).removeValue();
                            liveUsersRef.child(AppConstants.USER_ID).removeValue();
                            inviteAudienceRef.child(liveHostBackImg).removeValue();
                        }
                    } else {
                    }
                }
            }
        });
    }

    public void setPIPScreen() {
        Display d = getWindowManager().getDefaultDisplay();
        Point p = new Point();
        d.getSize(p);
        int width = p.x;
        int height = p.y;
        Rational ratio = new Rational(width, height);
        PictureInPictureParams.Builder pip_Builder = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            pip_Builder = new PictureInPictureParams.Builder();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            pip_Builder.setAspectRatio(ratio).build();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            enterPictureInPictureMode(pip_Builder.build());
        }
    }


    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode, Configuration newConfig) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig);
        if (isInPictureInPictureMode) {
            Log.i("onPictureInP", "show full screen in if " + newConfig.getLayoutDirection());
            binding.rlHostA.setVisibility(View.VISIBLE);
            binding.rlChair.setVisibility(View.GONE);
            binding.rlWholeLayout.setVisibility(View.GONE);
        } else {
            Log.i("onPictureInP", "show full screen  " + newConfig.getLayoutDirection());
            startActivity(new Intent(this, getClass())
                    .addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
            binding.rlHostA.setVisibility(View.GONE);
            binding.rlChair.setVisibility(View.VISIBLE);
            binding.rlWholeLayout.setVisibility(View.VISIBLE);


        }
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        setPIPScreen();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == ImagePicker.REQUEST_CODE) {
            assert data != null;
            Uri image = data.getData();
            assert image != null;
            this.stringCoverPhotoPath = image.getPath();
            coverInfoUserImg.setImageURI(image);
        } else if (requestCode == 1 && resultCode == RESULT_OK) {
            audioPath = data.getData().toString();
            audioEffectManager = rtcEngine().getAudioEffectManager();
            int id = 0;
        //add the file path for the audio you want to play
            audioEffectManager.preloadEffect(id++, audioPath);
            audioEffectManager.resumeAllEffects();
        }
    }

    private void getAppliedFrameApi(String userId, SVGAImageView DpFrame) {
        callViewModel.getAppliedFrame(CallActivity.this, userId).observe(CallActivity.this, new Observer<GetAppliedFrameRoot>() {
            @Override
            public void onChanged(GetAppliedFrameRoot getAppliedFrameRoot) {
                if (getAppliedFrameRoot != null) {
                    if (getAppliedFrameRoot.getStatus() == 1) {
                        frame = getAppliedFrameRoot.getDetails().getFrame_img();
                        App.getSharedpref().saveString("profileFrame",frame);
                        setFrameONDp(frame, DpFrame);
                    } else {
                        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) binding.hostNameTv.getLayoutParams();
                        params.addRule(RelativeLayout.BELOW, R.id.imgHostProfile);
                    }
                }
            }
        });
    }

    private void setFrameONDp(String frame, SVGAImageView DpFrame) {
        parser = new SVGAParser(CallActivity.this);
        try {
            parser.decodeFromURL(new URL(frame), new SVGAParser.ParseCompletion() {
                @Override
                public void onComplete(@com.google.firebase.database.annotations.NotNull SVGAVideoEntity videoItem) {
                    SVGADrawable drawable = new SVGADrawable(videoItem);
                    DpFrame.setImageDrawable(drawable);
                    DpFrame.startAnimation();
                }

                @Override
                public void onError() {

                }
            }, new SVGAParser.PlayCallback() {
                @Override
                public void onPlay(@NonNull List<? extends File> list) {

                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void getUserDetailApi(String otherUserId, TextView othrUsrFollowingTV, TextView countryTv, ImageView followingImg, TextView age, ImageView gender, ImageView anchorimg, ImageView vipImage, LinearLayout genderlayout, LinearLayout sendingLayout, ImageView receivinglayout, TextView sendinglvl, TextView receivingLvl, ImageView lvlimg, RelativeLayout receivingBottomSheetRL) {
        callViewModel.getUserDetail(CallActivity.this, AppConstants.USER_ID,otherUserId, "").observe(CallActivity.this,
                new Observer<GetUserDetailRoot>() {
                    @Override
                    public void onChanged(GetUserDetailRoot getUserDetailRoot) {
                        if (getUserDetailRoot != null) {
                            if (getUserDetailRoot.getStatus().equalsIgnoreCase("1")) {
                                countryTv.setText("ID:" + getUserDetailRoot.getDetails().getUsername().toString() + " | " + getUserDetailRoot.getDetails().getCountry());
                                age.setText(CommonUtils.ageCalcualte(getUserDetailRoot.getDetails().getDob()));

                                familyname = getUserDetailRoot.getDetails().getFamilyName();
                                Log.d("familyname", "familyname: " +familyname);
                             //  sendingLayout.getBackground().setColorFilter(Color.parseColor(getUserDetailRoot.getDetails().getLavelInfomation().getSandColor()), PorterDuff.Mode.SRC_ATOP);
//                              receivinglayout.getBackground().setColorFilter(Color.parseColor(getUserDetailRoot.getDetails().getLavelInfomation().getReciveColor()), PorterDuff.Mode.SRC_ATOP);
                            //  sendinglvl.setText(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel());
                                Glide.with(CallActivity.this).load(getUserDetailRoot.getDetails().getLavelInfomation().getReciveColor()).into(receivinglayout);
                                Log.d("receivingLvl", "receivingLvl: "+getUserDetailRoot.getDetails().getLavelInfomation().getReciveLevel());
                                if(Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getReciveLevel()) == 0){
                                    receivingBottomSheetRL.setVisibility(View.GONE);
                                }
                              receivingLvl.setText(getUserDetailRoot.getDetails().getLavelInfomation().getReciveLevel());


                                if(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel().isEmpty()){
                                    sendingLayout.setVisibility(View.GONE);
                                }else{
                                    sendingLayout.setVisibility(View.VISIBLE);
                                    sendinglvl.setText(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel());
                                    if (Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())==0){
                                        sendingLayout.setVisibility(View.GONE);
                                    } else if (Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())>=1 && Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())<=10) {
                                        sendingLayout.setVisibility(View.VISIBLE);
                                        sendingLayout.setBackgroundResource(R.drawable.level_1);
                                    } else if (Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())>=11 && Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())<=20) {
                                        sendingLayout.setVisibility(View.VISIBLE);
                                        sendingLayout.setBackgroundResource(R.drawable.level_2);
                                        lvlimg.setImageResource(R.drawable.badge1);
                                    } else if (Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())>=21 && Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())<=30) {
                                        sendingLayout.setVisibility(View.VISIBLE);
                                        sendingLayout.setBackgroundResource(R.drawable.level_3);
                                        lvlimg.setImageResource(R.drawable.badge1);
                                    } else if (Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())>=31 && Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())<=40) {
                                        sendingLayout.setVisibility(View.VISIBLE);
                                        sendingLayout.setBackgroundResource(R.drawable.level_4);
                                        lvlimg.setImageResource(R.drawable.badge2);
                                    } else if (Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())>=41 && Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())<=50) {
                                        sendingLayout.setVisibility(View.VISIBLE);
                                        sendingLayout.setBackgroundResource(R.drawable.level_5);
                                        lvlimg.setImageResource(R.drawable.badge2);
                                    } else if (Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())>=51 && Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())<=60) {
                                        sendingLayout.setVisibility(View.VISIBLE);
                                        sendingLayout.setBackgroundResource(R.drawable.level_6);
                                        lvlimg.setImageResource(R.drawable.badge3);
                                    } else if (Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())>=61 && Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())<=70) {
                                        sendingLayout.setVisibility(View.VISIBLE);
                                        sendingLayout.setBackgroundResource(R.drawable.level_7);
                                        lvlimg.setImageResource(R.drawable.badge3);
                                    } else if (Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())>=71 && Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())<=80) {
                                        sendingLayout.setVisibility(View.VISIBLE);
                                        sendingLayout.setBackgroundResource(R.drawable.level_8);
                                        lvlimg.setImageResource(R.drawable.badge3);
                                    } else if (Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())>=81 && Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())<=90) {
                                        sendingLayout.setVisibility(View.VISIBLE);
                                        sendingLayout.setBackgroundResource(R.drawable.level_9);
                                        lvlimg.setImageResource(R.drawable.badge3);
                                    } else if (Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())>=91 && Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())<=100) {
                                        sendingLayout.setVisibility(View.VISIBLE);
                                        sendingLayout.setBackgroundResource(R.drawable.level_10);
                                        lvlimg.setImageResource(R.drawable.badge3);
                                    } else if (Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())>=101 && Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())<=110) {
                                        sendingLayout.setVisibility(View.VISIBLE);
                                        sendingLayout.setBackgroundResource(R.drawable.level_11);
                                        lvlimg.setImageResource(R.drawable.badge3);
                                    } else if (Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())>=111 && Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())<=120) {
                                        sendingLayout.setVisibility(View.VISIBLE);
                                        sendingLayout.setBackgroundResource(R.drawable.level_12);
                                        lvlimg.setImageResource(R.drawable.badge4);
                                    } else if (Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())>=121 && Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())<=130) {
                                        sendingLayout.setVisibility(View.VISIBLE);
                                        sendingLayout.setBackgroundResource(R.drawable.level_13);
                                        lvlimg.setImageResource(R.drawable.badge4);
                                    } else if (Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())>=131 && Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())<=140) {
                                        sendingLayout.setVisibility(View.VISIBLE);
                                        sendingLayout.setBackgroundResource(R.drawable.level_14);
                                        lvlimg.setImageResource(R.drawable.badge4);
                                    } else if (Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())>=141 && Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())<=150) {
                                        sendingLayout.setVisibility(View.VISIBLE);
                                        sendingLayout.setBackgroundResource(R.drawable.level_15);
                                        lvlimg.setImageResource(R.drawable.badge4);
                                    } else if (Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())>150) {
                                        lvlimg.setImageResource(R.drawable.badge4);
                                        sendingLayout.setVisibility(View.VISIBLE);
                                        sendingLayout.setBackgroundResource(R.drawable.level_16);
                                    }
                                }


                                if (getUserDetailRoot.getDetails().getGender().equalsIgnoreCase("male")||getUserDetailRoot.getDetails().getGender().equalsIgnoreCase("Male")) {
                                    gender.setImageResource(R.drawable.ic_male_gender__2_);
                                    genderlayout.getBackground().setColorFilter(Color.parseColor("#0ed8a3"), PorterDuff.Mode.SRC_ATOP);
                                } else {
                                    gender.setImageResource(R.drawable.femenine);
                                    genderlayout.getBackground().setColorFilter(Color.parseColor("#fd5293"), PorterDuff.Mode.SRC_ATOP);
                                }

                                // anchor status
                                if (getUserDetailRoot.getDetails().getHost_status().equalsIgnoreCase("2")){
                                    anchorimg.setVisibility(View.VISIBLE);
                                }else {
                                    anchorimg.setVisibility(View.GONE);
                                }

                                // vip level
                                if(getUserDetailRoot.getDetails().getVipLevel().equalsIgnoreCase("0")){
                                   // binding.editProfileVipLvlTv.setText("Viplvl.0");
                                    vipImage.setVisibility(View.INVISIBLE);
                                }else{
                                    if(getUserDetailRoot.getDetails().getVipLevel().equalsIgnoreCase("1")){
                                        vipImage.setVisibility(View.VISIBLE);
                                        vipImage.setImageResource(R.drawable.vip1img);

                                    }else if(getUserDetailRoot.getDetails().getVipLevel().equalsIgnoreCase("2")){
                                        vipImage.setVisibility(View.VISIBLE);
                                        vipImage.setImageResource(R.drawable.vip2img);

                                    }else if(getUserDetailRoot.getDetails().getVipLevel().equalsIgnoreCase("3")){
                                        vipImage.setVisibility(View.VISIBLE);
                                       vipImage.setImageResource(R.drawable.vip3img);


                                    }else if(getUserDetailRoot.getDetails().getVipLevel().equalsIgnoreCase("4")){
                                        vipImage.setVisibility(View.VISIBLE);
                                       vipImage.setImageResource(R.drawable.vip4img);
                                    }else if(getUserDetailRoot.getDetails().getVipLevel().equalsIgnoreCase("5")){
                                        vipImage.setVisibility(View.VISIBLE);
                                             vipImage.setImageResource(R.drawable.vip5img);
                                    }else{
                                       // binding.editProfileVipLvlTv.setText("Viplvl."+App.getSharedpref().getString("vipLevel"));
                                        vipImage.setVisibility(View.INVISIBLE);
                                    }
                                  //  binding.editProfileVipLvlTv.setText("Viplvl."+App.getSharedpref().getString("vipLevel"));

                                }

                                if (getUserDetailRoot.getDetails().isFollowStatus()) {
                                    j = 1;
                                    followingImg.setImageResource(R.drawable.ic_minus__2_);
                                    followingImg.setImageTintList(ColorStateList.valueOf(Color.BLACK));
                                    othrUsrFollowingTV.setText("Following");
                                } else {
                                    j = 2;
                                    followingImg.setImageTintList(ColorStateList.valueOf(Color.BLACK));
                                    followingImg.setImageResource(R.drawable.ic_plus__6_);
                                    othrUsrFollowingTV.setText("Follow");
                                }
                            } else {
                            }

                            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_left);
                            binding.animationRL.startAnimation(animation);

                            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) binding.rlGustHost.getLayoutParams();
                            params.topMargin = 40;

                            final Handler handler = new Handler(Looper.getMainLooper());
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    binding.animationRL.setVisibility(View.GONE);
                                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) binding.rlGustHost.getLayoutParams();
                                    params.topMargin = 80;
                                }
                            }, 5000);
                            binding.animationRL.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(CallActivity.this, "Technical issue", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    private void getNameAnimationFromFirebase() {

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_left);
        binding.animationRL.startAnimation(animation);

        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) binding.rlGustHost.getLayoutParams();
        params.topMargin = 40;


        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.animationRL.setVisibility(View.GONE);

                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) binding.rlGustHost.getLayoutParams();
                params.topMargin = 80;

            }
        }, 5000);
        binding.animationRL.setVisibility(View.VISIBLE);
    }

    TextView musicStartTimeTv;
    ImageView playMusicFragmentImg;
    TextView musicFragSongName;
    TextView musicEndTimeTv;
    long duration1;
    String title1;

    private void addMusicDialogBox() {

        Dialog dialog_share = new Dialog(CallActivity.this);
        dialog_share.setContentView(R.layout.play_music_dialog_box);
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog_share.getWindow().getAttributes().windowAnimations = R.style.musicDialogAnimation;
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog_share.setCanceledOnTouchOutside(true);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog_share.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog_share.getWindow().setAttributes(layoutParams);
        Window window = dialog_share.getWindow();
        window.setGravity(Gravity.BOTTOM);
        musicRv = dialog_share.findViewById(R.id.musicRv);
        dialog_share.show();

        LinearLayoutManager layoutManagermusic = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        musicRv.setLayoutManager(layoutManagermusic);

//      List<MusicTable> musicList = new ArrayList<>();
        musicList = appDatabase.userDao().getAllSongs();

        TextView musicRescanTv = dialog_share.findViewById(R.id.musicRescanTv);
        LinearLayout noMusicLayout = dialog_share.findViewById(R.id.noMusicLayout);
        RelativeLayout musicPlayRL = dialog_share.findViewById(R.id.musicPlayRL);
        playMusicFragmentImg = (ImageView) dialog_share.findViewById(R.id.playMusicFragmentImg);
        musicFragSongName = (TextView) dialog_share.findViewById(R.id.musicFragSongName);
        musicStartTimeTv = (TextView) dialog_share.findViewById(R.id.musicStartTimeTv);
        musicEndTimeTv = (TextView) dialog_share.findViewById(R.id.musicEndTimeTv);
        seekbar1 = (AppCompatSeekBar) dialog_share.findViewById(R.id.seekbar);
        ImageView volumeMusicFragmentImg = (ImageView) dialog_share.findViewById(R.id.volumeMusicFragmentImg);
        ImageView musicBackImg = (ImageView) dialog_share.findViewById(R.id.musicBackImg);
        AppCompatButton scanAndAddMusicBtn = (AppCompatButton) dialog_share.findViewById(R.id.scanAndAddMusicBtn);

        musicBackImg.setOnClickListener(v -> dialog_share.dismiss());

        if (musicList.isEmpty()) {
            noMusicLayout.setVisibility(View.VISIBLE);
            musicRv.setVisibility(View.GONE);
            musicRescanTv.setVisibility(View.GONE);
            musicPlayRL.setVisibility(View.GONE);
        } else {
            noMusicLayout.setVisibility(View.GONE);
            musicRv.setVisibility(View.VISIBLE);
            musicRescanTv.setVisibility(View.VISIBLE);

            musicRVAdapter = new MusicRVAdapter(musicList, CallActivity.this, new MusicRVAdapter.Callback() {
                @Override
                public void playMusic(MusicTable musicDetail, int musicPlayStatus, ImageView imageView, boolean status, int postin) {
                    musicDetails = musicDetail;
                    valueTime = 0;
                    resumeCheck = 0;
                    musicpos = postin;
                    duration1 = musicDetail.getDuration();
                    title1 = musicDetail.getTitle();
                    setTimer(musicDetails.getDuration(), musicDetails.getTitle(), false, 0, musicpos);
                    audioEffectManager = rtcEngine().getAudioEffectManager();
                    audioEffectManager.setEffectPosition(musicDetail.getId(), 1);

                    musicFragSongName.setText(musicDetail.getTitle());
                    musicEndTimeTv.setText(formatDuration(musicDetail.getDuration()));

                    MusicId = musicDetail.getId();
                    audioEffectManager.preloadEffect(MusicId, musicDetail.getPath());

                    play_music(musicDetail, 0);
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) binding.animationRL.getLayoutParams();
                    params.addRule(RelativeLayout.BELOW, R.id.imgHostProfile);

                }

                @Override
                public void deleteMusic(MusicTable musicDetail, int posn) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CallActivity.this);
                    alertDialogBuilder.setMessage("Are you sure, You wanted to make decision");
                    alertDialogBuilder.setPositiveButton("yes",
                            (arg0, arg1) -> {
                                appDatabase.userDao().deleteById(musicDetail.getId());
                                musicRv.setAdapter(musicRVAdapter);
                                musicRVAdapter.notifyDataSetChanged();
                            });
                    alertDialogBuilder.setNegativeButton("No", (dialog, which) -> finish());

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                }
            });
            musicRv.setAdapter(musicRVAdapter);

        }
        //this is for outSide music box
//        if (outSideBoxMusicCheck == 1) {
//            seekbarDialog12.setFocusableInTouchMode(false);
//        }
        seekbar1.setFocusableInTouchMode(false);
        seekbar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
//                play_music(musicDetails, seekBar.getProgress());
//                musicStartTimeTv.setText(formatDuration(seekBar.getProgress()));
            }
        });

        playMusicFragmentImg.setOnClickListener(view -> {

            if (musicStatus == 0) {

                playMusicFragmentImg.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
                pause_music();
                musicCountDownTimer.cancel();

                resumeCheck = 1;
                musicStatus = 1;

                binding.musicPlayCirImg.setVisibility(View.GONE);
                binding.musicPlayCirImg.cancelAnimation();

                //this is for outside musicDialog
                if (outSideBoxMusicCheck == 1) {
                    playMusicDialogImg.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
                }

            } else {
                playMusicFragmentImg.setImageResource(R.drawable.ic_baseline_pause_circle_24);
                resume_music();
                musicStatus = 0;
                resumeCheck = 2;
                binding.musicPlayCirImg.setVisibility(View.VISIBLE);
                binding.musicPlayCirImg.playAnimation();
                //this is for outside musicDialog
                if (outSideBoxMusicCheck == 1) {
                    playMusicDialogImg.setImageResource(R.drawable.ic_baseline_pause_circle_24);
                }
            }
        });
        musicRescanTv.setOnClickListener(view -> {

            rescanDialog();
            dialog_share.dismiss();

        });
        scanAndAddMusicBtn.setOnClickListener(view -> rescanDialog());
    }

    private int getCurrentItem() {
        return 0;
    }

    private void setTimer(long duration, String title, boolean stopTime, float resumeTimes, int posin) {

        if (musicCountDownTimer != null) {
            musicCountDownTimer.cancel();
            musicCountDownTimer = null;
        }

        float dureationSeekbar = ((float) duration / 100000);

        musicCountDownTimer = new CountDownTimer(duration, 1000) {
            public void onTick(long millisUntilFinished) {
                int pos = posin;
                musicStartTimeTv.setText(formatDuration((long) (duration - (valueTime * 1000))));
                musictime = musicStartTimeTv.getText().toString();

                if (musictime.equals("00:01")||musictime.equals("00:00")) {
                    pos++;
                    Log.d("musicStartTimeTv","musicStartTimeTv "+musicStartTimeTv);
                    binding.musicPlayCirImg.setVisibility(View.VISIBLE);
                    binding.musicPlayCirImg.playAnimation();
                    audioEffectManager.playEffect(musicList.get(pos).getId(), musicList.get(pos).getPath(), -1, 0.0, 100, 0, true);
                    // Pauses all audio effects.
                    audioEffectManager.pauseAllEffects();
                    rtcEngine().startAudioMixing(musicList.get(pos).getPath(), false, false, -1, 0);
                    musicFragSongName.setText(title);
                } else {
                     pos=0;
                }
                Log.d("music", "music =" + " " + formatDuration((long) (duration - (valueTime * 1000))));
                seekbar1.setProgress((int) ((++valueTime) / dureationSeekbar));
                //this is for outside music dialog box
                if (outSideBoxMusicCheck == 1) {
                    startMusicTimeTv.setText(formatDuration((long) (duration - (valueTime * 1000))));
                    seekbarDialog12.setProgress((int) ((++valueTime) / dureationSeekbar));
                    String text = startMusicTimeTv.getText().toString();
                    Log.d("text","musicTime = "+text);
//                        if (text.equals("00:01")) {
//                            pos++;
//                            binding.musicPlayCirImg.setVisibility(View.VISIBLE);
//                            binding.musicPlayCirImg.playAnimation();
//                            audioEffectManager.playEffect(musicList.get(pos).getId(), musicList.get(pos).getPath(), -1, 0.0, 100, 0, true);
//                            // Pauses all audio effects.
//                            audioEffectManager.pauseAllEffects();
//                            rtcEngine().startAudioMixing(musicList.get(pos).getPath(), false, true, -1, 0);
//                            musicFragSongName.setText(title);
//                        } else {
//                            pos=0;
//                        }
                }
            }
            public void onFinish() {
                musicCountDownTimer.cancel();
                rtcEngine().stopAudioMixing();
                binding.musicPlayCirImg.setVisibility(View.GONE);
            }
        };
        musicCountDownTimer.start();
    }

    private void play_music(MusicTable musicDetail, int progress) {

        musicOnOffStatus = 1;
        binding.musicPlayCirImg.setVisibility(View.VISIBLE);

        binding.musicPlayCirImg.playAnimation();

        audioEffectManager.playEffect(musicDetail.getId(), musicDetail.getPath(), -1, 0.0, 40, 0, true);
        // Pauses all audio effects.
        audioEffectManager.pauseAllEffects();
        rtcEngine().startAudioMixing(musicDetail.getPath(), false, false, -1, progress);

        playMusicFragmentImg.setImageResource(R.drawable.ic_baseline_pause_circle_24);

        //this is for outside dialog box
        if (outSideBoxMusicCheck == 1) {
            playMusicDialogImg.setImageResource(R.drawable.ic_baseline_pause_circle_24);
        }
    }

    private void pause_music() {
        rtcEngine().pauseAudioMixing();
    }

    private void stopMusic() {
//        audioEffectManager.stopEffect(MusicId);
    }

    private void resume_music() {
        setTimer(musicDetails.getDuration(), musicDetails.getTitle(), false, 0, poos);
        rtcEngine().resumeAudioMixing();


    }

    //this method duration of song covert into mintues
    private String formatDuration(long duration) {
        long minutes = TimeUnit.MINUTES.convert(duration, TimeUnit.MILLISECONDS);
        long seconds = TimeUnit.SECONDS.convert(duration, TimeUnit.MILLISECONDS)
                - minutes * TimeUnit.SECONDS.convert(1, TimeUnit.MINUTES);
        return String.format("%02d:%02d", minutes, seconds);
    }

    private void rescanDialog() {
        Dialog dialog_share = new Dialog(CallActivity.this);
        dialog_share.setContentView(R.layout.rescan_dialog_box);
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog_share.getWindow().getAttributes().windowAnimations = R.style.musicDialogAnimation;
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        ImageView localAddedBackImg = dialog_share.findViewById(R.id.localAddedBackImg);
        dialog_share.setCanceledOnTouchOutside(true);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog_share.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog_share.getWindow().setAttributes(layoutParams);
        Window window = dialog_share.getWindow();
        window.setGravity(Gravity.BOTTOM);
        dialog_share.show();

        getMusic();

        TextView save = dialog_share.findViewById(R.id.save);
        localAddedBackImg.setOnClickListener(v -> {
            dialog_share.dismiss();
            addMusicDialogBox();
        });
        save.setOnClickListener(v -> {

            dialog_share.dismiss();
            addMusicDialogBox();
        });

        RecyclerView localAddedRV = dialog_share.findViewById(R.id.localAddedRV);

        LocalAddedAdapter localAddedAdapter = new LocalAddedAdapter(audioList, CallActivity.this);
        localAddedRV.setAdapter(localAddedAdapter);
        localAddedAdapter.notifyDataSetChanged();
    }
    private void diamondsDialogBox() {
        Bundle bundle = new Bundle();
        bundle.putString("liveId", liveHostBackImg);
        LiveDiamondDialogFragment liveDiamondDialogFragment = new LiveDiamondDialogFragment();
        liveDiamondDialogFragment.setArguments(bundle);
        liveDiamondDialogFragment.show(getSupportFragmentManager(), liveDiamondDialogFragment.getTag());
    }

    private void getAllTotalDiamonds() {
        Log.d("getAllTotalDiamonds", "getAllTotalDiamonds: " + liveHostBackImg);
        callViewModel.getLiveTotalDiamonds(CallActivity.this,liveHostBackImg).observe(CallActivity.this, getLiveTotalDiamondRoot -> {
            if (getLiveTotalDiamondRoot != null) {
                if (getLiveTotalDiamondRoot.getSuccess().equalsIgnoreCase("1")) {
                    //String formattedValue = formatValue(Integer.parseInt(getLiveTotalDiamondRoot.getDetails().getDiamond()));
                    String formattedValue = null;
                    try {
                        String diamondValue = getLiveTotalDiamondRoot.getDetails().getDiamond();
                        int diamond = Integer.parseInt(diamondValue);
                        formattedValue = formatValue(diamond);
                        // Use the formattedValue as needed
                    } catch (NumberFormatException e) {
                        // Handle the case where the diamondValue is not a valid integer
                        // Log an error, show a message to the user, or take appropriate action
                        e.printStackTrace(); // Print the stack trace for debugging purposes
                    }
                    liveDiamonds.child("liveDiamonds").setValue(formattedValue);
                    if (formattedValue !=null){
                        binding.liveDiamondsTv.setText(formattedValue);
                    }else {
                        liveDiamonds.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                binding.liveDiamondsTv.setText(snapshot.child("liveDiamonds").getValue().toString());
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                } else {
                }
            } else {
                Toast.makeText(CallActivity.this, "Technical issue", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @SuppressLint("DefaultLocale")
    private String formatValue(int value) {
        if (value < 1000) {
            return String.valueOf(value);
        } else if (value < 1000000) {
            return String.format("%.1fk", value / 1000.0);
        } else if (value < 1000000000) {
            return String.format("%.1fm", value / 1000000.0);
        } else {
            return String.format("%.1ft", value / 1000000000.0);
        }
    }

    private void getMusic() {
        String[] proj = {MediaStore.Audio.Media._ID, MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ALBUM, MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DATE_ADDED, MediaStore.Audio.Media.DATA, MediaStore.Audio.Media.ALBUM_ID};// Can include more data for more details and check it.

        Cursor audioCursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, proj, null, null, null);
        if (audioCursor != null) {

            if (audioCursor.moveToFirst()) {
                do {
                    String titleC = audioCursor.getString(audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
                    String idC = audioCursor.getString(audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
                    String albumC = audioCursor.getString(audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
                    String artistC = audioCursor.getString(audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                    String pathC = audioCursor.getString(audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                    @SuppressLint("Range") Long durationC = audioCursor.getLong(audioCursor.getColumnIndex(MediaStore.Audio.Media.DURATION));

                    Uri uri = Uri.parse("content://media/external/audio/albumart");
                    String artUriC = Uri.withAppendedPath(uri, albumC).toString();

                    File file = new File(pathC);
                    long length = file.length();
                    length = length / 1024;  //size in kb

                    SizeMB = (int) length / 1024;
                    if (SizeMB < 25) {
                        Music music = new Music(idC, titleC, albumC, artistC, pathC, durationC, artUriC);
                        audioList.add(music);
                    }
                } while (audioCursor.moveToNext());
            }
        }
        audioCursor.close();
    }

    @SuppressLint("InlinedApi")
    private void requestPermissions() {
        // below line is use to request permission in the current activity.
        // this method is use to handle error in runtime permissions
        //READ_EXTERNAL_STORAGE
        Dexter.withActivity(this)
                // below line is use to request the number of permissions which are required in our app.
                .withPermissions(Manifest.permission.CAMERA,
                        // below is the list of permissions
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.READ_MEDIA_AUDIO)
                // after adding permissions we are calling an with listener method.
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                        // this method is called when all permissions are granted
                        if (multiplePermissionsReport.areAllPermissionsGranted()) {
                            // do you work now
                            addMusicDialogBox();
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
        AlertDialog.Builder builder = new AlertDialog.Builder(CallActivity.this);

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
            Uri uri = Uri.fromParts("package", getPackageName(), null);
            intent.setData(uri);
            startActivityForResult(intent, 101);
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> {
            // this method is called when user click on negative button.
            dialog.cancel();
        });
        // below line is used to display our dialog
        builder.show();
    }
    public void makeActivityFullScreen(Window window) {
        View decorView = window.getDecorView();
        //        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        );

    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    private void newDialog(Activity activity, String time) {
        chronometer.stop();
        Rect displayRectangle = new Rect();
        final AlertDialog.Builder builder = new AlertDialog.Builder(CallActivity.this, R.style.CustomAlertDialog);
        builder.setCancelable(false);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(activity).inflate(R.layout.dialog_liveended, viewGroup, false);
        Window window = CallActivity.this.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        builder.setView(dialogView);
        dialogView.setMinimumWidth((int) (displayRectangle.width() * 0.7f));
        dialogView.setMinimumHeight((int) (displayRectangle.height() * 0.7f));
        TextView tv = dialogView.findViewById(R.id.tv_time);
        TextView diamondTextView = dialogView.findViewById(R.id.diamondTextView);
        TextView iv_imageliveuse = dialogView.findViewById(R.id.iv_imageliveuse);
        diamondTextView.setText(binding.liveDiamondsTv.getText().toString());
        iv_imageliveuse.setText(binding.liveUsersTV.getText().toString());

        tv.setText(time);
        alertDialog = builder.create();
        alertDialog.show();
        Button bt_ok = dialogView.findViewById(R.id.post_button);
        bt_ok.setOnClickListener(view -> {
            alertDialog.dismiss();
            CallActivity.super.onBackPressed();
        });
    }

    private BroadcastReceiver headsetReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action != null && action.equals(Intent.ACTION_HEADSET_PLUG)) {
                int state = intent.getIntExtra("state", -1);
                if (state == 0) {
                    // Headphones disconnected
                    audioManager.setSpeakerphoneOn(true);
                } else if (state == 1) {
                    // Headphones connected
                    audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
                    audioManager.setSpeakerphoneOn(false);
                }
            }
        }
    };

    @Override
    protected void onPause() {
        super.onPause();

        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    protected void onDestroy() {
        Log.i("LiveCall","on destroy");
        endLiveApiHit();
      //  ref.child(otherUserId).child(liveType).child(otherUserId).child("viewer List").child(userId).removeValue();
        super.onDestroy();
//        binding = null;
    }
}

