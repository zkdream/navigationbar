package com.zk.navigationbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        new DefaultNavigationBar.Builder(this).hideLeftIcon().setMiddleText("首页").create();
    }
}
