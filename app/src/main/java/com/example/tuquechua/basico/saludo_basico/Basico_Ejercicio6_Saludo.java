package com.example.tuquechua.basico.saludo_basico;

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

public class Basico_Ejercicio6_Saludo extends AppCompatActivity implements  Response.Listener<JSONObject>,Response.ErrorListener {
    ImageButton ibIniciar;
    Button btnop1_6saludo,btnop2_6saludo,btnop3_6saludo,btnop4_6saludo;
    TextView txtPregunta;
    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basico__ejercicio6__saludo);
        ibIniciar=findViewById(R.id.ibIniciar6saludo);
        btnop1_6saludo=findViewById(R.id.btnOp1_6saludo);
        btnop2_6saludo=findViewById(R.id.btnOp2_6saludo);
        btnop3_6saludo=findViewById(R.id.btnOp3_6saludo);
        txtPregunta= findViewById(R.id.txtPregunta);
        btnop4_6saludo=findViewById(R.id.btnOp4_6saludo);
        request= Volley.newRequestQueue(this);
        progreso=new ProgressDialog(this);
        progreso.setMessage("Consultando...");
        progreso.show();
        String url="http://192.168.1.195:85/pregunta/wsJSONConsultarPreguntaImagen.php?id="+24;


        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);

    }
    public void iniciar(View view) {
        /*String path = "android.resource://" + getPackageName() + "/" + R.raw.buenos_dias;
        vvAudio.setVideoURI(Uri.parse(path)); para video
        vvAudio.seekTo(0); vvAudio.start();*/
        MediaPlayer mp= MediaPlayer.create(this, R.raw.allintuuta_buenasnoches);
        mp.start();

    }
    public void irOpcionCorrecta(View v)
    {
        Intent i = new Intent(this, procesarBasicoSaludo.class);
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
        Pregunta miPregunta10=new Pregunta();
        JSONArray json=response.optJSONArray("idpregunta");
        JSONObject jsonObject=null;
        try {
            jsonObject=json.getJSONObject(0);

            miPregunta10.setPregunta(jsonObject.optString("pregunta"));
            miPregunta10.setOp1(jsonObject.optString("op1"));
            miPregunta10.setOp2(jsonObject.optString("op2"));
            miPregunta10.setOp3(jsonObject.optString("op3"));
            miPregunta10.setOp4(jsonObject.optString("op4"));



        } catch (JSONException e) {
            e.printStackTrace();
        }

        txtPregunta.setText(miPregunta10.getPregunta()+"");
        btnop1_6saludo.setText(miPregunta10.getOp1()+"");
        btnop2_6saludo.setText(miPregunta10.getOp2()+"");
        btnop3_6saludo.setText(miPregunta10.getOp3()+"");
        btnop4_6saludo.setText(miPregunta10.getOp4()+"");
    }
}