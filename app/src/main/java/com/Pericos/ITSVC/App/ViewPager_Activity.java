package com.Pericos.ITSVC.App;

import android.content.Context;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ViewPager_Activity extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private Integer[] images = {
            R.drawable.sliderregresoaclasesfiltro, R.drawable.logosisitemasdetransparencia,R.drawable.slideralimentarias
            , R.drawable.slidersistemas , R.drawable.sliderforestal, R.drawable.slidergeociencias};

    public ViewPager_Activity(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.activity_view_pager,null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageViewPager);
        imageView.setImageResource(images[position]);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position ==0){
                    //Toast.makeText(context,"Portada seleccionada",Toast.LENGTH_SHORT).show();
                } else if (position == 1){
                    //Toast.makeText(context,"Portada2 seleccionada",Toast.LENGTH_SHORT).show();
                } else if (position ==2){
                    //Toast.makeText(context,"Portada3 seleccionada",Toast.LENGTH_SHORT).show();
                } else if (position ==3){
                    //Toast.makeText(context,"Portada4 seleccionada",Toast.LENGTH_SHORT).show();
                } else if (position ==4){
                    //Toast.makeText(context,"Portada5 seleccionada",Toast.LENGTH_SHORT).show();
                }
                else {
                    //Toast.makeText(context,"Portada6 seleccionada",Toast.LENGTH_SHORT).show();
                }
            }
        });


        ViewPager vp =(ViewPager) container;
        vp.addView(view, 0);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);


    }
}















