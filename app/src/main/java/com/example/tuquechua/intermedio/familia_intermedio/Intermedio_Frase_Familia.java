package com.example.tuquechua.intermedio.familia_intermedio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tuquechua.R;
import com.example.tuquechua.adapter.ComidaImagenAdapter;
import com.example.tuquechua.adapter.FamiliaImagenAdapter;
import com.example.tuquechua.entidades.Pregunta;
import com.example.tuquechua.intermedio.comida_intermedio.Intermedio_Ejercicio1_Comida;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Intermedio_Frase_Familia extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    RecyclerView recyclerViewFamilias;
    ArrayList<Pregunta> listaFamilia;
    Button btnSiguiente;
    private Character sec;
    private Integer punt;
    private TextView txtSecYNiv, txtPuntosEjer;
    ProgressDialog progress;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermedio__frase__familia);
        txtSecYNiv = findViewById(R.id.txtSecNiv);
        txtPuntosEjer = findViewById(R.id.txtPuntos);

        txtSecYNiv.setText("FAMILIA | INTERMEDIO");
        txtPuntosEjer.setText("");

        listaFamilia = new ArrayList<>();
        recyclerViewFamilias=(RecyclerView)findViewById(R.id.idRecyclerListaFamiliaImagen);
        recyclerViewFamilias.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        btnSiguiente=findViewById(R.id.btnSiguiente);
        recyclerViewFamilias.setHasFixedSize(true);

        punt = getIntent().getIntExtra("puntaje", 0);
        sec = getIntent().getCharExtra("seccion", '0');

        request= Volley.newRequestQueue(this);
        llamarwebservice();
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(), Intermedio_Ejercicio3_Familia.class);

                i.putExtra("puntaje", punt);
                i.putExtra("seccion", sec);
                startActivity(i);
            }
        });
    }
    public void llamarwebservice() {
        progress = new ProgressDialog(this);
        progress.setMessage("Consultando Comidas");
        progress.show();

        String url=getString(R.string.urlIP)+"pregunta/ConsultarListaFraseFamilia.php?";

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

        JSONArray json=response.optJSONArray("frasefamilia");

        try {

            for (int i=0;i<json.length();i++){
                pregunta=new Pregunta();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);

                pregunta.setPalabra(jsonObject.optString("palabra"));
                pregunta.setPalabraEsp(jsonObject.optString("palabraEspanol"));

                pregunta.setDato(jsonObject.optString("imagen"));
                listaFamilia.add(pregunta);
            }
            progress.hide();
            FamiliaImagenAdapter adapter=new FamiliaImagenAdapter(listaFamilia);
            recyclerViewFamilias.setAdapter(adapter);

        } catch ( JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error servidor", Toast.LENGTH_SHORT).show();
            progress.hide();
        }
    }

    @Override
    public void onBackPressed()
    {
        Toast.makeText(this,"No puedes retroceder",Toast.LENGTH_SHORT).show();
    }
}