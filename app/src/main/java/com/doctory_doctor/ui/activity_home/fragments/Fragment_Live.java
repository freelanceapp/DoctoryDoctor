package com.doctory_doctor.ui.activity_home.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.doctory_doctor.R;
import com.doctory_doctor.databinding.FragmentLiveBinding;
import com.doctory_doctor.ui.activity_home.HomeActivity;

public class Fragment_Live extends Fragment {
    private FragmentLiveBinding binding;
    private double lat=0.0,lng=0.0;
    private HomeActivity activity;


    public static Fragment_Live newInstance(double lat, double lng){
        Bundle bundle = new Bundle();
        bundle.putDouble("lat",lat);
        bundle.putDouble("lng",lng);
        Fragment_Live fragment_live = new Fragment_Live();
        fragment_live.setArguments(bundle);
        return fragment_live;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_live,container,false);
        initView();
        return binding.getRoot();
    }

    private void initView() {
        activity = (HomeActivity) getActivity();
        Bundle bundle = getArguments();
        if (bundle != null) {
            lat = bundle.getDouble("lat");
            lng = bundle.getDouble("lng");
        }
    }
}
