package com.xworkz.issuemanagement.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("com.xworkz.issuemanagement")
@PropertySource("classpath:application.properties")  //for database configuration
@EnableWebMvc  //for form validation
public class SpringConfiguration implements WebMvcConfigurer {




    public SpringConfiguration()
    {
        System.out.println("No parameter constructor created for SpringConfiguration..");
    }



    //this for instead of mentioning jsp file like form.jsp just we can declared like this
// ..we can just declare jsp file name
    @Bean
    public ViewResolver viewResolver()
    {
        System.out.println("Register  viewResolver in SpringConfiguration");
        InternalResourceViewResolver resolver= new InternalResourceViewResolver();
        resolver.setPrefix("/"); //mapping..static files
        resolver.setSuffix(".jsp");
        return resolver;
    }



    // js validation to write one ,method

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        WebMvcConfigurer.super.addResourceHandlers(registry);
        registry.addResourceHandler("/js/**").addResourceLocations("/javascript/");
    }

}
