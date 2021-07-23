package com.createsapp.musicplayer.service

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.support.v4.media.session.MediaSessionCompat
import androidx.core.app.NotificationCompat
import com.createsapp.musicplayer.PlayerActivity
import com.createsapp.musicplayer.R
import com.createsapp.musicplayer.utils.ApplicationClass
import com.createsapp.musicplayer.utils.NotificationReceiver

class MusicService: Service() {

    private var myBinder = MyBinder()
    var mediaPlayer:MediaPlayer?=null
    private lateinit var mediaSession: MediaSessionCompat

    override fun onBind(intent: Intent?): IBinder? {
        mediaSession = MediaSessionCompat(baseContext, "My Music")
        return myBinder
    }

    inner class MyBinder:Binder(){
        fun currentService(): MusicService{
            return this@MusicService
        }
    }

    fun showNotification(playPauseBtn: Int){

        val prevIntent = Intent(baseContext, NotificationReceiver::class.java).setAction(ApplicationClass.PREVIOUS)
        val prevPendingIntent = PendingIntent.getBroadcast(baseContext,0,prevIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val playIntent = Intent(baseContext, NotificationReceiver::class.java).setAction(ApplicationClass.PREVIOUS)
        val playPendingIntent = PendingIntent.getBroadcast(baseContext,0,playIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val nextIntent = Intent(baseContext, NotificationReceiver::class.java).setAction(ApplicationClass.PREVIOUS)
        val nextPendingIntent = PendingIntent.getBroadcast(baseContext,0,nextIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val exitIntent = Intent(baseContext, NotificationReceiver::class.java).setAction(ApplicationClass.PREVIOUS)
        val exitPendingIntent = PendingIntent.getBroadcast(baseContext,0,exitIntent, PendingIntent.FLAG_UPDATE_CURRENT)


        val notification = NotificationCompat.Builder(baseContext, ApplicationClass.CHANNEL_ID)
            .setContentTitle(PlayerActivity.musicListPA[PlayerActivity.songPosition].title)
            .setContentText(PlayerActivity.musicListPA[PlayerActivity.songPosition].artUri)
            .setSmallIcon(R.drawable.music_icon)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.music_player))
            .setStyle(androidx.media.app.NotificationCompat.MediaStyle().setMediaSession(mediaSession.sessionToken))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setOnlyAlertOnce(true)
            .addAction(R.drawable.previous_icon, "Previous", prevPendingIntent)
            .addAction(playPauseBtn, "Play", playPendingIntent)
            .addAction(R.drawable.next_icon, "Next", nextPendingIntent)
            .addAction(R.drawable.exit_icon, "Exit", exitPendingIntent)
            .build()

        startForeground(13, notification)
    }
}