package com.miguelalvarez.homecontrol;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class LightsActivity extends ActionBarActivity {
    static byte[] sendData = new byte[1024];

    Button bLight1toggle, bLight2toggle, bLight3toggle, bLight4toggle, bLight5toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lights);

        sendData[1] = 0;
        TCPClient client = new TCPClient(sendData);
        Thread clientThread = new Thread(client);
        clientThread.start();

        try {
            clientThread.join(500);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        bLight1toggle = (Button) findViewById(R.id.bLight1toggle);
        bLight2toggle = (Button) findViewById(R.id.bLight2toggle);
        bLight3toggle = (Button) findViewById(R.id.bLight3toggle);
        bLight4toggle = (Button) findViewById(R.id.bLight4toggle);
        bLight5toggle = (Button) findViewById(R.id.bLight5toggle);

        bLight1toggle.setText("Light 1: "+TCPClient.receiveData[10]);
        bLight2toggle.setText("Light 2: "+TCPClient.receiveData[11]);
        bLight3toggle.setText("Light 3: "+TCPClient.receiveData[12]);
        bLight4toggle.setText("Light 4: "+TCPClient.receiveData[13]);
        bLight5toggle.setText("Light 5: "+TCPClient.receiveData[14]);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lights, menu);
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

    public void buttonClick(View v){
        byte[] sendData = new byte[1024];
        switch (v.getId()) {
            case R.id.bLight1toggle:
                sendData[0] = 0;    //unused identifier
                sendData[1] = 1;    //lights
                sendData[2] = 0;    //0 = toggle, 1 = on, 2 = off
                sendData[3] = 1;    //light number
                break;
            case R.id.bLight2toggle:
                sendData[0] = 0;    //unused identifier
                sendData[1] = 1;    //lights
                sendData[2] = 0;    //0 = toggle, 1 = on, 2 = off
                sendData[3] = 2;    //light number
                break;
            case R.id.bLight3toggle:
                sendData[0] = 0;    //unused identifier
                sendData[1] = 1;    //lights
                sendData[2] = 0;    //0 = toggle, 1 = on, 2 = off
                sendData[3] = 3;    //light number
                break;
            case R.id.bLight4toggle:
                sendData[0] = 0;    //unused identifier
                sendData[1] = 1;    //lights
                sendData[2] = 1;    //0 = toggle, 1 = on, 2 = off
                sendData[3] = 4;    //light number
                break;
            case R.id.bLight5toggle:
                sendData[0] = 0;    //unused identifier
                sendData[1] = 1;    //lights
                sendData[2] = 0;    //0 = toggle, 1 = on, 2 = off
                sendData[3] = 5;    //light number
                break;
            case R.id.bLight1on:
                sendData[0] = 0;    //unused identifier
                sendData[1] = 1;    //lights
                sendData[2] = 1;    //0 = toggle, 1 = on, 2 = off
                sendData[3] = 1;    //light number
                break;
            case R.id.bLight2on:
                sendData[0] = 0;    //unused identifier
                sendData[1] = 1;    //lights
                sendData[2] = 1;    //0 = toggle, 1 = on, 2 = off
                sendData[3] = 2;    //light number
                break;
            case R.id.bLight3on:
                sendData[0] = 0;    //unused identifier
                sendData[1] = 1;    //lights
                sendData[2] = 1;    //0 = toggle, 1 = on, 2 = off
                sendData[3] = 3;    //light number
                break;
            case R.id.bLight4on:
                sendData[0] = 0;    //unused identifier
                sendData[1] = 1;    //lights
                sendData[2] = 1;    //0 = toggle, 1 = on, 2 = off
                sendData[3] = 4;    //light number
                break;
            case R.id.bLight5on:
                sendData[0] = 0;    //unused identifier
                sendData[1] = 1;    //lights
                sendData[2] = 1;    //0 = toggle, 1 = on, 2 = off
                sendData[3] = 5;    //light number
                break;
            case R.id.bLight1off:
                sendData[0] = 0;    //unused identifier
                sendData[1] = 1;    //lights
                sendData[2] = 2;    //0 = toggle, 1 = on, 2 = off
                sendData[3] = 1;    //light number
                break;
            case R.id.bLight2off:
                sendData[0] = 0;    //unused identifier
                sendData[1] = 1;    //lights
                sendData[2] = 2;    //0 = toggle, 1 = on, 2 = off
                sendData[3] = 2;    //light number
                break;
            case R.id.bLight3off:
                sendData[0] = 0;    //unused identifier
                sendData[1] = 1;    //lights
                sendData[2] = 2;    //0 = toggle, 1 = on, 2 = off
                sendData[3] = 3;    //light number
                break;
            case R.id.bLight4off:
                sendData[0] = 0;    //unused identifier
                sendData[1] = 1;    //lights
                sendData[2] = 2;    //0 = toggle, 1 = on, 2 = off
                sendData[3] = 4;    //light number
                break;
            case R.id.bLight5off:
                sendData[0] = 0;    //unused identifier
                sendData[1] = 1;    //lights
                sendData[2] = 2;    //0 = toggle, 1 = on, 2 = off
                sendData[3] = 5;    //light number
                break;
        }

        TCPClient client = new TCPClient(sendData);
        Thread clientThread = new Thread(client);
        clientThread.start();
        try {
            clientThread.join(500);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        bLight1toggle.setText("Light 1: "+TCPClient.receiveData[10]);
        bLight2toggle.setText("Light 2: "+TCPClient.receiveData[11]);
        bLight3toggle.setText("Light 3: "+TCPClient.receiveData[12]);
        bLight4toggle.setText("Light 4: "+TCPClient.receiveData[13]);
        bLight5toggle.setText("Light 5: " + TCPClient.receiveData[14]);
    }
}
