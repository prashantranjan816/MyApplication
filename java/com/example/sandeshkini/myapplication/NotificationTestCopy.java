package com.example.sandeshkini.myapplication;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class NotificationTestCopy extends AppCompatActivity {


    private final String CHANNEL_ID = "personal_notification";
    private final int NOTIFICATION_ID = 007;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_notification_test );
    }

    public void ClickForNotification(View view) {

//        Bellow Seven Line Will Show A notification on Phone (Note: It will work till Android 7 only)

        NotificationCompat.Builder builder = new NotificationCompat.Builder( this, CHANNEL_ID );
        builder.setSmallIcon( R.drawable.ic_chat_black_24dp );
        builder.setContentTitle( "QA chat Notification" );
        builder.setContentText( "Hello Mr. Prashant Ranjan" );
        builder.setPriority( NotificationCompat.PRIORITY_DEFAULT );
        Notification notification = builder.build();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel( notification );
        } else {
            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from( this );
            notificationManagerCompat.notify( NOTIFICATION_ID, notification );
        }

    }

    //        If We want to use this notification on above 7 use channel
    @SuppressLint("NewApi")
    private void createNotificationChannel(Notification notification) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            CharSequence name = "Personal Notifications";
            String description = " Including all personal notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel( CHANNEL_ID, name, importance );
            notificationChannel.setDescription( description );

            NotificationManager notificationManager = (NotificationManager) getSystemService( NOTIFICATION_SERVICE );
            notificationManager.createNotificationChannel( notificationChannel );
            notificationManager.notify( NOTIFICATION_ID, notification );


        }
    }


}
