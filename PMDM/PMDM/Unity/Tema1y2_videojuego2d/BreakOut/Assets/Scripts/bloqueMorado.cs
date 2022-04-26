using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class bloqueMorado : MonoBehaviour
{
    private GameController gameController;
    private controlPuntuacion controlPuntuacion;
    public float dureza = 1;
    public int score = 50;
    //Variables sonido
    public AudioClip choque;
    public float volume = 1;
    private void Awake()
    {
        controlPuntuacion = GameObject.FindObjectOfType<controlPuntuacion>();
        gameController = GameObject.FindObjectOfType<GameController>();
    }


    private void OnCollisionEnter2D(Collision2D collision)
    {

        AudioSource.PlayClipAtPoint(choque, transform.position, volume);

        dureza = dureza - 1;
        if (dureza <= 0)
        {

            Destroy(gameObject);
           
        }


    }
    public void OnDestroy()
    {
        controlPuntuacion.actualizarPuntos(score);
        gameController.comprobarVictoria();
    }



}
