package com.pixels.orobackoup.ViewModel.Estadistica;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pixels.orobackoup.Model.BD.MYSQL.Consultas.Estadistica.ConsultasMermaMYSQL;
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
        System.out.println("----------------------------------------------------------------------------------------------Consulta: "+Consulta);

        ConsultasMermaMYSQL mysql=new ConsultasMermaMYSQL(context,Consulta,GraficaBarrasDViewModel.this);
    }

}
