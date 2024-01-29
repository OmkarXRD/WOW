package com.live.worldsocialintegrationapp.Firebase;


public class URLBuilder {


    public enum Request {

        GET,POST

    }


    public enum MessageType
    {
        Instructor,Student,Seen
    }
    public enum CurrencyType
    {
        $,
    }

    public enum Type
    {
        followUnfollow,chatRequest,userLive,chatNotification, family_request_accept
    }

    public enum Title
    {
        Booking,Cancel,Accepted,Rejected,Session,Message,follow,RequestReceived
    }

    public enum Parameter
    {
        //login and register parameters
        username,email,password,reg_id,device_id,device_type,type,phone,experience,location,
        latitude,longitude,countryName,address,country,profileImage,countryCode,country_Code,title,

        // add service parameters
        modeType,courseCategory,courseLocation,courseName,CourseDescription,noOfSeats,coursePrice,courseTiming,
        courseDate,picture,reason,userId,id,serviceId,startDate,endDate,category,startAge,endAge,mode,instructorId,status,bookingId,
        userMessage,instructorMessage,activeStatus,link,liveServiceId,socialId,chatID,fields,name,gender,birthday,uniqueServiceId,
        rating,product_description,accountHolderName,routingNumber,accountNumber,individualCity,individualLine1,individualLine2,individualPostalCode,
        individualState,dobDay,dobMonth,dobYear,firstName,lastName,idNumber,document,accountId,token,amount,message,toke,

        //instructorInformation parameters
        social_id,loginType,created,updated,image,hostType,liveUserId,channelName,archivedDate,endTime,toliveCountken,live_password,Liveimage,
        imageText,imageTitle,live_hideUnhideStatus,liveUser_name,liveUser_username,liveUser_dob,liveUser_gender,followerId,follower_name,follower_dob,follower_username,follower_gender,


        //this is for chatNotification
        notifyBy,notifyByImage,notifyById;



    }



    public enum StripeKey
    {
        pk_live_lnAwALpRZzaGJJFdQfvLdpoV00j4f5XY7r
    }

    public enum LiveStripeKey
    {



    }


    public static class SharedPreferencesKeyName
    {
        public static String SharedPreferencesKey="sharedPreferencesKey";
        public static String SharedPreferencesName="fireStationSharedPreferences";


    }




}


