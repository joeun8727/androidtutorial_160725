package com.example.a.a18_boradcasterreciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String str = "";
        SmsMessage[] msgs = null;
        Object[] pdus = (Object[]) intent.getExtras().get("pdus");
        msgs = new SmsMessage[pdus.length];

        for(int i=0; i< msgs.length; i++){
            msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
            str += "SMS from : " + msgs[i].getOriginatingAddress() + " msg : " + msgs[i].getMessageBody();
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
        }
    }
}
