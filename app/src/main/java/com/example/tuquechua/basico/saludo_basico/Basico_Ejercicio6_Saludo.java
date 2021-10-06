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
    Button btnop1, btnop2, btnop3, btnop4;
    TextView txtPregunta;
    String opbtn1, opbtn2, opbtn3, opbtn4, rptaCorrecta;
    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basico__ejercicio6__saludo);
        ibIniciar=findViewById(R.id.ibIniciar);
        btnop1 =findViewById(R.id.btnOp1);
        btnop2 =findViewById(R.id.btnOp2);
        btnop3 =findViewById(R.id.btnOp3);
        btnop4 =findViewById(R.id.btnOp4);
        txtPregunta= findViewById(R.id.txtPregunta);
        request= Volley.newRequestQueue(this);
        progreso=new ProgressDialog(this);
        progreso.setMessage("Consultando...");
        progreso.show();

        String url="http://192.168.1.195:85/pregunta/wsJSONConsultarPreguntaImagen.php?id="+24;

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }

    public void iniciar(View view) {
        MediaPlayer mp= MediaPlayer.create(this, R.raw.aycha_carne); //allin muyun buen dia
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
        Pregunta miPregunta=new Pregunta();
        JSONArray json=response.optJSONArray("idpregunta");
        JSONObject jsonObject=null;
        try {
            jsonObject=json.getJSONObject(0);

            miPregunta.setPregunta(jsonObject.optString("pregunta"));
            miPregunta.setOp1(jsonObject.optString("op1"));
            miPregunta.setOp2(jsonObject.optString("op2"));
            miPregunta.setOp3(jsonObject.optString("op3"));
            miPregunta.setOp4(jsonObject.optString("op4"));
            miPregunta.setPalabra(jsonObject.optString("palabra"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        txtPregunta.setText(miPregunta.getPregunta()+"");
        btnop1.setText(miPregunta.getOp1()+"");
        btnop2.setText(miPregunta.getOp2()+"");
        btnop3.setText(miPregunta.getOp3()+"");
        btnop4.setText(miPregunta.getOp4()+"");
        opbtn1 =miPregunta.getOp1().toString();
        opbtn2 =miPregunta.getOp2().toString();
        opbtn3 =miPregunta.getOp3().toString();
        opbtn4 =miPregunta.getOp4().toString();
        this.rptaCorrecta=miPregunta.getPalabra();

        btnop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarProcesarCalculo(opbtn1);
            }
        });
        btnop2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarProcesarCalculo(opbtn2);
            }
        });
        btnop3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarProcesarCalculo(opbtn3);
            }
        });
        btnop4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarProcesarCalculo(opbtn4);
            }
        });
    }

    public void lanzarProcesarCalculo(String opbutton) {
        Intent i = new Intent(this, procesarBasicoSaludo.class);
        int punt = getIntent().getIntExtra("puntaje",0);

        if(opbutton.equalsIgnoreCase(this.rptaCorrecta)) {
            Toast.makeText(getApplicationContext(), rptaCorrecta + ", Respuesta correcta", Toast.LENGTH_SHORT).show();
            i.putExtra("puntaje", punt+5);
        }else {
            Toast.makeText(getApplicationContext(), "Respuesta incorrecta, *" + rptaCorrecta, Toast.LENGTH_SHORT).show();
            i.putExtra("puntaje", punt);
        }
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed()
    {
        Toast.makeText(this,"No puedes retroceder",Toast.LENGTH_SHORT).show();
    }
}