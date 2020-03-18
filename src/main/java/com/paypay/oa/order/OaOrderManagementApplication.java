package com.paypay.oa.order;

import com.paypay.oa.order.infrastructure.GracefulShutdown;
import com.paypay.oa.order.infrastructure.GracefulShutdownProperties;
import io.micrometer.core.instrument.MeterRegistry;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OaOrderManagementApplication {
    static final String ENV = "env";
    static final String SERVICE_ID = "serviceId";
    static final String K8S_NODE_NAME = "node";
    static final String K8S_POD_NAME = "pod_name";
    static final String K8S_POD_NAMESPACE = "kube_namespace";
    static final String K8S_POD_TEMPLATE_HASH = "kube_pod_template_hash";

    @Value("${spring.application.name:defaultName}")
    String serviceId;

    @Value("${metrics.target.environment:local}")
    String env;

    @Value("${k8s.node.name:none}")
    String k8sNodeName;

    @Value("${k8s.pod.name:none}")
    String k8sPodName;

    @Value("${k8s.pod.namespace:none}")
    String k8sPodNamespace;

    @Value("${k8s.pod.template.hash:none}")
    String k8sPodTemplateHash;

    @Bean
    MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry ->
                registry
                        .config()
                        .commonTags(
                                SERVICE_ID,
                                serviceId,
                                ENV,
                                env,
                                K8S_NODE_NAME,
                                k8sNodeName,
                                K8S_POD_NAME,
                                k8sPodName,
                                K8S_POD_NAMESPACE,
                                k8sPodNamespace);
    }

    public static void main(String[] args) {
        System.setProperty("log4j.shutdownHookEnabled", Boolean.toString(false));
        SpringApplication.run(OaOrderManagementApplication.class, args);
    }

    @Bean
    public TomcatServletWebServerFactory servletContainerFactory(final GracefulShutdown gracefulShutdown, GracefulShutdownProperties properties) {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        // Add keep alive timeout for Tomcat.
        // Server will cut off connection actively when a TCP connection is idle for such period of time.
        // Note that some HTTP client can handle this properly but some can not, so as back up plan, we also add keep-alive header.
        factory.addConnectorCustomizers(connector ->
                ((AbstractHttp11Protocol) connector.getProtocolHandler()).setKeepAliveTimeout(properties.getTomcat().getKeepAliveTimeoutInSeconds() * 1000));
        factory.addConnectorCustomizers(gracefulShutdown);
        return factory;
    }
}
