package com.example.kepo.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kepo.databinding.TodoItemLayoutBinding;
import com.example.kepo.model.Todo;

import java.util.ArrayList;
import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder>{



    private ArrayList<Todo> todos;
    private Context context;

    public interface OnItemClickListener {
        void onItemClick(Todo todo);
    }


  //  private final OnItemClickListener listener;


    public TodoAdapter(Context context){

        this.todos = new ArrayList<>();
        this.context = context;
    }
    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        TodoItemLayoutBinding itemLayoutBinding = TodoItemLayoutBinding.inflate(layoutInflater,parent,false);
        return new TodoViewHolder(itemLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        Todo todo = todos.get(position);
        holder.itemLayoutBinding.setTodo(todo);
    }


    @Override
    public int getItemCount() {
        return todos.size();
    }

    public void UpdateData(ArrayList<Todo> newTodos){
        todos.clear();
        todos.addAll(newTodos);
        notifyDataSetChanged();
    }

    class TodoViewHolder extends RecyclerView.ViewHolder{
        private TodoItemLayoutBinding itemLayoutBinding;

        public TodoViewHolder(@NonNull TodoItemLayoutBinding itemLayoutBinding){
            super(itemLayoutBinding.getRoot());
            this.itemLayoutBinding=itemLayoutBinding;
        }

    }

    public void onClick(View view) {
        Log.d("<result>","cumantes");

    }


}