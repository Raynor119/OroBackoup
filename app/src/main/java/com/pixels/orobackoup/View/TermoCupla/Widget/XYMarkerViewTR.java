package com.pixels.orobackoup.View.TermoCupla.Widget;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.pixels.orobackoup.R;

import java.text.NumberFormat;

public class XYMarkerViewTR extends MarkerView {

    private final TextView tvContent;


    public XYMarkerViewTR(Context context) {
        super(context, R.layout.marker_view);
        tvContent = findViewById(R.id.tvContent);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        float value=e.getX();
        int horas=((int) value);
        int minutos=((int) (60 * (value - horas)));

        String formato="";
        if(minutos>9){
            formato=""+horas+":"+minutos;
        }else{
            formato=""+horas+":0"+minutos;
        }
        NumberFormat mFormat = NumberFormat.getNumberInstance();
        formato="Hora: "+formato+"\nTemperatura: "+mFormat.format(e.getY())+" °C";
        tvContent.setText(""+formato);

        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}