package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String mtype;
    ArrayList<String> categories = new ArrayList<>();
    ArrayList<String> types = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String url = "https://gaming-panda.df.r.appspot.com/intern_test";
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("HEY",""+response);
                            Log.d("HEY",""+response.getJSONArray("response").length());
                            for (int i=0; i<response.getJSONArray("response").length(); i++)
                            {
                                categories.add(response.getJSONArray("response").getJSONObject(i).getString("title"));
                                mtype = response.getJSONArray("response").getJSONObject(i).getString("type");
                                types.add(mtype);
                            }
                            initRecyclerView1();
                        }catch (Exception e)
                        {

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                    }
                });
        requestQueue.add(jsonObjectRequest);

    }

    private void initRecyclerView1() {
        RecyclerView recyclerView1 = findViewById(R.id.recyclerview1);
        RecyclerView1Adapter recyclerView1Adapter = new RecyclerView1Adapter(this, categories, types);
        recyclerView1.setAdapter(recyclerView1Adapter);
    }
}