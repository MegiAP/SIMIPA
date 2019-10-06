package com.vincode.simipa.krs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vincode.simipa.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CardViewKRSAdapter extends RecyclerView.Adapter<CardViewKRSAdapter.CardViewViewHolder>{
    private ArrayList<KRS> listPresident;
    private Context context;

    public CardViewKRSAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<KRS> getListPresident() {
        return listPresident;
    }

    public void setListPresident(ArrayList<KRS> listPresident) {
        this.listPresident = listPresident;
    }

    @NonNull
    @Override
    public CardViewKRSAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_krs, parent, false);
        CardViewViewHolder viewHolder = new CardViewViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewKRSAdapter.CardViewViewHolder holder, int position) {
        KRS p = getListPresident().get(position);

        holder.tvName.setText(p.getName());
        holder.tvRemarks.setText(p.getRemarks());
        holder.imgPhoto.setText(p.getPhoto());
    }

    @Override
    public int getItemCount() {
        return getListPresident().size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvRemarks, imgPhoto;
        public CardViewViewHolder(View itemView) {
            super(itemView);
            imgPhoto = (TextView)itemView.findViewById(R.id.img_item_photo);
            tvName = (TextView)itemView.findViewById(R.id.tv_item_name);
            tvRemarks = (TextView)itemView.findViewById(R.id.tv_item_remarks);
        }
    }
}
