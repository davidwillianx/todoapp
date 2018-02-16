package br.com.todoapp.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "br.com.todoapp.controllers")
@Profile("DEV")
public class Bootstrap extends WebMvcConfigurerAdapter {

    @Autowired
    private ServletContext servletContext;

    @Bean
    @Description("Thymeleaf Resolver")
    public ServletContextTemplateResolver templateResolver(){

        ServletContextTemplateResolver templateResolver;
        templateResolver = new ServletContextTemplateResolver(servletContext);

        templateResolver.setPrefix("/app/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");

        return templateResolver;
    }


    @Bean
    @Description("Thymeleaf Engine")
    public SpringTemplateEngine templateEngine(){

        SpringTemplateEngine templateEngine;
        templateEngine = new SpringTemplateEngine();

        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setTemplateEngineMessageSource(messageSource());

        return templateEngine;

    }

    @Bean
    @Description("View resolver using Thymeleaf")
    public ThymeleafViewResolver viewResolver(){

        ThymeleafViewResolver viewResolver;
        viewResolver = new ThymeleafViewResolver();

        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setOrder(1);

        return viewResolver;

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
           .addResourceHandler("/static/**")
           .addResourceLocations("/app/static/");
    }

    @Bean
    @Description("Spring Message resolver")
    public ResourceBundleMessageSource messageSource(){

        ResourceBundleMessageSource messageSource;
        messageSource = new ResourceBundleMessageSource();

        messageSource.setBasename("messages");

        return messageSource;

    }


}
