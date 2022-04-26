using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class GameController : MonoBehaviour
{
    
    // Referenciar  clase 
    public controlVidas controlVidas;
    public controlPuntuacion controlPuntuacion;
    public loadScene loadScene;

    public GameObject interfazJuego;
    public GameObject interfazPuntos;
    public GameObject interfazGameOver;
    public GameObject nombreJugador;
    //texto
    public Text mensajeFinal;
    public Text puntuacionFinal;
    public InputField jugador;
    public Text jugadorDisplay;
    //boolean victoria
    public bool victoria;
    // apoyo
    public string mensajeDerrota;
    public string mensajeVictoria;

     public void Start()
    {
        
        
            jugadorDisplay.text = PlayerPrefs.GetString("jugador");

        
       
    }
    public void FinRonda()
    {

        controlVidas.ActualizarVidas();//se restan las vidas

    }

    public void FinJuego(bool win)// metodo para ense�ar los mensajes finales ganador o perdedor
    {
        interfazJuego.SetActive(false);
        interfazPuntos.SetActive(false);
        
        //mostrammos la puntuacion final
        puntuacionFinal.text = controlPuntuacion.puntuacionTotal.ToString();
        if (win == true)
        {
            nombreJugador.SetActive(true);   
            mensajeFinal.text = mensajeVictoria;

        }
        else
        {
            nombreJugador.SetActive(false);
          
            mensajeFinal.text = mensajeDerrota;
            
        }
        interfazGameOver.SetActive(true);//activo el canvas de game over
    }
    //compruebo la victoria 
    public void comprobarVictoria()
    {
        //variables para detectar los bloques
        GameObject existenVerdes = GameObject.Find("BloqueV1");
        GameObject existenAzules = GameObject.Find("BloqueA1");
        GameObject existenMorados = GameObject.Find("BloqueM1");
        // si se cumple la condicion de que no hay bloques se gana
       if (existenVerdes == null && existenAzules == null && existenMorados == null)
        {
            victoria = true;
            mensajeVictoria = " HAS GANADO";
            FinJuego(victoria);
        }


    }
    public void nuevaPartida()
    {
        PlayerPrefs.SetString("jugador", jugador.text);
        loadScene.cambioEscena("Inicio");

    }
    public void salir()
    { 
        UnityEditor.EditorApplication.isPlaying = false;// la funcion "QUIT" solo funciona con un proyecto buildeado, esta l�nea haece que se pare el juego
        Application.Quit();
    }


}

