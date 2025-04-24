package com.pixels.orobackoup.ViewModel.TermoCupla.WS;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pixels.orobackoup.Model.BD.MYSQL.Consultas.TermoCupla.GraficaTiempoRealMYSQL;
import com.pixels.orobackoup.Model.DatosEncapsulados.TermoCalor;
import com.pixels.orobackoup.View.TermoCupla.TermoCupla;

import java.util.List;

public class GraficaTiempoRealViewModel extends ViewModel {
    public MutableLiveData<List<TermoCalor>> resultado;
    GraficaTiempoRealViewModel(){
        resultado=new MutableLiveData<>();
    }
    public void reset(){
        resultado=new MutableLiveData<>();
    }
    public LiveData<List<TermoCalor>> getResultado(){
        return resultado;
    }
    public void GraficaTiempoReal(TermoCupla context, String Session){
        GraficaTiempoRealMYSQL sv=new GraficaTiempoRealMYSQL(context,GraficaTiempoRealViewModel.this,Session);
    }
}
