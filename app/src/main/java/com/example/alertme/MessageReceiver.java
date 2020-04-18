package com.example.alertme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.io.IOException;

public class MessageReceiver extends BroadcastReceiver {

    private static MessageListener mListener;

    private Context _context;
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle data = intent.getExtras();
        _context= context;
        Object[] pdus = (Object[]) data.get("pdus");
        for (int i = 0; i < pdus.length; i++) {
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);
            String message = "Sender : " + smsMessage.getDisplayOriginatingAddress()
                    + "Email From: " + smsMessage.getEmailFrom()
                    + "Emal Body: " + smsMessage.getEmailBody()
                    + "Display message body: " + smsMessage.getDisplayMessageBody()
                    + "Time in millisecond: " + smsMessage.getTimestampMillis()
                    + "Message: " + smsMessage.getMessageBody();

            if ( smsMessage.getDisplayMessageBody().equalsIgnoreCase("Some Unauthorized access")) {

                startSound("");
            }

            Log.d("Sms", message);

           // mListener.messageReceived(message);
        }
    }

    public static void bindListener(MessageListener listener) {
        mListener = listener;
    }


    private void startSound(String filename) {
        MediaPlayer m = new MediaPlayer();
        try{
            AssetFileDescriptor descriptor = _context.getAssets().openFd("a1.mp3");
            m.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength() );
            descriptor.close();
            m.prepare();
            m.start();
        } catch(Exception e){
            // handle error here..

            Log.d("e",e.getMessage());
        }
    }


}