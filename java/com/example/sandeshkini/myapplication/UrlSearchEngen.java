package com.example.sandeshkini.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class UrlSearchEngen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_url_search_engen );

        //below four line to get value from first activity and display here.
        Intent intent = getIntent();
        String message = intent.getStringExtra( "EXTRA_MESSAGE" );
        TextView textView = findViewById( R.id.viewbox );
        textView.setText( message );



    }
}
