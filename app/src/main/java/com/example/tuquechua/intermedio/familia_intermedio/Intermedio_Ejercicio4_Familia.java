package com.example.tuquechua.intermedio.familia_intermedio;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tuquechua.R;
import com.example.tuquechua.entidades.Pregunta;
import com.example.tuquechua.intermedio.numero_intermedio.Intermedio_Ejercicio4_Numero;
import com.example.tuquechua.intermedio.saludo_intermedio.Intermedio_Ejercicio4_Saludo;
import com.example.tuquechua.procesar_resultado;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Intermedio_Ejercicio4_Familia extends AppCompatActivity implements  Response.Listener<JSONObject>,Response.ErrorListener{
    TextView pregunta, oracion;
    ImageView imagen, imgsonido;
    String  rptaCorrecta;
    Spinner spop;
    Button btnSiguiente;
    String op1, op2, op3, op4;
    private Character sec;
    private Integer punt;
    private TextView txtSecYNiv, txtPuntosEjer;
    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermedio__ejercicio4__familia);
        pregunta= findViewById(R.id.tvPregunta);
        oracion= findViewById(R.id.txtPalabra);
        imagen= findViewById(R.id.ivImagen);
        imgsonido= findViewById(R.id.ivSonido);
        spop= findViewById(R.id.spOpc);
        btnSiguiente = findViewById(R.id.btnSiguiente);
        txtSecYNiv = findViewById(R.id.txtSecNiv);
        txtPuntosEjer = findViewById(R.id.txtPuntos);

        txtSecYNiv.setText("FAMILIA | INTERMEDIO");
        txtPuntosEjer.setText("5 puntos");

        punt = getIntent().getIntExtra("puntaje", 0);
        sec = getIntent().getCharExtra("seccion", '0');

        request = Volley.newRequestQueue(this);
        progreso = new ProgressDialog(this);
        progreso.setMessage("Consultando...");
        progreso.show();
       /* btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String r1 = spop.getSelectedItem().toString();
                procesar(r1);
                Intent i = new Intent(getApplication(), Intermedio_Ejercicio4_Numero.class);
                startActivity(i);
            }
        });*/
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String r1 = spop.getSelectedItem().toString();
                procesar(r1);
            }
        });

        String url = getString(R.string.urlIP)+"pregunta/wsJSONConsultarPreguntaIntermedio.php?id="+6;

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }
    public void iniciar(View view) {

        MediaPlayer mp= MediaPlayer.create(this, R.raw.papa_viaje);
        mp.start();
    }

    public void procesar(String opcion) {
        Intent i = new Intent(this,  procesar_resultado.class);

        if (opcion.equals("Elija una opci??n")){
            Toast.makeText(getApplicationContext(), "Elija una opci??n", Toast.LENGTH_SHORT).show();
        }else{
            if (opcion.equalsIgnoreCase(rptaCorrecta)){
                Toast.makeText(getApplicationContext(), rptaCorrecta+", Respuesta correcta", Toast.LENGTH_SHORT).show();
                punt = punt + 5;
            }else {
                Toast.makeText(getApplicationContext(), "Respuesta Incorrecta, *"+rptaCorrecta, Toast.LENGTH_SHORT).show();
            }
            i.putExtra("puntaje", punt);
            i.putExtra("puntajeTotal", 35); //estatico por los ejercicios propuestos para este nivel
            i.putExtra("seccion", sec);
            i.putExtra("nivel", '2'); //estatico por el nivel de esta activity
            startActivity(i);
            finish();
        }
        /*if (opcion.equalsIgnoreCase(rptaCorrecta)){
            Toast.makeText(getApplicationContext(), rptaCorrecta+", Respuesta correcta", Toast.LENGTH_SHORT).show();
            i.putExtra("puntaje", punt+5);
            i.putExtra("puntajeTotal", 35); //estatico por los ejercicios propuestos para este nivel
            i.putExtra("seccion", sec);
            i.putExtra("nivel", '2'); //estatico por el nivel de esta activity
            startActivity(i);
            finish();
        }
        else if (opcion.equals("Elija una opci??n")){
            Toast.makeText(getApplicationContext(), "Elija una opci??n", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Respuesta Incorrecta, *"+rptaCorrecta, Toast.LENGTH_SHORT).show();
            i.putExtra("puntaje", punt);
            i.putExtra("puntajeTotal", 35); //estatico por los ejercicios propuestos para este nivel
            i.putExtra("seccion", sec);
            i.putExtra("nivel", '2'); //estatico por el nivel de esta activity
            startActivity(i);
            finish();
        }*/
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
        Pregunta miPregunta = new Pregunta();
        JSONArray json = response.optJSONArray("intermedios");
        JSONObject jsonObject = null;
        try {
            jsonObject=json.getJSONObject(0);
            miPregunta.setPalabra(jsonObject.optString("palabra"));
            miPregunta.setPregunta(jsonObject.optString("pregunta"));
            miPregunta.setDato1(jsonObject.optString("op1Imagen"));
            miPregunta.setOp1(jsonObject.optString("op1"));
            miPregunta.setOp2(jsonObject.optString("op2"));
            miPregunta.setOp3(jsonObject.optString("op3"));
            miPregunta.setOp4(jsonObject.optString("op4"));
            this.rptaCorrecta = jsonObject.optString("palabraEspanol");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        pregunta.setText(miPregunta.getPregunta()+"");
        oracion.setText(miPregunta.getPalabra());
        op1=miPregunta.getOp1().toString();
        op2=miPregunta.getOp2().toString();
        op3=miPregunta.getOp3().toString();
        op4=miPregunta.getOp4().toString();

        if (miPregunta.getOp1Imagen()!=null){
            imagen.setImageBitmap(miPregunta.getOp1Imagen());
        }else{
            imagen.setImageResource(R.drawable.img_base);
        }

        String []respuestas = {"Elija una opci??n",op1,op2,op3,op4};
        ArrayAdapter<String> adapter1= new
                ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,respuestas);
        spop.setAdapter(adapter1);
    }

    @Override
    public void onBackPressed()
    {
        Toast.makeText(this,"No puedes retroceder",Toast.LENGTH_SHORT).show();
    }
}