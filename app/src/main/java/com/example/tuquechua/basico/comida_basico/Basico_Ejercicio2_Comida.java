package com.example.tuquechua.basico.comida_basico;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tuquechua.R;
import com.example.tuquechua.basico.familia_basico.Basico_Ejercicio2_Familia;
import com.example.tuquechua.entidades.Pregunta;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Basico_Ejercicio2_Comida extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener, View.OnClickListener {
    ImageButton ibIniciar;
    //VideoView vvAudio; para video
    Spinner spRespuesta;
    Button btnSiguiente;
    TextView txtPregunta;
    String respuesta2comida, rpta1anterior;
    EditText edtrespuesta;
    ProgressDialog progreso;
    ImageView campoImagen;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basico__ejercicio2__comida);
        ibIniciar=findViewById(R.id.ibIniciar2comida);
        txtPregunta= findViewById(R.id.txtPregunta);
        btnSiguiente=findViewById(R.id.btnSiguiente2_comida);
        campoImagen=findViewById(R.id.imagenId);
        edtrespuesta=findViewById(R.id.etRespuesta2_basica_comida);
        request= Volley.newRequestQueue(this);
        rpta1anterior= getIntent().getStringExtra("rpta1Comida");
        progreso=new ProgressDialog(this);
        progreso.setMessage("Consultando...");
        progreso.show();
        String url="http://192.168.1.195:85/pregunta/wsJSONConsultarPreguntaImagen.php?id="+25;

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
        btnSiguiente.setOnClickListener(this);


    }
    public void iniciar(View view) {

        MediaPlayer mp= MediaPlayer.create(this, R.raw.sal_audio);
        mp.start();
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(getApplicationContext(), "No se pudo consultar"+error.toString(), Toast.LENGTH_SHORT).show();
        Log.i("Error", error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        progreso.hide();
        Toast.makeText(this, "Mensaje: "+response,Toast.LENGTH_SHORT).show();
        Pregunta miPregunta10=new Pregunta();
        JSONArray json=response.optJSONArray("idpregunta");
        JSONObject jsonObject=null;
        try {
            jsonObject=json.getJSONObject(0);

            miPregunta10.setPregunta(jsonObject.optString("pregunta"));
            miPregunta10.setDato(jsonObject.optString("imagen"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        txtPregunta.setText(miPregunta10.getPregunta()+"");

        if (miPregunta10.getImagen()!=null){
            campoImagen.setImageBitmap(miPregunta10.getImagen());
        }else{
            campoImagen.setImageResource(R.drawable.img_base);
        }
    }

    @Override
    public void onClick(View v) {

        respuesta2comida = edtrespuesta.getText().toString();

        lanzarProcesarCalculo(v, respuesta2comida, rpta1anterior);
    }

    public void lanzarProcesarCalculo(View v, String respuesta2comida, String rpta1anterior) {
        Intent i = new Intent(this,Basico_Ejercicio3_Comida.class);
        i.putExtra("rpta2comida", respuesta2comida);
        i.putExtra("rpta1comida_anterior1", rpta1anterior);
        startActivity(i);
    }
}