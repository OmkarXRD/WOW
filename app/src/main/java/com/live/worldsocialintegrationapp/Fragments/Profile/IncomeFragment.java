package com.live.worldsocialintegrationapp.Fragments.Profile;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.live.worldsocialintegrationapp.ModelClasses.ExchangeCoinsRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GetDiamondRoot;
import com.live.worldsocialintegrationapp.ModelClasses.HostApproveModelClass;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.live.worldsocialintegrationapp.utils.CommonUtils;


public class IncomeFragment extends Fragment {
    private ImageView incomeQuestionImg;
    private TextView totalDiamondsTV, goldCoinsTV, totalIncomeCoinTV,liveRecord;
    private int i = 0;
    private EditText coinsEdtx;
    private AppCompatButton exchangeCoinsBtn;
    private String totalDiamonds = "";
    boolean shouldChangeStatusBarTintToDark=true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_income, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findIds(view);
        //Toast.makeText(requireContext(), "123"+AppConstants.USER_ID, Toast.LENGTH_SHORT).show();
        new Mvvm().hostApprovedApi(requireActivity(),AppConstants.USER_ID).observe(requireActivity(), new Observer<HostApproveModelClass>() {
            @Override
            public void onChanged(HostApproveModelClass hostApproveModelClass) {
                if (hostApproveModelClass!=null){
                    if (hostApproveModelClass.getSuccess().equalsIgnoreCase("1")){
                      liveRecord.setVisibility(View.VISIBLE);
                    }else {
                        liveRecord.setVisibility(View.GONE);
                    }
                }else {
                    //Toast.makeText(requireContext(), "null", Toast.LENGTH_SHORT).show();
                }
            }
        });
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = requireActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }

        CommonUtils.disableBottomNavigation(requireActivity());
        changeEditTxt();
        clickListeners(view);
        getDiamondApi();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = requireActivity().getWindow().getDecorView();
            if (shouldChangeStatusBarTintToDark) {
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                // We want to change tint color to white again.
                // You can also record the flags in advance so that you can turn UI back completely if
                // you have set other flags before, such as translucent or full screen.
                decor.setSystemUiVisibility(0);
            }
        }
    }
    private void findIds(View view) {
        incomeQuestionImg = view.findViewById(R.id.incomeQuestionImg);
        totalDiamondsTV = view.findViewById(R.id.totalDiamondsTV);
        coinsEdtx = view.findViewById(R.id.coinsEdtx);
        liveRecord = view.findViewById(R.id.liveRecord);
        exchangeCoinsBtn = view.findViewById(R.id.exchangeCoinsBtn);
        goldCoinsTV = view.findViewById(R.id.goldCoinsTV);
        totalIncomeCoinTV = view.findViewById(R.id.totalIncomeCoinTV);
    }

    private void clickListeners(View view) {

        view.findViewById(R.id.incomeBackImg).setOnClickListener(view1 -> {
            getActivity().onBackPressed();
        });

        view.findViewById(R.id.liveRecord).setOnClickListener(view12 -> Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.liveRecordsFragment));

        view.findViewById(R.id.incomeAllTV).setOnClickListener(view13 -> {

            if (totalDiamondsTV.getText().toString().equalsIgnoreCase("0")) {
                Toast.makeText(requireContext(), "Insufficient balance", Toast.LENGTH_SHORT).show();
            } else {
                coinsEdtx.setText(totalDiamonds);
            }
        });

        incomeQuestionImg.setOnClickListener(view14 -> Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.helpCenterFragment));

        view.findViewById(R.id.incomeLIveRecodsTV).setOnClickListener(view15 -> Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.liveRecordsFragment));
        exchangeCoinsBtn.setOnClickListener(view16 -> {

            if (goldCoinsTV.getText().toString().isEmpty()) {
                Toast.makeText(requireContext(), "Enter the diamonds", Toast.LENGTH_SHORT).show();
            } else {
                exchangeDiamondsToCoinsApi(coinsEdtx.getText().toString());
            }

        });
    }

    private void getDiamondApi() {

        new Mvvm().getDaimonds(requireActivity(), AppConstants.USER_ID).observe(requireActivity(), new Observer<GetDiamondRoot>() {
            @Override
            public void onChanged(GetDiamondRoot getDiamondRoot) {

                if (getDiamondRoot != null) {
                    if (getDiamondRoot.getStatus().equalsIgnoreCase("1")) {
                        if (getDiamondRoot.getDetails().getMyDiamond().isEmpty()) {
                            totalDiamondsTV.setText("0");
                        } else {
                            totalDiamondsTV.setText(getDiamondRoot.getDetails().getMyDiamond());
                            totalDiamonds = getDiamondRoot.getDetails().getMyDiamond();
                            totalIncomeCoinTV.setText(getDiamondRoot.getDetails().getMyCoin());
                        }
                    } else {
                    }
                } else {
                    if(getContext() !=  null){
                        Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void exchangeDiamondsToCoinsApi(String diamonds) {

        if (!diamonds.isEmpty()) {
            new Mvvm().exchangeDiamondsToCoins(requireActivity(), AppConstants.USER_ID, diamonds).observe(requireActivity(), new Observer<ExchangeCoinsRoot>() {
                @Override
                public void onChanged(ExchangeCoinsRoot exchangeCoinsRoot) {
                    if (exchangeCoinsRoot.getStatus().equalsIgnoreCase("1")) {
                        getDiamondApi();
                    } else {
                    }
                }
            });
        } else {
            Toast.makeText(requireContext(), "Please enter diamonds", Toast.LENGTH_SHORT).show();
        }
    }

    private void changeEditTxt() {
        coinsEdtx.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                goldCoinsTV.setText(s.toString());
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();

        boolean shouldChangeStatusBarTintToDark = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = requireActivity().getWindow().getDecorView();
            if (shouldChangeStatusBarTintToDark) {
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                // We want to change tint color to white again.
                // You can also record the flags in advance so that you can turn UI back completely if
                // you have set other flags before, such as translucent or full screen.
                decor.setSystemUiVisibility(0);
            }
        }

    }
}