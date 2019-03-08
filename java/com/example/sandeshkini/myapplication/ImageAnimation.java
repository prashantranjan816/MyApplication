package com.example.sandeshkini.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Toast;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;
import com.flaviofaria.kenburnsview.Transition;

public class ImageAnimation extends AppCompatActivity {
    private KenBurnsView kbv;
    private boolean moving = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_image_animation );


        kbv = findViewById( R.id.kbv );

        AccelerateDecelerateInterpolator adi = new AccelerateDecelerateInterpolator();
        RandomTransitionGenerator generator = new RandomTransitionGenerator( 2000, adi );
        kbv.setTransitionGenerator( generator );

        kbv.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (moving) {
                    kbv.pause();
                    moving = false;
                } else {
                    kbv.resume();
                    moving = true;
                }
            }
        } );

        kbv.setTransitionListener( new KenBurnsView.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                Toast.makeText( ImageAnimation.this, "Started", Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                Toast.makeText( ImageAnimation.this, "Finished", Toast.LENGTH_SHORT ).show();
            }
        } );

    }
}
