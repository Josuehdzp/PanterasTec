package com.Pericos.ITSVC.App.PantallaMain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.Pericos.ITSVC.App.R;
import com.makeramen.roundedimageview.RoundedImageView;


public class ContactoActivity extends AppCompatActivity implements OnMapReadyCallback {

    private TextView textView;

    private MapView mapView;
    private GoogleMap gmap;
    Toolbar contactoToolbar;

    ImageView btnFacebook,btnTwiter,btnYoutube,btnInstagram;
    RoundedImageView imgLogo;

    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacto);

        contactoToolbar = findViewById(R.id.idContactoToolbar);

        btnFacebook = findViewById(R.id.idContactoBtnFacebook);
        btnTwiter = findViewById(R.id.idContactoBtnTwitter);
        btnYoutube = findViewById(R.id.idContactoBtnYoutube);
        btnInstagram = findViewById(R.id.idContactoBtnInstagram);
        imgLogo = findViewById(R.id.idContactoFoto2);

        setSupportActionBar(contactoToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }

        mapView = findViewById(R.id.idContactoMapView);
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);

        Glide.with(this).load(R.drawable.itsvclogo).into(imgLogo);


        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.facebook.com/profile.php?id=100014642463374";
                Intent verEnFacebook = new Intent(Intent.ACTION_VIEW);
                verEnFacebook.setData(Uri.parse(url));
                v.getContext().startActivity(verEnFacebook);
            }
        });

        btnTwiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://twitter.com/ITSVCOFICIAL";
                Intent twitter = new Intent(Intent.ACTION_VIEW);
                twitter.setData(Uri.parse(url));
                v.getContext().startActivity(twitter);

            }
        });

        btnYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.youtube.com/channel/UCoIZZBwWbeXNYXJWsJ9AEAA";
                Intent youtube = new Intent(Intent.ACTION_VIEW);
                youtube.setData(Uri.parse(url));
                v.getContext().startActivity(youtube);

            }
        });

        btnInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.instagram.com/ITSVCarranza/";
                Intent instagram = new Intent(Intent.ACTION_VIEW);
                instagram.setData(Uri.parse(url));
                v.getContext().startActivity(instagram);

            }
        });



    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }
    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }
    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;
        gmap.setMinZoomPreference(12);

        LatLng itsvc = new LatLng(20.4711936,-97.6997376);
        gmap.moveCamera(CameraUpdateFactory.newLatLng(itsvc));

        gmap.setIndoorEnabled(true);

        UiSettings uiSettings = gmap.getUiSettings();
        uiSettings.setIndoorLevelPickerEnabled(true);
        uiSettings.setMyLocationButtonEnabled(true);
        uiSettings.setMapToolbarEnabled(true);
        uiSettings.setCompassEnabled(true);
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setZoomGesturesEnabled(true);
        uiSettings.setAllGesturesEnabled(true);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(itsvc);
        gmap.addMarker(markerOptions);

        gmap.moveCamera(CameraUpdateFactory.newLatLng(itsvc));
    }

}



