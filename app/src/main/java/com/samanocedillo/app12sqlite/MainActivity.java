package com.samanocedillo.app12sqlite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;

public class MainActivity extends AppCompatActivity {

    Intent i;
    int cod, cant;
    String nombre, desc;
    Double precio;
    EditText etCodigo, etNombre, etDesc, etCantidad, etPrecio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCodigo = (EditText)findViewById(R.id.etCodigo);
        etNombre = (EditText)findViewById(R.id.etNombre);
        etDesc = (EditText)findViewById(R.id.etDesc);
        etCantidad = (EditText)findViewById(R.id.etCantidad);
        etPrecio = (EditText)findViewById(R.id.etPrecio);

    }


    public void enviar(View view) throws InvocationTargetException {


        if (etNombre.getText().toString() != "") {
            nombre = etNombre.getText().toString().toUpperCase();
        } else { nombre = ""; }

        if (etDesc.getText().toString() != "") {
            desc = etDesc.getText().toString().toUpperCase();
        } else { desc = ""; }

        try {

            if (etCodigo.getText().toString() != "") {
                cod = Integer.parseInt(etCodigo.getText().toString());
            } else { cod = 0; }

            if (etCantidad.getText().toString() != null) {
                cant = Integer.parseInt(etCantidad.getText().toString());
            } else { cant = 0; }

            if (etPrecio.getText().toString() != "") {
                precio =  Double.parseDouble(etPrecio.getText().toString());
            } else { precio = 0.0; }

        } catch (NumberFormatException e) {

        }


        i = new Intent(this, buttons.class);

        i.putExtra("codigo", cod);
        i.putExtra("nombre", nombre);
        i.putExtra("desc", desc);
        i.putExtra("cantidad", cant);
        i.putExtra("precio", precio);

        etCodigo.setText("");
        etNombre.setText("");
        etDesc.setText("");
        etCantidad.setText("");
        etPrecio.setText("");

        startActivity(i);
    }

}
