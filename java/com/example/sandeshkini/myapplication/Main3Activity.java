package com.example.sandeshkini.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener {


    String user = "https://google.com";
    String message;
    private Button start, stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main3 );

//This two line will take data from edit tex box.
        EditText editText = findViewById( R.id.google_search );
        String message = editText.getText().toString();


        Intent intent = getIntent();
//        Below one line will send extra message to next activity.
        intent.putExtra( "EXTRA_MESSAGE", message );


//        Below code for music button

        start = (Button) findViewById( R.id.Mstart );
        stop = (Button) findViewById( R.id.Mstop );

        start.setOnClickListener( this );
        stop.setOnClickListener( this );


    }

    @Override
    public void onClick(View view) {
        if (view == start) {
            startService( new Intent( this, MyService.class ) );

        } else if (view == stop) {
            stopService( new Intent( this, MyService.class ) );

        }


    }


    public void enterToFregment(View view) {


        Intent intent = new Intent( this, Main4Activity.class );

        startActivity( intent );
    }

    public void search_button(View view) {

        Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( user ) );
        startActivity( intent );

        Toast.makeText( this, message, Toast.LENGTH_SHORT ).show();
    }


//    private void updateUI(){
//        Intent intent = new Intent(this,Main3Activity.class);
//
////       Below one line will send extra message to next activity.
//        intent.putExtra("EXTRA_MESSAGE1",user);
//
//        startActivity(intent);
//    }


    public void callSearchEngin(View view) {
        //This two line will take dat frim edit tex box.
        EditText editText = findViewById( R.id.google_search );
        String message = editText.getText().toString();

        startActivity( new Intent( this, UrlSearchEngen.class ) );

        Intent intent = getIntent();
//        Below one line will send extra message to next activity.
        intent.putExtra( "EXTRA_MESSAGE", message );

    }


//    ===============*** Service Test button ***==========================


    public void logout_submit(View view) {


        startActivity( new Intent( this, MainActivity.class ) );


    }


    public void stop_muzic(View view) {

    }

    public void start_music(View view) {

//        startActivity(new Intent(this,MusicSystem.class));


    }


    public void NextPage(View view) {

        startActivity( new Intent( this, NotificationTest.class ) );
    }
}
