package com.Pericos.ITSVC.App.Facebook;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.Pericos.ITSVC.App.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainFacebookJsonParseNext1 extends AppCompatActivity implements View.OnClickListener {

    ArrayList<HashMap< String, String>> ListaArray;

    private String TAG = MainFacebookJsonParseNext1.class.getSimpleName();


    String[] objetos = new String [1000];
    String[] manual = new String [1000];

    private static String NoesqueseaPreNext = "https://graph.facebook.com/v3.2/254951361282496/posts?access_token=EAACpeSbpCtABAAfZAZAvc24ZCAgyorqSNZBBXRajUO6iyzVY5ZCI4hqjzjJK9J5kU8z1pZBHpeLUZBcGMsKQwX5Wq7S7S6bOonaMet009whdsXooWZBdZAik9C7tK2K85Apzjhh31xVXuosYw9kjhUQCXPKTssFaGa9kH5TM2ZAZCEetAZDZD&pretty=0&fields=from%2Ccreated_time%2Cmessage%2Cfull_picture%2Cpermalink_url%2Cstatus_type%2Cstory&limit=10&after=";

    private String TokenPanteras = "EAACpeSbpCtABANbkpBzTLQVVYafNuoTufA8rNUG0WwSieUsovcckexpZAZBYzjkDPnBigN3PfR9M5UjAjMSoUWMUgVfnZB32WHs8wJP2vg9iEWVNtZBNH7wfQuO41haBkFYGugTL5WXZC6QXLAyxbW3ZCi1zY9ZAijvG04ZAxV8iPwZDZD";
    private String after = "Q2c4U1pXNTBYM0YxWlhKNVgzTjBiM0o1WDJsa0R5UXlOVFE1TlRFek5qRXlPREkwT1RZANkxUa3lNRFk0TVRBME16azJNall5TVRNNE1UTVBER0ZA3YVY5emRHOXllVjlwWkE4ZA01qVTBPVFV4TXpZAeE1qZA3lORGsyWHpFNU1EWTNORE0wTXpZAeE1ETXlOeklQQkhScGJXVUdYRktPTUFFPQZDZD";
    private String preafter = "https://graph.facebook.com/v3.2/953367544722442/posts?access_token=EAACpeSbpCtABANbkpBzTLQVVYafNuoTufA8rNUG0WwSieUsovcckexpZAZBYzjkDPnBigN3PfR9M5UjAjMSoUWMUgVfnZB32WHs8wJP2vg9iEWVNtZBNH7wfQuO41haBkFYGugTL5WXZC6QXLAyxbW3ZCi1zY9ZAijvG04ZAxV8iPwZDZD&pretty=0&fields=from%2Ccreated_time%2Cmessage%2Cfull_picture%2Cpermalink_url%2Cstatus_type%2Cstory&limit=10&after=";
    private static String LestraigocancerNext1 = "https://graph.facebook.com/v3.2/425822364223626/posts?pretty=0&fields=from%2Ccreated_time%2Cmessage%2Cfull_picture%2Cpermalink_url&limit=10&after=Q2c4U1pXNTBYM0YxWlhKNVgzTjBiM0o1WDJsa0R5UTBNalU0TWpJek5qUXlNak0yTWpZANkxUYzNNRFUyTnpJMU56VXdOakE1TXpVeE5EQVBER0ZA3YVY5emRHOXllVjlwWkE4ZAk5ESTFPREl5TXpZAME1qSXpOakkyWHpRNU5EZA3hOamc0TnpNeU5ERTNNdzhFZAEdsdFpRWlVvT0xuQVE9PQZDZD&access_token=EAACpeSbpCtABAP36ulrjfiyOJe2FeMs5wne94VGkr2wXkX5fbiKadXeMuXFztrZBLDMI5yCEe5n9DBO6lkI6JhHBt5uL9nbunOnSWYYLd67I6ZCSbYqTZCZBmwV8MBDCcAO349frRp2ZBbPtq7uSA7o6jNQR8aGMs5c5ZCYpj4vgZDZD";
    private static String Noesqueseaaaaa = "https://graph.facebook.com/v3.2/me?fields=posts.limit(10)%7Bfrom%2Ccreated_time%2Cmessage%2Cfull_picture%2Cpermalink_url%2Cstatus_type%2Cstory%7D&access_token=EAACpeSbpCtABANbkpBzTLQVVYafNuoTufA8rNUG0WwSieUsovcckexpZAZBYzjkDPnBigN3PfR9M5UjAjMSoUWMUgVfnZB32WHs8wJP2vg9iEWVNtZBNH7wfQuO41haBkFYGugTL5WXZC6QXLAyxbW3ZCi1zY9ZAijvG04ZAxV8iPwZDZD";
    private static String NoesqueseaNext = "https://graph.facebook.com/v3.2/254951361282496/posts?access_token=EAACpeSbpCtABAAfZAZAvc24ZCAgyorqSNZBBXRajUO6iyzVY5ZCI4hqjzjJK9J5kU8z1pZBHpeLUZBcGMsKQwX5Wq7S7S6bOonaMet009whdsXooWZBdZAik9C7tK2K85Apzjhh31xVXuosYw9kjhUQCXPKTssFaGa9kH5TM2ZAZCEetAZDZD&pretty=0&fields=from%2Ccreated_time%2Cmessage%2Cfull_picture%2Cpermalink_url%2Cstatus_type%2Cstory&limit=5&after=Q2c4U1pXNTBYM0YxWlhKNVgzTjBiM0o1WDJsa0R5UXlOVFE1TlRFek5qRXlPREkwT1RZANkxUSXdOREF5TnpnM01qRXlNVEUyTURJeE5ESVBER0ZA3YVY5emRHOXllVjlwWkE4ZA01qVTBPVFV4TXpZAeE1qZA3lORGsyWHpFNU1EWTNOREUwT0RJM056QXhNelFQQkhScGJXVUdYRktObHdFPQZDZD";


    TextView botoncitonext;
    JSONObject jsonObjectTextos;
    TextView textViewPageName;
    TextView textViewCreacion;
    TextView textViewStatus;
    TextView textViewStory;
    TextView textViewMessage;
    TextView textViewLinkURL;
    ImageView FeedImage;
    String FeedImage2;

    private ProgressDialog pDialog;

    private List<PostsItems> postsList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_graphapi_next1);


//        TextView receptor = (TextView) findViewById(R.id.kenya);
//                //Recepcion de datos.
//        Bundle miBundle = this.getIntent().getExtras();
//        if(miBundle !=null){
//            String after = miBundle.getString("pagina");
//            receptor.setText(after);
//        }





        postsList = new ArrayList<>();

        View botonNext1 =findViewById(R.id.BotonBack);
        botonNext1.setOnClickListener(this);

        new AsyncTaskExample().execute(LestraigocancerNext1);
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_action_bar, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_settings){
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }




    public class AsyncTaskExample extends AsyncTask<String, String, String[]> {



        @Override
        protected void onPreExecute() {


            pDialog = new ProgressDialog(MainFacebookJsonParseNext1.this);
            pDialog.setIcon(R.mipmap.ic_launcher_round);
            pDialog.setTitle("Espera un poco");
            pDialog.setMessage("Estoy descargando noticias...");
            pDialog.show();


        }

        @Override
        protected String[] doInBackground(String... Big) {

            try{
                jsonObjectTextos=JsonParse.readJsonFromUrl(Noesqueseaaaaa);
                manual[99] = jsonObjectTextos.getJSONObject("posts").getJSONObject("paging").getJSONObject("cursors").getString("after");

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }


            try {
                jsonObjectTextos = JsonParse.readJsonFromUrl(preafter+manual[99]);
                objetos[0] = jsonObjectTextos.getJSONArray("data").getJSONObject(0).getJSONObject("from").getString("name");
                objetos[1] = jsonObjectTextos.getJSONArray("data").getJSONObject(0).getString("created_time");
                objetos[2] = jsonObjectTextos.getJSONArray("data").getJSONObject(0).getString("status_type");
                objetos[6] = jsonObjectTextos.getJSONArray("data").getJSONObject(0).getString("permalink_url");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextos = JsonParse.readJsonFromUrl(preafter+manual[99]);
                objetos[3] = jsonObjectTextos.getJSONArray("data").getJSONObject(0).getString("story");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextos = JsonParse.readJsonFromUrl(preafter+manual[99]);
                objetos[4] = jsonObjectTextos.getJSONArray("data").getJSONObject(0).getString("message");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextos = JsonParse.readJsonFromUrl(preafter+manual[99]);
                objetos[5] = jsonObjectTextos.getJSONArray("data").getJSONObject(0).getString("full_picture");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

            try {
                jsonObjectTextos = JsonParse.readJsonFromUrl(preafter+manual[99]);
                objetos[7] = jsonObjectTextos.getJSONArray("data").getJSONObject(1).getJSONObject("from").getString("name");
                objetos[8] = jsonObjectTextos.getJSONArray("data").getJSONObject(1).getString("created_time");
                objetos[9] = jsonObjectTextos.getJSONArray("data").getJSONObject(1).getString("status_type");
                objetos[13] = jsonObjectTextos.getJSONArray("data").getJSONObject(1).getString("permalink_url");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextos = JsonParse.readJsonFromUrl(preafter+manual[99]);
                objetos[10] = jsonObjectTextos.getJSONArray("data").getJSONObject(1).getString("story");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextos = JsonParse.readJsonFromUrl(preafter+manual[99]);
                objetos[11] = jsonObjectTextos.getJSONArray("data").getJSONObject(1).getString("message");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextos = JsonParse.readJsonFromUrl(preafter+manual[99]);
                objetos[12] = jsonObjectTextos.getJSONArray("data").getJSONObject(1).getString("full_picture");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

            try {
                jsonObjectTextos = JsonParse.readJsonFromUrl(preafter+manual[99]);
                objetos[14] = jsonObjectTextos.getJSONArray("data").getJSONObject(2).getJSONObject("from").getString("name");
                objetos[15] = jsonObjectTextos.getJSONArray("data").getJSONObject(2).getString("created_time");
                objetos[16] = jsonObjectTextos.getJSONArray("data").getJSONObject(2).getString("status_type");
                objetos[20] = jsonObjectTextos.getJSONArray("data").getJSONObject(2).getString("permalink_url");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextos = JsonParse.readJsonFromUrl(preafter+manual[99]);
                objetos[17] = jsonObjectTextos.getJSONArray("data").getJSONObject(2).getString("story");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextos = JsonParse.readJsonFromUrl(preafter+manual[99]);
                objetos[18] = jsonObjectTextos.getJSONArray("data").getJSONObject(2).getString("message");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextos = JsonParse.readJsonFromUrl(preafter+manual[99]);
                objetos[19] = jsonObjectTextos.getJSONArray("data").getJSONObject(2).getString("full_picture");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

            try {
                jsonObjectTextos = JsonParse.readJsonFromUrl(preafter+manual[99]);
                objetos[21] = jsonObjectTextos.getJSONArray("data").getJSONObject(3).getJSONObject("from").getString("name");
                objetos[22] = jsonObjectTextos.getJSONArray("data").getJSONObject(3).getString("created_time");
                objetos[23] = jsonObjectTextos.getJSONArray("data").getJSONObject(3).getString("status_type");
                objetos[27] = jsonObjectTextos.getJSONArray("data").getJSONObject(3).getString("permalink_url");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextos = JsonParse.readJsonFromUrl(preafter+manual[99]);
                objetos[24] = jsonObjectTextos.getJSONArray("data").getJSONObject(3).getString("story");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextos = JsonParse.readJsonFromUrl(preafter+manual[99]);
                objetos[25] = jsonObjectTextos.getJSONArray("data").getJSONObject(3).getString("message");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextos = JsonParse.readJsonFromUrl(preafter+manual[99]);
                objetos[26] = jsonObjectTextos.getJSONArray("data").getJSONObject(3).getString("full_picture");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

            try {
                jsonObjectTextos = JsonParse.readJsonFromUrl(preafter+manual[99]);
                objetos[28] = jsonObjectTextos.getJSONArray("data").getJSONObject(4).getJSONObject("from").getString("name");
                objetos[29] = jsonObjectTextos.getJSONArray("data").getJSONObject(4).getString("created_time");
                objetos[30] = jsonObjectTextos.getJSONArray("data").getJSONObject(4).getString("status_type");
                objetos[34] = jsonObjectTextos.getJSONArray("data").getJSONObject(4).getString("permalink_url");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextos = JsonParse.readJsonFromUrl(preafter+manual[99]);
                objetos[31] = jsonObjectTextos.getJSONArray("data").getJSONObject(4).getString("story");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextos = JsonParse.readJsonFromUrl(preafter+manual[99]);
                objetos[32] = jsonObjectTextos.getJSONArray("data").getJSONObject(4).getString("message");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextos = JsonParse.readJsonFromUrl(preafter+manual[99]);
                objetos[33] = jsonObjectTextos.getJSONArray("data").getJSONObject(4).getString("full_picture");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

            try {
                jsonObjectTextos = JsonParse.readJsonFromUrl(preafter+manual[99]);
                objetos[35] = jsonObjectTextos.getJSONArray("data").getJSONObject(5).getJSONObject("from").getString("name");
                objetos[36] = jsonObjectTextos.getJSONArray("data").getJSONObject(5).getString("created_time");
                objetos[37] = jsonObjectTextos.getJSONArray("data").getJSONObject(5).getString("status_type");
                objetos[41] = jsonObjectTextos.getJSONArray("data").getJSONObject(5).getString("permalink_url");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextos = JsonParse.readJsonFromUrl(preafter+manual[99]);
                objetos[38] = jsonObjectTextos.getJSONArray("data").getJSONObject(5).getString("story");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextos = JsonParse.readJsonFromUrl(preafter+manual[99]);
                objetos[39] = jsonObjectTextos.getJSONArray("data").getJSONObject(5).getString("message");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextos = JsonParse.readJsonFromUrl(preafter+manual[99]);
                objetos[40] = jsonObjectTextos.getJSONArray("data").getJSONObject(5).getString("full_picture");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////



            try {
                jsonObjectTextos = JsonParse.readJsonFromUrl(preafter+manual[99]);
                objetos[42] = jsonObjectTextos.getJSONArray("data").getJSONObject(6).getJSONObject("from").getString("name");
                objetos[43] = jsonObjectTextos.getJSONArray("data").getJSONObject(6).getString("created_time");
                objetos[44] = jsonObjectTextos.getJSONArray("data").getJSONObject(6).getString("status_type");
                objetos[48] = jsonObjectTextos.getJSONArray("data").getJSONObject(6).getString("permalink_url");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextos = JsonParse.readJsonFromUrl(preafter+manual[99]);
                objetos[45] = jsonObjectTextos.getJSONArray("data").getJSONObject(6).getString("story");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextos = JsonParse.readJsonFromUrl(preafter+manual[99]);
                objetos[46] = jsonObjectTextos.getJSONArray("data").getJSONObject(6).getString("message");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextos = JsonParse.readJsonFromUrl(preafter+manual[99]);
                objetos[47] = jsonObjectTextos.getJSONArray("data").getJSONObject(6).getString("full_picture");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

            try {
                jsonObjectTextos = JsonParse.readJsonFromUrl(preafter+manual[99]);
                objetos[49] = jsonObjectTextos.getJSONArray("data").getJSONObject(7).getJSONObject("from").getString("name");
                objetos[50] = jsonObjectTextos.getJSONArray("data").getJSONObject(7).getString("created_time");
                objetos[51] = jsonObjectTextos.getJSONArray("data").getJSONObject(7).getString("status_type");
                objetos[55] = jsonObjectTextos.getJSONArray("data").getJSONObject(7).getString("permalink_url");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextos = JsonParse.readJsonFromUrl(preafter+manual[99]);
                objetos[52] = jsonObjectTextos.getJSONArray("data").getJSONObject(7).getString("story");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextos = JsonParse.readJsonFromUrl(preafter+manual[99]);
                objetos[53] = jsonObjectTextos.getJSONArray("data").getJSONObject(7).getString("message");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextos = JsonParse.readJsonFromUrl(preafter+manual[99]);
                objetos[54] = jsonObjectTextos.getJSONArray("data").getJSONObject(7).getString("full_picture");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////



            try {
                jsonObjectTextos = JsonParse.readJsonFromUrl(preafter+manual[99]);
                objetos[56] = jsonObjectTextos.getJSONArray("data").getJSONObject(8).getJSONObject("from").getString("name");
                objetos[57] = jsonObjectTextos.getJSONArray("data").getJSONObject(8).getString("created_time");
                objetos[58] = jsonObjectTextos.getJSONArray("data").getJSONObject(8).getString("status_type");
                objetos[62] = jsonObjectTextos.getJSONArray("data").getJSONObject(8).getString("permalink_url");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextos = JsonParse.readJsonFromUrl(preafter+manual[99]);
                objetos[59] = jsonObjectTextos.getJSONArray("data").getJSONObject(8).getString("story");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextos = JsonParse.readJsonFromUrl(preafter+manual[99]);
                objetos[60] = jsonObjectTextos.getJSONArray("data").getJSONObject(8).getString("message");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextos = JsonParse.readJsonFromUrl(preafter+manual[99]);
                objetos[61] = jsonObjectTextos.getJSONArray("data").getJSONObject(8).getString("full_picture");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////



            try {
                jsonObjectTextos = JsonParse.readJsonFromUrl(preafter+manual[99]);
                objetos[63] = jsonObjectTextos.getJSONArray("data").getJSONObject(9).getJSONObject("from").getString("name");
                objetos[64] = jsonObjectTextos.getJSONArray("data").getJSONObject(9).getString("created_time");
                objetos[65] = jsonObjectTextos.getJSONArray("data").getJSONObject(9).getString("status_type");
                objetos[69] = jsonObjectTextos.getJSONArray("data").getJSONObject(9).getString("permalink_url");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextos = JsonParse.readJsonFromUrl(preafter+manual[99]);
                objetos[66] = jsonObjectTextos.getJSONArray("data").getJSONObject(9).getString("story");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextos = JsonParse.readJsonFromUrl(preafter+manual[99]);
                objetos[67] = jsonObjectTextos.getJSONArray("data").getJSONObject(9).getString("message");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextos = JsonParse.readJsonFromUrl(preafter+manual[99]);
                objetos[68] = jsonObjectTextos.getJSONArray("data").getJSONObject(9).getString("full_picture");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

            return objetos;
        }

        @Override
        protected void onPostExecute(String[] stringFromDoInBackground) {
            pDialog.dismiss();

            textViewPageName = findViewById(R.id.textViewPageNamenext);
            textViewCreacion = findViewById(R.id.textViewCreacionnext);
            textViewStatus = findViewById(R.id.textViewStatusTypenext);
            textViewStory = findViewById(R.id.textViewStorynext);
            textViewMessage = findViewById(R.id.textViewMessagenext);
            FeedImage = findViewById(R.id.FeedImagenext);
            textViewLinkURL = findViewById(R.id.textViewLinkURLnext);


            textViewPageName.setText(stringFromDoInBackground[0]);
            textViewPageName.setVisibility(View.VISIBLE);

            textViewCreacion.setText(stringFromDoInBackground[1]);
            textViewCreacion.setVisibility(View.VISIBLE);

            textViewStatus.setText(stringFromDoInBackground[2]);
            textViewStatus.setVisibility(View.VISIBLE);

            textViewStory.setText(stringFromDoInBackground[3]);
            textViewStory.setVisibility(View.VISIBLE);

            textViewMessage.setText(stringFromDoInBackground[4]);
            textViewMessage.setVisibility(View.VISIBLE);

            Glide.with(MainFacebookJsonParseNext1.this).load(stringFromDoInBackground[5]).into(FeedImage);
            FeedImage.setVisibility(View.VISIBLE);

            textViewLinkURL.setText(stringFromDoInBackground[6]);
            textViewLinkURL.setVisibility(View.VISIBLE);


///////////////////////////////////////////////////////////////////////////////////////////////////
            textViewPageName = findViewById(R.id.textViewPageNamenext1);
            textViewCreacion = findViewById(R.id.textViewCreacionnext1);
            textViewStatus = findViewById(R.id.textViewStatusTypenext1);
            textViewStory = findViewById(R.id.textViewStorynext1);
            textViewMessage = findViewById(R.id.textViewMessagenext1);
            FeedImage = findViewById(R.id.FeedImagenext1);
            textViewLinkURL = findViewById(R.id.textViewLinkURLnext1);


            textViewPageName.setText(stringFromDoInBackground[7]);
            textViewPageName.setVisibility(View.VISIBLE);

            textViewCreacion.setText(stringFromDoInBackground[8]);
            textViewCreacion.setVisibility(View.VISIBLE);

            textViewStatus.setText(stringFromDoInBackground[9]);
            textViewStatus.setVisibility(View.VISIBLE);

            textViewStory.setText(stringFromDoInBackground[10]);
            textViewStory.setVisibility(View.VISIBLE);

            textViewMessage.setText(stringFromDoInBackground[11]);
            textViewMessage.setVisibility(View.VISIBLE);

            Glide.with(MainFacebookJsonParseNext1.this).load(stringFromDoInBackground[12]).into(FeedImage);
            FeedImage.setVisibility(View.VISIBLE);

            textViewLinkURL.setText(stringFromDoInBackground[13]);
            textViewLinkURL.setVisibility(View.VISIBLE);


///////////////////////////////////////////////////////////////////////////////////////////////////
            textViewPageName = findViewById(R.id.textViewPageNamenext2);
            textViewCreacion = findViewById(R.id.textViewCreacionnext2);
            textViewStatus = findViewById(R.id.textViewStatusTypenext2);
            textViewStory = findViewById(R.id.textViewStorynext2);
            textViewMessage = findViewById(R.id.textViewMessagenext2);
            FeedImage = findViewById(R.id.FeedImagenext2);
            textViewLinkURL = findViewById(R.id.textViewLinkURLnext2);


            textViewPageName.setText(stringFromDoInBackground[14]);
            textViewPageName.setVisibility(View.VISIBLE);

            textViewCreacion.setText(stringFromDoInBackground[15]);
            textViewCreacion.setVisibility(View.VISIBLE);

            textViewStatus.setText(stringFromDoInBackground[16]);
            textViewStatus.setVisibility(View.VISIBLE);

            textViewStory.setText(stringFromDoInBackground[17]);
            textViewStory.setVisibility(View.VISIBLE);

            textViewMessage.setText(stringFromDoInBackground[18]);
            textViewMessage.setVisibility(View.VISIBLE);

            Glide.with(MainFacebookJsonParseNext1.this).load(stringFromDoInBackground[19]).into(FeedImage);
            FeedImage.setVisibility(View.VISIBLE);

            textViewLinkURL.setText(stringFromDoInBackground[20]);
            textViewLinkURL.setVisibility(View.VISIBLE);


///////////////////////////////////////////////////////////////////////////////////////////////////
            textViewPageName = findViewById(R.id.textViewPageNamenext3);
            textViewCreacion = findViewById(R.id.textViewCreacionnext3);
            textViewStatus = findViewById(R.id.textViewStatusTypenext3);
            textViewStory = findViewById(R.id.textViewStorynext3);
            textViewMessage = findViewById(R.id.textViewMessagenext3);
            FeedImage = findViewById(R.id.FeedImagenext3);
            textViewLinkURL = findViewById(R.id.textViewLinkURLnext3);


            textViewPageName.setText(stringFromDoInBackground[21]);
            textViewPageName.setVisibility(View.VISIBLE);

            textViewCreacion.setText(stringFromDoInBackground[22]);
            textViewCreacion.setVisibility(View.VISIBLE);

            textViewStatus.setText(stringFromDoInBackground[23]);
            textViewStatus.setVisibility(View.VISIBLE);

            textViewStory.setText(stringFromDoInBackground[24]);
            textViewStory.setVisibility(View.VISIBLE);

            textViewMessage.setText(stringFromDoInBackground[25]);
            textViewMessage.setVisibility(View.VISIBLE);

            Glide.with(MainFacebookJsonParseNext1.this).load(stringFromDoInBackground[26]).into(FeedImage);
            FeedImage.setVisibility(View.VISIBLE);

            textViewLinkURL.setText(stringFromDoInBackground[27]);
            textViewLinkURL.setVisibility(View.VISIBLE);


///////////////////////////////////////////////////////////////////////////////////////////////////
            textViewPageName = findViewById(R.id.textViewPageNamenext4);
            textViewCreacion = findViewById(R.id.textViewCreacionnext4);
            textViewStatus = findViewById(R.id.textViewStatusTypenext4);
            textViewStory = findViewById(R.id.textViewStorynext4);
            textViewMessage = findViewById(R.id.textViewMessagenext4);
            FeedImage = findViewById(R.id.FeedImagenext4);
            textViewLinkURL = findViewById(R.id.textViewLinkURLnext4);


            textViewPageName.setText(stringFromDoInBackground[28]);
            textViewPageName.setVisibility(View.VISIBLE);

            textViewCreacion.setText(stringFromDoInBackground[29]);
            textViewCreacion.setVisibility(View.VISIBLE);

            textViewStatus.setText(stringFromDoInBackground[30]);
            textViewStatus.setVisibility(View.VISIBLE);

            textViewStory.setText(stringFromDoInBackground[31]);
            textViewStory.setVisibility(View.VISIBLE);

            textViewMessage.setText(stringFromDoInBackground[32]);
            textViewMessage.setVisibility(View.VISIBLE);

            Glide.with(MainFacebookJsonParseNext1.this).load(stringFromDoInBackground[33]).into(FeedImage);
            FeedImage.setVisibility(View.VISIBLE);

            textViewLinkURL.setText(stringFromDoInBackground[34]);
            textViewLinkURL.setVisibility(View.VISIBLE);

///////////////////////////////////////////////////////////////////////////////////////////////////
            textViewPageName = findViewById(R.id.textViewPageNamenext5);
            textViewCreacion = findViewById(R.id.textViewCreacionnext5);
            textViewStatus = findViewById(R.id.textViewStatusTypenext5);
            textViewStory = findViewById(R.id.textViewStorynext5);
            textViewMessage = findViewById(R.id.textViewMessagenext5);
            FeedImage = findViewById(R.id.FeedImagenext5);
            textViewLinkURL = findViewById(R.id.textViewLinkURLnext5);


            textViewPageName.setText(stringFromDoInBackground[35]);
            textViewPageName.setVisibility(View.VISIBLE);

            textViewCreacion.setText(stringFromDoInBackground[36]);
            textViewCreacion.setVisibility(View.VISIBLE);

            textViewStatus.setText(stringFromDoInBackground[37]);
            textViewStatus.setVisibility(View.VISIBLE);

            textViewStory.setText(stringFromDoInBackground[38]);
            textViewStory.setVisibility(View.VISIBLE);

            textViewMessage.setText(stringFromDoInBackground[39]);
            textViewMessage.setVisibility(View.VISIBLE);

            Glide.with(MainFacebookJsonParseNext1.this).load(stringFromDoInBackground[40]).into(FeedImage);
            FeedImage.setVisibility(View.VISIBLE);

            textViewLinkURL.setText(stringFromDoInBackground[41]);
            textViewLinkURL.setVisibility(View.VISIBLE);

///////////////////////////////////////////////////////////////////////////////////////////////////
            textViewPageName = findViewById(R.id.textViewPageNamenext6);
            textViewCreacion = findViewById(R.id.textViewCreacionnext6);
            textViewStatus = findViewById(R.id.textViewStatusTypenext6);
            textViewStory = findViewById(R.id.textViewStorynext6);
            textViewMessage = findViewById(R.id.textViewMessagenext6);
            FeedImage = findViewById(R.id.FeedImagenext6);
            textViewLinkURL = findViewById(R.id.textViewLinkURLnext6);


            textViewPageName.setText(stringFromDoInBackground[42]);
            textViewPageName.setVisibility(View.VISIBLE);

            textViewCreacion.setText(stringFromDoInBackground[43]);
            textViewCreacion.setVisibility(View.VISIBLE);

            textViewStatus.setText(stringFromDoInBackground[44]);
            textViewStatus.setVisibility(View.VISIBLE);

            textViewStory.setText(stringFromDoInBackground[45]);
            textViewStory.setVisibility(View.VISIBLE);

            textViewMessage.setText(stringFromDoInBackground[46]);
            textViewMessage.setVisibility(View.VISIBLE);

            Glide.with(MainFacebookJsonParseNext1.this).load(stringFromDoInBackground[47]).into(FeedImage);
            FeedImage.setVisibility(View.VISIBLE);

            textViewLinkURL.setText(stringFromDoInBackground[48]);
            textViewLinkURL.setVisibility(View.VISIBLE);

            ///////////////////////////////////////////////////////////////////////////////////////////////////
            textViewPageName = findViewById(R.id.textViewPageNamenext7);
            textViewCreacion = findViewById(R.id.textViewCreacionnext7);
            textViewStatus = findViewById(R.id.textViewStatusTypenext7);
            textViewStory = findViewById(R.id.textViewStorynext7);
            textViewMessage = findViewById(R.id.textViewMessagenext7);
            FeedImage = findViewById(R.id.FeedImagenext7);
            textViewLinkURL = findViewById(R.id.textViewLinkURLnext7);


            textViewPageName.setText(stringFromDoInBackground[49]);
            textViewPageName.setVisibility(View.VISIBLE);

            textViewCreacion.setText(stringFromDoInBackground[50]);
            textViewCreacion.setVisibility(View.VISIBLE);

            textViewStatus.setText(stringFromDoInBackground[51]);
            textViewStatus.setVisibility(View.VISIBLE);

            textViewStory.setText(stringFromDoInBackground[52]);
            textViewStory.setVisibility(View.VISIBLE);

            textViewMessage.setText(stringFromDoInBackground[53]);
            textViewMessage.setVisibility(View.VISIBLE);

            Glide.with(MainFacebookJsonParseNext1.this).load(stringFromDoInBackground[54]).into(FeedImage);
            FeedImage.setVisibility(View.VISIBLE);

            textViewLinkURL.setText(stringFromDoInBackground[55]);
            textViewLinkURL.setVisibility(View.VISIBLE);

            ///////////////////////////////////////////////////////////////////////////////////////////////////
            textViewPageName = findViewById(R.id.textViewPageNamenext8);
            textViewCreacion = findViewById(R.id.textViewCreacionnext8);
            textViewStatus = findViewById(R.id.textViewStatusTypenext8);
            textViewStory = findViewById(R.id.textViewStorynext8);
            textViewMessage = findViewById(R.id.textViewMessagenext8);
            FeedImage = findViewById(R.id.FeedImagenext8);
            textViewLinkURL = findViewById(R.id.textViewLinkURLnext8);


            textViewPageName.setText(stringFromDoInBackground[56]);
            textViewPageName.setVisibility(View.VISIBLE);

            textViewCreacion.setText(stringFromDoInBackground[57]);
            textViewCreacion.setVisibility(View.VISIBLE);

            textViewStatus.setText(stringFromDoInBackground[58]);
            textViewStatus.setVisibility(View.VISIBLE);

            textViewStory.setText(stringFromDoInBackground[59]);
            textViewStory.setVisibility(View.VISIBLE);

            textViewMessage.setText(stringFromDoInBackground[60]);
            textViewMessage.setVisibility(View.VISIBLE);

            Glide.with(MainFacebookJsonParseNext1.this).load(stringFromDoInBackground[61]).into(FeedImage);
            FeedImage.setVisibility(View.VISIBLE);

            textViewLinkURL.setText(stringFromDoInBackground[62]);
            textViewLinkURL.setVisibility(View.VISIBLE);

            ///////////////////////////////////////////////////////////////////////////////////////////////////
            textViewPageName = findViewById(R.id.textViewPageNamenext9);
            textViewCreacion = findViewById(R.id.textViewCreacionnext9);
            textViewStatus = findViewById(R.id.textViewStatusTypenext9);
            textViewStory = findViewById(R.id.textViewStorynext9);
            textViewMessage = findViewById(R.id.textViewMessagenext9);
            FeedImage = findViewById(R.id.FeedImagenext9);
            textViewLinkURL = findViewById(R.id.textViewLinkURLnext9);


            textViewPageName.setText(stringFromDoInBackground[63]);
            textViewPageName.setVisibility(View.VISIBLE);

            textViewCreacion.setText(stringFromDoInBackground[64]);
            textViewCreacion.setVisibility(View.VISIBLE);

            textViewStatus.setText(stringFromDoInBackground[65]);
            textViewStatus.setVisibility(View.VISIBLE);

            textViewStory.setText(stringFromDoInBackground[66]);
            textViewStory.setVisibility(View.VISIBLE);

            textViewMessage.setText(stringFromDoInBackground[67]);
            textViewMessage.setVisibility(View.VISIBLE);

            Glide.with(MainFacebookJsonParseNext1.this).load(stringFromDoInBackground[68]).into(FeedImage);
            FeedImage.setVisibility(View.VISIBLE);

            textViewLinkURL.setText(stringFromDoInBackground[69]);
            textViewLinkURL.setVisibility(View.VISIBLE);

///////////////////////////////////////////////////////////////////////////////////////////////////
            /*progressBar = findViewById(R.id.progress_bar);
            progressBar.setVisibility(View.GONE);*/

        }


    }


    @Override
    public void onClick(View v) {
        Intent Back =  new Intent(MainFacebookJsonParseNext1.this, MainFacebookJsonParse.class);
        startActivity(Back);

    }
}
