package com.pixels.orobackoup.View.TermoCupla.GraficasFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.pixels.orobackoup.Model.DatosEncapsulados.TermoCalor;
import com.pixels.orobackoup.R;
import com.pixels.orobackoup.View.TermoCupla.Widget.XYMarkerViewTR;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GraficaLineaTR extends Fragment {

    public static List<TermoCalor> Temperaturas;

    private LineChart GLinear;
    private ArrayList<Entry> data;

    public GraficaLineaTR(){

    }
    public GraficaLineaTR(List<TermoCalor> temperaturas){
        this.Temperaturas=temperaturas;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmentgraficalineal, container, false);
        GLinear = (LineChart) rootView.findViewById(R.id.chart_bottom);
        GenerarGrafica();
        return rootView;
    }
    public void GenerarGrafica(){
        data = new ArrayList<Entry>();
       // data.add(new Entry(0,0));
        boolean verificar=true;
        boolean verificarI=false;
        float maxTemp = Float.MIN_VALUE;
        float minTemp = Float.MAX_VALUE;
        float maxHora = Float.MIN_VALUE;
        float minHora = Float.MAX_VALUE;

        for (int i = 0; i < Temperaturas.size(); i++) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = simpleDateFormat.parse(Temperaturas.get(i).getTiempo());
                float hora = (float) (date.getHours() + (date.getMinutes() * 0.0166667) + (date.getSeconds() * 0.000277778));
                float temp = (float) Temperaturas.get(i).getTemperatura();

                data.add(new Entry(hora, temp));

                if (hora > 22) {
                    verificar = false;
                }
                if (date.getHours() < 1) {
                    verificarI = true;
                }

                // Temperaturas
                if (temp > maxTemp) maxTemp = temp;
                if (temp < minTemp) minTemp = temp;

                // Horas
                if (hora > maxHora) maxHora = hora;
                if (hora < minHora) minHora = hora;

            } catch (Exception e) {
                e.printStackTrace(); // opcional para ver errores
            }
        }

// Al final del bucle:
        System.out.println("Temperatura máxima: " + maxTemp);
        System.out.println("Temperatura mínima: " + minTemp);
        System.out.println("Hora máxima: " + maxHora);
        System.out.println("Hora mínima: " + minHora);

        if(verificarI){
           // data.remove(0);
        }
        if(verificar){
          //  data.add(new Entry(24,0));
        }
        LineDataSet lineDataSet=new LineDataSet(data,"Temperatura");
        lineDataSet.setColors(ColorTemplate.rgb("0090FD"));
        lineDataSet.setCircleColors(ColorTemplate.rgb("0090FD"));
        lineDataSet.setCircleRadius(4f);
        if (Temperaturas.size()>25){
            lineDataSet.setCircleRadius(2f);
        }
        if (Temperaturas.size()>55){
            lineDataSet.setCircleRadius(0f);
        }
        lineDataSet.setValueTextSize(11);
        lineDataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return "";
            }
        });
        ArrayList<ILineDataSet> dataSets=new ArrayList<>();
        dataSets.add(lineDataSet);

        LineData Ldata=new LineData(dataSets);
        Description description=new Description();
        description.setText("");
        GLinear.setData(Ldata);
        GLinear.setDescription(description);
        //Para que las lineas sean Curbas
        List<ILineDataSet> sets = GLinear.getData().getDataSets();
        for (ILineDataSet iSet : sets) {

            LineDataSet set = (LineDataSet) iSet;
            set.setMode(set.getMode() == LineDataSet.Mode.HORIZONTAL_BEZIER
                    ? LineDataSet.Mode.LINEAR
                    :  LineDataSet.Mode.HORIZONTAL_BEZIER);
        }
        GLinear.invalidate();
        GLinear.setDoubleTapToZoomEnabled(true);

        XAxis xAxis =GLinear.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setAxisMaximum(maxHora);
        xAxis.setAxisMinimum(minHora);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                int horas=((int) value);
                int minutos=((int) (60 * (value - horas)));
                float auxm=(60 * (value - horas));
                int segundos=((int) (60 * ( auxm - minutos)));
                String formato="";
                if(minutos>9){
                    if(segundos>0){
                        if (segundos>9){
                            formato=""+horas+":"+minutos+":"+segundos;
                        }else{
                            formato=""+horas+":"+minutos+":0"+segundos;
                        }
                    }else{
                        formato=""+horas+":"+minutos;
                    }
                }else{
                    if(segundos>0){
                        if(segundos>9){
                            formato=""+horas+":0"+minutos+":"+segundos;
                        }else{
                            formato=""+horas+":0"+minutos+":0"+segundos;
                        }
                    }else{
                        formato=""+horas+":0"+minutos;
                    }
                }
                return formato;
            }
        });

        YAxis LeftAxis = GLinear.getAxisLeft();
        LeftAxis.setDrawGridLines(false);
        LeftAxis.setAxisMinimum(minTemp-10);
        LeftAxis.setAxisMaximum(maxTemp+10);
        LeftAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                NumberFormat mFormat = NumberFormat.getNumberInstance();
                return ""+mFormat.format(value)+" °C";
            }
        });

        YAxis rigthAxis = GLinear.getAxisRight();
        rigthAxis.setEnabled(false);

      //  GLinear.animateY(900);
        try {
            XYMarkerViewTR mv = new XYMarkerViewTR(getActivity());
           mv.setChartView(GLinear);
           GLinear.setMarker(mv);
        }catch (Exception e){

        }

    }
}
