package com.example.tuquechua.ui.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tuquechua.Niveles;
import com.example.tuquechua.R;
import com.example.tuquechua.basico.comida_basico.Basico_Ejercicio1_Comida;
import com.example.tuquechua.entidades.Pregunta;
import com.example.tuquechua.entidades.Seccion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeFragment extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener {
    ProgressDialog progreso;
    RequestQueue request;
    TextView seccion1, seccion2,seccion3,seccion4;
    JsonObjectRequest jsonObjectRequest;
    ImageView campoImagen1,campoImagen2,campoImagen3,campoImagen4;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //final TextView textView = root.findViewById(R.id.text_home);
       /* homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                //textView.setText(s);
            }
        });*/
        campoImagen1=root.findViewById(R.id.ivseccion1);
        campoImagen2=root.findViewById(R.id.ivseccion2);
        campoImagen3=root.findViewById(R.id.ivseccion3);
        campoImagen4=root.findViewById(R.id.ivseccion4);
        seccion1=root.findViewById(R.id.tvseccion1);
        seccion2=root.findViewById(R.id.tvseccion2);
        seccion3=root.findViewById(R.id.tvseccion3);
        seccion4=root.findViewById(R.id.tvseccion4);
        request= Volley.newRequestQueue(getContext());
        progreso=new ProgressDialog(getContext());
        progreso.setMessage("Consultando...");
        progreso.show();
        String url="http://192.168.1.195:85/pregunta/wsJSONConsultarSeccion.php?idseccion="+2;
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);


        campoImagen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplication(), Niveles.class);
                startActivity(intent);
            }
        });
        return root;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(getContext(), "No se pudo consultar "+error.toString(), Toast.LENGTH_SHORT).show();
        Log.i("Error", error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        progreso.hide();
        //Toast.makeText(this, "Mensaje: "+response,Toast.LENGTH_SHORT).show();
        Seccion miseccion=new Seccion();
        JSONArray json=response.optJSONArray("secciones");
        JSONObject jsonObject=null;
        try {
            jsonObject=json.getJSONObject(0);
            miseccion.setNomsecc1(jsonObject.optString("nomsecc1"));
            miseccion.setNomsecc2(jsonObject.optString("nomsecc2"));
            miseccion.setNomsecc3(jsonObject.optString("nomsecc3"));
            miseccion.setNomsecc4(jsonObject.optString("nomsecc4"));

            miseccion.setDato1(jsonObject.optString("imagensecc1"));
            miseccion.setDato2(jsonObject.optString("imagensecc2"));
            miseccion.setDato3(jsonObject.optString("imagensecc3"));
            miseccion.setDato4(jsonObject.optString("imagensecc4"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        seccion1.setText(miseccion.getNomsecc1()+"");
        seccion2.setText(miseccion.getNomsecc2()+"");
        seccion3.setText(miseccion.getNomsecc3()+"");
        seccion4.setText(miseccion.getNomsecc4()+"");


        if (miseccion.getImagensecc1()!=null){
            campoImagen1.setImageBitmap(miseccion.getImagensecc1());
        }else{
            campoImagen1.setImageResource(R.drawable.img_base);
        }
        if (miseccion.getImagensecc2()!=null){
            campoImagen2.setImageBitmap(miseccion.getImagensecc2());
        }else{
            campoImagen2.setImageResource(R.drawable.img_base);
        }
        if (miseccion.getImagensecc3()!=null){
            campoImagen3.setImageBitmap(miseccion.getImagensecc3());
        }else{
            campoImagen3.setImageResource(R.drawable.img_base);
        }
        if (miseccion.getImagensecc4()!=null){
            campoImagen4.setImageBitmap(miseccion.getImagensecc4());
        }else{
            campoImagen4.setImageResource(R.drawable.img_base);
        }
    }
}