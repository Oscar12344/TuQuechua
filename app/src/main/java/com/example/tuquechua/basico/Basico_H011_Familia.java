package com.example.tuquechua.basico;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.example.tuquechua.entidades.Pregunta;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Basico_H011_Familia extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {
        TextView tvPregunta, tvPalabraENum, tvOp1, tvOp2, tvOp3, tvOp4;
        ImageButton ibtnOp1, ibtnOp2, ibtnOp3, ibtnOp4;
        String respuesta, rptaCorrecta;
        ProgressDialog progreso;
        RequestQueue request;
        JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basico_h011_familia);
        ibtnOp1 = findViewById(R.id.ibOp1);
        ibtnOp2 = findViewById(R.id.ibOp2);
        ibtnOp3 = findViewById(R.id.ibOp3);
        ibtnOp4 = findViewById(R.id.ibOp4);
        tvOp1 = findViewById(R.id.tvOp1);
        tvOp2 = findViewById(R.id.tvOp2);
        tvOp3 = findViewById(R.id.tvOp3);
        tvOp4 = findViewById(R.id.tvOp4);
        tvPregunta = findViewById(R.id.tvPregunta);
        tvPalabraENum = findViewById(R.id.tvPalabraEFamilia);

        request= Volley.newRequestQueue(this);
        progreso=new ProgressDialog(this);
        progreso.setMessage("Consultando...");
        progreso.show();

        String url="http://192.168.1.7:80/pregunta/wsJSONConsultarPreguntaImagen.php?id="+110;

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
                respuesta = findViewById(R.id.tvOp2).toString();
                procesar(respuesta);
            }
        });
        ibtnOp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                respuesta = findViewById(R.id.tvOp3).toString();
                procesar(respuesta);
            }
        });
        ibtnOp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                respuesta = findViewById(R.id.tvOp4).toString();
                procesar(respuesta);
            }
        });
    }

    public void procesar(String rpta)
    {
        if(rpta.equals(this.rptaCorrecta))
            Toast.makeText(getApplicationContext(), rptaCorrecta+", Respuesta correcta", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(), "Respuesta incorrecta, *"+rptaCorrecta, Toast.LENGTH_SHORT).show();

        /*Intent i = new Intent(this, Basico_H011_Saludo.class);
        startActivity(i);*/
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
        //Toast.makeText(this, "Mensaje: "+response,Toast.LENGTH_SHORT).show();
        Pregunta miPreguntaH011N = new Pregunta();
        JSONArray json = response.optJSONArray("idpregunta");
        JSONObject jsonObject = null;
        try {
            jsonObject=json.getJSONObject(0);
            miPreguntaH011N.setPalabraEsp(jsonObject.optString("palabraEspanol")+"");
            miPreguntaH011N.setPregunta(jsonObject.optString("pregunta"));
            miPreguntaH011N.setDato1(jsonObject.optString("op1Imagen"));
            miPreguntaH011N.setDato2(jsonObject.optString("op2Imagen"));
            miPreguntaH011N.setDato3(jsonObject.optString("op3Imagen"));
            miPreguntaH011N.setDato4(jsonObject.optString("op4Imagen"));
            miPreguntaH011N.setOp1(jsonObject.optString("op1"));
            miPreguntaH011N.setOp2(jsonObject.optString("op2"));
            miPreguntaH011N.setOp3(jsonObject.optString("op3"));
            miPreguntaH011N.setOp4(jsonObject.optString("op4"));

            this.rptaCorrecta = jsonObject.optString("palabra");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        tvPregunta.setText(miPreguntaH011N.getPregunta()+"");
        tvPalabraENum.setText(miPreguntaH011N.getPalabraEsp());
        tvOp1.setText(miPreguntaH011N.getOp1());
        tvOp2.setText(miPreguntaH011N.getOp2());
        tvOp3.setText(miPreguntaH011N.getOp3());
        tvOp4.setText(miPreguntaH011N.getOp4());

        if (miPreguntaH011N.getOp1Imagen()!=null){
            ibtnOp1.setImageBitmap(miPreguntaH011N.getOp1Imagen());
        }else{
            ibtnOp1.setImageResource(R.drawable.img_base);
        }
        if (miPreguntaH011N.getOp2Imagen()!=null){
            ibtnOp2.setImageBitmap(miPreguntaH011N.getOp2Imagen());
        }else{
            ibtnOp1.setImageResource(R.drawable.img_base);
        }
        if (miPreguntaH011N.getOp3Imagen()!=null){
            ibtnOp3.setImageBitmap(miPreguntaH011N.getOp3Imagen());
        }else{
            ibtnOp1.setImageResource(R.drawable.img_base);
        }
        if (miPreguntaH011N.getOp4Imagen()!=null){
            ibtnOp4.setImageBitmap(miPreguntaH011N.getOp4Imagen());
        }else{
            ibtnOp1.setImageResource(R.drawable.img_base);
        }
    }
}