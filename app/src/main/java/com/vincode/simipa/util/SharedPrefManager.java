package com.vincode.simipa.util;

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

    static final String KEY_NPM_STUDENT = "npm_student";
    static final String KEY_NAME_STUDENT = "name_student";
    static final String KEY_DEPARTMENT_STUDENT = "department_student";
    static final String KEY_IMAGE_STUDENT = "image_student";

    private SharedPrefManager(Context context) {
        SharedPrefManager.context = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new SharedPrefManager(context);
        }
        return INSTANCE;
    }

    public static String getNpmStudent() {
        return context.getSharedPreferences(PREF_NAME_LOGIN, Context.MODE_PRIVATE).getString(KEY_NPM_STUDENT, "");
    }

    public static String getNameStudent() {
        return context.getSharedPreferences(PREF_NAME_LOGIN, Context.MODE_PRIVATE).getString(KEY_NAME_STUDENT, "");
    }

    public static String getDepartmentStudent() {
        return context.getSharedPreferences(PREF_NAME_LOGIN, Context.MODE_PRIVATE).getString(KEY_DEPARTMENT_STUDENT, "");
    }

    public static String getImageStudent() {
        return context.getSharedPreferences(PREF_NAME_LOGIN, Context.MODE_PRIVATE).getString(KEY_IMAGE_STUDENT, "");
    }

    public boolean userLogin(UserData userData) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME_LOGIN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(KEY_ID, userData.getId());
        editor.putString(KEY_NAME, userData.getDisplayName());
        editor.putString(KEY_NPM, userData.getUserLogin());
        editor.apply();
        return true;
    }

    public UserData getUser() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME_LOGIN, Context.MODE_PRIVATE);
        return new UserData(
                sharedPreferences.getLong(KEY_ID, 0),
                sharedPreferences.getString(KEY_NPM, null),
                sharedPreferences.getString(KEY_NAME, null)
        );
    }

    public boolean logout() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME_LOGIN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME_LOGIN, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_NPM, null) != null;
    }

    // Start Shared Preference SIMIPA modul Orang tua
    public void setDataStudent(String npm, String name, String department, String image) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME_LOGIN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_NPM_STUDENT, npm);
        editor.putString(KEY_NAME_STUDENT, name);
        editor.putString(KEY_DEPARTMENT_STUDENT, department);
        editor.putString(KEY_IMAGE_STUDENT, image);
        editor.apply();
    }
// End Shared Preference SIMIPA modul Orang tua
}
