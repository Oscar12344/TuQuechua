package com.example.tuquechua.entidades;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Pregunta {

    private String palabra;
    private String dato;
    private Bitmap imagen;
    private String pregunta;
    /*nombre de la imagen*/
    private String img;
    /*usado con Json-OpcionesImagen*/
    private String op1Palabra, op2Palabra, op3Palabra, op4Palabra;
    private String dato1, dato2, dato3, dato4;
    private Bitmap op1Imagen, op2Imagen, op3Imagen, op4Imagen;

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

    public String getImgLocation() {
        return img;
    }

    public void setImgLocation(String imgLoc) {
        this.img = imgLoc;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }



    /*usando wsJSONConsultarPreguntaOpcionesImagen*/

    public String getPalabraEsp() {
        return palabra;
    }

    public void setPalabraEsp(String palabra) {
        this.palabra = palabra;
    }

    public String getDatoOp1() {
        return dato;
    }

    public void setDatoOp1(String dato) {
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

    public Bitmap getImagenOp1() {
        return imagen;
    }

    public void setImagenOp1(Bitmap imagen) {
        this.imagen = imagen;
    }

    public Bitmap getImagenOp2() {
        return imagen;
    }

    public void setImagenOp2(Bitmap imagen) {
        this.imagen = imagen;
    }

    public Bitmap getImagenOp3() {
        return imagen;
    }

    public void setImagenOp3(Bitmap imagen) {
        this.imagen = imagen;
    }

    public Bitmap getImagenOp4() {
        return imagen;
    }

    public void setImagenOp4(Bitmap imagen) {
        this.imagen = imagen;
    }
}
