package com.example.atividades;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ImcDuasTelas extends AppCompatActivity {

    TextView peso,altura,imc;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_imc_duas_telas);
        imageView=findViewById(R.id.imagem);
        peso=findViewById(R.id.peso);
        altura=findViewById(R.id.altura);
        imc=findViewById(R.id.imc);

        Bundle b=getIntent().getExtras();
        float pesoF=b.getFloat("peso");
        float alturaF=b.getFloat("altura");
        float imcF=(pesoF)/(alturaF*alturaF);

        peso.setText(Float.toString(pesoF));
        altura.setText(Float.toString(alturaF));
        imc.setText(Float.toString(imcF));


        if(imcF>18.5 && imcF<100){
            imageView.setImageResource(R.drawable.normal);
        }
        if (imcF<18.5){
            imageView.setImageResource(R.drawable.abaixopeso);
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}