package com.Pericos.ITSVC.App.Chat.Activity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Pericos.ITSVC.App.Chat.Entidades.Firebase.Usuario;
import com.Pericos.ITSVC.App.Chat.Persistencia.UsuarioDAO;
import com.Pericos.ITSVC.App.Chat.Utilidades.Constantes;
import com.Pericos.ITSVC.App.R;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kbeanie.multipicker.api.CacheLocation;
import com.kbeanie.multipicker.api.CameraImagePicker;
import com.kbeanie.multipicker.api.ImagePicker;
import com.kbeanie.multipicker.api.Picker;
import com.kbeanie.multipicker.api.callbacks.ImagePickerCallback;
import com.kbeanie.multipicker.api.entity.ChosenImage;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import de.hdodenhof.circleimageview.CircleImageView;


public class RegistroActivityISIC extends AppCompatActivity {

    private Toolbar registroToolbar;
    private CircleImageView fotoPerfil;
    private FloatingActionButton fotoPerfilFAB;
    private EditText txtNombre;
    private EditText txtCorreo;
    private EditText txtContrase??a;
    private EditText txtContrase??aRepetida;
    private EditText txtFechaDeNacimiento;
    private EditText txtMatricula;
    private TextView txtCarrera;
    private ImageView iconoCarrera;
    private RadioButton rdHombre;
    private RadioButton rdMujer;
    private Spinner spinercarreras;
    private Button btnRegistrar;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;

    private ImagePicker imagePicker;
    private CameraImagePicker cameraPicker;

    private String pickerPath;
    private Uri fotoPerfilUri;
    private long fechaDeNacimiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity_registro_isic);

        registroToolbar = findViewById(R.id.idRegistroToolbar);
        fotoPerfil = findViewById(R.id.fotoPerfil);
        fotoPerfilFAB = findViewById(R.id.fotoPerfilFloating);
        txtNombre = findViewById(R.id.idRegistroNombre);
        txtCorreo = findViewById(R.id.idRegistroCorreo);
        txtContrase??a = findViewById(R.id.idRegistroContrase??a);
        txtContrase??aRepetida = findViewById(R.id.idRegistroContrase??aRepetida);
        txtFechaDeNacimiento = findViewById(R.id.txtFechaDeNacimiento);
        iconoCarrera = findViewById(R.id.idRegistroIconoCarrera);
        txtCarrera = findViewById(R.id.idRegistroCarrera);
        txtMatricula = findViewById(R.id.idRegistroMatricula);

        rdHombre = findViewById(R.id.rdHombre);
        rdMujer = findViewById(R.id.rdMujer);
        spinercarreras = findViewById(R.id.spinnerCarreras);
        btnRegistrar = findViewById(R.id.idRegistroRegistrar);

        setSupportActionBar(registroToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        imagePicker = new ImagePicker(this);
        cameraPicker = new CameraImagePicker(this);

        cameraPicker.setCacheLocation(CacheLocation.EXTERNAL_STORAGE_APP_DIR);
        imagePicker.setCacheLocation(CacheLocation.EXTERNAL_STORAGE_APP_DIR);


        imagePicker.setImagePickerCallback(new ImagePickerCallback() {
            @Override
            public void onImagesChosen(List<ChosenImage> list) {
                if(!list.isEmpty()){
                    String path = list.get(0).getOriginalPath();
                    fotoPerfilUri = Uri.fromFile(new File(path));
                    fotoPerfil.setImageURI(fotoPerfilUri);

                }
            }

            @Override
            public void onError(String s) {
                Toast.makeText(RegistroActivityISIC.this, "Error: "+s, Toast.LENGTH_SHORT).show();
            }
        });

        cameraPicker.setImagePickerCallback(new ImagePickerCallback() {
            @Override
            public void onImagesChosen(List<ChosenImage> list) {
                String path = list.get(0).getOriginalPath();
                fotoPerfilUri = Uri.fromFile(new File(path));
                fotoPerfil.setImageURI(fotoPerfilUri);

            }

            @Override
            public void onError(String s) {
                Toast.makeText(RegistroActivityISIC.this, "Error: "+s, Toast.LENGTH_SHORT).show();
            }
        });

        fotoPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(RegistroActivityISIC.this);
                dialog.setTitle("Elige una foto de perfil");

                String[] items = {"Galeria","Camara"};

                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                imagePicker.pickImage();
                                break;
                            case 1:
                                pickerPath = cameraPicker.pickImage();
                                break;
                        }
                    }
                });

                AlertDialog dialogConstruido = dialog.create();
                dialogConstruido.show();

            }
        });

        fotoPerfilFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(RegistroActivityISIC.this);
                dialog.setTitle("Elige una foto de perfil");

                String[] items = {"Galeria","Camara"};

                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                imagePicker.pickImage();
                                break;
                            case 1:
                                pickerPath = cameraPicker.pickImage();
                                break;
                        }
                    }
                });

                AlertDialog dialogConstruido = dialog.create();
                dialogConstruido.show();

            }
        });

        txtFechaDeNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(RegistroActivityISIC.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int mes, int dia) {
                        Calendar calendarResultado = Calendar.getInstance();
                        calendarResultado.set(Calendar.YEAR,year);
                        calendarResultado.set(Calendar.MONTH,mes);
                        calendarResultado.set(Calendar.DAY_OF_MONTH,dia);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        Date date = calendarResultado.getTime();
                        String fechaDeNacimientoTexto = simpleDateFormat.format(date);
                        fechaDeNacimiento = date.getTime();
                        txtFechaDeNacimiento.setText(fechaDeNacimientoTexto);
                    }
                },calendar.get(Calendar.YEAR)-19,calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });


        ArrayAdapter<CharSequence> adapterCarreras = ArrayAdapter
        .createFromResource(this, R.array.carreras, android.R.layout.simple_spinner_item);
        adapterCarreras.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinercarreras.setAdapter(adapterCarreras);

        spinercarreras.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                /*Toast.makeText(parent.getContext(),
                        "Seleccionaste: "+parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();*/

                txtCarrera.setText(parent.getItemAtPosition(position).toString());

                if (parent.getItemAtPosition(position).equals ("ISIC")){
                    Glide.with(getApplicationContext()).load(R.drawable.isic_icon_nice).into(iconoCarrera);
                }
                else if (parent.getItemAtPosition(position).equals("IIAL")){
                    Glide.with(getApplicationContext()).load(R.drawable.icono_iial_nice).into(iconoCarrera);
                }
                else if (parent.getItemAtPosition(position).equals("IFOR")){
                    Glide.with(getApplicationContext()).load(R.drawable.icon_ifor_nice).into(iconoCarrera);
                }
                else if (parent.getItemAtPosition(position).equals("IGEO")){
                    Glide.with(getApplicationContext()).load(R.drawable.icon_igeo_nice).into(iconoCarrera);
                }
                else {
                    Glide.with(getApplicationContext()).load(R.drawable.icon_igem_nice).into(iconoCarrera);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Glide.with(getApplicationContext()).load(R.drawable.icono_itsvc_nice).into(iconoCarrera);
            }
        });



        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog progressDialog = new ProgressDialog(RegistroActivityISIC.this);
                progressDialog.setIcon(R.mipmap.ic_launcher_round);
                progressDialog.setTitle("Espera un poco");
                progressDialog.setMessage("Registrando datos...");
                progressDialog.setCancelable(true);

                progressDialog.show();


                final String correo = txtCorreo.getText().toString();
                final String nombre = txtNombre.getText().toString();
                final String matricula = txtMatricula.getText().toString();
                final String carrera = txtCarrera.getText().toString();

                if(isValidEmail(correo) && validarContrase??a() && validarNombre(nombre) && validarMatricula(matricula) && validarCarrera(carrera) ){
                    String contrase??a = txtContrase??a.getText().toString();
                    mAuth.createUserWithEmailAndPassword(correo, contrase??a)
                            .addOnCompleteListener(RegistroActivityISIC.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        final String genero;

                                        if(rdHombre.isChecked()){
                                            genero = "Hombre";
                                        }else{
                                            genero = "Mujer";
                                        }

                                        if(fotoPerfilUri!=null) {

                                            UsuarioDAO.getInstancia().subirFotoUri(fotoPerfilUri, new UsuarioDAO.IDevolverUrlFoto() {
                                                @Override
                                                public void devolerUrlString(String url) {
                                                    progressDialog.dismiss();
                                                    Toast.makeText(RegistroActivityISIC.this, "Se registro correctamente.", Toast.LENGTH_SHORT).show();
                                                    Usuario usuario = new Usuario();
                                                    usuario.setCorreo(correo);
                                                    usuario.setNombre(nombre);
                                                    usuario.setMatricula(matricula);
                                                    usuario.setCarrera(carrera);
                                                    usuario.setFechaDeNacimiento(fechaDeNacimiento);
                                                    usuario.setGenero(genero);
                                                    usuario.setFotoPerfilURL(url);
                                                    FirebaseUser currentUser = mAuth.getCurrentUser();
                                                    DatabaseReference reference = database.getReference("Usuarios/"+currentUser.getUid());
                                                    reference.setValue(usuario);
                                                    finish();
                                                }
                                            });

                                        }else{
                                            progressDialog.dismiss();
                                            Toast.makeText(RegistroActivityISIC.this, "Se registro correctamente.", Toast.LENGTH_SHORT).show();
                                            Usuario usuario = new Usuario();
                                            usuario.setCorreo(correo);
                                            usuario.setNombre(nombre);
                                            usuario.setMatricula(matricula);
                                            usuario.setCarrera(carrera);
                                            usuario.setFechaDeNacimiento(fechaDeNacimiento);
                                            usuario.setGenero(genero);
                                            usuario.setFotoPerfilURL(Constantes.URL_FOTO_POR_DEFECTO_USUARIOS);
                                            FirebaseUser currentUser = mAuth.getCurrentUser();
                                            DatabaseReference reference = database.getReference("Usuarios/"+currentUser.getUid());
                                            reference.setValue(usuario);
                                            finish();
                                        }

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        progressDialog.dismiss();

                                        Toast.makeText(RegistroActivityISIC.this, "Error al registrarse, quizas este usuario ya existe.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(RegistroActivityISIC.this, "Ingresa los datos correctamente.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Glide.with(getApplicationContext()).load(Constantes.URL_FOTO_POR_DEFECTO_USUARIOS).into(fotoPerfil);

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Picker.PICK_IMAGE_DEVICE && resultCode == RESULT_OK){
            imagePicker.submit(data);
        }else if(requestCode == Picker.PICK_IMAGE_CAMERA && resultCode == RESULT_OK){
            cameraPicker.reinitialize(pickerPath);
            cameraPicker.submit(data);
        }
    }

    private boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public boolean validarContrase??a(){
        String contrase??a,contrase??aRepetida;
        contrase??a = txtContrase??a.getText().toString();
        contrase??aRepetida = txtContrase??aRepetida.getText().toString();
        if(contrase??a.equals(contrase??aRepetida)){
            if(contrase??a.length()>=6 && contrase??a.length()<=16){
                return true;
            }else return false;
        }else return false;
    }

    public boolean validarNombre(String nombre){
        return !nombre.isEmpty();
    }

    public boolean validarMatricula(String matricula){
        return !matricula.isEmpty();
    }

    public boolean validarCarrera(String carrera){
        return !carrera.isEmpty();
    }





    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // You have to save path in case your activity is killed.
        // In such a scenario, you will need to re-initialize the CameraImagePicker
        outState.putString("picker_path", pickerPath);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // After Activity recreate, you need to re-intialize these
        // two values to be able to re-intialize CameraImagePicker
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("picker_path")) {
                pickerPath = savedInstanceState.getString("picker_path");
            }
        }
        super.onRestoreInstanceState(savedInstanceState);
    }


}
