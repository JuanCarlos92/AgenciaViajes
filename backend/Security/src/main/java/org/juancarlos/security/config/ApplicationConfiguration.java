package org.juancarlos.security.config;

import org.juancarlos.security.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Configuración de autenticación y seguridad para la aplicación.
 * -
 * Define la configuración de Spring Security, incluyendo la gestión de usuarios,
 * codificación de contraseñas y el proveedor de autenticación.
 */
@Configuration
public class ApplicationConfiguration {
    private final UserRepository userRepository;

    /**
     * Constructor que inyecta el repositorio de usuarios.
     *
     * @param userRepository Repositorio para acceder a los usuarios en la base de datos.
     */
    public ApplicationConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Define un `UserDetailsService` que busca un usuario en la base de datos
     * por su nombre de usuario.
     *
     * @return Un `UserDetailsService` que obtiene los detalles del usuario desde la base de datos.
     * @throws UsernameNotFoundException si el usuario no existe.
     */
    @Bean
    UserDetailsService userDetailsService() {
        return username -> userRepository.findByUser(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    /**
     * Define un `BCryptPasswordEncoder` para cifrar y verificar contraseñas.
     *
     * @return Instancia de `BCryptPasswordEncoder`.
     */
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Obtiene el `AuthenticationManager` desde la configuración de autenticación de Spring Security.
     *
     * @param config Configuración de autenticación.
     * @return Instancia de `AuthenticationManager` configurada.
     * @throws Exception Si ocurre un error al obtener el `AuthenticationManager`.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Define un `AuthenticationProvider` basado en DAO para manejar la autenticación de usuarios.
     * -
     * Utiliza `userDetailsService()` para obtener los detalles del usuario y
     * `passwordEncoder()` para validar las contraseñas en la base de datos.
     *
     * @return Instancia de `AuthenticationProvider` configurada.
     */
    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        // Configura el proveedor con el servicio de detalles de usuario
        authProvider.setUserDetailsService(userDetailsService());
        // Configura el proveedor con el codificador de contraseñas
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
}