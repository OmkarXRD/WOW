package com.live.worldsocialintegrationapp.Retrofit;

import static com.google.firebase.messaging.Constants.TAG;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.live.worldsocialintegrationapp.ModelClasses.AdminPanelStatusRoot;
import com.live.worldsocialintegrationapp.ModelClasses.AllPopularUsers.GetAllPopularRoot;
import com.live.worldsocialintegrationapp.ModelClasses.ApplyForHostModelClass;
import com.live.worldsocialintegrationapp.ModelClasses.ApplyGalleryImage;
import com.live.worldsocialintegrationapp.ModelClasses.BannerSliderRoot;
import com.live.worldsocialintegrationapp.ModelClasses.BlockUser.RootBlocked;
import com.live.worldsocialintegrationapp.ModelClasses.BuyVipRoot;
import com.live.worldsocialintegrationapp.ModelClasses.ChatNotificationRoot;
import com.live.worldsocialintegrationapp.ModelClasses.DailyDateLiveRecord;
import com.live.worldsocialintegrationapp.ModelClasses.DailyTask.GetDailyTaskRoot;
import com.live.worldsocialintegrationapp.ModelClasses.DailyTask.SetDailyTaskRoot;
import com.live.worldsocialintegrationapp.ModelClasses.EDitFamilyModelClass;
import com.live.worldsocialintegrationapp.ModelClasses.EntryEffectsRoot;
import com.live.worldsocialintegrationapp.ModelClasses.Events.CreateEventRoot;
import com.live.worldsocialintegrationapp.ModelClasses.Events.EventsDetailsRoot;
import com.live.worldsocialintegrationapp.ModelClasses.Events.GetAllEventInvitationsRoot;
import com.live.worldsocialintegrationapp.ModelClasses.Events.GetEventsRoot;
import com.live.worldsocialintegrationapp.ModelClasses.Events.ResponseEventInvitationRoot;
import com.live.worldsocialintegrationapp.ModelClasses.Events.SuscribeUnscribeRoot;
import com.live.worldsocialintegrationapp.ModelClasses.ExchangeCoinsRoot;
import com.live.worldsocialintegrationapp.ModelClasses.Family.CreateFamilyRoot;
import com.live.worldsocialintegrationapp.ModelClasses.Family.GetAllFamilyRoot;
import com.live.worldsocialintegrationapp.ModelClasses.Family.GetFamilyDetailsRoot;
import com.live.worldsocialintegrationapp.ModelClasses.Family.GetFamilyTopGiftersRoot;
import com.live.worldsocialintegrationapp.ModelClasses.Family.GetInvitationsRoot;
import com.live.worldsocialintegrationapp.ModelClasses.Family.GetJoinRequestRoot;
import com.live.worldsocialintegrationapp.ModelClasses.Family.GetLiveFamilyJoinersRoot;
import com.live.worldsocialintegrationapp.ModelClasses.Family.InvitationsResponseRoot;
import com.live.worldsocialintegrationapp.ModelClasses.Family.ResponseJoinRequestRoot;
import com.live.worldsocialintegrationapp.ModelClasses.FirebaseSendReqRoot;
import com.live.worldsocialintegrationapp.ModelClasses.FollowingFriend.FollowingFriendLiveRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GenerateOrderRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GeneratedIdClass;
import com.live.worldsocialintegrationapp.ModelClasses.GetAppliedFrameRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GetAppliedGalleryModel;
import com.live.worldsocialintegrationapp.ModelClasses.GetAppliedLuckyIdRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GetBadges.GetTopGifterRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GetChatFilesRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GetCoin.GetCoinRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GetCoin.GetSilverCoinRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GetCoin.PurchaseSilverCoins;
import com.live.worldsocialintegrationapp.ModelClasses.GetComment.GetCommentRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GetDiamondRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GetEmoji.GetEmojiRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GetFamilyDetails;
import com.live.worldsocialintegrationapp.ModelClasses.GetFans.GetFansRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GetFollowing.GetFollowingRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GetFollowingLiveRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GetFramesRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GetFriends.GetFriendRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GetLiveBackgroundImagesRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GetLiveDiamondRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GetLiveGiftHistoryRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GetLiveTotalDiamondRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GetLuckIdRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GetPurchaseGallery;
import com.live.worldsocialintegrationapp.ModelClasses.GetPurchaseThemes;
import com.live.worldsocialintegrationapp.ModelClasses.GetTotalSilverCoinsRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GetUserCoinModel;
import com.live.worldsocialintegrationapp.ModelClasses.GetUserDetail.GetUserDetailRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GetUserImagesRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GetUserTalentLevelRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GiftWall.RootGiftWall;
import com.live.worldsocialintegrationapp.ModelClasses.GiftWallLiveUsersRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GiftWallReceiverModelClass;
import com.live.worldsocialintegrationapp.ModelClasses.HostApproveModelClass;
import com.live.worldsocialintegrationapp.ModelClasses.KickOutLiveRoot;
import com.live.worldsocialintegrationapp.ModelClasses.LiveGallery.GetLiveGalleryRoot;
import com.live.worldsocialintegrationapp.ModelClasses.LiveGallery.PurchaseGalleryRoot;
import com.live.worldsocialintegrationapp.ModelClasses.LiveGallery.SendGalleryRoot;
import com.live.worldsocialintegrationapp.ModelClasses.LiveTheme.GetLiveThemeRoot;
import com.live.worldsocialintegrationapp.ModelClasses.LiveTheme.SendLiveTheme;
import com.live.worldsocialintegrationapp.ModelClasses.LiveUserByCountry.RootLiveUser;
import com.live.worldsocialintegrationapp.ModelClasses.LiveUserByIdRoot;
import com.live.worldsocialintegrationapp.ModelClasses.LiveUserHideUnhideRoot;
import com.live.worldsocialintegrationapp.ModelClasses.LockBroadCast.RootLockBroadCast;
import com.live.worldsocialintegrationapp.ModelClasses.LuckBag.CreateSuperLuckyBagRoot;
import com.live.worldsocialintegrationapp.ModelClasses.LuckBag.DeductCoinsRoot;
import com.live.worldsocialintegrationapp.ModelClasses.LuckBag.HitLuckyBagCoinsRoot;
import com.live.worldsocialintegrationapp.ModelClasses.LuckBag.HitSuperLuckyBagRoot;
import com.live.worldsocialintegrationapp.ModelClasses.LuckBag.LuckBagRoot;
import com.live.worldsocialintegrationapp.ModelClasses.LuckBag.SuperLuckyBagDetails;
import com.live.worldsocialintegrationapp.ModelClasses.Meet.RootMeet;
import com.live.worldsocialintegrationapp.ModelClasses.NearByLiveUsers.NearByLiveUsersRoot;
import com.live.worldsocialintegrationapp.ModelClasses.Posts.GetAllPostsRoot;
import com.live.worldsocialintegrationapp.ModelClasses.Posts.GetFriendsFollowingPostsRoot;
import com.live.worldsocialintegrationapp.ModelClasses.Posts.PostDetailsRoot;
import com.live.worldsocialintegrationapp.ModelClasses.PurchaseCarsRoot;
import com.live.worldsocialintegrationapp.ModelClasses.PurchaseFramesRoot;
import com.live.worldsocialintegrationapp.ModelClasses.PurchaseHistory.PurchaseHistoryRoot;
import com.live.worldsocialintegrationapp.ModelClasses.Register.RegisterRoot;
import com.live.worldsocialintegrationapp.ModelClasses.SaveTransactionRoot;
import com.live.worldsocialintegrationapp.ModelClasses.SendEventInviationRoot;
import com.live.worldsocialintegrationapp.ModelClasses.SendFileInChatRoot;
import com.live.worldsocialintegrationapp.ModelClasses.SendMsgRoot;
import com.live.worldsocialintegrationapp.ModelClasses.SendOtpRoot;
import com.live.worldsocialintegrationapp.ModelClasses.SendingLevel;
import com.live.worldsocialintegrationapp.ModelClasses.SetLiveCoverImgRoot;
import com.live.worldsocialintegrationapp.ModelClasses.ShowMyFamilyModelClass;
import com.live.worldsocialintegrationapp.ModelClasses.SingleLiveUserRoot;
import com.live.worldsocialintegrationapp.ModelClasses.SocialLoginRoot;
import com.live.worldsocialintegrationapp.ModelClasses.SomeFunctionalityModel;
import com.live.worldsocialintegrationapp.ModelClasses.UpdateUserProfileRoot;
import com.live.worldsocialintegrationapp.ModelClasses.UploadPost.RemoveUserPostRoot;
import com.live.worldsocialintegrationapp.ModelClasses.UploadPost.UploadPostRoot;
import com.live.worldsocialintegrationapp.ModelClasses.UserLevel.GetCarUserLevel;
import com.live.worldsocialintegrationapp.ModelClasses.UserLevel.GetColorByLevel;
import com.live.worldsocialintegrationapp.ModelClasses.UserLevel.GetFrameByLevel;
import com.live.worldsocialintegrationapp.ModelClasses.UserReportCategoryRoot;
import com.live.worldsocialintegrationapp.ModelClasses.UserReportRoot;
import com.live.worldsocialintegrationapp.ModelClasses.UsersSearch.UsersSearchRoot;
import com.live.worldsocialintegrationapp.ModelClasses.VipImagesRoot;
import com.live.worldsocialintegrationapp.ModelClasses.VipRoot;
import com.live.worldsocialintegrationapp.ModelClasses.Visitors.GetVisitorsRoot;
import com.live.worldsocialintegrationapp.ModelClasses.Wallet.AddWalletMoneyRoot;
import com.live.worldsocialintegrationapp.ModelClasses.Wallet.GetWalletRoot;
import com.live.worldsocialintegrationapp.ModelClasses.onlineServices.GetAnswerRoot;
import com.live.worldsocialintegrationapp.ModelClasses.onlineServices.GetQuestionRoot;
import com.live.worldsocialintegrationapp.ModelClasses.wowsBoard.WowsBoardRoot;
import com.live.worldsocialintegrationapp.Root.CountryRoot;
import com.live.worldsocialintegrationapp.agora.openvcall.model.BillingRecordModelClass;
import com.live.worldsocialintegrationapp.agora.openvcall.model.EmojiGiftModel;
import com.live.worldsocialintegrationapp.agora.openvcall.model.GiftSendModel;
import com.live.worldsocialintegrationapp.agora.openvcall.model.LiveUserModel;
import com.live.worldsocialintegrationapp.agora.openvcall.model.PrimeGiftModel;
import com.live.worldsocialintegrationapp.agora.openvcall.model.SendEmojiGiftModel;
import com.live.worldsocialintegrationapp.agora.openvcall.model.SpinWheelModelClass;
import com.live.worldsocialintegrationapp.agora.openvcall.model.TokenGenerateModel;
import com.live.worldsocialintegrationapp.games.model.CheckSpinWheel;
import com.live.worldsocialintegrationapp.games.model.SpinModelClass;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.live.worldsocialintegrationapp.utils.CommonUtils;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Mvvm extends ViewModel {
    ServiceApi serviceApi = RetrofitClient.getRetrofitCallerObject().create(ServiceApi.class);

    private MutableLiveData mutableLiveData;

    private MutableLiveData<CountryRoot> countryRootMutableLiveData;

    public LiveData<CountryRoot> getLiveDataCountryDetails(Activity activity) {

        countryRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {
            CommonUtils.openDialog(activity);

            serviceApi.getCountryDetails().enqueue(new Callback<CountryRoot>() {
                @Override
                public void onResponse(@NonNull Call<CountryRoot> call, @NonNull Response<CountryRoot> response) {
                    if (response.body() != null) {
                        countryRootMutableLiveData.postValue(response.body());
                        CommonUtils.dismissDialog();
                    } else {
                        Toast.makeText(activity, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        CommonUtils.dismissDialog();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<CountryRoot> call, @NonNull Throwable t) {
                    CommonUtils.dismissDialog();
                    Toast.makeText(activity, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "onFailure: " + t.getMessage());
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return countryRootMutableLiveData;
    }

    private MutableLiveData<GetPurchaseGallery> getPurchaseThemes;

    public LiveData<GetPurchaseGallery> getPurchaseThemes(String userId) {
        getPurchaseThemes = new MutableLiveData<>();

        serviceApi.getPurchaseThemes(userId).enqueue(new Callback<GetPurchaseGallery>() {
            @Override
            public void onResponse(Call<GetPurchaseGallery> call, Response<GetPurchaseGallery> response) {
                if (response.body() != null) {
                    getPurchaseThemes.postValue(response.body());
                } else {
                    getPurchaseThemes.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<GetPurchaseGallery> call, Throwable t) {

                getPurchaseThemes.postValue(null);
            }
        });
        return getPurchaseThemes;
    }

    private MutableLiveData<GetPurchaseGallery> applyTheme;

    public LiveData<GetPurchaseGallery> applyTheme(String userId, String themeId, String type) {
        applyTheme = new MutableLiveData<>();

        serviceApi.applyTheme(userId, themeId,type).enqueue(new Callback<GetPurchaseGallery>() {
            @Override
            public void onResponse(Call<GetPurchaseGallery> call, Response<GetPurchaseGallery> response) {
                if (response.body() != null) {
                    applyTheme.postValue(response.body());
                } else {
                    applyTheme.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<GetPurchaseGallery> call, Throwable t) {

                applyTheme.postValue(null);
            }
        });
        return applyTheme;
    }

    private MutableLiveData<CountryRoot> getPhoneUniqueMutableLiveData;

//    public LiveData<CountryRoot> checkPhoneUniqueLiveData(String phone) {
//
//
//        getPhoneUniqueMutableLiveData = new MutableLiveData<>();
//
//        serviceApi.checkPhone(phone).enqueue(new Callback<CountryRoot>() {
//            @Override
//            public void onResponse(@NonNull Call<CountryRoot> call, @NonNull Response<CountryRoot> response) {
//                if (response.body() != null) {
//                    getPhoneUniqueMutableLiveData.postValue(response.body());
//                } else {
//                    getPhoneUniqueMutableLiveData.postValue(null);
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<CountryRoot> call, @NonNull Throwable t) {
//                getPhoneUniqueMutableLiveData.postValue(null);
//            }
//        });
//
//        return getPhoneUniqueMutableLiveData;
//    }


//    public LiveData<CountryRoot> getLoginLiveData(String phone, String otp) {
//
//        getLoginMutableLiveData = new MutableLiveData<>();
//
//        serviceApi.login(phone, otp).enqueue(new Callback<CountryRoot>() {
//            @Override
//            public void onResponse(@NonNull Call<CountryRoot> call, @NonNull Response<CountryRoot> response) {
//                if (response.body() != null) {
//                    getLoginMutableLiveData.postValue(response.body());
//                } else {
//                    getLoginMutableLiveData.postValue(null);
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<CountryRoot> call, @NonNull Throwable t) {
//                getLoginMutableLiveData.postValue(null);
//            }
//        });
//
//        return getLoginMutableLiveData;
//    }
    //  ----------------------------------------------------------------    ----------------------------------------------------------------

    private MutableLiveData<CountryRoot> getLoginMutableLiveData;

    public LiveData<SendOtpRoot> sendOtp(Activity activity, String phone,String password,String salt, String resetPassword, String updateNumber,String username) {

        mutableLiveData = new MutableLiveData();

        if (CommonUtils.isNetworkConnected(activity)) {
            serviceApi.sendOtp(phone,password,salt,resetPassword,updateNumber,username).enqueue(new Callback<SendOtpRoot>() {
                @Override
                public void onResponse(Call<SendOtpRoot> call, Response<SendOtpRoot> response) {

                    if (response != null) {
                        mutableLiveData.postValue(response.body());
                        Log.i("Issssssueeeeee","phn "+response.body());
                    } else {
                        Toast.makeText(activity, "Body is null", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<SendOtpRoot> call, Throwable t) {
                    Toast.makeText(activity, "Failure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    mutableLiveData.postValue(null);
                }
            });

        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return mutableLiveData;
    }

//    public LiveData<SendOtpRoot> checkPhoneNumber(Activity activity, String phone) {
//
//        mutableLiveData = new MutableLiveData();
//
//        if (CommonUtils.isNetworkConnected(activity)) {
//            Log.i("Issssssueeeeee","phn"+phone);
//            serviceApi.checkPhoneNumber(phone).enqueue(new Callback<SendOtpRoot>() {
//                @Override
//                public void onResponse(Call<SendOtpRoot> call, Response<SendOtpRoot> response) {
//
//                    if (response != null) {
//                        mutableLiveData.postValue(response.body());
//                        Log.i("Issssssueeeeee","MVVM "+response.body());
//                    } else {
//                        Toast.makeText(activity, "Body is null", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<SendOtpRoot> call, Throwable t) {
//                    Toast.makeText(activity, "Failure " + t.getMessage(), Toast.LENGTH_SHORT).show();
//                    mutableLiveData.postValue(null);
//                }
//            });
//
//        } else {
//            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
//        }
//        return mutableLiveData;
//    }

    public LiveData<RegisterRoot> registerUser(Activity activity, String phone, String password,String salt, String country, String continent,String forgotPassword, String regId) {
        mutableLiveData = new MutableLiveData();
        if (CommonUtils.isNetworkConnected(activity)) {
            Log.i("RegisterUser","in else");
            Log.i("RegisterUser","phn"+phone);
            Log.i("RegisterUser","otp "+password);
            Log.i("RegisterUser","country "+country);
            Log.i("RegisterUser","continent "+continent);
            Log.i("RegisterUser","regId "+regId);
            serviceApi.registerUser(phone, password,salt, country, continent,forgotPassword, regId).enqueue(new Callback<RegisterRoot>() {
                @Override
                public void onResponse(Call<RegisterRoot> call, Response<RegisterRoot> response) {
                    if (response.body() != null) {
                        mutableLiveData.postValue(response.body());
                        //Log.i("Issssssueeeeee","respose "+response.body().toString());
                    } else {

                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RegisterRoot> call, Throwable t) {
                    mutableLiveData.postValue(null);
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return mutableLiveData;
    }

    public LiveData<SendOtpRoot> logoutUser(Activity activity, String userId) {

        if (CommonUtils.isNetworkConnected(activity)) {
            CommonUtils.showProgress(activity, "Loading...");

            mutableLiveData = new MutableLiveData();

            serviceApi.logoutUser(userId).enqueue(new Callback<SendOtpRoot>() {
                @Override
                public void onResponse(Call<SendOtpRoot> call, Response<SendOtpRoot> response) {

                    if (response.body() != null) {
                        CommonUtils.dismissProgress();
                        mutableLiveData.postValue(response.body());
                    } else {
                        CommonUtils.dismissProgress();
                        Toast.makeText(activity, "body is null", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<SendOtpRoot> call, Throwable t) {
                    mutableLiveData.postValue(null);
                    Toast.makeText(activity, "Not Logout", Toast.LENGTH_SHORT).show();
                    Toast.makeText(activity, "Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Network not connect", Toast.LENGTH_SHORT).show();
        }
        return mutableLiveData;
    }


    private MutableLiveData<UpdateUserProfileRoot> updateUserProfileRootMutableLiveData;

    public LiveData<UpdateUserProfileRoot> updateVendorHVRootModelLiveData(Activity activity, RequestBody id, RequestBody name, MultipartBody.Part image,
                                                                           RequestBody gender, RequestBody dob, RequestBody country, RequestBody bio) {
        updateUserProfileRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {
            CommonUtils.showProgress(activity, "Loading...");
            serviceApi.updateUserProfile(id, name, image, gender, dob, country, bio).enqueue(new Callback<UpdateUserProfileRoot>() {
                @Override
                public void onResponse(@NotNull Call<UpdateUserProfileRoot> call, @NotNull Response<UpdateUserProfileRoot> response) {

                    Log.d("ApiRespone", "ApiRespone : " + response.body());

                    if (response.body() != null) {
                        updateUserProfileRootMutableLiveData.postValue(response.body());
                        CommonUtils.dismissProgress();
                    } else {
                        updateUserProfileRootMutableLiveData.postValue(null);
                    }
//                    if (response.isSuccessful()) {
//                        CommonUtils.dismissProgress();
//                        updateUserProfileRootMutableLiveData.postValue(response.body());
//                    } else {
//                        CommonUtils.dismissProgress();
//                        Log.d("ApiRespone","ApiRespone : "+response.message());
//                        Toast.makeText(activity, "Body is Null", Toast.LENGTH_SHORT).show();
//                    }
                }

                @Override
                public void onFailure(@NonNull Call<UpdateUserProfileRoot> call, @NonNull Throwable t) {

                    updateUserProfileRootMutableLiveData.postValue(null);
                }
            });
        } else {
            Toast.makeText(activity, "Network not Connected", Toast.LENGTH_SHORT).show();
        }
        return updateUserProfileRootMutableLiveData;

    }


    private MutableLiveData<Map> endLive = new MutableLiveData<>();

    public LiveData<Map> endLive(Activity activity, String userId) {

        serviceApi.endLive(userId).enqueue(new Callback<Map>() {
            @Override
            public void onResponse(Call<Map> call, Response<Map> response) {
                if (response.code() == 200) {
                    endLive.postValue(response.body());
                } else {
                    Toast.makeText(activity, "Server not responding", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Map> call, Throwable t) {
                Toast.makeText(activity, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return endLive;
    }


    private MutableLiveData<LiveUserModel> getLiveUserApi = new MutableLiveData<>();

    public LiveData<LiveUserModel> getLiveUserApi(Activity activity) {

//        if (CommonUtils.isNetworkConnected(activity)) {
//
//            serviceApi.getLiveMultiLive().enqueue(new Callback<LiveUserModel>() {
//                @Override
//                public void onResponse(Call<LiveUserModel> call, Response<LiveUserModel> response) {
//                    if (response.body() != null) {
//                        getLiveUserApi.postValue(response.body());
//                    } else {
//                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<LiveUserModel> call, Throwable t) {
//                    Toast.makeText(activity, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });
//        } else {
//            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
//        }
        return getLiveUserApi;
    }


    private MutableLiveData<PrimeGiftModel> primeGiftModelMutableLiveData;

    public LiveData<PrimeGiftModel> giftModelLiveData(Activity activity, String userId) {

        primeGiftModelMutableLiveData = new MutableLiveData<>();

        serviceApi.getGifts(userId).enqueue(new Callback<PrimeGiftModel>() {
            @Override
            public void onResponse(Call<PrimeGiftModel> call, Response<PrimeGiftModel> response) {
                if (response.body() != null) {
                    primeGiftModelMutableLiveData.postValue(response.body());
                } else {
                    Toast.makeText(activity, "Gift not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PrimeGiftModel> call, Throwable t) {
                Toast.makeText(activity, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return primeGiftModelMutableLiveData;
    }

    private MutableLiveData<GiftSendModel> sendModelMutableLiveData;

    public LiveData<GiftSendModel> sendModelLiveData(Activity activity, String senderId, String receiverId, String diamond, String giftId, String liveId) {
        sendModelMutableLiveData = new MutableLiveData<>();

        serviceApi.sendGift(senderId, receiverId, diamond, giftId, liveId).enqueue(new Callback<GiftSendModel>() {
            @Override
            public void onResponse(Call<GiftSendModel> call, Response<GiftSendModel> response) {
                if (response.body() != null) {
                    sendModelMutableLiveData.postValue(response.body());
                } else {
//                    Toast.makeText(activity, "Gift not Send", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GiftSendModel> call, Throwable t) {
                Toast.makeText(activity, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return sendModelMutableLiveData;
    }


    private MutableLiveData<EmojiGiftModel> emojiGiftModelMutableLiveData;

    public LiveData<EmojiGiftModel> emojiGiftModelLiveData(Activity activity) {
        emojiGiftModelMutableLiveData = new MutableLiveData<>();

        serviceApi.getEmojiGifts().enqueue(new Callback<EmojiGiftModel>() {
            @Override
            public void onResponse(Call<EmojiGiftModel> call, Response<EmojiGiftModel> response) {
                if (response.body() != null) {
                    emojiGiftModelMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<EmojiGiftModel> call, Throwable t) {
                Toast.makeText(activity, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        return emojiGiftModelMutableLiveData;
    }


    private MutableLiveData<SendEmojiGiftModel> sendEmojiGiftModelMutableLiveData;

    public LiveData<SendEmojiGiftModel> sendEmojiGiftModelLiveData(Activity activity, String senderId, String receiverId, String diamond, String giftId, String liveId) {

        sendEmojiGiftModelMutableLiveData = new MutableLiveData<>();

        serviceApi.sendEmojiGift(senderId, receiverId, diamond, giftId, liveId).enqueue(new Callback<SendEmojiGiftModel>() {
            @Override
            public void onResponse(Call<SendEmojiGiftModel> call, Response<SendEmojiGiftModel> response) {
                if (response.body() != null) {
                    sendEmojiGiftModelMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<SendEmojiGiftModel> call, Throwable t) {
                Toast.makeText(activity, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return sendEmojiGiftModelMutableLiveData;
    }


    private MutableLiveData<TokenGenerateModel> getGenerateToke;

    public LiveData<TokenGenerateModel> getGenerateModel(Activity activity, HashMap<String, String> data) {
        getGenerateToke = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.generateToken(data).enqueue(new Callback<TokenGenerateModel>() {
                @Override
                public void onResponse(Call<TokenGenerateModel> call, Response<TokenGenerateModel> response) {
                    if (response.body() != null) {
                        getGenerateToke.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Body Null", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<TokenGenerateModel> call, Throwable t) {
                    getGenerateToke.postValue(null);
                    Toast.makeText(activity, "t :" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return getGenerateToke;
    }

    private MutableLiveData<UsersSearchRoot> searchUsersMutableLiveData;

    public LiveData<UsersSearchRoot> getSearchUsers(Activity activity, String search) {
        searchUsersMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {
            CommonUtils.showProgress(activity, "Loading...");

            serviceApi.searchUsers(search).enqueue(new Callback<UsersSearchRoot>() {
                @Override
                public void onResponse(Call<UsersSearchRoot> call, Response<UsersSearchRoot> response) {
                    CommonUtils.dismissProgress();
                    if (response.body() != null) {
                        searchUsersMutableLiveData.postValue(response.body());
                        CommonUtils.dismissProgress();
                    } else {
                        Toast.makeText(activity, "Body Null", Toast.LENGTH_SHORT).show();
                        CommonUtils.dismissProgress();
                    }
                }

                @Override
                public void onFailure(Call<UsersSearchRoot> call, Throwable t) {
                    searchUsersMutableLiveData.postValue(null);
                    Toast.makeText(activity, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return searchUsersMutableLiveData;
    }


    private MutableLiveData<NearByLiveUsersRoot> nearByLiveUsersRootMutableLiveData;

    public LiveData<NearByLiveUsersRoot> getNearByUsers(Activity activity, String userId, String latitude, String longitude, String kickTo) {
        nearByLiveUsersRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {
//            CommonUtils.showProgress(activity, "Loading...");

            serviceApi.getNearByUsers(userId, latitude, longitude, kickTo).enqueue(new Callback<NearByLiveUsersRoot>() {
                @Override
                public void onResponse(Call<NearByLiveUsersRoot> call, Response<NearByLiveUsersRoot> response) {
                    if (response.body() != null) {
                        nearByLiveUsersRootMutableLiveData.postValue(response.body());
//                        CommonUtils.dismissProgress();
                    } else {

//                        CommonUtils.dismissProgress();
                    }
                }

                @Override
                public void onFailure(Call<NearByLiveUsersRoot> call, Throwable t) {
                    nearByLiveUsersRootMutableLiveData.postValue(null);

                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return nearByLiveUsersRootMutableLiveData;
    }

    private MutableLiveData<GetAllPopularRoot> getAllPopularRootMutableLiveData;

    public LiveData<GetAllPopularRoot> getAllPopularLiveUsers(Activity activity, String userId, String kickTo) {


        getAllPopularRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {
            // CommonUtils.showProgress(activity, "Loading...");
            serviceApi.getAllPopularLiveUsers(userId, kickTo).enqueue(new Callback<GetAllPopularRoot>() {
                @Override
                public void onResponse(Call<GetAllPopularRoot> call, Response<GetAllPopularRoot> response) {
                    if (response.body() != null) {
                        getAllPopularRootMutableLiveData.setValue(response.body());
                        //   CommonUtils.dismissProgress();
                    } else {
                        Toast.makeText(activity, "body is null", Toast.LENGTH_SHORT).show();
                        //  CommonUtils.dismissProgress();
                    }
                }

                @Override
                public void onFailure(Call<GetAllPopularRoot> call, Throwable t) {
                    getAllPopularRootMutableLiveData.setValue(null);
                    Toast.makeText(activity, "Failure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    //CommonUtils.dismissProgress();
                }
            });
        } else {
            CommonUtils.dismissProgress();
            Toast.makeText(activity, "Connect to Network", Toast.LENGTH_SHORT).show();
        }
        return getAllPopularRootMutableLiveData;
    }


    private MutableLiveData<UploadPostRoot> uploadPostRootMutableLiveData;

    public LiveData<UploadPostRoot> uploadPost(Activity activity, RequestBody userId, RequestBody description, RequestBody status, MultipartBody.Part image) {

        uploadPostRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.uploadPost(userId, description, status, image).enqueue(new Callback<UploadPostRoot>() {
                @Override
                public void onResponse(Call<UploadPostRoot> call, Response<UploadPostRoot> response) {

                    if (response.body() != null) {
                        uploadPostRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "body is null", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<UploadPostRoot> call, Throwable t) {
                    uploadPostRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "Failure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to internet", Toast.LENGTH_SHORT).show();
        }
        return uploadPostRootMutableLiveData;
    }


    private MutableLiveData<PostDetailsRoot> postDetailsRootMutableLiveData;

    public LiveData<PostDetailsRoot> getPostDetail(Activity activity, String userId, String otherId) {

        postDetailsRootMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {
            CommonUtils.showProgress(activity, "Loading...");

            serviceApi.getPostDetail(userId, otherId).enqueue(new Callback<PostDetailsRoot>() {
                @Override
                public void onResponse(Call<PostDetailsRoot> call, Response<PostDetailsRoot> response) {
                    CommonUtils.dismissProgress();

                    if (response.body() != null) {
                        postDetailsRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "body is null", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<PostDetailsRoot> call, Throwable t) {
                    CommonUtils.dismissProgress();
                    postDetailsRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "failure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to internet", Toast.LENGTH_SHORT).show();
        }
        return postDetailsRootMutableLiveData;
    }

    private MutableLiveData<SendOtpRoot> sendOtpRootMutableLiveData;

    public LiveData<SendOtpRoot> likeDislike(Activity activity, String userId, String feedId) {

        sendOtpRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {


            serviceApi.likeDislike(userId, feedId).enqueue(new Callback<SendOtpRoot>() {
                @Override
                public void onResponse(Call<SendOtpRoot> call, Response<SendOtpRoot> response) {

                    if (response.body() != null) {
                        sendOtpRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "body is null", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<SendOtpRoot> call, Throwable t) {

                    sendOtpRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "failure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return sendOtpRootMutableLiveData;
    }


    private MutableLiveData<SendOtpRoot> addCommentMutableLiveData;

    public LiveData<SendOtpRoot> addComment(Activity activity, String userId, String feedId, String comment) {

        addCommentMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {
            CommonUtils.showProgress(activity, "Loading...");
            serviceApi.addComment(userId, feedId, comment).enqueue(new Callback<SendOtpRoot>() {
                @Override
                public void onResponse(Call<SendOtpRoot> call, Response<SendOtpRoot> response) {
                    CommonUtils.dismissProgress();
                    if (response.body() != null) {
                        addCommentMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "body is null", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<SendOtpRoot> call, Throwable t) {
                    CommonUtils.dismissProgress();
                    addCommentMutableLiveData.postValue(null);
                    Toast.makeText(activity, "Failure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return addCommentMutableLiveData;
    }


    private MutableLiveData<GetCommentRoot> getCommentRootMutableLiveData;

    public LiveData<GetCommentRoot> getComments(Activity activity, String feedId) {

        getCommentRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {
            CommonUtils.showProgress(activity, "Loading...");

            serviceApi.getCommentsList(feedId).enqueue(new Callback<GetCommentRoot>() {
                @Override
                public void onResponse(Call<GetCommentRoot> call, Response<GetCommentRoot> response) {
                    CommonUtils.dismissProgress();
                    if (response.body() != null) {
                        getCommentRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "body is null", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetCommentRoot> call, Throwable t) {
                    CommonUtils.dismissProgress();
                    getCommentRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "Failure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return getCommentRootMutableLiveData;
    }


    private MutableLiveData<SendOtpRoot> deleteMutableLiveData;

    public LiveData<SendOtpRoot> deleteComment(Activity activity, String feedId, String commentId) {
        deleteMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {
            serviceApi.deleteComment(feedId, commentId).enqueue(new Callback<SendOtpRoot>() {
                @Override
                public void onResponse(Call<SendOtpRoot> call, Response<SendOtpRoot> response) {
                    if (response.body() != null) {
                        deleteMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "body is null", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<SendOtpRoot> call, Throwable t) {
                    Toast.makeText(activity, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    deleteMutableLiveData.postValue(null);
                }
            });

        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return deleteMutableLiveData;
    }


    private MutableLiveData<GetUserDetailRoot> userDetailRootMutableLiveData;

    public LiveData<GetUserDetailRoot> getUserDetail(Activity activity, String userId, String otherUserId, String kickTo) {
        userDetailRootMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {
            serviceApi.getUserDetail(userId, otherUserId, kickTo).enqueue(new Callback<GetUserDetailRoot>() {
                @Override
                public void onResponse(Call<GetUserDetailRoot> call, Response<GetUserDetailRoot> response) {
                    if (response.body() != null) {
                        userDetailRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "body is null", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetUserDetailRoot> call, Throwable t) {
                    Toast.makeText(activity, "tt : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("tt", "erroe :- " + t.getMessage());
                    userDetailRootMutableLiveData.postValue(null);
                }
            });

        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return userDetailRootMutableLiveData;
    }


    private MutableLiveData<SendOtpRoot> userFollowMutableLiveData;

    public LiveData<SendOtpRoot> followUsers(Activity activity, String userId, String otherUserId, String type) {
        userFollowMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {
            serviceApi.followUser(userId, otherUserId,type).enqueue(new Callback<SendOtpRoot>() {
                @Override
                public void onResponse(Call<SendOtpRoot> call, Response<SendOtpRoot> response) {
                    if (response.body() != null) {
                        userFollowMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Body is null", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<SendOtpRoot> call, Throwable t) {
                    //Toast.makeText(activity, "Failure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    userFollowMutableLiveData.postValue(null);
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return userFollowMutableLiveData;
    }


    private MutableLiveData<GetFollowingRoot> getFollowingRootMutableLiveData;

    public LiveData<GetFollowingRoot> getFollowing(Activity activity, String userId) {

        getFollowingRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getFollowing(userId).enqueue(new Callback<GetFollowingRoot>() {
                @Override
                public void onResponse(Call<GetFollowingRoot> call, Response<GetFollowingRoot> response) {

                    if (response.body() != null) {
                        getFollowingRootMutableLiveData.postValue(response.body());
                    } else {
                        getFollowingRootMutableLiveData.postValue(null);
                        Toast.makeText(activity, "body is null", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetFollowingRoot> call, Throwable t) {
                    getFollowingRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return getFollowingRootMutableLiveData;
    }


    private MutableLiveData<GetFansRoot> fansRootMutableLiveData;

    public LiveData<GetFansRoot> getFans(Activity activity, String userId) {

        fansRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getFans(userId).enqueue(new Callback<GetFansRoot>() {
                @Override
                public void onResponse(Call<GetFansRoot> call, Response<GetFansRoot> response) {

                    if (response.body() != null) {
                        fansRootMutableLiveData.postValue(response.body());
                    } else {
                        fansRootMutableLiveData.postValue(null);
                        Toast.makeText(activity, "body is  null", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetFansRoot> call, Throwable t) {
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    fansRootMutableLiveData.postValue(null);
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return fansRootMutableLiveData;
    }


    private MutableLiveData<GetFriendRoot> friendRootMutableLiveData;

    public LiveData<GetFriendRoot> getFriends(Activity activity, String userId) {

        friendRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getFriends(userId).enqueue(new Callback<GetFriendRoot>() {
                @Override
                public void onResponse(Call<GetFriendRoot> call, Response<GetFriendRoot> response) {

                    if (response.body() != null) {
                        friendRootMutableLiveData.postValue(response.body());
                    } else {
                        friendRootMutableLiveData.postValue(null);
                        Toast.makeText(activity, "body is  null", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetFriendRoot> call, Throwable t) {
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    friendRootMutableLiveData.postValue(null);
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return friendRootMutableLiveData;
    }


    private MutableLiveData<GetVisitorsRoot> getVisitorsRootMutableLiveData;

    public LiveData<GetVisitorsRoot> setVisitors(Activity activity, String userId, String otherUserId) {

        getVisitorsRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.setVisitor(userId, otherUserId).enqueue(new Callback<GetVisitorsRoot>() {
                @Override
                public void onResponse(Call<GetVisitorsRoot> call, Response<GetVisitorsRoot> response) {

                    if (response.body() != null) {
                        getVisitorsRootMutableLiveData.postValue(response.body());
                    } else {
                        getVisitorsRootMutableLiveData.postValue(null);
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetVisitorsRoot> call, Throwable t) {
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    getVisitorsRootMutableLiveData.postValue(null);
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return getVisitorsRootMutableLiveData;
    }


    private MutableLiveData<GetVisitorsRoot> getVisitorsMutable;

    public LiveData<GetVisitorsRoot> getVisitors(Activity activity, String userId) {

        getVisitorsMutable = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {
            CommonUtils.openDialog(activity);
            serviceApi.getVisitors(userId).enqueue(new Callback<GetVisitorsRoot>() {
                @Override
                public void onResponse(Call<GetVisitorsRoot> call, Response<GetVisitorsRoot> response) {
                    if (response.body() != null) {
                        getVisitorsMutable.postValue(response.body());
                        CommonUtils.dismissDialog();
                    } else {
                        getVisitorsMutable.postValue(null);
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                        CommonUtils.dismissDialog();
                    }
                }

                @Override
                public void onFailure(Call<GetVisitorsRoot> call, Throwable t) {
                    getVisitorsMutable.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("GetVisitors", "Exception : " + t.getMessage());
                    CommonUtils.dismissDialog();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return getVisitorsMutable;
    }


    private MutableLiveData<GetCoinRoot> getCoinRootMutableLiveData;

    public LiveData<GetCoinRoot> getCoins(Activity activity,String userId) {

        getCoinRootMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {
            CommonUtils.showProgress(activity, "Loading...");

            serviceApi.getCoins(userId).enqueue(new Callback<GetCoinRoot>() {
                @Override
                public void onResponse(Call<GetCoinRoot> call, Response<GetCoinRoot> response) {
                    CommonUtils.dismissProgress();
                    if (response.body() != null) {
                        getCoinRootMutableLiveData.setValue(response.body());
                        CommonUtils.dismissProgress();
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                        CommonUtils.dismissProgress();
                    }
                }

                @Override
                public void onFailure(Call<GetCoinRoot> call, Throwable t) {
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    CommonUtils.dismissProgress();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return getCoinRootMutableLiveData;
    }


    private MutableLiveData<GetLuckIdRoot> getLuckIdRootMutableLiveData;

    public LiveData<GetLuckIdRoot> getLuckyId(Activity activity, String userId) {

        getLuckIdRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getLuckId(userId).enqueue(new Callback<GetLuckIdRoot>() {
                @Override
                public void onResponse(Call<GetLuckIdRoot> call, Response<GetLuckIdRoot> response) {

                    if (response.body() != null) {
                        getLuckIdRootMutableLiveData.setValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetLuckIdRoot> call, Throwable t) {
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }

        return getLuckIdRootMutableLiveData;
    }


    private MutableLiveData<GetFramesRoot> getFramesRootMutableLiveData;

    public LiveData<GetFramesRoot> getFrames(Activity activity, String userId) {

        getFramesRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getFrames(userId).enqueue(new Callback<GetFramesRoot>() {
                @Override
                public void onResponse(Call<GetFramesRoot> call, Response<GetFramesRoot> response) {

                    if (response.body() != null) {
                        getFramesRootMutableLiveData.postValue(response.body());

                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<GetFramesRoot> call, Throwable t) {
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }

        return getFramesRootMutableLiveData;
    }


    private MutableLiveData<GetEmojiRoot> getEmojiRootMutableLiveData;

    public LiveData<GetEmojiRoot> getEmoji(Activity activity) {

        getEmojiRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getEmoji().enqueue(new Callback<GetEmojiRoot>() {
                @Override
                public void onResponse(Call<GetEmojiRoot> call, Response<GetEmojiRoot> response) {

                    if (response.body() != null) {
                        getEmojiRootMutableLiveData.postValue(response.body());

                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<GetEmojiRoot> call, Throwable t) {
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return getEmojiRootMutableLiveData;
    }


    private MutableLiveData<GetWalletRoot> getWalletRootMutableLiveData;

    public LiveData<GetWalletRoot> getWallet(Activity activity, String userId) {

        getWalletRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getWallet(userId).enqueue(new Callback<GetWalletRoot>() {
                @Override
                public void onResponse(Call<GetWalletRoot> call, Response<GetWalletRoot> response) {

                    if (response.body() != null) {
                        getWalletRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetWalletRoot> call, Throwable t) {
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    getWalletRootMutableLiveData.postValue(null);
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return getWalletRootMutableLiveData;
    }


    private MutableLiveData<AddWalletMoneyRoot> addWalletMoneyRootMutableLiveData;

    public LiveData<AddWalletMoneyRoot> addMoneyToWallet(Activity activity, String userId, String amount, String razorpay_order_id, String razorpay_payment_id, String razorpay_signature) {

        addWalletMoneyRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.addWalletMoney(userId, amount, razorpay_order_id, razorpay_payment_id, razorpay_signature).enqueue(new Callback<AddWalletMoneyRoot>() {
                @Override
                public void onResponse(Call<AddWalletMoneyRoot> call, Response<AddWalletMoneyRoot> response) {

                    if (response.body() != null) {
                        addWalletMoneyRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<AddWalletMoneyRoot> call, Throwable t) {
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    addWalletMoneyRootMutableLiveData.postValue(null);
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return addWalletMoneyRootMutableLiveData;
    }


    private MutableLiveData<GetSilverCoinRoot> getSilverCoinRootMutableLiveData;

    public LiveData<GetSilverCoinRoot> getSilverCoins(Activity activity) {
        getSilverCoinRootMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {
            serviceApi.getSilverCoins().enqueue(new Callback<GetSilverCoinRoot>() {
                @Override
                public void onResponse(Call<GetSilverCoinRoot> call, Response<GetSilverCoinRoot> response) {

                    if (response.body() != null) {
                        getSilverCoinRootMutableLiveData.setValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetSilverCoinRoot> call, Throwable t) {
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    getAllPopularRootMutableLiveData.postValue(null);
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }

        return getSilverCoinRootMutableLiveData;
    }


    private MutableLiveData<PurchaseSilverCoins> addSilverCoinsRootMutableLiveData;

    public LiveData<PurchaseSilverCoins> purchaseSilverCoins(Activity activity, String userId, String coinValue) {

        addSilverCoinsRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.puchaseSilverCoins(userId, coinValue).enqueue(new Callback<PurchaseSilverCoins>() {
                @Override
                public void onResponse(Call<PurchaseSilverCoins> call, Response<PurchaseSilverCoins> response) {

                    if (response.body() != null) {
                        addSilverCoinsRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<PurchaseSilverCoins> call, Throwable t) {
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    addSilverCoinsRootMutableLiveData.postValue(null);
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return addSilverCoinsRootMutableLiveData;
    }


    private MutableLiveData<PurchaseFramesRoot> purchaseFramesRootMutableLiveData;

    public LiveData<PurchaseFramesRoot> purchaseFrames(Activity activity, String userId, String frameId) {

        purchaseFramesRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.purchaseFrames(userId, frameId).enqueue(new Callback<PurchaseFramesRoot>() {
                @Override
                public void onResponse(Call<PurchaseFramesRoot> call, Response<PurchaseFramesRoot> response) {

                    if (response.body() != null) {
                        purchaseFramesRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<PurchaseFramesRoot> call, Throwable t) {
                    purchaseFramesRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return purchaseFramesRootMutableLiveData;
    }


    private MutableLiveData<PurchaseFramesRoot> getPurchaseFramesRootMutableLiveData;

    public LiveData<PurchaseFramesRoot> getPurchaseFrame(Activity activity, String userId) {

        getPurchaseFramesRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getPurchaseFrame(userId).enqueue(new Callback<PurchaseFramesRoot>() {
                @Override
                public void onResponse(Call<PurchaseFramesRoot> call, Response<PurchaseFramesRoot> response) {

                    if (response.body() != null) {
                        getPurchaseFramesRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<PurchaseFramesRoot> call, Throwable t) {
                    getPurchaseFramesRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onFailure: " + t.getMessage());
                }
            });

        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return getPurchaseFramesRootMutableLiveData;
    }


    private MutableLiveData<PurchaseCarsRoot> purchaseCarsRootMutableLiveData;

    public LiveData<PurchaseCarsRoot> purchaseCars(Activity activity, String userId, String luckyId) {

        purchaseCarsRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.purchaseLuckyId(userId, luckyId).enqueue(new Callback<PurchaseCarsRoot>() {
                @Override
                public void onResponse(Call<PurchaseCarsRoot> call, Response<PurchaseCarsRoot> response) {

                    if (response.body() != null) {
                        purchaseCarsRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<PurchaseCarsRoot> call, Throwable t) {
                    purchaseCarsRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return purchaseCarsRootMutableLiveData;
    }

    private MutableLiveData<PurchaseCarsRoot> getPurchaseCarsRootMutableLiveData;

    public LiveData<PurchaseCarsRoot> getPurchaseCar(Activity activity, String userId) {

        getPurchaseCarsRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getPurchaseLuckyId(userId).enqueue(new Callback<PurchaseCarsRoot>() {
                @Override
                public void onResponse(Call<PurchaseCarsRoot> call, Response<PurchaseCarsRoot> response) {

                    if (response.body() != null) {
                        getPurchaseCarsRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<PurchaseCarsRoot> call, Throwable t) {
                    getPurchaseCarsRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return getPurchaseCarsRootMutableLiveData;
    }


    private MutableLiveData<EntryEffectsRoot> entryEffectsRootMutableLiveData;

    public LiveData<EntryEffectsRoot> getGifs(Activity activity) {

        entryEffectsRootMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getGifs().enqueue(new Callback<EntryEffectsRoot>() {
                @Override
                public void onResponse(Call<EntryEffectsRoot> call, Response<EntryEffectsRoot> response) {

                    if (response.body() != null) {
                        entryEffectsRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<EntryEffectsRoot> call, Throwable t) {
                    entryEffectsRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return entryEffectsRootMutableLiveData;
    }


    private MutableLiveData<GetCarUserLevel> getUserLevelRootMutableLiveData;

    public LiveData<GetCarUserLevel> getCarLevel(Activity activity, String userId) {

        getUserLevelRootMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getCarLevel(userId).enqueue(new Callback<GetCarUserLevel>() {
                @Override
                public void onResponse(Call<GetCarUserLevel> call, Response<GetCarUserLevel> response) {

                    if (response.body() != null) {
                        getUserLevelRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                    Log.d("WEALTH", "wealth: " + "success");
                }

                @Override
                public void onFailure(Call<GetCarUserLevel> call, Throwable t) {
                    getUserLevelRootMutableLiveData.postValue(null);
                    Log.d("WEALTH", "wealth: " + t.getMessage());
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return getUserLevelRootMutableLiveData;
    }

    private MutableLiveData<GetFrameByLevel> getFrameLevelMutableLiveData;

    public LiveData<GetFrameByLevel> getFrameLevel(Activity activity, String userId) {

        getFrameLevelMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getFrameLevel(userId).enqueue(new Callback<GetFrameByLevel>() {
                @Override
                public void onResponse(Call<GetFrameByLevel> call, Response<GetFrameByLevel> response) {

                    if (response.body() != null) {
                        getFrameLevelMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetFrameByLevel> call, Throwable t) {
                    getFrameLevelMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return getFrameLevelMutableLiveData;
    }

    private MutableLiveData<GetColorByLevel> getColorIdLevelMutableLiveData;

    public LiveData<GetColorByLevel> getColorIdLevel(Activity activity, String userId) {

        getColorIdLevelMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getColorIdLevel(userId).enqueue(new Callback<GetColorByLevel>() {
                @Override
                public void onResponse(Call<GetColorByLevel> call, Response<GetColorByLevel> response) {

                    if (response.body() != null) {
                        getColorIdLevelMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetColorByLevel> call, Throwable t) {
                    getColorIdLevelMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return getColorIdLevelMutableLiveData;
    }


    private MutableLiveData<GetUserTalentLevelRoot> getUserTalentLevelRootMutableLiveData;

    public LiveData<GetUserTalentLevelRoot> getUserTalentLevel(Activity activity) {

        getUserTalentLevelRootMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getUserTalentLevel().enqueue(new Callback<GetUserTalentLevelRoot>() {
                @Override
                public void onResponse(Call<GetUserTalentLevelRoot> call, Response<GetUserTalentLevelRoot> response) {

                    if (response.body() != null) {
                        getUserTalentLevelRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetUserTalentLevelRoot> call, Throwable t) {
                    getUserTalentLevelRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return getUserTalentLevelRootMutableLiveData;
    }


    private MutableLiveData<PurchaseHistoryRoot> purchaseHistoryRootMutableLiveData;

    public LiveData<PurchaseHistoryRoot> getPurchaseHistory(Activity activity, String userId) {

        purchaseHistoryRootMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getPurchaseHistory(userId).enqueue(new Callback<PurchaseHistoryRoot>() {
                @Override
                public void onResponse(Call<PurchaseHistoryRoot> call, Response<PurchaseHistoryRoot> response) {

                    if (response.body() != null) {
                        purchaseHistoryRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<PurchaseHistoryRoot> call, Throwable t) {
                    purchaseHistoryRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return purchaseHistoryRootMutableLiveData;
    }


    private MutableLiveData<SendOtpRoot> removeUserMututaleLiveData;

    public LiveData<SendOtpRoot> removeUserAccount(Activity activity, String userId) {

        removeUserMututaleLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.removeUserAccount(userId).enqueue(new Callback<SendOtpRoot>() {
                @Override
                public void onResponse(Call<SendOtpRoot> call, Response<SendOtpRoot> response) {

                    if (response.body() != null) {
                        removeUserMututaleLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<SendOtpRoot> call, Throwable t) {
                    removeUserMututaleLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return removeUserMututaleLiveData;
    }

    private MutableLiveData<SendOtpRoot> verifyPhoneMututableLiveData;

    public LiveData<SendOtpRoot> verifyPhoneNumber(Activity activity, String userId, String newPhone, String otp) {

        verifyPhoneMututableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.verifyPhone(userId, newPhone, otp).enqueue(new Callback<SendOtpRoot>() {
                @Override
                public void onResponse(Call<SendOtpRoot> call, Response<SendOtpRoot> response) {

                    if (response.body() != null) {
                        verifyPhoneMututableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<SendOtpRoot> call, Throwable t) {
                    verifyPhoneMututableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return verifyPhoneMututableLiveData;
    }


    private MutableLiveData<GetFollowingLiveRoot> getFollowingLiveRootMutableLiveData;

    public LiveData<GetFollowingLiveRoot> getFollowingLiveUsers(Activity activity, String userId, String kickTo) {
        getFollowingLiveRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getFollowingLiveUsers(userId, kickTo).enqueue(new Callback<GetFollowingLiveRoot>() {
                @Override
                public void onResponse(Call<GetFollowingLiveRoot> call, Response<GetFollowingLiveRoot> response) {

                    if (response.body() != null) {
                        getFollowingLiveRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetFollowingLiveRoot> call, Throwable t) {
                    getFollowingLiveRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return getFollowingLiveRootMutableLiveData;
    }


    private MutableLiveData<GetFollowingLiveRoot> getFriendsLiveMutableLiveData;

    public LiveData<GetFollowingLiveRoot> getFriendsLive(Activity activity, String userId) {

        getFriendsLiveMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getFriendLiveUsers(userId).enqueue(new Callback<GetFollowingLiveRoot>() {
                @Override
                public void onResponse(Call<GetFollowingLiveRoot> call, Response<GetFollowingLiveRoot> response) {

                    if (response.body() != null) {
                        getFriendsLiveMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetFollowingLiveRoot> call, Throwable t) {
                    getFriendsLiveMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return getFriendsLiveMutableLiveData;
    }


    private MutableLiveData<FollowingFriendLiveRoot> getFriendFollowingLiveMutableLiveData;

    public LiveData<FollowingFriendLiveRoot> getFriendFollowingLiveUsers(Activity activity, String userId) {

        getFriendFollowingLiveMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getFriendFollowingLive(userId).enqueue(new Callback<FollowingFriendLiveRoot>() {
                @Override
                public void onResponse(Call<FollowingFriendLiveRoot> call, Response<FollowingFriendLiveRoot> response) {

                    if (response.body() != null) {
                        getFriendFollowingLiveMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<FollowingFriendLiveRoot> call, Throwable t) {
                    getFriendFollowingLiveMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return getFriendFollowingLiveMutableLiveData;
    }


    private MutableLiveData<UserReportRoot> userReportRootMutableLiveData;

    public LiveData<UserReportRoot> userReport(Activity activity, String userReport_catId, String userReport_SubcatId, String userId, String otherUserId) {

        userReportRootMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.userReport(userReport_catId, userReport_SubcatId, userId, otherUserId).enqueue(new Callback<UserReportRoot>() {
                @Override
                public void onResponse(Call<UserReportRoot> call, Response<UserReportRoot> response) {

                    if (response.body() != null) {
                        userReportRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<UserReportRoot> call, Throwable t) {
                    userReportRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return userReportRootMutableLiveData;
    }


    private MutableLiveData<SocialLoginRoot> socialLoginRootMutableLiveData;

    public LiveData<SocialLoginRoot> socialLogin(Activity activity, RequestBody social_id, RequestBody reg_id, RequestBody dev_id, RequestBody dev_type,
                                                 RequestBody phone, RequestBody name, RequestBody email, RequestBody continent, RequestBody country,RequestBody isAddingAccount, RequestBody userName,RequestBody facebookId, RequestBody snapchatId, RequestBody facebookUserName) {
//        public LiveData<SocialLoginRoot> socialLogin(Activity activity, RequestBody social_id, RequestBody reg_id, RequestBody dev_id, RequestBody dev_type,
//                RequestBody phone, RequestBody name, RequestBody email, RequestBody continent, MultipartBody.Part image, RequestBody country) {
        socialLoginRootMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.socialLogin(social_id, reg_id, dev_id, dev_type, phone, name, email, continent, country,isAddingAccount,userName,facebookId,snapchatId,facebookUserName).enqueue(new Callback<SocialLoginRoot>() {
                @Override
                public void onResponse(@NonNull Call<SocialLoginRoot> call, @NonNull Response<SocialLoginRoot> response) {
                    if (response.body() != null) {
                        socialLoginRootMutableLiveData.postValue(response.body());
                        Log.i("in LOGIN FRAG","iffff");
                    } else {
                        Log.i("in LOGIN FRAG","elseeee");
                        socialLoginRootMutableLiveData.postValue(null);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<SocialLoginRoot> call, @NonNull Throwable t) {
                    socialLoginRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.i("onFailure", " "+ t.getMessage());
                }
            });

        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return socialLoginRootMutableLiveData;
    }

    private MutableLiveData<SendOtpRoot> endLiveMutableLiveData;

    public LiveData<SendOtpRoot> endLiveCall(Activity activity, String liveId) {

        endLiveMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.endLiveCall(liveId).enqueue(new Callback<SendOtpRoot>() {
                @Override
                public void onResponse(Call<SendOtpRoot> call, Response<SendOtpRoot> response) {

                    if (response.body() != null) {
                        endLiveMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<SendOtpRoot> call, Throwable t) {
                    endLiveMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return endLiveMutableLiveData;
    }


    private MutableLiveData<UserReportCategoryRoot> getUserReportCategoryMutableLiveData;

    public LiveData<UserReportCategoryRoot> getUserReportCategory(Activity activity) {

        getUserReportCategoryMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getUserReportCategory().enqueue(new Callback<UserReportCategoryRoot>() {
                @Override
                public void onResponse(Call<UserReportCategoryRoot> call, Response<UserReportCategoryRoot> response) {

                    if (response.body() != null) {
                        getUserReportCategoryMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<UserReportCategoryRoot> call, Throwable t) {
                    getUserReportCategoryMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return getUserReportCategoryMutableLiveData;
    }

    private MutableLiveData<UserReportCategoryRoot> getSubCategoryMutableLiveData;

    public LiveData<UserReportCategoryRoot> getSubCategory(Activity activity) {

        getSubCategoryMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getSubCategory().enqueue(new Callback<UserReportCategoryRoot>() {
                @Override
                public void onResponse(Call<UserReportCategoryRoot> call, Response<UserReportCategoryRoot> response) {

                    if (response.body() != null) {
                        getSubCategoryMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<UserReportCategoryRoot> call, Throwable t) {
                    getSubCategoryMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return getSubCategoryMutableLiveData;
    }

    private MutableLiveData<GetUserImagesRoot> getUserImagesRootMutableLiveData;

    public LiveData<GetUserImagesRoot> getUserImages(Activity activity, String userId) {

        getUserImagesRootMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getUserImages(userId).enqueue(new Callback<GetUserImagesRoot>() {
                @Override
                public void onResponse(Call<GetUserImagesRoot> call, Response<GetUserImagesRoot> response) {

                    if (response.body() != null) {
                        getUserImagesRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetUserImagesRoot> call, Throwable t) {
                    getUserImagesRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return getUserImagesRootMutableLiveData;
    }


    private MutableLiveData<GetDiamondRoot> getDiamondRootMutableLiveData;

    public LiveData<GetDiamondRoot> getDaimonds(Activity activity, String userId) {
        getDiamondRootMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getDiamonds(userId).enqueue(new Callback<GetDiamondRoot>() {
                @Override
                public void onResponse(Call<GetDiamondRoot> call, Response<GetDiamondRoot> response) {

                    if (response.body() != null) {
                        getDiamondRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetDiamondRoot> call, Throwable t) {
                    getDiamondRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return getDiamondRootMutableLiveData;
    }


    private MutableLiveData<ExchangeCoinsRoot> exchangeCoinsRootMutableLiveData;

    public LiveData<ExchangeCoinsRoot> exchangeDiamondsToCoins(Activity activity, String userId, String coins) {
        exchangeCoinsRootMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.exchangeDiamondsToCoins(userId, coins).enqueue(new Callback<ExchangeCoinsRoot>() {
                @Override
                public void onResponse(Call<ExchangeCoinsRoot> call, Response<ExchangeCoinsRoot> response) {

                    if (response.body() != null) {
                        exchangeCoinsRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ExchangeCoinsRoot> call, Throwable t) {
                    exchangeCoinsRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return exchangeCoinsRootMutableLiveData;
    }


    private MutableLiveData<GetDailyTaskRoot> getDailyTaskMutableLiveData;

    public LiveData<GetDailyTaskRoot> getDailyTask(Activity activity, String userId) {
        getDailyTaskMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getDailyTask(userId).enqueue(new Callback<GetDailyTaskRoot>() {
                @Override
                public void onResponse(Call<GetDailyTaskRoot> call, Response<GetDailyTaskRoot> response) {

                    if (response.body() != null) {
                        getDailyTaskMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetDailyTaskRoot> call, Throwable t) {
                    getDailyTaskMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return getDailyTaskMutableLiveData;
    }

    private MutableLiveData<SetDailyTaskRoot> setDailyTaskRootMutableLiveData;

    public LiveData<SetDailyTaskRoot> setDailyTask(Activity activity, String userId, String day) {
        setDailyTaskRootMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.setDailyTask(userId, day).enqueue(new Callback<SetDailyTaskRoot>() {
                @Override
                public void onResponse(Call<SetDailyTaskRoot> call, Response<SetDailyTaskRoot> response) {

                    if (response.body() != null) {
                        setDailyTaskRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<SetDailyTaskRoot> call, Throwable t) {
                    setDailyTaskRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return setDailyTaskRootMutableLiveData;
    }

    private MutableLiveData<GetFramesRoot> applyFrameMutuableLiveData;

    public LiveData<GetFramesRoot> applyFrame(Activity activity, String userId, String frameId) {
        applyFrameMutuableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.applyFrame(userId, frameId).enqueue(new Callback<GetFramesRoot>() {
                @Override
                public void onResponse(Call<GetFramesRoot> call, Response<GetFramesRoot> response) {

                    if (response.body() != null) {
                        applyFrameMutuableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetFramesRoot> call, Throwable t) {
                    applyFrameMutuableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return applyFrameMutuableLiveData;
    }

    private MutableLiveData<GetFramesRoot> sendFrameMutableLiveData;

    public LiveData<GetFramesRoot> sendFrame(Activity activity, String userId, String otherUserId, String frameId) {
        sendFrameMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.sendFrame(userId, otherUserId, frameId).enqueue(new Callback<GetFramesRoot>() {
                @Override
                public void onResponse(Call<GetFramesRoot> call, Response<GetFramesRoot> response) {

                    if (response.body() != null) {
                        sendFrameMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetFramesRoot> call, Throwable t) {
                    sendFrameMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return sendFrameMutableLiveData;
    }

    private MutableLiveData<GetAppliedFrameRoot> getAppliedFrameRootMutableLiveData;

    public LiveData<GetAppliedFrameRoot> getAppliedFrame(Activity activity, String userId) {
        getAppliedFrameRootMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getAppliedFrame(userId).enqueue(new Callback<GetAppliedFrameRoot>() {
                @Override
                public void onResponse(Call<GetAppliedFrameRoot> call, Response<GetAppliedFrameRoot> response) {

                    if (response.body() != null) {
                        getAppliedFrameRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetAppliedFrameRoot> call, Throwable t) {
                    getAppliedFrameRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return getAppliedFrameRootMutableLiveData;
    }

    private MutableLiveData<GetLuckIdRoot> applyLuckIdMutableLiveData;

    public LiveData<GetLuckIdRoot> applyLuckId(Activity activity, String userId, String luckyId) {
        applyLuckIdMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.applyLuckyId(userId, luckyId).enqueue(new Callback<GetLuckIdRoot>() {
                @Override
                public void onResponse(Call<GetLuckIdRoot> call, Response<GetLuckIdRoot> response) {

                    if (response.body() != null) {
                        applyLuckIdMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetLuckIdRoot> call, Throwable t) {
                    applyLuckIdMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return applyLuckIdMutableLiveData;
    }

    private MutableLiveData<GetAppliedLuckyIdRoot> getAppliedLuckyIdRootMutableLiveData;

    public LiveData<GetAppliedLuckyIdRoot> getAppliedLuckId(Activity activity, String userId) {
        getAppliedLuckyIdRootMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getAppliedLuckId(userId).enqueue(new Callback<GetAppliedLuckyIdRoot>() {
                @Override
                public void onResponse(Call<GetAppliedLuckyIdRoot> call, Response<GetAppliedLuckyIdRoot> response) {

                    if (response.body() != null) {
                        getAppliedLuckyIdRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetAppliedLuckyIdRoot> call, Throwable t) {
                    getAppliedLuckyIdRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return getAppliedLuckyIdRootMutableLiveData;
    }

    private MutableLiveData<GetLuckIdRoot> sendLuckIdMutableLiveData;

    public LiveData<GetLuckIdRoot> sendLuckId(Activity activity, String userId, String otherUserId, String luckyId) {
        sendLuckIdMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.sendLuckId(userId, otherUserId, luckyId).enqueue(new Callback<GetLuckIdRoot>() {
                @Override
                public void onResponse(Call<GetLuckIdRoot> call, Response<GetLuckIdRoot> response) {

                    if (response.body() != null) {
                        sendLuckIdMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetLuckIdRoot> call, Throwable t) {
                    sendLuckIdMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return sendLuckIdMutableLiveData;
    }

    private MutableLiveData<VipRoot> vipRootMutableLiveData;

    public LiveData<VipRoot> getVip(Activity activity, String userId) {
        vipRootMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getVip(userId).enqueue(new Callback<VipRoot>() {
                @Override
                public void onResponse(Call<VipRoot> call, Response<VipRoot> response) {

                    if (response.body() != null) {
                        vipRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<VipRoot> call, Throwable t) {
                    vipRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return vipRootMutableLiveData;
    }

    private MutableLiveData<BuyVipRoot> buyVipRootMutableLiveData;

    public LiveData<BuyVipRoot> buyVip(Activity activity, String userId, String vipId) {
        buyVipRootMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.buyVip(userId, vipId).enqueue(new Callback<BuyVipRoot>() {
                @Override
                public void onResponse(Call<BuyVipRoot> call, Response<BuyVipRoot> response) {

                    if (response.body() != null) {
                        buyVipRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<BuyVipRoot> call, Throwable t) {
                    buyVipRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return buyVipRootMutableLiveData;
    }

    private MutableLiveData<GetTopGifterRoot> getTopGifterRootMutableLiveData;

    public LiveData<GetTopGifterRoot> getTopGifterBadge(Activity activity) {
        getTopGifterRootMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getTopGifterBadge().enqueue(new Callback<GetTopGifterRoot>() {
                @Override
                public void onResponse(Call<GetTopGifterRoot> call, Response<GetTopGifterRoot> response) {

                    if (response.body() != null) {
                        getTopGifterRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetTopGifterRoot> call, Throwable t) {
                    getTopGifterRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return getTopGifterRootMutableLiveData;
    }


    private MutableLiveData<CreateFamilyRoot> createFamilyRootMutableLiveData;

    public LiveData<CreateFamilyRoot> createFamily(Activity activity, RequestBody userId, RequestBody familyName, RequestBody description, MultipartBody.Part image) {
        createFamilyRootMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.createFamily(userId, familyName, description, image).enqueue(new Callback<CreateFamilyRoot>() {
                @Override
                public void onResponse(Call<CreateFamilyRoot> call, Response<CreateFamilyRoot> response) {

                    if (response.body() != null) {
                        createFamilyRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<CreateFamilyRoot> call, Throwable t) {
                    createFamilyRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return createFamilyRootMutableLiveData;
    }

    private MutableLiveData<EDitFamilyModelClass> eDitFamilyModelClassMutableLiveData;

    public LiveData<EDitFamilyModelClass> editFamilyApi(Activity activity, RequestBody leaderId, RequestBody id, RequestBody familyName, RequestBody description, MultipartBody.Part image) {
        eDitFamilyModelClassMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.editFamily(leaderId, id, familyName, description, image).enqueue(new Callback<EDitFamilyModelClass>() {
                @Override
                public void onResponse(Call<EDitFamilyModelClass> call, Response<EDitFamilyModelClass> response) {

                    if (response.body() != null) {
                        eDitFamilyModelClassMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<EDitFamilyModelClass> call, Throwable t) {
                    eDitFamilyModelClassMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return eDitFamilyModelClassMutableLiveData;
    }

    private MutableLiveData<GetFamilyTopGiftersRoot> getAllFamilyRootMutableLiveData;

    public LiveData<GetFamilyTopGiftersRoot> getFamilyTopGifters(Activity activity, String type) {
        getAllFamilyRootMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getFamilyTopGifter(type).enqueue(new Callback<GetFamilyTopGiftersRoot>() {
                @Override
                public void onResponse(Call<GetFamilyTopGiftersRoot> call, Response<GetFamilyTopGiftersRoot> response) {

                    if (response.body() != null) {
                        getAllFamilyRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetFamilyTopGiftersRoot> call, Throwable t) {
                    getAllFamilyRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return getAllFamilyRootMutableLiveData;
    }

    private MutableLiveData<GetAllFamilyRoot> sendFamilyInvitationMutableLiveData;

    public LiveData<GetAllFamilyRoot> sendFamilyInvitation(Activity activity, String userId, String familyId) {
        sendFamilyInvitationMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.sendFamilyInvitation(userId, familyId).enqueue(new Callback<GetAllFamilyRoot>() {
                @Override
                public void onResponse(Call<GetAllFamilyRoot> call, Response<GetAllFamilyRoot> response) {

                    if (response.body() != null) {
                        sendFamilyInvitationMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetAllFamilyRoot> call, Throwable t) {
                    sendFamilyInvitationMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return sendFamilyInvitationMutableLiveData;
    }

    private MutableLiveData<GetInvitationsRoot> getInvitationsRootMutableLiveData;

    public LiveData<GetInvitationsRoot> getFamilyInvitation(Activity activity, String userId) {
        getInvitationsRootMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getFamilyInvitations(userId).enqueue(new Callback<GetInvitationsRoot>() {
                @Override
                public void onResponse(Call<GetInvitationsRoot> call, Response<GetInvitationsRoot> response) {

                    if (response.body() != null) {
                        getInvitationsRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetInvitationsRoot> call, Throwable t) {
                    getInvitationsRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return getInvitationsRootMutableLiveData;
    }

    //----------------------------------------------Show my Family-------------------------------------------------//

    private MutableLiveData<ShowMyFamilyModelClass> showMyFamilyModelClassMutableLiveData;

    public LiveData<ShowMyFamilyModelClass> getShowMyFamily(Activity activity, String userId) {
        showMyFamilyModelClassMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getShowMyFamilyDetails(userId).enqueue(new Callback<ShowMyFamilyModelClass>() {
                @Override
                public void onResponse(Call<ShowMyFamilyModelClass> call, Response<ShowMyFamilyModelClass> response) {

                    if (response.body() != null) {
                        showMyFamilyModelClassMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ShowMyFamilyModelClass> call, Throwable t) {
                    showMyFamilyModelClassMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return showMyFamilyModelClassMutableLiveData;
    }

    //----------------------------------------------blockUser-------------------------------------------------//
    private MutableLiveData<GetInvitationsRoot> blockUserMLD;

    public LiveData<GetInvitationsRoot> blockUser(Activity activity, String userId, String otherUserId) {
        blockUserMLD = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.blockUser(userId, otherUserId).enqueue(new Callback<GetInvitationsRoot>() {
                @Override
                public void onResponse(Call<GetInvitationsRoot> call, Response<GetInvitationsRoot> response) {
                    if (response.body() != null) {
                        blockUserMLD.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetInvitationsRoot> call, Throwable t) {
                    blockUserMLD.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }

        return blockUserMLD;
    }
    // ===================================getTopUser====================================//

    private MutableLiveData<RootMeet> getTopUserMLD;

    public LiveData<RootMeet> getTopUser(Activity activity) {
        getTopUserMLD = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getTopUSer().enqueue(new Callback<RootMeet>() {
                @Override
                public void onResponse(Call<RootMeet> call, Response<RootMeet> response) {
                    if (response.body() != null) {
                        getTopUserMLD.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RootMeet> call, Throwable t) {
                    getTopUserMLD.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }

        return getTopUserMLD;
    }

    private MutableLiveData<InvitationsResponseRoot> invitationsResponseRootMutableLiveData;

    public LiveData<InvitationsResponseRoot> responseInvitation(Activity activity, String userId, String requestId, String status) {
        invitationsResponseRootMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.responseInvitations(userId, requestId, status).enqueue(new Callback<InvitationsResponseRoot>() {
                @Override
                public void onResponse(Call<InvitationsResponseRoot> call, Response<InvitationsResponseRoot> response) {

                    if (response.body() != null) {
                        invitationsResponseRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<InvitationsResponseRoot> call, Throwable t) {
                    invitationsResponseRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return invitationsResponseRootMutableLiveData;
    }

    private MutableLiveData<GetInvitationsRoot> sendJoinInvitationsMutableLiveData;

    public LiveData<GetInvitationsRoot> sendJoinRequest(Activity activity, String userId, String familyId) {
        sendJoinInvitationsMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.sendJoinRequest(familyId, userId).enqueue(new Callback<GetInvitationsRoot>() {
                @Override
                public void onResponse(Call<GetInvitationsRoot> call, Response<GetInvitationsRoot> response) {

                    if (response.body() != null) {
                        sendJoinInvitationsMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetInvitationsRoot> call, Throwable t) {
                    sendJoinInvitationsMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return sendJoinInvitationsMutableLiveData;
    }

    private MutableLiveData<GetFamilyDetailsRoot> getFamilyDetailsRootMutableLiveData;

    public LiveData<GetFamilyDetailsRoot> getFamilyDetails(Activity activity, String familyId, String userId) {
        getFamilyDetailsRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getFamilyDetails(familyId, userId).enqueue(new Callback<GetFamilyDetailsRoot>() {
                @Override
                public void onResponse(Call<GetFamilyDetailsRoot> call, Response<GetFamilyDetailsRoot> response) {

                    if (response.body() != null) {
                        getFamilyDetailsRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetFamilyDetailsRoot> call, Throwable t) {
                    getFamilyDetailsRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return getFamilyDetailsRootMutableLiveData;
    }

    private MutableLiveData<GetFamilyDetailsRoot> leaveFamilyMutableLiveData;

    public LiveData<GetFamilyDetailsRoot> leaveFamily(Activity activity, String userId, String familyId) {
        leaveFamilyMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.leaveFamily(familyId, userId).enqueue(new Callback<GetFamilyDetailsRoot>() {
                @Override
                public void onResponse(Call<GetFamilyDetailsRoot> call, Response<GetFamilyDetailsRoot> response) {

                    if (response.body() != null) {
                        leaveFamilyMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetFamilyDetailsRoot> call, Throwable t) {
                    leaveFamilyMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return leaveFamilyMutableLiveData;
    }

    private MutableLiveData<GetJoinRequestRoot> getJoinRequestRootMutableLiveData;

    public LiveData<GetJoinRequestRoot> getJoinRequest(Activity activity, String leaderId) {
        getJoinRequestRootMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getJoinRequest(leaderId).enqueue(new Callback<GetJoinRequestRoot>() {
                @Override
                public void onResponse(Call<GetJoinRequestRoot> call, Response<GetJoinRequestRoot> response) {

                    if (response.body() != null) {
                        getJoinRequestRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetJoinRequestRoot> call, Throwable t) {
                    getJoinRequestRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return getJoinRequestRootMutableLiveData;
    }


    private MutableLiveData<ResponseJoinRequestRoot> responseJoinRequestRootMutableLiveData;

    public LiveData<ResponseJoinRequestRoot> responseJoinRequest(Activity activity, String requestId, String userId, String status) {
        responseJoinRequestRootMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.responseJoinRequest(requestId, userId, status).enqueue(new Callback<ResponseJoinRequestRoot>() {
                @Override
                public void onResponse(Call<ResponseJoinRequestRoot> call, Response<ResponseJoinRequestRoot> response) {

                    if (response.body() != null) {
                        responseJoinRequestRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseJoinRequestRoot> call, Throwable t) {
                    responseJoinRequestRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return responseJoinRequestRootMutableLiveData;
    }


    private MutableLiveData<GetLiveFamilyJoinersRoot> getLiveFamilyJoinersRootMutableLiveData;

    public LiveData<GetLiveFamilyJoinersRoot> getFamilyLiveJoiners(Activity activity, String familyId, String userId, String kickTo) {
        getLiveFamilyJoinersRootMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getFamilyLiveJoiners(familyId, userId, kickTo).enqueue(new Callback<GetLiveFamilyJoinersRoot>() {
                @Override
                public void onResponse(Call<GetLiveFamilyJoinersRoot> call, Response<GetLiveFamilyJoinersRoot> response) {

                    if (response.body() != null) {
                        getLiveFamilyJoinersRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetLiveFamilyJoinersRoot> call, Throwable t) {
                    getLiveFamilyJoinersRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return getLiveFamilyJoinersRootMutableLiveData;
    }


    private MutableLiveData<CreateEventRoot> createEventRootMutableLiveData;

    public LiveData<CreateEventRoot> createEvent(Activity activity, RequestBody event_topic, RequestBody userId, RequestBody description,
                                                 RequestBody event_startTime, RequestBody event_Type, MultipartBody.Part event_image) {
        createEventRootMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.createEvent(event_topic, userId, description, event_startTime, event_Type, event_image).enqueue(new Callback<CreateEventRoot>() {
                @Override
                public void onResponse(Call<CreateEventRoot> call, Response<CreateEventRoot> response) {

                    if (response.body() != null) {
                        createEventRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<CreateEventRoot> call, Throwable t) {
                    createEventRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("CREATEEVENT", "onFailure" + t.getMessage().toString());
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return createEventRootMutableLiveData;
    }


    //===========================================getBlockUser =================================//

    private MutableLiveData<RootBlocked> getBlockedUserMLD;

    public LiveData<RootBlocked> getBlockedUser(Activity activity, String userId) {
        getBlockedUserMLD = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {
            CommonUtils.openDialog(activity);

            serviceApi.getBlockedUser(userId).enqueue(new Callback<RootBlocked>() {
                @Override
                public void onResponse(Call<RootBlocked> call, Response<RootBlocked> response) {
                    if (response.body() != null) {
                        CommonUtils.dismissDialog();
                        getBlockedUserMLD.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RootBlocked> call, Throwable t) {
                    getBlockedUserMLD.postValue(null);
                    Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return getBlockedUserMLD;
    }

    //------------------------------------hideCountry------------------------------------------//
    private MutableLiveData<RootBlocked> hideCountry;

    public LiveData<RootBlocked> HidCountry(Activity activity, String userId) {
        hideCountry = new MutableLiveData<>();
        serviceApi.hideUnHideCountry(userId).enqueue(new Callback<RootBlocked>() {
            @Override
            public void onResponse(Call<RootBlocked> call, Response<RootBlocked> response) {
                if (response.body() != null) {

                    hideCountry.postValue(response.body());
                } else {
                    Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RootBlocked> call, Throwable t) {
                hideCountry.postValue(null);
                Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        return hideCountry;
    }

    //=======================================getLiveUserByCountry--------------------------------//

    private MutableLiveData<RootLiveUser> getLIveUserBycountry;

    public LiveData<RootLiveUser> getLiveUserByCountry(Activity activity, String country, String userId, String kickTo) {
        getLIveUserBycountry = new MutableLiveData<>();
        serviceApi.getLIveUserByCountry(country, userId, kickTo).enqueue(new Callback<RootLiveUser>() {
            @Override
            public void onResponse(Call<RootLiveUser> call, Response<RootLiveUser> response) {
                if (response.body() != null) {
                    getLIveUserBycountry.postValue(response.body());
                } else {
                    Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RootLiveUser> call, Throwable t) {
                getLIveUserBycountry.postValue(null);
                Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        return getLIveUserBycountry;
    }

    private MutableLiveData<GetEventsRoot> getEventsRootMutableLiveData;

    public LiveData<GetEventsRoot> getAllEvents(Activity activity, String userId) {
        getEventsRootMutableLiveData = new MutableLiveData<>();
        serviceApi.getAllEvents(userId).enqueue(new Callback<GetEventsRoot>() {
            @Override
            public void onResponse(Call<GetEventsRoot> call, Response<GetEventsRoot> response) {
                if (response.body() != null) {
                    getEventsRootMutableLiveData.postValue(response.body());
                } else {
                    Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetEventsRoot> call, Throwable t) {
                getEventsRootMutableLiveData.postValue(null);
                Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        return getEventsRootMutableLiveData;
    }


    private MutableLiveData<SendEventInviationRoot> sendEventInvitationMutableLiveData;

    public LiveData<SendEventInviationRoot> sendEventInvitation(Activity activity, String userId, String eventId) {
        sendEventInvitationMutableLiveData = new MutableLiveData<>();
        serviceApi.sendEventInvitation(userId, eventId).enqueue(new Callback<SendEventInviationRoot>() {
            @Override
            public void onResponse(Call<SendEventInviationRoot> call, Response<SendEventInviationRoot> response) {
                if (response.body() != null) {
                    sendEventInvitationMutableLiveData.postValue(response.body());
                } else {
                    Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SendEventInviationRoot> call, Throwable t) {
                sendEventInvitationMutableLiveData.postValue(null);
                Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return sendEventInvitationMutableLiveData;
    }

    private MutableLiveData<GetAllEventInvitationsRoot> getAllEventInvitationsRootMutableLiveData;

    public LiveData<GetAllEventInvitationsRoot> getAllEventsInvitations(Activity activity, String userId) {
        getAllEventInvitationsRootMutableLiveData = new MutableLiveData<>();
        serviceApi.getAllEventInvitation(userId).enqueue(new Callback<GetAllEventInvitationsRoot>() {
            @Override
            public void onResponse(Call<GetAllEventInvitationsRoot> call, Response<GetAllEventInvitationsRoot> response) {
                if (response.body() != null) {
                    getAllEventInvitationsRootMutableLiveData.postValue(response.body());
                } else {
                    Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetAllEventInvitationsRoot> call, Throwable t) {
                getAllEventInvitationsRootMutableLiveData.postValue(null);
                Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        return getAllEventInvitationsRootMutableLiveData;
    }

    private MutableLiveData<ResponseEventInvitationRoot> responseEventInvitationRootMutableLiveData;

    public LiveData<ResponseEventInvitationRoot> responseEventInvitation(Activity activity, String userId, String requestId, String status) {
        responseEventInvitationRootMutableLiveData = new MutableLiveData<>();
        serviceApi.responseEventInvitation(userId, requestId, status).enqueue(new Callback<ResponseEventInvitationRoot>() {
            @Override
            public void onResponse(Call<ResponseEventInvitationRoot> call, Response<ResponseEventInvitationRoot> response) {
                if (response.body() != null) {
                    responseEventInvitationRootMutableLiveData.postValue(response.body());
                } else {
                    Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseEventInvitationRoot> call, Throwable t) {
                responseEventInvitationRootMutableLiveData.postValue(null);
                Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
            }
        });
        return responseEventInvitationRootMutableLiveData;
    }

    private MutableLiveData<com.live.worldsocialintegrationapp.ModelClasses.RootNewUser> getNewUserMLD;

    public LiveData<com.live.worldsocialintegrationapp.ModelClasses.RootNewUser> getNewUser(Activity activity) {
        getNewUserMLD = new MutableLiveData<>();
        serviceApi.getNewUser().enqueue(new Callback<com.live.worldsocialintegrationapp.ModelClasses.RootNewUser>() {
            @Override
            public void onResponse(Call<com.live.worldsocialintegrationapp.ModelClasses.RootNewUser> call, Response<com.live.worldsocialintegrationapp.ModelClasses.RootNewUser> response) {
                if (response.body() != null) {
                    getNewUserMLD.postValue(response.body());
                } else {
                    Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<com.live.worldsocialintegrationapp.ModelClasses.RootNewUser> call, Throwable t) {
                getNewUserMLD.postValue(null);
                Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
            }
        });
        return getNewUserMLD;
    }

//    private MutableLiveData<GetAllEventInvitationsRoot> getAllEventInvitationsRootMutableLiveData;
//
//    public LiveData<GetAllEventInvitationsRoot> getAllEventsInvitations(Activity activity, String userId) {
//        getAllEventInvitationsRootMutableLiveData = new MutableLiveData<>();
//        serviceApi.getAllEventInvitation(userId).enqueue(new Callback<GetAllEventInvitationsRoot>() {
//            @Override
//            public void onResponse(Call<GetAllEventInvitationsRoot> call, Response<GetAllEventInvitationsRoot> response) {
//                if (response.body() != null) {
//                    getAllEventInvitationsRootMutableLiveData.postValue(response.body());
//                } else {
//                    Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<GetAllEventInvitationsRoot> call, Throwable t) {
//                getAllEventInvitationsRootMutableLiveData.postValue(null);
//                Toast.makeText(activity, "onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
//        return getAllEventInvitationsRootMutableLiveData;
//    }

    private MutableLiveData<EventsDetailsRoot> eventsDetailsRootMutableLiveData;

    public LiveData<EventsDetailsRoot> getEventDetail(Activity activity, String eventId, String userId) {
        eventsDetailsRootMutableLiveData = new MutableLiveData<>();
        serviceApi.getEventDetail(eventId, userId).enqueue(new Callback<EventsDetailsRoot>() {
            @Override
            public void onResponse(Call<EventsDetailsRoot> call, Response<EventsDetailsRoot> response) {
                if (response.body() != null) {
                    eventsDetailsRootMutableLiveData.postValue(response.body());
                } else {
                    Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EventsDetailsRoot> call, Throwable t) {
                eventsDetailsRootMutableLiveData.postValue(null);
                Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();

            }
        });
        return eventsDetailsRootMutableLiveData;
    }


    private MutableLiveData<SuscribeUnscribeRoot> suscribeUnscribeRootMutableLiveData;

    public LiveData<SuscribeUnscribeRoot> suscribeUnscribeEvent(Activity activity, String userId, String eventId) {
        suscribeUnscribeRootMutableLiveData = new MutableLiveData<>();
        serviceApi.suscribeUnscribeEvent(eventId, userId).enqueue(new Callback<SuscribeUnscribeRoot>() {
            @Override
            public void onResponse(Call<SuscribeUnscribeRoot> call, Response<SuscribeUnscribeRoot> response) {
                if (response.body() != null) {
                    suscribeUnscribeRootMutableLiveData.postValue(response.body());
                } else {
                    Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SuscribeUnscribeRoot> call, Throwable t) {
                suscribeUnscribeRootMutableLiveData.postValue(null);
                Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();

            }
        });
        return suscribeUnscribeRootMutableLiveData;
    }

    //=====================================getGiftWall----------------------------------------------//

    private MutableLiveData<RootGiftWall> getGiftWallMLD;

    public LiveData<RootGiftWall> getGiftWall(Activity activity) {

        getGiftWallMLD = new MutableLiveData<>();

        serviceApi.getGiftWall().enqueue(new Callback<RootGiftWall>() {
            @Override
            public void onResponse(Call<RootGiftWall> call, Response<RootGiftWall> response) {
                if (response.body() != null) {
                    getGiftWallMLD.postValue(response.body());
                } else {
                    Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RootGiftWall> call, Throwable t) {
                getGiftWallMLD.postValue(null);
                Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
            }

        });

        return getGiftWallMLD;

    }


    //==============================================lockUserLive======================================//

    private MutableLiveData<RootLockBroadCast> lockUserLIveMLD;

    public LiveData<RootLockBroadCast> LockUser(Activity activity, String userId, String liveId, String password) {
        lockUserLIveMLD = new MutableLiveData<>();

        serviceApi.lockUserLive(userId, liveId, password).enqueue(new Callback<RootLockBroadCast>() {
            @Override
            public void onResponse(Call<RootLockBroadCast> call, Response<RootLockBroadCast> response) {
                if (response.body() != null) {
                    lockUserLIveMLD.postValue(response.body());
                } else {
                    Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RootLockBroadCast> call, Throwable t) {
                lockUserLIveMLD.postValue(null);
                Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
            }
        });

        return lockUserLIveMLD;
    }


    private MutableLiveData<GetLiveBackgroundImagesRoot> getLiveBackgroundImagesRootMutableLiveData;

    public LiveData<GetLiveBackgroundImagesRoot> getLiveBackgroundImages(Activity activity) {
        getLiveBackgroundImagesRootMutableLiveData = new MutableLiveData<>();

        serviceApi.getLiveBackgroundImages().enqueue(new Callback<GetLiveBackgroundImagesRoot>() {
            @Override
            public void onResponse(Call<GetLiveBackgroundImagesRoot> call, Response<GetLiveBackgroundImagesRoot> response) {
                if (response.body() != null) {
                    getLiveBackgroundImagesRootMutableLiveData.postValue(response.body());
                } else {
                    Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetLiveBackgroundImagesRoot> call, Throwable t) {
                getLiveBackgroundImagesRootMutableLiveData.postValue(null);
                Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
            }
        });

        return getLiveBackgroundImagesRootMutableLiveData;
    }

    private MutableLiveData<GetLiveGiftHistoryRoot> getLiveGiftHistoryRootMutableLiveData;

    public LiveData<GetLiveGiftHistoryRoot> getLiveGiftHistory(Activity activity, String receiverId, String liveId) {
        getLiveGiftHistoryRootMutableLiveData = new MutableLiveData<>();

        serviceApi.getLiveGiftHistory(receiverId, liveId).enqueue(new Callback<GetLiveGiftHistoryRoot>() {
            @Override
            public void onResponse(Call<GetLiveGiftHistoryRoot> call, Response<GetLiveGiftHistoryRoot> response) {
                if (response.body() != null) {
                    getLiveGiftHistoryRootMutableLiveData.postValue(response.body());
                } else {
                    Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetLiveGiftHistoryRoot> call, Throwable t) {
                getLiveGiftHistoryRootMutableLiveData.postValue(null);
                Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
            }
        });

        return getLiveGiftHistoryRootMutableLiveData;
    }

    private MutableLiveData<SetLiveCoverImgRoot> setLiveCoverImgRootMutableLiveData;

    public LiveData<SetLiveCoverImgRoot> setLiveCoverImg(Activity activity, RequestBody userId, RequestBody liveId, RequestBody imageText, RequestBody imageTitle, MultipartBody.Part Liveimage) {
        setLiveCoverImgRootMutableLiveData = new MutableLiveData<>();

        serviceApi.setLiveCoverImg(userId, liveId, imageText, imageTitle, Liveimage).enqueue(new Callback<SetLiveCoverImgRoot>() {
            @Override
            public void onResponse(Call<SetLiveCoverImgRoot> call, Response<SetLiveCoverImgRoot> response) {
                if (response.body() != null) {
                    setLiveCoverImgRootMutableLiveData.postValue(response.body());
                } else {
                    Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SetLiveCoverImgRoot> call, Throwable t) {
                setLiveCoverImgRootMutableLiveData.postValue(null);

            }
        });

        return setLiveCoverImgRootMutableLiveData;
    }


    private MutableLiveData<LiveUserByIdRoot> LiveUserByIdMutableLiveData;

    public LiveData<LiveUserByIdRoot> getLiveUsersById(Activity activity, String userId, String kickToId) {

        LiveUserByIdMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getLiveUserById(userId, kickToId).enqueue(new Callback<LiveUserByIdRoot>() {
                @Override
                public void onResponse(Call<LiveUserByIdRoot> call, Response<LiveUserByIdRoot> response) {
                    if (response.body() != null) {
                        LiveUserByIdMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LiveUserByIdRoot> call, Throwable t) {
                    Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    LiveUserByIdMutableLiveData.postValue(null);
                }

            });

        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }

        return LiveUserByIdMutableLiveData;
    }


    private MutableLiveData<LiveUserHideUnhideRoot> liveUserHideUnhideRootMutableLiveData;

    public LiveData<LiveUserHideUnhideRoot> hideLiveUserRoom(Activity activity, String userId) {

        liveUserHideUnhideRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.hideUnhideLiveRooms(userId).enqueue(new Callback<LiveUserHideUnhideRoot>() {
                @Override
                public void onResponse(Call<LiveUserHideUnhideRoot> call, Response<LiveUserHideUnhideRoot> response) {
                    if (response.body() != null) {
                        liveUserHideUnhideRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LiveUserHideUnhideRoot> call, Throwable t) {
                    Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    liveUserHideUnhideRootMutableLiveData.postValue(null);
                }

            });

        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }

        return liveUserHideUnhideRootMutableLiveData;
    }

    private MutableLiveData<LuckBagRoot> luckBagRootMutableLiveData;

    public LiveData<LuckBagRoot> getLuckyBagDetail(Activity activity, String type) {

        luckBagRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getLuckBagDetail(type).enqueue(new Callback<LuckBagRoot>() {
                @Override
                public void onResponse(Call<LuckBagRoot> call, Response<LuckBagRoot> response) {
                    if (response.body() != null) {
                        luckBagRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LuckBagRoot> call, Throwable t) {
                    Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    luckBagRootMutableLiveData.postValue(null);
                }

            });

        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }

        return luckBagRootMutableLiveData;
    }

    private MutableLiveData<DeductCoinsRoot> deductCoinsRootMutableLiveData;

    public LiveData<DeductCoinsRoot> deductLuckbagCoins(Activity activity, String userId, String coins, String quantity, String type) {

        deductCoinsRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.DeductLuckBagCoins(userId, coins, quantity, type).enqueue(new Callback<DeductCoinsRoot>() {
                @Override
                public void onResponse(Call<DeductCoinsRoot> call, Response<DeductCoinsRoot> response) {
                    if (response.body() != null) {
                        deductCoinsRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<DeductCoinsRoot> call, Throwable t) {
                    Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();

                    deductCoinsRootMutableLiveData.postValue(null);
                }

            });

        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }

        return deductCoinsRootMutableLiveData;
    }

    private MutableLiveData<HitLuckyBagCoinsRoot> hitLuckyBagCoinsRootMutableLiveData;

    public LiveData<HitLuckyBagCoinsRoot> hitLuckBagCoins(Activity activity, String userId, String deductedId, String type) {

        hitLuckyBagCoinsRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.hitLuckyBagCoins(userId, deductedId, type).enqueue(new Callback<HitLuckyBagCoinsRoot>() {
                @Override
                public void onResponse(Call<HitLuckyBagCoinsRoot> call, Response<HitLuckyBagCoinsRoot> response) {
                    if (response.body() != null) {
                        hitLuckyBagCoinsRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<HitLuckyBagCoinsRoot> call, Throwable t) {
                    Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    hitLuckyBagCoinsRootMutableLiveData.postValue(null);
                }

            });

        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }

        return hitLuckyBagCoinsRootMutableLiveData;
    }


    private MutableLiveData<GetLiveThemeRoot> liveThemeRootMutableLiveData;

    public LiveData<GetLiveThemeRoot> getLiveTheme(Activity activity, String userId) {

        liveThemeRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getLiveTheme(userId).enqueue(new Callback<GetLiveThemeRoot>() {
                @Override
                public void onResponse(Call<GetLiveThemeRoot> call, Response<GetLiveThemeRoot> response) {

                    if (response.body() != null) {
                        liveThemeRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetLiveThemeRoot> call, Throwable t) {
                    liveThemeRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }

        return liveThemeRootMutableLiveData;
    }

    private MutableLiveData<GetLiveThemeRoot> puchaseLiveThemeMutableLiveData;

    public LiveData<GetLiveThemeRoot> purchaseLiveTheme(Activity activity, String userId, String frameId) {

        puchaseLiveThemeMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.purchaseLiveTheme(userId, frameId).enqueue(new Callback<GetLiveThemeRoot>() {
                @Override
                public void onResponse(Call<GetLiveThemeRoot> call, Response<GetLiveThemeRoot> response) {

                    if (response.body() != null) {
                        puchaseLiveThemeMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetLiveThemeRoot> call, Throwable t) {
                    puchaseLiveThemeMutableLiveData.postValue(null);
                    Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }

        return puchaseLiveThemeMutableLiveData;
    }


    private MutableLiveData<RemoveUserPostRoot> removeUserPostRootMutableLiveData;

    public LiveData<RemoveUserPostRoot> removeUserPost(Activity activity, String userId, String id) {

        removeUserPostRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.removeUserPost(userId, id).enqueue(new Callback<RemoveUserPostRoot>() {
                @Override
                public void onResponse(Call<RemoveUserPostRoot> call, Response<RemoveUserPostRoot> response) {

                    if (response.body() != null) {
                        removeUserPostRootMutableLiveData.postValue(response.body());
                    } else {
                        removeUserPostRootMutableLiveData.postValue(null);
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RemoveUserPostRoot> call, Throwable t) {
                    removeUserPostRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return removeUserPostRootMutableLiveData;
    }


    private MutableLiveData<VipImagesRoot> vipImagesRootMutableLiveData;

    public LiveData<VipImagesRoot> getVipImages(Activity activity, String userId) {

        vipImagesRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getVipImages(userId).enqueue(new Callback<VipImagesRoot>() {
                @Override
                public void onResponse(Call<VipImagesRoot> call, Response<VipImagesRoot> response) {

                    if (response.body() != null) {
                        vipImagesRootMutableLiveData.postValue(response.body());
                    } else {
                        vipImagesRootMutableLiveData.postValue(null);
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<VipImagesRoot> call, Throwable t) {
                    vipImagesRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return vipImagesRootMutableLiveData;
    }

    private MutableLiveData<WowsBoardRoot> wowsBoardRootMutableLiveData;

    public LiveData<WowsBoardRoot> getWowsBoard(Activity activity, String get, String type) {

        wowsBoardRootMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {
            serviceApi.getWowsBoard(get, type).enqueue(new Callback<WowsBoardRoot>() {
                @Override
                public void onResponse(Call<WowsBoardRoot> call, Response<WowsBoardRoot> response) {
                    if (response.body() != null) {
                        wowsBoardRootMutableLiveData.postValue(response.body());
                    } else {
                        wowsBoardRootMutableLiveData.postValue(null);

                    }
                }

                @Override
                public void onFailure(Call<WowsBoardRoot> call, Throwable t) {

                    wowsBoardRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return wowsBoardRootMutableLiveData;
    }

    private MutableLiveData<GiftWallLiveUsersRoot> giftWallLiveUsersRootMutableLiveData;

    public LiveData<GiftWallLiveUsersRoot> getGiftWallLiveUsers(Activity activity) {
        giftWallLiveUsersRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getGiftWallLiveUsers().enqueue(new Callback<GiftWallLiveUsersRoot>() {
                @Override
                public void onResponse(Call<GiftWallLiveUsersRoot> call, Response<GiftWallLiveUsersRoot> response) {
                    if (response.body() != null) {
                        giftWallLiveUsersRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GiftWallLiveUsersRoot> call, Throwable t) {
                    giftWallLiveUsersRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }

        return giftWallLiveUsersRootMutableLiveData;

    }

    private MutableLiveData<GenerateOrderRoot> generateOrderRootMutableLiveData;

    public LiveData<GenerateOrderRoot> generateOrder(Activity activity, String amount) {

        generateOrderRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.generateOrder(amount).enqueue(new Callback<GenerateOrderRoot>() {
                @Override
                public void onResponse(Call<GenerateOrderRoot> call, Response<GenerateOrderRoot> response) {
                    if (response.body() != null) {
                        generateOrderRootMutableLiveData.postValue(response.body());
                    } else {
                        Log.i("Razorpayzzzzzzzz","zzzzzzzzzzz 11111");
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GenerateOrderRoot> call, Throwable t) {
                    generateOrderRootMutableLiveData.postValue(null);
                    Log.i("Razorpayzzzzzzzz","zzzzzzzzzzz 222222222");
                    Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }

        return generateOrderRootMutableLiveData;

    }

    private MutableLiveData<GetLiveGalleryRoot> getLiveGalleryRootMutableLiveData;

    public LiveData<GetLiveGalleryRoot> getGallery(Activity activity, String userId) {

        getLiveGalleryRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getGallery(userId).enqueue(new Callback<GetLiveGalleryRoot>() {
                @Override
                public void onResponse(Call<GetLiveGalleryRoot> call, Response<GetLiveGalleryRoot> response) {
                    if (response.body() != null) {
                        getLiveGalleryRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetLiveGalleryRoot> call, Throwable t) {
                    getLiveGalleryRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }

        return getLiveGalleryRootMutableLiveData;

    }

    private MutableLiveData<PurchaseGalleryRoot> purchaseGalleryRootMutableLiveData;

    public LiveData<PurchaseGalleryRoot> purchaseGallery(Activity activity, RequestBody permissionId, RequestBody userId, MultipartBody.Part image) {

        purchaseGalleryRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.purchaseGallery(permissionId, userId, image).enqueue(new Callback<PurchaseGalleryRoot>() {
                @Override
                public void onResponse(Call<PurchaseGalleryRoot> call, Response<PurchaseGalleryRoot> response) {
                    if (response.body() != null) {
                        purchaseGalleryRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<PurchaseGalleryRoot> call, Throwable t) {
                    purchaseGalleryRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }

        return purchaseGalleryRootMutableLiveData;

    }

    private MutableLiveData<SendGalleryRoot> sendGalleryRootMutableLiveData;

    public LiveData<SendGalleryRoot> sendGallery(Activity activity, RequestBody userId, RequestBody otherUserId, RequestBody permissionId, MultipartBody.Part image) {

        sendGalleryRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.sendGallery(userId, otherUserId, permissionId,image).enqueue(new Callback<SendGalleryRoot>() {
                @Override
                public void onResponse(Call<SendGalleryRoot> call, Response<SendGalleryRoot> response) {
                    if (response.body() != null) {
                        sendGalleryRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<SendGalleryRoot> call, Throwable t) {
                    sendGalleryRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }

        return sendGalleryRootMutableLiveData;

    }

    private MutableLiveData<SendLiveTheme> sendLiveThemeMutableLiveData;

    public LiveData<SendLiveTheme> sendLiveTheme(Activity activity, String userId, String otherUserId, String themeId) {

        sendLiveThemeMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.sendTheme(userId, otherUserId, themeId).enqueue(new Callback<SendLiveTheme>() {
                @Override
                public void onResponse(Call<SendLiveTheme> call, Response<SendLiveTheme> response) {
                    if (response.body() != null) {
                        sendLiveThemeMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<SendLiveTheme> call, Throwable t) {
                    sendLiveThemeMutableLiveData.postValue(null);
                    Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }

        return sendLiveThemeMutableLiveData;

    }


    private MutableLiveData<KickOutLiveRoot> kickOutLiveRootMutableLiveData;

    public LiveData<KickOutLiveRoot> kickOutLiveUser(Activity activity, String kickToId, String kickById, String liveId) {
        kickOutLiveRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {
            serviceApi.liveUserKickOut(kickToId, kickById, liveId).enqueue(new Callback<KickOutLiveRoot>() {
                @Override
                public void onResponse(Call<KickOutLiveRoot> call, Response<KickOutLiveRoot> response) {
                    if (response.body() != null) {
                        kickOutLiveRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<KickOutLiveRoot> call, Throwable t) {
                    Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    kickOutLiveRootMutableLiveData.postValue(null);
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return kickOutLiveRootMutableLiveData;
    }

    private MutableLiveData<GetAllPostsRoot> getAllPostsRootMutableLiveData;

    public LiveData<GetAllPostsRoot> getAllPosts(Activity activity, String userId) {
        getAllPostsRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {
            serviceApi.getAllPosts(userId).enqueue(new Callback<GetAllPostsRoot>() {
                @Override
                public void onResponse(Call<GetAllPostsRoot> call, Response<GetAllPostsRoot> response) {
                    if (response.body() != null) {
                        getAllPostsRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetAllPostsRoot> call, Throwable t) {

                    getAllPostsRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return getAllPostsRootMutableLiveData;
    }


    private MutableLiveData<GetFriendsFollowingPostsRoot> getFriendsFollowingPostsRootMutableLiveData;

    public LiveData<GetFriendsFollowingPostsRoot> getFriendsFollowingPosts(Activity activity, String userId) {
        getFriendsFollowingPostsRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {
            serviceApi.getFriendsFollowingPosts(userId).enqueue(new Callback<GetFriendsFollowingPostsRoot>() {
                @Override
                public void onResponse(Call<GetFriendsFollowingPostsRoot> call, Response<GetFriendsFollowingPostsRoot> response) {
                    if (response.body() != null) {
                        getFriendsFollowingPostsRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetFriendsFollowingPostsRoot> call, Throwable t) {

                    getFriendsFollowingPostsRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }

        return getFriendsFollowingPostsRootMutableLiveData;
    }


    private MutableLiveData<FirebaseSendReqRoot> sendChatRequestMutableLiveData;

    public LiveData<FirebaseSendReqRoot> sendChatRequest(Activity activity, String userId, String otheruserId) {
        sendChatRequestMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {
            serviceApi.sendChatReq(userId, otheruserId).enqueue(new Callback<FirebaseSendReqRoot>() {
                @Override
                public void onResponse(Call<FirebaseSendReqRoot> call, Response<FirebaseSendReqRoot> response) {
                    if (response.body() != null) {
                        sendChatRequestMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<FirebaseSendReqRoot> call, Throwable t) {

                    sendChatRequestMutableLiveData.postValue(null);
                    Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }

        return sendChatRequestMutableLiveData;
    }


    private MutableLiveData<GetTotalSilverCoinsRoot> getTotalSilverCoinsRootMutableLiveData;

    public LiveData<GetTotalSilverCoinsRoot> getTotalSilverCoins(Activity activity, String userId) {

        getTotalSilverCoinsRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {
            serviceApi.getTotalSilverCoins(userId).enqueue(new Callback<GetTotalSilverCoinsRoot>() {
                @Override
                public void onResponse(Call<GetTotalSilverCoinsRoot> call, Response<GetTotalSilverCoinsRoot> response) {
                    if (response.body() != null) {
                        getTotalSilverCoinsRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetTotalSilverCoinsRoot> call, Throwable t) {
                    getTotalSilverCoinsRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }

        return getTotalSilverCoinsRootMutableLiveData;

    }

    private MutableLiveData<CreateSuperLuckyBagRoot> createSuperLuckyBagRootMutableLiveData;

    public LiveData<CreateSuperLuckyBagRoot> createSuperLuckyBag(Activity activity, String userId, String amount, String liveId) {

        createSuperLuckyBagRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {
            serviceApi.createSuperLuckBag(userId, amount, liveId).enqueue(new Callback<CreateSuperLuckyBagRoot>() {
                @Override
                public void onResponse(Call<CreateSuperLuckyBagRoot> call, Response<CreateSuperLuckyBagRoot> response) {
                    if (response.body() != null) {
                        createSuperLuckyBagRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<CreateSuperLuckyBagRoot> call, Throwable t) {
                    createSuperLuckyBagRootMutableLiveData.postValue(null);
                }
            });
        }

        return createSuperLuckyBagRootMutableLiveData;
    }

    private MutableLiveData<SuperLuckyBagDetails> superLuckyBagDetailsMutableLiveData;

    public LiveData<SuperLuckyBagDetails> getSuperLuckyBagDetails(Activity activity, String userId) {

        superLuckyBagDetailsMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {
            serviceApi.getSuperLuckyBagDetails(userId).enqueue(new Callback<SuperLuckyBagDetails>() {
                @Override
                public void onResponse(Call<SuperLuckyBagDetails> call, Response<SuperLuckyBagDetails> response) {
                    if (response.body() != null) {
                        superLuckyBagDetailsMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<SuperLuckyBagDetails> call, Throwable t) {
                    superLuckyBagDetailsMutableLiveData.postValue(null);
                }
            });
        }

        return superLuckyBagDetailsMutableLiveData;
    }


    private MutableLiveData<HitSuperLuckyBagRoot> hitSuperLuckyBagRootMutableLiveData;

    public LiveData<HitSuperLuckyBagRoot> hitSuperLuckyBag(Activity activity, String userId, String luckybagId) {
        hitSuperLuckyBagRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {
            serviceApi.hitSuperLuckyBag(userId, luckybagId).enqueue(new Callback<HitSuperLuckyBagRoot>() {
                @Override
                public void onResponse(Call<HitSuperLuckyBagRoot> call, Response<HitSuperLuckyBagRoot> response) {

                    if (response.body() != null) {
                        hitSuperLuckyBagRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<HitSuperLuckyBagRoot> call, Throwable t) {
                    hitSuperLuckyBagRootMutableLiveData.postValue(null);
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }

        return hitSuperLuckyBagRootMutableLiveData;
    }

    private MutableLiveData<BannerSliderRoot> bannerSliderRootMutableLiveData;

    public LiveData<BannerSliderRoot> getBanners(Activity activity) {
        bannerSliderRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {
            serviceApi.getGamesBanner().enqueue(new Callback<BannerSliderRoot>() {
                @Override
                public void onResponse(Call<BannerSliderRoot> call, Response<BannerSliderRoot> response) {

                    if (response.body() != null) {
                        bannerSliderRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<BannerSliderRoot> call, Throwable t) {
                    bannerSliderRootMutableLiveData.postValue(null);
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }

        return bannerSliderRootMutableLiveData;
    }


    private MutableLiveData<GetLiveDiamondRoot> liveDiamondRootMutableLiveData;

    public LiveData<GetLiveDiamondRoot> getLiveDiamonds(Activity activity, String liveId, String type) {

        liveDiamondRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {
            serviceApi.getLiveDiamonds(liveId, type).enqueue(new Callback<GetLiveDiamondRoot>() {
                @Override
                public void onResponse(Call<GetLiveDiamondRoot> call, Response<GetLiveDiamondRoot> response) {

                    if (response.body() != null) {
                        liveDiamondRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                        liveDiamondRootMutableLiveData.postValue(null);
                    }
                }

                @Override
                public void onFailure(Call<GetLiveDiamondRoot> call, Throwable t) {
                    liveDiamondRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }


        return liveDiamondRootMutableLiveData;
    }

    private MutableLiveData<GetLiveTotalDiamondRoot> liveTotalDiamondRootMutableLiveData;

    public LiveData<GetLiveTotalDiamondRoot> getLiveTotalDiamonds(Activity activity, String liveId) {

        liveTotalDiamondRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getLiveTotalDiamonds(liveId).enqueue(new Callback<GetLiveTotalDiamondRoot>() {
                @Override
                public void onResponse(Call<GetLiveTotalDiamondRoot> call, Response<GetLiveTotalDiamondRoot> response) {

                    if (response.body() != null) {
                        liveTotalDiamondRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                        liveTotalDiamondRootMutableLiveData.postValue(null);
                    }
                }

                @Override
                public void onFailure(Call<GetLiveTotalDiamondRoot> call, Throwable t) {
                    liveTotalDiamondRootMutableLiveData.postValue(null);
                    Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }


        return liveTotalDiamondRootMutableLiveData;
    }


    private MutableLiveData<SendFileInChatRoot> sendFileInChatRootMutableLiveData;

    public LiveData<SendFileInChatRoot> sendFileInChat(Activity activity, RequestBody sendersenderId, RequestBody receiverId, RequestBody fileType, MultipartBody.Part file) {

        sendFileInChatRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.sendFileInChat(sendersenderId, receiverId, fileType, file).enqueue(new Callback<SendFileInChatRoot>() {
                @Override
                public void onResponse(Call<SendFileInChatRoot> call, Response<SendFileInChatRoot> response) {
                    if (response.body() != null) {
                        sendFileInChatRootMutableLiveData.postValue(response.body());
                    } else {
                        sendFileInChatRootMutableLiveData.postValue(null);
                    }
                }

                @Override
                public void onFailure(Call<SendFileInChatRoot> call, Throwable t) {
                    sendFileInChatRootMutableLiveData.postValue(null);
                }
            });


        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return sendFileInChatRootMutableLiveData;
    }


    private MutableLiveData<GetChatFilesRoot> getSendFileInChatRootMutableLiveData;

    public LiveData<GetChatFilesRoot> getChatFiles(Activity activity, String receiverId) {

        getSendFileInChatRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getChatFiles(receiverId).enqueue(new Callback<GetChatFilesRoot>() {
                @Override
                public void onResponse(Call<GetChatFilesRoot> call, Response<GetChatFilesRoot> response) {
                    if (response.body() != null) {
                        getSendFileInChatRootMutableLiveData.postValue(response.body());
                    } else {
                        getSendFileInChatRootMutableLiveData.postValue(null);
                    }
                }

                @Override
                public void onFailure(Call<GetChatFilesRoot> call, Throwable t) {
                    getSendFileInChatRootMutableLiveData.postValue(null);
                }
            });


        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return getSendFileInChatRootMutableLiveData;
    }


    private MutableLiveData<AdminPanelStatusRoot> getAdminPanelStatusMutableLiveData;

    public LiveData<AdminPanelStatusRoot> getAdminStatus(Activity activity) {

        getAdminPanelStatusMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getAdminPanelStatus().enqueue(new Callback<AdminPanelStatusRoot>() {
                @Override
                public void onResponse(Call<AdminPanelStatusRoot> call, Response<AdminPanelStatusRoot> response) {
                    if (response.body() != null) {
                        getAdminPanelStatusMutableLiveData.postValue(response.body());
                    } else {
                        getAdminPanelStatusMutableLiveData.postValue(null);
                    }
                }

                @Override
                public void onFailure(Call<AdminPanelStatusRoot> call, Throwable t) {
                    getAdminPanelStatusMutableLiveData.postValue(null);
                }
            });


        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return getAdminPanelStatusMutableLiveData;
    }


    private MutableLiveData<GetQuestionRoot> getQuestionRootMutableLiveData;

    public LiveData<GetQuestionRoot> getQuestions(Activity activity) {

        getQuestionRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getOnlineSerQuestions().enqueue(new Callback<GetQuestionRoot>() {
                @Override
                public void onResponse(Call<GetQuestionRoot> call, Response<GetQuestionRoot> response) {
                    if (response.body() != null) {
                        getQuestionRootMutableLiveData.postValue(response.body());
                    } else {
                        getQuestionRootMutableLiveData.postValue(null);
                    }
                }

                @Override
                public void onFailure(Call<GetQuestionRoot> call, Throwable t) {
                    getQuestionRootMutableLiveData.postValue(null);
                }
            });


        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return getQuestionRootMutableLiveData;
    }


    private MutableLiveData<GetAnswerRoot> getAnswerRootMutableLiveData;

    public LiveData<GetAnswerRoot> getAnwser(Activity activity, String qusId, String userId) {

        getAnswerRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.getAnswer(qusId, userId).enqueue(new Callback<GetAnswerRoot>() {
                @Override
                public void onResponse(Call<GetAnswerRoot> call, Response<GetAnswerRoot> response) {
                    if (response.body() != null) {
                        getAnswerRootMutableLiveData.postValue(response.body());
                    } else {
                        getAnswerRootMutableLiveData.postValue(null);
                    }
                }

                @Override
                public void onFailure(Call<GetAnswerRoot> call, Throwable t) {
                    getAnswerRootMutableLiveData.postValue(null);
                }
            });


        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return getAnswerRootMutableLiveData;
    }


    private MutableLiveData<ChatNotificationRoot> chatNotificationRootMutableLiveData;

    public LiveData<ChatNotificationRoot> sendChatNotification(Activity activity, String notifyBy, String notifyTo) {

        chatNotificationRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.sendChatNotification(notifyBy, notifyTo).enqueue(new Callback<ChatNotificationRoot>() {
                @Override
                public void onResponse(Call<ChatNotificationRoot> call, Response<ChatNotificationRoot> response) {
                    if (response.body() != null) {
                        chatNotificationRootMutableLiveData.postValue(response.body());
                    } else {
                        chatNotificationRootMutableLiveData.postValue(null);
                    }
                }

                @Override
                public void onFailure(Call<ChatNotificationRoot> call, Throwable t) {
                    chatNotificationRootMutableLiveData.postValue(null);
                }
            });


        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return chatNotificationRootMutableLiveData;
    }


    private MutableLiveData<SingleLiveUserRoot> singleLiveUserRootMutableLiveData;

    public LiveData<SingleLiveUserRoot> getSingleLiveUser(Activity activity, String otherUserId, String userId) {

        singleLiveUserRootMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {

            serviceApi.singleLiveUser(otherUserId, userId).enqueue(new Callback<SingleLiveUserRoot>() {
                @Override
                public void onResponse(Call<SingleLiveUserRoot> call, Response<SingleLiveUserRoot> response) {

                    if (response.body() != null) {
                        singleLiveUserRootMutableLiveData.postValue(response.body());
                    } else {
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                        singleLiveUserRootMutableLiveData.postValue(null);
                    }
                }

                @Override
                public void onFailure(Call<SingleLiveUserRoot> call, Throwable t) {
                    Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    singleLiveUserRootMutableLiveData.postValue(null);
                }
            });

        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }

        return singleLiveUserRootMutableLiveData;
    }


    private MutableLiveData<GetFamilyDetails> getFamilyDetailsMutableLiveData;

    public LiveData<GetFamilyDetails> getFamilyDetailsData(Activity activity, Integer type) {

        getFamilyDetailsMutableLiveData = new MutableLiveData<>();

        if (CommonUtils.isNetworkConnected(activity)) {
            serviceApi.getFamilyLevelDetail(type).enqueue(new Callback<GetFamilyDetails>() {
                @Override
                public void onResponse(Call<GetFamilyDetails> call, Response<GetFamilyDetails> response) {

                    if (response.body() != null) {
                        getFamilyDetailsMutableLiveData.postValue(response.body());
                    } else {
                        getFamilyDetailsMutableLiveData.postValue(null);
                        Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetFamilyDetails> call, Throwable t) {
                    getFamilyDetailsMutableLiveData.postValue(null);
                    Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return getFamilyDetailsMutableLiveData;
    }


    private MutableLiveData<Map> getAgencyMutableLiveData;

    public LiveData<Map> getApplyForHost(Activity activity, HashMap<String, RequestBody> data) {
        getAgencyMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {
            //CommonUtils.openDialog(activity);
            serviceApi.getApplyForHost(data).enqueue(new Callback<Map>() {
                @Override
                public void onResponse(Call<Map> call, Response<Map> response) {

                    if (response.body() != null) {
                        //CommonUtils.dismissDialog();
                        getAgencyMutableLiveData.postValue(response.body());
                    }
                }

                @Override
                public void onFailure(Call<Map> call, Throwable t) {

                    Log.i("Agora :Get live", t.getMessage());
                }
            });

        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return getAgencyMutableLiveData;
    }

    private MutableLiveData<AgencyRoot> getAgencyCode;

    public LiveData<AgencyRoot> getAgencyCode() {

        getAgencyCode = new MutableLiveData<>();

        serviceApi.getAgencyCode().enqueue(new Callback<AgencyRoot>() {

            @Override
            public void onResponse(Call<AgencyRoot> call, Response<AgencyRoot> response) {
                if (response.body() != null) {
                    getAgencyCode.postValue(response.body());
                } else {
                    getAgencyCode.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<AgencyRoot> call, Throwable t) {

                getAgencyCode.postValue(null);
            }
        });
        return getAgencyCode;
    }


    private MutableLiveData<Map> agencyMutableLiveData;

    public LiveData<Map> agencyLiveData(HashMap<String, RequestBody> data, MultipartBody.Part image, MultipartBody.Part aadharCardFront,
                                        MultipartBody.Part panCardFrontPhoto, MultipartBody.Part aadharCardBack, MultipartBody.Part govt_photoId_proof) {

        agencyMutableLiveData = new MutableLiveData<>();

        serviceApi.getApplyAgency(data, image, aadharCardFront, panCardFrontPhoto, aadharCardBack, govt_photoId_proof).enqueue(new Callback<Map>() {
            @Override
            public void onResponse(@NotNull Call<Map> call, @NotNull Response<Map> response) {

                agencyMutableLiveData.postValue(response.body());

            }

            @Override
            public void onFailure(@NotNull Call<Map> call, @NotNull Throwable t) {

                agencyMutableLiveData.postValue(null);

            }
        });

        return agencyMutableLiveData;
    }

    private MutableLiveData<ApplyForHostModelClass> applyForHostModelClassMutableLiveData;

    public LiveData<ApplyForHostModelClass> applyHost(String userId) {
        applyForHostModelClassMutableLiveData = new MutableLiveData<>();

        serviceApi.applyforHost(userId).enqueue(new Callback<ApplyForHostModelClass>() {
            @Override
            public void onResponse(Call<ApplyForHostModelClass> call, Response<ApplyForHostModelClass> response) {

                applyForHostModelClassMutableLiveData.postValue(response.body());

            }

            @Override
            public void onFailure(Call<ApplyForHostModelClass> call, Throwable t) {
                applyForHostModelClassMutableLiveData.postValue(null);
            }
        });
        return applyForHostModelClassMutableLiveData;
    }


    private MutableLiveData<DailyDateLiveRecord> dailyDateLiveRecordMutableLiveData;

    public LiveData<DailyDateLiveRecord> dailyDateLiveRecord(Activity activity, String userId, String date) {
        applyForHostModelClassMutableLiveData = new MutableLiveData<>();

        serviceApi.dailyLiveRecord(userId, date).enqueue(new Callback<DailyDateLiveRecord>() {
            @Override
            public void onResponse(Call<DailyDateLiveRecord> call, Response<DailyDateLiveRecord> response) {

                dailyDateLiveRecordMutableLiveData.postValue(response.body());

            }

            @Override
            public void onFailure(Call<DailyDateLiveRecord> call, Throwable t) {
                dailyDateLiveRecordMutableLiveData.postValue(null);
            }
        });
        return dailyDateLiveRecordMutableLiveData;
    }


    public MutableLiveData<HostApproveModelClass> hostApproveModelClassMutableLiveData;

    public LiveData<HostApproveModelClass> hostApprovedApi(Activity activity, String userId) {
        hostApproveModelClassMutableLiveData = new MutableLiveData<>();

        serviceApi.hostApprove(userId).enqueue(new Callback<HostApproveModelClass>() {
            @Override
            public void onResponse(Call<HostApproveModelClass> call, Response<HostApproveModelClass> response) {

                hostApproveModelClassMutableLiveData.postValue(response.body());

            }

            @Override
            public void onFailure(Call<HostApproveModelClass> call, Throwable t) {
                applyForHostModelClassMutableLiveData.postValue(null);
            }
        });
        return hostApproveModelClassMutableLiveData;
    }


    private MutableLiveData<DailyDateLiveRecord> dailyMonthlyDateLiveRecordMutableLiveData;

    public LiveData<DailyDateLiveRecord> monthlyLiveDateRecord(Activity activity, String userId, String date) {
        dailyMonthlyDateLiveRecordMutableLiveData = new MutableLiveData<>();

        serviceApi.monthlyLiveRecord(userId, date).enqueue(new Callback<DailyDateLiveRecord>() {
            @Override
            public void onResponse(Call<DailyDateLiveRecord> call, Response<DailyDateLiveRecord> response) {

                dailyMonthlyDateLiveRecordMutableLiveData.postValue(response.body());

            }

            @Override
            public void onFailure(Call<DailyDateLiveRecord> call, Throwable t) {
                dailyMonthlyDateLiveRecordMutableLiveData.postValue(null);
            }
        });
        return dailyMonthlyDateLiveRecordMutableLiveData;
    }


    private MutableLiveData<GetUserCoinModel> getUserCoinModelMutableLiveData;

    public LiveData<GetUserCoinModel> getUserCoinModelLiveData(Activity activity, String userId) {
        getUserCoinModelMutableLiveData = new MutableLiveData<>();

        serviceApi.getUserCoin(userId).enqueue(new Callback<GetUserCoinModel>() {
            @Override
            public void onResponse(Call<GetUserCoinModel> call, Response<GetUserCoinModel> response) {
                getUserCoinModelMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<GetUserCoinModel> call, Throwable t) {
                getUserCoinModelMutableLiveData.postValue(null);
            }
        });
        return getUserCoinModelMutableLiveData;
    }

    private MutableLiveData<SomeFunctionalityModel> someFunctionalityModelMutableLiveData;

    public LiveData<SomeFunctionalityModel> getSomeFunctionalityModel(Activity activity, String userId, String otherUserId) {
        someFunctionalityModelMutableLiveData = new MutableLiveData<>();
        serviceApi.someFunctionality(userId, otherUserId).enqueue(new Callback<SomeFunctionalityModel>() {
            @Override
            public void onResponse(Call<SomeFunctionalityModel> call, Response<SomeFunctionalityModel> response) {
                someFunctionalityModelMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<SomeFunctionalityModel> call, Throwable t) {
                someFunctionalityModelMutableLiveData.postValue(null);
            }
        });
        return someFunctionalityModelMutableLiveData;
    }

    /******************************  Ban User  ******************************/

    private MutableLiveData<GeneratedIdClass> generatedIdClassMutableLiveData;

    public LiveData<GeneratedIdClass> getGeneratedIdClassLiveData(Activity activity, String userId) {
        generatedIdClassMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {
            serviceApi.getGeneratedIdClass(userId).enqueue(new Callback<GeneratedIdClass>() {
                @Override
                public void onResponse(Call<GeneratedIdClass> call, Response<GeneratedIdClass> response) {
                    generatedIdClassMutableLiveData.postValue(response.body());
                    Log.i("SPlacsh","zzzzzzzzzzzzzzzzzzzz on response");
                }

                @Override
                public void onFailure(Call<GeneratedIdClass> call, Throwable t) {
                    generatedIdClassMutableLiveData.postValue(null);
                    Log.i("SPlacsh","zzzzzzzzzzzzzzzzzzzz on failure");
                    Log.i("SPlacsh","zzzzzzzzzzzzzzzzzzzz on failure" + t.getMessage());
                }
            });
        } else {
            Toast.makeText(activity, "Connect to network", Toast.LENGTH_SHORT).show();
        }
        return generatedIdClassMutableLiveData;
    }

    /******************************  SpinWheel Api ******************************/
    private MutableLiveData<SpinModelClass> spinModelClassMutableLiveData;

    public LiveData<SpinModelClass> getSpinWheelLiveData(Activity activity, String userId, String coins, String type) {
        spinModelClassMutableLiveData = new MutableLiveData<>();
        serviceApi.getSpinWheel(userId, coins, type).enqueue(new Callback<SpinModelClass>() {
            @Override
            public void onResponse(Call<SpinModelClass> call, Response<SpinModelClass> response) {
                spinModelClassMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<SpinModelClass> call, Throwable t) {
                spinModelClassMutableLiveData.postValue(null);
            }
        });
        return spinModelClassMutableLiveData;
    }


    /******************************  Check SpinWheel Api ******************************/
    private MutableLiveData<CheckSpinWheel> checkSpinWheelMutableLiveData;

    public LiveData<CheckSpinWheel> checkSpinWheelLiveData(Activity activity, String userId) {
        checkSpinWheelMutableLiveData = new MutableLiveData<>();
        serviceApi.checkSpinWheel(userId).enqueue(new Callback<CheckSpinWheel>() {
            @Override
            public void onResponse(Call<CheckSpinWheel> call, Response<CheckSpinWheel> response) {
                checkSpinWheelMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<CheckSpinWheel> call, Throwable t) {
                checkSpinWheelMutableLiveData.postValue(null);
            }
        });
        return checkSpinWheelMutableLiveData;
    }

    /******************************  Get SpinWheel Details View Model ******************************/
    private MutableLiveData<SpinWheelModelClass> spinWheelModelClassMutableLiveData;

    public LiveData<SpinWheelModelClass> getSpinWheelDetailsViewModel(Activity activity, String type) {
        spinWheelModelClassMutableLiveData = new MutableLiveData<>();
        serviceApi.getSpinWheelDetails(type).enqueue(new Callback<SpinWheelModelClass>() {
            @Override
            public void onResponse(Call<SpinWheelModelClass> call, Response<SpinWheelModelClass> response) {
                spinWheelModelClassMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<SpinWheelModelClass> call, Throwable t) {
                spinWheelModelClassMutableLiveData.postValue(null);
            }
        });
        return spinWheelModelClassMutableLiveData;
    }

    /******************************  Billing Record Coins View Model ******************************/
    private MutableLiveData<BillingRecordModelClass> billingRecordModelClassMutableLiveData;

    public LiveData<BillingRecordModelClass> getUserBillingRecordViewModel(Activity activity, String userId) {
        billingRecordModelClassMutableLiveData = new MutableLiveData<>();
        serviceApi.getuserBillingRecords(userId).enqueue(new Callback<BillingRecordModelClass>() {
            @Override
            public void onResponse(Call<BillingRecordModelClass> call, Response<BillingRecordModelClass> response) {
                billingRecordModelClassMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<BillingRecordModelClass> call, Throwable t) {
                billingRecordModelClassMutableLiveData.postValue(null);
            }
        });
        return billingRecordModelClassMutableLiveData;
    }

    /******************************  GiftWall Record Coins ******************************/
    private MutableLiveData<GiftWallReceiverModelClass> giftWallReceiverModelClassMutableLiveData;

    public LiveData<GiftWallReceiverModelClass> getGiftWallRecordViewModel(Activity activity, String userId) {
        giftWallReceiverModelClassMutableLiveData = new MutableLiveData<>();
        serviceApi.getGiftWallRecordClass(userId).enqueue(new Callback<GiftWallReceiverModelClass>() {
            @Override
            public void onResponse(Call<GiftWallReceiverModelClass> call, Response<GiftWallReceiverModelClass> response) {
                giftWallReceiverModelClassMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<GiftWallReceiverModelClass> call, Throwable t) {
                giftWallReceiverModelClassMutableLiveData.postValue(null);
            }
        });
        return giftWallReceiverModelClassMutableLiveData;
    }

    /******************************  Get Purchase Gallery ******************************/
    private MutableLiveData<GetPurchaseGallery> getPurchaseGalleryMutableLiveData;

    public LiveData<GetPurchaseGallery> getPurchaseFromGallery(Activity activity, String userId) {
        getPurchaseGalleryMutableLiveData = new MutableLiveData<>();
        serviceApi.getPurchaseGallery(userId).enqueue(new Callback<GetPurchaseGallery>() {
            @Override
            public void onResponse(Call<GetPurchaseGallery> call, Response<GetPurchaseGallery> response) {
                getPurchaseGalleryMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<GetPurchaseGallery> call, Throwable t) {
                getPurchaseGalleryMutableLiveData.postValue(null);
            }
        });
        return getPurchaseGalleryMutableLiveData;
    }


    /******************************  Get Purchase Gallery ******************************/
    private MutableLiveData<ApplyGalleryImage> applyGalleryImageMutableLiveData;

    public LiveData<ApplyGalleryImage> applyGalleryImageLiveData(Activity activity, String permissionId, String userId) {
        applyGalleryImageMutableLiveData = new MutableLiveData<>();
        serviceApi.applyGalleryimage(permissionId, userId).enqueue(new Callback<ApplyGalleryImage>() {
            @Override
            public void onResponse(Call<ApplyGalleryImage> call, Response<ApplyGalleryImage> response) {
                applyGalleryImageMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ApplyGalleryImage> call, Throwable t) {
                applyGalleryImageMutableLiveData.postValue(null);
            }
        });
        return applyGalleryImageMutableLiveData;
    }


    /******************************  Get Applied Gallery View Model ******************************/
    private MutableLiveData<GetAppliedGalleryModel> getAppliedGalleryModelMutableLiveData;

    public LiveData<GetAppliedGalleryModel> getAppliedGalleryModelLiveData(Activity activity, String userId) {
        getAppliedGalleryModelMutableLiveData = new MutableLiveData<>();
        serviceApi.getAppliedGallery(userId).enqueue(new Callback<GetAppliedGalleryModel>() {
            @Override
            public void onResponse(Call<GetAppliedGalleryModel> call, Response<GetAppliedGalleryModel> response) {
                getAppliedGalleryModelMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<GetAppliedGalleryModel> call, Throwable t) {
                getAppliedGalleryModelMutableLiveData.postValue(null);
            }
        });
        return getAppliedGalleryModelMutableLiveData;
    }


    /******************************  Get USER LEVEL View Model ******************************/
    private MutableLiveData<SendingLevel> getSendingMutableLivedata;

    public LiveData<SendingLevel> getSendingLevelData(Activity activity, String userId) {
        getSendingMutableLivedata = new MutableLiveData<>();
        serviceApi.getLevelList(userId).enqueue(new Callback<SendingLevel>() {
            @Override
            public void onResponse(Call<SendingLevel> call, Response<SendingLevel> response) {
                getSendingMutableLivedata.postValue(response.body());
            }

            @Override
            public void onFailure(Call<SendingLevel> call, Throwable t) {
                getSendingMutableLivedata.postValue(null);
            }
        });
        return getSendingMutableLivedata;
    }

    private MutableLiveData<SendMsgRoot> sendMsg;

    public LiveData<SendMsgRoot> sendMsg(String user, String msg) {

        sendMsg = new MutableLiveData<>();

        serviceApi.sendMsg(user, msg).enqueue(new Callback<SendMsgRoot>() {
            @Override
            public void onResponse(@NonNull Call<SendMsgRoot> call, @NonNull Response<SendMsgRoot> response) {

                if (response.body() != null) {
                    sendMsg.postValue(response.body());
                } else {
                    sendMsg.postValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<SendMsgRoot> call, @NonNull Throwable t) {

                sendMsg.postValue(null);
            }
        });
        return sendMsg;
    }

    private MutableLiveData<SendMsgRoot> getMsg;

    public LiveData<SendMsgRoot> getMsg(String user) {

        getMsg = new MutableLiveData<>();

        serviceApi.getMsg(user).enqueue(new Callback<SendMsgRoot>() {
            @Override
            public void onResponse(@NonNull Call<SendMsgRoot> call, @NonNull Response<SendMsgRoot> response) {

                if (response.body() != null) {
                    getMsg.postValue(response.body());
                } else {
                    getMsg.postValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<SendMsgRoot> call, @NonNull Throwable t) {

                getMsg.postValue(null);
            }
        });
        return getMsg;
    }

    private MutableLiveData<VipImagesRoot> applyVip;

    public LiveData<VipImagesRoot> applyVip(String userId, String vipId) {

        applyVip = new MutableLiveData<>();

        serviceApi.applyVip(userId, vipId).enqueue(new Callback<VipImagesRoot>() {
            @Override
            public void onResponse(@NonNull Call<VipImagesRoot> call, @NonNull Response<VipImagesRoot> response) {

                if (response.body() != null) {
                    applyVip.postValue(response.body());
                } else {
                    applyVip.postValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<VipImagesRoot> call, @NonNull Throwable t) {

                applyVip.postValue(null);
            }
        });
        return applyVip;
    }

    private MutableLiveData<GetAllFamilyRoot> family_admin_remove;

    public LiveData<GetAllFamilyRoot> family_admin_remove(String type, String familyId, String otherUserId, String userId,String is_admin) {
        family_admin_remove = new MutableLiveData<>();

        serviceApi.family_admin_remove(type, familyId, otherUserId, userId,is_admin).enqueue(new Callback<GetAllFamilyRoot>() {
            @Override
            public void onResponse(Call<GetAllFamilyRoot> call, Response<GetAllFamilyRoot> response) {
                if (response.body() != null) {
                    family_admin_remove.postValue(response.body());
                } else {
                    family_admin_remove.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<GetAllFamilyRoot> call, Throwable t) {
                family_admin_remove.postValue(null);
            }
        });
        return family_admin_remove;
    }

    private MutableLiveData<PurchaseCarsRoot> claim_garage;

    public LiveData<PurchaseCarsRoot> claim_garage(String userId, String garageId, String type, String image){

        claim_garage = new MutableLiveData<>();

        serviceApi.claim_garage(userId, garageId,type,image).enqueue(new Callback<PurchaseCarsRoot>() {
            @Override
            public void onResponse(Call<PurchaseCarsRoot> call, Response<PurchaseCarsRoot> response) {
                if (response.body() !=null){
                    claim_garage.postValue(response.body());
                }else {
                    claim_garage.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<PurchaseCarsRoot> call, Throwable t) {
                claim_garage.postValue(null);
            }
        });
        return claim_garage;
    }

    private MutableLiveData<SaveTransactionRoot> saveTransaction;

    public LiveData<SaveTransactionRoot> saveTransaction(String userId,String merchantId,String merchantTransactionId,
                                                         String amount,String status,String responseCode,String upiTransaction){

        saveTransaction = new MutableLiveData<>();

        serviceApi.saveTransaction(userId,merchantId,merchantTransactionId,amount,status,responseCode,upiTransaction).enqueue(new Callback<SaveTransactionRoot>() {
            @Override
            public void onResponse(@NonNull Call<SaveTransactionRoot> call, @NonNull Response<SaveTransactionRoot> response) {

                if (response.body() !=null){
                    saveTransaction.postValue(response.body());
                }else {
                    saveTransaction.postValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<SaveTransactionRoot> call, @NonNull Throwable t) {

                saveTransaction.postValue(null);
            }
        });
        return saveTransaction;
    }
}

