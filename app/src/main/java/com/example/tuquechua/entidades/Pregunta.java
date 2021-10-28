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
    private String op1,op2,op3,op4,op5,op6,op7,op8,op9,op10,op11,op12;
    private String dato1, dato2, dato3, dato4;
    private Bitmap op1Imagen, op2Imagen, op3Imagen, op4Imagen;
    private String palabraEsp;

    private String vidUrl;
    private String avPre1Rpta,avPre2Rpta,avPre3Rpta;

    public String getVidUrl2() {
        return vidUrl2;
    }

    public void setVidUrl2(String vidUrl2) {
        this.vidUrl2 = vidUrl2;
    }

    public String getVidUrl3() {
        return vidUrl3;
    }

    public void setVidUrl3(String vidUrl3) {
        this.vidUrl3 = vidUrl3;
    }

    public String getVidUrl4() {
        return vidUrl4;
    }

    public void setVidUrl4(String vidUrl4) {
        this.vidUrl4 = vidUrl4;
    }

    private String vidUrl2,vidUrl3,vidUrl4;

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

    public String getVidUrl() { return vidUrl; }

    public void setVidUrl(String vidUrl) {        this.vidUrl = vidUrl;    }

    public String getAvPre1Rpta() {        return avPre1Rpta;    }

    public void setAvPre1Rpta(String avPre1Rpta) {        this.avPre1Rpta = avPre1Rpta;    }

    public String getAvPre2Rpta() {        return avPre2Rpta;    }

    public void setAvPre2Rpta(String avPre2Rpta) {        this.avPre2Rpta = avPre2Rpta;    }

    public String getAvPre3Rpta() {        return avPre3Rpta;    }

    public void setAvPre3Rpta(String avPre3Rpta) {        this.avPre3Rpta = avPre3Rpta;    }

    public String getOp5() {        return op5;    }

    public void setOp5(String op5) {        this.op5 = op5;    }

    public String getOp6() {        return op6;    }

    public void setOp6(String op6) {        this.op6 = op6;    }

    public String getOp7() {        return op7;    }

    public void setOp7(String op7) {        this.op7 = op7;    }

    public String getOp8() {        return op8;    }

    public void setOp8(String op8) {        this.op8 = op8;    }

    public String getOp9() {        return op9;    }

    public void setOp9(String op9) {        this.op9 = op9;    }

    public String getOp10() {        return op10;    }

    public void setOp10(String op10) {        this.op10 = op10;    }

    public String getOp11() {        return op11;    }

    public void setOp11(String op11) {        this.op11 = op11;    }

    public String getOp12() {        return op12;    }

    public void setOp12(String op12) {        this.op12 = op12;    }
}
