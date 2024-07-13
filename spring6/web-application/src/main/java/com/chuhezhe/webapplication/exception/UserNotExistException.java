package com.chuhezhe.webapplication.exception;

import java.io.Serial;

/**
 * ClassName: UserNotExistException
 * Package: com.chuhezhe.webapplication.exception
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/7/13 22:18
 * @Version 1.0
 */
public class UserNotExistException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = -1574016826948451793L;

    private String id;

    public UserNotExistException(String id) {
        super("user not exist.");
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
