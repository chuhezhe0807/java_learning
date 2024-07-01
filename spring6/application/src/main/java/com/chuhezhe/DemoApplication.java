package com.chuhezhe;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * ClassName: DemoApplication
 * Package: com.chuhezhe
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/6/30 17:10
 * @Version 1.0
 */
public class DemoApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(DemoApplication.class)
                .web(WebApplicationType.NONE)
                .profiles("dev")
                .run(args);
    }
}
