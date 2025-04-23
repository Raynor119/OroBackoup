package com.pixels.orobackoup.ViewModel.TermoCupla.WS;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.pixels.orobackoup.Model.BD.WS.ConexionWebSocket;

import java.util.List;

public class VerificarWSViewModel {
    public MutableLiveData<String> resultado;

    public VerificarWSViewModel(){
        resultado=new MutableLiveData<>();
    }
    public void reset(){
        resultado=new MutableLiveData<>();
    }
    public LiveData<String> getResultado(){
        return resultado;
    }
    public void verificarusuario(AppCompatActivity context, String consulta, String CodigoV){
        ConexionWebSocket ws =new ConexionWebSocket(context,consulta,VerificarWSViewModel.this,CodigoV);
    }
    public String obtenerUsuario(Context context){

        return "";
    }

}
