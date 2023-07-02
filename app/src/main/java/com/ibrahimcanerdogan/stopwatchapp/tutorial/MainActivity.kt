package com.ibrahimcanerdogan.stopwatchapp.tutorial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ibrahimcanerdogan.stopwatchapp.tutorial.TimerBackgroundService.Companion.TAG
import com.ibrahimcanerdogan.stopwatchapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Create Service Intent
        val serviceIntent = Intent(this, TimerBackgroundService::class.java)
        serviceIntent.putExtra("NAME", "Ibrahim")
        serviceIntent.putExtra("MARKS", 78)

        binding.apply {
            buttonStart.setOnClickListener {
                Log.i(TAG, "Service Starting!")
                startService(serviceIntent)
            }
            buttonStop.setOnClickListener {
                Log.i(TAG, "Service Stopping!")
                stopService(serviceIntent)
            }
        }
    }
}