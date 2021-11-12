package com.example.tuquechua.avanzado;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class Avanzado_Ejercicio3 extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject> {
    private TextView txtOra1, txtOra2, txtOra3, txtOra4;
    private Button btnSiguiente;
    private RadioGroup rdg1, rdg2, rdg3, rdg4;
    private RadioButton rbtn1V, rbtn1F;
    private Boolean respCorr1, respCorr2, respCorr3, respCorr4;
    private Integer respUsu1, respUsu2, respUsu3, respUsu4, rpCorr1, rpCorr2, rpCorr3, rpCorr4;
    private Character vf1, vf2, vf3, vf4;
    private ProgressDialog progreso;
    private RequestQueue request;
    private JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avanzado_ejercicio3);
        txtOra1 = findViewById(R.id.txtPreguntaVF1);
        txtOra2 = findViewById(R.id.txtPreguntaVF2);
        txtOra3 = findViewById(R.id.txtPreguntaVF3);
        txtOra4 = findViewById(R.id.txtPreguntaVF4);
        rdg1 = findViewById(R.id.rdg1);
        rdg2 = findViewById(R.id.rdg2);
        rdg3 = findViewById(R.id.rdg3);
        rdg4 = findViewById(R.id.rdg4);
        rbtn1V = findViewById(R.id.rbtnPre1V);
        rbtn1F = findViewById(R.id.rbtnPre1F);
        btnSiguiente = findViewById(R.id.btnSiguiente);

        request= Volley.newRequestQueue(this);
        progreso=new ProgressDialog(this);
        progreso.setMessage("Consultando...");
        progreso.show();

        String url = getString(R.string.urlIntermedioVF)+100;

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                procesarRespuesta();
            }
        });
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(), "No se pudo consultar "+error.toString(), Toast.LENGTH_SHORT).show();
        Log.i("Error", error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        progreso.hide();
        Pregunta miPregunta=new Pregunta();
        JSONArray json=response.optJSONArray("intVF");
        JSONObject jsonObject=null;
        try {
            jsonObject=json.getJSONObject(0);
            miPregunta.setOp1(jsonObject.optString("vfOra1"));
            miPregunta.setOp2(jsonObject.optString("vfOra2"));
            miPregunta.setOp3(jsonObject.optString("vfOra3"));
            miPregunta.setOp4(jsonObject.optString("vfOra4"));
            miPregunta.setResp1(jsonObject.optBoolean("vfResp1"));
            miPregunta.setResp2(jsonObject.optBoolean("vfResp2"));
            miPregunta.setResp3(jsonObject.optBoolean("vfResp3"));
            miPregunta.setResp4(jsonObject.optBoolean("vfResp4"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        txtOra1.setText(miPregunta.getOp1()+"");
        txtOra2.setText(miPregunta.getOp2());
        txtOra3.setText(miPregunta.getOp3());
        txtOra4.setText(miPregunta.getOp4());
        respCorr1 = miPregunta.getResp1();
        respCorr2 = miPregunta.getResp2();
        respCorr3 = miPregunta.getResp3();
        respCorr4 = miPregunta.getResp4();
        rpCorr1 = respCorr1 ? 1:0;
        rpCorr2 = respCorr2 ? 1:0;
        rpCorr3 = respCorr3 ? 1:0;
        rpCorr4 = respCorr4 ? 1:0;

        if (respCorr1 == true)
            vf1 = 'V';
        else
            vf1 = 'F';

        if (respCorr2 == true)
            vf2 = 'V';
        else
            vf2 = 'F';

        if (respCorr3 == true)
            vf3 = 'V';
        else
            vf3 = 'F';

        if (respCorr4 == true)
            vf4 = 'V';
        else
            vf4 = 'F';
    }

    void procesarRespuesta(){
        respUsu1 = rdg1.getCheckedRadioButtonId();
        respUsu2 = rdg2.getCheckedRadioButtonId();
        respUsu3 = rdg3.getCheckedRadioButtonId();
        respUsu4 = rdg4.getCheckedRadioButtonId();

        Intent i = new Intent(this, Avanzado_Ejercicio3.class);

        if (respUsu1 == -1 || respUsu2 == -1 || respUsu3 == -1 || respUsu4 == -1)
            Toast.makeText(this, "Elija V o F. "+respUsu1+respUsu2+respUsu3+respUsu4,Toast.LENGTH_SHORT).show();
        else{
            Toast.makeText(this, "aea: "+respUsu1,Toast.LENGTH_SHORT).show();

            if (respUsu1 == rpCorr1 && respUsu2 == rpCorr2 && respUsu3 == rpCorr3 && respUsu4 == rpCorr4){
                i.putExtra("puntaje", 5);
                Toast.makeText(this, vf1+", "+vf2+", "+vf3+", "+vf4+", Respuestas correctas",Toast.LENGTH_SHORT).show();
                startActivity(i);
                finish();
            }else {
                i.putExtra("puntaje", 0);
                Toast.makeText(this,"Incorrecta, *"+vf1+", "+vf2+", "+vf3+", "+vf4,Toast.LENGTH_SHORT).show();
                startActivity(i);
                finish();
            }
        }
    }

    @Override
    public void onBackPressed()
    {
        Toast.makeText(this,"No puedes retroceder",Toast.LENGTH_SHORT).show();
    }
}