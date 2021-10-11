package com.example.beermaker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Vos_recettes extends AppCompatActivity {

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.vos_recettes);
        ecouteBtnClose();
    }

    private void ecouteBtnClose(){
        findViewById(R.id.button04).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });
    }
}
