package com.example.orobackoup;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int PERMISSIONS_REQUEST_CAMERA = 2;
    CardView btnCamara;
    CardView btnCamarav2;
    ImageView imgView;
    TextInputEditText PesoInicial,pesofinal;
    TextView mermatext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCamara = findViewById(R.id.foto);
        PesoInicial= findViewById(R.id.PesoInicial);
        pesofinal= findViewById(R.id.pesofinal);
        mermatext=findViewById(R.id.merma);
        btnCamarav2 = findViewById(R.id.fotoRealizada);
        imgView = findViewById(R.id.fotoprenda);
        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Request camera permission
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.CAMERA},
                            PERMISSIONS_REQUEST_CAMERA);
                } else {
                    // Permission already granted
                    abrirCamara();
                }
            }
        });
        btnCamarav2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Request camera permission
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.CAMERA},
                            PERMISSIONS_REQUEST_CAMERA);
                } else {
                    // Permission already granted
                    abrirCamara();
                }
            }
        });
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calcularMerma();
            }

            @Override
            public void afterTextChanged(Editable s) { }
        };

        PesoInicial.addTextChangedListener(watcher);
        pesofinal.addTextChangedListener(watcher);

    }
    private void abrirCamara(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent, 1);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imgBitmap = (Bitmap) extras.get("data");
            imgView.setImageBitmap(imgBitmap);
            btnCamara.setVisibility(View.GONE);
            //Toast.makeText(this, "se tomo la foto", Toast.LENGTH_LONG).show();
        }
    }
    private void calcularMerma() {
        String pesoInicialStr = PesoInicial.getText().toString();
        String pesoFinalStr = pesofinal.getText().toString();

        if (!pesoInicialStr.isEmpty() && !pesoFinalStr.isEmpty()) {
            try {
                double pesoInicial = Double.parseDouble(pesoInicialStr);
                double pesoFinal = Double.parseDouble(pesoFinalStr);
                double merma = pesoInicial - pesoFinal;
                mermatext.setText(String.format("La merma es: %.2f", merma));
            } catch (NumberFormatException e) {
                // Manejo de errores si el formato del número es incorrecto
                mermatext.setText("La merma es:: 0");
            }
        } else {
            mermatext.setText("La merma es:a: 0");
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_CAMERA) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
                abrirCamara();
            } else {
                // Permission denied
                Toast.makeText(this, "dele los permisos de la camara a la aplicacion", Toast.LENGTH_LONG).show();
            }
        }
    }
}