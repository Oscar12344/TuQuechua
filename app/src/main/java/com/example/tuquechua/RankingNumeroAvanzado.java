package com.example.tuquechua;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tuquechua.adapter.NumeroRankingAvanzadoAdapter;
import com.example.tuquechua.adapter.NumeroRankingBasicoAdapter;
import com.example.tuquechua.entidades.Ranking;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RankingNumeroAvanzado extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    RecyclerView recyclerViewRankingAvanzadoNumeros;
    ArrayList<Ranking> listaRankingAvanzadoNumero;
    Button btnSiguiente;

    ProgressDialog progress;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking_numero_avanzado);
        listaRankingAvanzadoNumero = new ArrayList<>();
        recyclerViewRankingAvanzadoNumeros=(RecyclerView)findViewById(R.id.idRecyclerListaRankingNumeroAvanzado);
        recyclerViewRankingAvanzadoNumeros.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));

        recyclerViewRankingAvanzadoNumeros.setHasFixedSize(true);
        request= Volley.newRequestQueue(this);
        llamarwebservice();

    }

    private void llamarwebservice() {
        progress = new ProgressDialog(this);
        progress.setMessage("Consultando Rank Numeros");
        progress.show();
        String url=getString(R.string.urlIP)+"pregunta/ConsultarRankingNumeroAvanzado.php?";


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
        Ranking ranking=null;

        JSONArray json=response.optJSONArray("rank_numero_avanzado");

        try {

            for (int i=0;i<json.length();i++){
                ranking=new Ranking();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);

                ranking.setIdrank(jsonObject.optInt("idrank"));
                ranking.setNombre(jsonObject.optString("nombre"));
                ranking.setPuntaje(jsonObject.optInt("puntaje"));


                listaRankingAvanzadoNumero.add(ranking);
            }
            progress.hide();
            NumeroRankingAvanzadoAdapter adapter=new NumeroRankingAvanzadoAdapter(listaRankingAvanzadoNumero);
            recyclerViewRankingAvanzadoNumeros.setAdapter(adapter);

        } catch ( JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error servidor", Toast.LENGTH_SHORT).show();
            progress.hide();
        }
    }
}