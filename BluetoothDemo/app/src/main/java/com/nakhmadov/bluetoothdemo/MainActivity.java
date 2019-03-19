package com.nakhmadov.bluetoothdemo;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    BluetoothAdapter BA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BA = ((BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE)).getAdapter();

        if (BA.isEnabled()) {
            Toast.makeText(this, "Bluetooth is on", Toast.LENGTH_LONG).show();
        } else {
            Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(i);

            if (BA.isEnabled()) {
                Toast.makeText(this, "Bluetooth turned on", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void turnBluetoothOff(View view) {
        BA.disable();
        if (BA.isEnabled()) {
            Toast.makeText(this, "Bluetooth could not be disabled", Toast.LENGTH_LONG).show();
        } else {

            Toast.makeText(this, "Bluetooth turned off", Toast.LENGTH_LONG).show();

        }

    }

    public void findDiscoverableDevices(View view) {
        Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivity(i);
    }

    public void viewPairedDevices(View view) {
        Set<BluetoothDevice> pairedDevices = BA.getBondedDevices();

        ListView pairedDevicesListView = (ListView) findViewById(R.id.pairedDevicesListView);

        ArrayList<String> pairedDevicesList = new ArrayList<>();
        for (BluetoothDevice bluetoothDevice : pairedDevices) {
            pairedDevicesList.add(bluetoothDevice.getName());
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, pairedDevicesList);
        pairedDevicesListView.setAdapter(arrayAdapter);
    }
}
