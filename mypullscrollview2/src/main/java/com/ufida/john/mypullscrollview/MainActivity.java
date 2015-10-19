package com.ufida.john.mypullscrollview;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

    private TextView  mRootView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRootView = (TextView) findViewById(R.id.tv);

        Button btnMoveDown = (Button) findViewById(R.id.layout_down);
        Button  btnAnimationUp = (Button) findViewById(R.id.animation_up);

        btnMoveDown.setOnClickListener(this);
        btnAnimationUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case  R.id.layout_down:
            {
                mRootView.layout(mRootView.getLeft(),mRootView.getTop()+100,mRootView.getRight(),mRootView.getBottom()+100);
                break;
            }
            case  R.id.animation_up: {
                TranslateAnimation animation = new TranslateAnimation(0, 0, 0, -100);
                animation.setDuration(200);
                mRootView.startAnimation(animation);
                break;
            }
        }
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
