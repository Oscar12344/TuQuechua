package com.example.tuquechua;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tuquechua.adapter.UsuariosAdapter;
import com.example.tuquechua.entidades.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class consultar_lista_usuario extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{
    RecyclerView recyclerUsuarios;
    ArrayList<Usuario> listaUsuarios;
    ProgressDialog progress;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_lista_usuario);
        listaUsuarios=new ArrayList<>();

        recyclerUsuarios= (RecyclerView) findViewById(R.id.idRecycler);
        recyclerUsuarios.setLayoutManager(new LinearLayoutManager(this));
        recyclerUsuarios.setHasFixedSize(true);
        request= Volley.newRequestQueue(this);
        cargarWebService();
    }

    private void cargarWebService() {
        progress=new ProgressDialog(this);
        progress.setMessage("Consultando...");
        progress.show();

        String url="http://192.168.1.195:85/ejemploBDRemota/wsJSONConsultarLista.php";

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);


    }


    @Override
    public void onResponse(JSONObject response) {
        Usuario usuario=null;

        JSONArray json=response.optJSONArray("usuario");

        try {

            for (int i=0;i<json.length();i++){
                usuario=new Usuario();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);

                usuario.setDocumento(jsonObject.optInt("documento"));//optInt por que el campo es entero
                usuario.setNombre(jsonObject.optString("nombre"));
                usuario.setProfesion(jsonObject.optString("profesion"));
                listaUsuarios.add(usuario);
            }
            progress.hide();
            UsuariosAdapter adapter=new UsuariosAdapter(listaUsuarios);
            recyclerUsuarios.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "No se ha podido establecer conexión con el servidor" +
                    " "+response, Toast.LENGTH_LONG).show();
            progress.hide();
        }
    }
    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "No se puede conectar "+error.toString(), Toast.LENGTH_LONG).show();
        System.out.println();
        Log.d("ERROR: ", error.toString());
        progress.hide();
    }


}