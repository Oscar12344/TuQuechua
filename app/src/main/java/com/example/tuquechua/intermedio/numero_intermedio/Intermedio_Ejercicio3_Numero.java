package com.example.tuquechua.intermedio.numero_intermedio;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tuquechua.R;
import com.example.tuquechua.entidades.Pregunta;
import com.example.tuquechua.intermedio.familia_intermedio.Intermedio_Ejercicio3_Familia;
import com.example.tuquechua.intermedio.saludo_intermedio.Intermedio_Frase_Saludo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Intermedio_Ejercicio3_Numero extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener, View.OnClickListener {
    String respuesta1,respuesta2;
    String rptaOp1,rptaOp2;
    ImageView imagen;
    TextView pregunta;
    String op1, op2;
    Button btnSiguiente;
    EditText rpta1,rpta2;
    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermedio__ejercicio3__numero);
        rpta1=findViewById(R.id.etRespuesta1);
        rpta2=findViewById(R.id.etRespuesta2);

        imagen= findViewById(R.id.ivImagen);
        pregunta= findViewById(R.id.tvPregunta);
        btnSiguiente = findViewById(R.id.btnSiguiente);
        request = Volley.newRequestQueue(this);
        progreso = new ProgressDialog(this);
        progreso.setMessage("Consultando...");
        progreso.show();
        String url="http://192.168.1.195:85/pregunta/wsJSONConsultarPreguntaIntermedio.php?id="+15;

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
        btnSiguiente.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        respuesta1 = rpta1.getText().toString();
        respuesta2 = rpta2.getText().toString();
        int punt = getIntent().getIntExtra("puntaje", 0);
        char sec = getIntent().getCharExtra("seccion", '0');

        Intent i = new Intent(this, Intermedio_Ejercicio4_Numero.class);
        if((respuesta1.equals("") && respuesta2.equals("")) ||
                (respuesta1.equals("") || respuesta2.equals("") ))
        {
            Toast.makeText(this,"No debe dejar ningun casillero sin completar",Toast.LENGTH_SHORT).show();

        }else if (respuesta1.equalsIgnoreCase(rptaOp1) && respuesta2.equalsIgnoreCase(rptaOp2)){
            Toast.makeText(this, respuesta1+", Respuesta correcta",Toast.LENGTH_SHORT).show();
            Toast.makeText(this, respuesta2+", Respuesta correcta",Toast.LENGTH_SHORT).show();
            i.putExtra("puntaje", punt+5);
            i.putExtra("seccion", sec);
            startActivity(i);
            finish();
        }else{
            Toast.makeText(this,"Respuesta incorrecta, *"+respuesta1,Toast.LENGTH_SHORT).show();
            Toast.makeText(this,"Respuesta incorrecta, *"+respuesta2,Toast.LENGTH_SHORT).show();
            i.putExtra("puntaje", punt);
            i.putExtra("seccion", sec);
            startActivity(i);
            finish();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(getApplicationContext(), "No se pudo consultar "+error.toString(), Toast.LENGTH_SHORT).show();
        Log.i("Error", error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        progreso.hide();
        Pregunta miPregunta = new Pregunta();
        JSONArray json = response.optJSONArray("intermedios");
        JSONObject jsonObject = null;
        try {
            jsonObject=json.getJSONObject(0);

            miPregunta.setPregunta(jsonObject.optString("pregunta"));
            miPregunta.setDato1(jsonObject.optString("op1Imagen"));
            miPregunta.setOp1(jsonObject.optString("op1"));
            miPregunta.setOp2(jsonObject.optString("op2"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        pregunta.setText(miPregunta.getPregunta()+"");
        this.rptaOp1 = miPregunta.getOp1();
        this.rptaOp2 = miPregunta.getOp2();

        if (miPregunta.getOp1Imagen()!=null){
            imagen.setImageBitmap(miPregunta.getOp1Imagen());
        }else{
            imagen.setImageResource(R.drawable.img_base);
        }
    }

    @Override
    public void onBackPressed()
    {
        Toast.makeText(this,"No puedes retroceder",Toast.LENGTH_SHORT).show();
    }
}