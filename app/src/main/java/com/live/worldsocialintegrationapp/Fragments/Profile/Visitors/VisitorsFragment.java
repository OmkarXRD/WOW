package com.live.worldsocialintegrationapp.Fragments.Profile.Visitors;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.live.worldsocialintegrationapp.Adapters.VisitorsRVAdapter;
import com.live.worldsocialintegrationapp.ModelClasses.Visitors.GetVisitorsRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.databinding.FragmentVisitorsBinding;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.live.worldsocialintegrationapp.utils.CommonUtils;

import java.util.List;


public class VisitorsFragment extends Fragment {

    FragmentVisitorsBinding binding;
    List<GetVisitorsRoot.Deatil> list;
    VisitorsRVAdapter visitorsRVAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentVisitorsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CommonUtils.disableBottomNavigation(requireActivity());
        ClickListeners(view);
        vistorsListApi();
    }

    private void vistorsListApi() {

        Log.d("VISITORSFRAGMENT","USERID: "+AppConstants.USER_ID);
        new Mvvm().getVisitors(requireActivity(), AppConstants.USER_ID).observe(requireActivity(), new Observer<GetVisitorsRoot>() {
            @Override
            public void onChanged(GetVisitorsRoot getVisitorsRoot) {

                if(getVisitorsRoot.getStatus().equalsIgnoreCase("1")){
                    Toast.makeText(requireContext(), "1 "+getVisitorsRoot.getMessage(), Toast.LENGTH_SHORT).show();

                    list= getVisitorsRoot.getDeatils();

                    visitorsRVAdapter= new VisitorsRVAdapter(list, requireContext(), new VisitorsRVAdapter.Callback() {
                        @Override
                        public void callback(String otherUserId) {
                            if(otherUserId != null){
                                Bundle bundle  = new Bundle();
                                bundle.putString("otherUserId",otherUserId);
                                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.otherUser,bundle);
                            }else{
//                                Toast.makeText(requireContext(), "otherUserIsNull", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    binding.visitorsRV.setAdapter(visitorsRVAdapter);

                }else{
//                    Toast.makeText(requireContext(), "0 "+getVisitorsRoot.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void ClickListeners(View view) {

        binding.visitorsBackImag.setOnClickListener(view1 -> {

            getActivity().onBackPressed();
        });
    }
}