package com.example.sendsmsonnoti

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseMessaging.getInstance().subscribeToTopic("SMS")
            .addOnCompleteListener { task ->
                var msg = getString(R.string.msg_subscribed)
                if (!task.isSuccessful) {
                    msg = getString(R.string.msg_subscribe_failed)
                }
                Log.d("TAG", msg)
                Toast.makeText(baseContext, msg, Toast.LENGTH_LONG).show()
            }
        var lbm = LocalBroadcastManager.getInstance(this)
        lbm.registerReceiver(broadcastReceiver, IntentFilter("update_sendsmsonnoti_log"))
    }

    val broadcastReceiver = object: BroadcastReceiver(){
        override fun onReceive(context: Context?,intent:Intent ){
            if (intent != null){
                    findViewById<TextView>(R.id.LogView).append(
                        intent.getStringExtra("AddString")+"\n"
                    )
                }
        }
    };
}
