package com.victoria.myapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayuantika.penjualan.R;
import com.ayuantika.penjualan.model.ModelHandphone;
import com.ayuantika.penjualan.server.BaseURL;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Laptop extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<ModelHandphone> item;

    public Laptop(Activity activity, List<ModelHandphone> item) {
        this.activity = activity;
        this.item = item;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(androidx.core.R .layout.content_hadphone, null);


        TextView MerkHandphone = (TextView) convertView.findViewById(R.id.txtMerkHandphone);
        TextView KodeHandphone = (TextView) convertView.findViewById(R.id.txtKode);
        TextView TipeHandphone = (TextView) convertView.findViewById(R.id.txtTipe);
        TextView Harga         = (TextView) convertView.findViewById(R.id.txtHarga);
        TextView Spesifikasi   = (TextView) convertView.findViewById(R.id.txtSpesifikasi);
        ImageView gambarHandphone = (ImageView) convertView.findViewById(R.id.gambarHandphone);

        MerkHandphone.setText(item.get(position).getMerkHandphone());
        KodeHandphone.setText(item.get(position).getKodeHandphone());
        TipeHandphone.setText(item.get(position).getTipeHandphone());
        Harga.setText("Rp." + item.get(position).getHargaHandphone());
        Spesifikasi.setText(item.get(position).getSpesifikasi());
        Picasso.get().load(BaseURL.baseURL + "gambar/" + item.get(position).getGambar())
                .resize(40, 40)
                .centerCrop()
                .into(gambarHandphone);
        return convertView;
    }

}
