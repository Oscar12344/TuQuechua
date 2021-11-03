package com.example.tuquechua.entidades;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.util.Base64;

public class Pregunta {

    private String palabra;
    private String dato;
    private Bitmap imagen;
    private String pregunta;
    private String op1;
    private String op2;
    private String op3;
    private String op4;
    private String dato1, dato2, dato3, dato4;
    private Bitmap op1Imagen, op2Imagen, op3Imagen, op4Imagen;
    private String palabraEsp;


    public String getOp1() {
        return op1;
    }

    public void setOp1(String op1) {
        this.op1 = op1;
    }

    public String getOp2() {
        return op2;
    }

    public void setOp2(String op2) {
        this.op2 = op2;
    }

    public String getOp3() {
        return op3;
    }

    public void setOp3(String op3) {
        this.op3 = op3;
    }

    public String getOp4() {
        return op4;
    }

    public void setOp4(String op4) {
        this.op4 = op4;
    }

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

    public String getDato1() {
        return dato1;
    }

    public void setDato1(String dato1) {
        this.dato1 = dato1;
        try {
            byte[] byteCode= Base64.decode(dato1,Base64.DEFAULT);
            this.op1Imagen= BitmapFactory.decodeByteArray(byteCode,0,byteCode.length);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getDato2() {
        return dato2;
    }

    public void setDato2(String dato2) {
        this.dato2 = dato2;
        try {
            byte[] byteCode= Base64.decode(dato2,Base64.DEFAULT);
            this.op2Imagen= BitmapFactory.decodeByteArray(byteCode,0,byteCode.length);
            //bajar la resolucion
            /*int alto=100;//alto en pixeles
            int ancho=150;//ancho en pixeles

            Bitmap foto=BitmapFactory.decodeByteArray(byteCode,0,byteCode.length);
            this.imagen=Bitmap.createScaledBitmap(foto,alto,ancho,true);*/
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getDato3() {
        return dato3;
    }

    public void setDato3(String dato3) {
        this.dato3 = dato3;
        try {
            byte[] byteCode= Base64.decode(dato3,Base64.DEFAULT);
            this.op3Imagen= BitmapFactory.decodeByteArray(byteCode,0,byteCode.length);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getDato4() {
        return dato4;
    }

    public void setDato4(String dato4) {
        this.dato4 = dato4;
        try {
            byte[] byteCode= Base64.decode(dato4,Base64.DEFAULT);
            this.op4Imagen= BitmapFactory.decodeByteArray(byteCode,0,byteCode.length);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Bitmap getOp1Imagen() {
        return op1Imagen;
    }

    public void setOp1Imagen(Bitmap op1Imagen) {
        this.op1Imagen = op1Imagen;
    }

    public Bitmap getOp2Imagen() {
        return op2Imagen;
    }

    public void setOp2Imagen(Bitmap op2Imagen) {
        this.op2Imagen = op2Imagen;
    }

    public Bitmap getOp3Imagen() {
        return op3Imagen;
    }

    public void setOp3Imagen(Bitmap op3Imagen) {
        this.op3Imagen = op3Imagen;
    }

    public Bitmap getOp4Imagen() {
        return op4Imagen;
    }

    public void setOp4Imagen(Bitmap op4Imagen) {
        this.op4Imagen = op4Imagen;
    }

    public String getPalabraEsp() {
        return palabraEsp;
    }

    public void setPalabraEsp(String palabraEsp) {
        this.palabraEsp = palabraEsp;
    }
}
