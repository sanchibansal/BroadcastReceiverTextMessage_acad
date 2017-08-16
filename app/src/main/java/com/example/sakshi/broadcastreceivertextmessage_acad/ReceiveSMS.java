package com.example.sakshi.broadcastreceivertextmessage_acad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by sakshi on 8/1/2017.
 */

public class ReceiveSMS extends BroadcastReceiver {
    // Get the object of SmsManager



    @Override
    public void onReceive(Context context, Intent intent) {
        // if the action received is equal to message received
        if(intent.getAction().equals("SMS_RECEIVED")){
            //creating bundle
            final Bundle bundle = intent.getExtras();

            try {
                if (bundle != null) {
                    //creating object
                    final Object[] pdusObj = (Object[]) bundle.get("pdus");
                    String phoneNumber = null;
                    for (int i = 0; i < pdusObj.length; i++) {

                        SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                        //getting sender's phone number
                        phoneNumber = currentMessage.getOriginatingAddress();

                        String senderNum = phoneNumber;
                        //getting Sender's text message
                        String message = currentMessage.getDisplayMessageBody();

                        Log.i("SmsReceiver", "senderNum: "+ senderNum + "; message: " + message);
                        //displaying toast
                        Toast.makeText(context, "senderNum: "+ senderNum + ", message: " + message, Toast.LENGTH_LONG).show();
                        final SmsManager sms = SmsManager.getDefault();
                        sms.sendTextMessage(senderNum,null,"hi",null,null);

                    } // end for loop
                } // bundle is null

            } catch (Exception e) {
                Log.e("ReceiveSMS", "Exception smsReceiver" + e);
            }
            }
        }

    }
