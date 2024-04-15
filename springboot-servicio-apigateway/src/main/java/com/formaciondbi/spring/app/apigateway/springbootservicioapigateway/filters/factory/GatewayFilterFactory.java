package com.formaciondbi.spring.app.apigateway.springbootservicioapigateway.filters.factory;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
@Slf4j
public class GatewayFilterFactory extends AbstractGatewayFilterFactory<GatewayFilterFactory.Configuracion> {
    public GatewayFilterFactory() {
        super(Configuracion.class);
    }

    @Override
    public GatewayFilter apply(Configuracion config) {
        return (exchange, chain) -> {
            log.info("Ejecutando pre filter factory" + config.mensaje);
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                Optional.ofNullable(config.cookieValor).ifPresent(cookie -> {
                    exchange.getResponse().addCookie(ResponseCookie.from("cookie", cookie).build());
                });
                log.info("Ejecutando post filter factory");
            }));
        };
    }

    @Data
    public static class Configuracion {
        private String mensaje;
        private String cookieValor;
        private String cookieNombre;
    }
}
