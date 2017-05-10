package com.example.deyvi.proyectounammobile.fragment;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.deyvi.proyectounammobile.R;
import com.example.deyvi.proyectounammobile.adapter.StoreAdapter;
import com.example.deyvi.proyectounammobile.pojo.Store;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


/**
 * falta terminar el mapa donde se va a mostrar
 * cheaca con calma el recyclerView adapter que es el que puede fallar
 * ademas de el oncLick que puede que este mal
 * A simple {@link Fragment} subclass.
 */
public class storeFragment extends Fragment {
    ProgressDialog progressDialog;
    ArrayList<Store> stores;
    RecyclerView  rvStore;

    public storeFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_store, container, false);

        stores = new ArrayList<>();

        new MyTask().execute("http://www.mocky.io/v2/58e12f07250000ca06633073");
        //le desimos como se va a comportar nuestra Recycler View

        return v;
    }


    public  class  MyTask extends AsyncTask<String,Void,String> {

        protected void onPreExecute() {
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setView(getView());
            progressDialog.setMessage("Espera...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection conecction = null;
            BufferedReader reader = null;

            //le pasamos nuestro url de nuestro servidor
            try {

                URL url = new URL(params[0]);

                conecction = (HttpURLConnection) url.openConnection();
                conecction.connect();

                InputStream stream = conecction.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                String line="";
                StringBuffer buffer= new StringBuffer();

                //mientras la lectura no sea null entonces
                while((line = reader.readLine()) != null){
                    // mientras la linea agregar no sea nula entonces lo agregas al buffer
                    //ya  alli entonces quedara como una cadena String
                    buffer.append(line);
                }
                return buffer.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                //ya si se leyo bien entones cerramos la conccion y la lectura de el JSON
                //hay que inicialzar la variables en null
                if(conecction != null){
                    //esto manda nullpointerExeption por eso verificamos que no sea nula
                    conecction.disconnect();
                }try {
                    if(reader != null){
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray =jsonObject.getJSONArray("business_data");
                //aqui va la creacion de nuestro objeto
                for(int i = 0 ; i <= jsonArray.length(); i++){
                    JSONObject json = jsonArray.getJSONObject(i);

                    String name = json.getString("name");
                    String image = json.getString("image");
                    double lat = json.getDouble("lat");
                    double lon = json.getDouble("lon");
                    stores.add(new Store(name,image,lat,lon));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            rvStore = (RecyclerView) getView().findViewById(R.id.rvStore);

            LinearLayoutManager llm= new LinearLayoutManager(getContext());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            rvStore.setLayoutManager(llm);

            StoreAdapter storeAdapter = new StoreAdapter(getContext(),getActivity(),stores);
            rvStore.setAdapter(storeAdapter);
            progressDialog.dismiss();
        }
    }


}
