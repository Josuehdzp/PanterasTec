package com.Pericos.ITSVC.App;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Pericos.ITSVC.App.Chat.Activity.LoginActivity;
import com.Pericos.ITSVC.App.Chat.Entidades.Firebase.Usuario;
import com.Pericos.ITSVC.App.Horarios.DatabaseHelper;
import com.Pericos.ITSVC.App.Horarios.HorarioPersonalizado;
import com.Pericos.ITSVC.App.Notas.NotasMain;
import com.bumptech.glide.Glide;
import com.danimahardhika.cafebar.CafeBar;
import com.danimahardhika.cafebar.CafeBarGravity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import de.hdodenhof.circleimageview.CircleImageView;


public class EstudiantesPerfil extends AppCompatActivity {

    DatabaseHelper myDbHelper;

    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private String NOMBRE_USUARIO;
    private String CARRERA_USUARIO;
    private String CORREO_USUARIO;
    private String MATRICULA_USUARIO;
    private String GENERO_USUARIO;
    private String FECHA_USUARIO;

    private String SISTEMAS = "Ing. Sistemas Computacionales";
    private String ALIMENTARIAS = "Ing. Industias Alimentarias";
    private String FORESTAL = "Ing. Forestal";
    private String GEOCIENCIAS = "Ing. Geociencias";
    private String GESTION = "Ing. Gestión Empresarial";



    Toolbar estudiantestoolbar;
    CardView cerrarSesion;
    ImageView headerImagen;
    TextView nombreUsuario;
    TextView correoUsuario;
    TextView matriculaUsuario;
    TextView carreraUsuario;
    TextView generoUsuario;
    ImageView iconoCarrera;

    CircleImageView fotoUsuario;

    @SuppressLint("ObsoleteSdkInt")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.estudiantes_activity_dos);

        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();

        //Copiar la Base de datos interna
        myDbHelper = new DatabaseHelper(this, "HorarioDos.db", 1);
        try {
            myDbHelper.CheckDb();
        }catch (Exception e){}
        //////////////////

        estudiantestoolbar = findViewById(R.id.idPerfilEstudiantesToolbar);
        headerImagen = findViewById(R.id.idPerfilEstudiantesFotoHeader);

        cerrarSesion = findViewById(R.id.idPerfilEstudiantescardCerrarSesion);

        nombreUsuario = findViewById(R.id.idPerfilEstudiantesNombreUsuario);
        correoUsuario = findViewById(R.id.idPerfilEstudiantesCorreoUsuario);
        matriculaUsuario= findViewById(R.id.idPerfilEstudiantesMatriculaUsuario);
        fotoUsuario = findViewById(R.id.idPerfilEstudiantesFotoUsuario);
        carreraUsuario = findViewById(R.id.idPerfilEstudiantesCarreraUsuario);
        generoUsuario= findViewById(R.id.idPerfilEstudiantesGeneroUsuario);

        iconoCarrera = findViewById(R.id.idPerfilEstudiantesIconoCarreraUsuario);

        setSupportActionBar(estudiantestoolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));


        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                returnLogin();
            }
        });


/*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
*/

        verifyStoragePermissions(this);
    }

    public static boolean verifyStoragePermissions(AppCompatActivity activity) {
        String[] PERMISSIONS_STORAGE = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        int REQUEST_EXTERNAL_STORAGE = 1;
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
            return false;
        }else{
            return true;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStart() {
        super.onStart();

        try {

            FirebaseUser currentUser = mAuth.getCurrentUser();
            if(currentUser!=null){
                DatabaseReference reference = database.getReference("Usuarios/"+currentUser.getUid());
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Usuario usuario = dataSnapshot.getValue(Usuario.class);
                        Usuario correo = dataSnapshot.getValue(Usuario.class);
                        Usuario matricula = dataSnapshot.getValue(Usuario.class);
                        Usuario carrera = dataSnapshot.getValue(Usuario.class);
                        Usuario perfil = dataSnapshot.getValue(Usuario.class);
                        Usuario genero = dataSnapshot.getValue(Usuario.class);

                        assert usuario != null;
                        NOMBRE_USUARIO = usuario.getNombre();
                        nombreUsuario.setText(NOMBRE_USUARIO);
                        estudiantestoolbar.setTitle(NOMBRE_USUARIO);
                        nombreUsuario.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_transition_animation_up));


                        CafeBar.builder(EstudiantesPerfil.this)
                                .icon(R.drawable.icono_escencial_usuario)
                                .gravity(CafeBarGravity.CENTER)
                                .content("Hola! "+NOMBRE_USUARIO)
                                .floating(true)
                                .duration(CafeBar.Duration.MEDIUM)
                                .neutralColor(getResources().getColor(R.color.negroprincipal))
                                .show();

                        assert correo != null;
                        CORREO_USUARIO = usuario.getCorreo();
                        correoUsuario.setText(CORREO_USUARIO);
                        correoUsuario.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_transition_animation_up));


                        assert matricula != null;
                        MATRICULA_USUARIO = usuario.getMatricula();
                        matriculaUsuario.setText(MATRICULA_USUARIO);
                        matriculaUsuario.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_transition_animation_up));


                        assert genero != null;
                        GENERO_USUARIO = usuario.getGenero();
                        generoUsuario.setText(GENERO_USUARIO);
                        generoUsuario.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_transition_animation_up));


                        assert carrera != null;
                        CARRERA_USUARIO = carrera.getCarrera();

                        if (CARRERA_USUARIO.equals("ISIC")){
                            carreraUsuario.setText(SISTEMAS);
                            Glide.with(getApplicationContext()).load(R.drawable.sistemas).into(iconoCarrera);
                            iconoCarrera.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in));


                        }

                        else if (CARRERA_USUARIO.equals("IIAL")){
                            carreraUsuario.setText(ALIMENTARIAS);
                            Glide.with(getApplicationContext()).load(R.drawable.alimentarias).into(iconoCarrera);
                            iconoCarrera.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in));

                        }

                        else if (CARRERA_USUARIO.equals("IFOR")){
                            carreraUsuario.setText(FORESTAL);
                            Glide.with(getApplicationContext()).load(R.drawable.forestal).into(iconoCarrera);
                            iconoCarrera.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in));

                        }

                        else if (CARRERA_USUARIO.equals("IGEO")){
                            carreraUsuario.setText(GEOCIENCIAS);
                            Glide.with(getApplicationContext()).load(R.drawable.geocienciaslogo).into(iconoCarrera);
                            iconoCarrera.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in));

                        }

                        else if (CARRERA_USUARIO.equals("IGEM")){
                            carreraUsuario.setText(GESTION);
                            Glide.with(getApplicationContext()).load(R.drawable.gestionlogo).into(iconoCarrera);
                            iconoCarrera.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in));

                        }

                        else {
                            carreraUsuario.setText(CARRERA_USUARIO);
                            Glide.with(getApplicationContext()).load(R.drawable.itsvclogo).into(iconoCarrera);
                            iconoCarrera.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in));
                        }

                        assert perfil != null;
                        Glide.with(getApplicationContext()).load(perfil.getFotoPerfilURL()).into(fotoUsuario);
                        Glide.with(getApplicationContext()).load(perfil.getFotoPerfilURL()).into(headerImagen);
                        fotoUsuario.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in));
                        headerImagen.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in));


                        //carreraUsuario.setText(CARRERA_USUARIO);

                        //Snackbar.make(findViewById(R.id.linearMensajeria), CARRERA.toString(), Snackbar.LENGTH_LONG).show();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }else{
                returnLogin();
            }

        } catch (Exception error) {
            Toast.makeText(this, "Error: "+ error, Toast.LENGTH_SHORT).show();
        }
    }

    private void returnLogin(){
        startActivity(new Intent(EstudiantesPerfil.this, LoginActivity.class));
        finish();
    }


}
