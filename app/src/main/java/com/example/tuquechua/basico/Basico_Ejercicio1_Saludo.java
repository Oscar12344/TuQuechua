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

public class Basico_Ejercicio1_Saludo extends AppCompatActivity implements View.OnClickListener,  Response.Listener<JSONObject>,Response.ErrorListener  {
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
        setContentView(R.layout.activity_basico__ejercicio1__saludo);
        etRespuesta1=findViewById(R.id.etRespuesta1_basica_saludo);
        btnSiguiente1=findViewById(R.id.btnSiguiente1);

        txtPalabra= (TextView) findViewById(R.id.txtPalabra);
        txtPregunta= (TextView) findViewById(R.id.txtPregunta);
        campoImagen=(ImageView) findViewById(R.id.imagenId);
        request= Volley.newRequestQueue(this);
        progreso=new ProgressDialog(this);
        progreso.setMessage("Consultando...");
        progreso.show();



        String url="http://192.168.1.7:80/pregunta/wsJSONConsultarPreguntaImagen.php?id="+16;


        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
        btnSiguiente1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String respuesta1;

        respuesta1= etRespuesta1.getText().toString();
        if(respuesta1.equals("Buenos Dias") || respuesta1.equals("buenos dias"))
        {
            Toast.makeText(this, "La respuesta es correcta", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, Basico_Ejercicio4_Saludo.class);
            startActivity(i);
        }
        else
        {
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
        Pregunta miPregunta4=new Pregunta();
        JSONArray json=response.optJSONArray("idpregunta");
        JSONObject jsonObject=null;
        try {
            jsonObject=json.getJSONObject(0);
            miPregunta4.setPalabra(jsonObject.optString("palabra"));
            miPregunta4.setPregunta(jsonObject.optString("pregunta"));
            miPregunta4.setDato(jsonObject.optString("imagen"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        txtPalabra.setText(miPregunta4.getPalabra()+"");
        txtPregunta.setText(miPregunta4.getPregunta()+"");
        if (miPregunta4.getImagen()!=null){
            campoImagen.setImageBitmap(miPregunta4.getImagen());
        }else{
            campoImagen.setImageResource(R.drawable.img_base);
        }
    }
}