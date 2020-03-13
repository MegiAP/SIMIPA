package com.vincode.simipa.ui.achievement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.vincode.simipa.R;
import com.vincode.simipa.adapter.AchievementAdapter;
import com.vincode.simipa.adapter.AchievementPagerAdapter;
import com.vincode.simipa.adapter.KRSPagerAdapter;
import com.vincode.simipa.model.AchievementResponse;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;
import com.vincode.simipa.ui.krs.FormKRSFragment;
import com.vincode.simipa.ui.krs.MyKRSFragment;
import com.vincode.simipa.util.SharedPrefManager;

public class AchievementActivity extends AppCompatActivity {
    private TextView tvAcademic, tvNonAcademic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement);

        TextView tvName = findViewById(R.id.tv_achiev_name);
        TextView tvNpm = findViewById(R.id.tv_achiev_npm);
        tvName.setText(SharedPrefManager.getInstance(this).getUser().getDisplayName());
        tvNpm.setText(SharedPrefManager.getInstance(this).getUser().getUserLogin());
        tvAcademic = findViewById(R.id.academic);
        tvNonAcademic = findViewById(R.id.non_academic);
        tvAcademic.setEnabled(false);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(R.string.achievment);

        AcademicAchievementFragment academicAchievementFragment = new AcademicAchievementFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.scroll_layout, academicAchievementFragment).commit();
    }

    public void cacademic (View view) {
        AcademicAchievementFragment academicAchievementFragment = new AcademicAchievementFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.scroll_layout, academicAchievementFragment).commit();
        tvAcademic.setEnabled(false);
        tvNonAcademic.setEnabled(true);
    }

    public void cnonacademic (View view) {
        NonAcademicAchievementFragment nonAcademicAchievementFragment = new NonAcademicAchievementFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.scroll_layout, nonAcademicAchievementFragment).commit();
        tvAcademic.setEnabled(true);
        tvNonAcademic.setEnabled(false);
    }

}
