package com.chuhezhe.jdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

/**
 * ClassName: JdbcTemplateTest
 * Package: com.chuhezhe.jdbc
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/25 23:26
 * @Version 1.0
 */
@SpringJUnitConfig(locations = "classpath:beans.xml")
public class JdbcTemplateTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 添加、修改、删除操作
    @Test
    public void updateTest() {
        // 1、添加操作
//        // 第一步 编写sql语句
//        String sql = "insert into t_emp values(null,?,?,?)";
//
//        // 第二步 调用jdbcTemplate的方法，传入相关参数
//        int rows = jdbcTemplate.update(sql, "Andy", 22, "女");
//        System.out.println(rows);

//        // 修改操作
//        String sql = "update t_emp set name=? where id=?";
//        int rows = jdbcTemplate.update(sql, "Andy_changed", 3);
//        System.out.println(rows);

        // 删除操作
        String sql = "delete from t_emp where id=?";
        int rows = jdbcTemplate.update(sql, 3);
        System.out.println(rows);
    }

    // 查询: 返回对象
    @Test
    public void testSelectObject() {
        // 写法一
//        String sql = "select * from t_emp where id=?";
//        Emp empResult = jdbcTemplate.queryForObject(sql, (res, rowNumber) -> {
//            Emp emp = new Emp();
//            emp.setAge(res.getInt("age"));
//            emp.setId(res.getInt("id"));
//            emp.setSex(res.getString("sex"));
//            emp.setName(res.getString("name"));
//
//            return emp;
//        }, 2);
//
//        System.out.println(empResult);

        // 写法二
        String sql = "select * from t_emp where id=?";
        Emp empResult = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Emp.class), 2);
        System.out.println(empResult);
    }

    // 查询：返回list集合
    @Test
    public void testSelectList() {
        String sql = "select * from t_emp";
        List<Emp> empResult = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Emp.class));
        System.out.println(empResult);
    }

    // 查询：返回单个值
    @Test
    public void testCount() {
        String sql = "select count(*) from t_emp";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println(count);
    }
}
