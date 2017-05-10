package com.example.deyvi.proyectounammobile.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.deyvi.proyectounammobile.R;
import com.example.deyvi.proyectounammobile.pojo.User;

import java.util.ArrayList;

/**
 * Created by deyvi on 08/04/2017.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    Context context;
    ArrayList<User> users;

    public UserAdapter(Context context, ArrayList<User> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_shared,parent,false);
        return new UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user = users.get(position);

        holder.user.setText(user.getUser());
        holder.content_title.setText(user.getContent_title());
        holder.content_texto.setText(user.getContent_texto());
        Glide.with(context).load(users.get(position).getImage()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class UserViewHolder extends  RecyclerView.ViewHolder {
        private TextView user;
        private TextView content_title;
        private TextView content_texto;
        private ImageView image;

        public UserViewHolder(View itemView) {
            super(itemView);
            user          = (TextView) itemView.findViewById(R.id.tvUser);
            content_title = (TextView) itemView.findViewById(R.id.tvTitle);
            content_texto = (TextView) itemView.findViewById(R.id.tvText);
            image         = (ImageView) itemView.findViewById(R.id.imgUser);
        }
    }
}
