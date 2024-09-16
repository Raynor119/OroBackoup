package com.pixels.orobackoup.View.Menu.AlertDialog;

import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.pixels.orobackoup.R;
import com.pixels.orobackoup.View.Menu.Menu;

public class alertdeagregarprenda {
    public Menu Context;
    public int[] i = {0};
    public AlertDialog dialog;
    public TextInputEditText nombre;
    public TextInputLayout Cnombre;
    public int controlador=0;
    public alertdeagregarprenda(Menu contextt){
        this.Context=contextt;
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
                    alertCancelar();
                }
            }
        });

    }
}
