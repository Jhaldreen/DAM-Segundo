using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class cubo : MonoBehaviour
{
   
    private controlPuntuacion controlPuntuacion;
    public float dureza;
    public int score = 1;
    private void Awake()
    {
       // controlPuntuacion = GameObject.FindObjectOfType<controlPuntuacion>();
        GameObject puntuacion = GameObject.Find("controlPuntuacion");// encuentra  el game object controlPuntuacion
        controlPuntuacion = puntuacion.GetComponent < controlPuntuacion > ();
    }
    private void OnCollisionEnter(Collision collision)
    {
        dureza = dureza - 1;
        if (dureza <= 0)
        {
            Destroy(gameObject);

        }

    }
    public void OnDestroy()
    {
        controlPuntuacion.actualizarPuntos(score);
        controlPuntuacion.comprobarVictoria();
    }

}
