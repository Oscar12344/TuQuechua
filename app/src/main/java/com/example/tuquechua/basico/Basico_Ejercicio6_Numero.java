package com.example.tuquechua.basico;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tuquechua.R;
import com.example.tuquechua.entidades.Pregunta;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Basico_Ejercicio6_Numero extends AppCompatActivity implements  Response.Listener<JSONObject>,Response.ErrorListener  {
    ImageButton ibIniciar;
    Button btnop1_6numero,btnop2_6numero,btnop3_6numero,btnop4_6numero;
    TextView txtPregunta;
    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basico__ejercicio6__numero);
        ibIniciar=findViewById(R.id.ibIniciar6numero);
        btnop1_6numero=findViewById(R.id.btnOp1_6numero);
        btnop2_6numero=findViewById(R.id.btnOp2_6numero);
        txtPregunta= findViewById(R.id.txtPregunta);
        btnop3_6numero=findViewById(R.id.btnOp3_6numero);
        btnop4_6numero=findViewById(R.id.btnOp4_6numero);
        request= Volley.newRequestQueue(this);
        progreso=new ProgressDialog(this);
        progreso.setMessage("Consultando...");
        progreso.show();
        String url="http://192.168.1.195:85/pregunta/wsJSONConsultarPreguntaImagen.php?id="+23;


        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }
    public void iniciar(View view) {
        /*String path = "android.resource://" + getPackageName() + "/" + R.raw.buenos_dias;
        vvAudio.setVideoURI(Uri.parse(path)); para video
        vvAudio.seekTo(0); vvAudio.start();*/
        MediaPlayer mp= MediaPlayer.create(this, R.raw.juc_one);
        mp.start();

    }
    public void irOpcionCorrecta(View v)
    {
        Intent i = new Intent(this, Basico_Ejercicio6_Saludo.class);
        startActivity(i);
    }
    public void irOpcionIncorrecta1(View v)
    {
        Toast.makeText(getApplicationContext(), "Respuesta Incorrecta", Toast.LENGTH_SHORT).show();
    }
    public void irOpcionIncorrecta2(View v)
    {
        Toast.makeText(getApplicationContext(), "Respuesta Incorrecta", Toast.LENGTH_SHORT).show();
    }
    public void irOpcionIncorrecta3(View v)
    {
        Toast.makeText(getApplicationContext(), "Respuesta Incorrecta", Toast.LENGTH_SHORT).show();
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
        Pregunta miPregunta9=new Pregunta();
        JSONArray json=response.optJSONArray("idpregunta");
        JSONObject jsonObject=null;
        try {
            jsonObject=json.getJSONObject(0);

            miPregunta9.setPregunta(jsonObject.optString("pregunta"));

            miPregunta9.setOp1(jsonObject.optString("op1"));
            miPregunta9.setOp2(jsonObject.optString("op2"));
            miPregunta9.setOp3(jsonObject.optString("op3"));
            miPregunta9.setOp4(jsonObject.optString("op4"));



        } catch (JSONException e) {
            e.printStackTrace();
        }

        txtPregunta.setText(miPregunta9.getPregunta()+"");
        btnop1_6numero.setText(miPregunta9.getOp1()+"");
        btnop2_6numero.setText(miPregunta9.getOp2()+"");
        btnop3_6numero.setText(miPregunta9.getOp3()+"");
        btnop4_6numero.setText(miPregunta9.getOp4()+"");
    }
}