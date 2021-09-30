package com.example.tuquechua.basico;

import androidx.appcompat.app.AppCompatActivity;

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


public class Basico_Ejercicio1_Familia extends AppCompatActivity implements View.OnClickListener,  Response.Listener<JSONObject>,Response.ErrorListener  {
    EditText etRespuesta1;
    Button btnSiguiente1;
    TextView txtPalabra,txtPregunta;

    ProgressDialog progreso;
    ImageView campoImagen;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basico__ejercicio1__familia);
        etRespuesta1 = findViewById(R.id.etRespuesta1_basica_familia);
        btnSiguiente1 = findViewById(R.id.btnSiguiente1);
        txtPalabra= (TextView) findViewById(R.id.txtPalabra);
        txtPregunta= (TextView) findViewById(R.id.txtPregunta);
        campoImagen=(ImageView) findViewById(R.id.imagenId);
        request= Volley.newRequestQueue(this);
        progreso=new ProgressDialog(this);
        progreso.setMessage("Consultando...");
        progreso.show();



        String url="http://192.168.1.7:80/pregunta/wsJSONConsultarPreguntaImagen.php?id="+14;


        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);


        btnSiguiente1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String respuesta1;

        respuesta1 = etRespuesta1.getText().toString();
        if (respuesta1.equals("Familia") || respuesta1.equals("familia")) {
            Toast.makeText(this, "La respuesta es correcta", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, Basico_Ejercicio4_Familia.class);
            startActivity(i);
        } else {
            Toast.makeText(this, "La respuesta incorrecta", Toast.LENGTH_SHORT).show();
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
        Pregunta miPregunta1=new Pregunta();
        JSONArray json=response.optJSONArray("idpregunta");
        JSONObject jsonObject=null;
        try {
            jsonObject=json.getJSONObject(0);
            miPregunta1.setPalabra(jsonObject.optString("palabra"));
            miPregunta1.setPregunta(jsonObject.optString("pregunta"));
            miPregunta1.setDato(jsonObject.optString("imagen"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        txtPalabra.setText(miPregunta1.getPalabra()+"");
        txtPregunta.setText(miPregunta1.getPregunta()+"");
        if (miPregunta1.getImagen()!=null){
            campoImagen.setImageBitmap(miPregunta1.getImagen());
        }else{
            campoImagen.setImageResource(R.drawable.img_base);
        }
    }
}