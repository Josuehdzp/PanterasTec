package com.Pericos.ITSVC.App.Facebook;


import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.Pericos.ITSVC.App.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterPosts extends RecyclerView.Adapter<AdapterPosts.ViewHolder>{

    public static final String KEY_NAME = "name";
    public static final String KEY_CREACTED_TIME = "created_time";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_FULL_PICTURE = "full_picture";
    public static final String KEY_PERMA_LINK_URL = "permalink_url";

    private List<PostsItems> postsItemss;
    private Context context;



    public AdapterPosts(List<PostsItems> postsItemss, Context context) {
        this.postsItemss = postsItemss;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_graphapi, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final PostsItems postsItemsList = postsItemss.get(position);
//        PostsItems adapterPosts = postsItemsList.get(position);

        holder.objetoname.setText(postsItemsList.getName());

        holder.objetocreated_name.setText(postsItemsList.getCreated_time());

        Glide.with(context).load(postsItemsList.getFull_picture()).into(holder.full_picture);

        holder.objetomesage.setText(postsItemsList.getMessage());

        holder.objetolink.setText(postsItemsList.getLink());

//        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                PostsItems postsItems1 = postsItemss.get(position);
//                Intent skipIntent = new Intent(v.getContext(),)
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return postsItemss.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView objetoname;
        public TextView objetocreated_name;
        public ImageView full_picture;
        public TextView objetomesage;
        public TextView objetolink;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
///////////////////ver objetos
            objetoname = itemView.findViewById(R.id.textViewPageName);
            objetocreated_name = itemView.findViewById(R.id.textViewCreacion);
            objetomesage = itemView.findViewById(R.id.textViewMessage);
            objetolink = itemView.findViewById(R.id.textViewLinkURL);

        }
    }
}

