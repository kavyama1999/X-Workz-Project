package com.xworkz.issuemanagement.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
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


    public SpringConfiguration() {
        System.out.println("No parameter constructor created for SpringConfiguration..");
    }


    //this for instead of mentioning jsp file like form.jsp just we can declared like this
// ..we can just declare jsp file name
    @Bean
    public ViewResolver viewResolver() {
        System.out.println("Register  viewResolver in SpringConfiguration");
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/"); //mapping..static files
        resolver.setSuffix(".jsp");
        return resolver;
    }


    // js validation to write one ,method

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);
        registry.addResourceHandler("/js/**").addResourceLocations("/javascript/");

        //set path for image display

        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:C:/Users/kavya/issue-management-images/");

    }


    //    @Bean
//    public CommonsMultipartResolver multipartResolver()
//    {
//        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//        multipartResolver.setDefaultEncoding("utf-8");
//        multipartResolver.setMaxUploadSize(2097152); // 2MB
//        return multipartResolver;
//    }


    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(10485760); // 10MB
        return multipartResolver;
    }

    @Bean
    public StandardServletMultipartResolver standardServletMultipartResolver() {
        return new StandardServletMultipartResolver();
    }


    //Encrypt and Decrypt
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}



//for image upload we have to use this
//@Bean
//public CommonsMultipartResolver multipartResolver() {
//    CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//    resolver.setDefaultEncoding("utf-8");
//    resolver.setMaxUploadSize(10 * 1024 * 1024); // 10MB
//    return resolver;
//}