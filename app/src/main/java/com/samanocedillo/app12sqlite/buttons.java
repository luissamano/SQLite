package com.samanocedillo.app12sqlite;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class buttons extends AppCompatActivity {

    int Id, Cant;
    String Nombre, Descripcion;
    Double Precio;
    TextView txv1,txv2,txv3,txv4,txv5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttons);

        txv1 = (TextView) findViewById(R.id.txv1);
        txv2 = (TextView) findViewById(R.id.txv2);
        txv3 = (TextView) findViewById(R.id.txv3);
        txv4 = (TextView) findViewById(R.id.txv4);
        txv5 = (TextView) findViewById(R.id.txv5);

        Bundle params = getIntent().getExtras();

        Id = params.getInt("codigo");
        Nombre = params.getString("nombre");
        Descripcion = params.getString("desc");
        Cant = params.getInt("cantidad");
        Precio = params.getDouble("precio");

        txv1.setText("Codigo: " +Id);
        txv2.setText("Nombre: " +Nombre);
        txv3.setText("Descripcion: "  +Descripcion);
        txv4.setText("Cantidad: " +Cant);
        txv5.setText("Precio: " +Precio);


        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("Detalle del producto");
        dialogo1.setMessage("Selecciona alguna accion para realizar con los datos recuperados.");
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                aceptar();
            }
        });
        dialogo1.show();


        //Toast.makeText(this, "Los datos se recuperaron de la actividad anterior.", Toast.LENGTH_LONG).show();

    }

    public void aceptar() {

    }

    public void alta (View view) {
        AdminSQLite  admin = new AdminSQLite(this,
                "dbase", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();

        registro.put("codigo", Id);
        registro.put("nombre", Nombre);
        registro.put("descripcion", Descripcion);
        registro.put("cantidad", Cant);
        registro.put("precio", Precio);

        db.insert("art", null, registro);
        db.close();


        Toast.makeText(this, "Se cargaron los datos del articulo", Toast.LENGTH_LONG).show();

    }

    public void consultarporcodigo(View view) {
        AdminSQLite  admin = new AdminSQLite(this,
                "dbase", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();

        Cursor fila = db.rawQuery("select nombre, descripcion, cantidad, precio from art where codigo = '"+ Id +"'", null);
        Log.i("Fila Query", "consultarporcodigo: " +fila.getWantsAllOnMoveCalls());
        if (fila.moveToFirst()) {
            txv1.setText("Codigo: " +Id);
            txv2.setText("Nombre: " +fila.getString(0));
            txv3.setText("Descripcion: "  +fila.getString(1));
            txv4.setText("Cantidad: " +fila.getInt(2));
            txv5.setText("Precio: " +fila.getDouble(3));
        }
        else {
            Toast.makeText(this, "No exite un articulo con dicho codigo", Toast.LENGTH_LONG).show();
        }

        db.close();
    }

    public void consultapordescripcion(View view) {
        AdminSQLite  admin = new AdminSQLite(this,
                "dbase", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();



        Cursor fila = db.rawQuery("select codigo, nombre, cantidad, precio from art where descripcion = '"+ Descripcion +"'", null);
        if (fila.moveToFirst()) {
            txv1.setText("Codigo: "  +fila.getString(0));
            txv2.setText("Nombre: " +fila.getString(1));
            txv3.setText("Descripcion: " +Descripcion);
            txv4.setText("Cantidad: " +fila.getString(2));
            txv5.setText("Precio: " +fila.getString(3));
        }
        else {
            Toast.makeText(this, "No exite un articulo con dicha descripcion", Toast.LENGTH_LONG).show();
        }

        db.close();
    }


    public void bajarporcodigo(View view) {
        AdminSQLite  admin = new AdminSQLite(this,
                "dbase", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();


        int cant = db.delete("art", "codigo=" + Id, null);
        db.close();


        if (cant == 1) {
            Toast.makeText(this, "Se borro el articulo con dicho codigo", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "No existe articulo con dicho codigo", Toast.LENGTH_LONG).show();
        }

    }


    public void modificacion (View view) {

        AdminSQLite  admin = new AdminSQLite(this,
                "dbase", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();


        ContentValues registro = new ContentValues();
        registro.put("codigo", Id);
        registro.put("nombre", Nombre);
        registro.put("descripcion", Descripcion);
        registro.put("cantidad", Cant);
        registro.put("precio", Precio);


        int cant = db.update("art", registro, "codigo=" + Id, null);
        db.close();

        if (cant == 1) {
            Toast.makeText(this, "Se modifico con exito", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "No se realizo ningun cambio", Toast.LENGTH_LONG).show();
        }

    }
}
