package com.chuhezhe.webapplication.controller;

import com.chuhezhe.webapplication.service.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * ClassName: AsyncController
 * Package: com.chuhezhe.webapplication.controller
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/7/11 23:12
 * @Version 1.0
 */
@RestController
@RequestMapping("/async")
public class AsyncController {
    private final Logger logger = LoggerFactory.getLogger(AsyncController.class);
    private final AsyncService asyncService;

    public AsyncController(AsyncService asyncService) {
        this.asyncService = asyncService;
    }

    @GetMapping("/testAsync")
    public void testAsync() throws ExecutionException, InterruptedException, TimeoutException {
        long start = System.currentTimeMillis();
        logger.info("异步方法开始");

        CompletableFuture<String> completableFuture = asyncService.asyncMethod();
        String result = completableFuture.get(60, TimeUnit.SECONDS); // get 为阻塞方法，可以设置超时时间，异步方法没有在设定时间内返回值的的话，就直接抛出超时异常
        logger.info("异步方法返回值: {}", result);

        logger.info("异步方法结束");
        long end = System.currentTimeMillis();
        logger.info("总耗时: {}", end - start);
    }

    @GetMapping("/testSync")
    public void testSync() {
        long start = System.currentTimeMillis();
        logger.info("同步方法开始");

        asyncService.syncMethod();

        logger.info("同步方法结束");
        long end = System.currentTimeMillis();
        logger.info("总耗时: {}", end - start);
    }
}
