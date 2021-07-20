package com.Pericos.ITSVC.App.Chat.Activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.Pericos.ITSVC.App.R;
import com.danimahardhika.cafebar.CafeBar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;


public class LoginActivity extends AppCompatActivity {

    private EditText txtCorreo,txtContraseña;
    private Button btnLogin,btnRegistro;
    private FirebaseAuth mAuth;
    private CheckBox mostrarContraseña;
    private Toolbar loginToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity_login);

        loginToolbar = findViewById(R.id.idLoginToolbar);
        txtCorreo = findViewById(R.id.idCorreoLogin);
        txtContraseña = findViewById(R.id.idContraseñaLogin);
        btnLogin = findViewById(R.id.idLoginLogin);
        btnRegistro = findViewById(R.id.idRegistroLogin);
        mostrarContraseña = findViewById(R.id.idContraseñaLoginVisible);

        setSupportActionBar(loginToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        mAuth = FirebaseAuth.getInstance();


        mostrarContraseña.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (!isChecked) {
                    // mostrar contraseña
                    txtContraseña.setTransformationMethod(PasswordTransformationMethod.getInstance());


                } else {
                    // esconder contraseña
                    txtContraseña.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo = txtCorreo.getText().toString();
                if(isValidEmail(correo) && validarContraseña()){
                    String contraseña = txtContraseña.getText().toString();
                    mAuth.signInWithEmailAndPassword(correo, contraseña)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information

                                        CafeBar.builder(LoginActivity.this)
                                                .content("Se logeo correctamente.")
                                                .floating(true)
                                                .duration(CafeBar.Duration.MEDIUM)
                                                .neutralColor(getResources().getColor(R.color.negroprincipal))
                                                .show();

                                        nextActivity();
                                    } else {
                                        // If sign in fails, display a message to the user.

                                        CafeBar.builder(LoginActivity.this)
                                                .content("Error, verifica tu correo o contraseña.")
                                                .floating(true)
                                                .duration(CafeBar.Duration.MEDIUM)
                                                .neutralColor(getResources().getColor(R.color.negroprincipal))
                                                .show();

                                    }

                                }
                            });
                }else{
                    CafeBar.builder(LoginActivity.this)
                            .content("Error, escribe correctamente tu correo o contraseña.")
                            .floating(true)
                            .duration(CafeBar.Duration.MEDIUM)
                            .neutralColor(getResources().getColor(R.color.negroprincipal))
                            .show();
                }
            }
        });

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegistroActivityISIC.class));
            }
        });
        //UsuarioDAO.getInstancia().añadirFotoDePerfilALosUsuariosQueNoTienenFoto();

    }

    private boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public boolean validarContraseña(){
        String contraseña;
        contraseña = txtContraseña.getText().toString();
        return contraseña.length() >= 6 && contraseña.length() <= 16;
    }

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser!=null){
            CafeBar.builder(LoginActivity.this)
                    .content("Bienvenido.")
                    .floating(true)
                    .duration(CafeBar.Duration.MEDIUM)
                    .neutralColor(getResources().getColor(R.color.negroprincipal))
                    .show();
            nextActivity();
        }
    }

    private void nextActivity(){


        startActivity(new Intent(LoginActivity.this,MenuActivity.class));
        finish();
    }



}
