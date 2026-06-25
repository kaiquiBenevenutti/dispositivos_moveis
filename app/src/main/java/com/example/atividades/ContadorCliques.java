package com.example.atividades;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ContadorCliques extends AppCompatActivity {

    Button btn;
    TextView tv;
    int c;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contador_cliques);
        btn = findViewById(R.id.botao);
        tv =findViewById(R.id.textView);
        btn.setText("Clique");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c++;
                Button b = (Button) v;
                tv.setText(String.valueOf(c));
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}