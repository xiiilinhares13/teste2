package com.gswaugustom.login.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.gswaugustom.login.actitivitys.Main_MicrosServiceDetalhe_Activity;
import com.gswaugustom.login.models.Microsservice;

import java.util.List;

public class RecyclerViewAdapterMs extends RecyclerView.Adapter<RecyclerViewAdapterMs.MyViewHolderMs> {


    Context mContext;
    List<Microsservice> mData;


    public RecyclerViewAdapterMs(Context mContext, List<Microsservice> mData) {
        this.mContext = mContext;
        this.mData = mData;

    }


    @NonNull
    @Override
    public MyViewHolderMs onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {
        //Estou inflando o layout criado para o consumidor no xml abaixo.
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_microsservice, parent, false);
        final RecyclerViewAdapterMs.MyViewHolderMs viewHolder = new RecyclerViewAdapterMs.MyViewHolderMs(v);

        viewHolder.microsservice_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (viewHolder.getAdapterPosition() == 0) {

                    Intent intent = new Intent(mContext, Main_MicrosServiceDetalhe_Activity.class);
                    mContext.startActivity(intent);

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
                }
            }
        });

        return viewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderMs holder, int position) {
        holder.name_microservice.setText(mData.get(position).getName());
        holder.contribuidor_ranking.setText(mData.get(position).getConsumidor());
        holder.img_MicrosService.setImageResource(mData.get(position).getImg());
        holder.img_ranking.setImageResource(mData.get(position).getPhotoRanking());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolderMs extends RecyclerView.ViewHolder {

        //identifico meu layout para que possa ser clicado.
        private LinearLayout microsservice_item;

        private TextView name_microservice;
        private TextView contribuidor_ranking;
        private ImageView img_MicrosService;
        private ImageView img_ranking;

        public MyViewHolderMs(View itemView) {
            super(itemView);
            microsservice_item = itemView.findViewById(R.id.microsservice_item);
            name_microservice = itemView.findViewById(R.id.name_microservice);
            contribuidor_ranking = itemView.findViewById(R.id.contribuidor_ranking);
            img_MicrosService = itemView.findViewById(R.id.img_MicrosService);
            img_ranking = itemView.findViewById(R.id.img_ranking);


        }
    }
}
