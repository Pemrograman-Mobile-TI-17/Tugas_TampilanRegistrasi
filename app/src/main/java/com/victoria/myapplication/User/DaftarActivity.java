package com.victoria.myapplication.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ornach.nobobutton.NoboButton;
import com.victoria.myapplication.R;

import studio.carbonylgroup.textfieldboxes.ExtendedEditText;

public class DaftarActivity extends AppCompatActivity {

    NoboButton daftarbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);


        daftarbtn = (NoboButton) findViewById(R.id.daftarbtn);


        daftarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(DaftarActivity.this, LoginActivity.class);
                startActivity(i);
                finish();

            }
        });
    }
}
