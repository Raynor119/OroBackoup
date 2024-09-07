package com.pixels.orobackoup.View;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;
import com.pixels.orobackoup.Model.BD.MYSQL.Conexion;
import com.pixels.orobackoup.Model.DatosEncapsulados.Fundicion;
import com.pixels.orobackoup.R;
import com.pixels.orobackoup.ViewModel.FundicionViewModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import androidx.core.content.FileProvider;
import android.media.ExifInterface;
import android.graphics.Matrix;


public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int PERMISSIONS_REQUEST_CAMERA = 2;
    CardView btnCamara;
    CardView btnCamarav2;
    ImageView imgView;
    TextInputEditText PesoInicial,pesofinal;
    TextView mermatext;
    Button boton;
    Bitmap imgBitmap;
    private Uri photoURI;
    private File photoFile;

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
        boton= findViewById(R.id.ButtonG);
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

        /*
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FundicionViewModel agregar= ViewModelProviders.of(MainActivity.this).get(FundicionViewModel.class);
                agregar.reset();
                List<Fundicion>datos=new ArrayList<>();
                byte[] imageBytes = bitmapToByteArray(imgBitmap);
                datos.add(new Fundicion(3, 34.3F,32.1F,imageBytes));
                agregar.guardarfundicion(MainActivity.this,datos);
                Observer<Boolean> observer1= new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        if(aBoolean){
                            try{
                                Toast.makeText(MainActivity.this, "Se guardo el Producto en la Base de Datos", Toast.LENGTH_LONG).show();
                            }catch (Exception e){
                             //   Toast.makeText(MainActivity.this, "Error al guardar el Producto en la Base de Datos", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                };
                agregar.getResultado().observe(MainActivity.this,observer1);
            }
        });

         */



        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FundicionViewModel agregar= ViewModelProviders.of(MainActivity.this).get(FundicionViewModel.class);
                agregar.reset();
                agregar.verfoto(MainActivity.this,3);
                Observer<byte[]> observer=new Observer<byte[]>() {
                    @Override
                    public void onChanged(byte[] bytes) {
                        imgBitmap= byteArrayToBitmap(bytes);
                        imgView.setImageBitmap(imgBitmap);
                        btnCamara.setVisibility(View.GONE);
                        showImageInGalleryApp(imgBitmap);
                        //saveImageToGallery(imgBitmap);
                    }
                };
                agregar.getResultadov2().observe(MainActivity.this,observer);
            }
        });


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
                imgBitmap = BitmapFactory.decodeFile(photoFile.getAbsolutePath(), options);

                // Verificar si la imagen fue decodificada correctamente
                if (imgBitmap != null) {
                    // Corregir la orientación de la imagen
                    imgBitmap = rotateImageIfRequired(imgBitmap, photoFile.getAbsolutePath());

                    // Mostrar la imagen en el ImageView
                    imgView.setImageBitmap(imgBitmap);
                    btnCamara.setVisibility(View.GONE);

                    // Guardar en la galería o en la base de datos
                    //saveImageToGallery(imgBitmap);
                } else {
                    // Manejo del error si la imagen no se decodifica
                    Toast.makeText(this, "Error al decodificar la imagen", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Archivo de imagen no encontrado", Toast.LENGTH_SHORT).show();
            }
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
                mermatext.setText("La merma es: 0");
            }
        } else {
            mermatext.setText("La merma es: 0");
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
    private byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        // Usa PNG para guardar sin pérdida, o JPEG con calidad máxima (100)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream); // Cambiar a PNG si es posible, para calidad sin pérdida
        return stream.toByteArray();
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

    private void saveImageToGallery(Bitmap bitmap) {
        OutputStream fos;
        String imageName = "imagen_" + System.currentTimeMillis() + ".jpg"; // Nombre único para cada imagen

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            // Para Android 10 (API 29) y superiores
            ContentResolver resolver = getContentResolver();
            ContentValues contentValues = new ContentValues();
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, imageName);
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");
            contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES); // Guarda en la carpeta "Pictures"

            Uri imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

            try {
                if (imageUri != null) {
                    fos = resolver.openOutputStream(imageUri);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos); // Guarda el bitmap en formato JPEG
                    fos.close();
                    Toast.makeText(this, "Imagen guardada en la galería", Toast.LENGTH_SHORT).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error al guardar la imagen", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Para versiones anteriores a Android 10
            File directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "MiApp");
            if (!directory.exists()) {
                directory.mkdirs(); // Crea el directorio si no existe
            }

            File imageFile = new File(directory, imageName);
            try {
                fos = new FileOutputStream(imageFile);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.close();

                // Notificar al sistema para que la imagen aparezca en la galería
                Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                intent.setData(Uri.fromFile(imageFile));
                sendBroadcast(intent);

                Toast.makeText(this, "Imagen guardada en la galería", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error al guardar la imagen", Toast.LENGTH_SHORT).show();
            }
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

}