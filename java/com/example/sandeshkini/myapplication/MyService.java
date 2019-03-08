package com.example.sandeshkini.myapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

public class MyService extends Service {

    private MediaPlayer player;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;

    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (Build.VERSION.SDK_INT >= 26) {
            String channelId = "9998";
            NotificationChannel channel = new NotificationChannel( channelId, "This is the Channel", NotificationManager.IMPORTANCE_DEFAULT );
            ((NotificationManager) getSystemService( Context.NOTIFICATION_SERVICE )).createNotificationChannel( channel );

            Notification notification = new NotificationCompat.Builder( this, channelId )
                    .setContentTitle( "This is Title" )
                    .setContentText( "This is Text" )
                    .build();

            startForeground( 1, notification );
        }


        player = MusicSystem.getMediaPlayerInstance( this );
        if (player.isPlaying()) {
            player.stop();
        }
//        player.pause();
//        player.start();
        player.setLooping( true );
        player.start();
        return START_STICKY;
    }

    @Override
    public boolean stopService(Intent name) {
        stopForeground( true );
        stopSelf();
        return super.stopService( name );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        player.stop();
    }
}
