package org.juancarlos.apigateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration  // Indica clase de config
@EnableWebSecurity // Habilita Spring Security
public class SecurityConfig {

    private final DataSource dataSource;

    public SecurityConfig(DataSource dataSource) { // Inyeccion de dependencia
        this.dataSource = dataSource;
    }

    //config de users de la BD
    @Bean
    public JdbcUserDetailsManager usersDetailsJdbc() {
        JdbcUserDetailsManager jdbcDetails = new JdbcUserDetailsManager(dataSource);
        jdbcDetails.setUsersByUsernameQuery("select user, pwd, enabled"
                + " from users where user=?");
        jdbcDetails.setAuthoritiesByUsernameQuery("select user, rol "
                + "from roles where user=?");
        return jdbcDetails;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        //--------------------------------- SECURITY HOTELES --------------------------------
                        .requestMatchers(HttpMethod.GET, "/hoteles").hasAuthority("ROLE_GUEST")
                        .requestMatchers(HttpMethod.GET, "/hoteles//{id}").hasAuthority("ROLE_GUEST")
                        .requestMatchers(HttpMethod.GET, "/nombre/{nombre}").hasAuthority("ROLE_GUEST")
                        .requestMatchers(HttpMethod.POST, "/hoteles").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.POST, "/hoteles/{id}/{disponible}").hasAuthority("ROLE_USER")
                        .requestMatchers(HttpMethod.PUT, "/hoteles").hasAuthority("ROLE_OPERATOR")
                        .requestMatchers(HttpMethod.DELETE, "/hoteles/{id}").hasAuthority("ROLE_ADMIN")
                        //--------------------------------- SECURITY VUELOS --------------------------------
                        .requestMatchers(HttpMethod.GET, "/vuelos").hasAuthority("ROLE_GUEST")
                        .requestMatchers(HttpMethod.GET, "/vuelos//{id}").hasAuthority("ROLE_GUEST")
                        .requestMatchers(HttpMethod.GET, "/nombre/{nombre}").hasAuthority("ROLE_GUEST")
                        .requestMatchers(HttpMethod.POST, "/vuelos").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.POST, "/vuelos/{id}/{disponible}").hasAuthority("ROLE_USER")
                        .requestMatchers(HttpMethod.PUT, "/vuelos").hasAuthority("ROLE_OPERATOR")
                        .requestMatchers(HttpMethod.DELETE, "/vuelos/{id}").hasAuthority("ROLE_ADMIN")
                        //--------------------------------- SECURITY RESERVAS --------------------------------
                        .requestMatchers(HttpMethod.POST, "/reservas").hasAuthority("ROLE_USER")
                        .requestMatchers(HttpMethod.GET, "/reservas").hasAuthority("ROLE_USER")
                        .requestMatchers(HttpMethod.GET, "/reservas//{id}").hasAuthority("ROLE_USER")
                        .requestMatchers(HttpMethod.GET, "/nombre/{nombre}").hasAuthority("ROLE_USER")
                        .requestMatchers(HttpMethod.DELETE, "/reservas/{id}").hasAuthority("ROLE_ADMIN")
                        .anyRequest().permitAll()  // (cualquier otra URL) --> LIBRE SIN RESTRICTION
                )
                .httpBasic(withDefaults());  // Configurar authentication basic

        return http.build();
    }

}
