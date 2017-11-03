package com.example.abdulrahman.lcation;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView =(TextView) findViewById(R.id.aaa);
    }
final int q=123;
    public void Loca(View view) {
        chk();
    }

    public void chk(){
        if (Build.VERSION.SDK_INT>=23){
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
                    requestPermissions(new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION
                    },q);
                }
            }
            return;
        }
        locd();
    }

    public void locd(){
        LocationManager locationManager=(LocationManager) getSystemService(LOCATION_SERVICE);
        Location location=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        textView.setText(location.getLongitude()+"");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case q:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
               locd();
                } else {
                    Toast.makeText(this,"not allowed ",Toast.LENGTH_LONG).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
