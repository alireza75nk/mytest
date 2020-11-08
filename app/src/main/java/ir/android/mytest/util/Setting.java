package ir.android.mytest.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Setting {
    SharedPreferences preferences;

    public Setting(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public Setting(Activity activity) {
        this.preferences = activity.getSharedPreferences("global", Context.MODE_PRIVATE);
    }

    public void put(String key,String value){
        preferences.edit().putString(key, value).apply();
    }

    public String get(String key,String defValue){
        return preferences.getString(key, defValue);
    }

}
