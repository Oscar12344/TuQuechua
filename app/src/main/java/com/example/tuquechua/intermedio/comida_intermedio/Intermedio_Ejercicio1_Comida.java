package com.example.tuquechua.intermedio.comida_intermedio;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tuquechua.R;
import com.example.tuquechua.basico.comida_basico.procesarBasicoComida;
import com.example.tuquechua.entidades.Pregunta;
import com.example.tuquechua.intermedio.familia_intermedio.Intermedio_Frase_Familia;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Intermedio_Ejercicio1_Comida extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {
    TextView tvPregunta;
    String rptaCorrecta;
    ImageView ivimagen1,ivimagen2;
    RadioButton rbop1,rbop2,rbop3,rbop4;
    String opbtn1, opbtn2, opbtn3, opbtn4;
    private TextView txtSecYNiv, txtPuntosEjer;
    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermedio__ejercicio1__comida);
        tvPregunta = findViewById(R.id.tvPregunta);
        ivimagen1 = findViewById(R.id.ivOp1);
        ivimagen2 = findViewById(R.id.ivOp2);
        rbop1 = findViewById(R.id.rbOp1);
        rbop2 = findViewById(R.id.rbOp2);
        rbop3 = findViewById(R.id.rbOp3);
        rbop4 = findViewById(R.id.rbOp4);
        txtSecYNiv = findViewById(R.id.txtSecNiv);
        txtPuntosEjer = findViewById(R.id.txtPuntos);

        txtSecYNiv.setText("COMIDA | INTERMEDIO");
        txtPuntosEjer.setText("5 puntos");

        request= Volley.newRequestQueue(this);
        progreso=new ProgressDialog(this);
        progreso.setMessage("Consultando...");
        progreso.show();

        String url = getString(R.string.urlIntermedio)+1;

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
        /*rbop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(), Intermedio_Frase_Familia.class);
                startActivity(i);
            }
        });*/
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
        Pregunta miPregunta = new Pregunta();
        JSONArray json = response.optJSONArray("intermedios");
        JSONObject jsonObject = null;
        try {
            jsonObject=json.getJSONObject(0);

            miPregunta.setPregunta(jsonObject.optString("pregunta"));
            miPregunta.setDato1(jsonObject.optString("op1Imagen"));
            miPregunta.setDato2(jsonObject.optString("op2Imagen"));
            miPregunta.setOp1(jsonObject.optString("op1"));
            miPregunta.setOp2(jsonObject.optString("op2"));
            miPregunta.setOp3(jsonObject.optString("op3"));
            miPregunta.setOp4(jsonObject.optString("op4"));
            miPregunta.setPalabra(jsonObject.optString("palabra"));


        } catch (JSONException e) {
            e.printStackTrace();
        }

        tvPregunta.setText(miPregunta.getPregunta()+"");
        rbop1.setText(miPregunta.getOp1());
        rbop2.setText(miPregunta.getOp2());
        rbop3.setText(miPregunta.getOp3());
        rbop4.setText(miPregunta.getOp4());
        opbtn1 =miPregunta.getOp1().toString();
        opbtn2 =miPregunta.getOp2().toString();
        opbtn3 =miPregunta.getOp3().toString();
        opbtn4 =miPregunta.getOp4().toString();
        this.rptaCorrecta=miPregunta.getPalabra();

        if (miPregunta.getOp1Imagen()!=null){
            ivimagen1.setImageBitmap(miPregunta.getOp1Imagen());
        }else{
            ivimagen1.setImageResource(R.drawable.img_base);
        }
        if (miPregunta.getOp2Imagen()!=null){
            ivimagen2.setImageBitmap(miPregunta.getOp2Imagen());
        }else{
            ivimagen2.setImageResource(R.drawable.img_base);
        }
        rbop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                procesar(opbtn1);
            }
        });
        rbop2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                procesar(opbtn2);
            }
        });
        rbop3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                procesar(opbtn3);
            }
        });
        rbop4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                procesar(opbtn4);
            }
        });

    }

    public void procesar(String opbutton) {

        int punt = 0;
        //char seccion = getIntent().getCharExtra("seccion", '0');

        Intent i = new Intent(this, Intermedio_Ejercicio2.class);
        i.putExtra("seccion", 'c');

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