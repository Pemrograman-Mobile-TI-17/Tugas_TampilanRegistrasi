package com.victoria.myapplication.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.victoria.myapplication.R;

import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class LoginActivity extends AppCompatActivity {

    Button btndaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        btndaftar = (Button) findViewById(R.id.btndaftar);


        btndaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(LoginActivity.this, DaftarActivity.class);
                startActivity(i);
                finish();

            }
        });



    }
}
