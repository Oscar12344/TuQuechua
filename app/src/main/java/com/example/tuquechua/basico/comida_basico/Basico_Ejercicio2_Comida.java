package com.example.tuquechua.basico.comida_basico;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tuquechua.R;

import org.json.JSONObject;

public class Basico_Ejercicio2_Comida extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {
    ImageButton ibIniciar;
    //VideoView vvAudio; para video
    Spinner spRespuesta;
    Button btnSiguiente;
    TextView txtPregunta;
    String op1,op2,op3,op4;
    EditText edtrespuesta;
    ProgressDialog progreso;
    ImageView campoImagen;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basico__ejercicio2__comida);
        ibIniciar=findViewById(R.id.ibIniciar2comida);
        btnSiguiente=findViewById(R.id.btnSiguiente2_comida);
        campoImagen=findViewById(R.id.idImagen);
        edtrespuesta=findViewById(R.id.etRespuesta2_basica_comida);
        request= Volley.newRequestQueue(this);
        progreso=new ProgressDialog(this);
        progreso.setMessage("Consultando...");
        progreso.show();



    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {

    }
}