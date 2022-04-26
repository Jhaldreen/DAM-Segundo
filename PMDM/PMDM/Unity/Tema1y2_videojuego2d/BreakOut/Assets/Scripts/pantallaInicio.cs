using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class pantallaInicio : MonoBehaviour
{
    public GameObject botonBorrar;
    public GameObject textos;
    public Text textoBienvenida;
    public Text nombreJugador;
    
    // Start is called before the first frame update
    void Start()
    {
        if(PlayerPrefs.GetString("Jugador")!= null)
        {
            textos.SetActive(true);
            botonBorrar.SetActive(true);
            nombreJugador.text = PlayerPrefs.GetString("jugador");

        }
        else
        {
            textos.SetActive(false);
            botonBorrar.SetActive(false);
        }
        
    }

    public void borrarPartida()
    {
        PlayerPrefs.DeleteAll();
        textos.SetActive(false);
    }
}
