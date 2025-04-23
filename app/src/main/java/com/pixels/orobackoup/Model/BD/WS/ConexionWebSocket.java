package com.pixels.orobackoup.Model.BD.WS;

import android.widget.Toast;

import androidx.annotation.WorkerThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


import com.pixels.orobackoup.R;
import com.pixels.orobackoup.ViewModel.TermoCupla.WS.VerificarWSViewModel;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

import tech.gusavila92.websocketclient.WebSocketClient;
public class ConexionWebSocket {
    private WebSocketClient webSocketClient;
    private int contador=0;
    private AppCompatActivity CContext;
    private String Consulta;
    //private VerificarInicioSWSViewModel ViewModel;
    private VerificarWSViewModel ViewModel;
    private String CodigoV;
    public ConexionWebSocket(AppCompatActivity context, String consulta, VerificarWSViewModel viewModel, String codigoV){
        this.CContext=context;
        this.Consulta=consulta;
        this.ViewModel=viewModel;
        this.CodigoV=codigoV;
        createWebSocketClient();
    }
    private void createWebSocketClient() {
        URI uri;
        try {
            // Connect to local host
            uri = new URI(""+CContext.getResources().getString(R.string.ServidorWS));
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }
        workerThread(uri);
    }
    @WorkerThread
    void workerThread(URI uri) {
        ContextCompat.getMainExecutor(CContext).execute(()  -> {
            webSocketClient = new WebSocketClient(uri) {
                @Override
                public void onOpen() {
                    webSocketClient.send(Consulta);
                }

                @Override
                public void onTextReceived(String s) {
                    final String message = s;
                    CContext.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try{

                                if(contador!=0){
                                    ViewModel.resultado.setValue(message);
                                    webSocketClient.close();
                                }else{
                                    if(message.equals(CodigoV)){
                                        contador++;
                                    }else{
                                        ViewModel.resultado.setValue("0");
                                        webSocketClient.close();
                                    }
                                }
                            } catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    });
                }

                @Override
                public void onBinaryReceived(byte[] data) {
                }

                @Override
                public void onPingReceived(byte[] data) {
                    String TT = new String(data, StandardCharsets.UTF_8);
                    System.out.println("----------------------------------------------Pong: "+TT+" --------------------------");
                }

                @Override
                public void onPongReceived(byte[] data) {


                    String TT = new String(data, StandardCharsets.UTF_8);
                    System.out.println("----------------------------------------------Ping: "+TT+" --------------------------");
                }

                @Override
                public void onException(Exception e) {
                    System.out.println("------------------------------"+e.getMessage()+"----------------------------------");
                }


                @Override
                public void onCloseReceived() {
                }
            };
            webSocketClient.setConnectTimeout(10000);
            webSocketClient.setReadTimeout(60000);
            webSocketClient.enableAutomaticReconnection(5000);
            webSocketClient.connect();
        });
    }

}
