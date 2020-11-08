package api;

import api.config.ApiConfig;
import core.config.AppConfig;
import core.config.DBConfig;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(AppConfig.class);
        DispatcherServlet servlet = new DispatcherServlet(context);
        ServletRegistration.Dynamic baseServlet = servletContext.addServlet("api", servlet);
        baseServlet.setLoadOnStartup(1);
        baseServlet.addMapping("/api/*");
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {AppConfig.class, DBConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {ApiConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/api/*" };
    }
}
