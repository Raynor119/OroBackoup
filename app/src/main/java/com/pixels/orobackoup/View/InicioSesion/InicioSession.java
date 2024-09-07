package com.pixels.orobackoup.View.InicioSesion;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.pixels.orobackoup.R;

public class InicioSession extends AppCompatActivity {

    private TextInputEditText Usuario;
    private TextInputLayout EditUsuario;
    private TextInputEditText Contr;
    private TextInputLayout EditContr;
    private CheckBox GuardadoSesion;
    private Button BInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_session);
        Usuario=findViewById(R.id.usuario);
        EditUsuario=findViewById(R.id.Uusuario);
        Contr=findViewById(R.id.contra);
        EditContr=findViewById(R.id.Ccontra);
        GuardadoSesion=findViewById(R.id.CheckGS);
        BInicio=findViewById(R.id.ButtonI);
        TextWatcher textWatcherU = new  TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                EditUsuario.setErrorEnabled(false);
                if (Usuario.getText().toString().length()>33){
                    EditUsuario.setError("El Usuario no puede superar los 33 caracteres");
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        TextWatcher textWatcherC = new  TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                EditContr.setErrorEnabled(false);
                if(Contr.getText().toString().length()>18){
                    EditContr.setError("La Contraseña no puede superar los 18 caracteres");
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        Usuario.addTextChangedListener(textWatcherU);
        Contr.addTextChangedListener(textWatcherC);
        BInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Usuario.getText().toString().equals("") || Contr.getText().toString().equals("") || Usuario.getText().toString().length()>33 || Contr.getText().toString().length()>18){
                    if(Usuario.getText().toString().equals("")){
                        EditUsuario.setError("Digite el Usuario");
                    }
                    if(Contr.getText().toString().equals("")){
                        EditContr.setError("Digite la Contraseña");
                    }
                    if (Usuario.getText().toString().length()>33){
                        EditUsuario.setError("El Usuario no puede superar los 33 caracteres");
                    }
                    if(Contr.getText().toString().length()>18){
                        EditContr.setError("La Contraseña no puede superar los 18 caracteres");
                    }
                }else{
                    Toast.makeText(InicioSession.this, "Iniciando Session", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}