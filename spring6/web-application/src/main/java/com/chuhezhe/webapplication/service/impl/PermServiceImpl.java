package com.chuhezhe.webapplication.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chuhezhe.webapplication.entity.Perm;
import com.chuhezhe.webapplication.mapper.PermMapper;
import com.chuhezhe.webapplication.service.PermService;
import org.springframework.stereotype.Service;

/**
 * ClassName: PermServiceImpl
 * Package: com.chuhezhe.webapplication.service.impl
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/7/20 21:01
 * @Version 1.0
 */
@Service
public class PermServiceImpl extends ServiceImpl<PermMapper, Perm> implements PermService {
}
