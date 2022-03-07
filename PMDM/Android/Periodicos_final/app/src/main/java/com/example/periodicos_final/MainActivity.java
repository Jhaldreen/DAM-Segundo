package com.example.periodicos_final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
   // List<Titulares> datosTitular = new ArrayList<Titulares>();
   ArrayList<Titulares> titulares;
    AdaptadorTitulares adaptador_contactos;
    DBInterface dbInterface;
    int position ; //posicion clickada en el recyclerview para menu contextual

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Para añadir la toolbar, no se por qeu el cambio de nombre me da fallo
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
     /**
      * Esto lo lo tengo para hacer mis pruebas con el recucler view
      *
      *  AdaptadorTitulares miAdapt = new AdaptadorTitulares(datosTitular);
        RecyclerView miR = findViewById(R.id.recycleTitulares);
        miR.setAdapter(miAdapt);
        miR.setLayoutManager(new LinearLayoutManager(this));

        datosTitular.add(new Titulares("Marca","Deportes"));
        datosTitular.add(new Titulares("El Diario Montañes","Generalista"));

      */
        dbInterface = new DBInterface(this);
        dbInterface.abre();
        cargarContactos();

    }
    public void cargarContactos(){
        Cursor c= dbInterface.obtenerContactos();
        // Movemos el cursor en la primera posición
        if (c == null) {
            Toast.makeText(getBaseContext(), "Tabla vacía",
                    Toast.LENGTH_LONG).show();
        } else {

            titulares = new ArrayList<Titulares>();
            if (c.moveToFirst()) {
                do {
                    titulares.add(new Titulares(c.getLong(0),
                            c.getString(2),
                            c.getString(1)));
                    // Mientras podamos pasar al siguiente contacto
                } while (c.moveToNext());
            }
        }

        RecyclerView rv = findViewById(R.id.recycleTitulares);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adaptador_contactos =
                new AdaptadorTitulares(titulares);
        rv.setAdapter(adaptador_contactos);
        registerForContextMenu(rv);

        adaptador_contactos.setOnItemLongClickListener(new OnRecyclerViewLongItemClickListener() {
            @Override
            public void onItemLongClick(View view, int p) {
                position = p;
                openContextMenu(view);
            }
        });
    }

    @Override
    public void onResume() {//cada vez que se vuelve a la actividad se refresca el listview
        super.onResume();
        dbInterface.abre();
        cargarContactos();
        TextView txtUrl = findViewById(R.id.txtURL);
        SharedPreferences preferencias =
                PreferenceManager.getDefaultSharedPreferences((this));
        txtUrl.setText(preferencias.getString("url",""));
    }

    @Override
    public void onStop() {//cada vez que se vuelve a la actividad se refresca el listview
        super.onStop();
        dbInterface.cierra();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }
    public void Siguiente(MenuItem view) {
        Intent siguiente = new Intent(this, Activity_anadir.class);
        startActivity(siguiente);
    }

}