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
import com.example.tuquechua.avanzado.Avanzado_Video;
import com.example.tuquechua.basico.comida_basico.Basico_Ejercicio1_Comida;
import com.example.tuquechua.basico.familia_basico.Basico_Ejercicio1_Familia;
import com.example.tuquechua.basico.numero_basico.Basico_Ejercicio1_Numero;
import com.example.tuquechua.basico.saludo_basico.Basico_Ejercicio1_Saludo;
import com.example.tuquechua.entidades.Nivel;
import com.example.tuquechua.intermedio.comida_intermedio.Intermedio_Ejercicio1_Comida;
import com.example.tuquechua.intermedio.familia_intermedio.Intermedio_Ejercicio1_Familia;
import com.example.tuquechua.intermedio.numero_intermedio.Intermedio_Ejercicio1_Numero;
import com.example.tuquechua.intermedio.saludo_intermedio.Intermedio_Ejercicio1_Saludo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Niveles extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {
    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    TextView nivel1, nivel2,nivel3;
    ImageView campoImagen1,campoImagen2,campoImagen3;
    String secccomida, seccfamilia,seccnumero,seccsaludo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niveles);
        nivel1=findViewById(R.id.tvnivel1);
        nivel2=findViewById(R.id.tvnivel2);
        nivel3=findViewById(R.id.tvnivel3);
        campoImagen1=findViewById(R.id.ivnivel1);
        campoImagen2=findViewById(R.id.ivnivel2);
        campoImagen3=findViewById(R.id.ivnivel3);
        request= Volley.newRequestQueue(this);
        progreso=new ProgressDialog(this);
        secccomida= getIntent().getStringExtra("secc_comida");
        seccfamilia= getIntent().getStringExtra("secc_familia");
        seccnumero= getIntent().getStringExtra("secc_numero");
        seccsaludo= getIntent().getStringExtra("secc_saludo");
        progreso.setMessage("Consultando...");
        progreso.show();

        String url=getString(R.string.urlIP)+"pregunta/wsJSONConsultarNivel.php?idnivel="+1;

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);

        if(secccomida!=null)
        {
            campoImagen1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i= new Intent(getApplication(), Basico_Ejercicio1_Comida.class);
                    i.putExtra("seccion", 'c');
                    startActivity(i);
                    finish();
                }
            });

            campoImagen2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i= new Intent(getApplication(), Intermedio_Ejercicio1_Comida.class);
                    i.putExtra("seccion", 'c');
                    startActivity(i);
                    finish();
                }
            });

            campoImagen3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i= new Intent(getApplication(), Avanzado_Video.class);
                    i.putExtra("seccion", 'c');
                    startActivity(i);
                    finish();
                }
            });
        }

        if(seccfamilia!=null)
        {
            campoImagen1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i= new Intent(getApplication(), Basico_Ejercicio1_Familia.class);
                    startActivity(i);
                    finish();
                }
            });
            campoImagen2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i= new Intent(getApplication(), Intermedio_Ejercicio1_Familia.class);
                    i.putExtra("seccion", 'f');
                    startActivity(i);
                    finish();
                }
            });

            campoImagen3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i= new Intent(getApplication(), Avanzado_Video.class);
                    i.putExtra("seccion", 'f');
                    startActivity(i);
                    finish();
                }
            });
        }
        if(seccnumero!=null)
        {
            campoImagen1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i= new Intent(getApplication(), Basico_Ejercicio1_Numero.class);
                    startActivity(i);
                    finish();
                }
            });

            campoImagen2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i= new Intent(getApplication(), Intermedio_Ejercicio1_Numero.class);
                    i.putExtra("seccion", 'n');
                    startActivity(i);
                    finish();
                }
            });

            campoImagen3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i= new Intent(getApplication(), Avanzado_Video.class);
                    i.putExtra("seccion", 'n');
                    startActivity(i);
                    finish();
                }
            });
        }
        if(seccsaludo!=null)
        {
            campoImagen1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i= new Intent(getApplication(), Basico_Ejercicio1_Saludo.class);
                    startActivity(i);
                    finish();
                }
            });

            campoImagen2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i= new Intent(getApplication(), Intermedio_Ejercicio1_Saludo.class);
                    i.putExtra("seccion", 's');
                    startActivity(i);
                    finish();
                }
            });

            campoImagen3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i= new Intent(getApplication(), Avanzado_Video.class);
                    i.putExtra("seccion", 's');
                    startActivity(i);
                    finish();
                }
            });
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(getApplicationContext(), "No se pudo consultar "+error.toString(), Toast.LENGTH_SHORT).show();
        Log.i("Error", error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        progreso.hide();
        Nivel minivel=new Nivel();
        JSONArray json=response.optJSONArray("niveles");
        JSONObject jsonObject=null;
        try {
            jsonObject=json.getJSONObject(0);
            minivel.setNomnivel1(jsonObject.optString("nomnivel1"));
            minivel.setNomnivel2(jsonObject.optString("nomnivel2"));
            minivel.setNomnivel3(jsonObject.optString("nomnivel3"));
            minivel.setDato1(jsonObject.optString("imagennivel1"));
            minivel.setDato2(jsonObject.optString("imagennivel2"));
            minivel.setDato3(jsonObject.optString("imagennivel3"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        nivel1.setText(minivel.getNomnivel1()+"");
        nivel2.setText(minivel.getNomnivel2()+"");
        nivel3.setText(minivel.getNomnivel3()+"");

        if (minivel.getImagennivel1()!=null){
            campoImagen1.setImageBitmap(minivel.getImagennivel1());
        }else{
            campoImagen1.setImageResource(R.drawable.img_base);
        }
        if (minivel.getImagennivel2()!=null){
            campoImagen2.setImageBitmap(minivel.getImagennivel2());
        }else{
            campoImagen2.setImageResource(R.drawable.img_base);
        }
        if (minivel.getImagennivel3()!=null){
            campoImagen3.setImageBitmap(minivel.getImagennivel3());
        }else{
            campoImagen3.setImageResource(R.drawable.img_base);
        }
    }
}