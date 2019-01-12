package de.dhbw.ics.spring;

import de.dhbw.ics.filesystem.ApplicationEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

;

@Configuration
@ImportResource("classpath:spring/applicationContext.xml")
public class IcsWebConfiguration implements WebMvcConfigurer {

    private static final Logger LOG = LoggerFactory.getLogger(IcsWebConfiguration.class);

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

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (System.getProperty("os.name").contains("Windows")) {
            registry
                    .addResourceHandler("/content/**")
                    .addResourceLocations("file:///".concat(ApplicationEnvironment.getContentPath().toString().concat("/")));
        } else {
            registry
                    .addResourceHandler("/content/**")
                    .addResourceLocations("file://".concat(ApplicationEnvironment.getContentPath().toString().concat("/")));
        }
    }


/*    @Bean
    public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        jsonConverter.setObjectMapper(objectMapper);
        return jsonConverter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(customJackson2HttpMessageConverter());
    }*/

}
