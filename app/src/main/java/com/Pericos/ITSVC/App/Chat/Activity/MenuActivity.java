package com.Pericos.ITSVC.App.Chat.Activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.Pericos.ITSVC.App.Chat.Persistencia.UsuarioDAO;
import com.Pericos.ITSVC.App.R;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.Nullable;


public class MenuActivity extends AppCompatActivity {

    private Button BtnChatISIC;
    private Button BtnChatIIAL;
    private Button BtnChatIFOR;
    private Button BtnChatIGEO;
    private Button BtnChatIGEM;

    private Button BtnCerrarSesion;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity_menu);
        BtnChatISIC = findViewById(R.id.btnChatISIC);
        BtnChatIIAL = findViewById(R.id.btnChatIIAL);
        BtnChatIFOR = findViewById(R.id.btnChatIFOR);
        BtnChatIGEO = findViewById(R.id.btnChatIGEO);
        BtnChatIGEM = findViewById(R.id.btnChatIGEM);


        BtnCerrarSesion = findViewById(R.id.btnCerrarSesion);


        BtnChatISIC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MensajeriaActivityISIC.class);
                startActivity(intent);
            }
        });

        BtnChatIIAL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MensajeriaActivityIIAL.class);
                startActivity(intent);

            }
        });

        BtnChatIFOR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MensajeriaActivityIFOR.class);
                startActivity(intent);
            }
        });

        BtnChatIGEO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MensajeriaActivityIGEO.class);
                startActivity(intent);
            }
        });


        BtnChatIGEM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MensajeriaActivityIGEM.class);
                startActivity(intent);
            }
        });



        BtnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                returnLogin();
            }
        });
    }

    private void returnLogin(){
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (UsuarioDAO.getInstancia().isUsuarioLogeado()){
            //El usuario esta logeado
        } else {
            returnLogin();
        }
    }

}
