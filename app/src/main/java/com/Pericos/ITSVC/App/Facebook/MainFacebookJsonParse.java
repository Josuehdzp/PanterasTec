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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class MainFacebookJsonParse extends AppCompatActivity implements View.OnClickListener {

    private String TAG = MainFacebookJsonParse.class.getSimpleName();

    String[] objetosnext = new String [1000];
    private String paging;


    private String TokenPanteras = "EAACpeSbpCtABANbkpBzTLQVVYafNuoTufA8rNUG0WwSieUsovcckexpZAZBYzjkDPnBigN3PfR9M5UjAjMSoUWMUgVfnZB32WHs8wJP2vg9iEWVNtZBNH7wfQuO41haBkFYGugTL5WXZC6QXLAyxbW3ZCi1zY9ZAijvG04ZAxV8iPwZDZD";

    private static String graphURL = "https://graph.facebook.com/v3.2/me?fields=posts.limit(10)%7Bmessage%2Ccreated_time%2Cfull_picture%2Cpermalink_url%2Cfrom%7D&access_token=https://graph.facebook.com/v3.2/me?fields=posts.limit(25)%7Bmessage%2Ccreated_time%2Cfull_picture%2Cpermalink_url%7D&access_token=EAACpeSbpCtABAAfZAZAvc24ZCAgyorqSNZBBXRajUO6iyzVY5ZCI4hqjzjJK9J5kU8z1pZBHpeLUZBcGMsKQwX5Wq7S7S6bOonaMet009whdsXooWZBdZAik9C7tK2K85Apzjhh31xVXuosYw9kjhUQCXPKTssFaGa9kH5TM2ZAZCEetAZDZD";
    private static String graphURL2 = "https://graph.facebook.com/v3.2/me?fields=posts.limit(10)%7Bmessage%2Ccreated_time%2Cfull_picture%2Cpermalink_url%2Cfrom%7D&access_token=EAACpeSbpCtABAEZASJaoaCWeG6OVZAV5lpkis4rDMQlFx8NffeVJwJ7he4dl2KchkWymFC5MVV9y2iAKxPZCWoWJlWZCOICFH9RIxLIPkPAPz20owUgzeRjO0mIcV7ZAmk8gbroDAZAIZCu82StVD6F49oTRAbLXtxQmlVhWbgu5O5VWH6oiLfUfWWZChZCoS8zsZD";
    private static String Lestraigocancer = "https://graph.facebook.com/v3.2/me?fields=posts.limit(20)%7Bfrom%2Ccreated_time%2Cmessage%2Cfull_picture%2Cpermalink_url%2Cstatus_type%2Cstory%7D&access_token=EAACpeSbpCtABAP36ulrjfiyOJe2FeMs5wne94VGkr2wXkX5fbiKadXeMuXFztrZBLDMI5yCEe5n9DBO6lkI6JhHBt5uL9nbunOnSWYYLd67I6ZCSbYqTZCZBmwV8MBDCcAO349frRp2ZBbPtq7uSA7o6jNQR8aGMs5c5ZCYpj4vgZDZD";
    private static String LestraigocancerNext1 = "https://graph.facebook.com/v3.2/425822364223626/posts?pretty=0&fields=from%2Ccreated_time%2Cmessage%2Cfull_picture%2Cpermalink_url&limit=10&after=Q2c4U1pXNTBYM0YxWlhKNVgzTjBiM0o1WDJsa0R5UTBNalU0TWpJek5qUXlNak0yTWpZANkxUYzNNRFUyTnpJMU56VXdOakE1TXpVeE5EQVBER0ZA3YVY5emRHOXllVjlwWkE4ZAk5ESTFPREl5TXpZAME1qSXpOakkyWHpRNU5EZA3hOamc0TnpNeU5ERTNNdzhFZAEdsdFpRWlVvT0xuQVE9PQZDZD&access_token=EAACpeSbpCtABAP36ulrjfiyOJe2FeMs5wne94VGkr2wXkX5fbiKadXeMuXFztrZBLDMI5yCEe5n9DBO6lkI6JhHBt5uL9nbunOnSWYYLd67I6ZCSbYqTZCZBmwV8MBDCcAO349frRp2ZBbPtq7uSA7o6jNQR8aGMs5c5ZCYpj4vgZDZD";

    private static String Noesquesea = "https://graph.facebook.com/v3.2/me?fields=posts.limit(10)%7Bfrom%2Ccreated_time%2Cmessage%2Cfull_picture%2Cpermalink_url%2Cstatus_type%2Cstory%7D&access_token=EAACpeSbpCtABANbkpBzTLQVVYafNuoTufA8rNUG0WwSieUsovcckexpZAZBYzjkDPnBigN3PfR9M5UjAjMSoUWMUgVfnZB32WHs8wJP2vg9iEWVNtZBNH7wfQuO41haBkFYGugTL5WXZC6QXLAyxbW3ZCi1zY9ZAijvG04ZAxV8iPwZDZD";
    private static String NoesqueseaPreNext = "https://graph.facebook.com/v3.2/254951361282496/posts?access_token=EAACpeSbpCtABAAfZAZAvc24ZCAgyorqSNZBBXRajUO6iyzVY5ZCI4hqjzjJK9J5kU8z1pZBHpeLUZBcGMsKQwX5Wq7S7S6bOonaMet009whdsXooWZBdZAik9C7tK2K85Apzjhh31xVXuosYw9kjhUQCXPKTssFaGa9kH5TM2ZAZCEetAZDZD&pretty=0&fields=from%2Ccreated_time%2Cmessage%2Cfull_picture%2Cpermalink_url%2Cstatus_type%2Cstory&limit=10&after=";
    private  static String NoesqueseaNext = "https://graph.facebook.com/v3.2/254951361282496/posts?access_token=EAACpeSbpCtABAAfZAZAvc24ZCAgyorqSNZBBXRajUO6iyzVY5ZCI4hqjzjJK9J5kU8z1pZBHpeLUZBcGMsKQwX5Wq7S7S6bOonaMet009whdsXooWZBdZAik9C7tK2K85Apzjhh31xVXuosYw9kjhUQCXPKTssFaGa9kH5TM2ZAZCEetAZDZD&pretty=0&fields=from%2Ccreated_time%2Cmessage%2Cfull_picture%2Cpermalink_url%2Cstatus_type%2Cstory&limit=5&after=Q2c4U1pXNTBYM0YxWlhKNVgzTjBiM0o1WDJsa0R5TXlOVFE1TlRFek5qRXlPREkwT1RZANk5ETTJPREV5TlRZAME1Ua3dPRFUzTkRreE9ROE1ZAWEJwWDNOMGIzSjVYMmxrRHlBeU5UUTVOVEV6TmpFeU9ESTBPVFpmTVRrd05qY3pNekV3TWpjM01EazNNZAzhFZAEdsdFpRWmNVb3JyQVE9PQZDZD";

    private static  String preafter = "https://graph.facebook.com/v3.2/254951361282496/posts?access_token=EAACpeSbpCtABAAfZAZAvc24ZCAgyorqSNZBBXRajUO6iyzVY5ZCI4hqjzjJK9J5kU8z1pZBHpeLUZBcGMsKQwX5Wq7S7S6bOonaMet009whdsXooWZBdZAik9C7tK2K85Apzjhh31xVXuosYw9kjhUQCXPKTssFaGa9kH5TM2ZAZCEetAZDZD&pretty=0&fields=from%2Ccreated_time%2Cmessage%2Cfull_picture%2Cpermalink_url%2Cstatus_type%2Cstory&limit=10&after=";



    private View botonNext1;

    JSONObject jsonObjectTextosnext;
    JSONArray jsonArray;
    TextView pagina;
    TextView textViewPageNamenext;
    TextView textViewCreacionnext;
    TextView textViewStatus;
    TextView textViewStory;
    TextView textViewMessagenext;
    TextView textViewLinkURLnext;
    ImageView FeedImagenext;
    String FeedImage2;

    ArrayList<String> postsList = new ArrayList<>();


    private ProgressDialog pDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_graphapi);

        View botonNext1 =findViewById(R.id.BotonNext1);
        botonNext1.setOnClickListener(this);

        postsList = new ArrayList<>();

        new AsyncTaskExample().execute(Noesquesea);
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
            pDialog = new ProgressDialog(MainFacebookJsonParse.this);
            pDialog.setIcon(R.mipmap.ic_launcher_round);
            pDialog.setTitle("Espera un poco");
            pDialog.setMessage("Estoy descargando noticias...");
            pDialog.show();

        }

        @Override
        protected String[] doInBackground(String... Big) {

////////////////////////////////////////////////////////////////////////////////////////////////////
            try {
                jsonObjectTextosnext = JsonParse.readJsonFromUrl(Noesquesea);
                paging = jsonObjectTextosnext.getJSONObject("posts").getJSONObject("paging").getJSONObject("cursors").getString("after");
                objetosnext[0] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(0).getJSONObject("from").getString("name");
                objetosnext[1] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(0).getString("created_time");
                objetosnext[2] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(0).getString("status_type");
                objetosnext[6] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(0).getString("permalink_url");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextosnext = JsonParse.readJsonFromUrl(Noesquesea);
                objetosnext[3] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(0).getString("story");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextosnext = JsonParse.readJsonFromUrl(Noesquesea);
                objetosnext[4] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(0).getString("message");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextosnext = JsonParse.readJsonFromUrl(Noesquesea);
                objetosnext[5] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(0).getString("full_picture");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

            try {
                jsonObjectTextosnext = JsonParse.readJsonFromUrl(Noesquesea);

                objetosnext[7] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(1).getJSONObject("from").getString("name");
                objetosnext[8] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(1).getString("created_time");
                objetosnext[9] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(1).getString("status_type");
                objetosnext[13] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(1).getString("permalink_url");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextosnext = JsonParse.readJsonFromUrl(Noesquesea);
                objetosnext[10] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(1).getString("story");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextosnext = JsonParse.readJsonFromUrl(Noesquesea);
                objetosnext[11] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(1).getString("message");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextosnext = JsonParse.readJsonFromUrl(Noesquesea);
                objetosnext[12] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(1).getString("full_picture");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

            try {
                jsonObjectTextosnext = JsonParse.readJsonFromUrl(Noesquesea);

                objetosnext[14] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(2).getJSONObject("from").getString("name");
                objetosnext[15] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(2).getString("created_time");
                objetosnext[16] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(2).getString("status_type");
                objetosnext[20] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(2).getString("permalink_url");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextosnext = JsonParse.readJsonFromUrl(Noesquesea);
                objetosnext[17] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(2).getString("story");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextosnext = JsonParse.readJsonFromUrl(Noesquesea);
                objetosnext[18] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(2).getString("message");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextosnext = JsonParse.readJsonFromUrl(Noesquesea);
                objetosnext[19] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(2).getString("full_picture");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

            try {
                jsonObjectTextosnext = JsonParse.readJsonFromUrl(Noesquesea);

                objetosnext[21] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(3).getJSONObject("from").getString("name");
                objetosnext[22] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(3).getString("created_time");
                objetosnext[23] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(3).getString("status_type");
                objetosnext[27] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(3).getString("permalink_url");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextosnext = JsonParse.readJsonFromUrl(Noesquesea);
                objetosnext[24] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(3).getString("story");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextosnext = JsonParse.readJsonFromUrl(Noesquesea);
                objetosnext[25] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(3).getString("message");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextosnext = JsonParse.readJsonFromUrl(Noesquesea);
                objetosnext[26] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(3).getString("full_picture");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

            try {
                jsonObjectTextosnext = JsonParse.readJsonFromUrl(Noesquesea);

                objetosnext[28] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(4).getJSONObject("from").getString("name");
                objetosnext[29] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(4).getString("created_time");
                objetosnext[30] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(4).getString("status_type");
                objetosnext[34] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(4).getString("permalink_url");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextosnext = JsonParse.readJsonFromUrl(Noesquesea);
                objetosnext[31] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(4).getString("story");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextosnext = JsonParse.readJsonFromUrl(Noesquesea);
                objetosnext[32] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(4).getString("message");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextosnext = JsonParse.readJsonFromUrl(Noesquesea);
                objetosnext[33] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(4).getString("full_picture");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

            try {
                jsonObjectTextosnext = JsonParse.readJsonFromUrl(Noesquesea);

                objetosnext[35] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(5).getJSONObject("from").getString("name");
                objetosnext[36] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(5).getString("created_time");
                objetosnext[37] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(5).getString("status_type");
                objetosnext[41] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(5).getString("permalink_url");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextosnext = JsonParse.readJsonFromUrl(Noesquesea);
                objetosnext[38] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(5).getString("story");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextosnext = JsonParse.readJsonFromUrl(Noesquesea);
                objetosnext[39] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(5).getString("message");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextosnext = JsonParse.readJsonFromUrl(Noesquesea);
                objetosnext[40] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(5).getString("full_picture");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

            try {
                jsonObjectTextosnext = JsonParse.readJsonFromUrl(Noesquesea);

                objetosnext[42] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(6).getJSONObject("from").getString("name");
                objetosnext[43] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(6).getString("created_time");
                objetosnext[44] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(6).getString("status_type");
                objetosnext[48] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(6).getString("permalink_url");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextosnext = JsonParse.readJsonFromUrl(Noesquesea);
                objetosnext[45] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(6).getString("story");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextosnext = JsonParse.readJsonFromUrl(Noesquesea);
                objetosnext[46] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(6).getString("message");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextosnext = JsonParse.readJsonFromUrl(Noesquesea);
                objetosnext[47] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(6).getString("full_picture");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

            try {
                jsonObjectTextosnext = JsonParse.readJsonFromUrl(Noesquesea);

                objetosnext[49] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(7).getJSONObject("from").getString("name");
                objetosnext[50] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(7).getString("created_time");
                objetosnext[51] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(7).getString("status_type");
                objetosnext[55] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(7).getString("permalink_url");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextosnext = JsonParse.readJsonFromUrl(Noesquesea);
                objetosnext[52] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(7).getString("story");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextosnext = JsonParse.readJsonFromUrl(Noesquesea);
                objetosnext[53] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(7).getString("message");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextosnext = JsonParse.readJsonFromUrl(Noesquesea);
                objetosnext[54] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(7).getString("full_picture");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

            try {
                jsonObjectTextosnext = JsonParse.readJsonFromUrl(Noesquesea);

                objetosnext[56] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(8).getJSONObject("from").getString("name");
                objetosnext[57] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(8).getString("created_time");
                objetosnext[58] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(8).getString("status_type");
                objetosnext[62] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(8).getString("permalink_url");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextosnext = JsonParse.readJsonFromUrl(Noesquesea);
                objetosnext[59] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(8).getString("story");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextosnext = JsonParse.readJsonFromUrl(Noesquesea);
                objetosnext[60] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(8).getString("message");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextosnext = JsonParse.readJsonFromUrl(Noesquesea);
                objetosnext[61] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(8).getString("full_picture");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

            try {
                jsonObjectTextosnext = JsonParse.readJsonFromUrl(Noesquesea);

                objetosnext[63] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(9).getJSONObject("from").getString("name");
                objetosnext[64] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(9).getString("created_time");
                objetosnext[65] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(9).getString("status_type");
                objetosnext[69] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(9).getString("permalink_url");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextosnext = JsonParse.readJsonFromUrl(Noesquesea);
                objetosnext[66] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(9).getString("story");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextosnext = JsonParse.readJsonFromUrl(Noesquesea);
                objetosnext[67] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(9).getString("message");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObjectTextosnext = JsonParse.readJsonFromUrl(Noesquesea);
                objetosnext[68] = jsonObjectTextosnext.getJSONObject("posts").getJSONArray("data").getJSONObject(9).getString("full_picture");


            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////






            return objetosnext;
        }

        @Override
        protected void onPostExecute(String[] stringFromDoInBackground) {

            pDialog.dismiss();
///////////////////////////////////////////////////////////////////////////////////////////////////
            textViewPageNamenext = findViewById(R.id.textViewPageName);
            textViewCreacionnext = findViewById(R.id.textViewCreacion);
            textViewStatus = findViewById(R.id.textViewStatusType);
            textViewStory = findViewById(R.id.textViewStory);
            textViewMessagenext = findViewById(R.id.textViewMessage);
            FeedImagenext = findViewById(R.id.FeedImage);
            textViewLinkURLnext = findViewById(R.id.textViewLinkURL);


            textViewPageNamenext.setText(stringFromDoInBackground[0]);
            textViewPageNamenext.setVisibility(View.VISIBLE);

            textViewCreacionnext.setText(stringFromDoInBackground[1]);
            textViewCreacionnext.setVisibility(View.VISIBLE);

            textViewStatus.setText(stringFromDoInBackground[2]);
            textViewStatus.setVisibility(View.VISIBLE);

            textViewStory.setText(stringFromDoInBackground[3]);
            textViewStory.setVisibility(View.VISIBLE);

            textViewMessagenext.setText(stringFromDoInBackground[4]);
            textViewMessagenext.setVisibility(View.VISIBLE);

            Glide.with(MainFacebookJsonParse.this).load(stringFromDoInBackground[5]).into(FeedImagenext);
            FeedImagenext.setVisibility(View.VISIBLE);

            textViewLinkURLnext.setText(stringFromDoInBackground[6]);
            textViewLinkURLnext.setVisibility(View.VISIBLE);


///////////////////////////////////////////////////////////////////////////////////////////////////
            textViewPageNamenext = findViewById(R.id.textViewPageName1);
            textViewCreacionnext = findViewById(R.id.textViewCreacion1);
            textViewStatus = findViewById(R.id.textViewStatusType1);
            textViewStory = findViewById(R.id.textViewStory1);
            textViewMessagenext = findViewById(R.id.textViewMessage1);
            FeedImagenext = findViewById(R.id.FeedImage1);
            textViewLinkURLnext = findViewById(R.id.textViewLinkURL1);


            textViewPageNamenext.setText(stringFromDoInBackground[7]);
            textViewPageNamenext.setVisibility(View.VISIBLE);

            textViewCreacionnext.setText(stringFromDoInBackground[8]);
            textViewCreacionnext.setVisibility(View.VISIBLE);

            textViewStatus.setText(stringFromDoInBackground[9]);
            textViewStatus.setVisibility(View.VISIBLE);

            textViewStory.setText(stringFromDoInBackground[10]);
            textViewStory.setVisibility(View.VISIBLE);

            textViewMessagenext.setText(stringFromDoInBackground[11]);
            textViewMessagenext.setVisibility(View.VISIBLE);

            Glide.with(MainFacebookJsonParse.this).load(stringFromDoInBackground[12]).into(FeedImagenext);
            FeedImagenext.setVisibility(View.VISIBLE);

            textViewLinkURLnext.setText(stringFromDoInBackground[13]);
            textViewLinkURLnext.setVisibility(View.VISIBLE);

///////////////////////////////////////////////////////////////////////////////////////////////////
            textViewPageNamenext = findViewById(R.id.textViewPageName2);
            textViewCreacionnext = findViewById(R.id.textViewCreacion2);
            textViewStatus = findViewById(R.id.textViewStatusType2);
            textViewStory = findViewById(R.id.textViewStory2);
            textViewMessagenext = findViewById(R.id.textViewMessage2);
            FeedImagenext = findViewById(R.id.FeedImage2);
            textViewLinkURLnext = findViewById(R.id.textViewLinkURL2);


            textViewPageNamenext.setText(stringFromDoInBackground[14]);
            textViewPageNamenext.setVisibility(View.VISIBLE);

            textViewCreacionnext.setText(stringFromDoInBackground[15]);
            textViewCreacionnext.setVisibility(View.VISIBLE);

            textViewStatus.setText(stringFromDoInBackground[16]);
            textViewStatus.setVisibility(View.VISIBLE);

            textViewStory.setText(stringFromDoInBackground[17]);
            textViewStory.setVisibility(View.VISIBLE);

            textViewMessagenext.setText(stringFromDoInBackground[18]);
            textViewMessagenext.setVisibility(View.VISIBLE);

            Glide.with(MainFacebookJsonParse.this).load(stringFromDoInBackground[19]).into(FeedImagenext);
            FeedImagenext.setVisibility(View.VISIBLE);

            textViewLinkURLnext.setText(stringFromDoInBackground[20]);
            textViewLinkURLnext.setVisibility(View.VISIBLE);

///////////////////////////////////////////////////////////////////////////////////////////////////
            textViewPageNamenext = findViewById(R.id.textViewPageName3);
            textViewCreacionnext = findViewById(R.id.textViewCreacion3);
            textViewStatus = findViewById(R.id.textViewStatusType3);
            textViewStory = findViewById(R.id.textViewStory3);
            textViewMessagenext = findViewById(R.id.textViewMessage3);
            FeedImagenext = findViewById(R.id.FeedImage3);
            textViewLinkURLnext = findViewById(R.id.textViewLinkURL3);


            textViewPageNamenext.setText(stringFromDoInBackground[21]);
            textViewPageNamenext.setVisibility(View.VISIBLE);

            textViewCreacionnext.setText(stringFromDoInBackground[22]);
            textViewCreacionnext.setVisibility(View.VISIBLE);

            textViewStatus.setText(stringFromDoInBackground[23]);
            textViewStatus.setVisibility(View.VISIBLE);

            textViewStory.setText(stringFromDoInBackground[24]);
            textViewStory.setVisibility(View.VISIBLE);

            textViewMessagenext.setText(stringFromDoInBackground[25]);
            textViewMessagenext.setVisibility(View.VISIBLE);

            Glide.with(MainFacebookJsonParse.this).load(stringFromDoInBackground[26]).into(FeedImagenext);
            FeedImagenext.setVisibility(View.VISIBLE);

            textViewLinkURLnext.setText(stringFromDoInBackground[27]);
            textViewLinkURLnext.setVisibility(View.VISIBLE);



///////////////////////////////////////////////////////////////////////////////////////////////////
            textViewPageNamenext = findViewById(R.id.textViewPageName4);
            textViewCreacionnext = findViewById(R.id.textViewCreacion4);
            textViewStatus = findViewById(R.id.textViewStatusType4);
            textViewStory = findViewById(R.id.textViewStory4);
            textViewMessagenext = findViewById(R.id.textViewMessage4);
            FeedImagenext = findViewById(R.id.FeedImage4);
            textViewLinkURLnext = findViewById(R.id.textViewLinkURL4);


            textViewPageNamenext.setText(stringFromDoInBackground[28]);
            textViewPageNamenext.setVisibility(View.VISIBLE);

            textViewCreacionnext.setText(stringFromDoInBackground[29]);
            textViewCreacionnext.setVisibility(View.VISIBLE);

            textViewStatus.setText(stringFromDoInBackground[30]);
            textViewStatus.setVisibility(View.VISIBLE);

            textViewStory.setText(stringFromDoInBackground[31]);
            textViewStory.setVisibility(View.VISIBLE);

            textViewMessagenext.setText(stringFromDoInBackground[32]);
            textViewMessagenext.setVisibility(View.VISIBLE);

            Glide.with(MainFacebookJsonParse.this).load(stringFromDoInBackground[33]).into(FeedImagenext);
            FeedImagenext.setVisibility(View.VISIBLE);

            textViewLinkURLnext.setText(stringFromDoInBackground[34]);
            textViewLinkURLnext.setVisibility(View.VISIBLE);


///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
            textViewPageNamenext = findViewById(R.id.textViewPageName5);
            textViewCreacionnext = findViewById(R.id.textViewCreacion5);
            textViewStatus = findViewById(R.id.textViewStatusType5);
            textViewStory = findViewById(R.id.textViewStory5);
            textViewMessagenext = findViewById(R.id.textViewMessage5);
            FeedImagenext = findViewById(R.id.FeedImage5);
            textViewLinkURLnext = findViewById(R.id.textViewLinkURL5);


            textViewPageNamenext.setText(stringFromDoInBackground[35]);
            textViewPageNamenext.setVisibility(View.VISIBLE);

            textViewCreacionnext.setText(stringFromDoInBackground[36]);
            textViewCreacionnext.setVisibility(View.VISIBLE);

            textViewStatus.setText(stringFromDoInBackground[37]);
            textViewStatus.setVisibility(View.VISIBLE);

            textViewStory.setText(stringFromDoInBackground[38]);
            textViewStory.setVisibility(View.VISIBLE);

            textViewMessagenext.setText(stringFromDoInBackground[39]);
            textViewMessagenext.setVisibility(View.VISIBLE);

            Glide.with(MainFacebookJsonParse.this).load(stringFromDoInBackground[40]).into(FeedImagenext);
            FeedImagenext.setVisibility(View.VISIBLE);

            textViewLinkURLnext.setText(stringFromDoInBackground[41]);
            textViewLinkURLnext.setVisibility(View.VISIBLE);

            ///////////////////////////////////////////////////////////////////////////////////////////////////
            textViewPageNamenext = findViewById(R.id.textViewPageName6);
            textViewCreacionnext = findViewById(R.id.textViewCreacion6);
            textViewStatus = findViewById(R.id.textViewStatusType6);
            textViewStory = findViewById(R.id.textViewStory6);
            textViewMessagenext = findViewById(R.id.textViewMessage6);
            FeedImagenext = findViewById(R.id.FeedImage6);
            textViewLinkURLnext = findViewById(R.id.textViewLinkURL6);


            textViewPageNamenext.setText(stringFromDoInBackground[42]);
            textViewPageNamenext.setVisibility(View.VISIBLE);

            textViewCreacionnext.setText(stringFromDoInBackground[43]);
            textViewCreacionnext.setVisibility(View.VISIBLE);

            textViewStatus.setText(stringFromDoInBackground[44]);
            textViewStatus.setVisibility(View.VISIBLE);

            textViewStory.setText(stringFromDoInBackground[45]);
            textViewStory.setVisibility(View.VISIBLE);

            textViewMessagenext.setText(stringFromDoInBackground[46]);
            textViewMessagenext.setVisibility(View.VISIBLE);

            Glide.with(MainFacebookJsonParse.this).load(stringFromDoInBackground[47]).into(FeedImagenext);
            FeedImagenext.setVisibility(View.VISIBLE);

            textViewLinkURLnext.setText(stringFromDoInBackground[48]);
            textViewLinkURLnext.setVisibility(View.VISIBLE);

            ///////////////////////////////////////////////////////////////////////////////////////////////////
            textViewPageNamenext = findViewById(R.id.textViewPageName7);
            textViewCreacionnext = findViewById(R.id.textViewCreacion7);
            textViewStatus = findViewById(R.id.textViewStatusType7);
            textViewStory = findViewById(R.id.textViewStory7);
            textViewMessagenext = findViewById(R.id.textViewMessage7);
            FeedImagenext = findViewById(R.id.FeedImage7);
            textViewLinkURLnext = findViewById(R.id.textViewLinkURL7);


            textViewPageNamenext.setText(stringFromDoInBackground[49]);
            textViewPageNamenext.setVisibility(View.VISIBLE);

            textViewCreacionnext.setText(stringFromDoInBackground[50]);
            textViewCreacionnext.setVisibility(View.VISIBLE);

            textViewStatus.setText(stringFromDoInBackground[51]);
            textViewStatus.setVisibility(View.VISIBLE);

            textViewStory.setText(stringFromDoInBackground[52]);
            textViewStory.setVisibility(View.VISIBLE);

            textViewMessagenext.setText(stringFromDoInBackground[53]);
            textViewMessagenext.setVisibility(View.VISIBLE);

            Glide.with(MainFacebookJsonParse.this).load(stringFromDoInBackground[54]).into(FeedImagenext);
            FeedImagenext.setVisibility(View.VISIBLE);

            textViewLinkURLnext.setText(stringFromDoInBackground[55]);
            textViewLinkURLnext.setVisibility(View.VISIBLE);

            ///////////////////////////////////////////////////////////////////////////////////////////////////
            textViewPageNamenext = findViewById(R.id.textViewPageName8);
            textViewCreacionnext = findViewById(R.id.textViewCreacion8);
            textViewStatus = findViewById(R.id.textViewStatusType8);
            textViewStory = findViewById(R.id.textViewStory8);
            textViewMessagenext = findViewById(R.id.textViewMessage8);
            FeedImagenext = findViewById(R.id.FeedImage8);
            textViewLinkURLnext = findViewById(R.id.textViewLinkURL8);


            textViewPageNamenext.setText(stringFromDoInBackground[56]);
            textViewPageNamenext.setVisibility(View.VISIBLE);

            textViewCreacionnext.setText(stringFromDoInBackground[57]);
            textViewCreacionnext.setVisibility(View.VISIBLE);

            textViewStatus.setText(stringFromDoInBackground[58]);
            textViewStatus.setVisibility(View.VISIBLE);

            textViewStory.setText(stringFromDoInBackground[59]);
            textViewStory.setVisibility(View.VISIBLE);

            textViewMessagenext.setText(stringFromDoInBackground[60]);
            textViewMessagenext.setVisibility(View.VISIBLE);

            Glide.with(MainFacebookJsonParse.this).load(stringFromDoInBackground[61]).into(FeedImagenext);
            FeedImagenext.setVisibility(View.VISIBLE);

            textViewLinkURLnext.setText(stringFromDoInBackground[62]);
            textViewLinkURLnext.setVisibility(View.VISIBLE);

            ///////////////////////////////////////////////////////////////////////////////////////////////////
            textViewPageNamenext = findViewById(R.id.textViewPageName9);
            textViewCreacionnext = findViewById(R.id.textViewCreacion9);
            textViewStatus = findViewById(R.id.textViewStatusType9);
            textViewStory = findViewById(R.id.textViewStory9);
            textViewMessagenext = findViewById(R.id.textViewMessage9);
            FeedImagenext = findViewById(R.id.FeedImage9);
            textViewLinkURLnext = findViewById(R.id.textViewLinkURL9);


            textViewPageNamenext.setText(stringFromDoInBackground[63]);
            textViewPageNamenext.setVisibility(View.VISIBLE);

            textViewCreacionnext.setText(stringFromDoInBackground[64]);
            textViewCreacionnext.setVisibility(View.VISIBLE);

            textViewStatus.setText(stringFromDoInBackground[65]);
            textViewStatus.setVisibility(View.VISIBLE);

            textViewStory.setText(stringFromDoInBackground[66]);
            textViewStory.setVisibility(View.VISIBLE);

            textViewMessagenext.setText(stringFromDoInBackground[67]);
            textViewMessagenext.setVisibility(View.VISIBLE);

            Glide.with(MainFacebookJsonParse.this).load(stringFromDoInBackground[68]).into(FeedImagenext);
            FeedImagenext.setVisibility(View.VISIBLE);

            textViewLinkURLnext.setText(stringFromDoInBackground[69]);
            textViewLinkURLnext.setVisibility(View.VISIBLE);


        }


    }
    @Override
    public void onClick(View v) {
        Intent miIntent = new Intent(MainFacebookJsonParse.this, MainFacebookJsonParseNext1.class);
//        Bundle miBundle = new Bundle();
//        try {
//            miIntent.putExtra("pagina", jsonObjectTextosnext.getJSONObject("posts").getJSONObject("paging").getJSONObject("cursors").getString("after"));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        miIntent.putExtras(miBundle);
        startActivity(miIntent);

    }

}