package com.chuhezhe.annotationaop;

import org.springframework.stereotype.Component;

/**
 * ClassName: CalculatorImpl
 * Package: com.chuhezhe.example
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/24 20:55
 * @Version 1.0
 */
@Component
public class CalculatorImpl implements Calculator {
    @Override
    public int add(int i, int j) {
        int result = i + j;

        System.out.println("方法内部 result = " + result);

        return result;
    }

    @Override
    public int sub(int i, int j) {
        int result = i - j;

        System.out.println("方法内部 result = " + result);

        return result;
    }

    @Override
    public int mul(int i, int j) {
        int result = i * j;

        System.out.println("方法内部 result = " + result);

        return result;
    }

    @Override
    public int div(int i, int j) {
        int result = i / j;

        System.out.println("方法内部 result = " + result);

        return result;
    }
}
