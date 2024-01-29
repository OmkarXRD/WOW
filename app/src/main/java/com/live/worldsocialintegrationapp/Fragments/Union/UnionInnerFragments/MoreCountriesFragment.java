package com.live.worldsocialintegrationapp.Fragments.Union.UnionInnerFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.live.worldsocialintegrationapp.Adapters.MoreCountriesAdapter;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.Root.CountryRoot;
import com.live.worldsocialintegrationapp.Root.CountryRootDetails;

import com.live.worldsocialintegrationapp.databinding.FragmentMoreCountriesBinding;
import com.live.worldsocialintegrationapp.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

public class MoreCountriesFragment extends Fragment implements MoreCountriesAdapter.Callback {

    FragmentMoreCountriesBinding binding;
    MoreCountriesAdapter moreCountriesAdapter;
    private List<CountryRootDetails> list;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMoreCountriesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CommonUtils.disableBottomNavigation(requireActivity());
        hitApi();
        clicks(view);
        searchData();

    }

    private void clicks(View view) {
        view.findViewById(R.id.countrybackImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

    }

    private void hitApi() {

        new Mvvm().getLiveDataCountryDetails(requireActivity()).observe(requireActivity(), new Observer<CountryRoot>() {
            @Override
            public void onChanged(CountryRoot response) {

                if(response != null){
                    if (response.getSuccess().equals("1")) {
                        list = response.getDetails();
                        moreCountriesAdapter = new MoreCountriesAdapter(list, MoreCountriesFragment.this);
                        binding.rvCountryList.setAdapter(moreCountriesAdapter);
                    } else {
                        if(getContext() != null){
                            Toast.makeText(requireContext(), "" + response.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }else{
                    Toast.makeText(requireContext(), "Technical iussue", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }


    private void searchData() {

        binding.search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                binding.search.requestFocus();
                binding.search.setSelection(s.toString().length());
                filter(s.toString());

            }
        });
    }

    private void filter(String text) {

        ArrayList<CountryRootDetails> filteredList = new ArrayList<>();
        for (CountryRootDetails item : list) {

            if (item.getName().toLowerCase().contains(text.toLowerCase()) || item.getName().toUpperCase().contains(text.toLowerCase())
                    || item.getName().toLowerCase().contains(text.toLowerCase()) || item.getName().toUpperCase().contains(text.toLowerCase())
            ) {
                filteredList.add(item);
            }
        }
        moreCountriesAdapter.filterList(filteredList);
        moreCountriesAdapter.notifyDataSetChanged();
    }


    @Override
    public void callback(String countryName) {
        if (countryName != null) {
            Bundle bundle = new Bundle();
            bundle.putString("CountryName", countryName);
            Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.countriesFragment, bundle);
        } else {
            Toast.makeText(requireContext(), "country name is null", Toast.LENGTH_SHORT).show();
        }
    }
}