package com.example.volley_api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.volley_api.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
String uri="https://jsonplaceholder.typicode.com/comments";
ArrayList<model_class> datalist=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        RequestQueue queue= Volley.newRequestQueue(this);
        if (isOnline())
        {
            StringRequest stringRequest= new StringRequest(Request.Method.GET, uri, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray array=new JSONArray(response);
                        for (int i=0;i< array.length();i++) {
                            String name;
                            String email;
                            String body;

                            JSONObject mainobj = array.getJSONObject(i);
                            name=mainobj.getString("name");
                            email=mainobj.getString("email");
                            body=mainobj.getString("body");

                            model_class modelClass=new model_class(name,email,body);
                            datalist.add(modelClass);

                        }

                        binding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                            @Override
                            public void onRefresh() {
                                Shuffale();
                                binding.swipeRefresh.setRefreshing(false);

                            }
                        });
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            queue.add(stringRequest);
        }
        else
        {
            finish();
        }

    }

    private void Shuffale() {
       // Collections.shuffle(datalist);
        binding.swipeRefresh.setRefreshing(true);
        recycle_adapter adapter=new recycle_adapter(MainActivity.this,datalist);
        LinearLayoutManager layoutManager=new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL,false);
        binding.recycleView.setLayoutManager(layoutManager);
        binding.recycleView.setAdapter(adapter);
    }

    private boolean isOnline() {
        ConnectivityManager manager= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info=manager.getActiveNetworkInfo();
        return(info != null  && info.isConnected());
    }
}