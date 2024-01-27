package thread.lockupgrade;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * ClassName: LockUpgrade
 * Package: thread.cas.lockupgrade
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/1/27 20:07
 * @Version 1.0
 */
public class LockUpgrade {
    public static void main(String[] args) throws Exception {
        User userTemp = new User();
        // 因为jvm启动后默认延时4s后开启偏向锁，所以 userTemp 的状态还是 001
        System.out.println("无状态(001): " + ClassLayout.parseInstance(userTemp).toPrintable());

        // 从JDK15开始 偏向锁就默认关闭了，需要手动开启
        // jvm默认延时4s自动开启偏向锁，可通过 -XX:BiasedLockingStartupDelay=0 取消延时
        // 如果不需要偏向锁，可以通过-XX:+UseBiasedLocking 来设置
        TimeUnit.SECONDS.sleep(5);
        User user = new User();
        System.out.println("启用偏向锁(101): " + ClassLayout.parseInstance(user).toPrintable());

        for(int i = 0; i < 2; i++) {
            synchronized (user) { // 局部变量不会有线程安全问题，这里使用 synchronized 仅仅是查看对象头信息
                System.out.println("偏向锁(101)(带线程id)：" + ClassLayout.parseInstance(user).toPrintable());
            }

            // 虽然是释放偏向锁，但是加锁对象的对象头markword中存储的线程id不会变，只是撤销偏向锁的次数会增长
            System.out.println("偏向锁释放(001)(带线程id)：" + ClassLayout.parseInstance(user).toPrintable());
        }

        new Thread(() -> {
            synchronized (user) {
                // 已经加了偏向锁的对象，如果有其它线程争抢，则立即升级为轻量级锁
                System.out.println("轻量级锁(00): " + ClassLayout.parseInstance(user).toPrintable());

                try {
                    System.out.println("sleep 3 seconds.");
                    TimeUnit.SECONDS.sleep(3);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("轻量 --> 重量(10): " + ClassLayout.parseInstance(user).toPrintable());
            }
        }).start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            synchronized (user) {
                // 其他线程持有该锁（此刻持有的是轻量级锁）。此时，Java 虚拟机会将这把锁膨胀为重量级锁，并且阻塞当前线程。
                System.out.println("重量级锁(10): " + ClassLayout.parseInstance(user).toPrintable());
            }
        }).start();
    }
}

class User {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
