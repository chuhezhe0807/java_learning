package com.chuhezhe.tx.service;

import com.chuhezhe.tx.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * ClassName: BookServiceImpl
 * Package: com.chuhezhe.tx.service
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/26 23:54
 * @Version 1.0
 */

/**
 * @ Transactional 注解可以设置的参数
 * 1、readOnly = true 只读：设置只读，只能查询，不能修改添加删除
 *
 * 2、timeout = 3 超时：在设置超时时候之内没有完成，抛出异常回滚, timeout 单位为秒
 *
 * 3、noRollbackFor = ArithmeticException.class 算数异常不回滚  回滚策略：设置哪些异常不回滚
 *
 * 4、isolation = Isolation.DEFAULT 隔离级别：读问题, MySql默认级别为 REPEATABLE READ
 *
 *      @ Transactional(isolation = Isolation.DEFAULT)//使用数据库默认的隔离级别
 *      @ Transactional(isolation = Isolation.READ_UNCOMMITTED)//读未提交
 *      @ Transactional(isolation = Isolation.READ_COMMITTED)//读已提交
 *      @ Transactional(isolation = Isolation.REPEATABLE_READ)//可重复读
 *      @ Transactional(isolation = Isolation.SERIALIZABLE)//串行化
 *
 * 5、传播行为：事务方法之间调用事务如何使用
 *
 *  - REQUIRED：支持当前事务，如果不存在就新建一个(默认)**【没有就新建，有就加入】**
 * - SUPPORTS：支持当前事务，如果当前没有事务，就以非事务方式执行**【有就加入，没有就不管了】**
 * - MANDATORY：必须运行在一个事务中，如果当前没有事务正在发生，将抛出一个异常**【有就加入，没有就抛异常】**
 * - REQUIRES_NEW：开启一个新的事务，如果一个事务已经存在，则将这个存在的事务挂起**【不管有没有，直接开启一个新事务，开启的新事务和之前的事务不存在嵌套关系，之前事务被挂起】**
 * - NOT_SUPPORTED：以非事务方式运行，如果有事务存在，挂起当前事务**【不支持事务，存在就挂起】**
 * - NEVER：以非事务方式运行，如果有事务存在，抛出异常**【不支持事务，存在就抛异常】**
 * - NESTED：如果当前正有一个事务在进行中，则该方法应当运行在一个嵌套式事务中。被嵌套的事务可以独立于外层事务进行提交或回滚。如果外层事务不存在，行为就像REQUIRED一样。**【有事务的话，就在这个事务里再嵌套一个完全独立的事务，嵌套的事务可以独立的提交和回滚。没有事务就和REQUIRED一样。】**
 */


@Transactional(isolation = Isolation.DEFAULT) // 事务注解
@Service
public class BookServiceImpl implements BookService{
    private BookDao bookDao;

    /**
     * 构造器注入
     * public BookServiceImpl(BookDao bookDao) {
     *         this.bookDao = bookDao;
     *     }kDao
     */

    // set注入
    @Autowired
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void buyBook(Integer bookId, Integer userId) {
        // TODO 模拟超时效果
//        try {
//            TimeUnit.SECONDS.sleep(5);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        // 根据图书id查询图书价格
        Integer price = bookDao.getBookPriceByBookId(bookId);

        // 更新图书表库存量 -1
        bookDao.updateStock(bookId);

        // 更新用户表用户余额 -图书价格
        bookDao.updateUserBalance(userId, price);
    }
}
