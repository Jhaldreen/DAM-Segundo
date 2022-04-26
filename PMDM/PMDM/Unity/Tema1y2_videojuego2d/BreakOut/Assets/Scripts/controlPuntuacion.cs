using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
public class controlPuntuacion : MonoBehaviour
{
    public Text puntos;
    public int puntuacionTotal = 0;

    public void actualizarPuntos(int score)
    {
        puntuacionTotal += score;
        puntos.text = puntuacionTotal.ToString();//paso de int a string para que me lo lea


    }
   
}
