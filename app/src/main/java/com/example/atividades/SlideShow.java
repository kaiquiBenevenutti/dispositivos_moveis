package com.example.atividades;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SlideShow extends AppCompatActivity {
    Button avancar, voltar;
    ImageView img;
    Integer imagens[]=new Integer[]{
            R.drawable.cachorro,
            R.drawable.gardem,
            R.drawable.patinho,
            R.drawable.happy,
            R.drawable.porquinho
    };
    int posicao=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_slide_show);

        voltar = findViewById(R.id.voltar);
        avancar = findViewById(R.id.avancar);
        img = findViewById(R.id.imageView);

        avancar.setOnClickListener(v ->{
            if(imagens.length > (posicao + 1)){
                posicao++;
            img.setImageResource(imagens[posicao]);
        }} );

        voltar.setOnClickListener(v ->{
            if(posicao > 0) {
                posicao--;
                img.setImageResource(imagens[posicao]);
            }
        } );

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}