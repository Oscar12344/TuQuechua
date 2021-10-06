package com.example.tuquechua.basico.comida_basico;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
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
import com.example.tuquechua.basico.familia_basico.Basico_Ejercicio1_Familia;
import com.example.tuquechua.entidades.Pregunta;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Basico_Ejercicio4_Comida extends AppCompatActivity implements  Response.Listener<JSONObject>,Response.ErrorListener  {
    ImageButton ibIniciar;
    Spinner spRespuesta;
    Button btnSiguiente;
    TextView txtPregunta;
    String op1,op2,op3,op4,rptaCorrecta;
    ProgressDialog progreso;
    ImageView campoImagen;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basico__ejercicio4__comida);
        ibIniciar=findViewById(R.id.ibIniciar);
        spRespuesta=findViewById(R.id.spRespuesta);
        btnSiguiente= findViewById(R.id.btnSiguiente);
        txtPregunta= (TextView) findViewById(R.id.txtPregunta);
        campoImagen=(ImageView) findViewById(R.id.imagenId);
        request= Volley.newRequestQueue(this);
        progreso=new ProgressDialog(this);
        progreso.setMessage("Consultando...");
        progreso.show();

        String url="http://192.168.1.7:80/pregunta/wsJSONConsultarPreguntaImagen.php?id="+17;

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);

        /*spRespuesta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String r1=spRespuesta.getSelectedItem().toString();
                procesar(r1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });*/
    }
    public void iniciar(View view) {
        /*String path = "android.resource://" + getPackageName() + "/" + R.raw.sal;
        vvAudio.setVideoURI(Uri.parse(path));
        vvAudio.seekTo(0); vvAudio.start(); //con el video*/
        MediaPlayer mp= MediaPlayer.create(this, R.raw.sal_audio);
        mp.start();
    }

    public void procesar(String nom)
    {
        Intent i = new Intent(this, Basico_Ejercicio5_Comida.class);
        int punt = getIntent().getIntExtra("puntaje",0);

        if (nom.equalsIgnoreCase(rptaCorrecta)){
            Toast.makeText(getApplicationContext(), rptaCorrecta+", Respuesta correcta", Toast.LENGTH_SHORT).show();
            i.putExtra("puntaje", punt+5);
            startActivity(i);
            finish();
        } else if (nom.equals("Elija una opción")){
            Toast.makeText(getApplicationContext(), "Elija una opción", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Respuesta Incorrecta, *"+rptaCorrecta, Toast.LENGTH_SHORT).show();
            i.putExtra("puntaje", punt);
            startActivity(i);
            finish();
        }
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
        Pregunta miPregunta=new Pregunta();
        JSONArray json=response.optJSONArray("idpregunta");
        JSONObject jsonObject=null;
        try {
            jsonObject=json.getJSONObject(0);

            miPregunta.setPregunta(jsonObject.optString("pregunta"));
            miPregunta.setOp1(jsonObject.optString("op1"));
            miPregunta.setOp2(jsonObject.optString("op2"));
            miPregunta.setOp3(jsonObject.optString("op3"));
            miPregunta.setOp4(jsonObject.optString("op4"));
            miPregunta.setDato(jsonObject.optString("imagen"));
            miPregunta.setPalabra(jsonObject.optString("palabra"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        txtPregunta.setText(miPregunta.getPregunta()+"");
        this.rptaCorrecta = miPregunta.getPalabra();

        if (miPregunta.getImagen()!=null){
            campoImagen.setImageBitmap(miPregunta.getImagen());
        }else{
            campoImagen.setImageResource(R.drawable.img_base);
        }

        op1=miPregunta.getOp1().toString();
        op2=miPregunta.getOp2().toString();
        op3=miPregunta.getOp3().toString();
        op4=miPregunta.getOp4().toString();

        String []respuestas={"Elija una opción",op1,op2,op3,op4};
        ArrayAdapter<String> adapter1= new
                ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,respuestas);
        spRespuesta.setAdapter(adapter1);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String r1=spRespuesta.getSelectedItem().toString();
                procesar(r1);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        Toast.makeText(this,"No puedes retroceder",Toast.LENGTH_SHORT).show();
    }
}