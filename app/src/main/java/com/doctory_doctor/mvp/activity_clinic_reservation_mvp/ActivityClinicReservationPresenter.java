package com.doctory_doctor.mvp.activity_clinic_reservation_mvp;

import android.app.FragmentManager;
import android.content.Context;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.doctory_doctor.R;
import com.doctory_doctor.models.AddFastResevModel;
import com.doctory_doctor.models.LoginModel;
import com.doctory_doctor.models.ReservisionTimeModel;
import com.doctory_doctor.models.SingleReservisionTimeModel;
import com.doctory_doctor.models.UserModel;
import com.doctory_doctor.remote.Api;
import com.doctory_doctor.tags.Tags;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityClinicReservationPresenter implements DatePickerDialog.OnDateSetListener{
    private Context context;
    private ActivityClinicReservationView view;
    private DatePickerDialog datePickerDialog;

    public ActivityClinicReservationPresenter(Context context, ActivityClinicReservationView view) {
        this.context = context;
        this.view = view;
        createDateDialog();
    }
    private void createDateDialog()
    {
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR));
        calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH));
        datePickerDialog = DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.setOkText(context.getString(R.string.select));
        datePickerDialog.setCancelText(context.getString(R.string.cancel));
        datePickerDialog.setAccentColor(ContextCompat.getColor(context, R.color.colorPrimary));
        datePickerDialog.setOkColor(ContextCompat.getColor(context, R.color.colorPrimary));
        datePickerDialog.setCancelColor(ContextCompat.getColor(context, R.color.gray4));
        datePickerDialog.setLocale(Locale.ENGLISH);
        datePickerDialog.setVersion(DatePickerDialog.Version.VERSION_1);
        datePickerDialog.setMinDate(calendar);

    }
    public void showDateDialog(FragmentManager fragmentManager){
        try {
            datePickerDialog.show(fragmentManager,"");

        }catch (Exception e){}
    }
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        calendar.set(Calendar.MONTH, monthOfYear);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        String date = dateFormat.format(new Date(calendar.getTimeInMillis()));
        SimpleDateFormat sdf = new SimpleDateFormat("EEE",Locale.ENGLISH);
        String stringDate = sdf.format(new Date(calendar.getTimeInMillis()));
        ActivityClinicReservationPresenter.this.view.onDateSelected(date,stringDate.toUpperCase());
    }
    public void getreservisiontime(UserModel singleDoctorModel, String type, String date, String dayname)
    {
        // Log.e("tjtjtj",userModel.getIs_confirmed());
        view.onLoad();

        Api.getService(Tags.base_url)
                .getreservision(singleDoctorModel.getData().getId()+"",date,dayname,type)
                .enqueue(new Callback<ReservisionTimeModel>() {
                    @Override
                    public void onResponse(Call<ReservisionTimeModel> call, Response<ReservisionTimeModel> response) {
                        view.onFinishload();

                        if (response.isSuccessful() && response.body() != null) {
                            view.onreservtimesucess(response.body());
                        } else {
                            view.onFinishload();
                            view.onFailed(context.getString(R.string.something));

                            try {
                                Log.e("error_codess",response.code()+ response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }


                    }

                    @Override
                    public void onFailure(Call<ReservisionTimeModel> call, Throwable t) {
                        try {
                            view.onFinishload();
                            view.onFailed(context.getString(R.string.something));
                            Log.e("Error", t.getMessage());
                        } catch (Exception e) {

                        }
                    }
                });
    }
    public void checkData(UserModel userModel, SingleReservisionTimeModel.Detials detials, String date, String dayname, String type, AddFastResevModel addFastResevModel) {
        if (addFastResevModel.isDataValid(context)) {
            addresrevision(userModel,detials,date,dayname,type,addFastResevModel);
        }
    }

    public void addresrevision(UserModel userModel, SingleReservisionTimeModel.Detials detials, String date, String dayname, String type, AddFastResevModel addFastResevModel) {

        //Log.e("llll",detials.getFrom_hour_type());
        if(userModel!=null) {
            view.onLoad();
            Api.getService(Tags.base_url)
                    .addfastreservision( userModel.getData().getId() + "", date, detials.getFrom(), userModel.getData().getDetection_price() + "", type, dayname.toUpperCase(),detials.getFrom_hour_type(),addFastResevModel.getName(),addFastResevModel.getPhone_code()+addFastResevModel.getPhone())
                    .enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            view.onFinishload();
                            if (response.isSuccessful() && response.body() != null) {
                                //  Log.e("eeeeee", response.body().getUser().getName());
                                //view.onSignupValid(response.body());
                                view.onsucsess();
                            } else {
                                try {
                                    Log.e("mmmmmmmmmmssss", response.errorBody().string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }


                                if (response.code() == 500) {
                                    view.onServer();
                                } else {
                                    try {
                                        view.onFailed(response.errorBody().string());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    //  Toast.makeText(VerificationCodeActivity.this, getString(R.string.failed), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            try {
                                view.onFinishload();
                                if (t.getMessage() != null) {
                                    Log.e("msg_category_error", t.getMessage() + "__");

                                    if (t.getMessage().toLowerCase().contains("failed to connect") || t.getMessage().toLowerCase().contains("unable to resolve host")) {
                                        view.onnotconnect(t.getMessage().toLowerCase());
                                        //  Toast.makeText(VerificationCodeActivity.this, getString(R.string.something), Toast.LENGTH_SHORT).show();
                                    } else {
                                        view.onFailed();
                                        // Toast.makeText(VerificationCodeActivity.this, getString(R.string.failed), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } catch (Exception e) {
                                Log.e("Error", e.getMessage() + "__");
                            }
                        }
                    });

        }
        else {
            view.onnotlogin();
        }
    }

}
