package com.example.tuquechua.intermedio.comida_intermedio;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.example.tuquechua.basico.comida_basico.Basico_Ejercicio2_Comida;
import com.example.tuquechua.entidades.Pregunta;
import com.example.tuquechua.intermedio.familia_intermedio.Intermedio_Ejercicio4_Familia;
import com.example.tuquechua.intermedio.familia_intermedio.Intermedio_Frase_Familia;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Intermedio_Ejercicio3_Comida extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener, View.OnClickListener {
    String respuesta1,respuesta2,respuesta3,respuesta4;
    String rptaOp1,rptaOp2,rptaOp3,rptaOp4;
    ImageView imagen;
    TextView pregunta;
    String op1, op2, op3, op4;
    Button btnSiguiente;
    EditText rpta1,rpta2,rpta3,rpta4;
    private TextView txtSecYNiv, txtPuntosEjer;
    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermedio__ejercicio3__comida);
        rpta1=findViewById(R.id.etRespuesta1);
        rpta2=findViewById(R.id.etRespuesta2);
        rpta3=findViewById(R.id.etRespuesta3);
        rpta4=findViewById(R.id.etRespuesta4);
        imagen= findViewById(R.id.ivImagen);
        pregunta= findViewById(R.id.tvPregunta);
        btnSiguiente = findViewById(R.id.btnSiguiente);
        txtSecYNiv = findViewById(R.id.txtSecNiv);
        txtPuntosEjer = findViewById(R.id.txtPuntos);

        txtSecYNiv.setText("COMIDA | INTERMEDIO");
        txtPuntosEjer.setText("5 puntos");

        request = Volley.newRequestQueue(this);

        llamarWebService();

        btnSiguiente.setOnClickListener(this);
    }

    private void llamarWebService(){
        progreso = new ProgressDialog(this);
        progreso.setMessage("Consultando...");
        progreso.show();

        String url=getString(R.string.urlIP)+"pregunta/wsJSONConsultarPreguntaIntermedio.php?id="+13;

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onClick(View v) {
        respuesta1 = rpta1.getText().toString();
        respuesta2 = rpta2.getText().toString();
        respuesta3 = rpta3.getText().toString();
        respuesta4 = rpta4.getText().toString();

        int punt = getIntent().getIntExtra("puntaje", 0);
        char sec = getIntent().getCharExtra("seccion", '0');
        Intent i = new Intent(this, Intermedio_Ejercicio4_Comida.class);

        if((respuesta1.equals("") && respuesta2.equals("") && respuesta3.equals("") && respuesta4.equals("")) ||
                (respuesta1.equals("") || respuesta2.equals("") || respuesta3.equals("") || respuesta4.equals("")))
        {
            Toast.makeText(this,"No debe dejar ningun casillero sin completar",Toast.LENGTH_SHORT).show();

        }else if (respuesta1.equalsIgnoreCase(rptaOp1) && respuesta2.equalsIgnoreCase(rptaOp2) &&
                respuesta3.equalsIgnoreCase(rptaOp3) && respuesta4.equalsIgnoreCase(rptaOp4)){
            Toast.makeText(this, respuesta1+", Respuesta correcta",Toast.LENGTH_SHORT).show();
            Toast.makeText(this, respuesta2+", Respuesta correcta",Toast.LENGTH_SHORT).show();
            Toast.makeText(this, respuesta3+", Respuesta correcta",Toast.LENGTH_SHORT).show();
            Toast.makeText(this, respuesta4+", Respuesta correcta",Toast.LENGTH_SHORT).show();
            i.putExtra("puntaje", punt+5);
            i.putExtra("seccion", sec);
            startActivity(i);
            finish();
        }else{
            Toast.makeText(this,"Respuesta incorrecta, *"+respuesta1,Toast.LENGTH_SHORT).show();
            Toast.makeText(this,"Respuesta incorrecta, *"+respuesta2,Toast.LENGTH_SHORT).show();
            Toast.makeText(this,"Respuesta incorrecta, *"+respuesta3,Toast.LENGTH_SHORT).show();
            Toast.makeText(this,"Respuesta incorrecta, *"+respuesta4,Toast.LENGTH_SHORT).show();
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
            miPregunta.setOp3(jsonObject.optString("op3"));
            miPregunta.setOp4(jsonObject.optString("op4"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        pregunta.setText(miPregunta.getPregunta()+"");
        this.rptaOp1 = miPregunta.getOp1();
        this.rptaOp2 = miPregunta.getOp2();
        this.rptaOp3 = miPregunta.getOp3();
        this.rptaOp4 = miPregunta.getOp4();

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