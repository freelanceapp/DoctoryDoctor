package com.doctory_doctor.ui.activity_clinic_reservation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.doctory_doctor.R;
import com.doctory_doctor.adapters.ChildReservisionHourAdapter;
import com.doctory_doctor.adapters.ReservisionHourAdapter;
import com.doctory_doctor.databinding.ActivityClinicReservationBinding;
import com.doctory_doctor.language.Language;
import com.doctory_doctor.models.AddFastResevModel;
import com.doctory_doctor.models.ReservisionTimeModel;
import com.doctory_doctor.models.SingleReservisionTimeModel;
import com.doctory_doctor.models.UserModel;
import com.doctory_doctor.mvp.activity_clinic_reservation_mvp.ActivityClinicReservationPresenter;
import com.doctory_doctor.mvp.activity_clinic_reservation_mvp.ActivityClinicReservationView;
import com.doctory_doctor.preferences.Preferences;
import com.doctory_doctor.share.Common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;

public class ClinicReservationActivity extends AppCompatActivity implements ActivityClinicReservationView {
    private String lang;
    private ActivityClinicReservationBinding binding;


    private String type = "";
    private String date = "";
    private String time = "";
    private String dayname = "";
    private ActivityClinicReservationPresenter presenter;
    private List<SingleReservisionTimeModel> singleReservisionTimeModelList;
    private ReservisionHourAdapter reservisionHourAdapter;
    private List<SingleReservisionTimeModel.Detials> detialsList;
    private ChildReservisionHourAdapter childReservisionHourAdapter;
    private ProgressDialog dialog2;
    private Preferences preferences;
    private UserModel userModel;
    private AddFastResevModel addFastResevModel;
    private SingleReservisionTimeModel.Detials singletimemodel;

    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(Language.updateResources(newBase, Paper.book().read("lang", "ar")));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_clinic_reservation);
        getDataFromIntent();
        initView();

    }

    private void getDataFromIntent() {
        Intent intent = getIntent();

    }

    private void initView() {
        singleReservisionTimeModelList = new ArrayList<>();
        detialsList = new ArrayList<>();
        preferences = Preferences.getInstance();
        userModel = preferences.getUserData(this);
        addFastResevModel = new AddFastResevModel();
        binding.setModel(addFastResevModel);
        Paper.init(this);
        lang = Paper.book().read("lang", "ar");
        binding.setLang(lang);
        presenter = new ActivityClinicReservationPresenter(this, this);
        reservisionHourAdapter = new ReservisionHourAdapter(singleReservisionTimeModelList, this);
        childReservisionHourAdapter = new ChildReservisionHourAdapter(detialsList, this);
        binding.recViewhour.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        binding.recViewhour.setAdapter(reservisionHourAdapter);
        binding.recViewchildhour.setLayoutManager(new GridLayoutManager(this, 3));
        binding.recViewchildhour.setAdapter(childReservisionHourAdapter);
        type = "normal";


        binding.imageBack.setOnClickListener(view -> {
            finish();
        });
        binding.btreserv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.checkData(userModel, singletimemodel, date, dayname, "normal", addFastResevModel);
            }
        });
        binding.llDate.setOnClickListener(view -> presenter.showDateDialog(getFragmentManager()));
        gettimes();
    }

    private void gettimes() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        String date = dateFormat.format(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("EEE", Locale.ENGLISH);
        String stringDate = sdf.format(System.currentTimeMillis());
        this.date = date;
        this.dayname = stringDate;

        binding.tvDate.setText(date);
        presenter.getreservisiontime(userModel, type, date, dayname.toUpperCase());
    }

    @Override
    public void onDateSelected(String date, String dayname) {
        this.date = date;
        binding.tvDate.setText(date);
        this.dayname = dayname;

        Log.e("llll", dayname);
        presenter.getreservisiontime(userModel, type, date, dayname.toUpperCase());

    }

    @Override
    public void onLoad() {
        dialog2 = Common.createProgressDialog(this, getString(R.string.wait));
        dialog2.setCancelable(false);
        dialog2.show();
    }

    @Override
    public void onFinishload() {
        dialog2.dismiss();
    }


    @Override
    public void onreservtimesucess(ReservisionTimeModel body) {
        singleReservisionTimeModelList.clear();
        reservisionHourAdapter.notifyDataSetChanged();
        singleReservisionTimeModelList.addAll(body.getData());
        reservisionHourAdapter.notifyDataSetChanged();
        if (singleReservisionTimeModelList.size() == 0) {
            binding.tvNoData.setVisibility(View.VISIBLE);
        } else {
            binding.tvNoData.setVisibility(View.GONE);
        }
        detialsList.clear();
        childReservisionHourAdapter.notifyDataSetChanged();
    }

    public void getchild(int position) {
        childReservisionHourAdapter.i = -1;
        detialsList.clear();
        detialsList.addAll(singleReservisionTimeModelList.get(position).getDetials());
        childReservisionHourAdapter.notifyDataSetChanged();
    }

    public void Setitem(SingleReservisionTimeModel.Detials detials) {
        singletimemodel = detials;


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Intent intent = getIntent();
            setResult(RESULT_OK, intent);

            finish();
        }
    }

    @Override
    public void onFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onsucsess() {
        Intent intent = getIntent();
        setResult(RESULT_OK, intent);

        finish();
    }

    @Override
    public void onServer() {
        Toast.makeText(ClinicReservationActivity.this, getString(R.string.server_error), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onnotlogin() {


        Common.CreateDialogAlert(this, getResources().getString(R.string.please_sign_in_or_sign_up));
    }

    @Override
    public void onFailed() {
        Toast.makeText(ClinicReservationActivity.this, getString(R.string.failed), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onnotconnect(String msg) {
        Toast.makeText(ClinicReservationActivity.this, msg, Toast.LENGTH_SHORT).show();

    }
}