package com.Pericos.ITSVC.App.Feeds;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.Pericos.ITSVC.App.Horarios.DatabaseHelper;
import com.Pericos.ITSVC.App.Notas.NotasMain;
import com.Pericos.ITSVC.App.R;
import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.DialogFragment;
import yuku.ambilwarna.AmbilWarnaDialog;

public class DialogoFeeds extends DialogFragment {

    NestedScrollView layoutVista;
    PhotoView photoView;
    String urldelaImagen;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.DialogTema);


        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.feed_dialogo, null);

        photoView = view.findViewById(R.id.idFeedDialogImage);

        Bundle bundle = getArguments();
        urldelaImagen = bundle.getString("full_picture","");

        Picasso.with(getContext())
                .load(urldelaImagen)
                .into(photoView);

        layoutVista = view.findViewById(R.id.idVistaFeedDialogo);

        builder.setView(view);

        return builder.create();
    }


}


