package com.scujcc.smartfarm.detail.fragment;

import android.view.View;
import android.widget.TextView;

import com.scujcc.smartfarm.base.BaseFragment;

/**
 * Created by  范朝波 on 2017/11/8.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */

public class DetailFragment extends BaseFragment {
    @Override
    public View initView() {
        TextView textView = new TextView(getActivity());
        textView.setText("这是详情页");
        textView.setPadding(300,400,0,0);
        textView.setTextSize(32);

        return textView;
    }
}
