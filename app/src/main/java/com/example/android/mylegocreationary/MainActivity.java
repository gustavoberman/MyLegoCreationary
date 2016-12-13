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


    private int[] dadoArray = {R.drawable.dice1,R.drawable.dice2,R.drawable.dice3,R.drawable.dice4,R.drawable.dice5,R.drawable.dice6};
    private int[] cartasArray = {R.drawable.lego_03,R.drawable.lego_04,R.drawable.lego_05,R.drawable.lego_06,R.drawable.lego_07,R.drawable.lego_08,R.drawable.lego_09,R.drawable.lego_10,R.drawable.lego_11,R.drawable.lego_12,R.drawable.lego_13,R.drawable.lego_14,R.drawable.lego_15,R.drawable.lego_16,R.drawable.lego_17,R.drawable.lego_18,R.drawable.lego_19,R.drawable.lego_20,R.drawable.lego_21,R.drawable.lego_22,R.drawable.lego_23,R.drawable.lego_24,R.drawable.lego_25,R.drawable.lego_26,R.drawable.lego_27,R.drawable.lego_28,R.drawable.lego_29,R.drawable.lego_30,R.drawable.lego_31,R.drawable.lego_32,R.drawable.lego_33};

    public void lanzarDado(View view) {
//        Resources res = getResources();

//        String[] animal = res.getStringArray(R.array.animals_array);
//        String randomStr = animal[new Random().nextInt(animal.length)];

//        TextView texto = (TextView) findViewById(R.id.mensaje);
//        texto.setText(randomStr);

        ImageView mostrarImagen = (ImageView) findViewById(R.id.imagen);
        ImageView mostrarCarta = (ImageView) findViewById(R.id.carta);

        //Rotar la imagen como si fuese un dado
        Animation rotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);

        mostrarImagen.startAnimation(rotate);
        //Y mostrar una imagen al azar
        mostrarImagen.setImageResource(dadoArray[new Random().nextInt(dadoArray.length)]);

        //lo mismo con la carta
        mostrarCarta.startAnimation(rotate);
        mostrarCarta.setImageResource(cartasArray[new Random().nextInt(cartasArray.length)]);
    }

}
