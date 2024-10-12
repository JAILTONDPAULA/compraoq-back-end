package br.com.traumfabrik.compraoq.infra;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class RetornoConfig {
    @Bean
    @Scope("prototype")
    public <T> Retornos<T> retornos() {
        return new Retornos<>();
    }
}
