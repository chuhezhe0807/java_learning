package com.chuhezhe;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * ClassName: ResourceI18n
 * Package: com.chuhezhe
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/10/4 20:51
 * @Version 1.0
 */
public class ResourceI18n {
    public static void main(String[] args) {
        ResourceBundle bundle1 = ResourceBundle.getBundle("messages", new Locale("zh", "CN"));
        System.out.println(bundle1.getString("test"));

        ResourceBundle bundle2 = ResourceBundle.getBundle("messages", new Locale("en", "GB"));
        System.out.println(bundle2.getString("test"));
    }

    @Test
    public void test01() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");

        Object[] objs = new Object[]{"小张", new Date().toString()};
        System.out.println(context.getMessage("welcome", objs, Locale.CHINA));
        System.out.println(context.getMessage("welcome", objs, Locale.ENGLISH));
    }
}
