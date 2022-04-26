package com.example.ejemplo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    // 1 - declarar variables para crear los view
    RecyclerView lista;


    BaseDeDatos baseDeDatos;
    ArrayList<Periodico> arrayCosas;
    MiAdaptador adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2 - findviewbyid
        lista = findViewById(R.id.lvLista);


        // 3- recoger datos de otras actividades


        // 4- listeners


        // 5 - obtener otros datos y pintar los datos en pantalla


        // las listas no cogen losdatos directamente, sino por medio deun adaptador
        // creamos priomero los datos

        baseDeDatos = new BaseDeDatos(this, "BDPERIODICOS", null, 1);


        arrayCosas = baseDeDatos.leerTodosLosRegistros();

        if (arrayCosas.isEmpty()) {
            Toast.makeText(getApplicationContext(), "CARGANDO DATOS INICIALES", Toast.LENGTH_LONG).show();
            // METEMOS DE ENTRADA UNOS REGISTROS PARA PROBAR//
            Periodico p = new Periodico("Marca", "Deportes", 2);
            baseDeDatos.insertarRegistro(p);
            p = new Periodico("HOLA", "Moda", 4);
            baseDeDatos.insertarRegistro(p);
            p = new Periodico("El PAIS", "Noticias", 3);
            baseDeDatos.insertarRegistro(p);
            arrayCosas = baseDeDatos.leerTodosLosRegistros();
        }


        // crear el adaptador con los datos BASICO
        // ArrayAdapter<String> adaptador = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, arrayCosas);
         adaptador = new MiAdaptador(arrayCosas);

        lista.setAdapter(adaptador);
        lista.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    // CONSTRUYE EL MENU; INFLANDOLO
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater bomba = getMenuInflater();
        bomba.inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // LISTENER DEL MENU; HA DE SER ESTE METODO


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        if(item.getItemId() == R.id.opAnadir){
            abrirOtraActividad();
        }
        if(item.getItemId() == R.id.opContactos){
            Toast.makeText(getApplicationContext(), "FUNCIONALIDAD NO ACTIVA AUN", Toast.LENGTH_LONG).show();

        }
        return super.onOptionsItemSelected(item);
    }


    public void abrirOtraActividad(){
        Intent intent = new Intent(MainActivity.this, AltaPeriodicoActvity.class);
        startActivity(intent);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        arrayCosas = baseDeDatos.leerTodosLosRegistros();
        adaptador.notifyDataSetChanged();
        adaptador = new MiAdaptador(arrayCosas);
        lista.setAdapter(adaptador);

    }
}