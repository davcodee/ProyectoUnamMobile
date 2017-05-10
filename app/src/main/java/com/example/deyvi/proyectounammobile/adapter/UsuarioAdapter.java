package com.example.deyvi.proyectounammobile.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.deyvi.proyectounammobile.R;
import com.example.deyvi.proyectounammobile.pojo.User;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by deyvi on 08/04/2017.
 */

public class UsuarioAdapter extends BaseAdapter {


    private Context context;
    private List<User> users;

    public UsuarioAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater =  LayoutInflater.from(context);
        //hay que buscar la vista que defini
        View v = layoutInflater.inflate(R.layout.cardview_timeline, null);
        User u = users.get(position);
        ((TextView) v.findViewById(R.id.tvUser)).setText(u.getUser());
        ((TextView) v.findViewById(R.id.tvTitle)).setText(u.getContent_title());
        ((TextView) v.findViewById(R.id.tvText)).setText(u.getContent_texto());
        ImageView i = (ImageView) v.findViewById(R.id.imgUser);
        i.setVisibility(View.INVISIBLE);
        if (!TextUtils.isEmpty(u.getImage())) {
            i.setVisibility(View.VISIBLE);
            Picasso.with(context)
                    .load(u.getImage())
                    .into(i);
        }
        return v;

    }
}
