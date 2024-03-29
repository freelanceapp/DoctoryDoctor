package com.doctory_doctor.adapters;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.doctory_doctor.R;
import com.doctory_doctor.databinding.LoadMoreRowBinding;
import com.doctory_doctor.databinding.MyTimeBinding;
import com.doctory_doctor.models.MyTimeModel;
import com.doctory_doctor.ui.activity_mytime.MyTimeActivity;

import java.util.List;

public class MyTimeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int DATA = 1;
    private final int LOAD = 2;
    private List<MyTimeModel.Data> list;
    private Context context;
    private LayoutInflater inflater;
    private AppCompatActivity activity;

    public MyTimeAdapter(List<MyTimeModel.Data> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
        activity = (AppCompatActivity) context;


    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType==DATA){
            MyTimeBinding binding = DataBindingUtil.inflate(inflater, R.layout.my_time, parent, false);
            return new MyHolder(binding);
        }else {
            LoadMoreRowBinding binding = DataBindingUtil.inflate(inflater, R.layout.load_more_row, parent, false);
            return new LoadMoreHolder(binding);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof MyHolder){
            MyHolder myHolder = (MyHolder) holder;
myHolder.binding.setModel(list.get(position));
myHolder.binding.flremove.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        MyTimeActivity myTimeActivity=(MyTimeActivity)context;
        myTimeActivity.remove(position);
    }
});
//            myHolder.binding.btnDetails.setOnClickListener(v -> {
//
//            });
        }else if (holder instanceof LoadMoreHolder){
            LoadMoreHolder loadMoreHolder = (LoadMoreHolder) holder;
            loadMoreHolder.binding.prgBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(context,R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
            loadMoreHolder.binding.prgBar.setIndeterminate(true);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        private MyTimeBinding binding;

        public MyHolder(MyTimeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


        }

    }

    public static class LoadMoreHolder extends RecyclerView.ViewHolder {
        private LoadMoreRowBinding binding;

        public LoadMoreHolder(LoadMoreRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


        }

    }

    @Override
    public int getItemViewType(int position) {
        if (list.size()>0){
            if (list.get(position)==null){
                return LOAD;
            }else {
                return DATA;
            }
        }else {
            return DATA;

        }

    }
}
