package com.example.tuquechua.intermedio.comida_intermedio;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tuquechua.R;
import com.example.tuquechua.avanzado.Avanzado_Ejercicio3;
import com.example.tuquechua.entidades.Pregunta;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Intermedio_EjercicioR15_Comida extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    private TextView txtPre1, txtPre2, txtPre3, txtPre4, txtSuf1, txtSuf2, txtSuf3, txtSuf4;
    TextView txtFrase1, txtFrase2, txtFrase3, txtFrase4;
    private EditText txtPalabra1, txtPalabra2, txtPalabra3, txtPalabra4;
    private String palabraCorr1, palabraCorr2, palabraCorr3, palabraCorr4;
    private Button btnMostrarTrad, btnSiguiente;
    private Pregunta miPregunta;
    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermedio_ejercicio_r15_comida);
        txtPre1 = findViewById(R.id.txtPrefixOra1);
        txtPre2 = findViewById(R.id.txtPrefixOra2);
        txtPre3 = findViewById(R.id.txtPrefixOra3);
        txtPre4 = findViewById(R.id.txtPrefixOra4);
        txtSuf1 = findViewById(R.id.txtSufixOra1);
        txtSuf2 = findViewById(R.id.txtSufixOra2);
        txtSuf3 = findViewById(R.id.txtSufixOra3);
        txtSuf4 = findViewById(R.id.txtSufixOra4);
        txtPalabra1 = findViewById(R.id.palabra1);
        txtPalabra2 = findViewById(R.id.palabra2);
        txtPalabra3 = findViewById(R.id.palabra3);
        txtPalabra4 = findViewById(R.id.palabra4);
        btnSiguiente = findViewById(R.id.btnSiguiente);
        btnMostrarTrad = findViewById(R.id.btnMostrarTraduc);

        request= Volley.newRequestQueue(this);
        progreso=new ProgressDialog(this);
        progreso.setMessage("Consultando...");
        progreso.show();

        String url = getString(R.string.urlAvOrInc)+100;

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);

        btnMostrarTrad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonShowPopupWindowClick(v);
            }
        });
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                procesarRespuesta();
            }
        });
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
        miPregunta=new Pregunta();
        JSONArray json=response.optJSONArray("avanOraInc");
        JSONObject jsonObject=null;
        try {
            jsonObject=json.getJSONObject(0);
            miPregunta.setOp1(jsonObject.optString("aoiPrefijo1"));
            miPregunta.setOp2(jsonObject.optString("aoiPalabra1"));
            miPregunta.setOp3(jsonObject.optString("aoiSufijo1"));
            miPregunta.setOp4(jsonObject.optString("aoiPrefijo2"));
            miPregunta.setOp5(jsonObject.optString("aoiPalabra2"));
            miPregunta.setOp6(jsonObject.optString("aoiSufijo2"));
            miPregunta.setOp7(jsonObject.optString("aoiPrefijo3"));
            miPregunta.setOp8(jsonObject.optString("aoiPalabra3"));
            miPregunta.setOp9(jsonObject.optString("aoiSufijo3"));
            miPregunta.setOp10(jsonObject.optString("aoiPrefijo4"));
            miPregunta.setOp11(jsonObject.optString("aoiPalabra4"));
            miPregunta.setOp12(jsonObject.optString("aoiSufijo4"));
            miPregunta.setDato1(jsonObject.optString("aoiFraseEsp1"));
            miPregunta.setDato2(jsonObject.optString("aoiFraseEsp2"));
            miPregunta.setDato3(jsonObject.optString("aoiFraseEsp3"));
            miPregunta.setDato4(jsonObject.optString("aoiFraseEsp4"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (miPregunta.getOp1().equals("null"))
            txtPre1.setText("");
        else
            txtPre1.setText(miPregunta.getOp1());

        if (miPregunta.getOp3().equals("null"))
            txtSuf1.setText("");
        else
            txtSuf1.setText(miPregunta.getOp3());

        if (miPregunta.getOp4().equals("null"))
            txtPre2.setText("");
        else
            txtPre2.setText(miPregunta.getOp4());

        if (miPregunta.getOp6().equals("null"))
            txtSuf2.setText("");
        else
            txtSuf2.setText(miPregunta.getOp6());

        if (miPregunta.getOp7().equals("null"))
            txtPre3.setText("");
        else
            txtPre3.setText(miPregunta.getOp7());
        if (miPregunta.getOp9().equals("null"))
            txtSuf3.setText("");
        else
            txtSuf3.setText(miPregunta.getOp9());

        if (miPregunta.getOp10().equals("null"))
            txtPre4.setText("");
        else
            txtPre4.setText(miPregunta.getOp10());

        if (miPregunta.getOp12().equals("null"))
            txtSuf4.setText("");
        else
            txtSuf4.setText(miPregunta.getOp12());

        this.palabraCorr1 = miPregunta.getOp2();
        this.palabraCorr2 = miPregunta.getOp5();
        this.palabraCorr3 = miPregunta.getOp8();
        this.palabraCorr4 = miPregunta.getOp11();
    }

    private void procesarRespuesta(){
        String palabraUsu1, palabraUsu2, palabraUsu3, palabraUsu4;
        palabraUsu1 = txtPalabra1.getText().toString();
        palabraUsu2 = txtPalabra2.getText().toString();
        palabraUsu3 = txtPalabra3.getText().toString();
        palabraUsu4 = txtPalabra4.getText().toString();

        if (palabraUsu1.equals("") || palabraUsu2.equals("") || palabraUsu3.equals("") || palabraUsu4.equals("")){
            Toast.makeText(this,"Rellene su respuesta",Toast.LENGTH_SHORT).show();
        }else{
            if (palabraUsu1.equalsIgnoreCase(palabraCorr1) &&
                    palabraUsu2.equalsIgnoreCase(palabraCorr2) &&
                    palabraUsu3.equalsIgnoreCase(palabraCorr3) &&
                    palabraUsu4.equalsIgnoreCase(palabraCorr4)){
                //Intent i = new Intent(this, procesarIntermedioComida.class);
                //i.putExtra("puntaje", 5);
                Toast.makeText(this, "Respuestas correctas",Toast.LENGTH_SHORT).show();
                //startActivity(i);
                //finish();
            }else{
                Toast.makeText(this, "Respuestas incorrectas, debieron ser: "+palabraCorr1+", "+palabraCorr2+", "+palabraCorr3+", "+palabraCorr4
                        ,Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onButtonShowPopupWindowClick(View view) {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.intermedio_ejercicio_r15_popup, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        txtFrase1 = popupView.findViewById(R.id.txtFrase1Esp);
        txtFrase2 = popupView.findViewById(R.id.txtFrase2Esp);
        txtFrase3 = popupView.findViewById(R.id.txtFrase3Esp);
        txtFrase4 = popupView.findViewById(R.id.txtFrase4Esp);
        txtFrase1.setText("1. "+miPregunta.getDato1());
        txtFrase2.setText("2. "+miPregunta.getDato2());
        txtFrase3.setText("3. "+miPregunta.getDato3());
        txtFrase4.setText("4. "+miPregunta.getDato4());

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        Toast.makeText(this,"No puedes retroceder",Toast.LENGTH_SHORT).show();
    }
}