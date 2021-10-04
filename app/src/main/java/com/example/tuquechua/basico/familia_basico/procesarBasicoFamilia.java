package com.example.tuquechua.basico.familia_basico;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.example.tuquechua.R;

public class procesarBasicoFamilia extends AppCompatActivity {
    TextView tvFraseResult, tvPuntResul, tvNumRespCorrect, tvNumRespIncorr;
    Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procesar_basico_familia);
        tvFraseResult = findViewById(R.id.tvFraseResultado);
        tvPuntResul = findViewById(R.id.tvPuntajeResultante);
        tvNumRespCorrect = findViewById(R.id.tvNumRespCorrectas);
        tvNumRespIncorr = findViewById(R.id.tvNumRespIncorrectas);
        ok = findViewById(R.id.ok);

        int puntajeFinal = getIntent().getIntExtra("puntaje",0);
        tvPuntResul.setText(String.valueOf(puntajeFinal));

        int numCorr = puntajeFinal/5;
        int numIncorr = 6 - numCorr;
        tvNumRespCorrect.setText(String.valueOf(numCorr));
        tvNumRespIncorr.setText(String.valueOf(numIncorr));

        if (puntajeFinal <= 10) {
            tvFraseResult.setText("¡Sigamos practicando!");
        }else if (puntajeFinal <= 20){
            tvFraseResult.setText("¡Bien hecho!");
        }else{
            tvFraseResult.setText("¡Excelente trabajo!");
        }
    }
}