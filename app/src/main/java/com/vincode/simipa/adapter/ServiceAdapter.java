package com.vincode.simipa.adapter;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vincode.simipa.R;
import com.vincode.simipa.model.ServiceResult;
import com.vincode.simipa.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {

    private List<ServiceResult> listService = new ArrayList<>();
    private final Activity activity;

    public ServiceAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setListService(List<ServiceResult> serviceResults){

        if (serviceResults == null)return;
        this.listService.clear();
        this.listService.addAll(serviceResults);

    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_service, viewGroup, false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder serviceViewHolder, int i) {
        ServiceResult dataService = listService.get(i);
        TimeUtil timeUtil = new TimeUtil();
        String date = dataService.getCreated().substring(0,10);
        serviceViewHolder.tvFormService.setText(dataService.getNamaLayanan());//calendarAcademic.getData()
        serviceViewHolder.tvDateService.setText(timeUtil.converDate(date));

        if (dataService.getStatus().equals("0")){
            serviceViewHolder.tvStateService.setText(R.string.not_finish);
            serviceViewHolder.tvStateService.setBackgroundResource(R.drawable.bg_state_red);
        }else {
            serviceViewHolder.tvStateService.setText(R.string.finish);
            serviceViewHolder.tvStateService.setBackgroundResource(R.drawable.bg_btn_blue);
        }



    }

    @Override
    public int getItemCount() {
        return listService.size();
    }

    static class ServiceViewHolder extends RecyclerView.ViewHolder {
        TextView tvFormService, tvDateService, tvStateService;

        ServiceViewHolder(@NonNull View itemView) {
            super(itemView);

            tvFormService = itemView.findViewById(R.id.tv_service_form);
            tvDateService = itemView.findViewById(R.id.tv_service_date);
            tvStateService = itemView.findViewById(R.id.tv_service_state);
        }
    }
}
