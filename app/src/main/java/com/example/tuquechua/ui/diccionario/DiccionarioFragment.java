package com.example.tuquechua.ui.diccionario;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tuquechua.Buscar;
import com.example.tuquechua.R;
import com.example.tuquechua.adapter.DiccionarioAdapter;
import com.example.tuquechua.adapter.FamiliaImagenAdapter;
import com.example.tuquechua.entidades.Diccionario;
import com.example.tuquechua.entidades.Pregunta;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DiccionarioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiccionarioFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener{
    RecyclerView recyclerViewDiccionarios;
    ArrayList<Diccionario> listaDiccionario;
    ProgressDialog progress;
    RequestQueue request;
    Button btnbuscar;
    JsonObjectRequest jsonObjectRequest;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DiccionarioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DiccionarioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DiccionarioFragment newInstance(String param1, String param2) {
        DiccionarioFragment fragment = new DiccionarioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  vista= inflater.inflate(R.layout.fragment_diccionario, container, false);
        listaDiccionario = new ArrayList<>();
        recyclerViewDiccionarios=(RecyclerView)vista.findViewById(R.id.idRecyclerDiccionario);
        recyclerViewDiccionarios.setLayoutManager(new LinearLayoutManager(this.getContext()));
        btnbuscar=vista.findViewById(R.id.btnBuscar);
        btnbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), Buscar.class);
                startActivity(i);
            }
        });
        recyclerViewDiccionarios.setHasFixedSize(true);
        request= Volley.newRequestQueue(getContext());
        llamarwebservice();
        return vista;
    }

    public void llamarwebservice() {
        progress = new ProgressDialog(getContext());
        progress.setMessage("Consultando");
        progress.show();
        String url="http://192.168.1.195:80/pregunta/ConsultarDiccionario.php?";

        String url = getString(R.string.urlIP)+"pregunta/ConsultarDiccionario.php?";

        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url,null,this, this);
        request.add(jsonObjectRequest);
        Toast.makeText(getContext(), "Listando", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progress.hide();
        Toast.makeText(getContext(), "Error de registro", Toast.LENGTH_SHORT).show();
        Log.i("Error", error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        Diccionario diccionario=null;

        JSONArray json=response.optJSONArray("diccc");

        try {

            for (int i=0;i<json.length();i++){
                diccionario=new Diccionario();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);


                diccionario.setDicPalabraQuechua(jsonObject.optString("dicPalabraQuechua"));
                diccionario.setDicSignificado(jsonObject.optString("dicSignificado"));
                listaDiccionario.add(diccionario);
            }
            progress.hide();
            DiccionarioAdapter adapter=new DiccionarioAdapter(listaDiccionario);
            recyclerViewDiccionarios.setAdapter(adapter);

        } catch ( JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Error servidor", Toast.LENGTH_SHORT).show();
            progress.hide();
        }
    }
}