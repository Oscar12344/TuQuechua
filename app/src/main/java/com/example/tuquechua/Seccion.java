package com.example.tuquechua;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Seccion extends AppCompatActivity implements View.OnClickListener, Response.Listener<JSONObject>, Response.ErrorListener {
    ProgressDialog progreso;
    RequestQueue request;
    TextView seccion1, seccion2,seccion3,seccion4;
    JsonObjectRequest jsonObjectRequest, jsonObjectRequest2, jsonObjectRequest3, jsonObjectRequest4;
    ImageView campoImagen1,campoImagen2,campoImagen3,campoImagen4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seccion);
        campoImagen1=findViewById(R.id.ivseccion1);
        campoImagen2=findViewById(R.id.ivseccion2);
        campoImagen3=findViewById(R.id.ivseccion3);
        campoImagen4=findViewById(R.id.ivseccion4);
        seccion1=findViewById(R.id.tvseccion1);
        seccion2=findViewById(R.id.tvseccion2);
        seccion3=findViewById(R.id.tvseccion3);
        seccion4=findViewById(R.id.tvseccion4);
        request= Volley.newRequestQueue(this);
        progreso=new ProgressDialog(this);
        progreso.setMessage("Consultando...");
        progreso.show();

        String url="http://192.168.1.7:80/pregunta/wsJSONConsultarSeccionIntento.php?idseccion="+1;
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);

        /*String url2="http://192.168.1.7:80/pregunta/wsJSONConsultarSeccionIntento.php?idseccion="+2;
        jsonObjectRequest2=new JsonObjectRequest(Request.Method.GET,url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progreso.hide();
                com.example.tuquechua.entidades.Seccion miseccion=new com.example.tuquechua.entidades.Seccion();
                JSONArray json=response.optJSONArray("secciones");
                JSONObject jsonObject=null;
                try {
                    jsonObject=json.getJSONObject(0);
                    miseccion.setNomsecc1(jsonObject.optString("nomsecc2"));
                    miseccion.setDato1(jsonObject.optString("imagensecc2"));
                } catch (JSONException e) {
                    e.printStackTrace(); }

                seccion1.setText(miseccion.getNomsecc1()+"");

                if (miseccion.getImagensecc1()!=null){
                    campoImagen1.setImageBitmap(miseccion.getImagensecc1());
                }else{
                    campoImagen1.setImageResource(R.drawable.img_base); }

                campoImagen1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent im= new Intent (getApplication(), Niveles.class);
                        im.putExtra("secc_comida",miseccion.getNomsecc1().toString());
                        startActivity(im);
                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: Handle error
                progreso.hide();
                Toast.makeText(getApplicationContext(), "No se pudo consultar "+error.toString(), Toast.LENGTH_SHORT).show();
                Log.i("Error", error.toString());
            }
        });

        //accede a la cola de request
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

        String url3="http://192.168.1.7:80/pregunta/wsJSONConsultarSeccionIntento.php?idseccion="+3;
        jsonObjectRequest3=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest3);

        String url4="http://192.168.1.7:80/pregunta/wsJSONConsultarSeccionIntento.php?idseccion="+4;
        jsonObjectRequest4=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest4);*/
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onResponse(JSONObject response) {
        progreso.hide();
        //Toast.makeText(this, "Mensaje: "+response,Toast.LENGTH_SHORT).show();
        com.example.tuquechua.entidades.Seccion miseccion=new com.example.tuquechua.entidades.Seccion();
        JSONArray json=response.optJSONArray("secciones");
        JSONObject jsonObject=null;
        try {
            jsonObject=json.getJSONObject(0);
            miseccion.setNomsecc1(jsonObject.optString("nomsecc1"));
            /*miseccion.setNomsecc2(jsonObject.optString("nomsecc2"));
            miseccion.setNomsecc3(jsonObject.optString("nomsecc3"));
            miseccion.setNomsecc4(jsonObject.optString("nomsecc4"));*/

            miseccion.setDato1(jsonObject.optString("imagensecc1"));
            /*miseccion.setDato2(jsonObject.optString("imagensecc2"));
            miseccion.setDato3(jsonObject.optString("imagensecc3"));
            miseccion.setDato4(jsonObject.optString("imagensecc4"));*/
        } catch (JSONException e) {
            e.printStackTrace();
        }

        seccion1.setText(miseccion.getNomsecc1()+"");
        /*seccion2.setText(miseccion.getNomsecc2()+"");
        seccion3.setText(miseccion.getNomsecc3()+"");
        seccion4.setText(miseccion.getNomsecc4()+"");*/


        if (miseccion.getImagensecc1()!=null){
            campoImagen1.setImageBitmap(miseccion.getImagensecc1());
        }else{
            campoImagen1.setImageResource(R.drawable.img_base);
        }
        /*if (miseccion.getImagensecc2()!=null){
            campoImagen2.setImageBitmap(miseccion.getImagensecc2());
        }else{
            campoImagen2.setImageResource(R.drawable.img_base);
        }
        if (miseccion.getImagensecc3()!=null){
            campoImagen3.setImageBitmap(miseccion.getImagensecc3());
        }else{
            campoImagen3.setImageResource(R.drawable.img_base);
        }
        if (miseccion.getImagensecc4()!=null){
            campoImagen4.setImageBitmap(miseccion.getImagensecc4());
        }else{
            campoImagen4.setImageResource(R.drawable.img_base);
        }*/
        campoImagen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent im= new Intent (getApplication(), Niveles.class);
                im.putExtra("secc_comida",miseccion.getNomsecc1().toString());
                startActivity(im);
            }
        });
      /*campoImagen2.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent im= new Intent (getApplication(), Niveles.class);
              im.putExtra("secc_familia",miseccion.getNomsecc2().toString());
              startActivity(im);
          }
      });
      campoImagen3.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent im= new Intent (getApplication(), Niveles.class);
              im.putExtra("secc_numero",miseccion.getNomsecc3().toString());
              startActivity(im);
          }
      });
      campoImagen4.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent im= new Intent (getApplication(), Niveles.class);
              im.putExtra("secc_saludo",miseccion.getNomsecc4().toString());
              startActivity(im);
          }
      });*/
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(this, "No se pudo consultar "+error.toString(), Toast.LENGTH_SHORT).show();
        Log.i("Error", error.toString());
    }
}