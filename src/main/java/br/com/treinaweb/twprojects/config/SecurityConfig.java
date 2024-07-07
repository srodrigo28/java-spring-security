package br.com.treinaweb.twprojects.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.treinaweb.twprojects.core.services.auth.Authority;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
// @EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final AuthConfigProperties authConfigProperties;

    private static final String[] ADMIN_MATCHERS = {
        "/*/create",
        "/*/edit/**",
        "/*/delete/**",
    };

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests(customizer -> customizer
                .requestMatchers(ADMIN_MATCHERS)
                    .hasAuthority(Authority.ADMIN.name())
                .anyRequest().authenticated()
                // .anyRequest().permitAll()
            )
            .formLogin(customizer -> customizer
                .loginPage("/auth/login")
                .defaultSuccessUrl("/")
                .permitAll()
            )
            .logout(customizer -> customizer
                .logoutRequestMatcher(new AntPathRequestMatcher(
                    "/auth/logout", "GET"
                ))
                .logoutSuccessUrl("/auth/login")
            )
            .rememberMe(customizer -> customizer
                .key(authConfigProperties.getRememberMeToken())
                .tokenValiditySeconds(authConfigProperties.getRememberMeValiditySeconds())
            )
            .build();
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
            .requestMatchers("/webjars/**");
    }
    
}
