package com.vincode.simipa.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vincode.simipa.R;
import com.vincode.simipa.model.Service;

import java.util.ArrayList;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {

    private ArrayList<Service> listService;
    private final Context mContext;

    public ServiceAdapter(Context mContext, ArrayList<Service> listService) {
        this.listService = listService;
        this.mContext = mContext;
    }



    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_service, viewGroup, false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder serviceViewHolder, int i) {
        Service service = listService.get(i);

        serviceViewHolder.tvFormService.setText(service.getForm());//calendarAcademic.getData()
        serviceViewHolder.tvDateService.setText(service.getDate());

        if (service.getState() == 0){
            serviceViewHolder.tvStateService.setText("Belum Selesai");
            serviceViewHolder.tvStateService.setBackgroundResource(R.drawable.bg_state_red);
        }else {
            serviceViewHolder.tvStateService.setText("Selesai");
            serviceViewHolder.tvStateService.setBackgroundResource(R.drawable.bg_btn_blue);
        }
    }

    @Override
    public int getItemCount() {
        return listService.size();
    }

    public class ServiceViewHolder extends RecyclerView.ViewHolder {
        TextView tvFormService, tvDateService, tvStateService;

        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);

            tvFormService = itemView.findViewById(R.id.tv_service_form);
            tvDateService = itemView.findViewById(R.id.tv_service_date);
            tvStateService = itemView.findViewById(R.id.tv_service_state);
        }
    }
}
