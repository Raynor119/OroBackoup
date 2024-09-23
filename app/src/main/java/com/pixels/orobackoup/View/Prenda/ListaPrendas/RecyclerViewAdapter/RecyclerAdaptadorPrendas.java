package com.pixels.orobackoup.View.Prenda.ListaPrendas.RecyclerViewAdapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pixels.orobackoup.Model.DatosEncapsulados.ListaPrenda;
import com.pixels.orobackoup.R;
import com.pixels.orobackoup.View.Prenda.ListaPrendas.PrendasListView;
import com.pixels.orobackoup.View.Prenda.PrendaView;

import java.util.List;

public class RecyclerAdaptadorPrendas extends RecyclerView.Adapter<RecyclerAdaptadorPrendas.ViewHolder> {
    private final PrendasListView MParentActivity;
    private final List<ListaPrenda> MValues;

    public RecyclerAdaptadorPrendas(PrendasListView ParentActivity,List<ListaPrenda> mValues){
        this.MParentActivity=ParentActivity;
        this.MValues=mValues;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_prendas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imagen.setImageResource(R.drawable.baseline_dashboard_24);
        holder.mContentView.setText(MValues.get(position).getNombre());
        holder.fechaR.setText("Registro: "+MValues.get(position).getFecha());
        final int PPossition=position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(MParentActivity, "codigo: "+MValues.get(PPossition).getCodigo(), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MParentActivity, PrendaView.class);
                intent.putExtra("codigo",MValues.get(PPossition).getCodigo()+"");
                MParentActivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return MValues.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mContentView,fechaR;
        public final ImageView imagen;

        public ViewHolder(View view) {
            super(view);
            imagen=(ImageView) view.findViewById(R.id.imagen);
            mContentView = (TextView) view.findViewById(R.id.content);
            fechaR = (TextView) view.findViewById(R.id.fecha);
        }
    }
}
