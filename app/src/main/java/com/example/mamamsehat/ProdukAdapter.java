package com.example.mamamsehat;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

class ProdukAdapter extends RecyclerView.Adapter<ProdukAdapter.ViewHolder>  {

    private ArrayList<Produk> mProdukData;
    private Context mContext;

    ProdukAdapter(Context context, ArrayList<Produk> ProdukData) {
        this.mProdukData = ProdukData;
        this.mContext = context;
    }

    @Override
    public ProdukAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ProdukAdapter.ViewHolder holder,
                                 int position) {
        Produk currentProduk = mProdukData.get(position);

        holder.bindTo(currentProduk);
    }

    @Override
    public int getItemCount() {
        return mProdukData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        // Member Variables for the TextViews
        private TextView mTitleText;
        private TextView mInfoText;
        private ImageView mProdukImage;

        ViewHolder(View itemView) {
            super(itemView);

            mTitleText = itemView.findViewById(R.id.title);
            mInfoText = itemView.findViewById(R.id.subTitle);
            mProdukImage = itemView.findViewById(R.id.ProdukImage);

            itemView.setOnClickListener(this);
        }

        void bindTo(Produk currentProduk){

            mTitleText.setText(currentProduk.getTitle());
            mInfoText.setText(currentProduk.getInfo());

            Glide.with(mContext).load(
                    currentProduk.getImageResource()).into(mProdukImage);

        }

        @Override
        public void onClick(View view) {
            Produk currentProduk = mProdukData.get(getAdapterPosition());
            Intent detailIntent = new Intent(mContext, DetailProduk.class);
            detailIntent.putExtra("title", currentProduk.getTitle());
            detailIntent.putExtra("image_resource",
                    currentProduk.getImageResource());
            mContext.startActivity(detailIntent);
        }
    }
}
