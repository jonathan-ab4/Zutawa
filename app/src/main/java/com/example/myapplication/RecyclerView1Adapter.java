package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecyclerView1Adapter extends RecyclerView.Adapter<RecyclerView1Adapter.ViewHolder>{

    Context context;
    ArrayList<String> categories;
    ArrayList<String> types;


    public RecyclerView1Adapter(Context context, ArrayList<String> categories, ArrayList<String> types) {
        this.context = context;
        this.categories = categories;
        this.types = types;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_items_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.category.setText(categories.get(position));

        ArrayList<String> titles = new ArrayList<>();
        ArrayList<String> subtitles = new ArrayList<>();
        ArrayList<String> images = new ArrayList<>();

        Log.d("Hello1", titles+"");
        String url = "https://gaming-panda.df.r.appspot.com/intern_test";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    for (int j=0; j<response.getJSONArray("response").getJSONObject(position).getJSONArray("items").length(); j++)
                    {
                        titles.add(response.getJSONArray("response").getJSONObject(position).getJSONArray("items").getJSONObject(j).getString("title"));
                        images.add(response.getJSONArray("response").getJSONObject(position).getJSONArray("items").getJSONObject(j).getString("img_src"));
                        if (types.get(position).equals("type2"))
                        {
                            subtitles.add(response.getJSONArray("response").getJSONObject(position).getJSONArray("items").getJSONObject(j).getString("sub_title"));
                        }
                    }

                        Log.d("Hello", titles+"");
                        if (types.get(position).equals("type1"))
                        {
                            RecyclerView2Adapter adapter = new RecyclerView2Adapter(context, titles, images);
                            holder.recyclerview2.setAdapter(adapter);
                        }
                        else if (types.get(position).equals("type2"))
                        {
                            RecyclerView2Type2Adapter adapter2 = new RecyclerView2Type2Adapter(context, titles, subtitles, images);
                            holder.recyclerview2.setAdapter(adapter2);
                        }


                } catch (JSONException e) {
                    e.printStackTrace();
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


    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView category;
        RecyclerView recyclerview2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            category = itemView.findViewById(R.id.category);
            recyclerview2 = itemView.findViewById(R.id.recyclerview2);
        }
    }
}
