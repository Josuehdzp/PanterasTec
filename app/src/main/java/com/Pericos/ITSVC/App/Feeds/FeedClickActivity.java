package com.Pericos.ITSVC.App.Feeds;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.Pericos.ITSVC.App.R;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;


public class FeedClickActivity extends AppCompatActivity {

    public static final String FULL_PICTURE = "full";


    ImageView FullPictureHead;
    CardView contenedorCompleto;
    CardView Compartir;
    CardView VerFacebook;
    TextView CreatedTime;
    TextView StatusType;
    TextView Story;
    TextView Message;
    RoundedImageView FullpicturePrincipal;
    TextView PermalinkUrl;
    TextView TituloFeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed_completo_activity);

        contenedorCompleto = findViewById(R.id.idFeedCompletoCard);
        TituloFeed = findViewById(R.id.idFeedCompletoTitulo);
        FullPictureHead =  findViewById(R.id.idFeedCompletoFullpictureHeader);
        CreatedTime =  findViewById(R.id.idFeedCompletoCreacion);
        StatusType = findViewById(R.id.idFeedCompletoStatus);
        Story = findViewById(R.id.idFeedCompletoStory);
        Message = findViewById(R.id.idFeedCompletoMensaje);
        Compartir =  findViewById(R.id.idFeedCompletoCompartir);
        VerFacebook =  findViewById(R.id.idFeedCompletoVerFacebook);
        PermalinkUrl =  findViewById(R.id.idFeedCompletoPermalink);
        FullpicturePrincipal = findViewById(R.id.idFeedCompletoFullpicture);


        Intent intent = getIntent();
        final String creacion = intent.getStringExtra(FeedFacebookAdapterTest.CREATED_TIME);
        final String estado = intent.getStringExtra(FeedFacebookAdapterTest.STATUS_TYPE);
        final String historia = intent.getStringExtra(FeedFacebookAdapterTest.STORY);
        final String mensaje = intent.getStringExtra(FeedFacebookAdapterTest.MESSAGE);
        final String image = intent.getStringExtra(FeedFacebookAdapterTest.FULL_PICTURE);
        final String link = intent.getStringExtra(FeedFacebookAdapterTest.PERMALINK_URL);

        contenedorCompleto.setAnimation(AnimationUtils.loadAnimation(this,R.anim.fade_scale_animation));
        FullpicturePrincipal.setAnimation(AnimationUtils.loadAnimation(this,R.anim.fade_transition_animation));
        FullPictureHead.setAnimation(AnimationUtils.loadAnimation(this,R.anim.fade_scale_animation));

        TituloFeed.setText(mensaje);
        TituloFeed.setSelected(true);
        //TituloFeed.setMovementMethod(new ScrollingMovementMethod());

        Picasso.with(this)
                .load(image)
                .into(FullPictureHead);

        Picasso.with(this)
                .load(image)
                .into(FullpicturePrincipal);

        CreatedTime.setText(creacion);
        StatusType.setText(estado);
        Story.setText(historia);
        Message.setText(mensaje);
        Message.setMovementMethod(LinkMovementMethod.getInstance());
        PermalinkUrl.setText(link);


        FullpicturePrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle(); //Bundle cotiene los datos a mandar a el dialog
                bundle.putString("full_picture",image);


                DialogoFeeds dialog = new DialogoFeeds(); //Create a new Dialog
                dialog.setArguments(bundle);
                dialog.show(getSupportFragmentManager(), "MY_DIALOG_TAG"); //Inflar el dialog
            }
        });

        VerFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = link;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });


        Compartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey! dale un viztazo a esta publicacion: " + link);
                Intent chooser = Intent.createChooser(shareIntent, "Compartir via:");
                if (shareIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(chooser);
                }
            }
        });


    }
}
