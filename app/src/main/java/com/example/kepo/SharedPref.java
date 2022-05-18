package com.example.kepo;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SharedPref {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    public SharedPref(Context context)
    {
        pref = context.getSharedPreferences("SharedPref",Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void save (String user_id, String username,String name){
        editor.putString("user_id",user_id);
        editor.apply();
        editor.putString("username",username);
        editor.apply();
        editor.putString("name",name);
        editor.apply();
    }

    public String getUserID(){
        return pref.getString("user_id","");
    }

    public String getusername(){
        Log.d("<result>","cek "+pref.getString("username","a"));
        return pref.getString("username","a");
    }

    public String getname(){
        Log.d("<result>","cek "+pref.getString("name","a"));
        return pref.getString("name","a");
    }

    public void clearsharedpreferences()
    {
        editor.clear().commit();
    }
}
