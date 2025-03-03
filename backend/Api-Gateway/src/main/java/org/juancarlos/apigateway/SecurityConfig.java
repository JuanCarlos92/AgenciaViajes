package org.juancarlos.apigateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration  // Indica clase de config
@EnableWebSecurity // Habilita Spring Security
public class SecurityConfig {

//        public DataSource dataSource() {
//        DriverManagerDataSource ds=new DriverManagerDataSource();
//        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        ds.setUrl("jdbc:mysql://localhost:3306/springsecurity?serverTimezone=UTC");
//        ds.setUsername("root");
//        ds.setPassword("root");
//        return ds;
//    }

    private final DataSource dataSource;

    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //config de users de la BD
    @Bean
    public JdbcUserDetailsManager usersDetailsJdbc() {
        JdbcUserDetailsManager jdbcDetails = new JdbcUserDetailsManager(dataSource);
        jdbcDetails.setUsersByUsernameQuery("SELECT user, pwd, enabled FROM users WHERE user=?");
        jdbcDetails.setAuthoritiesByUsernameQuery("SELECT user, rol  FROM roles WHERE user=?");
        return jdbcDetails;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        //--------------------------------- SECURITY HOTELES --------------------------------
                        .requestMatchers(HttpMethod.GET, "/hoteles").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.POST, "/hoteles").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.POST, "/hoteles/{id}/{disponible}").hasAuthority("ROLE_USER")
                        .requestMatchers(HttpMethod.PUT, "/hoteles").hasAuthority("ROLE_OPERATOR")
                        .requestMatchers(HttpMethod.DELETE, "/hoteles/{id}").hasAuthority("ROLE_ADMIN")
                        //--------------------------------- SECURITY VUELOS --------------------------------
                        .requestMatchers(HttpMethod.POST, "/vuelos").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.POST, "/vuelos/{id}/{disponible}").hasAuthority("ROLE_USER")
                        .requestMatchers(HttpMethod.PUT, "/vuelos").hasAuthority("ROLE_OPERATOR")
                        .requestMatchers(HttpMethod.DELETE, "/vuelos/{id}").hasAuthority("ROLE_ADMIN")
                        //--------------------------------- SECURITY RESERVAS --------------------------------
                        .requestMatchers(HttpMethod.POST, "/reservas").hasAuthority("ROLE_USER")
                        .requestMatchers(HttpMethod.GET, "/reservas").hasAuthority("ROLE_USER")
                        .requestMatchers(HttpMethod.GET, "/reservas/{id}").hasAuthority("ROLE_USER")
                        .requestMatchers(HttpMethod.GET, "/nombre/{nombre}").hasAuthority("ROLE_USER")
                        .requestMatchers(HttpMethod.DELETE, "/reservas/{id}").hasAuthority("ROLE_ADMIN")
                        .anyRequest().permitAll()  // (cualquier otra URL) --> LIBRE SIN RESTRICTION
                )
                .httpBasic(withDefaults());  // Configurar authentication basic
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}

