package com.example.tuquechua;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class Basico_Ejercicio6_Comida extends AppCompatActivity {
    Button btnop4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basico__ejercicio6__comida);
        btnop4= findViewById(R.id.btnOp4_6comida);
        btnop4.setText("op5");
    }
}