using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class muros : MonoBehaviour
{
    public AudioClip choque;
    public float volume = 1;
    
    private void OnCollisionEnter2D(Collision2D collision)
    {
    AudioSource.PlayClipAtPoint(choque, transform.position, volume);

    }

}
