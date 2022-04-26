using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System;
using UnityEngine.UI;

public class Moverball : MonoBehaviour
{
    public bool victoria;
    public controlVidas controlVidas;
    public GameController gameController;
    public GameObject bola;
    public GameObject spawnBola;
    public float speed = 100;

 


    void Start()
    {
       
        GetComponent<Rigidbody2D>().velocity = Vector2.down * speed * Time.deltaTime;
        

    }

    // Update is called once per frame
    void FixedUpdate()
    {
        
    }
    float reboteY(Vector2 bolaPos, Vector2 raquetaPos,
          float anchuraRaqueta)
    {
        // ||  1 <- parte superior de la raqueta 
        // || 
        // ||  0 <- parte media de la raqueta 
        // || 
        // || -1 <- parte inferior de la raqueta 
        return (bolaPos.x - raquetaPos.x) / anchuraRaqueta;

     
    }
    
    private void OnCollisionEnter2D(Collision2D collision)
    {
        float x = 0;
        if (collision.gameObject.name == ("RaquetaPong"))
        {

            x = reboteY(transform.position, collision.transform.position, collision.collider.bounds.size.x);
            //calculo la direccion, set lenght to 1
            Vector2 dir1 = new Vector2(x,1).normalized;

            GetComponent<Rigidbody2D>().velocity = dir1 * speed * Time.deltaTime;

           
        }
        //destruimos la bola cuando toca el muroinferior
        if (collision.gameObject.name == "Muroinf")
        {
            //Utilizamos una corutina para "parar" la ejecucion del script de la bola, hasta el proximo
            //choque con la raqueta. Esto genera que baje la velocidad de la bola al resetear
            if(controlVidas.vidaTotal <= 0)
            {

                victoria = false;
                gameController.mensajeDerrota = " HAS PERDIDO";
                gameController.FinJuego(victoria);
                bola.SetActive(false);//en caso de que la bola no se destruya que se oculte
                Destroy(this.gameObject);
                
            }
            else
           {
                StartCoroutine(SiguienteRonda());
           }
    
        }

    }
    //Corutina que instancia una bola nueva y destruye la anterior, 
     public IEnumerator SiguienteRonda()
    {
        yield return null;
         bola = (GameObject)Instantiate(bola,spawnBola.transform.position, Quaternion.identity);//instancia
       bola.SetActive(true);//activa la nueva bola para que sea visible
       Destroy(this.gameObject);// destruimos la bola
        gameController.FinRonda();// fin de la ronda
        }

   

}

