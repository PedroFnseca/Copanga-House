package com.example.copangahouse;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageButton imgQuarto = (ImageButton)findViewById(R.id.imgQuarto);
    ImageButton imgSala = (ImageButton)findViewById(R.id.imgSala);
    ImageButton imgCozinha = (ImageButton)findViewById(R.id.imgCozinha);
    ImageButton imgVaranda = (ImageButton)findViewById(R.id.imgVaranda);

    Button btnConectar = (Button)findViewById(R.id.btnConectar);

    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    int REQUEST_ENABLE_BT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(bluetoothAdapter == null){
            Toast.makeText(getApplicationContext(), "O seu dispositivo não possui bluetooth", Toast.LENGTH_LONG).show();
        } else if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }
}