package com.formaciondbi.spring.app.apigateway.springbootservicioapigateway.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
public class PostTiempoTranscurridoFilter extends ZuulFilter {
    //    private static Logger log = LoggerFactory.getLogger(PreTiempoTranscurridoFilter.class);
    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info("Entrando a post filter");

        Long tiempoInicio = (Long) request.getAttribute("tiempoInicio");
        Long tiempoFinal = System.currentTimeMillis();
        Long tiempoTranscurrido = tiempoFinal - tiempoInicio;

        log.info(String.format("Tiempo transcurrido en segundos %s seg.", tiempoInicio.doubleValue() / 1000.00));
        log.info(String.format("Tiempo transcurrido en mileseg %s ms.", tiempoTranscurrido));
        System.out.println("Este es el request ===>" + request);
        return null;
    }
}
