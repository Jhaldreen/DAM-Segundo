package com.example.periodicos_final;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_anadir extends AppCompatActivity {

    EditText nombre;
    EditText seccion;
    RadioButton deportes;
    RadioButton politica;
    RadioButton generalista;
    DBInterface dbInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir);

        dbInterface = new DBInterface(this);
        dbInterface.abre();
    //buscamos los id de cada texto y cada radio button
        nombre = findViewById(R.id.txtNombre);
        seccion = findViewById(R.id.txtSeccion);
        deportes = findViewById(R.id.deportesButton);
        politica = findViewById(R.id.politicaButon);
        generalista = findViewById(R.id.generalistaButon);
        Button anadir = findViewById(R.id.btnAgregar);
        anadir.setOnClickListener(new View.OnClickListener() {
            //en el metodo onclick agregamos al boton la accion que queremos que haga insertar los datos
            @Override
            public void onClick(View v) {
                if (nombre.getText().toString().equals("")||
                        seccion.getText().toString().equals("")  ){
                    Toast.makeText(getBaseContext(),
                            "Los dos campos son obligatorios",
                            Toast.LENGTH_LONG).show();
                }else{
                    if (dbInterface.insertarContacto(nombre.getText().toString(),
                            seccion.getText().toString())==-1){
                        Toast.makeText(getBaseContext(),
                                "Error en la inserción",
                                Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getBaseContext(),
                                "Usuario insertado",
                                Toast.LENGTH_LONG).show();
                    }
                    finish();
                }

            }
        });

    }

    @Override
    protected void onStop(){
        super.onStop();
        dbInterface.cierra();

    }
/** He intentando de muchas maneras pero no encuentro con la solucion al radio button
 *
 *
    public void guardar (){
        Long codId= null;
        String nomb = nombre.getText().toString();
        String tipo = "";
        if(politica.isChecked()) {
            tipo="Politica";
        }else if (deportes.isChecked()){
            tipo="Deportes";
        }else {
            tipo="Generalista";
        }

    }
*/
}
