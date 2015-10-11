package com.ufida.john.mydrawerlayout;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private DrawerLayout drawer_layout;
    private ListView list_left_drawer;
    private ArrayList<Item> menuLists;
    private  MyAdapter<Item> myAdapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        list_left_drawer = (ListView) findViewById(R.id.list_left_drawer);

        menuLists = new ArrayList<Item>();
        menuLists.add(new Item(R.mipmap.iv_menu_realtime,"实时信息"));
        menuLists.add(new Item(R.mipmap.iv_menu_alert,"提醒通知"));
        menuLists.add(new Item(R.mipmap.iv_menu_trace,"活动路线"));
        menuLists.add(new Item(R.mipmap.iv_menu_settings,"相关设置"));
        myAdapter = new MyAdapter<Item>(menuLists,R.layout.item_list){

            @Override
            public void bindView(ViewHolder holder, Item obj) {
                 holder.setImageResource(R.id.img_icon,obj.getIconId());
                holder.setText(R.id.txt_content,obj.getIconName());
            }
        };

        list_left_drawer.setAdapter(myAdapter);
        list_left_drawer.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ContentFragment contentFragment = new ContentFragment();
        Bundle  args = new Bundle();
        args.putString("text",menuLists.get(position).getIconName());
        contentFragment.setArguments(args);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.ly_content,contentFragment).commit();
        drawer_layout.closeDrawer(list_left_drawer);
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
