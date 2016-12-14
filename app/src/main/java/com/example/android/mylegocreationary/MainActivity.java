package com.example.android.mylegocreationary;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    SoundPool dice_sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
    int sound_id;        //Used to control sound stream return by SoundPool
    private int[] dadoArray = {R.drawable.dice1, R.drawable.dice2, R.drawable.dice3, R.drawable.dice4, R.drawable.dice5, R.drawable.dice6};
    private int[] cartasArray = {R.drawable.lego_03, R.drawable.lego_04, R.drawable.lego_05, R.drawable.lego_06, R.drawable.lego_07, R.drawable.lego_08, R.drawable.lego_09, R.drawable.lego_10, R.drawable.lego_11, R.drawable.lego_12, R.drawable.lego_13, R.drawable.lego_14, R.drawable.lego_15, R.drawable.lego_16, R.drawable.lego_17, R.drawable.lego_18, R.drawable.lego_19, R.drawable.lego_20, R.drawable.lego_21, R.drawable.lego_22, R.drawable.lego_23, R.drawable.lego_24, R.drawable.lego_25, R.drawable.lego_26, R.drawable.lego_27, R.drawable.lego_28, R.drawable.lego_29, R.drawable.lego_30, R.drawable.lego_31, R.drawable.lego_32, R.drawable.lego_33};

    int rDado, rCarta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //load dice sound
        sound_id = dice_sound.load(this, R.raw.shake_dice, 1);

        //recuperar el estado si viene de una vista anterior
        if (savedInstanceState != null) {
            rDado = savedInstanceState.getInt("dado");
            rCarta = savedInstanceState.getInt("carta");
            ImageView mostrarDado = (ImageView) findViewById(R.id.dado);
            ImageView mostrarCarta = (ImageView) findViewById(R.id.carta);
            mostrarDado.setImageResource(dadoArray[rDado]);
            mostrarCarta.setImageResource(cartasArray[rCarta]);
        }
    }

    //Si la pantalla la vista se tiene que destruir, guardar el estado del dado y de la carta
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("dado", rDado);
        savedInstanceState.putInt("carta", rCarta);
    }

    public void lanzarDado(View view) {
        ImageView mostrarDado = (ImageView) findViewById(R.id.dado);
        ImageView mostrarCarta = (ImageView) findViewById(R.id.carta);

        //Seleccionar un lado del dado y una carta al azar
        rDado = new Random().nextInt(dadoArray.length);
        rCarta = new Random().nextInt(cartasArray.length);

        //Start rolling sound
        dice_sound.play(sound_id, 1.0f, 1.0f, 0, 0, 1.0f);

        //Rotar la imagen como si fuese un dado
        Animation rotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        mostrarDado.startAnimation(rotate);
        //Y mostrar una imagen al azar
        mostrarDado.setImageResource(dadoArray[rDado]);
        //lo mismo con la carta
        mostrarCarta.startAnimation(rotate);
        mostrarCarta.setImageResource(cartasArray[rCarta]);


    }


}
