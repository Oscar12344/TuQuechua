package com.example.tuquechua;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
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

public class LoginUsuario extends AppCompatActivity implements  Response.ErrorListener, Response.Listener<JSONObject>  {
    EditText edtCorreo, edtContra;
    Button btnguardar;
    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_usuario);
        edtCorreo= findViewById(R.id.edtcorreo);
        edtContra= findViewById(R.id.edtcontra);
        btnguardar=findViewById(R.id.btnguardar);
        request= Volley.newRequestQueue(this);
        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llamarWebService();
            }
        });



    }

    public void llamarWebService() {

        progreso = new ProgressDialog(this);
        progreso.setMessage("Consultando");
        progreso.show();
        String url="http://192.168.1.195:85/pregunta/registroUser.php?id="+edtCorreo.getText().toString()+"&nombre="+edtContra.getText().toString();

        //idserie se debe optener desde el spinner serie
        url=url.replace(" ","%20");
        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url,null,this, this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(this, "Error de registro", Toast.LENGTH_SHORT).show();
        Log.i("Error", error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(this , "Ingreso exitoso", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, Seccion.class);

        progreso.hide();
        edtCorreo.setText("");
        edtContra.setText("");

    }
}