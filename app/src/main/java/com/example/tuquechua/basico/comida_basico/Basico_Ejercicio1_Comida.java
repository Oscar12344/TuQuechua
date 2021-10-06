package com.example.tuquechua.basico.comida_basico;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
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

public class Basico_Ejercicio1_Comida extends AppCompatActivity implements View.OnClickListener,  Response.Listener<JSONObject>,Response.ErrorListener {
    EditText etRespuesta1;
    Button btnSiguiente1;
    String respuesta1comida;
    TextView txtPalabra,txtPregunta;
    ProgressDialog progreso;
    ImageView campoImagen;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basico__ejercicio1_comida);
        etRespuesta1=findViewById(R.id.etRespuesta1_basica_saludo);
        btnSiguiente1=findViewById(R.id.btnSiguiente1);
        txtPalabra= (TextView) findViewById(R.id.txtPalabra);
        txtPregunta= (TextView) findViewById(R.id.txtPregunta);
        campoImagen=(ImageView) findViewById(R.id.imagenId1comida);
        request= Volley.newRequestQueue(this);
        progreso=new ProgressDialog(this);
        progreso.setMessage("Consultando...");
        progreso.show();

        String url="http://192.168.1.195:85/pregunta/wsJSONConsultarPreguntaImagen.php?id="+13;

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
        btnSiguiente1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        respuesta1comida= etRespuesta1.getText().toString();

        if(!respuesta1comida.equals(""))
        {
            lanzarProcesarCalculo(v, respuesta1comida);
        }
        else
        {
            Toast.makeText(this,"Escriba su respuesta",Toast.LENGTH_SHORT).show();
            etRespuesta1.requestFocus();
            InputMethodManager imm= (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
            imm.showSoftInput(etRespuesta1, InputMethodManager.SHOW_IMPLICIT);

        }



    }
    @Override
    public void onBackPressed()
    {
        Toast.makeText(this,"No puedes retroceder",Toast.LENGTH_SHORT).show();
    }

    public void lanzarProcesarCalculo(View v, String respuesta1comida) {
        Intent i = new Intent(this,Basico_Ejercicio2_Comida.class);
        i.putExtra("rpta1Comida", respuesta1comida);
        startActivity(i);
        finish();

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
        //Toast.makeText(this, "Mensaje: "+response,Toast.LENGTH_SHORT).show();
        Pregunta miPregunta=new Pregunta();
        JSONArray json=response.optJSONArray("idpregunta");
        JSONObject jsonObject=null;
        try {
            jsonObject=json.getJSONObject(0);
            miPregunta.setPalabra(jsonObject.optString("palabra"));
            miPregunta.setPregunta(jsonObject.optString("pregunta"));
            miPregunta.setDato(jsonObject.optString("imagen"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        txtPalabra.setText(miPregunta.getPalabra()+"");
        txtPregunta.setText(miPregunta.getPregunta()+"");

        if (miPregunta.getImagen()!=null){
            campoImagen.setImageBitmap(miPregunta.getImagen());
        }else{
            campoImagen.setImageResource(R.drawable.img_base);
        }
    }
}