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

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.menuViewHolder> {

    Context context;
    ArrayList<MenuData2> dataList;

    public MenuAdapter(Context context, ArrayList<MenuData2> dataList){
        this.context = context;
        this.dataList = dataList;
    }
    @NonNull
    @Override
    public MenuAdapter.menuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_menu, viewGroup, false);
        return new menuViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.menuViewHolder menuViewHolder, int i) {
        menuViewHolder.nama.setText(dataList.get(i).getNama());
        menuViewHolder.harga.setText(dataList.get(i).getHarga());
        menuViewHolder.deskripsi = dataList.get(i).getDeskripsi();
        Glide.with(context)
                .load(dataList.get(i).getImgUrl())
                .into(menuViewHolder.img);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
    public class menuViewHolder extends RecyclerView.ViewHolder{

        private TextView nama, harga;
        private ImageView img;
        String deskripsi;

        public menuViewHolder(@NonNull final View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img);
            nama = itemView.findViewById(R.id.nama);
            harga = itemView.findViewById(R.id.harga);
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
