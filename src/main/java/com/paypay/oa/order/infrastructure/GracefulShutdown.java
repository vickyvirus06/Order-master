package com.paypay.oa.order.infrastructure;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class GracefulShutdown implements TomcatConnectorCustomizer, ApplicationListener<ContextClosedEvent> {

    private static final Logger log = LoggerFactory.getLogger(GracefulShutdown.class);
    private final GracefulShutdownProperties properties;
    private final ApplicationContext applicationContext;
    private volatile Connector connector;
    @Getter
    private volatile boolean isShuttingDown;


    @Override
    public void customize(Connector connector) {
        this.connector = connector;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        isShuttingDown = true;

        if (applicationContext.equals(event.getApplicationContext())) {
            log.info("Received ContextClosedEvent, start graceful shutdown");

            log.info("Wait {} seconds before stop accepting requests", properties.getTomcat().getWaitBeforeCloseInSeconds());
            try {
                Thread.sleep(properties.getTomcat().getWaitBeforeCloseInSeconds() * 1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt(); //restore the interruption status
                log.info("Wait before close gets interrupted: ", ex);
                log.info("We will continue the shutdown process,  some requests may be rejected");
            }
        } else {
            log.debug("Received ContextClosedEvent from non-local context, skip it. {}", event.getApplicationContext());
            return;
        }

        this.connector.pause();
        Executor executor = this.connector.getProtocolHandler().getExecutor();
        if (executor instanceof ThreadPoolExecutor) {
            ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
            log.info("ThreadPoolExecutor: Gracefully shutting down " + threadPoolExecutor.toString());
            try {
                // Disable new tasks from being submitted
                threadPoolExecutor.shutdown();
                log.info("ThreadPoolExecutor: shutdown");

                // Wait a while for existing tasks to terminate
                if (!threadPoolExecutor.awaitTermination(properties.getTomcat().getShutdownGracePeriodInSeconds(), TimeUnit.SECONDS)) {
                    log.warn("ThreadPoolExecutor: Tomcat thread pool did not shut down gracefully within "
                        + properties.getTomcat().getShutdownGracePeriodInSeconds()
                        + " seconds. Proceeding with forceful shutdown");

                    // Cancel currently executing tasks
                    threadPoolExecutor.shutdownNow();
                    log.info("ThreadPoolExecutor: shutdownNow");

                    // Wait a while for tasks to respond to being cancelled
                    if (!threadPoolExecutor.awaitTermination(properties.getTomcat().getShutdownNowGracePeriodInSeconds(), TimeUnit.SECONDS)) {
                        log.error("ThreadPoolExecutor: Tomcat thread pool did not terminate within "
                            + properties.getTomcat().getShutdownNowGracePeriodInSeconds()
                            + " seconds.");
                    }
                }
            } catch (InterruptedException ex) {
                log.debug("ThreadPoolExecutor: InterruptedException");
                // (Re-)Cancel if current thread also interrupted
                threadPoolExecutor.shutdownNow();
                // Preserve interrupt status
                Thread.currentThread().interrupt();
            }
        }
        log.debug("Exit onApplicationEvent");
    }
}
