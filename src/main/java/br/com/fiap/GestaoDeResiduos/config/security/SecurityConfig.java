package br.com.fiap.GestaoDeResiduos.config.security;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private VerificaToken verificaToken;

    @Bean
    public SecurityFilterChain filtrarCadeiaDeSegurança(HttpSecurity http) throws Exception {

        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        // Requesições do Aterro
                        .requestMatchers(HttpMethod.GET, "/api/aterros").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/aterros/id/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/aterros/status").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/aterros").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/aterros").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/aterros/id/**").hasRole("ADMIN")

                        // Requesições da Rota
                        .requestMatchers(HttpMethod.GET, "/api/rotas").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/rotas/id/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/rotas").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/rotas").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/rotas/id/**").hasRole("ADMIN")

                        // Requesições do Caminhao
                        .requestMatchers(HttpMethod.GET, "/api/caminhoes").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/caminhoes/id/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/caminhoes/ok").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/caminhoes").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/caminhoes").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/caminhoes/id/**").hasRole("ADMIN")

                        // Requesições da Coleta
                        .requestMatchers(HttpMethod.GET, "/api/coletas").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/coletas/id/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/coletas/localizacao/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/coletas").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/coletas").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/coletas/id/**").hasRole("ADMIN")

                        // Requesições do Funcionario
                        .requestMatchers(HttpMethod.GET, "/api/funcionarios").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/funcionarios/id/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/funcionarios/nome/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/funcionarios").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/funcionarios").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/funcionarios/id/**").hasRole("ADMIN")

                        // Requisições do Usuario
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()

                        .anyRequest().authenticated()
                )
                .addFilterBefore(
                        verificaToken,
                        UsernamePasswordAuthenticationFilter.class
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
