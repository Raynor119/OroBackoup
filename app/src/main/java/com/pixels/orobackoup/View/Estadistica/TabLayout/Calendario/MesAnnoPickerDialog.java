package com.pixels.orobackoup.View.Estadistica.TabLayout.Calendario;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.pixels.orobackoup.R;
import com.pixels.orobackoup.View.Estadistica.TabLayout.Fragment.FragmentMes;

public class MesAnnoPickerDialog{

    private DatePickerDialog.OnDateSetListener listener;
    public static FragmentMes Context;
    private NumberPicker mesPicker;
    private NumberPicker annoPicker;
    private int anno,mes;

    public MesAnnoPickerDialog(FragmentMes context,int Mes,int Anno){
        this.Context=context;
        this.mes=Mes;
        this.anno=Anno;
        this.listener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int anno, int mes, int i2) {
                Context.calendarioEditText.setText(mes+"/"+anno);

                Context.iniciarGraficaColumnas(Context.calendarioEditText.getText().toString());
            }
        };
    }

    public void  SelectFecha() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Context.getActivity());
        LayoutInflater inflater = Context.getActivity().getLayoutInflater();
        View dialog = inflater.inflate(R.layout.date_picker_dialog, null);
        mesPicker = (NumberPicker) dialog.findViewById(R.id.picker_month);
        annoPicker = (NumberPicker) dialog.findViewById(R.id.picker_year);

        TextView mesTextView=(TextView) dialog.findViewById(R.id.mes);
        TextView annonTextView=(TextView) dialog.findViewById(R.id.annon);

        mesPicker.setMinValue(1);
        mesPicker.setMaxValue(12);
        mesPicker.setValue(mes);

        annoPicker.setMinValue(1900);
        annoPicker.setMaxValue(2099);
        annoPicker.setValue(anno);


        VerificarMes(mesTextView,mesPicker);
        annonTextView.setText(""+annoPicker.getValue());

        annoPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                annonTextView.setText(""+numberPicker.getValue());
            }
        });

        mesPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                VerificarMes(mesTextView,numberPicker);
            }
        });

        builder.setView(dialog)
                .setPositiveButton("aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                       listener.onDateSet(null, annoPicker.getValue(), mesPicker.getValue(), 0);
                    }
                })
                .setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        AlertDialog alert =builder.create();
        alert.show();
    }
     public void VerificarMes(TextView m,NumberPicker n){
         if(n.getValue()==1){
             m.setText("Enero");
         }
         if(n.getValue()==2){
             m.setText("Febrero");
         }
         if(n.getValue()==3){
             m.setText("Marzo");
         }
         if(n.getValue()==4){
             m.setText("Abril");
         }
         if(n.getValue()==5){
             m.setText("Mayo");
         }
         if(n.getValue()==6){
             m.setText("Junio");
         }
         if(n.getValue()==7){
             m.setText("Julio");
         }
         if(n.getValue()==8){
             m.setText("Agosto");
         }
         if(n.getValue()==9){
             m.setText("Septiembre");
         }
         if(n.getValue()==10){
             m.setText("Octubre");
         }
         if(n.getValue()==11){
             m.setText("Noviembre");
         }
         if(n.getValue()==12){
             m.setText("Diciembre");
         }
     }
}