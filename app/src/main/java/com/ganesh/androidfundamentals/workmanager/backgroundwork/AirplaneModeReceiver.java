package com.ganesh.androidfundamentals.workmanager.backgroundwork;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AirplaneModeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Ganesh", "onReceive AirplaneModeReceiver");
    }
    
}