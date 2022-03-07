package com.example.pmdm06;

import androidx.appcompat.app.AppCompatActivity;
import androidx.vectordrawable.graphics.drawable.AnimationUtilsCompat;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class PantallaJuego extends AppCompatActivity{
    //variables para los componentes de la vista
    ImageButton imb00, imb01, imb02, imb03, imb04, imb05, imb06, imb07, imb08, imb09, imb10, imb11;
    ImageButton[] tablero = new ImageButton[12];//importante contar bien las variables
    TextView textoIntentos;
    int numIntentos;
    int aciertos;
    //imagenes
    int[] imagenes;
    int fondo;
    int[] sonido;

    //variables del juego
    ArrayList<Integer> arrayDesordenado;
    ImageButton primero;
    int numeroPrimero;
    int numeroSegundo;
    boolean bloqueo = false;// para que no se desbloqueen mas cartas de 2
    final Handler handler = new Handler();
    //sonido
    MediaPlayer so =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_juego);

        init();

    }
    private void comprobar(int i,final ImageButton imb ){
       if (primero==null){//si primero no es null se cumple todo
                  primero =imb;
                     //cargo las imagenes
                  primero.setScaleType(ImageView.ScaleType.CENTER_CROP);
                  primero.setImageResource(imagenes[arrayDesordenado.get(i)]);
                  primero.setEnabled(false);//va quedar inhabilitado mostrando su imagen
                  numeroPrimero = arrayDesordenado.get(i);
                //Sonido
                  switch(numeroPrimero)
                  {
                      case 0:
                          so = MediaPlayer.create(this, R.raw.anec);
                          so.start();
                          break;
                      case 1:
                          so = MediaPlayer.create(this, R.raw.gat);
                          so.start();
                          break;
                      case 2:
                          so = MediaPlayer.create(this, R.raw.cavall);
                          so.start();
                          break;
                      case 3:
                          so = MediaPlayer.create(this, R.raw.gos);
                          so.start();
                          break;
                      case 4:
                          so = MediaPlayer.create(this, R.raw.porc);
                          so.start();
                          break;
                      case 5:
                          so = MediaPlayer.create(this, R.raw.vaca);
                          so.start();
                          break;

                  }

    }else{// cuando se pulsa otro boton comprueba y tendria que hacer lo siguiente
                bloqueo=true;//con dos cartas destapadas no puedo pulsar mas botones
                imb.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imb.setImageResource(imagenes[arrayDesordenado.get(i)]);
                imb.setEnabled(false);
                numeroSegundo = arrayDesordenado.get(i);

           //Sonido
           switch(numeroSegundo)


           {
               case 0:
                   so = MediaPlayer.create(this, R.raw.anec);
                   so.start();
                   break;
               case 1:
                   so = MediaPlayer.create(this, R.raw.gat);
                   so.start();
                   break;
               case 2:
                   so = MediaPlayer.create(this, R.raw.cavall);
                   so.start();
                   break;
               case 3:
                   so = MediaPlayer.create(this, R.raw.gos);
                   so.start();
                   break;
               case 4:
                   so = MediaPlayer.create(this, R.raw.porc);
                   so.start();
                   break;
               case 5:
                   so = MediaPlayer.create(this, R.raw.vaca);
                   so.start();
                   break;

           }

        if(numeroPrimero==numeroSegundo){
                primero=null;
                bloqueo = false;
                numIntentos++;
                aciertos++;
        if(aciertos== imagenes.length){

                Toast toast = Toast.makeText(getApplicationContext(),"Numero de intentos" +
                        " "+numIntentos, Toast.LENGTH_LONG);
                toast.show();
                 back();
        }
        }else{
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    primero.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    primero.setImageResource(fondo);
                    primero.setEnabled(true);//va quedar inhabilitado mostrando su imagen
                    imb.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    imb.setImageResource(fondo);
                    imb.setEnabled(true);
                    bloqueo = false;
                    primero=null;
                    numIntentos++;
                    textoIntentos.setText("Numero de intentos :" +numIntentos);
                }
            },1000);
        }
        /**   //Para rotar la imagen pero no soy capaz
           // debemos crear primero un conjunto de animaciones, pues se pueden aplicar varias animaciones a la vez
           AnimationSet conjuntoAnimaciones = new AnimationSet(false);
           //indicar desde que grado y hasta ue grado giramos; giar alrededor de si mismo, grosor  del giro y si es giar alrededor de si mismo,
           RotateAnimation rotateAnim = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
           // añadimos el efectogiro al conjunto de animaciones
           conjuntoAnimaciones.addAnimation(rotateAnim);
           // cuanto van a durar las animaciones ??? ( 500 milisegundos)
           conjuntoAnimaciones.setDuration(500);
           // indicamos que la imagen de la carta actual tenga esa animacion


                   // arranco la animacion !!
           conjuntoAnimaciones.start();*/

        }

    }

    private void cargarTablero() {
        imb00 = findViewById(R.id.boton00);
        imb01 = findViewById(R.id.boton01);
        imb02 = findViewById(R.id.boton02);
        imb03 = findViewById(R.id.boton03);
        imb04 = findViewById(R.id.boton04);
        imb05 = findViewById(R.id.boton05);
        imb06 = findViewById(R.id.boton06);
        imb07 = findViewById(R.id.boton07);
        imb08 = findViewById(R.id.boton08);
        imb09 = findViewById(R.id.boton09);
        imb10 = findViewById(R.id.boton10);
        imb11 = findViewById(R.id.boton11);


        tablero[0] = imb00;
        tablero[1] = imb01;
        tablero[2] = imb02;
        tablero[3] = imb03;
        tablero[4] = imb04;
        tablero[5] = imb05;
        tablero[6] = imb06;
        tablero[7] = imb07;
        tablero[8] = imb08;
        tablero[9] = imb09;
        tablero[10] = imb10;
        tablero[11] = imb11;

    }

    private void init() {
        cargarTablero();
        cargarTexto();
        cargarImagenes();

        arrayDesordenado = barajar(imagenes.length);
        for (int i =0; i<tablero.length; i++){
            tablero[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
            tablero[i].setImageResource(imagenes[arrayDesordenado.get(i)]);

        }

        for (int i =0; i<tablero.length; i++){
            tablero[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
            tablero[i].setImageResource(fondo);
        }
      /**
       * Esto es para que se vean 1 segundo antes de que se tapen, pero no lo pide el ejercicio
       *es para ver que he enetendido vien el handler para los segundos
       *
       * handler.postDelayed(new Runnable() {
           @Override
           public void run() {
               for (int i =0; i<tablero.length; i++){
                   tablero[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
                   tablero[i].setImageResource(fondo);
               }
           }
       }, 1000);*/

         for(int i=0; i< tablero.length; i++){
            final int j = i;
            tablero[i].setEnabled(true);
            tablero[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                   if(bloqueo!=true)
                        comprobar(j, tablero[j]);
                      // System.out.println("esto funciona");


                }
            });

        }

    }

    private void cargarTexto() {
        textoIntentos = findViewById(R.id.txtIntentos);
        numIntentos = 0;
        textoIntentos.setText("Numero de Intentos: " + numIntentos);
    }


    private void cargarImagenes() {
        imagenes = new int[]{
                R.drawable.anec,
                R.drawable.cat,
                R.drawable.cavall,
                R.drawable.gos,
                R.drawable.porc,
                R.drawable.vaca,

        };
        fondo = R.drawable.fondo1;

    }

    private ArrayList<Integer> barajar(int longitud) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < longitud * 2; i++) {
            result.add(i % longitud);
        }
        Collections.shuffle(result);
       //System.out.println(Arrays.toString(result.toArray()));
        return result;
    }
    public void back () {
        Intent siguiente = new Intent(this,MainActivity.class);
        startActivity(siguiente);
    }
   private void sonido(){

   }

}