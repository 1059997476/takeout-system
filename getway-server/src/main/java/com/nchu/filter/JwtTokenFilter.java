package com.nchu.filter;


import com.alibaba.fastjson.JSON;
import com.nchu.bean.Code;
import com.nchu.bean.Result;
import com.nchu.utils.JwtException;
import com.nchu.utils.JwtUtil;
import com.nchu.utils.UserInfo;
import com.nimbusds.jose.JOSEException;
import feign.Response;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;

/**
 * JwtTokenFilter用于拦截登录后的一些请求，判断请求头中是否有合法的access-token，如果有
 * 就放行，并且在请求头中带着username和uid等用户信息，并且在响应回来时，再次拦截，在响应头中
 * 更新新的access-token
 */
public class JwtTokenFilter implements GatewayFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //1.获取token
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        try {
            UserInfo userInfo = JwtUtil.validToken(token);
            ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate()
                    .header("username", userInfo.getUsername())
                    .header("type", String.valueOf(userInfo.getType())).build();
            String newToken = JwtUtil.genToken(userInfo.getType(),userInfo.getUsername());
            ServerHttpResponse serverHttpResponse = exchange.getResponse();
            serverHttpResponse.getHeaders().add("Authorization",newToken);
            ServerWebExchange serverWebExchange = exchange.mutate().request(serverHttpRequest).response(serverHttpResponse).build();
            return chain.filter(serverWebExchange);
        } catch (ParseException | JOSEException | JwtException e) {
            ServerHttpResponse response = exchange.getResponse();
            Result result = new Result(e.getMessage(), Code.forbidden.getCode());
            byte[] data = JSON.toJSONString(result).getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = response.bufferFactory().wrap(data);
            response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            return response.writeWith(Mono.just(buffer));
        }
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}

