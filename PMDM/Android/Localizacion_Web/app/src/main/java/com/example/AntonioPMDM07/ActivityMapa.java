package com.example.AntonioPMDM07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.example.pmdm07.R;

import java.util.List;

public class ActivityMapa extends AppCompatActivity implements LocationListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
            //tiempo de ejecución ACCESS_FINE_LOCATION
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            LocationManager gestorLoc = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            gestorLoc.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);

        }else{
            Toast.makeText(this, "NO HAY PERMISOS !!!!" , Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        String text = "Posicion actual:\n" +
                "Latitud = " + location.getLatitude() + "\n" +
                "Longitud = " + location.getLongitude();

        Toast.makeText(getApplicationContext(), text,   Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLocationChanged(@NonNull List<Location> locations) {

    }

    @Override
    public void onFlushComplete(int requestCode) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }
}