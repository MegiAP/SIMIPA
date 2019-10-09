package com.vincode.simipa.beasiswa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vincode.simipa.R;
import com.vincode.simipa.beasiswa.Beasiswa;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CardViewBeasiswaAdapter extends RecyclerView.Adapter<CardViewBeasiswaAdapter.CardViewViewHolder>{
    private ArrayList<Beasiswa> listBeasiswa;
    private Context context;

    public CardViewBeasiswaAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Beasiswa> getListBeasiswa() {
        return listBeasiswa;
    }

    public void setListPresident(ArrayList<Beasiswa> listBeasiswa) {
        this.listBeasiswa = listBeasiswa;
    }

    @NonNull
    @Override
    public CardViewBeasiswaAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_beasiswa, parent, false);
        CardViewBeasiswaAdapter.CardViewViewHolder viewHolder = new CardViewBeasiswaAdapter.CardViewViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewBeasiswaAdapter.CardViewViewHolder holder, int position) {
        Beasiswa p = getListBeasiswa().get(position);

        holder.tvName.setText(p.getName());
        holder.tvRemarks.setText(p.getRemarks());
        holder.tvStatus.setText(p.getStatus());
    }

    @Override
    public int getItemCount() {
        return getListBeasiswa().size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvRemarks, tvStatus;
        public CardViewViewHolder(View itemView) {
            super(itemView);
            tvStatus = (TextView)itemView.findViewById(R.id.tv_status);
            tvName = (TextView)itemView.findViewById(R.id.tv_scholarship);
            tvRemarks = (TextView)itemView.findViewById(R.id.tv_item_remarks);
        }
    }
}
