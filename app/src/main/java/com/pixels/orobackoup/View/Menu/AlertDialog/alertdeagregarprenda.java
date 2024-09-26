package com.pixels.orobackoup.View.Menu.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.pixels.orobackoup.R;
import com.pixels.orobackoup.View.InicioSesion.AlertDialog.AlertCarga;
import com.pixels.orobackoup.View.Menu.Menu;
import com.pixels.orobackoup.View.Prenda.PrendaView;
import com.pixels.orobackoup.ViewModel.Menu.AgregarPrendaViewModel;

public class alertdeagregarprenda {
    public Menu Context;
    public int[] i = {0};
    public AlertDialog dialog;
    public TextInputEditText nombre;
    public TextInputLayout Cnombre;
    public String CodigoU;
    public int controlador=0;
    public alertdeagregarprenda(Menu contextt,String codigoU){
        this.Context=contextt;
        this.CodigoU=codigoU;
    }
    public void alertCancelar(){
        dialog.cancel();
    }
    public void pedirnombre(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Context);
        builder.setCancelable(false);
        LayoutInflater inflater= Context.getLayoutInflater();
        View view=inflater.inflate(R.layout.alertagregarprenda, null);
        builder.setView(view);
        builder.setTitle("Digite el nombre de la prenda");
        builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.cancel();
            }
        });
        dialog = builder.create();
        dialog.show();
        Button guardar= view.findViewById(R.id.ButtonI);

        nombre= view.findViewById(R.id.nombreprenda);
        Cnombre=view.findViewById(R.id.Editprenda);
        TextWatcher textWatcherN = new  TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Cnombre.setErrorEnabled(false);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        nombre.addTextChangedListener(textWatcherN);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nombre.getText().toString().equals("")){
                    Cnombre.setError("Digite el nombre de la prenda");
                }else{
                    AlertCarga carga =new AlertCarga(Context);
                    AgregarPrendaViewModel prendaViewModel= ViewModelProviders.of(Context).get(AgregarPrendaViewModel.class);
                    prendaViewModel.reset();
                    carga.Cargar();
                    prendaViewModel.guardarprenda(Context,CodigoU,nombre.getText().toString());
                    Observer<Integer> observernombreregi=new Observer<Integer>() {
                        @Override
                        public void onChanged(Integer aBoolean) {
                            carga.setInicio(1);
                            carga.Cerrar();
                            if(aBoolean!=0){
                                alertCancelar();
                                Intent intent=new Intent(Context, PrendaView.class);
                                intent.putExtra("codigo",aBoolean+"");
                                intent.putExtra("estado","N"+"");
                                Context.startActivity(intent);
                                //Toast.makeText(Context, "La prenda se ha registro codigo:"+aBoolean, Toast.LENGTH_SHORT).show();
                            }else{
                                Cnombre.setError("Error al registrar la prenda");
                            }
                        }
                    };
                    prendaViewModel.getResultado().observe(Context,observernombreregi);
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(carga.getInicio()==0){
                                Toast.makeText(Context, "Error no hay conexion", Toast.LENGTH_LONG).show();
                                carga.Cerrar();
                                alertCancelar();
                            }
                        }
                    },12000);
                }
            }
        });

    }
}
