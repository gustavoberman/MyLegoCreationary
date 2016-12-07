package com.example.android.mylegocreationary;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private int[] fotosArray = {R.drawable.cosas,R.drawable.naturaleza,R.drawable.transportes,R.drawable.edificios,R.drawable.doble,R.drawable.elegir};

    public void cambiarMensaje(View view) {
        Resources res = getResources();

        String[] animal = res.getStringArray(R.array.animals_array);
        String randomStr = animal[new Random().nextInt(animal.length)];

        TextView texto = (TextView) findViewById(R.id.mensaje);
        texto.setText(randomStr);

        ImageView mostrarImagen = (ImageView) findViewById(R.id.imagen);


        //Rotar la imagen como si fuese un dado
        Animation rotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        mostrarImagen.startAnimation(rotate);
        //Y mostrar una imagen al azar
        mostrarImagen.setImageResource(fotosArray[new Random().nextInt(fotosArray.length)]);

    }

}
