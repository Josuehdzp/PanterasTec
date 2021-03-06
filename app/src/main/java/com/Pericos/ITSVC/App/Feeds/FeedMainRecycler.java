package com.Pericos.ITSVC.App.Feeds;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.net.ParseException;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.Pericos.ITSVC.App.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class FeedMainRecycler extends AppCompatActivity {

    //private static final String URL_DATA = "https://api.github.com/search/users?q=language:java+location:lagos";
    private static final String URL_DATA1 = "https://graph.facebook.com/v3.2/me?fields=posts.limit(50)%7Bcreated_time%2Cstatus_type%2Cstory%2Cmessage%2Cfull_picture%2Cpermalink_url%2Cname%7D%2Cname&access_token=EAACpeSbpCtABAO09g9ML4ETZCjul9Em5wZBFPrjmTNJgIqVbZC6wgRZB52MEzCqVQZBZC8fA9gLaZBZAhYWFMAgoYJ9ZBqYA2LXytNZCNl1tZCWYD6v2VZAL1Ggwkqdo1sabw5RvqeRlwGGUoDCn50KPriZAp0nd3RAjcwalIOZAHJhota102CTVZBLnSRdsQI8Ua2sC2uULgMbAqtoQgZDZD";
    private static final String URL_DATA = "https://graph.facebook.com/v3.2/me?fields=posts.limit(50)%7Bfrom%2Ccreated_time%2Cmessage%2Cfull_picture%2Cpermalink_url%2Cstatus_type%2Cstory%7D&access_token=EAACpeSbpCtABANbkpBzTLQVVYafNuoTufA8rNUG0WwSieUsovcckexpZAZBYzjkDPnBigN3PfR9M5UjAjMSoUWMUgVfnZB32WHs8wJP2vg9iEWVNtZBNH7wfQuO41haBkFYGugTL5WXZC6QXLAyxbW3ZCi1zY9ZAijvG04ZAxV8iPwZDZD";


    private String TIEMPO;
    private String LINK;
    private String MENSAJE;
    private String HISTORIA;
    private String ESTADO;
    private String IMAGEN;

    private String timestamp = "2014-09-23T23:03:11Z";
    private String FORMATO_FECHA = "yyyy-MM-dd'T'HH:mm:ssZ";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<FeedFacebookListTest> feedFacebookListTests;

    private ProgressDialog pDialog;
    private TextView TVprueba;
    private Button btntest;
    private RequestQueue queue;

    private TextView tvmensaje;

    private  FeedFacebookAdapterTest adapterFeeds;
    Toolbar avisoToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed_recycler);


        avisoToolbar = findViewById(R.id.idAvisosToolbar);

        recyclerView = findViewById(R.id.RecyclerFeeds);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));

        feedFacebookListTests = new ArrayList<>();

        setSupportActionBar(avisoToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        loadUrlData();
    }



    private void loadUrlData() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setIcon(R.mipmap.ic_launcher_round);
        progressDialog.setTitle("Espera un poco");
        progressDialog.setMessage("Estoy descargando noticias...");
        progressDialog.setCancelable(true);

        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault());// 2018-06-19T22:38:28+0000
                formato.setTimeZone(TimeZone.getTimeZone("GMT"));
                Calendar cal= Calendar.getInstance();
                String dateTime = formato.format(cal.getTime());

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray array = jsonObject.getJSONObject("posts").getJSONArray("data");


                    for (int i = 0; i < array.length(); i++) {

                        JSONObject json = array.getJSONObject(i);

                        String date = json.getString("created_time");
                        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault());
                        @SuppressLint("SimpleDateFormat") SimpleDateFormat output = new SimpleDateFormat("EEEE  d 'de' MMMM 'de' YYYY 'a las' hh:mm aaa");
                        Date datetime = null;

                        try {
                            datetime = dateformat.parse(date /*date as String*/);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        catch (java.text.ParseException e) {
                            e.printStackTrace();
                        }
                        String formattedDate = output.format(datetime);


                        //obtener fecha de creacion
                        if (json.has("created_time")){
                            TIEMPO = json.getString("created_time");
                            TIEMPO = formattedDate;
                        }
                        //obtener Link

                        if (json.has("permalink_url")){
                            LINK = json.getString("permalink_url");
                        }
                        //obtener Mensaje

                        if (json.has("message")){
                            MENSAJE = json.getString("message");
                        } else {
                            MENSAJE = "";
                        }
                        //obtener Imagen

                        if (json.has("full_picture")){
                            IMAGEN = json.getString("full_picture");
                        }else {
                            IMAGEN = String.valueOf(getResources().getDrawable(R.drawable.icono_escencial_internet));
                        }
                        //obtener Historia

                        if (json.has("story")){
                            HISTORIA = json.getString("story");
                        } else {
                            HISTORIA = "";
                        }
                        //obtener tipo de estado

                        if (json.has("status_type")){
                            ESTADO = json.getString("status_type");

                            if (ESTADO.equals("mobile_status_update")){
                                ESTADO = FeedConstantesFB.ACTUALIZO_ESTADO;
                            }
                            if (ESTADO.equals("added_photos")){
                                ESTADO = FeedConstantesFB.AGREGO_FOTO;
                            }
                            if (ESTADO.equals("added_video")){
                                ESTADO = FeedConstantesFB.AGREGO_VIDEO;
                            }

                            if (ESTADO.equals("shared_story")){
                                ESTADO = FeedConstantesFB.COMPARTIO_ESTADO;
                            }
                        }



                        FeedFacebookListTest developers = new FeedFacebookListTest(
                                TIEMPO,
                                MENSAJE,
                                IMAGEN,
                                LINK,
                                ESTADO,
                                HISTORIA);


                        feedFacebookListTests.add(developers);



                    }

                    adapter = new FeedFacebookAdapterTest(feedFacebookListTests, getApplicationContext());
                    recyclerView.setAdapter(adapter);


                } catch (JSONException e) {

                    e.printStackTrace();
                }
////////////////////////////


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(FeedMainRecycler.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    ///////////////////////////////////
    private void cargarDatos() {
        String url = "https://graph.facebook.com/v3.2/me?fields=posts.limit(10)%7Bfrom%2Ccreated_time%2Cmessage%2Cfull_picture%2Cpermalink_url%2Cstatus_type%2Cstory%7D&access_token=EAACpeSbpCtABAHSc6UtFLx6yQRkW5Y2y3gWE34InitTamyjWBP7wi1Eed2NPPd7BCkyPeBhslrevZBLXUn24sjvcN7rbHuh9hXe12tFfvYVLZAHgeD47SwoCt7UbUrH53d0VdEILdrsFT0aUZCIDQSpeAgqVK88mea0auV6ZBwLfbjpLSuVpMJskdKt8nU3r4oCKmzZCoIgZDZD";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray array = response.getJSONObject("posts").getJSONArray("data");

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject feed = array.getJSONObject(i);

                                String crea = feed.getString("tv_created_time");
                                String link = feed.getString("tv_permalink_url");
                                String full = feed.getString("tv_full_picture");



                                TVprueba.append(full+ "\n\n");
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);

    }
}

