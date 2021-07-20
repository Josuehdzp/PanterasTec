package com.Pericos.ITSVC.App;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MensajeDirector extends AppCompatActivity{

    ImageView imgDirec;
    Toolbar directoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mensaje_director_activity);

        directoolbar = findViewById(R.id.idMesjDirecToolbar);

        imgDirec = findViewById(R.id.idMsjDirectFotoDirec);
        Glide.with(this).load(R.drawable.director_msj).into(imgDirec);

        setSupportActionBar(directoolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

}
