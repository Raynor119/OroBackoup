package com.pixels.orobackoup.View.Estadistica.TabLayout.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
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
        View rootView = inflater.inflate(R.layout.fragment_merma, container, false);
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
        iniciarGraficaColumnas(getMes());

        calendarioEditText=(TextInputEditText) rootView.findViewById(R.id.fecha);
        calendarioEditText.setEnabled(false);
        calendarioEditText.setText(getMes());
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

        return rootView;
    }

    public void iniciarGraficaColumnas(String Ffecha){
        try{
            GraficaColumnaD graficaColumnaF=new GraficaColumnaD(Ffecha,"");
            GraficaColumnaD graficaColumnaG=new GraficaColumnaD(Ffecha,"");
            GraficaColumnaD graficaColumnaL=new GraficaColumnaD(Ffecha,"");
            GraficaColumnaD graficaColumnaLL=new GraficaColumnaD(Ffecha,"");
            GraficaColumnaD graficaColumnaE=new GraficaColumnaD(Ffecha,"");
            GraficaColumnaD graficaColumnaP=new GraficaColumnaD(Ffecha,"");
            graficaColumnaF.Fecha=Ffecha;
            graficaColumnaG.Fecha=Ffecha;
            graficaColumnaL.Fecha=Ffecha;
            graficaColumnaLL.Fecha=Ffecha;
            graficaColumnaE.Fecha=Ffecha;
            graficaColumnaP.Fecha=Ffecha;
            getChildFragmentManager().beginTransaction().replace(R.id.containerF,graficaColumnaF).commit();
            getChildFragmentManager().beginTransaction().replace(R.id.containerG,graficaColumnaG).commit();
            getChildFragmentManager().beginTransaction().replace(R.id.containerL,graficaColumnaL).commit();
            getChildFragmentManager().beginTransaction().replace(R.id.containerLL,graficaColumnaLL).commit();
            getChildFragmentManager().beginTransaction().replace(R.id.containerE,graficaColumnaE).commit();
            getChildFragmentManager().beginTransaction().replace(R.id.containerP,graficaColumnaP).commit();
        }catch (Exception e){

        }
    }
    public String getMes(){
        Calendar calendar= Calendar.getInstance();
        int mes=(calendar.get(Calendar.MONTH)+1);
        int anno=calendar.get(Calendar.YEAR);
        return mes+"/"+anno;
    }
}
