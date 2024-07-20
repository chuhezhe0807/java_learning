package com.chuhezhe.webapplication.config;

import com.chuhezhe.webapplication.converter.PropertiesHttpMessageConverter;
import com.chuhezhe.webapplication.filter.RequestTimeConsumptionFilter;
import com.chuhezhe.webapplication.interceptor.RequestTimeConsumptionInterceptor;
import com.chuhezhe.webapplication.resolver.PropertiesHandlerMethodArgumentResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ClassName: WebConfigurer
 * Package: com.chuhezhe.webapplication.config
 * Description:
 *
 *     " @Autowired按byType自动注入，而@Resource默认按 byName自动注入
 *
 * @Author Chuhezhe
 * @Create 2024/7/6 16:43
 * @Version 1.0
 */
@Configuration
@EnableAsync // 开启异步支持
@EnableWebSecurity // 开启 SpringSecurity，之后会注册大量的过滤器 servlet filter
public class WebConfigurer implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(WebConfigurer.class);

    /**
     * 加密编码，开发环境一般明文加密，生产环境一般密文加密
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        // 模拟生成用户，实际开发时会从数据库读取数据
//        UserDetails user1 = User.withUsername("admin").password("123").roles("admin", "user").build();
//        UserDetails user2 = User.withUsername("user").password("123").roles("user").build();

        UserDetails user1 = User.withUsername("admin").password("123").authorities("admin:api", "user:api").build();
        UserDetails user2 = User.withUsername("user").password("123").authorities("user:api").build();

        return new InMemoryUserDetailsManager(user1, user2);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // authorizeHttpRequests 针对http请求进行授权配置
        // login 登陆接口需要匿名访问
        // permitAll 具有所有权限，也就是可以匿名访问
        // anyRequest 任何请求
        // authenticated 认证(登录)
        http.authorizeHttpRequests(authorizeHttpRequests ->
                authorizeHttpRequests
                        // 角色
//                        .requestMatchers("/auth/admin/api").hasRole("admin") // 只有 admin 角色才可以访问
//                        .requestMatchers("/auth/user/api").hasAnyRole("admin", "user") // 含有 user 角色就可以访问

                        // 权限
                        .requestMatchers("/auth/admin/api").hasAuthority("admin:api") // 必须有 admin:api权限 才可以访问到
                        .requestMatchers("/auth/user/api").hasAnyAuthority("admin:api", "user:api") // 含有 "admin:api", "user:api" 其中一个权限就可以

                        // 匹配模式
                        // ? 匹配任意单个字符
                        // * 0到任意数量的字符
                        // ** 0到任意个目录
                        .requestMatchers("/auth/user/api/?").hasAnyAuthority("admin:api", "user:api") // 含有 "admin:api", "user:api" 其中一个权限就可以

                        .requestMatchers("/auth/app/api").permitAll() // 任何角色都可以访问(匿名可以访问)
                        .requestMatchers("/login").permitAll()
                        .anyRequest().authenticated()
        );

        // loginPage 登录页面
        // loginProcessingUrl 登录接口 过滤器
        // defaultSuccessfulUrl 登录成功之后访问的页面
        // successHandler 登录成功的处理器
        // failureHandler 登录失败的处理器
        http.formLogin(formLogin ->
                formLogin
                        .loginPage("/login").permitAll()
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/index")
        );

        // TODO 应该捕获异常，根据异常类型，判断重定向到哪一个页面
        http.exceptionHandling(e -> e.accessDeniedPage("/auth/noAuth"));

        // 关闭跨域漏洞防御
        http.csrf(Customizer.withDefaults());

        // 跨域拦截关闭
        http.cors(Customizer.withDefaults());

        // 退出
        http.logout(logout -> logout.invalidateHttpSession(true));

        return http.build();
    }

    // 添加检测请求耗时的自定义过滤器，多个过滤器可以向Spring容器中添加多个 FilterRegistrationBean
    @Bean
    public FilterRegistrationBean<RequestTimeConsumptionFilter> timeFilter() {
        FilterRegistrationBean<RequestTimeConsumptionFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();

        Set<String> urlSet = new HashSet<>();
        urlSet.add("/*");

        filterFilterRegistrationBean.setFilter(new RequestTimeConsumptionFilter()); // 检测请求耗时的过滤器
        filterFilterRegistrationBean.setUrlPatterns(urlSet);
        filterFilterRegistrationBean.addInitParameter(RequestTimeConsumptionFilter.INIT_PARAMETER_NAME, "/favicon.ico,/excludeURI");
        filterFilterRegistrationBean.setName("RequestTimeConsumption");
        filterFilterRegistrationBean.setOrder(1); // 数字越小，执行越早

        return filterFilterRegistrationBean;
    }

    // 添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestTimeConsumptionInterceptor());
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 将 PropertiesHttpMessageConverter 添加到消息转换器集合中，并且指定添加到第一个位置，
        // 防止被前面的 MappingJackson2HttpMessageConverter 提前处理 JSON 了。
        converters.add(0, new PropertiesHttpMessageConverter());
    }

    // 添加自定义的请求方法参数解析器
    // Properties 本质也是一个Map，而spring内置的 MapMethodProcessor 就是处理Map类型参数的，所以需要把 PropertiesHandlerMethodArgumentResolver 添加到第一个位置
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(0, new PropertiesHandlerMethodArgumentResolver());
    }

    // 处理跨域
    // 所有请求都支持跨域访问，且不限定域，但是支持GET请求
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET");
    }
}
