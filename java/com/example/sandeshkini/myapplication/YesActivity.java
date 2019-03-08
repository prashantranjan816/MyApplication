package com.example.sandeshkini.myapplication;

import android.app.NotificationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class YesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_yes );

        NotificationManager notificationManager = (NotificationManager) getSystemService( NOTIFICATION_SERVICE );
        notificationManager.cancel( NotificationTest.NOTIFICATION_ID );


    }

}
