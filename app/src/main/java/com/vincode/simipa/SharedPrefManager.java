package com.vincode.simipa;

import android.content.Context;
import android.content.SharedPreferences;

import com.vincode.simipa.model.UserData;

public class SharedPrefManager {

    private static SharedPrefManager INSTANCE;
    private static Context context;

    private static String PREF_NAME_LOGIN = "pref_name_login";

    private static final String KEY_ID = "key_id";
    private static final String KEY_NAME = "key_name";
    private static final String KEY_NPM = "key_npm";
    private static final String KEY_EMAIL = "key_email";

    private SharedPrefManager(Context context) {
        this.context = context;
    }

    public static synchronized  SharedPrefManager getInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = new SharedPrefManager(context);
        }
        return INSTANCE;
    }

    public boolean userLogin (UserData userData){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME_LOGIN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor  = sharedPreferences.edit();
        editor.putLong(KEY_ID, userData.getId());
        editor.putString(KEY_NAME, userData.getDisplayName());
        editor.putString(KEY_NPM, userData.getUserLogin());
        editor.apply();
        return true;
    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME_LOGIN, Context.MODE_PRIVATE);
        if (sharedPreferences.getString(KEY_NPM, null) != null)
            return true;
        return false;
    }

    public UserData getUser(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME_LOGIN, Context.MODE_PRIVATE);
        return new UserData(
                sharedPreferences.getLong(KEY_ID, 0),
                sharedPreferences.getString(KEY_NPM, null),
                sharedPreferences.getString(KEY_NAME, null),
                sharedPreferences.getString(KEY_EMAIL, null)
        );
    }
}
