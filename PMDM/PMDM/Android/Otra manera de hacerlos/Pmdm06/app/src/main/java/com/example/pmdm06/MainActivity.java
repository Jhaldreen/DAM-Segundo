package com.example.pmdm06;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int veceselegido;
    Carta primeraelegida, segundaelegida;
    ImageView iv1, iv2, iv3, iv4, iv5, iv6, iv7, iv8, iv9, iv10, iv11, iv12;
    int intentos = 0;
    int aciertos = 0;
    ArrayList<Carta> cartas = new ArrayList<>();
    public MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv1 = findViewById(R.id.iv1);
        iv2 = findViewById(R.id.iv2);
        iv3 = findViewById(R.id.iv3);
        iv4 = findViewById(R.id.iv4);
        iv5 = findViewById(R.id.iv5);
        iv6 = findViewById(R.id.iv6);
        iv7 = findViewById(R.id.iv7);
        iv8 = findViewById(R.id.iv8);
        iv9 = findViewById(R.id.iv9);
        iv10 = findViewById(R.id.iv10);
        iv11 = findViewById(R.id.iv11);
        iv12 = findViewById(R.id.iv12);
        iniciar();

        mainActivity = this;
    }

    public void iniciar() {
        Carta objetoCarta;
        Bitmap bmp;
        cartas.clear();

        // BitMapFactory es una fabrica de crear BMP. Coge el recurso (getResources()) llamado R.Drawable.anec
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.anec);  // creamos un BMP (BitMap, una imagen )
        // construimos un objeto Carta con el BMP, el tipo (pato) y un int (R.raw.anec) que es el recurso del sonido del pato
        objetoCarta = new Carta(bmp, 1, 1, R.raw.anec);
        // el objeto carta creado va al array de cartas
        cartas.add(objetoCarta);

        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.anec);
        objetoCarta = new Carta(bmp, 2, 1, R.raw.anec);
        cartas.add(objetoCarta);

        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.cat);
        objetoCarta = new Carta(bmp, 3, 2, R.raw.gat);
        cartas.add(objetoCarta);

        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.cat);
        objetoCarta = new Carta(bmp, 4, 2, R.raw.gat);
        cartas.add(objetoCarta);
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.cavall);
        objetoCarta = new Carta(bmp, 5, 3, R.raw.cavall);
        cartas.add(objetoCarta);
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.cavall);
        objetoCarta = new Carta(bmp, 6, 3, R.raw.cavall);
        cartas.add(objetoCarta);
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.porc);
        objetoCarta = new Carta(bmp, 7, 4, R.raw.porc);
        cartas.add(objetoCarta);
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.porc);
        objetoCarta = new Carta(bmp, 8, 4, R.raw.porc);
        cartas.add(objetoCarta);
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.vaca);
        objetoCarta = new Carta(bmp, 9, 5, R.raw.vaca);
        cartas.add(objetoCarta);
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.vaca);
        objetoCarta = new Carta(bmp, 10, 5, R.raw.vaca);
        cartas.add(objetoCarta);
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.gos);
        objetoCarta = new Carta(bmp, 11, 6, R.raw.gos);
        cartas.add(objetoCarta);
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.gos);
        objetoCarta = new Carta(bmp, 12, 6, R.raw.gos);
        cartas.add(objetoCarta);

        desordenar(cartas);

        // guardamos en cada carta el ImageView donde se va a pintar
        cartas.get(0).setImageViewDondeMeVoyAPintar(iv1);
        cartas.get(1).setImageViewDondeMeVoyAPintar(iv2);
        cartas.get(2).setImageViewDondeMeVoyAPintar(iv3);
        cartas.get(3).setImageViewDondeMeVoyAPintar(iv4);
        cartas.get(4).setImageViewDondeMeVoyAPintar(iv5);
        cartas.get(5).setImageViewDondeMeVoyAPintar(iv6);
        cartas.get(6).setImageViewDondeMeVoyAPintar(iv7);
        cartas.get(7).setImageViewDondeMeVoyAPintar(iv8);
        cartas.get(8).setImageViewDondeMeVoyAPintar(iv9);
        cartas.get(9).setImageViewDondeMeVoyAPintar(iv10);
        cartas.get(10).setImageViewDondeMeVoyAPintar(iv11);
        cartas.get(11).setImageViewDondeMeVoyAPintar(iv12);

        Bitmap bmpcartaabajo = BitmapFactory.decodeResource(getResources(), R.drawable.bocaabajo);

        // recorremos todas las cartas
        for (int i = 0; i < cartas.size(); i++) {


            cartas.get(i).getImageViewDondeMeVoyAPintar().setEnabled(true);

            final int finalI = i;
            cartas.get(i).getImageViewDondeMeVoyAPintar().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    pinchado(finalI);
                }
            });


            cartas.get(i).getImageViewDondeMeVoyAPintar().setImageBitmap(bmpcartaabajo);
        }

        intentos = 0;
        aciertos = 0;
        veceselegido = 0;

    }

    public void pinchado(int i) {

        Carta cartaPinchada = cartas.get(i);
        cartaPinchada.getImageViewDondeMeVoyAPintar().setImageBitmap(cartaPinchada.getImg());
        veceselegido++;

        if (veceselegido == 1) {
            primeraelegida = cartaPinchada;
        } else {
            segundaelegida = cartaPinchada;

            if(primeraelegida.getId()==segundaelegida.getId()){
                Snackbar.make(iv1, "No elijas dos veces la misma carta!", Snackbar.LENGTH_LONG).show();

                return;
            }
            veceselegido = 0;
            if (primeraelegida.getTipo() == segundaelegida.getTipo()) {
                Snackbar.make(iv1, "ACERTASTE !!!", Snackbar.LENGTH_LONG).show();
                aciertos++;
                primeraelegida.getImageViewDondeMeVoyAPintar().setEnabled(false);
                segundaelegida.getImageViewDondeMeVoyAPintar().setEnabled(false);
            } else {
                // cogemos la imagen de la carta boca abajo
                Bitmap bmporiginal = BitmapFactory.decodeResource(getResources(), R.drawable.bocaabajo);

                int tiempoEspera = 1000;
                // un handler es un hilo que se puede ejecutar... pasado un cierto tiempo
                Handler handler = new Handler();  // hilo que se ejecuta en segundo plano...... y se ejecuta pasado un tiempo
                // postDelayed hace que el hilo que viene dentro se ejecute dentro de x milisegundos (tiempoespera)
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        primeraelegida.getImageViewDondeMeVoyAPintar().setImageBitmap(bmporiginal);
                        segundaelegida.getImageViewDondeMeVoyAPintar().setImageBitmap(bmporiginal);
                    }
                }, tiempoEspera);

            }
        }

        intentos++;

        // MediaPlayer es una herramienta para hacer musica y video
        MediaPlayer sonido = MediaPlayer.create(this, cartaPinchada.getSonido());
        sonido.start();

        // debemos crear primero un conjunto de animaciones, pues se pueden aplicar varias animaciones a la vez
        AnimationSet conjuntoAnimaciones = new AnimationSet(false);
        // aqui vamos a cifrar un efecto de giro, una rotacion, con la clase RotationAnimation.
        // hay que indicar: desde que grado y hasta ue grado giramos; gira alrededor de si mismo, grosor  del giro y si es girar alrededor de si mismo,
        RotateAnimation rotateAnim = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        // añadimos el efectogiro al conjunto de animaciones
        conjuntoAnimaciones.addAnimation(rotateAnim);
        // cuanto van a durar las animaciones  ( 500 milisegundos)
        conjuntoAnimaciones.setDuration(500);
        // indicamos que la imagen de la carta actual tenga esa animacion
        cartaPinchada.getImageViewDondeMeVoyAPintar().setAnimation(conjuntoAnimaciones);
        // arranco la animacion
        conjuntoAnimaciones.start();

        if (aciertos == 6) {
            finpartida();
        }

    }

    public void finpartida() {

        // pintamos la ventana de preguntar si quiere volver a jugar.... y se hace antes de la de los records por que .....
        // la de los records mostrara encima de esta
        AlertDialog.Builder ventana = new AlertDialog.Builder(this);
        ventana.setMessage("¿Volver a jugar?")
                .setCancelable(false)
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        iniciar();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
        AlertDialog alerta = ventana.create();
        alerta.show();

        // pintamos ahora encima de la ventana anterior la de los records
        int recordactual = leerPreferencias();
        if (recordactual > intentos) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("NUEVO RECORD !!!!!!   \nAntiguo record : " + recordactual + "\nNuevo record : " + intentos)
                    .setCancelable(false)
                    .setPositiveButton("CONTINUAR", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();

            guardarPreferencia(intentos); // guardamos en preferencias el nuevo record

        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Puntuacion : " + intentos + "\nNo alcanza al record vigente : " + recordactual)
                    .setCancelable(false)
                    .setPositiveButton("CONTINUAR", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }



    }


    public void desordenar(ArrayList<Carta> lista) {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int pos1 = random.nextInt(lista.size());
            int pos2 = random.nextInt(lista.size());
            // intercambia en una lista los dos valores que se indican en las dos posiciones
            Collections.swap(lista, pos1, pos2);
        }
    }


    public int leerPreferencias() { //metodo que devuelve las puntuaciones
        SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        // Si no existe el valor, se devuelve el segundo parametro
        int numero = prefs.getInt("record", 1000);
        return numero;
    }

    public void guardarPreferencia(int intentos) { //metodo para guardar las puntuaciones
        SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        // GUARDAR o AÑADIR NUEVOS valores a preferencias
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("record", intentos);
        editor.commit();
    }



}