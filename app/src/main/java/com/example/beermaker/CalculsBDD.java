package com.example.beermaker;

public class CalculsBDD extends Calculs{
    //Attributs
    Integer id;

    public CalculsBDD(Integer id, Double volumeBiere, Double alcoolDegre, Double moyenneEBC) {
        super(volumeBiere, alcoolDegre, moyenneEBC);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}


