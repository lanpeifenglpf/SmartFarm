package com.scujcc.smartfarm.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.scujcc.smartfarm.R;
import com.scujcc.smartfarm.base.BaseFragment;
import com.scujcc.smartfarm.control.fragment.ControlFragment;
import com.scujcc.smartfarm.detail.fragment.DetailFragment;
import com.scujcc.smartfarm.home.fragment.HomeFragment;
import com.scujcc.smartfarm.user.fragment.UserFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {

    @BindView(R.id.fragmentLayout)
    FrameLayout mFragmentLayout;
    @BindView(R.id.rb_home)
    RadioButton mRbHome;
    @BindView(R.id.rb_detail)
    RadioButton mRbDetail;
    @BindView(R.id.rb_control)
    RadioButton mRbControl;
    @BindView(R.id.rb_user)
    RadioButton mRbUser;
    @BindView(R.id.rg_main)
    RadioGroup mRgMain;

    private ArrayList<BaseFragment> fragments;
    private int position;
    private BaseFragment mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //绑定活动
        ButterKnife.bind(this);

        initFragment();
        initListenner();
    }


    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new DetailFragment());
        fragments.add(new ControlFragment());
        fragments.add(new UserFragment());
    }

    private void initListenner() {
        mRgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.rb_home:
                        position = 0;
                        break;
                    case R.id.rb_detail:
                        position = 1;
                        break;
                    case R.id.rb_control:
                        position = 2;
                        break;
                    case R.id.rb_user:
                        position = 3;
                        break;
                }

                BaseFragment baseFragment = getFragment(position);
                switchFragment(mContext, baseFragment);
            }
        });

        mRgMain.check(R.id.rb_home);
    }


    private BaseFragment getFragment(int position) {
        if (fragments != null && fragments.size()>0)
        {
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }

    private void switchFragment(Fragment fromFragment, BaseFragment nextFragment) {
        if (mContext != nextFragment)
        {
            mContext = nextFragment;
            if (nextFragment != null)
            {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                if (!nextFragment.isAdded())
                {
                    if (fromFragment != null)
                    {
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.fragmentLayout, nextFragment).commit();
                } else
                {
                    if (fromFragment != null)
                    {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }

    }

}
