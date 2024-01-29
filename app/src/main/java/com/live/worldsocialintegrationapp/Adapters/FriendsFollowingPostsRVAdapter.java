package com.live.worldsocialintegrationapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.Fragments.Home.Related.RelatedMoment.FriendsFollowingPostsFragment;
import com.live.worldsocialintegrationapp.ModelClasses.Posts.GetAllPostsRoot;
import com.live.worldsocialintegrationapp.ModelClasses.Posts.GetFriendsFollowingPostsRoot;
import com.live.worldsocialintegrationapp.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FriendsFollowingPostsRVAdapter extends RecyclerView.Adapter<FriendsFollowingPostsRVAdapter.ViewHolder> {
    List<GetFriendsFollowingPostsRoot.Detail> list;
    Context context;
    Callback callback;
    int j;
    int check = 0, imageChangeCheck = 12;

    public FriendsFollowingPostsRVAdapter(List<GetFriendsFollowingPostsRoot.Detail> list, Context context, Callback callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }


    @NonNull
    @Override
    public FriendsFollowingPostsRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.posts_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {


        if(imageChangeCheck == 12){
            holder.postLikeCountTV.setText(list.get(position).getLikeCount() + " likes");
        }else{}


        //Toast.makeText(context, "comment count "+list.get(position).getCommentCount(), Toast.LENGTH_SHORT).show();

        if(list.get(position).getCommentCount() != null && !list.get(position).getCommentCount().equalsIgnoreCase("0")){
            holder.postCommnetCountTV.setVisibility(View.VISIBLE);
            holder.postCommnetCountTV.setText("View all "+list.get(position).getCommentCount().toString()+" comments");
        }else{
            holder.postCommnetCountTV.setVisibility(View.GONE);
        }


        if(list.get(position).getCommentByame() !=null && !list.get(position).getCommentByame().isEmpty()){
            holder.lastCommentUserNameTv.setVisibility(View.VISIBLE);
            holder.lastCommentTv.setVisibility(View.VISIBLE);
            holder.lastCommentUserNameTv.setText(list.get(position).getCommentByame());
            holder.lastCommentTv.setText(list.get(position).getComment());
        }else{
            holder.lastCommentUserNameTv.setVisibility(View.GONE);
            holder.lastCommentTv.setVisibility(View.GONE);
        }


        Glide.with(context).load(list.get(position).getUserImage().getImage()).error(R.drawable.demo_user_profile_img).into(holder.postsUsrCirleImg);

        holder.postUserName.setText(list.get(position).getName());

        holder.postDes.setText(list.get(position).getDescription());

        holder.postTimeTv.setText(list.get(position).getPostTime());

        holder.postsUsrCirleImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.openUserProfile(list.get(position).getUserId());
            }
        });

        holder.postCommnetCountTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.comment(list.get(position));
            }
        });


        if (list.get(position).getPostLikeStatus()) {
            if (imageChangeCheck == 12) {
                holder.postLikeImg.setImageResource(R.drawable.ic_heart__5_);
            }
            ImageLikeClick(holder, position, 0);
        } else {
            if (imageChangeCheck == 12) {
                holder.postLikeImg.setImageResource(R.drawable.heart_img);
            }
            ImageLikeClick(holder, position, 1);
        }

        if (list.get(position).getStatus().equalsIgnoreCase("0")) {
            holder.momentVideoView.setVisibility(View.GONE);
            holder.postImg.setVisibility(View.VISIBLE);
            Glide.with(context).load(list.get(position).getImage()).error(R.drawable.demo_user_profile_img).into(holder.postImg);
        } else {

            videoPlayMethod(0, holder, position);
        }

        holder.postCommentImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.comment(list.get(position));
            }
        });
//
        holder.postThreeDots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.popUpMenu(list.get(position), holder.postThreeDots);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView postsUsrCirleImg;
        ImageView postImg, postCommentImg, postLikeImg,postThreeDots;
        TextView postUserName, postLikeCountTV, postTimeTv, postCommnetCountTV, postDes,lastCommentUserNameTv,lastCommentTv;
        VideoView momentVideoView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            postsUsrCirleImg = itemView.findViewById(R.id.postsUsrCirleImg);
            postUserName = itemView.findViewById(R.id.postUserName);
            momentVideoView = itemView.findViewById(R.id.momentVideoView);
            postImg = itemView.findViewById(R.id.postImg);
            postLikeCountTV = itemView.findViewById(R.id.postLikeCountTV);
            postTimeTv = itemView.findViewById(R.id.postTimeTv);
            postCommnetCountTV = itemView.findViewById(R.id.postCommnetCountTV);
            postDes = itemView.findViewById(R.id.postDes);
            postLikeImg = itemView.findViewById(R.id.postLikeImg);
            postCommentImg = itemView.findViewById(R.id.postCommentImg);
            postThreeDots = itemView.findViewById(R.id.postThreeDots);
            lastCommentUserNameTv = itemView.findViewById(R.id.lastCommentUserNameTv);
            lastCommentTv = itemView.findViewById(R.id.lastCommentTv);
        }
    }
    public interface Callback {
        void callback(GetFriendsFollowingPostsRoot.Detail detail);

        void likeUnlike(GetFriendsFollowingPostsRoot.Detail detail,String value, String mediaId, ImageView imageView, TextView likeCount, String likeString);

        void comment(GetFriendsFollowingPostsRoot.Detail detail);

        void popUpMenu(GetFriendsFollowingPostsRoot.Detail detail, ImageView imageView);

        void openUserProfile(String otherUserId);

    }

    private void videoPlayMethod(int i, ViewHolder holder, int position) {

        if (i < 2) {
            holder.momentVideoView.setVisibility(View.VISIBLE);
            holder.postImg.setVisibility(View.GONE);
            holder.momentVideoView.setVideoURI(Uri.parse(list.get(position).getImage()));
            holder.momentVideoView.start();
            holder.momentVideoView.seekTo(1);
            j = i;
            Handler handler = new Handler();

            handler.postDelayed(
                    new Runnable() {
                        public void run() {
                            videoPlayMethod(j++, holder, position);
                        }
                    }, 21000);
        } else {
            videoPlayMethod(i, holder, position);
        }
    }


    private void ImageLikeClick(ViewHolder holder, int position, int j) {

        holder.postLikeImg.setOnClickListener(view -> {
            if (j == 0) {
                holder.postLikeImg.setImageResource(R.drawable.heart_img);
                callback.likeUnlike(list.get(position),"0",list.get(position).getId(), holder.postLikeImg, holder.postLikeCountTV, list.get(position).getLikeCount());
//                callback.likeUnlike(list.get(position),"1",holder.postLikeImg,holder.postLikeCountTV,list.get(position).getLikeCount());

                imageChangeCheck = 13;
//                Toast.makeText(context, "unlike", Toast.LENGTH_SHORT).show();
            } else if (j == 1) {
                holder.postLikeImg.setImageResource(R.drawable.ic_heart__5_);
                callback.likeUnlike(list.get(position),"1", list.get(position).getId(), holder.postLikeImg, holder.postLikeCountTV, list.get(position).getLikeCount());
//                callback.likeUnlike(list.get(position),"1",holder.postLikeImg,holder.postLikeCountTV,list.get(position).getLikeCount());
                imageChangeCheck = 13;
//                Toast.makeText(context, "like", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
