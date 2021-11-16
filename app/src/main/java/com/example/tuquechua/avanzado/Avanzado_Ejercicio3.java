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
import com.example.tuquechua.procesar_resultado;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Avanzado_Ejercicio3 extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject> {
    private TextView txtOra1, txtOra2, txtOra3, txtOra4;
    private Button btnSiguiente;
    private RadioGroup rdg1, rdg2, rdg3, rdg4;
    private RadioButton rbtn1V, rbtn1F, rbtn2V, rbtn2F, rbtn3V, rbtn3F, rbtn4V, rbtn4F;
    private Boolean respCorr1, respCorr2, respCorr3, respCorr4;
    private Integer respUsu1, respUsu2, respUsu3, respUsu4, rpCorr1, rpCorr2, rpCorr3, rpCorr4;
    private Character vf1, vf2, vf3, vf4, rptaUsu1, rptaUsu2, rptaUsu3, rptaUsu4;
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
        rbtn2V = findViewById(R.id.rbtnPre2V);
        rbtn2F = findViewById(R.id.rbtnPre2F);
        rbtn3V = findViewById(R.id.rbtnPre3V);
        rbtn3F = findViewById(R.id.rbtnPre3F);
        rbtn4V = findViewById(R.id.rbtnPre4V);
        rbtn4F = findViewById(R.id.rbtnPre4F);
        btnSiguiente = findViewById(R.id.btnSiguiente);

        request= Volley.newRequestQueue(this);
        progreso=new ProgressDialog(this);
        progreso.setMessage("Consultando...");
        progreso.show();

        String url = getString(R.string.urlAvanzadoVF)+100;

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
        rpCorr1 = respCorr1 ? 0 : 1;
        rpCorr2 = respCorr2 ? 0 : 1;
        rpCorr3 = respCorr3 ? 0 : 1;
        rpCorr4 = respCorr4 ? 0 : 1;

        if (rpCorr1.equals(1))
            vf1 = 'V';
        else
            vf1 = 'F';

        if (rpCorr2.equals(1))
            vf2 = 'V';
        else
            vf2 = 'F';

        if (rpCorr3.equals(1))
            vf3 = 'V';
        else
            vf3 = 'F';

        if (rpCorr4.equals(1))
            vf4 = 'V';
        else
            vf4 = 'F';
    }

    void procesarRespuesta(){
        respUsu1 = rdg1.getCheckedRadioButtonId();
        respUsu2 = rdg2.getCheckedRadioButtonId();
        respUsu3 = rdg3.getCheckedRadioButtonId();
        respUsu4 = rdg4.getCheckedRadioButtonId();

        int punt = getIntent().getIntExtra("puntaje",0);
        Intent i = new Intent(this, procesar_resultado.class);

        if (respUsu1 == -1 || respUsu2 == -1 || respUsu3 == -1 || respUsu4 == -1)
            Toast.makeText(this, "Elija V o F",Toast.LENGTH_SHORT).show();
        else{
            //if (rbtn1V.isSelected()) respUsu1=1;

            if (respUsu1 == rpCorr1)
                punt = punt + 5;

            if (respUsu2 == rpCorr2)
                punt = punt + 5;

            if (respUsu3 == rpCorr3)
                punt = punt + 5;

            if (respUsu4 == rpCorr4)
                punt = punt + 5;

            if (respUsu1 == rpCorr1 && respUsu2 == rpCorr2 && respUsu3 == rpCorr3 && respUsu4 == rpCorr4){
                Toast.makeText(this, vf1+", "+vf2+", "+vf3+", "+vf4+", Respuestas correctas",Toast.LENGTH_SHORT).show();
                i.putExtra("puntaje", punt);
                startActivity(i);
                finish();
            }else {
                Toast.makeText(this,"Incorrecta, *V, "+vf2+", "+vf3+", F",Toast.LENGTH_SHORT).show();
                i.putExtra("puntaje", punt);
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