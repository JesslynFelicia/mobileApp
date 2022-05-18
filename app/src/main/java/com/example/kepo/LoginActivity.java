package com.example.kepo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.kepo.databinding.ActivityLoginBinding;

import com.example.kepo.model.User;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private SharedPref pref;
    private ActivityLoginBinding binding;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_login);
        binding.btnLogin.setOnClickListener(v -> login());
        binding.setUser(new User());
        pref = new SharedPref(this);
        if (!pref.getUserID().equals("")){

            gotoMainActivity();
        }
    }


    private void login() {

        User user = binding.getUser();

        if (user.getUsername()==null ||user.getPassword()==null)
        {
            openbutton("Please Input Username and Password");
        }

        else
        {
            //1. method,2. url 3. request 4. listener (berhasil) 5. listener (gagal)
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    "https://it-division-kepo.herokuapp.com/login",
                    getloginBody(user),

                    response -> {
                        Log.d("<result>","tes" + response);
                        try{
                            Log.d("<result>","masuksini");
                            String user_id = response.getJSONObject("data").getString("user_id");
                            String username = response.getJSONObject("data").getString("username");
                            String name = response.getJSONObject("data").getString("name");
                            pref.save(user_id,username,name);
                            gotoMainActivity();
                        }catch(Exception ex){
                            Log.d("<result>","masuksini1");
                            try {
                                Log.d("<result>","masuksini2");
                                String message = response.getString("message");
                                openbutton(message);
                            } catch (JSONException e) {
                                Log.d("<result>","masuksini3");
                                e.printStackTrace();
                            }
                            Log.d("<result>","masuksini4");
                            ex.printStackTrace();
                        }
                    },
                    error -> {
                        //Log.d("<RESULT>","yes");
                        error.printStackTrace();

                    }
            );

            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(jsonObjectRequest);
        }
    }
    private JSONObject getloginBody(User user) {
        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username",user.getUsername());
            jsonObject.put("password",user.getPassword());
            Log.d("<result>","tes" + jsonObject);
            return jsonObject;
        }catch(Exception ex){
            return null;
        }
    }

    private void gotoMainActivity(){
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void openbutton(String string)
        {

            BottomDialog bottomDialog = new BottomDialog();
            bottomDialog.a= string ;
            bottomDialog.show(getSupportFragmentManager(),"error message");
    }

}
