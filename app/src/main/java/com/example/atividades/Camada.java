package com.example.atividades;

import android.graphics.Paint;

import android.graphics.Path;

public class Camada {
    Path path;
    Paint paint;

    public Camada() {
        path = new Path();
        paint = new Paint();
        paint.setColor(0xFF000000);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
    }
    public Camada(Paint paint) {
        this.path = new Path();
        this.paint = new Paint(paint);
    }

}