package com.mahesh.locationmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView location_tv;

    LocationManager mLocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        location_tv = findViewById(R.id.textView);

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    public void getLocation(View view) {
        Location location = getLastBestLocation();
        if (location != null) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            location_tv.setText("" + latitude + " , " + longitude);
        }
    }

    private Location getLastBestLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "allow permisions....", Toast.LENGTH_SHORT).show();
            return null;
        }
        Location locationGPS = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Location locationNET = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        Toast.makeText(this, "gxcvxcvv", Toast.LENGTH_SHORT).show();

        long gpsLocationTime = 0;
        long netLocationTime = 0;

        if(locationGPS!=null){
            gpsLocationTime = locationGPS.getTime();
            Toast.makeText(this, "gps"+gpsLocationTime, Toast.LENGTH_SHORT).show();
        }
        if(locationNET != null){
            netLocationTime = locationNET.getTime();
            Toast.makeText(this, "net"+netLocationTime, Toast.LENGTH_SHORT).show();
        }

        if(gpsLocationTime-netLocationTime>0){
            return locationGPS;
        }else{
            return locationNET;
        }
    }

}