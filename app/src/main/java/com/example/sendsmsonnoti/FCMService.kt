package com.example.sendsmsonnoti

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.telephony.SmsManager
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FCMService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        var intent = Intent("update_sendsmsonnoti_log")
        intent.putExtra("AddString",remoteMessage.data["phone number"])

        var intentSucess = Intent("update_sendsmsonnoti_log")
        intent.putExtra("AddString","Send SMS successed")
        var intentDelivered = Intent("update_sendsmsonnoti_log")
        intent.putExtra("AddString","Send SMS failed")

        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
        if(remoteMessage.data["phone number"]!=null)
        {
            SmsManager.getDefault().sendTextMessage(remoteMessage.data["phone number"],null,"This is an automate sms",null,null)
        }
        //super.onMessageReceived(remoteMessage)
    }
}
