package com.chuhezhe.tx.service;

/**
 * ClassName: CheckoutService
 * Package: com.chuhezhe.tx.service
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/29 22:38
 * @Version 1.0
 */
public interface CheckoutService {
    void checkout(Integer[] bookIds, Integer userId);
}
