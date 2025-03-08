package org.juancarlos.apigateway.authorization;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * Clase de configuración de seguridad que define las reglas y filtros de seguridad para la aplicación.
 * Se encarga de configurar la autorización, autenticación y CORS para la protección de los recursos de la aplicación.
 */
@Configuration
public class SecurityConfiguration {

    /**
     * Configura los filtros de seguridad para la aplicación.
     * Define las reglas de autorización para las rutas de la aplicación y establece la política de sesiones.
     *
     * @param http el objeto HttpSecurity que permite configurar la seguridad de las solicitudes HTTP
     * @return un objeto SecurityFilterChain que contiene la configuración de seguridad
     */
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http, JwtAuthenticationWebFilter jwtAuthenticationWebFilter) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(auth -> auth
                        .pathMatchers("/swagger-ui.html").permitAll()
                        .pathMatchers("/swagger-ui/**").permitAll()
                        .pathMatchers(HttpMethod.POST, "/hoteles/**").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.PUT, "/hoteles/**").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.DELETE, "/hoteles/**").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.POST, "/vuelos/**").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.PUT, "/vuelos/**").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.DELETE, "/vuelos/**").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.DELETE, "/reservas/**").hasRole("ADMIN")
                        .anyExchange().authenticated()
                )
                .addFilterAt(jwtAuthenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION) // Agregar filtro manualmente
                .build();
    }

    /**
     * Configura las reglas de CORS para la aplicación.
     * Permite solicitudes desde un origen específico y restringe los métodos HTTP y encabezados permitidos.
     *
     * @return un objeto CorsConfigurationSource que gestiona las configuraciones de CORS
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("http://localhost:8005")); // Permite solicitudes desde el origen http://localhost:8005
        configuration.setAllowedMethods(List.of("GET", "POST")); // Permite los métodos GET y POST
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type")); // Permite los encabezados Authorization y Content-Type

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration); // Registra la configuración de CORS para todas las rutas

        return source;
    }

}