package com.pixels.orobackoup.View.Estadistica.TabLayout.Widgets;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.pixels.orobackoup.Model.DatosEncapsulados.DatosColumn;
import com.pixels.orobackoup.R;
import java.text.NumberFormat;
import java.util.List;

public class XYMarkerView extends MarkerView {
    private final TextView tvContent;
    private final List<DatosColumn> DatosP;

    public XYMarkerView(Context context, List<DatosColumn> datosP) {
        super(context, R.layout.marker_view);
        tvContent = findViewById(R.id.tvContent);
        this.DatosP=datosP;
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {

        int i=(int) e.getX();
        NumberFormat mFormat = NumberFormat.getNumberInstance();
        tvContent.setText(""+DatosP.get(i).getNombre()+"\n"+DatosP.get(i).getCodigo()+"\nTotal Vendido: $ "+mFormat.format(e.getY()));

        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
