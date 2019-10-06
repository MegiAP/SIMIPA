package com.vincode.simipa.ui.intro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vincode.simipa.MainActivity;
import com.vincode.simipa.R;
import com.vincode.simipa.ui.login.LoginActivity;

public class IntroSliderActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout dotsLayout;
    private int[]layouts;
    private Button btnNext, btnSkip;
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferenceManager = new PreferenceManager(this);
        if (!preferenceManager.isFirstTimeLaunch()){
            launchHomeScreen();
            finish();
        }

        if (Build.VERSION.SDK_INT >= 21 ){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            setContentView(R.layout.activity_intro_slider);
            viewPager = findViewById(R.id.vp_intro);
            dotsLayout = findViewById(R.id.layout_dots);
            btnNext = findViewById(R.id.btn_next);
            btnSkip = findViewById(R.id.btn_skip);

            layouts = new int[]{
                    R.layout.slider1,
                    R.layout.slider2,
                    R.layout.slider3
            };

            addBottomDots(0);

            changeStatusBarColor();
            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter();
            viewPager.setAdapter(viewPagerAdapter);
            viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
            btnSkip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    launchHomeScreen();
                }
            });
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int current = getItem();
                    if (current < layouts.length){
                        viewPager.setCurrentItem(current);
                    }else {
                        launchHomeScreen();
                    }
                }
            });
        }
    }

    private void addBottomDots(int currentPage){
        TextView[] dots = new TextView[layouts.length];
        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInActive = getResources().getIntArray(R.array.array_dot_inactive);
        dotsLayout.removeAllViews();
        for (int i = 0; i< dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInActive[currentPage]);
            dotsLayout.addView(dots[i]);
        }
        if (dots.length >0 ){
            dots[currentPage].setTextColor(colorsActive[currentPage]);
        }
    }

    private int getItem(){
        return viewPager.getCurrentItem() + 1;
    }

    private void launchHomeScreen(){
        preferenceManager.setFirstTimeLaunch(false);
        startActivity( new Intent(this, LoginActivity.class));//login
        finish();
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageSelected(int position){
            addBottomDots(position);

            if (position == layouts.length -1 ){
                btnNext.setText(getResources().getString(R.string.start));
                btnSkip.setVisibility(View.GONE);
            }else {
                btnNext.setText(getResources().getString(R.string.next));
                btnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2){
        }

        @Override
        public void onPageScrollStateChanged(int arg0){
        }
    };

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private class ViewPagerAdapter extends PagerAdapter{
        LayoutInflater layoutInflater;

        ViewPagerAdapter(){

        }

        @NonNull
        @Override
        public Object instantiateItem (@NonNull ViewGroup viewGroup, int position){
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = null;
            if (layoutInflater != null) {
                view = layoutInflater.inflate(layouts[position], viewGroup, false);
            }
            viewGroup.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup viewGroup, int position, @NonNull Object object){
            View view = (View) object;
            viewGroup.removeView(view);
        }
    }
}
