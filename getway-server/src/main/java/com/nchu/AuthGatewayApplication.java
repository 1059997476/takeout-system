package com.nchu;

import com.nchu.filter.JwtTokenFilter;
import com.nchu.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

@SpringBootApplication
@EnableDiscoveryClient
public class AuthGatewayApplication {


    @Value("${jwt.secret:heheehklfdsjfkldsjfkslfjkdslfjdsf}")
    private String secret;
    @Value("${jwt.expire:600000}")
    private Long expire;

    /**
     * 初始化jwttoken的盐和超时时间
     */
    @Bean
    @Lazy(value = false)
    public void initJWT() {
        JwtUtil.secret = secret;
        JwtUtil.expire = expire;
    }

    public static void main(String[] args) {
        SpringApplication.run(AuthGatewayApplication.class, args);
    }

    /**
     * 注入用于验证是否登录的filter
     *
     * @return
     */
    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter();
    }

    /**
     * 配置路由规则
     */
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                //路由登录或者注册请求，统一放行，放行时通过stripPrefix去除前缀auth
                .route( r -> r.path("/auth/**").filters(f -> f.stripPrefix(1))
                        .uri("lb://user-server"))
                .route(r -> r.path("/merchant/**")
                        .filters(f -> f.stripPrefix(1).filter(jwtTokenFilter()))
                        .uri("lb://merchant-server"))
                .route(r-> r.path("/consumer/**")
                        .filters(f -> f.stripPrefix(1).filter(jwtTokenFilter()))
                        .uri("lb://consumer-server"))
                .build();
    }
}
