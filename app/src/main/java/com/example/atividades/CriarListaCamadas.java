package com.example.atividades;

import android.graphics.Paint;

import java.util.ArrayList;

public class CriarListaCamadas {
    ArrayList<Camada> camadas;
    Camada camada;
    public CriarListaCamadas(){
        camadas=new ArrayList<Camada>();
        camada =new Camada();
        camadas.add(camada);
    }
    public void adicionarNovaCamada(){
        Camada camada=new Camada(this.camada.paint);
        this.camada =camada;
        camadas.add(camada);

    }

    public  void removeCamadaAtual(Camada camada){
        camadas.remove(camada);
        if(camadas.size()>0){
            this.camada =camadas.get(camadas.size()-1);
        }else{
            this.camada =new Camada();
        }
    }
    public  void limparTodasAsCamadas(){
        Paint paint=new Paint(camada.paint);
        camadas.removeAll(camadas);
        camada =new Camada(paint);
        camadas.add(camada);
    }


}