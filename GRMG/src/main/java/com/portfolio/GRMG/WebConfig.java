package com.portfolio.GRMG;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer{
    //@Override
    public void addCorsMpappings(CorsRegistry registry){
        registry.addMapping("/**");
    }
    
}
