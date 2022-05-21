package com.mittal.onlychats;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.jar.Attributes;


public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder>{



    ArrayList<Users> list;

    Context context;

    public UsersAdapter(ArrayList<Users> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_user_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Users users=list.get(position);
        Picasso.get().load(users.getProfilePic()).placeholder(R.drawable.user_avatar).into(holder.profilePic);
        holder.Name.setText(users.getUsername());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(context,ChatScreen.class);
                in.putExtra("name",users.getUsername());
                in.putExtra("profilePic",users.getProfilePic());
                in.putExtra("userId",users.getUserId());
                context.startActivity(in);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView profilePic;
        TextView Name,LastMessage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profilePic=itemView.findViewById(R.id.profile_image);
            Name=itemView.findViewById(R.id.Name);
            LastMessage=itemView.findViewById(R.id.LastMessage);

        }
    }
}
