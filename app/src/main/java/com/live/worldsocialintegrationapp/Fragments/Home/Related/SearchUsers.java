package com.live.worldsocialintegrationapp.Fragments.Home.Related;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.live.worldsocialintegrationapp.Adapters.SearchUserRVAdapter;
import com.live.worldsocialintegrationapp.Fragments.Home.UserProfile;
import com.live.worldsocialintegrationapp.Fragments.Profile.OtherUserTabs.FamilyBatchFragment;
import com.live.worldsocialintegrationapp.ModelClasses.AllPopularUsers.GetAllPopularRoot;
import com.live.worldsocialintegrationapp.ModelClasses.Family.GetAllFamilyRoot;
import com.live.worldsocialintegrationapp.ModelClasses.LiveUserByIdRoot;
import com.live.worldsocialintegrationapp.ModelClasses.UsersSearch.Detail;
import com.live.worldsocialintegrationapp.ModelClasses.UsersSearch.UsersSearchRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.agora.openvcall.ui.CallActivity;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.live.worldsocialintegrationapp.utils.CommonUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import in.aabhasjindal.otptextview.OtpTextView;


public class SearchUsers extends Fragment implements SearchUserRVAdapter.SearchUserDataCallback {

    private RecyclerView searchUserRV;
    private List<Detail> detailArrayList = new ArrayList<>();
    private EditText searchEt;
    private SearchUserRVAdapter searchUserRVAdapter;
    private RelativeLayout seachLinearLayout;
    public static int familyJoinCheck = 0;
    public static String familyId;
//    private Spinner spinner;
//    private String spinnerText;
//    private ImageView dropDownIcon;
//    private LinearLayout spinnerLlayout;
//    private TextView jasTV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_users, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        backPressed(view);
        try {
            RelativeLayout relativeLayout = requireActivity().findViewById(R.id.rlHomeFragmentTop);
            relativeLayout.setVisibility(View.GONE);
            CommonUtils.disableBottomNavigation(requireActivity());
            requireActivity().findViewById(R.id.chiragIV).setVisibility(View.GONE);
        } catch (Exception e) {

        }

        findIds(view);
        clicks(view);
       searchData();

        if (familyJoinCheck == 0) {
        } else {
            SearchUserRVAdapter.familyJoinCheck = 1;
        }
//        spinner();
    }

    private void clicks(View view) {
//        dropDownIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                spinnerLlayout.setVisibility(View.VISIBLE);
//            }
//        });
//
//        jasTV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                searchEt.setText("j");
//            }
//        });
        view.findViewById(R.id.search_txt).setOnClickListener(view1 -> ApiUsersList(searchEt.getText().toString()));
    }

    private void ApiUsersList(String s) {
        new Mvvm().getSearchUsers(requireActivity(), s).observe(requireActivity(), new Observer<UsersSearchRoot>() {
            @Override
            public void onChanged(UsersSearchRoot searchUsers) {
                if (searchUsers.getSuccess().equalsIgnoreCase("1")) {
                    detailArrayList = searchUsers.getDetails();
                    if (isAdded() && getContext()!=null){
                        searchUserRVAdapter = new SearchUserRVAdapter(detailArrayList, getContext(), SearchUsers.this);
                        searchUserRV.setAdapter(searchUserRVAdapter);
                    }
//                  searchUserRVAdapter.notifyDataSetChanged();
                 } else {
                    Toast.makeText(requireContext(), "No User Found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void findIds(View view) {

        searchUserRV = view.findViewById(R.id.searchUserRV);
        searchEt = view.findViewById(R.id.searchEt);
        seachLinearLayout = view.findViewById(R.id.seachLinearLayout);
//      dropDownIcon=view.findViewById(R.id.dropDownIcon);
//      spinnerLlayout=view.findViewById(R.id.spinnerLlayout);
//      jasTV=view.findViewById(R.id.jasTV);
    }


    private void searchData() {

        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("afterTextChanged", "afterTextChanged: "+s.length());
                if(s.length() == 7) {
                    ApiUsersList(s.toString());
                    searchEt.requestFocus();
                    searchEt.setSelection(s.toString().length());
                    filter(s.toString());

                }
                else{

                }
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void filter(String text) {
        List<Detail> filteredList = new ArrayList<>();
        for (Detail item : detailArrayList) {
            if (item.getUsername().toLowerCase().contains(text.toLowerCase()) || item.getUsername().toUpperCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        searchUserRVAdapter.filterList(filteredList);
        searchUserRVAdapter.notifyDataSetChanged();

//        ArrayList<Detail> filteredList = new ArrayList<>();
//        if (detailArrayList!=null){
//            for (Detail item : detailArrayList) {
//                if (item.getUsername().toLowerCase().contains(text.toLowerCase()) || item.getUsername().toUpperCase().contains(text.toLowerCase())
////                    || item.getName().toLowerCase().contains(text.toLowerCase()) || item.getName().toUpperCase().contains(text.toLowerCase())
//                ){
//                    filteredList.add(item);
//                }
//            }
//
////            searchUserRVAdapter.filterList(filteredList);
////            searchUserRVAdapter.notifyDataSetChanged();
//        }
    }

    @Override
    public void searchDataOfUsers(Detail detail) {

        if (detail != null) {
            UserProfile userProfile = new UserProfile();
            Bundle bundle = new Bundle();
            bundle.putSerializable("list", (Serializable) detail);
            bundle.putString("status","1");
            bundle.putInt("UserSearch", 0);
            bundle.putString("otherUser",detail.getId());
            userProfile.setArguments(bundle);
            //Toast.makeText(requireContext(), "otherUser search :- "+detail.getId(), Toast.LENGTH_SHORT).show();
            Navigation.findNavController(requireActivity(), R.id.nav_home).navigate(R.id.otherUser, bundle);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            requireActivity().findViewById(R.id.chiragIV).setVisibility(View.VISIBLE);
            CommonUtils.visibileBottomNavigation(requireActivity());
        } catch (Exception e) {
        }
    }


    private void spinner() {
//        spinner = (Spinner)getView().findViewById(R.id.searchSpinner);

        String[] arRoleSpinner = {"", "Sort", "Country", "Filter", "jas"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arRoleSpinner);
//        adapter.setDropDownViewResource(R.layout.spinner_item);
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
////                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.WHITE);
//                ((TextView) adapterView.getChildAt(0)).setTextSize(12);
//                spinnerText = String.valueOf(adapterView.getSelectedItem());
//                Toast.makeText(requireContext(), ""+String.valueOf(adapterView.getSelectedItemPosition()), Toast.LENGTH_SHORT).show();
////                searchEt.setText(adapterView.getSelectedItem().toString());
//                ((TextView) adapterView.getChildAt(0)).setVisibility(View.GONE);
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//            }
//        });
    }

    @Override
    public void sendInvitationJoinFamily(Detail detail, AppCompatButton textView) {

        Toast.makeText(requireContext(), "family " + familyId, Toast.LENGTH_SHORT).show();
        new Mvvm().sendFamilyInvitation(requireActivity(), detail.getId(), familyId).observe(requireActivity(), new Observer<GetAllFamilyRoot>() {
            @Override
            public void onChanged(GetAllFamilyRoot getAllFamilyRoot) {

                if (getAllFamilyRoot.getStatus() == 1) {
                    textView.setText("Invited");
                    //Toast.makeText(requireContext(), "1 " + getAllFamilyRoot.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    textView.setText("Invite");
                    //Toast.makeText(requireContext(), "0 " + getAllFamilyRoot.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void oepnLiveUser(Detail detail) {
        if (detail != null) {
            getLiveUserById(detail.getId());
        } else {
            if (getContext() != null) {
//                Toast.makeText(requireContext(), "detail is null", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void openfamilyprofile(Detail detail) {
        if(detail!=null){
            FamilyBatchFragment.FamilyID = detail.getId();
           Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.familyBatchFragment);
        }else{
            Toast.makeText(requireContext(), "Technical issue..", Toast.LENGTH_SHORT).show();
        }
    }


    public void enterPassword(LiveUserByIdRoot liveUserByIdRoot) {
        Dialog dialog_share = new Dialog(requireContext());
        dialog_share.setContentView(R.layout.set_room_password_dialog_box);
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog_share.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_share.setCanceledOnTouchOutside(true);

        Window window = dialog_share.getWindow();
        window.setGravity(Gravity.CENTER);
        OtpTextView liveLock = dialog_share.findViewById(R.id.liveLock_view);
        AppCompatButton confirm_pin = dialog_share.findViewById(R.id.confirm_pin);
        AppCompatButton cancel = dialog_share.findViewById(R.id.cancle_pin);
        confirm_pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (liveLock.getOTP().equalsIgnoreCase(liveUserByIdRoot.getDetails().getPassword())) {
                    sendUserToLive(liveUserByIdRoot.getDetails());
                    dialog_share.dismiss();
                } else {
                    Toast.makeText(requireContext(), "Wrong Pin Entered", Toast.LENGTH_SHORT).show();
                    dialog_share.dismiss();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_share.dismiss();
            }
        });
        dialog_share.show();
    }

    private void getLiveUserById(String userId) {

        new Mvvm().getLiveUsersById(requireActivity(), userId, AppConstants.USER_ID).observe(requireActivity(), new Observer<LiveUserByIdRoot>() {
            @Override
            public void onChanged(LiveUserByIdRoot liveUserByIdRoot) {
                if (liveUserByIdRoot != null) {
                    if (liveUserByIdRoot.getSuccess().equalsIgnoreCase("1")) {
                        if (liveUserByIdRoot.getDetails().isKickOutStatus()) {
                            Toast.makeText(requireContext(), "Your Banned", Toast.LENGTH_SHORT).show();
                        } else {
                            if (liveUserByIdRoot.getDetails().getPassword().equalsIgnoreCase("")) {
                                sendUserToLive(liveUserByIdRoot.getDetails());
                            } else {
                                enterPassword(liveUserByIdRoot);
                            }
                        }
                    } else {
                    }
                } else {
                    if (getContext() != null) {
                        Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }

    private void sendUserToLive(LiveUserByIdRoot.Details detail) {

        App.getSingletone().setLiveType("");
        Intent intent = new Intent(requireActivity(), CallActivity.class);
        intent.putExtra("channelName", detail.getChannelName());
        intent.putExtra("userId", detail.getUserId());
        intent.putExtra("liveHostIds", detail.getUserId());
        intent.putExtra("liveType", "multiLive");
        intent.putExtra("liveStatus", "host");
        intent.putExtra("token", detail.getToken());
        intent.putExtra("name", detail.getName());
        intent.putExtra("liveHostId", detail.getUserId());

        if (detail.getImageTitle() != null && !detail.getImageTitle().isEmpty()) {
            intent.putExtra("broadTitle", detail.getImageTitle());
        } else {
            intent.putExtra("broadTitle", detail.getName());
        }

        intent.putExtra("liveImage", detail.getLiveimage());
        intent.putExtra("image",detail.getUserProfileImage());


        intent.putExtra("status", "1");
        startActivity(intent);

    }

    private void backPressed(View view) {
        view.requestFocus();

        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    if (i == KeyEvent.KEYCODE_BACK) {
                        return true;
                    }
                }
                return false;
            }
        });
    }

}