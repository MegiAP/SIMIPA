package com.vincode.simipa.adapter;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vincode.simipa.R;
import com.vincode.simipa.model.LectureResponse;
import com.vincode.simipa.model.LectureResult;
import com.vincode.simipa.model.SeminarResult;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;
import com.vincode.simipa.ui.recapitulation.DetailSeminarRecapFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.vincode.simipa.ui.recapitulation.DetailSeminarRecapFragment.EXTRA_DOSEN;
import static com.vincode.simipa.ui.recapitulation.DetailSeminarRecapFragment.EXTRA_JENIS;
import static com.vincode.simipa.ui.recapitulation.DetailSeminarRecapFragment.EXTRA_JUDUL;
import static com.vincode.simipa.ui.recapitulation.DetailSeminarRecapFragment.EXTRA_NAME;
import static com.vincode.simipa.ui.recapitulation.DetailSeminarRecapFragment.EXTRA_NPM;
import static com.vincode.simipa.ui.recapitulation.DetailSeminarRecapFragment.EXTRA_RUANG;
import static com.vincode.simipa.ui.recapitulation.DetailSeminarRecapFragment.EXTRA_TANGGAL;

public class SeminarRecapHasilAdapter extends RecyclerView.Adapter<SeminarRecapHasilAdapter.CardViewHolder> implements Filterable {

    private List<SeminarResult> listSeminar = new ArrayList<>();
    private List<SeminarResult> listSeminarFull;

    public void SeminarRecapHasilAdapter(List<SeminarResult> listSeminar) {
        if (listSeminar == null) return;
        this.listSeminar.clear();
        this.listSeminar = listSeminar;
        listSeminarFull = new ArrayList<>(listSeminar);
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_seminar_recap, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewHolder holder, final int position) {
        final SeminarResult p = listSeminar.get(position);

        holder.tvName.setText(p.getNama());
        holder.tvNpm.setText(p.getNpm());
        holder.tvSjudul.setText(p.getJudul());
        holder.tvSTanggal.setText(p.getTanggal());
        holder.tvSjenis.setText(p.getJenis());
        String jam = p.getJam();
        final String jam4 = jam.substring(0,5);

        holder.cvRekap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailSeminarRecapFragment mDetailSeminarRecapFragment = new DetailSeminarRecapFragment();
                Bundle bundle = new Bundle();
                bundle.putString(EXTRA_NAME, p.getNama());
                bundle.putString(EXTRA_NPM, p.getNpm());
                bundle.putString(EXTRA_JENIS, p.getJenis());
                bundle.putString(EXTRA_DOSEN, holder.tvSdosen.getText().toString());
                bundle.putString(EXTRA_JUDUL, p.getJudul());
                bundle.putString(EXTRA_RUANG, p.getRuang());
                final String OLD_FORMAT = "yyyy-MM-dd";
                final String NEW_FORMAT = "dd-MMM-yyyy";
                String oldDateString = p.getTanggal();
                String newDateString;
                SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
                Date d = null;
                try {
                    d = sdf.parse(oldDateString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                sdf.applyPattern(NEW_FORMAT);
                newDateString = sdf.format(d);
                bundle.putString(EXTRA_TANGGAL, newDateString + " / " + jam4);
                mDetailSeminarRecapFragment.setArguments(bundle);
                FragmentManager fragmentManager = ((AppCompatActivity)view.getContext()).getSupportFragmentManager();
                mDetailSeminarRecapFragment.show(fragmentManager, DetailSeminarRecapFragment.class.getSimpleName());
//                Toast.makeText(view.getContext(), "Seminar dengan judul \"" + p.getJudul() + "\"", Toast.LENGTH_LONG).show();
            }
        });

        if (p.getJenis().equals("Seminar Hasil")) {
            holder.coJenis.setBackgroundResource(R.drawable.bg_red_gradient);
        }

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<LectureResponse> call = apiInterface.getLecture(p.getPem1());
        call.enqueue(new Callback<LectureResponse>() {
            @Override
            public void onResponse(@NonNull Call<LectureResponse> call, Response<LectureResponse> response) {
                assert response.body() != null;
                List<LectureResult> lectureResults = response.body().getRecords();
                holder.tvSdosen.setText(lectureResults.get(0).getLectureName());
            }

            @Override
            public void onFailure(@NonNull Call<LectureResponse> call, Throwable t) {
                Log.d(holder.tvSdosen.getText().toString(), " ");
            }
        });
    }

    @Override
    public int getItemCount() {
        return listSeminar.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvNpm, tvSjudul, tvSdosen, tvSjenis, tvSTanggal;
        ImageView coJenis;
        CardView cvRekap;
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_recMhsSem);
            tvNpm = itemView.findViewById(R.id.tv_recNpm);
            tvSjudul = itemView.findViewById(R.id.tv_rec_judulSem);
            tvSdosen = itemView.findViewById(R.id.tv_rec_dosenSem);
            tvSTanggal = itemView.findViewById(R.id.tv_recTanggalSem);
            tvSjenis = itemView.findViewById(R.id.tv_recJenisSem);
            coJenis = itemView.findViewById(R.id.co_jenis_seminar);
            cvRekap = itemView.findViewById(R.id.card_view);
        }
    }

    //filter data hasil
    @Override
    public Filter getFilter() {
        return dataFilterHasil;
    }

    private Filter dataFilterHasil = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<SeminarResult> seminarResult = new ArrayList<>();

            for (SeminarResult item : listSeminarFull) {
                if (item.getJenis().toLowerCase().contains("seminar hasil")) {
                    seminarResult.add(item);
                }
            }

            FilterResults results = new FilterResults();
            results.values = seminarResult;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            listSeminar.clear();
            listSeminar.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };
}
