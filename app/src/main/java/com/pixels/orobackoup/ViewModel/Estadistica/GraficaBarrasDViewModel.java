package com.pixels.orobackoup.ViewModel.Estadistica;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pixels.orobackoup.Model.DatosEncapsulados.DatosColumn;

import java.util.ArrayList;
import java.util.List;

public class GraficaBarrasDViewModel extends ViewModel {
    public MutableLiveData<List<DatosColumn>> resultado;
    public GraficaBarrasDViewModel(){
        resultado=new MutableLiveData<>();
    }
    public void reset(){
        resultado=new MutableLiveData<>();
    }
    public LiveData<List<DatosColumn>> getResultado(){
        return resultado;
    }
    public void buscarVProductos(Context context, String Consulta){
        List<DatosColumn> listcolumnas=new ArrayList<>();
        listcolumnas.add(new DatosColumn("1","Snoppy",2));
        listcolumnas.add(new DatosColumn("2","Solitario Esmeralda",1));
        listcolumnas.add(new DatosColumn("3","Anillo Black Flag",4));
        listcolumnas.add(new DatosColumn("4","LLaves",3));
        listcolumnas.add(new DatosColumn("5","solitario de pear",9));
        resultado.setValue(listcolumnas);
    }

}
