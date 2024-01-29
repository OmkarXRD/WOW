package com.live.worldsocialintegrationapp.Fragments.Profile.Family;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.live.worldsocialintegrationapp.R;

public class FamilyRulesFragment extends Fragment {

    int j=0;
    private RelativeLayout question1,question2,question3,question4,question5,question6,question7,question8,question9,question10;
    private RelativeLayout  ans1,ans2,ans3,ans4,ans5,ans6,ans7,ans8,ans9,ans10;
    private ImageView questionImg1,questionImg2,questionImg3,questionImg4,questionImg5,questionImg6,questionImg7,questionImg8,questionImg9,questionImg10;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_family_rules, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findIds(view);
        clicks(view);

    }

    private void findIds(View view) {

        question1=view.findViewById(R.id.question1);
        question2=view.findViewById(R.id.question2);
        question3=view.findViewById(R.id.question3);
        question4=view.findViewById(R.id.question4);
        question5=view.findViewById(R.id.question5);
        question6=view.findViewById(R.id.question6);
        question7=view.findViewById(R.id.question7);
        question8=view.findViewById(R.id.question8);
        question9=view.findViewById(R.id.question9);
        question10=view.findViewById(R.id.question10);

        ans1=view.findViewById(R.id.ans1);
        ans2=view.findViewById(R.id.ans2);
        ans3=view.findViewById(R.id.ans3);
        ans4=view.findViewById(R.id.ans4);
        ans5=view.findViewById(R.id.ans5);
        ans6=view.findViewById(R.id.ans6);
        ans7=view.findViewById(R.id.ans7);
        ans8=view.findViewById(R.id.ans8);
        ans9=view.findViewById(R.id.ans9);
        ans10=view.findViewById(R.id.ans10);

        questionImg1=view.findViewById(R.id.questionImg1);
        questionImg2=view.findViewById(R.id.questionImg2);
        questionImg3=view.findViewById(R.id.questionImg3);
        questionImg4=view.findViewById(R.id.questionImg4);
        questionImg5=view.findViewById(R.id.questionImg5);
        questionImg6=view.findViewById(R.id.questionImg6);
        questionImg7=view.findViewById(R.id.questionImg7);
        questionImg8=view.findViewById(R.id.questionImg8);
        questionImg9=view.findViewById(R.id.questionImg9);
        questionImg10=view.findViewById(R.id.questionImg10);
    }

    private void clicks(View view) {

        view.findViewById(R.id.familyRulesBackImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        question1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(j==0){
                    ans1.setVisibility(View.VISIBLE);
                    questionImg1.setRotation(0);
                    j=1;
                }else{
                    ans1.setVisibility(View.GONE);
                    questionImg1.setRotation(-90);
                    j=0;
                }
            }
        });

        question2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(j==0){
                    ans2.setVisibility(View.VISIBLE);
                    questionImg2.setRotation(0);
                    j=1;
                }else{
                    ans2.setVisibility(View.GONE);
                    questionImg2.setRotation(-90);
                    j=0;
                }
            }
        });

        question3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(j==0){
                    ans3.setVisibility(View.VISIBLE);
                    questionImg3.setRotation(0);
                    j=1;
                }else{
                    ans3.setVisibility(View.GONE);
                    questionImg3.setRotation(-90);
                    j=0;
                }
            }
        });
        question4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(j==0){
                    ans4.setVisibility(View.VISIBLE);
                    questionImg4.setRotation(0);
                    j=1;
                }else{
                    ans4.setVisibility(View.GONE);
                    questionImg4.setRotation(-90);
                    j=0;
                }
            }
        });
        question5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(j==0){
                    ans5.setVisibility(View.VISIBLE);
                    questionImg5.setRotation(0);
                    j=1;
                }else{
                    ans5.setVisibility(View.GONE);
                    questionImg5.setRotation(-90);
                    j=0;
                }
            }
        });
        question6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(j==0){
                    ans6.setVisibility(View.VISIBLE);
                    questionImg6.setRotation(0);
                    j=1;
                }else{
                    ans6.setVisibility(View.GONE);
                    questionImg6.setRotation(-90);
                    j=0;
                }
            }
        });
        question7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(j==0){
                    ans7.setVisibility(View.VISIBLE);
                    questionImg7.setRotation(0);
                    j=1;
                }else{
                    ans7.setVisibility(View.GONE);
                    questionImg7.setRotation(-90);
                    j=0;
                }
            }
        });
        question8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(j==0){
                    ans8.setVisibility(View.VISIBLE);
                    questionImg8.setRotation(0);
                    j=1;
                }else{
                    ans8.setVisibility(View.GONE);
                    questionImg8.setRotation(-90);
                    j=0;
                }
            }
        });
        question9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(j==0){
                    ans9.setVisibility(View.VISIBLE);
                    questionImg9.setRotation(0);
                    j=1;
                }else{
                    ans9.setVisibility(View.GONE);
                    questionImg9.setRotation(-90);
                    j=0;
                }
            }
        });
        question10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(j==0){
                    ans10.setVisibility(View.VISIBLE);
                    questionImg10.setRotation(0);
                    j=1;
                }else{
                    ans10.setVisibility(View.GONE);
                    questionImg10.setRotation(-90);
                    j=0;
                }
            }
        });

    }
}