package com.formaciondbi.spring.app.apigateway.springbootservicioapigateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
//import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
@Slf4j
public class PreFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("====================");
        log.info("||   PRE FILTER   ||");
        log.info("====================");
        exchange.getRequest().mutate().headers(head -> head.add("token", "112233"));
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            log.info("====================");
            log.info("||   POST FILTER  ||");
            log.info("====================");
            Optional.ofNullable(exchange.getRequest().getHeaders().getFirst("token")).ifPresent(token -> {
                exchange.getResponse().getHeaders().add("token", token);
            });
            exchange.getResponse().getCookies().add("color", ResponseCookie.from("color", "rojo").build());
//            exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
        }));
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
