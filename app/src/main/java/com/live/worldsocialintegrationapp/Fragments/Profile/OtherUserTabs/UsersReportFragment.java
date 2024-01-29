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
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;

import java.util.ArrayList;
import java.util.List;


public class UsersReportFragment extends Fragment {

    private String otherUserId, radioSelectedText;
    private AppCompatButton usrReportNextBtn1,usrReportNextBtn2;
    private List<UserReportCategoryRoot.Detail> list = new ArrayList<>();
    private RecyclerView userReportRV;
    ItemClickListenter itemClickListenter;
    RadioRVAdapter radioRVAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_users_report, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findIds(view);
        getReportCategory();
        clicks(view);


        if (getArguments() != null) {
            otherUserId = getArguments().getString("otherUserId");
        } else {
//            Toast.makeText(requireContext(), "Argmts null", Toast.LENGTH_SHORT).show();
        }
    }

    private void findIds(View view) {

        usrReportNextBtn2 = view.findViewById(R.id.usrReportNextBtn2);
        usrReportNextBtn1 = view.findViewById(R.id.usrReportNextBtn1);
        userReportRV = view.findViewById(R.id.userReportRV);
    }

    private void clicks(View view) {

        usrReportNextBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(requireContext(), "Select the Option", Toast.LENGTH_SHORT).show();
            }
        });
        usrReportNextBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("otherUserId",otherUserId);
                bundle.putString("radioSelectedId",radioSelectedText);
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.usersReportSecondFragment,bundle);
            }
        });

        view.findViewById(R.id.userRportBackImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    private void getReportCategory(){

        new Mvvm().getUserReportCategory(requireActivity()).observe(requireActivity(), new Observer<UserReportCategoryRoot>() {
            @Override
            public void onChanged(UserReportCategoryRoot userReportCategoryRoot) {

                if(userReportCategoryRoot.getSuccess().equalsIgnoreCase("1")){
                    RadioRVAdapter.i=0;
                    list= userReportCategoryRoot.getDetails();

                    itemClickListenter = new ItemClickListenter() {
                        @Override
                        public void onClick(String s) {
                            userReportRV.post(new Runnable() {
                                @Override
                                public void run() {

                                    radioRVAdapter.notifyDataSetChanged();
                                    usrReportNextBtn1.setVisibility(View.GONE);
                                    usrReportNextBtn2.setVisibility(View.VISIBLE);
                                }
                            });
                            radioSelectedText=s;
//                            Toast.makeText(requireContext(), "" + s, Toast.LENGTH_SHORT).show();
                        }
                    };

                    radioRVAdapter = new RadioRVAdapter(list, itemClickListenter);
                    userReportRV.setAdapter(radioRVAdapter);
                }else{}
            }
        });

    }
}


