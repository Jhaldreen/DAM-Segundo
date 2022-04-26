package com.example.ejemplo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class AltaPeriodicoActvity extends AppCompatActivity {

    // 1 - declarar variables para crear los view

    EditText nombrePeriodico, precio;
    RadioButton generalista;
    RadioButton deportes;
    RadioButton politica;
    Button añadir;

    BaseDeDatos baseDeDatos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_periodico);

        // 2 - findviewbyid
        nombrePeriodico = findViewById(R.id.nombrePeriodico);
        generalista = findViewById(R.id.generalista);
        deportes = findViewById(R.id.deportes);
        politica = findViewById(R.id.politica);
        añadir = findViewById(R.id.btanadir);
        precio = findViewById(R.id.precio);


        // 3.- listeners

        añadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               guardar();
            }
        });

    }



    public void guardar(){
        // sacar la infor que quiero añadir
        String nombre = nombrePeriodico.getText().toString();
        String pre = precio.getText().toString();
        String tipo="";
        if(politica.isChecked()) {
            tipo="Politica";
        }else if (deportes.isChecked()){
            tipo="Deportes";
        }else {
            tipo="Generalista";
        }

        Periodico p = new Periodico(nombre, tipo, Integer.parseInt(pre));

        baseDeDatos = new BaseDeDatos(this, "BDPERIODICOS", null, 1);
        baseDeDatos.insertarRegistro(p);

        finish();
    }



}