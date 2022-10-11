package com.example.copangahouse;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ListActivity; // Listagem padrão do android
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import java.util.Set;

public class ListaDispositivos extends ListActivity {

    private BluetoothAdapter bluetoothAdapter2 = null;

    static String ENDERO_MAC = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayAdapter<String> ArrayBluetooth = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        bluetoothAdapter2 = BluetoothAdapter.getDefaultAdapter();

        Set<BluetoothDevice> dispositivosPareados = bluetoothAdapter2.getBondedDevices();

        if (dispositivosPareados.size() > 0){
            for (BluetoothDevice dispositivos: dispositivosPareados){
                String nomeBT = dispositivos.getName();
                String macBT = dispositivos.getAddress();
                ArrayBluetooth.add(nomeBT + "\n" + macBT);
            }
        }

        setListAdapter(ArrayBluetooth);
    }
}
