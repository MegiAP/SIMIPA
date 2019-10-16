package com.vincode.simipa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vincode.simipa.R;

import com.vincode.simipa.model.PresenceRecap;


import java.util.ArrayList;

public class PresenceRecapAdapter extends RecyclerView.Adapter<PresenceRecapAdapter.CardViewViewHolder> {
    private ArrayList<PresenceRecap> listPresence;
    private Context context;

    public PresenceRecapAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<PresenceRecap> getlistPresence() {
        return listPresence;
    }

    public void setListPresence(ArrayList<PresenceRecap> listPresence) {
        this.listPresence = listPresence;
    }

    @NonNull
    @Override
    public PresenceRecapAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_presence_recap, parent, false);
        PresenceRecapAdapter.CardViewViewHolder viewHolder = new PresenceRecapAdapter.CardViewViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PresenceRecapAdapter.CardViewViewHolder holder, int position) {
        PresenceRecap p = getlistPresence().get(position);

        holder.tvName.setText(p.getName());
        holder.tvRkode.setText(p.getRkode());
        holder.tvRdosen.setText(p.getRdosen());
        holder.btnCheckPresence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Lagi tes button", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return getlistPresence().size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvRkode, tvRdosen;
        private Button btnCheckPresence;
        public CardViewViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.recapPresensi);
            tvRkode = itemView.findViewById(R.id.recapMatkode);
            tvRdosen = itemView.findViewById(R.id.recapDosen);
            btnCheckPresence = itemView.findViewById(R.id.btn_checkPresence);
        }
    }
}
