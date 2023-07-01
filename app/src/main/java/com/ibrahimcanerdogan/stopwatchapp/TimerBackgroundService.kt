package com.ibrahimcanerdogan.stopwatchapp

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class TimerBackgroundService : Service() {

    init {
        Log.i(TAG, "Service has been created!")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "Service has been started!")
        val name = intent?.getStringExtra("NAME")
        val marks = intent?.getIntExtra("MARKS", 0)
        Log.i(TAG, "Name: $name, Marks: $marks")
        return START_STICKY
    }

    /*
    START_STICKY: When a service is started with the "START_STICKY" flag, it tells the system to recreate the service and call its onStartCommand() method after it has been killed, as long as there are still pending start requests to be delivered. The service will be restarted with a null Intent, so you need to handle this case appropriately in your code.
    START_NOT_STICKY: If the service is killed, it will not be restarted automatically.
    START_REDELIVER_INTENT: If the service is killed, it will be restarted and the last delivered Intent will be redelivered to the service's onStartCommand() method.
    START_FLAG_REDELIVERY: If the service is killed, it will be restarted and the last delivered Intent will be redelivered to the service's onStartCommand() method.
    START_FLAG_RETRY: Indicates that the service should be started with a retry attempt if it gets killed before completing its work. This flag is typically used in conjunction with START_FLAG_REDELIVERY.
    START_FLAG_FOREGROUND: Starts the service in the foreground, indicating that it is performing a task that the user is actively aware of and may need to interact with. This flag is commonly used for services that display ongoing notifications.
    START_FLAG_NOT_FOREGROUND: The opposite of START_FLAG_FOREGROUND. This flag is used to start the service in the background, indicating that it is performing a background task without direct user interaction.
    START_FLAG_INCLUDE_STOPPED_PACKAGES: Includes services in stopped packages while starting. This flag allows you to start a service even if its package is in a stopped state.
    START_FLAG_ACTIVITY_CLEAR_TOP: If the service is already running and has an associated activity, this flag clears the activity stack above the target activity and brings it to the top of the stack.
    START_FLAG_ACTIVITY_NEW_TASK: Starts the service in a new task. This flag is commonly used when starting a service from outside of an existing task or when launching an activity from the service.
    */

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        Log.i(TAG, "Service has been destroyed!")
        super.onDestroy()
    }

    companion object {
        const val TAG = "MAINTAG"
    }
}