package com.live.worldsocialintegrationapp.Fragments.Union.UnionInnerFragments.Meet;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.utils.CommonUtils;

public class MeetMsgFragment extends Fragment {

    ImageView meetMsgbackImg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meet_msg, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CommonUtils.disableBottomNavigation(requireActivity());
        findIds(view);
        clicks(view);
    }

    private void clicks(View view) {
        meetMsgbackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

    }

    private void findIds(View view) {

        meetMsgbackImg=view.findViewById(R.id.meetMsgbackImg);
    }
}