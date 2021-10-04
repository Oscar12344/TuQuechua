package com.example.tuquechua.basico.comida_basico;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.tuquechua.R;

public class procesarBasicoComida extends AppCompatActivity {
String r1_comida, r2_comida, r3_comida,r4_comida,r5_comida,r6_comida;
TextView tv1,tv2,tv3,tv4,tv5,tv6;
int puntaje1=0, puntaje2=0,puntaje3=0,puntaje4=0,puntaje5=0,puntaje6=0,pacumu;
int rptacorrecta1=0,rptacorrecta2=0,rptacorrecta3=0,rptacorrecta4=0,rptacorrecta5=0,rptacorrecta6=0, rptacorracumu;
int rptaincorrecta1=0,rptaincorrecta2=0,rptaincorrecta3=0,rptaincorrecta4=0,rptaincorrecta5=0,rptaincorrecta6=0, rptaincorracumu;


TextView tvpuntaje,tvcorrecta,tvincorrecta, tvresultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procesar_basico_comida);
        tvpuntaje=findViewById(R.id.tvpuntaje);
       tvresultado=findViewById(R.id.tvResultado);
        tvcorrecta=findViewById(R.id.tvcorrecta);
        tvincorrecta=findViewById(R.id.tvincorrecta);

        r1_comida=getIntent().getStringExtra("r_1comida");
        r2_comida=getIntent().getStringExtra("r_2comida");
        r3_comida=getIntent().getStringExtra("r_3comida");
        r4_comida=getIntent().getStringExtra("r_4comida");
        r5_comida=getIntent().getStringExtra("r_5comida");
        r6_comida=getIntent().getStringExtra("op6_comida");

      if(r1_comida!=null)
       {
           if(r1_comida.equals("sal")|| r1_comida.equals("Sal")|| r1_comida.equals("SAL"))
           {
               puntaje1=+5;
               rptacorrecta1=1;


           }else{
               puntaje1=0;
               rptaincorrecta1=1;
           }
       }else
       {
           puntaje1=0;
           rptaincorrecta1=1;
       }

        if(r2_comida!=null)
        {
            if(r2_comida.equals("kachi")|| r2_comida.equals("Kachi") || r2_comida.equals("KACHI") )
            {
                puntaje2=+5;
                rptacorrecta2=1;

            }else{
                puntaje2=0;
                rptaincorrecta2=1;
            }
        }else
        {
            puntaje2=0;
            rptaincorrecta2=1;
        }
        if(r3_comida!=null)
        {
            if(r3_comida.equals("Papa"))
            {
                puntaje3=+5;
                rptacorrecta3=1;


            }else{
                puntaje3=0;
                rptaincorrecta3=1;
            }
        }else
        {
            puntaje3=0;
            rptaincorrecta3=1;
        }
        if(r4_comida!=null)
        {
            if(r4_comida.equals("Acshu"))
            {
                puntaje4=+5;
                rptacorrecta4=1;

            }else{
                puntaje4=0;
                rptaincorrecta4=1;
            }
        }else
        {
            puntaje4=0;
            rptaincorrecta4=1;
        }
        if(r5_comida!=null)
        {
            if(r5_comida.equals("Jala")  )
            {
                puntaje5=+5;
                rptacorrecta5=1;

            }else{
                puntaje5=0;
                rptaincorrecta5=1;
            }
        }else
        {
            puntaje5=0;
            rptaincorrecta5=1;
        }
       if(r6_comida!=null)
       {
           if(r6_comida.equals("Aycha")  )
           {
               puntaje6=+5;
               rptacorrecta6=1;


           }else{
               puntaje6=0;
               rptaincorrecta6=1;

           }
       }else
       {
           puntaje6=0;
           rptaincorrecta6=1;

       }




        pacumu=puntaje1+puntaje2+puntaje3+puntaje4+puntaje5+puntaje6;
        rptacorracumu= rptacorrecta1+rptacorrecta2+rptacorrecta3+rptacorrecta4+rptacorrecta5+rptacorrecta6;
        rptaincorracumu=rptaincorrecta1+rptaincorrecta2+rptaincorrecta3+rptaincorrecta4+rptaincorrecta5+rptaincorrecta6;
        if(pacumu==15)
        {
            tvresultado.setText("¡Aun puedes mejorar!");
        }
        else
        {
            if(pacumu>= 5 & pacumu<=10 )
            {
                tvresultado.setText("¡Debes practicar mas!");
            }
            else
            {
                if(pacumu>= 20 & pacumu<=25 )
                {
                    tvresultado.setText("¡Mucho mejor!");
                }
                else
                {
                    if(pacumu==30 )
                    {
                        tvresultado.setText("¡Bien Hecho!");
                    }
                    else
                    {
                        tvresultado.setText("¡No te rindas!");
                    }
                }
            }

        }
        tvpuntaje.setText(pacumu+"");
        tvcorrecta.setText(rptacorracumu+"");
        tvincorrecta.setText(rptaincorracumu+"");






    }

}