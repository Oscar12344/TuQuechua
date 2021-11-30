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
    private RadioButton rbtn1Selected, rbtn2Selected, rbtn3Selected, rbtn4Selected;
    private Integer respUsu1, respUsu2, respUsu3, respUsu4, rpCorr1, rpCorr2, rpCorr3, rpCorr4;
    private Integer index1, index2, index3, index4;
    private Character vf1, vf2, vf3, vf4;
    private Integer urlSec;
    private Character seccion;
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
        btnSiguiente = findViewById(R.id.btnSiguiente);

        seccion = getIntent().getCharExtra("seccion", '0');
        switch (seccion){
            case 'c': urlSec=100;
                break;
            case 's': urlSec=200;
                break;
            case 'n': urlSec=300;
                break;
            case 'f': urlSec=400;
                break;
        }

        request= Volley.newRequestQueue(this);
        progreso=new ProgressDialog(this);
        progreso.setMessage("Consultando...");
        progreso.show();

        String url = getString(R.string.urlIP)+"pregunta/wsJSONConsultarPreguntaAvanzadoVF.php?id="+urlSec;

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
            miPregunta.setRpta1(jsonObject.optInt("vfResp1"));
            miPregunta.setRpta2(jsonObject.optInt("vfResp2"));
            miPregunta.setRpta3(jsonObject.optInt("vfResp3"));
            miPregunta.setRpta4(jsonObject.optInt("vfResp4"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        txtOra1.setText(miPregunta.getOp1()+"");
        txtOra2.setText(miPregunta.getOp2());
        txtOra3.setText(miPregunta.getOp3());
        txtOra4.setText(miPregunta.getOp4());
        rpCorr1 = miPregunta.getRpta1();
        rpCorr2 = miPregunta.getRpta2();
        rpCorr3 = miPregunta.getRpta3();
        rpCorr4 = miPregunta.getRpta4();

        if (rpCorr1.equals(0)){
            vf1 = 'F';
            rpCorr1 = 1;
        }else{
            vf1 = 'V';
            rpCorr1 = 0;
        }

        if (rpCorr2.equals(0)){
            vf2 = 'F';
            rpCorr2 = 1;
        }else{
            vf2 = 'V';
            rpCorr2 = 0;
        }

        if (rpCorr3.equals(0)){
            vf3 = 'F';
            rpCorr3 = 1;
        }else{
            vf3 = 'V';
            rpCorr3 = 0;
        }

        if (rpCorr4.equals(0)){
            vf4 = 'F';
            rpCorr4 = 1;
        }else{
            vf4 = 'V';
            rpCorr4 = 0;
        }
    }

    void procesarRespuesta(){
        respUsu1 = rdg1.getCheckedRadioButtonId();
        rbtn1Selected = rdg1.findViewById(respUsu1);
        index1 = rdg1.indexOfChild(rbtn1Selected);

        respUsu2 = rdg2.getCheckedRadioButtonId();
        rbtn2Selected = rdg2.findViewById(respUsu2);
        index2 = rdg2.indexOfChild(rbtn2Selected);

        respUsu3 = rdg3.getCheckedRadioButtonId();
        rbtn3Selected = rdg3.findViewById(respUsu3);
        index3 = rdg3.indexOfChild(rbtn3Selected);

        respUsu4 = rdg4.getCheckedRadioButtonId();
        rbtn4Selected = rdg4.findViewById(respUsu4);
        index4 = rdg4.indexOfChild(rbtn4Selected);

        if (respUsu1 == -1 || respUsu2 == -1 || respUsu3 == -1 || respUsu4 == -1)
            Toast.makeText(this, "Elija V o F",Toast.LENGTH_SHORT).show();
        else{
            int punt = getIntent().getIntExtra("puntaje",0);
            Intent i = new Intent(this, procesar_resultado.class);

            if (respUsu1 != rpCorr1)
                punt = punt + 5;

            if (respUsu2 != rpCorr2)
                punt = punt + 5;

            if (respUsu3 != rpCorr3)
                punt = punt + 5;

            if (respUsu4 != rpCorr4)
                punt = punt + 5;

            if (respUsu1 != rpCorr1 && respUsu2 != rpCorr2 && respUsu3 != rpCorr3 && respUsu4 != rpCorr4){
                Toast.makeText(this, vf1+", "+vf2+", "+vf3+", "+vf4+", Respuestas correctas",Toast.LENGTH_SHORT).show();
                i.putExtra("puntaje", punt);
            }else {
                Toast.makeText(this,"Una o más incorrectas, *"+vf1+", "+vf2+", "+vf3+", "+vf4,Toast.LENGTH_SHORT).show();
                i.putExtra("puntaje", punt);
            }
            i.putExtra("puntajeTotal", 40); //estático por el num de ejercicios del nivel
            i.putExtra("nivel", '3'); //definido por el nivel de esta activity
            i.putExtra("seccion", seccion);
            startActivity(i);
            finish();
        }
    }

    @Override
    public void onBackPressed()
    {
        Toast.makeText(this,"No puedes retroceder",Toast.LENGTH_SHORT).show();
    }
}