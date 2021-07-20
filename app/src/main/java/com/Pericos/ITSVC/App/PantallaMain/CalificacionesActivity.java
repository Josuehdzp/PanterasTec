package com.Pericos.ITSVC.App.PantallaMain;

import android.annotation.SuppressLint;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.Pericos.ITSVC.App.R;

public class CalificacionesActivity extends AppCompatActivity {


    WebView calificaccionesWeb;
    private ProgressBar progressBar;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portalweb);

        calificaccionesWeb = findViewById(R.id.portalweb);
        calificaccionesWeb.setWebViewClient(new WebViewClient());
        calificaccionesWeb.loadUrl("http://sie.itsh.edu.mx/intertec/index.html");

        calificaccionesWeb.getSettings().setJavaScriptEnabled(true);
        calificaccionesWeb.getSettings().setBuiltInZoomControls(true); // Habilita el Zoom
        calificaccionesWeb.getSettings().setDisplayZoomControls(false); // Oculta los botones de zoom, haciendo que solo funcione con gestos.

        //Sincronizamos la barra de progreso de la web
        progressBar = (ProgressBar) findViewById(R.id.progressBarPortal);

        calificaccionesWeb.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progress) {
                progressBar.setProgress(0);
                progressBar.setVisibility(View.VISIBLE);
                CalificacionesActivity.this.setProgress(progress * 1000);

                progressBar.incrementProgressBy(progress);

                if (progress == 100) {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

    }
    @Override
    public void onBackPressed() {

        if (calificaccionesWeb.canGoBack())
        {
            calificaccionesWeb.goBack();
        }
        else
        {
            super.onBackPressed();
        }
    }

    }


