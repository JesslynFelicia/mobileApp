package com.example.kepo;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.kepo.Adapter.TodoAdapter;
import com.example.kepo.databinding.ActivityAddBinding;
import com.example.kepo.model.Todo;
import com.example.kepo.model.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AddActivity extends AppCompatActivity {
   private SharedPref pref;
    private ActivityAddBinding binding;
    private TodoAdapter todoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_add);
        pref = new SharedPref(this);
        binding.setTodo(new Todo());
        TextView mTextView;
        TextView tv_count=findViewById(R.id.tv_count);
        EditText mEditText = findViewById(R.id.et_description);
        final TextWatcher mTextEditorWatcher = new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tv_count.setText(String.valueOf(s.length())+"/100");
            }

            public void afterTextChanged(Editable s) {
            }
        };
        mEditText.addTextChangedListener(mTextEditorWatcher);

    }


    public void backadd(View view) {
        Intent intent = new Intent(AddActivity.this,TodoActivity.class);
        startActivity(intent);
        finish();
    }

    public void check(View view) {
        addto();
    }

    private void addto(){
       Todo todo = binding.getTodo();
        Log.d("<result>","apa"+todo.getTitle());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                "https://it-division-kepo.herokuapp.com/user/"+pref.getUserID()+"/todo",
                viewtitle(todo),

                response -> {
                    try{
                        String message = response.getString("message");
                        TextView msg = findViewById(R.id.createtodomsg);
                        msg.setText(message);
                        JSONArray array = response.getJSONArray("listTodo");
                        JSONObject jsonObj= new JSONObject();
                        JSONObject todo_id = jsonObj.put("todo_id", todo.getTodo_id());
                        jsonObj.put("title", todo.getTitle());
                        jsonObj.put("description", todo.getDescription());
                        array.put(jsonObj);
                        JSONObject currentJsonObject = new JSONObject();
                        response.put("data",array);

                        Log.d("<result>", "hasil4 "+response);

                        JSONArray data = response.getJSONArray("listTodo");
                        ArrayList<Todo> listdata = new ArrayList<>();
                        for (int i=0;i<data.length();i++){
                            JSONObject jsonObject = data.getJSONObject(i);
                            String todoid = jsonObject.getString("todo_id");
                            String title = jsonObject.getString("title");
                            String description = jsonObject.getString("description");
                            String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                            String last_edited = jsonObject.getString(currentDate);

                           todo.setTodo_id(todoid);
                           todo.setDescription(description);
                           todo.setTitle(title);
                           todo.setLast_edited(last_edited);
                            listdata.add(todo);
                            todoAdapter.UpdateData(listdata);
                        }todoAdapter.UpdateData(listdata);


                    }catch(Exception ex){
                        Log.d("<result>","testing1" + response);
                        ex.printStackTrace();
                    }


                },
                error -> {
                    if (todo.getTitle()==null || todo.getDescription()==null)
                    {
                        String message ="Title and Description must be filled";
                        TextView msg = findViewById(R.id.createtodomsg);
                        msg.setText(message);
                    }

                    else
                    {
                        String message ="Something is wrong";
                        TextView msg = findViewById(R.id.createtodomsg);
                        msg.setText(message);
                    }
                    Log.d("<result>", "hasil2 "+error);
                    error.getMessage();
                    error.printStackTrace();

                }
        );

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);
    }

    private JSONObject viewtitle(Todo todo){
        try{

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("title",todo.getTitle());

            jsonObject.put("description",todo.getDescription());

            return jsonObject;
        }catch(Exception ex){
            return null;
        }
    }
}
