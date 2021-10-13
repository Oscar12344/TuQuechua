package com.example.tuquechua.intermedio.saludo_intermedio;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tuquechua.R;
import com.example.tuquechua.entidades.Pregunta;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Intermedio_Ejercicio2_Saludo extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {
    TextView tvPregunta;
    ImageView ivimagen1,ivimagen2;
    RadioButton rbop1,rbop2,rbop3,rbop4;
    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermedio__ejercicio2__saludo);
        tvPregunta = findViewById(R.id.tvPregunta);
        ivimagen1 = findViewById(R.id.ivOp1);
        ivimagen2 = findViewById(R.id.ivOp2);
        rbop1 = findViewById(R.id.rbOp1);
        rbop2 = findViewById(R.id.rbOp2);
        rbop3 = findViewById(R.id.rbOp3);
        rbop4 = findViewById(R.id.rbOp4);
        request= Volley.newRequestQueue(this);
        progreso=new ProgressDialog(this);
        progreso.setMessage("Consultando...");
        progreso.show();

        String url="http://192.168.1.195:85/pregunta/wsJSONConsultarPreguntaIntermedio.php?id="+4;

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
        rbop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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


        } catch (JSONException e) {
            e.printStackTrace();
        }

        tvPregunta.setText(miPregunta.getPregunta()+"");
        rbop1.setText(miPregunta.getOp1());
        rbop2.setText(miPregunta.getOp2());
        rbop3.setText(miPregunta.getOp3());
        rbop4.setText(miPregunta.getOp4());

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
    }
}