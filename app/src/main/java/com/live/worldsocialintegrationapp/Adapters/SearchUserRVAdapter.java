package com.live.worldsocialintegrationapp.Adapters;

import android.annotation.SuppressLint;
import android.app.AppOpsManager;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.ModelClasses.AllPopularUsers.GetAllPopularRoot;
import com.live.worldsocialintegrationapp.ModelClasses.UsersSearch.Detail;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchUserRVAdapter extends RecyclerView.Adapter<SearchUserRVAdapter.ViewHolder> {

    private List<Detail> list;
    private Context context;
    private SearchUserDataCallback searchUserDataCallback;
    public static int familyJoinCheck = 0;

    public SearchUserRVAdapter(List<Detail> list, Context context, SearchUserDataCallback searchUserDataCallback) {
        this.list = list;
        this.context = context;
        this.searchUserDataCallback = searchUserDataCallback;
    }

    @NonNull
    @Override
    public SearchUserRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.search_user_rv_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchUserRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context).load(list.get(position).getImage()).error(R.drawable.demo_user_profile_img).into(holder.searchUserCircleIV);

        if (list.get(position).getName().length() != 0 && list.get(position).getName() != null && list.get(position).getId() != AppConstants.USER_ID
                && !list.get(position).getId().equalsIgnoreCase(AppConstants.USER_ID)) {
            holder.searchUsrMainRL.setVisibility(View.VISIBLE);

            holder.searchedUserNameTV.setText(list.get(position).getName());

            holder.itemView.setOnClickListener(v -> {
                if (list.get(position).getFamilyId() == null || list.get(position).getFamilyId().length() == 0 || list.get(position).getFamilyId().equalsIgnoreCase("")) {
                    searchUserDataCallback.searchDataOfUsers(list.get(position));
                }
                else if(list.get(position).getUsername() == null || list.get(position).getUsername().length() == 0 || list.get(position).getUsername().equalsIgnoreCase("")) {
                    searchUserDataCallback.openfamilyprofile(list.get(position));
               }
            });

//            if(list.get(position).getLiveStatus()){
//                holder.liveRound.setVisibility(View.VISIBLE);
//
//            }else{
            holder.liveRound.setVisibility(View.GONE);
//            }
            //open live user
//            holder.liveRound.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    searchUserDataCallback.oepnLiveUser(list.get(position));
//                }
//            });

            //for Family join
            if (familyJoinCheck == 1) {
                holder.searchUsersFamilyInviteBtn.setVisibility(View.GONE);
            } else {
                holder.searchUsersFamilyInviteBtn.setVisibility(View.GONE);
            }

            holder.searchUsersFamilyInviteBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    searchUserDataCallback.sendInvitationJoinFamily(list.get(position), holder.searchUsersFamilyInviteBtn);
                }
            });
        } else {
            holder.searchUsrMainRL.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size() > 0 ? list.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView searchUserCircleIV;
        TextView searchedUserNameTV;
        RelativeLayout searchUsrMainRL;
        ImageView liveRound;
        AppCompatButton searchUsersFamilyInviteBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            searchUserCircleIV = itemView.findViewById(R.id.searchUserCircleIV);
            searchedUserNameTV = itemView.findViewById(R.id.searchedUserNameTV);
            searchUsrMainRL = itemView.findViewById(R.id.searchUsrMainRL);
            liveRound = itemView.findViewById(R.id.liveRound);
            searchUsersFamilyInviteBtn = itemView.findViewById(R.id.searchUsersFamilyInviteBtn);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void filterList(List<Detail> list1) {
        list = list1;
        notifyDataSetChanged();
    }

    public interface SearchUserDataCallback {
        void searchDataOfUsers(Detail detail);

        void sendInvitationJoinFamily(Detail detail, AppCompatButton textView);

        void oepnLiveUser(Detail detail);

        void openfamilyprofile(Detail detail);

    }

}
