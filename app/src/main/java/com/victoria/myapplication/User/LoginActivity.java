package com.victoria.myapplication.User;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ornach.nobobutton.NoboButton;
import com.victoria.myapplication.Admin.HomeAdminActifity;
import com.victoria.myapplication.R;
import com.victoria.myapplication.server.BaseURL;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class LoginActivity extends AppCompatActivity {

    Button btndaftar ;
    NoboButton btnlogin;
    EditText edtUsername, edtPassword;


    private RequestQueue mRequestQueue;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        mRequestQueue    = Volley.newRequestQueue(this);

        pDialog   = new ProgressDialog(this);
        pDialog.setCancelable(false);

        btndaftar = (Button) findViewById(R.id.btndaftar);
        btnlogin  = (NoboButton) findViewById(R.id.LoginBtn);
        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);



        btndaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(LoginActivity.this, DaftarActivity.class);
                startActivity(i);
                finish();

            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, HomeAdminActifity.class);
                startActivity(i);
                finish();
            }
        });



    } private void showDialog(){
        if(!pDialog.isShowing()){
            pDialog.show();
        }

    }
    private  void hideDialog(){
        if(pDialog.isShowing()){
            pDialog.dismiss();
        }
    }
}
