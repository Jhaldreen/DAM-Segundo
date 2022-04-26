package com.example.ejemplo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class BaseDeDatos extends SQLiteOpenHelper {


    // Objeto de la bbdd que se usa en todos los metodos de esta clase
    SQLiteDatabase objetoBBDD;

    // Constructor, que no se toca
    public BaseDeDatos(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
        objetoBBDD = getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // tipico de crear las tablas iniciales de la bbdd

        String sql = "CREATE TABLE periodicos ( nombre TEXT primary key, tipo TEXT, precio INTEGER )";
        db.execSQL(sql);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // aqui se viene automaticamente si se cambia la version de la bbdd
    }


    public void insertarRegistro(Periodico pp) {

        String sql = "INSERT INTO periodicos (nombre, tipo, precio) VALUES (?,?,?)";

        Object[] parametros = new Object[3];
        parametros[0] = pp.getNombre();
        parametros[1] = pp.getTipo();
        parametros[2] = pp.getPrecio();

        objetoBBDD.execSQL(sql, parametros);

    }

    public ArrayList<Periodico> leerTodosLosRegistros() {
        ArrayList<Periodico> lista = new ArrayList<>();

        String sql = "SELECT nombre, tipo, precio FROM periodicos";
        Cursor c = objetoBBDD.rawQuery(sql, null);

        while (c.moveToNext()) {
            String nombre = c.getString(0);
            String tipo = c.getString(1);
            int precio = c.getInt(2);
            Periodico p = new Periodico(nombre, tipo, precio);
            lista.add(p);
        }

        return lista;
    }


}
