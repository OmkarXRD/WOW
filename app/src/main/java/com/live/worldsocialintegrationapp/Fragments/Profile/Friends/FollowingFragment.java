package com.live.worldsocialintegrationapp.Fragments.Profile.Friends;

import android.app.Activity;
import android.os.Build;
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
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.live.worldsocialintegrationapp.Adapters.FollowingRVAdapter;
import com.live.worldsocialintegrationapp.ModelClasses.GetFollowing.Detail;
import com.live.worldsocialintegrationapp.ModelClasses.GetFollowing.GetFollowingRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.AppConstants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class FollowingFragment extends Fragment {

   private RecyclerView followingRV;
   private List<Detail> followingList;
   public static int check =0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setStatusBar();
        return inflater.inflate(R.layout.fragment_following2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findIds(view);

//        Toast.makeText(requireContext(), "check "+check, Toast.LENGTH_SHORT).show();
        if(check ==1){
            view.findViewById(R.id.editfollowingLinearLayout).setVisibility(View.VISIBLE);
            view.findViewById(R.id.editFollowingLine).setVisibility(View.VISIBLE);

            view.findViewById(R.id.followingBackImg).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().onBackPressed();
                }
            });
        }else{
            view.findViewById(R.id.editfollowingLinearLayout).setVisibility(View.GONE);
            view.findViewById(R.id.editFollowingLine).setVisibility(View.GONE);
            TextView topTX = (TextView) requireActivity().findViewById(R.id.profileTabsTV);
            topTX.setText("Following");
        }

        getFollowingApi();
    }

    //Method to adjust the system UI as per required
    private void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Activity activity = getActivity();
            if (activity != null) {
                Window window = activity.getWindow();
                View decorView = window.getDecorView();
                decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            }
        }
    }
    private void getFollowingApi() {

        new Mvvm().getFollowing(requireActivity(), AppConstants.USER_ID).observe(requireActivity(), new Observer<GetFollowingRoot>() {
            @Override
            public void onChanged(GetFollowingRoot getFollowingRoot) {
                if(getFollowingRoot.getSuccess().equalsIgnoreCase("1")){
                    //Toast.makeText(requireContext(), "1 "+getFollowingRoot.getMessage(), Toast.LENGTH_SHORT).show();
                    followingList = new ArrayList<>();
                    followingList= getFollowingRoot.getDetails();
                    FollowingRVAdapter followingRVAdapter = new FollowingRVAdapter(requireContext(), followingList, new FollowingRVAdapter.Callback() {
                        @Override
                        public void callback(String otherUserId,Detail detail) {
                            if(otherUserId != null){
                                //sumit
                                Bundle bundle  = new Bundle();
                                bundle.putString("otherUserId",otherUserId);
                                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.otherUser,bundle);
                            }else{
                                //Toast.makeText(requireContext(), "otherUserIsNull", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    followingRV.setAdapter(followingRVAdapter);
                }else{
//                    Toast.makeText(requireContext(), "0 "+getFollowingRoot.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void findIds(View view) {

        followingRV=view.findViewById(R.id.followingRV);
    }

    @Override
    public void onResume() {
        super.onResume();
        check=0;
    }
}