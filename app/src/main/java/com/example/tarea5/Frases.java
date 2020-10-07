package com.example.tarea5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;

public class Frases extends AppCompatActivity implements View.OnClickListener {

    TextView nombre, sexo, frase;
    String nom, sex, fra;
    Button salir, compartir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frases);

        nombre = (TextView) findViewById(R.id.nombre3);
        sexo = (TextView) findViewById(R.id.sexo3);
        frase = (TextView) findViewById(R.id.frases);
        salir = (Button) findViewById(R.id.salir4);
        compartir = (Button) findViewById(R.id.compartir2);
        compartir.setOnClickListener(this);
        salir.setOnClickListener(this);

        nom = getIntent().getStringExtra("Nombre");
        nombre.setText(nom);
        sex = getIntent().getStringExtra("Genero");
        sexo.setText(sex);
        fra = getIntent().getStringExtra("Frase");
        frase.setText(fra);
    }

    public void getBack(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.salir4:
                Context context;
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Alerta");
                builder.setMessage("Desea salir de la aplicacion?")
                        .setCancelable(false)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                intent.addCategory(Intent.CATEGORY_HOME);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
                break;

            case R.id.compartir2:

                try {
                    File file = new File(getApplicationContext().getExternalCacheDir(), File.separator + frase.getText().toString());
                    FileOutputStream fOut = new FileOutputStream(file);
                    fOut.flush();
                    fOut.close();
                    file.setReadable(true, false);
                    final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Uri photoURI = FileProvider.getUriForFile(getApplicationContext(), BuildConfig.APPLICATION_ID +".provider", file);

                    intent.putExtra(Intent.EXTRA_STREAM, photoURI);
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intent.setType("String");

                    startActivity(Intent.createChooser(intent, "Share image via"));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
        }
    }
}
