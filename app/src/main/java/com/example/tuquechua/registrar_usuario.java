package com.example.tuquechua;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class registrar_usuario extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    EditText campoNombre,campoDocumento,campoProfesion;
    Button botonRegistro,btnFoto;

    ProgressDialog progreso;



    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);
        campoDocumento= (EditText) findViewById(R.id.campoDoc);
        campoNombre= (EditText) findViewById(R.id.campoNombre);
        campoProfesion= (EditText) findViewById(R.id.campoProfesion);
        botonRegistro= (Button) findViewById(R.id.btnRegistrar);
        request= Volley.newRequestQueue(this);

        botonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cargarWebService();
            }
        });


    }

    private void cargarWebService() {
        progreso= new ProgressDialog(this);
        progreso.setMessage("Cargando....");
        progreso.show();
        String url="http://192.168.1.195:85/ejemploBDRemota/wsJSONRegistro.php?documento="+campoDocumento.getText().toString()+
                "&nombre="+campoNombre.getText().toString()+
        "&profesion="+campoProfesion.getText().toString();
        url=url.replace(" ","%20");
        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url,null,this, this);
        request.add(jsonObjectRequest);

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(getApplicationContext(), "Error de registro"+error.toString(), Toast.LENGTH_SHORT).show();
        Log.i("Error", error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_SHORT).show();
        progreso.hide();
        campoDocumento.setText("");
        campoNombre.setText("");
        campoProfesion.setText("");
    }
}