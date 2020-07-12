package com.victoria.myapplication.Pembeli;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ayuantika.penjualan.R;
import com.ayuantika.penjualan.adapter.AdapterHandphone;
import com.ayuantika.penjualan.admin.HomeAdminActivity;
import com.ayuantika.penjualan.model.ModelHandphone;
import com.ayuantika.penjualan.server.BaseURL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class HomePembeli extends AppCompatActivity {

    ProgressDialog pDialog;

    AdapterLaptop adapter;
    ListView list;

    ArrayList<ModelLaptop> newsList = new ArrayList<ModelLaptop();
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_pembeli);

         getSupportActionBar().setTitle("Data Handphone");
        mRequestQueue = Volley.newRequestQueue(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        list = (ListView) findViewById(R.id.array_list);
        newsList.clear();
        adapter = new AdapterLaptop(HomePembeli.this, newsList);
        list.setAdapter(adapter);
        getAllBuku();
    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(HomePembeli.this, HomeAdminActivity.class);
        startActivity(i);
        finish();
    }

    private void getAllBuku() {
        // Pass second argument as "null" for GET requests
        pDialog.setMessage("Loading");
        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, BaseURL.dataHandphone, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            boolean status = response.getBoolean("error");
                            if (status == false) {
                                Log.d("data handphone = ", response.toString());
                                String data = response.getString("data");
                                JSONArray jsonArray = new JSONArray(data);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    final ModelLaptop handphone = new ModelLaptop();
                                    final String _id = jsonObject.getString("_id");
                                    final String kodeHandphone = jsonObject.getString("kodeHandphone");
                                    final String merkHandphone = jsonObject.getString("merkHandphone");
                                    final String tipeHandphone = jsonObject.getString("tipeHandphone");
                                    final String spesifikasi = jsonObject.getString("spesifikasi");
                                    final String hargaHandphone = jsonObject.getString("hargaHandphone");
                                    final String gambar = jsonObject.getString("gambar");
                                    handphone.setKodeLaptop(kodeHandphone);
                                    handphone.setMerkLaptop(merkHandphone);
                                    handphone.setTipeLaptop(tipeHandphone);
                                    handphone.setSpesifikasi(spesifikasi);
                                    handphone.setHargaLaptop(hargaHandphone);
                                    handphone.setGambar(gambar);
                                    handphone.set_id(_id);

                                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            // TODO Auto-generated method stub
                                            Intent a = new Intent(HomePembeli.this, DetailHandphone.class);
                                            a.putExtra("kodeHandphone", newsList.get(position).getKodeLaptop());
                                            a.putExtra("merkHandphone", newsList.get(position).getMerkLaptop());
                                            a.putExtra("tipeHandphone", newsList.get(position).getTipeLaptop());
                                            a.putExtra("spesifikasi", newsList.get(position).getSpesifikasi());
                                            a.putExtra("hargaHandphone", newsList.get(position).getHargaLaptop());
                                            a.putExtra("gambar", newsList.get(position).getGambar());
                                            a.putExtra("id", newsList.get(position).get_id());
                                            startActivity(a);
                                        }
                                    });
                                    newsList.add(handphone);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                hideDialog();
            }
        });

        /* Add your Requests to the RequestQueue to execute */
        mRequestQueue.add(req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
