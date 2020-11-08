package web.config;

import com.shieldsolutions.velocity.view.VelocityConfigurer;
import com.shieldsolutions.velocity.view.VelocityViewResolver;
import core.formatter.ProductFormatter;
import core.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "web.controller")
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        //final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("core.config");
        final ProductService productService = applicationContext.getBean(ProductService.class);
        ProductFormatter productFormatter = new ProductFormatter(productService);
        registry.addFormatter(productFormatter);
    }

    @Bean
    public VelocityConfigurer configurer() {
        VelocityConfigurer configurer = new VelocityConfigurer();
        configurer.setResourceLoaderPath("/WEB-INF/velocity");
        return configurer;
    }

    @Bean
    public VelocityViewResolver resolver() {
        VelocityViewResolver resolver = new VelocityViewResolver();
        resolver.setSuffix(".vm");
        return resolver;
    }

}
