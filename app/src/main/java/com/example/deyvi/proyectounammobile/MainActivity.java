package com.example.deyvi.proyectounammobile;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.deyvi.proyectounammobile.adapter.PageAdapter;
import com.example.deyvi.proyectounammobile.fragment.SharedFragment;
import com.example.deyvi.proyectounammobile.fragment.storeFragment;
import com.example.deyvi.proyectounammobile.fragment.timelineFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar     =   (Toolbar) findViewById(R.id.toolbar);
        tabLayout   =   (TabLayout) findViewById(R.id.tabLayout);
        viewPager   =   (ViewPager) findViewById(R.id.viewPager);

        //vamos hacer una validacion
        if(toolbar != null){
            setSupportActionBar(toolbar);
        }
        setupViewPager();
    }

    private ArrayList<Fragment> agregarFragment(){
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new timelineFragment());
        fragments.add(new storeFragment());
        fragments.add(new SharedFragment());

        return  fragments;
    }

    private  void setupViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),agregarFragment()));
        tabLayout.setupWithViewPager(viewPager);

        //ahora hay que agregar nuestro icono y nombre

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_timeline).setText(getResources().getString(R.string.timeline));
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_store).setText(getResources().getString(R.string.store));
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_user).setText(getResources().getString(R.string.shared));
    }
}
