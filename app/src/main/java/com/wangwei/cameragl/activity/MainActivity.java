package com.wangwei.cameragl.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

import com.wangwei.cameragl.R;
import com.wangwei.cameragl.model.DemoItem;
import com.wangwei.cameragl.utils.ConvertDP;
import com.wangwei.cameragl.utils.DemoListDataProvider;
import com.wangwei.cameragl.utils.PermissionUtil;
import com.wangwei.cameragl.viewholder.DemoItemViewHolder;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;


public class MainActivity extends AppCompatActivity implements RecyclerArrayAdapter.OnItemClickListener {
    private EasyRecyclerView               m_recyclerView;
    private RecyclerArrayAdapter<DemoItem> m_adapter;
    private String                         TAG        = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 权限获取
        PermissionUtil.permissionRequest(this);

        initView();
    }

    private void initView() {
        // 初始化UI和数据
        LinearLayoutManager layoutManager;
        m_recyclerView = findViewById(R.id.rv_demo_list);
        layoutManager  = new LinearLayoutManager(this);
        m_recyclerView.setLayoutManager(layoutManager);

        DividerDecoration itemDecoration;
        itemDecoration = new DividerDecoration(Color.GRAY,
                ConvertDP.dip2px(this,1f),
                0,
                0);
        itemDecoration.setDrawLastItem(false);
        m_recyclerView.addItemDecoration(itemDecoration);

        m_recyclerView.setAdapterWithProgress(m_adapter = new RecyclerArrayAdapter<DemoItem>(this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new DemoItemViewHolder(parent);
            }
        });

        m_adapter.addAll(DemoListDataProvider.getDemoList());

        // 设置Item点击事件
        m_adapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(int position) {
        Log.d(TAG, "点击了" + position);
        ArrayList<DemoItem> arr = DemoListDataProvider.getDemoList();
        DemoItem item = arr.get(position);

        if (item.getCls() != null) {
            startActivity(new Intent(this, item.getCls()));
        } else {
            Toast.makeText(this,
                    "点击了" + position,
                    LENGTH_SHORT).show();
        }
    }
}
