package com.wangwei.cameragl.utils;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.wangwei.cameragl.model.DemoItem;
import com.wangwei.cameragl.viewholder.DemoItemViewHolder;

public class DemoListAdapter extends RecyclerArrayAdapter<DemoItem> {
    public DemoListAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new DemoItemViewHolder(parent);
    }
}
