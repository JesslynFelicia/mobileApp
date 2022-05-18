package com.example.kepo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class SearchtodoActivity extends AppCompatActivity {
   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_searchtodo);

   }

    public void backstodo(View view) {
       Intent intent = new Intent(SearchtodoActivity.this,MainActivity.class);
       startActivity(intent);
       finish();
    }
}
