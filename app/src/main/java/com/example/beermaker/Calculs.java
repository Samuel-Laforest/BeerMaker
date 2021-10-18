package com.example.beermaker;

public class Calculs{

    Double vlBiere;
    Double dgralcool;
    Double moyEBC;

    //constru
    public Calculs(Double vlBiere, Double dgralcool, Double moyEBC) {
        this.vlBiere = vlBiere;
        this.dgralcool = dgralcool;
        this.moyEBC = moyEBC;
    }


    //Getter/Setter

    public Double getVlBiere() {
        return vlBiere;
    }
    public void setVlBiere(Double vlBiere) {
        this.vlBiere = vlBiere;
    }

    public Double getDgralcool() {
        return dgralcool;
    }
    public void setDgralcool(Double dgralcool) {
        this.dgralcool = dgralcool;
    }

    public Double getMoyEBC() {
        return moyEBC;
    }
    public void setMoyenneEBC(Double moyEBC) {
        this.moyEBC = moyEBC;
    }


    private static double arrondir(double chiffre, int nbChiffreApresVirgule){
        double chiffreArrond;
        String nbC = "1";
        for (int i = 0; i < nbChiffreApresVirgule; i++) {
            nbC += "0";
        }
        Integer arrond = Integer.parseInt(nbC);
        chiffreArrond = (double)Math.round(chiffre*arrond) / arrond;
        return chiffreArrond;
    }


    public Double calcMalt() {
        return arrondir((vlBiere * dgralcool)  / 20,2);
    }
    public Double calcEauB() {
        return arrondir(calcMalt() * 2.8,2);
    }
    public Double calcEauR() {
        return arrondir((vlBiere * 1.25) - ( calcEauB() * 0.7 ), 2);
    }
    public Double calcHouAm() {
        return arrondir(vlBiere * 3,2);
    }
    public Double calchouAr() {
        return vlBiere;
    }
    public Double calcLevure() {
        return arrondir(vlBiere / 2,2);
    }

    public Double calcMcu() {
        return arrondir((4.23 * (moyEBC * calcMalt()) / vlBiere ),3);
    }
    public Double calcSrm() {
        return arrondir(calcEbcFromMcu() * 0.508,3);
    }
    public Double calcEbcFromSrm() {
        return arrondir(calcSrm() * 1.97, 3);
    }
    public Double calcEbcFromMcu() {
        return 2.9396 * Math.pow(calcMcu(), 0.6859);
    }

}
