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
import android.view.DragEvent;
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

                //dragListen = new myDragEventListener();
                btnPal1.setEnabled(false);

                return true;
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
                btnPal2.setEnabled(false);

                return true;
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
                btnPal3.setEnabled(false);

                return true;
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
                btnPal4.setEnabled(false);

                return true;
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
                btnPal5.setEnabled(false);

                return true;
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

        /*if (miPregunta.getImagen()!=null){
            imgOr.setImageBitmap(miPregunta.getImagen());
        }else{
            imgOr.setImageResource(R.drawable.img_base);
        }*/
    }

    private void procesarRespuesta(){
        String rpta = txtOraUsu.getText().toString();

        int punt = getIntent().getIntExtra("puntaje",0);
        Intent i = new Intent(this, Avanzado_Ejercicio3.class);

        if(rpta.equals(""))
        {
            Toast.makeText(this,"Rellene su respuesta",Toast.LENGTH_SHORT).show();
            txtOraUsu.requestFocus();
        }else if (rpta.equalsIgnoreCase(oraCorrQue)){
            Toast.makeText(this, oraCorrQue+", Respuesta correcta",Toast.LENGTH_SHORT).show();
            i.putExtra("puntaje", punt+5);
            startActivity(i);
            finish();
        }else {
            Toast.makeText(this,"Respuesta incorrecta, *"+oraCorrQue,Toast.LENGTH_SHORT).show();
            i.putExtra("puntaje", punt);
            startActivity(i);
            finish();
        }
    }

    private static class MyDragShadowBuilder extends View.DragShadowBuilder {
        private static Drawable shadow;
        public MyDragShadowBuilder(View v) {
            super(v);
            shadow = new ColorDrawable(Color.LTGRAY);
        }

        @Override
        public void onProvideShadowMetrics (Point size, Point touch) {
            int width, height;
            width = getView().getWidth() / 2;
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
        @Override
        public void onDrawShadow(Canvas canvas) {
            shadow.draw(canvas);
        }
    }

    protected class myDragEventListener implements View.OnDragListener {

        // This is the method that the system calls when it dispatches a drag event to the
        // listener.
        public boolean onDrag(View v, DragEvent event) {

            // Defines a variable to store the action type for the incoming event
            final int action = event.getAction();

            // Handles each of the expected events
            switch(action) {

                case DragEvent.ACTION_DRAG_STARTED:

                    // Determines if this View can accept the dragged data
                    if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {

                        // As an example of what your application might do,
                        // applies a blue color tint to the View to indicate that it can accept
                        // data.
                        //v.setColorFilter(Color.BLUE);

                        // Invalidate the view to force a redraw in the new tint
                        v.invalidate();

                        // returns true to indicate that the View can accept the dragged data.
                        return true;

                    }

                    // Returns false. During the current drag and drop operation, this View will
                    // not receive events again until ACTION_DRAG_ENDED is sent.
                    return false;

                case DragEvent.ACTION_DRAG_ENTERED:

                    // Applies a green tint to the View. Return true; the return value is ignored.

                    //v.setColorFilter(Color.GREEN);

                    // Invalidate the view to force a redraw in the new tint
                    v.invalidate();

                    return true;

                case DragEvent.ACTION_DRAG_LOCATION:

                    // Ignore the event
                    return true;

                case DragEvent.ACTION_DRAG_EXITED:

                    // Re-sets the color tint to blue. Returns true; the return value is ignored.
                    //v.setColorFilter(Color.BLUE);

                    // Invalidate the view to force a redraw in the new tint
                    v.invalidate();

                    return true;

                case DragEvent.ACTION_DROP:

                    // Gets the item containing the dragged data
                    ClipData.Item item = event.getClipData().getItemAt(0);

                    // Gets the text data from the item.
                    //dragData = item.getText();

                    // Displays a message containing the dragged data.
                    //Toast.makeText(this, "Dragged data is " + dragData, Toast.LENGTH_LONG).show();

                    // Turns off any color tints
                    //v.clearColorFilter();

                    // Invalidates the view to force a redraw
                    v.invalidate();

                    // Returns true. DragEvent.getResult() will return true.
                    return true;

                case DragEvent.ACTION_DRAG_ENDED:

                    // Turns off any color tinting
                    //v.clearColorFilter();

                    // Invalidates the view to force a redraw
                    v.invalidate();

                    // Does a getResult(), and displays what happened.
                    if (event.getResult()) {
                        //Toast.makeText(this, "The drop was handled.", Toast.LENGTH_LONG).show();
                    } else {
                        //Toast.makeText(this, "The drop didn't work.", Toast.LENGTH_LONG).show();
                    }

                    // returns true; the value is ignored.
                    return true;

                // An unknown action type was received.
                default:
                    Log.e("DragDrop Example","Unknown action type received by OnDragListener.");
                    break;
            }

            return false;
        }
    }
}