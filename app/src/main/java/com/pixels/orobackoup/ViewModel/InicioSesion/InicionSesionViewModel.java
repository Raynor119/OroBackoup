package com.pixels.orobackoup.ViewModel.InicioSesion;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InicionSesionViewModel extends ViewModel {
    public MutableLiveData<String> resultado;
    public InicionSesionViewModel(){
        this.resultado=new MutableLiveData<>();

    }
    public void reset(){
        this.resultado=new MutableLiveData<>();

    }
    public LiveData<String> getResultado(){
        return resultado;
    }
    public void verificarUsuario(Context context,String Usuario,String Contra){

    }
}
