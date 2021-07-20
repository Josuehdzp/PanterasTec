package com.Pericos.ITSVC.App.PantallaMain;

import android.annotation.SuppressLint;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.webkit.WebChromeClient;

import com.Pericos.ITSVC.App.R;


public class PortalwebActivity extends AppCompatActivity {

    WebView portalWeb;
    private ProgressBar progressBar;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portalweb);

        portalWeb = findViewById(R.id.portalweb);
        portalWeb.setWebViewClient(new WebViewClient());
        portalWeb.loadUrl("https://www.itsvc.edu.mx");

            portalWeb.getSettings().setJavaScriptEnabled(true);
            portalWeb.getSettings().setBuiltInZoomControls(true); // Habilita el Zoom
            portalWeb.getSettings().setDisplayZoomControls(false); // Oculta los botones de zoom, haciendo que solo funcione con gestos.

        //Sincronizamos la barra de progreso de la web
        progressBar = findViewById(R.id.progressBarPortal);

        portalWeb.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progress) {
                progressBar.setProgress(0);
                progressBar.setVisibility(View.VISIBLE);
                PortalwebActivity.this.setProgress(progress * 1000);

                progressBar.incrementProgressBy(progress);

                if (progress == 100) {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

    }
    @Override
    public void onBackPressed() {

        if (portalWeb.canGoBack())
        {
            portalWeb.goBack();
        }
        else
        {
            super.onBackPressed();
        }
    }




}
