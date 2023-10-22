package com.chuhezhe.annotationaop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * ClassName: LogAspect
 * Package: com.chuhezhe.annotationaop
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/24 23:16
 * @Version 1.0
 */
@Aspect // 表示这个类是一个切面类
@Component // 保证此类能够放入IOC容器
public class LogAspect {
    // 设置切入点和通知类型
    // 切入点表达式：execution(访问修饰符 增强方法返回类型 增强方法所在类的全类名.方法名(方法参数))
    // 通知类型
    // 前置 @Before(value = "切入点表达式配置切入点")             在被代理的目标方法前执行
    @Before(value = "execution(public int com.chuhezhe.annotationaop.CalculatorImpl.*(..))")
    public void beforeMethod() {
        System.out.println("Logger - 前置通知");
    }

    // 返回 @AfterReturning       在被代理的目标方法成功结束后执行（寿终正寝）
    @AfterReturning(value = "execution(public int com.chuhezhe.annotationaop.CalculatorImpl.*(..)))", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String name = joinPoint.getSignature().getName();
        System.out.println("Logger - 返回通知, 方法名称: " + name + "返回值: " + result);
    }

    // 异常 @AfterThrowing        在被代理的目标方法异常结束后执行（死于非命）
    // 后置 @After                在被代理的目标方法最终结束后执行（盖棺定论）
    @After(value = "pointCut()")
    public void afterMethod(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        System.out.println("Logger - 后置通知, 方法名称" + name);
    }

    // 环绕 @Around               使用try...catch...finally结构围绕**整个**被代理的目标方法，包括上面四种通知对应的所有位置
    @Around(value = "execution(public int com.chuhezhe.annotationaop.CalculatorImpl.*(..)))")
    public Object around(ProceedingJoinPoint joinPoint) {
//        String methodName = joinPoint.getSignature().getName();
//        String argsString = Arrays.toString(joinPoint.getArgs());

        Object result = null;

        try {
            System.out.println("环绕通知===目标方法之前执行");

            // 调用目标方法
            result = joinPoint.proceed();

            System.out.println("环绕通知===目标方法之后执行");
        } catch(Throwable throwable) {
            System.out.println("环绕通知===目标方法出现异常执行");
        } finally {
            System.out.println("环绕通知===目标方法执行完毕执行");
        }

        return result;
    }

    // 重用切入点表达式
    @Pointcut(value = "execution(public int com.chuhezhe.annotationaop.CalculatorImpl.*(..)))")
    public void pointCut() {}
}
