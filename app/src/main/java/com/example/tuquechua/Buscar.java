package com.example.tuquechua;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tuquechua.entidades.Diccionario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Buscar extends AppCompatActivity {
    EditText edtbuscarquechua,edtbuscarespanol;
    Button btnbuscarquechua, btnbuscarespanol;
    TextView tvquechua1, tvquechua2, tvespanol1, tvespanol2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);
        edtbuscarespanol= findViewById(R.id.edtBuscarEspanol);
        edtbuscarquechua= findViewById(R.id.edtBuscarQuechua);
        btnbuscarespanol=findViewById(R.id.btnBuscarEspanol);
        btnbuscarquechua=findViewById(R.id.btnBuscarQuechua);
         tvquechua1=findViewById(R.id.tvquechua1);
        tvquechua2=findViewById(R.id.tvquechua2);
        tvespanol1=findViewById(R.id.tvEspanol1);
        tvespanol2=findViewById(R.id.tvEspanol2);
        btnbuscarquechua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llamarWebServiceQuechua();
            }
        });
        btnbuscarespanol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llamarWebServiceEspanol();
            }
        });

    }


    private void llamarWebServiceQuechua() {

        RequestQueue requestQuechua;
        JsonObjectRequest jsonObjectRequestQuechua;
        ProgressDialog progresoQuec;


        requestQuechua = Volley.newRequestQueue(this);
        progresoQuec = new ProgressDialog(this);
        progresoQuec.setMessage("Consultando...");
        progresoQuec.show();
        String urlQuechua = getString(R.string.urlIP)+"pregunta/ConsultarDiccionarioQuechua.php?dicPalabraQuechua="+edtbuscarquechua.getText().toString();
        urlQuechua=urlQuechua.replace(" ","%20");

        jsonObjectRequestQuechua = new JsonObjectRequest(Request.Method.GET, urlQuechua, null, new Response.Listener<JSONObject>()
        {

            @Override
            public void onResponse(JSONObject response) {
                progresoQuec.hide();
                Diccionario diccionario= new Diccionario();
                JSONArray json = response.optJSONArray("idquechua");
                JSONObject jsonObject= null;
                try{
                    jsonObject=json.getJSONObject(0);
                    diccionario.setDicPalabraQuechua(jsonObject.optString("dicPalabraQuechua"));
                    diccionario.setDicSignificado(jsonObject.optString("dicSignificado"));



                }catch ( JSONException e)
                {
                    e.printStackTrace();
                }
                tvquechua1.setText(diccionario.getDicPalabraQuechua());
                tvespanol1.setText(diccionario.getDicSignificado());
            }
        },new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {
                progresoQuec.hide();
                Toast.makeText(getApplicationContext(), "No se pudo consultar la busqueda "+error.toString(), Toast.LENGTH_SHORT).show();
                Log.i("Error", error.toString());
            }
        });
        requestQuechua.add(jsonObjectRequestQuechua);

    }
    private void llamarWebServiceEspanol() {
        RequestQueue requestEspanol;
        JsonObjectRequest jsonObjectRequestEspanol;
        ProgressDialog progresoEspa;



        requestEspanol = Volley.newRequestQueue(this);
        progresoEspa = new ProgressDialog(this);
        progresoEspa.setMessage("Consultando...");
        progresoEspa.show();
        String urlEspanol = getString(R.string.urlIP)+"pregunta/ConsultarDiccionarioEspanol.php?dicPalabraEspanol="+edtbuscarespanol.getText().toString();
        urlEspanol=urlEspanol.replace(" ","%20");

        jsonObjectRequestEspanol = new JsonObjectRequest(Request.Method.GET, urlEspanol, null, new Response.Listener<JSONObject>()
        {

            @Override
            public void onResponse(JSONObject response) {
                progresoEspa.hide();
                Diccionario midiccionario= new Diccionario();
                JSONArray json = response.optJSONArray("idespanol");
                JSONObject jsonObject= null;
                try{
                    jsonObject=json.getJSONObject(0);
                    midiccionario.setDicPalabraQuechua(jsonObject.optString("dicPalabraQuechua"));
                    midiccionario.setDicSignificado(jsonObject.optString("dicSignificado"));



                }catch ( JSONException e)
                {
                    e.printStackTrace();
                }
                tvquechua2.setText(midiccionario.getDicPalabraQuechua());
                tvespanol2.setText(midiccionario.getDicSignificado());
            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                progresoEspa.hide();
                Toast.makeText(getApplicationContext(), "No se pudo consultar la busqueda "+error.toString(), Toast.LENGTH_SHORT).show();
                Log.i("Error", error.toString());
            }
        });
        requestEspanol.add(jsonObjectRequestEspanol);


    }

}