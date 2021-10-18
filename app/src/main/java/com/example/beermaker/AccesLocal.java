package com.example.beermaker;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class AccesLocal {

    //propriété
    private String nomDB = "BddRecette";
    private Integer version = 1;
    private MySQLiteOpenHelper accesBD;
    private SQLiteDatabase database;


    public AccesLocal(Context contexte){
        accesBD = new MySQLiteOpenHelper(contexte, nomDB, null, version);
    }

    // methodes personnalisées
    // methode pour ajouter un enregistrement,
    public void ajout(Double ingredient1, Double ingredient2, Double ingredient3 ){
        database = accesBD.getWritableDatabase();
        String request = " Insert into BddRecette (id, ingredient1, ingredient2, ingredient3) values ";
        Integer id =  recupDernierId()+1;
        request += "( "+id +" , \""+ingredient1+"\", \""+ingredient2+"\", \""+ingredient3+"\");";
        Log.d("log Request", "Mon ajout :" +request);
        database.execSQL(request);
    }

    // methode pour retirer tout les enregistrements
    public void vider(){
        database = accesBD.getWritableDatabase();
        String request = "delete from BddRecette";
        database.execSQL(request);
    }

    // methode pour récupèrer l'id du dernier enregistrement
    public Integer recupDernierId(){
        Integer id = 0;
        database = accesBD.getReadableDatabase();
        String request = "select * from BddRecette;";
        Cursor curseur = database.rawQuery(request, null);
        curseur.moveToLast();
        if (!curseur.isAfterLast()){
            id = curseur.getInt(0);
        }
        curseur.close();

        return id;
    }

    // methode pour récupèrer le dernier enregistrement
    public Calculs recupDernierEnreg(){

        database = accesBD.getReadableDatabase();
        String request = "select * from BddRecette;";
        Cursor curseur = database.rawQuery(request, null);
        Calculs donnee = null;
        curseur.moveToLast();
        if (!curseur.isAfterLast()){
            Double volumeBiere = curseur.getDouble(0);
            Double alcoolDegre = curseur.getDouble(1);
            Double moyenneEBC = curseur.getDouble(2);
            donnee = new Calculs(volumeBiere,  alcoolDegre,  moyenneEBC);
        }
        curseur.close();

        return donnee;
    }

    // methode pour tout recupérer
    public ArrayList<Calculs> recupEtoile(){
        ArrayList<Calculs> listDonnee = new ArrayList<>();
        database = accesBD.getReadableDatabase();
        String request = "select * from BddRecette;";
        Cursor curseur = database.rawQuery(request, null);
        Calculs donnee = null;

        curseur.moveToFirst();
        while (curseur.isAfterLast() == false)
        {
            Integer id = curseur.getInt(0);
            Double volumeBiere = curseur.getDouble(1);
            Double alcoolDegre = curseur.getDouble(2);
            Double moyenneEBC = curseur.getDouble(3);
            donnee = new CalculsBDD(id ,volumeBiere,  alcoolDegre,  moyenneEBC);
            listDonnee.add(donnee);
            curseur.moveToNext();
        }
        curseur.close();
        return listDonnee;
    }
}
