package com.yash.FMS.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Initializes the Spring MVC DispatcherServlet programmatically (replaces web.xml).
 * Configures the root and web application contexts.
 */
public class DispatcherServletConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * Specifies root application context configuration (e.g., services, data access).
     * @return Root configuration classes (like {@link SpringRootConfig}).
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringRootConfig.class};
    }

    /**
     * Specifies servlet application context configuration (e.g., controllers, view resolvers).
     * @return Servlet configuration classes (like {@link SpringWebConfig}).
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringWebConfig.class};
    }

    /**
     * Maps the DispatcherServlet to URL patterns. "/" handles all requests.
     * @return URL patterns for the DispatcherServlet.
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * Hook for additional programmatic ServletContext configuration (e.g., filters).
     * @param servletContext The web application's ServletContext.
     * @throws ServletException On startup error.
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        // Optional: Add further servlet context customization here.
    }
}