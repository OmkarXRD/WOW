package com.live.worldsocialintegrationapp.Retrofit;

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
import com.live.worldsocialintegrationapp.ModelClasses.RootNewUser;
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
import com.live.worldsocialintegrationapp.agora.openvcall.model.PrimeGiftModel;
import com.live.worldsocialintegrationapp.agora.openvcall.model.SendEmojiGiftModel;
import com.live.worldsocialintegrationapp.agora.openvcall.model.SpinWheelModelClass;
import com.live.worldsocialintegrationapp.agora.openvcall.model.TokenGenerateModel;
import com.live.worldsocialintegrationapp.games.model.CheckSpinWheel;
import com.live.worldsocialintegrationapp.games.model.SpinModelClass;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface ServiceApi {

    @GET("getCountries")
    Call<CountryRoot> getCountryDetails();

    @FormUrlEncoded
    @POST("sendOtp")
    Call<SendOtpRoot> sendOtp(@Field("phone") String phone);

    @FormUrlEncoded
    @POST("loginRegisterUser")
    Call<RegisterRoot> registerUser(@Field("phone") String phone,
                                    @Field("otp") String otp,
                                    @Field("Country") String Country,
                                    @Field("continent") String continent,
                                    @Field("reg_id") String reg_id);

    @FormUrlEncoded
    @POST("usersLogout")
    Call<SendOtpRoot> logoutUser(@Field("userId") String userId);

    @Multipart
    @POST("updateUserProfile")
    Call<UpdateUserProfileRoot> updateUserProfile(@Part("userId") RequestBody userId,
                                                  @Part("name") RequestBody name,
                                                  @Part MultipartBody.Part image,
                                                  @Part("gender") RequestBody gender,
                                                  @Part("dob") RequestBody dob,
                                                  @Part("Country") RequestBody country,
                                                  @Part("bio") RequestBody bio);

    @FormUrlEncoded
    @POST("endLive")
    Call<Map> endLive(@Field("userId") String userId);

    @FormUrlEncoded
    @POST("agoraToken")
    Call<TokenGenerateModel> generateToken(@FieldMap HashMap<String, String> data);

    @FormUrlEncoded
    @POST("getPrimeGift")
    Call<PrimeGiftModel> getGifts(@Field("userId")String userId);

    @FormUrlEncoded
    @POST("sendGift")
    Call<GiftSendModel> sendGift(
            @Field("senderId") String senderId,
            @Field("receiverId") String receiverId,
            @Field("diamond") String diamond,
            @Field("giftId") String giftId,
            @Field("liveId") String liveId);

    @GET("getPrimeGift")
    Call<EmojiGiftModel> getEmojiGifts();

    @FormUrlEncoded
    @POST("sendGift")
    Call<SendEmojiGiftModel> sendEmojiGift(
            @Field("senderId") String senderId,
            @Field("receiverId") String receiverId,
            @Field("diamond") String diamond,
            @Field("giftId") String giftId,
            @Field("liveId") String liveId);

    @FormUrlEncoded
    @POST("searchUsers")
    Call<UsersSearchRoot> searchUsers(@Field("search") String search);

    @FormUrlEncoded
    @POST("nearByUsers")
    Call<NearByLiveUsersRoot> getNearByUsers(@Field("userId") String userId,
                                             @Field("latitude") String latitude,
                                             @Field("longitude") String longitude,
                                             @Field("kickTo") String kickTo);

    @FormUrlEncoded
    @POST("getPopularUserLive")
    Call<GetAllPopularRoot> getAllPopularLiveUsers(@Field("userId") String userId,
                                                   @Field("otherId") String kickTo);

    @Multipart
    @POST("userPostAndVideo")
    Call<UploadPostRoot> uploadPost(@Part("userId") RequestBody userId,
                                    @Part("description") RequestBody description,
                                    @Part("status") RequestBody status,
                                    @Part MultipartBody.Part image);

    @FormUrlEncoded
    @POST("feedDetails")
    Call<PostDetailsRoot> getPostDetail(@Field("userId") String userId,
                                        @Field("otherId") String otherId);

    @FormUrlEncoded
    @POST("likeDislike")
    Call<SendOtpRoot> likeDislike(@Field("userId") String userId,
                                  @Field("feedId") String feedId);

    @FormUrlEncoded
    @POST("addComment")
    Call<SendOtpRoot> addComment(@Field("userId") String userId,
                                 @Field("feedId") String feedId,
                                 @Field("comment") String comment);

    @FormUrlEncoded
    @POST("getComments")
    Call<GetCommentRoot> getCommentsList(@Field("feedId") String feedId);

    @FormUrlEncoded
    @POST("deleteComment")
    Call<SendOtpRoot> deleteComment(@Field("feedId") String feedId,
                                    @Field("commentId") String commentId);

    @FormUrlEncoded
    @POST("getUserDetails")
    Call<GetUserDetailRoot> getUserDetail(@Field("userId") String userId,
                                          @Field("otherUserId") String otherUserId,
                                          @Field("kickTo") String kickTo);

    @FormUrlEncoded
    @POST("followUnfollow")
    Call<SendOtpRoot> followUser(@Field("userId") String userId,
                                 @Field("followingUserId") String followingUserId,
                                 @Field("type")String type);

//    @POST("register")
//    Call<Object> getUser(@Body Map<String, String> body);

    @FormUrlEncoded
    @POST("getFollowingDetails")
    Call<GetFollowingRoot> getFollowing(@Field("userId") String userId);

    @FormUrlEncoded
    @POST("getFollowersDetails")
    Call<GetFansRoot> getFans(@Field("userId") String userId);

    @FormUrlEncoded
    @POST("getFriendsDetails")
    Call<GetFriendRoot> getFriends(@Field("userId") String userId);

//    @FormUrlEncoded
//    @POST("getMyFriends")
//    Call<GetFriendRoot> getFriends(@Field("userId") String userId);

    @FormUrlEncoded
    @POST("setVisitor")
    Call<GetVisitorsRoot> setVisitor(@Field("userId") String userId,
                                     @Field("otherUserId") String otherUserId);

    @FormUrlEncoded
    @POST("getSetVisitor")
    Call<GetVisitorsRoot> getVisitors(@Field("userId") String userId);

    @GET("getCoinValue")
    Call<GetCoinRoot> getCoins(@Query("userId") String userId);

    @GET("getEmoji")
    Call<GetEmojiRoot> getEmoji();

    @GET("getSilverCoinValue")
    Call<GetSilverCoinRoot> getSilverCoins();

    @FormUrlEncoded
    @POST("getUserWallet")
    Call<GetWalletRoot> getWallet(@Field("userId") String userId);

    @FormUrlEncoded
    @POST("userWallet")
    Call<AddWalletMoneyRoot> addWalletMoney(@Field("userId") String userId,
                                            @Field("wallet_amount") String wallet_amount,
                                            @Field("razorpay_order_id") String razorpay_order_id,
                                            @Field("razorpay_payment_id") String razorpay_payment_id,
                                            @Field("razorpay_signature") String razorpay_signature
    );

    @FormUrlEncoded
    @POST("purchaseSilverCoin")
    Call<PurchaseSilverCoins> puchaseSilverCoins(@Field("userId") String userId,
                                                 @Field("coinValue") String coinValue);

    @GET("getGifs")
    Call<EntryEffectsRoot> getGifs();

    @FormUrlEncoded
    @POST("getCarsByLevel")
    Call<GetCarUserLevel> getCarLevel(@Field("userId") String userId);

    @FormUrlEncoded
    @POST("getFrameByLevel")
    Call<GetFrameByLevel> getFrameLevel(@Field("userId") String userId);

    @FormUrlEncoded
    @POST("getColorByLevel")
    Call<GetColorByLevel> getColorIdLevel(@Field("userId") String userId);

    @GET("getBadges")
    Call<GetUserTalentLevelRoot> getUserTalentLevel();

    @FormUrlEncoded
    @POST("purchaseHistory")
    Call<PurchaseHistoryRoot> getPurchaseHistory(@Field("userId") String userId);

    @FormUrlEncoded
    @POST("removeUserAccount")
    Call<SendOtpRoot> removeUserAccount(@Field("userId") String userId);

    @FormUrlEncoded
    @POST("phoneNumUpdate")
    Call<SendOtpRoot> verifyPhone(@Field("userId") String userId,
                                  @Field("newPhone") String newPhone,
                                  @Field("otp") String otp);

    @FormUrlEncoded
    @POST("getFollowing")
    Call<GetFollowingLiveRoot> getFollowingLiveUsers(@Field("userId") String userId,
                                                     @Field("kickTo") String kickTo);

    @FormUrlEncoded
    @POST("getFollowingFriends")
    Call<GetFollowingLiveRoot> getFriendLiveUsers(@Field("userId") String userId);

    @FormUrlEncoded
    @POST("getLiveFriendsAndFollowing")
    Call<FollowingFriendLiveRoot> getFriendFollowingLive(@Field("userId") String userId);

    @FormUrlEncoded
    @POST("userReport")
    Call<UserReportRoot> userReport(@Field("userReport_catId") String userReport_catId,
                                    @Field("userReport_SubcatId") String userReport_SubcatId,
                                    @Field("userId") String userId,
                                    @Field("otherUserId") String otherUserId);
//    @FormUrlEncoded
//    @POST("socialLogin")
//    Call<SocialLoginRoot> socialLogin(
//            @Field("social_id") String social_id,
//            @Field("reg_id") String reg_id,
//            @Field("dev_id") String dev_id,
//            @Field("dev_type") String dev_type,
//            @Field("phone") String phone,
//            @Field("name") String name,
//            @Field("email") String email,
//            @Field("image") String image,
//            @Field("Country") String Country);

    @Multipart
    @POST("socialLogin")
    Call<SocialLoginRoot> socialLogin(@Part("social_id") RequestBody social_id,
                                      @Part("reg_id") RequestBody reg_id,
                                      @Part("dev_id") RequestBody dev_id,
                                      @Part("dev_type") RequestBody dev_type,
                                      @Part("phone") RequestBody phone,
                                      @Part("name") RequestBody name,
                                      @Part("email") RequestBody email,
                                      @Part("continent") RequestBody continent,
                                      //@Part MultipartBody.Part image,
                                      @Part("Country") RequestBody Country);

    @FormUrlEncoded
    @POST("archieveLive")
    Call<SendOtpRoot> endLiveCall(@Field("liveId") String liveId);

    @GET("getUserReportTypeCategories")
    Call<UserReportCategoryRoot> getUserReportCategory();

    @GET("getUserReportTypeSubCategories")
    Call<UserReportCategoryRoot> getSubCategory();

    @FormUrlEncoded
    @POST("getUserImages")
    Call<GetUserImagesRoot> getUserImages(@Field("userId") String userId);

    @FormUrlEncoded
    @POST("getWalletDetails")
    Call<GetDiamondRoot> getDiamonds(@Field("userId") String userId);

    @FormUrlEncoded
    @POST("exchangeCoins")
    Call<ExchangeCoinsRoot> exchangeDiamondsToCoins(@Field("userId") String userId,
                                                    @Field("myDiamond") String myDiamond);

    @FormUrlEncoded
    @POST("dailyTasks")
    Call<GetDailyTaskRoot> getDailyTask(@Field("userId") String userId);

    @FormUrlEncoded
    @POST("setDailytask")
    Call<SetDailyTaskRoot> setDailyTask(@Field("userId") String userId,
                                        @Field("day") String day);

    @FormUrlEncoded
    @POST("getFrames")
    Call<GetFramesRoot> getFrames(@Field("userId") String userId);

    @FormUrlEncoded
    @POST("purchaseFrames")
    Call<PurchaseFramesRoot> purchaseFrames(@Field("userId") String userId,
                                            @Field("frameId") String frameId);

    @FormUrlEncoded
    @POST("getPurchaseFrame")
    Call<PurchaseFramesRoot> getPurchaseFrame(@Field("userId") String userId);

    @FormUrlEncoded
    @POST("applyFrame")
    Call<GetFramesRoot> applyFrame(@Field("userId") String userId,
                                   @Field("frameId") String frameId);

    @FormUrlEncoded
    @POST("sendFrames")
    Call<GetFramesRoot> sendFrame(@Field("userId") String userId,
                                  @Field("otherUserId") String otherUserId,
                                  @Field("frameId") String frameId);

    @FormUrlEncoded
    @POST("getAppliedFrame")
    Call<GetAppliedFrameRoot> getAppliedFrame(@Field("userId") String userId);

    @FormUrlEncoded
    @POST("getLuckyId")
    Call<GetLuckIdRoot> getLuckId(@Field("userId") String userId);

    @FormUrlEncoded
    @POST("purchaseLuckyId")
    Call<PurchaseCarsRoot> purchaseLuckyId(@Field("userId") String userId,
                                           @Field("luckyId") String luckyId);

    @FormUrlEncoded
    @POST("getPurchaseLuckyId")
    Call<PurchaseCarsRoot> getPurchaseLuckyId(@Field("userId") String userId);

    @FormUrlEncoded
    @POST("applyLuckyId")
    Call<GetLuckIdRoot> applyLuckyId(@Field("userId") String userId,
                                     @Field("luckyId") String luckyId);

    @FormUrlEncoded
    @POST("getAppliedLuckyId")
    Call<GetAppliedLuckyIdRoot> getAppliedLuckId(@Field("userId") String userId);

    @FormUrlEncoded
    @POST("sendLuckyId")
    Call<GetLuckIdRoot> sendLuckId(@Field("userId") String userId,
                                   @Field("otherUserId") String otherUserId,
                                   @Field("luckyId") String luckyId);

    @FormUrlEncoded
    @POST("getVip")
    Call<VipRoot> getVip(@Field("userId") String userId);

    @FormUrlEncoded
    @POST("buyVip")
    Call<BuyVipRoot> buyVip(@Field("userId") String userId,
                            @Field("vipId") String vipId);

    @GET("getTopSender")
    Call<GetTopGifterRoot> getTopGifterBadge();

    //----------------------------------------------------------------------below apis not working------------------------------------

    @Multipart
    @POST("createFamily")
    Call<CreateFamilyRoot> createFamily(@Part("userId") RequestBody userId,
                                        @Part("familyName") RequestBody familyName,
                                        @Part("description") RequestBody description,
                                        @Part MultipartBody.Part image);

    @Multipart
    @POST("editFamily")
    Call<EDitFamilyModelClass> editFamily(@Part("leaderId") RequestBody leaderId,
                                          @Part("id") RequestBody id,
                                          @Part("familyName") RequestBody familyName,
                                          @Part("description") RequestBody description,
                                          @Part MultipartBody.Part image);

    //    @GET("getFamilies")
//    Call<GetAllFamilyRoot> getAllFamily();

    @FormUrlEncoded
    @POST("getTopGifter")
    Call<GetFamilyTopGiftersRoot> getFamilyTopGifter(@Field("type") String type);

    @FormUrlEncoded
    @POST("sendInvitation")
        //family leader send invitation to user
    Call<GetAllFamilyRoot> sendFamilyInvitation(@Field("userId") String userId,
                                                @Field("familyId") String familyId
    );
    @FormUrlEncoded
    @POST("family_admin_remove")
    Call<GetAllFamilyRoot> family_admin_remove(@Field("type") String type,
                                               @Field("familyId") String familyId,
                                               @Field("otherUserId") String otherUserId,
                                               @Field("userId") String userId,
                                               @Field("is_admin") String is_admin);

    @FormUrlEncoded
    @POST("getInvitations")
        //user getInvitation
    Call<GetInvitationsRoot> getFamilyInvitations(@Field("userId") String userId);

    @FormUrlEncoded
    @POST("show_my_family")
    Call<ShowMyFamilyModelClass> getShowMyFamilyDetails(@Field("userId") String userId);

    @FormUrlEncoded
    @POST("blockUnblock")
    Call<GetInvitationsRoot> blockUser(@Field("blocker") String userId,
                                       @Field("blockerTo") String otherUserId);

    @GET("getTopGiftReceiver")
    Call<RootMeet> getTopUSer();

    @FormUrlEncoded
    @POST("responseInvitation")
        //user response invitations
    Call<InvitationsResponseRoot> responseInvitations(@Field("userId") String userId,
                                                      @Field("requestId") String requestId,
                                                      @Field("status") String status);

        @FormUrlEncoded
        @POST("sendJoinRequest")
            //user send send request to family leader to join family
        Call<GetInvitationsRoot> sendJoinRequest(@Field("familyId") String familyId,
                                                 @Field("userId") String userId);

    @FormUrlEncoded
    @POST("getJoinRequest")
        //family leader get request for family
    Call<GetJoinRequestRoot> getJoinRequest(@Field("leaderId") String leaderId);

    @FormUrlEncoded
    @POST("responseJoinRequest")
        //family leader join request userId jiski request aai hai oski hogi
    Call<ResponseJoinRequestRoot> responseJoinRequest(@Field("requestId") String requestId,
                                                      @Field("userId") String userId,
                                                      @Field("status") String status);

    @FormUrlEncoded
    @POST("getFamiliesDetails")
    Call<GetFamilyDetailsRoot> getFamilyDetails(@Field("familyId") String familyId,
                                                @Field("userId") String userId);

    @FormUrlEncoded
    @POST("leavefamilyGroup")
    Call<GetFamilyDetailsRoot> leaveFamily(@Field("familyId") String familyId,
                                           @Field("userId") String userId);

    @FormUrlEncoded
    @POST("getLiveJoiners")
    Call<GetLiveFamilyJoinersRoot> getFamilyLiveJoiners(@Field("familyId") String familyId,
                                                        @Field("userId") String userId,
                                                        @Field("kickTo") String kickTo);
    @Multipart
    @POST("createEvent")
    Call<CreateEventRoot> createEvent(@Part("event_topic") RequestBody event_topic,
                                      @Part("userId") RequestBody userId,
                                      @Part("description") RequestBody description,
                                      @Part("event_startTime") RequestBody event_startTime,
                                      @Part("event_Type") RequestBody event_Type,
                                      @Part MultipartBody.Part event_image);
    @FormUrlEncoded
    @POST("getBlockUsers")
    Call<RootBlocked> getBlockedUser(@Field("blocker") String blocker);

    @FormUrlEncoded
    @POST("hideUnHideCountry")
    Call<RootBlocked> hideUnHideCountry(@Field("userId") String userId);

    @FormUrlEncoded
    @POST("getLiveUser")
    Call<RootLiveUser> getLIveUserByCountry(@Field("country") String country,
                                            @Field("userId") String userId,
                                            @Field("kickTo") String kickTo);

    @FormUrlEncoded
    @POST("getAllEvents")
    Call<GetEventsRoot> getAllEvents(@Field("userId") String userId);

    @FormUrlEncoded
    @POST("sendEventInvitation")
    Call<SendEventInviationRoot> sendEventInvitation(@Field("userId") String userId,
                                                     @Field("eventId") String eventId);

    @FormUrlEncoded
    @POST("getEventInvitations")
    Call<GetAllEventInvitationsRoot> getAllEventInvitation(@Field("userId") String userId);

    @FormUrlEncoded
    @POST("responseEventInvitation")
    Call<ResponseEventInvitationRoot> responseEventInvitation(@Field("userId") String userId,
                                                              @Field("requestId") String requestId,
                                                              @Field("status") String status);
    @GET("getNewLiveUsers")
    Call<RootNewUser> getNewUser();

    @FormUrlEncoded
    @POST("eventSubscriberDetails")
    Call<EventsDetailsRoot> getEventDetail(@Field("eventId") String eventId,
                                           @Field("userId") String userId);

    @FormUrlEncoded
    @POST("subscribeUnSubscribeEvent")
    Call<SuscribeUnscribeRoot> suscribeUnscribeEvent(@Field("userId") String userId,
                                                     @Field("eventId") String eventId);

    @FormUrlEncoded
    @POST("lockUserLive")
    Call<RootLockBroadCast> lockUserLive(@Field("userId") String userId,
                                         @Field("liveId") String liveId,
                                         @Field("password") String password);

    @GET("getSenderReceiverGifting")
    Call<RootGiftWall> getGiftWall();

    @GET("getImages")
    Call<GetLiveBackgroundImagesRoot> getLiveBackgroundImages();

    @Multipart
    @POST("setLiveImage")
    Call<SetLiveCoverImgRoot> setLiveCoverImg(@Part("userId") RequestBody userId,
                                              @Part("liveId") RequestBody liveId,
                                              @Part("imageText") RequestBody imageText,
                                              @Part("imageTitle") RequestBody imageTitle,
                                              @Part MultipartBody.Part Liveimage);

    @FormUrlEncoded
    @POST("getReceiverGiftHistory")
    Call<GetLiveGiftHistoryRoot> getLiveGiftHistory(@Field("receiverId") String receiverId,
                                                    @Field("liveId") String liveId);

    @FormUrlEncoded
    @POST("getLiveUsers")
    Call<LiveUserByIdRoot> getLiveUserById(@Field("userId") String userId,
                                           @Field("kickToId") String kickToId);

    @FormUrlEncoded
    @POST("hideUnHideLiveUser")
    Call<LiveUserHideUnhideRoot> hideUnhideLiveRooms(@Field("userId") String userId);
    //type 1 means luckyBag , 2 means SuperLucky bag

    @FormUrlEncoded
    @POST("getCoins")
    Call<LuckBagRoot> getLuckBagDetail(@Field("type") String type);

    @FormUrlEncoded
    @POST("deductCoins")
    Call<DeductCoinsRoot> DeductLuckBagCoins(@Field("userId") String userId,
                                             @Field("coins") String coins,
                                             @Field("quantity") String quantity,
                                             @Field("type") String type);

    @FormUrlEncoded
    @POST("divideShare")
    Call<HitLuckyBagCoinsRoot> hitLuckyBagCoins(@Field("userId") String userId,
                                                @Field("deductId") String deductId,
                                                @Field("type") String type
    );

    @FormUrlEncoded
    @POST("getThemes")
    Call<GetLiveThemeRoot> getLiveTheme(@Field("userId") String userId);

    @FormUrlEncoded
    @POST("purchaseThemes")
    Call<GetLiveThemeRoot> purchaseLiveTheme(@Field("userId") String userId,
                                             @Field("themeId") String themeId);

    @FormUrlEncoded
    @POST("removeUserPost")
    Call<RemoveUserPostRoot> removeUserPost(@Field("userId") String userId,
                                            @Field("id") String id);

    @GET("getVipImages")
    Call<VipImagesRoot> getVipImages(@Query("userId") String userId);

    @FormUrlEncoded
    @POST("getLeaderBoard")
    Call<WowsBoardRoot> getWowsBoard(@Field("get") String get,
                                     @Field("type") String type);

    @GET("giftingLiveUser")
    Call<GiftWallLiveUsersRoot> getGiftWallLiveUsers();

    @FormUrlEncoded
    @POST("orderIdGenerate")
    Call<GenerateOrderRoot> generateOrder(@Field("amount") String amount);

    @FormUrlEncoded
    @POST("getGallery")
    Call<GetLiveGalleryRoot> getGallery(@Field("userId") String userId);

    @Multipart
    @POST("purchaseGallery")
    Call<PurchaseGalleryRoot> purchaseGallery(@Part("permissionId") RequestBody permissionId,
                                              @Part("userId") RequestBody userId,
                                              @Part MultipartBody.Part image);

    @Multipart
    @POST("sendGallery")
    Call<SendGalleryRoot> sendGallery(@Part("userId") RequestBody userId,
                                      @Part("otherUserId") RequestBody otherUserId,
                                      @Part("permissionId") RequestBody permissionId,
                                      @Part MultipartBody.Part image);

    @FormUrlEncoded
    @POST("sendThemes")
    Call<SendLiveTheme> sendTheme(@Field("userId") String userId,
                                  @Field("otherUserId") String otherUserId,
                                  @Field("themeId") String themeId);


    @FormUrlEncoded
    @POST("kickOutLiveUser")
    Call<KickOutLiveRoot> liveUserKickOut(@Field("kickToId") String kickToId,
                                          @Field("kickById") String kickById,
                                          @Field("liveId") String liveId);

    @FormUrlEncoded
    @POST("getAllUserPost")
    Call<GetAllPostsRoot> getAllPosts(@Field("userId") String userId);

    @FormUrlEncoded
    @POST("getFriendsPosts")
    Call<GetFriendsFollowingPostsRoot> getFriendsFollowingPosts(@Field("userId") String userId);

    @FormUrlEncoded
    @POST("PushNotificationToUser")
    Call<FirebaseSendReqRoot> sendChatReq(@Field("userId") String userId,
                                          @Field("otheruserId") String otheruserId);

    @FormUrlEncoded
    @POST("getTotalSilverCoins")
    Call<GetTotalSilverCoinsRoot> getTotalSilverCoins(@Field("userId") String userId);

    @FormUrlEncoded
    @POST("createUserSuperLuckyBag")
    Call<CreateSuperLuckyBagRoot> createSuperLuckBag(@Field("userId") String userId,
                                                     @Field("amount") String amount,
                                                     @Field("liveId") String liveId);

    @FormUrlEncoded
    @POST("getSuperLuckyBagDetails")
    Call<SuperLuckyBagDetails> getSuperLuckyBagDetails(@Field("userId") String userId);

    @FormUrlEncoded
    @POST("hitLuckyBag")
    Call<HitSuperLuckyBagRoot> hitSuperLuckyBag(@Field("userId") String userId,
                                                @Field("luckybagId") String luckybagId);

    @GET("getGamesBanner")
    Call<BannerSliderRoot> getGamesBanner();

    //type 1 means daily detail and 0 means overall (daily+month+year etc.)

    @FormUrlEncoded
    @POST("getLiveGifting")
    Call<GetLiveDiamondRoot> getLiveDiamonds(@Field("liveId") String liveId,
                                             @Field("type") String type);

    @FormUrlEncoded
    @POST("getTotalLiveGifting")
    Call<GetLiveTotalDiamondRoot> getLiveTotalDiamonds(@Field("liveId") String liveId);

    @Multipart
    @POST("sendFileToUser")
    Call<SendFileInChatRoot> sendFileInChat(@Part("senderId") RequestBody senderId,
                                            @Part("receiverId") RequestBody receiverId,
                                            @Part("fileType") RequestBody fileType,
                                            @Part MultipartBody.Part file);

    @FormUrlEncoded
    @POST("getSendFileToUser")
    Call<GetChatFilesRoot> getChatFiles(@Field("receiverId") String receiverId);

    @GET("getAdminStatus")
    Call<AdminPanelStatusRoot> getAdminPanelStatus();

    @GET("onlineSevicesQues")
    Call<GetQuestionRoot> getOnlineSerQuestions();

    @FormUrlEncoded
    @POST("onlineSevicesAns")
    Call<GetAnswerRoot> getAnswer(@Field("queId") String queId,
                                  @Field("userId") String userId);

    @FormUrlEncoded
    @POST("notificationToUser")
    Call<ChatNotificationRoot> sendChatNotification(@Field("notifyBy") String notifyBy,
                                                    @Field("notifyTo") String notifyTo);

    @FormUrlEncoded
    @POST("getLiveUsersDetails")
    Call<SingleLiveUserRoot>  singleLiveUser(@Field("otherUserId")String otherUserId,
                                             @Field("userId")String userId);

    @FormUrlEncoded
    @POST("get_single_family_details")
    Call<GetFamilyDetails> getFamilyLevelDetail(@Field("type") Integer type);

    @Multipart
    @POST("hostApi")
    Call<Map> getApplyForHost(@PartMap HashMap<String, RequestBody> data);

    @GET("get_agencies")
    Call<AgencyRoot> getAgencyCode();

    @Multipart
    @POST("apply_for_agency")
    Call<Map> getApplyAgency(@PartMap HashMap<String, RequestBody> data,
                             @Part MultipartBody.Part image,
                             @Part MultipartBody.Part aadharCardFront,
                             @Part MultipartBody.Part panCardFrontPhoto,
                             @Part MultipartBody.Part aadharCardBack,
                             @Part MultipartBody.Part govt_photoId_proof);

    @FormUrlEncoded
    @POST("apply_for_host")
    Call<ApplyForHostModelClass> applyforHost(@Field("userId") String userId);

    @FormUrlEncoded
    @POST("get_user_live_details_today")
    Call<DailyDateLiveRecord> dailyLiveRecord(@Field("userId")String userId,
                                              @Field("date")String date);

    @FormUrlEncoded
    @POST("getHost")
    Call<HostApproveModelClass> hostApprove(@Field("userId")String userId);

    @FormUrlEncoded
    @POST("get_user_live_details_by_dates")
    Call<DailyDateLiveRecord> monthlyLiveRecord(@Field("userId")String userId,
                                              @Field("date")String date);

    @FormUrlEncoded
    @POST("getUserMyCoins")
     Call<GetUserCoinModel> getUserCoin(@Field("userId")String userId);

    @FormUrlEncoded
    @POST("someFunctionality")
    Call<SomeFunctionalityModel> someFunctionality(@Field("userId")String userId,
                                                   @Field("otherUserId")String otherUserId);

    @FormUrlEncoded
    @POST("checkUserIdDeviceBanUnBan")
    Call<GeneratedIdClass> getGeneratedIdClass(@Field("userId")String userId);

    @FormUrlEncoded
    @POST("hitSpinWheel")
    Call<SpinModelClass> getSpinWheel(@Field("userId")String userId,
                                      @Field("coins")String coins,
                                      @Field("type")String type);

    @FormUrlEncoded
    @POST("checkSpinWheel")
    Call<CheckSpinWheel> checkSpinWheel(@Field("userId")String userId);

    @FormUrlEncoded
    @POST("getSpinWheelDetails")
    Call<SpinWheelModelClass> getSpinWheelDetails(@Field("type")String type);

    @FormUrlEncoded
    @POST("userBillingRecords")
    Call<BillingRecordModelClass> getuserBillingRecords(@Field("userId")String userId);

    @FormUrlEncoded
    @POST("giftWallRecords")
    Call<GiftWallReceiverModelClass> getGiftWallRecordClass(@Field("userId")String userId);

    @FormUrlEncoded
    @POST("getPurchaseGallery")
    Call<GetPurchaseGallery> getPurchaseGallery(@Field("userId")String userId);

    @FormUrlEncoded
    @POST("applyGalleryImage")
    Call<ApplyGalleryImage> applyGalleryimage(@Field("permissionId")String permissionId,
                                              @Field("userId")String userId);

    @FormUrlEncoded
    @POST("getAppliedGallery")
    Call<GetAppliedGalleryModel> getAppliedGallery(@Field("userId")String userId);

    @FormUrlEncoded
    @POST("getLevelList")
    Call<SendingLevel> getLevelList(@Field("userId")String userId);

    @FormUrlEncoded
    @POST("sendMsg")
    Call<SendMsgRoot> sendMsg(@Field("user") String user,
                              @Field("msg") String msg);
    @FormUrlEncoded
    @POST("getMsg")
    Call<SendMsgRoot> getMsg(@Field("user") String user);

    @FormUrlEncoded
    @POST("applyVip")
    Call<VipImagesRoot> applyVip(@Field("userId") String userId,
                                 @Field("vipId") String vipId);

    @FormUrlEncoded
    @POST("getPurchaseThemes")
    Call<GetPurchaseGallery> getPurchaseThemes(@Field("userId") String userId);

    @FormUrlEncoded
    @POST("applyTheme")
    Call<GetPurchaseGallery> applyTheme(@Field("userId") String userId,
                                        @Field("themeId") String themeId,
                                        @Field("type") String type);

    @FormUrlEncoded
    @POST("claim_garage")
    Call<PurchaseCarsRoot> claim_garage(@Field("userId") String userId,
                                        @Field("garageId") String garageId,
                                        @Field("type") String type,
                                        @Field("image") String image);
    @FormUrlEncoded
    @POST("saveTransaction")
    Call<SaveTransactionRoot> saveTransaction(@Field("userId") String userId,
                                              @Field("merchantId") String merchantId,
                                              @Field("merchantTransactionId") String merchantTransactionId,
                                              @Field("amount") String amount,
                                              @Field("status") String status,
                                              @Field("responseCode") String responseCode,
                                              @Field("upiTransaction") String upiTransaction);
}
