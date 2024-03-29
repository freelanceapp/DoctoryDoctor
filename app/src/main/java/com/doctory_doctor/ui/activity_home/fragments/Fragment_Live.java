package com.doctory_doctor.ui.activity_home.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.doctory_doctor.R;
import com.doctory_doctor.adapters.AppointmentAdapter;
import com.doctory_doctor.databinding.FragmentAppoinmentsBinding;
import com.doctory_doctor.databinding.FragmentLiveBinding;
import com.doctory_doctor.models.ApointmentModel;
import com.doctory_doctor.models.UserModel;
import com.doctory_doctor.mvp.fragment_home_mvp.HomeFragmentPresenter;
import com.doctory_doctor.mvp.fragment_live_mvp.LiveFragmentPresenter;
import com.doctory_doctor.mvp.fragment_live_mvp.LiveFragmentView;
import com.doctory_doctor.preferences.Preferences;
import com.doctory_doctor.ui.activity_home.HomeActivity;
import com.doctory_doctor.ui.activity_patient_details.PatientDetailsActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Fragment_Live extends Fragment implements LiveFragmentView {
    private FragmentLiveBinding binding;
    private AppointmentAdapter adapter;
    private HomeActivity activity;
    private LiveFragmentPresenter presenter;
    private List<ApointmentModel.Data> apointmentModelList;
    private Preferences preferences;


    private UserModel userModel;

    public static Fragment_Live newInstance() {
        return new Fragment_Live();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_live, container, false);
        initView();
        return binding.getRoot();
    }

    private void initView() {
        apointmentModelList = new ArrayList<>();
        activity = (HomeActivity) getActivity();
        preferences = Preferences.getInstance();
        userModel = preferences.getUserData(activity);
        adapter = new AppointmentAdapter(apointmentModelList, activity, this);
        binding.progBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(activity, R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        binding.recView.setLayoutManager(new LinearLayoutManager(activity));
        binding.recView.setAdapter(adapter);
        presenter = new LiveFragmentPresenter(this, activity);
        if (userModel != null) {
            presenter.getApointment(userModel);
        }
    }

    @Override
    public void onSuccess(ApointmentModel apointmentModel) {
        apointmentModelList.clear();
        apointmentModelList.addAll(apointmentModel.getData());
        adapter.notifyDataSetChanged();
        if(apointmentModelList.size()==0){
            binding.tvNoData.setVisibility(View.VISIBLE);
        }
        else {
            binding.tvNoData.setVisibility(View.GONE);
        }

    }

    @Override
    public void onFailed(String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {
        binding.progBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        binding.progBar.setVisibility(View.GONE);

    }

    public void setitem(ApointmentModel.Data.PatientFk patient_fk, int id, String reservation_type) {
        Intent intent = new Intent(activity, PatientDetailsActivity.class);
        intent.putExtra("DATA", patient_fk);
        intent.putExtra("id", id);
        intent.putExtra("type", reservation_type);
        startActivity(intent);
    }

//    public void open(ApointmentModel.Data data) {
//        String date = data.getDate()+" " + data.getTime()+" "+data.getTime_type();
//        Log.e("kdkdk", date);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss aa", Locale.US);
//        long datetime = 0;
//        try {
//            datetime = sdf.parse(date).getTime();
//        } catch (ParseException e) {
//            Log.e("dldkkd",e.toString());
//        }
//        long currenttime = System.currentTimeMillis();
//        Log.e("kdkdk", date+" "+currenttime+" "+datetime);
//
//        //  if (currenttime >= datetime) {
//
//        intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", data.getPatient_fk().getPhone_code() + data.getPatient_fk().getPhone(), null));
//        if (intent != null) {
//            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
//                } else {
//                    startActivity(intent);
//                }
//            } else {
//                startActivity(intent);
//            }
//
//        }
////        } else {
////            Toast.makeText(activity, activity.getResources().getString(R.string.not_avail_now), Toast.LENGTH_LONG).show();
////        }
//
//    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case REQUEST_PHONE_CALL: {
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                        if (activity.checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                            // TODO: Consider calling
//                            //    Activity#requestPermissions
//                            // here to request the missing permissions, and then overriding
//                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                            //                                          int[] grantResults)
//                            // to handle the case where the user grants the permission. See the documentation
//                            // for Activity#requestPermissions for more details.
//                            return;
//                        }
//                    }
//                    startActivity(intent);
//                } else {
//
//                }
//                return;
//            }
//        }
//    }
//    public void showdetails(ApointmentModel.Data data) {
//        Intent intent=new Intent(activity, ReservationActivity.class);
//        intent.putExtra("data",data);
//        startActivity(intent);
//    }
}