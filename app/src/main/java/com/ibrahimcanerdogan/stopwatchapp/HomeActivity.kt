package com.ibrahimcanerdogan.stopwatchapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ibrahimcanerdogan.stopwatchapp.R
import com.ibrahimcanerdogan.stopwatchapp.databinding.ActivityHomeBinding
import kotlin.math.roundToInt

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var serviceIntent : Intent
    private var isStarted = false
    private var time = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            buttonStartStop.setOnClickListener {
                timerStartOrStop()
            }
            buttonReset.setOnClickListener {
                resetTimer()
            }
        }

        serviceIntent = Intent(applicationContext, StopWatchService::class.java)
        registerReceiver(updateTime, IntentFilter(StopWatchService.UPDATED_TIME))
    }

    private fun timerStartOrStop() {
        if (isStarted) {
            stopTimer()
        } else {
            startTimer()
        }
     }

    private fun startTimer() {
        serviceIntent.putExtra(StopWatchService.CURRENT_TIME, time)
        startService(serviceIntent)
        binding.buttonStartStop.text = "Stop"
        isStarted = true
    }

    private fun stopTimer() {
        stopService(serviceIntent)
        binding.buttonStartStop.text = "Start"
        isStarted = false

    }

    private fun resetTimer() {
        stopTimer()
        time = 0.0
        binding.textViewTimer.text = getFormattedTime(time)
    }

    private val updateTime : BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            time = intent.getDoubleExtra(StopWatchService.CURRENT_TIME, 0.0)
            binding.textViewTimer.text = getFormattedTime(time)
        }

    }

    private fun getFormattedTime(time : Double) : String {
        val timeInt = time.roundToInt()
        val hours = timeInt % 86400 / 3600
        val minutes = timeInt % 86400 % 3600 / 60
        val seconds = timeInt % 86400 % 3600 % 60

        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }
}