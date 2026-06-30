package com.example.atividades;


import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Listagem extends AppCompatActivity {
    EditText ed;
    ListView listView;
    Button button;
    ArrayList<String> textos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listagem);

        textos = new ArrayList<>();
        listView =findViewById(R.id.listView);
        button = findViewById(R.id.button);
        ed = findViewById(R.id.editTextText);
        ArrayAdapter<String> a = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, textos);
        listView.setAdapter(a);
        button.setOnClickListener(v -> {
            textos.add(ed.getText().toString());
            a.notifyDataSetChanged();
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(Listagem.this);
            builder.setTitle("Excluir")
                    .setMessage("Confirmar")
                    .setPositiveButton("Sim", (dialog, which) -> {
                        textos.remove(position);
                        a.notifyDataSetChanged();
                        Toast.makeText(Listagem.this, "Excluido", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Cancelar", (dialog, which) -> {
                        Toast.makeText(Listagem.this, "Cancelado", Toast.LENGTH_SHORT).show();
                    })
                    .setCancelable(false);

            builder.show();
        });
        }
    }