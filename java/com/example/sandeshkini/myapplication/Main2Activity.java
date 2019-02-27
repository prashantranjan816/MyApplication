package com.example.sandeshkini.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main2 );


//        below four line to get value from first activity and display here.
        Intent intent = getIntent();
        String message1 = intent.getStringExtra( "EXTRA_MESSAGE1" );
        TextView textView = findViewById( R.id.Wellcome_message );
        textView.setText( "Welcome Back  " + message1 );

//Below line will show toast msg in activity2
        Toast.makeText( this, "Welcome Back  " + message1, Toast.LENGTH_SHORT ).show();


    }

    public void logout_submit(View view) {


        startActivity( new Intent( this, MainActivity.class ) );


    }
}
