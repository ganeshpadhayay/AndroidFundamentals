package com.ganesh.androidfundamentals.multithreading;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppExecutor {

    private static volatile AppExecutor INSTANCE;
    private Handler uiHandler;
    private ExecutorService executorService;

    private AppExecutor() {
        uiHandler = new Handler(Looper.getMainLooper());
        executorService = Executors.newFixedThreadPool(3);
    }

    public static AppExecutor getExecutor() {
        if (INSTANCE == null) {
            synchronized (AppExecutor.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AppExecutor();
                }
            }
        }
        return INSTANCE;
    }

    public void runOnUiThread(Runnable runnable) {
        uiHandler.post(runnable);
    }

    public void runOnBgThread(Runnable runnable) {
        executorService.execute(runnable);
    }
}
