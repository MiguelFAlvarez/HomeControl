package com.miguelalvarez.homecontrol;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;


public class TempControl extends ActionBarActivity {
    static byte[] sendData = new byte[1024];

    Button bTempDec, bTempInc, bTempSet;
    RadioButton rbFanOff, rbFanAuto, rbCool, rbOff, rbHeat;
    EditText etTempSet;
    TextView tvTemp1, tvTemp2, tvTemp3, tvTemp4, tvTemp5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_control);

        sendData[1] = 0;
        TCPClient client = new TCPClient(sendData);
        Thread clientThread = new Thread(client);
        clientThread.start();

        try {
            clientThread.join(2000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        bTempDec = (Button) findViewById(R.id.bTempDec);
        bTempInc = (Button) findViewById(R.id.bTempInc);
        bTempSet = (Button) findViewById(R.id.bTempSet);

        rbFanOff = (RadioButton) findViewById(R.id.rbFanOff);
        rbFanAuto = (RadioButton) findViewById(R.id.rbFanAuto);
        rbCool = (RadioButton) findViewById(R.id.rbCool);
        rbOff = (RadioButton) findViewById(R.id.rbOff);
        rbHeat = (RadioButton) findViewById(R.id.rbHeat);

        etTempSet = (EditText) findViewById(R.id.etTempSet);

        tvTemp1 = (TextView) findViewById(R.id.tvTemp1);
        tvTemp2 = (TextView) findViewById(R.id.tvTemp2);
        tvTemp3 = (TextView) findViewById(R.id.tvTemp3);
        tvTemp4 = (TextView) findViewById(R.id.tvTemp4);
        tvTemp5 = (TextView) findViewById(R.id.tvTemp5);

        TempViewUpdate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_temp_control, menu);
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

    public void buttonClickTemp(View v){
        byte[] sendData = new byte[1024];

        sendData[0] = 0;    //unused identifier
        sendData[1] = 2;    //thermostat
/*
        switch (v.getId()) {
            case R.id.rbFanOff:
                sendData[2] = 0;   //0 = fan off 1 = fan auto
                break;
            case R.id.rbFanAuto:
                sendData[2] =1;    //0 = fan off 1 = fan auto
                break;
            case R.id.rbCool:
                sendData[3] = 0;    //0 = cool, 1 = off, 2 = heat
                break;
            case R.id.rbOff:
                sendData[3] = 1;    //0 = cool, 1 = off, 2 = heat
                break;
            case R.id.rbHeat:
                sendData[3] = 2;    //0 = cool, 1 = off, 2 = heat
                break;
        }
*/
        if (rbFanOff.isChecked()) {
            sendData[2] = 0;   //0 = fan off 1 = fan auto
        }
        else if (rbFanAuto.isChecked()) {
            sendData[2] = 1;    //0 = fan off 1 = fan auto
        }
        if(rbCool.isChecked()) {
            sendData[3] = 0;    //0 = cool, 1 = off, 2 = heat
        }
        else if(rbOff.isChecked()) {
            sendData[3] = 1;    //0 = cool, 1 = off, 2 = heat
        }
        else if(rbHeat.isChecked()){
                sendData[3] = 2;    //0 = cool, 1 = off, 2 = heat
        }


        sendData[4] = (byte) (Integer.parseInt(etTempSet.getText().toString())) ;

        TCPClient client = new TCPClient(sendData);
        Thread clientThread = new Thread(client);
        clientThread.start();
        try {
            clientThread.join(2000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        TempViewUpdate();
    }

    public void TempViewUpdate(){



        switch (TCPClient.receiveData[1]){
            case 0:
                rbFanOff.setChecked(true);
               // rbFanAuto.setChecked(false);
                break;
            case 1:
               // rbFanOff.setChecked(false);
                rbFanAuto.setChecked(true);
                break;
        }
        switch (TCPClient.receiveData[2]){
            case 0:
                rbCool.setChecked(true);
                //rbOff.setChecked(false);
                //rbHeat.setChecked(false);
                break;
            case 1:
                //rbCool.setChecked(false);
                rbOff.setChecked(true);
                //rbHeat.setChecked(false);
                break;
            case 2:
                //rbCool.setChecked(false);
                //rbOff.setChecked(false);
                rbHeat.setChecked(true);
                break;
        }

        tvTemp1.setText("Temp 1: "+TCPClient.receiveData[4]);
        tvTemp2.setText("Temp 2: "+TCPClient.receiveData[5]);
        tvTemp3.setText("Temp 3: "+TCPClient.receiveData[6]);
        tvTemp4.setText("Temp 4: "+TCPClient.receiveData[7]);
        tvTemp5.setText("Temp 5: "+TCPClient.receiveData[8]);

        etTempSet.setText(""+TCPClient.receiveData[3]);



    }
}
