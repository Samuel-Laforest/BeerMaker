package com.example.beermaker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Etape_fabrication();
        Outils_fabrication();
        Vos_recettes();
    }
    private void Etape_fabrication(){
        findViewById(R.id.button01).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, Etape_fabrication.class);
                startActivity(intent);
            }
        });
    }
    private void Outils_fabrication() {
        findViewById(R.id.button02).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Outils_fabrication.class);
                startActivity(intent);
            }
        });
    }
    private void Vos_recettes() {
        findViewById(R.id.button03).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Vos_recettes.class);
                startActivity(intent);
            }
        });
    }
}
