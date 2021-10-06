package com.example.tuquechua.entidades;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Nivel {
    private String nomnivel1;
    private String nomnivel2;
    private String nomnivel3;
    private String dato1, dato2, dato3;

    public String getDato1() {
        return dato1;
    }

    public void setDato1(String dato1) {
        this.dato1 = dato1;
        try {
            byte[] byteCode= Base64.decode(dato1,Base64.DEFAULT);
            this.imagennivel1= BitmapFactory.decodeByteArray(byteCode,0,byteCode.length);

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
            this.imagennivel2= BitmapFactory.decodeByteArray(byteCode,0,byteCode.length);

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
            this.imagennivel3= BitmapFactory.decodeByteArray(byteCode,0,byteCode.length);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private Bitmap imagennivel1, imagennivel2, imagennivel3;

    public String getNomnivel1() {
        return nomnivel1;
    }

    public void setNomnivel1(String nomnivel1) {
        this.nomnivel1 = nomnivel1;
    }

    public String getNomnivel2() {
        return nomnivel2;
    }

    public void setNomnivel2(String nomnivel2) {
        this.nomnivel2 = nomnivel2;
    }

    public String getNomnivel3() {
        return nomnivel3;
    }

    public void setNomnivel3(String nomnivel3) {
        this.nomnivel3 = nomnivel3;
    }

    public Bitmap getImagennivel1() {
        return imagennivel1;
    }

    public void setImagennivel1(Bitmap imagennivel1) {
        this.imagennivel1 = imagennivel1;
    }

    public Bitmap getImagennivel2() {
        return imagennivel2;
    }

    public void setImagennivel2(Bitmap imagennivel2) {
        this.imagennivel2 = imagennivel2;
    }

    public Bitmap getImagennivel3() {
        return imagennivel3;
    }

    public void setImagennivel3(Bitmap imagennivel3) {
        this.imagennivel3 = imagennivel3;
    }
}
