package br.com.fiap.GestaoDeResiduos.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filtrarCadeiaDeSegurança(HttpSecurity http) throws Exception {

        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        // Requesições do Aterro
                        .requestMatchers(HttpMethod.GET, "/api/aterros").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/aterros/**").permitAll()

                        // Requesições da Rota
                        .requestMatchers(HttpMethod.GET, "/api/rotas").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/rotas/**").permitAll()

                        // Requesições do Caminhao
                        .requestMatchers(HttpMethod.GET, "/api/caminhoes").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/caminhoes/**").permitAll()

                        // Requesições da Coleta
                        .requestMatchers(HttpMethod.GET, "/api/coletas").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/coletas/**").permitAll()

                        // Requesições do Funcionario
                        .requestMatchers(HttpMethod.GET, "/api/funcionarios").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/funcionarios/**").permitAll()
                        .anyRequest().authenticated()
                )
                .build();
    }


    @Bean
    public AuthenticationManager getAuthenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
