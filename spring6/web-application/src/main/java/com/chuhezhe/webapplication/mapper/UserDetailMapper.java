package com.chuhezhe.webapplication.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chuhezhe.webapplication.entity.UserDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: UserDetailMapper
 * Package: com.chuhezhe.webapplication.mapper
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/7/20 18:51
 * @Version 1.0
 */
@Mapper
public interface UserDetailMapper extends BaseMapper<UserDetail> {
}
