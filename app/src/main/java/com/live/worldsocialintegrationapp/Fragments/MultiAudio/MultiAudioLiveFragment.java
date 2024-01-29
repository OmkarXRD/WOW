package com.live.worldsocialintegrationapp.Fragments.MultiAudio;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.live.worldsocialintegrationapp.Adapters.MultiAudioLiveAdapter;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.databinding.FragmentMultiAudioLiveBinding;


public class MultiAudioLiveFragment extends Fragment {

    FragmentMultiAudioLiveBinding binding;
    private Integer check = 0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMultiAudioLiveBinding.inflate( inflater, container, false );

        setAdapter();

        disableBottomNavigation();

        onClicks();

        return binding.getRoot();
    }


    private void setAdapter() {

        binding.rvMultiLiveAudio.setAdapter( new MultiAudioLiveAdapter() );
    }

    private void disableBottomNavigation() {

        View view1 = requireActivity().findViewById( R.id.bottom_lay );

        view1.setVisibility( View.GONE );
    }

    private void onClicks() {

        binding.mainPhoto.setOnClickListener( view -> Navigation.findNavController( binding.getRoot() ).navigate( R.id.goLiveFragment2 ));

        binding.showComments.setOnClickListener( view -> binding.llComments.setVisibility( View.VISIBLE ));

        binding.emojis.setOnClickListener( view -> {
            check=0;
            showDialogBox();

        } );
        binding.llshare.setOnClickListener( view -> {
            check= 1;
            showDialogBox();
        } );
        binding.powerButton.setOnClickListener( view -> {
            check=2;
            showDialogBox();
        } );
        binding.board.setOnClickListener( view -> {
            check=3;
            showDialogBox();
        } );
    }

    private void showDialogBox() {

        if (check==0){
            Dialog dialogChooseAlbum = new Dialog(requireContext());

            dialogChooseAlbum.requestWindowFeature( Window.FEATURE_NO_TITLE);

            dialogChooseAlbum.setContentView(R.layout.smile_bottom_sheet);

            dialogChooseAlbum.setCanceledOnTouchOutside(true);

            dialogChooseAlbum.setCancelable(true);

            dialogChooseAlbum.getWindow().setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);

            dialogChooseAlbum.getWindow().setBackgroundDrawable(new ColorDrawable( Color.TRANSPARENT));

//        dialogChooseAlbum.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

            dialogChooseAlbum.getWindow().setGravity( Gravity.BOTTOM);

            dialogChooseAlbum.show();
        }

        if (check==1){
            Dialog dialogChooseAlbum = new Dialog(requireContext());

            dialogChooseAlbum.requestWindowFeature( Window.FEATURE_NO_TITLE);

            dialogChooseAlbum.setContentView(R.layout.share_bottom_sheet);

            dialogChooseAlbum.setCanceledOnTouchOutside(true);

            dialogChooseAlbum.setCancelable(true);

            dialogChooseAlbum.getWindow().setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);

            dialogChooseAlbum.getWindow().setBackgroundDrawable(new ColorDrawable( Color.TRANSPARENT));

//        dialogChooseAlbum.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

            dialogChooseAlbum.getWindow().setGravity( Gravity.BOTTOM);

            dialogChooseAlbum.show();
        }
        if (check==2){
            Dialog dialogChooseAlbum = new Dialog(requireContext());

            dialogChooseAlbum.requestWindowFeature( Window.FEATURE_NO_TITLE);

            dialogChooseAlbum.setContentView(R.layout.disconnect_bottom_sheet);

            dialogChooseAlbum.setCanceledOnTouchOutside(true);

            dialogChooseAlbum.setCancelable(true);

            dialogChooseAlbum.getWindow().setLayout(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);

            dialogChooseAlbum.getWindow().setBackgroundDrawable(new ColorDrawable( Color.TRANSPARENT));

//        dialogChooseAlbum.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

            dialogChooseAlbum.getWindow().setGravity( Gravity.BOTTOM);

            dialogChooseAlbum.show();
        }
        if (check==3){
            Dialog dialogChooseAlbum = new Dialog(requireContext());

            dialogChooseAlbum.requestWindowFeature( Window.FEATURE_NO_TITLE);

            dialogChooseAlbum.setContentView(R.layout.board_bottom_sheet);

            dialogChooseAlbum.setCanceledOnTouchOutside(true);

            dialogChooseAlbum.setCancelable(true);

            dialogChooseAlbum.getWindow().setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);

            dialogChooseAlbum.getWindow().setBackgroundDrawable(new ColorDrawable( Color.TRANSPARENT));

//        dialogChooseAlbum.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

            dialogChooseAlbum.getWindow().setGravity( Gravity.BOTTOM);

            dialogChooseAlbum.show();
        }

    }


}