package com.example.mamamsehat;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginadmin extends AppCompatActivity {

    private EditText textemail;
    private EditText textpassword;
    private Button login;
    private TextView textregis;
    private String email, password;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginadmin);

        firebaseAuth = FirebaseAuth.getInstance();

        textemail = findViewById(R.id.email);
        textpassword = findViewById(R.id.password);
        textregis = findViewById(R.id.regis);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = textemail.getText().toString();
                password = textpassword.getText().toString();

                if (email.equals("admin")&& password.equals("admin123")) {
                    Intent intent = new Intent(loginadmin.this, homeadmin.class);
                    startActivity(intent);
                    finish();

                } else if (password.length() < 6) {
                    textpassword.setError(getString(R.string.min_pass));

                } else if (TextUtils.isEmpty(email)) {
                        Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                        return;
                } else if (TextUtils.isEmpty(password)) {
                        Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                        return;
                } else {
                    Toast.makeText(loginadmin.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                    Log.d("Admin", email+""+password);
                }

            }

        });
    }
}