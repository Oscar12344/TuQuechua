package com.example.tuquechua.basico.numero_basico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tuquechua.R;
import com.example.tuquechua.Secciones;

public class procesarBasicoNumero extends AppCompatActivity {
    TextView tvFraseResult, tvPuntResul, tvNumRespCorrect, tvNumRespIncorr;
    Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procesar_basico_numero);
        tvFraseResult=findViewById(R.id.tvResultado);
        tvPuntResul=findViewById(R.id.tvpuntaje);
        tvNumRespCorrect=findViewById(R.id.tvcorrecta);
        tvNumRespIncorr=findViewById(R.id.tvincorrecta);
        ok = findViewById(R.id.btnOk);

        int puntFinal = getIntent().getIntExtra("puntaje",0);
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
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(), Secciones.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        Toast.makeText(this,"No puedes retroceder",Toast.LENGTH_SHORT).show();
    }
}