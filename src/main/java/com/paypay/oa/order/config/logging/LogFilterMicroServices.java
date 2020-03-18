package com.paypay.oa.order.config.logging;

import org.springframework.context.annotation.Configuration;

import java.net.URI;

@Configuration
public class LogFilterMicroServices {

    public LogFilterMicroServices() {
    }


    public boolean isOutput(URI requestURI) {
        // @todo in Day1, all request/response log masked.
        return false;
        /*
        String hostName = requestURI.getHost();
        if(URI.create(userModulePublicServiceClientConfiguration.getBaseUrl()).getHost().equals(hostName)) {
            return false;
        } else if (URI.create(walletClientConfiguration.getBaseUrl()).getHost().equals(hostName)) {
            return false;
        } else if (URI.create(walletClientConfiguration.getBaseUrl()).getHost().equals(hostName)) {
            return false;
        }
        return true;
        */
    }
}
