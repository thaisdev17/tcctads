package com.thais.demotcc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemotccApplication {

    
    @Bean
    public FilterRegistrationBean filtroJwtFuncionario() {
        FilterRegistrationBean fr = new FilterRegistrationBean();
        fr.setFilter(new FiltroFuncionario());
        fr.addUrlPatterns("/autentFuncio/*");
        return fr;
    }

    @Bean
    public FilterRegistrationBean filtroJwtAdmin() {
        FilterRegistrationBean fr = new FilterRegistrationBean();
        fr.setFilter(new FiltroProprietario());
        fr.addUrlPatterns("/autentProprie/*");
        return fr;
    }
    
    public static void main(String[] args) {
	SpringApplication.run(DemotccApplication.class, args);
    }

}

