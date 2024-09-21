package com.pixels.orobackoup.View.Prenda;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import java.io.ByteArrayOutputStream;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.itextpdf.text.pdf.parser.Line;
import com.pixels.orobackoup.Model.DatosEncapsulados.DatosPrenda;
import com.pixels.orobackoup.R;
import com.pixels.orobackoup.View.InicioSesion.AlertDialog.AlertCarga;
import com.pixels.orobackoup.ViewModel.Prenda.DatosEstadosViewModel;
import com.pixels.orobackoup.ViewModel.Prenda.DatosPrendaViewModel;
import android.graphics.Matrix;

import java.io.FileOutputStream;
import java.io.OutputStream;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class PrendaView extends AppCompatActivity {
    public LinearLayout LayoutF,LayoutG,LayoutL,LayoutLL,LayoutE,LayoutP;
    public TextInputEditText nombreP;
    String CodigoP;
    public CardView mostrarF,mostrarG,mostrarL,mostrarLL,mostrarE,mostrarP;

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int PERMISSIONS_REQUEST_CAMERA = 2;
    CardView btnCamaraF;
    CardView btnCamarav2F;
    CardView btnVerFotoF;
    ImageView imgViewF;
    TextInputEditText PesoInicialF,pesofinalF;
    TextInputLayout LYPesoInicialF,LYpesofinalF;
    TextView mermatextF;
    Button botonF;
    LinearLayout LFechaF;
    TextView FechaF;
    Bitmap imgBitmapF=null;

    CardView btnCamaraG;
    CardView btnCamarav2G;
    CardView btnVerFotoG;
    ImageView imgViewG;
    TextInputEditText PesoInicialG,pesofinalG;
    TextInputLayout LYPesoInicialG,LYpesofinalG;
    TextView mermatextG;
    Button botonG;
    LinearLayout LFechaG;
    TextView FechaG;
    Bitmap imgBitmapG=null;


    CardView btnCamaraL;
    CardView btnCamarav2L;
    CardView btnVerFotoL;
    ImageView imgViewL;
    TextInputEditText PesoInicialL,pesofinalL;
    TextInputLayout LYPesoInicialL,LYpesofinalL;
    TextView mermatextL;
    Button botonL;
    LinearLayout LFechaL;
    TextView FechaL;
    Bitmap imgBitmapL=null;

    CardView btnCamaraLL;
    CardView btnCamarav2LL;
    CardView btnVerFotoLL;
    ImageView imgViewLL;
    TextInputEditText PesoInicialLL,pesofinalLL;
    TextInputLayout LYPesoInicialLL,LYpesofinalLL;
    TextView mermatextLL;
    Button botonLL;
    LinearLayout LFechaLL;
    TextView FechaLL;
    Bitmap imgBitmapLL=null;

    CardView btnCamaraE;
    CardView btnCamarav2E;
    CardView btnVerFotoE;
    ImageView imgViewE;
    TextInputEditText PesoInicialE,pesofinalE;
    TextInputLayout LYPesoInicialE,LYpesofinalE;
    TextView mermatextE;
    Button botonE;
    LinearLayout LFechaE;
    TextView FechaE;
    Bitmap imgBitmapE=null;


    CardView btnCamaraP;
    CardView btnCamarav2P;
    CardView btnVerFotoP;
    ImageView imgViewP;
    TextInputEditText PesoInicialP,pesofinalP;
    TextInputLayout LYPesoInicialP,LYpesofinalP;
    TextView mermatextP;
    Button botonP;
    LinearLayout LFechaP;
    TextView FechaP;
    Bitmap imgBitmapP=null;



    String Camara="";
    private Uri photoURI;
    private File photoFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prenda_view);
        CodigoP=getIntent().getExtras().getString("codigo");
        LayoutF=findViewById(R.id.LayoutF);
        LayoutG=findViewById(R.id.LayoutG);
        LayoutL=findViewById(R.id.LayoutL);
        LayoutLL=findViewById(R.id.Layoutll);
        LayoutE=findViewById(R.id.LayoutE);
        LayoutP=findViewById(R.id.LayoutP);
        nombreP=findViewById(R.id.nomprenda);
        mostrarF=findViewById(R.id.mostrarF);
        mostrarG=findViewById(R.id.mostrarG);
        mostrarL=findViewById(R.id.mostrarL);
        mostrarLL=findViewById(R.id.mostrarLL);
        mostrarE=findViewById(R.id.mostrarE);
        mostrarP=findViewById(R.id.mostrarP);
        LayoutF.setVisibility(LinearLayout.GONE);
        LayoutG.setVisibility(LinearLayout.GONE);
        LayoutL.setVisibility(LinearLayout.GONE);
        LayoutLL.setVisibility(LinearLayout.GONE);
        LayoutE.setVisibility(LinearLayout.GONE);
        LayoutP.setVisibility(LinearLayout.GONE);


        //tarjeta de fundicion
        btnCamaraF = findViewById(R.id.fotoF);
        btnCamarav2F = findViewById(R.id.fotoRealizadaF);
        btnVerFotoF=findViewById(R.id.vergaleriaF);
        PesoInicialF= findViewById(R.id.PesoInicialF);
        pesofinalF= findViewById(R.id.pesofinalF);
        LYPesoInicialF= findViewById(R.id.TLPesoInicialF);
        LYpesofinalF= findViewById(R.id.TLpesofinalF);
        mermatextF=findViewById(R.id.mermaF);
        imgViewF = findViewById(R.id.fotoprendaF);
        botonF= findViewById(R.id.ButtonF);
        LFechaF=findViewById(R.id.LFechaF);
        FechaF=findViewById(R.id.FechaF);
        LFechaF.setVisibility(View.GONE);

        TextWatcher watcherF = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calcularMerma(PesoInicialF,pesofinalF,mermatextF);
            }

            @Override
            public void afterTextChanged(Editable s) { }
        };
        PesoInicialF.addTextChangedListener(watcherF);
        pesofinalF.addTextChangedListener(watcherF);

        btnCamaraF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Camara="F";
                if (ContextCompat.checkSelfPermission(PrendaView.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Request camera permission
                    ActivityCompat.requestPermissions(PrendaView.this,
                            new String[]{Manifest.permission.CAMERA},
                            PERMISSIONS_REQUEST_CAMERA);
                } else {
                    // Permission already granted
                    abrirCamara();
                }
            }
        });
        btnCamarav2F.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Camara="F";
                if (ContextCompat.checkSelfPermission(PrendaView.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Request camera permission
                    ActivityCompat.requestPermissions(PrendaView.this,
                            new String[]{Manifest.permission.CAMERA},
                            PERMISSIONS_REQUEST_CAMERA);
                } else {
                    // Permission already granted
                    abrirCamara();
                }
            }
        });
        btnVerFotoF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageInGalleryApp(imgBitmapF);
            }
        });

        botonF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(PesoInicialF.getText().toString().equals("") || pesofinalF.getText().toString().equals("") || imgBitmapF==null){
                    if(PesoInicialF.getText().toString().equals("")){
                        Toast.makeText(PrendaView.this, "Digite el Peso inicial", Toast.LENGTH_SHORT).show();
                    }
                    if(pesofinalF.getText().toString().equals("")){
                        Toast.makeText(PrendaView.this, "Digite el Peso final", Toast.LENGTH_SHORT).show();
                    }
                    if(imgBitmapF==null){
                        Toast.makeText(PrendaView.this, "Tome la foto de la prenda", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    AlertCarga carga =new AlertCarga(PrendaView.this);
                    DatosEstadosViewModel prendaestadosViewModel= ViewModelProviders.of(PrendaView.this).get(DatosEstadosViewModel.class);
                    prendaestadosViewModel.reset();
                    carga.Cargar();
                    prendaestadosViewModel.daotsestados(PrendaView.this,"Fundicion",CodigoP,Float.parseFloat(PesoInicialF.getText().toString()),Float.parseFloat(pesofinalF.getText().toString()),bitmapToByteArray(imgBitmapF));
                    Observer<String> observer=new Observer<String>() {
                        @Override
                        public void onChanged(String s) {
                            carga.setInicio(1);
                            carga.Cerrar();
                            btnCamarav2F.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            });
                            LFechaF.setVisibility(View.VISIBLE);
                            botonF.setVisibility(View.GONE);
                            FechaF.setText("Se Registro en el "+s);
                            PesoInicialF.setEnabled(false);
                            pesofinalF.setEnabled(false);
                        }
                    };
                    prendaestadosViewModel.getResultado().observe(PrendaView.this,observer);
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(carga.getInicio()==0){
                                Toast.makeText(PrendaView.this, "Error no hay conexion", Toast.LENGTH_LONG).show();
                                carga.Cerrar();
                                finish();
                            }
                        }
                    },12000);
                }

            }
        });














        //tarjeta de ElectriPulido
        btnCamaraG = findViewById(R.id.fotoG);
        btnCamarav2G = findViewById(R.id.fotoRealizadaG);
        btnVerFotoG=findViewById(R.id.vergaleriaG);
        PesoInicialG= findViewById(R.id.PesoInicialG);
        pesofinalG= findViewById(R.id.pesofinalG);
        LYPesoInicialG= findViewById(R.id.TLPesoInicialG);
        LYpesofinalG= findViewById(R.id.TLpesofinalG);
        mermatextG=findViewById(R.id.mermaG);
        imgViewG = findViewById(R.id.fotoprendaG);
        botonG= findViewById(R.id.ButtonG);
        LFechaG=findViewById(R.id.LFechaG);
        FechaG=findViewById(R.id.FechaG);
        LFechaG.setVisibility(View.GONE);


        TextWatcher watcherG = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calcularMerma(PesoInicialG,pesofinalG,mermatextG);
            }

            @Override
            public void afterTextChanged(Editable s) { }
        };
        PesoInicialG.addTextChangedListener(watcherG);
        pesofinalG.addTextChangedListener(watcherG);

        btnCamaraG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Camara="G";
                if (ContextCompat.checkSelfPermission(PrendaView.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Request camera permission
                    ActivityCompat.requestPermissions(PrendaView.this,
                            new String[]{Manifest.permission.CAMERA},
                            PERMISSIONS_REQUEST_CAMERA);
                } else {
                    // Permission already granted
                    abrirCamara();
                }
            }
        });
        btnCamarav2G.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Camara="G";
                if (ContextCompat.checkSelfPermission(PrendaView.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Request camera permission
                    ActivityCompat.requestPermissions(PrendaView.this,
                            new String[]{Manifest.permission.CAMERA},
                            PERMISSIONS_REQUEST_CAMERA);
                } else {
                    // Permission already granted
                    abrirCamara();
                }
            }
        });
        btnVerFotoG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageInGalleryApp(imgBitmapG);
            }
        });











        //tarjeta de Limado
        btnCamaraL = findViewById(R.id.fotoL);
        btnCamarav2L = findViewById(R.id.fotoRealizadaL);
        btnVerFotoL=findViewById(R.id.vergaleriaL);
        PesoInicialL= findViewById(R.id.PesoInicialL);
        pesofinalL= findViewById(R.id.pesofinalL);
        LYPesoInicialL= findViewById(R.id.TLPesoInicialL);
        LYpesofinalL= findViewById(R.id.TLpesofinalL);
        mermatextL=findViewById(R.id.mermaL);
        imgViewL = findViewById(R.id.fotoprendaL);
        botonL= findViewById(R.id.ButtonL);
        LFechaL=findViewById(R.id.LFechaL);
        FechaL=findViewById(R.id.FechaL);
        LFechaL.setVisibility(View.GONE);


        TextWatcher watcherL = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calcularMerma(PesoInicialL,pesofinalL,mermatextL);
            }

            @Override
            public void afterTextChanged(Editable s) { }
        };
        PesoInicialL.addTextChangedListener(watcherL);
        pesofinalL.addTextChangedListener(watcherL);

        btnCamaraL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Camara="L";
                if (ContextCompat.checkSelfPermission(PrendaView.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Request camera permission
                    ActivityCompat.requestPermissions(PrendaView.this,
                            new String[]{Manifest.permission.CAMERA},
                            PERMISSIONS_REQUEST_CAMERA);
                } else {
                    // Permission already granted
                    abrirCamara();
                }
            }
        });
        btnCamarav2L.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Camara="L";
                if (ContextCompat.checkSelfPermission(PrendaView.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Request camera permission
                    ActivityCompat.requestPermissions(PrendaView.this,
                            new String[]{Manifest.permission.CAMERA},
                            PERMISSIONS_REQUEST_CAMERA);
                } else {
                    // Permission already granted
                    abrirCamara();
                }
            }
        });

        btnVerFotoL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageInGalleryApp(imgBitmapL);
            }
        });








        //tarjeta de Lijado
        btnCamaraLL = findViewById(R.id.fotoLL);
        btnCamarav2LL = findViewById(R.id.fotoRealizadaLL);
        btnVerFotoLL=findViewById(R.id.vergaleriaLL);
        PesoInicialLL= findViewById(R.id.PesoInicialLL);
        pesofinalLL= findViewById(R.id.pesofinalLL);
        LYPesoInicialLL= findViewById(R.id.TLPesoInicialLL);
        LYpesofinalLL= findViewById(R.id.TLpesofinalLL);
        mermatextLL=findViewById(R.id.mermaLL);
        imgViewLL = findViewById(R.id.fotoprendaLL);
        botonLL= findViewById(R.id.ButtonLL);
        LFechaLL=findViewById(R.id.LFechaLL);
        FechaLL=findViewById(R.id.FechaLL);
        LFechaLL.setVisibility(View.GONE);


        TextWatcher watcherLL = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calcularMerma(PesoInicialLL,pesofinalLL,mermatextLL);
            }

            @Override
            public void afterTextChanged(Editable s) { }
        };
        PesoInicialLL.addTextChangedListener(watcherLL);
        pesofinalLL.addTextChangedListener(watcherLL);


        btnCamaraLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Camara="LL";
                if (ContextCompat.checkSelfPermission(PrendaView.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Request camera permission
                    ActivityCompat.requestPermissions(PrendaView.this,
                            new String[]{Manifest.permission.CAMERA},
                            PERMISSIONS_REQUEST_CAMERA);
                } else {
                    // Permission already granted
                    abrirCamara();
                }
            }
        });
        btnCamarav2LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Camara="LL";
                if (ContextCompat.checkSelfPermission(PrendaView.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Request camera permission
                    ActivityCompat.requestPermissions(PrendaView.this,
                            new String[]{Manifest.permission.CAMERA},
                            PERMISSIONS_REQUEST_CAMERA);
                } else {
                    // Permission already granted
                    abrirCamara();
                }
            }
        });

        btnVerFotoLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageInGalleryApp(imgBitmapLL);
            }
        });



        //tarjeta de Engaste
        btnCamaraE = findViewById(R.id.fotoE);
        btnCamarav2E = findViewById(R.id.fotoRealizadaE);
        btnVerFotoE=findViewById(R.id.vergaleriaE);
        PesoInicialE= findViewById(R.id.PesoInicialE);
        pesofinalE= findViewById(R.id.pesofinalE);
        LYPesoInicialE= findViewById(R.id.TLPesoInicialE);
        LYpesofinalE= findViewById(R.id.TLpesofinalE);
        mermatextE=findViewById(R.id.mermaE);
        imgViewE = findViewById(R.id.fotoprendaE);
        botonE= findViewById(R.id.ButtonE);
        LFechaE=findViewById(R.id.LFechaE);
        FechaE=findViewById(R.id.FechaE);
        LFechaE.setVisibility(View.GONE);

        TextWatcher watcherE = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calcularMerma(PesoInicialE,pesofinalE,mermatextE);
            }

            @Override
            public void afterTextChanged(Editable s) { }
        };
        PesoInicialE.addTextChangedListener(watcherE);
        pesofinalE.addTextChangedListener(watcherE);


        btnCamaraE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Camara="E";
                if (ContextCompat.checkSelfPermission(PrendaView.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Request camera permission
                    ActivityCompat.requestPermissions(PrendaView.this,
                            new String[]{Manifest.permission.CAMERA},
                            PERMISSIONS_REQUEST_CAMERA);
                } else {
                    // Permission already granted
                    abrirCamara();
                }
            }
        });
        btnCamarav2E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Camara="E";
                if (ContextCompat.checkSelfPermission(PrendaView.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Request camera permission
                    ActivityCompat.requestPermissions(PrendaView.this,
                            new String[]{Manifest.permission.CAMERA},
                            PERMISSIONS_REQUEST_CAMERA);
                } else {
                    // Permission already granted
                    abrirCamara();
                }
            }
        });

        btnVerFotoE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageInGalleryApp(imgBitmapE);
            }
        });


        //tarjeta de Pulido
        btnCamaraP = findViewById(R.id.fotoP);
        btnCamarav2P = findViewById(R.id.fotoRealizadaP);
        btnVerFotoP=findViewById(R.id.vergaleriaP);
        PesoInicialP= findViewById(R.id.PesoInicialP);
        pesofinalP= findViewById(R.id.pesofinalP);
        LYPesoInicialP= findViewById(R.id.TLPesoInicialP);
        LYpesofinalP= findViewById(R.id.TLpesofinalP);
        mermatextP=findViewById(R.id.mermaP);
        imgViewP = findViewById(R.id.fotoprendaP);
        botonP= findViewById(R.id.ButtonP);
        LFechaP=findViewById(R.id.LFechaP);
        FechaP=findViewById(R.id.FechaP);
        LFechaP.setVisibility(View.GONE);

        TextWatcher watcherP = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calcularMerma(PesoInicialP,pesofinalP,mermatextP);
            }

            @Override
            public void afterTextChanged(Editable s) { }
        };
        PesoInicialP.addTextChangedListener(watcherP);
        pesofinalP.addTextChangedListener(watcherP);

        btnCamaraP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Camara="P";
                if (ContextCompat.checkSelfPermission(PrendaView.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Request camera permission
                    ActivityCompat.requestPermissions(PrendaView.this,
                            new String[]{Manifest.permission.CAMERA},
                            PERMISSIONS_REQUEST_CAMERA);
                } else {
                    // Permission already granted
                    abrirCamara();
                }
            }
        });
        btnCamarav2P.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Camara="P";
                if (ContextCompat.checkSelfPermission(PrendaView.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Request camera permission
                    ActivityCompat.requestPermissions(PrendaView.this,
                            new String[]{Manifest.permission.CAMERA},
                            PERMISSIONS_REQUEST_CAMERA);
                } else {
                    // Permission already granted
                    abrirCamara();
                }
            }
        });

        btnVerFotoP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageInGalleryApp(imgBitmapP);
            }
        });









        // Comportamiento de tarjetas
        mostrarF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LayoutF.getVisibility()== LinearLayout.GONE){
                    LayoutF.setVisibility(LinearLayout.VISIBLE);
                }else{
                    LayoutF.setVisibility(LinearLayout.GONE);
                }
            }
        });
        mostrarG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LayoutG.getVisibility()== LinearLayout.GONE){
                    LayoutG.setVisibility(LinearLayout.VISIBLE);
                }else{
                    LayoutG.setVisibility(LinearLayout.GONE);
                }
            }
        });
        mostrarL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LayoutL.getVisibility()== LinearLayout.GONE){
                    LayoutL.setVisibility(LinearLayout.VISIBLE);
                }else{
                    LayoutL.setVisibility(LinearLayout.GONE);
                }
            }
        });
        mostrarLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LayoutLL.getVisibility()== LinearLayout.GONE){
                    LayoutLL.setVisibility(LinearLayout.VISIBLE);
                }else{
                    LayoutLL.setVisibility(LinearLayout.GONE);
                }
            }
        });
        mostrarE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LayoutE.getVisibility()== LinearLayout.GONE){
                    LayoutE.setVisibility(LinearLayout.VISIBLE);
                }else{
                    LayoutE.setVisibility(LinearLayout.GONE);
                }
            }
        });
        mostrarP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LayoutP.getVisibility()== LinearLayout.GONE){
                    LayoutP.setVisibility(LinearLayout.VISIBLE);
                }else{
                    LayoutP.setVisibility(LinearLayout.GONE);
                }
            }
        });

        cargadatos();
    }
    public void cargadatos(){
        AlertCarga carga =new AlertCarga(PrendaView.this);
        DatosPrendaViewModel prendaViewModel= ViewModelProviders.of(PrendaView.this).get(DatosPrendaViewModel.class);
        prendaViewModel.reset();
        carga.Cargar();
        prendaViewModel.nombreprenda(PrendaView.this,CodigoP);
        Observer<String> observer=new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s.equals("0nohayfalse")){
                    Toast.makeText(PrendaView.this, "Error en la conexion de la base de datos", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    nombreP.setText(s);
                    nombreP.setEnabled(false);
                    prendaViewModel.DatosdePrendas(PrendaView.this,CodigoP);
                    Observer<List<DatosPrenda>> observer1=new Observer<List<DatosPrenda>>() {
                        @Override
                        public void onChanged(List<DatosPrenda> datosPrendas) {
                            carga.setInicio(1);
                            carga.Cerrar();
                            if(datosPrendas.size()>=1){
                                btnCamarav2F.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                });
                                LFechaF.setVisibility(View.VISIBLE);
                                botonF.setVisibility(View.GONE);
                                FechaF.setText("Se Registro en el "+datosPrendas.get(0).getFecha());
                                PesoInicialF.setText(""+datosPrendas.get(0).getPesoinicial());
                                pesofinalF.setText(""+datosPrendas.get(0).getPesofinal());
                                PesoInicialF.setEnabled(false);
                                pesofinalF.setEnabled(false);
                                imgBitmapF=byteArrayToBitmap(datosPrendas.get(0).getFoto());
                                try {
                                    photoFile = createImageFile();
                                    if (photoFile != null) {
                                        photoURI = FileProvider.getUriForFile(PrendaView.this, "com.pixels.orobackoup.fileprovider", photoFile);
                                        if (imgBitmapF != null) {
                                            // Corregir la orientación de la imagen
                                            imgBitmapF = rotateImageIfRequired(imgBitmapF, photoFile.getAbsolutePath());
                                            // Mostrar la imagen en el ImageView
                                            imgViewF.setImageBitmap(imgBitmapF);
                                            btnCamaraF.setVisibility(View.GONE);
                                        } else {
                                            // Manejo del error si la imagen no se decodifica
                                            Toast.makeText(PrendaView.this, "Error al decodificar la imagen", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                    Toast.makeText(PrendaView.this, "Error al crear el archivo de imagen", Toast.LENGTH_SHORT).show();
                                }
                            }
                            //---------------------------------------------------------------------------------------------------------------------------------------------------
                            if(datosPrendas.size()>=2){
                                btnCamarav2G.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                });
                                LFechaG.setVisibility(View.VISIBLE);
                                botonG.setVisibility(View.GONE);
                                FechaG.setText("Se Registro en el "+datosPrendas.get(1).getFecha());
                                PesoInicialG.setText(""+datosPrendas.get(1).getPesoinicial());
                                pesofinalG.setText(""+datosPrendas.get(1).getPesofinal());
                                PesoInicialG.setEnabled(false);
                                pesofinalG.setEnabled(false);
                                imgBitmapG=byteArrayToBitmap(datosPrendas.get(1).getFoto());
                                try {
                                    photoFile = createImageFile();
                                    if (photoFile != null) {
                                        photoURI = FileProvider.getUriForFile(PrendaView.this, "com.pixels.orobackoup.fileprovider", photoFile);
                                        if (imgBitmapG != null) {
                                            // Corregir la orientación de la imagen
                                            imgBitmapG = rotateImageIfRequired(imgBitmapG, photoFile.getAbsolutePath());
                                            // Mostrar la imagen en el ImageView
                                            imgViewG.setImageBitmap(imgBitmapG);
                                            btnCamaraG.setVisibility(View.GONE);
                                        } else {
                                            // Manejo del error si la imagen no se decodifica
                                            Toast.makeText(PrendaView.this, "Error al decodificar la imagen", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                    Toast.makeText(PrendaView.this, "Error al crear el archivo de imagen", Toast.LENGTH_SHORT).show();
                                }
                            }
                            //----------------------------------------------------------------------------------------------------------------------------------------------------------------

                            if(datosPrendas.size()>=3){
                                btnCamarav2L.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                });
                                LFechaL.setVisibility(View.VISIBLE);
                                botonL.setVisibility(View.GONE);
                                FechaL.setText("Se Registro en el "+datosPrendas.get(2).getFecha());
                                PesoInicialL.setText(""+datosPrendas.get(2).getPesoinicial());
                                pesofinalL.setText(""+datosPrendas.get(2).getPesofinal());
                                PesoInicialL.setEnabled(false);
                                pesofinalL.setEnabled(false);
                                imgBitmapL=byteArrayToBitmap(datosPrendas.get(2).getFoto());
                                try {
                                    photoFile = createImageFile();
                                    if (photoFile != null) {
                                        photoURI = FileProvider.getUriForFile(PrendaView.this, "com.pixels.orobackoup.fileprovider", photoFile);
                                        if (imgBitmapL != null) {
                                            // Corregir la orientación de la imagen
                                            imgBitmapL = rotateImageIfRequired(imgBitmapL, photoFile.getAbsolutePath());
                                            // Mostrar la imagen en el ImageView
                                            imgViewL.setImageBitmap(imgBitmapL);
                                            btnCamaraL.setVisibility(View.GONE);
                                        } else {
                                            // Manejo del error si la imagen no se decodifica
                                            Toast.makeText(PrendaView.this, "Error al decodificar la imagen", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                    Toast.makeText(PrendaView.this, "Error al crear el archivo de imagen", Toast.LENGTH_SHORT).show();
                                }
                            }

                            //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------
                            if(datosPrendas.size()>=4){
                                btnCamarav2LL.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                });
                                LFechaLL.setVisibility(View.VISIBLE);
                                botonLL.setVisibility(View.GONE);
                                FechaLL.setText("Se Registro en el "+datosPrendas.get(3).getFecha());
                                PesoInicialLL.setText(""+datosPrendas.get(3).getPesoinicial());
                                pesofinalLL.setText(""+datosPrendas.get(3).getPesofinal());
                                PesoInicialLL.setEnabled(false);
                                pesofinalLL.setEnabled(false);
                                imgBitmapLL=byteArrayToBitmap(datosPrendas.get(3).getFoto());
                                try {
                                    photoFile = createImageFile();
                                    if (photoFile != null) {
                                        photoURI = FileProvider.getUriForFile(PrendaView.this, "com.pixels.orobackoup.fileprovider", photoFile);
                                        if (imgBitmapLL != null) {
                                            // Corregir la orientación de la imagen
                                            imgBitmapLL = rotateImageIfRequired(imgBitmapLL, photoFile.getAbsolutePath());
                                            // Mostrar la imagen en el ImageView
                                            imgViewLL.setImageBitmap(imgBitmapLL);
                                            btnCamaraLL.setVisibility(View.GONE);
                                        } else {
                                            // Manejo del error si la imagen no se decodifica
                                            Toast.makeText(PrendaView.this, "Error al decodificar la imagen", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                    Toast.makeText(PrendaView.this, "Error al crear el archivo de imagen", Toast.LENGTH_SHORT).show();
                                }
                            }
                            //-------------------------------------------------------------------------------------------------------------------------------------------------------
                            if(datosPrendas.size()>=5){
                                btnCamarav2E.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                });
                                LFechaE.setVisibility(View.VISIBLE);
                                botonE.setVisibility(View.GONE);
                                FechaE.setText("Se Registro en el "+datosPrendas.get(4).getFecha());
                                PesoInicialE.setText(""+datosPrendas.get(4).getPesoinicial());
                                pesofinalE.setText(""+datosPrendas.get(4).getPesofinal());
                                PesoInicialE.setEnabled(false);
                                pesofinalE.setEnabled(false);
                                imgBitmapE=byteArrayToBitmap(datosPrendas.get(4).getFoto());
                                try {
                                    photoFile = createImageFile();
                                    if (photoFile != null) {
                                        photoURI = FileProvider.getUriForFile(PrendaView.this, "com.pixels.orobackoup.fileprovider", photoFile);
                                        if (imgBitmapE != null) {
                                            // Corregir la orientación de la imagen
                                            imgBitmapE = rotateImageIfRequired(imgBitmapE, photoFile.getAbsolutePath());
                                            // Mostrar la imagen en el ImageView
                                            imgViewE.setImageBitmap(imgBitmapE);
                                            btnCamaraE.setVisibility(View.GONE);
                                        } else {
                                            // Manejo del error si la imagen no se decodifica
                                            Toast.makeText(PrendaView.this, "Error al decodificar la imagen", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                    Toast.makeText(PrendaView.this, "Error al crear el archivo de imagen", Toast.LENGTH_SHORT).show();
                                }
                            }

                            //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
                            if(datosPrendas.size()>=6){
                                btnCamarav2P.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                });
                                LFechaP.setVisibility(View.VISIBLE);
                                botonP.setVisibility(View.GONE);
                                FechaP.setText("Se Registro en el "+datosPrendas.get(5).getFecha());
                                PesoInicialP.setText(""+datosPrendas.get(5).getPesoinicial());
                                pesofinalP.setText(""+datosPrendas.get(5).getPesofinal());
                                PesoInicialP.setEnabled(false);
                                pesofinalP.setEnabled(false);
                                imgBitmapP=byteArrayToBitmap(datosPrendas.get(5).getFoto());
                                try {
                                    photoFile = createImageFile();
                                    if (photoFile != null) {
                                        photoURI = FileProvider.getUriForFile(PrendaView.this, "com.pixels.orobackoup.fileprovider", photoFile);
                                        if (imgBitmapP != null) {
                                            // Corregir la orientación de la imagen
                                            imgBitmapP = rotateImageIfRequired(imgBitmapP, photoFile.getAbsolutePath());
                                            // Mostrar la imagen en el ImageView
                                            imgViewP.setImageBitmap(imgBitmapP);
                                            btnCamaraP.setVisibility(View.GONE);
                                        } else {
                                            // Manejo del error si la imagen no se decodifica
                                            Toast.makeText(PrendaView.this, "Error al decodificar la imagen", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                    Toast.makeText(PrendaView.this, "Error al crear el archivo de imagen", Toast.LENGTH_SHORT).show();
                                }
                            }

                            //Toast.makeText(PrendaView.this, "datos:"+datosPrendas.size(), Toast.LENGTH_SHORT).show();
                        }
                    };
                    prendaViewModel.getResultadoDatos().observe(PrendaView.this,observer1);
                }
            }
        };
        prendaViewModel.getResultado().observe(PrendaView.this,observer);
        nombreP.setEnabled(false);
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(carga.getInicio()==0){
                    Toast.makeText(PrendaView.this, "Error no hay conexion", Toast.LENGTH_LONG).show();
                    carga.Cerrar();
                    finish();
                }
            }
        },12000);
    }

    private void calcularMerma( TextInputEditText PesoInicial,TextInputEditText pesofinal, TextView mermatext) {
        String pesoInicialStr = PesoInicial.getText().toString();
        String pesoFinalStr = pesofinal.getText().toString();

        if (!pesoInicialStr.isEmpty() && !pesoFinalStr.isEmpty()) {
            try {
                double pesoInicial = Double.parseDouble(pesoInicialStr);
                double pesoFinal = Double.parseDouble(pesoFinalStr);
                double merma = pesoInicial - pesoFinal;
                mermatext.setText(String.format("La merma es: %.2fg", merma));
            } catch (NumberFormatException e) {
                // Manejo de errores si el formato del número es incorrecto
                mermatext.setText("La merma es: 0g");
            }
        } else {
            mermatext.setText("La merma es: 0g");
        }
    }

    private void showImageInGalleryApp(Bitmap bitmap) {
        try {
            // Crear un archivo temporal para almacenar la imagen
            File tempFile = File.createTempFile("temp_image", ".jpg", getCacheDir());

            // Guardar el Bitmap en el archivo temporal
            OutputStream fos = new FileOutputStream(tempFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();

            // Crear un URI para el archivo temporal utilizando FileProvider
            Uri imageUri = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", tempFile);

            // Crear un Intent para visualizar la imagen en la app de galería
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(imageUri, "image/jpeg");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // Permisos para la galería

            // Verificar que haya una aplicación disponible para abrir el Intent
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent); // Mostrar la imagen
            } else {
                Toast.makeText(this, "No hay una aplicación disponible para visualizar la imagen", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al visualizar la imagen", Toast.LENGTH_SHORT).show();
        }
    }
    private Bitmap rotateImageIfRequired(Bitmap img, String imagePath) {
        try {
            ExifInterface exif = new ExifInterface(imagePath);
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    return rotateImage(img, 90);
                case ExifInterface.ORIENTATION_ROTATE_180:
                    return rotateImage(img, 180);
                case ExifInterface.ORIENTATION_ROTATE_270:
                    return rotateImage(img, 270);
                default:
                    return img;
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al leer los metadatos EXIF", Toast.LENGTH_SHORT).show();
            return img; // Devuelve la imagen sin rotar si hay un error
        }
    }

    // Método para rotar el Bitmap
    private Bitmap rotateImage(Bitmap img, int degree) {
        if (img == null) {
            Toast.makeText(this, "Error: Imagen no cargada", Toast.LENGTH_SHORT).show();
            return null; // Asegúrate de no intentar rotar un Bitmap nulo
        }
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        return Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
    }
    private Bitmap byteArrayToBitmap(byte[] imageBytes) {
        // Configura opciones para evitar la reducción de tamaño
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inSampleSize = 1; // Asegúrate de no escalar la imagen

        // Decodifica la imagen desde los bytes manteniendo la resolución original
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length, options);
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

    private void abrirCamara() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            // Crear el archivo donde se guardará la foto
            try {
                photoFile = createImageFile();
                if (photoFile != null) {
                    photoURI = FileProvider.getUriForFile(this, "com.pixels.orobackoup.fileprovider", photoFile);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(intent, 1);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                Toast.makeText(this, "Error al crear el archivo de imagen", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Verificar si el archivo existe y tiene datos
            if (photoFile != null && photoFile.exists()) {
                // Decodificar la imagen desde el archivo con resolución completa
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                // Establecer inSampleSize para reducir la calidad. Un valor de 2 reducirá la resolución a la mitad.
                options.inSampleSize = 2; // Cambia este valor si quieres reducir más la calidad
                if(Camara.equals("F")){
                    imgBitmapF = BitmapFactory.decodeFile(photoFile.getAbsolutePath(), options);
                    // Verificar si la imagen fue decodificada correctamente
                    if (imgBitmapF != null) {
                        // Corregir la orientación de la imagen
                        imgBitmapF = rotateImageIfRequired(imgBitmapF, photoFile.getAbsolutePath());
                        // Mostrar la imagen en el ImageView
                        imgViewF.setImageBitmap(imgBitmapF);
                        btnCamaraF.setVisibility(View.GONE);
                    } else {
                        // Manejo del error si la imagen no se decodifica
                        Toast.makeText(this, "Error al decodificar la imagen", Toast.LENGTH_SHORT).show();
                    }
                }
                if(Camara.equals("G")){
                    imgBitmapG = BitmapFactory.decodeFile(photoFile.getAbsolutePath(), options);
                    // Verificar si la imagen fue decodificada correctamente
                    if (imgBitmapG != null) {
                        // Corregir la orientación de la imagen
                        imgBitmapG = rotateImageIfRequired(imgBitmapG, photoFile.getAbsolutePath());
                        // Mostrar la imagen en el ImageView
                        imgViewG.setImageBitmap(imgBitmapG);
                        btnCamaraG.setVisibility(View.GONE);
                    } else {
                        // Manejo del error si la imagen no se decodifica
                        Toast.makeText(this, "Error al decodificar la imagen", Toast.LENGTH_SHORT).show();
                    }
                }
                if(Camara.equals("L")){
                    imgBitmapL = BitmapFactory.decodeFile(photoFile.getAbsolutePath(), options);
                    // Verificar si la imagen fue decodificada correctamente
                    if (imgBitmapL != null) {
                        // Corregir la orientación de la imagen
                        imgBitmapL = rotateImageIfRequired(imgBitmapL, photoFile.getAbsolutePath());
                        // Mostrar la imagen en el ImageView
                        imgViewL.setImageBitmap(imgBitmapL);
                        btnCamaraL.setVisibility(View.GONE);
                    } else {
                        // Manejo del error si la imagen no se decodifica
                        Toast.makeText(this, "Error al decodificar la imagen", Toast.LENGTH_SHORT).show();
                    }
                }
                if(Camara.equals("LL")){
                    imgBitmapLL = BitmapFactory.decodeFile(photoFile.getAbsolutePath(), options);
                    // Verificar si la imagen fue decodificada correctamente
                    if (imgBitmapLL != null) {
                        // Corregir la orientación de la imagen
                        imgBitmapLL = rotateImageIfRequired(imgBitmapLL, photoFile.getAbsolutePath());
                        // Mostrar la imagen en el ImageView
                        imgViewLL.setImageBitmap(imgBitmapLL);
                        btnCamaraLL.setVisibility(View.GONE);
                    } else {
                        // Manejo del error si la imagen no se decodifica
                        Toast.makeText(this, "Error al decodificar la imagen", Toast.LENGTH_SHORT).show();
                    }
                }
                if(Camara.equals("E")){
                    imgBitmapE = BitmapFactory.decodeFile(photoFile.getAbsolutePath(), options);
                    // Verificar si la imagen fue decodificada correctamente
                    if (imgBitmapE != null) {
                        // Corregir la orientación de la imagen
                        imgBitmapE = rotateImageIfRequired(imgBitmapE, photoFile.getAbsolutePath());
                        // Mostrar la imagen en el ImageView
                        imgViewE.setImageBitmap(imgBitmapE);
                        btnCamaraE.setVisibility(View.GONE);
                    } else {
                        // Manejo del error si la imagen no se decodifica
                        Toast.makeText(this, "Error al decodificar la imagen", Toast.LENGTH_SHORT).show();
                    }
                }
                if(Camara.equals("P")){
                    imgBitmapP = BitmapFactory.decodeFile(photoFile.getAbsolutePath(), options);
                    // Verificar si la imagen fue decodificada correctamente
                    if (imgBitmapP != null) {
                        // Corregir la orientación de la imagen
                        imgBitmapP = rotateImageIfRequired(imgBitmapP, photoFile.getAbsolutePath());
                        // Mostrar la imagen en el ImageView
                        imgViewP.setImageBitmap(imgBitmapP);
                        btnCamaraP.setVisibility(View.GONE);
                    } else {
                        // Manejo del error si la imagen no se decodifica
                        Toast.makeText(this, "Error al decodificar la imagen", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                Toast.makeText(this, "Archivo de imagen no encontrado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private File createImageFile() throws IOException {
        // Crear un nombre de archivo único
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES); // Directorio temporal para guardar la imagen
        File image = File.createTempFile(
                imageFileName, /* prefix */
                ".jpg",        /* suffix */
                storageDir     /* directory */
        );

        return image;
    }
    private byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        // Usa PNG para guardar sin pérdida, o JPEG con calidad máxima (100)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream); // Cambiar a PNG si es posible, para calidad sin pérdida
        return stream.toByteArray();
    }
}