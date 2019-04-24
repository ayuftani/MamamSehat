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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private TextView button_register;
    TextView type;
    private EditText email, password;
    private Button button_login;
    CardView loading_bar;

    //deklarasi Firebase
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //initialize xml component
        button_register = findViewById(R.id.button_register);
        button_login = findViewById(R.id.button_login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loading_bar = findViewById(R.id.loading_bar);
        type = findViewById(R.id.type);

        //setOnClickListener
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_register();
            }
        });
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_login();
            }
        });


    }

    public void do_register() {
        Intent intent = new Intent(this, RegisterActivity.class);
        this.startActivity(intent);
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        FirebaseUser user = firebaseAuth.getCurrentUser();
//        if (user != null){
//            startActivity(new Intent(LoginActivity.this, MainActivity.class));
//        }
//    }

    public void do_login() {
        loading_bar.setVisibility(View.VISIBLE);
        String emails = email.getText().toString();
        String passwords = password.getText().toString();

        firebaseAuth = FirebaseAuth.getInstance();

        if (!emails.equals("") && !passwords.equals("")) {

            firebaseAuth.signInWithEmailAndPassword(emails, passwords).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        firebaseDatabase = FirebaseDatabase.getInstance();
                        databaseReference = firebaseDatabase.getReference().child("user").child(firebaseAuth.getUid());
                        databaseReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                RegisterData userProfile = dataSnapshot.getValue(RegisterData.class);
                                String userType = (userProfile.getType());

                                if ( !userType.isEmpty() && userType.equals("Admin")) {
                                    startActivity(new Intent(LoginActivity.this, homeadmin.class));
                                } else {
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }

                        });
                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                    }

                }

            });
        }
    }
}