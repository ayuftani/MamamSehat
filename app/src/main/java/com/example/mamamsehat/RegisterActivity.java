package com.example.mamamsehat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private TextView button_login;

    private EditText nama, email,password;
    private TextView type;
    private Button button_daftar;
    CardView loading_bar;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //initialize component xml
        button_login = findViewById(R.id.button_login);
        nama = findViewById(R.id.nama);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        type = findViewById(R.id.type);
        button_daftar = findViewById(R.id.button_daftar);
        loading_bar = findViewById(R.id.loading_bar);

        //dekalari firebase
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        //initialize onClickListener
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_login();
            }
        });
        button_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_register();
            }
        });

    }
    public void do_login(){
        Intent intent = new Intent(this, LoginActivity.class);
        this.startActivity(intent);
        finish();
    }
    public void do_register(){
        loading_bar.setVisibility(View.VISIBLE);
        final String emails = email.getText().toString();
        final String passwords = password.getText().toString();
        final String namas = nama.getText().toString();
        final String types = "User";

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(emails, passwords)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {

                            Toast.makeText(RegisterActivity.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_LONG).show();

                        } else {
                            email.setText("");
                            password.setText("");
                            nama.setText("");
                            type.setText("");

                            loading_bar.setVisibility(View.GONE);
                            // THE USER ID
                            String uid = task.getResult().getUser().getUid();
                            RegisterData register = new RegisterData
                                    (namas,emails,passwords, types);

                            databaseReference.child("user").child(uid)
                                    .setValue(register);

                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            RegisterActivity.this.startActivity(intent);
                            finish();


                        }
                    }
                });
    }
}
