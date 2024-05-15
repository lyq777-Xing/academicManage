package com.manage.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.annotation.web.configurers.RememberMeConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @author lyq
 * @time 2024/1/26 13:34
 */

@Configuration
@EnableWebSecurity
//securedEnabled开启注解@Secured功能，用于基于角色的访问控制
// prePostEnabled开启注解@PreAuthorize和@PostAuthorize功能 用于基于表达式的访问控制
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class MySecurityConfiguration extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

//    DefaultSecurityFilterChain 用于提供一个默认的安全过滤器链，这个类实现了SecurityFilterChain接口，该接口只有一个方法getFilters()，用于获取安全过滤器列表

//    HttpSecurity 用于Security框架中的配置处理 提供一些方法来配置安全过滤器链，这个类实现了SecurityBuilder接口，该接口只有一个方法build()，用于创建一个安全过滤器链

//    继承适配器后需要重写方法

    /**
     * 注入自定义的密码加密器 创建一个passwordEncoder对象
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
//        构造方法可以传递整型参数4-31，表示加密强度，值越大加密强度越高，默认值是10
        return new BCryptPasswordEncoder();
    }

    /**
     * 注入自定义的持久化token仓库
     * 创建一个Bean对象
     * 这个Bean对象，使用的是SpringSecurity提供的JdbcTokenRepositoryImpl类
     * JdbcTokenRepositoryImpl 基于DataSource数据源连接池，访问指定的数据库
     * 把认证成功的用户信息，需要Remember Me的，保存到数据库中
     * @param dataSource 数据源对象
     * @return PersistentTokenRepository
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository(DataSource dataSource){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource); //设置数据源
//        初始化参数，仅第一次自动启动当前项目时设置为Ture，以后启动项目时设置为False，否则会报错
//        如果设置为True 启动时会自动链接数据库，创建表格，表格就是保存rememberMe数据的
        jdbcTokenRepository.setCreateTableOnStartup(false); //设置启动时自动创建表
        return jdbcTokenRepository;
    }

    /**
     * 注入自定义的安全过滤器链
     * @param security
     * @return
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
//        securityFilterChain对象 不需要手工new 可以通过SecurityConfigurerAdapter的getBuilder()方法获取

//        创建匿名类对象，实现认证相关配置
        Customizer<FormLoginConfigurer<HttpSecurity>> customizer = new Customizer<FormLoginConfigurer<HttpSecurity>>() {
            @Override
            public void customize(FormLoginConfigurer<HttpSecurity> configurer) {
//                具体的认证配置
                configurer.loginPage("/login") //设置登录页面的请求地址 必须是get请求
                        .loginProcessingUrl("/login") //设置登录页面的表单提交地址 必须是post请求
                        .usernameParameter("username") //设置登录页面的用户名参数名
                        .passwordParameter("password") //设置登录页面的密码参数名
//                        .defaultSuccessUrl("/index") //设置登录成功后跳转的页面
//                        其实可以不用注释掉 因为会覆盖
                        .successHandler(myAuthenticationSuccessHandler) //设置登录成功后的处理器
//                        .failureUrl("/loginFail")
                        .failureHandler(myAuthenticationFailureHandler) //设置登录失败后的处理器
                ;
            }
        };
//        设置认证配置
        security.formLogin(customizer);

//        配置RememberMe相关信息
        Customizer<RememberMeConfigurer<HttpSecurity>> rememberMe = new Customizer<RememberMeConfigurer<HttpSecurity>>() {
            @Override
            public void customize(RememberMeConfigurer<HttpSecurity> configurer) {
                configurer
                        .tokenRepository(persistentTokenRepository(dataSource)) //设置持久化token仓库 设置保存记住我数据的具体对象
                        .rememberMeParameter("rememberMe") //设置登录页面的记住我参数名
                        .rememberMeCookieName("rememberMe") //设置浏览器中的cookie名
                        .rememberMeCookieDomain("localhost") //设置浏览器中的cookie的作用域
                        .tokenValiditySeconds(60*60*24*7) //设置token有效期 单位秒 默认1800秒
                        .userDetailsService(userDetailsService) //设置自定义的UserDetailsService
                ;
            }
        };
        security.rememberMe(rememberMe);

//        退出登录配置 建议只配置退出登录的请求地址和退出登录成功后的跳转页面
        Customizer<LogoutConfigurer<HttpSecurity>> logout = new Customizer<LogoutConfigurer<HttpSecurity>>() {
            @Override
            public void customize(LogoutConfigurer<HttpSecurity> configurer) {
                configurer.logoutUrl("/logout") //设置退出登录的请求地址
                        .logoutSuccessUrl("/login") //设置退出登录后跳转的页面
                        .logoutSuccessHandler(new MyLogoutSuccessHandler("/login",true)) //设置退出登录成功后的处理器
                        .addLogoutHandler(new MyLogoutHandler()) //设置退出登录时的处理器
//                        .invalidateHttpSession(true) //设置是否使session失效
//                        .clearAuthentication(true) //设置是否清除认证信息
                ;
            }
        };
        security.logout(logout);

//        授权配置
        /*
        requestMatchers方法有多个重载方法
            requestMatchers(RequestMatcher... requestMatchers) 代表指定的请求
            requestMatchers(Class<? extends RequestMatcher>... requestMatcherClasses) 代表指定的请求
            requestMatchers(String... antPatterns) 代表指定的请求
            requestMatchers(HttpMethod method, String... antPatterns) 代表指定的请求
            requestMatchers(HttpMethod method, RequestMatcher... requestMatchers) 代表指定的请求
            requestMatchers(RequestMatcher requestMatcher) 代表指定的请求
            requestMatchers() 代表所有请求 相当于requestMatchers("/**")
          anyRequest 代表所有请求 相当于requestMatchers("/**")
          注意 此方法必须在最后调用 放在前面会影响权限校验
         */
        security.authorizeRequests()
                .requestMatchers("/login","/loginFail","/v3/api-docs","/swagger-ui/*","/swagger-ui.html").permitAll() //登录页面不需要认证
                .requestMatchers("/logout").authenticated() //退出登录页面需要认证
//                .requestMatchers("/lyq/test").hasRole("SuperAdmin") //指定的请求需要指定的角色
//                .requestMatchers("/lyq/test").hasAuthority("user:add") //指定的请求需要指定的权限
                .anyRequest().authenticated() //其他页面需要认证
//                .requestMatchers("/lyq/test").access("@IMyPermissionChecker.check(request,authentication)") //指定的请求需要指定的权限

        ;

//        检查是否登录
//        security.addFilter(new JwtAuthorizationFilter(authenticationManager()));


//        异常处理配置
        Customizer<ExceptionHandlingConfigurer<HttpSecurity>> handlingConfigurerCustomizer = new Customizer<ExceptionHandlingConfigurer<HttpSecurity>>(){
            @Override
            public void customize(ExceptionHandlingConfigurer<HttpSecurity> configurer) {
//                设置自定义的访问无权限
                configurer.accessDeniedHandler(new MyAccessDeniedHandler());
            }
        };
        security.exceptionHandling(handlingConfigurerCustomizer);

        security.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //设置session策略

        security.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint); //设置JWT认证失败处理器

        security.addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        security.cors().and().csrf().disable();

        return security.build();
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter();
    }
}
