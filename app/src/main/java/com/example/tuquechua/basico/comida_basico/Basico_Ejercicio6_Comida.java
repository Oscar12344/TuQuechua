package com.example.tuquechua.basico.comida_basico;

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
import com.example.tuquechua.basico.familia_basico.Basico_Ejercicio6_Familia;
import com.example.tuquechua.entidades.Pregunta;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Basico_Ejercicio6_Comida extends AppCompatActivity implements  Response.Listener<JSONObject>,Response.ErrorListener{
    ImageButton ibIniciar;
    Button btnop1_6comida,btnop2_6comida,btnop3_6comida,btnop4_6comida;
    TextView txtPregunta;
    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    String opbuttoncomida1,opbuttoncomida2,opbuttoncomida3,opbuttoncomida4;

    String r1anterior, r2anterior, r3anterior,r4anterior,r5anterior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basico__ejercicio6__comida);
        ibIniciar=findViewById(R.id.ibIniciar6comida);
        btnop1_6comida=findViewById(R.id.btnOp1_6comida);
        btnop2_6comida=findViewById(R.id.btnOp2_6comida);
        btnop3_6comida=findViewById(R.id.btnOp3_6comida);
        btnop4_6comida=findViewById(R.id.btnOp4_6comida);
        txtPregunta= findViewById(R.id.txtPregunta);
        request= Volley.newRequestQueue(this);
        progreso=new ProgressDialog(this);
        progreso.setMessage("Consultando...");
        progreso.show();
        String url="http://192.168.1.195:85/pregunta/wsJSONConsultarPreguntaImagen.php?id="+21;


        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);


    }
    public void iniciar(View view) {
        /*String path = "android.resource://" + getPackageName() + "/" + R.raw.buenos_dias;
        vvAudio.setVideoURI(Uri.parse(path)); para video
        vvAudio.seekTo(0); vvAudio.start();*/
        MediaPlayer mp= MediaPlayer.create(this, R.raw.aycha_carne);
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
        Pregunta miPregunta7=new Pregunta();
        JSONArray json=response.optJSONArray("idpregunta");
        JSONObject jsonObject=null;
        try {
            jsonObject=json.getJSONObject(0);

            miPregunta7.setPregunta(jsonObject.optString("pregunta"));
            miPregunta7.setOp1(jsonObject.optString("op1"));
            miPregunta7.setOp2(jsonObject.optString("op2"));
            miPregunta7.setOp3(jsonObject.optString("op3"));
            miPregunta7.setOp4(jsonObject.optString("op4"));





        } catch (JSONException e) {
            e.printStackTrace();
        }

        txtPregunta.setText(miPregunta7.getPregunta()+"");
        btnop1_6comida.setText(miPregunta7.getOp1()+"");
        btnop2_6comida.setText(miPregunta7.getOp2()+"");
        btnop3_6comida.setText(miPregunta7.getOp3()+"");
        btnop4_6comida.setText(miPregunta7.getOp4()+"");
        opbuttoncomida1=miPregunta7.getOp1().toString();
        opbuttoncomida2=miPregunta7.getOp2().toString();
        opbuttoncomida3=miPregunta7.getOp3().toString();
        opbuttoncomida4=miPregunta7.getOp4().toString();
        r1anterior= getIntent().getStringExtra("rpta_1comida");
        r2anterior= getIntent().getStringExtra("rpta_2comida");
        r3anterior= getIntent().getStringExtra("rpta_3comida");
        r4anterior= getIntent().getStringExtra("rpta_4comida");
        r5anterior= getIntent().getStringExtra("op5_comida");
        btnop1_6comida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lanzarProcesarCalculo(opbuttoncomida1,r1anterior,r2anterior,r3anterior,r4anterior,r5anterior);

            }
        });
        btnop2_6comida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarProcesarCalculo(opbuttoncomida2,r1anterior,r2anterior,r3anterior,r4anterior,r5anterior);
            }
        });
        btnop3_6comida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarProcesarCalculo(opbuttoncomida3,r1anterior,r2anterior,r3anterior,r4anterior,r5anterior);
            }
        });
        btnop4_6comida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarProcesarCalculo(opbuttoncomida4,r1anterior,r2anterior,r3anterior,r4anterior,r5anterior);
            }
        });


    }

    public void lanzarProcesarCalculo(String opbuttoncomida1, String r1anterior, String r2anterior, String r3anterior, String r4anterior, String r5anterior) {
        Intent i = new Intent(this, procesarBasicoComida.class);
        i.putExtra("r_1comida", r1anterior);
        i.putExtra("r_2comida", r2anterior);
        i.putExtra("r_3comida", r3anterior);
        i.putExtra("r_4comida", r4anterior);
        i.putExtra("r_5comida", r5anterior);
        i.putExtra("op6_comida", opbuttoncomida1);
        startActivity(i);

    }
}