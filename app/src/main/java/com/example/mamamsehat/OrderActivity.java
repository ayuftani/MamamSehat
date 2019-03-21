package com.example.mamamsehat;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;

public class OrderActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private ArrayList<Produk> mProdukData;
    private ProdukAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);

        int gridColumnCount = getResources().getInteger(R.integer.grid_column_count);

        mRecyclerView.setLayoutManager(new GridLayoutManager(
                this, gridColumnCount));


        mProdukData = new ArrayList<>();


        mAdapter = new ProdukAdapter(this, mProdukData);
        mRecyclerView.setAdapter(mAdapter);


        initializeData();


        int swipeDirs;
        if(gridColumnCount > 1){
            swipeDirs = 0;
        } else {
            swipeDirs = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        }


        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper
                .SimpleCallback(
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT |
                        ItemTouchHelper.DOWN | ItemTouchHelper.UP,
                swipeDirs) {

            @Override
            public boolean onMove(RecyclerView recyclerView,
                                  RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {

                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();

                Collections.swap(mProdukData, from, to);
                mAdapter.notifyItemMoved(from, to);
                return true;
            }


            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                 int direction) {

                mProdukData.remove(viewHolder.getAdapterPosition());

                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });

        helper.attachToRecyclerView(mRecyclerView);
    }

    private void initializeData() {

        String[] ProdukList = getResources()
                .getStringArray(R.array.Produk_titles);
        String[] ProdukInfo = getResources()
                .getStringArray(R.array.Produk_info);
        TypedArray ProdukImageResources =
                getResources().obtainTypedArray(R.array.Produk_images);


        mProdukData.clear();

        for (int i = 0; i < ProdukList.length; i++) {
            mProdukData.add(new Produk(ProdukList[i], ProdukInfo[i],
                    ProdukImageResources.getResourceId(i, 0)));
        }

        ProdukImageResources.recycle();

        mAdapter.notifyDataSetChanged();
    }

    public void resetProduk(View view) {
        initializeData();
    }
}
