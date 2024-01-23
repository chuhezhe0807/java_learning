package thread.guradianthread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

/**
 * 使用定时器实现日志备份
 *
 * ClassName: Timer
 * Package: thread.guradianthread
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/1/22 22:01
 * @Version 1.0
 */
public class TimerTask {
    public static void main(String[] args) {
        Timer timer = new Timer();
        String firstTimeStr = "2021-05-09 17:56:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");

        try {
            Date firstTime = sdf.parse(firstTimeStr);
            timer.schedule(new java.util.TimerTask() {
                @Override
                public void run() {
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
                    String time = sdf.format(date);

                    System.out.println(time + ": 日志备份一次！");
                }
            }, firstTime, 1_000);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
