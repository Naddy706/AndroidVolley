package com.example.volleyexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class FetchData extends AppCompatActivity {


    ListView ls;
    ArrayList<Data> data;
    String url="https://tiniest-submarining.000webhostapp.com/fetch.php";

    DataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_data);

        data = new ArrayList<Data>();
        ls = findViewById(R.id.listview);
        adapter = new DataAdapter(data,FetchData.this);

        ls.setAdapter(adapter);

        fetch();

    }


    public  void fetch(){


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                data.clear();
                try {
                    JSONObject obj = new JSONObject(response.substring(2));
                    JSONArray jsonArray = obj.getJSONArray("data");

                    for(int i=0;i<jsonArray.length();i++){

                        // Toast.makeText(FetchData.this, ""+jsonArray.get(i), Toast.LENGTH_SHORT).show();
                        JSONObject ob= jsonArray.getJSONObject(i);
                        Data d = new Data();
                        d.id= ob.getString("id");
                        d.name = ob.getString("name");
                        d.department = ob.getString("department");
                        d.salary = ob.getString("salary");

                        Toast.makeText(FetchData.this, ""+d.id+ " "+d.name+" "+d.department+" "+d.salary, Toast.LENGTH_SHORT).show();


                        data.add(d);
                        adapter.notifyDataSetChanged();

                    }




                }
                catch (Exception e){
                    Toast.makeText(FetchData.this, ""+e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue rq= Volley.newRequestQueue(this);
        rq.add(stringRequest);


    }
}