package com.im_dsd.googlepaly.ui.activity;

import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.MenuItem;

import com.im_dsd.googlepaly.R;
import com.im_dsd.googlepaly.ui.fragment.BaseFragment;
import com.im_dsd.googlepaly.ui.fragment.FragmentFactory;
import com.im_dsd.googlepaly.ui.view.PagerTab;
import com.im_dsd.googlepaly.utils.UIUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.im_dsd.googlepaly.R.string.drawer_close;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    private DrawerLayout mDrawerLayout;// 侧边栏布局
    private ActionBarDrawerToggle mToggle;

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
        initActionBar();

    }

    /**
     * 初始化ActionBar
     */
    private void initActionBar() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        ActionBar actionBar = getSupportActionBar();
        // 左上角显示logo
        actionBar.setHomeButtonEnabled(true);

        // 左上角显示返回图标, 和侧边栏绑定后显示侧边栏图标
        actionBar.setDisplayHomeAsUpEnabled(true);

        // 初始化侧边栏开关
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer_am, R.string.drawer_open,
                drawer_close);// 参2:DrawerLayout对象, 参3:侧边栏开关图标,
        // 参4:打开侧边栏文本描述;参5:关闭侧边栏文本描述
        // 调用当前同步方法，才可以将侧拉菜单和按钮的点击操作绑定起来
        mToggle.syncState();
    }

    // ActionBar上的按钮被点击后的回调方法
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:// 左上角logo处被点击
                mToggle.onOptionsItemSelected(item);//侧边栏收起或者关闭
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initView() {

        final MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager());
        vpFragmentsContainer.setAdapter(myAdapter);

        ptIndicator.setViewPager(vpFragmentsContainer);

        ptIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i(TAG, "onPageSelected: position" + position );
                myAdapter.getItem(position).LoadDate();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
        public BaseFragment getItem(int position) {
            return FragmentFactory.CreaterFragment(position);
        }

        @Override
        public int getCount() {
            return mTabNameList.length;
        }

    }
}
