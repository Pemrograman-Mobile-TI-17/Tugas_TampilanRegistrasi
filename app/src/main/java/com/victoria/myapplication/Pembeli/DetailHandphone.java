package com.victoria.myapplication.Pembeli;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.ayuantika.penjualan.R;
import com.ayuantika.penjualan.server.BaseURL;
import com.squareup.picasso.Picasso;

public class DetailHandphone extends AppCompatActivity {

    EditText edtKode, edtMerkH, edtTipeH, edtspesifikasi, edtHargaH;
    ImageView imgGambarHandphone;

    String strKodeHandphone, strMerkHandphone, strTipeHandphone, strSpesifikasi, strHargaHandphone, strGambar, _id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_handphone);

        edtKode = (EditText) findViewById(R.id.edtKodeHandphone);
        edtMerkH = (EditText) findViewById(R.id.edtMerkHandphone);
        edtTipeH = (EditText) findViewById(R.id.edtTipeHandphone);
        edtspesifikasi = (EditText) findViewById(R.id.edtSpesifikasi);
        edtHargaH = (EditText) findViewById(R.id.edtHargaHandphone);


        imgGambarHandphone = (ImageView) findViewById(R.id.gambar);

        Intent i = getIntent();
        strKodeHandphone = i.getStringExtra("kodeHandphone");
        strMerkHandphone = i.getStringExtra("merkHandphone");
        strTipeHandphone = i.getStringExtra("tipeHandphone");
        strSpesifikasi = i.getStringExtra("spesifikasi");
        strHargaHandphone = i.getStringExtra("hargaHandphone");
        strGambar = i.getStringExtra("gambar");
        _id = i.getStringExtra("id");

        edtKode.setText(strKodeHandphone);
        edtMerkH.setText(strMerkHandphone);
        edtTipeH.setText(strTipeHandphone);
        edtspesifikasi.setText(strSpesifikasi);
        edtHargaH.setText(strHargaHandphone);
        Picasso.get().load(BaseURL.baseURL + "gambar/" + strGambar)
                .into(imgGambarHandphone);
    }
}
