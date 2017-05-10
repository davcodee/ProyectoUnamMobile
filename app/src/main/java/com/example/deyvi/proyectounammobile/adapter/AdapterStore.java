package com.example.deyvi.proyectounammobile.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.deyvi.proyectounammobile.R;
import com.example.deyvi.proyectounammobile.pojo.Store;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by deyvi on 23/04/2017.
 */

class AdaptadorNegocio extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Store> negocios;
    private Bitmap bm;

    public AdaptadorNegocio(Context context, int layout, ArrayList<Store> negocios) {
        this.context = context;
        this.layout = layout;
        this.negocios = negocios;
    }

    @Override
    public int getCount() {
        return negocios.size();
    }

    @Override
    public Object getItem(int position) {
        return negocios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater;
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.cardview_store, parent, false);
            holder.imagen = (ImageView) convertView.findViewById(R.id.imgStore);
            holder.nombre = (TextView) convertView.findViewById(R.id.tvName);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Store n = negocios.get(position);
        holder.nombre.setText(n.getName());
        Picasso.with(context)
                .load(n.getImage())
                .into(holder.imagen);

        return convertView;
    }

    private static class ViewHolder {
        ImageView imagen;
        TextView nombre;
    }


}
