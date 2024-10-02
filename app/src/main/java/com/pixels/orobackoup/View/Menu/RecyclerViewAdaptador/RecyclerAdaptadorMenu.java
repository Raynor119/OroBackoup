package com.pixels.orobackoup.View.Menu.RecyclerViewAdaptador;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pixels.orobackoup.Model.DatosEncapsulados.MenuLista;
import com.pixels.orobackoup.R;
import com.pixels.orobackoup.View.Estadistica.Estadistica;
import com.pixels.orobackoup.View.Menu.Menu;
import com.pixels.orobackoup.View.Prenda.ListaPrendas.PrendasListView;
import com.pixels.orobackoup.View.Prenda.PrendaView;

import java.util.List;

public class RecyclerAdaptadorMenu extends RecyclerView.Adapter<RecyclerAdaptadorMenu.ViewHolder> {

    private final Menu MParentActivity;
    private final List<MenuLista> MValues;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int Id = Integer.parseInt((view.getTag() + ""));
            if (Id==1){
                Intent intent=new Intent(MParentActivity, PrendasListView.class);
               // intent.putExtra("codigo","1");
                MParentActivity.startActivity(intent);
            }
            if (Id==2){
                Intent intent=new Intent(MParentActivity, PrendasListView.class);
                // intent.putExtra("codigo","1");
                MParentActivity.startActivity(intent);
            }
            if (Id==3){
                Intent intent=new Intent(MParentActivity, Estadistica.class);
                MParentActivity.startActivity(intent);
            }
            if(Id==4){
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        }
    };

    public RecyclerAdaptadorMenu(Menu ParentActivity,List<MenuLista> mValues){
        this.MParentActivity=ParentActivity;
        this.MValues=mValues;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menucardlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        if(MValues.get(position).getId()==1){
            holder.imagen.setImageResource(R.drawable.baseline_dashboard_customize_24);
        }
        if(MValues.get(position).getId()==2){
            holder.imagen.setImageResource(R.drawable.baseline_dashboard_24);
        }
        if(MValues.get(position).getId()==3){
            holder.imagen.setImageResource(R.drawable.baseline_analytics_24);
        }
        if(MValues.get(position).getId()==4){
            holder.imagen.setImageResource(R.drawable.baseline_power_settings_new_24);
        }
        holder.mContentView.setText(MValues.get(position).getTitulo());
        holder.itemView.setTag(MValues.get(position).getId());
        holder.itemView.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {

        return MValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mContentView;
        public final ImageView imagen;

        public ViewHolder(View view) {
            super(view);
            imagen=(ImageView) view.findViewById(R.id.imagen);
            mContentView = (TextView) view.findViewById(R.id.content);
        }
    }
}
