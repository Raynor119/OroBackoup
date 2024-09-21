package com.pixels.orobackoup.ViewModel.Prenda;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pixels.orobackoup.Model.BD.MYSQL.Consultas.Prenda.DatosEstadosMYSQL;

public class DatosEstadosViewModel extends ViewModel {
    public MutableLiveData<String> datosman;

    public DatosEstadosViewModel(){
        this.datosman=new MutableLiveData<>();
    }
    public void reset(){
        this.datosman=new MutableLiveData<>();
    }
    public LiveData<String> getResultado(){
        return datosman;
    }
    public void daotsestados(Context context,String TipoEstado, String CodigoP,float peso_inicial,float peso_final,byte[] Foto){
        DatosEstadosMYSQL BD=new DatosEstadosMYSQL(context, TipoEstado, Integer.parseInt(CodigoP),peso_inicial,peso_final,Foto,DatosEstadosViewModel.this);
    }
}
