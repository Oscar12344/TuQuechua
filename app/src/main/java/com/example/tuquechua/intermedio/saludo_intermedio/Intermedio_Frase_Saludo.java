package com.example.tuquechua.intermedio.saludo_intermedio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tuquechua.R;
import com.example.tuquechua.adapter.FamiliaImagenAdapter;
import com.example.tuquechua.adapter.SaludoImagenAdapter;
import com.example.tuquechua.entidades.Pregunta;
import com.example.tuquechua.intermedio.familia_intermedio.Intermedio_Ejercicio1_Familia;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Intermedio_Frase_Saludo extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    RecyclerView recyclerViewSaludos;

    ArrayList<Pregunta> listaSaludo;
    Button btnSiguiente;

    ProgressDialog progress;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermedio__frase__saludo);
        listaSaludo = new ArrayList<>();
        recyclerViewSaludos=(RecyclerView)findViewById(R.id.idRecyclerListaSaludoImagen);
        recyclerViewSaludos.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        btnSiguiente=findViewById(R.id.btnSiguiente);
        recyclerViewSaludos.setHasFixedSize(true);
        request= Volley.newRequestQueue(this);
        llamarwebservice();
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(), Intermedio_Ejercicio3_Saludo.class);

                startActivity(i);
            }
        });
    }
    public void llamarwebservice() {
        progress = new ProgressDialog(this);
        progress.setMessage("Consultando Comidas");
        progress.show();
        String url="http://192.168.1.195:85/pregunta/ConsultarListaFraseSaludo.php?";


        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url,null,this, this);
        request.add(jsonObjectRequest);
        Toast.makeText(this, "Listando", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progress.hide();
        Toast.makeText(this, "Error de registro", Toast.LENGTH_SHORT).show();
        Log.i("Error", error.toString());
    }


    @Override
    public void onResponse(JSONObject response) {
        Pregunta pregunta=null;

        JSONArray json=response.optJSONArray("frasesaludo");

        try {

            for (int i=0;i<json.length();i++){
                pregunta=new Pregunta();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);

                pregunta.setPalabra(jsonObject.optString("palabra"));
                pregunta.setPalabraEsp(jsonObject.optString("palabraEspanol"));

                pregunta.setDato(jsonObject.optString("imagen"));
                listaSaludo.add(pregunta);
            }
            progress.hide();
            SaludoImagenAdapter adapter=new SaludoImagenAdapter(listaSaludo);
            recyclerViewSaludos.setAdapter(adapter);

        } catch ( JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error servidor", Toast.LENGTH_SHORT).show();
            progress.hide();
        }
    }
}