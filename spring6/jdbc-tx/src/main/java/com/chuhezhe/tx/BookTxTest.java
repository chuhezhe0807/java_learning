package com.chuhezhe.tx;

import com.chuhezhe.tx.controller.BookController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * ClassName: BookTxTest
 * Package: com.chuhezhe.tx
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/27 23:54
 * @Version 1.0
 */
@SpringJUnitConfig(locations = "classpath:beans.xml")
public class BookTxTest {
    @Autowired
    private BookController bookController;

    @Test
    public void testBuyBook() {
//        bookController.buyBook(1, 1);
        bookController.checkout(new Integer[]{1, 2}, 1);
    }
}
