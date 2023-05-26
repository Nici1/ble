



package com.punchthrough.blestarterappandroid

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.punchthrough.blestarterappandroid.R




class Detect : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == "com.example.ACTION_DATA_RECEIVED") {
                val data = intent.getStringExtra("data")
                if (data != null) {
                    if (data == "c") {
                        imageView.setImageResource(R.drawable.chicken)
                    } else if (data == "g") {
                        imageView.setImageResource(R.drawable.goat_foreground)
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detect)

        imageView = findViewById(R.id.imageView)

        // Register the broadcast receiver to receive the data broadcast
        val filter = IntentFilter("com.example.ACTION_DATA_RECEIVED")
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, filter)
    }

    override fun onDestroy() {
        // Unregister the broadcast receiver
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver)

        super.onDestroy()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        // Process the new intent and update the UI accordingly
        val data = intent.getStringExtra("data")
        if (data != null) {
            if (data == "c") {
                imageView.setImageResource(R.drawable.chicken)
            } else if (data == "g") {
                imageView.setImageResource(R.drawable.goat_foreground)
            }
        }
    }
}


