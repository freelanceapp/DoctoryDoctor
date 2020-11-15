package com.doctory_doctor.ui.activity_consulting_reservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.doctory_doctor.R;
import com.doctory_doctor.databinding.ActivityClinicReservationBinding;
import com.doctory_doctor.databinding.ActivityConsultingReservationBinding;
import com.doctory_doctor.language.Language;
import com.doctory_doctor.models.DoctorModel;

import io.paperdb.Paper;

public class ConsultingReservationActivity extends AppCompatActivity {
    private String lang;
    private ActivityConsultingReservationBinding binding;
    private DoctorModel doctorModel;
    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(Language.updateResources(newBase,Paper.book().read("lang","ar")));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_consulting_reservation);
        getDataFromIntent();
        initView();

    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        doctorModel = (DoctorModel) intent.getSerializableExtra("data");

    }

    private void initView() {
        Paper.init(this);
        lang = Paper.book().read("lang","ar");
        binding.setLang(lang);


        binding.imageBack.setOnClickListener(view -> {
            finish();
        });

    }

}