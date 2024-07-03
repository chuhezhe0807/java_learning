package com.chuhezhe.webapplication.domain;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.io.Serializable;

/**
 * ClassName: UserDTO
 * Package: com.chuhezhe.webapplication.domain
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/7/3 23:23
 * @Version 1.0
 */
public class UserDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -2731598327208972274L;

    @NotNull
    private Long userId;

    @NotNull
    @Length(min = 3, max = 5)
    private String userName;

    @NotNull
    private String account;
}
