package com.example.tuquechua.avanzado;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tuquechua.MainActivity;
import com.example.tuquechua.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.tuquechua.Seccion;
import com.example.tuquechua.entidades.Pregunta;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

public class Avanzado_Video_Numeros extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    SimpleExoPlayer player;
    PlayerView playerView;
    ProgressBar progressBar;
    Button btnOp1, btnOp2, btnOp3, btnOp4;
    String op1,op2,op3,op4,rptaCorrecta,rpta;
    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    Pregunta miPregunta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avanzado_video_numeros);
        btnOp1 = findViewById(R.id.btnOp1);
        btnOp2 = findViewById(R.id.btnOp2);
        btnOp3 = findViewById(R.id.btnOp3);
        btnOp4 = findViewById(R.id.btnOp4);
        playerView = findViewById(R.id.player);

        request= Volley.newRequestQueue(this);
        progreso=new ProgressDialog(this);
        progreso.setMessage("Consultando...");
        progreso.show();

        String url = getString(R.string.urlAvVid)+1;

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
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
        JSONArray json=response.optJSONArray("avanVideo");
        JSONObject jsonObject=null;

        try {
            jsonObject=json.getJSONObject(0);
            miPregunta.setVidUrl(jsonObject.optString("avURL"));
            miPregunta.setOp1(jsonObject.optString("avPre1Op1"));
            miPregunta.setOp2(jsonObject.optString("avPre1Op2"));
            miPregunta.setOp3(jsonObject.optString("avPre1Op3"));
            miPregunta.setOp4(jsonObject.optString("avPre1Op4"));
            miPregunta.setOp5(jsonObject.optString("avPre2Op1"));
            miPregunta.setOp6(jsonObject.optString("avPre2Op2"));
            miPregunta.setOp7(jsonObject.optString("avPre2Op3"));
            miPregunta.setOp8(jsonObject.optString("avPre2Op4"));
            miPregunta.setOp9(jsonObject.optString("avPre3Op1"));
            miPregunta.setOp10(jsonObject.optString("avPre3Op2"));
            miPregunta.setOp11(jsonObject.optString("avPre3Op3"));
            miPregunta.setOp12(jsonObject.optString("avPre3Op4"));
            miPregunta.setAvPre1Rpta(jsonObject.optString("avPre1Rpta"));
            miPregunta.setAvPre2Rpta(jsonObject.optString("avPre2Rpta"));
            miPregunta.setAvPre3Rpta(jsonObject.optString("avPre3Rpta"));
            miPregunta.setVidUrl2(jsonObject.optString("avURL2"));
            miPregunta.setVidUrl3(jsonObject.optString("avURL3"));
            miPregunta.setVidUrl4(jsonObject.optString("avURL4"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        player = new SimpleExoPlayer.Builder(this).build();
        // Bind the player to the view.
        playerView.setPlayer(player);
        //"https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4"

        String vidurl = miPregunta.getVidUrl();
        String parte = "1";

        InitializeVideo(vidurl,parte);
    }

    private void InitializeVideo(String videUri, String parte){
        btnOp1.setText("");
        btnOp2.setText("");
        btnOp3.setText("");
        btnOp4.setText("");

        Uri vidurl = Uri.parse(videUri);//"https://imgur.com/a/4bh8rKw")
        /*new YouTubeExtractor(this) {
            @Override
            public void onExtractionComplete(SparseArray<YtFile> ytFiles, VideoMeta vMeta) {
                if (ytFiles != null) {
                    int itag = 22;
                    String downloadUrl = ytFiles.get(itag).getUrl();
                }
            }
        }.extract(videUri);*/

        // Build the media item.
        MediaItem mediaItem = MediaItem.fromUri(vidurl);
        // Set the media item to be played.
        player.setMediaItem(mediaItem);
        // Prepare the player.
        player.prepare();
        // Start the playback.
        player.play();

        player.addListener(new Player.Listener() {
            @Override
            public void onPlaybackStateChanged(int playbackState) {
                /*if (playbackState == Player.STATE_BUFFERING){
                    progressBar.setVisibility(View.VISIBLE);
                }else if (playbackState == Player.STATE_READY){
                    progressBar.setVisibility(View.GONE);
                }else*/
                if (playbackState == Player.STATE_ENDED){
                    MostrarOpciones(parte);
                }
            }
        });
    }

    private void MostrarOpciones(String parte) {
        switch (parte){
            case "1":
                btnOp1.setText(miPregunta.getOp1());
                btnOp2.setText(miPregunta.getOp2());
                btnOp3.setText(miPregunta.getOp3());
                btnOp4.setText(miPregunta.getOp4());
                HabilitarClicks(parte);
                break;
            case "2":
                btnOp1.setText(miPregunta.getOp5());
                btnOp2.setText(miPregunta.getOp6());
                btnOp3.setText(miPregunta.getOp7());
                btnOp4.setText(miPregunta.getOp8());
                HabilitarClicks(parte);
                break;
            case "3":
                btnOp1.setText(miPregunta.getOp9());
                btnOp2.setText(miPregunta.getOp10());
                btnOp3.setText(miPregunta.getOp11());
                btnOp4.setText(miPregunta.getOp12());
                HabilitarClicks(parte);
                break;
            case "4":
                procesarRespuesta(null,"4");
                break;
        }
    }

    private void HabilitarClicks(String parte){
        btnOp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rpta = btnOp1.getText().toString();
                procesarRespuesta(rpta, parte);
            }
        });
        btnOp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rpta = btnOp2.getText().toString();
                procesarRespuesta(rpta, parte);
            }
        });
        btnOp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rpta = btnOp3.getText().toString();
                procesarRespuesta(rpta, parte);
            }
        });
        btnOp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rpta = btnOp4.getText().toString();
                procesarRespuesta(rpta, parte);
            }
        });
    }

    private void procesarRespuesta(String rptaUsu, String parte){
        switch (parte){
            case "1":
                this.rptaCorrecta = miPregunta.getAvPre1Rpta();
                if (rptaUsu.equalsIgnoreCase(this.rptaCorrecta)){
                    Toast.makeText(this, this.rptaCorrecta+", Respuesta correcta",Toast.LENGTH_SHORT).show();
                    String url = miPregunta.getVidUrl2();
                    InitializeVideo(url,parte="2");
                } else {
                    Toast.makeText(this, rptaUsu+", Respuesta incorrecta",Toast.LENGTH_SHORT).show();
                }
                break;
            case "2":
                this.rptaCorrecta = miPregunta.getAvPre2Rpta();
                if (rptaUsu.equalsIgnoreCase(this.rptaCorrecta)){
                    Toast.makeText(this, this.rptaCorrecta+", Respuesta correcta",Toast.LENGTH_SHORT).show();
                    String url = miPregunta.getVidUrl3();
                    InitializeVideo(url,parte="3");
                } else {
                    Toast.makeText(this, rptaUsu+", Respuesta incorrecta",Toast.LENGTH_SHORT).show();
                }
                break;
            case "3":
                this.rptaCorrecta = miPregunta.getAvPre3Rpta();
                if (rptaUsu.equalsIgnoreCase(this.rptaCorrecta)){
                    Toast.makeText(this, this.rptaCorrecta+", Respuesta correcta",Toast.LENGTH_SHORT).show();
                    String url = miPregunta.getVidUrl4();
                    InitializeVideo(url,parte="4");
                } else {
                    Toast.makeText(this, rptaUsu+", Respuesta incorrecta",Toast.LENGTH_SHORT).show();
                }
                break;
            case "4":
                player.release();
                this.finish();
                Intent i = new Intent(this, Avanzado_Ejercicio2_Comida.class);
                startActivity(i);
                break;
        }
    }

    @Override
    public void onBackPressed()
    {
        player.release();
        Toast.makeText(this,"No se guardó el progreso",Toast.LENGTH_SHORT).show();
        this.finish();
        Intent i = new Intent(getApplication(), Seccion.class);
        startActivity(i);
    }
}