package com.example.tuquechua;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tuquechua.entidades.Pregunta;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Basico_Ejercicio4_Numero extends AppCompatActivity implements  Response.Listener<JSONObject>,Response.ErrorListener {
    ImageButton btnIniciar;
    //VideoView vvAudio; video
    Spinner spRespuesta;
    Button btnSiguiente;
    TextView txtPregunta;

    ProgressDialog progreso;
    ImageView campoImagen;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
   //int posicion = 0; video
    String []respuestas={"Eliga una opción","Tawa","Wallpa","Yaku","Challwa","Quwi"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basico__ejercicio4__numero);
        btnIniciar=findViewById(R.id.ibIniciar4numero);
        spRespuesta=findViewById(R.id.spRespuesta);

        btnSiguiente= findViewById(R.id.btnSiguiente4_numero);
        txtPregunta= (TextView) findViewById(R.id.txtPregunta);
        campoImagen=(ImageView) findViewById(R.id.imagenId);
        request= Volley.newRequestQueue(this);
        progreso=new ProgressDialog(this);
        progreso.setMessage("Consultando...");
        progreso.show();



        String url="http://192.168.1.195:85/pregunta/wsJSONConsultarPreguntaImagen.php?id="+19;


        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);

        ArrayAdapter<String> adapter1= new
                ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,respuestas);
        spRespuesta.setAdapter(adapter1);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String r1=spRespuesta.getSelectedItem().toString();
                procesar(r1);


            }
        });
    }
    public void iniciar(View view) {
        /*String path = "android.resource://" + getPackageName() + "/" + R.raw.cuatro;
        vvAudio.setVideoURI(Uri.parse(path)); para video
        vvAudio.seekTo(0); vvAudio.start();*/
        MediaPlayer mp= MediaPlayer.create(this, R.raw.cuatro_audio);
        mp.start();
    }
    public void procesar(String nom)
    {
        switch (nom)
        {
            case "Tawa":
                Toast.makeText(getApplicationContext(), nom+" Respuesta correcta", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, Basico_Ejercicio1_Saludo.class);
                startActivity(i);

                break;
            case "Wallpa":
                Toast.makeText(getApplicationContext(), "Respuesta Incorrecta", Toast.LENGTH_SHORT).show();
                break;


            case "Yaku":
                Toast.makeText(getApplicationContext(), "Respuesta Incorrecta", Toast.LENGTH_SHORT).show();
                break;
            case "Challwa":
                Toast.makeText(getApplicationContext(), "Respuesta Incorrecta", Toast.LENGTH_SHORT).show();
                break;
            case "Quwi":
                Toast.makeText(getApplicationContext(), "Respuesta Incorrecta", Toast.LENGTH_SHORT).show();
                break;

        }
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
        Pregunta miPregunta6=new Pregunta();
        JSONArray json=response.optJSONArray("idpregunta");
        JSONObject jsonObject=null;
        try {
            jsonObject=json.getJSONObject(0);
            miPregunta6.setPalabra(jsonObject.optString("palabra"));
            miPregunta6.setPregunta(jsonObject.optString("pregunta"));
            miPregunta6.setDato(jsonObject.optString("imagen"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        txtPregunta.setText(miPregunta6.getPregunta()+"");
        if (miPregunta6.getImagen()!=null){
            campoImagen.setImageBitmap(miPregunta6.getImagen());
        }else{
            campoImagen.setImageResource(R.drawable.img_base);
        }
    }
}