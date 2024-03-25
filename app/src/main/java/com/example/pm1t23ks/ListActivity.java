package com.example.pm1t23ks;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Configuracion.SQLiteConexion;
import Configuracion.Transaccion;
import Models.Photograh;

public class ListActivity extends AppCompatActivity {
    ListView listImages;
    ArrayList<Photograh> lista;
    SimpleAdapter adp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listImages = (ListView) findViewById(R.id.listView);
        ObtenerListado();
    }

    private void ObtenerListado() {
        SQLiteConexion conexion = new SQLiteConexion(this, Transaccion.DBName, null, 1);
        SQLiteDatabase db = conexion.getReadableDatabase();
        Photograh photograh = null;
        lista = new ArrayList<Photograh>();

        Cursor cursor = db.rawQuery(Transaccion.SelectAllPhotograph, null);

        while(cursor.moveToNext()){
            photograh = new Photograh();
            photograh.setId(cursor.getInt(0));
            photograh.setDescripcion(cursor.getString(1));
            photograh.setImagen(cursor.getBlob(2));
            lista.add(photograh);
        }

        PhotographAdapter adapter = new PhotographAdapter(this, R.layout.list_items, lista);
        listImages.setAdapter(adapter);
    }
}