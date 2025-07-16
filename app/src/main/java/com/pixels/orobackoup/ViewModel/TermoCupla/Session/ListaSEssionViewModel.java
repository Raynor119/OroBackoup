package com.pixels.orobackoup.ViewModel.TermoCupla.Session;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pixels.orobackoup.Model.BD.MYSQL.Consultas.TermoCupla.GraficaTiempoRealMYSQL;
import com.pixels.orobackoup.Model.BD.MYSQL.Consultas.TermoCupla.ListaSessionesMYSQL;
import com.pixels.orobackoup.Model.DatosEncapsulados.Sessiones;
import com.pixels.orobackoup.Model.DatosEncapsulados.TermoCalor;
import com.pixels.orobackoup.View.TermoCupla.TermoCupla;
import com.pixels.orobackoup.ViewModel.TermoCupla.WS.GraficaTiempoRealViewModel;

import java.util.List;

public class ListaSEssionViewModel extends ViewModel {
    public MutableLiveData<List<Sessiones>> resultado;
    ListaSEssionViewModel(){
        resultado=new MutableLiveData<>();
    }
    public void reset(){
        resultado=new MutableLiveData<>();
    }
    public LiveData<List<Sessiones>> getResultado(){
        return resultado;
    }
    public void ListadeSessiones(TermoCupla context){
        ListaSessionesMYSQL sv=new ListaSessionesMYSQL(context, ListaSEssionViewModel.this);
    }
}
