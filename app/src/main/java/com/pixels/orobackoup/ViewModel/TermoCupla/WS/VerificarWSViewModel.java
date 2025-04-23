package com.pixels.orobackoup.ViewModel.TermoCupla.WS;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pixels.orobackoup.Model.BD.WS.ConexionWebSocket;

import java.util.List;

public class VerificarWSViewModel extends ViewModel {
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
    public void EjecutarSession(AppCompatActivity context, String consulta){
        ConexionWebSocket ws =new ConexionWebSocket(context,consulta,VerificarWSViewModel.this);
    }
}
