package com.ufida.john.myeventbus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import de.greenrobot.event.EventBus;


public class MainActivity extends Activity {

    private Button btn;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //注册EventBus
        EventBus.getDefault().register(this);

        btn = (Button)findViewById(R.id.btn_try);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//取消注册
    }

    public void onEventMainThread(FirstEvent event){
        String msg = "onEventMainThread收到了消息:"+event.getmMsg();
        Log.d("ufida",msg);
        tv.setText(msg);
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    public void onEventMainThread(SecondEvent event) {
        Log.d("ufida", "onEventMainThread收到了消息:" + event.getmMsg());
    }

    public void onEventBackgroundThread(SecondEvent event) {
        Log.d("ufida", "onEventBackground收到了消息:" + event.getmMsg());
    }

    public void onEventAsync(SecondEvent event) {
        Log.d("ufida","onEventAsync收到了消息:"+event.getmMsg());
    }

    public void onEvent(ThirdEvent event) {
        Log.d("ufida","OnEvent收到了消息:"+event.getmMsg());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
