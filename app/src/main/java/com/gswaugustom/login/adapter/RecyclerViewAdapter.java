package com.gswaugustom.login.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gswaugustom.login.R;
import com.gswaugustom.login.models.Consumidor;


import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<Consumidor> mData;


    public RecyclerViewAdapter(Context mContext, List<Consumidor> mData) {
        this.mContext = mContext;
        this.mData = mData;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Estou inflando o layout criado para o consumidor no xml abaixo.
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_consumidor, parent, false);
        final MyViewHolder viewHolder = new MyViewHolder(v);

        viewHolder.cosumidor_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (viewHolder.getAdapterPosition() == 0) {

                    Toast.makeText(mContext, "click item 1", Toast.LENGTH_SHORT).show();
                } else if (viewHolder.getAdapterPosition() == 1) {

                    Toast.makeText(mContext, "click item 2", Toast.LENGTH_SHORT).show();
                } else if (viewHolder.getAdapterPosition() == 2) {

                    Toast.makeText(mContext, "click item 3", Toast.LENGTH_SHORT).show();
                } else if (viewHolder.getAdapterPosition() == 3) {

                    Toast.makeText(mContext, "click item 4", Toast.LENGTH_SHORT).show();
                } else if (viewHolder.getAdapterPosition() == 4) {

                    Toast.makeText(mContext, "click item 5", Toast.LENGTH_SHORT).show();
                } else if (viewHolder.getAdapterPosition() == 5) {

                    Toast.makeText(mContext, "click item 6", Toast.LENGTH_SHORT).show();
                } else if (viewHolder.getAdapterPosition() == 6) {

                    Toast.makeText(mContext, "click item 7", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(mContext, "click item 8", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_cargo.setText(mData.get(position).getCargo());
        holder.img_perfil.setImageResource(mData.get(position).getPhotoPerfil());
        holder.img_ranking.setImageResource(mData.get(position).getPhotoRanking());


    }

    @Override
    public int getItemCount() {
        return mData.size();

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout cosumidor_item;
        private TextView tv_name;
        private TextView tv_cargo;
        private ImageView img_perfil;
        private ImageView img_ranking;

        public MyViewHolder(View itemView) {
            super(itemView);
            cosumidor_item = itemView.findViewById(R.id.cosumidor_item);
            tv_name = itemView.findViewById(R.id.name_consumidor);
            tv_cargo = itemView.findViewById(R.id.cargo_consumidor);
            img_perfil = itemView.findViewById(R.id.img_consumidor);
            img_ranking = itemView.findViewById(R.id.img_ranking);

        }
    }


}
