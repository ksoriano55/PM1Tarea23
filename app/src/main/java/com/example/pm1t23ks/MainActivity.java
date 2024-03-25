package com.example.pm1t23ks;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.util.regex.Pattern;

import Configuracion.SQLiteConexion;
import Configuracion.Transaccion;

public class MainActivity extends AppCompatActivity {
    static final int peticion_camara = 100;
    static final int  peticion_foto = 101;
    private MaterialButton btnTomarFoto, btnGuardar, btnListado;
    private TextInputEditText txtDescripcion;
    byte[] imagenBase64;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTomarFoto = findViewById(R.id.btnTomarFoto);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnListado = findViewById(R.id.btnVerFotos);
        imageView = findViewById(R.id.fotoPersona);
        txtDescripcion = findViewById(R.id.txtDescripcion);
        Intent intent = getIntent();
        btnTomarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObtenerPermisos();
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AgregarRegistro();
            }
        });

        btnListado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void ObtenerPermisos()
    {
        if(ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},
                    peticion_camara);
        }
        else
        {
            CapturarFoto();
        }
    }
    private void CapturarFoto()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(intent.resolveActivity(getPackageManager()) != null)
        {
            startActivityForResult(intent, peticion_foto);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == peticion_foto && resultCode == RESULT_OK)
        {
            Bundle extras = data.getExtras();
            Bitmap imagen = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imagen);

            /*---------Convertir imagen a base64-------*/
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            imagen.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            imagenBase64 = byteArray;//Base64.encodeToString(byteArray, Base64.DEFAULT);
        }
    }
    private void AgregarRegistro()
    {
            SQLiteConexion conexion = new SQLiteConexion(this, Transaccion.DBName, null, 1);
            SQLiteDatabase db = conexion.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put(Transaccion.descripcion, txtDescripcion.getText().toString());
            valores.put(Transaccion.imagen, imagenBase64);

            Long resultado = db.insert(Transaccion.TableImagenes, Transaccion.id, valores);

            Toast.makeText(getApplicationContext(), "Registro Ingresado con exito " + resultado.toString(),
                    Toast.LENGTH_LONG).show();

            db.close();
            //ClearTexts();
    }
}