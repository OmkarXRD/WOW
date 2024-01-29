package com.live.worldsocialintegrationapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.ModelClasses.DoubleClickListener;
import com.live.worldsocialintegrationapp.ModelClasses.Posts.Details;
import com.live.worldsocialintegrationapp.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.InnerClassAdapter> {
    Callback callback;
    List<Details> list = new ArrayList<>();
    Context context;
    int check = 0, imageChangeCheck = 12;
    int j;
    public static String postAdapterUserImg;
    public PostsAdapter(Callback callback, List<Details> list, Context context) {
        this.callback = callback;
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public InnerClassAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerClassAdapter(LayoutInflater.from(parent.getContext()).inflate(R.layout.posts_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InnerClassAdapter holder, @SuppressLint("RecyclerView") int position) {

        holder.postShareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               callback.postShare(position,list.get(position));
            }
        });

        if (list != null && list.size() != 0) {
            if(imageChangeCheck == 12){
                holder.postLikeCountTV.setText(list.get(position).getLikeCount() + " likes");
            }else{

            }


            if (list.get(position).getCommentCount().equalsIgnoreCase("0")){
                holder.postCommnetCountTV.setVisibility(View.GONE);
            }else{
                holder.postCommnetCountTV.setVisibility(View.VISIBLE);
                holder.postCommnetCountTV.setText("View all "+list.get(position).getCommentCount()+" comments ");
            }


            if(list.get(position).getCommentByame() != null && !list.get(position).getCommentByame().isEmpty()){

                holder.lastCommentUserNameTv.setVisibility(View.VISIBLE);
                holder.lastCommentTv.setVisibility(View.VISIBLE);

                holder.lastCommentUserNameTv.setText(list.get(position).getCommentByame());
                holder.lastCommentTv.setText(list.get(position).getComment());
            }else{
                holder.lastCommentUserNameTv.setVisibility(View.GONE);
                holder.lastCommentTv.setVisibility(View.GONE);
            }

            Glide.with(context).load(list.get(position).getImage()).error(R.drawable.no_records).into(holder.add_comments_pic);
            holder.postTimeTv.setText(list.get(position).getPostTime());

            if (list.get(position).isLikeStatus()) {
                if (imageChangeCheck == 12) {
                    holder.postLikeImg.setImageResource(R.drawable.ic_heart__5_);
                }

//                doubleClick(holder, position, 1);
                ImageLikeClick(holder, position, 0);
            } else {
                if (imageChangeCheck == 12) {
                    holder.postLikeImg.setImageResource(R.drawable.heart_img);
                }

//                doubleClick(holder, position, 2);
                ImageLikeClick(holder, position, 1);
            }

            holder.commentEdtx.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.comment(list.get(position).getMediaId());
                }
            });
            holder.postCommentImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.comment(list.get(position).getMediaId());
                }
            });
            holder.postCommnetCountTV.setOnClickListener(view -> {
                        callback.comment(list.get(position).getMediaId());
                    }
            );

            holder.postThreeDots.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.popUpMenu(list.get(position), holder.postThreeDots);
                }
            });

            holder.postUserName.setText(list.get(position).getName());
            Glide.with(context).load(postAdapterUserImg).error(R.drawable.demo_user_profile_img).into(holder.postsUsrCirleImg);

            holder.postsUsrCirleImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    callback.openUserProfile(list.get(position).getUserId());
                }
            });

            holder.postDes.setText(list.get(position).getMediaDescription());
            if (list.get(position).getMediaStatus().equalsIgnoreCase("0")) {
                holder.momentVideoView.setVisibility(View.GONE);
                holder.postImg.setVisibility(View.VISIBLE);
                Glide.with(context).load(list.get(position).getMedia()).error(R.drawable.demo_user_profile_img).into(holder.postImg);
            } else {
//            holder.momentVideoView.setVisibility(View.VISIBLE);
//            holder.postImg.setVisibility(View.GONE);
//            holder.momentVideoView.setVideoURI(Uri.parse(list.get(position).getMedia()));
//            holder.momentVideoView.start();
//            holder.momentVideoView.seekTo(1);
                videoPlayMethod(0, holder, position);
            }

        } else {
            callback.callback(list, String.valueOf(position));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class InnerClassAdapter extends RecyclerView.ViewHolder {
        ImageView postShareBtn, postImg, postLikeImg, postCommentImg, postThreeDots;
        CircleImageView postsUsrCirleImg, add_comments_pic;
        TextView postUserName, postLikeCountTV, postDes, postCommnetCountTV,postTimeTv,lastCommentUserNameTv,lastCommentTv;
        private LottieAnimationView animationView;
        EditText commentEdtx;
        VideoView momentVideoView;
        public InnerClassAdapter(@NonNull View itemView) {
            super(itemView);

            postShareBtn = itemView.findViewById(R.id.postShareBtn);
            postImg = itemView.findViewById(R.id.postImg);
            postLikeImg = itemView.findViewById(R.id.postLikeImg);
            postCommentImg = itemView.findViewById(R.id.postCommentImg);
            postsUsrCirleImg = itemView.findViewById(R.id.postsUsrCirleImg);
            postUserName = itemView.findViewById(R.id.postUserName);
            postLikeCountTV = itemView.findViewById(R.id.postLikeCountTV);
            postDes = itemView.findViewById(R.id.postDes);
            postCommnetCountTV = itemView.findViewById(R.id.postCommnetCountTV);
            animationView = itemView.findViewById(R.id.lottie_main);
            commentEdtx = itemView.findViewById(R.id.commentEdtx);
            add_comments_pic = itemView.findViewById(R.id.add_comments_pic);
            momentVideoView = itemView.findViewById(R.id.momentVideoView);
            postThreeDots = itemView.findViewById(R.id.postThreeDots);
            postTimeTv = itemView.findViewById(R.id.postTimeTv);
            lastCommentUserNameTv = itemView.findViewById(R.id.lastCommentUserNameTv);
            lastCommentTv = itemView.findViewById(R.id.lastCommentTv);

        }
    }

    public interface Callback {

        void callback(List<Details> list, String index);

        void comment(String mediaId);

        void likeDislike(String value, String mediaId, ImageView imageView, TextView likeCount, String likeString);

        void popUpMenu(Details details, ImageView imageView);

        void openUserProfile(String otherUserId);

        void postShare(int pos,Details details);
    }
    private void doubleClick(InnerClassAdapter holder, int position, int check) {

        holder.postImg.setOnClickListener(new DoubleClickListener() {
            @Override
            public void onDoubleClick() {

                if (check == 2) {

                    callback.likeDislike("1", list.get(position).getMediaId(), holder.postLikeImg, holder.postLikeCountTV, list.get(position).getLikeCount());
                    holder.postLikeImg.setImageResource(R.drawable.ic_heart__5_);

                    holder.animationView.setVisibility(View.VISIBLE);
                    holder.animationView.playAnimation();

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            holder.animationView.cancelAnimation();
                            holder.animationView.setVisibility(View.GONE);
                        }
                    }, 1000);
                } else {
                    holder.animationView.setVisibility(View.VISIBLE);
                    holder.animationView.playAnimation();

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            holder.animationView.cancelAnimation();
                            holder.animationView.setVisibility(View.GONE);
                        }
                    }, 1000);
                }
            }
        });
    }

    public void loadData(List<Details> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    private void ImageLikeClick(InnerClassAdapter holder, int position, int j) {

        holder.postLikeImg.setOnClickListener(view -> {
            if (j == 0) {
                holder.postLikeImg.setImageResource(R.drawable.heart_img);
                callback.likeDislike("0", list.get(position).getMediaId(), holder.postLikeImg, holder.postLikeCountTV, list.get(position).getLikeCount());

                imageChangeCheck = 13;
//                Toast.makeText(context, "unlike", Toast.LENGTH_SHORT).show();
            } else if (j == 1) {
                holder.postLikeImg.setImageResource(R.drawable.ic_heart__5_);
                callback.likeDislike("1", list.get(position).getMediaId(), holder.postLikeImg, holder.postLikeCountTV, list.get(position).getLikeCount());
                imageChangeCheck = 13;
//                Toast.makeText(context, "like", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void videoPlayMethod(int i, InnerClassAdapter holder, int position) {

        if (i < 2) {
            holder.momentVideoView.setVisibility(View.VISIBLE);
            holder.postImg.setVisibility(View.GONE);
            holder.momentVideoView.setVideoURI(Uri.parse(list.get(position).getMedia()));
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

}
