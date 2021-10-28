package com.example.tuquechua.avanzado;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tuquechua.R;
import com.example.tuquechua.basico.comida_basico.Basico_Ejercicio2_Comida;
import com.example.tuquechua.entidades.Pregunta;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Avanzado_Ejercicio2_Comida extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject> {
    private TextView txtPregunta, txtOraCorrEsp, txtOraUsu;
    private ImageView imgOr;
    private String oraCorrQue;
    private Button btnPal1, btnPal2, btnPal3, btnPal4, btnPal5, btnSiguiente;
    private ProgressDialog progreso;
    private RequestQueue request;
    private JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avanzado_ejercicio2_comida);
        txtOraCorrEsp = findViewById(R.id.txtOracionOrdenada);
        txtOraUsu = findViewById(R.id.txtOracionUsuario);
        btnPal1 = findViewById(R.id.btnPalabra1);
        btnPal2 = findViewById(R.id.btnPalabra2);
        btnPal3 = findViewById(R.id.btnPalabra3);
        btnPal4 = findViewById(R.id.btnPalabra4);
        btnPal5 = findViewById(R.id.btnPalabra5);
        btnSiguiente = findViewById(R.id.btnSiguiente);
        txtPregunta = findViewById(R.id.txtPregunta);
        imgOr = findViewById(R.id.imgOra);

        request= Volley.newRequestQueue(this);
        progreso=new ProgressDialog(this);
        progreso.setMessage("Consultando...");
        progreso.show();

        String url = getString(R.string.urlAvReord)+100;

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);

        btnPal1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item(btnPal1.getText().toString());
                ClipData dragData = new ClipData(
                        btnPal1.getText().toString(),
                        new String[] { ClipDescription.MIMETYPE_TEXT_PLAIN },
                        item);

                View.DragShadowBuilder myShadow = new MyDragShadowBuilder(btnPal1);
                v.startDrag(dragData,  // the data to be dragged
                        myShadow,  // the drag shadow builder
                        null,      // no need to use local data
                        0          // flags (not currently used, set to 0)
                );

                return false;
            }
        });

        btnPal2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item(btnPal2.getText().toString());
                ClipData dragData = new ClipData(
                        btnPal2.getText().toString(),
                        new String[] { ClipDescription.MIMETYPE_TEXT_PLAIN },
                        item);

                View.DragShadowBuilder myShadow = new MyDragShadowBuilder(btnPal2);
                v.startDrag(dragData,  // the data to be dragged
                        myShadow,  // the drag shadow builder
                        null,      // no need to use local data
                        0          // flags (not currently used, set to 0)
                );

                return false;
            }
        });

        btnPal3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item(btnPal3.getText().toString());
                ClipData dragData = new ClipData(
                        btnPal3.getText().toString(),
                        new String[] { ClipDescription.MIMETYPE_TEXT_PLAIN },
                        item);

                View.DragShadowBuilder myShadow = new MyDragShadowBuilder(btnPal3);
                v.startDrag(dragData,  // the data to be dragged
                        myShadow,  // the drag shadow builder
                        null,      // no need to use local data
                        0          // flags (not currently used, set to 0)
                );

                return false;
            }
        });

        btnPal4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item(btnPal4.getText().toString());
                ClipData dragData = new ClipData(
                        btnPal4.getText().toString(),
                        new String[] { ClipDescription.MIMETYPE_TEXT_PLAIN },
                        item);

                View.DragShadowBuilder myShadow = new MyDragShadowBuilder(btnPal4);
                v.startDrag(dragData,  // the data to be dragged
                        myShadow,  // the drag shadow builder
                        null,      // no need to use local data
                        0          // flags (not currently used, set to 0)
                );

                return false;
            }
        });

        btnPal5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item(btnPal5.getText().toString());
                ClipData dragData = new ClipData(
                        btnPal5.getText().toString(),
                        new String[] { ClipDescription.MIMETYPE_TEXT_PLAIN },
                        item);

                View.DragShadowBuilder myShadow = new MyDragShadowBuilder(btnPal5);
                v.startDrag(dragData,  // the data to be dragged
                        myShadow,  // the drag shadow builder
                        null,      // no need to use local data
                        0          // flags (not currently used, set to 0)
                );

                return false;
            }
        });

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rpta = txtOraUsu.getText().toString();
                //Intent i = new Intent(this, Avanzado_Ejercicio3.class);
                if(rpta.equals(""))
                {
                    //Toast.makeText(this,"Rellene su respuesta",Toast.LENGTH_SHORT).show();
                    txtOraUsu.requestFocus();
                }else if (rpta.equalsIgnoreCase(oraCorrQue)){
                    //i.putExtra("puntaje", 5);
                    //Toast.makeText(this, oraCorrQue+", Respuesta correcta",Toast.LENGTH_SHORT).show();
                    //startActivity(i);
                    //finish();
                }else {
                    //i.putExtra("puntaje", 0);
                    //Toast.makeText(this,"Respuesta incorrecta, *"+oraCorrQue,Toast.LENGTH_SHORT).show();
                    //startActivity(i);
                    //finish();
                }
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
        Pregunta miPregunta=new Pregunta();
        JSONArray json=response.optJSONArray("avanOrac");
        JSONObject jsonObject=null;
        try {
            jsonObject=json.getJSONObject(0);
            miPregunta.setPregunta(jsonObject.optString("avPregunta"));
            miPregunta.setDato(jsonObject.optString("avImagen"));
            miPregunta.setPalabraEsp(jsonObject.optString("avOracionEsp"));
            miPregunta.setPalabra(jsonObject.optString("avOracionQue"));
            miPregunta.setOp1(jsonObject.optString("avPalabra1"));
            miPregunta.setOp2(jsonObject.optString("avPalabra2"));
            miPregunta.setOp3(jsonObject.optString("avPalabra3"));
            miPregunta.setOp4(jsonObject.optString("avPalabra4"));
            miPregunta.setOp5(jsonObject.optString("avPalabra5"));
            miPregunta.setDato(jsonObject.optString("avImagen"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        txtPregunta.setText(miPregunta.getPregunta()+"");
        this.txtOraCorrEsp.setText(miPregunta.getPalabraEsp());
        this.oraCorrQue = miPregunta.getPalabra();
        this.btnPal1.setText(miPregunta.getOp1());
        this.btnPal2.setText(miPregunta.getOp2());
        this.btnPal3.setText(miPregunta.getOp3());
        this.btnPal4.setText(miPregunta.getOp4());
        this.btnPal5.setText(miPregunta.getOp5());

        if (miPregunta.getImagen()!=null){
            imgOr.setImageBitmap(miPregunta.getImagen());
        }else{
            imgOr.setImageResource(R.drawable.img_base);
        }
    }

    private static class MyDragShadowBuilder extends View.DragShadowBuilder {

        // The drag shadow image, defined as a drawable thing
        private static Drawable shadow;

        // Defines the constructor for myDragShadowBuilder
        public MyDragShadowBuilder(View v) {

            // Stores the View parameter passed to myDragShadowBuilder.
            super(v);

            // Creates a draggable image that will fill the Canvas provided by the system.
            shadow = new ColorDrawable(Color.LTGRAY);
        }

        // Defines a callback that sends the drag shadow dimensions and touch point back to the
        // system.
        @Override
        public void onProvideShadowMetrics (Point size, Point touch) {
            // Defines local variables
            int width, height;

            // Sets the width of the shadow to half the width of the original View
            width = getView().getWidth() / 2;

            // Sets the height of the shadow to half the height of the original View
            height = getView().getHeight() / 2;

            // The drag shadow is a ColorDrawable. This sets its dimensions to be the same as the
            // Canvas that the system will provide. As a result, the drag shadow will fill the
            // Canvas.
            shadow.setBounds(0, 0, width, height);

            // Sets the size parameter's width and height values. These get back to the system
            // through the size parameter.
            size.set(width, height);

            // Sets the touch point's position to be in the middle of the drag shadow
            touch.set(width / 2, height / 2);
        }

        // Defines a callback that draws the drag shadow in a Canvas that the system constructs
        // from the dimensions passed in onProvideShadowMetrics().
        @Override
        public void onDrawShadow(Canvas canvas) {

            // Draws the ColorDrawable in the Canvas passed in from the system.
            shadow.draw(canvas);
        }
    }
}