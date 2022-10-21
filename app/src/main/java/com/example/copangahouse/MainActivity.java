package com.example.copangahouse;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.app.AlertDialog;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    ImageButton imgQuarto, imgSala, imgCozinha, imgVaranda;

    public String valueQuarto = "a";
    public String valueSala = "b";
    public String valueCozinha = "c";
    public String valueVaranda = "d";

    Button btnConectar;

    BluetoothAdapter bluetoothAdapter = null;
    BluetoothDevice bluetoothDevice = null;
    BluetoothSocket bluetoothSocket = null;

    private static final int REQUEST_ENABLE_BT = 1;
    private static final int REQUEST_CONECTION_BT = 2;
    boolean conexao = false;

    ConnectedThread connectedThread;

    // Dispositivo selecionado
    private static String ENDERECO_MAC = null;
    private static String NOME_DISPOSITIVO = null;

    UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

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
                    try {
                        bluetoothSocket.close();
                        conexao = false;
                        btnConectar.setText("Conectar");
                        Toast.makeText(getApplicationContext(), ENDERECO_MAC + " desconectado", Toast.LENGTH_LONG).show();
                    } catch (IOException erro){
                        Toast.makeText(getApplicationContext(), "Ocorreu um erro" + erro, Toast.LENGTH_LONG).show();
                    }
                } else{
                    // conectar
                    Intent abreLista = new Intent(MainActivity.this, ListaDispositivos.class);
                    startActivityForResult(abreLista, REQUEST_CONECTION_BT);
                }
            }
        });

        //----------------------------------------------------------------------------------------//

        imgQuarto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(conexao){
                    connectedThread.enviar(valueQuarto);
                }else{
                    Toast.makeText(getApplicationContext(), "Dispositivo não conectado", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imgQuarto.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                alterarValor(valueQuarto, "quarto");
                return false;
            }
        });

        //----------------------------------------------------------------------------------------//

        imgSala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(conexao){
                    connectedThread.enviar(valueSala);
                }else{
                    Toast.makeText(getApplicationContext(), "Dispositivo não conectado", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imgSala.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                alterarValor(valueSala, "sala");
                return false;
            }
        });

        //----------------------------------------------------------------------------------------//

        imgCozinha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(conexao){
                    connectedThread.enviar(valueCozinha);
                }else{
                    Toast.makeText(getApplicationContext(), "Dispositivo não conectado", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imgCozinha.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                alterarValor(valueCozinha, "cozinha");
                return false;
            }
        });

        //----------------------------------------------------------------------------------------//

        imgVaranda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(conexao){
                    connectedThread.enviar(valueVaranda);
                }else{
                    Toast.makeText(getApplicationContext(), "Dispositivo não conectado", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imgVaranda.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                alterarValor(valueVaranda, "varanda");
                return false;
            }
        });
    }

    @SuppressLint("MissingPermission")
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
                    //NOME_DISPOSITIVO = data.getExtras().getString(ListaDispositivos.NOME_DISPOSITIVO);
                    ENDERECO_MAC = data.getExtras().getString(ListaDispositivos.ENDERECO_MAC);

                    // Conecta no dispositivo selecionado
                     bluetoothDevice = bluetoothAdapter.getRemoteDevice(ENDERECO_MAC);

                    try{
                        bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(uuid);
                        bluetoothSocket.connect();
                        conexao = true;

                        connectedThread = new ConnectedThread(bluetoothSocket);
                        connectedThread.start();

                        btnConectar.setText("Desconectar");

                        Toast.makeText(getApplicationContext(), "Conectado com: " + ENDERECO_MAC, Toast.LENGTH_LONG).show();
                    } catch (IOException erro){
                        conexao = false;
                        Toast.makeText(getApplicationContext(), "Erro ao conectar com " + ENDERECO_MAC, Toast.LENGTH_LONG).show();
                    }

                } else{
                    Toast.makeText(getApplicationContext(), "Falha ao obter o MAC", Toast.LENGTH_LONG).show();
                }
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }

    // Classe responsavel por realizar leitura e escrita no bluetooth
    private class ConnectedThread extends Thread {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;
        private byte[] mmBuffer; // mmBuffer store for the stream

        public ConnectedThread(BluetoothSocket socket) {
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            try {
                tmpIn = socket.getInputStream();
            } catch (IOException e) {}
            try {
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {}

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void enviar(String dadosEnviar) {
            byte[] msgBuffer = dadosEnviar.getBytes();
            try {
                mmOutStream.write(msgBuffer);

            } catch (IOException e) {
            }
        }
    }

    public boolean alterarValor(String value, String nomeBotao){
        AlertDialog.Builder Alert = new AlertDialog.Builder(this);
        final EditText TextField = new EditText(this);

        TextField.setText(value);
        TextField.setTextSize(25);
        TextField.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        Alert
            .setTitle(nomeBotao.toUpperCase())
            .setView(TextField)
            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    String valor = TextField.getText().toString();

                    if(valor.equals("")){
                        Toast.makeText(getApplicationContext(), "Valor inválido", Toast.LENGTH_SHORT).show();
                        return;
                    } else if(valor.equals(value)){
                        return;
                    }
                    Toast.makeText(getApplicationContext(), "Valor alterado para: " + valor, Toast.LENGTH_SHORT).show();

                    switch (nomeBotao){
                        case "quarto":
                            valueQuarto = valor;
                            break;
                        case "sala":
                            valueSala = valor;
                            break;
                        case "cozinha":
                            valueCozinha = valor;
                            break;
                        case "varanda":
                            valueVaranda = valor;
                            break;
                    }
                }
            })
            .create()
            .show();
        return true;
    }
}
