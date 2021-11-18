package com.example.tuquechua;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tuquechua.entidades.Pregunta;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class procesar_resultado extends AppCompatActivity {
    private ImageView imgvSeccion, imgvNivel;
    private TextView tvSeccion, tvNivel, tvPuntObt, tvPuntTotal, tvCorr, tvIncorr, tvResultado;
    private Button ok;
    private Integer punt, puntTotal;
    private Character sec, niv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procesar_resultado);
        ok = findViewById(R.id.btnOk);
        imgvSeccion = findViewById(R.id.imgvSeccion);
        imgvNivel = findViewById(R.id.imgvNivel);
        tvSeccion = findViewById(R.id.tvSeccion);
        tvNivel = findViewById(R.id.tvNivel);
        tvPuntObt = findViewById(R.id.tvPuntajeObtenido);
        tvPuntTotal = findViewById(R.id.tvPuntajeTotal);
        tvCorr = findViewById(R.id.tvCorrectas);
        tvIncorr = findViewById(R.id.tvIncorrectas);
        tvResultado = findViewById(R.id.tvResultado);

        punt = getIntent().getIntExtra("puntaje", 0);
        puntTotal = getIntent().getIntExtra("puntajeTotal", 0);
        sec = getIntent().getCharExtra("seccion", '0');
        niv = getIntent().getCharExtra("nivel", '0');

        llamarWebServiceSeccion();
        llamarWebServiceNivel();

        tvPuntObt.setText(String.valueOf(punt));
        tvPuntTotal.setText(String.valueOf(puntTotal));

        int numCorr = punt/5;
        int numIncorrect = (puntTotal - punt)/5;
        tvCorr.setText(String.valueOf(numCorr));
        tvIncorr.setText(String.valueOf(numIncorrect));

        int reference = puntTotal/5;
        if (punt <= reference*2) {
            tvResultado.setText("¡No te rindas!");
        }else if (punt <= reference*4){
            tvResultado.setText("¡Bien hecho!");
        }else{
            tvResultado.setText("¡Excelente trabajo!");
        }

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(), Secciones.class);
                startActivity(i);
            }
        });
    }

    private void llamarWebServiceSeccion(){
        RequestQueue requestSeccion;
        JsonObjectRequest jsonObjectRequestSeccion;
        ProgressDialog progresoSec;

        String urlSeccion = getString(R.string.urlIP)+"pregunta/wsJSONConsultarSeccion.php?idseccion="+1;

        requestSeccion = Volley.newRequestQueue(this);
        progresoSec = new ProgressDialog(this);
        progresoSec.setMessage("Consultando secciones...");
        progresoSec.show();

        jsonObjectRequestSeccion = new JsonObjectRequest(Request.Method.GET, urlSeccion, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progresoSec.hide();
                Pregunta miPregunta = new Pregunta();
                JSONArray json = response.optJSONArray("secciones");
                JSONObject jsonObject = null;
                try {
                    jsonObject=json.getJSONObject(0);
                    miPregunta.setDato1(jsonObject.optString("imagensecc1"));
                    miPregunta.setDato2(jsonObject.optString("imagensecc2"));
                    miPregunta.setDato3(jsonObject.optString("imagensecc3"));
                    miPregunta.setDato4(jsonObject.optString("imagensecc4"));
                    miPregunta.setOp1(jsonObject.optString("nomsecc1"));
                    miPregunta.setOp2(jsonObject.optString("nomsecc2"));
                    miPregunta.setOp3(jsonObject.optString("nomsecc3"));
                    miPregunta.setOp4(jsonObject.optString("nomsecc4"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                switch (sec){
                    case 'c':
                        tvSeccion.setText(miPregunta.getOp1()+"");
                        if (miPregunta.getOp1Imagen()!=null){
                            imgvSeccion.setImageBitmap(miPregunta.getOp1Imagen());
                        }else{
                            imgvSeccion.setImageResource(R.drawable.img_base);
                        }
                        break;
                    case 's':
                        tvSeccion.setText(miPregunta.getOp2()+"");
                        if (miPregunta.getOp2Imagen()!=null){
                            imgvNivel.setImageBitmap(miPregunta.getOp2Imagen());
                        }else{
                            imgvNivel.setImageResource(R.drawable.img_base);
                        }
                        break;
                    case 'n':
                        tvSeccion.setText(miPregunta.getOp3()+"");
                        if (miPregunta.getOp3Imagen()!=null){
                            imgvSeccion.setImageBitmap(miPregunta.getOp3Imagen());
                        }else{
                            imgvSeccion.setImageResource(R.drawable.img_base);
                        }
                        break;
                    case 'f':
                        tvSeccion.setText(miPregunta.getOp4()+"");
                        if (miPregunta.getOp4Imagen()!=null){
                            imgvSeccion.setImageBitmap(miPregunta.getOp4Imagen());
                        }else{
                            imgvSeccion.setImageResource(R.drawable.img_base);
                        }
                        break;
                    default: tvSeccion.setText("Error");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progresoSec.hide();
                Toast.makeText(getApplicationContext(), "No se pudo consultar seccion "+error.toString(), Toast.LENGTH_SHORT).show();
                Log.i("Error", error.toString());
            }
        });
        requestSeccion.add(jsonObjectRequestSeccion);
    }

    private void llamarWebServiceNivel(){
        RequestQueue requestNivel;
        JsonObjectRequest jsonObjectRequestNivel;
        ProgressDialog progresoNiv;

        requestNivel = Volley.newRequestQueue(this);
        progresoNiv = new ProgressDialog(this);
        progresoNiv.setMessage("Consultando niveles...");
        progresoNiv.show();

        String urlNivel = getString(R.string.urlIP)+"pregunta/wsJSONConsultarNivel.php?idnivel="+1;

        //jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        jsonObjectRequestNivel = new JsonObjectRequest(Request.Method.GET, urlNivel, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progresoNiv.hide();
                Pregunta miPregunta = new Pregunta();
                JSONArray json = response.optJSONArray("niveles");
                JSONObject jsonObject = null;
                try {
                    jsonObject=json.getJSONObject(0);
                    miPregunta.setDato1(jsonObject.optString("imagennivel1"));
                    miPregunta.setDato2(jsonObject.optString("imagennivel2"));
                    miPregunta.setDato3(jsonObject.optString("imagennivel3"));
                    miPregunta.setOp1(jsonObject.optString("nomnivel1"));
                    miPregunta.setOp2(jsonObject.optString("nomnivel2"));
                    miPregunta.setOp3(jsonObject.optString("nomnivel3"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                switch (niv){
                    case '1':
                        tvNivel.setText(miPregunta.getOp1()+"");
                        if (miPregunta.getOp1Imagen()!=null){
                            imgvNivel.setImageBitmap(miPregunta.getOp1Imagen());
                        }else{
                            imgvNivel.setImageResource(R.drawable.img_base);
                        }
                        break;
                    case '2':
                        tvNivel.setText(miPregunta.getOp2()+"");
                        if (miPregunta.getOp2Imagen()!=null){
                            imgvNivel.setImageBitmap(miPregunta.getOp2Imagen());
                        }else{
                            imgvNivel.setImageResource(R.drawable.img_base);
                        }
                        break;
                    case '3':
                        tvNivel.setText(miPregunta.getOp3()+"");
                        if (miPregunta.getOp3Imagen()!=null){
                            imgvNivel.setImageBitmap(miPregunta.getOp3Imagen());
                        }else{
                            imgvNivel.setImageResource(R.drawable.img_base);
                        }
                        break;
                    default: tvNivel.setText("Error");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progresoNiv.hide();
                Toast.makeText(getApplicationContext(), "No se pudo consultar niveles "+error.toString(), Toast.LENGTH_SHORT).show();
                Log.i("Error", error.toString());
            }
        });
        requestNivel.add(jsonObjectRequestNivel);
    }

    @Override
    public void onBackPressed()
    {
        Toast.makeText(this,"No puedes retroceder",Toast.LENGTH_SHORT).show();
    }
}