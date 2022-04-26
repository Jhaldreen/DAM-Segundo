using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
public class controlVidas : MonoBehaviour
{
    public Text textoVida;
    public int vidaTotal = 3;

    public GameController gameController;
    public void Start()
    {
        textoVida.text = vidaTotal.ToString();
       
    }
    public void ActualizarVidas()
    {
        vidaTotal--;
        textoVida.text = vidaTotal.ToString();// cambiar de int a string
        //comprobamos vidas restantes

     

    }


}
