package com.im_dsd.googlepaly.ui.fragment;

import java.util.HashMap;

/**
 * Fragment工厂类
 * Created by im_dsd on 16-6-3.
 */
public class FragmentFactory {

    //用于保存已经创建好的Fragment，方便复用
    private static HashMap<Integer,BaseFragment> mFragmentMap =
            new HashMap<Integer,BaseFragment>();

    public static BaseFragment CreaterFragment(int position)
    {
        BaseFragment fragment = mFragmentMap.get(position);

        if (fragment == null)
        {
            switch (position) {
                case 0:
                    fragment = new HomeFragment();
                    break;
                case 1:
                    fragment = new AppFragment();
                    break;
                case 2:
                    fragment = new GameFragment();
                    break;
                case 3:
                    fragment = new SubjectFragment();
                    break;
                case 4:
                    fragment = new RecommendFragment();
                    break;
                case 5:
                    fragment = new CategoryFragment();
                    break;
                case 6:
                    fragment = new HotFragment();
                    break;

            }

            mFragmentMap.put(position,fragment);
        }


        return  fragment;
    }

}
