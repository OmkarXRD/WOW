package com.live.worldsocialintegrationapp.Fragments.Profile.Friends;

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
import android.widget.TextView;
import android.widget.Toast;

import com.live.worldsocialintegrationapp.Adapters.FansRVAdapter;
import com.live.worldsocialintegrationapp.ModelClasses.GetFans.Detail;
import com.live.worldsocialintegrationapp.ModelClasses.GetFans.GetFansRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.live.worldsocialintegrationapp.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;


public class FansFragment extends Fragment {

    private RecyclerView fansRV;
    private List<Detail> fansList;
    public static int check = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fans, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CommonUtils.disableBottomNavigation(requireActivity());

        if(check==1){

            view.findViewById(R.id.editFansBackImg).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().onBackPressed();
                }
            });

            view.findViewById(R.id.editFansLinearLayout).setVisibility(View.VISIBLE);
            view.findViewById(R.id.editFansLine).setVisibility(View.VISIBLE);
        }else{
            TextView topTX = (TextView) requireActivity().findViewById(R.id.profileTabsTV);
            topTX.setText("Fans");
            view.findViewById(R.id.editFansLinearLayout).setVisibility(View.GONE);
            view.findViewById(R.id.editFansLine).setVisibility(View.GONE);
        }

        findIds(view);
        getFansListApi();
    }

    private void getFansListApi() {

        new Mvvm().getFans(requireActivity(), AppConstants.USER_ID).observe(requireActivity(), new Observer<GetFansRoot>() {
            @Override
            public void onChanged(GetFansRoot getFansRoot) {
                if(getFansRoot.getStatus().equalsIgnoreCase("1")){
                    //Toast.makeText(requireContext(), "1 "+getFansRoot.getMessage(), Toast.LENGTH_SHORT).show();
                    fansList= new ArrayList<>();
                    fansList=getFansRoot.getDetails();
                    FansRVAdapter fansRVAdapter = new FansRVAdapter(fansList, requireContext(), new FansRVAdapter.Callback() {
                        @Override
                        public void callback(String otherUserId) {
                            if(otherUserId != null){
                                Bundle bundle  = new Bundle();
                                bundle.putString("otherUserId",otherUserId);
                                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.otherUser,bundle);
                            }else{
//                                Toast.makeText(requireContext(), "otherUserIsNull", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    fansRV.setAdapter(fansRVAdapter);
                }else{
//                    Toast.makeText(requireContext(), "0 "+getFansRoot.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void findIds(View view) {

        fansRV=view.findViewById(R.id.fansRV);
    }

    @Override
    public void onResume() {
        super.onResume();
        check=0;
    }
}