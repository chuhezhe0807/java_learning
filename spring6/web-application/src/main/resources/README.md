# Spring security 认证过程源码分析
1、在哪里做登录 filter<br />
* 启动项目的时候，会执行 WebConfigur@securityFilterChain ->> formLogin ->> new FormLoginConfigurer
```java
    public class FormLoginConfigurer {
        public FormLoginConfigurer() {
            super(new UsernamePasswordAuthenticationFilter(), null);
            usernameParameter("username");
            passwordParameter("password");
        }
    }
```

2、用户是存储在哪里的，是不是可以自己添加多个用户呢<br />
* SecurityProperties

3、退出接口是在哪里实现的<br />

```java
    // 如果我们需要查询数据库，则实现这个接口 替换默认的 InMemoryUserDetailsManager
    public interface UserDetailsService {
        UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    }
```