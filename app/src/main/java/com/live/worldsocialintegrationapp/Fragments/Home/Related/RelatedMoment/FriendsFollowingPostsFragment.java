package com.live.worldsocialintegrationapp.Fragments.Home.Related.RelatedMoment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.live.worldsocialintegrationapp.Adapters.FriendsFollowingPostsRVAdapter;
import com.live.worldsocialintegrationapp.Adapters.SquarePostsRVAdapter;
import com.live.worldsocialintegrationapp.Fragments.Profile.EditProfileSection.Comments.CommentsFragment;
import com.live.worldsocialintegrationapp.ModelClasses.Posts.GetAllPostsRoot;
import com.live.worldsocialintegrationapp.ModelClasses.Posts.GetFriendsFollowingPostsRoot;
import com.live.worldsocialintegrationapp.ModelClasses.SendOtpRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;


public class FriendsFollowingPostsFragment extends Fragment implements FriendsFollowingPostsRVAdapter.Callback {

    private List<GetFriendsFollowingPostsRoot.Detail> list = new ArrayList<>();
    private RecyclerView allPostsRv;
    private ImageView momentSquareNoPostImg;
    private FriendsFollowingPostsRVAdapter squarePostsRVAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friends_following_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findIds(view);
        momentSquareNoPostImg.setVisibility(View.GONE);
        getAllPostsApi();

    }

    private void findIds(View view) {
        allPostsRv = view.findViewById(R.id.friendsFollowingPostRv);
        momentSquareNoPostImg = view.findViewById(R.id.momentFiendsFollowingNoPostImg);

    }


    @Override
    public void callback(GetFriendsFollowingPostsRoot.Detail detail) {

    }

    @Override
    public void likeUnlike(GetFriendsFollowingPostsRoot.Detail detail, String value, String mediaId, ImageView imageView, TextView likeCount, String likeCountString) {
        if (value.length() != 0) {

            if (value.equalsIgnoreCase("1")) {

                likeDislikeApi(AppConstants.USER_ID, detail.getId(), imageView, likeCount, likeCountString);

            } else if (value.equalsIgnoreCase("0")) {

                likeDislikeApi(AppConstants.USER_ID, detail.getId(), imageView, likeCount, likeCountString);
            }
        }
    }

    @Override
    public void comment(GetFriendsFollowingPostsRoot.Detail detail) {

        if (detail.getId().length() != 0) {

            CommentsFragment commentsFragment = new CommentsFragment();
            Bundle bundle = new Bundle();
            bundle.putString("mediaId", detail.getId());
//            bundle.putInt("check",check_value);
            bundle.putString("addCommentUserId", AppConstants.USER_ID);

            commentsFragment.setArguments(bundle);
            Navigation.findNavController(requireActivity().findViewById(R.id.nav_home))
                    .navigate(R.id.commentsFragment, bundle);
        }
    }



    @Override
    public void popUpMenu(GetFriendsFollowingPostsRoot.Detail detail, ImageView imageView) {

        setMenu(detail,imageView);
    }

    @Override
    public void openUserProfile(String otherUserId) {

        Bundle bundle = new Bundle();
        bundle.putString("otherUserId", otherUserId);
        Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.otherUser, bundle);
    }

    private void getAllPostsApi() {
        new Mvvm().getFriendsFollowingPosts(requireActivity(), AppConstants.USER_ID).observe(requireActivity(), new Observer<GetFriendsFollowingPostsRoot>() {
            @Override
            public void onChanged(GetFriendsFollowingPostsRoot getAllPostsRoot) {
                if (getAllPostsRoot != null) {

                    if (getAllPostsRoot.getSuccess().equalsIgnoreCase("1")) {

                        list = getAllPostsRoot.getDetails();
                        if (list.isEmpty()) {
                            momentSquareNoPostImg.setVisibility(View.VISIBLE);
                        } else {
                            squarePostsRVAdapter = new FriendsFollowingPostsRVAdapter(list, requireContext(), FriendsFollowingPostsFragment.this);
                            allPostsRv.setAdapter(squarePostsRVAdapter);
                        }
                    } else {
                        momentSquareNoPostImg.setVisibility(View.VISIBLE);
                    }

                } else {
                    if (getContext() != null) {
                        Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void likeDislikeApi(String userId, String mediaId, ImageView imageView, TextView textView, String likeCountString) {

        new Mvvm().likeDislike(requireActivity(), userId, mediaId).observe(requireActivity(), new Observer<SendOtpRoot>() {
            @Override
            public void onChanged(SendOtpRoot sendOtpRoot) {

                if (sendOtpRoot != null) {
                    if (sendOtpRoot.getStatus().equalsIgnoreCase("1")) {

                        if (sendOtpRoot.isLikeUnLikestatus()) {

                            imageView.setImageResource(R.drawable.ic_heart__5_);
                            int count = Integer.parseInt(likeCountString);
                            count++;
                            textView.setText(String.valueOf(count) + " likes");

                        } else {
                            imageView.setImageResource(R.drawable.heart_img);
                            int count = Integer.parseInt(likeCountString);
                            if (count <= 1) {
                                count = 0;
                            } else {
                                count--;
                            }
                            textView.setText(String.valueOf(count) + " likes");
                        }
                        squarePostsRVAdapter.notifyDataSetChanged();
                    } else {
                    }
                } else {

                }

            }
        });

    }

    private void setMenu(GetFriendsFollowingPostsRoot.Detail details, ImageView menu) {

        PopupMenu popupMenu = new PopupMenu(requireActivity(), menu);
        popupMenu.getMenuInflater().inflate(R.menu.report_on_post, popupMenu.getMenu());



        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.reportItem:
                        Bundle bundle = new Bundle();
                        bundle.putString("otherUserId", AppConstants.USER_ID);
                        Navigation.findNavController(requireActivity(), R.id.nav_home).navigate(R.id.usersReportFragment, bundle);
                        return true;
//                    case R.id.item2:
//                        removePost(details.getId(),details.getMediaId());
//                        return true;

                    default:
                }
                return true;
            }
        });
        popupMenu.show();
    }

}