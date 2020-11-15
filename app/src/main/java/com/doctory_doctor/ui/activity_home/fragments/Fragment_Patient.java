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
import com.doctory_doctor.databinding.FragmentPatientBinding;

public class Fragment_Patient extends Fragment {
    private FragmentPatientBinding binding;

    public static Fragment_Patient newInstance(){
        return new Fragment_Patient();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_patient,container,false);
        initView();
        return binding.getRoot();
    }

    private void initView() {

    }
}
