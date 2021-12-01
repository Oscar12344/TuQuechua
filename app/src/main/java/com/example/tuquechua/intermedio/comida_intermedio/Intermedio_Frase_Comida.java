package com.example.tuquechua.intermedio.comida_intermedio;

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
import com.example.tuquechua.entidades.Pregunta;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Intermedio_Frase_Comida extends AppCompatActivity  implements Response.Listener<JSONObject>, Response.ErrorListener{
    RecyclerView recyclerViewComidas;
    ArrayList<Pregunta> listaComida;
    Button btnSiguiente;
    private Character seccion;
    private Integer punt;
    private TextView txtSecYNiv, txtPuntosEjer;
    ProgressDialog progress;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermedio__frase__comida);
        txtSecYNiv = findViewById(R.id.txtSecNiv);
        txtPuntosEjer = findViewById(R.id.txtPuntos);

        txtSecYNiv.setText("COMIDA | INTERMEDIO");
        txtPuntosEjer.setText("");

        listaComida = new ArrayList<>();
        recyclerViewComidas=(RecyclerView)findViewById(R.id.idRecyclerListaComidaImagen);
        recyclerViewComidas.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        btnSiguiente=findViewById(R.id.btnSiguiente);
        recyclerViewComidas.setHasFixedSize(true);

        seccion = getIntent().getCharExtra("seccion", '0');
        punt = getIntent().getIntExtra("puntaje", 0);

        request= Volley.newRequestQueue(this);
        llamarwebservice();
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(), Intermedio_Ejercicio3_Comida.class);
                i.putExtra("seccion", seccion);
                i.putExtra("puntaje", punt);
                startActivity(i);
                finish();
            }
        });
    }

    public void llamarwebservice() {
        progress = new ProgressDialog(this);
        progress.setMessage("Consultando Comidas");
        progress.show();
        String url = getString(R.string.urlIP)+"pregunta/ConsultarListaFraseComida.php?";

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

        JSONArray json=response.optJSONArray("frasecomida");

        try {

            for (int i=0;i<json.length();i++){
                pregunta=new Pregunta();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);

                pregunta.setPalabra(jsonObject.optString("palabra"));
                pregunta.setPalabraEsp(jsonObject.optString("palabraEspanol"));

                pregunta.setDato(jsonObject.optString("imagen"));
                listaComida.add(pregunta);
            }
            progress.hide();
            ComidaImagenAdapter adapter=new ComidaImagenAdapter(listaComida);
            recyclerViewComidas.setAdapter(adapter);

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