package com.example.tuquechua.avanzado;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tuquechua.R;
import com.example.tuquechua.RankingNumeroBasico;
import com.example.tuquechua.RankingSaludoAvanzado;
import com.example.tuquechua.Secciones;
import com.example.tuquechua.basico.comida_basico.procesarBasicoComida;
import com.example.tuquechua.basico.numero_basico.procesarBasicoNumero;
import com.example.tuquechua.intermedio.comida_intermedio.procesarIntermedioComida;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONObject;

public class procesarAvanzadoSaludo extends AppCompatActivity implements  Response.ErrorListener, Response.Listener<JSONObject> {
    TextView tvFraseResult, tvPuntResul, tvNumRespCorrect, tvNumRespIncorr;
    Button ok;
    Button irRanking;
    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procesar_avanzado_saludo);
        tvFraseResult=findViewById(R.id.tvResultado);
        tvPuntResul=findViewById(R.id.tvpuntaje);
        tvNumRespCorrect=findViewById(R.id.tvcorrecta);
        tvNumRespIncorr=findViewById(R.id.tvincorrecta);
        ok = findViewById(R.id.btnOk);
        irRanking= findViewById(R.id.irRanking);

        int puntFinal = getIntent().getIntExtra("puntaje",0);
        request= Volley.newRequestQueue(this);
        llamarWebService();
        tvPuntResul.setText(String.valueOf(puntFinal));

        int numCorr = puntFinal/5;
        int numIncorrect = 6 - numCorr;
        tvNumRespCorrect.setText(String.valueOf(numCorr));
        tvNumRespIncorr.setText(String.valueOf(numIncorrect));

        if (puntFinal <= 10) {
            tvFraseResult.setText("¡No te rindas!");
        }else if (puntFinal <= 20){
            tvFraseResult.setText("¡Bien hecho!");
        }else{
            tvFraseResult.setText("¡Excelente trabajo!");
        }
        irRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(), RankingSaludoAvanzado.class);

                startActivity(i);


            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(), procesarIntermedioComida.class);
                startActivity(i);
            }
        });
    }

    private void llamarWebService() {
        FirebaseUser users =  FirebaseAuth.getInstance().getCurrentUser();
        if(users!=null){
            String nombres=users.getDisplayName();
            int puntFinal = getIntent().getIntExtra("puntaje",0);

            progreso = new ProgressDialog(this);
            progreso.setMessage("Consultando");
            progreso.show();
            String url=getString(R.string.urlIP)+"pregunta/registroRankingSaludoAvanzado.php?nombre="+nombres+"&puntaje="+puntFinal;

            //idserie se debe optener desde el spinner serie
            url=url.replace(" ","%20");
            jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url,null,this, this);
            request.add(jsonObjectRequest);


        }else{
            getApplicationContext();
        }
    }

    @Override
    public void onBackPressed()
    {
        Toast.makeText(this,"No puedes retroceder",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(this, "Error de registro", Toast.LENGTH_SHORT).show();
        Log.i("Error", error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
        progreso.hide();
    }
}