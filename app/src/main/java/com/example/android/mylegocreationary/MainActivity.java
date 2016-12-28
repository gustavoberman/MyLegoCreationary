package com.example.android.mylegocreationary;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    SoundPool dice_sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
    int sound_id;        //Used to control sound stream return by SoundPool
    private int[] dadoArray = {R.drawable.dice1, R.drawable.dice2, R.drawable.dice3, R.drawable.dice4, R.drawable.dice5, R.drawable.dice6};
    private int[] cartasArray = {R.drawable.lego_03, R.drawable.lego_04, R.drawable.lego_05, R.drawable.lego_06, R.drawable.lego_07, R.drawable.lego_08, R.drawable.lego_09, R.drawable.lego_10, R.drawable.lego_11, R.drawable.lego_12, R.drawable.lego_13, R.drawable.lego_14, R.drawable.lego_15, R.drawable.lego_16, R.drawable.lego_17, R.drawable.lego_18, R.drawable.lego_19, R.drawable.lego_20, R.drawable.lego_21, R.drawable.lego_22, R.drawable.lego_23, R.drawable.lego_24, R.drawable.lego_25, R.drawable.lego_26, R.drawable.lego_27, R.drawable.lego_28, R.drawable.lego_29, R.drawable.lego_30, R.drawable.lego_31, R.drawable.lego_32, R.drawable.lego_33};
    String[] dadoNombre = {"Elegir categoría", "Cosas", "Naturaleza", "Puntos dobles", "Edificios", "Vehículos"};
    int rDado, rCarta;

    TextToSpeech t1;

    TextView timerText;
    boolean timerIsSet=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //load dice sound
        sound_id = dice_sound.load(this, R.raw.shake_dice, 1);

        //tts
        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    //String resultado = t1.getLanguage().toString();
                    //String resultado = (String) status;
                    //Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_SHORT).show();
                    //t1.setLanguage(Locale.es_ES);
                }
            }
        });

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate menu
        getMenuInflater().inflate(R.menu.menu_main, menu);

        //timer
        MenuItem timerItem = menu.findItem(R.id.break_timer);
        timerText = (TextView) MenuItemCompat.getActionView(timerItem);

        timerText.setPadding(10, 0, 10, 0); //Or something like that...




        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_reglas:
                //code to run
                Intent intent = new Intent(this, ReglasActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

        //nombrar resultado dado
        t1.speak(dadoNombre[rDado], TextToSpeech.QUEUE_FLUSH, null);

        if (!timerIsSet) {
            startTimer(30000, 1000); //One tick every second for 30 seconds
            timerIsSet = true;
        }
    }

    //tts
    public void onPause() {
        if (t1 != null) {
            t1.stop();
            t1.shutdown();
        }
        super.onPause();
    }



    //timer
    private void startTimer(long duration, long interval) {

        CountDownTimer timer = new CountDownTimer(duration, interval) {

            @Override
            public void onFinish() {
                //TODO Whatever's meant to happen when it finishes
                t1.speak("Terminó el tiempo!", TextToSpeech.QUEUE_FLUSH, null);
            }

            @Override
            public void onTick(long millisecondsLeft) {
                int secondsLeft = (int) Math.round((millisecondsLeft / (double) 1000));
                timerText.setText(secondsToString(secondsLeft));
            }
        };

        timer.start();
    }

    private String secondsToString(int improperSeconds) {

        //Seconds must be fewer than are in a day

        Time secConverter = new Time();

        secConverter.hour = 0;
        secConverter.minute = 0;
        secConverter.second = 0;

        secConverter.second = improperSeconds;
        secConverter.normalize(true);

        String hours = String.valueOf(secConverter.hour);
        String minutes = String.valueOf(secConverter.minute);
        String seconds = String.valueOf(secConverter.second);

        if (seconds.length() < 2) {
            seconds = "0" + seconds;
        }
        if (minutes.length() < 2) {
            minutes = "0" + minutes;
        }
        if (hours.length() < 2) {
            hours = "0" + hours;
        }

        String timeString = hours + ":" + minutes + ":" + seconds;
        return timeString;
    }

}
