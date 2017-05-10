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
import android.widget.ListView;
import android.widget.Toast;


import com.example.deyvi.proyectounammobile.R;
import com.example.deyvi.proyectounammobile.adapter.UserAdapter;
import com.example.deyvi.proyectounammobile.adapter.UsuarioAdapter;
import com.example.deyvi.proyectounammobile.pojo.User;
import com.google.gson.stream.JsonWriter;


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
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class timelineFragment extends Fragment {
    ListView lvuser;
    //mi lista de usuario donde se va a parsear los datos ya deserealisados
    ArrayList<User> users;
    RecyclerView rvUser;
    ProgressDialog progressDialog;
    public timelineFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_timeline, container, false);
        //creamos nuestra lista donde se almacenaran nuestros  users
        users = new ArrayList<>();

        new JsonTask().execute("http://www.mocky.io/v2/58e12be02500006c06633072");

        return  v;
    }

    public  class JsonTask  extends AsyncTask<String,Void, String> {

        protected void onPreExecute() {
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setView(getView());
            progressDialog.setMessage("Espera...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            HttpURLConnection  conecction = null;
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

                JSONArray jsonArray = jsonObject.getJSONArray("timeline");

                for(int i=0 ; i <= jsonArray.length(); i++){
                    JSONObject json = jsonArray.getJSONObject(i);

                    String usuario = json.getString("user");
                    String content_title = json.getString("content_title");
                    String content_text = json.getString("content_text");
                    String image = json.getString("image");

                    users.add(new User(usuario,content_title,content_text,image));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            Toast.makeText(getContext(), "hay "+ users.size()+" posts", Toast.LENGTH_SHORT).show();
            rvUser = (RecyclerView) getView().findViewById(R.id.rvUser);
            //hay que definir como se va a mostrar nuestra lista
            LinearLayoutManager llm= new LinearLayoutManager(getActivity());
            llm.setOrientation(LinearLayoutManager.VERTICAL);

            /**
             * ahora a mi lista de contactos o mi recycler view
             * le decimos que adquiera el comportamiento de  un linear layout
             */
            rvUser.setLayoutManager(llm);
            UserAdapter userAdapter= new UserAdapter(getContext(),users);
            rvUser.setAdapter(userAdapter);
            progressDialog.dismiss();

        }
    }

}


