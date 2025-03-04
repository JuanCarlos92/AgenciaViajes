package org.juancarlos.security.service;

import io.jsonwebtoken.Claims;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Servicio para la generación, validación y extracción de información de tokens JWT.
 */
@Service
public class JwtService {

    /**
     * Clave secreta para firmar los tokens JWT, obtenida de las propiedades de configuración.
     */
    @Value("${security.jwt.secret-key}")
    private String secretKey;

    /**
     * Tiempo de expiración del token JWT en milisegundos, definido en la configuración.
     */
    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;

    /**
     * Extrae el nombre de usuario del token JWT.
     *
     * @param token El token JWT del cual extraer el nombre de usuario.
     * @return El nombre de usuario contenido en el token.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extrae un reclamo específico del token JWT.
     *
     * @param token          El token JWT.
     * @param claimsResolver Función para extraer el reclamo deseado.
     * @param <T>            Tipo del reclamo extraído.
     * @return El valor del reclamo extraído.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Genera un token JWT sin reclamos adicionales.
     *
     * @param userDetails Detalles del usuario para el cual generar el token.
     * @return Token JWT generado.
     */
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Genera un token JWT con reclamos adicionales.
     *
     * @param extraClaims Mapa de reclamos adicionales a incluir en el token.
     * @param userDetails Detalles del usuario para el cual generar el token.
     * @return Token JWT generado.
     */
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    /**
     * Obtiene el tiempo de expiración configurado para los tokens JWT.
     *
     * @return Tiempo de expiración en milisegundos.
     */
    public long getExpirationTime() {
        return jwtExpiration;
    }

    /**
     * Construye un token JWT con los parámetros proporcionados.
     *
     * @param extraClaims Reclamos adicionales a incluir en el token.
     * @param userDetails Detalles del usuario asociado al token.
     * @param expiration  Tiempo de expiración del token en milisegundos.
     * @return Token JWT generado.
     */
    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Verifica si un token JWT es válido para un usuario específico.
     *
     * @param token       Token JWT a validar.
     * @param userDetails Detalles del usuario asociado al token.
     * @return true si el token es válido, false en caso contrario.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    /**
     * Verifica si un token JWT ha expirado.
     *
     * @param token Token JWT a verificar.
     * @return true si el token ha expirado, false en caso contrario.
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extrae la fecha de expiración de un token JWT.
     *
     * @param token Token JWT.
     * @return Fecha de expiración del token.
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extrae todos los reclamos de un token JWT.
     *
     * @param token Token JWT.
     * @return Objeto {@link Claims} con la información del token.
     */
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Obtiene la clave secreta utilizada para firmar los tokens JWT.
     *
     * @return Clave secreta en formato {@link Key}.
     */
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}