package com.Pericos.ITSVC.App.MainActivity;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.Pericos.ITSVC.App.Estudiantes;
import com.Pericos.ITSVC.App.Horarios.DatabaseHelper;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.Pericos.ITSVC.App.Aspirantes.OfertaEducativaActivity;
import com.Pericos.ITSVC.App.Chat.Activity.LoginActivity;
import com.Pericos.ITSVC.App.Feeds.FeedMainRecycler;
import com.Pericos.ITSVC.App.MensajeDirector;
import com.Pericos.ITSVC.App.PantallaMain.ContactoActivity;
import com.Pericos.ITSVC.App.PantallaMain.PortalwebActivity;
import com.Pericos.ITSVC.App.PantallaMain.Departamentos;
import com.Pericos.ITSVC.App.R;

import com.Pericos.ITSVC.App.ViewPager_Activity;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private View btniniciarsecion;
    private View btncontacto;
    DatabaseHelper myDbHelper;

    ViewPager viewPager;




    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_edit);
/////////////////////////////////// SnackBar Inicial /////////////////////////////////////////////
        //View parentLayout = findViewById(android.R.id.content);
        //Snackbar.make(findViewById(R.id.layoutMain), "Hola!!! Bienvenido", Snackbar.LENGTH_LONG).show();
////////////////////////////TEPORIZADOR DEL VIEWPAGER////////////////////////////////////////

        viewPager = findViewById(R.id.viewPager);
        ViewPager_Activity viewPager_activity = new ViewPager_Activity(this);
        viewPager.setAdapter(viewPager_activity);

        ImageView imageView = new ImageView (MainActivity.this);
        imageView.setScaleType (ImageView.ScaleType.FIT_CENTER);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(),2000,4000);



////////////////////////////linea para la flecha de regreso del Action bar///////////////////////////////
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);



///////////Partes del boton //////////////////////////////////
        btniniciarsecion = findViewById(R.id.imageButtonTecnologico);
        btniniciarsecion.setOnClickListener(this);

        btncontacto = findViewById(R.id.imageButtonAvisos);
        btncontacto.setOnClickListener(this);



      /////////////////////////////////////Carga de imagenes///////////////////////////////////////////

        /*ImageView imgFONDOPERICOS = findViewById(R.id.imageViewFONDOPERICOS);
        Glide.with(this).load(R.drawable.fondopericosjpeg).into(imgFONDOPERICOS);
        imgFONDOPERICOS.setImageDrawable(null);*/


        /////////////////////////////////////Cargar base de datos de horario desde el inicio///////////////////////////////////////////
        myDbHelper = new DatabaseHelper(this, "HorarioDos.db", 1);
        try {
            myDbHelper.CheckDb();
        }catch (Exception e){}
    }
    ////////////////////////////////////////////////Eventos botones facebook ////////////////////////////////////////////////
//    private void goLoginScreen() {
//        Intent intent = new Intent(this, RedessocialesActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
//
//    }

//    public void logout(View view){
//        LoginManager.getInstance().logOut();
//        goLoginScreen();
//    }
    /////////////////////////////////////////// Parte ViewPager de la pagina principal ////////////////////////////////////////////////

    public class MyTimerTask extends TimerTask{


        @Override
        public void run() {

            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if(viewPager.getCurrentItem()==0){
                        viewPager.setCurrentItem(1);
                    } else if (viewPager.getCurrentItem() ==1){
                        viewPager.setCurrentItem(2);
                    } else if (viewPager.getCurrentItem() ==2){
                        viewPager.setCurrentItem(3);
                    } else if (viewPager.getCurrentItem() ==3){
                        viewPager.setCurrentItem(4);
                    } else if (viewPager.getCurrentItem() ==4){
                        viewPager.setCurrentItem(5);
                    } else if (viewPager.getCurrentItem() ==5){
                        viewPager.setCurrentItem(6);
                    }  else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });

        }
    }

    ///////////////////////////Funciones del action bar: Los tres puntitos//////////////////////////////////////////////
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_action_bar, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_search:
//                openSearch();
//                return true;
//            case R.id.action_settings:
//                openSettings();
//                return true;
//            default:
//
//
//                return super.onOptionsItemSelected(item);
//        }
//    }

//    private void openSettings() {
//    }
//
//    private void openSearch() {
//    }

/////////////////////////////metodo para tener mas de un click ///////////////////////////////////////

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.imageButtonTecnologico:
                Intent Tecnologico = new Intent(MainActivity.this, MensajeDirector.class);
                startActivity(Tecnologico);

                break;

            case R.id.imageButtonOfertaEducativa:

                Intent OfertaEducativa =  new Intent(MainActivity.this, OfertaEducativaActivity.class);
                startActivity(OfertaEducativa);

                break;

            case R.id.imageButtonAvisos:

                Intent Avisos =  new Intent(MainActivity.this, FeedMainRecycler.class);
                startActivity(Avisos);

                break;


            case R.id.imageButtonAcerca:

                Intent Acerca =  new Intent(MainActivity.this, LoginActivity.class);
                startActivity(Acerca);

                break;
            case R.id.imageButtonVinculacion:

                Intent Vinculacion =  new Intent(MainActivity.this, Departamentos.class);
                startActivity(Vinculacion);

                break;

            case R.id.imageButtonEstudiantes:

                Intent Estudiantes =  new Intent(MainActivity.this, Estudiantes.class);
                startActivity(Estudiantes);

                break;


            case R.id.imageButtonContacto:


                Intent Contacto =  new Intent(MainActivity.this, ContactoActivity.class);
                startActivity(Contacto);

                break;

            case R.id.imageButtonPortalweb:

                Intent PortalWeb =  new Intent(MainActivity.this, PortalwebActivity.class);
                startActivity(PortalWeb);
//                Uri uriUrl = Uri.parse("http://www.itsh.edu.mx/index.html");
//                Intent Portal_web = new Intent(Intent.ACTION_VIEW, uriUrl);
//                startActivity(Portal_web);

                break;

                default:
                break;
        }

    }
}
