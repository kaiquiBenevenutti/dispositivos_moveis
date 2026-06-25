package com.example.atividades;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ImcUmaTela extends AppCompatActivity {

    TextView tvImcTexto, tvImcNumero;
    EditText etPeso, etAltura;

    Button btnCalcular;
    ImageView imageView, imageView2;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_imc_uma_tela);

        etPeso = findViewById(R.id.peso);
        etAltura = findViewById(R.id.altura);
        btnCalcular = findViewById(R.id.button);
        tvImcTexto=findViewById(R.id.imcTexto);
        tvImcNumero=findViewById(R.id.imcNumero);

        imageView=findViewById(R.id.imageView);
        imageView2=findViewById(R.id.imageView2);

        imageView2.setImageResource(R.drawable.perfil);

        btnCalcular.setOnClickListener(v -> {

            if (etPeso.getText().toString().trim().isEmpty()) {
                return;
            }

            if (etAltura.getText().toString().trim().isEmpty()) {
                return;
            }

                float peso = Float.parseFloat(etPeso.getText().toString());
                float altura = Float.parseFloat(etAltura.getText().toString());

                float imc = peso / (altura * altura);

                tvImcNumero.setText(String.format("%.2f", imc));

                if (imc < 18.5) {
                    imageView.setImageResource(R.drawable.abaixopeso);
                    tvImcTexto.setText("Abaixo do peso.");

                } else if (imc < 25) {
                    imageView.setImageResource(R.drawable.normal);
                    tvImcTexto.setText("Normal.");

                } else if (imc < 30) {
                    imageView.setImageResource(R.drawable.sobrepeso);
                    tvImcTexto.setText("Sobrepeso.");

                } else if (imc < 35) {
                    imageView.setImageResource(R.drawable.obesidade1);
                    tvImcTexto.setText("Obesidade Grau I.");

                } else if (imc < 40) {
                    imageView.setImageResource(R.drawable.obesidade2);
                    tvImcTexto.setText("Obesidade Grau II.");

                } else {
                    imageView.setImageResource(R.drawable.obesidade3);
                    tvImcTexto.setText("Obesidade Grau III.");
                }
            });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}