package com.example.pmdm06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button play;
    TextView aciertos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play = findViewById(R.id.botonIniciar);


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(),"Empieza la partida", Toast.LENGTH_SHORT);
                toast.show();
                next();
            }
        });
    }
    public void next () {
        Intent siguiente = new Intent(this,PantallaJuego.class);
        startActivity(siguiente);
    }
}