package com.example.mpape.Ser;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Rect;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.app.Activity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
public class VoiceMode extends Activity implements SurfaceHolder.Callback,DataListener{
    private SurfaceView surfaceview;
    private SurfaceHolder surfaceholder;
    private ArrayList<String> messagesReceivedArray;
    private int activepohoto;
    public static EditText editTextIpAddress;
    private static boolean ipConfirmed = false;
    LinkedList<Integer>damn;
    Handler myHandler;
    protected void onCreate(Bundle savedInstanceState) {
        messagesReceivedArray = new ArrayList<>();

        myHandler = new Handler() {
            public void handleMessage(Message msg) {
                int t=msg.what;
                if(t==13){
                    Toast.makeText(getApplicationContext(), "Find a human.",
                            Toast.LENGTH_SHORT).show();
                }
                if(t==1){
                    Toast.makeText(getApplicationContext(), "Find something.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        };
        //ipConfirmed = false;
        System.out.print("hello\n");
        damn=new LinkedList<Integer>();
        activepohoto=0;
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.activity_voice_mode);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(VoiceMode.this,KeyMode.class);
                startActivity(intent);
            }
        });

        final ImageButton ibutton = (ImageButton) findViewById(R.id.button6);
        ibutton.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent e){
                switch(e.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        sendData("W");
//                        System.out.print("W pressed");
                        return true;
                    case MotionEvent.ACTION_UP:
                        sendData("Q");
                        return true;
                }
                return false;
            }
        });
        final ImageButton ibutton2 = (ImageButton) findViewById(R.id.button3);
        ibutton2.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent e){
                switch(e.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        sendData("S");
                        return true;
                    case MotionEvent.ACTION_UP:
                        sendData("Q");
                        return true;
                }
                return false;
            }
        });
        final Button ibutton22 = (Button) findViewById(R.id.button8);
        ibutton22.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent e){
                switch(e.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        //sendData("S");
                        return true;
                    case MotionEvent.ACTION_UP:
                        VoiceToWord voice = new VoiceToWord(VoiceMode.this,"534e3fe2",(MyApplication)getApplication());
                        voice.GetWordFromVoice();
                        return true;
                }
                return false;
            }
        });

        editTextIpAddress = (EditText) findViewById(R.id.ip_edit_text);

        final Button ipButton = (Button) findViewById(R.id.button10);
        ipButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ipConfirmed = true;
                ipButton.setVisibility(View.INVISIBLE);
                editTextIpAddress.setVisibility(View.INVISIBLE);
                return;
            }
        });

//        final Button nfcButton = (Button) findViewById(R.id.button11);
//        ipButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(VoiceMode.this,Nfc.class);
//                startActivity(intent);
//            }
//        });

        final ImageButton ibutton3 = (ImageButton) findViewById(R.id.button7);
        ibutton3.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent e){
                switch(e.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        sendData("A");
                        return true;
                    case MotionEvent.ACTION_UP:
                        sendData("Q");
                        return true;
                }
                return false;
            }
        });
        final ImageButton ibutton4 = (ImageButton) findViewById(R.id.button4);
        ibutton4.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent e){

                switch(e.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        sendData("D");
//                        System.out.print("d Pressed");
                        return true;
                    case MotionEvent.ACTION_UP:
                        sendData("Q");
                        return true;
                }
                return false;
            }
        });
        final ImageButton ibutton5 = (ImageButton) findViewById(R.id.button5);
        ibutton5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((MyApplication)getApplication()).phts.addFirst(((MyApplication)getApplication()).mLastFrame);
                Toast.makeText(getApplicationContext(), "Photo Taken!",
                        Toast.LENGTH_SHORT).show();
            }
        });
        final Button gray_radio = ( Button) findViewById(R.id.button2);
        gray_radio.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Cops on their way!",
                        Toast.LENGTH_SHORT).show();
                ((MyApplication)getApplication()).server.add(1);
            }
        });
        final Button gray_radio2 = ( Button) findViewById(R.id.button9);
        gray_radio2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendData("Q");
            }
        });

//        EditText iptext = (EditText) findViewById(R.id.ip_edit_text);
//        iptext.setText("192.168.1.1");

        surfaceview = (SurfaceView)findViewById(R.id.surfaceView);
        surfaceholder = surfaceview.getHolder();
        surfaceholder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        surfaceholder.addCallback(this);
    }


    private void handleNfcIntent(Intent NfcIntent) {
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(NfcIntent.getAction())) {
            Parcelable[] receivedArray =
                    NfcIntent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

            if (receivedArray != null) {
                messagesReceivedArray.clear();
                NdefMessage ndefMessage = (NdefMessage) receivedArray[0];
                NdefRecord[] attachedRecords = ndefMessage.getRecords();

                for (NdefRecord record : attachedRecords) {
                    String string = new String(record.getPayload());
                    //Make sure we don't pass along our AAR (Android Application Record)
                    if (string.equals(getPackageName())) {
                        continue;
                    }
                    messagesReceivedArray.add(string);
                }

                String receivedMessage = messagesReceivedArray.get(0);
                System.out.println(receivedMessage);
                EditText iptext = (EditText) findViewById(R.id.ip_edit_text);
                iptext.setText(receivedMessage);

            } else {
                Toast.makeText(this, "Received Blank Parcel", Toast.LENGTH_LONG).show();
            }
        }
    }

    protected void onNewIntent(Intent intent) {
        handleNfcIntent(intent);
//        if (ipConfirmed) {
//            final Button ipButton = (Button) findViewById(R.id.button10);
//            ipButton.setVisibility(View.INVISIBLE);
//            editTextIpAddress.setVisibility(View.INVISIBLE);
//        }
    }

//    protected void onNewIntent(Intent intent) {
//        setIntent(intent);
//    }

    boolean sendData(String m){
        try{
            String msg = m;
            ((MyApplication)getApplication()).mmOutputStream.write(msg.getBytes());
            System.out.println("Data Sent!");
            return true;
        }catch (Exception e){
            return false;
        }
    }
    protected void onPause(){
        super.onPause();
        activepohoto=0;
        surfaceview.setVisibility(View.GONE);
        sendData("Q");
    }
    protected void onResume(){
        super.onResume();
        handleNfcIntent(getIntent());
        surfaceview.setVisibility(View.VISIBLE);
        ((MyApplication)getApplication()).server.setOnDataListener(this);
    }
    public void surfaceCreated(SurfaceHolder holder) {
        activepohoto=1;
    }
    public void surfaceChanged(SurfaceHolder holder,int a,int b,int c){
    }
    public void surfaceDestroyed(SurfaceHolder arg0) {
    }
    public void onDirty(Bitmap bufferedImage) {
        updateUI(bufferedImage);
    }
    private void updateUI(Bitmap bufferedImage) {
        synchronized (((MyApplication)getApplication()).mQueue) {
            if (((MyApplication)getApplication()).mQueue.size() ==  ((MyApplication)getApplication()).MAX_BUFFER) {
                ((MyApplication)getApplication()).mLastFrame = ((MyApplication)getApplication()).mQueue.poll();
            }
            ((MyApplication)getApplication()).mQueue.add(bufferedImage);
        }
        repaint();
    }
    public void repaint() {
        synchronized (((MyApplication)getApplication()).mQueue) {
            if (((MyApplication)getApplication()).mQueue.size() > 0) {
                ((MyApplication)getApplication()).mLastFrame = ((MyApplication)getApplication()).mQueue.poll();
            }
        }
        if (((MyApplication)getApplication()).mLastFrame != null) {
            Canvas c=surfaceholder.lockCanvas();
            if(c!=null){
                synchronized (surfaceholder) {
                    Rect tmp=new Rect(0,0,c.getWidth(),c.getHeight());
                    c.drawBitmap(((MyApplication)getApplication()).mLastFrame,null,tmp,new Paint());
                }
                surfaceholder.unlockCanvasAndPost(c);
            }
        }
        else if (((MyApplication)getApplication()).mImage != null) {
            Canvas c=surfaceholder.lockCanvas();
            if(c!=null){
                synchronized (surfaceholder) {
                    Rect tmp=new Rect(0,0,c.getWidth(),c.getHeight());
                    c.drawBitmap(((MyApplication)getApplication()).mImage,null,tmp,new Paint());
                }
                surfaceholder.unlockCanvasAndPost(c);
            }
        }
    }
    public void conv(int t){
        Message msg=new Message();
        msg.what=t;
        myHandler.sendMessage(msg);
    }
    public static String getAddr(){
        if(ipConfirmed == false){
            return null;
        }
        if(editTextIpAddress == null){
            return null;
        }
        return editTextIpAddress.getText().toString();
    }
}
