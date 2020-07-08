package com.ganesh.androidfundamentals.workmanager.backgroundwork;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class UserPresentBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, final Intent intent) {
        Log.d("Ganesh", "onReceive UserPresentBroadcastReceiver");
    }
}