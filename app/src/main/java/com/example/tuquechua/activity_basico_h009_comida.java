package com.example.tuquechua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class activity_basico_h009_comida extends AppCompatActivity {
    Spinner spOpciones;
    Button btnSiguiente;
    String []respuestas={"Elija una opción","Oyuco","Camote","Papa","Legumbre","Verdura"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basico_h009_comida);
        btnSiguiente = findViewById(R.id.btnSiguiente);
        spOpciones = findViewById(R.id.spOpc);

        ArrayAdapter<String> adapter1= new
                ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,respuestas);
        spOpciones.setAdapter(adapter1);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String r1=spOpciones.getSelectedItem().toString();
                procesar(r1);
            }
        });
    }

    public void procesar(String opcion)
    {
        if (opcion.equals("Papa")){
            Toast.makeText(getApplicationContext(), opcion+", Respuesta correcta", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(), "Respuesta Incorrecta", Toast.LENGTH_SHORT).show();
        }

        /*Intent i = new Intent(this, Basico_H009_Saludo.class);
        startActivity(i);*/
    }
}