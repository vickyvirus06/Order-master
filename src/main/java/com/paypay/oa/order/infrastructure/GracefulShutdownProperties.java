package com.paypay.oa.order.infrastructure;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Component
@ConfigurationProperties(prefix = "graceful-shutdown")
@Validated
@Getter
public class GracefulShutdownProperties {

    @Valid
    private final Tomcat tomcat = new Tomcat();

    @Data
    public static class Tomcat {

        @Min(0)
        private long shutdownGracePeriodInSeconds;

        @Min(0)
        private long shutdownNowGracePeriodInSeconds;

        @Min(0)
        private int keepAliveTimeoutInSeconds;

        @Min(0)
        private long waitBeforeCloseInSeconds;
    }
}