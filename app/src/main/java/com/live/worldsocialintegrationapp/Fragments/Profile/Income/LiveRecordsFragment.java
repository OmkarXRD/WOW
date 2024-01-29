package com.live.worldsocialintegrationapp.Fragments.Profile.Income;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.live.worldsocialintegrationapp.ModelClasses.DailyDateLiveRecord;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.live.worldsocialintegrationapp.utils.CommonUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class LiveRecordsFragment extends Fragment {
    private TextView text,dailydateTextView,dailyTextDiamonds,dailyDuration,monthlyDuration,validDays,monthlyDiamonds;
    private String currentDateAndTime,selected_date = "";
    private SimpleDateFormat simpleDateFormat;
    private int mYear, mDay, mMonth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_live_records, container, false);
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        text = view.findViewById(R.id.monthydateTextView);
        dailydateTextView=view.findViewById(R.id.dailydateTextView);
        dailyTextDiamonds = view.findViewById(R.id.dailyTextDiamonds);
        dailyDuration = view.findViewById(R.id.dailyDuration);
        monthlyDuration = view.findViewById(R.id.monthlyDuration);
        validDays = view.findViewById(R.id.validDays);
        monthlyDiamonds = view.findViewById(R.id.monthlyDiamonds);

        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        currentDateAndTime = simpleDateFormat.format(new Date());
        text.setText(currentDateAndTime);
        Log.d("TAG0", "hitApi: "+text.getText().toString());
        hitApi(text.getText().toString());

        text.setOnClickListener(view1 -> {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(android.widget.DatePicker view, int i, int i1, int i2) {
                    Date myDate = new Date();
                    myDate.setMonth(i1);
                    myDate.setYear(i - 1900);
                    myDate.setDate(i2);
                    SimpleDateFormat dmyFormat = new SimpleDateFormat("dd-MM-yyyy");
                    // Format the date to Strings
                    String mdy = dmyFormat.format(myDate);

                    selected_date = mdy;
                    text.setText(selected_date);
                    Log.d("TAG1", "hitApi: "+text.getText().toString());
                    hitApi(text.getText().toString());
                }

            }, mYear, mMonth, mDay);
            datePickerDialog.show();
        });
        view.findViewById(R.id.liveRecordsBackImg).setOnClickListener(view12 -> getActivity().onBackPressed());
    }

    @SuppressLint("SetTextI18n")
    private void hitApi(String dailydate) {
        Log.d("TAG2", "hitApi: "+dailydate);
        text.setText(dailydate);
        new Mvvm().monthlyLiveDateRecord(requireActivity(),AppConstants.USER_ID,dailydate).observe(requireActivity(), dailyDateLiveRecord -> {
            if (dailyDateLiveRecord!=null){
                if (dailyDateLiveRecord.getStatus()==1){
                    monthlyDiamonds.setText("Total Diamonds : "+dailyDateLiveRecord.getDetails().getGiftsRecievedToday());
                    monthlyDuration.setText("Total Active Duration : "+dailyDateLiveRecord.getDetails().getTotalLiveTimeToday());
                    validDays.setText("Total Valid Days : "+dailyDateLiveRecord.getDetails().getValidDays());
                }
            }
        });

    }
}