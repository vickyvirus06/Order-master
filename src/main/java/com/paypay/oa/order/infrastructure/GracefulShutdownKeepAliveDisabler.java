package com.paypay.oa.order.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class GracefulShutdownKeepAliveDisabler extends OncePerRequestFilter {

    private static String KEEP_ALIVE_HEADER = "Keep-Alive";
    private final GracefulShutdown gracefulShutdown;
    private final GracefulShutdownProperties properties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

        if (gracefulShutdown.isShuttingDown()) {
            response.addHeader(HttpHeaders.CONNECTION, "close");
        } else {
            // the keep-alive header is not honored by all the http client
            // add 1s buffer because there can be a network lag
            response.addHeader(KEEP_ALIVE_HEADER,
                "timeout=" + (properties.getTomcat().getKeepAliveTimeoutInSeconds() > 1 ?
                    properties.getTomcat().getKeepAliveTimeoutInSeconds() - 1 : 0));
        }
        filterChain.doFilter(request, response);
    }
}
