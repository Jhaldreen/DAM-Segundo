using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MoverRaqueta : MonoBehaviour{
    public float speed = 90;
    public AudioClip choque;
    public float volume = 1;

    void FixedUpdate()
    {
        float h = Input.GetAxisRaw("Horizontal");
        // que se mueve siemrpe a la misma velocidad
        GetComponent<Rigidbody2D>().velocity = new Vector2(h,0)*speed* Time.deltaTime;
        
    }

    
    private void OnCollisionEnter2D(Collision2D collision)
    {
        
        AudioSource.PlayClipAtPoint(choque, transform.position, volume);
    }
}
