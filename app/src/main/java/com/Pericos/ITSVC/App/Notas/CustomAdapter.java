package com.Pericos.ITSVC.App.Notas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.Pericos.ITSVC.App.R;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<StringsNotas> dataSet;
    Boolean check=false;
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titulo,cuerpo,fecha;

        RelativeLayout expandable;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.expandable= itemView.findViewById(R.id.expandableLayout);
            this.titulo= itemView.findViewById(R.id.wordtext);

            this.cuerpo = itemView.findViewById(R.id.meaningtext);
            this.fecha= itemView.findViewById(R.id.meaningtext2);

        }
    }

    public CustomAdapter(ArrayList<StringsNotas> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notas_card_view_row, parent, false);

                final MyViewHolder myViewHolder = new MyViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!check)
                {
                    myViewHolder.expandable.animate()
                            .alpha(0.0f)
                            .setDuration(1000);


                    myViewHolder.expandable.setVisibility(View.VISIBLE);
                        check=true;
                  //  myViewHolder.schedule.setVisibility(View.VISIBLE);

                }
                else {
                    myViewHolder.expandable.setVisibility(View.VISIBLE);
                    myViewHolder.expandable.animate()
                            .alpha(1.0f)
                            .setDuration(1000);

                    check=false;

                }
            }
        });

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView Titulo= holder.titulo;
        TextView Cuerpo = holder.cuerpo;
        TextView Fecha = holder.fecha;

        Titulo.setText(dataSet.get(listPosition).getTituloNota());
        Cuerpo.setText(dataSet.get(listPosition).getCuerpoNota());
        Fecha.setText(dataSet.get(listPosition).getFechaNota());

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}