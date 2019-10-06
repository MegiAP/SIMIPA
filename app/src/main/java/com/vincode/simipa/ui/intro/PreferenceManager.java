package com.vincode.simipa.ui.intro;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    int PRIVATE_MODE =0;

    private static final String PREF_NAME = "intro_slider";
    private static final String IS_FIRST_LAUNCH = "is_first_launch";

    public PreferenceManager(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime){
        editor.putBoolean(IS_FIRST_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch(){
        return sharedPreferences.getBoolean(IS_FIRST_LAUNCH,true);
    }
}
