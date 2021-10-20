package com.example.tuquechua.basico.numero_basico;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.example.tuquechua.basico.comida_basico.Basico_Ejercicio5_Comida;
import com.example.tuquechua.entidades.Pregunta;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Basico_Ejercicio5_Numero extends AppCompatActivity implements  Response.Listener<JSONObject>,Response.ErrorListener {
    TextView tvPregunta, tvPalabraENum, tvOp1, tvOp2, tvOp3, tvOp4;
    ImageButton ibtnOp1, ibtnOp2, ibtnOp3, ibtnOp4;
    String respuesta, rptaCorrecta;
    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basico__ejercicio5__numero);
        ibtnOp1 = findViewById(R.id.ibOp1);
        ibtnOp2 = findViewById(R.id.ibOp2);
        ibtnOp3 = findViewById(R.id.ibOp3);
        ibtnOp4 = findViewById(R.id.ibOp4);
        tvOp1 = findViewById(R.id.tvOp1);
        tvOp2 = findViewById(R.id.tvOp2);
        tvOp3 = findViewById(R.id.tvOp3);
        tvOp4 = findViewById(R.id.tvOp4);
        tvPregunta = findViewById(R.id.tvPregunta);
        tvPalabraENum = findViewById(R.id.tvPalabraE);

        request= Volley.newRequestQueue(this);
        progreso=new ProgressDialog(this);
        progreso.setMessage("Consultando...");
        progreso.show();

        String url=getString(R.string.urlBasico)+410;

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);

        ibtnOp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                respuesta = tvOp1.getText().toString();
                procesar(respuesta);
            }
        });
        ibtnOp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                respuesta = tvOp2.getText().toString();
                procesar(respuesta);
            }
        });
        ibtnOp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                respuesta = tvOp3.getText().toString();
                procesar(respuesta);
            }
        });
        ibtnOp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                respuesta = tvOp4.getText().toString();
                procesar(respuesta);
            }
        });
    }

    public void procesar(String rpta)
    {
        Intent i = new Intent(this, Basico_Ejercicio6_Numero.class);
        int punt = getIntent().getIntExtra("puntaje",0);

        if(rpta.equals(this.rptaCorrecta)) {
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
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(getApplicationContext(), "No se pudo consultar"+error.toString(), Toast.LENGTH_SHORT).show();
        Log.i("Error", error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        progreso.hide();
        Pregunta miPregunta = new Pregunta();
        JSONArray json = response.optJSONArray("idpregunta");
        JSONObject jsonObject = null;
        try {
            jsonObject=json.getJSONObject(0);
            miPregunta.setPalabraEsp(jsonObject.optString("palabraEspanol")+"");
            miPregunta.setPregunta(jsonObject.optString("pregunta"));
            miPregunta.setDato1(jsonObject.optString("op1Imagen"));
            miPregunta.setDato2(jsonObject.optString("op2Imagen"));
            miPregunta.setDato3(jsonObject.optString("op3Imagen"));
            miPregunta.setDato4(jsonObject.optString("op4Imagen"));
            miPregunta.setOp1(jsonObject.optString("op1"));
            miPregunta.setOp2(jsonObject.optString("op2"));
            miPregunta.setOp3(jsonObject.optString("op3"));
            miPregunta.setOp4(jsonObject.optString("op4"));

            this.rptaCorrecta = jsonObject.optString("palabra");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        tvPregunta.setText(miPregunta.getPregunta()+"");
        tvPalabraENum.setText(miPregunta.getPalabraEsp());
        tvOp1.setText(miPregunta.getOp1());
        tvOp2.setText(miPregunta.getOp2());
        tvOp3.setText(miPregunta.getOp3());
        tvOp4.setText(miPregunta.getOp4());

        if (miPregunta.getOp1Imagen()!=null){
            ibtnOp1.setImageBitmap(miPregunta.getOp1Imagen());
        }else{
            ibtnOp1.setImageResource(R.drawable.img_base);
        }
        if (miPregunta.getOp2Imagen()!=null){
            ibtnOp2.setImageBitmap(miPregunta.getOp2Imagen());
        }else{
            ibtnOp1.setImageResource(R.drawable.img_base);
        }
        if (miPregunta.getOp3Imagen()!=null){
            ibtnOp3.setImageBitmap(miPregunta.getOp3Imagen());
        }else{
            ibtnOp1.setImageResource(R.drawable.img_base);
        }
        if (miPregunta.getOp4Imagen()!=null){
            ibtnOp4.setImageBitmap(miPregunta.getOp4Imagen());
        }else{
            ibtnOp1.setImageResource(R.drawable.img_base);
        }
    }

    @Override
    public void onBackPressed()
    {
        Toast.makeText(this,"No puedes retroceder",Toast.LENGTH_SHORT).show();
    }
}