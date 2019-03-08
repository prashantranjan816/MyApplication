package com.example.sandeshkini.myapplication;

import android.app.NotificationManager;
import android.app.RemoteInput;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class RemotRecive extends AppCompatActivity {
    Bundle remoteReply;
    String Inpit = "";
    private TextView textView;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_remot_recive );

        textView = findViewById( R.id.txt_display );
        remoteReply = RemoteInput.getResultsFromIntent( getIntent() );

        if (remoteReply != null) {

            String message = (String) remoteReply.getCharSequence( NotificationTest.TXT_REPLY.toString() );
            textView.setText( message );
        }


        NotificationManager notificationManager = (NotificationManager) getSystemService( NOTIFICATION_SERVICE );
        notificationManager.cancel( NotificationTest.NOTIFICATION_ID );
    }

    public void Start_Game(View view) {
        if (remoteReply != null) {
            Intent intent = new Intent( this, BottleGame.class );

            startActivity( intent );
        }
    }

    public void TicTak_Game(View view) {
        if (remoteReply != null) {
            Intent intent = new Intent( this, TicTac.class );

            startActivity( intent );
        }
    }

    public void Dice_Roller(View view) {
        if (remoteReply != null) {
            Intent intent = new Intent( this, DiceRoller.class );

            startActivity( intent );
        }

    }

    public void Animation_Image(View view) {
        if (remoteReply != null) {
            Intent intent = new Intent( this, ImageAnimation.class );

            startActivity( intent );
        }

    }
}
