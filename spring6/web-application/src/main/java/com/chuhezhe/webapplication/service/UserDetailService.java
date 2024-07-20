package com.chuhezhe.webapplication.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chuhezhe.webapplication.entity.UserDetail;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * ClassName: UserDetailService
 * Package: com.chuhezhe.webapplication.service
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/7/20 18:53
 * @Version 1.0
 */
public interface UserDetailService extends IService<UserDetail>, UserDetailsService {
}
