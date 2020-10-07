package com.example.tarea5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Formulario extends AppCompatActivity implements View.OnClickListener {
Button salir;
TextView name;
RadioButton masculino, femenino;
EditText nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        salir = (Button) findViewById(R.id.salir1);
        salir.setOnClickListener(this);

        masculino = (RadioButton) findViewById(R.id.masculino);
        femenino = (RadioButton) findViewById(R.id.femenino);
        nombre = (EditText) findViewById(R.id.nombre);

    }

    //METODOS POR DEFAULT
    public void getBack(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
    public void getOut(){
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.salir1:
                getOut();
                break;
        }
    }

    //METODOS PROPIOS DE LA CLASE

    public void TercerActivity(View view){
        if(nombre.length() > 0 && masculino.isChecked() || femenino.isChecked()) {
            if (masculino.isChecked()) {
                Intent i = new Intent(this, TercerActiviy.class);
                i.putExtra("Nombre", nombre.getText().toString());
                i.putExtra("Genero", "Masculino");
                startActivity(i);
            } else {
                Intent i = new Intent(this, TercerActiviy.class);
                i.putExtra("Nombre", nombre.getText().toString());
                i.putExtra("Genero", "Femenino");
                startActivity(i);
            }
        }else {
            Toast.makeText(this, "Debes llenar los campos", Toast.LENGTH_SHORT).show();
        }
    }




}
