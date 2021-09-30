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

import java.io.File;

public class basico_h009_comida extends AppCompatActivity implements  Response.Listener<JSONObject>,Response.ErrorListener{
    Spinner spOpciones;
    Button btnSiguiente;
    TextView txtPregunta, txtPalabraQ;

    ProgressDialog progreso;
    ImageView campoImagen;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    String []respuestas={"Elija una opción","Oyuco","Camote","Papa","Legumbre"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basico_h009_comida);
        btnSiguiente = findViewById(R.id.btnSiguiente);
        spOpciones = findViewById(R.id.spOpc);
        txtPregunta = (TextView) findViewById(R.id.tvPregunta);
        txtPalabraQ = (TextView) findViewById(R.id.tvPalabraQComida);
        campoImagen =(ImageView) findViewById(R.id.imgViewH009Comida);
        request = Volley.newRequestQueue(this);
        progreso = new ProgressDialog(this);
        progreso.setMessage("Consultando...");
        progreso.show();

        String url="http://192.168.1.7:80/pregunta/wsJSONConsultarPreguntaImagen.php?id="+30;

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);

        ArrayAdapter<String> adapter1= new
                ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,respuestas);
        spOpciones.setAdapter(adapter1);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String r1=spOpciones.getSelectedItem().toString();
                procesar(r1);
            }
        });
    }

    public void procesar(String opcion)
    {
        if (opcion.equals("Papa") || opcion.equals("papa")){
            Toast.makeText(getApplicationContext(), opcion+", Respuesta correcta", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(), "Respuesta Incorrecta", Toast.LENGTH_SHORT).show();
        }

        Intent i = new Intent(this, Basico_H011_Numero.class);
        startActivity(i);
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
        //Toast.makeText(this, "Mensaje: "+response,Toast.LENGTH_SHORT).show();
        Pregunta miPreguntaH009C = new Pregunta();
        JSONArray json=response.optJSONArray("idpregunta");
        JSONObject jsonObject=null;
        try {
            jsonObject=json.getJSONObject(0);
            miPreguntaH009C.setPalabra(jsonObject.optString("palabra"));
            miPreguntaH009C.setPregunta(jsonObject.optString("pregunta"));
            miPreguntaH009C.setDato(jsonObject.optString("imagen"));
            miPreguntaH009C.setImgLocation(jsonObject.optString("imgLocation"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        txtPregunta.setText(miPreguntaH009C.getPregunta()+"");
        txtPalabraQ.setText(miPreguntaH009C.getPalabra());

        if (miPreguntaH009C.getImagen()!=null){
            campoImagen.setImageBitmap(miPreguntaH009C.getImagen());
        }else{
            campoImagen.setImageResource(R.drawable.img_base);
        }

        /*imgFile = new File("http://192.168.1.7:80/pregunta/recursos/imagenes/papa.png");
        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            //campoImagen.setImageResource(R.drawable.papa);
            campoImagen.setImageBitmap(myBitmap);
        }*/
    }
}