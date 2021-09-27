package com.example.tuquechua.entidades;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Pregunta {

    private String palabra;
    private String dato;
    private Bitmap imagen;
    private String pregunta;

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public String getDato() {
        return dato;

    }

    public void setDato(String dato) {
        this.dato = dato;
        this.dato = dato;
        try {
            byte[] byteCode= Base64.decode(dato,Base64.DEFAULT);
            this.imagen= BitmapFactory.decodeByteArray(byteCode,0,byteCode.length);
            //bajar la resolucion
            /*int alto=100;//alto en pixeles
            int ancho=150;//ancho en pixeles

            Bitmap foto=BitmapFactory.decodeByteArray(byteCode,0,byteCode.length);
            this.imagen=Bitmap.createScaledBitmap(foto,alto,ancho,true);*/


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }
}
