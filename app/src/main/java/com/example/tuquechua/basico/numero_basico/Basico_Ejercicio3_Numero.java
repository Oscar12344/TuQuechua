package com.example.tuquechua.basico.numero_basico;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
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

public class Basico_Ejercicio3_Numero extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener{
    Spinner spOpciones;
    Button btnSiguiente;
    TextView txtPregunta, txtPalabraQ;
    String op1, op2, op3, op4, rptaCorrecta;
    private TextView txtSecYNiv, txtPuntosEjer;
    ProgressDialog progreso;
    ImageView campoImagen;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basico__ejercicio3__numero);
        btnSiguiente = findViewById(R.id.btnSiguiente);
        spOpciones = findViewById(R.id.spOpc);
        txtPregunta = (TextView) findViewById(R.id.tvPregunta);
        txtPalabraQ = (TextView) findViewById(R.id.tvPalabraQ);
        campoImagen =(ImageView) findViewById(R.id.imgView);
        txtSecYNiv = findViewById(R.id.txtSecNiv);
        txtPuntosEjer = findViewById(R.id.txtPuntos);

        txtSecYNiv.setText("COMIDA | BÁSICO");
        txtPuntosEjer.setText("5 puntos");

        request = Volley.newRequestQueue(this);
        progreso = new ProgressDialog(this);
        progreso.setMessage("Consultando...");
        progreso.show();
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String r1=spOpciones.getSelectedItem().toString();
                procesar(r1);
            }
        });

        String url=getString(R.string.urlBasico)+400;

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
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
        JSONArray json=response.optJSONArray("idpregunta");
        JSONObject jsonObject=null;
        try {
            jsonObject=json.getJSONObject(0);
            miPregunta.setPalabra(jsonObject.optString("palabra"));
            miPregunta.setPregunta(jsonObject.optString("pregunta"));
            miPregunta.setOp1(jsonObject.optString("op1"));
            miPregunta.setOp2(jsonObject.optString("op2"));
            miPregunta.setOp3(jsonObject.optString("op3"));
            miPregunta.setOp4(jsonObject.optString("op4"));
            miPregunta.setDato(jsonObject.optString("imagen"));
            this.rptaCorrecta = jsonObject.optString("palabraEspanol");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        txtPregunta.setText(miPregunta.getPregunta()+"");
        txtPalabraQ.setText(miPregunta.getPalabra());

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
        spOpciones.setAdapter(adapter1);
    }

    public void procesar(String opcion)
    {
        Intent i = new Intent(this, Basico_Ejercicio4_Numero.class);
        int punt = getIntent().getIntExtra("puntaje",0);

        if (opcion.equals(rptaCorrecta)){
            Toast.makeText(getApplicationContext(), opcion+", Respuesta correcta", Toast.LENGTH_SHORT).show();
            i.putExtra("puntaje", punt+5);
            startActivity(i);
            finish();
        }
        else if (opcion.equals("Elija una opción")){
            Toast.makeText(getApplicationContext(), "Elija una opción", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Respuesta Incorrecta, *"+opcion, Toast.LENGTH_SHORT).show();
            i.putExtra("puntaje", punt);
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