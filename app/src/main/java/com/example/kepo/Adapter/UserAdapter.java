package com.example.kepo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kepo.databinding.UserItemLayoutBinding;
import com.example.kepo.model.User;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{

    private ArrayList<User> users;
    private Context context;

    public UserAdapter(Context context){
        this.users = new ArrayList<>();
        this.context = context;
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        UserItemLayoutBinding itemLayoutBinding = UserItemLayoutBinding.inflate(layoutInflater,parent,false);
        return new UserViewHolder(itemLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = users.get(position);
        holder.itemLayoutBinding.setUser(user);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void UpdateData(ArrayList<User> newUsers){
        users.clear();
        users.addAll(newUsers);
        notifyDataSetChanged();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{
        private UserItemLayoutBinding itemLayoutBinding;

        public UserViewHolder(@NonNull UserItemLayoutBinding itemLayoutBinding){
            super(itemLayoutBinding.getRoot());
            this.itemLayoutBinding=itemLayoutBinding;
        }
    }


}
