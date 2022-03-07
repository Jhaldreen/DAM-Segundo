package com.example.AntonioPMDM07;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pmdm07.R;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    Button cargar,mapas;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cargar = findViewById(R.id.buttonCargar);
        mapas = findViewById(R.id.buttonMapas);
        editText = (EditText) findViewById(R.id.editText);
        IntentFilter filter = new IntentFilter
                (ConnectivityManager.CONNECTIVITY_ACTION);

        //cargamos la imagen en segundo plano
        ImageView iv = (ImageView) findViewById(R.id.imgenBack);
        LoadImage imagenT = new LoadImage(iv);
        imagenT.execute("https://bit.ly/2uUlAzw");//imagen a cargar


        /******************************* Paso a la actividad ActivityWeb ***********************************/
        cargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextActivity2();
            }
        });
        /*********************************  PASO A LA ACTIVIDAD ActivityMaps  ***********************************/
        mapas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextActivity3();
            }
        });
    }
    /******************************cargamos la web desde el intent pasando un edittext *********************************/
    public void nextActivity2 () {
        Intent siguiente = new Intent(this, ActivityWeb.class);
        String message = editText.getText().toString();
        siguiente.putExtra("passUrl", message);
        startActivity(siguiente);
    }
    /******************************************************************************************/
    public void nextActivity3 () {
        Intent siguiente = new Intent(this, ActivityMaps.class);
        startActivity(siguiente);
    }
    /******************************************************************************************/
    public class LoadImage  extends AsyncTask<String, Void, Bitmap> {
        ImageView iv;
        public LoadImage(ImageView i){
            iv = i;
        }
        @Override
        protected Bitmap doInBackground(String... param) {
            Bitmap bitmap = null;
            try {
                // Descargamos la imagen desde una URL según los apuntes
                InputStream input = new java.net.URL(param[0]).openStream();
                // Decodificamos el Bitmap
                bitmap = BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }
        public void onPostExecute(Bitmap result) {
        /*
         Recibimos el resultado de doInBackground (el Bitmap)
         y lo usamos para cargar la imagen a la UI
        */
            iv.setImageBitmap(result);
        }
    }
}