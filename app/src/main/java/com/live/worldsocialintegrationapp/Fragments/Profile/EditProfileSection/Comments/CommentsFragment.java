package com.live.worldsocialintegrationapp.Fragments.Profile.EditProfileSection.Comments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.Adapters.CommentAdpater;
import com.live.worldsocialintegrationapp.ModelClasses.GetComment.Detail;
import com.live.worldsocialintegrationapp.ModelClasses.GetComment.GetCommentRoot;
import com.live.worldsocialintegrationapp.ModelClasses.SendOtpRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.live.worldsocialintegrationapp.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class CommentsFragment extends Fragment implements CommentAdpater.Callback {

    private RecyclerView commentRV;
    private EditText commentEdtx;
    private TextView commentsPostTV, noCommentTv;
    private ImageView commentsBackImg;
    private List<Detail> list = new ArrayList<>();
    private CommentAdpater commentAdpater;
    private String mediaId, addCommentUserId;
    private CircleImageView commentsUserImg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comments, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CommonUtils.disableBottomNavigation(requireActivity());

        if (getArguments() != null) {
            this.mediaId = getArguments().getString("mediaId");
            this.addCommentUserId = getArguments().getString("addCommentUserId");
        } else {
        }

        findIds(view);
        clicks(view);

        commentAdpater = new CommentAdpater(new ArrayList<>(), requireContext(), this);
        commentRV.setAdapter(commentAdpater);


        Glide.with(requireContext()).load(App.getSharedpref().getString("image")).error(R.drawable.demo_user_profile_img).into(commentsUserImg);
        getCommentListApi();
    }

    private void getCommentListApi() {
        Toast.makeText(requireContext(), "mediaId :- "+mediaId, Toast.LENGTH_SHORT).show();

        new Mvvm().getComments(requireActivity(), mediaId).observe(requireActivity(), new Observer<GetCommentRoot>() {
            @Override
            public void onChanged(GetCommentRoot getCommentRoot) {

                if (getCommentRoot.getStatus().equalsIgnoreCase("1")) {
                    list = getCommentRoot.getDetails();

                    if (list.isEmpty()) {
                        noCommentTv.setVisibility(View.VISIBLE);
                    } else {

                        noCommentTv.setVisibility(View.GONE);
                        commentAdpater.loadData(list);
                        commentAdpater.notifyDataSetChanged();
                    }
                } else {
                    noCommentTv.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void clicks(View view) {

        commentsPostTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCommentApi();
            }
        });

        commentsBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });
    }

    private void addCommentApi() {

        if (commentEdtx.getText().toString().trim().length() == 0) {
            Toast.makeText(requireContext(), "Type message", Toast.LENGTH_SHORT).show();
        } else {
            new Mvvm().addComment(requireActivity(), addCommentUserId, mediaId, commentEdtx.getText().toString()).observe(requireActivity(), new Observer<SendOtpRoot>() {
                @Override
                public void onChanged(SendOtpRoot sendOtpRoot) {
                    if(sendOtpRoot != null){

                        if (sendOtpRoot.getStatus().equalsIgnoreCase("1")) {
                            getCommentListApi();
                            commentEdtx.setText("");
                        } else {
                        }
                    }

                }
            });
        }
    }

    private void findIds(View view) {

        commentRV = view.findViewById(R.id.commentRV);
        commentEdtx = view.findViewById(R.id.commentEdtx);
        commentsPostTV = view.findViewById(R.id.commentsPostTV);
        commentsBackImg = view.findViewById(R.id.commentsBackImg);
        commentsUserImg = view.findViewById(R.id.commentsUserImg);
        noCommentTv = view.findViewById(R.id.noCommentTv);

    }

    private void deleteComment(String feedId, String commentId) {

        new Mvvm().deleteComment(requireActivity(), feedId, commentId).observe(requireActivity(), new Observer<SendOtpRoot>() {
            @Override
            public void onChanged(SendOtpRoot sendOtpRoot) {

                if (sendOtpRoot.getStatus().equalsIgnoreCase("1")) {
                } else {
                }
            }
        });
    }

    @Override
    public void delComment(String feedId, String commentId) {

        if (feedId.length() != 0 && commentId.length() != 0) {
            deleteComment(feedId, commentId);
        } else {
        }
    }

    @Override
    public void userProfile(String userId) {
        Bundle bundle = new Bundle();
        bundle.putString("otherUserId", userId);
        Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.otherUser, bundle);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        CommonUtils.visibileBottomNavigation(requireActivity());
        Toast.makeText(requireContext(), "onDestroy view", Toast.LENGTH_SHORT).show();
    }
}