package com.pixels.orobackoup.Model.BD.MYSQL;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class Conexion extends AsyncTask<String, Void , String> {
    public String Url,Usuario,Contra;
    public Context Context;

    public Conexion(Context context){
        this.Url="jdbc:mysql://"+"100.115.92.205"+"/"+"Joyeria";
        //this.Url="jdbc:mysql://"+"192.168.1.8"+"/"+"Joyeria";
        this.Usuario="raynor";
        this.Contra="67895421d";
        this.Context=context;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected String doInBackground(String... params) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            DriverManager.setLoginTimeout(10);
            Connection connection= DriverManager.getConnection(Url,Usuario,Contra);
            return "Hay Conexion en la Base de Datos";
        }catch (Exception e){
            return "No se puede conectar a La Base Datos";
        }
    }
    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(Context, result, Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onCancelled (String result) {
        Toast.makeText(Context, result, Toast.LENGTH_LONG).show();
    }
}
