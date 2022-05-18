package com.example.kepo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.kepo.Adapter.TodoAdapter;
import com.example.kepo.databinding.ActivityMytodoBinding;
import com.example.kepo.model.Todo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class TodoActivity extends AppCompatActivity {
    private SharedPref pref;
    private String base_url;
    private SharedPref pref1;
    private TodoAdapter todoAdapter;
    private ActivityMytodoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_mytodo);
        pref = new SharedPref(this);
        base_url = "https://it-division-kepo.herokuapp.com/user/"+pref.getUserID()+"/todo";

        cektodo();
        initAdapter();
        //taketodo() ;
    }

    public void detailtodo(){
        Log.d("<result>","sabi");
    }

    private void cektodo(){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                base_url,
                null,

                response -> {
                    Log.d("<result>","tes" + response);
                    try{
                        JSONObject DATA = response.getJSONObject("data");
                        JSONArray data = DATA.getJSONArray("listTodo");
                        ArrayList<Todo> listdata = new ArrayList<>();

                        for (int i=0;i<data.length();i++){
                            JSONObject jsonObject = data.getJSONObject(i);
                            Log.d("<result>","ini data "+ data.getJSONObject(i));
                            String todo_id = jsonObject.getString("todo_id");
                            String title = jsonObject.getString("title");
                            String lastEdited = jsonObject.getString("last_edited");
                            Todo todo = new Todo();
                            todo.setTodo_id(todo_id);
                            todo.setTitle(title);
                            todo.setLast_edited(lastEdited);
                            listdata.add(todo);
                        }
                        todoAdapter.UpdateData(listdata);


                    }catch(Exception ex){

                        ex.printStackTrace();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("<RESULT>","error");
                        error.printStackTrace();

                    }
                }
        );

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);
    }

    private void initAdapter(){
        Log.d("<result>","halooo1");
        todoAdapter = new TodoAdapter(this);
        binding.rvmytodo.setLayoutManager(new LinearLayoutManager(this));
        binding.rvmytodo.setAdapter(todoAdapter);
    }

    public void backtodo(View view) {
        Intent intent = new Intent(TodoActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void added(View view) {
        Intent intent = new Intent(TodoActivity.this,AddActivity.class);
        startActivity(intent);
        finish();
    }
}
