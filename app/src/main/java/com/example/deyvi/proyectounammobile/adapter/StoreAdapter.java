package com.example.deyvi.proyectounammobile.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.deyvi.proyectounammobile.MapsActivity;
import com.example.deyvi.proyectounammobile.R;
import com.example.deyvi.proyectounammobile.pojo.Store;

import java.util.ArrayList;

/**
 * Created by deyvi on 22/04/2017.
 */

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewHolder> {
    Context context;
    Activity activity;
    ArrayList<Store> stores;

    public StoreAdapter(Context context, Activity activity, ArrayList<Store> stores) {
        this.context = context;
        this.activity = activity;
        this.stores = stores;
    }

    /**
     * infla o da vida a nuestro cardView hay que inflarlo y lo asosiamos a nuestra CardView
     * lliteralmente le pasamos la vista y en StoreViewHolder tomamos cada parate para poder
     * manejarlas
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public StoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_store,parent,false);
        return  new StoreViewHolder(v);
    }

    @Override
    public void onBindViewHolder(StoreViewHolder holder, int position) {
         final Store store = stores.get(position);

        holder.name.setText(store.getName());
        Glide.with(context).load(store.getImage()).into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(activity,MapsActivity.class);
                intent.putExtra("name",store.getName());
                intent.putExtra("lat", store.getLat());
                intent.putExtra("lon", store.getLon());
                activity.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return stores.size();
    }


    public  static class  StoreViewHolder extends  RecyclerView.ViewHolder{
        private TextView name ;
        private ImageView image;


        public StoreViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.tvName);
            image = (ImageView) itemView.findViewById(R.id.imgStore);


        }
    }
}
