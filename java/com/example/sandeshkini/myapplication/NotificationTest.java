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

public class NotificationTest extends AppCompatActivity {


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

//        Below three line will for landing sreen when click on notification drower
        Intent landingIntent = new Intent( this, MainActivity.class );
        landingIntent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
        PendingIntent landingPandingIntent = PendingIntent.getActivities( this, 0, new Intent[]{landingIntent}, PendingIntent.FLAG_ONE_SHOT );


        // Below three line will for landing sreen when click on "Yes" button of notification drower
        Intent YesIntent = new Intent( this, YesActivity.class );
        YesIntent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
        PendingIntent YesPendingIntent = PendingIntent.getActivities( this, 0, new Intent[]{YesIntent}, PendingIntent.FLAG_ONE_SHOT );


        // Below three line will for landing sreen when click on "NO" button of notification drower
        Intent NoIntent = new Intent( this, NoActivity.class );
        NoIntent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
        PendingIntent NoPendingIntent = PendingIntent.getActivities( this, 0, new Intent[]{NoIntent}, PendingIntent.FLAG_ONE_SHOT );


//        Bellow Seven Line Will Show A notification on Phone (Note: It will work till Android 7 only)

        NotificationCompat.Builder builder = new NotificationCompat.Builder( this, CHANNEL_ID );
        builder.setSmallIcon( R.drawable.ic_chat_black_24dp );
        builder.setContentTitle( "QA chat Notification" );
        builder.setContentText( "Hello Mr. Prashant Ranjan ,Go to Home screen" );
        builder.setPriority( NotificationCompat.PRIORITY_DEFAULT );

        builder.setAutoCancel( true );  //This line will disapear the notification after click on notification.
        builder.setContentIntent( landingPandingIntent ); //This line will land to defined page after click on notification.


//        Below for line for notification action option "Yes & No".
        builder.addAction( R.drawable.ic_chat_black_24dp, " Yes", YesPendingIntent );
        builder.addAction( R.drawable.ic_chat_black_24dp, " No", NoPendingIntent );


        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from( this );
        notificationManagerCompat.notify( NOTIFICATION_ID, builder.build() );


    }


    //Create Notification with Reply option
    public void CreateNotificationWithReply(View view) {

        createNotificationChannel(); //Call notification channel


//        Bellow Seven Line Will Show A notification on Phone (Note: It will work till Android 7 only)

        NotificationCompat.Builder builder1 = new NotificationCompat.Builder( this, CHANNEL_ID );
        builder1.setSmallIcon( R.drawable.ic_chat_black_24dp );
        builder1.setContentTitle( "QA chat Notification" );
        builder1.setContentText( "Hello Mr. Prashant Ranjan ,Go to Home screen" );
        builder1.setPriority( NotificationCompat.PRIORITY_DEFAULT );
        builder1.setAutoCancel( true );  //This line will disapear the notification after click on notification.
        builder1.setContentIntent( landingPandingIntent ); //This line will land to defined page after click on notification.

        //        Bellow lines is for reply in notification from notification it self.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {


            android.support.v4.app.RemoteInput remoteInput = new RemoteInput.Builder( TXT_REPLY ).setLabel( "reply" ).build();
            Intent replayIntent = new Intent( this, RemotRecive.class );
            replayIntent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
            PendingIntent replyPendingIntent = PendingIntent.getActivities( this, 0, new Intent[]{replayIntent}, PendingIntent.FLAG_ONE_SHOT );
            NotificationCompat.Action action = new NotificationCompat.Action.Builder( R.drawable.ic_chat_black_24dp, "Reply"
                    , replyPendingIntent ).addRemoteInput( remoteInput ).build();

            builder1.addAction( action );


        }


        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from( this );
        notificationManagerCompat.notify( NOTIFICATION_ID, builder1.build() );


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
