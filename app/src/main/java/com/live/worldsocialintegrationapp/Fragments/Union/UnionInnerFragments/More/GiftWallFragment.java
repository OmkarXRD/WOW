package com.live.worldsocialintegrationapp.Fragments.Union.UnionInnerFragments.More;

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
import android.widget.Toast;

import com.live.worldsocialintegrationapp.Adapters.GiftWallAdapter;
import com.live.worldsocialintegrationapp.ModelClasses.GiftWall.RootGiftWall;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;


public class GiftWallFragment extends Fragment {
    ;

    private RecyclerView giftWallRV;
    GiftWallAdapter giftWallAdapter;
    List<RootGiftWall.Detail> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gift_wall, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CommonUtils.disableBottomNavigation(requireActivity());

        findIds(view);
        apiGiftWall(view);
        setAdapter(view);

    }

    private void setAdapter(View view) {
        giftWallAdapter = new GiftWallAdapter(list, requireContext(), new GiftWallAdapter.Callback() {
            @Override
            public void callback(RootGiftWall.Detail detail, int pos) {

            }
        });
    }


    private void findIds(View view) {
        giftWallRV = view.findViewById(R.id.giftWallRV);

        view.findViewById(R.id.giftWallQuestionImge).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireActivity().findViewById(R.id.giftWallQuestionImge)).navigate(R.id.giftWallQuestionFragment);
            }
        });

        view.findViewById(R.id.giftwallBackImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }


    private void apiGiftWall(View view) {
        new Mvvm().getGiftWall(requireActivity()).observe(requireActivity(), new Observer<RootGiftWall>() {
            @Override
            public void onChanged(RootGiftWall rootGiftWall) {
                if (rootGiftWall != null) {

                    if (rootGiftWall.getSuccess().equalsIgnoreCase("1")) {
                        list = rootGiftWall.getDetails();
                        giftWallAdapter.loadData(list);
                        giftWallRV.setAdapter(giftWallAdapter);
                    }
                } else {
                    if (getContext() != null) {
                        Toast.makeText(requireActivity(), "Technical issue", Toast.LENGTH_SHORT).show();
                    } else {
                    }

                }

            }

        });
    }
}