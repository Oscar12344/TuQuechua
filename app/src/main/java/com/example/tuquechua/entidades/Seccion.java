package com.example.tuquechua.entidades;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Seccion {
    private String dato1, dato2, dato3, dato4;
    private String nomsecc1;
    private String nomsecc2;
    private String nomsecc3;
    private String nomsecc4;
    private Bitmap imagensecc1, imagensecc2, imagensecc3, imagensecc4;

    public String getDato1() {
        return dato1;
    }

    public void setDato1(String dato1) {
        this.dato1 = dato1;
        try {
            byte[] byteCode= Base64.decode(dato1,Base64.DEFAULT);
            this.imagensecc1= BitmapFactory.decodeByteArray(byteCode,0,byteCode.length);

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
            this.imagensecc2= BitmapFactory.decodeByteArray(byteCode,0,byteCode.length);

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
            this.imagensecc3= BitmapFactory.decodeByteArray(byteCode,0,byteCode.length);

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
            this.imagensecc4= BitmapFactory.decodeByteArray(byteCode,0,byteCode.length);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getNomsecc1() {
        return nomsecc1;
    }

    public void setNomsecc1(String nomsecc1) {
        this.nomsecc1 = nomsecc1;
    }

    public String getNomsecc2() {
        return nomsecc2;
    }

    public void setNomsecc2(String nomsecc2) {
        this.nomsecc2 = nomsecc2;
    }

    public String getNomsecc3() {
        return nomsecc3;
    }

    public void setNomsecc3(String nomsecc3) {
        this.nomsecc3 = nomsecc3;
    }

    public String getNomsecc4() {
        return nomsecc4;
    }

    public void setNomsecc4(String nomsecc4) {
        this.nomsecc4 = nomsecc4;
    }

    public Bitmap getImagensecc1() {
        return imagensecc1;
    }

    public void setImagensecc1(Bitmap imagensecc1) {
        this.imagensecc1 = imagensecc1;
    }

    public Bitmap getImagensecc2() {
        return imagensecc2;
    }

    public void setImagensecc2(Bitmap imagensecc2) {
        this.imagensecc2 = imagensecc2;
    }

    public Bitmap getImagensecc3() {
        return imagensecc3;
    }

    public void setImagensecc3(Bitmap imagensecc3) {
        this.imagensecc3 = imagensecc3;
    }

    public Bitmap getImagensecc4() {
        return imagensecc4;
    }

    public void setImagensecc4(Bitmap imagensecc4) {
        this.imagensecc4 = imagensecc4;
    }
}
