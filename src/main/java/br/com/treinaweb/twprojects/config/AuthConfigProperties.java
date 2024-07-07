package br.com.treinaweb.twprojects.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Configuration
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "br.com.treinaweb.twprojects.auth")
public class AuthConfigProperties {

    private String rememberMeToken;
    private Integer rememberMeValiditySeconds;
    
}
