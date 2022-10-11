package com.example.copangahouse;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageButton imgQuarto, imgSala, imgCozinha, imgVaranda;

    Button btnConectar;

    BluetoothAdapter bluetoothAdapter = null;
    private static final int REQUEST_ENABLE_BT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgQuarto = (ImageButton)findViewById(R.id.imgQuarto);
        imgSala = (ImageButton)findViewById(R.id.imgSala);
        imgCozinha = (ImageButton)findViewById(R.id.imgCozinha);
        imgVaranda = (ImageButton)findViewById(R.id.imgVaranda);

        btnConectar = (Button)findViewById(R.id.btnConectar);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(bluetoothAdapter == null){
            Toast.makeText(getApplicationContext(), "O seu dispositivo não possui bluetooth", Toast.LENGTH_LONG).show();
        } else if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }

//    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_ENABLE_BT:
                if (resultCode == Activity.RESULT_OK) {
                    Toast.makeText(getApplicationContext(), "O bluetooth foi ativado!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "O bluetooth não foi ativado e o app foi encerrado!", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
        }
    }
}