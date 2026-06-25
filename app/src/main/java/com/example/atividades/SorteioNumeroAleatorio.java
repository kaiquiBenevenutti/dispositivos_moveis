package com.example.atividades;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class SorteioNumeroAleatorio extends AppCompatActivity {
    EditText min, max;
    TextView tv;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sorteio_numero_aleatorio);
        min=findViewById(R.id.min);
        max=findViewById(R.id.max);
        tv=findViewById(R.id.tv);
        Button button=findViewById(R.id.button);

        button.setOnClickListener(v -> {
            int minimo = Integer.parseInt(min.getText().toString());
            int maximo = Integer.parseInt(max.getText().toString());
            Random r = new Random();
            int numero = r.nextInt(maximo-minimo) + minimo;
            tv.setText(Integer.toString(numero));
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}