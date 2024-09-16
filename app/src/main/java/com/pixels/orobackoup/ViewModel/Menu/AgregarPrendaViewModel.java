package com.pixels.orobackoup.ViewModel.Menu;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AgregarPrendaViewModel extends ViewModel {
    public MutableLiveData<Boolean> resultado;
    public  AgregarPrendaViewModel(){
        this.resultado=new MutableLiveData<>();
    }
    public void reset(){
        this.resultado=new MutableLiveData<>();
    }
    public LiveData<Boolean> getResultado(){
        return resultado;
    }
    public void guardarprenda(Context context, String condigoU, String nombreP){

    }
}
