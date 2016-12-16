package com.example.rafaelcomar.realdortmundes.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rafaelcomar.realdortmundes.R;
import com.example.rafaelcomar.realdortmundes.model.Atleta;
import com.squareup.picasso.Picasso;


import java.util.List;


/**
 * Created by rafael.comar on 14/12/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context myContext;
    private List<Atleta> listaAtletas;

    public MyAdapter(Context context) {
        myContext = context;
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNomeAtleta;
        private TextView txtPosicaoAtleta;
        private TextView txtIdadeAtleta;
        private ImageView imgAtleta;

        public ViewHolder(View v) {
            super(v);
            txtNomeAtleta = (TextView) v.findViewById(R.id.txtNomeAtletaCard);
            txtPosicaoAtleta = (TextView) v.findViewById(R.id.txtPosicaoAtletaCard);
            txtIdadeAtleta = (TextView) v.findViewById(R.id.txtIdadeAtletaCard);
            imgAtleta = (ImageView) v.findViewById(R.id.imgAtletaCard);
        }
    }



    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.atletas, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
//        holder.mTextView.setText(mDataset[position]);
        if (listaAtletas != null){
            Atleta atleta = listaAtletas.get(position);
            holder.txtNomeAtleta.setText(atleta.getNome());
            Picasso.with(myContext)
                    .load(atleta.getThumbnail())
                    .placeholder(R.drawable.placeholder)
                    .into(holder.imgAtleta);

        }else{
            holder.txtNomeAtleta.setText("teste");
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
//        return mDataset.length;
        if (listaAtletas == null ) return 0;
        return listaAtletas.size();
    }

    public void update(List<Atleta> atletas){
        listaAtletas = atletas;
        notifyDataSetChanged();

    }
}
