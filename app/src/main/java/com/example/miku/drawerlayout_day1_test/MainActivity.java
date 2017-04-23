package com.example.miku.drawerlayout_day1_test;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> arrayList;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private ListView listView;
    private Toolbar toolbar;
    private ArrayAdapter<String> adapter;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                //Toast.makeText(MainActivity.this, "Toobar", Toast.LENGTH_SHORT).show();
                drawerLayout.openDrawer(listView);
                break;

        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list_view);
        toolbar = (Toolbar) findViewById(R.id.toobar);
        toolbar.setNavigationIcon(R.mipmap.ic_home);
        toolbar.setTitle("Hello");
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //支持Toobar
        setSupportActionBar(toolbar);
        //监听侧边栏状态
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close){
            @Override
            public void onDrawerClosed(View drawerView) {
                toolbar.setTitle("Hello");
                //Toast.makeText(MainActivity.this, "Hello world", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
               toolbar.setTitle("World");
            }
        };
        drawerLayout.addDrawerListener(toggle);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getdata());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = arrayList.get(position);
                FragmentManager manager = getSupportFragmentManager();
                Fragment fragment = new Custom_Fragment();
                Bundle args = new Bundle();
                args.putString("data", data);
                fragment.setArguments(args);
                manager.beginTransaction().replace(R.id.main_layout, fragment).commit();
                //关闭侧边栏
                drawerLayout.closeDrawer(listView);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toobar, menu);
        return true;
    }

    private List<String> getdata() {
        arrayList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            arrayList.add("item:" + i);
        }
        return arrayList;
    }
}
