package com.ym.swiperefreshlayoutdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
/**
 * @className: MainActivity
 * @classDescription: 首页
 * @author: leibing
 * @createTime: 2016/8/16
 */
public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    private SwipeRefreshLayout mSwipeRefresh;
    private ListView mListView;
    //声明并初始化ListView的数据源
    private List<String> list = new ArrayList<>();
    //声明ListView的适配器
    private ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 初始化SwipeRefreshLayout
        mSwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_test);
        // 为SwipeRefreshLayout设置监听事件
        mSwipeRefresh.setOnRefreshListener(this);
        // 为SwipeRefreshLayout设置刷新时的颜色变化，最多可以设置4种
        mSwipeRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        // 初始化ListView
        mListView = (ListView) findViewById(R.id.lv_test);
        // 初始化适配器
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getData());
        // 设置适配器
        mListView.setAdapter(adapter);
        // 加载
        mSwipeRefresh.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefresh.setRefreshing(true);
                //刷新
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //结束后停止刷新
                        mSwipeRefresh.setRefreshing(false);
                    }
                }, 3000);
            }
        });
    }

    /**
     * 增加数据
     * @author leibing
     * @createTime 2016/8/16
     * @lastModify 2016/8/16
     * @param
     * @return
     */
    private List<String> getData() {
        list.add("hello");
        list.add("this is johnsonHou");
        list.add("an android rookie developer");
        list.add("love android");
        return list;
    }

    @Override
    public void onRefresh() {
        //刷新
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //结束后停止刷新
                mSwipeRefresh.setRefreshing(false);
            }
        }, 3000);
    }
}
