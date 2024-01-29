package com.live.worldsocialintegrationapp.Adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.ModelClasses.GetComment.Detail;
import com.live.worldsocialintegrationapp.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentAdpater extends RecyclerView.Adapter<CommentAdpater.ViewHolder> {

    List<Detail> list = new ArrayList<>();
    Context context;
    Callback callback;

    public CommentAdpater(List<com.live.worldsocialintegrationapp.ModelClasses.GetComment.Detail> list, Context context, Callback callback) {
        this.list = list;
        this.context = context;
        this.callback= callback;
    }

    @NonNull
    @Override
    public CommentAdpater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.comments_rv_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdpater.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.commentTV.setText(list.get(position).getComment());
        holder.commentUserNameTV.setText(list.get(position).getName());
        holder.commentTimeTv.setText(list.get(position).getCommentCreatedTime());
        Glide.with(context).load(list.get(position).getImage()).error(R.drawable.demo_user_profile_img).into(holder.commentsUserCirImg);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                deleteCommentDialogBox(position);

                return true;
            }
        });

        holder.commentsUserCirImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.userProfile(list.get(position).getUserId());
            }
        });
    }

    private void deleteCommentDialogBox(int position) {

        AlertDialog.Builder alert = new AlertDialog.Builder(context);

        alert.setTitle("Delete Comment");
        alert.setMessage("Are you sure you want to delete?");
        alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // continue with delete
                callback.delComment(list.get(position).getFeedId(),list.get(position).getId());
                list.remove(position);
                notifyDataSetChanged();
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
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView commentTV,commentUserNameTV,commentTimeTv;
        CircleImageView commentsUserCirImg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            commentTV= itemView.findViewById(R.id.commentTV);
            commentUserNameTV=itemView.findViewById(R.id.commentUserNameTV);
            commentsUserCirImg=itemView.findViewById(R.id.commentsUserCirImg);
            commentTimeTv=itemView.findViewById(R.id.commentTimeTv);

        }
    }

    public interface  Callback{

        void delComment(String feedId,String commentId);
        void userProfile(String userId);
    }
    public void loadData(List<com.live.worldsocialintegrationapp.ModelClasses.GetComment.Detail> list)
    {
        this.list = list;
    }
}
