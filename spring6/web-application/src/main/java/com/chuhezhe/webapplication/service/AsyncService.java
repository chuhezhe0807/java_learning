package com.chuhezhe.webapplication.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * ClassName: AsyncService
 * Package: com.chuhezhe.webapplication.service
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/7/11 23:08
 * @Version 1.0
 */
@Service
public class AsyncService {
    private final Logger logger  = LoggerFactory.getLogger(AsyncService.class);

    @Async // 方法加上 @Async 注解就是异步方法了
    public void asyncMethod() {
        sleep(); // 由于是异步方法的原因，程序并不会被 sleep() 方法阻塞。异步方法内部会开一个线程来执行
        logger.info("异步方法内部线程名称: {}", Thread.currentThread().getName());
    }

    public void syncMethod() {
        sleep();
    }

    private void sleep() {
        try {
            TimeUnit.SECONDS.sleep(2);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
