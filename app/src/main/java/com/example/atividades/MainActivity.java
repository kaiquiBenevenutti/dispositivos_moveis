package com.example.atividades;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText pesoE, alturaE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        button =findViewById(R.id.button);
        pesoE =findViewById(R.id.pesoE);
        alturaE =findViewById(R.id.alturaE);

        button.setOnClickListener(v -> {
            if (pesoE.getText().toString().trim().isEmpty()) {
                return;
            }

            if (alturaE.getText().toString().trim().isEmpty()) {
                return;
            }

            Intent intent=new Intent(this,ImcDuasTelas.class);
            float pesoF= Float.parseFloat(pesoE.getText().toString());
            float alturaF= Float.parseFloat(alturaE.getText().toString());
            intent.putExtra("altura",alturaF);
            intent.putExtra("peso",pesoF);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}