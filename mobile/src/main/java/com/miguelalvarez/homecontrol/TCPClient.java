package com.miguelalvarez.homecontrol;

import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Miguel on 5/16/2015.
 */
public class TCPClient extends Thread {

    static byte[] sendData;
    static byte[] receiveData = new byte[1024];

    public TCPClient( byte[] sendData){
        this.sendData=sendData;

    }

    @Override
    public void run() {
        try {
            Socket clientSocket = new Socket("192.168.1.113", 5015);

            OutputStream out = clientSocket.getOutputStream();
            out.write(sendData);
            InputStream in = clientSocket.getInputStream();
            in.read(receiveData);

            Log.d("TCPClient", "Light1:"+receiveData[10]+" Fan status:"+receiveData[1]+" TempMode:"+receiveData[2]);
            clientSocket.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
