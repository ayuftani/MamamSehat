package com.example.mamamsehat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class usermenu extends AppCompatActivity {

    private Button button_load;

    private static final String TAG = "MyActivity";

    RecyclerView rv_menu;
    MenuAdapter adapter;
    ArrayList<MenuData2> menuArrayList;

    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usermenu);

        FirebaseApp.initializeApp(this);

        //initialize
        rv_menu = findViewById(R.id.rv_menu);
        firebaseFirestore = FirebaseFirestore.getInstance();

        //check user login
        checkUserLogin();

    }
    public void checkUserLogin(){
        if(FirebaseAuth.getInstance().getCurrentUser() == null){
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            this.startActivity(intent);
            finish();
        }
    }

    public void do_logout(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Anda Yakin Ingin Logout?")
                .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, close
                        // current activity
                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(usermenu.this, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        usermenu.this.startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }

    public class showDataAsycktask extends AsyncTask<Void, Void, Void> {

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference menu = rootRef.child("menu");

        ArrayList<MenuData2> menuArray;
        String getnama, getharga, getdeskripsi, getimgUrl;

        @Override
        protected Void doInBackground(Void... voids) {
            menuArray = new ArrayList<>();
            firebaseFirestore.collection("menu")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    getnama = document.getData().get("name").toString();
                                    getharga = document.getData().get("harga").toString();
                                    getdeskripsi = document.getData().get("deskripsi").toString();
                                    getimgUrl = document.getData().get("imgUrl").toString();

                                    Log.i(TAG, "nama " + getnama + " harga " + getharga + " deskripsi " + getdeskripsi + " imgUrl " + getimgUrl);
                                    menuArray.add(new MenuData2(getnama, getharga, getimgUrl, getdeskripsi));

                                    adapter = new MenuAdapter(usermenu.this, menuArray);
                                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(usermenu.this);
                                    rv_menu.setLayoutManager(layoutManager);
                                    rv_menu.setAdapter(adapter);

                                }
                            } else {

                            }
                        }
                    });

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }




    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_logout:
                do_logout();
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().getCurrentUser();
        usermenu.showDataAsycktask show = new usermenu.showDataAsycktask();
        show.execute();
    }


    @Override
    protected void onResume() {
        super.onResume();
        FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    protected void onStop() {
        super.onStop();
        FirebaseAuth.getInstance().getCurrentUser();
    }
}
