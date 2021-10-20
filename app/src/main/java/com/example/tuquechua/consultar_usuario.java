package com.example.tuquechua;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.example.tuquechua.entidades.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class consultar_usuario extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener  {
    EditText campoDocumento;
    TextView txtNombre,txtProfesion;
    Button btnConsultarUsuario;
    ProgressDialog progreso;
    ImageView campoImagen;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_usuario);
        campoDocumento= (EditText) findViewById(R.id.campoDocumento);
        txtNombre= (TextView) findViewById(R.id.txtNombre);
        txtProfesion= (TextView) findViewById(R.id.txtProfesion);
        btnConsultarUsuario= (Button) findViewById(R.id.btnConsultarUsuario);
        campoImagen=(ImageView) findViewById(R.id.imagenId);
        request= Volley.newRequestQueue(this);
        btnConsultarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarWebService();
            }
        });
    }

    private void cargarWebService() {
        progreso=new ProgressDialog(this);
        progreso.setMessage("Consultando...");
        progreso.show();



        String url=getString(R.string.urlIP)+"ejemploBDRemota/wsJSONConsultarUsuarioImagen.php?documento="
                +campoDocumento.getText().toString();

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);


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
        Toast.makeText(this, "Mensaje: "+response,Toast.LENGTH_SHORT).show();
        Usuario miUsuario=new Usuario();
        JSONArray json=response.optJSONArray("usuario");
        JSONObject jsonObject=null;
        try {
            jsonObject=json.getJSONObject(0);
            miUsuario.setNombre(jsonObject.optString("nombre"));
            miUsuario.setProfesion(jsonObject.optString("profesion"));
            miUsuario.setDato(jsonObject.optString("imagen"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        txtNombre.setText("Nombre :"+miUsuario.getNombre());
        txtProfesion.setText("Profesion :"+miUsuario.getProfesion());
        if (miUsuario.getImagen()!=null){
            campoImagen.setImageBitmap(miUsuario.getImagen());
        }else{
            campoImagen.setImageResource(R.drawable.img_base);
        }

    }
}