package com.example.kepo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.kepo.Adapter.UserAdapter;
import com.example.kepo.databinding.ActivitySearchuserBinding;
import com.example.kepo.model.User;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchuserActivity extends AppCompatActivity {
    private SharedPref pref;
    private UserAdapter userAdapter;
    private ActivitySearchuserBinding binding;
    String search_query = "tes";
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_searchuser);
       // EditText etsearch = findViewById(R.id.et_searchuser);
        tes();
        initAdapter();
       // cari();
    }

    private void tes()  {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                "https://it-division-kepo.herokuapp.com/searchUser",
                search(),

                response -> {
                    try{
                        JSONArray array = response.getJSONArray("data");
                        JSONObject jsonObj= new JSONObject();
                        jsonObj.put("user_id", pref.getUserID());
                        jsonObj.put("name", "inites");
                        jsonObj.put("username", "initesjuga");
                        array.put(jsonObj);
                        JSONObject currentJsonObject = new JSONObject();
                        response.put("data",array);

                        //entah kenapa di response ini masuk giliran dipanggil dibawah gamasuk :')
                        Log.d("<result>", "hasil4 "+response);

                        JSONArray data = response.getJSONArray("data");
                        ArrayList<User> listdata = new ArrayList<>();
                        for (int i=0;i<data.length();i++){
                            JSONObject jsonObject = data.getJSONObject(i);
                            String user_id = jsonObject.getString("user_id");
                            String username = jsonObject.getString("username");
                            String name = jsonObject.getString("name");
                            User user = new User();
                            user.setName(name);
                            user.setUser_id(user_id);
                            user.setUsername(username);
                            listdata.add(user);
                        }
                        userAdapter.UpdateData(listdata);

                    }catch(Exception ex){
                        Log.d("<result>","testing1" + response);
                        ex.printStackTrace();
                    }


                },
                error -> {
                    Log.d("<result>", "hasil2 ");
                    error.printStackTrace();

                }
        );

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);
    }



    private void initAdapter(){
        userAdapter = new UserAdapter(this);
        binding.rvUsers.setLayoutManager(new LinearLayoutManager(this));
        binding.rvUsers.setAdapter(userAdapter);
    }

    public void cari(){
        //1. method,2. url 3. request 4. listener (berhasil) 5. listener (gagal)
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                "https://it-division-kepo.herokuapp.com/searchUser",
                search(),

                response -> {
                    Log.d("<result>","testing" + response);
                    try{
                        JSONArray data = response.getJSONArray("data");
                        ArrayList<User> listdata = new ArrayList<>();
                        if ( data.length() != 0) {
                            Log.d("<result>"," data found"+ data.length());
                            for (int i=0;i<data.length();i++){
                               JSONObject jsonObject = data.getJSONObject(i);
                                String user_id = jsonObject.getString("user_id");
                                String username = jsonObject.getString("username");
                                String name = jsonObject.getString("name");
                                User user = new User();
                                user.setName(name);
                                user.setUser_id(user_id);
                                user.setUsername(username);
                                listdata.add(user);
                            }
                            userAdapter.UpdateData(listdata);
                        }
                        else{
                            Log.d("<result>","no data found");
                        }

                    }catch(Exception ex){
                        Log.d("<result>","testing1" + response);
                        ex.printStackTrace();
                        }


                },
                error -> {
                    Log.d("<result>","testing" + error);
                    Log.d("<RESULT>","yes1");
                    error.printStackTrace();

                }
        );

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);
    }

    private JSONObject search(){

        User user = null;
        try{
            pref = new SharedPref(this);

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("user_id",pref.getUserID());

            jsonObject.put("searchQuery",search_query);

            //Log.d("<result>","search" + jsonObject);
            return jsonObject;
        }catch(Exception ex){
            return null;
        }
    }

    public void backp(View view) {
        Intent intent = new Intent(SearchuserActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
