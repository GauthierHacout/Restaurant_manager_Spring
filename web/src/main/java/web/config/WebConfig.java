package web.config;

import com.shieldsolutions.velocity.view.VelocityConfigurer;
import com.shieldsolutions.velocity.view.VelocityViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "web.controller")
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");
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
