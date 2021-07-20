package com.Pericos.ITSVC.App.Chat.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Pericos.ITSVC.App.Chat.Adaptadores.MensajeriaAdaptador;
import com.Pericos.ITSVC.App.Chat.Entidades.Firebase.Mensaje;
import com.Pericos.ITSVC.App.Chat.Entidades.Firebase.Usuario;
import com.Pericos.ITSVC.App.Chat.Entidades.Logica.LMensaje;
import com.Pericos.ITSVC.App.Chat.Entidades.Logica.LUsuario;
import com.Pericos.ITSVC.App.Chat.Persistencia.UsuarioDAO;
import com.Pericos.ITSVC.App.Chat.Utilidades.Constantes;
import com.Pericos.ITSVC.App.R;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.kbeanie.multipicker.api.CameraImagePicker;
import com.kbeanie.multipicker.api.ImagePicker;
import com.kbeanie.multipicker.api.Picker;
import com.kbeanie.multipicker.api.callbacks.ImagePickerCallback;
import com.kbeanie.multipicker.api.entity.ChosenImage;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class MensajeriaActivityIGEM extends AppCompatActivity {

    private CircleImageView fotoPerfil;
    private Button btnCambiar;
    private TextView nombre;
    private RecyclerView rvMensajes;
    private EditText txtMensaje;
    private FloatingActionButton btnEnviar;
    private FloatingActionButton cerrarSesion;
    private MensajeriaAdaptador adapter;
    private ImageButton btnEnviarFoto;
    private ImageView restriccion;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private static final int PHOTO_SEND = 1;
    private static final int PHOTO_PERFIL = 2;
    private String fotoPerfilCadena;
    private Uri fotoPerfilUri;

    private ImagePicker imagePicker;
    private CameraImagePicker cameraPicker;

    private String pickerPath;
    private long fechaDeNacimiento;


    private FirebaseAuth mAuth;
    private String NOMBRE_USUARIO;
    private DatabaseReference CARRERA;
    private String CARRERITAS;

    public String uNombre;
    public String uCorreo;
    public String uMatricula;
    public String uCarrera;
    public long uFecha ;
    public String uGenero;
    public String uFotoPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity_mensajeria);

        fotoPerfil = findViewById(R.id.fotoPerfil);
        btnCambiar = findViewById(R.id.idbotonCambiar);
        nombre = findViewById(R.id.nombre);
        rvMensajes = findViewById(R.id.rvMensajes);
        txtMensaje = findViewById(R.id.txtMensaje);
        btnEnviar = findViewById(R.id.btnEnviar);
        btnEnviarFoto = findViewById(R.id.btnEnviarFoto);
        cerrarSesion = findViewById(R.id.cerrarSesion);
        restriccion = findViewById(R.id.imgRestrccion);
        fotoPerfilCadena = "";

        restriccion.setVisibility(View.GONE);

        imagePicker = new ImagePicker(this);
        cameraPicker = new CameraImagePicker(this);

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
                Toast.makeText(MensajeriaActivityIGEM.this, "Error: "+s, Toast.LENGTH_SHORT).show();
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
                Toast.makeText(MensajeriaActivityIGEM.this, "Error: "+s, Toast.LENGTH_SHORT).show();
            }
        });

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference(Constantes.NODO_MENSAJES_GESTION);//Sala de chat (nombre) version 2
        storage = FirebaseStorage.getInstance();
        mAuth = FirebaseAuth.getInstance();

        adapter = new MensajeriaAdaptador(this);
        LinearLayoutManager l = new LinearLayoutManager(this);
        rvMensajes.setLayoutManager(l);
        rvMensajes.setAdapter(adapter);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mensajeEnviar = txtMensaje.getText().toString();
                if(!mensajeEnviar.isEmpty()){
                    Mensaje mensaje = new Mensaje();
                    mensaje.setMensaje(mensajeEnviar);
                    mensaje.setContieneFoto(false);
                    mensaje.setKeyEmisor(UsuarioDAO.getInstancia().getKeyUsuario());
                    databaseReference.push().setValue(mensaje);
                    txtMensaje.setText("");
                }
            }
        });

        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                returnLogin();
            }
        });

        btnEnviarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/jpeg");
                i.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                startActivityForResult(Intent.createChooser(i,"Selecciona una foto"),PHOTO_SEND);
            }
        });

        fotoPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnCambiar.setVisibility(View.VISIBLE);

                AlertDialog.Builder dialog = new AlertDialog.Builder(MensajeriaActivityIGEM.this);
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

        btnCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subirPerfilFoto();
                btnCambiar.setVisibility(View.GONE);
            }
        });


        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                setScrollbar();
            }
        });

        databaseReference.addChildEventListener(new ChildEventListener() {

            //traer la informacion del usuario
            //guardamos la informacion del usuario en una lista temporal
            //obtenemos la informacion guardada  por la llave
            Map<String, LUsuario> mapUsuariosTemporales = new HashMap<>();

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                final Mensaje mensaje = dataSnapshot.getValue(Mensaje.class);
                final LMensaje lMensaje = new LMensaje(dataSnapshot.getKey(),mensaje);


                final int posicion = adapter.addMensaje(lMensaje);

                if(mapUsuariosTemporales.get(mensaje.getKeyEmisor())!=null){
                    lMensaje.setlUsuario(mapUsuariosTemporales.get(mensaje.getKeyEmisor()));
                    adapter.actualizarMensaje(posicion,lMensaje);
                }else{
                    UsuarioDAO.getInstancia().obtenerInformacionDeUsuarioPorLLave(mensaje.getKeyEmisor(), new UsuarioDAO.IDevolverUsuario() {
                        @Override
                        public void devolverUsuario(LUsuario lUsuario) {
                            mapUsuariosTemporales.put(mensaje.getKeyEmisor(),lUsuario);
                            lMensaje.setlUsuario(lUsuario);
                            adapter.actualizarMensaje(posicion,lMensaje);
                        }

                        @Override
                        public void devolverError(String error) {
                            Toast.makeText(MensajeriaActivityIGEM.this, "Error: "+error, Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        verifyStoragePermissions(this);

    }

    private void setScrollbar(){
        rvMensajes.scrollToPosition(adapter.getItemCount()-1);
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
        if(requestCode == PHOTO_SEND && resultCode == RESULT_OK){
            Uri u = data.getData();
            storageReference = storage.getReference("imagenes_chat_"+Constantes.NODO_MENSAJES_GESTION);//imagenes_chat
            final StorageReference fotoReferencia = storageReference.child(u.getLastPathSegment());
            fotoReferencia.putFile(u).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if(!task.isSuccessful()){
                        throw task.getException();
                    }
                    return fotoReferencia.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if(task.isSuccessful()){
                        Uri uri = task.getResult();
                        Mensaje mensaje = new Mensaje();
                        mensaje.setMensaje(NOMBRE_USUARIO+" ha enviado una foto");
                        mensaje.setUrlFoto(uri.toString());
                        mensaje.setContieneFoto(true);
                        mensaje.setKeyEmisor(UsuarioDAO.getInstancia().getKeyUsuario());
                        databaseReference.push().setValue(mensaje);
                    }
                }
            });
        } else {

        }
//////////////

        if(requestCode == Picker.PICK_IMAGE_DEVICE && resultCode == RESULT_OK){
            imagePicker.submit(data);
        }else if(requestCode == Picker.PICK_IMAGE_CAMERA && resultCode == RESULT_OK){
            cameraPicker.reinitialize(pickerPath);
            cameraPicker.submit(data);
        }




    }


    private void subirPerfilFoto() {

        if (fotoPerfilUri != null) {

            UsuarioDAO.getInstancia().subirFotoUri(fotoPerfilUri, new UsuarioDAO.IDevolverUrlFoto() {
                @Override
                public void devolerUrlString(String url) {
                    Usuario usuario = new Usuario();

                    usuario.setNombre(uNombre);
                    usuario.setCorreo(uCorreo);
                    usuario.setCarrera(uCarrera);
                    usuario.setFechaDeNacimiento(uFecha);
                    usuario.setGenero(uGenero);
                    usuario.setMatricula(uMatricula);

                    usuario.setFotoPerfilURL(url);
                    FirebaseUser currentUser = mAuth.getCurrentUser();
                    DatabaseReference reference = database.getReference("Usuarios/" + currentUser.getUid());
                    reference.setValue(usuario);
                }
            });
        } else{
            Usuario usuario = new Usuario();

            usuario.setNombre(uNombre);
            usuario.setCorreo(uCorreo);
            usuario.setCarrera(uCarrera);
            usuario.setFechaDeNacimiento(uFecha);
            usuario.setGenero(uGenero);
            usuario.setMatricula(uMatricula);
            usuario.setFotoPerfilURL(uFotoPerfil);
            FirebaseUser currentUser = mAuth.getCurrentUser();
            DatabaseReference reference = database.getReference("Usuarios/" + currentUser.getUid());
            reference.setValue(usuario);


        }



    }
    public String getKeyUsuario(){
        return FirebaseAuth.getInstance().getUid();
    }

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            btnEnviar.setEnabled(false);
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
                    Usuario fecha = dataSnapshot.getValue(Usuario.class);


                    uNombre = usuario.getNombre();
                    uCorreo = correo.getCorreo();
                    uMatricula = matricula.getMatricula();
                    uCarrera = usuario.getCarrera();
                    uGenero = genero.getGenero();
                    uFecha = fecha.getFechaDeNacimiento();
                    uFotoPerfil = perfil.getFotoPerfilURL();


                    assert usuario != null;
                    NOMBRE_USUARIO = usuario.getNombre();
                    nombre.setText(NOMBRE_USUARIO);

                    assert perfil != null;
                    Glide.with(getApplicationContext()).load(perfil.getFotoPerfilURL()).into(fotoPerfil);

                    assert carrera != null;
                    CARRERA = database.getReference("Mensajes/"+carrera.getCarrera());
                    CARRERITAS = carrera.getCarrera();
                    //Snackbar.make(findViewById(R.id.linearMensajeria), CARRERA.toString(), Snackbar.LENGTH_LONG).show();

                    btnEnviar.setEnabled(true);

                    if (CARRERITAS.equals("IGEM")){

                        Snackbar.make(findViewById(R.id.linearMensajeria), "Hola, bienvenido al chat para IGEM :D", Snackbar.LENGTH_LONG)
                                .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                                .setAction("Di hola", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        txtMensaje.setText("Hey hola");
                                    }
                                })
                                .show();
                    }else{
                        Snackbar.make(findViewById(R.id.linearMensajeria), "Lo siento, no puedes ver mensajes de otra carrera :c", Snackbar.LENGTH_LONG)
                                .setActionTextColor(getResources().getColor(R.color.rojo))
                                .setAction("De acuerdo", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        finish();
                                    }
                                })
                                .show();

                        restriccion.setVisibility(View.VISIBLE);
                        txtMensaje.setVisibility(View.INVISIBLE);
                        btnEnviar.setVisibility(View.INVISIBLE);
                        btnEnviarFoto.setVisibility(View.INVISIBLE);
                        rvMensajes.setVisibility(View.INVISIBLE);

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }else{
            returnLogin();
        }

    }

    private void returnLogin(){
        startActivity(new Intent(MensajeriaActivityIGEM.this, LoginActivity.class));
        finish();
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
