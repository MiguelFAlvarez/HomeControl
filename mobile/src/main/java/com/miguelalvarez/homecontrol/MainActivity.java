package com.miguelalvarez.homecontrol;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    public static byte[] sendData = new byte[1024];

    TextView tvLight1, tvLight2, tvLight3, tvLight4, tvLight5;
    TextView tvFanStatus, tvTempMode, tvSetTemp, tvTemp1, tvTemp2;
    TextView tvDoor1, tvDoor2, tvDoor3, tvDoor4, tvDoor5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendData[1] = 0;
        TCPClient client = new TCPClient(sendData);
        Thread clientThread = new Thread(client);
        clientThread.start();
        try {
            clientThread.join(2000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        tvLight1 = (TextView) findViewById(R.id.tvLight1);
        tvLight2 = (TextView) findViewById(R.id.tvLight2);
        tvLight3 = (TextView) findViewById(R.id.tvLight3);
        tvLight4 = (TextView) findViewById(R.id.tvLight4);
        tvLight5 = (TextView) findViewById(R.id.tvLight5);

        tvFanStatus = (TextView) findViewById(R.id.tvFanStatus);
        tvTempMode = (TextView) findViewById(R.id.tvTempMode);
        tvSetTemp = (TextView) findViewById(R.id.tvSetTemp);
        tvTemp1 = (TextView) findViewById(R.id.tvTemp1);
        tvTemp2 = (TextView) findViewById(R.id.tvTemp2);

        tvDoor1 = (TextView) findViewById(R.id.tvDoor1);
        tvDoor2 = (TextView) findViewById(R.id.tvDoor2);
        tvDoor3 = (TextView) findViewById(R.id.tvDoor3);
        tvDoor4 = (TextView) findViewById(R.id.tvDoor4);
        tvDoor5 = (TextView) findViewById(R.id.tvDoor5);

        TextViewUpdate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void openActivity(View v){
        switch (v.getId()) {
            case R.id.bLights:
                Intent intent = new Intent(this, LightsActivity.class);
                startActivity(intent);
                break;
            case R.id.bTemp:
                Intent intent1 = new Intent(this, TempControl.class);
                startActivity(intent1);
                break;
            case R.id.bStatus:

                break;
            case R.id.bUpdate:
                sendData[1] = 0;
                TCPClient client = new TCPClient(sendData);
                Thread clientThread = new Thread(client);
                clientThread.start();
                try {
                    clientThread.join(2000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                TextViewUpdate();
                break;


        }
    }

    public void TextViewUpdate(){
        Log.d("MainActivity", "Light1:" + TCPClient.receiveData[10]);
        tvLight1.setText("Light1: " + TCPClient.receiveData[10]);
        tvLight2.setText("Light2: "+TCPClient.receiveData[11]);
        tvLight3.setText("Light3: "+TCPClient.receiveData[12]);
        tvLight4.setText("Light4: "+TCPClient.receiveData[13]);
        tvLight5.setText("Light5: "+TCPClient.receiveData[14]);

        switch (TCPClient.receiveData[1]){
            case 0:
                tvFanStatus.setText("Fan Auto");
            case 1:
                tvFanStatus.setText("Fan Off");
        }
        switch (TCPClient.receiveData[2]){
            case 0:
                tvTempMode.setText("Cool");
            case 1:
                tvTempMode.setText("Off");
            case 2:
                tvTempMode.setText("Heat");
        }
        tvSetTemp.setText("Set Temp: "+TCPClient.receiveData[3]);
        tvTemp1.setText("Temp1: "+TCPClient.receiveData[4]);
        tvTemp2.setText("Temp2: "+TCPClient.receiveData[5]);

        tvDoor1.setText("Door1: "+TCPClient.receiveData[30]);
        tvDoor2.setText("Door2: "+TCPClient.receiveData[31]);
        tvDoor3.setText("Door3: "+TCPClient.receiveData[32]);
        tvDoor4.setText("Door4: "+TCPClient.receiveData[33]);
        tvDoor5.setText("Door5: "+TCPClient.receiveData[34]);

    }
}
