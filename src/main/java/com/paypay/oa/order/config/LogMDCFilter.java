package com.paypay.oa.order.config;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Component
@Slf4j
public class LogMDCFilter extends OncePerRequestFilter {
    public static final String RESPONSE_REQUEST_ID = "X-REQUEST-ID";
    private static final String MDC_TOKEN_KEY = "logMDCFilter.UUID";
    static final String PATH_INFO = "PATH_INFO";

    public LogMDCFilter() {

    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
                                    final FilterChain chain) throws java.io.IOException, ServletException {

        try {

            final StringBuilder token = new StringBuilder();

            token.append("OAORDM").append(UUID.randomUUID().toString().toUpperCase().replace("-", ""));

            MDC.put(MDC_TOKEN_KEY, token.toString());

            response.addHeader(RESPONSE_REQUEST_ID, token.toString());

            String pathInfo = String.format("%s : URL : %s", request.getMethod(), request.getServletPath());

            log.info(pathInfo);

            //insert path info into MDC as its thread safe
            MDC.put(PATH_INFO, pathInfo);

            chain.doFilter(request, response);

        } finally {

            MDC.remove(MDC_TOKEN_KEY);
            // if the path value exists we need to clear it
            if (null != MDC.get(PATH_INFO)) MDC.remove(PATH_INFO);
        }
    }

}
