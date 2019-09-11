package com.toughput.myapplicationchunnin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        btnSubmit = findViewById(R.id.btn_submit_login);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtUsername.length() == 0 || edtPassword.length() == 0){
                    Toast.makeText(MainActivity.this, "Username or Password must be filled", Toast.LENGTH_SHORT).show();
                }else if (edtPassword.length() == 0 && edtPassword.length() == 0){
                    Toast.makeText(MainActivity.this, "Please fill the form", Toast.LENGTH_SHORT).show();
                }else if (edtUsername.getText().toString().equals("admin") && edtPassword.getText().toString().equals("root")){
                    Intent intent = new Intent(MainActivity.this, MovieActivity.class);
                    intent.putExtra("username", "Admin");
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this, "Wrong Password or Username", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
