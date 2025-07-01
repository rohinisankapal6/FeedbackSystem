package com.yash.FMS.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
// import org.springframework.web.multipart.commons.CommonsMultipartResolver; // Uncomment if multipart is needed
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter; // Note: WebMvcConfigurer is preferred now
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Configures Spring Web MVC components.
 * Enables MVC features, scans for web controllers, sets up view resolution,
 * and configures static resource handling.
 */
@Configuration
@ComponentScan(basePackages = {"com.yash"}) // Scans for @Controller, @RestController, etc.
@EnableWebMvc // Enables Spring MVC functionalities (request mapping, validation, etc.)
// Extends deprecated adapter; implementing WebMvcConfigurer is recommended for newer Spring versions.
public class SpringWebConfig extends WebMvcConfigurerAdapter {

    /**
     * Configures serving static resources (e.g., CSS, JS, images).
     * Maps requests to `/static/**` to the `/static/` resource directory.
     *
     * @param registry Registry for adding resource handlers.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Serves files from /static/ path (e.g., /static/css/style.css)
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }

    /**
     * Creates the {@link ViewResolver} bean for mapping logical view names to JSP files.
     * Sets the prefix for the view path and the suffix for the file extension.
     *
     * @return Configured {@link InternalResourceViewResolver} for JSPs.
     */
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver vr = new InternalResourceViewResolver();
        vr.setViewClass(JstlView.class); // Use JSTL views
        vr.setPrefix("/WEB-INF/views/"); // Path to view files
        vr.setSuffix(".jsp"); // View file extension
        return vr;
    }

    /*
     * Uncomment to enable multipart (file upload) handling.
     * Requires commons-fileupload dependency.
     */
    // @Bean public CommonsMultipartResolver multipartResolver() {
    //   CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
    //   multipartResolver.setMaxUploadSize(10485760); // 10MB max upload size example
    //   return multipartResolver;
    // }
}