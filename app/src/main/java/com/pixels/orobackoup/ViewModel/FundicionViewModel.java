package com.pixels.orobackoup.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pixels.orobackoup.Model.BD.MYSQL.Consultas.FundicionMYSQL;
import com.pixels.orobackoup.Model.DatosEncapsulados.Fundicion;

import java.util.List;

public class FundicionViewModel extends ViewModel {
    public MutableLiveData<Boolean> resultado;
    public FundicionViewModel(){
        this.resultado=new MutableLiveData<>();
    }
    public void reset(){
        this.resultado=new MutableLiveData<>();
    }
    public LiveData<Boolean> getResultado(){
        return resultado;
    }

    public void guardarfundicion(Context context, List<Fundicion> datos){
        FundicionMYSQL BD=new FundicionMYSQL(context,FundicionViewModel.this,datos);
    }

}
