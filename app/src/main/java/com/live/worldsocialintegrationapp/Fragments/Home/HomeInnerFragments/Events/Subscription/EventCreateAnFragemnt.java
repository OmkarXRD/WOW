package com.live.worldsocialintegrationapp.Fragments.Home.HomeInnerFragments.Events.Subscription;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.PagerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TimePicker;


import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.utils.CommonUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class EventCreateAnFragemnt extends Fragment {


    private EditText eventTopicEdtx, eventsRulesEdtex;
    private TextView eventStartTimeTv;
    private String type, eventType;
    private RelativeLayout selectTimeRL;
    private int timeSetStatus = 0; // A clicked button result.
    private RadioGroup set_radiogroup;
    AppCompatButton eventCreateAnFragemntGreyBtn, eventCreateAnFragemntBtn;
    private String date, time;

    int editTextCheck =0;


    final Calendar myCalendar = Calendar.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_create_an_fragemnt, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CommonUtils.disableBottomNavigation(requireActivity());
        findIds(view);
        onClick(view);
        dataPicker();

    }

    private void findIds(View view) {
        eventTopicEdtx = view.findViewById(R.id.eventTopicEdtx);
        eventsRulesEdtex = view.findViewById(R.id.eventsRulesEdtex);
        eventStartTimeTv = view.findViewById(R.id.eventStartTimeTv);
        selectTimeRL = view.findViewById(R.id.selectTime);
        set_radiogroup = view.findViewById(R.id.set_radiogroup);
        eventCreateAnFragemntBtn = view.findViewById(R.id.eventCreateAnFragemntBtn);
        eventCreateAnFragemntGreyBtn = view.findViewById(R.id.eventCreateAnFragemntGreyBtn);


    }

    private void onClick(View view) {

        view.findViewById(R.id.eventCreateAnFragemntBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(editTextCheck == 1) {
                   type  = eventTopicEdtx.getText().toString();
                }else{
                    
                }
                if (type.isEmpty()) {
                    Toast.makeText(requireContext(), "type not be empty", Toast.LENGTH_SHORT).show();
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("eventTopicEdtx", eventTopicEdtx.getText().toString());
                    bundle.putString("eventsRulesEdtex", eventsRulesEdtex.getText().toString());
                    bundle.putString("eventStartTimeTv", eventStartTimeTv.getText().toString());  //dateTimeFormat y-m-d  h:m:s
                    bundle.putString("type", type);
                    Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.eventSelectAPosterFragment, bundle);
                }

            }
        });


        set_radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                RadioButton rb = view.findViewById(checkedId);
                eventType = rb.getText().toString();
                int index = set_radiogroup.indexOfChild(rb);

                if (index == 0) {
                    editTextCheck = 1;
                    eventTopicEdtx.setEnabled(true);
                    Toast.makeText(requireContext(), "0 ", Toast.LENGTH_SHORT).show();
                } else if (index == 1) {
                    editTextCheck = 0;
                    eventTopicEdtx.setEnabled(false);
                    eventTopicEdtx.setText("");
                    eventTopicEdtx.setText("Birthday party celebration");
                    type = "Birthday party celebration";
                    Toast.makeText(requireContext(), "1 ", Toast.LENGTH_SHORT).show();
                } else {
                    editTextCheck = 0;
                    eventTopicEdtx.setEnabled(false);
                    eventTopicEdtx.setText("");
                    eventTopicEdtx.setText("Anniversary celebration");
                    type = "Anniversary celebration";
                    Toast.makeText(requireContext(), "2 ", Toast.LENGTH_SHORT).show();
                }

                if (rb.isChecked()) {
                    eventCreateAnFragemntBtn.setVisibility(View.VISIBLE);
                    eventCreateAnFragemntGreyBtn.setVisibility(View.GONE);
                } else {
                    eventCreateAnFragemntBtn.setVisibility(View.GONE);
                    eventCreateAnFragemntGreyBtn.setVisibility(View.VISIBLE);
                }
            }
        });


        view.findViewById(R.id.createAnEventBackImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    private void dataPicker() {

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, day);
                updateLabel();
            }
        };

        selectTimeRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), date, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();

                datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {

                        if (timeSetStatus == 0) {
                            timePick();
                        } else {
                            dialog.cancel();
                        }

                    }
                });
                datePickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        if (timeSetStatus == 0) {
                            timePick();
                        } else {
                            datePickerDialog.dismiss();
                        }
                    }
                });
            }
        });
    }

    private void updateLabel() {
        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
//        eventStartTimeTv.setText(dateFormat.format(myCalendar.getTime()));
        date = dateFormat.format(myCalendar.getTime());
        Toast.makeText(requireContext(), "" + dateFormat.format(myCalendar.getTime()), Toast.LENGTH_SHORT).show();


    }

    private void timePick() {

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm", Locale.US);
        Calendar mcurrentTime = dateFormat.getInstance().getCalendar();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(requireContext(),
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        time = hourOfDay + ":" + minute + ":03";
                        eventStartTimeTv.setText(date + " " + time);

                        Toast.makeText(requireContext(), "" + hourOfDay + ":" + minute, Toast.LENGTH_SHORT).show();
                    }
                }, hour, minute, true);
        timePickerDialog.show();
    }

}