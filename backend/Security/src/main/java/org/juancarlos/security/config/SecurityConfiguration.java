package org.juancarlos.security.config;

import org.juancarlos.security.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * Clase de configuración de seguridad que define las reglas y filtros de seguridad para la aplicación.
 * Se encarga de configurar la autorización, autenticación y CORS para la protección de los recursos de la aplicación.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * Constructor que inyecta las dependencias necesarias para la configuración de seguridad.
     *
     * @param jwtAuthenticationFilter el filtro de autenticación basado en JWT
     * @param authenticationProvider  el proveedor de autenticación que valida las credenciales
     */
    public SecurityConfiguration(
            JwtAuthenticationFilter jwtAuthenticationFilter,
            AuthenticationProvider authenticationProvider
    ) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    /**
     * Configura los filtros de seguridad para la aplicación.
     * Define las reglas de autorización para las rutas de la aplicación y establece la política de sesiones.
     *
     * @param http el objeto HttpSecurity que permite configurar la seguridad de las solicitudes HTTP
     * @return un objeto SecurityFilterChain que contiene la configuración de seguridad
     * @throws Exception si ocurre un error durante la configuración de la seguridad
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) // Deshabilita la protección CSRF
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/auth/**").permitAll() // Permite el acceso sin autenticación a los endpoints /auth/**
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Requiere que el usuario tenga el rol ADMIN para acceder a /admin/**
                        .requestMatchers("/db/**").access(new WebExpressionAuthorizationManager("hasRole('ADMIN') and hasRole('DBA')"))
                        // .requestMatchers("/db/**").access(AuthorizationManagers.allOf(AuthorityAuthorizationManager.hasRole("ADMIN"), AuthorityAuthorizationManager.hasRole("DBA")))
                        .anyRequest().permitAll() // Permite el acceso a cualquier otra solicitud sin restricciones
                )
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Define que no se usará sesión en el servidor, utilizando JWT para autenticación
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
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