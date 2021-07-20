package com.Pericos.ITSVC.App.PantallaMain;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.Pericos.ITSVC.App.R;

public class SocialActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);

       /* View cardviewface = findViewById(R.id.relativeredesfacebook1);
        cardviewface.setOnClickListener(this);

        View cardviewinsta = findViewById(R.id.relativeredesinstagram1);
        cardviewinsta.setOnClickListener(this);

        View cardviewtwitter = findViewById(R.id.relativeredestwitter1);
        cardviewtwitter.setOnClickListener(this);

        View cardviewyou = findViewById(R.id.relativeredesyoutube1);
        cardviewyou.setOnClickListener(this);*/

    }

    @Override
    public void onClick(View v) {

//        if(v.getId()==findViewById(R.id.relativeredesfacebook1).getId())
//        {
//            CardView cardView = (CardView) findViewById(R.id.relativeredesfacebook1);
//            Uri uriUrl = Uri.parse("https://www.facebook.com/ITSHPUE/?ref=ts&fref=ts");
//            Intent facebook = new Intent(Intent.ACTION_VIEW, uriUrl);
//            startActivity(facebook);
//        }
//        if (v.getId() == findViewById(R.id.relativeredesinstagram1).getId())
//        {
//            CardView cardView = (CardView) findViewById(R.id.relativeredesinstagram1);
//
//            Uri uriUrl = Uri.parse("https://www.instagram.com/panterastec/");
//            Intent instagram = new Intent(Intent.ACTION_VIEW, uriUrl);
//            startActivity(instagram);
//        }
//
//        if (v.getId() == findViewById(R.id.relativeredestwitter1).getId()) {
//            CardView cardView = (CardView) findViewById(R.id.relativeredestwitter1);
//
//            Uri uriUrl = Uri.parse("https://twitter.com/Tec_huauchinang");
//            Intent twitter = new Intent(Intent.ACTION_VIEW, uriUrl);
//            startActivity(twitter);
//        }
//
//        if (v.getId() == findViewById(R.id.relativeredesyoutube1).getId()) {
//            CardView cardView = (CardView) findViewById(R.id.relativeredesyoutube1);
//
//            Uri uriUrl = Uri.parse("https://www.youtube.com/channel/UChaFB52YzV_I857IcR6FbmQ");
//            Intent youtube = new Intent(Intent.ACTION_VIEW, uriUrl);
//            startActivity(youtube);
//        }
    }
}
