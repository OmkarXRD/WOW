package com.live.worldsocialintegrationapp.Fragments.Profile.OtherUserTabs;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.live.worldsocialintegrationapp.Adapters.RadioRVAdapter;
import com.live.worldsocialintegrationapp.ModelClasses.UserReportCategoryRoot;
import com.live.worldsocialintegrationapp.ModelClasses.UserReportRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;


public class UsersReportSecondFragment extends Fragment {

    private AppCompatButton reportBtn1,reportBtn2;
    private String otherUserId ,radioSelectedId,radioSelectedType;
    private List<UserReportCategoryRoot.Detail> list=new ArrayList<>();
    private RecyclerView userReportSecondRV;
    ItemClickListenter itemClickListenter;
    RadioRVAdapter radioRVAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_users_report_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findIds(view);

        getSubCategory();
        clicks(view);

        if(getArguments() != null){
            Bundle bundle = new Bundle();
            otherUserId = getArguments().getString("otherUserId");
            radioSelectedId=getArguments().getString("radioSelectedId");
        }else{
//            Toast.makeText(requireContext(), "Argments null", Toast.LENGTH_SHORT).show();
        }

    }

    private void getSubCategory() {


        new Mvvm().getSubCategory(requireActivity()).observe(requireActivity(), new Observer<UserReportCategoryRoot>() {
            @Override
            public void onChanged(UserReportCategoryRoot userReportCategoryRoot) {

                if(userReportCategoryRoot.getSuccess().equalsIgnoreCase("1")){
                    RadioRVAdapter.i=1;
                    list = userReportCategoryRoot.getDetails();
//                    Toast.makeText(requireContext(), "1 "+userReportCategoryRoot.getMessage(), Toast.LENGTH_SHORT).show();

                    itemClickListenter = new ItemClickListenter() {
                        @Override
                        public void onClick(String s) {
                            userReportSecondRV.post(new Runnable() {
                                @Override
                                public void run() {
                                    radioRVAdapter.notifyDataSetChanged();
                                    reportBtn1.setVisibility(View.GONE);
                                    reportBtn2.setVisibility(View.VISIBLE);
                                }
                            });
                            radioSelectedType=s;
                            Toast.makeText(requireContext(), "" + s, Toast.LENGTH_SHORT).show();
                        }
                    };
                    radioRVAdapter = new RadioRVAdapter(list, itemClickListenter);
                    userReportSecondRV.setAdapter(radioRVAdapter);
                }else{
//                    Toast.makeText(requireContext(), "0 "+userReportCategoryRoot.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void findIds(View view) {

        reportBtn2=view.findViewById(R.id.reportBtn2);
        reportBtn1=view.findViewById(R.id.reportBtn1);
        userReportSecondRV=view.findViewById(R.id.userReportSecondRV);
    }

    private void clicks(View view) {

        view.findViewById(R.id.userReportSecondBackImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        reportBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userReportApi();
            }
        });
    }


    private void userReportApi(){

//        Toast.makeText(requireContext(), "othrUserId : "+otherUserId, Toast.LENGTH_SHORT).show();
//        Toast.makeText(requireContext(), "radioSelectedId : "+radioSelectedId, Toast.LENGTH_SHORT).show();
//        Toast.makeText(requireContext(), "radioSelectedType : "+radioSelectedType, Toast.LENGTH_SHORT).show();


        new Mvvm().userReport(requireActivity(),radioSelectedId,radioSelectedType,AppConstants.USER_ID,otherUserId)
                .observe(requireActivity(), new Observer<UserReportRoot>() {
            @Override
            public void onChanged(UserReportRoot userReportRoot) {

                if(userReportRoot.getSuccess().equalsIgnoreCase("1")){
//                    Toast.makeText(requireContext(), " "+userReportRoot.getMessage(), Toast.LENGTH_SHORT).show();
                    Bundle bundle = new Bundle();
                    bundle.putString("otherUserId", otherUserId);
                    Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.otherUser,bundle);

                }else{
//                    Toast.makeText(requireContext(), " "+userReportRoot.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}