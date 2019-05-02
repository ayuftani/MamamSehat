package com.example.mamamsehat;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ArtikelAdapter extends RecyclerView.Adapter<ArtikelAdapter.ArtikelViewHolder> {

    Context context;
    ArrayList<ArtikelData2> dataList;

    public ArtikelAdapter(Context context, ArrayList<ArtikelData2> dataList){
        this.context = context;
        this.dataList = dataList;
    }
    @NonNull
    @Override
    public ArtikelAdapter.ArtikelViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_menu, viewGroup, false);
        return new ArtikelAdapter.ArtikelViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ArtikelAdapter.ArtikelViewHolder ArtikelViewHolder, int i) {
        ArtikelViewHolder.judul.setText(dataList.get(i).getJudul());
        ArtikelViewHolder.deskripsi = dataList.get(i).getDeskripsi();
        Glide.with(context)
                .load(dataList.get(i).getImgUrl())
                .into(ArtikelViewHolder.img);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ArtikelViewHolder extends RecyclerView.ViewHolder{

        private TextView judul, desktipsi;
        private ImageView img;
        String deskripsi;

        public ArtikelViewHolder(@NonNull final View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img);
            judul = itemView.findViewById(R.id.nama);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(itemView.getContext());
                    alert.setMessage(deskripsi).setTitle("Deskripsi Porduk")
                            .setNegativeButton("ok",null);

                    AlertDialog alertDialog = alert.create();
                    alertDialog.show();
                }
            });


        }
    }
}
