package com.example.tuquechua.basico.saludo_basico;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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

public class Basico_Ejercicio2_Saludo extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener, View.OnClickListener {
    ImageButton ibIniciar;
    Button btnSiguiente;
    TextView txtPregunta;
    String respuestaUsuario, rptaCorrecta;
    EditText edtrespuesta;
    ProgressDialog progreso;
    ImageView campoImagen;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basico__ejercicio2__saludo);
        ibIniciar=findViewById(R.id.ibIniciar);
        txtPregunta= findViewById(R.id.txtPregunta);
        btnSiguiente=findViewById(R.id.btnSiguiente);
        campoImagen=findViewById(R.id.imagenId);
        edtrespuesta=findViewById(R.id.etRespuesta);
        request= Volley.newRequestQueue(this);
        progreso=new ProgressDialog(this);
        progreso.setMessage("Consultando...");
        progreso.show();

        String url=getString(R.string.urlBasico)+28;

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
        btnSiguiente.setOnClickListener(this);
        edtrespuesta.requestFocus();
    }
    public void iniciar(View view) {
        MediaPlayer mp= MediaPlayer.create(this, R.raw.allinwaala_buenosdias);
        mp.start();
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
    }

    @Override
    public void onClick(View v) {
        respuestaUsuario = edtrespuesta.getText().toString();
        Intent i = new Intent(this, Basico_Ejercicio3_Saludo.class);
        int punt = getIntent().getIntExtra("puntaje",0);

        if(respuestaUsuario.equals(""))
        {
            Toast.makeText(this,"Escriba su respuesta",Toast.LENGTH_SHORT).show();
            edtrespuesta.requestFocus();
            InputMethodManager imm= (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
            imm.showSoftInput(edtrespuesta, InputMethodManager.SHOW_IMPLICIT);
        }else if (respuestaUsuario.equalsIgnoreCase(rptaCorrecta)){
            i.putExtra("puntaje", punt+5);
            Toast.makeText(getApplicationContext(), rptaCorrecta+", Respuesta correcta", Toast.LENGTH_SHORT).show();
            startActivity(i);
            finish();
        }else{
            i.putExtra("puntaje", punt);
            Toast.makeText(getApplicationContext(), "Respuesta incorrecta, *"+rptaCorrecta, Toast.LENGTH_SHORT).show();
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