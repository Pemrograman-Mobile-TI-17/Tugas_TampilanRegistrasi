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
import com.victoria.myapplication.R;
import com.victoria.myapplication.server.BaseURL;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import studio.carbonylgroup.textfieldboxes.ExtendedEditText;

public class DaftarActivity extends AppCompatActivity {

    NoboButton daftarbtn;
    EditText edtUserName, edtNamaLengkap, edtEmail, edtNomortelp, edtPassword;
    ProgressDialog pDialog;

    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        getSupportActionBar().hide();

        mRequestQueue    = Volley.newRequestQueue(this);

        edtUserName      = findViewById(R.id.edtUserName);
        edtNamaLengkap   = findViewById(R.id.edtNamaLengkap);
        edtEmail         = findViewById(R.id.edtEmail);
        edtNomortelp     = findViewById(R.id.edtNomortelp);
        edtPassword      = findViewById(R.id.edtPassword);


        daftarbtn = (NoboButton) findViewById(R.id.daftarbtn);
        pDialog   = new ProgressDialog(this);
        pDialog.setCancelable(false);

        daftarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUsername = edtUserName.getText().toString();
                String strNamaLengkap = edtNamaLengkap.getText().toString();
                String strEmail = edtEmail.getText().toString();
                String strNomorTelp = edtNomortelp.getText().toString();
                String strPassword = edtPassword.getText().toString();

                if(strUsername.isEmpty()){
                    Toast.makeText(getApplicationContext(), "UserName tidak boleh kosong", Toast.LENGTH_LONG).show();
                }else if (strNamaLengkap.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Nama tidak boleh kosong", Toast.LENGTH_LONG).show();
                }else if (strEmail.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Email tidak boleh kosong", Toast.LENGTH_LONG).show();
                }else if (strNomorTelp.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Nomor telepon tidak boleh kosong", Toast.LENGTH_LONG).show();
                }else if (strPassword.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Password tidak boleh kosong", Toast.LENGTH_LONG).show();
                }else  {
                    registrasi(strUsername,strNamaLengkap,strEmail,strNomorTelp,strPassword);
                }




            }
        });
    }




    public void registrasi(String userName,String namaLengkap, String email, String noTelp, String password ){


// Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("userName", userName);
        params.put("namaLengkap", namaLengkap);
        params.put("email", email);
        params.put("noTelp", noTelp);
        params.put("role", "2");
        params.put("password", password);

        pDialog.setMessage("Mohon Tunggu");
        showDialog();


        JsonObjectRequest req = new JsonObjectRequest(BaseURL.register, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            String strMsg = response.getString("msg");
                            boolean status= response.getBoolean("error");

                            if (status == false){
                                Toast.makeText(getApplicationContext(),  strMsg, Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(DaftarActivity.this, LoginActivity.class);
                                startActivity(i);
                                finish();
                            }else {
                                Toast.makeText(getApplicationContext(),  strMsg, Toast.LENGTH_SHORT).show();
                            }
                            Toast.makeText(getApplicationContext(),  strMsg, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                hideDialog();
            }
        });


// add the request object to the queue to be executed
        mRequestQueue.add(req);

    }

    private void showDialog(){
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
