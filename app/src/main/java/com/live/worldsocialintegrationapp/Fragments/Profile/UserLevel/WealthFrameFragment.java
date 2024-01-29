package com.live.worldsocialintegrationapp.Fragments.Profile.UserLevel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.live.worldsocialintegrationapp.Adapters.WealthInsideFrameRVAdapter;
import com.live.worldsocialintegrationapp.ModelClasses.UserLevel.GetCarUserLevel;
import com.live.worldsocialintegrationapp.ModelClasses.UserLevel.GetFrameByLevel;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;


public class WealthFrameFragment extends Fragment {

     WealthInsideFrameRVAdapter wealthInsideFrameRVAdapter;
    private RecyclerView weathInsideFrameRV;
    private AppCompatButton wealthInsideFrameObtainLvBtn;
    private TextView wealthFrameLetterTV;
    String letter = "";
    List<GetFrameByLevel.Detail> list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wealth_frame, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findIds(view);
        clicks(view);
        getFirstLetterName();
        getFrameLevelApi();

    }

    private void getFirstLetterName() {
        String name = App.getSharedpref().getString("name");
        for (int i = 0; i < name.length(); i++) {
            if (i == 0) {
                letter += name.charAt(i);
            }
        }
        wealthFrameLetterTV.setText(letter);
    }

    private void findIds(View view) {
        weathInsideFrameRV = view.findViewById(R.id.weathInsideFrameRV);
        wealthInsideFrameObtainLvBtn = view.findViewById(R.id.wealthInsideFrameObtainLvBtn);
        wealthFrameLetterTV = view.findViewById(R.id.wealthFrameLetterTV);
    }

    private void clicks(View view) {

        view.findViewById(R.id.wealthFrameBackImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        wealthInsideFrameObtainLvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.howLevelUpFragment);
            }
        });

    }

    private void getFrameLevelApi() {

        new Mvvm().getFrameLevel(requireActivity(), AppConstants.USER_ID).observe(requireActivity(), new Observer<GetFrameByLevel>() {
            @Override
            public void onChanged(GetFrameByLevel getUserLevelRoot) {

                if(getUserLevelRoot != null) {
                    if (getUserLevelRoot.getStatus() == 1) {
//                    Toast.makeText(requireContext(), "1 " + getUserLevelRoot.getMessage(), Toast.LENGTH_SHORT).show();

                        list = new ArrayList<>();
                        list = getUserLevelRoot.getDetails();

                        Log.d("WealthFragment", "level " + getUserLevelRoot.getDetails().get(1).getLevel().toString());
                        wealthInsideFrameRVAdapter = new WealthInsideFrameRVAdapter(list, requireContext());
                        weathInsideFrameRV.setAdapter(wealthInsideFrameRVAdapter);

                    } else {
//                    Toast.makeText(requireContext(), "0 " + getUserLevelRoot.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }



}