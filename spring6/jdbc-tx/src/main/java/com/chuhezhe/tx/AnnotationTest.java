package com.chuhezhe.tx;

import com.chuhezhe.tx.config.SpringConfig;
import com.chuhezhe.tx.controller.BookController;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * ClassName: AnnotationTest
 * Package: com.chuhezhe.tx
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/29 23:12
 * @Version 1.0
 */
public class AnnotationTest {
    @Test
    public void testAnnotation() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        BookController bookController = context.getBean("tempBookController", BookController.class);
        bookController.checkout(new Integer[]{1, 2}, 1);
    }
}
