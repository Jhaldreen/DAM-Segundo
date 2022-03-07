package com.example.periodicos_final;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Titulares {

    String nombre;
    String periodico;
    Long id;
    public Titulares(long id, String periodico, String nombre) {
        this.nombre = nombre;
        this.periodico = periodico;
        this.id=id;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPeriodico() {
        return periodico;
    }

    public void setPeriodico(String periodico) {
        this.periodico = periodico;
    }


}
