package com.example.android.mylegocreationary;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void cambiarMensaje(View view) {
        Resources res = getResources();
        String[] animal = res.getStringArray(R.array.animals_array);
        String randomStr = animal[new Random().nextInt(animal.length)];
        TextView texto = (TextView) findViewById(R.id.mensaje);
        texto.setText(randomStr);
    }

}
