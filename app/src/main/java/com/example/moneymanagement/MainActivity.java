package com.example.moneymanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewParent;
import android.widget.TabHost;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private List<Fragment> fragmentList;
    private TabLayout tabLayout;
    private List<String> titleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
//        TabHost tabHost = findViewById(R.id.tabhost);
//        //初始化TabHost容器
//        tabHost.setup();
//        LayoutInflater inflater = LayoutInflater.from(this);
//        //在TabHost创建标签，然后设置：标题／图标／标签页布局
//        inflater.inflate(R.layout.activity_keep_accounts, tabHost.getTabContentView());
//        inflater.inflate(R.layout.activity_statement, tabHost.getTabContentView());
//        inflater.inflate(R.layout.activity_fund, tabHost.getTabContentView());
//        inflater.inflate(R.layout.activity_mine, tabHost.getTabContentView());
//        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("记账").setContent(R.id.keepAccount));
//        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("报表").setContent(R.id.statement));
//        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("资金").setContent(R.id.fund));
//        tabHost.addTab(tabHost.newTabSpec("tab4").setIndicator("我的").setContent(R.id.mine));

    }
    private void initView(){
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);

        fragmentList = new ArrayList<>();
        fragmentList.add(new KeepAccount());
        fragmentList.add(new Statement());
        fragmentList.add(new Fund());
        fragmentList.add(new Mine());

        titleList = new ArrayList<>();
        titleList.add("记账");
        titleList.add("报表");
        titleList.add("资金");
        titleList.add("我的");

        // 实例化适配器
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(), fragmentList,titleList);
        viewPager.setAdapter(adapter);

        // 给tab设置一个viewpager
        tabLayout.setupWithViewPager(viewPager);

        //给viewpager设置监听器
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            // 选中
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        Toast.makeText(MainActivity.this,"记账", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(MainActivity.this,"报表", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(MainActivity.this,"资金", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(MainActivity.this,"我的", Toast.LENGTH_SHORT).show();
                        break;
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}