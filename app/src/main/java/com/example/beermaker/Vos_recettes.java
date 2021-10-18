package com.example.beermaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

//import com.example.beermaker.RecyclerView.MyAdapter;

import java.util.ArrayList;

public class Vos_recettes extends AppCompatActivity {

    //Attributs
    private RecyclerView listeBdd;
    private AccesLocal accesLocal;
    TextView nbrRecette;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vos_recettes);

        init();

        ArrayList<Calculs> listeRecette = accesLocal.recupEtoile();
        Log.d("Pourquoi ?", String.valueOf(listeRecette.size()));
        listeBdd.setLayoutManager(new LinearLayoutManager(this));
        //listeBdd.setAdapter(new MyAdapter(listeRecette));

    }

    public void init(){
        listeBdd = findViewById(R.id.listerecette);
        accesLocal = new AccesLocal(this);

        nbrRecette = findViewById(R.id.nbrRecette);
    }



    @Override
    public void onResume(){
        Log.d("MonLog", "OnResume Start");
        super.onResume();
    }
}