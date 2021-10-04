package com.example.tuquechua.basico.comida_basico;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.tuquechua.R;

public class procesarBasicoComida extends AppCompatActivity {
    TextView tvrpta1comida,tvrpta1familia,tvop1comida,tvop1familia,tvopbutton1,tvopbutton2,tvop3Comida,tvop5Comida;
    int contator=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procesar_basico_comida);
        tvrpta1comida= findViewById(R.id.tvrpta1comida);
        tvrpta1familia=findViewById(R.id.tvrpta1familia);
        tvop1comida=findViewById(R.id.tvrpta1numero);
        tvop1familia=findViewById(R.id.tvrpta1saludo);
        tvop3Comida=findViewById(R.id.tvrpta3Comida);
        tvop5Comida=findViewById(R.id.tvrpta5Comida);
        tvopbutton1=findViewById(R.id.tvopmensaje1);
        tvopbutton2=findViewById(R.id.tvopmensaje2);
        String rpta_basico_comida= getIntent().getStringExtra("rpta1BasicoComida");
        tvrpta1comida.setText(rpta_basico_comida+"");
        String rpta_basico_familia= getIntent().getStringExtra("rpta1BasicoFamilia");
        tvrpta1familia.setText(rpta_basico_familia+"");
        //------------------------------------------------
        String rpta_op_comida= getIntent().getStringExtra("op_comida");
        String rpta_op_familia= getIntent().getStringExtra("op_familia");
        tvop1comida.setText(rpta_op_comida);
        tvop1familia.setText(rpta_op_familia);
        //---------------------------------------------
        String rpta_button_comida= getIntent().getStringExtra("buttoncomida");
        String rpta_button_familia= getIntent().getStringExtra("buttonfamilia");
        tvopbutton1.setText(rpta_button_comida);
        tvopbutton2.setText(rpta_button_familia);

        String rpta_basico_comida5= getIntent().getStringExtra("rpta5Comida");
        tvop5Comida.setText(rpta_basico_comida5+"");
    }
}