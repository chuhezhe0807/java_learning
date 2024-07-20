package com.chuhezhe.webapplication.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chuhezhe.webapplication.entity.Perm;
import com.chuhezhe.webapplication.entity.UserDetail;
import com.chuhezhe.webapplication.mapper.PermMapper;
import com.chuhezhe.webapplication.mapper.UserDetailMapper;
import com.chuhezhe.webapplication.service.UserDetailService;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: UserDetailServiceImpl
 * Package: com.chuhezhe.webapplication.service.impl
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/7/20 18:54
 * @Version 1.0
 */
@Service
public class UserDetailServiceImpl extends ServiceImpl<UserDetailMapper, UserDetail> implements UserDetailService {

    private final UserDetailMapper userDetailMapper;

    private final PermMapper permMapper;

    public UserDetailServiceImpl(UserDetailMapper userDetailMapper, PermMapper permMapper) {
        this.userDetailMapper = userDetailMapper;
        this.permMapper = permMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<UserDetail> userDetailQueryWrapper = new QueryWrapper<>();
        userDetailQueryWrapper.eq("username", username);
        UserDetail userDetail = userDetailMapper.selectOne(userDetailQueryWrapper);

        if(userDetail == null) {
            throw new UsernameNotFoundException("用户未找到....");
        }

        QueryWrapper<Perm> permQueryWrapper = new QueryWrapper<>();
        permQueryWrapper.eq("user_id", userDetail.getId());
        List<Perm> perms = permMapper.selectList(permQueryWrapper);

        // 权限标识
        List<String> permTags = perms.stream().map(Perm::getTag).toList();
        // 设置权限标识
        userDetail.setAuthorities(AuthorityUtils.createAuthorityList(permTags));

        return userDetail;
    }
}
