package com.example.tuquechua.basico;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
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

public class Basico_H011_Numero extends AppCompatActivity implements  Response.Listener<JSONObject>,Response.ErrorListener {
    TextView tvPregunta, tvPalabraENum, tvOp1, tvOp2, tvOp3, tvOp4;
    ImageButton ibtnOp1, ibtnOp2, ibtnOp3, ibtnOp4;
    String respuesta;
    ProgressDialog progreso;
    ImageView campoImagen;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bbasico_h011_numero);
        ibtnOp1 =(ImageButton) findViewById(R.id.ibOp1);
        ibtnOp2 =(ImageButton) findViewById(R.id.ibOp2);
        ibtnOp3 =(ImageButton) findViewById(R.id.ibOp3);
        ibtnOp4 =(ImageButton) findViewById(R.id.ibOp4);
        tvOp1 =(TextView)  findViewById(R.id.tvOp1);
        tvOp2 =(TextView)  findViewById(R.id.tvOp2);
        tvOp3 =(TextView)  findViewById(R.id.tvOp3);
        tvOp4 =(TextView)  findViewById(R.id.tvOp4);
        tvPregunta =(TextView) findViewById(R.id.tvPregunta);
        tvPalabraENum =(TextView) findViewById(R.id.tvPalabraENumero);

        request= Volley.newRequestQueue(this);
        progreso=new ProgressDialog(this);
        progreso.setMessage("Consultando...");
        progreso.show();

        String url="http://192.168.1.7:80/pregunta/wsJSONConsultarPreguntaOpcionesImagen.php?id="+1;

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
        String rCorrecta = "Pusaa";
        System.out.println(rCorrecta);
        if(rpta.equals(rCorrecta))
            Toast.makeText(getApplicationContext(), rCorrecta+", Respuesta correcta", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(), "Respuesta incorrecta", Toast.LENGTH_SHORT).show();

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
            miPreguntaH011N.setPalabraEsp(jsonObject.optString("palabra")+"");
            miPreguntaH011N.setPregunta(jsonObject.optString("pregunta"));
            miPreguntaH011N.setDato(jsonObject.optString("op1Imagen"));
            miPreguntaH011N.setDato(jsonObject.optString("op2Imagen"));
            miPreguntaH011N.setDato(jsonObject.optString("op3Imagen"));
            miPreguntaH011N.setDato(jsonObject.optString("op4Imagen"));
            miPreguntaH011N.setPalabra(jsonObject.optString("op1Palabra"));
            miPreguntaH011N.setPalabra(jsonObject.optString("op2Palabra"));
            miPreguntaH011N.setPalabra(jsonObject.optString("op3Palabra"));
            miPreguntaH011N.setPalabra(jsonObject.optString("op4Palabra"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        tvPregunta.setText(miPreguntaH011N.getPregunta()+"");
        tvPalabraENum.setText(miPreguntaH011N.getPalabra()+"");
        ibtnOp1.setImageBitmap(miPreguntaH011N.getImagen());
        ibtnOp2.setImageBitmap(miPreguntaH011N.getImagen());
        ibtnOp3.setImageBitmap(miPreguntaH011N.getImagen());
        ibtnOp4.setImageBitmap(miPreguntaH011N.getImagen());
        tvOp1.setText(miPreguntaH011N.getPalabra()+"");
        tvOp2.setText(miPreguntaH011N.getPalabra()+"");
        tvOp3.setText(miPreguntaH011N.getPalabra()+"");
        tvOp4.setText(miPreguntaH011N.getPalabra()+"");
    }
}