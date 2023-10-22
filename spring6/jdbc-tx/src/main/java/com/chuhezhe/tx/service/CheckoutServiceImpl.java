package com.chuhezhe.tx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * ClassName: CheckoutServiceImpl
 * Package: com.chuhezhe.tx.service
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/29 22:40
 * @Version 1.0
 */
@Service
public class CheckoutServiceImpl implements CheckoutService{
    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    // 一个用户一次买多本书
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void checkout(Integer[] bookIds, Integer userId) {
        for(Integer bookId : bookIds) {
            bookService.buyBook(bookId, userId);
        }
    }
}
