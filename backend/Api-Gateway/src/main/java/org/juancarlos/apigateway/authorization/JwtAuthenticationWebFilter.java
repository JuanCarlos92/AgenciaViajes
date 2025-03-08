package org.juancarlos.apigateway.authorization;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Filtro de autenticación JWT que intercepta cada solicitud HTTP para validar el token de autenticación.
 * <p>
 * Extiende {@link OncePerRequestFilter} para garantizar que el filtro se ejecute una sola vez por solicitud.
 * Se encarga de extraer el token JWT de la cabecera de autorización, validarlo y establecer la autenticación en el contexto de seguridad.
 * </p>
 */
@Component
public class JwtAuthenticationWebFilter implements WebFilter {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String token = getTokenFromRequest(exchange);
        if (token != null) {
            Authentication authentication = getAuthentication(token);
            SecurityContext securityContext = new SecurityContextImpl(authentication);

            return chain.filter(exchange)
                    .contextWrite(ReactiveSecurityContextHolder.withSecurityContext(Mono.just(securityContext)));
        }

        return chain.filter(exchange);
    }

    private String getTokenFromRequest(ServerWebExchange exchange) {
        String bearerToken = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // Elimina "Bearer " para obtener solo el token
        }
        return null;
    }

    private Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        String role = claims.get("role", String.class);
        System.out.println(role);
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));

        return new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorities);
    }
}

