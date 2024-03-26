package com.live.worldsocialintegrationapp.Fragments.Profile;

import static com.live.worldsocialintegrationapp.Firebase.URLBuilder.Parameter.link;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.live.worldsocialintegrationapp.Adapters.PostsAdapter;
import com.live.worldsocialintegrationapp.BuildConfig;
import com.live.worldsocialintegrationapp.Fragments.Profile.EditProfileSection.Comments.CommentsFragment;
import com.live.worldsocialintegrationapp.ModelClasses.Posts.Details;
import com.live.worldsocialintegrationapp.ModelClasses.Posts.PostDetailsRoot;
import com.live.worldsocialintegrationapp.ModelClasses.SendOtpRoot;
import com.live.worldsocialintegrationapp.ModelClasses.UploadPost.RemoveUserPostRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;


public class Fragment_Moments extends Fragment implements PostsAdapter.Callback{
    RecyclerView recyclerView;
    private ImageView momentNoPostImg;
    List<Details> list=new ArrayList<>();
    PostsAdapter postsAdapter;
    public  static   String id,mediaId,otherId,userId;
    public static int check_value=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__moments, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        userId=AppConstants.USER_ID;
        findIds(view);
//
//        SnapHelper snapHelper = new PagerSnapHelper();
//        snapHelper.attachToRecyclerView(recyclerView);
        postsAdapter = new PostsAdapter(this,new ArrayList<>(),requireContext());
        recyclerView.setAdapter(postsAdapter);
        getArgmentData();
        getPostDetailsApi();

    }

    private void getArgmentData() {

            if(check_value==1){

                userId=otherId;
                otherId=AppConstants.USER_ID;

            } else if(check_value ==2){

                this.otherId = AppConstants.USER_ID;

            } else{
                this.otherId = AppConstants.USER_ID;
            }
    }

    private void getPostDetailsApi() {
//        otherId otherUser aa rahi hai isme
//        if (otherId.equalsIgnoreCase(userId)){
//            otherId="4";
//        }
        new Mvvm().getPostDetail(requireActivity(), userId,otherId).observe(requireActivity(), new Observer<PostDetailsRoot>() {
            @Override
            public void onChanged(PostDetailsRoot postDetailsRoot) {

                if(postDetailsRoot != null){
                    if(postDetailsRoot.getStatus().equalsIgnoreCase("1")){
                        list= postDetailsRoot.getDetails();
                        postsAdapter.loadData(list);
                        momentNoPostImg.setVisibility(View.GONE);
                    } else{}
                }else{
                    if(getContext() != null){
                        Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void findIds(View view) {
        recyclerView = view.findViewById(R.id.rvPosts);
        momentNoPostImg=view.findViewById(R.id.momentNoPostImg);

    }


    @Override
    public void callback(List<Details> list, String index) {

//        if(list.size()==0){
//            momentNoPostImg.setVisibility(View.VISIBLE);
//            recyclerView.setVisibility(View.GONE);
//
//        }else{
//            Toast.makeText(requireContext(), "list is null", Toast.LENGTH_SHORT).show();
//        }
    }

    @Override
    public void comment(String mediaId) {

        if(mediaId.length() != 0){

            CommentsFragment commentsFragment = new CommentsFragment();
            Bundle bundle = new Bundle();
            bundle.putString("mediaId",mediaId);
            bundle.putInt("check",check_value);
            bundle.putString("addCommentUserId",otherId);

            commentsFragment.setArguments(bundle);
            Navigation.findNavController(requireActivity().findViewById(R.id.nav_home))
                    .navigate( R.id.commentsFragment,bundle);
        }
    }

    @Override
    public void likeDislike(String value, String mediaId, ImageView imageView, TextView likeCount,String likeCountString) {

        if(value.length() != 0){

            if(value.equalsIgnoreCase("1")){

                likeDislikeApi(otherId,mediaId,imageView,likeCount,likeCountString);

//                getPostDetailsApi();
//                postsAdapter.notifyDataSetChanged();
            }
            else if(value.equalsIgnoreCase("0")){

                likeDislikeApi(otherId,mediaId,imageView,likeCount,likeCountString);
//             getPostDetailsApi();

            }
        }
    }


    @Override
    public void popUpMenu(Details details, ImageView imageView) {

        setMenu(details,imageView);
    }

    @Override
    public void openUserProfile(String otherUserId) {
        Bundle bundle = new Bundle();
        bundle.putString("otherUserId", otherUserId);
        Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.otherUser, bundle);
    }

    @Override
    public void postShare(int pos,Details details) {

                            DynamicLink dynamicLink = FirebaseDynamicLinks.getInstance().createDynamicLink()
                // .setLink(dynamicLinkUri)
                .setLink(Uri.parse("http://www.google.com/?userId=" + "?key="))
                .setDomainUriPrefix("https://worldsocialintegrationapp.page.link")
                // Open links with this app on Android
                .setAndroidParameters(new DynamicLink.AndroidParameters.Builder().build())
                // Open links with com.example.ios on iOS
                .setIosParameters(new DynamicLink.IosParameters.Builder("com.appinventiv.ios").build())
                .buildDynamicLink();
        String link = dynamicLink.getUri().toString();

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
    }

    private void likeDislikeApi(String userId,String mediaId,ImageView imageView,TextView textView,String likeCountString) {

        new Mvvm().likeDislike(requireActivity(),userId,mediaId).observe(requireActivity(), new Observer<SendOtpRoot>() {
            @Override
            public void onChanged(SendOtpRoot sendOtpRoot) {

                if(sendOtpRoot != null){
                    if(sendOtpRoot.getStatus().equalsIgnoreCase("1")){

                        if(sendOtpRoot.isLikeUnLikestatus()){
                            imageView.setImageResource(R.drawable.ic_heart__5_);
                            int count=Integer.parseInt(likeCountString);
                            count++;
                            textView.setText(String.valueOf(count)+" likes");

                        }else{
                            imageView.setImageResource(R.drawable.heart_img);
                            int count=Integer.parseInt(likeCountString);
                            if(count<=1){
                               count=0;
                            }else{
                                count--;
                            }
                            textView.setText(String.valueOf(count)+" likes");
                        }
                        postsAdapter.notifyDataSetChanged();
                    }
                    else{
                    }
                }else{
                }
            }
        });

    }

    private void setMenu(Details details,ImageView menu) {

        if (details.getUserId().equalsIgnoreCase(AppConstants.USER_ID)) {

            PopupMenu popupMenu = new PopupMenu(requireActivity(), menu);
            popupMenu.getMenuInflater().inflate(R.menu.post_menu, popupMenu.getMenu());

            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @SuppressLint("NonConstantResourceId")
                @Override
                public boolean onMenuItemClick(MenuItem item) {

                    switch (item.getItemId()) {
                        case R.id.item1:
                            Bundle bundle = new Bundle();
                            bundle.putString("otherUserId", details.getId());
                            Navigation.findNavController(requireActivity(), R.id.nav_home).navigate(R.id.usersReportFragment, bundle);
                            return true;
                        case R.id.item2:
                            removePost(details.getId(),details.getMediaId());
                            return true;

                        default:
                    }
                    return true;
                }
            });
            popupMenu.show();
        }else{
            PopupMenu popupMenu = new PopupMenu(requireActivity(), menu);
            popupMenu.getMenuInflater().inflate(R.menu.report_on_post, popupMenu.getMenu());

            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @SuppressLint("NonConstantResourceId")
                @Override
                public boolean onMenuItemClick(MenuItem item) {

                    switch (item.getItemId()) {
                        case R.id.reportItem:
                            Bundle bundle = new Bundle();
                            bundle.putString("otherUserId", details.getId());
                            Navigation.findNavController(requireActivity(), R.id.nav_home).navigate(R.id.usersReportFragment, bundle);
                            return true;
                            default:
                    }
                    return true;
                }
            });
            popupMenu.show();
        }

    }

    private void removePost(String userId,String mediaId){

            if(AppConstants.USER_ID.equalsIgnoreCase(userId)){
                new Mvvm().removeUserPost(requireActivity(),AppConstants.USER_ID,mediaId).observe(requireActivity(), new Observer<RemoveUserPostRoot>() {
                    @Override
                    public void onChanged(RemoveUserPostRoot removeUserPostRoot) {
                        if(removeUserPostRoot != null){
                            if(removeUserPostRoot.getSuccess().equalsIgnoreCase("1")){
                                if(getContext()!= null){
                                    //Toast.makeText(requireContext(), ""+removeUserPostRoot.getMessage(), Toast.LENGTH_SHORT).show();
                                    postsAdapter.notifyDataSetChanged();
                                }
                            }else{
                                //Toast.makeText(requireContext(), ""+removeUserPostRoot.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
//
        }
}