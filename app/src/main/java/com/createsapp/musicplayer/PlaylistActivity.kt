package com.createsapp.musicplayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.createsapp.musicplayer.databinding.ActivityPlayerBinding

class PlaylistActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_MusicPlayer)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}