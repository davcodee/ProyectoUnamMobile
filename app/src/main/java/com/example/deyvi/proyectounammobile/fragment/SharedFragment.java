package com.example.deyvi.proyectounammobile.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.JsonReader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.example.deyvi.proyectounammobile.R;
import com.example.deyvi.proyectounammobile.adapter.UserAdapter;
import com.example.deyvi.proyectounammobile.pojo.User;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static java.lang.System.in;
import static java.lang.System.loadLibrary;


/**
 * A simple {@link Fragment} subclass.
 */
public class SharedFragment extends Fragment {
    private CheckBox estado;
    private EditText edtText;
    private Button btnSave;
    public SharedFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v  = inflater.inflate(R.layout.fragment_shared, container, false);
        estado  = (CheckBox) v.findViewById(R.id.checkBox);
        edtText = (EditText) v.findViewById(R.id.edtText);
        btnSave = (Button)v.findViewById(R.id.btnSave);

        CargarPreferencias();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GuardarPreferencias();
            }
        });
        return v;

    }

    public  void CargarPreferencias(){
        SharedPreferences mispreferencias = getActivity().getSharedPreferences("preferencesUser", Context.MODE_PRIVATE);
        estado.setChecked(mispreferencias.getBoolean("checked",false));
        edtText.setText(mispreferencias.getString("name",""));
    }

    public void  GuardarPreferencias(){
        SharedPreferences mispreferencias = getActivity().getSharedPreferences("preferencesUser", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= mispreferencias.edit();
        //guardamos que el usuario haya dado en el checkbox
        boolean valor= estado.isChecked();
        String nombre =edtText.getText().toString().trim();
        editor.putBoolean("checked", valor);
        editor.putString("name",nombre );
        editor.commit();
    }

}
