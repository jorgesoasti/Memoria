package com.jorgesoasti.memoria;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.lang.UCharacterEnums;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void guardarPreferencia(View v){

        SharedPreferences miPreferenciaCompartida = getSharedPreferences("MisDatosPersonales", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = miPreferenciaCompartida.edit();

        EditText edtNombre = (EditText) findViewById(R.id.edtNombre);
        EditText edtCorreo = (EditText) findViewById(R.id.edtCorreo);

        String nombre = edtNombre.getText().toString();
        String correo = edtCorreo.getText().toString();

        editor.putString("nombre",nombre);
        editor.putString("correo",correo);

        editor.commit();

        Toast.makeText(this, "Se ha creado la preferencia compartida", Toast.LENGTH_SHORT).show();

        edtNombre.setText("");
        edtCorreo.setText("");
    }

    public void mostrarPreferencia(View v){

        SharedPreferences miPreferenciaCompartida = getSharedPreferences("MisDatosPersonales", Context.MODE_PRIVATE);

        String nombre = miPreferenciaCompartida.getString("nombre", "no existe esa variable");
        String correo = miPreferenciaCompartida.getString("correo", "no existe esa variable");

        TextView tvPreferCompart = (TextView) findViewById(R.id.tvPreferenciaCompartida);

        String preferencia = "\nNombre: " + nombre + "\nCorreo: " + correo;
        tvPreferCompart.setText(preferencia);

    }

    public void generarArchivo(View v){
        try{

            EditText edtNombre = (EditText) findViewById(R.id.edtNombre);
            String nombre = edtNombre.getText().toString();

            FileOutputStream outputStream = null;
            outputStream = openFileOutput("MiArchivo.txt", Context.MODE_APPEND);
            outputStream.write(nombre.getBytes());
            outputStream.close();

            Toast.makeText(MainActivity.this, "El archivo se ha creado", Toast.LENGTH_SHORT).show();
            edtNombre.setText("");

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Hubo un error en la escritura del archivo", Toast.LENGTH_SHORT).show();
        }
    }
}
