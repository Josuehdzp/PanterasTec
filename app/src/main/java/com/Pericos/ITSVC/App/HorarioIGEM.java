package com.Pericos.ITSVC.App;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;



public class HorarioIGEM extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.horario_igem);

        /*PDFView pdfView = findViewById(R.id.pdfViewigem);

        pdfView.fromAsset("IGEM_2019.pdf")

                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(0)
                .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                .password(null)
                .scrollHandle(null)
                .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                // spacing between pages in dp. To define spacing color, set view background
                .spacing(8)
                .pageFitPolicy(FitPolicy.WIDTH)
                .load();*/
    }


}
