package com.example.mamamsehat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import activities.UsersListActivity;
import helpers.InputValidation;
import sql.DatabaseHelper;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = Login.this;

    private TextView JudulEmail;
    private TextView JudulPassword;

    private EditText Email;
    private EditText Password;

    private Button btnLogin;
    private TextView createone;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        initVIews();
        initListeners();
        initObjects();
    }

    private void initVIews(){
        JudulEmail = (TextView) findViewById(R.id.JudulEmail);
        JudulPassword = (TextView) findViewById(R.id.JudulPassword);
        Email = (EditText) findViewById(R.id.Email);
        Password = (EditText) findViewById(R.id.Password);
        btnLogin = (Button) findViewById(R.id.btnlogin);
        createone = (TextView) findViewById(R.id.cretaeone);
}

    private void initListeners() {
        btnLogin.setOnClickListener(this);
        createone.setOnClickListener(this);
    }

    private void initObjects() {
        databaseHelper = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnlogin:
                verifyFromSQLite();
                break;
            case R.id.cretaeone:
                // Navigate to RegisterActivity
                Intent intentRegister = new Intent(getApplicationContext(), Register.class);
                startActivity(intentRegister);
                break;
        }
    }


    private void verifyFromSQLite() {
        if (!inputValidation.isInputEditTextFilled(Email, JudulEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(Email, JudulEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(Password, JudulPassword, getString(R.string.error_message_email))) {
            return;
        }

        if (databaseHelper.checkUser(Email.getText().toString().trim()
                , Password.getText().toString().trim())) {


            Intent accountsIntent = new Intent(activity, beranda.class);
            accountsIntent.putExtra("EMAIL", Email.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountsIntent);


        } else {
            Toast.makeText(getApplicationContext(), "Email/Password Salah", Toast.LENGTH_LONG).show();
        }
    }

    private void emptyInputEditText() {
        Email.setText(null);
        Password.setText(null);
    }
}