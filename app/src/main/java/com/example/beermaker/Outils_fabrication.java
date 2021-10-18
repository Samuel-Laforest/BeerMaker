package com.example.beermaker;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class Outils_fabrication extends AppCompatActivity {

    private Calculs calculs;
    private AccesLocal accesLocal;
    private RecyclerView listeBdd;

    EditText saisie1;
    EditText saisie2;
    EditText saisie3;
    TextView resultat1;
    TextView resultat2;
    TextView resultat3;
    TextView resultat4;
    TextView resultat5;
    TextView resultat6;
    TextView resultat7;
    TextView resultat8;
    TextView resultat9;
    TextView resultat10;
    TextView fondcouleur;
    LinearLayout blockresultat;
    Button enregistrer;
    Button lancercalcul;
    String serializable = "serializable";

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.outils_fabrication);
        init();
        lancercalcul();
        checkSerialize();
        enregistrer();
    }

    private void init(){
        saisie1 = findViewById(R.id.saisie_1);
        saisie2 = findViewById(R.id.saisie_2);
        saisie3 = findViewById(R.id.saisie_3);
        resultat1 = findViewById(R.id.résultat1);
        resultat2 = findViewById(R.id.résultat2);
        resultat3 = findViewById(R.id.résultat3);
        resultat4 = findViewById(R.id.résultat4);
        resultat5 = findViewById(R.id.résultat5);
        resultat6 = findViewById(R.id.résultat6);
        resultat7 = findViewById(R.id.résultat7);
        resultat8 = findViewById(R.id.résultat8);
        resultat9 = findViewById(R.id.résultat9);
        resultat10 = findViewById(R.id.résultat10);
        fondcouleur = findViewById(R.id.textColor);
        blockresultat = findViewById(R.id.blockrésultat);
        enregistrer = findViewById(R.id.boutonEnregistrer);
        lancercalcul = findViewById(R.id.boutoncalcul);
        accesLocal = new AccesLocal(this);
        listeBdd = findViewById(R.id.listerecette);
    }

    private void résultat(){
        resultat1.setText("Quantité de Malt "+calculs.calcMalt()+" kg");
        resultat2.setText("Quantité Eau Brassage: "+calculs.calcEauB()+" L");
        resultat3.setText("Quantité Eau Rinçage: "+calculs.calcEauR()+" L");
        resultat4.setText("Quantité Houblon Amerisant: "+calculs.calcHouAm()+" g");
        resultat5.setText("Quantité Houblon Aromatique: "+calculs.calchouAr()+" g");
        resultat6.setText("Quantité levure: "+calculs.calcLevure()+" kg");
        resultat7.setText("Colorimétrie");
        resultat8.setText("MCU = "+calculs.calcMcu());
        resultat9.setText("EBC = "+calculs.calcEbcFromSrm());
        resultat10.setText("SRM = "+calculs.calcSrm());

        fondcouleur.setBackgroundColor(Color.parseColor(srmToRGB(calculs.calcSrm())));
        fondcouleur.setText(srmToRGB(calculs.calcSrm()));
        blockresultat.setVisibility(View.VISIBLE);
        enregistrer.setVisibility(View.VISIBLE);

    }

    private void calculer(){


        Double volume = Double.parseDouble(saisie1.getText().toString());
        Double degre = Double.parseDouble(saisie2.getText().toString());
        Double moyenneEBC = Double.parseDouble(saisie3.getText().toString());

        calculs = new Calculs(volume, degre, moyenneEBC);

        saisie1.setText(calculs.getVlBiere().toString());
        saisie2.setText(calculs.getDgralcool().toString());
        saisie3.setText(calculs.getMoyEBC().toString());

        résultat();

        Serializer.serialize(serializable, calculs, Outils_fabrication.this);

    }

    private void lancercalcul(){
        lancercalcul.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculer();
            }
        });
    }

    private void enregistrer(){
        enregistrer.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                accesLocal.ajout(calculs.getVlBiere(), calculs.getDgralcool(), calculs.getMoyEBC());
                Toast.makeText(Outils_fabrication.this, "Saisie Enregistrée", Toast.LENGTH_LONG).show();

            }
        });
    }


    private void recupSerialize(Context contexte){
        calculs = (Calculs) Serializer.deserialize(serializable, contexte);
    }
    private void checkSerialize(){
        try{
            recupSerialize(this);
            ((TextView) findViewById(R.id.saisie_1)).setText("" + calculs.getVlBiere());
            ((TextView) findViewById(R.id.saisie_2)).setText("" + calculs.getDgralcool());
            ((TextView) findViewById(R.id.saisie_3)).setText("" + calculs.getMoyEBC());
            // findViewById(R.id.btn_save).performClick();
        }catch (Exception e){};
    }

    private String srmToRGB(double srm) {
// Returns an RGB value based on SRM
        Double r, g, b;
        r= g= b= (double) 0;
        if (srm>=0 && srm<=1) {
            r = (double) 240; g = (double) 239; b = (double) 181;
        } else if (srm>1 && srm<=2) {
            r = (double) 233; g = (double) 215; b = (double) 108;
        } else if (srm>2) {
// Set red decimal
            if (srm<70.6843) { r = 243.8327-(6.4040*srm)+(0.0453*srm*srm);
            } else { r = 17.5014; }
// Set green decimal
            if (srm<35.0674) { g = 230.929-12.484*srm+0.178*srm*srm;
            } else { g = 12.0382; }
// Set blue decimal
            if (srm<4) { b = (double) -54*srm+216;
            } else if (srm>=4 && srm<7) { b = (double) 0;
            } else if (srm>=7 && srm<9) { b = (double) 13*srm-91;
            } else if (srm>=9 && srm<13) { b = (double) 2*srm+8;
            } else if (srm>=13 && srm<17) { b = -1.5*srm+53.5;
            } else if (srm>=17 && srm<22) { b = 0.6*srm+17.8;
            } else if (srm>=22 && srm<27) { b = -2.2*srm+79.4;
            } else if (srm>=27 && srm<34) { b = -0.4285*srm + 31.5714;
            } else { b = (double) 17; }
        }
        Integer red = r.intValue(); Integer green = g.intValue(); Integer blue = b.intValue();
        String rr = red.toHexString(red);
        String gg = green.toHexString(green);
        String bb = blue.toHexString(blue);
        String rgb = "#";
        if (rr.length()<2){ rr="0"+rr;
        }else if (gg.length()<2){ gg="0"+gg;
        }else if (bb.length()<2){ bb="0"+bb; }
        rgb = rgb+rr+gg+bb;
        return rgb;
    }

}
