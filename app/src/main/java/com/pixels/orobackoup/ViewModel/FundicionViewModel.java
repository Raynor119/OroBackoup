package com.pixels.orobackoup.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pixels.orobackoup.Model.BD.MYSQL.Consultas.FundicionFotoMYSQL;
import com.pixels.orobackoup.Model.BD.MYSQL.Consultas.FundicionMYSQL;
import com.pixels.orobackoup.Model.DatosEncapsulados.Fundicion;

import java.util.List;

public class FundicionViewModel extends ViewModel {
    public MutableLiveData<Boolean> resultado;
    public MutableLiveData<byte[]> resultadov2;
    public FundicionViewModel(){
        this.resultado=new MutableLiveData<>();
        this.resultadov2=new MutableLiveData<>();
    }
    public void reset(){
        this.resultado=new MutableLiveData<>();
        this.resultadov2=new MutableLiveData<>();
    }
    public LiveData<Boolean> getResultado(){
        return resultado;
    }
    public LiveData<byte[]> getResultadov2(){
        return resultadov2;
    }

    public void guardarfundicion(Context context, List<Fundicion> datos){
        FundicionMYSQL BD=new FundicionMYSQL(context,FundicionViewModel.this,datos);
    }
    public void verfoto(Context context, int codigodeprenda){
        FundicionFotoMYSQL BD=new FundicionFotoMYSQL(context,FundicionViewModel.this,codigodeprenda);
    }

}
