package com.im_dsd.googlepaly.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.im_dsd.googlepaly.R;
import com.im_dsd.googlepaly.ui.fragment.FragmentFactory;
import com.im_dsd.googlepaly.ui.view.PagerTab;
import com.im_dsd.googlepaly.utils.UIUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    //绑定PagerTab（ViewPager指示器）
    @Bind(R.id.pt_indicator)
    PagerTab ptIndicator;

    //绑定ViewPager，用于展示各种能界面（由Fragment构成）
    @Bind(R.id.vp_fragments_container)
    ViewPager vpFragmentsContainer;

    //从资源文件中获取PagerTab的中ViewPager对应的各个tab的name
    private String[] mTabNameList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();

    }

    private void initView() {

        vpFragmentsContainer.setAdapter(new MyAdapter(getSupportFragmentManager()));
        ptIndicator.setViewPager(vpFragmentsContainer);


    }


    /**
     * 自定义Adapter，因为ViewPager的内容是Fragment所以直接使用 FragmentPagerAdapter 加速开发
     */
     class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);

            //从资源文件中获取PagerTab的中ViewPager对应的各个tab的name
            mTabNameList = UIUtils.getStringArrayResources(R.array.tab_names);
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return mTabNameList[position];
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentFactory.CreaterFragment(position);
        }

        @Override
        public int getCount() {
            return mTabNameList.length;
        }
    }
}
