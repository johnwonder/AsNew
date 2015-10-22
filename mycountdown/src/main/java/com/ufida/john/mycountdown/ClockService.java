package com.ufida.john.mycountdown;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by jn on 2015/10/20.
 */
public class ClockService extends Service {
    public static final String CLOCK_SERVICE_ACTION = "clock_service_action";
    private boolean controllerOpt= true;

    private BroadcastReceiver serviceController =new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String method = intent.getStringExtra("method");
            switch (method) {
                case "pause":
                    controllerOpt =false;
                    break;
                case "continue":
                    controllerOpt =true;
                    countTime();
                    break;
                case "stop":
                    controllerOpt =false;
                    stopSelf();
                    break;
            }
        }
    };
    public ClockService() {

    }

    @Override
    public void onCreate() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(CLOCK_SERVICE_ACTION);
        super.registerReceiver(serviceController, intentFilter);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        countTime();
        return  Service.START_STICKY;
    }

    private void countTime() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ClockActivity.CLOCK_ACTION);
                while (controllerOpt) {
                    try {
                        Thread.sleep(1000);
                        if (ClockActivity.TIME <= 0) {
                            sendBroadcast(intent);
                            stopSelf();
                            break;
                        }
                        ClockActivity.TIME -= 1000;
                        sendBroadcast(intent);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("");
    }

    @Override
    public void onDestroy() {
        super.unregisterReceiver(serviceController);

    }
}
