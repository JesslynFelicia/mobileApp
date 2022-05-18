package com.example.kepo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SharedPref pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pref = new SharedPref(this);
        setContentView(R.layout.activity_main);
        TextView welcomemessage;
        welcomemessage = findViewById(R.id.welcome_message);
        welcomemessage.setText("Welcome "+pref.getname());
    }

    public void profile(View view) {
        Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
        startActivity(intent);

    }

    public void mytodo(View view) {
        Intent intent = new Intent(MainActivity.this,TodoActivity.class);
        startActivity(intent);

    }

    public void SearchUser(View view) {
        Intent intent = new Intent(MainActivity.this,SearchuserActivity.class);
        startActivity(intent);

    }

    public void searchtodo(View view) {
        Intent intent = new Intent(MainActivity.this,SearchtodoActivity.class);
        startActivity(intent);

    }
}