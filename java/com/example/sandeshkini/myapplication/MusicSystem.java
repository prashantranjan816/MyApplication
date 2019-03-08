package com.example.sandeshkini.myapplication;

import android.content.Context;
import android.media.MediaPlayer;
import android.provider.Settings;

import java.lang.ref.WeakReference;

public class MusicSystem {
    private static MediaPlayer mMediaPlayer;
    private static WeakReference<Context> weakReference;

    private MusicSystem() {
    }

    public static MediaPlayer getMediaPlayerInstance(Context context) {
        if (mMediaPlayer == null) {
            weakReference = new WeakReference<Context>( context );
            mMediaPlayer = MusicSystemHelper.MUSIC_SYSTEM;
        }
        return mMediaPlayer;
    }

    static class MusicSystemHelper {
        static final MediaPlayer MUSIC_SYSTEM = MediaPlayer.create( weakReference.get(), Settings.System.DEFAULT_ALARM_ALERT_URI );
    }
}
