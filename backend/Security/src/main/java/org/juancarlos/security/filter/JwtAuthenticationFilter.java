package org.juancarlos.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.juancarlos.security.service.JwtService;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

/**
 * Filtro de autenticación JWT que intercepta cada solicitud HTTP para validar el token de autenticación.
 * <p>
 * Extiende {@link OncePerRequestFilter} para garantizar que el filtro se ejecute una sola vez por solicitud.
 * Se encarga de extraer el token JWT de la cabecera de autorización, validarlo y establecer la autenticación en el contexto de seguridad.
 * </p>
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final HandlerExceptionResolver handlerExceptionResolver;

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    /**
     * Constructor que inyecta las dependencias necesarias.
     *
     * @param jwtService               Servicio para manejar operaciones con JWT.
     * @param userDetailsService       Servicio para cargar los detalles del usuario.
     * @param handlerExceptionResolver Manejador global de excepciones en Spring.
     */
    public JwtAuthenticationFilter(
            JwtService jwtService,
            UserDetailsService userDetailsService,
            HandlerExceptionResolver handlerExceptionResolver
    ) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    /**
     * Metodo que intercepta cada solicitud HTTP para validar el token JWT.
     * <p>
     * Si el encabezado "Authorization" contiene un token JWT válido, se autentica el usuario
     * y se establece en el {@link SecurityContextHolder}.
     * </p>
     *
     * @param request     La solicitud HTTP entrante.
     * @param response    La respuesta HTTP saliente.
     * @param filterChain La cadena de filtros para continuar la ejecución.
     * @throws ServletException En caso de error en el procesamiento del filtro.
     * @throws IOException      Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        // Si no hay encabezado o no tiene formato correcto, se continúa sin procesar el filtro
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            // Extrae el token sin la palabra "Bearer"
            final String jwt = authHeader.substring(7);
            final String userEmail = jwtService.extractUsername(jwt);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            // Si el usuario no está autenticado y el token es válido, se procede a autenticarlo
            if (userEmail != null && authentication == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

                if (jwtService.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            // Continúa con la cadena de filtros
            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            // En caso de error, delega el manejo de excepciones al resolver global
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }
    }
}

