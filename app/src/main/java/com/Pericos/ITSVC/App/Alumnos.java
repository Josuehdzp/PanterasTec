package com.Pericos.ITSVC.App;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.Pericos.ITSVC.App.Chat.Activity.LoginActivity;
import com.Pericos.ITSVC.App.Horarios.HorarioPersonalizado;
import com.google.android.material.chip.Chip;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class Alumnos extends AppCompatActivity {

    private Chip chipChat, chipNotas, chiptres;
    private FloatingActionButton fabChat;
    private long fechaDeNacimiento;
    private TextView txtE;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);

        chipChat = findViewById(R.id.chipChat);
        chipNotas = findViewById(R.id.chipNotas);
        chiptres = findViewById(R.id.chip3);
        fabChat = findViewById(R.id.fabChat);
        txtE = findViewById(R.id.txtEstud);

        chipChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentChat = new Intent(Alumnos.this, LoginActivity.class);
                startActivity(intentChat);
            }
        });
        fabChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentChat = new Intent(Alumnos.this, HorarioPersonalizado.class);
                startActivity(intentChat);
            }
        });

        chipNotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(Alumnos.this, new DatePickerDialog.OnDateSetListener() {
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
                        txtE.setText(fechaDeNacimientoTexto);
                    }
                },calendar.get(Calendar.YEAR)-19,calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

    }
}