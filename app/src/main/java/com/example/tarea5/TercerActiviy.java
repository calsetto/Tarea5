package com.example.tarea5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class TercerActiviy extends AppCompatActivity implements View.OnClickListener {

    TextView nombre, sexo;
    String nom, sex;
    Button salir;
    //Imagenes
    private static final Random rgenerador = new Random();
    private static final Integer[] MaculinoimagenesID =
            {R.drawable.am, R.drawable.bm, R.drawable.cm, R.drawable.dm, R.drawable.em};

    private static final Integer[] FemeninoimagenesID =
            {R.drawable.af, R.drawable.bf, R.drawable.cf, R.drawable.df, R.drawable.de};

    //Frases
    String MaculinoFrases[] = {
      "Quiero que tu mundo empiece y acabe conmigo", "¿Quieres respeto? Ve fuera y consíguelo por ti mismo",
            "Soy el hombre de la libertad, esa es toda la fortuna que tengo",
            "Lo interesante es cómo un hombre, a través de vivir sus propias fantasías, vive las fantasías de otras personas",
            "Llamar a las mujeres el sexo débil es una calumnia; es la injusticia del hombre hacia la mujer"
    };

    String FemeninoFrases[] = {
            "Para liberarse, la mujer debe sentirse libre, no para rivalizar con los hombres, sino libres en sus capacidades y personalidad",
            "La vida es corta: sonríele a quien llora, ignora a quien te critica, y sé feliz con quien te importa",
            "Usted no puede esperar construir un mundo mejor sin mejorar a las personas. Cada uno de nosotros debe trabajar para su propia mejora",
            "No nacemos como mujer, sino que nos convertimos en una",
            "La ceguera nos separa de las cosas que nos rodea, pero la sordera nos separa de las personas"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tercer_activiy);
        nombre = (TextView) findViewById(R.id.nombre1);
        sexo = (TextView) findViewById(R.id.sexo1);

        nom = getIntent().getStringExtra("Nombre");
        nombre.setText(nom);
        sex = getIntent().getStringExtra("Genero");
        sexo.setText(sex);

        salir = (Button) findViewById(R.id.salir2);
        salir.setOnClickListener(this);

        //Variables Random

        Integer q = MaculinoimagenesID[rgenerador.nextInt(MaculinoimagenesID.length)];
        Integer f = FemeninoimagenesID[rgenerador.nextInt(FemeninoimagenesID.length)];


    }

    public void getBack(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.salir2:
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
        }
    }

    public void SendtoImagen(View view){
        Intent i = new Intent(this, Imagen.class);
        int resource;
        if(sexo.getText().toString().equals("Masculino")){
            resource = MaculinoimagenesID[rgenerador.nextInt(MaculinoimagenesID.length)];
        }else{
            resource = FemeninoimagenesID[rgenerador.nextInt(FemeninoimagenesID.length)];
        }
        i.putExtra("Nombre", nombre.getText().toString());
        i.putExtra("Genero", sexo.getText().toString());
        i.putExtra("Imagen", resource);
        startActivity(i);
    }

    public void SenttoFrases(View view){
        Intent i = new Intent(this, Frases.class);

        Random fgenerador = new Random();
        int num ;
        if(sexo.getText().toString().equals("Masculino")){
            num = fgenerador.nextInt(MaculinoFrases.length);
            i.putExtra("Frase", MaculinoFrases[num]);
        }else{
            num = fgenerador.nextInt(FemeninoFrases.length);
            i.putExtra("Frase", FemeninoFrases[num]);
        }

        i.putExtra("Nombre", nombre.getText().toString());
        i.putExtra("Genero", sexo.getText().toString());

        startActivity(i);
    }
}
