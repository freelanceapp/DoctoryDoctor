package com.doctory_doctor.ui.activity_login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Html;
import android.widget.Toast;

import com.doctory_doctor.R;
import com.doctory_doctor.databinding.ActivityLoginBinding;
import com.doctory_doctor.language.Language;
import com.doctory_doctor.models.LoginModel;
import com.doctory_doctor.models.UserModel;
import com.doctory_doctor.mvp.activity_login_presenter.ActivityLoginPresenter;
import com.doctory_doctor.mvp.activity_login_presenter.ActivityLoginView;
import com.doctory_doctor.ui.activity_confirm_code.ConfirmCodeActivity;
import com.doctory_doctor.ui.activity_home.HomeActivity;
import com.doctory_doctor.ui.activity_phone.PhoneActivity;
import com.doctory_doctor.ui.activity_sign_up.SignUpActivity;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity implements ActivityLoginView {
    private ActivityLoginBinding binding;
    private LoginModel model;
    private ActivityLoginPresenter presenter;

    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(Language.updateResources(newBase, Paper.book().read("lang", "ar")));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        initView();
    }


    private void initView() {
        model = new LoginModel();
        binding.tv1.setText(Html.fromHtml(getString(R.string.login2)));
        binding.tvSignUp.setText(Html.fromHtml(getString(R.string.donot_have_account)));
        binding.setModel(model);
        presenter = new ActivityLoginPresenter(this, this);
        binding.btnLogin.setOnClickListener(view -> {
            presenter.checkData(model);
        });

        binding.tvSignUp.setOnClickListener(view -> {
            Intent intent = new Intent(this, PhoneActivity.class);
            startActivity(intent);
            finish();
        });
    }


    @Override
    public void onSuccess(UserModel userModel) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}