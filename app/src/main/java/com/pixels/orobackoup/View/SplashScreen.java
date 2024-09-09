package com.pixels.orobackoup.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.pixels.orobackoup.R;
import com.pixels.orobackoup.View.InicioSesion.InicioSession;
import com.pixels.orobackoup.View.Menu.Menu;

public class SplashScreen extends AppCompatActivity {

    private SharedPreferences prefe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                switch(getFirstTimeRun()) {
                    case 0:
                        SharedPreferences preferencias=getSharedPreferences("datos",SplashScreen.this.MODE_PRIVATE);
                        SharedPreferences.Editor editor=preferencias.edit();
                        editor.putString("checkboxG", "0");
                        editor.commit();
                        Intent intent =new Intent(SplashScreen.this, InicioSession.class);
                        startActivity(intent);
                        finish();
                        break;
                    case 1:
                        prefe=getSharedPreferences("Sesion",SplashScreen.this.MODE_PRIVATE);
                        if(prefe.getString("SesionD","0").equals("0")){
                            Intent IIntent =new Intent(SplashScreen.this, InicioSession.class);
                            startActivity(IIntent);
                            finish();
                        }else{
                            Intent IIntent =new Intent(SplashScreen.this, Menu.class);
                            startActivity(IIntent);
                            finish();
                        }
                        break;
                    case 2:
                        Toast.makeText(getApplicationContext(), "se Actualizo", Toast.LENGTH_LONG).show();
                        finish();
                        break;
                }

            }
        },2000);
    }

    public int getFirstTimeRun() {
        SharedPreferences sp = SplashScreen.this.getSharedPreferences("MYAPP", 0);
        int result, currentVersionCode = 2;
        int lastVersionCode = sp.getInt("FIRSTTIMERUN", -1);
        if (lastVersionCode == -1) result = 0; else
            result = (lastVersionCode == currentVersionCode) ? 1 : 2;
        sp.edit().putInt("FIRSTTIMERUN", currentVersionCode).apply();
        return result;
    }
}