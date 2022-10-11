package com.example.copangahouse;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageButton imgQuarto, imgSala, imgCozinha, imgVaranda;

    Button btnConectar;

    BluetoothAdapter bluetoothAdapter = null;
    private static final int REQUEST_ENABLE_BT = 1;
    private static final int REQUEST_CONECTION_BT = 2;

    // Dispositivo selecionado
    private static String ENDERECO_MAC = null;
    private static String NOME_DISPOSITIVO = null;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgQuarto = (ImageButton)findViewById(R.id.imgQuarto);
        imgSala = (ImageButton)findViewById(R.id.imgSala);
        imgCozinha = (ImageButton)findViewById(R.id.imgCozinha);
        imgVaranda = (ImageButton)findViewById(R.id.imgVaranda);

        btnConectar = (Button)findViewById(R.id.btnConectar);

        boolean conexao = false;
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // Caso não tenha bluetooth
        if(bluetoothAdapter == null){
            Toast.makeText(getApplicationContext(), "O seu dispositivo não possui bluetooth", Toast.LENGTH_LONG).show();
        }
        // Caso o bluetooth esteja desligado
        else if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE); // Faz a tela padrão de permissão
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT); // Ativa o bluetoth
        }

        btnConectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(conexao){
                    // desconectar
                } else{
                    // conectar
                    Intent abreLista = new Intent(MainActivity.this, ListaDispositivos.class);
                    startActivityForResult(abreLista, REQUEST_CONECTION_BT);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case REQUEST_ENABLE_BT:
                // Caso a permissão seja aceita de ativação de bluetooth
                if (resultCode == Activity.RESULT_OK) {
                    Toast.makeText(getApplicationContext(), "O bluetooth foi ativado!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "O bluetooth não foi ativado e o app foi encerrado!", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
            case REQUEST_CONECTION_BT:
                if(resultCode == Activity.RESULT_OK){
                    // Coleta o MAC e dispositivo selecionado pelo usuário da outra activity
                    ENDERECO_MAC = data.getExtras().getString(ListaDispositivos.ENDERECO_MAC);
                    NOME_DISPOSITIVO = data.getExtras().getString(ListaDispositivos.NOME_DISPOSITIVO);
                    
                    Toast.makeText(getApplicationContext(), "Dispositivo selecionado: " + NOME_DISPOSITIVO, Toast.LENGTH_LONG).show();
                } else{
                    Toast.makeText(getApplicationContext(), "Falha ao obter o MAC", Toast.LENGTH_LONG).show();
                }
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }
}