
package com.example.volleyexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.security.interfaces.RSAKey;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button add;
    EditText Name,Dept,Sal;

    String server_url="https://tiniest-submarining.000webhostapp.com/insert.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = findViewById(R.id.Name);
        Dept = findViewById(R.id.Department);
        Sal = findViewById(R.id.editTextNumber);

        add = findViewById(R.id.button);






        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Add_Data();
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.fetch :
                callfetch();
            default:
                return super.onOptionsItemSelected(item);


        }
    }



    public void callfetch(){

        Intent i =new Intent(MainActivity.this,FetchData.class);
        startActivity(i);
        
    }

    public  void Add_Data(){

        String n = Name.getText().toString().trim();
        String d= Dept.getText().toString().trim();
        String s = Sal.getText().toString();

        String url=server_url;


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Toast.makeText(MainActivity.this, ""+response.substring(2), Toast.LENGTH_SHORT).show();
//


                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "ex :"+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "err : "+error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params= new HashMap<String,String>();
                params.put("name",n);
                params.put("dept",d);
                params.put("sal",s);

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
               Map<String,String> params = new HashMap<String, String>();
               params.put("Content-Type", "application/x-www-form-urlencoded");

               return  params;
            }
        };

        RequestQueue  requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }


}