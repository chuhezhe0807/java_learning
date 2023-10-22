package com.chuhezhe.tx.controller;

import com.chuhezhe.tx.service.BookService;
import com.chuhezhe.tx.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * ClassName: BookController
 * Package: com.chuhezhe.tx.controller
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/26 23:54
 * @Version 1.0
 */
@Controller("tempBookController")
public class BookController {
//    @Autowired
//    private BookService bookService;
//
//    // 买书的方法
//    public void buyBook(Integer bookId, Integer userId) {
//        bookService.buyBook(bookId, userId);
//    }

    private CheckoutService checkoutService;

    @Autowired
    public void setCheckoutService(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    // 买多本书的方法
    public void checkout(Integer[] bookIds, Integer userId) {
        checkoutService.checkout(bookIds, userId);
    }
}
