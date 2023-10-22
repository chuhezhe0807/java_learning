package com.chuhezhe.tx.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * ClassName: BookDaoImpl
 * Package: com.chuhezhe.tx.dao
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/26 23:54
 * @Version 1.0
 */
@Repository
public class BookDaoImpl implements BookDao{
    private final JdbcTemplate jdbcTemplate;

    public BookDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer getBookPriceByBookId(Integer bookId) {
        String sql = "select price from t_book where book_id=?";

        return jdbcTemplate.queryForObject(sql, Integer.class, bookId);
    }

    @Override
    public void updateStock(Integer bookId) {
        String sql = "update t_book set stock=stock-1 where book_id=?";
        jdbcTemplate.update(sql, bookId);
    }

    @Override
    public void updateUserBalance(Integer userId, Integer price) {
        String sql = "update t_user set balance=balance-? where user_id=?";
        jdbcTemplate.update(sql, price, userId);
    }
}
