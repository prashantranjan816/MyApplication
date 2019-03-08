package com.example.sandeshkini.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class DiceRoller extends AppCompatActivity {
    private ImageView imageViewDice;
    private Random rng = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_dice_roller );
        imageViewDice = findViewById( R.id.image_view_dice );
        imageViewDice.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();
            }
        } );
    }

    private void rollDice() {
        int randomNumber = rng.nextInt( 6 ) + 1;

        switch (randomNumber) {
            case 1:
                imageViewDice.setImageResource( R.drawable.dice1 );
                break;
            case 2:
                imageViewDice.setImageResource( R.drawable.dice2 );
                break;
            case 3:
                imageViewDice.setImageResource( R.drawable.dice3 );
                break;
            case 4:
                imageViewDice.setImageResource( R.drawable.dice4 );
                break;
            case 5:
                imageViewDice.setImageResource( R.drawable.dice5 );
                break;
            case 6:
                imageViewDice.setImageResource( R.drawable.dice6 );
                break;
        }
    }


}
