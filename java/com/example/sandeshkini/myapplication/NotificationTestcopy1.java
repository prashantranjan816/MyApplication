package com.example.sandeshkini.myapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class NotificationTestcopy1 extends AppCompatActivity {


    public static final int NOTIFICATION_ID = 007;
    public static final String TXT_REPLY = "text_reply";
    private final String CHANNEL_ID = "personal_notification";
    private PendingIntent landingPandingIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_notification_test );
    }


    public void ClickForNotification(View view) {
        createNotificationChannel(); //Call notification channel


//        Bellow Seven Line Will Show A notification on Phone (Note: It will work till Android 7 only)

        NotificationCompat.Builder builder = new NotificationCompat.Builder( this, CHANNEL_ID );
        builder.setSmallIcon( R.drawable.ic_chat_black_24dp );
        builder.setContentTitle( "QA chat Notification" );
        builder.setContentText( "Hello Mr. Prashant Ranjan ,Go to Home screen" );
        builder.setPriority( NotificationCompat.PRIORITY_DEFAULT );

        builder.setAutoCancel( true );  //This line will disapear the notification after click on notification.
        builder.setContentIntent( landingPandingIntent ); //This line will land to defined page after click on notification.

//        Bellow lines is for reply in notification from notification it self.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {


            RemoteInput remoteInput = new RemoteInput.Builder( TXT_REPLY ).setLabel( "reply" ).build();
            Intent replayIntent = new Intent( this, RemotRecive.class );
            replayIntent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
            PendingIntent replyPendingIntent = PendingIntent.getActivities( this, 0, new Intent[]{replayIntent}, PendingIntent.FLAG_ONE_SHOT );
            NotificationCompat.Action action = new NotificationCompat.Action.Builder( R.drawable.ic_chat_black_24dp, "Reply"
                    , replyPendingIntent ).addRemoteInput( remoteInput ).build();

            builder.addAction( action );


        }

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from( this );
        notificationManagerCompat.notify( NOTIFICATION_ID, builder.build() );


    }

    //        If We want to use this notification on above 7 use channel so create channel
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            CharSequence name = "Personal Notifications";
            String description = " Including all personal notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel( CHANNEL_ID, name, importance );
            notificationChannel.setDescription( description );

            NotificationManager notificationManager = (NotificationManager) getSystemService( NOTIFICATION_SERVICE );
            notificationManager.createNotificationChannel( notificationChannel );

        }

    }


}
