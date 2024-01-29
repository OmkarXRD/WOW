package com.live.worldsocialintegrationapp.Fragments.Union.UnionInnerFragments;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.Adapters.MeetItemsAdapter;
import com.live.worldsocialintegrationapp.ModelClasses.Meet.RootMeet;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.databinding.FragmentMeetBinding;


import java.util.ArrayList;
import java.util.List;

public class MeetFragment extends Fragment {

    FragmentMeetBinding binding;
    MeetItemsAdapter meetItemsAdapter;
    List<RootMeet.Detail> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMeetBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL);
        binding.meetRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        setAdapter();
        apiMeet();
        meetItemsAdapter = new MeetItemsAdapter(new ArrayList<>(), requireContext(), new MeetItemsAdapter.Callback() {

            @Override
            public void callback(RootMeet.Detail detail, String value, int pos) {

                if (value.equalsIgnoreCase("1")) {

                    meetClickDialogBox(detail, pos);

                } else {
//                    Toast.makeText(requireContext(), "Callback is null", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void meetClickDialogBox(RootMeet.Detail detail, int pos) {

        Dialog dialog_share = new Dialog(requireContext());
        dialog_share.setContentView(R.layout.meet_click_dialog_box);
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog_share.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog_share.setCanceledOnTouchOutside(true);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog_share.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog_share.getWindow().setAttributes(layoutParams);
        Window window = dialog_share.getWindow();
        window.setGravity(Gravity.CENTER);

        TextView name = dialog_share.findViewById(R.id.nameD);
        ImageView userImage = dialog_share.findViewById(R.id.imageD);
        TextView age = dialog_share.findViewById(R.id.ageD);
        TextView timing = dialog_share.findViewById(R.id.timing);
        ImageView genderImage=dialog_share.findViewById(R.id.female);
        TextView meetClickUsernameTv=dialog_share.findViewById(R.id.meetClickUsernameTv);

        if (detail.getGender().equalsIgnoreCase("male")){
            genderImage.setImageResource(R.drawable.male_gender__4_);
        }else{
            genderImage.setImageResource(R.drawable.femenine);
        }
        if(detail.getName()==null && detail.getName().isEmpty()){
            meetClickUsernameTv.setText("Username");
        }else {
            meetClickUsernameTv.setText(detail.getName());
        }
        name.setText(detail.getName());
        Glide.with(requireActivity()).load(detail.getImageDp()).error(R.drawable.user_avatar).into(userImage);
        age.setText(detail.getAge());
        timing.setText(detail.getMyExp());


        AppCompatButton sayHiBtn = dialog_share.findViewById(R.id.sayHiBtn);
        sayHiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_share.dismiss();
                Bundle bundle = new Bundle();
                bundle.putString("otherUserId", detail.getId());
                bundle.putString("otherUserImg", detail.getImageDp());
                bundle.putString("otherUserName", detail.getName());
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.messagesFragment, bundle);
                //         Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.meetMsgFragment);
            }
        });

        dialog_share.show();
    }

    private void apiMeet() {
        new Mvvm().getTopUser(requireActivity()).observe(requireActivity(), new Observer<RootMeet>() {
            @Override
            public void onChanged(RootMeet rootMeet) {
                if (rootMeet != null) {

                    if (rootMeet.getSuccess().equalsIgnoreCase("1")) {

//                        Toast.makeText(requireActivity(), "Meet", Toast.LENGTH_SHORT).show();
                        list = rootMeet.getDetails();
                        meetItemsAdapter.loadData(list);
//                        Toast.makeText(requireActivity(), "ok" + list, Toast.LENGTH_SHORT).show();


                    }
                } else {
//                    Toast.makeText(requireActivity(), "Root is null", Toast.LENGTH_SHORT).show();
                }

                binding.meetRecyclerView.setAdapter(meetItemsAdapter);
            }
        });
    }


    private void setAdapter() {


//        binding.meetRecyclerView.setAdapter( new MeetItemsAdapter() );

    }
}