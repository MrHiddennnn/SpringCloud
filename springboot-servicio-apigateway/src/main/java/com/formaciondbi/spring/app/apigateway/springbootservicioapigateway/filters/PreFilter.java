package com.formaciondbi.spring.app.apigateway.springbootservicioapigateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class PreFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("====================");
        log.info("    PRE FILTER      ");
        log.info("====================");
        return chain.filter(exchange).then(Mono.fromRunnable(() ->{
            log.info("====================");
            log.info("     POST FILTER    ");
            log.info("====================");
            exchange.getResponse().getCookies().add("color", ResponseCookie.from("color", "rojo").build());
            exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
        }));
    }
}
