package com.example.tuquechua.basico.comida_basico;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.tuquechua.R;

public class procesarBasicoComida extends AppCompatActivity {
String r1_comida, r2_comida, r3_comida,r4_comida,r5_comida,r6_comida;
TextView tv1,tv2,tv3,tv4,tv5,tv6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procesar_basico_comida);
        r1_comida=getIntent().getStringExtra("r_1comida");
        r2_comida=getIntent().getStringExtra("r_2comida");
        r3_comida=getIntent().getStringExtra("r_3comida");
        r4_comida=getIntent().getStringExtra("r_4comida");
        r5_comida=getIntent().getStringExtra("r_5comida");
        r6_comida=getIntent().getStringExtra("op6_comida");
        /*tv1=findViewById(R.id.textView5);
        tv2=findViewById(R.id.textView7);
        tv3=findViewById(R.id.textView8);
        tv4=findViewById(R.id.textView11);
        tv5=findViewById(R.id.textView12);
        tv6=findViewById(R.id.textView13);
        tv1.setText(r1_comida);
        tv2.setText(r2_comida);
        tv3.setText(r3_comida);
        tv4.setText(r4_comida);
        tv5.setText(r5_comida);
        tv6.setText(r6_comida);*/


    }
}