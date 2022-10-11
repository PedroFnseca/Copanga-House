package com.example.copangahouse;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ListActivity; // Listagem padrão do android
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import java.util.Set;

public class ListaDispositivos extends ListActivity {

    private BluetoothAdapter bluetoothAdapter2 = null;

    static String ENDERECO_MAC = null;
    static String NOME_DISPOSITIVO = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayAdapter<String> ArrayBluetooth = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        bluetoothAdapter2 = BluetoothAdapter.getDefaultAdapter();

        @SuppressLint("MissingPermission") Set<BluetoothDevice> dispositivosPareados = bluetoothAdapter2.getBondedDevices();

        if (dispositivosPareados.size() > 0){
            for (BluetoothDevice dispositivos: dispositivosPareados){
                @SuppressLint("MissingPermission") String nomeBT = dispositivos.getName();
                String macBT = dispositivos.getAddress();
                ArrayBluetooth.add(nomeBT + "\n" + macBT);
            }
        }

        setListAdapter(ArrayBluetooth);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String infoGeral = ((TextView) v).getText().toString();
        String enderecoMac = infoGeral.substring(infoGeral.length() - 17);
        String nomeDispositivo = infoGeral.split("\n")[0];

        Intent retornaMac = new Intent();
        retornaMac.putExtra(ENDERECO_MAC, enderecoMac);
        retornaMac.putExtra(NOME_DISPOSITIVO, nomeDispositivo);
        setResult(RESULT_OK, retornaMac);
        finish();
    }
}
