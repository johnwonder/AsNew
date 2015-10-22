package com.ufida.john.mycountdown;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by jn on 2015/10/20.
 * http://www.cnblogs.com/jerehedu/p/4885259.html
 */
public class ClockActivity extends Activity {

    private TextView tvClock;
    public static  final String CLOCK_ACTION ="com.ufida.john.Clock_Action";
    public  static int TIME= 2*60*60*1000;//倒计时2小时
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_clock);
        tvClock = (TextView) findViewById(R.id.tvClock);

        regReceiver();
        startService(new Intent(this, ClockService.class));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        super.unregisterReceiver(clockReceiver);

        TIME = 2*60*60*1000;
        Intent  intent = new Intent();
        intent.setAction(ClockService.CLOCK_SERVICE_ACTION);
        intent.putExtra("method", "stop");
        super.sendBroadcast(intent);

    }

    private void regReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(CLOCK_ACTION);
        super.registerReceiver(clockReceiver,intentFilter);
    }

    private BroadcastReceiver clockReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
                changetime();
        }
    };

    public  void restart(View view) {
        Intent intent = new Intent();
        intent.setAction(ClockService.CLOCK_SERVICE_ACTION);
        intent.putExtra("method", "continue");
        super.sendBroadcast(intent);

    }

    public void pause(View view) {
        Intent intent = new Intent();
        intent.setAction(ClockService.CLOCK_SERVICE_ACTION);
        intent.putExtra("method", "pause");
        super.sendBroadcast(intent);
    }

    private void changetime() {
        String stime = "";
        if (TIME == 0) {
            stime = "倒计时结束";

        } else {

            int hour = TIME / (1000 * 60 * 60);
            int minute = TIME % (1000 * 60 * 60) / (60 * 1000);
            int second=(TIME%(1000*60*60))%(60*1000)/1000;
            String shour=""+hour,sminute=""+minute,ssecond=""+second;
            if(hour<=9){
                shour="0"+hour;
            }
            if(minute<=9){
                sminute="0"+minute;
            }
            if (second<=9){
                ssecond="0"+second;
            }
            stime=shour+":"+sminute+":"+ssecond;
        }
        tvClock.setText(stime);
    }
}
