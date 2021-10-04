package com.example.tuquechua.basico.comida_basico;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

public class Basico_Ejercicio3_Comida extends AppCompatActivity implements  Response.Listener<JSONObject>,Response.ErrorListener{
    Spinner spOpciones;
    Button btnSiguiente;
    TextView txtPregunta, txtPalabraQ;
    String op1, op2, op3, op4, rptaCorrecta;
    String respuesta3comida;
    String r2anterior, r1anterior;
    ProgressDialog progreso;
    ImageView campoImagen;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    /*prueba random id
    int id = getRandomNumber(30, 39);
    para probar que numero sale: (en "elija una opcion")
    String ids = String.valueOf(id);*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basico__ejercicio3__comida);
        btnSiguiente = findViewById(R.id.btnSiguiente);
        spOpciones = findViewById(R.id.spOpc);
        txtPregunta = findViewById(R.id.tvPregunta);
        txtPalabraQ = findViewById(R.id.tvPalabraQComida);
        campoImagen = findViewById(R.id.imgViewH009Comida);
        request = Volley.newRequestQueue(this);
        progreso = new ProgressDialog(this);
        progreso.setMessage("Consultando...");

        progreso.show();


        String url="http://192.168.1.195:85/pregunta/wsJSONConsultarPreguntaImagen.php?id="+300;

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
        //Toast.makeText(this, "Mensaje: "+response,Toast.LENGTH_SHORT).show();
        Pregunta miPreguntaH009C = new Pregunta();
        JSONArray json=response.optJSONArray("idpregunta");
        JSONObject jsonObject=null;
        try {
            jsonObject=json.getJSONObject(0);
            miPreguntaH009C.setPalabra(jsonObject.optString("palabra"));
            miPreguntaH009C.setPregunta(jsonObject.optString("pregunta"));
            miPreguntaH009C.setOp1(jsonObject.optString("op1"));
            miPreguntaH009C.setOp2(jsonObject.optString("op2"));
            miPreguntaH009C.setOp3(jsonObject.optString("op3"));
            miPreguntaH009C.setOp4(jsonObject.optString("op4"));
            miPreguntaH009C.setDato(jsonObject.optString("imagen"));
            this.rptaCorrecta = jsonObject.optString("palabraEspanol");
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

        op1=miPreguntaH009C.getOp1().toString();
        op2=miPreguntaH009C.getOp2().toString();
        op3=miPreguntaH009C.getOp3().toString();
        op4=miPreguntaH009C.getOp4().toString();

        String []respuestas = {"Elija una opción",op1,op2,op3,op4};
        ArrayAdapter<String> adapter1= new
                ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,respuestas);
        spOpciones.setAdapter(adapter1);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respuesta3comida=spOpciones.getSelectedItem().toString();
                if(!respuesta3comida.equals("Elija una opción"))
                {
                    r1anterior= getIntent().getStringExtra("rpta1comida_anterior1");
                    r2anterior= getIntent().getStringExtra("rpta2comida");
                    lanzarProcesarCalculo(respuesta3comida, r1anterior,r2anterior);
                }
                else
                {
                    //Toast.makeText(this,"Seleccione una opcion",Toast.LENGTH_SHORT).show();

                    Toast.makeText(getApplicationContext(), "Seleccione una opción",Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
    @Override
    public void onBackPressed()
    {
        Toast.makeText(this,"No puedes retroceder",Toast.LENGTH_SHORT).show();
    }

    public void lanzarProcesarCalculo(String respuesta3comida, String r1anterior, String r2anterior) {
        Intent i = new Intent(this, Basico_Ejercicio4_Comida.class);
        i.putExtra("rpta1_comida", r1anterior);
        i.putExtra("rpta2_comida", r2anterior);
        i.putExtra("op3_comida", respuesta3comida);
        startActivity(i);
    }

    public void procesar(String opcion)
    {
        Intent i = new Intent(this, Basico_Ejercicio4_Comida.class);
        if (opcion.equals(rptaCorrecta)){
            Toast.makeText(getApplicationContext(), opcion+", Respuesta correcta", Toast.LENGTH_SHORT).show();
            //i.putExtra("puntaje", 5);
            startActivity(i);
        }
        else if (opcion.equals("Elija una opción")){
            Toast.makeText(getApplicationContext(), "Elija una opción", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Respuesta Incorrecta", Toast.LENGTH_SHORT).show();
            //i.putExtra("puntaje", 0);
            startActivity(i);
        }
        //i.putExtra("puntaje", puntaje);
    }

    /*prueba random id
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }*/
}