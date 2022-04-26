package com.example.pmdm06;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class Carta {
    private int id;
    private ImageView imageViewDondeMeVoyAPintar;
    private Bitmap img;
    private int tipo;
    private int sonido;

    //public Carta(Bitmap img, int id, ImageView ivDondeEstoy, int tipo) {
    public Carta(Bitmap img, int id, int tipo, int sonido) {
        this.id = id;
        this.img = img;
        this.tipo = tipo;
        this.sonido = sonido;
    }


    public Bitmap getImg() {
        return img;
    }

    public int getSonido() {
        return sonido;
    }

    public int getTipo() {
        return tipo;
    }


    public ImageView getImageViewDondeMeVoyAPintar() {
        return imageViewDondeMeVoyAPintar;
    }

    public void setImageViewDondeMeVoyAPintar(ImageView imageViewDondeMeVoyAPintar) {
        this.imageViewDondeMeVoyAPintar = imageViewDondeMeVoyAPintar;
    }

    public int getId() {
        return id;
    }
}