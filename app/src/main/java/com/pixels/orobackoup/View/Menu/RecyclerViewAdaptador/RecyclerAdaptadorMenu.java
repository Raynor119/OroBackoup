package com.pixels.orobackoup.View.Menu.RecyclerViewAdaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pixels.orobackoup.Model.DatosEncapsulados.MenuLista;
import com.pixels.orobackoup.R;
import com.pixels.orobackoup.View.Menu.Menu;

import java.util.List;

public class RecyclerAdaptadorMenu extends RecyclerView.Adapter<RecyclerAdaptadorMenu.ViewHolder> {

    private final Menu MParentActivity;
    private final List<MenuLista> MValues;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int Id = Integer.parseInt((view.getTag() + ""));
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
            holder.imagen.setImageResource(R.mipmap.caja);
        }
        if(MValues.get(position).getId()==2){
            holder.imagen.setImageResource(R.mipmap.productos);
        }
        if(MValues.get(position).getId()==3){
            holder.imagen.setImageResource(R.mipmap.ventas);
        }
        if(MValues.get(position).getId()==4){
            holder.imagen.setImageResource(R.mipmap.ajustes);
        }
        if(MValues.get(position).getId()==4){
            holder.imagen.setImageResource(R.mipmap.salir);
        }
        holder.mContentView.setText(MValues.get(position).getTitulo());
        holder.itemView.setTag(MValues.get(position).getId());
        holder.itemView.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
        return MValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mContentView;
        final ImageView imagen;

        ViewHolder(View view) {
            super(view);
            imagen=(ImageView) view.findViewById(R.id.imagen);
            mContentView = (TextView) view.findViewById(R.id.content);
        }
    }
}
