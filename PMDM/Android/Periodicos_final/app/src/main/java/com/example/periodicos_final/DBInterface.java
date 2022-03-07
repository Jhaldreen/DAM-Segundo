package com.example.periodicos_final;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Roberto on 21/11/2015.
 */
public class DBInterface {
    // Constantes
    public static final String CAMPO_ID = "_id";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_SECCION = "seccion";
    public static final String TAG = "DBInterface";

    public static final String BD_NOMBRE = "BDContactos";
    public static final String BD_TABLA = "contactos";
    public static final int VERSION = 2;

    public static final String BD_CREATE =
            "create table " + BD_TABLA + "(" + CAMPO_ID +
                    " integer primary key autoincrement, "+
                    CAMPO_NOMBRE + " text not null," +
                    CAMPO_SECCION + " text not null); ";
    
    private final Context contexto;
    private AyudaDB ayuda;
    private SQLiteDatabase bd;

    public DBInterface(Context con)
    {
        this.contexto = con;
        Log.w(TAG, "creando ayuda" );
        ayuda = new AyudaDB(contexto);
    }

    public DBInterface abre () throws SQLException {
        Log.w(TAG, "abrimos base de datos" );
        bd = ayuda.getWritableDatabase();
        return this;
    }

    // Cierra la BD
    public void cierra () {
        ayuda.close();
    }

    public long insertarContacto(String nombre, String seccion)
    {
        ContentValues initialValues = new ContentValues ();
        initialValues.put(CAMPO_NOMBRE, nombre);
        initialValues.put(CAMPO_SECCION, seccion);
        return bd.insert(BD_TABLA, null,
                initialValues);
    }

    // Devuelve todos los Contactos
    public Cursor obtenerContactos(){
        return bd.query(BD_TABLA, new String []
                        { CAMPO_ID,CAMPO_NOMBRE, CAMPO_SECCION},
                null,null, null, null,
                null);
    }

    public long modificaContacto(long id,String nombre, String periodico)
    {
        ContentValues newValues = new ContentValues ();
        newValues.put(CAMPO_NOMBRE, nombre);
        newValues.put(CAMPO_SECCION, periodico);
        return bd.update(BD_TABLA, newValues, CAMPO_ID + "=" + id, null);
    }

    public long borrarContacto(long id)
    {

        return bd.delete(BD_TABLA, CAMPO_ID + "=" + id, null);
    }

    public class AyudaDB extends SQLiteOpenHelper {

        public AyudaDB(Context con){
            super (con, BD_NOMBRE, null, VERSION);
            Log.w(TAG, "constructor de ayuda");
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                Log.w(TAG, "creando la base de datos "+BD_CREATE );
                db.execSQL(BD_CREATE);
            } catch (SQLException e) {
                e.printStackTrace ();
            }
        }
      @Override
        public void onUpgrade (SQLiteDatabase db,
                               int VersionAntigua, int VersionNueva) {
            Log.w(TAG, "Actualizando Base de datos de la versión" +
                    VersionAntigua + "A" + VersionNueva + ". Destruirá todos los datos");
            db.execSQL("DROP TABLE IF EXISTS " + BD_TABLA);
            onCreate(db);
        }
    }

}
