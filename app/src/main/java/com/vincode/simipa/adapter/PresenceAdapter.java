package com.vincode.simipa.adapter;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.vincode.simipa.R;
import com.vincode.simipa.model.PresenceResult;
import com.vincode.simipa.ui.presence.ScanPresenceActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PresenceAdapter extends RecyclerView.Adapter<PresenceAdapter.PresenceViewHolder> {
    private List<PresenceResult> listPresence = new ArrayList<>();
    private final Activity activity;
    private static final int REQUEST_LOCATION = 1;
    private LocationManager locationManager;


    public PresenceAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setListPresence(List<PresenceResult> presenceList){

        if (presenceList == null)return;
        this.listPresence.clear();
        this.listPresence.addAll(presenceList);

    }

    @NonNull
    @Override
    public PresenceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_presence, parent, false);
        return new PresenceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PresenceViewHolder holder, int position) {
        PresenceResult presence = listPresence.get(position);

        holder.tvTimeOne.setText(presence.getMulai());
        holder.tvTimeTwo.setText(presence.getSelesai());
        holder.tvTitle.setText(presence.getMataKuliah());
        holder.tvCode.setText(presence.getKodeMK());
        holder.tvLecture.setText(presence.getDosenPJ());
        holder.tvRoom.setText(presence.getRuang());
        holder.btnAbsent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);

                assert locationManager != null;
                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                    // untuk maksa ngehidupin gps
                    onGPS();
                }else {
                    //gps udah hidup get location
                    getLocation();
                }

                //Toast.makeText(activity, "tes", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Criteria criteria = new Criteria();
            Location locationGps = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location locationNetwork = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location locationPassive = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
            String bestProvider = locationManager.getBestProvider(criteria, false);
            assert bestProvider != null;
            Location locationCriteria = locationManager.getLastKnownLocation(bestProvider);

            if (locationGps != null) {
                double lat = locationGps.getLatitude();
                double longi = locationGps.getLongitude();

                Toast.makeText(activity,  getAddress(lat, longi), Toast.LENGTH_LONG).show();
                alertPosition(lat, longi);

            } else if (locationNetwork != null) {
                double lat = locationNetwork.getLatitude();
                double longi = locationNetwork.getLongitude();

                //alertPosition(lat, longi);
                Toast.makeText(activity,  getAddress(lat, longi) , Toast.LENGTH_LONG).show();
                alertPosition(lat, longi);

            } else if (locationPassive != null) {
                double lat = locationPassive.getLatitude();
                double longi = locationPassive.getLongitude();

                Toast.makeText(activity,  getAddress(lat, longi), Toast.LENGTH_LONG).show();
                alertPosition(lat, longi);
            } else if (locationCriteria != null) {
                double lat = locationCriteria.getLatitude();
                double longi = locationCriteria.getLongitude();

                alertPosition(lat, longi);
                Toast.makeText(activity,  getAddress(lat, longi), Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(activity, "Cek kembali gps lokasi anda", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void alertPosition(final double lat, final double longi){
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setMessage("Posisi anda saat ini berada di " + getAddress(lat, longi)).setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //activity.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                Intent scanIntent = new Intent(activity, ScanPresenceActivity.class);
                scanIntent.putExtra("a", getAddress(lat, longi));
                scanIntent.putExtra("b", lat);
                scanIntent.putExtra("c", longi);
                activity.startActivity(scanIntent);
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void onGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                activity.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private String getAddress(double latitude, double longitude){
        Geocoder geocoder = new Geocoder(activity, Locale.getDefault());

        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            Address obj = addresses.get(0);

            String add = obj.getAddressLine(0);
            add = add + ","+ obj.getAdminArea();
            add = add + ","+ obj.getCountryName();

            return add;

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    @Override
    public int getItemCount() {
        return listPresence.size();
    }

    class PresenceViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTimeOne, tvTimeTwo, tvTitle, tvCode;
        private TextView tvLecture, tvRoom;
        private Button btnAbsent;

        PresenceViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTimeOne = itemView.findViewById(R.id.tv_time1_absent);
            tvTimeTwo = itemView.findViewById(R.id.tv_time_absent);
            tvTitle = itemView.findViewById(R.id.tv_name_absent);
            tvCode = itemView.findViewById(R.id.tv_code_absent);
            tvLecture = itemView.findViewById(R.id.tv_lecture_absent);
            tvRoom = itemView.findViewById(R.id.tv_place_absent);
            btnAbsent = itemView.findViewById(R.id.btn_absent);
            locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        }
    }
}
