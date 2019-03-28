package com.example.mamamsehat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import helpers.InputValidation;
import model.User;
import sql.DatabaseHelper;


public class Register extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = Register.this;

    private TextView JudulNama;
    private TextView jdlEmail;
    private TextView jdlPassword;
    private TextView jdlComfirm;

    private EditText nama;
    private EditText Email;
    private EditText Pass;
    private EditText ComformPassword;

    private Button register;
    private TextView login;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    private void initViews() {

        JudulNama = (TextView) findViewById(R.id.JudulNama);
        jdlEmail = (TextView) findViewById(R.id.jdlEmail);
        jdlPassword = (TextView) findViewById(R.id.jdlPassword);
        jdlComfirm = (TextView) findViewById(R.id.jdlComfirm);

        nama = (EditText) findViewById(R.id.nama);
        Email = (EditText) findViewById(R.id.Email);
        Pass = (EditText) findViewById(R.id.Pass);
        ComformPassword = (EditText) findViewById(R.id.ComfirmPassword);

        register = (Button) findViewById(R.id.register);
        login = (TextView) findViewById(R.id.login);
    }

    private void initListeners() {
        register.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        user = new User();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.register:
                postDataToSQLite();
                break;

            case R.id.login:
                finish();
                break;
        }
    }

    private void postDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled(nama, JudulNama, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(Email, jdlEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(Email, jdlEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(Pass, jdlPassword, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(Pass, ComformPassword,
                jdlComfirm, getString(R.string.error_password_match))) {
            return;
        }

        if (!databaseHelper.checkUser(Email.getText().toString().trim())) {

            user.setName(nama.getText().toString().trim());
            user.setEmail(Email.getText().toString().trim());
            user.setPassword(Pass.getText().toString().trim());

            databaseHelper.addUser(user);

            // Snack Bar to show success message that record saved successfully
            Toast.makeText(getApplicationContext(), "YOUR REGISTRATION IS SUCCESSFULLY", Toast.LENGTH_LONG).show();
            emptyInputEditText();
            Intent intent5 = new Intent(Register.this, Login.class);
            startActivity(intent5);


        } else {
            Toast.makeText(getApplicationContext(), "Email Already Exist", Toast.LENGTH_LONG).show();
        }
    }

    private void emptyInputEditText() {
        nama.setText(null);
        Email.setText(null);
        Pass.setText(null);
        ComformPassword.setText(null);
    }
}