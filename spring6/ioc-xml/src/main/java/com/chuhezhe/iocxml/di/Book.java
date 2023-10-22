package com.chuhezhe.iocxml.di;

import lombok.Data;

import java.util.List;

/**
 * ClassName: Book
 * Package: com.chuhezhe.iocxml.di
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/13 21:08
 * @Version 1.0
 */
@Data
public class Book {
    private String name;
    private String author;
    private List<Lesson> lessonList;

    public Book() {

    }

    public Book(String name, String author) {
        System.out.println("Constructor args....");
        this.name = name;
        this.author = author;
    }

    public void run() {
        System.out.println("list" + lessonList);
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
