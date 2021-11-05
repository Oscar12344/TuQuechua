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
import com.example.tuquechua.adapter.ComidaRankingAdapter;
import com.example.tuquechua.adapter.SaludoRankingAdapter;
import com.example.tuquechua.entidades.Ranking;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RankingSaludo extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{
    RecyclerView recyclerViewRankingSaludos;
    ArrayList<Ranking> listaRankingSaludo;
    Button btnSiguiente;

    ProgressDialog progress;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking_saludo);
        listaRankingSaludo = new ArrayList<>();
        recyclerViewRankingSaludos=(RecyclerView)findViewById(R.id.idRecyclerListaRankingSaludo);
        recyclerViewRankingSaludos.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));

        recyclerViewRankingSaludos.setHasFixedSize(true);
        request= Volley.newRequestQueue(this);
        llamarwebservice();
    }

    public void llamarwebservice() {
        progress = new ProgressDialog(this);
        progress.setMessage("Consultando Rank Saludos");
        progress.show();
        String url="http://192.168.1.195:85/pregunta/ConsultarRankingSaludo.php?";


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

        JSONArray json=response.optJSONArray("rank_saludo");

        try {

            for (int i=0;i<json.length();i++){
                ranking=new Ranking();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);

                ranking.setIdrank(jsonObject.optInt("idrank"));
                ranking.setNombre(jsonObject.optString("nombre"));
                ranking.setPuntaje(jsonObject.optInt("puntaje"));


                listaRankingSaludo.add(ranking);
            }
            progress.hide();
            SaludoRankingAdapter adapter=new SaludoRankingAdapter(listaRankingSaludo);
            recyclerViewRankingSaludos.setAdapter(adapter);

        } catch ( JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error servidor", Toast.LENGTH_SHORT).show();
            progress.hide();
        }
    }
}