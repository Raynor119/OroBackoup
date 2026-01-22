package com.pixels.orobackoup.View.Estadistica.TabLayout.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.pixels.orobackoup.R;
import com.pixels.orobackoup.View.Estadistica.TabLayout.Calendario.MesAnnoPickerDialog;
import com.pixels.orobackoup.View.Estadistica.TabLayout.GraficasFragment.GraficaColumnaD;
import com.pixels.orobackoup.View.Estadistica.TabLayout.GraficasFragment.GraficaColumnaM;

import java.util.Calendar;

public class FragmentMes extends Fragment {
    public MesAnnoPickerDialog pd;
    public CardView mostrarF,mostrarG,mostrarL,mostrarLL,mostrarE,mostrarP;
    public LinearLayout LayoutF,LayoutG,LayoutL,LayoutLL,LayoutE,LayoutP;
    public TextInputEditText calendarioEditText;

    public FragmentMes(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mermam, container, false);
        LayoutF=rootView.findViewById(R.id.LayoutF);
        LayoutG=rootView.findViewById(R.id.LayoutG);
        LayoutL=rootView.findViewById(R.id.LayoutL);
        LayoutLL=rootView.findViewById(R.id.Layoutll);
        LayoutE=rootView.findViewById(R.id.LayoutE);
        LayoutP=rootView.findViewById(R.id.LayoutP);
        mostrarF=rootView.findViewById(R.id.mostrarF);
        mostrarG=rootView.findViewById(R.id.mostrarG);
        mostrarL=rootView.findViewById(R.id.mostrarL);
        mostrarLL=rootView.findViewById(R.id.mostrarLL);
        mostrarE=rootView.findViewById(R.id.mostrarE);
        mostrarP=rootView.findViewById(R.id.mostrarP);
        LayoutF.setVisibility(LinearLayout.GONE);
        LayoutG.setVisibility(LinearLayout.GONE);
        LayoutL.setVisibility(LinearLayout.GONE);
        LayoutLL.setVisibility(LinearLayout.GONE);
        LayoutE.setVisibility(LinearLayout.GONE);
        LayoutP.setVisibility(LinearLayout.GONE);
        calendarioEditText=(TextInputEditText) rootView.findViewById(R.id.fecha);
        calendarioEditText.setEnabled(false);
        calendarioEditText.setText(getTMes());
        CardView Bcalendario=(CardView) rootView.findViewById(R.id.calendario);
        Bcalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mes=1,anno;
                int cont=0;
                String date="";
                for(int i=0;i<calendarioEditText.getText().length();i++){
                    if((calendarioEditText.getText().charAt(i)+"").equals("/")){
                        if (cont==0){
                            mes=Integer.parseInt(date);
                            date="";
                        }
                        cont++;
                    }else {
                        date = date + (calendarioEditText.getText().charAt(i));
                    }
                }
                anno=Integer.parseInt(date);
                pd = new MesAnnoPickerDialog(FragmentMes.this,mes,anno);
                pd.SelectFecha();
            }
        });
        mostrarF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LayoutF.getVisibility()== LinearLayout.GONE){
                    LayoutF.setVisibility(LinearLayout.VISIBLE);
                }else{
                    LayoutF.setVisibility(LinearLayout.GONE);
                }
            }
        });
        mostrarG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LayoutG.getVisibility()== LinearLayout.GONE){
                    LayoutG.setVisibility(LinearLayout.VISIBLE);
                }else{
                    LayoutG.setVisibility(LinearLayout.GONE);
                }
            }
        });
        mostrarL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LayoutL.getVisibility()== LinearLayout.GONE){
                    LayoutL.setVisibility(LinearLayout.VISIBLE);
                }else{
                    LayoutL.setVisibility(LinearLayout.GONE);
                }
            }
        });
        mostrarLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LayoutLL.getVisibility()== LinearLayout.GONE){
                    LayoutLL.setVisibility(LinearLayout.VISIBLE);
                }else{
                    LayoutLL.setVisibility(LinearLayout.GONE);
                }
            }
        });
        mostrarE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LayoutE.getVisibility()== LinearLayout.GONE){
                    LayoutE.setVisibility(LinearLayout.VISIBLE);
                }else{
                    LayoutE.setVisibility(LinearLayout.GONE);
                }
            }
        });
        mostrarP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LayoutP.getVisibility()== LinearLayout.GONE){
                    LayoutP.setVisibility(LinearLayout.VISIBLE);
                }else{
                    LayoutP.setVisibility(LinearLayout.GONE);
                }
            }
        });
        iniciarGraficaColumnasM(getMes());
        return rootView;
    }

    public void reiniciar(){
        iniciarGraficaColumnasM(getMes());
    }
    public void iniciarGraficaColumnasM(String Ffecha){
        try{
            Handler handlerv2 = new Handler();
            int delay = 2000; // Tiempo en milisegundos entre cada fragment
            handlerv2.postDelayed(() -> {
                GraficaColumnaM graficaColumnaFM=new GraficaColumnaM(Ffecha,"Fundicion");
                getChildFragmentManager().beginTransaction().replace(R.id.containerFM, graficaColumnaFM).commitAllowingStateLoss();
            }, delay);
            handlerv2.postDelayed(() -> {
                GraficaColumnaM graficaColumnaGM=new GraficaColumnaM(Ffecha,"Electropulidobomba");
                getChildFragmentManager().beginTransaction().replace(R.id.containerGM, graficaColumnaGM).commitAllowingStateLoss();
            }, delay * 2);
            handlerv2.postDelayed(() -> {
                GraficaColumnaM graficaColumnaLM=new GraficaColumnaM(Ffecha,"Limado");
                getChildFragmentManager().beginTransaction().replace(R.id.containerLM, graficaColumnaLM).commitAllowingStateLoss();
            }, delay * 3);
            handlerv2.postDelayed(() -> {
                GraficaColumnaM graficaColumnaLLM=new GraficaColumnaM(Ffecha,"Lijado");
                getChildFragmentManager().beginTransaction().replace(R.id.containerLLM, graficaColumnaLLM).commitAllowingStateLoss();
            }, delay * 4);
            handlerv2.postDelayed(() -> {
                GraficaColumnaM graficaColumnaEM=new GraficaColumnaM(Ffecha,"Engaste");
                getChildFragmentManager().beginTransaction().replace(R.id.containerEM, graficaColumnaEM).commitAllowingStateLoss();
            }, delay * 5);
            handlerv2.postDelayed(() -> {
                GraficaColumnaM graficaColumnaPM=new GraficaColumnaM(Ffecha,"Pulido");
                getChildFragmentManager().beginTransaction().replace(R.id.containerPM, graficaColumnaPM).commitAllowingStateLoss();
            }, delay * 6);
        }catch (Exception e){

        }
    }
    public String getMes(){
        Calendar calendar= Calendar.getInstance();
        int mes=(calendar.get(Calendar.MONTH)+1);
        int anno=calendar.get(Calendar.YEAR);
        return "10"+"/"+mes+"/"+anno;
    }
    public String getTMes(){
        Calendar calendar= Calendar.getInstance();
        int mes=(calendar.get(Calendar.MONTH)+1);
        int anno=calendar.get(Calendar.YEAR);
        return mes+"/"+anno;
    }
}
