package com.example.myapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {
    private var notificationManager: NotificationManager? = null
    private val CHANNEL_ID = "chennal01"
    private val name = "chennal"
    private val channelDescription = "rendom number chennal"
    private val requestCode = 101 // 원하는 고유한 값으로 변경
    private lateinit var pendingIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var ToastButton: Button = findViewById(R.id.ToastButton)
        var CountButton: Button = findViewById(R.id.CountButton)
        var RandomButton: Button = findViewById(R.id.RandomButton)
        val output_text: TextView = findViewById<TextView>(R.id.CountView)
        var num = 0;

        notificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val intent = Intent(this, SubActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }


        ToastButton.setOnClickListener {
            Toast.makeText(this@MainActivity, "Toast message", Toast.LENGTH_SHORT).show()
        }

        CountButton.setOnClickListener {
            num++
            output_text.setText(num.toString())
        }

        createNotificationChannel()
        RandomButton.setOnClickListener {
            intent.putExtra("num", num)
            pendingIntent = PendingIntent.getActivity(this, 0, intent, FLAG_IMMUTABLE or FLAG_UPDATE_CURRENT)
          //  startActivity(intent)
            createBuilder()

        }

    }


    //채널 생성
    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT // set importance
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = channelDescription
            }

              //  assert(notificationManager != null)
            // Register the channel with the system
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager?.createNotificationChannel(channel)
        }
    }


    private fun createBuilder(){
        val notificationId = 0
        val priority = NotificationCompat.PRIORITY_DEFAULT  // Set PRIORITY
        var builder = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_child)   // Set Icon
            .setContentTitle("random")  // Set Title
            .setContentText("랜덤숫자가 공개됩니다")   // Set Content
            .setPriority(priority)
            .setContentIntent(pendingIntent) // Notification Click Event
            .setAutoCancel(true) // Remove After Click Notification
            .build()
        //return builder
        notificationManager?.notify(notificationId, builder)
    }
}