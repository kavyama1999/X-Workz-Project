package com.xworkz.issuesmanagement.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

//@Component //or
//@Configuration //bcz parent type is component so we can use both

@Configuration
public class MailConfiguration {




    public MailConfiguration()
    {
        System.out.println("Created MailConfiguration.. ");
    }


    @Bean //register in spring container
    public JavaMailSender javaMailSender()
    {
        JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("kmsrcb@gmail.com");
        mailSender.setPassword("pbry pogb ndym rlud");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

}
