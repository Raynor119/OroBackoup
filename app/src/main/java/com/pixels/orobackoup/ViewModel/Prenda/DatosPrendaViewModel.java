package com.pixels.orobackoup.ViewModel.Prenda;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pixels.orobackoup.Model.DatosEncapsulados.DatosPrenda;

import java.util.List;

public class DatosPrendaViewModel extends ViewModel {
    public MutableLiveData<String> Nombre;
    public MutableLiveData<List<DatosPrenda>> DatosdePrenda;
    public  DatosPrendaViewModel(){
        this.Nombre=new MutableLiveData<>();
        this.DatosdePrenda=new MutableLiveData<>();
    }
    public void reset(){
        this.Nombre=new MutableLiveData<>();
        this.DatosdePrenda=new MutableLiveData<>();
    }
    public LiveData<String> getResultado(){
        return Nombre;
    }
    public LiveData<List<DatosPrenda>> getResultadoDatos(){
        return DatosdePrenda;
    }
    public void nombreprenda(Context context, String CodigoP){

    }
    public void DatosdePrendas(Context context, String CodigoP){

    }
}