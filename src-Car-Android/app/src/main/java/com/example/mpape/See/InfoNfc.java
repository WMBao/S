package com.example.mpape.See;

import android.app.Activity;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by Weiming on 2016/12/25.
 */

public class InfoNfc extends Activity implements NfcAdapter.CreateNdefMessageCallback{
    private TextView ipTextVIew;
    private NfcAdapter nfcAdapter;
    private ArrayList<String> messagesToSendArray;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.activity_ipinfo);

        Button button = (Button) findViewById(R.id.button9);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        nfcAdapter = NfcAdapter.getDefaultAdapter(getActivity());
        String ipAddress = getIpAddress();

        ipTextVIew = (TextView) findViewById(R.id.ip_address_text_view);
        ipTextVIew.setText(ipAddress);
        System.out.println(ipAddress);

        messagesToSendArray = new ArrayList<>();
        messagesToSendArray.add(ipAddress);

        if (nfcAdapter == null) {
            Toast.makeText(this, "NFC is not supported.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }else if (!nfcAdapter.isEnabled()) {
            Toast.makeText(this, "NFC is not enabled.", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
            startActivity(intent);
        }

        nfcAdapter.setNdefPushMessageCallback(this, getActivity());
    }

    public NdefMessage createNdefMessage(NfcEvent nfcEvent) {
        NdefRecord[] recordsToAttach = createRecords();
        //When creating an NdefMessage we need to provide an NdefRecord[]
        return new NdefMessage(recordsToAttach);
    }

    public void onNdefPushComplete(NfcEvent nfcEvent) {}

    public NdefRecord[] createRecords() {
        NdefRecord[] records = new NdefRecord[messagesToSendArray.size() + 1];
        //To Create Messages Manually if API is less than
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            for (int i = 0; i < messagesToSendArray.size(); i++){
                byte[] payload = messagesToSendArray.get(i).getBytes(Charset.forName("UTF-8"));
                NdefRecord record = new NdefRecord(
                        NdefRecord.TNF_WELL_KNOWN,      //Our 3-bit Type name format
                        NdefRecord.RTD_TEXT,            //Description of our payload
                        new byte[0],                    //The optional id for our Record
                        payload);                       //Our payload for the Record

                records[i] = record;
            }
        }
        //Api is high enough that we can use createMime, which is preferred.
        else {
            for (int i = 0; i < messagesToSendArray.size(); i++){
                byte[] payload = messagesToSendArray.get(i).
                        getBytes(Charset.forName("UTF-8"));

                NdefRecord record = NdefRecord.createMime("text/plain",payload);
                records[i] = record;

            }
        }
        records[messagesToSendArray.size()] = NdefRecord.createApplicationRecord(getActivity().getPackageName());
        return records;
    }

    private String getIpAddress(){
        WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        int ipAddress = wifiManager.getConnectionInfo().getIpAddress();
        String ipAddressFormatted = String.format("%d.%d.%d.%d", (ipAddress & 0xff), (ipAddress >> 8 & 0xff),
                (ipAddress >> 16 & 0xff), (ipAddress >> 24 & 0xff));
        return ipAddressFormatted;
    }

    private Activity getActivity(){
        return this;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Check to see that the Activity started due to an Android Beam
//        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
//            processIntent(getIntent());
//        }
    }


    /**
     * Parses the NDEF Message from the intent and prints to the TextView
     */
}