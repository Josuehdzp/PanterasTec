package com.Pericos.ITSVC.App.PantallaMain;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.Pericos.ITSVC.App.R;

public class Departamentos extends AppCompatActivity {

    CardView cardServicios, cardVinculacion, cardProcesos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.departamentos_activity);
        //setSupportActionBar(toolbar);

        cardServicios = findViewById(R.id.cardDptoServicios);
        cardVinculacion = findViewById(R.id.cardDptoVinculacion);
        cardProcesos = findViewById(R.id.cardDptoProcesos);

        cardServicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cardVinculacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cardProcesos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
