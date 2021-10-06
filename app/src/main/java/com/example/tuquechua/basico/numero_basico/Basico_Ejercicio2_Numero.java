package com.example.tuquechua.basico.numero_basico;

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
import com.example.tuquechua.basico.saludo_basico.Basico_Ejercicio2_Saludo;
import com.example.tuquechua.entidades.Pregunta;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Basico_Ejercicio2_Numero extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener, View.OnClickListener {
    ImageButton ibIniciar;
    //VideoView vvAudio; para video
    Spinner spRespuesta;
    Button btnSiguiente;
    TextView txtPregunta;

    EditText edtrespuesta;
    ProgressDialog progreso;
    ImageView campoImagen;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basico__ejercicio2__numero);
        ibIniciar=findViewById(R.id.ibIniciar2numero);
        txtPregunta= findViewById(R.id.txtPregunta);
        btnSiguiente=findViewById(R.id.btnSiguiente2_numero);
        campoImagen=findViewById(R.id.imagenId2numero);
        edtrespuesta=findViewById(R.id.etRespuesta2_basica_numero);
        request= Volley.newRequestQueue(this);
        progreso=new ProgressDialog(this);
        progreso.setMessage("Consultando...");
        progreso.show();
        String url="http://192.168.1.195:85/pregunta/wsJSONConsultarPreguntaImagen.php?id="+27;

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
        btnSiguiente.setOnClickListener(this);
    }
    public void iniciar(View view) {
        /*String path = "android.resource://" + getPackageName() + "/" + R.raw.cuatro;
        vvAudio.setVideoURI(Uri.parse(path)); para video
        vvAudio.seekTo(0); vvAudio.start();*/
        MediaPlayer mp= MediaPlayer.create(this, R.raw.cuatro_audio);
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
        Pregunta miPregunta12=new Pregunta();
        JSONArray json=response.optJSONArray("idpregunta");
        JSONObject jsonObject=null;
        try {
            jsonObject=json.getJSONObject(0);

            miPregunta12.setPregunta(jsonObject.optString("pregunta"));
            miPregunta12.setDato(jsonObject.optString("imagen"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        txtPregunta.setText(miPregunta12.getPregunta()+"");

        if (miPregunta12.getImagen()!=null){
            campoImagen.setImageBitmap(miPregunta12.getImagen());
        }else{
            campoImagen.setImageResource(R.drawable.img_base);
        }
    }

    @Override
    public void onClick(View v) {
        Intent im= new Intent(this, Basico_Ejercicio3_Numero.class);
        startActivity(im);
    }
}