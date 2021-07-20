package com.Pericos.ITSVC.App;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.Pericos.ITSVC.App.Aspirantes.OfertaEducativaActivity;
import com.Pericos.ITSVC.App.Chat.Entidades.Firebase.Mensaje;
import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


public class TecnologicoMenu extends AppCompatActivity {

    CardView cardNosotros, cardMsjDirec, cardNvoIngrs, cardHistoria, cardCert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tecnologico_menu);

        cardNosotros = findViewById(R.id.cardTecNosotros);
        cardMsjDirec = findViewById(R.id.cardTecMsjDirector);
        cardNvoIngrs = findViewById(R.id.cardTecNvoIngreso);
        cardHistoria = findViewById(R.id.cardTecHistoria);
        cardCert = findViewById(R.id.cardTecCertificaciones);


        RoundedImageView imgnstr = findViewById(R.id.imgTecNosotros);
        Glide.with(this).load(R.drawable.edificio).into(imgnstr);
        imgnstr.setImageDrawable(null);

        RoundedImageView imgmsj = findViewById(R.id.imgTecMsjDirector);
        Glide.with(this).load(R.drawable.slidersistemas).into(imgmsj);
        imgmsj.setImageDrawable(null);

        RoundedImageView imgnvo = findViewById(R.id.imgTecHistoria);
        Glide.with(this).load(R.drawable.sliderforestal).into(imgnvo);
        imgnvo.setImageDrawable(null);

        RoundedImageView imgbecas = findViewById(R.id.imgTecBecas);
        Glide.with(this).load(R.drawable.becas_img).into(imgbecas);
        imgbecas.setImageDrawable(null);

        RoundedImageView imgcer = findViewById(R.id.imgTecCertificaciones);
        Glide.with(this).load(R.drawable.slidergeociencias).into(imgcer);
        imgcer.setImageDrawable(null);

        cardNosotros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nosotros = new Intent(TecnologicoMenu.this, Nosotros.class);
                startActivity(nosotros);
            }
        });

        cardMsjDirec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent msj = new Intent(TecnologicoMenu.this, MensajeDirector.class);
                startActivity(msj);
            }
        });

        cardNvoIngrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nvo = new Intent(TecnologicoMenu.this, NvoIngreso.class);
                startActivity(nvo);
            }
        });

        cardHistoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent becas = new Intent(TecnologicoMenu.this, Historia.class);
                startActivity(becas);
            }
        });

        cardCert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cert = new Intent(TecnologicoMenu.this, EstudiantesPerfil.class);
                startActivity(cert);
            }
        });

    }


}
