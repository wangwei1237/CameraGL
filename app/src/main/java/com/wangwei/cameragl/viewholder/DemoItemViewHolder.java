package com.wangwei.cameragl.viewholder;

import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wangwei.cameragl.R;
import com.wangwei.cameragl.model.DemoItem;

public class DemoItemViewHolder extends BaseViewHolder<DemoItem> {
    private TextView  mTV_name;
    private TextView  mTV_sign;

    public DemoItemViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_demo);

        mTV_name  = $(R.id.demo_name);
        mTV_sign  = $(R.id.demo_sign);
    }

    @Override
    public void setData(final DemoItem item){
        Log.d("ViewHolder","position" + getDataPosition());

        mTV_name.setText(item.getName());
        mTV_sign.setText(item.getSign());
    }
}
