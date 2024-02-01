package thread.threadsafe.lock;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 为什么需要读锁
 *  读锁是为了防止读到写的中间值
 *
 * ClassName: WhyReadLock
 * Package: thread.threadsafe.lock
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/1 22:46
 * @Version 1.0
 */
public class WhyReadLock {
    public static void main(String[] args) {
        Demo demo = new Demo();
//        Demo demo = new ReadWriteLockDemo();
        long start = System.currentTimeMillis();
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10000; i++){
            //读线程
            threads.add(new Thread(() -> {
                long value = demo.get();

                if (value > Demo.MAX_VALUE){
                    CommonMethod.log("读到错误的数据了");
                    System.exit(-1);
                }

                CommonMethod.log("get " + value);
            }, "thread-get-" + i));

            //写线程
            threads.add(new Thread(demo::add,"thread-add-" + i));
        }

        CommonMethod.start(threads);
        CommonMethod.join(threads);
        CommonMethod.log("" + demo.get());
        CommonMethod.log("耗时：" + (System.currentTimeMillis() - start));
    }
}

class Demo {
    public static final int MAX_VALUE = 2;
    //值，且值不大于 MAX_VALUE
    long value;
    //返回值
    long get(){
        CommonMethod.sleep(10);

        return value;
    }
    //值加1，模拟非原子的写操作
    void add(){
        //加法结果类似写操作的中间状态
        value++;
        CommonMethod.sleep(10);
        //重置为0
        if (value > MAX_VALUE){
            value = 0;
        }
    }
}

class CommonMethod {
    private static final AtomicLong UNIQ_ID = new AtomicLong();

    public static void sleep(long time){
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void log(String log){
        System.out.println(new SimpleDateFormat("yyyyMMdd HH:mm:ss.SSS").format(new Date())+ "  "+ Thread.currentThread().getName() + "  " + log);
    }

    public static void start(Collection<Thread> threads){
        threads.forEach(Thread::start);
    }

    public static void join(Thread thread){
        try {
            thread.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void join(Collection<Thread> threads){
        threads.forEach(CommonMethod::join);
    }


    public static int randomInt(int high){
        return new Random().nextInt(high);
    }

    public static Long getUniqId(){
        return UNIQ_ID.getAndIncrement();
    }
}


class ReadWriteLockDemo extends Demo {
    private final ReadWriteLock rw = new ReentrantReadWriteLock();
    private final Lock rl = rw.readLock();
    private final Lock wl = rw.writeLock();
    @Override
    public long get(){
        rl.lock();

        try{
            return super.get();
        }
        finally {
            rl.unlock();
        }
    }
    @Override
    public void add(){
        wl.lock();

        try{
            super.add();
        }
        finally{
            wl.unlock();
        }
    }
}