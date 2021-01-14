package com.doctory_doctor.models;

import android.content.Context;
import android.util.Patterns;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;

import com.doctory_doctor.BR;
import com.doctory_doctor.R;

import java.io.Serializable;

public class AddFastResevModel extends BaseObservable implements Serializable {
    private String name;

    private String phone_code;
    private String phone;


    public ObservableField<String> error_name = new ObservableField<>();
    public ObservableField<String> error_phone = new ObservableField<>();



    public AddFastResevModel() {
        this.phone_code = "+964";
        this.phone = "";
        this.name = "";


    }

    public boolean isDataValid(Context context)
    {
        if (!name.isEmpty()&&
                !phone.isEmpty()
                ){
            error_name.set(null);
            error_phone.set(null);
            return true;
        }else {
            if (name.isEmpty()){
                error_name.set(context.getString(R.string.field_req));
            }else {
                error_name.set(null);
            }

            if (phone.isEmpty()){
                error_phone.set(context.getString(R.string.field_req));
            }else {
                error_phone.set(null);
            }

            return false;
        }
    }



   @Bindable
   public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public String getPhone_code() {
        return phone_code;
    }

    public void setPhone_code(String phone_code) {
        this.phone_code = phone_code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
