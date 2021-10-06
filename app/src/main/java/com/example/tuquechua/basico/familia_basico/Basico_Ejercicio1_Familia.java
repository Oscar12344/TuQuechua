package com.example.tuquechua.basico.familia_basico;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
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

public class Basico_Ejercicio1_Familia extends AppCompatActivity implements View.OnClickListener,  Response.Listener<JSONObject>,Response.ErrorListener  {
    EditText etRespuesta;
    Button btnSiguiente;
    String respuestaUsuario, rptaCorrecta;
    TextView txtPalabra,txtPregunta;
    ProgressDialog progreso;
    ImageView campoImagen;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basico__ejercicio1__familia);
        etRespuesta = findViewById(R.id.etRespuesta);
        btnSiguiente =findViewById(R.id.btnSiguiente);
        txtPalabra= (TextView) findViewById(R.id.txtPalabra);
        txtPregunta= (TextView) findViewById(R.id.txtPregunta);
        campoImagen=(ImageView) findViewById(R.id.imagenId);
        request= Volley.newRequestQueue(this);
        progreso=new ProgressDialog(this);
        progreso.setMessage("Consultando...");
        progreso.show();

        String url="http://192.168.1.195:85/pregunta/wsJSONConsultarPreguntaImagen.php?id="+14;

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
        btnSiguiente.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        respuestaUsuario = etRespuesta.getText().toString();
        Intent i = new Intent(this,Basico_Ejercicio2_Familia.class);
        if(respuestaUsuario.equals(""))
        {
            //que sucede aqui
            Toast.makeText(this,"Escriba su respuesta",Toast.LENGTH_SHORT).show();
            etRespuesta.requestFocus();
            InputMethodManager imm= (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
            imm.showSoftInput(etRespuesta, InputMethodManager.SHOW_IMPLICIT);
        }else if (respuestaUsuario.equalsIgnoreCase(rptaCorrecta)){
            i.putExtra("puntaje", 5);
            Toast.makeText(this, rptaCorrecta+", Respuesta correcta",Toast.LENGTH_SHORT).show();
        }else{
            i.putExtra("puntaje", 0);
            Toast.makeText(this,"Respuesta incorrecta, *"+rptaCorrecta,Toast.LENGTH_SHORT).show();
        }
        startActivity(i);
        finish();
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
        Pregunta miPregunta=new Pregunta();
        JSONArray json=response.optJSONArray("idpregunta");
        JSONObject jsonObject=null;
        try {
            jsonObject=json.getJSONObject(0);
            miPregunta.setPalabra(jsonObject.optString("palabra"));
            miPregunta.setPregunta(jsonObject.optString("pregunta"));
            miPregunta.setDato(jsonObject.optString("imagen"));
            miPregunta.setPalabraEsp(jsonObject.optString("palabraEspanol"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        txtPalabra.setText(miPregunta.getPalabra()+"");
        txtPregunta.setText(miPregunta.getPregunta()+"");
        this.rptaCorrecta = miPregunta.getPalabraEsp();

        if (miPregunta.getImagen()!=null){
            campoImagen.setImageBitmap(miPregunta.getImagen());
        }else{
            campoImagen.setImageResource(R.drawable.img_base);
        }
    }

    @Override
    public void onBackPressed()
    {
        Toast.makeText(this,"No puedes retroceder",Toast.LENGTH_SHORT).show();
    }
}