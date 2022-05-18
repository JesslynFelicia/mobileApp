package com.example.kepo;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.kepo.databinding.ActivityProfileBinding;
import com.example.kepo.model.User;

import org.json.JSONObject;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    TextView tvname, tvusername;
    private SharedPref pref;
    private SharedPreferences.Editor editor;

    private ActivityProfileBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_profile);
        loaddata();
    }

    public void loaddata()
    {
        pref = new SharedPref(this);
        tvname = findViewById(R.id.tv_name);
        tvname.setText(pref.getname());
        tvusername = findViewById(R.id.tv_username);
        tvusername.setText(pref.getusername());
    }

    public void logout(View view) {

                    AlertDialog.Builder builder
                    = new AlertDialog.Builder(this);
            // Set the message show for the Alert time
            builder.setMessage("Are you sure you want to logout?");
            // Set Alert Title
            builder.setTitle("Logout");
            builder.setCancelable(false);
            builder
                    .setPositiveButton(
                            "yes",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which)
                                {

                                    // If user click no
                                    // then dialog box is canceled.
                                    yeslogout();
                                }
                            })
                    .setNegativeButton(
                            "no",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which)
                                {

                                    // If user click no
                                    // then dialog box is canceled.
                                    dialog.cancel();
                                }
                            });

            // Create the Alert dialog
           // AlertDialog alertDialog = builder.create();
            AlertDialog dialog = builder.create();


            dialog.show();
            // Show the Alert Dialog box
            //alertDialog.show();
    }

    public void back(View view) {
        Intent intent = new Intent(ProfileActivity.this,MainActivity.class);
        startActivity (intent);
    }

    public void yeslogout()
    {
        Intent intent = new Intent(ProfileActivity.this,SplashActivity.class);
        startActivity (intent);
        pref.clearsharedpreferences();
        finish();
    }
}
