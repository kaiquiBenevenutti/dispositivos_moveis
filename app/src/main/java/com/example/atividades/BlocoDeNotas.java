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

public class BlocoDeNotas extends AppCompatActivity {
    EditText ed;
    ListView listView;
    Button button;
    ArrayList<String> textos;

    int p = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bloco_de_notas);

        textos = new ArrayList<>();
        listView =findViewById(R.id.listView);
        button = findViewById(R.id.button);
        ed = findViewById(R.id.editText);
        ArrayAdapter<String> a = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, textos);
        listView.setAdapter(a);
        button.setOnClickListener(v -> {
            String t = ed.getText().toString();
            if (!t.trim().isEmpty()) {
                if (p >= 0) {
                    textos.set(p, t);
                    p = -1;
                } else {
                    textos.add(t);
                }
                a.notifyDataSetChanged();
                ed.setText("");
            }
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            ed.setText(textos.get(position));
            p = position;
        });

        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(BlocoDeNotas.this);
            builder.setTitle("Excluir")
                    .setMessage("Confirmar")
                    .setPositiveButton("Sim", (dialog, which) -> {
                        textos.remove(position);
                        a.notifyDataSetChanged();
                        Toast.makeText(BlocoDeNotas.this, "Excluido", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Cancelar", (dialog, which) -> {
                        Toast.makeText(BlocoDeNotas.this, "Cancelado", Toast.LENGTH_SHORT).show();
                    })
                    .setCancelable(false);

            builder.show();
            return true;
        });
    }
}
