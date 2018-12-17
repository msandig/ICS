package de.dhbw.ics.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@Configuration
@ImportResource("classpath:spring/applicationContext.xml")
@SpringBootApplication
public class IcsApplication {

    private static final Logger LOG = LoggerFactory.getLogger(IcsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(IcsApplication.class, args);
    }

    @Bean
    protected ServletContextListener listener() {
        return new ServletContextListener() {
            @Override
            public void contextInitialized(ServletContextEvent sce) {
                LOG.info("ServletContext initialized");
            }

            @Override
            public void contextDestroyed(ServletContextEvent sce) {
                LOG.info("ServletContext destroyed");
            }
        };
    }


}
