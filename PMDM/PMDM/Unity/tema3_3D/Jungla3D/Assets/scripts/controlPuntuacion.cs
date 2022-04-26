using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;
public class controlPuntuacion : MonoBehaviour
{

    public Text puntos;
    public Text mensajeFinal;
    public string mensajeVictoria;
    public int puntuacionTotal = 4;
    public GameObject finJuego;
    public bool vistoria;

   private void Start()
    {
        puntos.text = puntuacionTotal.ToString();
    }
    public void actualizarPuntos(int score )
    {
        puntuacionTotal -= score;
        puntos.text = puntuacionTotal.ToString();//paso de int a string para que me lo lea

    }
    public void Fin(bool win)
    {
        finJuego.SetActive(false);

        if (win == true)
        {
            mensajeFinal.text = mensajeVictoria;
        }
    }
    public void comprobarVictoria()
    {
        //variables para detectar los bloques
        GameObject existenCubo = GameObject.Find("Cube");
       
        // si se cumple la condicion de que no hay bloques se gana
        if (existenCubo == null)

        {
            vistoria = true;
             mensajeVictoria = " Has Ganado ";
            finJuego.SetActive(true);

        }
    }
    public void ReiniciarPartida()
    {
        SceneManager.LoadScene(SceneManager.GetActiveScene().name);
    }
    public void salir()
    {
        UnityEditor.EditorApplication.isPlaying = false;// la funcion "QUIT" solo funciona con un proyecto buildeado, esta l�nea haece que se pare el juego
        Application.Quit();
    }


}
