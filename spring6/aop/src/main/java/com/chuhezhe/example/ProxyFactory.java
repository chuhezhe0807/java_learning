package com.chuhezhe.example;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * ClassName: ProxyFactory
 * Package: com.chuhezhe.example
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/24 21:10
 * @Version 1.0
 */
public class ProxyFactory {
    // 目标对象
    private Object target;
    public ProxyFactory(Object target) {
        this.target = target;
    }

    // 返回代理对象
    public Object getProxy() {
        /**
         * Proxy.newProxyInstance()方法 有三个参数
         * 第一个参数 ClassLoader: 加载动态生成代理类的加载器
         * 第二个参数 Class[] interfaces: 目标对象的所有接口的class类型数组
         * 第三个参数 InvocationHandler: 设置代理对象实现目标对象方法的过程
         */
        // 目标对象类的加载器
        ClassLoader classLoader = target.getClass().getClassLoader();
        // 目标对象所有接口的数组
        Class<?>[] interfaces = target.getClass().getInterfaces();
        // InvocationHandler
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
//            @SuppressWarnings("TryWithIdenticalCatches")
            public Object invoke(Object proxy, Method method, Object[] args) {

                // 调用目标方法之前
                System.out.println("[动态代理][日志] "+method.getName()+"，参数："+ Arrays.toString(args));

                // 调用目标对象的方法
                Object result = null;
                try {
                    result = method.invoke(target, args);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }

                // 调用目标方法之后
                System.out.println("[动态代理][日志] "+method.getName()+"，结果："+ result);

                return result;
            }
        };

        return Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
    }
}
