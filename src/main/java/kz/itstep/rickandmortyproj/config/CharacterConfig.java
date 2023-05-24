package kz.itstep.rickandmortyproj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class CharacterConfig {
    @Bean
    public WebClient openCharacterClient(){
        return WebClient.create("https://rickandmortyapi.com/api/");
    }
}
