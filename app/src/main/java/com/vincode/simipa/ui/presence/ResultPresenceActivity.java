package com.vincode.simipa.ui.presence;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.vincode.simipa.R;
import com.vincode.simipa.model.Value;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;
import com.vincode.simipa.util.SharedPrefManager;


import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultPresenceActivity extends AppCompatActivity {

    public static final String EXTRA_INTENT = "extra_intent";
    public static final String EXTRA_LONGI = "extra_longi";
    public static final String EXTRA_LATI = "extra_lati";
    public static final String EXTRA_ADDRESS = "extra_addres";
    public static final String EXTRA_ID = "extra_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_presence);

        String value = getIntent().getStringExtra(EXTRA_INTENT);
        double latitude = getIntent().getDoubleExtra(EXTRA_LATI,0);
        double longitude = getIntent().getDoubleExtra(EXTRA_LONGI, 0);
        String address = getIntent().getStringExtra(EXTRA_ADDRESS);
        String idPresence = getIntent().getStringExtra(EXTRA_ID);

        TextView tvValue = findViewById(R.id.tv_value);
        tvValue.setText(value);

        TextView tvLongi = findViewById(R.id.tv_longi);
        tvLongi.setText(String.valueOf(longitude));

        TextView tvLati = findViewById(R.id.tv_lati);
        tvLati.setText(String.valueOf(latitude));

        TextView tvLocation = findViewById(R.id.tv_addres_location);
        tvLocation.setText(address);

        updateStudent(longitude, latitude, address, idPresence);


    }

    private void updateStudent( final double longi, final double lati, final String location, String idPresence){

        //Api latihan
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        String latitude = String.valueOf(lati);
        String longitude = String.valueOf(longi);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("ID_presensi", idPresence);
            jsonObject.put("status", "H");
            jsonObject.put("latitude", latitude);
            jsonObject.put("longitude", longitude);
            jsonObject.put("alamat", location);
            jsonObject.put("npm", SharedPrefManager.getInstance(this).getUser().getUserLogin());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
//        Call<Value> call = apiInterface.updatePresence(idPresence, "H" , latitude, longitude, location,
//                SharedPrefManager.getInstance(this).getUser().getUserLogin()
//        );
        Call<Value> call = apiInterface.updatePresence(jsonObject.toString());

        call.enqueue(new Callback<Value>() {
            
            @Override
            public void onResponse(@NonNull Call<Value> call, @NonNull Response<Value> response) {
                //String value = response.body().getValue();
                Log.d("njir 1", "error" );
                if (response.body() != null) {
                    String message = response.body().getMessage();
                    Toast.makeText(ResultPresenceActivity.this, message, Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
//                if (value.equals("1")){
//                    Toast.makeText(ResultPresenceActivity.this, message, Toast.LENGTH_SHORT).show();
//                    //getDataTointent(result, longi, lati, location);
//                }else {
//                    Toast.makeText(ResultPresenceActivity.this, message, Toast.LENGTH_SHORT).show();
//                }

            }

            @Override
            public void onFailure(@NonNull Call<Value> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ResultPresenceActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("njir 2", "error" );
            }
        });

    }
//
//    private String getCurrentDate(){
//        Calendar calendar = Calendar.getInstance();
//        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        return dateFormat.format(calendar.getTime());
//    }
//
//    private String getCurrentTime(){
//        Calendar calendar = Calendar.getInstance();
//        @SuppressLint("SimpleDateFormat") SimpleDateFormat timeFormat = new SimpleDateFormat("HH-mm-ss");
//        return timeFormat.format(calendar.getTime());
//    }
}
