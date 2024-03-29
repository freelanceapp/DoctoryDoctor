package com.doctory_doctor.ui.activity_medical_advice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;

import com.doctory_doctor.R;
import com.doctory_doctor.databinding.ActivityMedicalAdviceBinding;
import com.doctory_doctor.language.Language;

import io.paperdb.Paper;

public class MedicalAdviceActivity extends AppCompatActivity {
    private ActivityMedicalAdviceBinding binding;
    private String lang;
    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(Language.updateResources(newBase,Paper.book().read("lang","ar")));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_medical_advice);
        initView();

    }

    private void initView() {
        Paper.init(this);
        lang = Paper.book().read("lang","ar");
        binding.setLang(lang);
        binding.llBack.setOnClickListener(view -> finish());
    }
}