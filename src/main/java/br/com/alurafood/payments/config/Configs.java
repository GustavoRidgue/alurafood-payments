package br.com.alurafood.payments.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configs {

    @Bean
    public ModelMapper obtainModelMapper() {
        return new ModelMapper();
    }
}
