package com.chuhezhe.example;

/**
 * ClassName: CalculatorTest
 * Package: com.chuhezhe.example
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/24 21:30
 * @Version 1.0
 */
public class CalculatorTest {
    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory(new CalculatorImpl());
        Calculator proxy = (Calculator) proxyFactory.getProxy();
//        proxy.add(1, 2);
        proxy.mul(1, 2);
    }
}
