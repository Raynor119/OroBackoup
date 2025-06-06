package com.pixels.orobackoup.View.Estadistica.TabLayout.GraficasFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.pixels.orobackoup.Model.DatosEncapsulados.DatosColumn;
import com.pixels.orobackoup.R;
import com.pixels.orobackoup.View.Estadistica.TabLayout.Widgets.XYMarkerView;
import com.pixels.orobackoup.ViewModel.Estadistica.GraficaBarrasDViewModel;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class GraficaColumnaM extends Fragment {
    public static String Fecha;
    private BarChart GColumna;
    private static String EstadoProceso;
    private ArrayList<BarEntry> barEntryArrayList;

    public GraficaColumnaM(){

    }
    public GraficaColumnaM(String fecha, String proceso){
        this.EstadoProceso=proceso;
        this.Fecha=fecha;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmentgraficacolumna, container, false);
        GColumna = (BarChart) rootView.findViewById(R.id.chart_bottom);
        GenerarGrafica();
        return rootView;
    }
    public void GenerarGrafica(){
        GraficaBarrasDViewModel productos= ViewModelProviders.of(getActivity()).get(GraficaBarrasDViewModel.class);
        productos.reset();
        productos.buscarVProductos(getActivity(),getConsulta(Fecha));
        Observer<List<DatosColumn>> observer= new Observer<List<DatosColumn>>() {
            @Override
            public void onChanged(List<DatosColumn> datosColumns) {
                barEntryArrayList=new ArrayList<>();
                for(int i=0;i<datosColumns.size();i++){
                    barEntryArrayList.add(new BarEntry(i,datosColumns.get(i).getTotalV()));

                }
                BarDataSet barDataSet = new BarDataSet(barEntryArrayList,"Porcentaje de la Merma");
                barDataSet.setColors(ColorTemplate.rgb("0090FD"));
                barDataSet.setValueTextSize(11);
                barDataSet.setValueFormatter(new ValueFormatter() {
                    @Override
                    public String getFormattedValue(float value) {
                        return "";
                    }
                });
                Description description=new Description();
                description.setText("");
                GColumna.setDescription(description);
                BarData barData=new BarData(barDataSet);
                GColumna.setData(barData);

                XAxis xAxis= GColumna.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setValueFormatter(new ValueFormatter() {
                    @Override
                    public String getFormattedValue(float value) {
                        return "Prenda #"+((int)value+1);
                    }
                });
                xAxis.setDrawGridLines(false);
                xAxis.setDrawAxisLine(false);
                xAxis.setGranularity(1f);
                xAxis.setLabelCount(datosColumns.size());
                xAxis.setLabelRotationAngle(0);


                YAxis leftAxis = GColumna.getAxisLeft();
                leftAxis.setValueFormatter(new ValueFormatter() {
                    @Override
                    public String getFormattedValue(float value) {
                        NumberFormat mFormat = NumberFormat.getNumberInstance();
                        return "%` "+mFormat.format(value);
                    }
                });
                GColumna.animateX(900);

                YAxis rigthAxis = GColumna.getAxisRight();
                rigthAxis.setEnabled(false);

                GColumna.animateY(900);
                GColumna.invalidate();
                GColumna.setDoubleTapToZoomEnabled(false);
                try {
                    XYMarkerView mv = new XYMarkerView(getActivity(),datosColumns);
                    mv.setChartView(GColumna);
                    GColumna.setMarker(mv);
                }catch (Exception e){

                }

            }
        };
        productos.getResultado().observe(getActivity(),observer);
    }

    public String getConsulta(String fechaE){

        return "";
    }
}
