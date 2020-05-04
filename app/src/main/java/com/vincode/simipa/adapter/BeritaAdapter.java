package com.vincode.simipa.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.vincode.simipa.R;
import com.vincode.simipa.model.BeritaResult;
import com.vincode.simipa.ui.berita.DetailBeritaActvity;

import java.util.ArrayList;
import java.util.List;

public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder> {

    private List<BeritaResult> listBerita = new ArrayList<>();
    private final Activity activity;

    public BeritaAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setListBerita(List<BeritaResult> beritaResults){

        if (beritaResults == null)return;
        this.listBerita.clear();
        this.listBerita.addAll(beritaResults);

    }

    @NonNull
    @Override
    public BeritaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_berita, parent, false);
        return new BeritaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final BeritaViewHolder holder, int position) {

        final BeritaResult data = listBerita.get(position);
        holder.tvTitle.setText(data.getTitle());
        holder.tvTime.setText(data.getReleaseDate());
//        Glide.with(activity)
//                .load("https://image.tmdb.org/t/p/w500/"+data.getBackdropPath())
//                .transform(new CenterInside(), new RoundedCorners(10))
//                .into(holder.imgBerita);
        holder.cvBerita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, DetailBeritaActvity.class);
                intent.putExtra("berita", data);
                activity.startActivity(intent);
//                Toast.makeText(activity, "tes", Toast.LENGTH_SHORT).show();
            }
        });
//        Log.d("title", data.getTitle());
        Glide.with(activity)
                .load("https://fmipa.unila.ac.id/"+data.getPhoto())
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        holder.imgBerita.setBackground(resource);
                    }
                });

    }

    @Override
    public int getItemCount() {
        return listBerita.size();
    }

    static class BeritaViewHolder extends RecyclerView.ViewHolder {

        CardView cvBerita;
        ImageView imgBerita;
        TextView tvTitle, tvTime;

        BeritaViewHolder(@NonNull View itemView) {
            super(itemView);

            cvBerita = itemView.findViewById(R.id.cv_berita);
            tvTitle = itemView.findViewById(R.id.tv_title_berita);
            tvTime = itemView.findViewById(R.id.tv_time_berita);
            imgBerita = itemView.findViewById(R.id.img_berita);
        }
    }
}
