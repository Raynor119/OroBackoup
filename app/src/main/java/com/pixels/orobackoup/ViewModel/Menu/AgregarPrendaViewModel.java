package com.pixels.orobackoup.ViewModel.Menu;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pixels.orobackoup.Model.BD.MYSQL.Consultas.Menu.AgregarPrendaMYSQL;

public class AgregarPrendaViewModel extends ViewModel {
    public MutableLiveData<Integer> resultado;
    public  AgregarPrendaViewModel(){
        this.resultado=new MutableLiveData<>();
    }
    public void reset(){
        this.resultado=new MutableLiveData<>();
    }
    public LiveData<Integer> getResultado(){
        return resultado;
    }
    public void guardarprenda(Context context, String condigoU, String nombreP){
        AgregarPrendaMYSQL BD=new AgregarPrendaMYSQL(context,condigoU,nombreP,AgregarPrendaViewModel.this);
    }
}
