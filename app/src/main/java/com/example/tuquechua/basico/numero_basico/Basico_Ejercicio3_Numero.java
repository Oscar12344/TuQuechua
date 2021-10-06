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
import com.example.tuquechua.basico.saludo_basico.Basico_Ejercicio3_Saludo;
import com.example.tuquechua.entidades.Pregunta;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Basico_Ejercicio3_Numero extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener{
    Spinner spOpciones;
    Button btnSiguiente;
    TextView txtPregunta, txtPalabraQ;
    String op1, op2, op3, op4, rptaCorrecta;

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
        txtPalabraQ = (TextView) findViewById(R.id.tvPalabraQNumero);
        campoImagen =(ImageView) findViewById(R.id.imgViewH009Numero);
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

        String url="http://192.168.1.195:85/pregunta/wsJSONConsultarPreguntaImagen.php?id="+400;

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

        String []respuestas={"Elija una opción",op1,op2,op3,op4};
        ArrayAdapter<String> adapter1= new
                ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,respuestas);
        spOpciones.setAdapter(adapter1);
        /*imgFile = new File("http://192.168.1.7:80/pregunta/recursos/imagenes/papa.png");
        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            //campoImagen.setImageResource(R.drawable.papa);
            campoImagen.setImageBitmap(myBitmap);
        }*/
    }

    public void procesar(String opcion)
    {
        Intent i = new Intent(this, Basico_Ejercicio4_Numero.class);
        if (opcion.equals(rptaCorrecta)){
            Toast.makeText(getApplicationContext(), opcion+", Respuesta correcta", Toast.LENGTH_SHORT).show();
            startActivity(i);
        }
        else if (opcion.equals("Elija una opción")){
            Toast.makeText(getApplicationContext(), "Elija una opción", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Respuesta Incorrecta "+opcion, Toast.LENGTH_SHORT).show();
            startActivity(i);
        }
    }
}