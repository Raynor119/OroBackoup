package com.pixels.orobackoup.View.Estadistica.TabLayout.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.pixels.orobackoup.R;
import com.pixels.orobackoup.View.Estadistica.TabLayout.GraficasFragment.GraficaColumnaD;

import java.util.Calendar;

public class FragmentDiarias extends Fragment {
    public CardView mostrarF,mostrarG,mostrarL,mostrarLL,mostrarE,mostrarP;
    public LinearLayout LayoutF,LayoutG,LayoutL,LayoutLL,LayoutE,LayoutP;
    private TextInputEditText calendarioEditText;
    public FragmentDiarias(){

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
        calendarioEditText=(TextInputEditText) rootView.findViewById(R.id.fecha);
        calendarioEditText.setEnabled(false);
        calendarioEditText.setText(getDia());
        CardView Bcalendario=(CardView) rootView.findViewById(R.id.calendario);
        Bcalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dia=1,mes=1,anno;
                int cont=0;
                String date="";
                for(int i=0;i<calendarioEditText.getText().length();i++){
                    if((calendarioEditText.getText().charAt(i)+"").equals("/")){
                        if(cont==0){
                            dia=Integer.parseInt(date);
                            date="";
                        }
                        if (cont==1){
                            mes=Integer.parseInt(date);
                            date="";
                        }
                        cont++;
                    }else {
                        date = date + (calendarioEditText.getText().charAt(i));
                    }
                }
                anno=Integer.parseInt(date);
                DatePickerDialog datePickerDialog= new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int Mes, int Dia) {
                        int mes=Mes+1;
                        calendarioEditText.setText(Dia+"/"+(mes)+"/"+year);
                        //iniciarRecyclerView(calendarioEditText.getText().toString());
                        iniciarGraficaColumnas(calendarioEditText.getText().toString());
                    }
                },anno,mes-1,dia);
                datePickerDialog.show();
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
        iniciarGraficaColumnas(calendarioEditText.getText().toString());
        return rootView;
    }

    public void iniciarGraficaColumnas(String Ffecha){
        try{
            GraficaColumnaD graficaColumnaF=new GraficaColumnaD(Ffecha,"Fundicion");
            GraficaColumnaD graficaColumnaG=new GraficaColumnaD(Ffecha,"Electropulidobomba");
            GraficaColumnaD graficaColumnaL=new GraficaColumnaD(Ffecha,"Limado");
            GraficaColumnaD graficaColumnaLL=new GraficaColumnaD(Ffecha,"Lijado");
            GraficaColumnaD graficaColumnaE=new GraficaColumnaD(Ffecha,"Engaste");
            GraficaColumnaD graficaColumnaP=new GraficaColumnaD(Ffecha,"Pulido");

            Handler handler = new Handler();
            int delay = 500; // Tiempo en milisegundos entre cada fragment
            handler.postDelayed(() -> {
                getChildFragmentManager().beginTransaction().replace(R.id.containerF, graficaColumnaF).commitAllowingStateLoss();
            }, delay);
            handler.postDelayed(() -> {
                getChildFragmentManager().beginTransaction().replace(R.id.containerG, graficaColumnaG).commitAllowingStateLoss();
            }, delay * 2);
            handler.postDelayed(() -> {
                getChildFragmentManager().beginTransaction().replace(R.id.containerL, graficaColumnaL).commitAllowingStateLoss();
            }, delay * 3);
            handler.postDelayed(() -> {
                getChildFragmentManager().beginTransaction().replace(R.id.containerLL, graficaColumnaLL).commitAllowingStateLoss();
            }, delay * 4);
            handler.postDelayed(() -> {
                getChildFragmentManager().beginTransaction().replace(R.id.containerE, graficaColumnaE).commitAllowingStateLoss();
            }, delay * 5);
            handler.postDelayed(() -> {
                getChildFragmentManager().beginTransaction().replace(R.id.containerP, graficaColumnaP).commitAllowingStateLoss();
            }, delay * 6);
        }catch (Exception e){

        }
    }
    public String getDia(){
        Calendar calendar= Calendar.getInstance();
        int dia=calendar.get(Calendar.DAY_OF_MONTH);
        int mes=calendar.get(Calendar.MONTH)+1;
        int anno=calendar.get(Calendar.YEAR);
        return dia+"/"+(mes)+"/"+anno;
    }
}
