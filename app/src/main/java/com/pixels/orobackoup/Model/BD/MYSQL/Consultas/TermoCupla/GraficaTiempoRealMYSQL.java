package com.pixels.orobackoup.Model.BD.MYSQL.Consultas.TermoCupla;

import android.content.Context;
import android.widget.Toast;

import com.mysql.jdbc.PreparedStatement;
import com.pixels.orobackoup.Model.BD.MYSQL.Conexion;
import com.pixels.orobackoup.Model.DatosEncapsulados.TermoCalor;
import com.pixels.orobackoup.ViewModel.FundicionViewModel;
import com.pixels.orobackoup.ViewModel.TermoCupla.WS.GraficaTiempoRealViewModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GraficaTiempoRealMYSQL extends Conexion {

    private boolean verficar;
    private boolean verificarE=false;
    private GraficaTiempoRealViewModel ViewModel;

    public List<TermoCalor> ListaTiempoReal=new ArrayList<>();
    int conteo=0;
    private String Session;
    public GraficaTiempoRealMYSQL(Context context,GraficaTiempoRealViewModel viewModel,String session) {
        super(context);
        this.ViewModel=viewModel;
        this.Session=session;

        this.Url="jdbc:mysql://"+"192.168.20.254"+"/"+"horno_monitoring";
        execute("");
        new android.os.Handler().postDelayed(new Runnable() {
            public void run() {
                if(verificarE){
                    onCancelled();
                }else{
                    onCancelled("No se puede conectar a La Base Datos");
                    verificarE=true;
                }
            }
        },11000);
    }
    @Override
    protected String doInBackground(String... params) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            DriverManager.setLoginTimeout(10);
            Connection connection= DriverManager.getConnection(Url,Usuario,Contra);
            if(verificarE){
                return "Error en la conexion";
            }else{

                String Sql = "SELECT * FROM temperature_readings WHERE session_id = "+Session;
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(Sql);
                while (rs.next()) {
                  // conteo++;
                    ListaTiempoReal.add(new TermoCalor(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getFloat(4)));
                }
                return "";
            }
        }catch (Exception e){
            return "Error en la Conexion al MYSQL "+e;
        }
    }
    protected void onPostExecute(String result) {
        verificarE=true;
        if(result.equals("")){
           // Toast.makeText(Context, "El conteo es :"+conteo +" SEssion: "+Session, Toast.LENGTH_SHORT).show();
            verficar=true;
            ConsultaBaseDatos();
        }else {
            verficar=false;
            ConsultaBaseDatos();
            if(result.equals("Error en la conexion")){

            }else{
                Toast.makeText(Context, result, Toast.LENGTH_LONG).show();
            }
        }
    }
    public void ConsultaBaseDatos() {
        ViewModel.resultado.setValue(ListaTiempoReal);
    }
}
