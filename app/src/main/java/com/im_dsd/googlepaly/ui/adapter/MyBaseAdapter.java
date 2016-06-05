package com.im_dsd.googlepaly.ui.adapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.im_dsd.googlepaly.ui.holder.BaseHolder;
import com.im_dsd.googlepaly.ui.holder.LoadMoreHolder;
import com.im_dsd.googlepaly.utils.UIUtils;

import java.util.ArrayList;

import static com.im_dsd.googlepaly.ui.holder.LoadMoreHolder.TYPE_HAS_MORE;

/**
 * 万能的BaseAapter
 * Created by im_dsd on 16-6-4.
 */

public abstract class MyBaseAdapter<T> extends BaseAdapter {

    private static final String TAG = "MyBaseAdapter";

    //viewType的数目
    private final int VIEW_TYPE_COUNT = 2;

    //加载更多的ListView条目
    private final int VIEW_TYPE_MORE = 1;

    //普通ListView条目
    private final int VIEW_TYPE_NORMAL = 0;

    private final ArrayList<T> mArrayListData;

    public OnLoadMoreDataListener mLoadMoreDataListener;

    /**
     * @param mArrayListData 需要使用的数据的集合
     */
    public MyBaseAdapter(ArrayList<T> mArrayListData) {
        this.mArrayListData = mArrayListData;
    }

    @Override
    public int getCount() {
        return mArrayListData.size() + 1;
    }

    @Override
    public T getItem(int position) {
        return mArrayListData.get(position);
    }


    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        if (getCount() - 1 == position) {
            //展示最后的加载更多条目
            return VIEW_TYPE_MORE;
        } else {
            //展示普通的item
            return getInnerType(position);
        }
    }

    public int getInnerType(int position) {
        return VIEW_TYPE_NORMAL;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 判断是否需要加载更多功能
     *
     * @return true 拥有，反之 没有。
     */
    public boolean hasLoadMore() {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        BaseHolder viewHolder = null;

        if (convertView != null) {
            view = convertView;
            viewHolder = ((BaseHolder) view.getTag());
        } else {
            //展示加载跟多类型
            if (getItemViewType(position) == VIEW_TYPE_MORE) {

                viewHolder = new LoadMoreHolder(hasLoadMore());
            }
            //如果类型是普通类型，加载普通类型
            else if (getItemViewType(position) == getInnerType(position)) {
                viewHolder = getHolder();
            }

        }

        if (getItemViewType(position) != VIEW_TYPE_MORE) {

            //设置数据
            viewHolder.setData(getItem(position));
        } else {
            LoadMoreHolder loadMoreHolder = (LoadMoreHolder) viewHolder;

            if (loadMoreHolder.getData() == TYPE_HAS_MORE) {
                Log.i(TAG, "getView: loadMoreHolder.getData() == VIEW_TYPE_MORE");
                LoadMore(loadMoreHolder);
            }

        }

        //设置布局
        view = viewHolder.getItemRootView();
        return view;
    }

    /**
     * 加载更对数据
     *
     * @param loadMoreHolder
     */
    private void LoadMore(LoadMoreHolder loadMoreHolder) {
        new MyThread(loadMoreHolder).start();
    }

    /**
     * 获取BaseHolder的子类 getHolder
     *
     * @return 开发者自己实现的Holder
     */
    public abstract BaseHolder<T> getHolder();

    /**
     * 设置对于加载更多数据的监听。
     *
     * @param listener
     */
    public void setOnLoadMoreDataListener(OnLoadMoreDataListener listener) {
        mLoadMoreDataListener = listener;
    }


    /**
     * 加载更多数据的监听。
     */
    public interface OnLoadMoreDataListener<T> {
        /**
         * 加载更多数据。
         *
         * @return 加载的数据集合
         */
        ArrayList<T> OnLoadMoreData();
    }

    /**
     * 加载数据。
     * 优化：静态成员内部类不具有外部类对象的强引用。在内部类（线程）不完成的情况下也可以销毁。
     * 但是加上 static 会带来其他开销，--》其使用的数据必须也要时 static
     */
    private class MyThread extends Thread {

        private final LoadMoreHolder loadMoreHolder;

        public MyThread(LoadMoreHolder loadMoreHolder) {
            this.loadMoreHolder = loadMoreHolder;
            Log.i(TAG, "run: mLoadMoreDataListener != null");
        }

        @Override
        public void run() {
            super.run();
            if (mLoadMoreDataListener != null) {
                Log.i(TAG, "run: mLoadMoreDataListener != null");
                final ArrayList<T> loadMoreData = mLoadMoreDataListener.OnLoadMoreData();

                if (loadMoreData != null) {


                    //在数线程中刷新Adapter
                    UIUtils.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //根据当前加载来的数据集合长度，判断下一次加载更多时候的试图状态。
                            if (loadMoreData.size() < 20) {
                                //长度小于 20，说明没有更多的数据可供加载
                                loadMoreHolder.setData(LoadMoreHolder.TYPE_NO_MORE);
                            } else {
                                //大于20，下一次还要加载跟多。
                                loadMoreHolder.setData(TYPE_HAS_MORE);
                            }

                            //将加载来的新页，添加到主list中
                            mArrayListData.addAll(loadMoreData);
                            notifyDataSetChanged();
                        }
                    });


                } else {
                    //如果数据为空（没有拿到数据），代表状态 错误。
                    loadMoreHolder.setData(LoadMoreHolder.TYPE_LOAD_ERROR);
                }
            }
            Log.i(TAG, "run: mLoadMoreDataListener == null");
        }
    }

}