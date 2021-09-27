package com.example.tuquechua;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;


public class agregar_pregunta extends AppCompatActivity implements  Response.ErrorListener, Response.Listener<JSONObject> {
    ImageView ivimagen;
    EditText edtpalabra, edtpregunta,edtid;

    Button btnguardar;
    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_pregunta);
        ivimagen= findViewById(R.id.imgId);
        edtpalabra= findViewById(R.id.edtPalabraQuechua);
        edtpregunta= findViewById(R.id.edtPregPalQuechua);
        edtid= findViewById(R.id.edtIdpreg);
        btnguardar=findViewById(R.id.btnIngresar);
        request= Volley.newRequestQueue(this);
        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llamarWebService();
            }

        });
    }
    public void llamarWebService()
    {
        progreso = new ProgressDialog(this);
        progreso.setMessage("Consultant");
        progreso.show();
        String url="http://192.168.1.195:85/pregunta/registroanim.php?id="+edtid.getText().toString()+"&palabra="+edtpalabra.getText().toString()+
                "&pregunta="+edtpregunta.getText().toString();

        //idserie se debe optener desde el spinner serie
        url=url.replace(" ","%20");
        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url,null,this, this);
        request.add(jsonObjectRequest);

    }

    public void onClick(View view) {
        cargarImagen();

    }

    private void cargarImagen() {

        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.setType("image/");
        startActivityForResult(i.createChooser(i, "Seleccione  la aplicacion "), 10);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK)
        {
            Uri path= data.getData();
            ivimagen.setImageURI(path);

        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(getApplicationContext(), "Error de registro", Toast.LENGTH_SHORT).show();
        Log.i("Error", error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_SHORT).show();
        progreso.hide();
        edtid.setText("");
        edtpalabra.setText("");
        edtpregunta.setText("");

    }


}