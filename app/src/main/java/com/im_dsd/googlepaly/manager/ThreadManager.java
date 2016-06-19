package com.im_dsd.googlepaly.manager;

import android.util.Log;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by im_dsd on 16-6-18.
 */
public class ThreadManager {

    public static final String TAG = "ThreadManager";
    private static ThreadPool mThreadPool;

    public static ThreadPool getThreadPool()
    {
        if (mThreadPool == null)
        {
            synchronized (ThreadPool.class)
            {
                if (mThreadPool == null)
                {
                    int cpuCount = Runtime.getRuntime().availableProcessors();//获取处理器数量
                    int threadCount = cpuCount * 2 + 1;//设定最大线程数目
                    Log.i(TAG, "getThreadPool: cpuCount = " + cpuCount);
                    mThreadPool = new ThreadPool(threadCount,threadCount,0L);
                }
            }
        }
        return mThreadPool;
    }


    public static class ThreadPool {
        ThreadPoolExecutor poolExecutor = null;
        private int corePoolSize;
        private int maximumPoolSize;
        private long keepAliveTime;

        public ThreadPool(int corePoolSize,int maximumPoolSize,long keepAliveTime)
        {
            this.corePoolSize = corePoolSize;
            this.maximumPoolSize = maximumPoolSize;
            this.keepAliveTime = keepAliveTime;
        }


        // 线程池几个参数的理解:
        // 比如去火车站买票, 有10个售票窗口, 但只有5个窗口对外开放. 那么对外开放的5个窗口称为核心线程数,
        // 而最大线程数是10个窗口.
        // 如果5个窗口都被占用, 那么后来的人就必须在后面排队, 但后来售票厅人越来越多, 已经人满为患, 就类似于线程队列已满.
        // 这时候火车站站长下令, 把剩下的5个窗口也打开, 也就是目前已经有10个窗口同时运行. 后来又来了一批人,
        // 10个窗口也处理不过来了, 而且售票厅人已经满了, 这时候站长就下令封锁入口,不允许其他人再进来, 这就是线程异常处理策略.
        // 而线程存活时间指的是, 允许售票员休息的最长时间, 以此限制售票员偷懒的行为.
        public void executor (Runnable runnable)
        {
            if (runnable == null)
            {
                new RuntimeException("runnable 不能为空");
                return;
            }

            if (poolExecutor != null)
            {
                poolExecutor = new ThreadPoolExecutor(
                        corePoolSize,//核心线程数目
                        maximumPoolSize,//最大线程数目
                        keepAliveTime,//歇息时间
                        TimeUnit.MILLISECONDS,//时间单位
                        new LinkedBlockingDeque<Runnable>(),//线程队列
                        Executors.defaultThreadFactory(),//线程工厂
                        new ThreadPoolExecutor.AbortPolicy()//队列已满，且当前线程
                                                    // 数目超过最大线程数目的异常处理策略
                );
                poolExecutor.execute(runnable);
            }

        }

        //从线程队列中移除数据
        public void cancal(Runnable runnable)
        {
            if (poolExecutor != null)
            {
                poolExecutor.getQueue().remove(runnable);
            }
        }
    }
}
