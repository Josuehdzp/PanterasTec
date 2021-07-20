package com.Pericos.ITSVC.App;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.view.View;
import android.widget.ImageView;
import com.Pericos.ITSVC.App.Aspirantes.OfertaEducativaActivity;
import com.bumptech.glide.Glide;

public class NvoIngreso extends AppCompatActivity{


    CardView btnIsic,btnIial,btnIfor,btnIgeo,btnIgem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nvo_ingreso);

        btnIsic = findViewById(R.id.idNvoIngresoBtnIsic);
        btnIial = findViewById(R.id.idNvoIngresoBtnIial);
        btnIfor = findViewById(R.id.idNvoIngresoBtnIfor);
        btnIgeo = findViewById(R.id.idNvoIngresoBtnIgeo);
        btnIgem = findViewById(R.id.idNvoIngresoBtnIgem);

        ImageView imgPrimera = findViewById(R.id.imgPrimera);
        Glide.with(this).load(R.drawable.sliderconvocatoria).into(imgPrimera);
        imgPrimera.setImageDrawable(null);

        ImageView imgInsLogo = findViewById(R.id.inscripLogo);
        Glide.with(this).load(R.drawable.pericocontituloyfondo2sombra).into(imgInsLogo);
        imgInsLogo.setImageDrawable(null);

        ImageView imgOF1 = findViewById(R.id.idNvoIngresoImgIsic);
        Glide.with(this).load(R.drawable.isic_icon_blanco).into(imgOF1);
        imgOF1.setImageDrawable(null);

        ImageView imgOF2 = findViewById(R.id.idNvoIngresoImgIial);
        Glide.with(this).load(R.drawable.icono_iial_blanco).into(imgOF2);
        imgOF2.setImageDrawable(null);

        ImageView imgOF3 = findViewById(R.id.idNvoIngresoImgIfor);
        Glide.with(this).load(R.drawable.icon_ifor_blanco).into(imgOF3);
        imgOF3.setImageDrawable(null);

        ImageView imgOF4 = findViewById(R.id.idNvoIngresoImgIgeo);
        Glide.with(this).load(R.drawable.icon_igeo_blanco).into(imgOF4);
        imgOF4.setImageDrawable(null);

        ImageView imgOF5 = findViewById(R.id.idNvoIngresoImgIgem);
        Glide.with(this).load(R.drawable.icon_igem_blanco).into(imgOF5);
        imgOF5.setImageDrawable(null);

    }


public void Carreras(View chido){
    switch (chido.getId()) {

        case R.id.idNvoIngresoBtnIsic:
            Intent btnOSis = new Intent(NvoIngreso.this, OfertaEducativaActivity.class);
            startActivity(btnOSis);

            break;
        case R.id.idNvoIngresoBtnIial:
            Intent btnOFor = new Intent(NvoIngreso.this, OfertaEducativaActivity.class);
            startActivity(btnOFor);

            break;
        case R.id.idNvoIngresoBtnIfor:
            Intent btnOAli = new Intent(NvoIngreso.this, OfertaEducativaActivity.class);
            startActivity(btnOAli);

            break;
        case R.id.idNvoIngresoBtnIgeo:
            Intent btnOGeo = new Intent(NvoIngreso.this, OfertaEducativaActivity.class);
            startActivity(btnOGeo);

            break;
        case R.id.idNvoIngresoBtnIgem:
            Intent btnOGes = new Intent(NvoIngreso.this, OfertaEducativaActivity.class);
            startActivity(btnOGes);

            break;
        default:
            break;
    }
}

}
