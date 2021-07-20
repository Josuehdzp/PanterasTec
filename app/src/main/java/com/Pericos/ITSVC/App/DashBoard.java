package com.Pericos.ITSVC.App;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.Pericos.ITSVC.App.Aspirantes.OfertaEducativaActivity;
import com.Pericos.ITSVC.App.Feeds.FeedMainRecycler;
import com.Pericos.ITSVC.App.Horarios.DatabaseHelper;
import com.Pericos.ITSVC.App.PantallaMain.AcercaDeActivity;
import com.Pericos.ITSVC.App.PantallaMain.ContactoActivity;
import com.Pericos.ITSVC.App.PantallaMain.PortalwebActivity;
import com.Pericos.ITSVC.App.PantallaMain.Departamentos;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DashBoard extends AppCompatActivity {

    DatabaseHelper myDbHelper;
    SliderView sliderView;
    LinearLayout btnTec, btnOferta, btnAvisos, btnEstudiantes, btnVinculacion, btnPortal, btnContacto, btnAcerca;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);

        sliderView = findViewById(R.id.dashimageSlider);

        btnTec = findViewById(R.id.dashbtnTecnologico);
        btnOferta = findViewById(R.id.dashbtnOfertaeducativa);
        btnAvisos = findViewById(R.id.dashbtnAvisos);
        btnEstudiantes = findViewById(R.id.dashbtnEstudiantes);
        btnVinculacion = findViewById(R.id.dashbtnVinculacion);
        btnPortal = findViewById(R.id.dashbtnPortalWeb);
        btnContacto = findViewById(R.id.dashbtnContacto);
        btnAcerca = findViewById(R.id.dashbtnAcercaDe);


        myDbHelper = new DatabaseHelper(this, "HorarioDos.db", 1);
        try {
            myDbHelper.CheckDb();
        }catch (Exception e){}

        final AdaptadorSlider adapter = new AdaptadorSlider(this);
        adapter.setCuenta(5);

        sliderView.setSliderAdapter(adapter);

        sliderView.setIndicatorAnimation(IndicatorAnimations.DROP); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.CUBEINDEPTHTRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(getResources().getColor(R.color.colorAccent));
        sliderView.setIndicatorUnselectedColor(getResources().getColor(R.color.colorPrimary));
        sliderView.setScrollTimeInSec(5); //set scroll delay in seconds :
        sliderView.startAutoCycle();

        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                sliderView.setCurrentPagePosition(position);
            }
        });


        btnTec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Tecnologico = new Intent(DashBoard.this, TecnologicoMenu.class);
                startActivity(Tecnologico);
            }
        });

        btnOferta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent OfertaEducativa =  new Intent(DashBoard.this, OfertaEducativaActivity.class);
                startActivity(OfertaEducativa);
            }
        });

        btnAvisos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Avisos = new Intent(DashBoard.this, FeedMainRecycler.class);
                startActivity(Avisos);
            }
        });

        btnEstudiantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Estudiantes = new Intent(DashBoard.this, Estudiantes.class);
                startActivity(Estudiantes);
            }
        });

        btnVinculacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Vinculacion = new Intent(DashBoard.this, Departamentos.class);
                startActivity(Vinculacion);
            }
        });

        btnPortal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Portal = new Intent(DashBoard.this, PortalwebActivity.class);
                startActivity(Portal);
            }
        });

        btnContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Contacto = new Intent(DashBoard.this, ContactoActivity.class);
                startActivity(Contacto);
            }
        });

        btnAcerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Acerca = new Intent(DashBoard.this, AcercaDeActivity.class);
                startActivity(Acerca);
            }
        });
    }
}
